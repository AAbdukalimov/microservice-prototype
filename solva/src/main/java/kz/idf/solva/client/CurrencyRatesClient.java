package kz.idf.solva.client;

import kz.idf.solva.dto.CurrencyRateDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static kz.idf.solva.utility.ConstantsUtility.ONE;
import static kz.idf.solva.utility.SqlUtility.*;

@Slf4j
@Component
@AllArgsConstructor
public class CurrencyRatesClient {

    private RestTemplate restTemplate;
    private JdbcTemplate jdbcTemplate;

    public CurrencyRateDto getRate() {
        CurrencyRateDto currencyRateDto = restTemplate.getForObject(CURRENCY_RATE_UPDATE_RESOURCE_URL, CurrencyRateDto.class);
        assert currencyRateDto != null;
        jdbcTemplate.update(CREATE_CURRENCY_RATE,
                currencyRateDto.getSymbol(),
                currencyRateDto.getDatetime(),
                currencyRateDto.getClose(),
                currencyRateDto.getPreviousClose(),
                currencyRateDto.getIsMarketOpen());
        return currencyRateDto;
    }

    public Double getActualRate(LocalDate date) {
        CurrencyRateDto actualRate;
        Stream<CurrencyRateDto> stream = jdbcTemplate.queryForStream
                (FIND_ALL_CURRENCY_RATES, new BeanPropertyRowMapper<>(CurrencyRateDto.class));
        List<CurrencyRateDto> list = stream
                .filter(rate -> rate.getDatetime().equals(date))
                .toList();
        if(list.isEmpty()){
            return getRate().getPreviousClose();
        }
        actualRate = list.get(list.size() - ONE);
        if (actualRate == null) {
            actualRate = getRate();
        }
        if (!actualRate.getIsMarketOpen()) {
            return actualRate.getPreviousClose();
        }
        return actualRate.getClose();
    }

}

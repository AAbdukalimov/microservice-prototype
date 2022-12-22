package kz.idf.solva.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @JsonSetter("symbol")
    private String symbol;
    @JsonSetter("datetime")
    private LocalDate datetime;
    @JsonSetter("close")
    private Double close;
    @JsonSetter("previous_close")
    private Double previousClose;
    @JsonSetter("is_market_open")
    private Boolean isMarketOpen;

}

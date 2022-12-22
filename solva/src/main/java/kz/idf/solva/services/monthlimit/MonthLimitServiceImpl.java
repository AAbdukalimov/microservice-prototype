package kz.idf.solva.services.monthlimit;

import kz.idf.solva.dto.MonthLimitDto;
import kz.idf.solva.entities.MonthLimit;
import kz.idf.solva.repositories.monthlimit.MonthLimitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static kz.idf.solva.utility.ConstantsUtility.*;

@Service
@AllArgsConstructor
public class MonthLimitServiceImpl implements MonthLimitService {

    private MonthLimitRepository monthLimitRepository;

    @Override
    public MonthLimit create(MonthLimit value) {                                                      //2.	Храним месячный лимит по расходам в долларах США (USD)
        value.setStartingDate(LocalDate.now());                                                       // раздельно для двух категорий расходов: товаров и услуг
        value.setEndingDate(value.getStartingDate().plusMonths(ONE_MONTH));       //При установлении нового лимита микросервисом автоматически выставляется текущая дата
        value.setCurrency(USD);
        return monthLimitRepository.create(value);
    }

    @Override
    public List<MonthLimit> findAll() {
        return monthLimitRepository.findAll();
    }

    @Override
    public MonthLimit findActualLimitByCategory(String expenseCategory) {
        return monthLimitRepository.findActualLimitByCategory(expenseCategory);
    }

    @Override
    public Double sumTransactionFromLimitPeriod(String expenseCategory) {
        return monthLimitRepository.sumTransactionFromLimitPeriod(expenseCategory);
    }

    @Override
    public MonthLimit toMonthLimit(MonthLimitDto monthLimitDto) {
        return monthLimitRepository.toMonthLimit(monthLimitDto);
    }
}

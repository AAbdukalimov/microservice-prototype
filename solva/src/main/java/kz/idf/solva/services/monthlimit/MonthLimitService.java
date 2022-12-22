package kz.idf.solva.services.monthlimit;

import kz.idf.solva.dto.MonthLimitDto;
import kz.idf.solva.entities.MonthLimit;
import kz.idf.solva.services.Service;
import java.util.List;

public interface MonthLimitService extends Service<MonthLimit> {

    List<MonthLimit> findAll();

    MonthLimit findActualLimitByCategory(String expenseCategory);

    Double sumTransactionFromLimitPeriod(String expenseCategory);

    MonthLimit toMonthLimit(MonthLimitDto monthLimitDto);


}

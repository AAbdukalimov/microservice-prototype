package kz.idf.solva.services.monthlimit;

import kz.idf.solva.dto.MonthLimitDto;
import kz.idf.solva.entities.MonthLimit;
import kz.idf.solva.services.Service;

import java.time.LocalDate;
import java.util.List;

public interface MonthLimitService extends Service<MonthLimit> {

    List<MonthLimit> findAll();



    Double sumTransactionFromLimitPeriod(String expenseCategory, LocalDate dateTime);

    MonthLimit toMonthLimit(MonthLimitDto monthLimitDto);


}

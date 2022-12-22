package kz.idf.solva.repositories.monthlimit;

import kz.idf.solva.dto.MonthLimitDto;
import kz.idf.solva.entities.MonthLimit;
import kz.idf.solva.repositories.Repository;

import java.time.LocalDate;
import java.util.List;

public interface MonthLimitRepository extends Repository<MonthLimit> {

    List<MonthLimit> findAll();
    MonthLimit findActualLimitByCategory(String expenseCategory);

    Double sumTransactionFromLimitPeriod(String expenseCategory);

    MonthLimit findLimitByDate(LocalDate transactionDate);
    MonthLimit createDefaultLimit(String expenseCategory);
    Boolean checkLimitTable(String expenseCategory);
    MonthLimit toMonthLimit(MonthLimitDto monthLimitDto);
}

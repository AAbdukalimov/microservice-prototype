package kz.idf.solva.repositories.monthlimit;

import kz.idf.solva.dto.MonthLimitDto;
import kz.idf.solva.entities.MonthLimit;
import kz.idf.solva.entities.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static kz.idf.solva.utility.ConstantsUtility.*;
import static kz.idf.solva.utility.SqlUtility.*;

@Repository
@AllArgsConstructor
public class MonthLimitRepositoryImpl implements MonthLimitRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    public MonthLimit create(MonthLimit value) {      // 2. Храним месячный лимит по расходам в долларах США (USD) раздельно для двух категорий расходов: товаров и услуг
        jdbcTemplate.update(CREATE_MONTH_LIMIT,
                value.getStartingDate(),
                value.getEndingDate(),
                value.getExpenseCategory(),
                value.getCurrency(),
                value.getSumLimit());
        return value;
    }

    @Override
    public List<MonthLimit> findAll() {
        Stream<MonthLimit> stream = jdbcTemplate.queryForStream
                (FIND_ALL_MONTH_LIMITS, new BeanPropertyRowMapper<>(MonthLimit.class));
        return stream.toList();
    }

    @Override
    public MonthLimit findActualLimit(String expenseCategory, LocalDate dateTime) {
        MonthLimit monthLimit;
        Stream<MonthLimit> stream = jdbcTemplate.queryForStream(
                "SELECT * FROM solva.month_limit WHERE ? BETWEEN starting_date AND ending_date;",
                new BeanPropertyRowMapper<>(MonthLimit.class),
                dateTime);
        List<MonthLimit> limits = stream
                .filter(limit -> limit.getExpenseCategory().equals(expenseCategory))
                .toList();
        if (checkLimitTable(expenseCategory)) {
            monthLimit = createDefaultLimit(expenseCategory);
        } else monthLimit = limits.get(limits.size() - ONE);
        return monthLimit;
    }

    @Override
    public Double sumTransactionFromLimitPeriod(String expenseCategory, LocalDate dateTime) {
        MonthLimit limit = findActualLimit(expenseCategory, dateTime);
        Stream<Transaction> stream = jdbcTemplate.queryForStream(
                "SELECT * FROM solva.transaction WHERE date_time BETWEEN ? AND ?;",
                new BeanPropertyRowMapper<>(Transaction.class),
                limit.getStartingDate(), limit.getEndingDate());
        return stream
                .filter(transaction -> transaction.getExpenseCategory().equals(expenseCategory))
                .filter(transaction -> !transaction.getLimitExceeded())
                .map(Transaction::getTransactionSumInUsd)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public MonthLimit createDefaultLimit(String expenseCategory) {
        return create(MonthLimit.builder()
                .startingDate(LocalDate.now())
                .endingDate(LocalDate.now().plusMonths(1L))
                .expenseCategory(expenseCategory)
                .currency(USD)
                .sumLimit(DEFAULT_MONTH_LIMIT)
                .build());
    }

    @Override
    public Boolean checkLimitTable(String expenseCategory) {
        List<MonthLimit> limits = findAll().stream()
                .filter(monthLimit -> monthLimit.getExpenseCategory().equals(expenseCategory))
                .toList();
        return limits.isEmpty();
    }

    @Override
    public MonthLimit toMonthLimit(MonthLimitDto monthLimitDto) {
        if (monthLimitDto.getSumLimit() == null) {
            monthLimitDto.setSumLimit(DEFAULT_MONTH_LIMIT);
        }
        return MonthLimit.builder()
                .expenseCategory(monthLimitDto.getExpenseCategory())
                .sumLimit(monthLimitDto.getSumLimit())
                .build();
    }

}

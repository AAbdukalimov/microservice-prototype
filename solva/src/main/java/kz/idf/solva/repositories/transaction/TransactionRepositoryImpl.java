package kz.idf.solva.repositories.transaction;

import kz.idf.solva.dto.TransactionDto;
import kz.idf.solva.entities.MonthLimit;
import kz.idf.solva.entities.Transaction;
import lombok.AllArgsConstructor;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;
import java.util.stream.Stream;
import static kz.idf.solva.utility.SqlUtility.*;


@Repository
@AllArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Transaction create(Transaction value) {
        jdbcTemplate.update(CREATE_TRANSACTION,
                value.getDateTime(),
                value.getAccountFrom(),
                value.getAccountTo(),
                value.getCurrencyShortname(),
                value.getSum(),
                value.getExpenseCategory(),
                value.getTransactionSumInUsd(),
                value.getLimitExceeded());
        return value;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        Stream<Transaction> stream = jdbcTemplate.queryForStream
                (FIND_ALL_TRANSACTIONS, new BeanPropertyRowMapper<>(Transaction.class));
        return stream.toList();
    }

    @Override
    public List<Transaction> findAllLimitExceededTransactions() {
        return findAll()
                .stream()
                .filter(Transaction::getLimitExceeded)
                .toList();
    }

    @Override
    public Transaction toTransaction(TransactionDto transactionDto) {
        return Transaction.builder()
                .accountTo(transactionDto.getAccountTo())
                .sum(transactionDto.getSum())
                .expenseCategory(transactionDto.getExpenseCategory())
                .build();
    }
}

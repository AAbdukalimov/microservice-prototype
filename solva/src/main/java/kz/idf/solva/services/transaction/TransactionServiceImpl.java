package kz.idf.solva.services.transaction;

import kz.idf.solva.client.CurrencyRatesClient;
import kz.idf.solva.dto.LimitExceededDto;
import kz.idf.solva.dto.TransactionDto;
import kz.idf.solva.entities.MonthLimit;
import kz.idf.solva.entities.Transaction;
import kz.idf.solva.repositories.monthlimit.MonthLimitRepository;
import kz.idf.solva.repositories.transaction.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static kz.idf.solva.utility.ConstantsUtility.*;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private MonthLimitRepository monthLimitRepository;
    private CurrencyRatesClient currencyRatesClient;

    @Override
    @Transactional
    public Transaction create(Transaction value) {                //  1. 	Получаем информацию о каждой расходной операции
        value.setDateTime(LocalDate.now());                        // в тенге (KZT) в реальном времени и сохраняем ее в своей собственной базе данных;
        value.setAccountFrom(ACCOUNT_FROM);
        value.setCurrencyShortname(KZT);
        MonthLimit limit = monthLimitRepository.findActualLimit(value.getExpenseCategory(), value.getDateTime());              // 2. Если лимит не установлен, создаем дефолтный лимит с суммой == 0;
        Double actualRate = currencyRatesClient.getActualRate(value.getDateTime());
        Double sumTransactionsInLimitPeriodInUSD = monthLimitRepository.sumTransactionFromLimitPeriod(value.getExpenseCategory(),value.getDateTime()) / actualRate;
        value.setTransactionSumInUsd(value.getSum() / actualRate);
        Boolean limitExceeded = !(sumTransactionsInLimitPeriodInUSD + value.getTransactionSumInUsd() <= limit.getSumLimit());
        value.setLimitExceeded(limitExceeded);                                  //4.	Помечаем транзакции, превысившие месячный лимит операций (технический флаг limit_exceeded);
        return transactionRepository.create(value);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> findAllLimitExceededTransactions() {
        return transactionRepository.findAllLimitExceededTransactions();
    }

    @Override
    public Map<Transaction, LimitExceededDto> detailingReportForLimitExceededTransactions() {        //  Возвращаем список транзакций, превысивших лимит, с указанием лимита,
        Map<Transaction, LimitExceededDto> detailing = new HashMap<>();                                    //  который был превышен (дата установления, сумма лимита, валюта (USD)).
        List<Transaction> transactions = findAllLimitExceededTransactions();
        for (Transaction transaction : transactions) {
            MonthLimit monthLimit = monthLimitRepository.findActualLimit(transaction.getExpenseCategory(),transaction.getDateTime());
            LimitExceededDto limitExceededDto = LimitExceededDto.builder()
                    .startingDate(monthLimit.getStartingDate())
                    .sumLimit(monthLimit.getSumLimit())
                    .currency(monthLimit.getCurrency())
                    .build();
            detailing.put(transaction, limitExceededDto);
        }
        return detailing;
    }

    @Override
    public Transaction toTransaction(TransactionDto transactionDto) {
        return transactionRepository.toTransaction(transactionDto);
    }
}

package kz.idf.solva.services.transaction;

import kz.idf.solva.dto.LimitExceededDto;
import kz.idf.solva.dto.TransactionDto;
import kz.idf.solva.entities.Transaction;
import kz.idf.solva.services.Service;
import java.util.List;
import java.util.Map;

public interface TransactionService extends Service<Transaction> {

    List<Transaction> findAll();

    List<Transaction> findAllLimitExceededTransactions();

    Map<Transaction, LimitExceededDto> detailingReportForLimitExceededTransactions();

    Transaction toTransaction(TransactionDto transactionDto);

}

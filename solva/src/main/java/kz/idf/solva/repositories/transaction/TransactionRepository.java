package kz.idf.solva.repositories.transaction;

import kz.idf.solva.dto.TransactionDto;
import kz.idf.solva.entities.Transaction;
import kz.idf.solva.repositories.Repository;
import java.util.List;

public interface TransactionRepository extends Repository<Transaction> {

    List<Transaction> findAll();

    List<Transaction> findAllLimitExceededTransactions();

    Transaction toTransaction(TransactionDto transactionDto);
}

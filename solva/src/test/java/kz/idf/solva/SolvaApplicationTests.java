package kz.idf.solva;

import kz.idf.solva.entities.Transaction;
import kz.idf.solva.repositories.transaction.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestTransactionRepository {

//    TransactionRepository repository;
//
//    @ParameterizedTest
//    @MethodSource("testCreateParams")
//    public void testCreate(Transaction transaction, Transaction expected) {
//        Transaction actual = repository.create(transaction);
//        assertEquals(expected, actual);
//    }
//
//    static Stream<Arguments> testCreateParams() {
//        Transaction first = Transaction.builder()
//                .dateTime(LocalDate.of(2022, 12, 22))
//                .accountFrom(1000000002)
//                .accountTo(1000000001)
//                .currencyShortname("KZT")
//                .sum(10689.20)
//                .expenseCategory("PRODUCT")
//                .transactionSumInUsd(90.50)
//                .limitExceeded(true)
//                .build();
//
//        Transaction second = Transaction.builder()
//                .dateTime(LocalDate.of(2022, 10, 12))
//                .accountFrom(1000000002)
//                .accountTo(1000000003)
//                .currencyShortname("KZT")
//                .sum(35412.60)
//                .expenseCategory("SERVICE")
//                .transactionSumInUsd(100.80)
//                .limitExceeded(true)
//                .build();
//
//        Transaction third = Transaction.builder()
//                .dateTime(LocalDate.of(2022, 5, 5))
//                .accountFrom(1000000002)
//                .accountTo(1000000004)
//                .currencyShortname("KZT")
//                .sum(18952.60)
//                .expenseCategory("SERVICE")
//                .transactionSumInUsd(80.30)
//                .limitExceeded(false)
//                .build();
//
//return Stream.of(
//        Arguments.of(first)
//)
//    }

    @Test
    void contextLoads() {
    }

}

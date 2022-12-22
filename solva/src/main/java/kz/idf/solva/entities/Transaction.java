package kz.idf.solva.entities;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import java.time.LocalDate;


@Data
@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDate dateTime;
    private Integer accountFrom;
    @Digits(integer = 10, fraction = 0, message = "Account should be 10 digits")
    private Integer accountTo;
    private String currencyShortname;
    private Double sum;
    private String expenseCategory;
    private Double transactionSumInUsd;
    private Boolean limitExceeded;


}

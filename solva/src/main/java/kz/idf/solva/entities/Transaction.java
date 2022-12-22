package kz.idf.solva.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
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
    @Schema(required = true, type = "dateTime", format = "yyyy-MM-dd", example = "2023-01-01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateTime;
    private Integer accountFrom;
    @NotNull
    @Digits(integer = 10, fraction = 0, message = "Account should be 10 digits")
    private Integer accountTo;
    private String currencyShortname;
    @NotNull
    @Digits(integer = 5, fraction = 2)
    private Double sum;
    @NotNull
    private String expenseCategory;
    private Double transactionSumInUsd;
    private Boolean limitExceeded;

}

package kz.idf.solva.dto;


import lombok.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {

    @NotNull
    @Digits(integer = 10, fraction = 0, message = "Account should be 10 digits")
    private Integer accountTo;
    @NotNull
    @Digits(integer = 5, fraction = 2)
    private Double sum;
    @NotNull
    private String expenseCategory;

}

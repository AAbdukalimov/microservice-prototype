package kz.idf.solva.dto;


import lombok.*;
import javax.validation.constraints.Digits;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {

    @Digits(integer = 10, fraction = 0, message = "Account should be 10 digits")
    private Integer accountTo;
    private Double sum;
    private String expenseCategory;

}

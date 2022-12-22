package kz.idf.solva.dto;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class MonthLimitDto {

    @NotNull
    private String expenseCategory;
    @NotNull
    @Digits(integer = 5, fraction = 2)
    private Double sumLimit;
}

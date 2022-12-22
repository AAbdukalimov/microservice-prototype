package kz.idf.solva.dto;

import lombok.*;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class MonthLimitDto {

    private String expenseCategory;
    private Double sumLimit;
}

package kz.idf.solva.entities;

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
public class MonthLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDate startingDate;
    private LocalDate endingDate;
    @NotNull
    private String expenseCategory;
    private String currency;
    @NotNull
    @Digits(integer = 5, fraction = 2)
    private Double sumLimit;

}

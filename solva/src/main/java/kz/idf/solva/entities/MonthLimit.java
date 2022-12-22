package kz.idf.solva.entities;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String expenseCategory;
    private String currency;
    private Double sumLimit;

}

package kz.idf.solva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class LimitExceededDto {

    private LocalDate startingDate;
    private Double sumLimit;
    private String currency;

}

package kz.idf.solva.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kz.idf.solva.client.CurrencyRatesClient;
import kz.idf.solva.dto.LimitExceededDto;
import kz.idf.solva.entities.Transaction;
import kz.idf.solva.services.transaction.TransactionServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.idf.solva.dto.MonthLimitDto;
import kz.idf.solva.entities.MonthLimit;
import kz.idf.solva.services.monthlimit.MonthLimitServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Tag(name = "Client service", description = "Client API")
@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientRestController {

    private MonthLimitServiceImpl monthLimitService;
    private TransactionServiceImpl transactionService;
    private CurrencyRatesClient currencyRatesClient;

    @PostMapping()
    @Operation(summary = "Create month limit")
    @ResponseStatus(HttpStatus.CREATED)
    public MonthLimit createMonthLimit(@Valid @RequestBody MonthLimitDto monthLimitDto) {
        MonthLimit monthLimit = new MonthLimit();
        monthLimit.setSumLimit(monthLimitDto.getSumLimit());
        monthLimit.setExpenseCategory(monthLimitDto.getExpenseCategory());
        return monthLimitService.create(monthLimit);
    }

    @GetMapping("/allLimits")
    @Operation(summary = "Get all month limits")
    public List<MonthLimit> getAll() {
        return monthLimitService.findAll();
    }


    @GetMapping("/allLimitExceedingTransactions")
    @Operation(summary = "Get all limit exceeding transactions")
    public List<Transaction> getLimitExceededTransactions() {
        return transactionService.findAllLimitExceededTransactions();
    }

    @GetMapping("/detailingExceedingLimits")
    @Operation(summary = "Get a list of transactions that have exceeded the limit, indicating the limit that was exceeded (set date, limit amount, currency (USD))")
    public Map<Transaction, LimitExceededDto> getDetailingReportForExceedingTransactions() {
        return transactionService.detailingReportForLimitExceededTransactions();
    }

}

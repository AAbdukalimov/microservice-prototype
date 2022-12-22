package kz.idf.solva.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.idf.solva.dto.TransactionDto;
import kz.idf.solva.entities.Transaction;
import kz.idf.solva.services.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Tag(name = "Transaction", description = "Transaction API")
@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionRestController {

    private TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Create transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
       return transactionService.create(transactionService.toTransaction(transactionDto));
    }

}

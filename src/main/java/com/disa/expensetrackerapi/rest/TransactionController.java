package com.disa.expensetrackerapi.rest;

import com.disa.expensetrackerapi.domain.dto.dashboard.TotalResponse;
import com.disa.expensetrackerapi.domain.dto.transaction.TransactionRequest;
import com.disa.expensetrackerapi.domain.dto.transaction.TransactionResponse;
import com.disa.expensetrackerapi.enums.CategoryType;
import com.disa.expensetrackerapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create/{categoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest,
                                                                 @PathVariable Long categoryId) {
        return ResponseEntity.ok(transactionService.createTransaction(transactionRequest, categoryId));
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TransactionResponse>> getTransactions() {
        return ResponseEntity.ok().body(transactionService.getAllTransactions());
    }

    @GetMapping("/get/{transactionId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable Long transactionId) {
        return ResponseEntity.ok().body(transactionService.getTransaction(transactionId));
    }

    @DeleteMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/getMyTransactions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TransactionResponse>> getAllTransactionsByUserId(
            @RequestParam CategoryType type,
            @RequestParam Long categoryId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return ResponseEntity.ok().body(transactionService.getTransactions(type, categoryId, from, to));
    }

    @PostMapping("/getTotal")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TotalResponse> getMonthlySummary(@RequestParam("year") Integer year,
                                                           @RequestParam("month") Integer month) {
        return ResponseEntity.ok().body(transactionService.getTotalByMonth(year, month));
    }



}

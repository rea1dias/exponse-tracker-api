package com.disa.expensetrackerapi.rest;

import com.disa.expensetrackerapi.domain.dto.transaction.TransactionRequest;
import com.disa.expensetrackerapi.domain.dto.transaction.TransactionResponse;
import com.disa.expensetrackerapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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





}

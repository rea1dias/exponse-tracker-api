package com.disa.expensetrackerapi.service;

import com.disa.expensetrackerapi.domain.dto.transaction.TransactionRequest;
import com.disa.expensetrackerapi.domain.dto.transaction.TransactionResponse;
import com.disa.expensetrackerapi.enums.CategoryType;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService  {

    TransactionResponse createTransaction(TransactionRequest request, Long categoryId);

    List<TransactionResponse> getAllTransactions();

    TransactionResponse getTransaction(Long transactionId);

    void deleteTransaction(Long transactionId);

    List<TransactionResponse> getTransactions(CategoryType type, Long categoryId, LocalDate from, LocalDate to);


}

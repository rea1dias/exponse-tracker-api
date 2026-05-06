package com.disa.expensetrackerapi.service;

import com.disa.expensetrackerapi.domain.dto.transaction.TransactionRequest;
import com.disa.expensetrackerapi.domain.dto.transaction.TransactionResponse;

import java.util.List;

public interface TransactionService  {

    TransactionResponse createTransaction(TransactionRequest request, Long categoryId);

    List<TransactionResponse> getAllTransactions();
}

package com.disa.expensetrackerapi.service.impl;

import com.disa.expensetrackerapi.domain.dto.transaction.TransactionRequest;
import com.disa.expensetrackerapi.domain.dto.transaction.TransactionResponse;
import com.disa.expensetrackerapi.domain.entity.Category;
import com.disa.expensetrackerapi.domain.entity.Transaction;
import com.disa.expensetrackerapi.domain.entity.User;
import com.disa.expensetrackerapi.exception.BadRequestException;
import com.disa.expensetrackerapi.exception.NotFoundException;
import com.disa.expensetrackerapi.mapper.TransactionMapper;
import com.disa.expensetrackerapi.repo.CategoryRepository;
import com.disa.expensetrackerapi.repo.TransactionRepository;
import com.disa.expensetrackerapi.repo.UserRepository;
import com.disa.expensetrackerapi.service.SecurityService;
import com.disa.expensetrackerapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final SecurityService securityService;
    private final CategoryRepository categoryRepository;
    private final TransactionMapper transactionMapper;
    private final UserRepository userRepository;

    @Override
    public TransactionResponse createTransaction(TransactionRequest request, Long categoryId) {
        Long userId = securityService.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        if (!category.getUser().getId().equals(userId)) {
            throw new BadRequestException("You don't have access to this category");
        }
        if (!category.getType().equals(request.getType())) {
            throw new BadRequestException("The transaction type does not match");
        }
        Transaction transaction = transactionMapper.toEntity(request);
        transaction.setOccurredAt(
                request.getOccurredAt() != null
                        ? request.getOccurredAt()
                        : LocalDate.now()
        );
        transaction.setCategory(category);
        transaction.setUser(user);
        return transactionMapper.toResponse(transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        Long userId = securityService.getCurrentUserId();
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return transactions.stream()
                .map(transactionMapper::toResponse)
                .toList();
    }
}

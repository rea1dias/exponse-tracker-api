package com.disa.expensetrackerapi.domain.dto.transaction;

import com.disa.expensetrackerapi.enums.CategoryType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequest {

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    private String description;

    @NotNull
    private LocalDate occurredAt;
}

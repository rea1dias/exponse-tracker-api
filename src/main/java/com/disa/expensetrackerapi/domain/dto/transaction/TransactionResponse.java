package com.disa.expensetrackerapi.domain.dto.transaction;

import com.disa.expensetrackerapi.enums.CategoryType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionResponse {

    private Long id;

    private BigDecimal amount;

    private CategoryType type;

    private String description;

    private LocalDate occurredAt;

    private Long categoryId;

    private String categoryName;

}

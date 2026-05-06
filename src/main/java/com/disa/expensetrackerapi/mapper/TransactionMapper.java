package com.disa.expensetrackerapi.mapper;

import com.disa.expensetrackerapi.domain.dto.transaction.TransactionRequest;
import com.disa.expensetrackerapi.domain.dto.transaction.TransactionResponse;
import com.disa.expensetrackerapi.domain.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "occurredAt",ignore = true)
    Transaction toEntity(TransactionRequest request);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "type", source = "category.type")
    TransactionResponse toResponse(Transaction transaction);

}

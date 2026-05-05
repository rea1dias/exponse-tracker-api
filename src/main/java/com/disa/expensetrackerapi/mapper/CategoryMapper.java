package com.disa.expensetrackerapi.mapper;

import com.disa.expensetrackerapi.domain.dto.category.CategoryRequest;
import com.disa.expensetrackerapi.domain.dto.category.CategoryResponse;
import com.disa.expensetrackerapi.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "categoryName")
    @Mapping(target = "description", source = "categoryDescription")
    @Mapping(target = "type", source = "categoryType")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Category toEntity(CategoryRequest request);

    @Mapping(target = "categoryId", source = "id")
    @Mapping(target = "categoryName", source = "name")
    @Mapping(target = "categoryDescription", source = "description")
    @Mapping(target = "categoryType", source = "type")
    CategoryResponse toResponse(Category category);





}

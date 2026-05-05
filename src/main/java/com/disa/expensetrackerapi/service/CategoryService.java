package com.disa.expensetrackerapi.service;

import com.disa.expensetrackerapi.domain.dto.category.CategoryRequest;
import com.disa.expensetrackerapi.domain.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(CategoryRequest request, Long categoryId);
    void deleteCategory(Long categoryId);
    List<CategoryResponse> getAllCategories();




}

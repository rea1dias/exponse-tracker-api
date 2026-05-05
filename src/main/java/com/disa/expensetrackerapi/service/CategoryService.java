package com.disa.expensetrackerapi.service;

import com.disa.expensetrackerapi.domain.dto.category.CategoryRequest;
import com.disa.expensetrackerapi.domain.dto.category.CategoryResponse;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);


}

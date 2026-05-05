package com.disa.expensetrackerapi.service.impl;

import com.disa.expensetrackerapi.domain.dto.category.CategoryRequest;
import com.disa.expensetrackerapi.domain.dto.category.CategoryResponse;
import com.disa.expensetrackerapi.domain.entity.Category;
import com.disa.expensetrackerapi.domain.entity.User;
import com.disa.expensetrackerapi.mapper.CategoryMapper;
import com.disa.expensetrackerapi.repo.CategoryRepository;
import com.disa.expensetrackerapi.repo.UserRepository;
import com.disa.expensetrackerapi.service.CategoryService;
import com.disa.expensetrackerapi.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Long userId = securityService.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryMapper.toEntity(request);
        category.setUser(user);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }
}

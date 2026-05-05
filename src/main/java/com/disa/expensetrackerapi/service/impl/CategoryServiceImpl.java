package com.disa.expensetrackerapi.service.impl;

import com.disa.expensetrackerapi.domain.dto.category.CategoryRequest;
import com.disa.expensetrackerapi.domain.dto.category.CategoryResponse;
import com.disa.expensetrackerapi.domain.entity.Category;
import com.disa.expensetrackerapi.domain.entity.User;
import com.disa.expensetrackerapi.exception.BadRequestException;
import com.disa.expensetrackerapi.exception.NotFoundException;
import com.disa.expensetrackerapi.mapper.CategoryMapper;
import com.disa.expensetrackerapi.repo.CategoryRepository;
import com.disa.expensetrackerapi.repo.UserRepository;
import com.disa.expensetrackerapi.service.CategoryService;
import com.disa.expensetrackerapi.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        Long userId = securityService.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryMapper.toEntity(request);
        category.setUser(user);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(CategoryRequest request, Long categoryId) {
        Long userId = securityService.getCurrentUserId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        if (!category.getUser().getId().equals(userId)) {
            throw new BadRequestException("You don't have access to this category");
        }
        category.setName(request.getCategoryName());
        category.setDescription(request.getCategoryDescription());
        category.setType(request.getCategoryType());
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        Long userId = securityService.getCurrentUserId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        if (!category.getUser().getId().equals(userId)) {
            throw new BadRequestException("You don't have access to this category");
        }
        categoryRepository.delete(category);
    }

    @Override
    @Transactional
    public List<CategoryResponse> getAllCategories() {
        Long userId = securityService.getCurrentUserId();
        List<Category> categories = categoryRepository.findByUserId(userId);
        return categories.stream()
                .map(categoryMapper::toResponse)
                .toList();
    }
}





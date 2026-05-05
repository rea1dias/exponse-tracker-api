package com.disa.expensetrackerapi.rest;

import com.disa.expensetrackerapi.domain.dto.category.CategoryRequest;
import com.disa.expensetrackerapi.domain.dto.category.CategoryResponse;
import com.disa.expensetrackerapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        return ResponseEntity.ok().body(categoryService.createCategory(request));
    }

    @PutMapping("/update/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryResponse> update(@PathVariable Long categoryId,
                                                   @RequestBody CategoryRequest request) {
        return ResponseEntity.ok().body(categoryService.updateCategory(request, categoryId));
    }

    @DeleteMapping("/delete/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryService.getAllCategories());
    }






}

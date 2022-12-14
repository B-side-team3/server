package com.bside.server.module.category.controller;

import com.bside.server.module.category.dto.CategoryRequest;
import com.bside.server.module.category.dto.CategoryResponse;
import com.bside.server.module.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryResponse> getCategoryResponseList() {
        return categoryService.getCategoryResponseList();
    }

    @PostMapping("/admin/categories")
    public CategoryResponse createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @PatchMapping("/admin/categories")
    public CategoryResponse updateCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.updateCategory(categoryRequest);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}

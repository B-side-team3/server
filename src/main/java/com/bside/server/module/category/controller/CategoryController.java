package com.bside.server.module.category.controller;

import com.bside.server.module.category.dto.CategoryResponse;
import com.bside.server.module.category.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 루틴 찾기 - 카테고리 목록 조회
     * @return 카테고리 목록
     */
    @ApiOperation(value = "카테고리 목록 조회", notes = "카테고리 목록을 조회한다.")
    @GetMapping("/browse/categories")
    public List<CategoryResponse> getCategoryResponseList() {
        return categoryService.getCategoryResponseList();
    }

//    @PostMapping("/admin/categories")
//    public CategoryResponse createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
//        return categoryService.createCategory(categoryRequest);
//    }
//
//    @PatchMapping("/admin/categories/{categoryId}")
//    public CategoryResponse updateCategory(@PathVariable("categoryId") Integer categoryId, @RequestBody @Valid CategoryRequest categoryRequest) {
//        return categoryService.updateCategory(categoryId, categoryRequest);
//    }
//
//    @DeleteMapping("/admin/categories/{categoryId}")
//    public void deleteCategory(@PathVariable("categoryId") Integer categoryId) {
//        categoryService.deleteCategory(categoryId);
//    }
}

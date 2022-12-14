package com.bside.server.module.category.service;

import com.bside.server.module.category.domain.Category;
import com.bside.server.module.category.dto.CategoryRequest;
import com.bside.server.module.category.dto.CategoryResponse;
import com.bside.server.module.category.repository.CategoryRepository;
import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public List<CategoryResponse> getCategoryResponseList() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(CategoryResponse::new).collect(Collectors.toList());
    }

    public CategoryResponse createCategory(CategoryRequest request) {
        return new CategoryResponse(categoryRepository.save(request.toEntity(request)));
    }

    public CategoryResponse updateCategory(CategoryRequest request) {
        Category category = findCategory(request.getCategoryId());
        category.updateTitle(request.getTitle());
        return new CategoryResponse(categoryRepository.save(category));
    }

    public void deleteCategory(Integer categoryId) {
        categoryRepository.delete(findCategory(categoryId));
    }

    private Category findCategory(Integer categoryId) {
        return categoryRepository.findByCategoryId(categoryId).orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
    }
}

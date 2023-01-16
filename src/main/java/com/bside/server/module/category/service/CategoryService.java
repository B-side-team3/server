package com.bside.server.module.category.service;

import com.bside.server.module.category.domain.Category;
import com.bside.server.module.category.dto.CategoryResponse;
import com.bside.server.module.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public List<CategoryResponse> getCategoryResponseList() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(CategoryResponse::new).collect(Collectors.toList());
    }

//    @Transactional
//    public CategoryResponse createCategory(CategoryRequest request) {
//        return new CategoryResponse(categoryRepository.save(request.toEntity(request)));
//    }
//
//    @Transactional
//    public CategoryResponse updateCategory(Integer categoryId, CategoryRequest request) {
//        Category category = findCategory(categoryId);
//        category.updateTitle(request.getTitle());
//        return new CategoryResponse(categoryRepository.save(category));
//    }
//
//    @Transactional
//    public void deleteCategory(Integer categoryId) {
//        categoryRepository.delete(findCategory(categoryId));
//    }
//
//    public Category findCategory(Integer categoryId) {
//        return categoryRepository.findById(categoryId).orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
//    }
}

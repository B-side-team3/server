package com.bside.server.module.category.dto;

import com.bside.server.module.category.domain.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryResponse {

    private Integer categoryId;
    private String title;

    public CategoryResponse(Category category) {
        this.categoryId = category.getId();
        this.title = category.getTitle();
    }
}

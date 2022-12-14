package com.bside.server.category.dto;

import com.bside.server.category.domain.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryResponse {

    private Integer categoryId;
    private String title;
    private LocalDateTime createdDateTime;

    public CategoryResponse(Category category) {
        this.categoryId = category.getCategoryId();
        this.title = category.getTitle();
        this.createdDateTime = category.getCreatedDateTime();
    }
}

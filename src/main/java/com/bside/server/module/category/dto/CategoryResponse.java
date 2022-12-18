package com.bside.server.module.category.dto;

import com.bside.server.module.category.domain.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryResponse {

    private Integer categoryId;
    private String title;
    private LocalDateTime createdDateTime;

    public CategoryResponse(Category category) {
        this.categoryId = category.getId();
        this.title = category.getTitle();
        this.createdDateTime = category.getCreatedDateTime();
    }
}

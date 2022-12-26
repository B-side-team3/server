package com.bside.server.module.category.dto;

import com.bside.server.module.category.domain.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryResponse {

    private Integer categoryId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDateTime;

    public CategoryResponse(Category category) {
        this.categoryId = category.getId();
        this.title = category.getTitle();
        this.createdDateTime = category.getCreatedDateTime();
    }
}

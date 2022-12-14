package com.bside.server.module.category.dto;

import com.bside.server.module.category.domain.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryRequest {

    private Integer categoryId;

    @NotEmpty(message = "CATEGORY_NAME_EMPTY")
    private String title;

    public Category toEntity(CategoryRequest request){
        return Category.builder().title(request.getTitle()).build();
    }
}

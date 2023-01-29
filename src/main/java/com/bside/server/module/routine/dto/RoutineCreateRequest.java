package com.bside.server.module.routine.dto;

import com.bside.server.module.category.domain.Category;
import com.bside.server.module.routine.domain.Routine;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineCreateRequest
{

    private Integer categoryId;

    @NotEmpty(message = "ROUTINE_TITLE_EMPTY")
    private String title;

    @NotEmpty(message = "ROUTINE_DESCRIPTION_EMPTY")
    private String description;

    private String imageUrl;

    @Positive
    private Integer totalTime;

    private String startTime;

    private String anchor;

    public Routine toEntity(RoutineCreateRequest request, Category category) {
        return Routine.builder()
                .category(category)
                .title(request.getTitle())
                .description(request.getDescription())
                .startTime(request.getStartTime())
                .anchor(request.getAnchor())
                .totalTime(request.getTotalTime() == null ? 0 : request.getTotalTime())
                .imageUrl(request.getImageUrl())
                .build();
    }
}

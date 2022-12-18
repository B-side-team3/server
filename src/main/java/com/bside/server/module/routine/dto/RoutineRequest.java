package com.bside.server.module.routine.dto;

import com.bside.server.module.category.domain.Category;
import com.bside.server.module.routine.domain.Routine;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineRequest {

    private Integer categoryId;

    @NotEmpty(message = "ROUTINE_TITLE_EMPTY")
    private String title;

    @NotEmpty(message = "ROUTINE_DESCRIPTION_EMPTY")
    private String description;

    private String imageUrl;

    private Integer period;

    private Integer totalTime;

    private String startTime;

    private String anchor;

    public Routine toEntity(RoutineRequest request, Category category) {
        return Routine.builder()
                .category(category)
                .title(request.getTitle())
                .description(request.getDescription())
                .startTime(request.getStartTime())
                .anchor(request.getAnchor())
                .period(request.getPeriod())
                .totalTime(request.getTotalTime() == null ? 0 : request.getTotalTime())
                .imageUrl(request.getImageUrl())
                .build();
    }
}

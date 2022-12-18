package com.bside.server.module.routine.dto;

import com.bside.server.module.category.domain.Category;
import com.bside.server.module.routine.domain.Routine;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineUpdateRequest
{

    private Integer categoryId;

    private String title;

    private String description;

    private String imageUrl;

    private Integer period;

    private Integer totalTime;

    private String startTime;

    private String anchor;

    public Routine toEntity(RoutineUpdateRequest request, Category category) {
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

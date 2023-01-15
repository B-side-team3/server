package com.bside.server.module.routine.dto;

import com.bside.server.module.routine.domain.Routine;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineResponse {
    private Integer categoryId;
    private Integer routineId;
    private String title;
    private String description;
    private String imageUrl;
    private String startTime;
    private String anchor;
    private Integer totalTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDateTime;
    private Map<String, Integer> taskList;

    public RoutineResponse(Routine routine) {
        this.routineId = routine.getId();
        this.categoryId = routine.getCategory().getId();
        this.title = routine.getTitle();
        this.description = routine.getDescription();
        this.imageUrl = routine.getImageUrl();
        this.startTime = routine.getStartTime();
        this.anchor = routine.getAnchor();
        this.totalTime = routine.getTotalTime();
        this.totalTime = routine.getTotalTime();
        this.createdDateTime = routine.getCreatedDateTime();
    }

    public RoutineResponse(Routine routine, Map<String, Integer> taskListMap) {
        this.routineId = routine.getId();
        this.categoryId = routine.getCategory().getId();
        this.title = routine.getTitle();
        this.description = routine.getDescription();
        this.imageUrl = routine.getImageUrl();
        this.startTime = routine.getStartTime();
        this.anchor = routine.getAnchor();
        this.totalTime = routine.getTotalTime();
        this.totalTime = routine.getTotalTime();
        this.createdDateTime = routine.getCreatedDateTime();
        this.taskList = taskListMap;
    }
}

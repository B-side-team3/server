package com.bside.server.module.routine.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineResponse {
    private Integer routineId;
    private String title;
    private String category;
    private String startTime;
    private String anchor;
    private Integer totalTime;
    private LocalDateTime createdDateTime;
}

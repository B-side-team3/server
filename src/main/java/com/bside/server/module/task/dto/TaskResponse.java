package com.bside.server.module.task.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskResponse {
    private Integer taskId;
    private String title;
    private Integer expectedTime;
    private Integer actualTime;
    private LocalDateTime createdDateTime;
}

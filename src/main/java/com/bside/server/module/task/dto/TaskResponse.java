package com.bside.server.module.task.dto;

import com.bside.server.module.task.domain.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskResponse {
    private Integer routineId;
    private Integer taskId;
    private String title;
    private Integer expectedTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDateTime;

    public TaskResponse(Task task) {
        this.routineId = task.getRoutine().getId();
        this.taskId = task.getId();
        this.title = task.getTitle();
        this.expectedTime = task.getExpectedTime();
        this.createdDateTime = task.getCreatedDateTime();
    }
}

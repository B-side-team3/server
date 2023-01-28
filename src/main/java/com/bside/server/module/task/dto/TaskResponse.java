package com.bside.server.module.task.dto;

import com.bside.server.module.task.domain.Task;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskResponse {
    private Integer routineId;
    private Integer taskId;
    private String title;
    private Integer expectedTime;

    public TaskResponse(Task task) {
        this.routineId = task.getRoutine().getId();
        this.taskId = task.getId();
        this.title = task.getTitle();
        this.expectedTime = task.getExpectedTime();
    }
}

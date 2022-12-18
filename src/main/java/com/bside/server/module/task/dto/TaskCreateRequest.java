package com.bside.server.module.task.dto;

import com.bside.server.module.routine.domain.Routine;
import com.bside.server.module.task.domain.Task;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
public class TaskCreateRequest {
    @NotEmpty(message = "TASK_TITLE_EMPTY")
    private String title;

    @Positive
    private Integer expectedTime;

    public Task toEntity(TaskCreateRequest request, Routine routine){
        return Task.builder()
                .routine(routine)
                .title(request.getTitle())
                .expectedTime(request.getExpectedTime())
                .build();
    }
}

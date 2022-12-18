package com.bside.server.module.task.dto;

import lombok.Getter;


@Getter
public class TaskUpdateRequest {
    private String title;
    private Integer expectedTime;
}

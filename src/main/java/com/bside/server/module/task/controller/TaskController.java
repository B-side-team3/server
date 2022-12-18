package com.bside.server.module.task.controller;

import com.bside.server.module.task.domain.Task;
import com.bside.server.module.task.dto.TaskCreateRequest;
import com.bside.server.module.task.dto.TaskResponse;
import com.bside.server.module.task.dto.TaskUpdateRequest;
import com.bside.server.module.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/routines/{routineId}/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskResponse> getTaskList(@PathVariable("routineId")Integer routineId) {
        List<Task> taskList= taskService.getTaskList(routineId);
        return taskList.stream().map(TaskResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public TaskResponse createTask(@PathVariable("routineId") @Positive Integer routineId, @RequestBody TaskCreateRequest taskCreateRequest) {
        return new TaskResponse(taskService.createTask(routineId, taskCreateRequest));
    }

    @PatchMapping("/{taskId}")
    public TaskResponse updateTask(@PathVariable("routineId") @Positive Integer routineId,
                                   @PathVariable("taskId") @Positive Integer taskId,
                                   @RequestBody @Valid TaskUpdateRequest taskUpdateRequest
    ) {
        return new TaskResponse(taskService.updateTask(routineId, taskId, taskUpdateRequest));
    }

}

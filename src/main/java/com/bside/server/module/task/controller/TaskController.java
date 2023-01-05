package com.bside.server.module.task.controller;

import com.bside.server.module.task.domain.Task;
import com.bside.server.module.task.dto.TaskResponse;
import com.bside.server.module.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class TaskController {

    private final TaskService taskService;

    /**
     * 루틴 찾기 - 루틴 내 task 목록 조회
     * @param routineId 루틴 아이디
     * @return Task 목록
     */
    @GetMapping("/browse/routines/{routineId}/tasks")
    public List<TaskResponse> getTaskList(@PathVariable("routineId")Integer routineId) {
        List<Task> taskList= taskService.getTaskList(routineId);
        return taskList.stream().map(TaskResponse::new).collect(Collectors.toList());
    }

//    @PostMapping("/admin/routines/{routineId}/tasks")
//    public TaskResponse createTask(@PathVariable("routineId") @Positive Integer routineId, @RequestBody TaskCreateRequest taskCreateRequest) {
//        return new TaskResponse(taskService.createTask(routineId, taskCreateRequest));
//    }
//
//    @PatchMapping("/admin/routines/{routineId}/tasks/{taskId}")
//    public TaskResponse updateTask(@PathVariable("routineId") @Positive Integer routineId,
//                                   @PathVariable("taskId") @Positive Integer taskId,
//                                   @RequestBody @Valid TaskUpdateRequest taskUpdateRequest
//    ) {
//        return new TaskResponse(taskService.updateTask(routineId, taskId, taskUpdateRequest));
//    }

}

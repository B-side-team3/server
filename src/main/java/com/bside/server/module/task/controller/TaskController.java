package com.bside.server.module.task.controller;

import com.bside.server.module.task.domain.Task;
import com.bside.server.module.task.dto.TaskResponse;
import com.bside.server.module.task.service.TaskService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "루틴의 할 일 목록 조회", notes = "특정 루틴의 할 일 목록을 조회한다.")
    @ApiImplicitParam(name = "routineId", value = "루틴 아이디", required = true)
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

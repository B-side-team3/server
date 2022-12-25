package com.bside.server.module.membertask.controller;

import com.bside.server.module.membertask.dto.MemberTaskRequest;
import com.bside.server.module.membertask.dto.MemberTaskResponse;
import com.bside.server.module.membertask.service.MemberTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routines/{routineId}/tasks")
public class MemberTaskController {

  private final MemberTaskService memberTaskService;

  @PostMapping
  public MemberTaskResponse createTask(@PathVariable Integer routineId, @RequestBody MemberTaskRequest request){
    return memberTaskService.createTask(routineId, request);
  }

  @GetMapping
  public List<MemberTaskResponse> getTask(@PathVariable Integer routineId) {
    return memberTaskService.getTask(routineId);
  }

  @PatchMapping("/{memberTaskId}")
  public MemberTaskResponse updateTask(@PathVariable Integer routineId, @PathVariable Integer memberTaskId, @RequestBody MemberTaskRequest request) {
    return memberTaskService.updateTask(routineId, memberTaskId, request);
  }

  @DeleteMapping("/{memberTaskId}")
  public void deleteTask(@PathVariable Integer routineId, @PathVariable Integer memberTaskId) {
    memberTaskService.deleteTask(routineId, memberTaskId);
  }
}

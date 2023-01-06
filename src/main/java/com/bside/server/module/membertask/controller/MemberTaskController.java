package com.bside.server.module.membertask.controller;

import com.bside.server.module.membertask.dto.MemberTaskRequest;
import com.bside.server.module.membertask.dto.MemberTaskResponse;
import com.bside.server.module.membertask.service.MemberTaskService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

  @ApiOperation(value = "사용자 할 일 초기화", notes = "사용자 할 일을 초기화한다.(소요시간 및 상태 초기화)")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "routineId", value = "사용자 루틴 아이디", required = true),
          @ApiImplicitParam(name = "memberTaskId", value = "사용자 할 일 아이디", required = true)
  })
  @PatchMapping("/{memberTaskId}/reset")
  public void resetTask(@PathVariable Integer routineId, @PathVariable Integer memberTaskId) {
    memberTaskService.resetTask(memberTaskId);
  }

//  @DeleteMapping("/{memberTaskId}")
//  public void deleteTask(@PathVariable Integer routineId, @PathVariable Integer memberTaskId) {
//    memberTaskService.deleteTask(routineId, memberTaskId);
//  }
}

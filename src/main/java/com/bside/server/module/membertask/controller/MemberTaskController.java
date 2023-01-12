package com.bside.server.module.membertask.controller;

import com.bside.server.module.membertask.dto.MemberTaskRequest;
import com.bside.server.module.membertask.service.MemberTaskService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myRoutines/{memberRoutineId}")

public class MemberTaskController {

  private final MemberTaskService memberTaskService;

  @ApiOperation(value = "루틴 시작(할일(data)추가)")
  @PostMapping
  public void createTask(@PathVariable Integer memberRoutineId, @RequestBody MemberTaskRequest request){
    memberTaskService.createTask(memberRoutineId, request);
  }

//  @ApiOperation(value = "할 일 보기")
//  @GetMapping
//  public List<MemberTaskResponse> getTask(@PathVariable Integer routineId) {
//    return memberTaskService.getTask(routineId);
//  }

  @ApiOperation(value = "할 일 편집")
  @PatchMapping
  public void updateTask(@PathVariable Integer memberRoutineId, @RequestBody MemberTaskRequest request) {
    memberTaskService.updateTask(memberRoutineId, request);
  }

  @ApiOperation(value = "사용자 할 일 초기화", notes = "사용자 할 일을 초기화한다.(소요시간 및 상태 초기화)")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "routineId", value = "사용자 루틴 아이디", required = true),
          @ApiImplicitParam(name = "memberTaskId", value = "사용자 할 일 아이디", required = true)
  })
  @PatchMapping("/{memberTaskId}/reset")
  public void resetTask(@PathVariable Integer memberRoutineId, @PathVariable Integer memberTaskId) {
    memberTaskService.resetTask(memberTaskId);
  }

//  @DeleteMapping("/{memberTaskId}")
//  public void deleteTask(@PathVariable Integer routineId, @PathVariable Integer memberTaskId) {
//    memberTaskService.deleteTask(routineId, memberTaskId);
//  }
}

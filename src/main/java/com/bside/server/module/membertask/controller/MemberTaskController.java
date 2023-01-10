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
/*
todo
 엔드포인트 변경 예정
 1. @RequestMapping("/routines/{memberRoutineId}") 로 변경한다.
 2. @PostMapping -> @PostMapping("/{memberRoutineId}")(나의루틴상세_루틴저장(추가))할 때 할일추가 되도록 변경한다.
 3. @PatchMapping("/{memberTaskId}") -> 나의루틴상세_할일편집에서 하도록 변경한다.
 */
@RequestMapping("/routines/{routineId}/tasks")
public class MemberTaskController {

  private final MemberTaskService memberTaskService;

  @ApiOperation(value = "할 일 (data) 추가")
  @PostMapping
  public MemberTaskResponse createTask(@PathVariable Integer routineId, @RequestBody MemberTaskRequest request){
    return memberTaskService.createTask(routineId, request);
  }

//  @ApiOperation(value = "할 일 보기")
//  @GetMapping
//  public List<MemberTaskResponse> getTask(@PathVariable Integer routineId) {
//    return memberTaskService.getTask(routineId);
//  }

  @ApiOperation(value = "할 일 편집")
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

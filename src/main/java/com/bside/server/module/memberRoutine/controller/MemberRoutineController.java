package com.bside.server.module.memberroutine.controller;

import com.bside.server.module.memberroutine.dto.MemberRoutineRequest;
import com.bside.server.module.memberroutine.dto.MemberRoutineResponse;
import com.bside.server.module.memberroutine.service.MemberRoutineService;
import com.bside.server.module.membertask.dto.MemberTaskResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberRoutineController {

  private final MemberRoutineService routineService;

  @ApiOperation(value = "루틴 설정")
  @PostMapping("/routines")
  public MemberRoutineResponse createRoutine(@RequestBody MemberRoutineRequest request) {
   return routineService.createRoutine(request);
  }

  @ApiOperation(value = "루틴 별로 보기")
  @GetMapping("/myRoutines/list")
  public List<MemberRoutineResponse> getRoutineList(@RequestParam String date) {
    return routineService.getRoutineList(date);
  }

  @ApiOperation(value = "할 일 별로 보기")
  @GetMapping("/myRoutines/list/tasks")
  public List<MemberTaskResponse> getTaskList(@RequestParam String date) {
    return routineService.getTaskList(date);
  }

  @ApiOperation(value = "종료 예정 루틴")
  @GetMapping("/myRoutines/end")
  public List<MemberRoutineResponse> getEndRoutine() {
    return routineService.getEndRoutine();
  }

  @ApiOperation(value = "루틴 상세")
  @GetMapping("/myRoutines/{memberRoutineId}")
  public MemberRoutineResponse getRoutineDetail(@PathVariable Integer memberRoutineId) {
    return routineService.getRoutineDetail(memberRoutineId);
  }

  @ApiOperation(value = "루틴 편집")
  @PatchMapping("/myRoutines/{memberRoutineId}/updateRoutine")
  public MemberRoutineResponse updateRoutine(@PathVariable Integer memberRoutineId, @RequestBody MemberRoutineRequest request) {
    return routineService.updateRoutine(memberRoutineId, request);
  }

  @ApiOperation(value = "사용자의 루틴 초기화", notes = "사용자의 루틴을 초기화한다.(할 일 목록의 소요시간 및 상태 초기화)")
  @ApiImplicitParam(name = "memberRoutineId", value = "사용자의 루틴 아이디", required = true)
  @PatchMapping("/myRoutines/{memberRoutineId}/reset")
  public void resetRoutine(@PathVariable Integer memberRoutineId) {
    routineService.resetRoutine(memberRoutineId);
  }

  @ApiOperation(value = "루틴 삭제")
  @PatchMapping("/myRoutines/{memberRoutineId}/update/delete")
  public void deleteRoutine(@PathVariable Integer memberRoutineId) {
    routineService.deleteRoutine(memberRoutineId);
  }
}

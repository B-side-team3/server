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
@RequestMapping("/routines")
public class MemberRoutineController {

  private final MemberRoutineService routineService;

  @PostMapping
  public MemberRoutineResponse createRoutine(@RequestBody MemberRoutineRequest request) {
   return routineService.createRoutine(request);
  }

  @GetMapping("/list")
  public List<MemberRoutineResponse> getRoutine(@RequestParam String date) {
    return routineService.getRoutine(date);
  }

  @GetMapping("/list/tasks")
  public List<MemberTaskResponse> getTask(@RequestParam String date) {
    return routineService.getTask(date);
  }

  @GetMapping("/end")
  public List<MemberRoutineResponse> getEndRoutine() {
    return routineService.getEndRoutine();
  }

  @GetMapping("/{memberRoutineId}")
  public MemberRoutineResponse getRoutineDetail(@PathVariable Integer memberRoutineId) {
    return routineService.getRoutineDetail(memberRoutineId);
  }

  @PatchMapping("/{memberRoutineId}")
  public MemberRoutineResponse updateRoutine(@PathVariable Integer memberRoutineId, @RequestBody MemberRoutineRequest request) {
    return routineService.updateRoutine(memberRoutineId, request);
  }

  @ApiOperation(value = "사용자의 루틴 초기화", notes = "사용자의 루틴을 초기화한다.(할 일 목록의 소요시간 및 상태 초기화)")
  @ApiImplicitParam(name = "memberRoutineId", value = "사용자의 루틴 아이디", required = true)
  @PatchMapping("/{memberRoutineId}/reset")
  public void resetRoutine(@PathVariable Integer memberRoutineId) {
    routineService.resetRoutine(memberRoutineId);
  }

  @DeleteMapping("/{memberRoutineId}")
  public void deleteRoutine(@PathVariable Integer memberRoutineId) {
    routineService.deleteRoutine(memberRoutineId);
  }
}

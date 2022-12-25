package com.bside.server.module.memberroutine.controller;

import com.bside.server.module.memberroutine.dto.MemberRoutineRequest;
import com.bside.server.module.memberroutine.dto.MemberRoutineResponse;
import com.bside.server.module.memberroutine.service.MemberRoutineService;
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

  @PostMapping("/list")
  public List<MemberRoutineResponse> getRoutine(@RequestParam Integer memberId, @RequestParam String date) {
    return routineService.getRoutine(memberId, date);
  }

  @PostMapping("/end")
  public List<MemberRoutineResponse> getEndRoutine(@RequestParam Integer memberId) {
    return routineService.getEndRoutine(memberId);
  }

  @GetMapping("/{memberRoutineId}")
  public MemberRoutineResponse getRoutineDetail(@PathVariable Integer memberRoutineId) {
    return routineService.getRoutineDetail(memberRoutineId);
  }

  @PatchMapping("/{memberRoutineId}")
  public MemberRoutineResponse updateRoutine(@PathVariable Integer memberRoutineId, @RequestBody MemberRoutineRequest request) {
    return routineService.updateRoutine(memberRoutineId, request);
  }

  @DeleteMapping("/{memberRoutineId}")
  public void deleteRoutine(@PathVariable Integer memberRoutineId) {
    routineService.deleteRoutine(memberRoutineId);
  }
}

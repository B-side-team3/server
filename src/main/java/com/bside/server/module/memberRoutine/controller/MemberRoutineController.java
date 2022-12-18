package com.bside.server.module.memberroutine.controller;

import com.bside.server.module.memberroutine.dto.MemberRoutineRequest;
import com.bside.server.module.memberroutine.dto.MemberRoutineResponse;
import com.bside.server.module.memberroutine.service.MemberRoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routines")
public class MemberRoutineController {

  private final MemberRoutineService routineService;

  @PostMapping
  public MemberRoutineResponse createRoutine(@RequestBody MemberRoutineRequest request) {
   return routineService.createRoutine(request);
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

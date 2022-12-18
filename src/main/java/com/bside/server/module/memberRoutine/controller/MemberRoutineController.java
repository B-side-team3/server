package com.bside.server.module.memberroutine.controller;

import com.bside.server.module.memberRoutine.dto.MemberRoutineRequest;
import com.bside.server.module.memberRoutine.dto.MemberRoutineResponse;
import com.bside.server.module.memberroutine.service.MemberRoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberRoutineController {

  private final MemberRoutineService routineService;

  @PostMapping("/routines")
  public MemberRoutineResponse createRoutine(@RequestBody MemberRoutineRequest request) {
   return routineService.createRoutine(request);
  }

  @GetMapping("/routines/{memberRoutineId}")
  public MemberRoutineResponse getRoutineDetail(@PathVariable Integer memberRoutineId) {
    return routineService.getRoutineDetail(memberRoutineId);
  }

  @PatchMapping("/routines/{memberRoutineId}")
  public MemberRoutineResponse updateRoutine(@PathVariable Integer memberRoutineId, @RequestBody MemberRoutineRequest request) {
    return routineService.updateRoutine(memberRoutineId, request);
  }

  @DeleteMapping("/routines/{memberRoutineId}")
  public void deleteRoutine(@PathVariable Integer memberRoutineId, @RequestBody MemberRoutineRequest request) {
    routineService.deleteRoutine(memberRoutineId, request);
  }
}

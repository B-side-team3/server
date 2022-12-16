package com.bside.server.module.memberRoutine.controller;

import com.bside.server.module.memberRoutine.domain.MemberRoutine;
import com.bside.server.module.memberRoutine.service.MemberRoutineService;
import com.bside.server.module.memberRoutine.dto.MemberRoutineRequest;
import com.bside.server.module.memberRoutine.dto.MemberRoutineResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberRoutineController {

  private MemberRoutineService routineService;

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

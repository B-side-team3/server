package com.bside.server.module.routine.controller;

import com.bside.server.global.dto.PageResponse;

import com.bside.server.global.dto.RequestParam;
import com.bside.server.module.routine.domain.Routine;
import com.bside.server.module.routine.dto.RoutineCreateRequest;
import com.bside.server.module.routine.dto.RoutineResponse;
import com.bside.server.module.routine.dto.RoutineUpdateRequest;
import com.bside.server.module.routine.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping("/admin/routines")
    public RoutineResponse createRoutine(@RequestBody @Valid RoutineCreateRequest request) {
        return new RoutineResponse(routineService.createRoutine(request));
    }

    @PatchMapping("/admin/routines/{routineId}")
    public RoutineResponse updateRoutine(@PathVariable("routineId") Integer routineId, @RequestBody RoutineUpdateRequest request) {
        return new RoutineResponse(routineService.updateRoutine(routineId, request));
    }

    @DeleteMapping("/admin/routines/{routineId}")
    public void deleteRoutine(@PathVariable("routineId") Integer routineId) {
        routineService.deleteRoutine(routineId);
    }

    @GetMapping("/categories/{categoryId}/routines")
    public PageResponse<RoutineResponse> getRoutinePageResponse(@PathVariable("categoryId") Integer categoryId, @ModelAttribute RequestParam requestParam) {
        Page<Routine> routinePage = routineService.getRoutinePage(categoryId, requestParam);
        Page<RoutineResponse> routineResponsePage = routinePage.map(RoutineResponse::new);
        return new PageResponse<>(routineResponsePage);
    }

}

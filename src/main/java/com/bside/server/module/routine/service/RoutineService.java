package com.bside.server.module.routine.service;

import com.bside.server.global.dto.RequestParam;
import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.module.category.domain.Category;
import com.bside.server.module.category.service.CategoryService;
import com.bside.server.module.routine.domain.Routine;
import com.bside.server.module.routine.dto.RoutineRequest;
import com.bside.server.module.routine.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineService {

    private final CategoryService categoryService;
    private final RoutineRepository routineRepository;

    public Page<Routine> getRoutinePage(Integer categoryId, RequestParam requestParam) {
        return routineRepository.getRoutinePageByCategoryId(categoryId, requestParam.getPageRequest());
    }

    @Transactional
    public Routine createRoutine(RoutineRequest request) {
        Category category = categoryService.findCategory(request.getCategoryId());
        Routine routine = request.toEntity(request, category);
        return routineRepository.save(routine);
    }

    @Transactional
    public Routine updateRoutine(Integer routineId, RoutineRequest request) {
        Routine routine = findRoutine(routineId);
        return routineRepository.save(updateRoutine(request, routine));
    }

    @Transactional
    public void deleteRoutine(Integer routineId) {
        routineRepository.delete(findRoutine(routineId));
    }

    public Routine findRoutine(Integer routineId) {
        return routineRepository.findById(routineId).orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND));
    }

    private Routine updateRoutine(RoutineRequest request, Routine routine){
        if(request.getCategoryId() != null) {
            routine.updateCategory(categoryService.findCategory(request.getCategoryId()));
        }

        if(request.getTitle() != null) {
            routine.updateTitle(request.getTitle());
        }

        if(request.getDescription() != null) {
            routine.updateDescription(request.getDescription());
        }

        if(request.getImageUrl() != null) {
            routine.updateImageUrl(request.getImageUrl());
        }

        if(request.getPeriod() != null) {
            routine.updatePeriod(request.getPeriod());
        }

        if(request.getStartTime() != null) {
            routine.updateStartTime(request.getStartTime());
        }

        if(request.getAnchor() != null) {
            routine.updateAnchor(request.getAnchor());
        }

        if(request.getTotalTime() != null) {
            routine.updateTotalTime(request.getTotalTime());
        }

        return routine;
    }
}

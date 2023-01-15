package com.bside.server.module.routine.service;

import com.bside.server.global.dto.RequestParam;
import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.global.util.UserContext;
import com.bside.server.module.category.domain.Category;
import com.bside.server.module.category.service.CategoryService;
import com.bside.server.module.routine.domain.Routine;
import com.bside.server.module.routine.dto.RoutineCreateRequest;
import com.bside.server.module.routine.dto.RoutineResponse;
import com.bside.server.module.routine.dto.RoutineUpdateRequest;
import com.bside.server.module.routine.repository.RoutineRepository;
import com.bside.server.module.task.domain.Task;
import com.bside.server.module.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineService {

    @Value("${file-path.routineFile:0}")
    private String filePath;

    private final CategoryService categoryService;
    private final RoutineRepository routineRepository;
    private final TaskRepository taskRepository;

    public Page<Routine> getRoutinePage(Integer categoryId, RequestParam requestParam) {
        return routineRepository.getRoutinePageByCategoryId(categoryId, requestParam.getPageRequest());
    }

    @Transactional
    public Routine createRoutine(RoutineCreateRequest request) {
        Category category = categoryService.findCategory(request.getCategoryId());
        Routine routine = request.toEntity(request, category);
        return routineRepository.save(routine);
    }

    @Transactional
    public Routine updateRoutine(Integer routineId, RoutineUpdateRequest request) {
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

    private Routine updateRoutine(RoutineUpdateRequest request, Routine routine){
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

    @Transactional
    public void routineFileUpload(MultipartFile file) {
      if (UserContext.getMember().isAdmin() == true) {
        Path excelFilePath = Paths.get(filePath + "/" + file.getOriginalFilename());
        try {
          Files.write(excelFilePath, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
      } else throw new CustomException(ErrorCode.NOT_ADMIN);
    }

    @Transactional
    public RoutineResponse getRoutineDetail(Integer routineId) {
      Routine routine = routineRepository.findById(routineId).orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND));
      List<Task> taskList = taskRepository.findByRoutineId(routineId);
      Map<String, Integer> taskListMap = new HashMap<>();
      if (!ObjectUtils.isEmpty(taskList)) {
        for (int i = 0; i < taskList.size(); i++) {
          taskListMap.put(taskList.get(i).getTitle(), taskList.get(i).getExpectedTime());
        }
      } else throw new CustomException(ErrorCode.ROUTINE_NOT_FOUND);
      return new RoutineResponse(routine, taskListMap);
    }
}

package com.bside.server.module.routine.service;

import com.bside.server.global.dto.RequestParam;
import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.global.util.UserContext;
import com.bside.server.module.category.service.CategoryService;
import com.bside.server.module.routine.domain.Routine;
import com.bside.server.module.routine.dto.RoutineResponse;
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

    public Routine findRoutine(Integer routineId) {
        return routineRepository.findById(routineId).orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND));
    }

    @Transactional
    public void routineFileUpload(MultipartFile file) {
      if (UserContext.getMember().isAdmin() == true) {
        Path excelFilePath = Paths.get(filePath + file.getOriginalFilename());
        try {
          Files.write(excelFilePath, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
      } else throw new CustomException(ErrorCode.NOT_ADMIN);
    }

    @Transactional
    public RoutineResponse getRoutineDetail(Integer routineId) {
      Routine routine = findRoutine(routineId);
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

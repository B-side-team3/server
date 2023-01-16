package com.bside.server.module.task.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.module.routine.domain.Routine;
import com.bside.server.module.routine.service.RoutineService;
import com.bside.server.module.task.domain.Task;
import com.bside.server.module.task.dto.TaskCreateRequest;
import com.bside.server.module.task.dto.TaskUpdateRequest;
import com.bside.server.module.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

//    private final RoutineService routineService;
    private final TaskRepository taskRepository;

    public List<Task> getTaskList(Integer routineId) {
        return taskRepository.findByRoutineId(routineId);
    }

//    @Transactional
//    public Task createTask(Integer routineId, TaskCreateRequest request) {
//        Routine routine = routineService.findRoutine(routineId);
//        return taskRepository.save(request.toEntity(request, routine));
//    }
//
//    @Transactional
//    public Task updateTask(Integer routineId, Integer taskId, TaskUpdateRequest request) {
//        routineService.findRoutine(routineId);
//        Task task = findTask(taskId);
//        return taskRepository.save(updateTask(task, request));
//    }
//
//    private Task findTask(Integer taskId) {
//        return taskRepository.findById(taskId).orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
//    }
//
//    private Task updateTask(Task task, TaskUpdateRequest request) {
//        if(request.getTitle() != null) {
//            task.updateTitle(request.getTitle());
//        }
//
//        if(request.getExpectedTime() != null) {
//            task.updateExpectedTime(request.getExpectedTime());
//        }
//        return task;
//    }
}

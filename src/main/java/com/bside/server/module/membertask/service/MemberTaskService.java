package com.bside.server.module.membertask.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.module.membertask.domain.MemberTask;
import com.bside.server.module.membertask.dto.MemberTaskRequest;
import com.bside.server.module.membertask.dto.MemberTaskResponse;
import com.bside.server.module.membertask.repository.MemberTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberTaskService {

  private final MemberTaskRepository memberTaskRepository;

  @Transactional
  public MemberTaskResponse createTask(Integer routineId, MemberTaskRequest request) {
    memberTaskRepository.findByTaskRoutineId(routineId);
    return new MemberTaskResponse(memberTaskRepository.save(request.toEntity(request)));
  }

  @Transactional
  public List<MemberTaskResponse> getTask(Integer routineId) {
    List<MemberTask> taskList = memberTaskRepository.findByTaskRoutineId(routineId);
    return taskList.stream().map(MemberTaskResponse::new).collect(Collectors.toList());
  }

  @Transactional
  public MemberTaskResponse updateTask(Integer routineId, Integer memberTaskId, MemberTaskRequest request) {
    memberTaskRepository.findByTaskRoutineId(routineId);
    MemberTask memberTask = findTask(memberTaskId);
    memberTask.setActualTime(request.getActualTime());
    memberTask.setStatus(request.getStatus());
    return new MemberTaskResponse(memberTaskRepository.save(memberTask));
  }

  @Transactional
  public void deleteTask(Integer routineId, Integer memberTaskId) {
    memberTaskRepository.findByTaskRoutineId(routineId);
    MemberTask memberTask = findTask(memberTaskId);
    memberTaskRepository.delete(memberTask);
  }

  private MemberTask findTask(Integer memberTaskId) {
    return memberTaskRepository.findById(memberTaskId).orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
  }
}

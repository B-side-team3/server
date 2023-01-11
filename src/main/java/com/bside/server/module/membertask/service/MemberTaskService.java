package com.bside.server.module.membertask.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.module.membertask.domain.MemberTask;
import com.bside.server.module.membertask.dto.MemberTaskRequest;
import com.bside.server.module.membertask.dto.MemberTaskResponse;
import com.bside.server.module.membertask.repository.MemberTaskCondition;
import com.bside.server.module.membertask.repository.MemberTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberTaskService {

  private final MemberTaskRepository memberTaskRepository;

//  @Transactional
//  public void createTask(MemberTaskRequest request) {
//    memberTaskRepository.save(request.toEntity(request));
//  }

//  @Transactional
//  public List<MemberTaskResponse> getTask(Integer routineId) {
//    List<MemberTask> taskList = memberTaskRepository.findByTaskRoutineIdAndIsDeleted(routineId, 0);
//    return taskList.stream().map(MemberTaskResponse::new).collect(Collectors.toList());
//  }

  @Transactional
  public MemberTaskResponse updateTask(Integer routineId, Integer memberTaskId, MemberTaskRequest request) {
    memberTaskRepository.findByTaskRoutineIdAndIsDeleted(routineId, 0);
    MemberTask memberTask = findTask(memberTaskId);
    memberTask.setIsDeleted(request.getIsDeleted()); // 테스크편집 - 진행중인/쉬어가는
    memberTask.setStatus(request.getStatus()); // 진행상태
    return new MemberTaskResponse(memberTaskRepository.save(memberTask));
  }

  @Transactional
  public void resetTask(Integer memberTaskId) {
    MemberTaskCondition condition = MemberTaskCondition.builder().memberTaskId(memberTaskId).build();
    memberTaskRepository.resetMemberTask(condition);
  }

//  @Transactional
//  public void deleteTask(Integer routineId, Integer memberTaskId) {
//    memberTaskRepository.findByTaskRoutineIdAndIsDeleted(routineId, 0);
//    MemberTask memberTask = findTask(memberTaskId);
//    memberTask.setIsDeleted(1);
//    memberTaskRepository.save(memberTask);
//  }

  private MemberTask findTask(Integer memberTaskId) {
    return memberTaskRepository.findById(memberTaskId).orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
  }
}

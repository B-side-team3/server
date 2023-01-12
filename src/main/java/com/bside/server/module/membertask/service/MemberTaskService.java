package com.bside.server.module.membertask.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.module.membertask.domain.MemberTask;
import com.bside.server.module.membertask.dto.MemberTaskRequest;
import com.bside.server.module.membertask.repository.MemberTaskCondition;
import com.bside.server.module.membertask.repository.MemberTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberTaskService {

  private final MemberTaskRepository memberTaskRepository;

  @Transactional
  public void createTask(Integer memberRoutineId, MemberTaskRequest request) {
    List<MemberTask> memberTaskList = memberTaskRepository.findByMemberRoutineMemberRoutineId(memberRoutineId);
    if (!ObjectUtils.isEmpty(memberTaskList)) {
      for (MemberTask memberTask : memberTaskList) {
        memberTask.setActualTime(request.getActualTime());
        memberTaskRepository.saveAndFlush(memberTask);
      }
    } else throw new CustomException(ErrorCode.ROUTINE_NOT_FOUND);
  }

//  @Transactional
//  public List<MemberTaskResponse> getTask(Integer routineId) {
//    List<MemberTask> taskList = memberTaskRepository.findByTaskRoutineIdAndIsDeleted(routineId, 0);
//    return taskList.stream().map(MemberTaskResponse::new).collect(Collectors.toList());
//  }

  @Transactional
  public void updateTask(Integer memberRoutineId, MemberTaskRequest request) {
    List<MemberTask> memberTaskList = memberTaskRepository.findByMemberRoutineMemberRoutineId(memberRoutineId);
    if (!ObjectUtils.isEmpty(memberTaskList)) {
      for (MemberTask memberTask : memberTaskList) {
        memberTask.setIsDeleted(request.getIsDeleted()); // 테스크편집 - 진행중인/쉬어가는
        memberTask.setStatus(request.getStatus()); // 진행상태
        memberTaskRepository.save(memberTask);
      }
    } else throw new CustomException(ErrorCode.ROUTINE_NOT_FOUND);
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

//  private MemberTask findTask(Integer memberTaskId) {
//    return memberTaskRepository.findById(memberTaskId).orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
//  }
}

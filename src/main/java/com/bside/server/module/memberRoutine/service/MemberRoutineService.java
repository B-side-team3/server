package com.bside.server.module.memberroutine.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.global.util.UserContext;
import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.memberroutine.dto.MemberRoutineResponse;
import com.bside.server.module.memberroutine.repository.MemberRoutineRepository;
import com.bside.server.module.memberroutine.dto.MemberRoutineRequest;
import com.bside.server.module.membertask.domain.MemberTask;
import com.bside.server.module.membertask.dto.MemberTaskResponse;
import com.bside.server.module.membertask.repository.MemberTaskCondition;
import com.bside.server.module.membertask.repository.MemberTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberRoutineService {

  private final MemberRoutineRepository memberRoutineRepository;
  private final MemberTaskRepository memberTaskRepository;

  @Transactional
  public MemberRoutineResponse createRoutine(MemberRoutineRequest request) {
    if (!ObjectUtils.isEmpty(request.getMemberTaskList())) {
      for (int i = 0; i < request.getMemberTaskList().size(); i++) {
        memberTaskRepository.save(request.getMemberTaskList().get(i).toEntity(request.getMemberTaskList().get(i)));
      }
    } else throw new CustomException(ErrorCode.TASK_NOT_FOUND);
    return new MemberRoutineResponse(memberRoutineRepository.save(request.toEntity(request)));
  }

  @Transactional
  public List<MemberRoutineResponse> getRoutineList(String dateStr) {
    updateStatus();
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    LocalDateTime date = LocalDate.parse(dateStr, formatter).atStartOfDay();
    List<MemberRoutine> memberRoutineList = memberRoutineRepository.findByMemberMemberIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatusAndIsDeleted(UserContext.getMember().getMemberId(), date, date, "ongoing", 0);
    return memberRoutineList.stream().map(MemberRoutineResponse::new).collect(Collectors.toList());
  }

  @Transactional
  public List<MemberTaskResponse> getTaskList(String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    LocalDateTime date = LocalDate.parse(dateStr, formatter).atStartOfDay();
    List<MemberTask> taskList = memberTaskRepository.findByMemberMemberIdAndMemberRoutineStartDateLessThanEqualAndMemberRoutineEndDateGreaterThanEqualAndMemberRoutineStatusAndIsDeleted(UserContext.getMember().getMemberId(), date, date, "ongoing", 0);
    return taskList.stream().map(MemberTaskResponse::new).collect(Collectors.toList());
  }

  @Transactional
  public List<MemberRoutineResponse> getEndRoutine() {
    String startDateStr = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    LocalDateTime startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE).atStartOfDay();
    LocalDateTime endDate = startDate.plusDays(7);
    List<MemberRoutine> memberRoutineList = memberRoutineRepository.findByMemberMemberIdAndStartDateLessThanEqualAndEndDateLessThanEqualAndStatusAndIsDeleted(UserContext.getMember().getMemberId(), startDate, endDate, "ongoing", 0);
    return memberRoutineList.stream().map(MemberRoutineResponse::new).collect(Collectors.toList());
  }

  @Transactional
  public MemberRoutineResponse getRoutineDetail(Integer memberRoutineId) {
    MemberRoutine memberRoutine = findRoutine(memberRoutineId);
    List<MemberTask> memberTaskList = memberTaskRepository.findByMemberRoutineMemberRoutineId(memberRoutineId);
    Map<String, Integer> memberTaskListMap = new HashMap<>();
    if (!ObjectUtils.isEmpty(memberTaskList)) {
      for (int i = 0; i < memberTaskList.size(); i++) {
        memberTaskListMap.put(memberTaskList.get(i).getTask().getTitle(), memberTaskList.get(i).getTask().getExpectedTime());
      }
    } else throw new CustomException(ErrorCode.ROUTINE_NOT_FOUND);
    return new MemberRoutineResponse(memberRoutine, memberTaskListMap);
  }

  @Transactional
  public MemberRoutineResponse updateRoutine(Integer memberRoutineId, MemberRoutineRequest request) {
    MemberRoutine memberRoutine = findRoutine(memberRoutineId);
    memberRoutine.setStartDate(request.getStartDate());
    memberRoutine.setEndDate(request.getEndDate());
    memberRoutine.setAnchor(request.getAnchor());
    memberRoutine.setStatus(request.getStatus());
    memberRoutine.setColor(request.getColor().name());
    memberRoutine.setStartTime(request.getStartTime());
    memberRoutine.setIsPush(request.getIsPush());
    return new MemberRoutineResponse(memberRoutineRepository.save(memberRoutine));
  }

  @Transactional
  public void deleteRoutine(Integer memberRoutineId) {
    MemberRoutine memberRoutine = findRoutine(memberRoutineId);
    List<MemberTask> memberTaskList = memberTaskRepository.findByMemberRoutineMemberRoutineId(memberRoutineId);
    memberRoutine.setIsDeleted(1);
    for (MemberTask memberTask : memberTaskList) {
      memberTask.setIsDeleted(1);
      memberTaskRepository.save(memberTask);
    }
    memberRoutineRepository.save(memberRoutine);
  }

  @Transactional
  public void resetRoutine(Integer memberRoutineId) {
    MemberTaskCondition condition = MemberTaskCondition.builder().memberRoutineId(memberRoutineId).build();
    memberTaskRepository.resetMemberTask(condition);
  }

  private MemberRoutine findRoutine(Integer memberRoutineId) {
    return memberRoutineRepository.findByMemberRoutineId(memberRoutineId).orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND));
  }

  private void updateStatus() {
    List<MemberRoutine> memberRoutineList = memberRoutineRepository.findByMemberMemberIdAndStatusAndIsDeleted(UserContext.getMember().getMemberId(), "ongoing", 0);
    LocalDateTime date = LocalDateTime.now();
    for (MemberRoutine memberRoutine : memberRoutineList) {
      if (memberRoutine.getEndDate().compareTo(date) == -1) {
        memberRoutine.setStatus("uncompleted");
        memberRoutineRepository.saveAndFlush(memberRoutine);
      }
    }
  }
}

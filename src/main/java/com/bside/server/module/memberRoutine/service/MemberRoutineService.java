package com.bside.server.module.memberroutine.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.memberroutine.dto.MemberRoutineResponse;
import com.bside.server.module.memberroutine.repository.MemberRoutineRepository;
import com.bside.server.module.memberroutine.dto.MemberRoutineRequest;
import com.bside.server.module.membertask.domain.MemberTask;
import com.bside.server.module.membertask.dto.MemberTaskResponse;
import com.bside.server.module.membertask.repository.MemberTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberRoutineService {

  private final MemberRoutineRepository memberRoutineRepository;
  private final MemberTaskRepository memberTaskRepository;

  @Transactional
  public MemberRoutineResponse createRoutine(MemberRoutineRequest request) {
    return new MemberRoutineResponse(memberRoutineRepository.save(request.toEntity(request)));
  }

  @Transactional
  public List<MemberRoutineResponse> getRoutine(Integer memberId, String dateStr) {
    updateStatus(memberId);
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    LocalDateTime date = LocalDate.parse(dateStr, formatter).atStartOfDay();
    List<MemberRoutine> memberRoutineList = memberRoutineRepository.findByMemberMemberIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatus(memberId, date, date, "ongoing");
    return memberRoutineList.stream().map(MemberRoutineResponse::new).collect(Collectors.toList());
  }

  @Transactional
  public List<MemberTaskResponse> getTask(Integer memberId, String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    LocalDateTime date = LocalDate.parse(dateStr, formatter).atStartOfDay();
    List<MemberTask> taskList = memberTaskRepository.findByMemberMemberIdAndMemberRoutineStartDateLessThanEqualAndMemberRoutineEndDateGreaterThanEqualAndMemberRoutineStatus(memberId, date, date, "ongoing");
    return taskList.stream().map(MemberTaskResponse::new).collect(Collectors.toList());
  }

  @Transactional
  public List<MemberRoutineResponse> getEndRoutine(Integer memberId) {
    String startDateStr = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    LocalDateTime startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE).atStartOfDay();
    LocalDateTime endDate = startDate.plusDays(7);
    List<MemberRoutine> memberRoutineList = memberRoutineRepository.findByMemberMemberIdAndStartDateLessThanEqualAndEndDateLessThanEqualAndStatus(memberId, startDate, endDate, "ongoing");
    return memberRoutineList.stream().map(MemberRoutineResponse::new).collect(Collectors.toList());
  }

  @Transactional
  public MemberRoutineResponse getRoutineDetail(Integer memberRoutineId) {
    MemberRoutine memberRoutine = findRoutine(memberRoutineId);
    return new MemberRoutineResponse(memberRoutine);
  }

  @Transactional
  public MemberRoutineResponse updateRoutine(Integer memberRoutineId, MemberRoutineRequest request) {
    MemberRoutine memberRoutine = findRoutine(memberRoutineId);
    memberRoutine.setAnchor(request.getAnchor());
    memberRoutine.setStatus(request.getStatus());
    return new MemberRoutineResponse(memberRoutineRepository.save(memberRoutine));
  }

  @Transactional
  public void deleteRoutine(Integer memberRoutineId) {
    MemberRoutine memberRoutine = findRoutine(memberRoutineId);
    memberRoutineRepository.delete(memberRoutine);
  }

  private MemberRoutine findRoutine(Integer memberRoutineId) {
    return memberRoutineRepository.findByMemberRoutineId(memberRoutineId).orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND));
  }

  private void updateStatus(Integer memberId) {
    List<MemberRoutine> memberRoutineList = memberRoutineRepository.findByMemberMemberIdAndStatus(memberId, "ongoing");
    LocalDateTime date = LocalDateTime.now();
    for (MemberRoutine memberRoutine : memberRoutineList) {
      if (memberRoutine.getEndDate().compareTo(date) == -1) {
        memberRoutine.setStatus("uncompleted");
        memberRoutineRepository.saveAndFlush(memberRoutine);
      }
    }
  }
}

package com.bside.server.module.memberroutine.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.memberroutine.dto.MemberRoutineResponse;
import com.bside.server.module.memberroutine.repository.MemberRoutineRepository;
import com.bside.server.module.memberroutine.dto.MemberRoutineRequest;
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

  @Transactional
  public MemberRoutineResponse createRoutine(MemberRoutineRequest request) {
    return new MemberRoutineResponse(memberRoutineRepository.save(request.toEntity(request)));
  }

  @Transactional
  public List<MemberRoutineResponse> getRoutine(Integer memberId, String startDateStr, String endDateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    LocalDateTime startDate = LocalDate.parse(startDateStr, formatter).atStartOfDay();
    LocalDateTime endDate = LocalDate.parse(endDateStr, formatter).atStartOfDay();
    List<MemberRoutine> memberRoutineList = memberRoutineRepository.findByMemberMemberIdAndStartDateBetween(memberId, startDate, endDate);
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
}

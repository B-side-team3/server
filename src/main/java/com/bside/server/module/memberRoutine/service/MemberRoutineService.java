package com.bside.server.module.memberRoutine.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.module.memberRoutine.domain.MemberRoutine;
import com.bside.server.module.memberRoutine.dto.MemberRoutineResponse;
import com.bside.server.module.memberRoutine.repository.MemberRoutineRepository;
import com.bside.server.module.memberRoutine.dto.MemberRoutineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberRoutineService {

  private final MemberRoutineRepository memberRoutineRepository;

  @Transactional
  public MemberRoutineResponse createRoutine(MemberRoutineRequest request) {
    return new MemberRoutineResponse(memberRoutineRepository.save(request.toEntity(request)));
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
  public void deleteRoutine(Integer memberRoutineId, MemberRoutineRequest request) {
    MemberRoutine memberRoutine = findRoutine(memberRoutineId);
    memberRoutineRepository.delete(memberRoutine);
  }

  private MemberRoutine findRoutine(Integer memberRoutineId) {
    return memberRoutineRepository.findByMemberRoutineId(memberRoutineId).orElseThrow(() -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND));
  }
}

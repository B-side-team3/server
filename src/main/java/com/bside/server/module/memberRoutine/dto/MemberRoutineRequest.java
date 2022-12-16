package com.bside.server.module.memberRoutine.dto;

import com.bside.server.module.memberRoutine.domain.MemberRoutine;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRoutineRequest {

  private Integer memberRoutineId;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private String startTime;

  private String anchor;

  public MemberRoutine toEntity(MemberRoutineRequest request){
    return MemberRoutine.builder().startDate(request.getStartDate()).endDate(request.getEndDate()).startTime(request.getStartTime()).anchor(request.getAnchor()).build();
  }
}

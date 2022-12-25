package com.bside.server.module.memberroutine.dto;

import com.bside.server.module.member.domain.Member;
import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.routine.domain.Routine;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRoutineRequest {

  private Integer memberRoutineId;

  private Member member;

  private Routine routine;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private String startTime;

  private String anchor;

  private String status;

  public MemberRoutine toEntity(MemberRoutineRequest request){
    return MemberRoutine
        .builder()
        .member(request.getMember())
        .routine(request.getRoutine())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .startTime(request.getStartTime())
        .anchor(request.getAnchor())
        .status(request.getStatus())
        .build();
  }
}

package com.bside.server.module.memberroutine.dto;

import com.bside.server.global.util.UserContext;
import com.bside.server.module.member.domain.Member;
import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.routine.domain.Routine;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberRoutineRequest {

  private Integer memberId;

  private Integer routineId;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private String startTime;

  private String anchor;

  @Builder.Default
  private String status = "ongoing";

  @Builder.Default
  private Integer isDeleted = 0;

  public MemberRoutine toEntity(MemberRoutineRequest request) {
    return MemberRoutine
        .builder()
//        .member(UserContext.getMember())
        .member(Member.builder().memberId(request.getMemberId()).build())
        .routine(Routine.builder().id(request.getRoutineId()).build())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .startTime(request.getStartTime())
        .anchor(request.getAnchor())
        .status(request.getStatus())
        .isDeleted(request.getIsDeleted())
        .build();
  }
}

package com.bside.server.module.memberroutine.dto;

import com.bside.server.global.util.UserContext;
import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.memberroutine.enums.Color;
import com.bside.server.module.membertask.dto.MemberTaskRequest;
import com.bside.server.module.routine.domain.Routine;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MemberRoutineRequest {

  private Integer routineId;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private Color color;

  private String startTime;

  private String anchor;

  private Integer isPush;

  private List<MemberTaskRequest> memberTaskList;

  @Builder.Default
  private String status = "ongoing";

  @Builder.Default
  private Integer isDeleted = 0;

  public MemberRoutine toEntity(MemberRoutineRequest request) {
    return MemberRoutine
        .builder()
        .member(UserContext.getMember())
        .routine(Routine.builder().id(request.getRoutineId()).build())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .color(request.getColor().name())
        .startTime(request.getStartTime())
        .anchor(request.getAnchor())
        .status(request.getStatus())
        .isDeleted(request.getIsDeleted())
        .isPush(request.getIsPush())
        .build();
  }
}

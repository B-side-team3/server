package com.bside.server.module.memberroutine.dto;

import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.routine.domain.Routine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberRoutineResponse {

  private Integer memberRoutineId;
  private Integer routineId;
  private Integer memberId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String startTime;
  private String anchor;
  private Integer totalTime;
  private LocalDateTime createdDate;

  public MemberRoutineResponse(MemberRoutine memberRoutine) {
    this.memberRoutineId = memberRoutine.getMemberRoutineId();
    this.routineId = memberRoutine.getRoutine().getId();
    this.memberId = memberRoutine.getMember().getMemberId();
    this.startDate = memberRoutine.getStartDate();
    this.endDate = memberRoutine.getEndDate();
    this.startTime = memberRoutine.getStartTime();
    this.anchor = memberRoutine.getAnchor();
    this.totalTime = memberRoutine.getTotalTime();
    this.createdDate = memberRoutine.getCreatedDate();
  }
}

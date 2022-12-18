package com.bside.server.module.memberRoutine.dto;

import com.bside.server.module.memberRoutine.domain.MemberRoutine;
import com.bside.server.module.routine.domain.Routine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberRoutineResponse {

  private Integer memberRoutineId;
  private Routine routine;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String startTime;
  private String anchor;
  private Integer totalTime;
  private LocalDateTime createdDateTime;

  public MemberRoutineResponse(MemberRoutine memberRoutine) {
    this.memberRoutineId = memberRoutine.getMemberRoutineId();
    this.routine = memberRoutine.getRoutine();
    this.startDate = memberRoutine.getStartDate();
    this.endDate = memberRoutine.getEndDate();
    this.startTime = memberRoutine.getStartTime();
    this.anchor = memberRoutine.getAnchor();
    this.totalTime = memberRoutine.getTotalTime();
    this.createdDateTime = memberRoutine.getCreatedDateTime();
  }
}

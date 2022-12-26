package com.bside.server.module.memberroutine.dto;

import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberRoutineResponse {

  private Integer memberRoutineId;
  private Integer routineId;
  private Integer memberId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDateTime startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDateTime endDate;
  private String startTime;
  private String anchor;
  private Integer totalTime;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdDate;
  private String status;

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
    this.status = memberRoutine.getStatus();
  }
}

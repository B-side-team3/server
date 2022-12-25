package com.bside.server.module.membertask.dto;

import com.bside.server.module.member.domain.Member;
import com.bside.server.module.membertask.domain.MemberTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTaskResponse {

  private Integer memberTaskId;
  private Integer taskId;
  private Integer memberId;
  private Integer actualTime;
  private String status;
  private Integer isDeleted;
  private LocalDateTime createdDate;

  public MemberTaskResponse(MemberTask memberTask) {
    this.memberTaskId = memberTask.getMemberTaskId();
    this.taskId = memberTask.getTask().getId();
    this.memberId = memberTask.getMember().getMemberId();
    this.actualTime = memberTask.getActualTime();
    this.status = memberTask.getStatus();
    this.isDeleted = memberTask.getIsDeleted();
    this.createdDate = memberTask.getCreatedDate();
  }
}

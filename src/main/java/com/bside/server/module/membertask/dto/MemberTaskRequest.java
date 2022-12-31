package com.bside.server.module.membertask.dto;

import com.bside.server.module.member.domain.Member;
import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.membertask.domain.MemberTask;
import com.bside.server.module.task.domain.Task;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberTaskRequest {

  private Integer taskId;

  private Integer memberId;

  private Integer memberRoutineId;

  private Integer actualTime;

  @Builder.Default
  private String status = "ongoing";

  @Builder.Default
  private Integer isDeleted = 0;

  public MemberTask toEntity(MemberTaskRequest request) {
    return MemberTask
        .builder()
        .task(Task.builder().id(getTaskId()).build())
//        .member(UserContext.getMember())
        .member(Member.builder().memberId(request.getMemberId()).build())
        .memberRoutine(MemberRoutine.builder().memberRoutineId(getMemberRoutineId()).build())
        .actualTime(request.getActualTime())
        .status(request.getStatus())
        .isDeleted(request.getIsDeleted())
        .build();
  }
}

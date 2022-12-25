package com.bside.server.module.membertask.dto;

import com.bside.server.module.member.domain.Member;
import com.bside.server.module.membertask.domain.MemberTask;
import com.bside.server.module.task.domain.Task;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTaskRequest {

  private Integer memberTaskId;

  private Task task;

  private Member member;

  private int actualTime;

  private String status;

  private int isDeleted;

  public MemberTask toEntity(MemberTaskRequest request) {
    return MemberTask
        .builder()
        .task(request.getTask())
        .member(request.getMember())
        .actualTime(request.getActualTime())
        .status(request.getStatus())
        .isDeleted(request.getIsDeleted())
        .build();
  }
}

package com.bside.server.module.membertask.dto;

import com.bside.server.global.util.UserContext;
import com.bside.server.module.membertask.domain.MemberTask;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberTaskRequest {

  private Integer actualTime;

  @Builder.Default
  private String status = "ongoing";

  @Builder.Default
  private Integer isDeleted = 0;

  public MemberTask toEntity(MemberTaskRequest request) {
    return MemberTask
        .builder()
        .member(UserContext.getMember())
        .actualTime(request.getActualTime())
        .status(request.getStatus())
        .isDeleted(request.getIsDeleted())
        .build();
  }
}

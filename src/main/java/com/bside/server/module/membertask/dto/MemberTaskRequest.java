package com.bside.server.module.membertask.dto;

import com.bside.server.module.membertask.domain.MemberTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTaskRequest {

  private Integer memberTaskId;

  private Integer actualTime;

  private String status;

  public MemberTask toEntity(MemberTaskRequest request) {
    return MemberTask
        .builder()
        .actualTime(request.getActualTime())
        .status(request.getStatus())
        .build();
  }
}

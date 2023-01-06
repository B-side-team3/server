package com.bside.server.module.membertask.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberTaskCondition {
    private Integer memberRoutineId;
    private Integer memberTaskId;
}

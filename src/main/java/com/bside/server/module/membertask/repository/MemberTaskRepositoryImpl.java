package com.bside.server.module.membertask.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.bside.server.module.membertask.domain.QMemberTask.memberTask;

@RequiredArgsConstructor
public class MemberTaskRepositoryImpl implements MemberTaskRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public void resetMemberTask(MemberTaskCondition condition) {
        queryFactory
                .update(memberTask)
                .where(eqMemberTaskId(condition.getMemberTaskId()),
                        eqMemberRoutineId(condition.getMemberRoutineId()))
                .set(memberTask.actualTime, 0)
                .set(memberTask.status, "uncompleted")
                .execute();
    }

    private BooleanExpression eqMemberTaskId(Integer memberTaskId) {
        return memberTaskId == null ? null : memberTask.memberTaskId.eq(memberTaskId);
    }

    private BooleanExpression eqMemberRoutineId(Integer memberRoutineId) {
        return memberRoutineId == null ? null : memberTask.memberRoutine.memberRoutineId.eq(memberRoutineId);
    }
}

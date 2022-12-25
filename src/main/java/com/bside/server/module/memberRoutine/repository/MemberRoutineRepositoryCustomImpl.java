//package com.bside.server.module.memberroutine.repository;
//
//import com.bside.server.module.memberroutine.domain.MemberRoutine;
//import com.bside.server.module.memberroutine.domain.QMemberRoutine;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class MemberRoutineRepositoryCustomImpl implements MemberRoutineRepositoryCustom {
//
//  private final JPAQueryFactory jpaQueryFactory;
//  QMemberRoutine memberRoutine = QMemberRoutine.memberRoutine;
//
//  @Override
//  public List<MemberRoutine> findEndRoutine(Integer memberId, LocalDateTime startDate, LocalDateTime endDate, String status) {
//    return jpaQueryFactory
//          .selectFrom(memberRoutine)
//          .where(memberRoutine.member.memberId.eq(memberId).and(memberRoutine.startDate.loe(startDate)).and(memberRoutine.endDate.loe(endDate)).and(memberRoutine.status.eq(status)))
//          .fetch();
//  }
//}

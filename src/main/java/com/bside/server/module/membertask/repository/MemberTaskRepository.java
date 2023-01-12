package com.bside.server.module.membertask.repository;

import com.bside.server.module.membertask.domain.MemberTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberTaskRepository extends JpaRepository<MemberTask, Integer>, MemberTaskRepositoryCustom {
  List<MemberTask> findByMemberMemberIdAndMemberRoutineStartDateLessThanEqualAndMemberRoutineEndDateGreaterThanEqualAndMemberRoutineStatusAndIsDeleted(Integer memberId, LocalDateTime startdDate, LocalDateTime endDate, String status, Integer isDeleted);
  List<MemberTask> findByMemberRoutineMemberRoutineId(Integer memberRoutineId);
}

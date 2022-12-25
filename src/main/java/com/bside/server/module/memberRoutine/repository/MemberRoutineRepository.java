package com.bside.server.module.memberroutine.repository;

import com.bside.server.module.memberroutine.domain.MemberRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutine, Integer> {
  Optional<MemberRoutine> findByMemberRoutineId(Integer memberRoutineId);
  List<MemberRoutine> findByMemberMemberIdAndStatus(Integer memberId, String status);
  List<MemberRoutine> findByMemberMemberIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatus(Integer memberId, LocalDateTime startDate, LocalDateTime endDate, String status);
  List<MemberRoutine> findByMemberMemberIdAndStartDateLessThanEqualAndEndDateLessThanEqualAndStatus(Integer memberId, LocalDateTime startDate, LocalDateTime endDate, String status);
}

package com.bside.server.module.memberroutine.repository;

import com.bside.server.module.memberroutine.domain.MemberRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutine, Integer> {
  Optional<MemberRoutine> findByMemberRoutineId(Integer memberRoutineId);
  List<MemberRoutine> findByMemberMemberIdAndStatusAndIsDeleted(Integer memberId, String status, Integer isDeleted);
  List<MemberRoutine> findByMemberMemberIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatusAndIsDeleted(Integer memberId, LocalDateTime startDate, LocalDateTime endDate, String status, Integer isDeleted);
  List<MemberRoutine> findByMemberMemberIdAndStartDateLessThanEqualAndEndDateLessThanEqualAndStatusAndIsDeleted(Integer memberId, LocalDateTime startDate, LocalDateTime endDate, String status, Integer isDeleted);
}

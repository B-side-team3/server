package com.bside.server.module.memberroutine.repository;

import com.bside.server.module.memberroutine.domain.MemberRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutine, Integer> {
  Optional<MemberRoutine> findByMemberRoutineId(Integer MemberRoutineId);
  List<MemberRoutine> findByMemberMemberIdAndStartDateBetween(Integer MemberId, LocalDateTime StartDate, LocalDateTime EndDate);
}

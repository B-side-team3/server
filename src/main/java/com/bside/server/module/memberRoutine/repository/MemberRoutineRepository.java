package com.bside.server.module.memberRoutine.repository;

import com.bside.server.module.memberRoutine.domain.MemberRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutine, Long> {
  Optional<MemberRoutine> findByMemberRoutineId(Integer MemberRoutineId);
}

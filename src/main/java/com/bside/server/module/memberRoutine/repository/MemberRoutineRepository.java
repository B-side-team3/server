package com.bside.server.module.memberroutine.repository;

import com.bside.server.module.memberroutine.domain.MemberRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutine, Long> {
  Optional<MemberRoutine> findByMemberRoutineId(Integer MemberRoutineId);
}

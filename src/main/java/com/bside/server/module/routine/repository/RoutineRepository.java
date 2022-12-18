package com.bside.server.module.routine.repository;

import com.bside.server.module.routine.domain.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Integer>, RoutineRepositoryCustom {

}

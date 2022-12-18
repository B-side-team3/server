package com.bside.server.module.membertask.repository;

import com.bside.server.module.membertask.domain.MemberTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberTaskRepository extends JpaRepository<MemberTask, Integer> {
  List<MemberTask> findByTaskRoutineId(Integer routineId);
}

package com.bside.server.module.task.repository;

import com.bside.server.module.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "select t from Task t where t.routine.id = :routineId")
    List<Task> findByRoutineId(@Param("routineId") Integer routineId);
}

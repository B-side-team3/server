package com.bside.server.module.routine.repository;

import com.bside.server.module.routine.domain.Routine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoutineRepositoryCustom {
    Page<Routine> getRoutinePageByCategoryId(Integer categoryId, Pageable pageable);
}

package com.bside.server.module.routine.repository;

import com.bside.server.module.routine.domain.Routine;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import static com.bside.server.module.routine.domain.QRoutine.routine;

@RequiredArgsConstructor
public class RoutineRepositoryImpl implements RoutineRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @Override
    public Page<Routine> getRoutinePageByCategoryId(Integer categoryId, Pageable pageable) {

        JPAQuery<Routine> query = queryFactory
                .select(routine)
                .from(routine)
                .where(routine.category.id.eq(categoryId));

        if (pageable.isPaged()) {
            query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize());
        }

        JPAQuery<Long> countQuery = queryFactory
                .select(routine.count())
                .from(routine)
                .where(routine.category.id.eq(categoryId));

        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchOne);
    }
}

package com.bside.server.module.memberroutine.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRoutine is a Querydsl query type for MemberRoutine
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRoutine extends EntityPathBase<MemberRoutine> {

    private static final long serialVersionUID = -330759939L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRoutine memberRoutine = new QMemberRoutine("memberRoutine");

    public final StringPath anchor = createString("anchor");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> isDeleted = createNumber("isDeleted", Integer.class);

    public final com.bside.server.module.member.domain.QMember member;

    public final NumberPath<Integer> memberRoutineId = createNumber("memberRoutineId", Integer.class);

    public final com.bside.server.module.routine.domain.QRoutine routine;

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final StringPath startTime = createString("startTime");

    public final StringPath status = createString("status");

    public final NumberPath<Integer> totalTime = createNumber("totalTime", Integer.class);

    public QMemberRoutine(String variable) {
        this(MemberRoutine.class, forVariable(variable), INITS);
    }

    public QMemberRoutine(Path<? extends MemberRoutine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRoutine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRoutine(PathMetadata metadata, PathInits inits) {
        this(MemberRoutine.class, metadata, inits);
    }

    public QMemberRoutine(Class<? extends MemberRoutine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.bside.server.module.member.domain.QMember(forProperty("member")) : null;
        this.routine = inits.isInitialized("routine") ? new com.bside.server.module.routine.domain.QRoutine(forProperty("routine"), inits.get("routine")) : null;
    }

}


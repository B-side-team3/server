package com.bside.server.module.membertask.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberTask is a Querydsl query type for MemberTask
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberTask extends EntityPathBase<MemberTask> {

    private static final long serialVersionUID = 201892339L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberTask memberTask = new QMemberTask("memberTask");

    public final NumberPath<Integer> actualTime = createNumber("actualTime", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> expectedTime = createNumber("expectedTime", Integer.class);

    public final NumberPath<Integer> isDeleted = createNumber("isDeleted", Integer.class);

    public final com.bside.server.module.member.domain.QMember member;

    public final NumberPath<Integer> memberTaskId = createNumber("memberTaskId", Integer.class);

    public final StringPath status = createString("status");

    public final com.bside.server.module.task.domain.QTask task;

    public QMemberTask(String variable) {
        this(MemberTask.class, forVariable(variable), INITS);
    }

    public QMemberTask(Path<? extends MemberTask> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberTask(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberTask(PathMetadata metadata, PathInits inits) {
        this(MemberTask.class, metadata, inits);
    }

    public QMemberTask(Class<? extends MemberTask> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.bside.server.module.member.domain.QMember(forProperty("member")) : null;
        this.task = inits.isInitialized("task") ? new com.bside.server.module.task.domain.QTask(forProperty("task"), inits.get("task")) : null;
    }

}


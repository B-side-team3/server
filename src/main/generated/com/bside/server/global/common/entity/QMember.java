package com.bside.server.global.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.bside.server.member.domain.Member;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 232874180L;

    public static final QMember member = new QMember("member1");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Integer> isAdmin = createNumber("isAdmin", Integer.class);

    public final StringPath isDeleted = createString("isDeleted");

    public final NumberPath<Integer> memberId = createNumber("memberId", Integer.class);

    public final StringPath nickname = createString("nickname");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}


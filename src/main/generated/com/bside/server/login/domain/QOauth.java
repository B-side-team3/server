package com.bside.server.login.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOauth is a Querydsl query type for Oauth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOauth extends EntityPathBase<Oauth> {

    private static final long serialVersionUID = 1562134107L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOauth oauth = new QOauth("oauth");

    public final StringPath accessToken = createString("accessToken");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> isDeleted = createNumber("isDeleted", Integer.class);

    public final com.bside.server.member.domain.QMember member;

    public final NumberPath<Integer> oauthId = createNumber("oauthId", Integer.class);

    public final StringPath refreshToken = createString("refreshToken");

    public final StringPath type = createString("type");

    public QOauth(String variable) {
        this(Oauth.class, forVariable(variable), INITS);
    }

    public QOauth(Path<? extends Oauth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOauth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOauth(PathMetadata metadata, PathInits inits) {
        this(Oauth.class, metadata, inits);
    }

    public QOauth(Class<? extends Oauth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.bside.server.member.domain.QMember(forProperty("member")) : null;
    }

}


package com.bside.server.module.routine.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoutine is a Querydsl query type for Routine
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoutine extends EntityPathBase<Routine> {

    private static final long serialVersionUID = -1919173923L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoutine routine = new QRoutine("routine");

    public final StringPath anchor = createString("anchor");

    public final com.bside.server.module.category.domain.QCategory category;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath model = createString("model");

    public final StringPath startTime = createString("startTime");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> totalTime = createNumber("totalTime", Integer.class);

    public QRoutine(String variable) {
        this(Routine.class, forVariable(variable), INITS);
    }

    public QRoutine(Path<? extends Routine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoutine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoutine(PathMetadata metadata, PathInits inits) {
        this(Routine.class, metadata, inits);
    }

    public QRoutine(Class<? extends Routine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.bside.server.module.category.domain.QCategory(forProperty("category")) : null;
    }

}


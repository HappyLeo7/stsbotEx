package com.keduit.bpro051.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemom is a Querydsl query type for Memom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemom extends EntityPathBase<Memom> {

    private static final long serialVersionUID = 1936986016L;

    public static final QMemom memom = new QMemom("memom");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final StringPath writer = createString("writer");

    public QMemom(String variable) {
        super(Memom.class, forVariable(variable));
    }

    public QMemom(Path<? extends Memom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemom(PathMetadata metadata) {
        super(Memom.class, metadata);
    }

}


package org.example.dao;

import org.example.entity.AbstractEntity;

import java.util.Set;

public interface AbstractDao<ENTITY extends AbstractEntity, ID extends Number> {
    void create(ENTITY entity);

    ENTITY read(ID id);

    Set<ENTITY> read();

    void update(ENTITY entity);

    void delete(ENTITY entity);
}

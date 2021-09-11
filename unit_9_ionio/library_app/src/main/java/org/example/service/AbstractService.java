package org.example.service;

import org.example.entity.AbstractEntity;

import java.util.Set;

public interface AbstractService<ENTITY extends AbstractEntity, ID extends Number> {
    void create(ENTITY entity);
    ENTITY read(ID id);
    Set<ENTITY> readAll();
    void update(ENTITY entity);
    void delete(ENTITY entity);
}

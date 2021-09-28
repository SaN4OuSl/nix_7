package org.example.parser;

public interface PropertyMapper {
    <T> T parse(Class<T> entityClass);
}

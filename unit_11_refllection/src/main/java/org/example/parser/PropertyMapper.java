package org.example.parser;

import java.util.Properties;

public interface PropertyMapper {
    <T> T map(Class<T> entityClass, Properties properties);
}

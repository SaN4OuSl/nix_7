package org.example.csvutil;

import java.util.List;

public interface CsvMapper {
    <T> List<T> map(Class<T> tClass, CsvData data);
}

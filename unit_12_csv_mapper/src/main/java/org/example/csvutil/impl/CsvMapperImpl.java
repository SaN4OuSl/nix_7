package org.example.csvutil.impl;

import org.example.annotations.CsvCell;
import org.example.csvutil.CsvData;
import org.example.csvutil.CsvMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CsvMapperImpl implements CsvMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvMapperImpl.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public <T> List<T> map(Class<T> tClass, CsvData data) {
        LOGGER.info("Start mapping csv");
        List<T> result = new ArrayList<>();
        Constructor<T> constructor;
        try {
            constructor = tClass.getConstructor();
        } catch (NoSuchMethodException e) {
            LOGGER.error("Error ", e);
            throw new RuntimeException(e);
        }
        try {
            for (int i = 0; i < data.size(); i++) {
                T target = constructor.newInstance();
                for (Field field : target.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(CsvCell.class)) {
                        CsvCell csvCell = field.getAnnotation(CsvCell.class);
                        String header = csvCell.value();

                        if (field.getType() == Integer.class) {
                            field.set(target, Integer.parseInt(data.get(i, header)));
                        } else if (field.getType() == Long.class) {
                            field.set(target, Long.parseLong(data.get(i, header)));
                        } else if (field.getType() == Float.class) {
                            field.set(target, Float.parseFloat(data.get(i, header)));
                        } else if (field.getType() == Double.class) {
                            field.set(target, Double.parseDouble(data.get(i, header)));
                        } else if (field.getType() == Boolean.class) {
                            field.set(target, Boolean.parseBoolean(data.get(i, header)));
                        } else if (field.getType() == LocalDateTime.class) {
                            try {
                                field.set(target, LocalDateTime.parse(data.get(i, header), formatter));
                            } catch (DateTimeParseException e) {
                                LOGGER.error("Error: ", e);
                            }
                        } else {
                            field.set(target, data.get(i, header));
                        }
                    }
                    field.setAccessible(false);
                }
                result.add(target);
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            LOGGER.error("Error: ", e);
        }
        return result;
    }
}

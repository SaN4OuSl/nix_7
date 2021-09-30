package org.example.csvutil;

import org.example.annotations.CsvCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvMapper.class);
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static <T> List<T> map(Class<T> tClass, CsvData data) {
        LOGGER.info("Start mapping csv");
        List<T> result = new ArrayList<>();
        Constructor<T> constructor;
        T target;
        try {
            constructor = tClass.getConstructor();
            target = constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Error: " + e);
            throw new RuntimeException(e);
        }

        try {
            for (int i = 0; i < data.size(); i++) {
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
                        } else if (field.getType() == Date.class) {
                            try {
                                field.set(target, format.parse(data.get(i, header)));
                            } catch (ParseException e) {
                                LOGGER.error("Error: " + e);
                            }
                        } else {
                            field.set(target, data.get(i, header));
                        }
                    }
                    field.setAccessible(false);
                }
                result.add(target);
            }
        } catch (IllegalAccessException e) {
            LOGGER.error("Error: " + e);
        }
        return result;
    }
}

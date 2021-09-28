package org.example.parser.impl;

import org.example.annotation.PropertyKey;
import org.example.parser.PropertyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class PropertiesMapperImpl implements PropertyMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger("info");
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public <T> T parse(Class<T> entityClass) {
        LOGGER.info("Start parsing object");
        T obj = null;
        try {
            obj = Objects.requireNonNull(entityClass.getConstructor()).newInstance();
            for (Field field : obj.getClass().getFields()) {

                if (field.isAnnotationPresent(PropertyKey.class)) {
                    PropertyKey property = field.getAnnotation(PropertyKey.class);
                    String getValueFromProperty = new PropertiesParserImpl().getProperty(property.value());

                    if (field.getType().equals(Integer.TYPE)) {
                        field.set(obj, Integer.parseInt(getValueFromProperty));
                    } else if (field.getType().equals(Long.TYPE)) {
                        field.set(obj, Long.parseLong(getValueFromProperty));
                    } else if (field.getType().equals(Float.TYPE)) {
                        field.set(obj, Float.parseFloat(getValueFromProperty));
                    } else if (field.getType().equals(Double.TYPE)) {
                        field.set(obj, Double.parseDouble(getValueFromProperty));
                    } else if (field.getType().equals(Boolean.TYPE)) {
                        field.set(obj, Boolean.parseBoolean(getValueFromProperty));
                    } else if (field.getType() == Date.class) {
                        try {
                            field.set(obj, format.parse(getValueFromProperty));
                        } catch (ParseException e) {
                            LOGGER.info("Error: " + e);
                            System.err.println(e.getMessage());
                        }
                    } else {
                        field.set(obj, getValueFromProperty);
                    }
                }
            }
        } catch (IllegalAccessException | NumberFormatException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            LOGGER.info("Error: " + e);
            System.err.println(e.getMessage());
        }
        return obj;
    }
}

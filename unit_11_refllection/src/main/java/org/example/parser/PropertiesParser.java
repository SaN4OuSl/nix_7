package org.example.parser;

import org.example.Unit11Main;
import org.example.annotation.PropertyKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class PropertiesParser {

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    public void parse(Object obj) {
        LOGGER.info("Start parsing object");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (Field field : obj.getClass().getFields()) {

                if (field.isAnnotationPresent(PropertyKey.class)) {
                    PropertyKey property = field.getAnnotation(PropertyKey.class);

                    if (field.getType().equals(Integer.TYPE)) {
                        field.set(obj, Integer.parseInt(getProperty(property.value())));
                    } else if (field.getType().equals(Long.TYPE)) {
                        field.set(obj, Long.parseLong(getProperty(property.value())));
                    } else if (field.getType().equals(Float.TYPE)) {
                        field.set(obj, Float.parseFloat(getProperty(property.value())));
                    } else if (field.getType().equals(Double.TYPE)) {
                        field.set(obj, Double.parseDouble(getProperty(property.value())));
                    } else if (field.getType().equals(Boolean.TYPE)) {
                        field.set(obj, Boolean.parseBoolean(getProperty(property.value())));
                    } else if (field.getType() == Date.class) {
                        try {
                            field.set(obj, format.parse(getProperty(property.value())));
                        } catch (ParseException e) {
                            LOGGER.info("Error: " + e);
                            System.err.println(e.getMessage());
                        }
                    } else {
                        field.set(obj, getProperty(property.value()));
                    }
                }
            }
        } catch (IllegalAccessException | NumberFormatException e) {
            LOGGER.info("Error: " + e);
            System.err.println(e.getMessage());
        }
    }

    private String getProperty(String property) {

        LOGGER.info("Start get properties");
        Properties properties = new Properties();

        try (InputStream input = Unit11Main.class.getResourceAsStream("/app.properties")) {
            properties.load(input);
        } catch (IOException | NullPointerException e) {
            LOGGER.info("Error: " + e);
            throw new RuntimeException(e);
        }

        return properties.getProperty(property);
    }
}

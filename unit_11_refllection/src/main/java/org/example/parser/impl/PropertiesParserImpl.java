package org.example.parser.impl;

import org.example.parser.PropertyParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesParserImpl implements PropertyParser {
    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    public String getProperty(String property) {

        LOGGER.info("Start get properties");
        Properties properties = new Properties();

        try (BufferedReader input = new BufferedReader(new FileReader("unit_11_refllection/app.properties"))) {
            properties.load(input);
        } catch (IOException | NullPointerException e) {
            LOGGER.info("Error: " + e);
            throw new RuntimeException(e);
        }

        return properties.getProperty(property);
    }
}

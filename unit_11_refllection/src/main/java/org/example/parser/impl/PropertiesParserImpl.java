package org.example.parser.impl;

import org.example.parser.PropertyParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesParserImpl implements PropertyParser {
    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    public Properties getProperty() {

        LOGGER.info("Start get properties");
        Properties property = new Properties();
        try (BufferedReader input = new BufferedReader(new FileReader("unit_11_refllection/app.properties"))) {
            property.load(input);
        } catch (IOException e) {
            LOGGER.info("Error: " + e);
        }
        return property;
    }
}

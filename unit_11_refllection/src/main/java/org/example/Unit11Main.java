package org.example;

import org.example.parser.PropertiesParser;
import org.example.properties.AppProperties;

public class Unit11Main {

    public static void main(String[] args) {

        AppProperties appProperties = new AppProperties();
        PropertiesParser propertiesParser = new PropertiesParser();

        propertiesParser.parse(appProperties);

        System.out.println("Amount of connections = " + appProperties.amountConnections);
        System.out.println("Max number of connections = " + appProperties.maxConnections);
        System.out.println("Date of connection = " + appProperties.dateConnection);
        System.out.println("Name of connection = " + appProperties.nameConnection);
        System.out.println("Float number = " + appProperties.floatNumber);
    }
}

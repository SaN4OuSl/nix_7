package org.example;

import org.example.parser.impl.PropertiesMapperImpl;
import org.example.parser.impl.PropertiesParserImpl;
import org.example.properties.AppProperties;

public class Unit11Main {

    public static void main(String[] args) {

        AppProperties appProperties = new PropertiesMapperImpl().map(AppProperties.class, new PropertiesParserImpl().getProperty(args[0]));

        System.out.println("Amount of connections = " + appProperties.amountConnections);
        System.out.println("Max number of connections = " + appProperties.maxConnections);
        System.out.println("Date of connection = " + appProperties.dateConnection);
        System.out.println("Name of connection = " + appProperties.nameConnection);
        System.out.println("Float number = " + appProperties.floatNumber);
    }
}

package org.example.csvutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.util.Scanner;

public class CsvParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvParser.class);

    public static CsvData parse(BufferedReader stream) {
        LOGGER.info("Start parsing csv");
        Scanner scanner;
        CsvData res = new CsvData();

        scanner = new Scanner(stream);

        while (scanner.hasNext()) {
            res.add(scanner.nextLine().split(","));
        }

        return res;
    }
}

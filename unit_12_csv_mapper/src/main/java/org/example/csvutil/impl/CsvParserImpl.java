package org.example.csvutil.impl;

import org.example.csvutil.CsvData;
import org.example.csvutil.CsvParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.util.Scanner;

public class CsvParserImpl implements CsvParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvParserImpl.class);

    public CsvData parse(BufferedReader stream) {
        LOGGER.info("Start parsing csv");
        Scanner scanner;
        CsvData res = new CsvData();

        scanner = new Scanner(stream);
        int i = 0;
        while (scanner.hasNext()) {
            res.add(scanner.nextLine().split(","));
            i++;
        }

        return res;
    }
}

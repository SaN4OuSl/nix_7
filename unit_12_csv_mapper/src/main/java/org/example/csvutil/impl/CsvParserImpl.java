package org.example.csvutil.impl;

import org.example.csvutil.CsvData;
import org.example.csvutil.CsvParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvParserImpl implements CsvParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvParserImpl.class);

    public CsvData parse(BufferedReader stream) {
        LOGGER.info("Start parsing csv");
        List<String[]> rows = new ArrayList<>();
        Scanner scanner = new Scanner(stream);
        while (scanner.hasNext()) {
            rows.add(scanner.nextLine().split(","));
        }

        return new CsvData(rows);
    }
}

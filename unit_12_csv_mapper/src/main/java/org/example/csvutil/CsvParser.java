package org.example.csvutil;

import java.io.BufferedReader;

public interface CsvParser {
    CsvData parse(BufferedReader stream);
}

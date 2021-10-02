package org.example.csvutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CsvData {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvData.class);

    private final List<String[]> data;

    public CsvData() {
        this.data = new ArrayList<>();
    }

    public int size() {
        return data.size() - 1;
    }

    public String get(int row, int col) {
        return data.get(row + 1)[col];
    }

    public String get(int row, String h) {
        int col = List.of(this.data.get(0)).indexOf(h);
        if (col == -1) {
            LOGGER.error("No annotated field with this name!");
        }
        return data.get(row + 1)[col];
    }

    public String[] getHeader() {
        return this.data.get(0);
    }

    public void add(String[] row) {
        this.data.add(row);
    }
}

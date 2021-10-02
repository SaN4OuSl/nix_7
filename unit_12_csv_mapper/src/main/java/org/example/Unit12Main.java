package org.example;

import org.example.controller.CsvController;

public class Unit12Main {

    public static void main(String[] args) {
        CsvController.getInstance().run(args[0]);
    }
}

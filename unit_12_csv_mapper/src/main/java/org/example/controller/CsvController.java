package org.example.controller;

import org.example.csvutil.CsvData;
import org.example.csvutil.CsvMapper;
import org.example.csvutil.CsvParser;
import org.example.csvutil.impl.CsvMapperImpl;
import org.example.csvutil.impl.CsvParserImpl;
import org.example.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CsvController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvController.class);
    private static final CsvMapper CSV_MAPPER = new CsvMapperImpl();
    private static final CsvParser CSV_PARSER = new CsvParserImpl();
    private static CsvController instance = null;

    public static CsvController getInstance() {
        if (instance == null) {
            instance = new CsvController();
        }
        return instance;
    }

    public void run(String fileName) {
        Scanner in = new Scanner(System.in);

        CsvData data;
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            data = CSV_PARSER.parse(input);
        } catch (IOException e) {
            LOGGER.error("Error: ", e);
            throw new RuntimeException(e);
        }

        boolean isRun = true;
        while (isRun) {
            String row, col;

            System.out.println("Chose number from list and enter:\n" +
                    "1. Print list of users\n" +
                    "2. Print data by row and column number\n" +
                    "3. Print data by line number and header text\n" +
                    "4. Print list of headers\n" +
                    "For exit enter any another symbol");
            String userChoice = in.nextLine();

            switch (userChoice) {
                case "1":
                    List<User> users = CSV_MAPPER.map(User.class, data);
                    for (User user : users) {
                        System.out.println(user.toString());
                    }
                    break;
                case "2":
                    System.out.print("Enter a number of row: ");
                    row = in.nextLine();
                    System.out.print("Enter a number of col: ");
                    col = in.nextLine();
                    try {
                        System.out.println(data.get(Integer.parseInt(row), Integer.parseInt(col)));
                    } catch (RuntimeException e) {
                        LOGGER.error("Input error: ", e);
                    }
                    break;
                case "3":
                    System.out.print("Enter a number of row: ");
                    row = in.nextLine();
                    System.out.print("Enter a name of header: ");
                    col = in.nextLine();
                    try {
                        System.out.println(data.get(Integer.parseInt(row), col));
                    } catch (RuntimeException e) {
                        LOGGER.error("Input error: ", e);
                    }
                    break;
                case "4":
                    System.out.println(Arrays.toString(data.getHeader()));
                    break;
                default:
                    isRun = false;
            }
        }
    }
}

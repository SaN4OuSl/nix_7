package org.example.calendar.controllers;

import org.example.calendar.data.MyDate;
import org.example.calendar.exceptions.MyDateException;
import org.example.calendar.service.DateServiceAmerican;
import org.example.calendar.utils.CalculatorDates;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static org.example.calendar.controllers.SupportController.*;

public class CalendarControllerAmerican {

    private static final Scanner in = new Scanner(System.in);
    private static final DateServiceAmerican VALIDATOR = new DateServiceAmerican();
    private static final String[] BIGMONTH = new String[7];
    private static final String[] SMALLMONTH = new String[4];
    private static final String ANSWER = "What do you want";
    private static final String[] FORMATS = new String[7];

    static {
        FORMATS[0] = "mm/dd/yy - 01/12/34 00:00:00:000";
        FORMATS[1] = "/dd/yyyy - 1-4-2021 00:00:00:000";
        FORMATS[2] = "mm//yyyy - 4-1-2021 00:00:00:000";
        FORMATS[3] = "mmm d yy - Month 4 2021";
        FORMATS[4] = "dd mmm yyyy 00:00 - 09 Month 789 24:23";
        FORMATS[5] = "mm-dd-yy - 01-12-34";
        FORMATS[6] = "yyy 00:00 - 01 January 2020 00:00";
    }

    static {
        BIGMONTH[0] = "1";
        BIGMONTH[1] = "3";
        BIGMONTH[2] = "5";
        BIGMONTH[3] = "7";
        BIGMONTH[4] = "8";
        BIGMONTH[5] = "10";
        BIGMONTH[6] = "12";
    }

    static {
        SMALLMONTH[0] = "4";
        SMALLMONTH[1] = "6";
        SMALLMONTH[2] = "9";
        SMALLMONTH[3] = "11";
    }

    public static void run() {
        printAvailableFormats();
        boolean endProgram = false;
        while (!endProgram) {
            System.out.println("CALENDAR CALCULATOR(AMERICAN)");
            System.out.println("0. Exit\n" +
                    "1. Difference between dates \n" +
                    "2. Add or subtract time to date\n" +
                    "3. List of dates and sort them\n" +
                    "4. Print available formats\n");
            System.out.print(ANSWER + "?\n");
            String index = in.nextLine();
            switch (index) {
                case "0":
                    endProgram = true;
                    break;
                case "1":
                    differenceBetweenDates();
                    break;
                case "2":
                    addOrSubtract();
                    break;
                case "3":
                    listOfDates();
                    break;
                case "4":
                    printAvailableFormats();
                    break;
                default:
                    System.out.println("Incorrect index");
            }
        }
    }

    private static void differenceBetweenDates() {
        System.out.print("Enter the first date:");
        String first;
        boolean isNew = true;
        do {
            if (!isNew) {
                System.out.println("Incorrect date, please enter true date");
                System.out.print("Enter the first date:");
            }
            first = in.nextLine();
            isNew = false;
        } while (!checkFormatOfDate(first) || !checkFormatOfTime(first));
        MyDate firstDate = null;
        try {
            firstDate = VALIDATOR.stringToDate(first);
        } catch (MyDateException e) {
            e.printStackTrace();
        }

        System.out.print("Enter the second date:");
        String second;
        boolean isNew1 = true;
        do {
            if (!isNew1) {
                System.out.println("Incorrect date, please enter true date");
                System.out.print("Enter the second date:");
            }
            second = in.nextLine();
            isNew1 = false;
        } while (!checkFormatOfDate(second) || !checkFormatOfTime(second));
        MyDate secondDate = null;
        try {
            secondDate = VALIDATOR.stringToDate(second);
        } catch (MyDateException e) {
            e.printStackTrace();
        }

        long result = CalculatorDates.differenceBetweenDates(firstDate, secondDate);
        printOutputOfOperationInSelectedType(result);
        System.out.println();
    }

    private static void addOrSubtract() {

        System.out.print("Enter the date:");
        String date;
        boolean isFirst = true;
        do {
            if (!isFirst) {
                System.out.println("Incorrect date, please enter true date");
                System.out.print("Enter the date:");
            }
            date = in.nextLine();
            isFirst = false;
        } while (!checkFormatOfDate(date) || !checkFormatOfTime(date));
        MyDate myDate = null;
        try {
            myDate = VALIDATOR.stringToDate(date);
        } catch (MyDateException e) {
            e.printStackTrace();
        }

        System.out.print(ANSWER + "(add - (+)/subtract - (-))?");
        String operation = in.nextLine();
        long operand;
        String choice;
        switch (operation) {
            case "+":
                System.out.println(ANSWER + " to add?");
                operand = selectMeasureAndConvertToMilliseconds();
                myDate = CalculatorDates.addMillisecondsToDate(myDate, operand);
                System.out.println("In what format you want see the date\n" +
                        "1. Month 4 2021 00:00:00:000\n" +
                        "2. 4 Month 2021 00:00:00:000\n" +
                        "3. dd/mm/yy 00:00:00:000\n" +
                        "4. mm/dd/yy 00:00:00:000");
                choice = in.nextLine();
                System.out.println("The result date: " + VALIDATOR.dateToString(myDate, choice));
                break;
            case "-":
                System.out.println(ANSWER + " to subtract?");
                operand = selectMeasureAndConvertToMilliseconds();
                myDate = CalculatorDates.subtractMillisecondsFromDate(myDate, operand);
                System.out.println("In what format you want see the date\n" +
                        "1. Month 4 2021 00:00:00:000\n" +
                        "2. 4 Month 2021 00:00:00:000\n" +
                        "3. dd/mm/yy 00:00:00:000\n" +
                        "4. mm/dd/yy 00:00:00:000");
                choice = in.nextLine();
                System.out.println("The result date: " + VALIDATOR.dateToString(myDate, choice));
                break;
            default:
                System.out.println("Incorrect symbol");
        }
        System.out.println();
    }

    private static void listOfDates() {
        ArrayList<MyDate> dates = new ArrayList<MyDate>();
        boolean endAddDates = false;
        while (!endAddDates) {
            System.out.println("0. Exit\n" +
                    "1. Add date");
            System.out.print(ANSWER + "?\n");
            String indexAdd = in.nextLine();
            switch (indexAdd) {
                case "0":
                    endAddDates = true;
                    break;
                case "1":
                    System.out.print("Enter the date:");
                    String date;
                    boolean isFirst = true;
                    do {
                        if (!isFirst) {
                            System.out.println("Incorrect date, please enter true date");
                            System.out.print("Enter the date:");
                        }
                        date = in.nextLine();
                        isFirst = false;
                    } while (!checkFormatOfDate(date) || !checkFormatOfTime(date));
                    MyDate myDate = null;
                    try {
                        myDate = VALIDATOR.stringToDate(date);
                    } catch (MyDateException e) {
                        e.printStackTrace();
                    }
                    dates.add(myDate);
                    break;
                default:
                    System.out.println("Incorrect index");
            }
        }
        String kindOfSort;
        System.out.println("1. Decreasing sorting\n" +
                "2. Increasing sorting\n" +
                "Any another symbol to disable sorting");
        kindOfSort = in.nextLine();
        sortDates(dates, kindOfSort);
        System.out.println("In what format you want see the date\n" +
                "1. Month 4 2021 00:00:00:000\n" +
                "2. 4 Month 2021 00:00:00:000\n" +
                "3. dd/mm/yy 00:00:00:000\n" +
                "4. mm/dd/yy 00:00:00:000");
        String choice = in.nextLine();
        System.out.println("The dates:");
        for (MyDate o : dates) {
            System.out.println(VALIDATOR.dateToString(o, choice));
        }
        System.out.println();
    }

    public static void printAvailableFormats() {
        System.out.println("There are several available formats for entering date:");
        for (int i = 0; i < FORMATS.length; i++) {
            System.out.println(FORMATS[i] + ";");
        }
        System.out.println();
    }

    private static boolean checkFormatOfDate(String input) {
        try {
            if (input.contains("/") || input.contains("-")) {
                String delimiter;
                if (input.contains("/")) delimiter = "/";
                else delimiter = "-";

                String[] split = new String[4];

                switch (delimiter) {
                    case "/":
                        input += " 0";
                        split = input.split("[/ ]");
                        break;
                    case "-":
                        input += " 0";
                        split = input.split("[- ]");
                        break;
                }
                if (split.length >= 2) {
                    int day;
                    if (split[1].equals("")) {
                        split[1] = "1";
                        day = Integer.parseInt(split[1]);
                    } else {
                        day = Integer.parseInt(split[1]);
                    }

                    int month;
                    if (split[0].equals("")) {
                        split[0] = "1";
                        month = Integer.parseInt(split[0]);
                    } else {
                        month = Integer.parseInt(split[0]);
                    }

                    long year;
                    if (split[2].equals("")) {
                        split[2] = "0";
                        year = Integer.parseInt(split[2]);
                    } else {
                        year = Integer.parseInt(split[2]);
                    }
                    if ((split[0].matches("[-+]?\\d+") || split[0].matches(""))
                            && (split[1].matches("[-+]?\\d+"))
                            && split[2].matches("[-+]?\\d+")) {
                        if (day > 0 && month <= 12 && month >= 1 && year >= 0) {
                            boolean whatMonth = false;
                            for (int i = 0; i < BIGMONTH.length; i++) {
                                if (split[0].equals(BIGMONTH[i])) {
                                    if (day <= 31)
                                        whatMonth = true;
                                }
                            }
                            for (int i = 0; i < SMALLMONTH.length; i++) {
                                if (split[0].equals(SMALLMONTH[i])) {
                                    if (day <= 30)
                                        whatMonth = true;
                                }
                            }
                            if (split[0].equals("2")) {
                                if (day == 29 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0))
                                    whatMonth = true;
                                if (day <= 28)
                                    whatMonth = true;
                            }
                            return whatMonth;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                boolean isMonth = false;
                String[] split = input.split("[ ]");
                int countSpace = 0;
                for (char element : input.toCharArray()) {
                    if (element == ' ') countSpace++;
                }
                if (countSpace == 0) {
                    if (split[0].matches("[-+]?\\d+") && Integer.parseInt(split[0]) >= 0) {
                        return true;
                    }
                }
                if (input.contains(":") && countSpace == 1) {
                    if (split[0].matches("[-+]?\\d+") && Integer.parseInt(split[0]) >= 0) {
                        return true;
                    }
                }
                String[] MONTHS_NAMES = {"January", "February", "March", "April", "May", "June", "July",
                        "August", "September", "October", "November", "December"};
                if (split.length >= 2) {
                    if (split[1].matches("[-+]?\\d+")) {
                        int day = Integer.parseInt(split[1]);
                        int year = Integer.parseInt(split[2]);
                        for (int i = 0; i < MONTHS_NAMES.length; i++) {
                            if (split[0].toLowerCase(Locale.ROOT).equals(MONTHS_NAMES[i].toLowerCase(Locale.ROOT))) {
                                if (day > 0 && year >= 0) {
                                    for (int j = 0; j < BIGMONTH.length; j++) {
                                        if (i + 1 == Integer.parseInt(BIGMONTH[j])) {
                                            if (day <= 31)
                                                isMonth = true;
                                        }
                                    }
                                    for (int j = 0; j < SMALLMONTH.length; j++) {
                                        if (i + 1 == Integer.parseInt(SMALLMONTH[j])) {
                                            if (day <= 30)
                                                isMonth = true;
                                        }
                                    }
                                    if (split[0].toLowerCase(Locale.ROOT).equals("february")) {
                                        if (day == 29 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0))
                                            isMonth = true;
                                        if (day <= 28)
                                            isMonth = true;
                                    }
                                }
                            }
                        }
                        return isMonth;
                    } else {
                        int day = Integer.parseInt(split[0]);
                        int year = Integer.parseInt(split[2]);
                        for (int i = 0; i < MONTHS_NAMES.length; i++) {
                            if (split[1].toLowerCase(Locale.ROOT).equals(MONTHS_NAMES[i].toLowerCase(Locale.ROOT))) {
                                if (day > 0 && year >= 0) {
                                    for (int j = 0; j < BIGMONTH.length; j++) {
                                        if (i + 1 == Integer.parseInt(BIGMONTH[j])) {
                                            if (day <= 31)
                                                isMonth = true;
                                        }
                                    }
                                    for (int j = 0; j < SMALLMONTH.length; j++) {
                                        if (i + 1 == Integer.parseInt(SMALLMONTH[j])) {
                                            if (day <= 30)
                                                isMonth = true;
                                        }
                                    }
                                    if (split[1].toLowerCase(Locale.ROOT).equals("february")) {
                                        if (day == 29 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0))
                                            isMonth = true;
                                        if (day <= 28)
                                            isMonth = true;
                                    }
                                }
                            }
                        }
                        return isMonth;
                    }
                } else {
                    return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Some numbers are missing");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Not number");
            return false;
        }
    }

}

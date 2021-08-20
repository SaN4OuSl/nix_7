package org.example;

import org.example.calendar.data.MyDate;
import org.example.calendar.exceptions.MyDateException;
import org.example.calendar.service.DateServiceAmerican;
import org.example.calendar.util.CalculatorDates;
import org.example.calendar.util.ConverterToMilliseconds;
import org.example.calendar.util.InverterFromMilliseconds;

import java.util.ArrayList;
import java.util.Scanner;

public class CalendarControllerAmerican {

    private static final Scanner in = new Scanner(System.in);
    private static final DateServiceAmerican VALIDATOR = new DateServiceAmerican();
    private static final String[] FORMATS = new String[5];
    private static final String[] BIGMONTH = new String[7];
    private static final String[] SMALLMONTH = new String[4];
    private static final String ANSWER = "What do you want";
    private static final String TYPES = "1-Milliseconds\n" +
            "2-Seconds\n" +
            "3-Minutes\n" +
            "4-Hours\n" +
            "5-Days\n" +
            "6-Years\n";

    static {
        FORMATS[0] = "mm/dd/yy - 01/12/34";
        FORMATS[1] = "/dd/yyyy - 1-4-2021 23:23";
        FORMATS[2] = "mmm d yy - Month 4 2021";
        FORMATS[3] = "dd mmm yyyy 00:00 - 09 Month 789 24:23";
        FORMATS[4] = "mm-dd-yy - 01-12-34";
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

    private static void printAvailableFormats() {
        System.out.println("There are several available formats for entering date:");
        for (int i = 0; i < FORMATS.length; i++) {
            System.out.println(i + " " + FORMATS[i] + ";");
        }
        System.out.println();
    }

    private static ArrayList<MyDate> sortDates(ArrayList<MyDate> dates, String a) {
        boolean isSorted;
        int listSize = dates.size();
        switch (a) {
            case "1":
                do {
                    isSorted = true;
                    for (int i = 0; i < listSize - 1; i++) {
                        if (ConverterToMilliseconds.dateIntoMilliseconds(dates.get(i)) < ConverterToMilliseconds.dateIntoMilliseconds(dates.get(i + 1))) {
                            MyDate date = dates.get(i);
                            dates.set(i, dates.get(i + 1));
                            dates.set(i + 1, date);
                            isSorted = false;
                        }
                    }
                } while (!isSorted);
                return dates;
            case "2":
                do {
                    isSorted = true;
                    for (int i = 0; i < listSize - 1; i++) {
                        if (ConverterToMilliseconds.dateIntoMilliseconds(dates.get(i)) > ConverterToMilliseconds.dateIntoMilliseconds(dates.get(i + 1))) {
                            MyDate date = dates.get(i);
                            dates.set(i, dates.get(i + 1));
                            dates.set(i + 1, date);
                            isSorted = false;
                        }
                    }
                } while (!isSorted);
                return dates;
            default:
                return dates;
        }
    }

    private static void printOutputOfOperationInSelectedType(long milliseconds) {
        boolean endLoop;
        do {
            endLoop = false;
            System.out.println("How to display the result?");
            System.out.print("Difference in:\n" + TYPES);
            System.out.println("For exit enter any another symbol");
            String type = in.nextLine();
            System.out.println();
            String s = "Difference between dates in:\n";
            switch (type) {
                case "1":
                    System.out.println(s + "Milliseconds: " + milliseconds);
                    break;
                case "2":
                    System.out.println(s + "Seconds: " + InverterFromMilliseconds.toSeconds(milliseconds));
                    break;
                case "3":
                    System.out.println(s + "Minutes: " + InverterFromMilliseconds.toMinutes(milliseconds));
                    break;
                case "4":
                    System.out.println(s + "Hours: " + InverterFromMilliseconds.toHours(milliseconds));
                    break;
                case "5":
                    System.out.println(s + "Days: " + InverterFromMilliseconds.toDays(milliseconds));
                    break;
                case "6":
                    System.out.println(s + "Years: " + InverterFromMilliseconds.toYears(milliseconds));
                    break;
                default:
                    endLoop = true;
            }
        } while (!endLoop);
    }

    private static long selectMeasureAndConvertToMilliseconds() {
        System.out.print(TYPES);
        String type = in.nextLine();
        System.out.print("Value: ");
        boolean isFirst = true;
        String value;
        do {
            if (!isFirst) {
                System.out.println("Incorrect number, please enter correct");
                System.out.print("Value: ");
            }
            value = in.nextLine();
            isFirst = false;
        } while (!value.matches("[-+]?\\d+"));
        long valueInLong = Long.parseLong(value);
        switch (type) {
            case "1":
                return valueInLong;
            case "2":
                return ConverterToMilliseconds.secondsToMilli(valueInLong);
            case "3":
                return ConverterToMilliseconds.minutesToMilli((valueInLong));
            case "4":
                return ConverterToMilliseconds.hoursToMilli((valueInLong));
            case "5":
                return ConverterToMilliseconds.daysToMilli((valueInLong));
            case "6":
                return ConverterToMilliseconds.yearsToMilli(((int) valueInLong));
            default:
                System.out.println("Invalid index of operation");
        }
        return 0;
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
                        split = input.split("[/ ]");
                        break;
                    case "-":
                        split = input.split("[- ]");
                        break;
                }
                if (split.length >= 2) {
                    int day = Integer.parseInt(split[1]);
                    int month;
                    if (split[0].equals("")) {
                        split[0] = "1";
                        month = Integer.parseInt(split[0]);
                    } else {
                        month = Integer.parseInt(split[0]);
                    }
                    long year = Integer.parseInt(split[2]);
                    if ((split[0].matches("[-+]?\\d+") || split[0].matches(""))
                            && (split[1].matches("[-+]?\\d+"))
                            && split[2].matches("[-+]?\\d+")) {
                        if (day > 0 && month <= 11 && month >= 1 && year >= 0) {
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
                String[] MONTHS_NAMES = {"January", "February", "March", "April", "May", "June", "July",
                        "August", "September", "October", "November", "December"};
                if (split.length >= 2) {
                    if (split[1].matches("[-+]?\\d+")) {
                        for (int i = 0; i < MONTHS_NAMES.length; i++) {
                            if (split[0].equals(MONTHS_NAMES[i])) {
                                isMonth = true;
                            }
                        }
                        return isMonth;
                    } else {
                        for (int i = 0; i < MONTHS_NAMES.length; i++) {
                            if (split[1].equals(MONTHS_NAMES[i])) {
                                isMonth = true;
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

    private static boolean checkFormatOfTime(String input) {
        boolean isTime = true;
        try {
            if (input.contains("/") || input.contains("-")) {
                String delimiter;
                if (input.contains("/")) delimiter = "/";
                else delimiter = "-";
                String[] split = new String[4];

                switch (delimiter) {
                    case "/":
                        split = input.split("[/ ]");
                        break;
                    case "-":
                        split = input.split("[- ]");
                        break;
                }
                if (split.length > 3) {
                    String[] splittime = split[3].split(":");
                    if (!splittime[0].equals("")) {
                        if (Integer.parseInt(splittime[0]) > 23 || Integer.parseInt(splittime[0]) < 0) {
                            isTime = false;
                        }
                    }
                    if (splittime.length > 1) {
                        if (Integer.parseInt(splittime[1]) > 59 || Integer.parseInt(splittime[1]) < 0) {
                            isTime = false;
                        }
                    }
                    if (splittime.length > 2) {
                        if (Integer.parseInt(splittime[2]) > 59 || Integer.parseInt(splittime[2]) < 0) {
                            isTime = false;
                        }
                    }
                    if (splittime.length > 3) {
                        if (Integer.parseInt(splittime[3]) > 999 || Integer.parseInt(splittime[3]) < 0) {
                            isTime = false;
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Some numbers are missing");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Not number");
            return false;
        }
        return isTime;
    }
}

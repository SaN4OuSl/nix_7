package org.example.calendar.controllers;

import org.example.calendar.data.MyDate;
import org.example.calendar.utils.ConverterToMilliseconds;
import org.example.calendar.utils.InverterFromMilliseconds;

import java.util.ArrayList;
import java.util.Scanner;

public class SupportController {
    private static final Scanner in = new Scanner(System.in);
    private static final String[] FORMATS = new String[5];
    private static final String TYPES = "1-Milliseconds\n" +
            "2-Seconds\n" +
            "3-Minutes\n" +
            "4-Hours\n" +
            "5-Days\n" +
            "6-Years\n";

    static {
        FORMATS[0] = "dd/mm/yy - 01/12/34";
        FORMATS[1] = "/mm/yyyy - 1-4-2021 23:23";
        FORMATS[2] = "mmm d yy - Month 4 2021";
        FORMATS[3] = "dd mmm yyyy 00:00 - 09 Month 789 24:23";
        FORMATS[4] = "dd-mm-yy - 01-12-34";
    }

    public static void printAvailableFormats() {
        System.out.println("There are several available formats for entering date:");
        for (int i = 0; i < FORMATS.length; i++) {
            System.out.println(i + " " + FORMATS[i] + ";");
        }
        System.out.println();
    }

    public static ArrayList<MyDate> sortDates(ArrayList<MyDate> dates, String a) {
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

    public static void printOutputOfOperationInSelectedType(long milliseconds) {
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

    public static long selectMeasureAndConvertToMilliseconds() {
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

    public static boolean checkFormatOfTime(String input) {
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
                    for (int i = 0; i < splittime.length; i++) {
                        if (splittime[i].equals("")) {
                            splittime[i] = "0";
                        }
                    }
                    if (Integer.parseInt(splittime[0]) > 23 || Integer.parseInt(splittime[0]) < 0) {
                        isTime = false;
                    }
                    if (splittime.length > 2) {
                        if (Integer.parseInt(splittime[1]) > 59 || Integer.parseInt(splittime[1]) < 0) {
                            isTime = false;
                        }
                    }
                    if (splittime.length > 3) {
                        if (Integer.parseInt(splittime[2]) > 59 || Integer.parseInt(splittime[2]) < 0) {
                            isTime = false;
                        }
                    }
                    if (splittime.length > 4) {
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

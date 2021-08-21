package org.example.calendar.service;

import org.example.calendar.data.MyDate;
import org.example.calendar.exceptions.MyDateException;

import java.util.Locale;

public class DateServiceAmerican {
    private final String[] MONTHS_NAMES = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    private final String EXCEPTION_MESSAGE = "data";

    public String dateToString(MyDate date, String choice) {
        String dateString = "";
        switch (choice) {
            case "1":
                for (int i = 0; i < MONTHS_NAMES.length; ++i) {
                    if (i == (date.getMonth() - 1)) dateString += MONTHS_NAMES[i] + " ";
                }

                if (date.getDay() == 0) dateString += "1";
                dateString += date.getDay() + " ";

                dateString += date.getYear() + " ";

                dateString += changeZeroToDoubleZero(date.getHours()) + ":";
                dateString += changeZeroToDoubleZero(date.getMinutes()) + ":";
                dateString += changeZeroToDoubleZero(date.getSeconds()) + ":";
                dateString += changeZeroToTripleZero(date.getMilliseconds());

                return dateString;
            case "2":
                if (date.getDay() == 0) dateString += "1";
                dateString += date.getDay() + " ";

                for (int i = 0; i < MONTHS_NAMES.length; ++i) {
                    if (i == (date.getMonth() - 1)) dateString += MONTHS_NAMES[i] + " ";
                }
                dateString += date.getYear() + " ";

                dateString += changeZeroToDoubleZero(date.getHours()) + ":";
                dateString += changeZeroToDoubleZero(date.getMinutes()) + ":";
                dateString += changeZeroToDoubleZero(date.getSeconds()) + ":";
                dateString += changeZeroToTripleZero(date.getMilliseconds());

                return dateString;
            case "3":
                if (date.getDay() == 0) dateString += "1";
                dateString += date.getDay() + "/";
                for (int i = 0; i < MONTHS_NAMES.length; ++i) {
                    if (i == (date.getMonth() - 1)) dateString += date.getMonth() + "/";
                }

                dateString += date.getYear() + " ";

                dateString += changeZeroToDoubleZero(date.getHours()) + ":";
                dateString += changeZeroToDoubleZero(date.getMinutes()) + ":";
                dateString += changeZeroToDoubleZero(date.getSeconds()) + ":";
                dateString += changeZeroToTripleZero(date.getMilliseconds());

                return dateString;
            case "4":
                if (date.getDay() == 0) dateString += "1";
                for (int i = 0; i < MONTHS_NAMES.length; ++i) {
                    if (i == (date.getMonth() - 1)) dateString += date.getMonth() + "/";
                }
                dateString += date.getDay() + "/";
                dateString += date.getYear() + " ";

                dateString += changeZeroToDoubleZero(date.getHours()) + ":";
                dateString += changeZeroToDoubleZero(date.getMinutes()) + ":";
                dateString += changeZeroToDoubleZero(date.getSeconds()) + ":";
                dateString += changeZeroToTripleZero(date.getMilliseconds());

                return dateString;
            default:
                System.out.println("Incorrect index");
                return dateString;
        }
    }

    public MyDate stringToDate(String entered) throws MyDateException {
        MyDate date;
        if (entered.contains("/") || entered.contains("-")) date = formatWithDelimiter(entered);
        else date = formatWithOutDelimiter(entered);
        return date;

    }

    private MyDate formatWithDelimiter(String input) throws NumberFormatException {
        MyDate date = new MyDate();

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
        try {
            if (checkIfEntered(split[1])) {
                date.setDay(Integer.parseInt(split[1]));
            }
            if (checkIfEntered(split[0])) {
                date.setMonth(Integer.parseInt(split[0]));
            }
            if (checkIfEntered(split[2])) {
                date.setYear(Integer.parseInt(split[2]));
            }
            if (split.length > 3) setTime(date, split[3]);
            return date;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid entered " + EXCEPTION_MESSAGE);
        }

    }

    private MyDate formatWithOutDelimiter(String input) throws MyDateException {

        MyDate date = new MyDate();

        String[] split = input.split("[ ]");

        try {
            boolean endOfLoop = false;
            int indexOfMonth = -1;
            for (String s : split) {
                indexOfMonth++;
                for (int j = 0; j < MONTHS_NAMES.length; j++) {
                    if (s.toLowerCase(Locale.ROOT).equals(MONTHS_NAMES[j].toLowerCase(Locale.ROOT))) {
                        date.setMonth(j + 1);
                        endOfLoop = true;
                        break;
                    }
                }
                if (endOfLoop) break;
            }
            if (indexOfMonth == 1 && split.length >= 3) {
                date.setDay(Integer.parseInt(split[0]));
                date.setYear(Integer.parseInt(split[2]));
            } else if (indexOfMonth == 0) {
                date.setDay(Integer.parseInt(split[1]));
                date.setYear(Integer.parseInt(split[2]));
            } else throw new MyDateException("format");

            if (input.contains(":")) setTime(date, split[split.length - 1]);
            return date;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    private void setTime(MyDate date, String time) throws NumberFormatException {

        String[] split = time.split(":");
        try {
            for (int i = 0; i < split.length; ++i) {
                if (split[i].equals("")) {
                    split[i] = "0";
                }
                switch (i) {
                    case 0:
                        date.setHours(Integer.parseInt(split[0]));
                        break;
                    case 1:
                        date.setMinutes(Integer.parseInt(split[1]));
                        break;
                    case 2:
                        date.setSeconds(Integer.parseInt(split[2]));
                        break;
                    case 3:
                        date.setMilliseconds(Integer.parseInt(split[3]));
                        break;
                }
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid entered " + EXCEPTION_MESSAGE);
        }
    }

    private boolean checkIfEntered(String str) {
        return !str.equals("");
    }

    private String changeZeroToDoubleZero(int value) {
        if (value < 10) return "0" + value;
        return value + "";
    }

    private String changeZeroToTripleZero(int value) {
        if (value < 10) return "00" + value;
        if (value < 100) return "0" + value;
        return value + "";
    }
}

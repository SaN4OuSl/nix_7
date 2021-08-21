package org.example.calendar.utils;

import org.example.calendar.data.MyDate;

import java.text.DecimalFormat;

public class InverterFromMilliseconds {
    static private final double MILLISECONDS = 1;
    static private final double SECOND = MILLISECONDS / 1000;
    static private final double MINUTE = SECOND / 60;
    static private final double HOUR = MINUTE / 60;
    static private final double DAY = HOUR / 24;

    static private final long COMMON_YEAR = 31536000000L;
    static private final long LEAP_YEAR = 31622400000L;
    static private final int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    static public double toSeconds(long milliseconds) {
        return format(SECOND * milliseconds);
    }

    static public double toMinutes(long milliseconds) {
        return format(MINUTE * milliseconds);
    }

    static public double toHours(long milliseconds) {
        return HOUR * milliseconds;
    }

    static public double toDays(long milliseconds) {
        return format(DAY * milliseconds);
    }

    static public double toYears(long milliseconds) {
        double years = 0;
        int iterator = 1;
        while (true) {
            if (milliseconds == 0) return years;
            if (isLeapYear(iterator)) {
                if (milliseconds >= LEAP_YEAR) {
                    milliseconds -= LEAP_YEAR;
                    years++;
                } else {
                    years += toDays(milliseconds) / 366;
                    milliseconds = 0;
                }
            } else {
                if (milliseconds >= COMMON_YEAR) {
                    milliseconds -= COMMON_YEAR;
                    years++;
                } else {
                    years += toDays(milliseconds) / 365;
                    milliseconds = 0;
                }
            }
            iterator++;
        }
    }

    public static MyDate toDate(long milliseconds) {
        MyDate date = new MyDate();

        long amountOfMilliseconds = milliseconds;
        long amountOfSeconds = amountOfMilliseconds / 1000;
        long amountOfMinutes = amountOfSeconds / 60;
        long amountOfHours = amountOfMinutes / 60;
        long amountOfDays = amountOfHours / 24;

        int amountOfMonths = 0;
        int amountOfYears = 0;

        long tempAmountOfDays = amountOfDays;
        int currentMonth = 0;
        while (tempAmountOfDays > MONTH_DAYS[currentMonth]) {
            tempAmountOfDays -= MONTH_DAYS[currentMonth];
            if (currentMonth == 1) {
                if (isLeapYear(amountOfYears)) {
                    tempAmountOfDays -= 1;
                }
            }
            currentMonth += 1;
            if (currentMonth > 11) {
                amountOfYears += 1;
                currentMonth = 0;
            }
            amountOfMonths += 1;
        }

        date.setMilliseconds((int) (amountOfMilliseconds % 1000));
        date.setSeconds((int) (amountOfSeconds % 60));
        date.setMinutes((int) (amountOfMinutes % 60));
        date.setHours((int) (amountOfHours % 24));
        date.setDay((int) ((tempAmountOfDays) % MONTH_DAYS[currentMonth]) + 1);
        date.setMonth(((amountOfMonths) % 12) + 1);
        date.setYear(amountOfYears);
        return date;
    }

    private static double format(double result) {
        DecimalFormat doubleFormat = new DecimalFormat("#.######");
        String replacer = doubleFormat.format(result);
        replacer = replacer.replace(",", ".");
        result = Double.parseDouble(replacer);
        return result;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }
}

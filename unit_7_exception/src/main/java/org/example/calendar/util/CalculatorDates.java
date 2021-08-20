package org.example.calendar.util;

import org.example.calendar.data.MyDate;


public class CalculatorDates {
    public static long differenceBetweenDates(MyDate firstDate, MyDate secondDate) {
        long firstDateMilliseconds = ConverterToMilliseconds.dateIntoMilliseconds(firstDate);
        long secondDateMilliseconds = ConverterToMilliseconds.dateIntoMilliseconds(secondDate);
        if ((firstDate.getYear() % 4 == 0 && firstDate.getYear() % 100 != 0 || firstDate.getYear() % 400 == 0)
                || (secondDate.getYear() % 4 == 0 && secondDate.getYear() % 100 != 0 || secondDate.getYear() % 400 == 0)) {
            return Math.abs(firstDateMilliseconds - secondDateMilliseconds) + 86400000;
        } else return Math.abs(firstDateMilliseconds - secondDateMilliseconds);
    }

    public static MyDate addMillisecondsToDate(MyDate date, long added) {
        long dateMilliseconds = ConverterToMilliseconds.dateIntoMilliseconds(date);
        MyDate changed = InverterFromMilliseconds.toDate(dateMilliseconds + added);
        return changed;
    }

    public static MyDate subtractMillisecondsFromDate(MyDate date, long subtracted) {
        long dateMilliseconds = ConverterToMilliseconds.dateIntoMilliseconds(date);
        MyDate changed = InverterFromMilliseconds.toDate(dateMilliseconds - subtracted);
        return changed;
    }
}

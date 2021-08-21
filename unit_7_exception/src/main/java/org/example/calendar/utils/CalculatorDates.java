package org.example.calendar.utils;

import org.example.calendar.data.MyDate;


public class CalculatorDates {
    public static long differenceBetweenDates(MyDate firstDate, MyDate secondDate) {
        long firstDateMilliseconds = ConverterToMilliseconds.dateIntoMilliseconds(firstDate);
        long secondDateMilliseconds = ConverterToMilliseconds.dateIntoMilliseconds(secondDate);
        return Math.abs(firstDateMilliseconds - secondDateMilliseconds);
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

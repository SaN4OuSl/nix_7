package org.example.calendar.data;

public class MyDate {
    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public MyDate() {
        year = 0;
        month = 1;
        day = 1;
        hours = 0;
        minutes = 0;
        seconds = 0;
        milliseconds = 0;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                ", milliseconds=" + milliseconds +
                '}';
    }
}

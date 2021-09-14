package org.example.tasks.level1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateToString {

    private static final String[] BIGMONTH = new String[7];
    private static final String[] SMALLMONTH = new String[4];

    static {
        BIGMONTH[0] = "01";
        BIGMONTH[1] = "03";
        BIGMONTH[2] = "05";
        BIGMONTH[3] = "07";
        BIGMONTH[4] = "08";
        BIGMONTH[5] = "10";
        BIGMONTH[6] = "12";
    }

    static {
        SMALLMONTH[0] = "04";
        SMALLMONTH[1] = "06";
        SMALLMONTH[2] = "09";
        SMALLMONTH[3] = "11";
    }

    public static String[] checkDateAndRefractToString(List<String> dates) {
        List<String> datesWithoutDelimiters = new ArrayList<>();

        List<String> dateFormats = List.of(
                "(?<day>\\d{2})/(?<month>\\d{2})/(?<year>\\d{4})",
                "(?<year>\\d{4})/(?<month>\\d{2})/(?<day>\\d{2})",
                "(?<month>\\d{2})-(?<day>\\d{2})-(?<year>\\d{4})");

        for (String date : dates) {
            for (String dateFormat : dateFormats) {
                Matcher matcher = Pattern.compile(dateFormat).matcher(date);
                if (matcher.matches()) {
                    datesWithoutDelimiters.add(assemble(matcher.group("year"), matcher.group("month"), matcher.group("day")));
                    break;
                }
            }
        }
        return datesWithoutDelimiters.toArray(new String[0]);
    }

    private static String assemble(String year, String month, String day) {
        boolean whatMonth = false;
        if (Integer.parseInt(year) >= 0
                && Integer.parseInt(month) > 0 && Integer.parseInt(month) <= 12
                && Integer.parseInt(day) > 0 && Integer.parseInt(day) <= 31) {
            for (String s : BIGMONTH) {
                if (month.equals(s)) {
                    if (Integer.parseInt(day) <= 31)
                        whatMonth = true;
                }
            }
            for (String s : SMALLMONTH) {
                if (month.equals(s)) {
                    if (Integer.parseInt(day) <= 30)
                        whatMonth = true;
                }
            }
            if (month.equals("02")) {
                if (Integer.parseInt(day) == 29
                        && (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0 || Integer.parseInt(year) % 400 == 0))
                    whatMonth = true;
                if (Integer.parseInt(day) <= 28)
                    whatMonth = true;
            }
            if (whatMonth)
                return year + month + day;
        }
        return "";
    }
}

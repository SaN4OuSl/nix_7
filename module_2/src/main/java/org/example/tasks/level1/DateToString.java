package org.example.tasks.level1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateToString {

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
        if (Integer.parseInt(year) >= 0
                && Integer.parseInt(month) > 0 && Integer.parseInt(month) <= 12
                && Integer.parseInt(month) > 0 && Integer.parseInt(month) <= 31) {
            return year + month + day;
        } else
            return "";
    }
}

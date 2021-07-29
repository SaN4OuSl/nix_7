package org.example;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReverse {

    private StringReverse() {}

    public static String reverse(String src) {
        StringBuilder newsrc = new StringBuilder();
        String[] ArrayOfWords = src.split(" ");
        ArrayList<Integer> SpacePos = new ArrayList<>();

        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) == ' ') SpacePos.add(i);
        }

        StringBuilder word = new StringBuilder();
        for (int i = 0; i < ArrayOfWords.length; i++) {
            word.append(ArrayOfWords[i]);
            newsrc.append(word.reverse());
            word.setLength(0);
        }

        for (int i = 0; i < SpacePos.size(); i++) {
            newsrc.insert(SpacePos.get(i).intValue(), ' ');
        }
        return newsrc.toString();
    }

    public static String reverse(String src, String dest) {
        Pattern pattern = Pattern.compile(dest);
        Matcher matcher = pattern.matcher(src);
        String word = StringReverse.reverse(dest);
        return matcher.replaceAll(word);
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex) {
            firstIndex += lastIndex;
            lastIndex = firstIndex - lastIndex;
            firstIndex -= lastIndex;
        }
        String temp = src.substring(firstIndex, lastIndex + 1);
        temp = StringReverse.reverse(temp);

        return new StringBuilder(src).replace(firstIndex, lastIndex + 1, temp).toString();
    }

    public static String reverse(String src, char firstSymbol, char lastSymbol) {
        int firstIndex = -1, lastIndex = -1;
        String newsrc = src;

        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) == firstSymbol) {
                firstIndex = i;
            }
            if ((src.charAt(i) == lastSymbol) && (firstIndex != -1)) {
                lastIndex = i;
                newsrc = StringReverse.reverse(newsrc, firstIndex, lastIndex);
                firstIndex = -1;
                lastIndex = -1;
            }
        }
        return newsrc;
    }

    public static String reverse(String src, String firstString, String lastString) {
        Pattern pattern = Pattern.compile(firstString + "(.*?)" + lastString);
        Matcher matcher = pattern.matcher(src);
        int findPosition = 0;
        StringBuilder newsrc = new StringBuilder(src);
        String foundString;

        while (matcher.find(findPosition)) {
            foundString = StringReverse.reverse(matcher.group());
            newsrc.replace(matcher.start(), matcher.end(), foundString);
            findPosition = matcher.end();
        }
        return newsrc.toString();
    }
}

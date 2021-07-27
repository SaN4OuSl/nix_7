package org.exmaple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    
    public static void task2() {
        Scanner in = new Scanner(System.in);
        ArrayList<Character> arrLetters = new ArrayList<>();
        System.out.print("Введите строку: ");
        String strLet;
        strLet=in.nextLine();
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯёїієэЁЭЇІ]");
        Matcher matcher = pattern.matcher(strLet);
        while (matcher.find()) {
            arrLetters.add(matcher.group().charAt(0));
        }
        if (arrLetters.size() != 0) {
            Collections.sort(arrLetters);
            int countLetters = 1;
            char cur_letter = arrLetters.get(0);
            arrLetters.remove(0);
            System.out.println("Буквы в строке: ");
            for (char c: arrLetters) {
                if (cur_letter == c) {
                    countLetters += 1;
                } else {
                    System.out.println(cur_letter + " - " + countLetters);
                    cur_letter = c;
                    countLetters = 1;
                }
            }
            System.out.println(cur_letter + " - " + countLetters);
        } else {
            System.out.println("Нет ни одной буквы в строке");
        }
    }
}

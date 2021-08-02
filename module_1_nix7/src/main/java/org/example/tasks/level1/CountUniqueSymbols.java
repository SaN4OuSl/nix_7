package org.example.tasks.level1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CountUniqueSymbols {

    public static void run() {
        Scanner in = new Scanner(System.in);
        Set<Integer> resultSymbols = new HashSet<>();

        System.out.print("Enter the numbers through a whitespace: ");
        String enterString = in.nextLine();
        String[] stringSymbols = enterString.split(" ");
        for (String number : stringSymbols) {
            try {
                resultSymbols.add(Integer.parseInt(number));
            } catch (NumberFormatException e) {
                System.out.println(number + " isn`t a number!");
                return;
            }
        }

        System.out.println("Count of unique numbers is: " + resultSymbols.size());
    }
}

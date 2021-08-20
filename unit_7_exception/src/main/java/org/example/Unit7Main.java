package org.example;

import java.util.Scanner;

public class Unit7Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean endProgram = false;
        while (!endProgram) {
            System.out.println("1. If you are european\n" +
                    "2. If you are american\n" +
                    "Any another symbol for exit\n");
            String a = in.nextLine();
            switch (a) {
                case "1":
                    CalendarController.run();
                    break;
                case "2":
                    CalendarControllerAmerican.run();
                    break;
                default:
                    endProgram = true;
            }
        }
    }
}

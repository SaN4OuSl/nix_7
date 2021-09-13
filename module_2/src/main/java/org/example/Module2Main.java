package org.example;

import org.example.tasks.level1.FirstTask;
import org.example.tasks.level2.SecondTask;
import org.example.tasks.level3.TrirdTask;

import java.util.Scanner;

public class Module2Main {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        boolean ifLoop = true;
        while (ifLoop) {
            System.out.println("\nMenu:\n" +
                    "1. List of dates in a list without delimiters\n" +
                    "2. Find first unique name from the list of names\n" +
                    "3. Find the cheapest way between towns\n" +
                    "For exit enter any another symbol");

            String index = in.nextLine();
            switch (index) {
                case "1":
                    FirstTask.run();
                    break;
                case "2":
                    SecondTask.run();
                    break;
                case "3":
                    TrirdTask.run();
                    break;
                default:
                    ifLoop = false;
            }
        }
    }
}

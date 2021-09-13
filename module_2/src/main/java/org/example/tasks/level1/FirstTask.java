package org.example.tasks.level1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FirstTask {

    private static final String file = "files_for_module_2/dates.txt";
    private static final Scanner in = new Scanner(System.in);

    public static void run() {
        boolean ifLoop = true;

        System.out.println("First task");
        while (ifLoop) {
            System.out.println("1. Take dates from dates.txt file\n" +
                    "2. Enter dates by yourself\n" +
                    "For exit to main menu enter any another symbol");

            String index = in.nextLine();
            switch (index) {
                case "1":
                    Path path = Paths.get(file);
                    try {
                        List<String> dateWithDelimiters = Files.readAllLines(path);
                        dateWithDelimiters.forEach(System.out::println);

                        System.out.println("The list of formatted dates with correct first form:");
                        String[] formattedDates = DateToString.checkDateAndRefractToString(dateWithDelimiters);
                        for (String dateWithoutDelimiters : formattedDates) {
                            if (!dateWithoutDelimiters.equals("")) {
                                System.out.println(dateWithoutDelimiters);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        List<String> dateWithDelimiters;
                        System.out.println("Enter dates by separating them with the space bar (2020/04/05 04-05-2020 05/04/2020)");
                        dateWithDelimiters = List.of(in.nextLine().split(" "));
                        String[] formattedDates = DateToString.checkDateAndRefractToString(dateWithDelimiters);
                        for (String dateWithoutDelimiters : formattedDates) {
                            if (!dateWithoutDelimiters.equals("")) {
                                System.out.println(dateWithoutDelimiters);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    ifLoop = false;
            }
        }
    }
}

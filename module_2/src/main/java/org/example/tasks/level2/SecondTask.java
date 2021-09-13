package org.example.tasks.level2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SecondTask {

    private static final Scanner in = new Scanner(System.in);
    private static final String filepath = "files_for_module_2/names.txt";

    public static void run() {
        System.out.println("Second task");
        boolean isLoop = true;
        while (isLoop) {
            System.out.println("1. Take data from names.txt file\n" +
                    "2. Enter names by yourself\n" +
                    "For exit enter any another symbol");
            switch (in.nextLine()) {
                case "1":
                    testFromFile();
                    break;
                case "2":
                    inputByYourself();
                    break;
                default: {
                    isLoop = false;
                }
            }
        }
    }

    private static void inputByYourself() {
        System.out.println();
        System.out.println("Enter names by separating them with the space bar (Alex Iegor Vasya Lubov Andrey)");
        String[] names = in.nextLine().split(" ");
        ArrayList<String> listOfNames = new ArrayList<>(Arrays.asList(names));

        System.out.println("First unique name from list: ");
        System.out.println(findFirstUnique(listOfNames));
    }

    public static void testFromFile() {
        System.out.println();
        try {
            Path path = Paths.get(filepath);
            ArrayList<String> namesFromFile = (ArrayList<String>) Files.readAllLines(path);
            System.out.println("The list of names from file names.txt");
            namesFromFile.forEach(n -> System.out.print(n + " "));
            System.out.println();
            System.out.println("First unique name from list: ");
            System.out.println(findFirstUnique(namesFromFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String findFirstUnique(List<String> names) {
        LinkedHashMap<String, Integer> counts = new LinkedHashMap<>();

        for (String name : names) {
            counts.compute(name, (k, v) -> (v == null) ? 1 : v + 1);
        }
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return null;
    }
}

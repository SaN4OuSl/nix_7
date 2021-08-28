package org.example.controller;

import org.example.util.MathSet;

import java.util.Scanner;

public class MathSetController {
    private static final MathSet<Integer> numbers = new MathSet<>();
    private static final Scanner in = new Scanner(System.in);

    public static void run() {
        boolean endLoop = false;
        while (!endLoop) {
            System.out.println();
            System.out.println("Select the operation:\n" +
                    "0. Exit\n" +
                    "1. Add some new numbers\n" +
                    "2. Print whole list\n" +
                    "3. Print minimal value\n" +
                    "4. Print maximum value\n" +
                    "5. Print average of set\n" +
                    "6. Print median of set\n" +
                    "7. Sort set ascending\n" +
                    "8. Sort set descending\n" +
                    "9. Remove element from set\n" +
                    "10. Clear set\n" +
                    "11. Add elements to MathSet from other MathSet\n" +
                    "12. Sort set ascending between indexes\n" +
                    "13. Sort set descending between indexes\n" +
                    "14. Check the common elements of the two MathSets\n" +
                    "15. Truncate a set from index to index");
            System.out.print("The operation: ");
            String index = in.nextLine();
            switch (index) {
                case "0":
                    endLoop = true;
                    break;
                case "1":
                    fillSet(numbers);
                    break;
                case "2":
                    printWholeList();
                    break;
                case "3":
                    printMinValue();
                    break;
                case "4":
                    printMaxValue();
                    break;
                case "5":
                    printAverage();
                    break;
                case "6":
                    printMedian();
                    break;
                case "7":
                    sortAsc();
                    break;
                case "8":
                    sortDesc();
                    break;
                case "9":
                    removeElement();
                    break;
                case "10":
                    clearSet();
                    break;
                case "11":
                    joinSet();
                    break;
                case "12":
                    sortAscBetweenIndexes();
                    break;
                case "13":
                    sortDescBetweenIndexes();
                    break;
                case "14":
                    intersectionSet();
                    break;
                case "15":
                    cutSet();
                    break;
                default:
                    System.out.println("Incorrect index");
            }
        }
        in.close();
    }

    private static void fillSet(MathSet<Integer> mathSet) {
        boolean endLoop = false;
        while (!endLoop) {
            try {
                System.out.println("Enter the numbers. If you want end the " +
                        "entering, please enter 'e'");
                while (true) {
                    String str = in.nextLine();
                    if (str.equals("e")) {
                        endLoop = true;
                        break;
                    } else {
                        Integer entered = Integer.parseInt(str);
                        mathSet.add(entered);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid data");
            }
        }
    }

    private static void printWholeList() {
        if (numbers.size() != 0) {
            for (int i = 0; i < numbers.size(); i++) {
                System.out.println(i + ": " + numbers.get(i));
            }
        } else System.out.println("Set is empty");
    }

    private static void printMinValue() {
        System.out.println("Minimal: " + numbers.getMin());
    }

    private static void printMaxValue() {
        System.out.println("Maximal: " + numbers.getMax());
    }

    private static void printAverage() {
        System.out.println("Average: " + numbers.getAverage());
    }

    private static void printMedian() {
        numbers.sortAsc();
        System.out.println("Median: " + numbers.getMedian());
    }

    private static void sortDesc() {
        System.out.println("Before sorting:");
        printWholeList();
        numbers.sortDesc();
        System.out.println("After sorting:");
        printWholeList();
    }

    private static void sortDescBetweenIndexes() {
        System.out.println("Before sorting:");
        printWholeList();
        System.out.println("Enter the first index");
        int first = in.nextInt();
        System.out.println("Enter the second index");
        int second = in.nextInt();
        numbers.sortDesc(first, second);
        System.out.println("After sorting:");
        printWholeList();
    }

    private static void sortAsc() {
        System.out.println("Before sorting:");
        printWholeList();
        numbers.sortAsc();
        System.out.println("After sorting:");
        printWholeList();
    }

    private static void sortAscBetweenIndexes() {
        System.out.println("Before sorting:");
        printWholeList();
        System.out.println("Enter the first index");
        int first = in.nextInt();
        System.out.println("Enter the second index");
        int second = in.nextInt();
        numbers.sortAsc(first, second);
        System.out.println("After sorting:");
        printWholeList();
    }

    private static void removeElement() {
        printWholeList();
        while (true) {
            try {
                System.out.println("Enter the number you want to delete");
                String entered = in.nextLine();
                numbers.remove(Integer.parseInt(entered));
                break;
            } catch (NumberFormatException e) {
                System.out.println("You entered invalid data!");
            }
        }
        printWholeList();
    }

    private static void clearSet() {
        numbers.clear();
        printWholeList();
    }

    private static void joinSet() {
        System.out.println("Fill new MathSet");
        MathSet<Integer> buffer = new MathSet<>();
        fillSet(buffer);
        numbers.join(new MathSet(buffer));
        printWholeList();
    }

    private static void intersectionSet() {
        System.out.println("Fill new MathSet");
        MathSet<Integer> buffer = new MathSet<>();
        fillSet(buffer);
        numbers.intersection(new MathSet(buffer));
        printWholeList();
    }

    private static void cutSet() {
        while (true) {
            try {
                printWholeList();
                System.out.println("You need to set a range of cutting.");
                System.out.print("Enter the first index from " + 0 + "-" + (numbers.size() - 1) + ": ");
                String firstIndex = in.nextLine();
                System.out.print("Enter the second index: ");
                String secondIndex = in.nextLine();
                System.out.println("Cutted set:");
                MathSet cutted = numbers.cut(Integer.parseInt(firstIndex), Integer.parseInt(secondIndex));
                for (int i = 0; i < cutted.size(); i++) {
                    System.out.println(cutted.get(i));
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("You entered incorrect value!");
            }
        }
    }
}

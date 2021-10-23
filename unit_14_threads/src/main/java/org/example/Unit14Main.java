package org.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Unit14Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("First task");
        PrintHelloFromThread printHelloFromThread = new PrintHelloFromThread();
        printHelloFromThread.startMethod();

        System.out.println("\nSecond task");
        List<Integer> listOfNumbers = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).collect(Collectors.toList());

        PrimeNumberChecker firstPartOfList = new PrimeNumberChecker(listOfNumbers.subList(0, listOfNumbers.size() / 2));
        PrimeNumberChecker secondPartOfList = new PrimeNumberChecker(listOfNumbers.subList(listOfNumbers.size() / 2, listOfNumbers.size()));

        Thread firstThread = new Thread(firstPartOfList);
        Thread secondThread = new Thread(secondPartOfList);

        firstThread.start();
        firstThread.join();

        secondThread.start();
        secondThread.join();

        System.out.println("First thread: " + firstPartOfList.getCountOfPrimeNumbers());
        System.out.println("Second thread: " + secondPartOfList.getCountOfPrimeNumbers());
        int sum = firstPartOfList.getCountOfPrimeNumbers() + secondPartOfList.getCountOfPrimeNumbers();
        System.out.println("All: " + sum);
    }
}

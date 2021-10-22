package org.example;

import java.util.List;

public class PrimeNumberChecker extends Thread {

    private final List<Integer> listOfNumbers;
    private int countOfPrimeNumbers = 0;

    public PrimeNumberChecker(List<Integer> numbers) {
        this.listOfNumbers = numbers;
    }

    @Override
    public void run() {
        for (int number : listOfNumbers) {
            if (checkIsItPrimeNumber(number)) {
                countOfPrimeNumbers++;
            }
        }
    }

    public int getCountOfPrimeNumbers(){
        return countOfPrimeNumbers;
    }

    private boolean checkIsItPrimeNumber(Integer number) {
        if (number < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}

package org.exmaple;


import java.util.Scanner;

public class Unit3Main {
    public static void main(String[] args) {
        boolean continueCheck=true;
        int NumberOfChoice;
        while(continueCheck) {
            System.out.println("0. Выход из программы");
            System.out.println("1. Задача 1 про сумму чисел из строки");
            System.out.println("2. Задача 2 про количество одинаковых букв в строке");
            System.out.println("3. Задача 3 про конец урока");
            System.out.print("Введите в консоль пункт который хотите запустить:");
            Scanner NumberOfChoiceScan = new Scanner(System.in);
            NumberOfChoice=NumberOfChoiceScan.nextInt();
            switch(NumberOfChoice) {
                case 0:
                    NumberOfChoiceScan.close();
                    continueCheck=false;
                    break;
                case 1:
                    Task1.task1();
                    break;
                case 2:
                    Task2.task2();
                    break;
                case 3:
                    Task3.task3();
                    break;
                default:
                    System.out.println("Не стучите головой об клавиатуру, выберите вариант из списка!\n");
            }
        }
    }
}

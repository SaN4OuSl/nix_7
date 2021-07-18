package org.exmaple;

import java.util.Scanner;
import java.util.TreeMap;

public class Unit3Main {
    public static void main(String[] args) {
        while(true) {
            System.out.println("0. Выход из программы");
            System.out.println("1. Задача 1 про сумму чисел из строки");
            System.out.println("2. Задача 2 про количество одинаковых букв в строке");
            System.out.println("3. Задача 3 про конец урока");
            System.out.print("Введите в консоль пункт который хотите запустить:");
            int NumberOfChoice;
            Scanner NumberOfChoiceScan = new Scanner(System.in);
            NumberOfChoice=NumberOfChoiceScan.nextInt();
            switch(NumberOfChoice) {
                case 0:
                    NumberOfChoiceScan.close();
                    System.exit(0);
                    break;

                case 1:
                    Scanner StrScan = new Scanner(System.in);
                    System.out.print("Введите строку: ");
                    String str;
                    str=StrScan.nextLine();
                    String NumberOnlyString= str.replaceAll("[^0-9]", "");
                    if(NumberOnlyString.length()!=0){
                        int OnlyNumbers = Integer.parseInt(NumberOnlyString);
                        int sum=0;
                        while(OnlyNumbers != 0){
                            //Суммирование цифр числа
                            sum += (OnlyNumbers % 10);
                            OnlyNumbers/=10;
                        }
                        System.out.print("Сумма всех цифр в строке: ");
                        System.out.println(sum);
                        System.out.print("\n");
                    }else{
                        System.out.println("В строке не было чисел\n");
                    }
                    break;

                case 2:
                    TreeMap<Character, Integer> LettersList = new TreeMap<>();
                    Scanner StrLetterScan = new Scanner(System.in);
                    System.out.print("Введите строку: ");
                    String strLet;
                    strLet=StrLetterScan.nextLine();
                    String OnlyLetters = strLet.replaceAll("[^A-Za-zА-Яа-я]", "");
                    int CountLetters = 0;
                    for (int i = 0; i < OnlyLetters.length(); i++) {
                        if (LettersList.containsKey(OnlyLetters.charAt(i))) {
                            CountLetters = LettersList.get(OnlyLetters.charAt(i)) + 1;
                            LettersList.put(OnlyLetters.charAt(i), CountLetters);
                        } else {
                            LettersList.put(OnlyLetters.charAt(i), 1);
                        }
                    }
                    System.out.println(LettersList);
                    System.out.print("\n");
                    break;

                case 3:
                    Scanner NumOfLessonScan = new Scanner(System.in);
                    int NumberOfLesson, hour;
                    int TimeOfLesson = 45, TimeOfBigBrake = 15, TimeOfSmallBrake = 5;
                    System.out.print("Введите номер урока: ");
                    NumberOfLesson = NumOfLessonScan.nextInt();
                    hour = NumberOfLesson * TimeOfLesson + NumberOfLesson / 2 * TimeOfSmallBrake + (NumberOfLesson - 1) / 2 * TimeOfBigBrake;
                    System.out.printf("Этот урок заканчивается в: ");
                    System.out.printf("%s:%d", hour / 60 + 9, hour % 60);
                    System.out.println("\n");
                    break;
            }
        }
    }
}

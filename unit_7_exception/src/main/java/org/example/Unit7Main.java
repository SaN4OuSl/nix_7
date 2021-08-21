package org.example;

import org.example.calendar.controllers.CalendarController;
import org.example.calendar.controllers.CalendarControllerAmerican;

import java.util.Scanner;

public class Unit7Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean endProgram = false;
        System.out.println("ИНСТРУКЦИЯ:\n" +
                "1. Выберите формат даты европейская(день-месяц-год), амереканская(месяц-день-год)\n" +
                "2. После выбора откроется меню с списком возможных форматов даты(в списке представлены не все варианты), после выбора действия (1-3) введите дату в формате который вам нужен,\n" +
                "программа самостоятельно определит в каком формате вы ввели дату\n");
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

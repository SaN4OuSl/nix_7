package org.exmaple;

import java.util.Scanner;

public class Task3 {

    public static void task3() {
        Scanner NumOfLessonScan = new Scanner(System.in);
        int numberOfLesson, hour;
        int timeOfLesson = 45, timeOfBigBrake = 15, timeOfSmallBrake = 5;
        System.out.print("Введите номер урока: ");
        numberOfLesson = NumOfLessonScan.nextInt();
        hour = numberOfLesson * timeOfLesson + numberOfLesson / 2 * timeOfSmallBrake + (numberOfLesson - 1) / 2 * timeOfBigBrake;
        System.out.printf("Этот урок заканчивается в: ");
        System.out.println(hour / 60 + 9+":"+hour % 60);
    }
}

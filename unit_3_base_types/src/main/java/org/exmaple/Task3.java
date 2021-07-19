package org.exmaple;

import java.util.Scanner;

public class Task3 {
    public static void task3(){
        Scanner NumOfLessonScan = new Scanner(System.in);
        int NumberOfLesson, hour;
        int TimeOfLesson = 45, TimeOfBigBrake = 15, TimeOfSmallBrake = 5;
        System.out.print("Введите номер урока: ");
        NumberOfLesson = NumOfLessonScan.nextInt();
        hour = NumberOfLesson * TimeOfLesson + NumberOfLesson / 2 * TimeOfSmallBrake + (NumberOfLesson - 1) / 2 * TimeOfBigBrake;
        System.out.printf("Этот урок заканчивается в: ");
        System.out.println(hour / 60 + 9+":"+hour % 60);
    }
}

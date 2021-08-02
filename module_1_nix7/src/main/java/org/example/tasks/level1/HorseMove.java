package org.example.tasks.level1;

import java.util.Scanner;

public class HorseMove {

    public static void run() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the start horse position <x y>: ");
        int startX = in.nextInt();
        int startY = in.nextInt();
        System.out.print("Enter the destination horse position <x y>: ");
        int destX = in.nextInt();
        int destY = in.nextInt();

        if (((Math.abs(startX - destX) == 1) && (Math.abs(startY - destY) == 2)) || (((Math.abs(startX - destX) == 2) && (Math.abs(startY - destY) == 1)))) {
            System.out.println("Horse can move to this cell");
        } else {
            System.out.println("Horse can`t move to this cell");
        }
    }
}

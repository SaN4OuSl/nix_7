package org.example.tasks.level1;

import java.util.Scanner;

public class TriangleSquare {

    public static void run() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the first point coordinates (x y): ");
        int aX = in.nextInt();
        int aY = in.nextInt();
        System.out.print("Enter the second point coordinates (x y): ");
        int bX = in.nextInt();
        int bY = in.nextInt();
        System.out.print("Enter the third point coordinates (x y): ");
        int cX = in.nextInt();
        int cY = in.nextInt();
        if ((aX - cX) * (bY - cY) != (bX - cX) * (aY - cY)) {
            double res = 0.5 * Math.abs((aX - cX) * (bY - cY) - (aY - cY) * (bX - cX));
            System.out.println("Square of triangle is: " + res);
        } else {
            System.out.println("It is a straight line!");
        }
    }
}

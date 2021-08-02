package org.example;

import org.example.tasks.level1.CountUniqueSymbols;
import org.example.tasks.level1.HorseMove;
import org.example.tasks.level1.TriangleSquare;
import org.example.tasks.level2.BracketsCombination;
import org.example.tasks.level3.LaunchGameOfLive;

import java.util.Scanner;

public class Module1Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        boolean continueEnter = true;
        byte userChoice;
        while (continueEnter) {
            System.out.println("===============================================");
            System.out.println("Select the task from this list: ");
            System.out.println("0. Exit");
            System.out.println("Level 1:");
            System.out.println("1. Count unique numbers in a string");
            System.out.println("2. Does a checker horse able to move to users position on a desk");
            System.out.println("3. Count the triangle square by coordinates");
            System.out.println("Level 2:");
            System.out.println("4. Check the correct placement of brackets in a string");
            System.out.println("Level 3:");
            System.out.println("5. Start the game of life");

            userChoice = in.nextByte();
            in.nextLine();
            switch (userChoice) {
                case 0:
                    continueEnter = false;
                    break;
                case 1:
                    CountUniqueSymbols.run();
                    break;
                case 2:
                    HorseMove.run();
                    break;
                case 3:
                    TriangleSquare.run();
                    break;
                case 4:
                    BracketsCombination.run();
                    break;
                case 5:
                    LaunchGameOfLive.run();
                    break;
                default:
                    System.out.println("Enter correct number!");
            }
        }
    }
}

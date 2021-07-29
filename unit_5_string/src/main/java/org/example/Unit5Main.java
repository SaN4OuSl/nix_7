package org.example;

import org.example.FileToRun.*;
import java.util.Scanner;

public class Unit5Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean continueEnter = true;
        byte userChoice;
        while (continueEnter) {

            System.out.println("================================================");
            System.out.println("Select the operation from this list: ");
            System.out.println("0. Exit");
            System.out.println("1. Reverse string");
            System.out.println("2. Reverse substring in string");
            System.out.println("3. Reverse substring in string which limited by indexes");
            System.out.println("4. Reverse substring in string which limited by symbols");
            System.out.println("5. Reverse substring in string which limited by strings");

            userChoice = in.nextByte();
            in.nextLine();
            switch (userChoice) {
                case 0:
                    continueEnter = false;
                    break;
                case 1:
                    JustRevers.OnlyReverse();
                    break;
                case 2:
                    ReversSubString.ReverSubString();
                    break;
                case 3:
                    ReverseByIndex.ReverseFromIndexToIndex();
                    break;
                case 4:
                    ReverseBySymbols.ReversFromSymbolToSymbol();
                    break;
                case 5:
                    ReverseByStrings.ReverseFromStringToString();
                    break;
                default:
                    System.out.println("Enter correct number");
            }
        }
    }
}

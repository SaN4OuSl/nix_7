package org.example.FileToRun;

import org.example.StringReverse;

import java.util.Scanner;

public class ReverseByStrings {

    public static void ReverseFromStringToString() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String str = in.nextLine();

        System.out.print("Enter the start string: ");
        String firstStr = in.nextLine();

        System.out.print("Enter the end string: ");
        String lastStr = in.nextLine();

        System.out.println("Result string: " + StringReverse.reverse(str, firstStr, lastStr));
    }
}

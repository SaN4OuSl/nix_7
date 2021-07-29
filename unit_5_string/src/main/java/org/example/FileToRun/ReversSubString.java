package org.example.FileToRun;

import org.example.StringReverse;

import java.util.Scanner;

public class ReversSubString {

    public static void ReverSubString(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String str = in.nextLine();

        System.out.print("Enter the substring which you want to reverse: ");
        String dest = in.nextLine();

        System.out.println("Result string: " + StringReverse.reverse(str, dest));
    }
}

package org.example.FileToRun;

import org.example.StringReverse;

import java.util.Scanner;

public class JustRevers {

    public static void OnlyReverse() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String str = in.nextLine();
        System.out.println("Result string: " + StringReverse.reverse(str));
    }
}

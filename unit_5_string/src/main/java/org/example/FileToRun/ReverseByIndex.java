package org.example.FileToRun;

import org.example.StringReverse;

import java.util.Scanner;

public class ReverseByIndex {

    public static void ReverseFromIndexToIndex() {
        Scanner in = new Scanner(System.in);
        boolean checkIndex = false;
        System.out.print("Enter the string: ");
        String str = in.nextLine();
        do {
            System.out.print("Enter the start index: ");
            int firstIndex = in.nextInt();
            System.out.print("Enter the end index: ");
            int lastIndex = in.nextInt();
            if (lastIndex > str.length() || firstIndex < 0) {
                checkIndex = true;
                System.out.println("Incorrect index");
            } else {
                checkIndex = false;
                System.out.println("Result string: " + StringReverse.reverse(str, firstIndex, lastIndex));
            }
        } while (checkIndex);
    }
}

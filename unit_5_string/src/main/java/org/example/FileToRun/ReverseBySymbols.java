package org.example.FileToRun;

import org.example.StringReverse;

import java.util.Scanner;

public class ReverseBySymbols {

    public static void ReversFromSymbolToSymbol() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String str = in.nextLine();
        System.out.print("Enter the start symbol: ");
        char firstSymb = in.next().charAt(0);
        System.out.print("Enter the end symbol: ");
        char lastSymb = in.next().charAt(0);
        System.out.println("Result string: " + StringReverse.reverse(str, firstSymb, lastSymb));
    }
}

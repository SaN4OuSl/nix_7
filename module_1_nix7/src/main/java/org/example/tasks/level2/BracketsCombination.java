package org.example.tasks.level2;

import java.util.Scanner;
import java.util.Stack;

public class BracketsCombination {
    public static void run() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the string of brackets: ");
        String strBrackets = in.nextLine();
        Stack<Character> stackBrackets = new Stack<>();

        for (int i = 0; i < strBrackets.length(); i++) {
            int curStackSize = stackBrackets.size();
            if ((strBrackets.charAt(i) == '(') || (strBrackets.charAt(i) == '[') || (strBrackets.charAt(i) == '{')) {
                stackBrackets.push(strBrackets.charAt(i));
            } else if ((!stackBrackets.isEmpty())
                    && ((strBrackets.charAt(i) == ')' && (stackBrackets.peek() == '('))
                    || (strBrackets.charAt(i) == ']' && (stackBrackets.peek() == '['))
                    || (strBrackets.charAt(i) == '}' && (stackBrackets.peek() == '{')))) {
                stackBrackets.pop();
            } else if (curStackSize != stackBrackets.size()) {
                break;
            }
        }

        if (stackBrackets.isEmpty()) {
            System.out.println("Brackets combination is correct!");
        } else {
            System.out.println("Brackets combination is wrong!");
        }
    }
}

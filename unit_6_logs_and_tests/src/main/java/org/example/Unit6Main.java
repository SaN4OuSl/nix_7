package org.example;

import org.example.controller.AssociationController;
import org.example.controller.AuthorController;
import org.example.controller.BookController;

import java.util.Scanner;

public class Unit6Main {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean endApp = false;
        while (endApp == false) {
            System.out.println("------------Book library------------");
            System.out.println("What do you want? \n" +
                    "0. Exit\n" +
                    "1. Books\n" +
                    "2. Authors\n" +
                    "3. Make a association between a book and an author");
            String index = in.nextLine();
            switch (index) {
                case "0":
                    endApp = true;
                    break;
                case "1":
                    BookController.run();
                    break;
                case "2":
                    AuthorController.run();
                    break;
                case "3":
                    AssociationController.run();
                    break;
                default:
                    System.out.println("Incorrect index!");
            }
        }
    }
}

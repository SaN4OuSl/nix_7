package org.example.controller;

import org.example.exception.EmptyLibraryException;
import org.example.service.AssociateService;

import java.util.Scanner;

public class AssociationController {
    private static final Scanner in = new Scanner(System.in);
    private static final AssociateService SERVICE = new AssociateService();

    public static void run() {
        System.out.println();
        boolean endController = false;
        while (endController == false) {
            System.out.print("0. Exit\n" +
                    "1. Book with authors\n" +
                    "2. Author with books\n");
            String entered = in.nextLine();
            switch (entered) {
                case "0":
                    endController = true;
                    break;
                case "1":
                    bookWithAuthors();
                    break;
                case "2":
                    authorWithBooks();
                    break;
                default:
                    System.out.println("Incorrect symbol!");

            }
        }
    }

    private static void bookWithAuthors() {
        try {
            SERVICE.allBooks();
            System.out.print("Enter book's id: ");
            String bookId = in.nextLine();
            int id = Integer.parseInt(bookId);
            SERVICE.allAuthors();
            System.out.print("Enter authors' ids(divide id with ','): ");
            String authorId = in.nextLine();
            SERVICE.booksWithAuthors(id, authorId);
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id!");
        }
    }

    private static void authorWithBooks() {
        try {
            SERVICE.allAuthors();
            System.out.print("Enter author's id: ");
            String authorId = in.nextLine();
            int id = Integer.parseInt(authorId);
            SERVICE.allBooks();
            System.out.print("Enter books' ids(divide id with ','): ");
            String bookId = in.nextLine();
            SERVICE.authorWithBooks(id, bookId);
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id!");
        }
    }
}

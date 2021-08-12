package org.example.controller;

import org.example.entity.Author;
import org.example.exception.EmptyLibraryException;
import org.example.exception.NonexistentIdException;
import org.example.service.AuthorService;

import java.util.Scanner;

public class AuthorController {
    private static final Scanner in = new Scanner(System.in);
    private static final AuthorService SERVICE = new AuthorService();

    public static void run() {
        System.out.println();
        boolean endController = false;
        while (endController == false) {
            System.out.print("0. Exit\n" +
                    "1. Create author\n" +
                    "2. Delete author by id\n" +
                    "3. All authors\n" +
                    "4. Update author\n" +
                    "5. Author by id\n" +
                    "6. Author's books\n");
            String entered = in.nextLine();
            switch (entered) {
                case "0":
                    endController = true;
                    break;
                case "1":
                    create();
                    break;
                case "2":
                    delete();
                    break;
                case "3":
                    allAuthors();
                    break;
                case "4":
                    update();
                    break;
                case "5":
                    readById();
                    break;
                case "6":
                    allBooksByAuthor();
                    break;
                default:
                    System.out.println("Incorrect index!");
            }
        }
    }

    private static void create() {
        Author author = new Author();
        System.out.print("Enter author's first name: ");
        String firstName = in.nextLine();
        author.setFirstName(firstName);

        System.out.print("Enter author's second name: ");
        String secondName = in.nextLine();
        author.setSecondName(secondName);

        SERVICE.createAuthor(author);
    }

    private static void readById() {
        try {
            SERVICE.allAuthors();
            System.out.print("Enter author's id: ");
            String authorId = in.nextLine();
            int id = Integer.parseInt(authorId);
            System.out.println(SERVICE.readAuthorById(id));
        } catch (NumberFormatException e) {
            System.out.println("Invalid id!");
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        } catch (NonexistentIdException e) {
            System.out.println(e);
        }

    }

    private static void update() {
        try {
            SERVICE.allAuthors();
            Author author = new Author();
            System.out.print("Enter author's id: ");
            String bookId = in.nextLine();
            int id = Integer.parseInt(bookId);
            author.setId(id);
            if (SERVICE.containsAuthor(id)) {
                System.out.print("Enter author's first name: ");
                String firstName = in.nextLine();
                author.setFirstName(firstName);
                System.out.print("Enter author's second name: ");
                String secondName = in.nextLine();
                author.setSecondName(secondName);
                SERVICE.updateAuthor(author);
            } else {
                System.out.println("Nonexistent id!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid id!");
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }
    }

    private static void delete() {
        try {
            SERVICE.allAuthors();
            System.out.println("Enter author's id: ");
            String authorId = in.nextLine();
            int id = Integer.parseInt(authorId);
            SERVICE.deleteAuthorById(id);
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id!");
        }

    }

    private static void allBooksByAuthor() {
        try {
            SERVICE.allAuthors();
            System.out.print("Enter author's id: ");
            String authorId = in.nextLine();
            int id = Integer.parseInt(authorId);
            if (SERVICE.containsAuthor(id)) {
                SERVICE.allBooksByAuthorId(id);
            } else {
                System.out.println("Nonexistent id!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid id!");
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }
    }

    private static void allAuthors() {
        try {
            SERVICE.allAuthors();
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }
    }

}

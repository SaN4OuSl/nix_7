package org.example.controller;

import org.example.entity.Book;
import org.example.exception.EmptyLibraryException;
import org.example.exception.NonexistentIdException;
import org.example.service.BookService;

import java.util.Scanner;

public class BookController {
    private static final Scanner in = new Scanner(System.in);
    private static final BookService SERVICE = new BookService();

    public static void run() {
        System.out.println();
        boolean endController = false;
        while (endController == false) {
            System.out.print("0. Exit\n" +
                    "1. Create book \n" +
                    "2. Delete book by id\n" +
                    "3. All books\n" +
                    "4. Update book\n" +
                    "5. Book by id\n" +
                    "6. Book's authors\n");
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
                    allBooks();
                    break;
                case "4":
                    update();
                    break;
                case "5":
                    readById();
                    break;
                case "6":
                    allAuthorsByBook();
                    break;
                default:
                    System.out.println("Incorrect index!");
            }
        }
    }

    private static void create() {
        Book book = new Book();
        System.out.print("Enter book's name: ");
        String name = in.nextLine();
        book.setName(name);
        SERVICE.createBook(book);
    }

    private static void readById() {
        try {
            SERVICE.allBooks();
            System.out.print("Enter book's id: ");
            String bookId = in.nextLine();
            int id = Integer.parseInt(bookId);
            System.out.println(SERVICE.readBookById(id));
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
            SERVICE.allBooks();
            Book book = new Book();
            System.out.print("Enter book's id: ");
            String bookId = in.nextLine();
            int id = Integer.parseInt(bookId);
            book.setId(id);
            if (SERVICE.containsBook(id)) {
                System.out.print("Enter new book's name: ");
                String bookName = in.nextLine();
                book.setName(bookName);
                SERVICE.updateBook(book);
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
            SERVICE.allBooks();
            System.out.println("Enter book's id: ");
            String authorId = in.nextLine();
            int id = Integer.parseInt(authorId);
            SERVICE.deleteBookById(id);
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id!");
        }

    }

    private static void allAuthorsByBook() {
        try {
            SERVICE.allBooks();
            System.out.print("Enter book's id: ");
            String bookId = in.nextLine();
            int id = Integer.parseInt(bookId);
            if (SERVICE.containsBook(id)) {
                SERVICE.allAuthorsByBookId(id);
            } else {
                System.out.println("Nonexistent id!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid id!");
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }
    }

    private static void allBooks() {
        try {
            SERVICE.allBooks();
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }
    }
}

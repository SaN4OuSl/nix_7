package org.example.controller;

import org.example.entity.Author;
import org.example.entity.Book;
import org.example.service.AuthorService;
import org.example.service.BookService;
import org.example.service.impl.AssociateServiceImpl;
import org.example.service.impl.AuthorServiceImpl;
import org.example.service.impl.BookServiceImpl;

import java.util.Scanner;

public class AssociattionController {

    private static final Scanner in = new Scanner(System.in);
    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final AssociateServiceImpl associateService = new AssociateServiceImpl();

    public static void addBook() {

        Integer authorId;
        String bookId = null;
        try {
            System.out.print("Enter author id: ");
            authorId = Integer.parseInt(in.nextLine());
            BookController.getInstance().readAllBooks();
            System.out.print("Enter books' ids(divide id with ','): ");
            bookId = in.nextLine();
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }

        Author author = authorService.read(authorId);
        if (author == null) {
            System.err.println("No author with current id!");
            return;
        }
        bookId = bookId.replaceAll("\\s", "");
        String[] ids = bookId.split(",");
        Book book = null;
        for (String s : ids) {
            book = bookService.read(Integer.parseInt(s));
            if (book == null) {
                System.err.println("No book with current id!");
                return;
            }
            associateService.addBookToAuthor(author, book);
        }
        System.out.println("Books was added to author");
    }

    public static void addAuthor() {

        Integer bookId;
        String authorIds = null;
        try {
            System.out.print("Enter book id: ");
            bookId = Integer.parseInt(in.nextLine());
            AuthorController.getInstance().readAllAuthors();
            System.out.print("Enter authors' ids(divide id with ','): ");
            authorIds = in.nextLine();
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }

        Book book = bookService.read(bookId);
        if (book == null) {
            System.err.println("No book with current id!");
            return;
        }
        authorIds = authorIds.replaceAll("\\s", "");
        String[] ids = authorIds.split(",");
        Author authors = null;
        for (String s : ids) {
            authors = authorService.read(Integer.parseInt(s));
            if (authors == null) {
                System.err.println("No authors with current id!");
                return;
            }
            associateService.addAuthorToBook(book, authors);
        }
        System.out.println("Authors was added to book");
    }
}

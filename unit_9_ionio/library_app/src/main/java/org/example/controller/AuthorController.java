package org.example.controller;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.example.entity.AbstractEntity;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.service.AuthorService;
import org.example.service.impl.AuthorServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AuthorController {

    private static AuthorController instance = null;
    private final Scanner in = new Scanner(System.in);
    private final AuthorService authorService = new AuthorServiceImpl();

    private AuthorController() {
    }

    public static AuthorController getInstance() {
        if (instance == null) {
            instance = new AuthorController();
        }
        return instance;
    }

    public void createAuthor() {
        Author author = new Author();

        System.out.print("Enter name: ");
        author.setName(in.nextLine());
        System.out.print("Enter surname: ");
        author.setSurname(in.nextLine());
        System.out.print("Enter birth place: ");
        author.setBirthPlace(in.nextLine());
        authorService.create(author);

        System.out.println("Author id: " + author.getId().toString());
        System.out.println("Author created!");
    }

    public void readAllAuthors() {
        List<Author> authors = new ArrayList<>(authorService.readAll());
        authors.sort(Comparator.comparing(AbstractEntity::getId));

        AsciiTable authorTable = new AsciiTable();
        authorTable.addRule();
        authorTable.addRow(null, null, null, null, "AUTHORS").setTextAlignment(TextAlignment.CENTER);
        authorTable.addRule();
        authorTable.addRow("ID", "NAME", "SURNAME", "BIRTH_PLACE", "BOOKS_ID");
        authorTable.addRule();
        for (Author author : authors) {
            authorTable.addRow(author.getId(), author.getName(), author.getSurname(), author.getBirthPlace(), author.getBooks().toString());
            authorTable.addRule();
        }
        System.out.println(authorTable.render());
    }

    public void readAuthorBooks() {

        Integer id;
        System.out.print("Enter author id: ");
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }

        Author author = authorService.read(id);
        if (author == null) {
            System.err.println("No author with current id!");
            return;
        }
        List<Book> books = new ArrayList<>(authorService.readBooks(author));
        books.sort(Comparator.comparing(AbstractEntity::getId));

        AsciiTable bookTable = new AsciiTable();
        bookTable.addRule();
        bookTable.addRow(null, null, author.getName() + " " + author.getSurname() + " books").setTextAlignment(TextAlignment.CENTER);
        bookTable.addRule();
        bookTable.addRow("ID", "NAME", "PAGES");
        bookTable.addRule();
        for (Book book : books) {
            bookTable.addRow(book.getId(), book.getName(), book.getPages());
            bookTable.addRule();
        }
        System.out.println(bookTable.render());
    }

    public void updateAuthor() {

        Integer id;
        System.out.print("Enter author id: ");
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }

        Author author = authorService.read(id);
        if (author == null) {
            System.err.println("No author with current id!");
            return;
        }

        System.out.println(author);

        System.out.print("Enter new name: ");
        author.setName(in.nextLine());
        System.out.print("Enter new surname: ");
        author.setSurname(in.nextLine());
        System.out.print("Enter new birth place: ");
        author.setBirthPlace(in.nextLine());
        authorService.update(author);

        System.out.println("Author updated!");
    }

    public void deleteAuthor() {

        Integer id;
        System.out.print("Enter author id: ");
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }

        Author author = authorService.read(id);
        if (author == null) {
            System.err.println("No author with current id!");
            return;
        }
        authorService.delete(author);
        System.out.println("Author deleted!");
    }
}

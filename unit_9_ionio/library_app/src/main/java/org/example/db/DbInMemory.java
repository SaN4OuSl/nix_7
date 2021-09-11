package org.example.db;

import org.example.entity.Author;
import org.example.entity.Book;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DbInMemory {

    private static DbInMemory instance = null;
    private final Set<Author> authors = new HashSet<>();
    private final Set<Book> books = new HashSet<>();
    private final Map<Author, Book> bookRegistrations = new HashMap<>();

    private DbInMemory() {
    }

    public static DbInMemory getInstance() {
        if (instance == null) {
            instance = new DbInMemory();
        }
        return instance;
    }

    public void createAuthor(Author author) {
        author.setId(authors.size() + 1);
        authors.add(author);
    }

    public void createBook(Book book) {
        book.setId(books.size() + 1);
        books.add(book);
    }

    public void createBookRegistration(Author author, Book book) {
        bookRegistrations.put(author, book);
    }

    public Set<Author> readAllAuthors() {
        return authors;
    }

    public Set<Book> readAllBooks() {
        return books;
    }

    public Author readAuthorById(Integer id) {
        Author readAuthor = authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst()
                .get();

        return readAuthor;
    }

    public Book readBookById(Integer id) {
        Book readBook = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .get();

        return readBook;
    }

    public void updateAuthor(Author author) {
        Author updatedAuthor = authors.stream().filter(a -> a.getId().equals(author.getId())).findFirst().get();
        updatedAuthor = author;
    }

    public void updateBook(Book book) {
        Book updatedBook = books.stream().filter(b -> b.getId().equals(book.getId())).findFirst().get();
        updatedBook = book;
    }

    public void deleteAuthor(Author author) {
        Author deletedAuthor = authors.stream().filter(a -> a.getId().equals(author.getId())).findFirst().get();
        deletedAuthor.setAvailable(false);
    }

    public void deleteBook(Book book) {
        Book deletedBook = books.stream().filter(b -> b.getId().equals(book.getId())).findFirst().get();
        deletedBook.setAvailable(false);
    }
}


package org.example.service;

import org.example.custom_util.DynamicArray;
import org.example.exception.EmptyLibraryException;

public class AssociateService {

    private final AuthorService AUTHOR_SERVICE = new AuthorService();
    private final BookService BOOK_SERVICE = new BookService();

    public void booksWithAuthors(int bookId, String authorsId) {
        authorsId = authorsId.trim();
        String[] ids = authorsId.split(",");
        DynamicArray<Integer> authors = new DynamicArray<>();
        for (String s : ids) {
            authors.add(Integer.parseInt(s));
        }
        BOOK_SERVICE.associateAuthorWithBooks(bookId, authors);
    }

    public void authorWithBooks(int authorId, String booksId) {
        booksId = booksId.trim();
        String[] ids = booksId.split(",");
        DynamicArray<Integer> books = new DynamicArray<>();
        for (String s : ids) {
            books.add(Integer.parseInt(s));
        }
        AUTHOR_SERVICE.associateAuthorWithBooks(authorId, books);
    }

    public void allAuthors() throws EmptyLibraryException {
        AUTHOR_SERVICE.allAuthors();
    }

    public void allBooks() throws EmptyLibraryException {
        BOOK_SERVICE.allBooks();
    }
}

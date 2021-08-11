package org.example.dao.impl;

import org.example.custom_util.DynamicArray;
import org.example.db.DBInMemory;
import org.example.entity.Book;
import org.example.exception.EmptyLibraryException;
import org.example.exception.NonexistentIdException;

public class BookDAOImpl {
    private final DBInMemory bd = DBInMemory.getInstance();


    public boolean createBook(Book book) {
        return bd.createBook(book);
    }

    public boolean updateBook(Book book) {
        return bd.updateBook(book);
    }

    public boolean deleteBookById(int id) {
        return bd.deleteBookById(id);
    }

    public void associateBookWithAuthors(int bookId, DynamicArray<Integer> authorId) {
        bd.associateBookWithAuthors(bookId, authorId);
    }

    public void allBooks() throws EmptyLibraryException {
        bd.allBooks();
    }

    public Book readBookById(int id) throws NonexistentIdException {
        return bd.readBookById(id);
    }

    public void allAuthorsByBookId(int bookId) {
        bd.allAuthorsByBookId(bookId);
    }

    public boolean containsBook(int id) {
        return bd.containsBook(id);
    }
}

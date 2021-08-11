package org.example.dao;

import org.example.custom_util.DynamicArray;
import org.example.entity.Book;
import org.example.exception.EmptyLibraryException;
import org.example.exception.NonexistentIdException;

public interface BookDAO {
    boolean createBook(Book book);

    boolean updateBook(Book book);

    boolean deleteBookById(int id);

    void associateBookWithAuthors(int bookId, DynamicArray<Integer> authorId);

    void allBooks() throws EmptyLibraryException;

    Book readBookById(int id) throws NonexistentIdException;

    void allAuthorsByBookId(int bookId);

    boolean containsBook(int id);
}

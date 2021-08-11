package org.example.dao;

import org.example.custom_util.DynamicArray;
import org.example.entity.Author;
import org.example.exception.EmptyLibraryException;
import org.example.exception.NonexistentIdException;

public interface AuthorDAO {
    boolean createAuthor(Author author);

    boolean updateAuthor(Author author);

    boolean deleteAuthorById(int id);

    void associateAuthorWithBooks(int authorId, DynamicArray<Integer> booksID);

    Author readAuthorById(int id) throws NonexistentIdException;

    void allAuthors() throws EmptyLibraryException;

    void allBooksByAuthorId(int authorId);

    boolean containsAuthor(int id);
}

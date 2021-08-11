package org.example.dao.impl;

import org.example.custom_util.DynamicArray;
import org.example.db.DBInMemory;
import org.example.entity.Author;
import org.example.exception.EmptyLibraryException;
import org.example.exception.NonexistentIdException;

public class AuthorDAOImpl {
    private final DBInMemory bd = DBInMemory.getInstance();

    public boolean createAuthor(Author author) {
        return bd.createAuthor(author);
    }

    public boolean updateAuthor(Author author) {
        return bd.updateAuthor(author);
    }

    public boolean deleteAuthorById(int id) {
        return bd.deleteAuthorById(id);
    }

    public void associateAuthorWithBooks(int authorId, DynamicArray<Integer> booksID) {
        bd.associateAuthorWithBooks(authorId, booksID);
    }

    public Author readAuthorById(int id) throws NonexistentIdException {
        return bd.readAuthorById(id);
    }

    public void allAuthors() throws EmptyLibraryException {
        bd.allAuthors();
    }

    public void allBooksByAuthorId(int authorId) {
        bd.allBooksByAuthorId(authorId);
    }

    public boolean containsAuthor(int id) {
        return bd.containsAuthor(id);
    }

}

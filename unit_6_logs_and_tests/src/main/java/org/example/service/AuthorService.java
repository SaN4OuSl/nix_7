package org.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.dao.AuthorDAO;
import org.example.dao.impl.AuthorDAOImpl;
import org.example.custom_util.DynamicArray;
import org.example.entity.Author;
import org.example.exception.EmptyLibraryException;
import org.example.exception.NonexistentIdException;

public class AuthorService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final AuthorDAO DAO = new AuthorDAOImpl();

    public void createAuthor(Author author) {
        LOGGER_INFO.info("Start of creating author");
        DAO.createAuthor(author);
        LOGGER_INFO.info("Finish of creating author");
    }

    public boolean updateAuthor(Author author) {
        LOGGER_INFO.info("Updating author");
        return DAO.updateAuthor(author);
    }

    public boolean deleteAuthorById(int id) {
        LOGGER_INFO.info("Deleting author");
        return DAO.deleteAuthorById(id);
    }

    public void associateAuthorWithBooks(int authorId, DynamicArray<Integer> books) {
        LOGGER_INFO.info("Start of associating author with books");
        DAO.associateAuthorWithBooks(authorId, books);
        LOGGER_INFO.info("Finish of associating author with books");
    }

    public Author readAuthorById(int id) throws NonexistentIdException {
        LOGGER_INFO.info("Reading author");
        return DAO.readAuthorById(id);
    }

    public void allAuthors() throws EmptyLibraryException {
        LOGGER_INFO.info("Printing all authors");
        DAO.allAuthors();
        LOGGER_INFO.info("Finish of printing all authors");
    }

    public void allBooksByAuthorId(int authorId) {
        LOGGER_INFO.info("Printing all author's books");
        DAO.allBooksByAuthorId(authorId);
        LOGGER_INFO.info("Finish of printing all author's books");
    }

    public boolean containsAuthor(int id) {
        LOGGER_INFO.info("Checking author");
        return DAO.containsAuthor(id);
    }

}

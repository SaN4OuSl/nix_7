package org.example.service;

import org.apache.log4j.Logger;
import org.example.dao.impl.AuthorDAOImpl;
import org.example.custom_util.DynamicArray;
import org.example.entity.Author;
import org.example.exception.EmptyLibraryException;
import org.example.exception.NonexistentIdException;

public class AuthorService {
    private final Logger logger = Logger.getLogger(AuthorService.class);
    private final AuthorDAOImpl DAO = new AuthorDAOImpl();

    public void createAuthor(Author author) {
        logger.info("Start of creating author");
        DAO.createAuthor(author);
        logger.info("Finish of creating author");
    }

    public boolean updateAuthor(Author author) {
        logger.info("Updating author");
        return DAO.updateAuthor(author);
    }

    public boolean deleteAuthorById(int id) {
        logger.info("Deleting author");
        return DAO.deleteAuthorById(id);
    }

    public void associateAuthorWithBooks(int authorId, DynamicArray<Integer> books) {
        logger.info("Start of associating author with books");
        DAO.associateAuthorWithBooks(authorId, books);
        logger.info("Finish of associating author with books");
    }

    public Author readAuthorById(int id) throws NonexistentIdException {
        logger.info("Reading author");
        return DAO.readAuthorById(id);
    }

    public void allAuthors() throws EmptyLibraryException {
        logger.info("Printing all authors");
        DAO.allAuthors();
        logger.info("Finish of printing all authors");
    }

    public void allBooksByAuthorId(int authorId) {
        logger.info("Printing all author's books");
        DAO.allBooksByAuthorId(authorId);
        logger.info("Finish of printing all author's books");
    }

    public boolean containsAuthor(int id) {
        logger.info("Checking author");
        return DAO.containsAuthor(id);
    }

}

package org.example.service;

import org.example.dao.BookDAO;
import org.example.dao.impl.BookDAOImpl;
import org.example.custom_util.DynamicArray;
import org.example.entity.Book;
import org.example.exception.EmptyLibraryException;
import org.example.exception.NonexistentIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final BookDAO DAO = new BookDAOImpl();

    public void createBook(Book book) {
        LOGGER_INFO.info("Start of creating book");
        DAO.createBook(book);
        LOGGER_INFO.info("Finish of creating book");
    }

    public boolean updateBook(Book book) {
        LOGGER_INFO.info("Updating book");
        return DAO.updateBook(book);
    }

    public boolean deleteBookById(int id) {
        LOGGER_INFO.info("Deleting book");
        return DAO.deleteBookById(id);
    }

    public void associateAuthorWithBooks(int bookId, DynamicArray<Integer> authors) {
        LOGGER_INFO.info("Start of associating book with authors");
        DAO.associateBookWithAuthors(bookId, authors);
        LOGGER_INFO.info("Finish of associating book with authors");
    }

    public void allBooks() throws EmptyLibraryException {
        LOGGER_INFO.info("Printing all books");
        DAO.allBooks();
        LOGGER_INFO.info("Finish of printing all books");
    }

    public Book readBookById(int id) throws NonexistentIdException {
        LOGGER_INFO.info("Reading book");
        return DAO.readBookById(id);
    }

    public void allAuthorsByBookId(int bookId) {
        LOGGER_INFO.info("Printing all book's authors");
        DAO.allAuthorsByBookId(bookId);
        LOGGER_INFO.info("Finish of printing all book's authors");
    }

    public boolean containsBook(int id) {
        LOGGER_INFO.info("Checking book");
        return DAO.containsBook(id);
    }
}

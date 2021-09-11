package org.example.service.impl;

import org.example.dao.AuthorDao;
import org.example.dao.BookDao;
import org.example.dao.impl.CsvDB.AuthorDaoCsvDBImpl;
import org.example.dao.impl.CsvDB.BookDaoCsvDBImpl;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.service.AssociateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssociateServiceImpl implements AssociateService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final BookDao bookDao = new BookDaoCsvDBImpl();
    private final AuthorDao authorDAO = new AuthorDaoCsvDBImpl();

    @Override
    public void addAuthorToBook(Book book, Author author) {
        LOGGER_INFO.info("Start adding author to book.");
        if (book.getAuthors().contains(author.getId())) {
            System.out.println("Book already have this author!");
            LOGGER_WARN.warn("Book adding author warning: book already have this author");
            return;
        }
        bookDao.addAuthorToBook(book, author);
        LOGGER_INFO.info("Successful adding author to book.");
    }

    @Override
    public void addBookToAuthor(Author author, Book book) {
        LOGGER_INFO.info("Start adding book to author.");
        if (author == null) {
            System.err.println("No author with current id");
            return;
        }
        if (book == null) {
            System.err.println("No book with current id");
            return;
        }
        if (author.getBooks().contains(book.getId())) {
            LOGGER_WARN.warn("Author adding book warning: author already have this book");
            return;
        }
        authorDAO.addBookToAuthor(author, book);
        LOGGER_INFO.info("Successful adding book to author.");
    }
}

package org.example.service.impl;

import org.example.dao.AuthorDao;
import org.example.dao.BookDao;
import org.example.dao.impl.CsvDB.AuthorDaoCsvDBImpl;
import org.example.dao.impl.CsvDB.BookDaoCsvDBImpl;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final BookDao bookDao = new BookDaoCsvDBImpl();
    private final AuthorDao authorDao = new AuthorDaoCsvDBImpl();

    @Override
    public void create(Book book) {
        LOGGER_INFO.info("Start book creating.");
        bookDao.create(book);
        LOGGER_INFO.info("Successful book creating.");
    }

    @Override
    public Book read(Integer id) {
        LOGGER_INFO.info("Start book reading.");
        Book book = new Book();
        try {
            book = bookDao.read(id);
            if (isBookDeleted(book)) {
                LOGGER_ERROR.error("Reading book error: book with this id was deleted");
                return null;
            }
        } catch (NoSuchElementException e) {
            LOGGER_ERROR.error("Reading book error: no book with current id");
            return null;
        }
        LOGGER_INFO.info("Successful book reading.");
        return book;
    }

    @Override
    public Set<Book> readAll() {
        LOGGER_INFO.info("Start all books reading.");
        LOGGER_INFO.info("Successful all books reading.");
        return bookDao.read().stream().filter(book -> !isBookDeleted(book)).collect(Collectors.toSet());
    }

    @Override
    public void update(Book book) {
        LOGGER_INFO.info("Start book updating.");
        if (isBookDeleted(book)) {
            LOGGER_ERROR.error("Reading book error: book with this id was deleted");
            return;
        }
        bookDao.update(book);
        LOGGER_INFO.info("Successful book updating.");
    }

    @Override
    public void delete(Book book) {
        LOGGER_INFO.info("Start book deleting.");
        if (isBookDeleted(book)) {
            LOGGER_ERROR.error("Reading book error: book with this id was deleted");
            throw new NoSuchElementException("No book with current id");
        }
        bookDao.delete(book);
        LOGGER_INFO.info("Successful book deleting.");
    }

    @Override
    public Set<Author> readAuthors(Book book) {
        Set<Author> authors = new HashSet<>();
        book.getAuthors().forEach(authorId -> authors.add(authorDao.read(authorId)));
        return authors;
    }

    private boolean isBookDeleted(Book book) {
        return !book.getAvailable();
    }
}

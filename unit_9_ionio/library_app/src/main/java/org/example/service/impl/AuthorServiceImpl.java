package org.example.service.impl;

import org.example.dao.AuthorDao;
import org.example.dao.BookDao;
import org.example.dao.impl.CsvDB.AuthorDaoCsvDBImpl;
import org.example.dao.impl.CsvDB.BookDaoCsvDBImpl;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements AuthorService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final AuthorDao authorDAO = new AuthorDaoCsvDBImpl();
    private final BookDao bookDao = new BookDaoCsvDBImpl();

    @Override
    public void create(Author author) {
        LOGGER_INFO.info("Start author creating.");
        authorDAO.create(author);
        LOGGER_INFO.info("Successful author creating.");
    }

    @Override
    public Author read(Integer id) {
        LOGGER_INFO.info("Start author reading.");
        try {
            Author author = authorDAO.read(id);
            if (isAuthorDeleted(author)) {
                LOGGER_ERROR.error("Reading author error: author with this id was deleted");
                return null;
            }
            LOGGER_INFO.info("Successful author reading.");
            return author;
        } catch (NoSuchElementException e) {
            LOGGER_ERROR.error("Reading author error: no author with current id");
            return null;
        }
    }

    @Override
    public Set<Author> readAll() {
        LOGGER_INFO.info("Start all author reading.");
        LOGGER_INFO.info("Successful all author reading.");
        return authorDAO.read().stream().filter(author -> !isAuthorDeleted(author)).collect(Collectors.toSet());
    }

    @Override
    public void update(Author author) {
        LOGGER_INFO.info("Start author updating.");
        if (isAuthorDeleted(author)) {
            LOGGER_ERROR.error("Reading author error: author with this id was deleted");
            return;
        }
        authorDAO.update(author);
        LOGGER_INFO.info("Successful author updating.");
    }

    @Override
    public void delete(Author author) {
        LOGGER_INFO.info("Start author deleting.");
        if (isAuthorDeleted(author)) {
            LOGGER_ERROR.error("Reading author error: author with this id was deleted");
            return;
        }
        authorDAO.delete(author);
        LOGGER_INFO.info("Successful author deleting.");
    }


    @Override
    public Set<Book> readBooks(Author author) {
        LOGGER_INFO.info("Start reading author books.");
        if (author == null) {
            System.err.println("No author with current id");
            return null;
        }
        Set<Book> books = new HashSet<>();
        author.getBooks().forEach(bookId -> books.add(bookDao.read(bookId)));
        LOGGER_INFO.info("Successful reading author books.");
        return books;
    }

    private boolean isAuthorDeleted(Author author) {
        return !author.getAvailable();
    }
}

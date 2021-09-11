package org.example.dao.impl.CsvDB;

import org.example.dao.BookDao;
import org.example.db.DbInCSV;
import org.example.entity.Author;
import org.example.entity.Book;

import java.util.Set;

public class BookDaoCsvDBImpl implements BookDao {

    @Override
    public void create(Book book) {
        DbInCSV.getInstance().createBook(book);
    }

    @Override
    public Book read(Integer id) {
        return DbInCSV.getInstance().readBookById(id);
    }

    @Override
    public Set<Book> read() {
        return DbInCSV.getInstance().readAllBooks();
    }

    @Override
    public void update(Book book) {
        DbInCSV.getInstance().updateBook(book);
    }

    @Override
    public void delete(Book book) {
        DbInCSV.getInstance().deleteBook(book);
    }

    @Override
    public void addAuthorToBook(Book book, Author author) {
        DbInCSV.getInstance().createBookRegistration(author, book);
    }
}

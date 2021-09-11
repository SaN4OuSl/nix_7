package org.example.dao.impl.InMemoryDB;

import org.example.dao.BookDao;
import org.example.db.DbInMemory;
import org.example.entity.Author;
import org.example.entity.Book;

import java.util.Set;

public class BookDaoInMemoryDBImpl implements BookDao {

    @Override
    public void create(Book book) {
        DbInMemory.getInstance().createBook(book);
    }

    @Override
    public Book read(Integer id) {
        return DbInMemory.getInstance().readBookById(id);
    }

    @Override
    public Set<Book> read() {
        return DbInMemory.getInstance().readAllBooks();
    }

    @Override
    public void update(Book book) {
        DbInMemory.getInstance().updateBook(book);
    }

    @Override
    public void delete(Book book) {
        DbInMemory.getInstance().deleteBook(book);
    }

    @Override
    public void addAuthorToBook(Book book, Author author) {
        DbInMemory.getInstance().createBookRegistration(author, book);
    }
}

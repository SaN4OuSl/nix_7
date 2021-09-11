package org.example.dao.impl.CsvDB;

import org.example.dao.AuthorDao;
import org.example.db.DbInCSV;
import org.example.entity.Author;
import org.example.entity.Book;

import java.util.Set;

public class AuthorDaoCsvDBImpl implements AuthorDao {

    @Override
    public void create(Author author) {
        DbInCSV.getInstance().createAuthor(author);
    }

    @Override
    public Author read(Integer id) {
        return DbInCSV.getInstance().readAuthorById(id);
    }

    @Override
    public Set<Author> read() {
        return DbInCSV.getInstance().readAllAuthors();
    }

    @Override
    public void update(Author author) {
        DbInCSV.getInstance().updateAuthor(author);
    }

    @Override
    public void delete(Author author) {
        DbInCSV.getInstance().deleteAuthor(author);
    }

    @Override
    public void addBookToAuthor(Author author, Book book) {
        DbInCSV.getInstance().createBookRegistration(author, book);
    }
}

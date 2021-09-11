package org.example.dao.impl.InMemoryDB;

import org.example.dao.AuthorDao;
import org.example.db.DbInMemory;
import org.example.entity.Author;
import org.example.entity.Book;

import java.util.Set;

public class AuthorDaoInMemoryDBImpl implements AuthorDao {

    @Override
    public void create(Author author) {
        DbInMemory.getInstance().createAuthor(author);
    }

    @Override
    public Author read(Integer id) {
        return DbInMemory.getInstance().readAuthorById(id);
    }

    @Override
    public Set<Author> read() {
        return DbInMemory.getInstance().readAllAuthors();
    }

    @Override
    public void update(Author author) {
        DbInMemory.getInstance().updateAuthor(author);
    }

    @Override
    public void delete(Author author) {
        DbInMemory.getInstance().deleteAuthor(author);
    }

    @Override
    public void addBookToAuthor(Author author, Book book) {
        DbInMemory.getInstance().createBookRegistration(author, book);
    }
}

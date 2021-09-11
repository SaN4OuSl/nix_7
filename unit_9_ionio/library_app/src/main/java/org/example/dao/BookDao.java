package org.example.dao;

import org.example.entity.Author;
import org.example.entity.Book;

public interface BookDao extends AbstractDao<Book, Integer> {
    void addAuthorToBook(Book book, Author author);
}

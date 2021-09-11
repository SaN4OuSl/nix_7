package org.example.dao;

import org.example.entity.Author;
import org.example.entity.Book;

public interface AuthorDao extends AbstractDao<Author, Integer> {
    void addBookToAuthor(Author author, Book book);
}

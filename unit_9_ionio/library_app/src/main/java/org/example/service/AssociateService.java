package org.example.service;

import org.example.entity.Author;
import org.example.entity.Book;

public interface AssociateService {
    void addAuthorToBook(Book book, Author author);

    void addBookToAuthor(Author author, Book book);
}

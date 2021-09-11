package org.example.service;

import org.example.entity.Author;
import org.example.entity.Book;

import java.util.Set;

public interface AuthorService extends AbstractService<Author, Integer> {
    Set<Book> readBooks(Author author);
}

package org.example.service;

import org.example.entity.Author;
import org.example.entity.Book;

import java.util.Set;

public interface BookService extends AbstractService<Book, Integer> {
    Set<Author> readAuthors(Book book);
}

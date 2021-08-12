package org.example.db;

import org.example.custom_util.DynamicArray;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.exception.EmptyLibraryException;
import org.example.exception.NonexistentIdException;

public class DBInMemory {
    private static DBInMemory instance;

    private final DynamicArray<Author> authors = new DynamicArray<Author>();
    private final DynamicArray<Book> books = new DynamicArray<Book>();

    private DBInMemory() {
    }

    public static DBInMemory getInstance() {
        if (instance == null) {
            instance = new DBInMemory();
        }
        return instance;
    }

    public boolean createAuthor(Author author) {
        int id = authors.getSize();
        author.setAvailable(true);
        author.setId(id);
        return authors.add(author);
    }

    public boolean createBook(Book book) {
        int id = books.getSize();
        book.setAvailable(true);
        book.setId(id);
        return books.add(book);
    }

    public boolean updateAuthor(Author author) {
        for (int i = 0; i < authors.getSize(); i++) {
            if (authors.get(i).getId() == author.getId()) {
                authors.get(i).setFirstName(author.getFirstName());
                authors.get(i).setSecondName(author.getSecondName());
                return true;
            }
        }
        return false;
    }

    public boolean updateBook(Book book) {
        for (int i = 0; i < books.getSize(); i++) {
            if (books.get(i).getId() == book.getId()) {
                books.get(i).setName(book.getName());
                return true;
            }
        }
        return false;
    }

    public boolean deleteAuthorById(int id) {
        for (int i = 0; i < authors.getSize(); i++) {
            if (authors.get(i).getId() == id && authors.get(i).isAvailable()) {
                authors.get(i).setAvailable(false);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBookById(int id) {
        for (int i = 0; i < books.getSize(); i++) {
            if (books.get(i).getId() == id && books.get(i).isAvailable()) {
                books.get(i).setAvailable(false);
                return true;
            }
        }
        return false;
    }

    public Author readAuthorById(int id) throws NonexistentIdException {
        for (int i = 0; i < authors.getSize(); i++) {
            if (authors.get(i).getId() == id && authors.get(i).isAvailable()) return authors.get(i);
        }
        throw new NonexistentIdException();
    }

    public Book readBookById(int id) throws NonexistentIdException {
        for (int i = 0; i < books.getSize(); i++) {
            if (books.get(i).getId() == id && books.get(i).isAvailable()) return books.get(i);
        }
        throw new NonexistentIdException();
    }

    public void allAuthors() throws EmptyLibraryException {
        if (!authors.isEmpty()) {
            for (int i = 0; i < authors.getSize(); i++) {
                if (authors.get(i).isAvailable())
                    System.out.println("Id: " + authors.get(i).getId() + " First name: " + authors.get(i).getFirstName() +
                            " Second name: " + authors.get(i).getSecondName());
            }
        } else {
            throw new EmptyLibraryException();
        }
    }

    public void allBooks() throws EmptyLibraryException {
        if (!books.isEmpty()) {
            for (int i = 0; i < books.getSize(); i++) {
                if (books.get(i).isAvailable())
                    System.out.println("Id: " + books.get(i).getId() + " Name: " + books.get(i).getName());
            }
        } else {
            throw new EmptyLibraryException();
        }
    }

    public void associateAuthorWithBooks(int authorId, DynamicArray<Integer> booksId) {
        for (int i = 0; i < authors.getSize(); i++) {
            if (authors.get(i).getId() == authorId)
                authors.get(i).setBooks(booksId);
        }
        for (int i = 0; i < authors.getSize(); i++) {
            for (int j = 0; j < booksId.getSize(); j++) {
                if (booksId.get(j) == null) {
                    if (booksId.get(j) == books.get(i).getId())
                        books.get(i).getAuthors().add(authorId);
                }
            }
        }
    }

    public void associateBookWithAuthors(int bookId, DynamicArray<Integer> authorId) {
        for (int i = 0; i < books.getSize(); i++) {
            if (books.get(i).getId() == bookId) {
                books.get(i).setAuthors(authorId);
            }

        }
        for (int i = 0; i < authors.getSize(); i++) {
            for (int j = 0; j < authorId.getSize(); j++) {
                if (authorId.get(j) != null) {
                    if (authorId.get(j) == authors.get(i).getId()) {
                        authors.get(i).getBooks().add(bookId);
                    }
                }
            }
        }
    }

    public void allBooksByAuthorId(int authorId) {
        boolean check = true;
        for (int i = 0; i < books.getSize(); i++) {
            for (int j = 0; j < books.get(i).getAuthors().getSize(); j++)
                if (books.get(i).getAuthors().get(j) != null) {
                    if (books.get(i).getAuthors().get(j) == authorId) {
                        System.out.println("Name: " + books.get(i).getName());
                        check = false;
                    }
                }
        }
        if (check == true) System.out.println("Books aren't known");
    }

    public void allAuthorsByBookId(int bookId) {
        boolean check = true;
        for (int i = 0; i < authors.getSize(); i++) {
            for (int j = 0; j < authors.get(i).getBooks().getSize(); j++)
                if (authors.get(i).getBooks().get(j) != null) {
                    if (authors.get(i).getBooks().get(j) == bookId) {
                        System.out.println("FirstName: " + authors.get(i).getFirstName() + " SecondName: " + authors.get(i).getSecondName());
                        check = false;
                    }
                }
        }
        if (check == true) System.out.println("Authors aren't known");
    }

    public boolean containsAuthor(int id) {
        for (int i = 0; i < authors.getSize(); i++) {
            if (authors.get(i).getId() == id && authors.get(i).isAvailable()) return true;
        }
        return false;
    }

    public boolean containsBook(int id) {
        for (int i = 0; i < books.getSize(); i++) {
            if (books.get(i).getId() == id && books.get(i).isAvailable()) return true;
        }
        return false;
    }

}

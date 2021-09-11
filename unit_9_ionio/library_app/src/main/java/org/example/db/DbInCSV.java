package org.example.db;

import org.example.entity.Author;
import org.example.entity.Book;
import org.example.util.ReadFromCsv;
import org.example.util.WriteToCsv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DbInCSV {

    private static DbInCSV instance = null;

    private final String authorTablePath = "unit_9_ionio/library_app/src/main/resources/dbinfiles/author.csv";
    private final String bookTablePath = "unit_9_ionio/library_app/src/main/resources/dbinfiles/book.csv";
    private final String bookRegistrationTablePath = "unit_9_ionio/library_app/src/main/resources/dbinfiles/associations.csv";

    private final String[] authorTableHeader = {"id", "name", "surname", "birth_place", "available"};
    private final String[] bookTableHeader = {"id", "name", "pages", "available"};
    private final String[] bookRegistrationTableHeader = {"id", "author_id", "book_id"};

    private final List<String[]> authorCsvData = new ArrayList<>();
    private final List<String[]> bookCsvData = new ArrayList<>();
    private final List<String[]> bookRegistrationCsvData = new ArrayList<>();

    private final Integer AUTHOR_TABLE_COLUMNS_AMOUNT = authorTableHeader.length;
    private final Integer BOOK_TABLE_COLUMNS_AMOUNT = bookTableHeader.length;
    private final Integer BOOK_REGISTRATION_TABLE_COLUMNS_AMOUNT = bookRegistrationTableHeader.length;

    private WriteToCsv authorWriter;
    private WriteToCsv bookWriter;
    private WriteToCsv bookRegistrationWriter;

    private ReadFromCsv authorReader;
    private ReadFromCsv bookReader;
    private ReadFromCsv bookRegistrationReader;

    private DbInCSV() {
        authorWriter = new WriteToCsv(authorTablePath);
        authorReader = new ReadFromCsv(authorTablePath);

        bookWriter = new WriteToCsv(bookTablePath);
        bookReader = new ReadFromCsv(bookTablePath);

        bookRegistrationWriter = new WriteToCsv(bookRegistrationTablePath);
        bookRegistrationReader = new ReadFromCsv(bookRegistrationTablePath);

        boolean authorIsEmpty = authorReader.readAll().isEmpty();
        boolean bookIsEmpty = bookReader.readAll().isEmpty();
        boolean bookRegistrationIsEmpty = bookRegistrationReader.readAll().isEmpty();
        if (authorIsEmpty || bookIsEmpty || bookRegistrationIsEmpty) {
            initAuthorTable();
            initBookTable();
            initBookRegistrationTable();
        } else {
            authorReader = new ReadFromCsv(authorTablePath);
            authorCsvData.addAll(authorReader.readAll());

            bookReader = new ReadFromCsv(bookTablePath);
            bookCsvData.addAll(bookReader.readAll());

            bookRegistrationReader = new ReadFromCsv(bookRegistrationTablePath);
            bookRegistrationCsvData.addAll(bookRegistrationReader.readAll());
        }
    }

    public static DbInCSV getInstance() {
        if (instance == null) {
            instance = new DbInCSV();
        }
        return instance;
    }

    private void initAuthorTable() {
        authorWriter = new WriteToCsv(authorTablePath);
        authorReader = new ReadFromCsv(authorTablePath);

        authorCsvData.add(authorTableHeader);
        authorWriter.writeAll(authorCsvData);
    }

    private void initBookTable() {
        bookWriter = new WriteToCsv(bookTablePath);
        bookReader = new ReadFromCsv(bookTablePath);

        bookCsvData.add(bookTableHeader);
        bookWriter.writeAll(bookCsvData);
    }

    private void initBookRegistrationTable() {
        bookRegistrationWriter = new WriteToCsv(bookRegistrationTablePath);
        bookRegistrationReader = new ReadFromCsv(bookRegistrationTablePath);

        bookRegistrationCsvData.add(bookRegistrationTableHeader);
        bookRegistrationWriter.writeAll(bookRegistrationCsvData);
    }

    public void createAuthor(Author author) {
        author.setId(authorCsvData.size());
        String[] authorData = new String[AUTHOR_TABLE_COLUMNS_AMOUNT];
        authorData[0] = String.valueOf(author.getId());
        authorData[1] = author.getName();
        authorData[2] = author.getSurname();
        authorData[3] = author.getBirthPlace();
        authorData[4] = String.valueOf(author.getAvailable());

        authorCsvData.add(authorData);
        authorWriter = new WriteToCsv(authorTablePath);
        authorWriter.writeAll(this.authorCsvData);
    }

    public void createBook(Book book) {
        book.setId(bookCsvData.size());
        String[] bookData = new String[BOOK_TABLE_COLUMNS_AMOUNT];
        bookData[0] = String.valueOf(book.getId());
        bookData[1] = book.getName();
        bookData[2] = String.valueOf(book.getPages());
        bookData[3] = String.valueOf(book.getAvailable());

        bookCsvData.add(bookData);
        bookWriter = new WriteToCsv(bookTablePath);
        bookWriter.writeAll(this.bookCsvData);
    }

    public void createBookRegistration(Author author, Book book) {
        String[] bookRegistrationData = new String[BOOK_REGISTRATION_TABLE_COLUMNS_AMOUNT];
        bookRegistrationData[0] = String.valueOf(bookRegistrationCsvData.size());
        bookRegistrationData[1] = String.valueOf(author.getId());
        bookRegistrationData[2] = String.valueOf(book.getId());

        bookRegistrationCsvData.add(bookRegistrationData);
        bookRegistrationWriter = new WriteToCsv(bookRegistrationTablePath);
        bookRegistrationWriter.writeAll(this.bookRegistrationCsvData);
    }

    public Set<Author> readAllAuthors() {
        Set<Author> authors = new HashSet<>();
        for (int i = 1; i < authorCsvData.size(); i++) {
            int id = Integer.parseInt(authorCsvData.get(i)[0]);
            Author author = readAuthorById(id);
            authors.add(author);
        }

        return authors;
    }

    public Set<Book> readAllBooks() {
        Set<Book> books = new HashSet<>();
        for (int i = 1; i < bookCsvData.size(); i++) {
            int id = Integer.parseInt(bookCsvData.get(i)[0]);

            Book book = readBookById(id);
            books.add(book);
        }

        return books;
    }

    public Author readAuthorById(Integer id) {
        String[] authorRow = authorCsvData.stream()
                .skip(1)
                .filter(author -> Integer.parseInt(author[0]) == id)
                .findFirst().get();

        Author author = new Author();
        author.setId(Integer.parseInt(authorRow[0]));
        author.setName(authorRow[1]);
        author.setSurname(authorRow[2]);
        author.setBirthPlace(authorRow[3]);
        author.setAvailable(Boolean.parseBoolean(authorRow[4]));
        author.setBooks(readAuthorBooks(id));

        return author;
    }

    public Book readBookById(Integer id) {
        String[] bookRow = bookCsvData.stream()
                .skip(1)
                .filter(book -> Integer.parseInt(book[0]) == id)
                .findFirst().get();

        Book book = new Book();
        book.setId(Integer.parseInt(bookRow[0]));
        book.setName(bookRow[1]);
        book.setPages(Integer.parseInt(bookRow[2]));
        book.setAvailable(Boolean.parseBoolean(bookRow[3]));
        book.setAuthors(readBookAuthors(id));

        return book;
    }

    public Set<Integer> readAuthorBooks(Integer authorId) {
        Set<Integer> booksIds = new HashSet<>();
        List<String[]> bookRegistrationRows = bookRegistrationCsvData.stream()
                .skip(1)
                .filter(regData -> Integer.parseInt(regData[1]) == authorId)
                .collect(Collectors.toList());

        for (String[] data : bookRegistrationRows) {
            booksIds.add(Integer.parseInt(data[2]));
        }

        return booksIds;
    }

    public Set<Integer> readBookAuthors(Integer bookId) {
        Set<Integer> authorsIds = new HashSet<>();
        List<String[]> bookRegistrationRows = bookRegistrationCsvData.stream()
                .skip(1)
                .filter(regData -> Integer.parseInt(regData[2]) == bookId)
                .collect(Collectors.toList());

        for (String[] data : bookRegistrationRows) {
            authorsIds.add(Integer.parseInt(data[1]));
        }

        return authorsIds;
    }

    public void updateAuthor(Author author) {
        int updatedAuthorRowIndex = 0;
        for (int i = 1; i < this.authorCsvData.size(); i++) {
            if (Integer.parseInt(authorCsvData.get(i)[0]) == author.getId()) {
                updatedAuthorRowIndex = i;
            }
        }

        String[] updatedRow = new String[AUTHOR_TABLE_COLUMNS_AMOUNT];
        updatedRow[0] = String.valueOf(author.getId());
        updatedRow[1] = author.getName();
        updatedRow[2] = author.getSurname();
        updatedRow[3] = author.getBirthPlace();
        updatedRow[4] = String.valueOf(author.getAvailable());

        authorCsvData.set(updatedAuthorRowIndex, updatedRow);
        authorWriter = new WriteToCsv(authorTablePath);
        authorWriter.writeAll(authorCsvData);
    }

    public void updateBook(Book book) {
        int updatedBookRowIndex = 0;
        for (int i = 1; i < this.bookCsvData.size(); i++) {
            if (Integer.parseInt(bookCsvData.get(i)[0]) == book.getId()) {
                updatedBookRowIndex = i;
            }
        }

        String[] updatedRow = new String[BOOK_TABLE_COLUMNS_AMOUNT];
        updatedRow[0] = String.valueOf(book.getId());
        updatedRow[1] = book.getName();
        updatedRow[2] = String.valueOf(book.getPages());
        updatedRow[3] = String.valueOf(book.getAvailable());

        bookCsvData.set(updatedBookRowIndex, updatedRow);
        bookWriter = new WriteToCsv(bookTablePath);
        bookWriter.writeAll(bookCsvData);
    }

    public void deleteAuthor(Author author) {
        int deletedAuthorRowIndex = 0;
        for (int i = 1; i < this.authorCsvData.size(); i++) {
            if (Integer.parseInt(authorCsvData.get(i)[0]) == author.getId()) {
                deletedAuthorRowIndex = i;
                break;
            }
        }

        authorCsvData.get(deletedAuthorRowIndex)[4] = "false";
        authorWriter = new WriteToCsv(authorTablePath);
        authorWriter.writeAll(authorCsvData);
    }

    public void deleteBook(Book book) {
        int deletedBookRowIndex = 0;
        for (int i = 1; i < this.bookCsvData.size(); i++) {
            if (Integer.parseInt(bookCsvData.get(i)[0]) == book.getId()) {
                deletedBookRowIndex = i;
            }
        }

        bookCsvData.get(deletedBookRowIndex)[3] = "false";
        bookWriter = new WriteToCsv(bookTablePath);
        bookWriter.writeAll(bookCsvData);
    }
}
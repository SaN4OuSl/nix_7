package org.example.entity;

import java.util.HashSet;
import java.util.Set;

public class Author extends AbstractEntity {

    private String name;
    private String surname;
    private String birthPlace;
    private Set<Integer> books;

    public Author() {
        super();
        this.books = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Set<Integer> getBooks() {
        return books;
    }

    public void setBooks(Set<Integer> books) {
        this.books = books;
    }

    @Override
    public String toString() {

        String bookId;
        if (books.isEmpty()) {
            bookId = "Unknown";
        } else {
            bookId = books.toString();
        }
        return "Author {" + '\n'
                + "name: " + name + '\n'
                + "surname: " + surname + '\n'
                + "birthPlace: " + birthPlace + '\n'
                + "books id: " + bookId + "\n}";
    }
}

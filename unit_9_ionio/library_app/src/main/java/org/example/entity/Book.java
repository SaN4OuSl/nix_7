package org.example.entity;

import java.util.HashSet;
import java.util.Set;

public class Book extends AbstractEntity {

    private String name;
    private Integer pages;
    private Set<Integer> authors;

    public Book() {
        super();
        this.authors = new HashSet<>();
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Set<Integer> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Integer> authors) {
        this.authors = authors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String authorId;
        if (authors.isEmpty()) {
            authorId = "Unknown";
        } else {
            authorId = authors.toString();
        }

        return "Book {" + '\n'
                + "name: " + name + '\n'
                + "pages: " + pages + '\n'
                + "authorsId: " + authorId + "\n}";
    }
}

package org.example.entity;


import lombok.Getter;
import lombok.Setter;
import org.example.custom_util.DynamicArray;

@Getter
@Setter
public class Book extends BaseEntity {
    private String name;
    private DynamicArray<Integer> authors = new DynamicArray<>();

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                "name='" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name);
    }
}

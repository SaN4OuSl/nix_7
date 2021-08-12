package org.example.entity;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.example.custom_util.DynamicArray;

@Getter
@Setter
public class Book extends BaseEntity {
    private String name;
    private DynamicArray<Integer> authors = new DynamicArray<>();

    @Override
    public String toString() {
        String authorId = "[";
        if (authors.isEmpty()) {
            authorId = "Unknown";
        } else {
            for (int i = 0; i < authors.getSize(); i++) {
                authorId += authors.get(i).toString() +", ";
            }
            authorId=StringUtils.chop(authorId);
            authorId=StringUtils.chop(authorId);
            authorId +="]";
        }
        return "Book{" +
                "id='" + id +
                "', name='" + name +
                "', authors id=" + authorId +
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

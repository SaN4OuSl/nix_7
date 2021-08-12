package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.example.custom_util.DynamicArray;

@Getter
@Setter
public class Author extends BaseEntity {
    private String firstName;
    private String secondName;
    private DynamicArray<Integer> books = new DynamicArray<>();

    @Override
    public String toString() {
        String booksId = "[";
        if (books.isEmpty()) {
            booksId = "Unknown";
        } else {
            for (int i = 0; i < books.getSize(); i++) {
                booksId += books.get(i).toString() +", ";
            }
            booksId= StringUtils.chop(booksId);
            booksId=StringUtils.chop(booksId);
            booksId +="]";
        }
        return "Author{" +
                "id='" + id  +
                "', firstName='" + firstName  +
                "', secondName='" + secondName +
                "', books id="+booksId+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return firstName.equals(author.firstName) && secondName.equals(author.secondName);
    }
}

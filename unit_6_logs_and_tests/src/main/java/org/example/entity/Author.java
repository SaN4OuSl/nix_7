package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.custom_util.DynamicArray;

@Getter
@Setter
public class Author extends BaseEntity {
    private String firstName;
    private String secondName;
    private DynamicArray<Integer> books = new DynamicArray<>();

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName +
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

package org.example.entity;

import org.example.annotations.CsvCell;

import java.time.LocalDateTime;

public class User {
    @CsvCell(value = "id")
    private Integer id;

    @CsvCell(value = "first_name")
    private String firstName;

    @CsvCell(value = "last_name")
    private String lastName;

    @CsvCell(value = "birth_day")
    private LocalDateTime birthDay;

    @CsvCell(value = "is_available")
    private Boolean isAvailable;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

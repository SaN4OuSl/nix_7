package org.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private String phoneNumber;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<Accounts> accounts;


    public User() {
    }

    public User(String firstName, String secondName, String phoneNumber, String email, List<Accounts> accounts) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accounts = accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }
}

package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Groups group;

    public String getName() {
        return name;
    }
}

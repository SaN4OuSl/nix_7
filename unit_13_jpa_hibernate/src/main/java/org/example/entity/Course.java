package org.example.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    @NaturalId
    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Groups> groups;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public Course() {
        groups = new ArrayList<>();
        lessons = new ArrayList<>();
    }

    public String getTitle() { return title; }

    public List<Lesson> getLessons() { return lessons; }
}

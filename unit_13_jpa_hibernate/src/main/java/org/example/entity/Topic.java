package org.example.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic extends BaseEntity {

    @NaturalId
    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public Topic() {
        lessons = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

}

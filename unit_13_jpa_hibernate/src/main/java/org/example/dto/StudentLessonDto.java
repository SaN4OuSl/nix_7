package org.example.dto;

import org.example.entity.Teacher;
import org.example.entity.Topic;

import java.sql.Timestamp;


public class StudentLessonDto {

    Timestamp startsAt;
    Timestamp endsAt;
    Teacher teacher;
    Topic topic;

    public StudentLessonDto(Timestamp startsAt, Timestamp endsAt, Teacher teacher, Topic topic) {
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.teacher = teacher;
        this.topic = topic;
    }

    public Timestamp getStartsAt() {
        return startsAt;
    }

    public Timestamp getEndsAt() {
        return endsAt;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Topic getTopic() {
        return topic;
    }
}

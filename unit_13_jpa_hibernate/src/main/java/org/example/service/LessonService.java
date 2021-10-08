package org.example.service;

import org.example.dao.LessonDao;
import org.example.dto.StudentLessonDto;
import org.example.entity.Lesson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

public class LessonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LessonService.class);

    private final Supplier<EntityManager> persistence;

    private final LessonDao dao = new LessonDao();

    public LessonService(Supplier<EntityManager> persistence) {
        this.persistence = persistence;
    }

    public StudentLessonDto findNextLessonByStudentId(Long id) {
        EntityManager jpa = persistence.get();
        try {
            LOGGER.info("Start finding lesson by student id");
            List<Lesson> result = dao.findNextLessonByStudentId(jpa, id);
            LOGGER.info("Finish finding lesson by student id");
            if (result.size() != 0)
                return pushLessonToDto(result.get(0));
            else {
                LOGGER.warn("Student has not lesson");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }

    private StudentLessonDto pushLessonToDto(Lesson l) {
        return new StudentLessonDto(l.getStartsAt(), l.getEndsAt(), l.getTeacher(), l.getTopic());
    }
}

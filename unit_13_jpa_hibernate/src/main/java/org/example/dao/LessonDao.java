package org.example.dao;

import org.example.entity.Lesson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class LessonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(LessonDao.class);

    public List<Lesson> findNextLessonByStudentId(EntityManager jpa, Long id) {

        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {
            LOGGER.info("Start dao");
            String hql = "select l from Lesson l " +
                    "join l.topic topic " +
                    "join topic.courses course " +
                    "join course.groups coursegroup " +
                    "join coursegroup.students student " +
                    "where student.id = :id and current_timestamp < l.startsAt order by l.startsAt";
            TypedQuery<Lesson> typedQuery = jpa.createQuery(hql, Lesson.class);
            typedQuery.setMaxResults(1);
            typedQuery.setParameter("id", id);
            transaction.commit();
            return typedQuery.getResultList();
        } catch (Exception e) {
            LOGGER.error("Error: ", e);
            transaction.rollback();
            throw new RuntimeException("failed to find next lesson by student id");
        }
    }
}

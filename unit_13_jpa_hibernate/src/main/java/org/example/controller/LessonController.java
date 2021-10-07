package org.example.controller;

import org.example.Unit13Main;
import org.example.entity.*;
import org.example.util.EarliestLesson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LessonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Unit13Main.class);
    private static final EarliestLesson earliestLesson = new EarliestLesson();
    private static final Scanner in = new Scanner(System.in);
    private static LessonController instance = null;

    public static LessonController getInstance() {
        if (instance == null) {
            instance = new LessonController();
        }
        return instance;
    }

    private static void defineEarliestStudentLesson(Long id, Session session) {
        try {
            session.getTransaction().begin();
            Student student = session.find(Student.class, id);
            Groups group = student.getGroup();
            Course course = group.getCourse();
            List<Lesson> lessons = course.getLessons();

            long idEarliestLesson = earliestLesson.defineIdToEarliestLesson(lessons);

            Lesson lesson = session.find(Lesson.class, idEarliestLesson);
            LocalDateTime date = lesson.getDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String strDate = date.format(formatter);

            Teacher teacher = session.find(Teacher.class, lesson.getTeacher().getId());
            Topic topic = session.find(Topic.class, lesson.getTopic().getId());
            course = session.find(Course.class, lesson.getCourse().getId());
            System.out.println("Earliest lesson: " + strDate + "\nTeacher: " + teacher.getFirstName() + " " +
                    teacher.getSecondName() + "\nTopic: " + topic.getTitle() + "\nCourse: " + course.getTitle());

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Error: ", e);
        }
    }

    public void run() {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            boolean isRun = true;
            while (isRun) {
                System.out.println("Enter student id, for exit enter -1");
                long studentId = in.nextLong();
                if (studentId != -1) {
                    LOGGER.info("Find earliest lesson");
                    defineEarliestStudentLesson(studentId, session);
                } else {
                    isRun = false;
                }
            }
        }
    }
}

package org.example.controller;

import org.example.dto.StudentLessonDto;
import org.example.service.LessonService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Scanner;
import java.util.function.Supplier;

public class LessonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LessonController.class);
    private static final Scanner in = new Scanner(System.in);

    public void run() {
        Configuration configuration = new Configuration().configure();
        try {
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            EntityManager entityManager = sessionFactory.createEntityManager();

            Supplier<EntityManager> supplier = () -> entityManager;
            LessonService service = new LessonService(supplier);

            boolean isRun = true;
            while (isRun) {
                System.out.println("Enter student id, for exit enter -1");
                long studentId = in.nextLong();
                if (studentId != -1) {
                    StudentLessonDto studentLessonDto = service.findNextLessonByStudentId(studentId);
                    try {
                        System.out.println(" Next lesson for student #" + studentId);
                        System.out.println(studentLessonDto.getTopic().getName());
                        System.out.println(studentLessonDto.getStartsAt() + " - " + studentLessonDto.getEndsAt());
                        System.out.println(studentLessonDto.getTeacher().getName());
                    } catch (NullPointerException e) {
                        LOGGER.warn("Student was been without lesson");
                    }
                } else {
                    isRun = false;
                }
            }
            session.close();
        } catch (Exception e) {
            LOGGER.error("Error: ", e);
        }
    }
}

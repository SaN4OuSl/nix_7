package org.example;

import org.example.controller.MainController;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class Module3Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Module3Main.class);

    public static void main(String[] args) {

        LOGGER.info("App started.");

        try (SessionFactory sessionFactory = new Configuration().
                setProperty("hibernate.connection.username", args[0]).
                setProperty("hibernate.connection.password", args[1]).configure().buildSessionFactory()) {
            EntityManager entityManager = sessionFactory.createEntityManager();

            MainController mainController = new MainController(args[0], args[1]);
            mainController.createOperation(entityManager, args[2]);
            mainController.exportData(entityManager, args[2], args[3]);
        } catch (HibernateException e) {
            LOGGER.error("Error: ", e);
            throw new RuntimeException(new HibernateException("Hibernate error!"));
        }
    }
}

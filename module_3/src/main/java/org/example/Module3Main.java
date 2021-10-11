package org.example;

import org.example.controller.MainController;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Module3Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Module3Main.class);

    public static void main(String[] args) {

        LOGGER.info("App started.");

        try {
            MainController mainController = new MainController(args[0], args[1]);
            mainController.createOperation(args[2]);
            mainController.exportData(args[2], args[3]);
        } catch (HibernateException e) {
            LOGGER.error("Error: ", e);
            throw new RuntimeException(new HibernateException("Hibernate error!"));
        }
    }
}

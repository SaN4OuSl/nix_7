package org.example.controller;

import org.example.dao.impl.ConnectionsDaoImpl;
import org.example.entity.Accounts;
import org.example.entity.OperationCategories;
import org.example.entity.Operations;
import org.example.entity.User;
import org.example.service.ExportDataToCsvService;
import org.example.service.OperationsControlService;
import org.example.service.impl.ConnectionsServiceImpl;
import org.example.service.impl.ExportDataToCsvServiceImpl;
import org.example.service.impl.OperationsControlServiceImpl;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainController implements AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private final Scanner in = new Scanner(System.in);
    private final ConnectionsServiceImpl connectionsService;
    private final EntityManager entityManager;
    private final SessionFactory sessionFactory;

    public MainController(String username, String password) {
        connectionsService = new ConnectionsServiceImpl(new ConnectionsDaoImpl(username, password));

        sessionFactory = new Configuration().
                setProperty("hibernate.connection.username", username).
                setProperty("hibernate.connection.password", password).configure().buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
    }

    public void createOperation(String usersTelephone) {
        OperationsControlService operationsControlService = new OperationsControlServiceImpl(entityManager);
        User user = operationsControlService.getUserByPhoneNumber(usersTelephone);

        System.out.println("\nADD TEST TRANSACTIONS");
        printUsersAccounts(user);
        String accountId = in.nextLine();

        try {
            entityManager.getTransaction().begin();
            Accounts account = operationsControlService.getAccountById(Long.parseLong(accountId));
            List<OperationCategories> expenseOperationsCategories = new ArrayList<>();
            Operations expenseOperation = new Operations(account, -1000, Instant.now());

            expenseOperationsCategories.add(new OperationCategories(
                    expenseOperation,
                    null,
                    operationsControlService.getExpenseCategoryByName("Shop")));

            expenseOperationsCategories.add(new OperationCategories(
                    expenseOperation,
                    null,
                    operationsControlService.getExpenseCategoryByName("Clothes")));

            expenseOperation.setOperationCategories(expenseOperationsCategories);

            operationsControlService.createOperation(user, expenseOperation);
            List<OperationCategories> incomeOperationCategories = new ArrayList<>();
            Operations incomeOperation = new Operations(account, 1500, Instant.now());

            incomeOperationCategories.add(new OperationCategories(
                    incomeOperation,
                    operationsControlService.getIncomeCategoryByName("Salary"),
                    null));

            incomeOperation.setOperationCategories(incomeOperationCategories);

            operationsControlService.createOperation(user, incomeOperation);
            entityManager.getTransaction().commit();
        } catch (NumberFormatException | HibernateException e) {
            LOGGER.error("Error: ", e);
            entityManager.getTransaction().rollback();
        }
    }

    public void exportData(String usersTelephone, String filePath) {
        OperationsControlService operationsControlService = new OperationsControlServiceImpl(entityManager);
        User user = operationsControlService.getUserByPhoneNumber(usersTelephone);

        System.out.println("\nEXPORT DATA");
        System.out.print("Select the period of report:\n" +
                "1. All time\n" +
                "2. Year\n" +
                "3. Month\n" +
                "4. Day\n");
        String j = in.nextLine();

        try (Connection connection = connectionsService.getConnection()) {
            ExportDataToCsvService exportDataToCsvService;

            printUsersAccounts(user);
            String accountId = in.nextLine();

            switch (j) {
                case "1":
                    exportDataToCsvService = new ExportDataToCsvServiceImpl(connection);
                    exportDataToCsvService.exportOperationsInPeriodToCsv(
                            user,
                            operationsControlService.getAccountById(Long.parseLong(accountId)),
                            LocalDateTime.of(0, 1, 1, 0, 0),
                            LocalDateTime.now(),
                            filePath);
                    break;
                case "2":
                    exportDataToCsvService = new ExportDataToCsvServiceImpl(connection);
                    exportDataToCsvService.exportOperationsInPeriodToCsv(
                            user,
                            operationsControlService.getAccountById(Long.parseLong(accountId)),
                            LocalDateTime.of(LocalDateTime.now().getYear(), 1, 1, 0, 0),
                            LocalDateTime.now(),
                            filePath);
                    break;
                case "3":
                    exportDataToCsvService = new ExportDataToCsvServiceImpl(connection);
                    exportDataToCsvService.exportOperationsInPeriodToCsv(
                            user,
                            operationsControlService.getAccountById(Long.parseLong(accountId)),
                            LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), 1, 0, 0),
                            LocalDateTime.now(),
                            filePath);
                    break;
                case "4":
                    exportDataToCsvService = new ExportDataToCsvServiceImpl(connection);
                    exportDataToCsvService.exportOperationsInPeriodToCsv(
                            user,
                            operationsControlService.getAccountById(Long.parseLong(accountId)),
                            LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0),
                            LocalDateTime.now(),
                            filePath);
                    break;
                default:
                    LOGGER.warn("Incorrect index, export data for all time");
                    exportDataToCsvService = new ExportDataToCsvServiceImpl(connection);
                    exportDataToCsvService.exportOperationsInPeriodToCsv(
                            user,
                            operationsControlService.getAccountById(Long.parseLong(accountId)),
                            LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0),
                            LocalDateTime.now(),
                            filePath);

            }
        } catch (NumberFormatException e) {
            LOGGER.error("Error: ", e);
        } catch (SQLException e) {
            LOGGER.error("Error: ", e);
            throw new RuntimeException("Error with connection");
        }
    }

    private void printUsersAccounts(User user) {
        System.out.println("Choose user's account id from this list");
        System.out.println("id| first name| second name| balance");
        for (int i = 0; i < user.getAccounts().size(); i++) {
            System.out.println(user.getAccounts().get(i).getId() + "| " + user.getFirstName() + "| " + user.getSecondName()
                    + "| " + user.getAccounts().get(i).getBalance());
        }
    }

    @Override
    public void close() throws HibernateException {
        sessionFactory.close();
    }
}

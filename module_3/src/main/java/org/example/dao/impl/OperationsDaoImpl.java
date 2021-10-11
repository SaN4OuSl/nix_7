package org.example.dao.impl;

import org.example.dao.OperationsDao;
import org.example.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class OperationsDaoImpl implements OperationsDao {

    private final EntityManager entityManager;
    private final Logger LOGGER = LoggerFactory.getLogger(OperationsDao.class);

    public OperationsDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createOperation(Operations operation) {
        LOGGER.info("Start create operation");
        entityManager.persist(operation);
        Query updateQuery = entityManager.createQuery(
                "update Accounts ac set ac.balance = ac.balance + :balance where ac.id = :id");

        updateQuery.setParameter("balance", operation.getResult());
        updateQuery.setParameter("id", operation.getAccount().getId());

        updateQuery.executeUpdate();
    }

    @Override
    public Accounts getAccountById(Long id) {
        LOGGER.info("Start get account by id");
        return entityManager.find(Accounts.class, id);
    }

    @Override
    public ExpenseCategory getExpenseCategoryByName(String name) {
        LOGGER.info("Start get expense category by name");
        TypedQuery<ExpenseCategory> query = entityManager.createQuery(
                "select ct from ExpenseCategory ct where ct.name = :name",
                ExpenseCategory.class);

        query.setParameter("name", name);
        query.setMaxResults(1);

        ExpenseCategory expenseCategory;
        try {
            expenseCategory = query.getSingleResult();
            return expenseCategory;
        } catch (NoResultException e) {
            LOGGER.warn("WARN: ", e);
            return null;
        }
    }

    @Override
    public IncomeCategory getIncomeCategoryByName(String name) {
        LOGGER.info("Start get income category by name");
        TypedQuery<IncomeCategory> query = entityManager.createQuery(
                "select ct from IncomeCategory ct where ct.name = :name",
                IncomeCategory.class);

        query.setParameter("name", name);
        query.setMaxResults(1);

        IncomeCategory incomeCategory;
        try {
            incomeCategory = query.getSingleResult();
            return incomeCategory;
        } catch (NoResultException e) {
            LOGGER.warn("WARN: ", e);
            return null;
        }
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        LOGGER.info("Start get user by phone number");
        TypedQuery<User> query = entityManager.createQuery(
                "select us from User us where us.phoneNumber = :phone_number",
                User.class);

        query.setParameter("phone_number", phoneNumber);
        query.setMaxResults(1);

        User user;
        try {
            user = query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            LOGGER.warn("WARN: ", e);
            return null;
        }
    }

    @Override
    public void addOperationCategories(List<OperationCategories> operationCategories) {
        LOGGER.info("Start add operation categories");
        for (OperationCategories operationCategory : operationCategories) {
            entityManager.persist(operationCategory);
        }
    }
}

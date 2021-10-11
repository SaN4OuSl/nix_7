package org.example.service.impl;


import org.example.dao.OperationsDao;
import org.example.dao.impl.OperationsDaoImpl;
import org.example.entity.*;
import org.example.service.OperationsControlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class OperationsControlServiceImpl implements OperationsControlService {
    private final Logger LOGGER = LoggerFactory.getLogger(OperationsControlServiceImpl.class);
    private final OperationsDao operationsDao;

    public OperationsControlServiceImpl(EntityManager entityManager) {
        this.operationsDao = new OperationsDaoImpl(entityManager);
    }

    @Override
    public void createOperation(User user, Operations operation) {
        LOGGER.info("Start adding operation to account.");

        if (operation == null || operation.getOperationCategories().size() == 0) {
            LOGGER.error("Error while creating new operation! Operation categories is null or empty!");
            throw new IllegalArgumentException("Operation must got categories!");
        }

        if (!user.getId().equals(operation.getAccount().getUser().getId())) {
            LOGGER.error("Error while creating new operation!" +
                            " Account with id {} doesn't belong to user with id {}!",
                    operation.getAccount().getId(), user.getId());
            throw new RuntimeException(new IllegalAccessException("Current account doesn't belong to user!"));
        }

        if (operation.getResult() == 0) {
            LOGGER.error("Error while creating new operation!" +
                    "Operation result must be grater than 0!");
            throw new IllegalArgumentException("Operation can`t produce zero result!");
        }

        if (operation.getAccount().getBalance() + operation.getResult() < 0) {
            LOGGER.error("Error while creating new operation!" +
                    "Balance on account after operation lower than 0");
            throw new IllegalArgumentException("Minus balance after that operation!");
        }

        operationsDao.createOperation(operation);
        addOperationCategories(operation.getOperationCategories());

        if (operation.getType() == OperationTypes.EXPENSE && operation.getResult() > 0 ||
                operation.getType() == OperationTypes.INCOME && operation.getResult() < 0) {
            LOGGER.error("Error while creating new operation!" +
                    "Operation type doesn't fit the result!");
            throw new IllegalArgumentException("Operation type doesn't fit the result!");
        }

        LOGGER.info("Successful adding operation with id {} to account with id {}.",
                operation.getId(), operation.getAccount().getId());

    }

    @Override
    public Accounts getAccountById(Long id) {
        LOGGER.info("Start getting account with id {}", id);
        Accounts account = operationsDao.getAccountById(id);
        if (account == null) {
            LOGGER.error("Error get account by id. No account with id {}!", id);
            throw new IllegalArgumentException("No account with current id");
        }
        LOGGER.info("Successful getting account with id {}", id);
        return account;
    }

    @Override
    public ExpenseCategory getExpenseCategoryByName(String name) {
        LOGGER.info("Start getting expense category with name {}",
                name);
        ExpenseCategory expenseCategory = operationsDao.getExpenseCategoryByName(name);
        if (expenseCategory == null) {
            LOGGER.error("Error get expense category by name. No expense category with name {}!", name);
            throw new IllegalArgumentException("No expense category with current name");
        }
        LOGGER.info("Successful getting expense category with name {}", name);
        return expenseCategory;
    }

    @Override
    public IncomeCategory getIncomeCategoryByName(String name) {
        LOGGER.info("Start getting income category with name {}",
                name);
        IncomeCategory incomeCategory = operationsDao.getIncomeCategoryByName(name);
        if (incomeCategory == null) {
            LOGGER.error("Error get income category by name. No income category with name {}!", name);
            throw new IllegalArgumentException("No income category with current name");
        }
        LOGGER.info("Successful getting income category with name {}", name);
        return incomeCategory;
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        LOGGER.info("Start getting user with phone number {}", phoneNumber);
        User user = operationsDao.getUserByPhoneNumber(phoneNumber);
        if (user == null) {
            LOGGER.error("Error get user by phone number. No user with phone number {}!", phoneNumber);
            throw new IllegalArgumentException("No user with current phone_number");
        }
        LOGGER.info("Successful getting user with phone number {}", phoneNumber);
        return user;
    }

    @Override
    public void addOperationCategories(List<OperationCategories> operationCategories) {
        LOGGER.info("Start adding operation categories to database");
        for (OperationCategories operationCategory : operationCategories) {
            IncomeCategory incomeCategory = operationCategory.getIncomeCategory();
            ExpenseCategory expenseCategory = operationCategory.getExpenseCategory();
            Operations operation = operationCategory.getOperation();

            if (incomeCategory == null && expenseCategory == null ||
                    incomeCategory != null && expenseCategory != null) {
                LOGGER.error("Error adding operation category. Operation category can have only one type of category!");
                throw new IllegalArgumentException("Must be one type of category!");
            }

            if (operation.getType() == null) {
                if (incomeCategory == null) {
                    operation.setType(OperationTypes.EXPENSE);
                } else {
                    operation.setType(OperationTypes.INCOME);
                }
            } else {
                if (operation.getType() == OperationTypes.EXPENSE && incomeCategory != null ||
                        operation.getType() == OperationTypes.INCOME && expenseCategory != null) {
                    LOGGER.error("Error adding operation category. All categories must have the same type!");
                    throw new IllegalArgumentException("All categories must have the same type!");
                }
            }
        }
        operationsDao.addOperationCategories(operationCategories);
        LOGGER.info("Successful adding operation categories to database");
    }
}

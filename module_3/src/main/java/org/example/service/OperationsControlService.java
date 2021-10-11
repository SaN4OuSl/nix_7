package org.example.service;

import org.example.entity.*;

import java.util.List;

public interface OperationsControlService {
    void createOperation(User user, Operations operation);

    Accounts getAccountById(Long id);

    ExpenseCategory getExpenseCategoryByName(String name);

    IncomeCategory getIncomeCategoryByName(String name);

    User getUserByPhoneNumber(String phoneNumber);

    void addOperationCategories(List<OperationCategories> operationCategories);
}

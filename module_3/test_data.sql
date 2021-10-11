INSERT INTO module_3_db.users (id, email, first_name, phonenumber, second_name) VALUES (1, 'alexlap@gmail.com', 'Alex', '+380681002233', 'Laptev');
INSERT INTO module_3_db.users (id, email, first_name, phonenumber, second_name) VALUES (2, 'alexpal@gmail.com', 'Alex', '+380681234533', 'Palvalev');

INSERT INTO module_3_db.accounts (id, balance, user_id) VALUES (1, 3800, 1);
INSERT INTO module_3_db.accounts (id, balance, user_id) VALUES (2, 3500, 1);

INSERT INTO module_3_db.expense_categories (id, name) VALUES (1, 'Shop');
INSERT INTO module_3_db.expense_categories (id, name) VALUES (2, 'Clothes');
INSERT INTO module_3_db.expense_categories (id, name) VALUES (3, 'Products');
INSERT INTO module_3_db.expense_categories (id, name) VALUES (4, 'Entertainment');

INSERT INTO module_3_db.income_categories (id, name) VALUES (1, 'Salary');
INSERT INTO module_3_db.income_categories (id, name) VALUES (2, 'Gift');
INSERT INTO module_3_db.income_categories (id, name) VALUES (3, 'Found');
INSERT INTO module_3_db.income_categories (id, name) VALUES (4, 'Birthday');
INSERT INTO module_3_db.income_categories (id, name) VALUES (5, 'New Year');

INSERT INTO module_3_db.operations (id, date_time, result, account_id) VALUES (21, '2021-11-10 19:40:40', -1000, 1);
INSERT INTO module_3_db.operations (id, date_time, result, account_id) VALUES (22, '2021-11-10 19:40:41', 1500, 1);
INSERT INTO module_3_db.operations (id, date_time, result, account_id) VALUES (23, '2021-11-10 21:45:10', -1000, 1);
INSERT INTO module_3_db.operations (id, date_time, result, account_id) VALUES (24, '2021-11-10 21:45:10', 1500, 1);

INSERT INTO module_3_db.operation_categories (id, expense_category_id, income_category_id, operation_id) VALUES (1, 1, NULL, 21);
INSERT INTO module_3_db.operation_categories (id, expense_category_id, income_category_id, operation_id) VALUES (2, 2, NULL, 21);
INSERT INTO module_3_db.operation_categories (id, expense_category_id, income_category_id, operation_id) VALUES (3, NULL, 1, 22);
INSERT INTO module_3_db.operation_categories (id, expense_category_id, income_category_id, operation_id) VALUES (4, 1, NULL, 23);
INSERT INTO module_3_db.operation_categories (id, expense_category_id, income_category_id, operation_id) VALUES (5, 2, NULL, 23);
INSERT INTO module_3_db.operation_categories (id, expense_category_id, income_category_id, operation_id) VALUES (6, NULL, 1, 24);
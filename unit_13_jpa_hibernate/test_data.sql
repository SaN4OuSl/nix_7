    INSERT INTO unit_13_db.courses (id, title) VALUES (1, 'Java courses');
    INSERT INTO unit_13_db.courses (id, title) VALUES (2, 'Python courses');
    INSERT INTO unit_13_db.courses (id, title) VALUES (3, 'JavaScript courses');

    INSERT INTO unit_13_db.groups_of_course (id, course_id) VALUES (1, 2);
    INSERT INTO unit_13_db.groups_of_course (id, course_id) VALUES (2, 1);

    INSERT INTO unit_13_db.lessons (id, date, course_id, teacher_id, topic_id) VALUES (1, '2021-10-06 19:00', 1, 2, 1);
    INSERT INTO unit_13_db.lessons (id, date, course_id, teacher_id, topic_id) VALUES (2, '2021-10-06 14:00', 1, 1, 1);
    INSERT INTO unit_13_db.lessons (id, date, course_id, teacher_id, topic_id) VALUES (3, '2021-10-09 14:00', 2, 2, 3);

    INSERT INTO unit_13_db.students (id, age, firstName, secondName, group_id) VALUES (1, 18, 'Alex', 'Laptev', 2);
    INSERT INTO unit_13_db.students (id, age, firstName, secondName, group_id) VALUES (2, 20, 'Alex', 'Palvalev', 2);
    INSERT INTO unit_13_db.students (id, age, firstName, secondName, group_id) VALUES (3, 21, 'Vasya', 'Pupkin', 1);

    INSERT INTO unit_13_db.teachers (id, age, firstName, secondName) VALUES (1, 30, 'Iegor', 'Fentusov');
    INSERT INTO unit_13_db.teachers (id, age, firstName, secondName) VALUES (2, 25, 'Mikhail', 'Aspect');

    INSERT INTO unit_13_db.topics (id, title) VALUES (1, 'unit 11 reflection');
    INSERT INTO unit_13_db.topics (id, title) VALUES (2, 'unit 12 csv mapping');
    INSERT INTO unit_13_db.topics (id, title) VALUES (3, 'Python');
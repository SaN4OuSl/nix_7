INSERT INTO unit_13_db.lessons (id, ends_at, name, starts_at,teacher_id,topic_id) VALUES (2, '2021-11-01 15:00:00', 'bla sa','2021-11-01 12:00:00', 1, 2);
INSERT INTO unit_13_db.lessons (id, ends_at, name, starts_at,teacher_id,topic_id) VALUES (1, '2021-11-01 14:00:00', 'bla bla','2021-11-01 11:00:00', 2, 1);
INSERT INTO unit_13_db.lessons (id, ends_at, name, starts_at,teacher_id,topic_id) VALUES (3, '2021-11-01 16:00:00', 'bla bsasla','2021-11-01 13:00:00', 3, 1);

INSERT INTO unit_13_db.students (id, name, group_id) VALUES (1, 'Alex Laptev', 1);
INSERT INTO unit_13_db.students (id, name, group_id) VALUES (2, 'Alex Palvalev', 2);
INSERT INTO unit_13_db.students (id, name, group_id) VALUES (3, 'Alex dsd', 3);

INSERT INTO unit_13_db.teachers (id, name) VALUES (1, 'Iegor Fentusov');
INSERT INTO unit_13_db.teachers (id, name) VALUES (2, 'Mikhayl Gorbunov');

INSERT INTO unit_13_db.courses_topics (course_id, topic_id) VALUES (1, 1);
INSERT INTO unit_13_db.courses_topics (course_id, topic_id) VALUES (2, 2);
INSERT INTO unit_13_db.courses_topics (course_id, topic_id) VALUES (3, 3);

INSERT INTO unit_13_db.course_groups (id, name, course_id) VALUES (1, 'nix_7',1);
INSERT INTO unit_13_db.course_groups (id, name, course_id) VALUES (2, 'nix_6',2);
INSERT INTO unit_13_db.course_groups (id, name, course_id) VALUES (3, 'nix_5',3);

INSERT INTO unit_13_db.courses (id, name) VALUES (1, 'Java');
INSERT INTO unit_13_db.courses (id, name) VALUES (2, 'Python');
INSERT INTO unit_13_db.courses (id, name) VALUES (3, 'JavaScript');

INSERT INTO unit_13_db.topics (id, name) VALUES (1, 'unit 11');
INSERT INTO unit_13_db.topics (id, name) VALUES (2, 'unit 12');
INSERT INTO unit_13_db.topics (id, name) VALUES (3, 'unit 13');
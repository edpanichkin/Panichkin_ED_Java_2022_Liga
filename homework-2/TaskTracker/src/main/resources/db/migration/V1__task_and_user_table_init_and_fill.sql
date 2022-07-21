DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS tasks;

CREATE TABLE users(
                      id SERIAL PRIMARY KEY,
                      user_name VARCHAR(64) NOT NULL
);

CREATE TABLE tasks(
                      id SERIAL PRIMARY KEY,
                      task_name VARCHAR(255) NOT NULL,
                      task_info VARCHAR(255) NOT NULL,
                      task_status INTEGER,
                      user_id INTEGER REFERENCES users (id) ON DELETE CASCADE NOT NULL,
                      date DATE
);

-- ALTER TABLE tasks
--     ADD FOREIGN KEY(user_id) REFERENCES users;

INSERT INTO users (user_name)
VALUES ('User1'),
       ('User2'),
       ('User3');

INSERT INTO tasks (task_name, task_info, user_id, date, task_status)
VALUES ('Task_name_1', 'Task_info_1',1,'2022-12-31',0),
       ('Task_name_2', 'Task_info_2',1,'2022-12-31',0),
       ('Task_name_3', 'Task_info_3',2,'2022-12-31',1),
       ('Task_name_4', 'Task_info_4',3,'2022-12-31',0),
       ('Task_name_5', 'Task_info_5',2,'2022-12-31',2),
       ('Task_name_6', 'Task_info_6',2,'2022-12-31',0),
       ('Task_name_7', 'Task_info_7',1,'2022-12-31',0),
       ('Task_name_8', 'Task_info_8',1,'2022-12-31',1),
       ('Task_name_9', 'Task_info_9',2,'2022-12-31',0),
       ('Task_name_10', 'Task_info_10',3,'2022-12-31',0),
       ('Task_name_11', 'Task_info_11',2,'2022-12-31',1),
       ('Task_name_12', 'Task_info_12',1,'2022-12-31',2),
       ('Task_name_13', 'Task_info_13',1,'2022-12-31',2),
       ('Task_name_14', 'Task_info_14',2,'2022-12-31',0),
       ('Task_name_15', 'Task_info_15',3,'2022-12-31',0);
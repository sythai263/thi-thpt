CREATE TABLE students
(
    id   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

INSERT INTO students(name)
VALUES ('Le Sy Thai'),
       ('Huynh Kieu Tan Loc')
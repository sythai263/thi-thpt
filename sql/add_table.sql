CREATE TABLE school
(
    id      BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NULL,
    isExit  BOOLEAN DEFAULT true
);

CREATE TABLE student
(
    id           BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(255) NOT NULL,
    studentClass VARCHAR(255) NOT NULL,
    schoolId     BIGINT,
    priority     INT          NOT NULL,
    FOREIGN KEY (schoolId) REFERENCES school (id),
    groupSubject CHAR(10)     NOT NULL,
    isExit       BOOLEAN DEFAULT true
);

CREATE TABLE exam
(
    id        BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    studentId BIGINT,
    FOREIGN KEY (studentId) REFERENCES student (id),
    schoolId  BIGINT,
    FOREIGN KEY (schoolId) REFERENCES school (id),
    date      DATE         NOT NULL,
    room      VARCHAR(255) NOT NULL,
    subject   VARCHAR(255) NOT NULL,
    isExit    BOOLEAN DEFAULT true
);

INSERT INTO school(name, isExit)
VALUES ('Vo Truong Toan', true),
       ('Phan Boi Chau', true);

INSERT INTO student(name, studentClass, schoolId, priority, groupSubject)
VALUES ('Le Sy Thai', '12C11', 1, 1, 'A'),
       ('Huynh Kieu Tan Loc', '12C12', 1, 1, 'A');

INSERT INTO exam(studentId, schoolId, date, room, subject, isExit)
VALUES (1, 1, '2023-06-06', 'A11', 'Toan', true),
       (1, 1, '2023-06-06', 'A11', 'Ly', true),
       (1, 1, '2023-06-06', 'A11', 'Hoa', true);

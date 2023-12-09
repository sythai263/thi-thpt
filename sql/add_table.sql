CREATE TABLE school
(
    schoolId   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    schoolName VARCHAR(255) NOT NULL,
    isExit     BOOLEAN DEFAULT true
);

CREATE TABLE student
(
    id           BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(255) NOT NULL,
    studentClass VARCHAR(255) NOT NULL,
    schoolId     BIGINT,
    FOREIGN KEY (schoolId) REFERENCES school (schoolId),
    groupSubject CHAR(10)     NOT NULL,
    isExit       BOOLEAN DEFAULT true
);

CREATE TABLE test
(
    testId    BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    studentId BIGINT,
    FOREIGN KEY (studentId) REFERENCES student (id),
    schoolId  BIGINT,
    FOREIGN KEY (schoolId) REFERENCES school (schoolId),
    date      DATE         NOT NULL,
    room      VARCHAR(255) NOT NULL,
    subject   VARCHAR(255) NOT NULL,
    isExit    BOOLEAN DEFAULT true
);

INSERT INTO school(schoolName, isExit)
VALUES ('Vo Truong Toan', true),
       ('Phan Boi Chau', true);

INSERT INTO student(name, studentClass, schoolId, groupSubject)
VALUES ('Le Sy Thai', '12C11', 1, 'A'),
       ('Huynh Kieu Tan Loc', '12C12', 1, 'A');

INSERT INTO test(studentId, schoolId, date, room, subject, isExit)
VALUES (1, 1, '2023-06-06', 'A11', 'Toan', true),
       (1, 1, '2023-06-06', 'A11', 'Ly', true),
       (1, 1, '2023-06-06', 'A11', 'Hoa', true);

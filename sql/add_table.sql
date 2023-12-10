CREATE TABLE school
(
    id       BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(255) NOT NULL,
    address  VARCHAR(255) NULL,
    existing BOOLEAN DEFAULT true
);

CREATE TABLE student
(
    id           BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(255) NOT NULL,
    studentClass VARCHAR(255) NOT NULL,
    schoolId     BIGINT,
    priority     INT          NULL,
    FOREIGN KEY (schoolId) REFERENCES school (id),
    groupSubject CHAR(10)     NOT NULL,
    existing     BOOLEAN DEFAULT true
);

CREATE TABLE exam
(
    id        BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    studentId BIGINT,
    FOREIGN KEY (studentId) REFERENCES student (id),
    schoolId  BIGINT,
    FOREIGN KEY (schoolId) REFERENCES school (id),
    dueDate   DATE         NOT NULL,
    room      VARCHAR(255) NOT NULL,
    subject   VARCHAR(255) NOT NULL,
    existing  BOOLEAN DEFAULT true
);

INSERT INTO school(name, existing, address)
VALUES ('Vo Truong Toan', true, '60 Nguyen Thai Hoc, Cam Ranh, Khanh Hoa'),
       ('Phan Boi Chau', true, '185 Tran Hung Dao, Tien An, Bac Ninh');

INSERT INTO student(name, studentClass, schoolId, priority, groupSubject, existing)
VALUES ('Le Sy Thai', '12C11', 1, 2, 'A', true),
       ('Huynh Kieu Tan Loc', '12C12', 1, 1, 'A', true);

INSERT INTO exam(studentId, schoolId, dueDate, room, subject, existing)
VALUES (1, 1, '2023-06-06', 'A11', 'Toan', true),
       (1, 1, '2023-06-06', 'A11', 'Ly', true),
       (1, 1, '2023-06-06', 'A11', 'Hoa', true);

package com.ptithcm.services;

import com.ptithcm.common.ConnectionDB;
import com.ptithcm.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public StudentService() {

    }

    public List<Student> getStudents() {
        try (Connection connection = ConnectionDB.getConnection(); Statement statement = connection.createStatement()) {
            String sql = "SELECT " +
                    "student.id AS id, " +
                    "student.name AS name, " +
                    "student.studentClass, " +
                    "student.groupSubject, " +
                    "student.isExit, " +
                    "student.schoolId, " +
                    "school.schoolName " +
                    "FROM student " +
                    "JOIN school ON student.schoolId = school.schoolId";
            ResultSet rs = statement.executeQuery(sql);
            List<Student> studentList = new ArrayList<>();
            while (rs.next()) {
                studentList.add(this.mapToStudentEntity(rs));
            }

            return studentList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getStudentByName(String name) {
        String sql = "SELECT * FROM student WHERE name = ?";

        try (
                Connection connection = ConnectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();
            List<Student> students = new ArrayList<>();

            while (rs.next()) {
                students.add(mapToStudentEntity(rs));
            }

            return students;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student mapToStudentEntity(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setStudentClass(rs.getString("studentClass"));
        student.setSchoolId(rs.getLong("schoolId"));
        student.setGroupSubject(rs.getString("groupSubject"));
        student.setSchoolName(rs.getString("schoolName"));
        student.setIsExit(rs.getBoolean("isExit"));
        return student;
    }
}

package com.ptithcm.services;

import com.ptithcm.common.ConnectionDB;
import com.ptithcm.entities.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public StudentService() {

    }

    public List<Student> getStudents() {
        try (
            Connection connection = ConnectionDB.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT " +
                "student.id AS id, " +
                "student.name AS name, " +
                "student.studentClass, " +
                "student.groupSubject, " +
                "student.existing as existing, " +
                "student.schoolId as schoolId, " +
                "school.name as schoolName, " +
                "student.priority " +
                "FROM student " +
                "JOIN school ON student.schoolId = school.id " +
                "WHERE student.existing = true";
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

    public int addStudent(Student student) {
        String sql = "INSERT INTO student (name, studentClass, schoolId, priority, groupSubject) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getStudentClass());
            statement.setLong(3, student.getSchoolId());
            statement.setInt(4, student.getPriority());
            statement.setString(5, student.getGroupSubject());

            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateStudent(Student student) {
        String sql = "UPDATE student " +
            "SET name = ?, studentClass = ?, schoolId = ?, priority = ?, groupSubject = ? " +
            "WHERE id = ?";

        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getStudentClass());
            statement.setLong(3, student.getSchoolId());
            statement.setInt(4, student.getPriority());
            statement.setString(5, student.getGroupSubject());
            statement.setLong(6, student.getId());  // Assume student has getId() method

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteStudent(Student student) {
        String sql = "UPDATE student SET existing = false WHERE id = ?";

        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, student.getId());  // Assume student has getId() method

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Student> getStudentByName(String name) {
        String sql = "SELECT " +
            "student.id AS id, " +
            "student.name AS name, " +
            "student.studentClass, " +
            "student.groupSubject, " +
            "student.existing as existing, " +
            "student.schoolId as schoolId, " +
            "school.name as schoolName, " +
            "student.priority " +
            "FROM student " +
            "JOIN school ON student.schoolId = school.id " +
            "WHERE student.name LIKE ? AND student.existing = ?";

        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, "%" + name);
            statement.setBoolean(2, true);

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

    public List<Student> getStudentByGroupSubject(String groupSubject) {
        String sql = "SELECT " +
            "student.id AS id, " +
            "student.name AS name, " +
            "student.studentClass, " +
            "student.groupSubject, " +
            "student.existing as existing, " +
            "student.schoolId as schoolId, " +
            "school.name as schoolName, " +
            "student.priority " +
            "FROM student " +
            "JOIN school ON student.schoolId = school.id " +
            "WHERE student.groupSubject LIKE ? AND student.existing = ?";

        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, groupSubject);
            statement.setBoolean(2, true);

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

    public Student getStudentById(Long id) {
        String sql = "SELECT " +
            "st.id AS id, " +
            "st.name AS name, " +
            "st.studentClass, " +
            "st.groupSubject, " +
            "st.existing as existing, " +
            "st.schoolId as schoolId, " +
            "sc.name as schoolName, " +
            "st.priority " +
            "FROM student st " +
            "JOIN school sc ON st.schoolId = sc.id " +
            "WHERE st.id = ? AND st.existing = ?";

        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            statement.setBoolean(2, true);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return mapToStudentEntity(rs);
            }

            return null;
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
        student.setPriority(rs.getInt("priority"));
        student.setExisting(rs.getBoolean("existing"));
        return student;
    }
}

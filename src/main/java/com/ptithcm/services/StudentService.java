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
                    "student.existing as existing, " +
                    "student.schoolId as schoolId, " +
                    "school.name as schoolName, " +
                    "student.priority " +
                    "FROM student " +
                    "JOIN school ON student.schoolId = school.id " +
                    "WHERE student.existing = true";
            System.out.println(sql);
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

    public void addStudent(Student student) {
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

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Sinh viên đã được thêm thành công!");
            } else {
                System.out.println("Không thể thêm sinh viên. Vui lòng thử lại.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Student student) {
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

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Cập nhật sinh viên thành công!");
            } else {
                System.out.println("Cập nhật sinh viên thất bại. Vui lòng thử lại.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudent(Student student) {
        String sql = "UPDATE student SET existing = false WHERE id = ?";

        try (
                Connection connection = ConnectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, student.getId());  // Assume student has getId() method

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Xoá sinh viên thành công!");
            } else {
                System.out.println("Xoá sinh viên thất bại. Vui lòng thử lại.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Student> getStudentByName(String nameOrId) {
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
                "WHERE student.name LIKE ? OR student.id = ?";

        try (
                Connection connection = ConnectionDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            // Thêm dấu '%' để tìm kiếm theo mẫu tên
            statement.setString(1, "%" + nameOrId + "%");
            statement.setString(2, nameOrId);

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
        student.setPriority(rs.getInt("priority"));
        student.setExisting(rs.getBoolean("existing"));
        return student;
    }
}

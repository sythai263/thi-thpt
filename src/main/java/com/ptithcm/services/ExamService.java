package com.ptithcm.services;

import com.ptithcm.common.ConnectionDB;
import com.ptithcm.entities.Exam;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamService {

    public ExamService() {
    }

    public int addExam(Exam exam) {
        String sql = "INSERT INTO exam (studentId, schoolId, dueDate, room, subject, existing) VALUES (?, ?, ?, ?, ?, ?)";
        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, exam.getStudentId());
            statement.setLong(2, exam.getSchoolId());
            statement.setDate(3, new Date(exam.getDueDate().getTime()));
            statement.setString(4, exam.getRoom());
            statement.setString(5, exam.getSubject());
            statement.setBoolean(6, true);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateExam(Exam exam, Long id) {
        String sql = "UPDATE exam SET studentId = ?, schoolId = ?, dueDate = ?, room = ?, subject = ? WHERE id = ? ";
        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, exam.getStudentId());
            statement.setLong(2, exam.getSchoolId());
            statement.setDate(3, new Date(exam.getDueDate().getTime()));
            statement.setString(4, exam.getRoom());
            statement.setString(5, exam.getSubject());
            statement.setLong(6, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Exam> getAllExam() {
        String sql = "SELECT ex.*, " +
            "st.name AS studentName, " +
            "sc.name as schoolName " +
            "FROM exam ex " +
            "JOIN student st ON ex.studentId = st.id " +
            "JOIN school sc ON ex.schoolId = sc.id ";
        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            ResultSet rs = statement.executeQuery();
            List<Exam> examList = new ArrayList<>();
            while (rs.next()) {
                examList.add(mapToExamEntity(rs));
            }
            return examList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Exam mapToExamEntity(ResultSet rs) throws SQLException {
        Exam exam = new Exam();
        exam.setId(rs.getLong("id"));
        exam.setStudentId(rs.getLong("studentId"));
        exam.setStudentName(rs.getString("studentName"));
        exam.setSchoolId(rs.getLong("schoolId"));
        exam.setDueDate(rs.getDate("dueDate"));
        exam.setSchoolName(rs.getString("schoolName"));
        exam.setRoom(rs.getString("room"));
        exam.setSubject(rs.getString("subject"));
        exam.setExisting(rs.getBoolean("existing"));
        return exam;
    }
}

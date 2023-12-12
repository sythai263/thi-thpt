package com.ptithcm.services;

import com.ptithcm.common.ConnectionDB;
import com.ptithcm.entities.School;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolService {

    public SchoolService() {
    }

    public List<School> getSchool() {
        String sql = "SELECT *  FROM school";
        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)

        ) {
            ResultSet rs = statement.executeQuery(sql);
            List<School> schoolList = new ArrayList<>();
            while (rs.next()) {
                schoolList.add(this.mapToSchoolEntity(rs));
            }

            return schoolList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addSchool(School school) {
        String sql = "INSERT INTO school (name, existing, address) VALUES (?, ?, ?)";
        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)

        ) {
            statement.setString(1, school.getName());
            statement.setBoolean(2, true);
            statement.setString(3, school.getAddress());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSchool(School school) {
        String sql = "UPDATE school SET name=?, address=?, existing=?; ";
        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)

        ) {
            statement.setString(1, school.getName());
            statement.setBoolean(2, school.getExisting());
            statement.setString(3, school.getAddress());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Trường đã được cập nhật thành công!");
            } else {
                System.out.println("Không thể cập nhật trường. Vui lòng thử lại.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public School getSchoolById(Long schoolId) {
        String sql = "SELECT *  FROM school WHERE id = ?";
        try (
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, schoolId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return mapToSchoolEntity(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public School mapToSchoolEntity(ResultSet rs) throws SQLException {
        School school = new School();
        school.setId(rs.getLong("id"));
        school.setName(rs.getString("name"));
        school.setAddress(rs.getString("address"));
        school.setExisting(rs.getBoolean("existing"));
        return school;
    }

}

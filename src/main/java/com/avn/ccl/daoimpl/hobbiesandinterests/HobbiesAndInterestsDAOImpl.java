/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.hobbiesandinterests;

import com.avn.ccl.dao.hobbiesandinterests.HobbiesAndInterestsDAO;
import com.avn.ccl.model.hobbiesandinterests.HobbiesAndInterest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : HobbyDAOImpl
 * @Created on : Jan 12, 2016, 2:42:59 PM
 */
public class HobbiesAndInterestsDAOImpl implements HobbiesAndInterestsDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getHobbyDropdownList(int status) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT ID, DESCRIPTION FROM AVN_HOBBIESINTERESTS WHERE STATUS = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("ID"), resultSet.getString("DESCRIPTION"));
            }
        } catch (SQLException exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return list;
    }

    @Override
    public HobbiesAndInterest getHobbiesAndInterestById(String hobby_id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        HobbiesAndInterest hobbiesAndInterest = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    DESCRIPTION, "
                    + "    STATUS, "
                    + "    SORTID, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME,"
                    + "    CREATEDUSER "
                    + "FROM "
                    + "    AVN_HOBBIESINTERESTS "
                    + "WHERE "
                    + "    ID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(hobby_id));
            resultSet = statement.executeQuery();
            hobbiesAndInterest = new HobbiesAndInterest();
            while (resultSet.next()) {
                hobbiesAndInterest.setHobby_id(resultSet.getInt("ID"));
                hobbiesAndInterest.setHobby_description(resultSet.getString("DESCRIPTION"));
                hobbiesAndInterest.setStatus(resultSet.getInt("STATUS"));
                hobbiesAndInterest.setSortid(resultSet.getInt("SORTID"));
                hobbiesAndInterest.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                hobbiesAndInterest.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
                hobbiesAndInterest.setCreateduser(resultSet.getString("CREATEDUSER"));
            }
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception exception) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception exception) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception exception) {
                }
            }
        }
        return hobbiesAndInterest;
    }

}

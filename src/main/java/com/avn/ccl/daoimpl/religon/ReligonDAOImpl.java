/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.religon;

import com.avn.ccl.dao.religon.ReligonDAO;
import com.avn.ccl.model.religon.Religon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : ReligonDAOImpl
 * @Date : Jul 4, 2015 12:17:21 PM
 */
public class ReligonDAOImpl implements ReligonDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getReligonDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> religonList = null;
        try {
            String sql = "SELECT RELIGONID, DESCRIPTION FROM AVN_RELIGON ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            religonList = new LinkedHashMap<>();
            religonList.put("", "-- Select --");
            while (resultSet.next()) {
                religonList.put(resultSet.getString("RELIGONID"), resultSet.getString("DESCRIPTION"));
            }
        } catch (Exception exception) {
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
        return religonList;
    }

    @Override
    public Religon getReligonById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Religon addressType = null;
        try {
            String sql = "SELECT "
                    + "    RELIGONID, "
                    + "    DESCRIPTION "
                    + "FROM "
                    + "    AVN_RELIGON "
                    + "WHERE "
                    + "    RELIGONID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            resultSet = statement.executeQuery();
            addressType = new Religon();
            while (resultSet.next()) {
                addressType.setReligonid(resultSet.getString("RELIGONID"));
                addressType.setDescription(resultSet.getString("DESCRIPTION"));
            }
        } catch (SQLException | NumberFormatException exception) {
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
        return addressType;
    }
}

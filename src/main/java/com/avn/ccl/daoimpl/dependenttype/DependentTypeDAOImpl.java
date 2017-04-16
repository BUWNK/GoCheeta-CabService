/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.dependenttype;

import com.avn.ccl.dao.dependenttype.DependentTypeDAO;
import com.avn.ccl.model.dependentrype.DependentType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : DependentTypeDAOImpl
 * @Created on : Aug 6, 2015, 8:58:57 AM
 */
public class DependentTypeDAOImpl implements DependentTypeDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getDependentTypeDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT ID, DESCRIPTION FROM AVN_DEPENDENTTYPE ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("ID"), resultSet.getString("DESCRIPTION"));
            }
        } catch (Exception exception) {
            throw exception;
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
        return list;
    }

    @Override
    public DependentType getDependentTypeById(String dependentid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DependentType dependentType = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    DESCRIPTION, "
                    + "    SORTID, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME "
                    + "FROM "
                    + "    AVN_DEPENDENTTYPE "
                    + "WHERE "
                    + "    ID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(dependentid));
            resultSet = statement.executeQuery();
            dependentType = new DependentType();
            while (resultSet.next()) {
                dependentType.setId(resultSet.getString("ID"));
                dependentType.setDescription(resultSet.getString("DESCRIPTION"));
                dependentType.setSortid(resultSet.getString("SORTID"));
                dependentType.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                dependentType.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
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
        return dependentType;
    }
}

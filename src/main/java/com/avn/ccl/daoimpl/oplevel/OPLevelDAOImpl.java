/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.oplevel;

import com.avn.ccl.dao.oplevel.OPLevelDAO;
import com.avn.ccl.model.oplevel.OPLevel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : OPLevelDAOImpl
 * @Date : Jul 23, 2015 2:41:45 PM
 */
public class OPLevelDAOImpl implements OPLevelDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getLevelDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT ID, DESCRIPTION FROM AVN_BUSLEVEL ORDER BY DESCRIPTION";
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
    public OPLevel getLevelById(String levelid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OPLevel level = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    CODE, "
                    + "    DESCRIPTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER "
                    + "FROM "
                    + "    AVN_BUSLEVEL "
                    + "WHERE "
                    + "    ID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(levelid));
            resultSet = statement.executeQuery();
            level = new OPLevel();
            while (resultSet.next()) {
                level.setId(resultSet.getString("ID"));
                level.setCode(resultSet.getString("CODE"));
                level.setDescription(resultSet.getString("DESCRIPTION"));
                level.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                level.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
                level.setCreateduser(resultSet.getString("CREATEDUSER"));
            }
        } catch (SQLException | NumberFormatException exception) {
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
        return level;
    }

    @Override
    public OPLevel getLevelByCode(String levelcode) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OPLevel level = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    CODE, "
                    + "    DESCRIPTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER "
                    + "FROM "
                    + "    AVN_BUSLEVEL "
                    + "WHERE "
                    + "    CODE = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(levelcode));
            resultSet = statement.executeQuery();
            level = new OPLevel();
            while (resultSet.next()) {
                level.setId(resultSet.getString("ID"));
                level.setCode(resultSet.getString("CODE"));
                level.setDescription(resultSet.getString("DESCRIPTION"));
                level.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                level.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
                level.setCreateduser(resultSet.getString("CREATEDUSER"));
            }
        } catch (SQLException | NumberFormatException exception) {
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
        return level;
    }
}

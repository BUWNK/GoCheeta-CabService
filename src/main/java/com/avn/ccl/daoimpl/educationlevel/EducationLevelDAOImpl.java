/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.educationlevel;

import com.avn.ccl.dao.educationlevel.EducationLevelDAO;
import com.avn.ccl.model.educationlevel.EducationLevel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : EducationLevelDAOImpl
 * @Created on : Aug 4, 2015, 12:15:46 PM
 */
public class EducationLevelDAOImpl implements EducationLevelDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public EducationLevel getEducationLevelById(String educationlevelid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        EducationLevel educationLevel = null;
        try {
            String sql = "SELECT "
                    + "    EDUCATIONALLEVELID, "
                    + "    EDUCATIONALLEVELCODE, "
                    + "    DESCRIPTION, "
                    + "    SORTID, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME "
                    + "FROM "
                    + "    AVN_EDUCATIONLEVEL "
                    + "WHERE "
                    + "    EDUCATIONALLEVELID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(educationlevelid));
            resultSet = statement.executeQuery();
            educationLevel = new EducationLevel();
            while (resultSet.next()) {
                educationLevel.setEducationlevelid(resultSet.getString("EDUCATIONALLEVELID"));
                educationLevel.setEducationlevelcode(resultSet.getString("EDUCATIONALLEVELCODE"));
                educationLevel.setDescription(resultSet.getString("DESCRIPTION"));
                educationLevel.setSortid(String.valueOf(resultSet.getInt("SORTID")));
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
        return educationLevel;
    }

    @Override
    public EducationLevel getEducationLevelByCode(String educationlevelcode) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        EducationLevel educationLevel = null;
        try {
            String sql = "SELECT "
                    + "    EDUCATIONALLEVELID, "
                    + "    EDUCATIONALLEVELCODE, "
                    + "    DESCRIPTION, "
                    + "    SORTID, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME "
                    + "FROM "
                    + "    AVN_EDUCATIONLEVEL "
                    + "WHERE "
                    + "    EDUCATIONALLEVELCODE = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(educationlevelcode));
            resultSet = statement.executeQuery();
            educationLevel = new EducationLevel();
            while (resultSet.next()) {
                educationLevel.setEducationlevelid(resultSet.getString("EDUCATIONALLEVELID"));
                educationLevel.setEducationlevelcode(resultSet.getString("EDUCATIONALLEVELCODE"));
                educationLevel.setDescription(resultSet.getString("DESCRIPTION"));
                educationLevel.setSortid(String.valueOf(resultSet.getInt("SORTID")));
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
        return educationLevel;
    }

    @Override
    public Map<String, String> getEducationLevelDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT EDUCATIONALLEVELID, DESCRIPTION FROM AVN_EDUCATIONLEVEL ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("EDUCATIONALLEVELID"), resultSet.getString("DESCRIPTION"));
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
}

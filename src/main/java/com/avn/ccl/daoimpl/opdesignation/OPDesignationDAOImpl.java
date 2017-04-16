/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.opdesignation;

import com.avn.ccl.dao.opdesignation.OPDesignationDAO;
import com.avn.ccl.model.opdesignation.OPDesignation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : DesignationDAOImpl
 * @Date : Jul 23, 2015 12:52:13 PM
 */
public class OPDesignationDAOImpl implements OPDesignationDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getDesignationDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT ID, DESCRIPTION FROM AVN_BUSDESIGNATION ORDER BY DESCRIPTION";
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
    public OPDesignation getDesignationById(String designationid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OPDesignation designation = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    CODE, "
                    + "    DESCRIPTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER "
                    + "FROM "
                    + "    AVN_BUSDESIGNATION "
                    + "WHERE "
                    + "    ID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(designationid));
            resultSet = statement.executeQuery();
            designation = new OPDesignation();
            while (resultSet.next()) {
                designation.setId(resultSet.getString("ID"));
                designation.setCode(resultSet.getString("CODE"));
                designation.setDescription(resultSet.getString("DESCRIPTION"));
                designation.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                designation.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
                designation.setCreateduser(resultSet.getString("CREATEDUSER"));
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
        return designation;
    }

    @Override
    public OPDesignation getDesignationByCode(String designationcode) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OPDesignation designation = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    CODE, "
                    + "    DESCRIPTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER "
                    + "FROM "
                    + "    AVN_BUSDESIGNATION "
                    + "WHERE "
                    + "    CODE = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(designationcode));
            resultSet = statement.executeQuery();
            designation = new OPDesignation();
            while (resultSet.next()) {
                designation.setId(resultSet.getString("ID"));
                designation.setCode(resultSet.getString("CODE"));
                designation.setDescription(resultSet.getString("DESCRIPTION"));
                designation.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                designation.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
                designation.setCreateduser(resultSet.getString("CREATEDUSER"));
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
        return designation;
    }
}

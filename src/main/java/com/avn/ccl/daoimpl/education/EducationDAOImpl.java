/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.education;

import com.avn.ccl.dao.education.EducationDAO;
import com.avn.ccl.model.education.Education;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : EducationDAOImpl
 * @Created on : Aug 4, 2015, 10:27:58 AM
 */
public class EducationDAOImpl implements EducationDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long createEducation(Education education) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long educationid = 0;
        try {
            String sql = "INSERT "
                    + "INTO "
                    + "    AVN_ACCOUNTEDUCATION "
                    + "    ("
                    + "        ACCOUNTID, "
                    + "        EDUCATIONALLEVELID, "
                    + "        INSTITUTION, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME"
                    + "    ) "
                    + "    VALUES "
                    + "    ( ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            connection = dataSource.getConnection();
            String[] generatedColumns = {"ID"};
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setLong(1, Long.valueOf(education.getAccount_id()));
            statement.setInt(2, Integer.valueOf(education.getEducation_level()));
            statement.setString(3, education.getInstitute());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                educationid = resultSet.getLong(1);
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
        return educationid;
    }

    @Override
    public void deleteEducation(String educationid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_ACCOUNTEDUCATION WHERE ID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(educationid));
            statement.execute();
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
    }

    @Override
    public Education getEducationById(String educationid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Education education = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    ACCOUNTID, "
                    + "    EDUCATIONALLEVELID, "
                    + "    INSTITUTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME "
                    + "FROM "
                    + "    AVN_ACCOUNTEDUCATION "
                    + "WHERE "
                    + "    EDUCATIONALLEVELID = ?";
            connection = dataSource.getConnection();
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
        return education;
    }
}

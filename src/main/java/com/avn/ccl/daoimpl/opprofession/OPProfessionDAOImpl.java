/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.opprofession;

import com.avn.ccl.dao.opprofession.OPProfessionDAO;
import com.avn.ccl.model.opprofession.OPProfession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : OPProfessionDAOImpl
 * @Date : Jul 23, 2015 2:46:16 PM
 */
public class OPProfessionDAOImpl implements OPProfessionDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getProfessionDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT ID, DESCRIPTION FROM AVN_BUSPROFESSION ORDER BY DESCRIPTION";
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
    public OPProfession getProfessionById(String professionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OPProfession profession = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    CODE, "
                    + "    DESCRIPTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER "
                    + "FROM "
                    + "    AVN_BUSPROFESSION "
                    + "WHERE "
                    + "    ID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(professionid));
            resultSet = statement.executeQuery();
            profession = new OPProfession();
            while (resultSet.next()) {
                profession.setId(resultSet.getString("ID"));
                profession.setCode(resultSet.getString("CODE"));
                profession.setDescription(resultSet.getString("DESCRIPTION"));
                profession.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                profession.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
                profession.setCreateduser(resultSet.getString("CREATEDUSER"));
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
        return profession;
    }

    @Override
    public OPProfession getProfessionByCode(String professioncode) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OPProfession profession = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    CODE, "
                    + "    DESCRIPTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER "
                    + "FROM "
                    + "    AVN_BUSPROFESSION "
                    + "WHERE "
                    + "    CODE = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(professioncode));
            resultSet = statement.executeQuery();
            profession = new OPProfession();
            while (resultSet.next()) {
                profession.setId(resultSet.getString("ID"));
                profession.setCode(resultSet.getString("CODE"));
                profession.setDescription(resultSet.getString("DESCRIPTION"));
                profession.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                profession.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
                profession.setCreateduser(resultSet.getString("CREATEDUSER"));
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
        return profession;
    }

}

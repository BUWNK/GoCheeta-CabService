/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.opsector;

import com.avn.ccl.dao.opsector.OPSectorDAO;
import com.avn.ccl.model.opsector.OPSector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : OPSectorDAOImpl
 * @Date : Jul 23, 2015 2:57:20 PM
 */
public class OPSectorDAOImpl implements OPSectorDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getSectorDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT ID, DESCRIPTION FROM AVN_BUSSECTOR ORDER BY DESCRIPTION";
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
    public OPSector getSectorById(String sectorid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OPSector sector = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    CODE, "
                    + "    DESCRIPTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER "
                    + "FROM "
                    + "    AVN_BUSSECTOR "
                    + "WHERE "
                    + "    ID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(sectorid));
            resultSet = statement.executeQuery();
            sector = new OPSector();
            while (resultSet.next()) {
                sector.setId(resultSet.getString("ID"));
                sector.setCode(resultSet.getString("CODE"));
                sector.setDescription(resultSet.getString("DESCRIPTION"));
                sector.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                sector.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
                sector.setCreateduser(resultSet.getString("CREATEDUSER"));
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
        return sector;
    }
}

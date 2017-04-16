/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.opsubsector;

import com.avn.ccl.dao.opsubsector.OPSubsectorDAO;
import com.avn.ccl.model.opsubsector.OPSubsector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : OPSubsecgtorDAOImpl
 * @Date : Jul 23, 2015 3:22:57 PM
 */
public class OPSubsectorDAOImpl implements OPSubsectorDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<OPSubsector> getSubsectorDropdownListBySector(String sectorid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OPSubsector> list = null;
        try {
            String sql = "SELECT ID, DESCRIPTION FROM AVN_BUSSUBSECTOR WHERE SECTOR = ? ORDER BY DESCRIPTION";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sectorid));
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            OPSubsector oPSubsector;
            while (resultSet.next()) {
                oPSubsector = new OPSubsector();
                oPSubsector.setId(resultSet.getString("ID"));
                oPSubsector.setDescription(resultSet.getString("DESCRIPTION"));
                list.add(oPSubsector);
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
        return list;
    }

    @Override
    public OPSubsector getSubsectorById(String subsectorid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OPSubsector subsector = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    CODE, "
                    + "    SECTOR, "
                    + "    DESCRIPTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER "
                    + "FROM "
                    + "    AVN_BUSSUBSECTOR "
                    + "WHERE "
                    + "    ID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(subsectorid));
            resultSet = statement.executeQuery();
            subsector = new OPSubsector();
            while (resultSet.next()) {
                subsector.setId(resultSet.getString("ID"));
                subsector.setCode(resultSet.getString("CODE"));
                subsector.setSector(resultSet.getString("SECTOR"));
                subsector.setDescription(resultSet.getString("DESCRIPTION"));
                subsector.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                subsector.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
                subsector.setCreateduser(resultSet.getString("CREATEDUSER"));
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
        return subsector;
    }

    @Override
    public List<OPSubsector> getSubsectorByIds(String[] subsectorids) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OPSubsector> subsectors = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    CODE, "
                    + "    SECTOR, "
                    + "    DESCRIPTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER "
                    + "FROM "
                    + "    AVN_BUSSUBSECTOR "
                    + "WHERE "
                    + "    ID IN (:condition) ";
            String temp = " ";
            for (String val : subsectorids) {
                temp += val + ", ";
            }
            temp = temp.substring(0, temp.length() - 1);
            sql = sql.replace(":condition", temp);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            subsectors = new ArrayList<>();
            while (resultSet.next()) {
                OPSubsector subsector = new OPSubsector();
                subsector.setId(resultSet.getString("ID"));
                subsector.setCode(resultSet.getString("CODE"));
                subsector.setSector(resultSet.getString("SECTOR"));
                subsector.setDescription(resultSet.getString("DESCRIPTION"));
                subsector.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                subsector.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
                subsector.setCreateduser(resultSet.getString("CREATEDUSER"));
                subsectors.add(subsector);
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
        return subsectors;
    }

    @Override
    public Map<String, String> getSubsectorDropdownListByIds(String[] subsectorids) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT "
                    + "    ID, "
                    + "    CODE, "
                    + "    DESCRIPTION "
                    + "FROM "
                    + "    AVN_BUSSUBSECTOR "
                    + "WHERE "
                    + "    ID IN (:condition) "
                    + "ORDER BY DESCRIPTION";
            String temp = " ";
            for (String val : subsectorids) {
                temp += val + ", ";
            }
            temp = temp.substring(0, temp.length() - 2);
            sql = sql.replace(":condition", temp);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("ID"), resultSet.getString("DESCRIPTION"));
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
        return list;
    }
}

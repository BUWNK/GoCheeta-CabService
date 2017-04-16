/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.wedget;

import com.avn.ccl.dao.wedget.WedgetDAO;
import com.avn.ccl.model.section.Section;
import com.avn.ccl.model.wedget.Wedget;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : ISURU
 * @Document : WedgetDAOImpl
 * @Created on : May 4, 2016, 9:03:46 AM
 */
public class WedgetDAOImpl implements WedgetDAO{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long insertWedget(Wedget wedget, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long wedgetID = 0;
        try {
            String sql = "INSERT INTO AVN_WIDGET (DESCRIPTION,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER,STATUS)  VALUES(?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,?)";
            String[] generatedColumns = {"WIDGETID"};
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setString(1, wedget.getDescription());
            statement.setString(2, username);
            statement.setInt(3, Integer.valueOf(wedget.getStatusid()));
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                wedgetID = resultSet.getLong(1);
            }
        } catch (SQLException | NumberFormatException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return wedgetID;
    }

    @Override
    public int getTableDataCount(Wedget parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_WIDGET WI INNER JOIN AVN_STATUS ST ON WI.STATUS=ST.STATUSID :where ";
            sql = sql.replace(":where", this.getWhere(parameters));
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("CNT");
            }
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return count;
    }

    private String getWhere(Wedget parameters) {
        String where = "";
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("wedgetid")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where WI.WIDGETID LIKE '%" + parameters.getInput().trim() + "%'";
        }
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("sectiondes")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where WI.DESCRIPTION LIKE '%" + parameters.getInput().trim() + "%'";
        }
        return where;
    }

    @Override
    public List<Wedget> getTableData(Wedget parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Wedget> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + " FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        (SELECT WI.WIDGETID, WI.DESCRIPTION, WI.CREATEDDATETIME, WI.LASTUPDATEDDATETIME,WI.CREATEDUSER,WI.STATUS, ST.DESCRIPTION AS STDES FROM AVN_WIDGET WI INNER JOIN AVN_STATUS ST ON WI.STATUS=ST.STATUSID "
                    + " ) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            sql = sql.replace(":where", this.getWhere(parameters));
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, start + minCount);
            statement.setInt(2, start);
            resultSet = statement.executeQuery();

            list = new ArrayList<>();
            Wedget wedgetData;
            while (resultSet.next()) {
                wedgetData = new Wedget();
                wedgetData.setWedgetid(resultSet.getString("WIDGETID"));
                wedgetData.setDescription(resultSet.getString("DESCRIPTION"));
                wedgetData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                wedgetData.setLastupdatedatetime(resultSet.getString("LASTUPDATEDDATETIME"));
                wedgetData.setCreateduser(resultSet.getString("CREATEDUSER"));
                wedgetData.setStatusid(resultSet.getString("STATUS"));
                wedgetData.setStatusdes(resultSet.getString("STDES"));
                list.add(wedgetData);
            }

        } catch (Exception exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return list;
    }

    @Override
    public Wedget getWedgetByWedgetId(String wedgetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Wedget wedgetData = null;
        try {
            String sql = "SELECT WI.WIDGETID, WI.DESCRIPTION, WI.CREATEDDATETIME, WI.LASTUPDATEDDATETIME,WI.CREATEDUSER,WI.STATUS, "
                    + " ST.DESCRIPTION AS STDES  FROM AVN_WIDGET WI INNER JOIN AVN_STATUS ST ON WI.STATUS=ST.STATUSID WHERE WI.WIDGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(wedgetid));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                wedgetData = new Wedget();
                wedgetData.setWedgetid(resultSet.getString("WIDGETID"));
                wedgetData.setDescription(resultSet.getString("DESCRIPTION"));
                wedgetData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                wedgetData.setLastupdatedatetime(resultSet.getString("LASTUPDATEDDATETIME"));
                wedgetData.setCreateduser(resultSet.getString("CREATEDUSER"));
                wedgetData.setStatusid(resultSet.getString("STATUS"));
                wedgetData.setStatusdes(resultSet.getString("STDES"));
            }
        } catch (SQLException | NumberFormatException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return wedgetData;
    }

    @Override
    public void updateWedget(Wedget wedget, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_WIDGET SET DESCRIPTION=?,LASTUPDATEDDATETIME=CURRENT_TIMESTAMP,CREATEDUSER=?,STATUS=? WHERE WIDGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, wedget.getDescription());
            statement.setString(2, username);
            statement.setInt(3, Integer.valueOf(wedget.getStatusid()));
            statement.setString(4, wedget.getWedgetid());
            statement.executeUpdate();
        } catch (SQLException | NumberFormatException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public Map<String, String> getWedgetDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT WIDGETID, DESCRIPTION FROM  AVN_WIDGET ORDER BY WIDGETID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("WIDGETID"), resultSet.getString("DESCRIPTION"));
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
     public List<Wedget> getWedgetDropdownListNew(String usereoleid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Wedget> WedgetList = null;
        try {
            String sql = "SELECT  WIDGETID, DESCRIPTION FROM AVN_WIDGET WHERE WIDGETID NOT IN (SELECT WIDGETID FROM  AVN_USERROLEDASHBOARDWIDGET WHERE USERROLEID=?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
             statement.setString(1, usereoleid);
            resultSet = statement.executeQuery();
           
            WedgetList = new ArrayList<>();
            while (resultSet.next()) {
               Wedget wedgetData = new Wedget();
               wedgetData.setWedgetid(resultSet.getString("WIDGETID"));
               wedgetData.setDescription(resultSet.getString("DESCRIPTION"));
               WedgetList.add(wedgetData);
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
        return WedgetList;
    }
}

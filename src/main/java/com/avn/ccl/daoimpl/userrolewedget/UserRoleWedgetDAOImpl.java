/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.userrolewedget;

import com.avn.ccl.dao.userrolewedget.UserRoleWedgetDAO;
import com.avn.ccl.model.userrolewedget.UserRoleWedget;
import com.avn.ccl.model.wedget.Wedget;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : ISURU
 * @Document : UserRoleWedgetDAOImpl
 * @Created on : May 4, 2016, 8:23:54 PM
 */
public class UserRoleWedgetDAOImpl implements UserRoleWedgetDAO{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getInsertSortIdList(String userrole) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> list = null;
        try {
            String sql = "SELECT SORTID FROM  AVN_USERROLEDASHBOARDWIDGET WHERE USERROLEID=? ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userrole);
            resultSet = statement.executeQuery();
            list = new LinkedList<>();
            while (resultSet.next()) {
                list.add(resultSet.getString("SORTID"));
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
    public List<String> getInsertWedgetList(String userrole) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> list = null;
        try {
            String sql = "SELECT WIDGETID FROM  AVN_USERROLEDASHBOARDWIDGET WHERE USERROLEID=? ORDER BY WIDGETID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userrole);
            resultSet = statement.executeQuery();
            list = new LinkedList<>();
            while (resultSet.next()) {
                list.add(resultSet.getString("WIDGETID"));
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
    public void insertUserRoleWedget(UserRoleWedget userrolewedget, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "INSERT INTO AVN_USERROLEDASHBOARDWIDGET (WIDGETID, USERROLEID, SORTID, CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER,STATUS,WIDGETWIDTHID) VALUES(?,?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,?,?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userrolewedget.getWedgetid());
            statement.setString(2, userrolewedget.getUserroleid());
            statement.setString(3, userrolewedget.getSortid());
            statement.setString(4, username);
            statement.setString(5, userrolewedget.getStatusid());
            statement.setString(6, userrolewedget.getWedgetwidthid());
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
    public int getTableDataCount(UserRoleWedget parameters) throws Exception {
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

    private String getWhere(UserRoleWedget parameters) {
        String where = "";
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("userroleid")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where URD.USERROLEID LIKE '%" + parameters.getInput().trim() + "%'";
        }

        return where;
    }

    @Override
    public List<UserRoleWedget> getTableData(UserRoleWedget parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleWedget> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + " FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        (SELECT URD.WIDGETID, WI.DESCRIPTION AS WIDES, URD.USERROLEID, URD.SORTID, URD.CREATEDDATETIME, URD.LASTUPDATEDDATETIME, URD.CREATEDUSER, URD.STATUS, ST.DESCRIPTION AS STDES, UR.DESCRIPTION AS URDS, WW.DESCRIPTION FROM AVN_USERROLEDASHBOARDWIDGET URD INNER JOIN AVN_STATUS ST ON URD.STATUS=ST.STATUSID  INNER JOIN AVN_USER_ROLE UR ON URD.USERROLEID=UR.USERROLEID INNER JOIN AVN_WIDGET WI ON WI.WIDGETID=URD.WIDGETID INNER JOIN AVN_WEDGETWIDTH WW ON URD.WIDGETWIDTHID=WW.WIDGETWIDTHID   "
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
            UserRoleWedget userrolewedgetData;
            while (resultSet.next()) {
                userrolewedgetData = new UserRoleWedget();
                userrolewedgetData.setWedgetid(resultSet.getString("WIDGETID"));
                userrolewedgetData.setWedgetdescription(resultSet.getString("WIDES"));
                userrolewedgetData.setUserroleid(resultSet.getString("USERROLEID"));
                userrolewedgetData.setSortid(resultSet.getString("SORTID"));
                userrolewedgetData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                userrolewedgetData.setLastupdatedatetime(resultSet.getString("LASTUPDATEDDATETIME"));
                userrolewedgetData.setCreateduser(resultSet.getString("CREATEDUSER"));
                userrolewedgetData.setStatusid(resultSet.getString("STATUS"));
                userrolewedgetData.setStatusdes(resultSet.getString("STDES"));
                userrolewedgetData.setUserroledescription(resultSet.getString("URDS"));
                list.add(userrolewedgetData);
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
    public UserRoleWedget getWedgetByWedgetId(String wedgetid, String userroleid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserRoleWedget userrolewedgetData = null;
        try {
            String sql = "SELECT URD.WIDGETID, WI.DESCRIPTION AS WIDES, URD.USERROLEID, URD.SORTID, URD.CREATEDDATETIME, URD.LASTUPDATEDDATETIME, URD.CREATEDUSER, URD.STATUS, ST.DESCRIPTION AS STDES, UR.DESCRIPTION AS URDS, WW.DESCRIPTION,URD.WIDGETWIDTHID "
                    + " FROM AVN_USERROLEDASHBOARDWIDGET URD "
                    + " INNER JOIN AVN_STATUS ST ON URD.STATUS=ST.STATUSID  "
                    + " INNER JOIN AVN_USER_ROLE UR ON URD.USERROLEID=UR.USERROLEID "
                    + " INNER JOIN AVN_WIDGET WI ON WI.WIDGETID=URD.WIDGETID "
                    + " INNER JOIN AVN_WEDGETWIDTH WW ON URD.WIDGETWIDTHID=WW.WIDGETWIDTHID  WHERE URD.WIDGETID=? AND URD.USERROLEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(wedgetid));
            statement.setInt(2, Integer.valueOf(userroleid));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userrolewedgetData = new UserRoleWedget();
                userrolewedgetData.setWedgetid(resultSet.getString("WIDGETID"));
                userrolewedgetData.setUserroleid(resultSet.getString("USERROLEID"));
                userrolewedgetData.setSortid(resultSet.getString("SORTID"));
                userrolewedgetData.setOldsortid(resultSet.getString("SORTID"));
                userrolewedgetData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                userrolewedgetData.setLastupdatedatetime(resultSet.getString("LASTUPDATEDDATETIME"));
                userrolewedgetData.setCreateduser(resultSet.getString("CREATEDUSER"));
                userrolewedgetData.setStatusid(resultSet.getString("STATUS"));
                userrolewedgetData.setStatusdes(resultSet.getString("STDES"));
                userrolewedgetData.setUserroledescription(resultSet.getString("URDS"));
                 userrolewedgetData.setWedgetwidthid(resultSet.getString("WIDGETWIDTHID"));
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
        return userrolewedgetData;
    }

    @Override
    public void updateWedget(UserRoleWedget wedget, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql3 = "SELECT WIDGETID ,USERROLEID,SORTID FROM AVN_USERROLEDASHBOARDWIDGET WHERE USERROLEID=?  ORDER BY SORTID";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql3);
            statement.setString(1, wedget.getUserroleid());
            resultSet = statement.executeQuery();
            List<UserRoleWedget> changelist = new ArrayList<>();
            while (resultSet.next()) {
                UserRoleWedget obj = new UserRoleWedget();
                obj.setWedgetid(resultSet.getString("WIDGETID"));
                obj.setUserroleid(resultSet.getString("USERROLEID"));
                obj.setSortid(resultSet.getString("SORTID"));
                changelist.add(obj);
            }
            if (Integer.valueOf(wedget.getOldsortid()) > Integer.valueOf(wedget.getSortid())) {
                for (UserRoleWedget items : changelist) {
                    for (int i = Integer.valueOf(items.getSortid()); i < Integer.valueOf(wedget.getOldsortid()) && i >= Integer.valueOf(wedget.getSortid()); i++) {
                        String sql2 = "UPDATE AVN_USERROLEDASHBOARDWIDGET SET SORTID=?  WHERE  WIDGETID=? AND USERROLEID=?";
                        statement = connection.prepareStatement(sql2);
                        statement.setInt(1, Integer.valueOf(items.getSortid()) + 1);
                        statement.setString(2, items.getWedgetid());
                        statement.setString(3, wedget.getUserroleid());
                        statement.executeUpdate();
                    }
                }

            } else {
                for (UserRoleWedget items : changelist) {
                    for (int i = Integer.valueOf(items.getSortid()); i > Integer.valueOf(wedget.getOldsortid()) && i <= Integer.valueOf(wedget.getSortid()); i++) {
                        String sql2 = "UPDATE AVN_USERROLEDASHBOARDWIDGET SET SORTID=?  WHERE  WIDGETID=? AND USERROLEID=?";
//                        conn = dataSource.getConnection();
                        statement = connection.prepareStatement(sql2);
                        statement.setInt(1, Integer.valueOf(items.getSortid()) - 1);
                        statement.setString(2, items.getWedgetid());
                        statement.setString(3, wedget.getUserroleid());
                        statement.executeUpdate();
                    }
                }

            }
            String sql = "UPDATE AVN_USERROLEDASHBOARDWIDGET SET SORTID=?, LASTUPDATEDDATETIME=CURRENT_TIMESTAMP, CREATEDUSER=?, STATUS=?,WIDGETWIDTHID=? WHERE WIDGETID=? AND USERROLEID=?";
//            conn = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, wedget.getSortid());
            statement.setString(2, username);
            statement.setString(3, wedget.getStatusid());
            statement.setString(4, wedget.getWedgetwidthid());
            statement.setString(5, wedget.getWedgetid());
            statement.setString(6, wedget.getUserroleid());

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();

            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    connection.rollback();
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
    public void deleteWedget(String wedgetuid, String userroleid, String sortid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            String sql = "DELETE FROM AVN_USERROLEDASHBOARDWIDGET  WHERE WIDGETID=? AND USERROLEID=?";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setString(1, wedgetuid);
            statement.setString(2, userroleid);
            statement.executeUpdate();

            String sql3 = "SELECT WIDGETID ,USERROLEID,SORTID FROM AVN_USERROLEDASHBOARDWIDGET WHERE USERROLEID=?  ORDER BY SORTID";
//            conn = dataSource.getConnection();
            statement = connection.prepareStatement(sql3);
            statement.setString(1, userroleid);
            resultSet = statement.executeQuery();
            List<UserRoleWedget> changelist = new ArrayList<>();
            while (resultSet.next()) {
                UserRoleWedget obj = new UserRoleWedget();
                obj.setWedgetid(resultSet.getString("WIDGETID"));
                obj.setUserroleid(resultSet.getString("USERROLEID"));
                obj.setSortid(resultSet.getString("SORTID"));
                changelist.add(obj);
            }
//            conn = dataSource.getConnection();
//           
            for (UserRoleWedget items : changelist) {
                for (int i = Integer.valueOf(items.getSortid()); i > Integer.valueOf(sortid); i++) {
                    String sql2 = "UPDATE AVN_USERROLEDASHBOARDWIDGET SET SORTID=?  WHERE  WIDGETID=? and USERROLEID=?";
//                    conn = dataSource.getConnection();
                    statement = connection.prepareStatement(sql2);
                    statement.setInt(1, Integer.valueOf(items.getSortid()) - 1);
                    statement.setString(2, items.getWedgetid());
                    statement.setString(3, userroleid);
                    statement.executeUpdate();
                    break;
                }
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.status;

import com.avn.ccl.dao.status.StatusDAO;
import com.avn.ccl.model.status.Status;
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
 *
 * @author Hirantha
 */
public class StatusDAOImpl implements StatusDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Status> getStatusList(int statuscategory) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Status> statusList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AVN_STATUS WHERE CATEGORYID = ? ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, statuscategory);
            resultSet = statement.executeQuery();
            Status status;
            while (resultSet.next()) {
                status = new Status();
                status.setStatusID(resultSet.getInt("STATUSID"));
                status.setStatusDesc(resultSet.getString("DESCRIPTION"));
                status.setCategoryID(resultSet.getInt("CATEGORYID"));
                statusList.add(status);
            }
            return statusList;
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
    }

    @Override
    public Map<String, String> getStatusDropdownList(int statuscategory) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT STATUSID, DESCRIPTION FROM AVN_STATUS WHERE CATEGORYID = ? ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, statuscategory);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("STATUSID"), resultSet.getString("DESCRIPTION"));
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
    public Status getStatusById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Status status = null;
        try {
            String sql = "SELECT * FROM AVN_STATUS WHERE STATUSID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                status = new Status();
                status.setCategoryID(resultSet.getInt("STATUSID"));
                status.setStatusDesc(resultSet.getString("DESCRIPTION"));
                status.setCategoryID(resultSet.getInt("CATEGORYID"));
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
        return status;
    }

    @Override
    public Map<String, String> getStatusCodeDropdownList(int statuscategory) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT STATUSCODE, DESCRIPTION FROM AVN_STATUS WHERE CATEGORYID = ? ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, statuscategory);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("STATUSCODE"), resultSet.getString("DESCRIPTION"));
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
    public Status getStatusByCategoryAndCode(int statusCategory, String statusCode) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Status status = null;
        try {
            String sql = "SELECT * FROM AVN_STATUS WHERE CATEGORYID = ? AND STATUSCODE = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, statusCategory);
            statement.setString(2, statusCode);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                status = new Status();
                status.setCategoryID(resultSet.getInt("STATUSID"));
                status.setStatusDesc(resultSet.getString("DESCRIPTION"));
                status.setCategoryID(resultSet.getInt("CATEGORYID"));
                status.setStatusCode(resultSet.getString("STATUSCODE"));
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
        return status;
    }

    @Override
    public List<Status> getStatusListByCategory(int statusCategory) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Status> statuses = null;
        try {
            String sql = "SELECT * FROM AVN_STATUS WHERE CATEGORYID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, statusCategory);
            resultSet = statement.executeQuery();
            statuses = new ArrayList<>();
            Status status;
            while (resultSet.next()) {
                status = new Status();
                status.setCategoryID(resultSet.getInt("STATUSID"));
                status.setStatusDesc(resultSet.getString("DESCRIPTION"));
                status.setCategoryID(resultSet.getInt("CATEGORYID"));
                status.setStatusCode(resultSet.getString("STATUSCODE"));
                statuses.add(status);
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
        return statuses;
    }

    @Override
    public Map<String, String> getStatusDropdownListWithIdsNotIn(int statuscategory, List<Integer> notinids) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT STATUSID, DESCRIPTION FROM AVN_STATUS WHERE CATEGORYID = ? AND STATUSID NOT IN (%s) ORDER BY SORTID";
            String temp = "";
            for (int i = 0; i < notinids.size(); i++) {
                temp += notinids.get(i);
                if (i + 1 != notinids.size()) {
                    temp += ", ";
                }
            }
            sql = String.format(sql, temp);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, statuscategory);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("STATUSID"), resultSet.getString("DESCRIPTION"));
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
    public Map<String, String> getStatusListWithIdsNotIn(int statuscategory, List<Integer> notinids) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> statusList = null;
        try {
            String sql = "SELECT STATUSID, DESCRIPTION FROM AVN_STATUS WHERE CATEGORYID = ? AND STATUSID NOT IN (%s) ORDER BY SORTID";
            String temp = "";
            for (int i = 0; i < notinids.size(); i++) {
                temp += notinids.get(i);
                if (i + 1 != notinids.size()) {
                    temp += ", ";
                }
            }
            sql = String.format(sql, temp);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, statuscategory);
            resultSet = statement.executeQuery();
            statusList = new LinkedHashMap<>();
            statusList.put("", "-- Select --");
            while (resultSet.next()) {
                statusList.put(resultSet.getString("STATUSID"), resultSet.getString("DESCRIPTION"));
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
        return statusList;
    }

}

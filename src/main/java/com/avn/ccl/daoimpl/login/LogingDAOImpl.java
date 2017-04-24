/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.login;

import com.avn.ccl.dao.login.LoginDAO;
import com.avn.ccl.model.user.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @Author : Office Isuru
 * @Document : LogingDAOImpl
 * @Date : Sep 22, 2015 1:41:31 PM
 */
public class LogingDAOImpl implements LoginDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int checkUserAlreadyExists(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "Select Count(USERID)AS CNT from AVN_SYSTEMUSER Where USERID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
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
        return count;
    }

    @Override
    public List<User> getUserNamePasswordAndRole(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> usernamepw = new ArrayList<>();
        User userbean = null;
        try {
            String sql = "select su.USERID,su.PASSWORD, ur.USERROLECODE from AVN_SYSTEMUSER su, AVN_USER_ROLE ur where su.USERROLE=ur.USERROLEID and su.USERID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userbean = new User();
                userbean.setUsername(resultSet.getString("USERID"));
                userbean.setPassword(resultSet.getString("PASSWORD"));
                userbean.setUser_role(resultSet.getString("USERROLECODE"));
                usernamepw.add(userbean);
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
        return usernamepw;
    }

    @Override
    public Date getLastLoginDateTime(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Date lastlogintdatetime = null;
        try {
            String sql = "SELECT LASTLOGIN FROM AVN_SYSTEMUSER WHERE USERID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                lastlogintdatetime = new Date(resultSet.getTimestamp("LASTLOGIN").getTime());
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
        return lastlogintdatetime;
    }

    @Override
    public void updateLastLogin(String username, String logintime) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "Update systemuser SET LASTLOGIN =? where USERID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, logintime);
            statement.setString(2, username);
            statement.executeUpdate();

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

    public JSONArray getAssignedTaskList(int userroleid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray objList = new JSONArray();
        try {
            String sql = "SELECT ST.TASKID ,ST.SECTIONID FROM AVN_SECTIONTASK ST INNER JOIN  AVN_USERROLESECTION  URT ON ST.SECTIONID=URT.SECTIONID WHERE URT.USERROLEID= ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userroleid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONObject obj = new JSONObject();
                obj.put("taskid", resultSet.getString("TASKID"));
                obj.put("sectionid", resultSet.getString("SECTIONID"));
                objList.put(obj);
            }
        } catch (SQLException | JSONException ex) {
            throw ex;
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
        return objList;
    }
}

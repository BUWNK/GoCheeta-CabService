/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.activeusername;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @Author : Office Isuru
 * @Document : ActiveUser
 * @Date : Sep 10, 2015 9:44:56 AM
 */
public class ActiveUserDAOImpl {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getUserFirstName(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "Select E.FIRSTNAME, E.LASTNAME From AVN_SYSTEMUSER SU, AVN_EMPLOYEE E Where SU.EMPLOYEEID=E.EMPLOYEEID and SU.USERID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            String Fullname = null;
            while (resultSet.next()) {
                Fullname = resultSet.getString("FIRSTNAME") + " " + resultSet.getString("LASTNAME");
            }
            return Fullname;
        } catch (SQLException e) {
            throw e;

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

}

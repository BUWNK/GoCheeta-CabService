/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.logoutresons;

import com.avn.ccl.dao.logoutreasons.LogoutReasonsDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @Author : Office Isuru
 * @Document : LogoutResonsDAOImpl
 * @Date : Sep 28, 2015 9:11:53 AM
 */
public class LogoutReasonsDAOImpl implements LogoutReasonsDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getLogOutResons() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> list = null;
        try {
            String sql = "select REASONDESCRIPTION From AVN_LOGOUTREASONS ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                list.add("[" + resultSet.getString("REASONDESCRIPTION") + "]");
            }
        } catch (SQLException exception) {
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

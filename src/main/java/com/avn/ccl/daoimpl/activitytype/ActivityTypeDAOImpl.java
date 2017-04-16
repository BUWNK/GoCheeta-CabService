/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.daoimpl.activitytype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : ISURU
 * @Document : ActivityTypeDAOImpl
 * @Created on : Jun 30, 2016, 9:12:12 AM
 */
public class ActivityTypeDAOImpl {
    
    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    
    public Map<String, String> getActivityTypeDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> branchList = null;
        try {
            String sql = "SELECT ACTIVITYTYPEDESCRIPTION,ACTIVITYTYPEID FROM AVN_ACTIVITYTYPES";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            branchList = new LinkedHashMap<>();
            branchList.put("", "-- Select --");
            while (resultSet.next()) {
                branchList.put(resultSet.getString("ACTIVITYTYPEID"), resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
            }
        } catch (SQLException exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return branchList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.followupaction;

import com.avn.ccl.dao.followupaction.FollowUpActionDAO;
import com.avn.ccl.model.followupaction.FollowUpAction;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Lahiru
 */
public class FollowUpActionDAOImpl implements FollowUpActionDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getFollowUpActionDropdownList() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT FOLLOUPACTIONID, DESCRIPTION FROM AVN_FOLLOWUPACTIONS WHERE STATUS=? ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, MasterDataVarList.CCL_CODE_STATUS_ACTIVE);
            ResultSet rs = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (rs.next()) {
                list.put(rs.getString("FOLLOUPACTIONID"), rs.getString("DESCRIPTION"));
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
    public FollowUpAction getCallDirectionById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        FollowUpAction callDirection = null;
        try {
            String sql = "SELECT * FROM AVN_FOLLOWUPACTIONS WHERE FOLLOUPACTIONID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                callDirection = new FollowUpAction();
                callDirection.setFollowUpActionId(resultSet.getInt("FOLLOUPACTIONID"));
                callDirection.setDescription(resultSet.getString("DESCRIPTION"));
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
        return callDirection;
    }
}

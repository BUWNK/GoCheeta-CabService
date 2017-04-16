/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.casepriority;

import com.avn.ccl.dao.casepriority.CasePriorityDAO;
import com.avn.ccl.model.casepriority.CasePriority;
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
public class CasePriorityDAOImpl implements CasePriorityDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CasePriority> getCasePriorityList() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CasePriority> casePriorityList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AVN_CASEPRIORITY";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            CasePriority cp;
            while (resultSet.next()) {
                cp = new CasePriority();
                cp.setPriorityID(resultSet.getInt("CASEPRIORITYID"));
                cp.setPriorityDesc(resultSet.getString("DESCRIPTION"));
                cp.setSortID(resultSet.getInt("SORTID"));
                casePriorityList.add(cp);
            }
            return casePriorityList;
        } catch (Exception exception) {
            throw new RuntimeException(exception);

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
    public Map<String, String> getCasePriorityDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT CASEPRIORITYID, DESCRIPTION FROM AVN_CASEPRIORITY ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("CASEPRIORITYID"), resultSet.getString("DESCRIPTION"));
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
    public CasePriority getCasePriorityById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CasePriority casePriority = null;
        try {
            String sql = "SELECT * FROM AVN_CASEPRIORITY WHERE CASEPRIORITYID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                casePriority = new CasePriority();
                casePriority.setPriorityID(resultSet.getInt("CASEPRIORITYID"));
                casePriority.setPriorityDesc(resultSet.getString("DESCRIPTION"));
                casePriority.setSortID(resultSet.getInt("SORTID"));
            }
        } catch (SQLException | NumberFormatException exception) {
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
        return casePriority;
    }

}

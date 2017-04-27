/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.casetype;

import com.avn.ccl.dao.casetype.CaseTypeDAO;
import com.avn.ccl.model.casetype.CaseType;
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
public class CaseTypeDAOImpl implements CaseTypeDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CaseType> getCaseTypeList() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CaseType> caseTypeList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AVN_CASETYPE";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            CaseType ct;
            while (resultSet.next()) {
                ct = new CaseType();
                ct.setTypeID(resultSet.getInt("CASETYPEID"));
                ct.setTypeDesc(resultSet.getString("DESCRIPTION"));
                ct.setSortID(resultSet.getInt("SORTID"));
                caseTypeList.add(ct);
            }
            return caseTypeList;
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
    public Map<String, String> getCaseTypeDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT CASETYPEID, DESCRIPTION FROM tickettype ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("CASETYPEID"), resultSet.getString("DESCRIPTION"));
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
    public CaseType getCaseTypeById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CaseType caseType = null;
        try {
            String sql = "SELECT * FROM AVN_CASETYPE WHERE CASETYPEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                caseType = new CaseType();
                caseType.setTypeID(resultSet.getInt("CASETYPEID"));
                caseType.setTypeDesc(resultSet.getString("DESCRIPTION"));
                caseType.setSortID(resultSet.getInt("SORTID"));
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
        return caseType;
    }

}

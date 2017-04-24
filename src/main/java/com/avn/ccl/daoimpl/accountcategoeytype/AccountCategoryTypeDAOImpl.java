/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.accountcategoeytype;

import com.avn.ccl.dao.accountcategorytype.AccountCategoryTypeDAO;
import com.avn.ccl.model.accountcategegorytype.AccountCategoryType;
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
 * @Author : Roshen Dilshan
 * @Document : AccountCategoryTypeDAOImpl
 * @Created on : Oct 14, 2015, 2:27:07 PM
 */
public class AccountCategoryTypeDAOImpl implements AccountCategoryTypeDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getAccountCategoryTypeDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT ACCOUNTCATEGORYTYPEID, DESCRIPTION FROM AVN_ACCOUNTCATEGORYTYPE ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("ACCOUNTCATEGORYTYPEID"), resultSet.getString("DESCRIPTION"));
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
    public Map<String, String> getAccountCategoryTypeDropdownListByCategory(String category) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT ACCOUNTCATEGORYTYPEID, DESCRIPTION FROM AVN_ACCOUNTCATEGORYTYPE WHERE ACCOUNTCATEGORY = ? ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(category));
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("ACCOUNTCATEGORYTYPEID"), resultSet.getString("DESCRIPTION"));
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
    public AccountCategoryType getAccountCategoryTypeById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        AccountCategoryType accountCategoryType = null;
        try {
            String sql = "SELECT ACCOUNTCATEGORYTYPEID, ACCOUNTCATEGORY, DESCRIPTION FROM AVN_ACCOUNTCATEGORYTYPE WHERE ACCOUNTCATEGORYTYPEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountCategoryType = new AccountCategoryType();
                accountCategoryType.setAccountcategorytypeid(resultSet.getInt("ACCOUNTCATEGORYTYPEID"));
                accountCategoryType.setAccountcategory(resultSet.getInt("ACCOUNTCATEGORY"));
                accountCategoryType.setDescription(resultSet.getString("DESCRIPTION"));
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
        return accountCategoryType;
    }

    @Override
    public List<AccountCategoryType> getAccountCategoryTypesByCategory(String category) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<AccountCategoryType> list = null;
        try {
            String sql = "SELECT ACCOUNTCATEGORYTYPEID, DESCRIPTION FROM accountcategorytype WHERE ACCOUNTCATEGORY = ? ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(category));
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            AccountCategoryType categoryType;
            while (resultSet.next()) {
                categoryType = new AccountCategoryType();
                categoryType.setAccountcategorytypeid(resultSet.getInt("ACCOUNTCATEGORYTYPEID"));
                categoryType.setDescription(resultSet.getString("DESCRIPTION"));
                list.add(categoryType);
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

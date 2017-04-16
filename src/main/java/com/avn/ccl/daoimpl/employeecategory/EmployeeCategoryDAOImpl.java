/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.employeecategory;

import com.avn.ccl.dao.employeecategory.EmployeeCategoryDAO;
import com.avn.ccl.model.employeecategory.EmployeeCategory;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : EmployeeCategoryDAOImpl
 * @Created on : Oct 2, 2015, 2:16:45 PM
 */
public class EmployeeCategoryDAOImpl implements EmployeeCategoryDAO {

    private DataSource dataSource;
//    private ResultSet resultSet = null;
//    private Connection connection = null;
//    private PreparedStatement statement = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getEmployeeCategoryDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT CATEGORYID, CATEGORYDESCRIPTION FROM AVN_EMPLOYEECATEGORY ORDER BY CATEGORYDESCRIPTION";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("CATEGORYID"), resultSet.getString("CATEGORYDESCRIPTION"));
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
    public EmployeeCategory getEmployeeCategoryById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        EmployeeCategory employeeCategory = null;
        try {
            String sql = "SELECT "
                    + "    CATEGORYID, "
                    + "    CATEGORYDESCRIPTION "
                    + "FROM "
                    + "    AVN_EMPLOYEECATEGORY "
                    + "WHERE "
                    + "    CATEGORYID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            employeeCategory = new EmployeeCategory();
            while (resultSet.next()) {
                employeeCategory.setCategoryid(resultSet.getString("CATEGORYID"));
                employeeCategory.setCategorydescription(resultSet.getString("CATEGORYDESCRIPTION"));
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
        return employeeCategory;
    }

    @Override
    public Map<String, String> getEmployeeCategoryDropdownListSingleBr() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT CATEGORYID, CATEGORYDESCRIPTION FROM AVN_EMPLOYEECATEGORY WHERE CATEGORYID NOT IN ('" + MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER + "', '" + MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_ZONE_MANAGER + "') ORDER BY CATEGORYDESCRIPTION";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("CATEGORYID"), resultSet.getString("CATEGORYDESCRIPTION"));
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
    public Map<String, String> getEmployeeCategoryDropdownListMultiBr() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT CATEGORYID, CATEGORYDESCRIPTION FROM AVN_EMPLOYEECATEGORY WHERE CATEGORYID IN ('" + MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER + "', '" + MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_ZONE_MANAGER + "') ORDER BY CATEGORYDESCRIPTION";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("CATEGORYID"), resultSet.getString("CATEGORYDESCRIPTION"));
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

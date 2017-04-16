/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.customercodetype;

import com.avn.ccl.dao.customercodetype.CustomerCodeTypeDAO;
import com.avn.ccl.model.customercodetype.CustomerCodeType;
import com.avn.ccl.util.varlist.MasterDataVarList;
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
 * @Document : CustomerCodeDAOImpl
 * @Created on : Oct 15, 2015, 10:26:36 AM
 */
public class CustomerCodeTypeDAOImpl implements CustomerCodeTypeDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getCustomerCodeTypeDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT CODETYPEID, DESCRIPTION FROM AVN_ACCOUNTCUSTOMERCODETYPE ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("CODETYPEID"), resultSet.getString("DESCRIPTION"));
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
    public Map<String, String> getCustomerCodeTypeDropdownListIndividual() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT CODETYPEID, DESCRIPTION FROM AVN_ACCOUNTCUSTOMERCODETYPE WHERE CODETYPEID IN (" + MasterDataVarList.CCL_CODE_CUSTOMER_CODE_NIC + ", " + MasterDataVarList.CCL_CODE_CUSTOMER_CODE_CCID + ")  ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("CODETYPEID"), resultSet.getString("DESCRIPTION"));
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
    public Map<String, String> getCustomerCodeTypeDropdownListCorporate() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT CODETYPEID, DESCRIPTION FROM AVN_ACCOUNTCUSTOMERCODETYPE WHERE CODETYPEID = " + MasterDataVarList.CCL_CODE_CUSTOMER_CODE_BRN + " ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("CODETYPEID"), resultSet.getString("DESCRIPTION"));
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
    public CustomerCodeType getCustomerCodeTypeById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CustomerCodeType customerCodeType = null;
        try {
            String sql = "SELECT CODETYPEID, DESCRIPTION FROM AVN_ACCOUNTCUSTOMERCODETYPE WHERE CODETYPEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customerCodeType = new CustomerCodeType();
                customerCodeType.setCodetypeid(resultSet.getInt("CODETYPEID"));
                customerCodeType.setDescription(resultSet.getString("DESCRIPTION"));
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
        return customerCodeType;
    }

    @Override
    public List<CustomerCodeType> getCustomerCodeTypesIndividual() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CustomerCodeType> list = null;
        try {
            String sql = "SELECT CODETYPEID, DESCRIPTION FROM AVN_ACCOUNTCUSTOMERCODETYPE WHERE CODETYPEID IN (" + MasterDataVarList.CCL_CODE_CUSTOMER_CODE_NIC + ", " + MasterDataVarList.CCL_CODE_CUSTOMER_CODE_CCID + ")  ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            CustomerCodeType customerCodeType;
            while (resultSet.next()) {
                customerCodeType = new CustomerCodeType();
                customerCodeType.setCodetypeid(resultSet.getInt("CODETYPEID"));
                customerCodeType.setDescription(resultSet.getString("DESCRIPTION"));
                list.add(customerCodeType);
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
    public List<CustomerCodeType> getCustomerCodeTypesCorporate() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CustomerCodeType> list = null;
        try {
            String sql = "SELECT CODETYPEID, DESCRIPTION FROM AVN_ACCOUNTCUSTOMERCODETYPE WHERE CODETYPEID = " + MasterDataVarList.CCL_CODE_CUSTOMER_CODE_BRN + " ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            CustomerCodeType customerCodeType;
            while (resultSet.next()) {
                customerCodeType = new CustomerCodeType();
                customerCodeType.setCodetypeid(resultSet.getInt("CODETYPEID"));
                customerCodeType.setDescription(resultSet.getString("DESCRIPTION"));
                list.add(customerCodeType);
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

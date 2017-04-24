/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.product;

import com.avn.ccl.dao.product.ProductDAO;
import com.avn.ccl.model.product.Product;
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
 *
 * @author Hirantha
 */
public class ProductDAOImpl implements ProductDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getProductList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Product> productList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AVN_PRODUCT WHERE STATUS = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, MasterDataVarList.CCL_CODE_STATUS_ACTIVE);
            resultSet = statement.executeQuery();
            Product prod;
            while (resultSet.next()) {
                prod = new Product();
                prod.setProductID(resultSet.getInt("PRODUCTID"));
                prod.setProductDesc(resultSet.getString("DESCRIPTION"));
                prod.setSortID(resultSet.getInt("SORTID"));
                productList.add(prod);
            }
            return productList;
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

    @Override
    public Map<String, String> getProductDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT PRODUCTID, DESCRIPTION FROM AVN_PRODUCT WHERE STATUS = ? ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, MasterDataVarList.CCL_CODE_STATUS_ACTIVE);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("PRODUCTID"), resultSet.getString("DESCRIPTION"));
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
    public Map<String, String> getProductDropdownListPipeline(String createUser) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT PRD.PRODUCTID, "
                    + "  PRD.DESCRIPTION "
                    + "FROM product PRD "
                    + "INNER JOIN employeeproduct EMPPR "
                    + "ON PRD.PRODUCTID       = EMPPR.PRODUCTID "
                    + "WHERE EMPPR.EMPLOYEEID = "
                    + "  (SELECT EMPLOYEEID FROM systemuser WHERE USERID = ? "
                    + "  ) "
                    + "AND PRD.STATUS = ?";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, createUser);
            statement.setInt(2, MasterDataVarList.CCL_CODE_STATUS_ACTIVE);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("PRODUCTID"), resultSet.getString("DESCRIPTION"));
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
    public Product getProductById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Product product = null;
        try {
            String sql = "SELECT * FROM AVN_PRODUCT WHERE PRODUCTID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product = new Product();
                product.setProductID(resultSet.getInt("PRODUCTID"));
                product.setProductDesc(resultSet.getString("DESCRIPTION"));
                product.setSortID(resultSet.getInt("SORTID"));
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
        return product;
    }
}

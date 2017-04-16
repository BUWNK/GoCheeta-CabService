/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.customerhobbiesinterest;

import com.avn.ccl.dao.customerhobbiesinterest.CustomerHobbiesInterestDAO;
import com.avn.ccl.model.customerhobbiesinterests.CustomerHobbiesInterests;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : CustomerHobbiesInterest
 * @Created on : Jan 13, 2016, 10:44:28 AM
 */
public class CustomerHobbiesInterestDAOImpl implements CustomerHobbiesInterestDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isCustomerHobbyAlreadyExist(String account_id, String hobby_id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isAlreadyExist = false;
        try {
            String sql = "SELECT COUNT(*) AS CNT FROM AVN_CUSTOMERHOBBIESINTEREST WHERE ACCOUNTID = ? AND HOBBYID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(account_id));
            statement.setLong(2, Long.valueOf(hobby_id));
            resultSet = statement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt("CNT");
            }

            if (count > 0) {
                isAlreadyExist = true;
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
        return isAlreadyExist;
    }

    @Override
    public long createCustomerHobbiesInterest(CustomerHobbiesInterests customerHobbiesInterests, String createuser) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long customer_hobby_id = 0;
        try {
            String sql = "INSERT "
                    + "INTO "
                    + "    AVN_CUSTOMERHOBBIESINTEREST "
                    + "    ("
                    + "        ACCOUNTID, "
                    + "        HOBBYID, "
                    + "        HOBBYCOMMNT, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME, "
                    + "        CREATEDUSER"
                    + "    ) "
                    + "    VALUES "
                    + "    (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
            connection = dataSource.getConnection();
            String[] generatedColumns = {"ID"};
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setLong(1, Long.valueOf(customerHobbiesInterests.getAccount_id()));
            statement.setInt(2, Integer.valueOf(customerHobbiesInterests.getHobby_id()));
            if (customerHobbiesInterests.getHobby_comment() != null && !customerHobbiesInterests.getHobby_comment().isEmpty()) {
                statement.setString(3, customerHobbiesInterests.getHobby_comment());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            statement.setString(4, createuser);
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                customer_hobby_id = resultSet.getLong(1);
            }
        } catch (SQLException sqle) {
            throw sqle;
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
        return customer_hobby_id;
    }

    @Override
    public void deleteCustomerHobbiesInterest(String customerhobbyid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_CUSTOMERHOBBIESINTEREST WHERE ID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(customerhobbyid));
            statement.execute();
        } catch (SQLException sqle) {
            throw sqle;
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
    public List<CustomerHobbiesInterests> getCustomerHobbiesAndInterestByAccountId(String accountid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CustomerHobbiesInterests> customerHobbiesInterests;
        try {
            String sql = "SELECT CHI.ID, "
                    + "  CHI.ACCOUNTID, "
                    + "  CHI.HOBBYID, "
                    + "  HI.DESCRIPTION, "
                    + "  CHI.HOBBYCOMMNT "
                    + "FROM AVN_CUSTOMERHOBBIESINTEREST CHI "
                    + "INNER JOIN AVN_HOBBIESINTERESTS HI "
                    + "ON CHI.HOBBYID      = HI.ID "
                    + "WHERE CHI.ACCOUNTID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, accountid);
            resultSet = statement.executeQuery();
            customerHobbiesInterests = new ArrayList<>();
            while (resultSet.next()) {
                CustomerHobbiesInterests customerHobbiesInterest = new CustomerHobbiesInterests();
                customerHobbiesInterest.setAccount_id(resultSet.getString("ACCOUNTID"));
                customerHobbiesInterest.setHobby_id(resultSet.getString("HOBBYID"));
                customerHobbiesInterest.setHobby_description(resultSet.getString("DESCRIPTION"));
                customerHobbiesInterest.setHobby_comment(resultSet.getString("HOBBYCOMMNT"));
                customerHobbiesInterests.add(customerHobbiesInterest);
            }
        } catch (SQLException sqle) {
            throw sqle;
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
        return customerHobbiesInterests;
    }
}

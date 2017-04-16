/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.address;

import com.avn.ccl.dao.address.AddressDAO;
import com.avn.ccl.model.address.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : AddressDAOImpl
 * @Created on : Aug 3, 2015, 5:23:26 PM
 */
public class AddressDAOImpl implements AddressDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long createAddress(Address address) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long addressid = 0;
        try {
            String sql = "INSERT "
                    + "INTO "
                    + "    AVN_ACCOUNTADDRESS "
                    + "    ("
                    + "        ACCOUNT, "
                    + "        ADDRESSTYPEID, "
                    + "        ADDRESS1, "
                    + "        ADDRESS2, "
                    + "        ADDRESS3, "
                    + "        COUNTRY, "
                    + "        PROVINCE, "
                    + "        DISTRICT, "
                    + "        CITY, "
                    + "        GSDIVISION, "
                    + "        GPS, "
                    + "        LANDPHONE, "
                    + "        BILLINGPROOF, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME"
                    + "    ) "
                    + "    VALUES "
                    + "    ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            connection = dataSource.getConnection();
            String[] generatedColumns = {"ID"};
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setLong(1, Long.valueOf(address.getAccount_id()));
            statement.setInt(2, Integer.valueOf(address.getAddress_type()));
            statement.setString(3, address.getAddress_line_01());
            statement.setString(4, address.getAddress_line_02());
            statement.setString(5, address.getAddress_line_03());
            if (address.getCountry() != null && !address.getCountry().isEmpty()) {
                statement.setInt(6, Integer.valueOf(address.getCountry()));
            } else {
                statement.setNull(6, Types.INTEGER);
            }
            if (address.getProvince() != null && !address.getProvince().isEmpty()) {
                statement.setInt(7, Integer.valueOf(address.getProvince()));
            } else {
                statement.setNull(7, Types.INTEGER);
            }
            if (address.getDistrict() != null && !address.getDistrict().isEmpty()) {
                statement.setInt(8, Integer.valueOf(address.getDistrict()));
            } else {
                statement.setNull(8, Types.INTEGER);
            }
            if (address.getCity() != null && !address.getCity().isEmpty()) {
                statement.setInt(9, Integer.valueOf(address.getCity()));
            } else {
                statement.setNull(9, Types.INTEGER);
            }
            statement.setString(10, address.getGs());
            statement.setString(11, address.getGps());
            statement.setString(12, address.getLand_phone_no());
            statement.setInt(13, Integer.valueOf(address.getBilling_proof()));
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                addressid = resultSet.getLong(1);
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
        return addressid;
    }

    @Override
    public void deleteAddress(String addressid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_ACCOUNTADDRESS WHERE ID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(addressid));
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
    public int countAddress(String accountid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS CNT FROM AVN_ACCOUNTADDRESS WHERE ACCOUNT = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(accountid));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("CNT");
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
        return count;
    }

}

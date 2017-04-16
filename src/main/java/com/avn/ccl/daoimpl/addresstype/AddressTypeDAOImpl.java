/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.addresstype;

import com.avn.ccl.dao.addresstype.AddressTypeDAO;
import com.avn.ccl.model.addresstype.AddressType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : AddressTypeDAOImpl
 * @Created on : Jul 7, 2015, 11:43:15 PM
 */
public class AddressTypeDAOImpl implements AddressTypeDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getAddressTypeDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> addressTypeList = null;
        try {
            String sql = "SELECT ADDRESSTYPEID, DESCRIPTION FROM AVN_ADDRESSTYPE ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            addressTypeList = new LinkedHashMap<>();
            addressTypeList.put("", "-- Select --");
            while (resultSet.next()) {
                addressTypeList.put(resultSet.getString("ADDRESSTYPEID"), resultSet.getString("DESCRIPTION"));
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
        return addressTypeList;
    }

    @Override
    public AddressType getAddressTypeById(String addresstypeid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        AddressType addressType = null;
        try {
            String sql = "SELECT "
                    + "    ADDRESSTYPEID, "
                    /*+ "    ADDRESSTYPECODE, "*/
                    + "    DESCRIPTION, "
                    + "    SORTID, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME "
                    + "FROM "
                    + "    AVN_ADDRESSTYPE "
                    + "WHERE "
                    + "    ADDRESSTYPEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(addresstypeid));
            resultSet = statement.executeQuery();
            addressType = new AddressType();
            while (resultSet.next()) {
                addressType.setAddresstypeid(resultSet.getString("ADDRESSTYPEID"));
                /*addressType.setAddresstypecode(resultSet.getString("ADDRESSTYPECODE"));*/
                addressType.setDescription(resultSet.getString("DESCRIPTION"));
                addressType.setSortid(resultSet.getString("SORTID"));
                addressType.setCreateddatetime(resultSet.getTimestamp("CREATEDDATETIME"));
                addressType.setLastupdateddatetime(resultSet.getTimestamp("LASTUPDATEDDATETIME"));
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
        return addressType;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.province;

import com.avn.ccl.dao.province.ProvinceDAO;
import com.avn.ccl.model.province.Province;
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
 * @Document : ProvinceDAOImpl
 * @Created on : Jul 7, 2015, 6:01:31 PM
 */
public class ProvinceDAOImpl implements ProvinceDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getProvinceDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT PROVINCEID, PROVINCENAME FROM AVN_PROVINCE ORDER BY PROVINCENAME";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("PROVINCEID"), resultSet.getString("PROVINCENAME"));
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
    public List<Province> getProvinceListByCountry(String countryid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Province> list = null;
        try {
            String sql = "SELECT PROVINCEID, PROVINCENAME FROM AVN_PROVINCE WHERE COUNTRYID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(countryid));
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            Province province;
            while (resultSet.next()) {
                province = new Province();
                province.setProvinceid(resultSet.getString("PROVINCEID"));
                province.setProvincename(resultSet.getString("PROVINCENAME"));
                list.add(province);
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
        return list;
    }
}

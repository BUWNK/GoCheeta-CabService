/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.district;

import com.avn.ccl.dao.district.DistrictDAO;
import com.avn.ccl.model.district.District;
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
 * @Document : DistrictDAOImpl
 * @Created on : Jul 7, 2015, 6:01:10 PM
 */
public class DistrictDAOImpl implements DistrictDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getDistrictDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> districtList = null;
        try {
            String sql = "SELECT DISTRICTID, DISTRICTNAME FROM AVN_DISTRIC ORDER BY DISTRICTNAME";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            districtList = new LinkedHashMap<>();
            districtList.put("", "-- Select --");
            while (resultSet.next()) {
                districtList.put(resultSet.getString("DISTRICTID"), resultSet.getString("DISTRICTNAME"));
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
        return districtList;
    }

    @Override
    public List<District> getDistrictListByProvince(String provinceid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<District> districtList = null;
        try {
            String sql = "SELECT DISTRICTID, DISTRICTNAME FROM AVN_DISTRIC WHERE PROVINCEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(provinceid));
            resultSet = statement.executeQuery();
            districtList = new ArrayList<>();
            District district;
            while (resultSet.next()) {
                district = new District();
                district.setDistrictid(resultSet.getString("DISTRICTID"));
                district.setDistrictname(resultSet.getString("DISTRICTNAME"));
                districtList.add(district);
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
        return districtList;
    }
}

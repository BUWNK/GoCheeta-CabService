/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.city;

import com.avn.ccl.dao.city.CityDAO;
import com.avn.ccl.model.city.City;
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
 * @Document : CityDAOImpl
 * @Created on : Jul 7, 2015, 6:01:46 PM
 */
public class CityDAOImpl implements CityDAO {

    private DataSource dataSource;
//    private ResultSet resultSet = null;
//    private Connection connection = null;
//    private PreparedStatement statement = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, String> getCityDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> cityList = null;
        try {
            String sql = "SELECT CITYID, CITYNAME FROM AVN_CITY ORDER BY CITYNAME";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            cityList = new LinkedHashMap<>();
            cityList.put("", "-- Select --");
            while (resultSet.next()) {
                cityList.put(resultSet.getString("CITYID"), resultSet.getString("CITYNAME"));
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
        return cityList;
    }

    @Override
    public List<City> getCityListByDistrict(String districtid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<City> cityList = null;
        try {
            String sql = "SELECT CITYID, CITYNAME FROM AVN_CITY WHERE DISTRICTID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(districtid));
            resultSet = statement.executeQuery();
            cityList = new ArrayList<>();
            City city;
            while (resultSet.next()) {
                city = new City();
                city.setCityid(resultSet.getString("CITYID"));
                city.setCityname(resultSet.getString("CITYNAME"));
                cityList.add(city);
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
        return cityList;
    }

}

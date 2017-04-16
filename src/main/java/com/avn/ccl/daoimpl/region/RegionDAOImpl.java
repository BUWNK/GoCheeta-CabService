/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.region;

import com.avn.ccl.model.region.Region;
import com.avn.ccl.model.status.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * @Author : ISURU
 * @Document : RegionDAOImpl
 * @Created on : Jun 22, 2016, 9:13:19 AM
 */
public class RegionDAOImpl {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Region> getNotAssignRegionTarget(int targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Region> regionList = new ArrayList<>();
        try {
            String sql = "SELECT REGIONID,REGIONNAME FROM AVN_REGION WHERE REGIONID NOT IN (SELECT REGIONID FROM AVN_REGIONALTARGET WHERE TARGETID=?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, targetid);
            resultSet = statement.executeQuery();
            Region region;
            while (resultSet.next()) {
                region = new Region();
                region.setRegionid(resultSet.getString("REGIONID"));
                region.setRegiondes(resultSet.getString("REGIONNAME"));
                regionList.add(region);
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
          return regionList;
    }

    
    
    
     public List<Region> getAssignRegionTarget(int targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Region> regionList = new ArrayList<>();
        try {
            String sql = "SELECT REGIONID,REGIONNAME FROM AVN_REGION WHERE REGIONID  IN (SELECT REGIONID FROM AVN_REGIONALTARGET WHERE TARGETID=?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, targetid);
            resultSet = statement.executeQuery();
            Region region;
            while (resultSet.next()) {
                region = new Region();
                region.setRegionid(resultSet.getString("REGIONID"));
                region.setRegiondes(resultSet.getString("REGIONNAME"));
                regionList.add(region);
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
          return regionList;
    }

    
    
    
}

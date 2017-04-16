/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.dependent;

import com.avn.ccl.dao.dependent.DependentDAO;
import com.avn.ccl.model.dependent.Dependent;
import com.avn.ccl.util.Common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : DependentDAOImpl
 * @Created on : Aug 6, 2015, 10:23:47 AM
 */
public class DependentDAOImpl implements DependentDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long createDependent(Dependent dependent) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long dependentid = 0;
        try {
            String sql = "INSERT "
                    + "INTO "
                    + "    AVN_ACCOUNTDEPENDANTS "
                    + "    ("
                    + "        ACCOUNTID, "
                    + "        DEPENDANTTYPE, "
                    + "        DEPENDANTNAME, "
                    + "        DOB, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME"
                    + "    ) "
                    + "    VALUES "
                    + "    (?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            connection = dataSource.getConnection();
            String[] generatedColumns = {"ID"};
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setLong(1, Long.valueOf(dependent.getAccount_id()));
            statement.setInt(2, Integer.valueOf(dependent.getDependent_relationship()));
            statement.setString(3, dependent.getDependent_name_in_full());
            if (dependent.getDependent_date_of_birth() != null && !dependent.getDependent_date_of_birth().isEmpty()) {
                Date dob = Common.getDateFromString("yyyy-MM-dd", dependent.getDependent_date_of_birth());
                statement.setDate(4, new java.sql.Date(dob.getTime()));
            } else {
                statement.setNull(4, Types.DATE);
            }
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                dependentid = resultSet.getLong(1);
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
        return dependentid;
    }

    @Override
    public void deleteDependent(String dependentid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_ACCOUNTDEPENDANTS WHERE ID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.valueOf(dependentid));
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
    public List<Dependent> selectDelendentsByAccountId(String accountid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dependent> dependents;
        try {
            String sql = "SELECT "
                    + "    ACCDEPT.ID, "
                    + "    ACCDEPT.ACCOUNTID, "
                    + "    ACCDEPT.DEPENDANTTYPE, "
                    + "    DEPT.DESCRIPTION, "
                    + "    ACCDEPT.DEPENDANTNAME, "
                    + "    ACCDEPT.DOB, "
                    + "    ACCDEPT.CREATEDDATETIME, "
                    + "    ACCDEPT.LASTUPDATEDDATETIME "
                    + "FROM "
                    + "    AVN_ACCOUNTDEPENDANTS ACCDEPT "
                    + "INNER JOIN AVN_DEPENDENTTYPE DEPT ON DEPT.ID = ACCDEPT.DEPENDANTTYPE "
                    + "WHERE "
                    + "    ACCDEPT.ACCOUNTID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, accountid);
            resultSet = statement.executeQuery();
            dependents = new ArrayList<>();
            while (resultSet.next()) {
                Dependent dependent = new Dependent();
                dependent.setDependent_id(resultSet.getString("ID"));
                dependent.setAccount_id(resultSet.getString("ACCOUNTID"));
                dependent.setDependent_relationship(resultSet.getString("DEPENDANTTYPE"));
                dependent.setDependent_relationship_description(resultSet.getString("DESCRIPTION"));
                dependent.setDependent_name_in_full(resultSet.getString("DEPENDANTNAME") == null ? "" : resultSet.getString("DEPENDANTNAME"));
                dependent.setDependent_date_of_birth(resultSet.getTimestamp("DOB") == null ? "" : Common.getStringFormatDate("yyyy-MM-dd", new Date(resultSet.getTimestamp("DOB").getTime())));
                dependents.add(dependent);
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
        return dependents;
    }
}

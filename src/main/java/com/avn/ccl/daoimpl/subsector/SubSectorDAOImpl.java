/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.subsector;

import com.avn.ccl.dao.subsector.SubSectorDAO;
import com.avn.ccl.model.occupation.Occupation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : SubSectorDAOImpl
 * @Created on : Aug 4, 2015, 4:01:52 PM
 */
public class SubSectorDAOImpl implements SubSectorDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createSubSectors(Occupation occupation) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql_delete = "DELETE AVN_ACCOUNTBUSSUBSECTOR WHERE ACCOUNT = ?";
            String sql_insert = "INSERT "
                    + "INTO "
                    + "    AVN_ACCOUNTBUSSUBSECTOR "
                    + "    ("
                    + "        SUBSECTOR, "
                    + "        ACCOUNT, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME"
                    + "    ) "
                    + "    VALUES "
                    + "    ( ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql_delete);
            statement.setLong(1, Long.valueOf(occupation.getAccount_id()));
            statement.execute();
            for (String subsector : occupation.getSubsectors()) {
                statement = connection.prepareStatement(sql_insert);
                statement.setLong(1, Long.valueOf(subsector));
                statement.setLong(2, Long.valueOf(occupation.getAccount_id()));
                statement.execute();
            }
            connection.commit();
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

}

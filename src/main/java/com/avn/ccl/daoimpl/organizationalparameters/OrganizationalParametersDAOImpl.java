/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.organizationalparameters;

import com.avn.ccl.dao.organizationalparameters.OrganizationalParametersDAO;
import static com.avn.ccl.util.varlist.MasterDataVarList.CCL_CODE_DEFAULT_CC_NOTIFICATION_PERSON;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;

/**
 *
 * @author Isuru
 */
public class OrganizationalParametersDAOImpl implements OrganizationalParametersDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getDefaultCCPerson() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String emails = null;
        try {
            String sql = "Select PARAMETERVALUE From AVN_ORGANIZATIONPARAMETERS where PARAMETERID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, CCL_CODE_DEFAULT_CC_NOTIFICATION_PERSON);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                emails = resultSet.getString("PARAMETERVALUE");
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
        return emails;
    }
}

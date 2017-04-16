/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.contact;

import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author : Roshen Dilshan
 * @Document : ContactDAOImpl
 * @Created on : Jun 8, 2016, 10:27:58 AM
 */
@Repository("ContactDAO")
public class ContactDAOImpl implements ContactDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public long getContactCountForUserDateReange(String username, Date[] range) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT FROM AVN_CONTACTS WHERE CREATEDUSER = ? AND CREATEDDATETIME BETWEEN ? AND ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{username, range[0], range[1]}, Long.class);
    }

    @Override
    public String getContactNameInFullById(long contactid) throws SQLException {
        String query = "SELECT CT.NAMEINFULL FROM AVN_CONTACTS CT WHERE CT.CONTACTID = ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{contactid}, String.class);
    }

    @Override
    public String getContactMobileById(long contactid) throws SQLException {
        String query = "SELECT CT.MOBILE FROM AVN_CONTACTS CT WHERE CT.CONTACTID = ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{contactid}, String.class);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.contact;

import java.sql.SQLException;
import java.util.Date;

/**
 * @Author : Roshen Dilshan
 * @Document : ContactDAO
 * @Created on : Jun 8, 2016, 10:27:35 AM
 */
public interface ContactDAO {

    public long getContactCountForUserDateReange(String username, Date[] range) throws SQLException;

    public String getContactNameInFullById(long contactid) throws SQLException;
    
    public String getContactMobileById(long contactid) throws SQLException;

}

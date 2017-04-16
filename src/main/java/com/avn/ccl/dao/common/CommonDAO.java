/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.common;

import java.sql.SQLException;
import java.util.Date;
import org.json.JSONArray;

/**
 * @Author : Roshen Dilshan
 * @Document : CommonDAO
 * @Created on : Oct 8, 2015, 1:41:36 PM
 */
public interface CommonDAO {

    public Date getCurentTimesStamp() throws SQLException;

    public JSONArray getAssignedTaskList(int userroleid) throws Exception;

    public int getMaxinvaidLoginCount() throws Exception;

    public boolean checkPrivilage(String sectionid, String subsectionid, int task, JSONArray objList) throws Exception;
    
    public String getOgrParaValueByRecordId(int id) throws Exception;
    
    public Date[] getCurrentQuerter() throws SQLException;
    
}

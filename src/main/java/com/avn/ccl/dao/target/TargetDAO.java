/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.target;

import com.avn.ccl.model.performancedashboard.Performancedashboard;
import com.avn.ccl.model.target.Target;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 * @Author : Roshen Dilshan
 * @Document : TargetDAO
 * @Created on : May 10, 2016, 10:36:32 AM
 */
public interface TargetDAO {

    public Target getTargetByID(long targetid) throws SQLException;
    
    public List<Target> getTargetDropDownList(String username) throws SQLException;
    
    public Map<String, String> getDropdownValueList(String username, Performancedashboard performancedashboard) throws SQLException, DataAccessException;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.targetperiod;

import com.avn.ccl.model.targetperiod.TargetPeriod;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author : Roshen Dilshan
 * @Document : TargetPeriodDAO
 * @Created on : May 9, 2016, 3:24:15 PM
 */
public interface TargetPeriodDAO {
    
    public TargetPeriod getTargetPeriodById(int periodid) throws SQLException;
    
    public List<TargetPeriod> getTargetPeriodDropDownList() throws SQLException;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.service.target;

import com.avn.ccl.model.target.Target;
import java.sql.SQLException;

/**
 * @Author : Roshen Dilshan
 * @Document : TargetService
 * @Created on : Aug 19, 2016, 11:52:13 AM
 */
public interface TargetService {
    
    public Target getTargetByID(long targetid) throws SQLException;

}

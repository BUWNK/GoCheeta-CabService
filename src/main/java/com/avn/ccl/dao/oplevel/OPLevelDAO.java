/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.oplevel;

import com.avn.ccl.model.oplevel.OPLevel;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : OcupationLevel
 * @Date : Jul 23, 2015 2:40:00 PM
 */
public interface OPLevelDAO {

    public Map<String, String> getLevelDropdownList() throws Exception;
    
    public OPLevel getLevelById(String levelid) throws Exception;
    
    public OPLevel getLevelByCode(String levelcode) throws Exception;
    
}

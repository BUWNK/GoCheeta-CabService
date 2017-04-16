/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.calldirection;

import com.avn.ccl.model.calldirection.CallDirection;
import java.util.Map;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : CallDirectionDAO
 * @Created on : Aug 14, 2015, 5:36:05 PM
 */
public interface CallDirectionDAO {
    
    public Map<String, String> getCallDirectionDropdownList() throws Exception;
    
    public CallDirection getCallDirectionById(String id) throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.targettype;

import java.util.Map;

/**
 * @Author : ISURU
 * @Document : TargetTypeDAO
 * @Created on : Jul 1, 2016, 10:15:09 AM
 */
public interface TargetTypeDAO {
    
    public Map<String, String> getTargetTypeDropdownList() throws Exception;

}

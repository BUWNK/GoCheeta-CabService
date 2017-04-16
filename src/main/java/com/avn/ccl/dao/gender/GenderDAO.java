/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.gender;

import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : GenderDAO
 * @Created on : Oct 15, 2015, 2:28:17 PM
 */
public interface GenderDAO {
    
    public Map<String, String> getGenderDropdownList() throws Exception;

}

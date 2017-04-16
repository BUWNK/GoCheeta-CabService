/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.maritalstatus;

import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : MaritalStatusDAO
 * @Created on : Oct 15, 2015, 2:22:59 PM
 */
public interface MaritalStatusDAO {
    
    public Map<String, String> getMaritalStatusCodeDropdownList() throws Exception;

}

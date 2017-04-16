/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.country;

import com.avn.ccl.model.counrty.Country;
import java.util.Map;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : CountryDAO
 * @Created on : Jul 7, 2015, 5:46:43 PM
 */
public interface CountryDAO {

    public Map<String, String> getCountryDropdownList() throws Exception;
    
    public Country getCountryById(String id) throws Exception;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.city;

import com.avn.ccl.model.city.City;
import java.util.List;
import java.util.Map;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : CityDAO
 * @Created on : Jul 7, 2015, 5:59:34 PM
 */
public interface CityDAO {
    
    public Map<String, String> getCityDropdownList() throws Exception;

    public List<City> getCityListByDistrict(String districtid) throws Exception;
    
}

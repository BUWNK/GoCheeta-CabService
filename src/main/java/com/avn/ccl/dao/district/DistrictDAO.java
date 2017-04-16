/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.district;

import com.avn.ccl.model.district.District;
import java.util.List;
import java.util.Map;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : DistrictDAO
 * @Created on : Jul 7, 2015, 5:59:21 PM
 */
public interface DistrictDAO {

    public Map<String, String> getDistrictDropdownList() throws Exception;
    
    public List<District> getDistrictListByProvince(String provinceid) throws Exception;
    
}

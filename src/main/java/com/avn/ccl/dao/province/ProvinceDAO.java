/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.province;

import com.avn.ccl.model.province.Province;
import java.util.List;
import java.util.Map;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : ProvinceDAO
 * @Created on : Jul 7, 2015, 5:59:10 PM
 */
public interface ProvinceDAO {

    public Map<String, String> getProvinceDropdownList() throws Exception;

    public List<Province> getProvinceListByCountry(String countryid) throws Exception;

}

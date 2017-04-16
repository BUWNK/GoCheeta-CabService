/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.dependenttype;

import com.avn.ccl.model.dependentrype.DependentType;
import java.util.Map;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : DependentType
 * @Created on : Aug 6, 2015, 8:55:36 AM
 */
public interface DependentTypeDAO {

    public Map<String, String> getDependentTypeDropdownList() throws Exception;

    public DependentType getDependentTypeById(String dependentid) throws Exception;

}

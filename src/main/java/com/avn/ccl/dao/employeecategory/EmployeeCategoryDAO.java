/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.employeecategory;

import com.avn.ccl.model.employeecategory.EmployeeCategory;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : EmployeeCategoryDAO
 * @Created on : Oct 2, 2015, 2:15:19 PM
 */
public interface EmployeeCategoryDAO {
    
    public Map<String, String> getEmployeeCategoryDropdownList() throws Exception;
    
    public EmployeeCategory getEmployeeCategoryById (String id) throws Exception;
    
    public Map<String, String> getEmployeeCategoryDropdownListSingleBr() throws Exception;
    
    public Map<String, String> getEmployeeCategoryDropdownListMultiBr() throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.accountcategory;

import com.avn.ccl.model.accountcategory.AccountCategory;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : AccountCategoryDAO
 * @Created on : Oct 14, 2015, 1:54:51 PM
 */
public interface AccountCategoryDAO {
    
    public Map<String, String> getAccountCategoryDropdownList() throws Exception;
    
    public AccountCategory getAccountCategoryById(String id) throws Exception;

}

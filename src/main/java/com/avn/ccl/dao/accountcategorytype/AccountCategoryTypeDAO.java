/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.accountcategorytype;

import com.avn.ccl.model.accountcategegorytype.AccountCategoryType;
import java.util.List;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : AccountCategoryType
 * @Created on : Oct 14, 2015, 2:16:31 PM
 */
public interface AccountCategoryTypeDAO {

    public Map<String, String> getAccountCategoryTypeDropdownList() throws Exception;

    public Map<String, String> getAccountCategoryTypeDropdownListByCategory(String category) throws Exception;

    public AccountCategoryType getAccountCategoryTypeById(String id) throws Exception;

    public List<AccountCategoryType> getAccountCategoryTypesByCategory(String category) throws Exception;

}

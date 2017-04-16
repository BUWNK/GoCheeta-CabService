/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.customercodetype;

import com.avn.ccl.model.customercodetype.CustomerCodeType;
import java.util.List;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : CustomerCodeDAO
 * @Created on : Oct 15, 2015, 9:50:08 AM
 */
public interface CustomerCodeTypeDAO {

    public Map<String, String> getCustomerCodeTypeDropdownList() throws Exception;

    public Map<String, String> getCustomerCodeTypeDropdownListIndividual() throws Exception;

    public Map<String, String> getCustomerCodeTypeDropdownListCorporate() throws Exception;

    public CustomerCodeType getCustomerCodeTypeById(String id) throws Exception;

    public List<CustomerCodeType> getCustomerCodeTypesIndividual() throws Exception;

    public List<CustomerCodeType> getCustomerCodeTypesCorporate() throws Exception;

}

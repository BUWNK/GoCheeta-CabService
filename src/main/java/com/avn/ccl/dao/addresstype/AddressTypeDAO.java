/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.addresstype;

import com.avn.ccl.model.addresstype.AddressType;
import java.util.Map;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : AddressTypeDAO
 * @Created on : Jul 7, 2015, 11:42:29 PM
 */
public interface AddressTypeDAO {

    public Map<String, String> getAddressTypeDropdownList() throws Exception;
    
    public AddressType getAddressTypeById(String addresstypeid) throws Exception;
    
}

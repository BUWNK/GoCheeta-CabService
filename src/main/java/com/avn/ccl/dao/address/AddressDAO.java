/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.address;

import com.avn.ccl.model.address.Address;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : AddressDAO
 * @Created on : Aug 3, 2015, 5:21:53 PM
 */
public interface AddressDAO {

    public long createAddress(Address address) throws Exception;

    public void deleteAddress(String addressid) throws Exception;
    
    public int countAddress(String accountid) throws Exception;

}

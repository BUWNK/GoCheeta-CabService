/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.customerhobbiesinterest;

import com.avn.ccl.model.customerhobbiesinterests.CustomerHobbiesInterests;
import java.util.List;

/**
 * @Author : Roshen Dilshan
 * @Document : CustomerHobbiesInterest
 * @Created on : Jan 13, 2016, 10:41:49 AM
 */
public interface CustomerHobbiesInterestDAO {

    public boolean isCustomerHobbyAlreadyExist(String account_id, String hobby_id) throws Exception;

    public long createCustomerHobbiesInterest(CustomerHobbiesInterests customerHobbiesInterests, String createuser) throws Exception;

    public void deleteCustomerHobbiesInterest(String customerhobbyid) throws Exception;
    
    public List<CustomerHobbiesInterests> getCustomerHobbiesAndInterestByAccountId(String accountid) throws Exception;

}

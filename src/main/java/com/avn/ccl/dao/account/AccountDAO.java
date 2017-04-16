/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.account;

import com.avn.ccl.model.account.Account;
import com.avn.ccl.model.occupation.Occupation;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : AccountDAO
 * @Created on : Jul 31, 2015, 1:05:28 PM
 */
public interface AccountDAO {

    public long createAccount(Account account) throws Exception;

    public void updateAccountPersonalDetails(Account account) throws Exception;

    public void updateAccountOccupation(Occupation occupation) throws Exception;

    public void updateAccountSGC(Account account) throws Exception;

    public void updateAccountOther(Account account) throws Exception;

    public void updateAccountCCID(Account account) throws Exception;

    public void selectAccountByCCID(Account account) throws Exception;

    public Account selectAccountByCustomerCode(Account account) throws Exception;

    public void updateAccountCorporate(Account account) throws Exception;

    public void updateAccountContactPerson(Account account) throws Exception;

}

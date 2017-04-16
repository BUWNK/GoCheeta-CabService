/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.accountcategory;

/**
 * @Author : Roshen Dilshan
 * @Document : AccountCategory
 * @Created on : Oct 14, 2015, 1:55:25 PM
 */

public class AccountCategory {
    
    private int accountcategoryid;
    private String description;

    /**
     * @return the accountcategoryid
     */
    public int getAccountcategoryid() {
        return accountcategoryid;
    }

    /**
     * @param accountcategoryid the accountcategoryid to set
     */
    public void setAccountcategoryid(int accountcategoryid) {
        this.accountcategoryid = accountcategoryid;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.accountcategegorytype;

/**
 * @Author : Roshen Dilshan
 * @Document : AccountCategoryType
 * @Created on : Oct 14, 2015, 2:18:23 PM
 */
public class AccountCategoryType {

    private int accountcategorytypeid;
    private int accountcategory;
    private String description;

    /**
     * @return the accountcategorytypeid
     */
    public int getAccountcategorytypeid() {
        return accountcategorytypeid;
    }

    /**
     * @param accountcategorytypeid the accountcategorytypeid to set
     */
    public void setAccountcategorytypeid(int accountcategorytypeid) {
        this.accountcategorytypeid = accountcategorytypeid;
    }

    /**
     * @return the accountcategory
     */
    public int getAccountcategory() {
        return accountcategory;
    }

    /**
     * @param accountcategory the accountcategory to set
     */
    public void setAccountcategory(int accountcategory) {
        this.accountcategory = accountcategory;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.account;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : AccountSearchData
 * @Created on : Aug 10, 2015, 4:31:48 PM
 */

public class AccountSearchData {
    
    private String ccid;
    private String account_category;
    private String account_category_type;
    private String customer_code_type;
    private String customer_code;
    private String branch;

    /**
     * @return the ccid
     */
    public String getCcid() {
        return ccid;
    }

    /**
     * @param ccid the ccid to set
     */
    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    /**
     * @return the account_category
     */
    public String getAccount_category() {
        return account_category;
    }

    /**
     * @param account_category the account_category to set
     */
    public void setAccount_category(String account_category) {
        this.account_category = account_category;
    }

    /**
     * @return the account_category_type
     */
    public String getAccount_category_type() {
        return account_category_type;
    }

    /**
     * @param account_category_type the account_category_type to set
     */
    public void setAccount_category_type(String account_category_type) {
        this.account_category_type = account_category_type;
    }

    /**
     * @return the customer_code_type
     */
    public String getCustomer_code_type() {
        return customer_code_type;
    }

    /**
     * @param customer_code_type the customer_code_type to set
     */
    public void setCustomer_code_type(String customer_code_type) {
        this.customer_code_type = customer_code_type;
    }

    /**
     * @return the customer_code
     */
    public String getCustomer_code() {
        return customer_code;
    }

    /**
     * @param customer_code the customer_code to set
     */
    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    /**
     * @return the branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

}

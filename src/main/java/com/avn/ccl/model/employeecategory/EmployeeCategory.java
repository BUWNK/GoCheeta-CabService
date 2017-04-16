/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.employeecategory;

/**
 * @Author : Roshen Dilshan
 * @Document : EmployeeCategory
 * @Created on : Oct 5, 2015, 9:25:05 AM
 */

public class EmployeeCategory {
    
    private String categoryid;
    private String categorydescription;

    /**
     * @return the categoryid
     */
    public String getCategoryid() {
        return categoryid;
    }

    /**
     * @param categoryid the categoryid to set
     */
    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * @return the categorydescription
     */
    public String getCategorydescription() {
        return categorydescription;
    }

    /**
     * @param categorydescription the categorydescription to set
     */
    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

}

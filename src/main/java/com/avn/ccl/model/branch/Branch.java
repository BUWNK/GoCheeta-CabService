/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.branch;

/**
 *
 * @author Hirantha
 */
public class Branch {

    private int branchId;
    private String branchDesc;
    private String branchCode;
    private String aliasName;

    public String getBranchDesc() {
        return branchDesc;
    }

    public void setBranchDesc(String branchDesc) {
        this.branchDesc = branchDesc;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the aliasName
     */
    public String getAliasName() {
        return aliasName;
    }

    /**
     * @param aliasName the aliasName to set
     */
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

}

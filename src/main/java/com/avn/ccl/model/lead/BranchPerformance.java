/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.lead;

import java.math.BigDecimal;

/**
 * @Author : Roshen Dilshan
 * @Document : BranchPerformance
 * @Created on : Aug 19, 2016, 10:28:04 PM
 */
public class BranchPerformance {
    
    private String branch;
    private BigDecimal branchtarget;
    private BigDecimal fourecastamount;
    private BigDecimal achievedamount;

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

    /**
     * @return the branchtarget
     */
    public BigDecimal getBranchtarget() {
        return branchtarget;
    }

    /**
     * @param branchtarget the branchtarget to set
     */
    public void setBranchtarget(BigDecimal branchtarget) {
        this.branchtarget = branchtarget;
    }

    /**
     * @return the fourecastamount
     */
    public BigDecimal getFourecastamount() {
        return fourecastamount;
    }

    /**
     * @param fourecastamount the fourecastamount to set
     */
    public void setFourecastamount(BigDecimal fourecastamount) {
        this.fourecastamount = fourecastamount;
    }

    /**
     * @return the achievedamount
     */
    public BigDecimal getAchievedamount() {
        return achievedamount;
    }

    /**
     * @param achievedamount the achievedamount to set
     */
    public void setAchievedamount(BigDecimal achievedamount) {
        this.achievedamount = achievedamount;
    }

    @Override
    public String toString() {
        return "BranchPerformance{" + "branch=" + branch + ", branchtarget=" + branchtarget + ", fourecastamount=" + fourecastamount + ", achievedamount=" + achievedamount + '}';
    }

}

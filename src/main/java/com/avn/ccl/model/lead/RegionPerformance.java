/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.lead;

import java.math.BigDecimal;

/**
 * @Author : Roshen Dilshan
 * @Document : RegionPerformance
 * @Created on : Aug 20, 2016, 2:27:10 AM
 */
public class RegionPerformance {
    
    private String region;
    private BigDecimal regiontarget;
    private BigDecimal fourecastamount;
    private BigDecimal achievedamount;

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the regiontarget
     */
    public BigDecimal getRegiontarget() {
        return regiontarget;
    }

    /**
     * @param regiontarget the regiontarget to set
     */
    public void setRegiontarget(BigDecimal regiontarget) {
        this.regiontarget = regiontarget;
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

}

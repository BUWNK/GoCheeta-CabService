/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.targetperiod;

import java.util.Date;

/**
 * @Author : Roshen Dilshan
 * @Document : TargetPeriod
 * @Created on : May 9, 2016, 3:27:00 PM
 */
public class TargetPeriod {
    
    private int periodid;
    private String description;
    private int sortid;
    private int periodinmonths;
    private String createduser;
    private Date createddatetime;
    private Date lastupdateddatetime;

    /**
     * @return the periodid
     */
    public int getPeriodid() {
        return periodid;
    }

    /**
     * @param periodid the periodid to set
     */
    public void setPeriodid(int periodid) {
        this.periodid = periodid;
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

    /**
     * @return the sortid
     */
    public int getSortid() {
        return sortid;
    }

    /**
     * @param sortid the sortid to set
     */
    public void setSortid(int sortid) {
        this.sortid = sortid;
    }

    /**
     * @return the periodinmonths
     */
    public int getPeriodinmonths() {
        return periodinmonths;
    }

    /**
     * @param periodinmonths the periodinmonths to set
     */
    public void setPeriodinmonths(int periodinmonths) {
        this.periodinmonths = periodinmonths;
    }

    /**
     * @return the createduser
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * @param createduser the createduser to set
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * @return the createddatetime
     */
    public Date getCreateddatetime() {
        return createddatetime;
    }

    /**
     * @param createddatetime the createddatetime to set
     */
    public void setCreateddatetime(Date createddatetime) {
        this.createddatetime = createddatetime;
    }

    /**
     * @return the lastupdateddatetime
     */
    public Date getLastupdateddatetime() {
        return lastupdateddatetime;
    }

    /**
     * @param lastupdateddatetime the lastupdateddatetime to set
     */
    public void setLastupdateddatetime(Date lastupdateddatetime) {
        this.lastupdateddatetime = lastupdateddatetime;
    }

    @Override
    public String toString() {
        return "TargetPeriod{" + "periodid=" + periodid + ", description=" + description + ", sortid=" + sortid + ", periodinmonths=" + periodinmonths + ", createduser=" + createduser + ", createddatetime=" + createddatetime + ", lastupdateddatetime=" + lastupdateddatetime + '}';
    }

}

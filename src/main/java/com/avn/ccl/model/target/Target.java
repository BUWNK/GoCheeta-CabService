/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.target;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : Roshen Dilshan
 * @Document : Target
 * @Created on : May 10, 2016, 10:32:57 AM
 */
public class Target {

    private long targetid;
    private String description;
    private int productid;
    private BigDecimal revenue;
    private int targetgroupid;
    private int targetperiodid;
    private Date targetstartdate;
    private Date targetenddate;
    private Date createddatetime;
    private Date lastupdateddatetime;
    private String createduser;

    /**
     * @return the targetid
     */
    public long getTargetid() {
        return targetid;
    }

    /**
     * @param targetid the targetid to set
     */
    public void setTargetid(long targetid) {
        this.targetid = targetid;
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
     * @return the productid
     */
    public int getProductid() {
        return productid;
    }

    /**
     * @param productid the productid to set
     */
    public void setProductid(int productid) {
        this.productid = productid;
    }

    /**
     * @return the revenue
     */
    public BigDecimal getRevenue() {
        return revenue;
    }

    /**
     * @param revenue the revenue to set
     */
    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    /**
     * @return the targetgroupid
     */
    public int getTargetgroupid() {
        return targetgroupid;
    }

    /**
     * @param targetgroupid the targetgroupid to set
     */
    public void setTargetgroupid(int targetgroupid) {
        this.targetgroupid = targetgroupid;
    }

    /**
     * @return the targetperiodid
     */
    public int getTargetperiodid() {
        return targetperiodid;
    }

    /**
     * @param targetperiodid the targetperiodid to set
     */
    public void setTargetperiodid(int targetperiodid) {
        this.targetperiodid = targetperiodid;
    }

    /**
     * @return the targetstartdate
     */
    public Date getTargetstartdate() {
        return targetstartdate;
    }

    /**
     * @param targetstartdate the targetstartdate to set
     */
    public void setTargetstartdate(Date targetstartdate) {
        this.targetstartdate = targetstartdate;
    }

    /**
     * @return the targetenddate
     */
    public Date getTargetenddate() {
        return targetenddate;
    }

    /**
     * @param targetenddate the targetenddate to set
     */
    public void setTargetenddate(Date targetenddate) {
        this.targetenddate = targetenddate;
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

}

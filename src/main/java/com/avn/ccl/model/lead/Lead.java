/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.lead;

import java.math.BigDecimal;

/**
 *
 * @author Rasika Madushanka
 */
public class Lead {

    private int leadid;
    private int productid;
    private long contactid;
    private BigDecimal leadamount;
    private String forcastuntildate;
    private String status;
    private String createUser;
    private int channelId;
    private int campignId;
    private String promationCode;
    private String nameinFull;
    private String description;
    private String salecloseddate;
    private BigDecimal confirmedamount;
    private String lostdate;
    private int lostresoncode;
    private int accouncount;
    private int actualaccountcount;

    /**
     * @return the leadid
     */
    public int getLeadid() {
        return leadid;
    }

    /**
     * @param leadid the leadid to set
     */
    public void setLeadid(int leadid) {
        this.leadid = leadid;
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
     * @return the contactid
     */
    public long getContactid() {
        return contactid;
    }

    /**
     * @param contactid the contactid to set
     */
    public void setContactid(long contactid) {
        this.contactid = contactid;
    }

    /**
     * @return the forcastuntildate
     */
    public String getForcastuntildate() {
        return forcastuntildate;
    }

    /**
     * @param forcastuntildate the forcastuntildate to set
     */
    public void setForcastuntildate(String forcastuntildate) {
        this.forcastuntildate = forcastuntildate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the createUser
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser the createUser to set
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return the channelId
     */
    public int getChannelId() {
        return channelId;
    }

    /**
     * @param channelId the channelId to set
     */
    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    /**
     * @return the campignId
     */
    public int getCampignId() {
        return campignId;
    }

    /**
     * @param campignId the campignId to set
     */
    public void setCampignId(int campignId) {
        this.campignId = campignId;
    }

    /**
     * @return the promationCode
     */
    public String getPromationCode() {
        return promationCode;
    }

    /**
     * @param promationCode the promationCode to set
     */
    public void setPromationCode(String promationCode) {
        this.promationCode = promationCode;
    }

    /**
     * @return the nameinFull
     */
    public String getNameinFull() {
        return nameinFull;
    }

    /**
     * @param nameinFull the nameinFull to set
     */
    public void setNameinFull(String nameinFull) {
        this.nameinFull = nameinFull;
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
     * @return the salecloseddate
     */
    public String getSalecloseddate() {
        return salecloseddate;
    }

    /**
     * @param salecloseddate the salecloseddate to set
     */
    public void setSalecloseddate(String salecloseddate) {
        this.salecloseddate = salecloseddate;
    }

   

    /**
     * @return the lostdate
     */
    public String getLostdate() {
        return lostdate;
    }

    /**
     * @param lostdate the lostdate to set
     */
    public void setLostdate(String lostdate) {
        this.lostdate = lostdate;
    }

    /**
     * @return the lostresoncode
     */
    public int getLostresoncode() {
        return lostresoncode;
    }

    /**
     * @param lostresoncode the lostresoncode to set
     */
    public void setLostresoncode(int lostresoncode) {
        this.lostresoncode = lostresoncode;
    }

    /**
     * @return the leadamount
     */
    public BigDecimal getLeadamount() {
        return leadamount;
    }

    /**
     * @param leadamount the leadamount to set
     */
    public void setLeadamount(BigDecimal leadamount) {
        this.leadamount = leadamount;
    }

    /**
     * @return the confirmedamount
     */
    public BigDecimal getConfirmedamount() {
        return confirmedamount;
    }

    /**
     * @param confirmedamount the confirmedamount to set
     */
    public void setConfirmedamount(BigDecimal confirmedamount) {
        this.confirmedamount = confirmedamount;
    }

    /**
     * @return the accouncount
     */
    public int getAccouncount() {
        return accouncount;
    }

    /**
     * @param accouncount the accouncount to set
     */
    public void setAccouncount(int accouncount) {
        this.accouncount = accouncount;
    }

    /**
     * @return the actualaccountcount
     */
    public int getActualaccountcount() {
        return actualaccountcount;
    }

    /**
     * @param actualaccountcount the actualaccountcount to set
     */
    public void setActualaccountcount(int actualaccountcount) {
        this.actualaccountcount = actualaccountcount;
    }
}

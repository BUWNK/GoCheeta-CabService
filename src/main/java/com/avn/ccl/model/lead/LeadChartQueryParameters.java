/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.lead;

import java.util.Date;

/**
 * @Author : Roshen Dilshan
 * @Document : LeadChartQuery
 * @Created on : May 12, 2016, 6:48:42 AM
 */
public class LeadChartQueryParameters {
    
    private int statusid;
    private int productid;
    private Date fromdate;
    private Date todate;
    private String username;

    /**
     * @return the statusid
     */
    public int getStatusid() {
        return statusid;
    }

    /**
     * @param statusid the statusid to set
     */
    public void setStatusid(int statusid) {
        this.statusid = statusid;
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
     * @return the fromdate
     */
    public Date getFromdate() {
        return fromdate;
    }

    /**
     * @param fromdate the fromdate to set
     */
    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    /**
     * @return the todate
     */
    public Date getTodate() {
        return todate;
    }

    /**
     * @param todate the todate to set
     */
    public void setTodate(Date todate) {
        this.todate = todate;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.performancedashboard;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author : Roshen Dilshan
 * @Document : Performancedashboard
 * @Created on : Jun 29, 2016, 2:56:49 PM
 */
public class Performancedashboard {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
    private String targetPeriod;
    private String target;

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the targetPeriod
     */
    public String getTargetPeriod() {
        return targetPeriod;
    }

    /**
     * @param targetPeriod the targetPeriod to set
     */
    public void setTargetPeriod(String targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.event;

import java.math.BigDecimal;

/**
 *
 * @author user
 */
public class eventDetail {

    private String activityId;
    private String title;
    private String nameInFull;
    private String jobTitle;
    private String mobile;
    private String productdescription;
    private BigDecimal leadAmount;
    private String activityTypeDecription;
    private String activityDecription;
    private String activityTime;
    
    public String getActivityId(){
        return activityId; 
    }
    
    public void setActivityId(String activityId){
        this.activityId=activityId;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the nameInFull
     */
    public String getNameInFull() {
        return nameInFull;
    }

    /**
     * @param nameInFull the nameInFull to set
     */
    public void setNameInFull(String nameInFull) {
        this.nameInFull = nameInFull;
    }

    /**
     * @return the jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @param jobTitle the jobTitle to set
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the productdescription
     */
    public String getProductdescription() {
        return productdescription;
    }

    /**
     * @param productdescription the productdescription to set
     */
    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

   

    /**
     * @return the activityTypeDecription
     */
    public String getActivityTypeDecription() {
        return activityTypeDecription;
    }

    /**
     * @param activityTypeDecription the activityTypeDecription to set
     */
    public void setActivityTypeDecription(String activityTypeDecription) {
        this.activityTypeDecription = activityTypeDecription;
    }

    /**
     * @return the activityDecription
     */
    public String getActivityDecription() {
        return activityDecription;
    }

    /**
     * @param activityDecription the activityDecription to set
     */
    public void setActivityDecription(String activityDecription) {
        this.activityDecription = activityDecription;
    }

    /**
     * @return the activityTime
     */
    public String getActivityTime() {
        return activityTime;
    }

    /**
     * @param activityTime the activityTime to set
     */
    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    /**
     * @return the leadAmount
     */
    public BigDecimal getLeadAmount() {
        return leadAmount;
    }

    /**
     * @param leadAmount the leadAmount to set
     */
    public void setLeadAmount(BigDecimal leadAmount) {
        this.leadAmount = leadAmount;
    }

}

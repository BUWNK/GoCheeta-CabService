/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.activity;

import java.util.Date;

/**
 *
 * @author SameeraPJ
 */
public class Activity {

    private String activityid;
    private String description;
    private int leadid;
    private String spenttime;
    private String activitytime;
    private String nextactivitytime;
    private String activitytypedescription;
    private String createduser;
    private String activitytype;
    private String activitytypeid;
    private String nextactivitytypeid;
    private String title;
    private String start;
    private String nameinfull;
    private Date activitydatetime;

    /**
     * @return the activityid
     */
    public String getActivityid() {
        return activityid;
    }

    /**
     * @param activityid the activityid to set
     */
    public void setActivityid(String activityid) {
        this.activityid = activityid;
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
     * @return the spenttime
     */
    public String getSpenttime() {
        return spenttime;
    }

    /**
     * @param spenttime the spenttime to set
     */
    public void setSpenttime(String spenttime) {
        this.spenttime = spenttime;
    }

    /**
     * @return the activitytime
     */
    public String getActivitytime() {
        return activitytime;
    }

    /**
     * @param activitytime the activitytime to set
     */
    public void setActivitytime(String activitytime) {
        this.activitytime = activitytime;
    }

    /**
     * @return the nextactivitytime
     */
    public String getNextactivitytime() {
        return nextactivitytime;
    }

    /**
     * @param nextactivitytime the nextactivitytime to set
     */
    public void setNextactivitytime(String nextactivitytime) {
        this.nextactivitytime = nextactivitytime;
    }

    /**
     * @return the activitytypedescription
     */
    public String getActivitytypedescription() {
        return activitytypedescription;
    }

    /**
     * @param activitytypedescription the activitytypedescription to set
     */
    public void setActivitytypedescription(String activitytypedescription) {
        this.activitytypedescription = activitytypedescription;
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
     * @return the activitytype
     */
    public String getActivitytype() {
        return activitytype;
    }

    /**
     * @param activitytype the activitytype to set
     */
    public void setActivitytype(String activitytype) {
        this.activitytype = activitytype;
    }

    /**
     * @return the activitytypeid
     */
    public String getActivitytypeid() {
        return activitytypeid;
    }

    /**
     * @param activitytypeid the activitytypeid to set
     */
    public void setActivitytypeid(String activitytypeid) {
        this.activitytypeid = activitytypeid;
    }

    /**
     * @return the nextactivitytypeid
     */
    public String getNextactivitytypeid() {
        return nextactivitytypeid;
    }

    /**
     * @param nextactivitytypeid the nextactivitytypeid to set
     */
    public void setNextactivitytypeid(String nextactivitytypeid) {
        this.nextactivitytypeid = nextactivitytypeid;
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
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * @return the nameinfull
     */
    public String getNameinfull() {
        return nameinfull;
    }

    /**
     * @param nameinfull the nameinfull to set
     */
    public void setNameinfull(String nameinfull) {
        this.nameinfull = nameinfull;
    }

    /**
     * @return the activitydatetime
     */
    public Date getActivitydatetime() {
        return activitydatetime;
    }

    /**
     * @param activitydatetime the activitydatetime to set
     */
    public void setActivitydatetime(Date activitydatetime) {
        this.activitydatetime = activitydatetime;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.remindernotification;

import java.util.Date;

/**
 *
 * @author ISURU
 */
public class ReminderNotification {

    private String id;
    private String description;
    private String reminderUser;
    private String remindTime;
    private Date reminderDateTime;
    private String remindStatus;
    private String viewStatus;
    private String sourcetabelId;
    private String section;
    private String creatdatetime;
    private String lastupdatetime;
    private String createuser;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the reminderUser
     */
    public String getReminderUser() {
        return reminderUser;
    }

    /**
     * @param reminderUser the reminderUser to set
     */
    public void setReminderUser(String reminderUser) {
        this.reminderUser = reminderUser;
    }

    /**
     * @return the remindTime
     */
    public String getRemindTime() {
        return remindTime;
    }

    /**
     * @param remindTime the remindTime to set
     */
    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    /**
     * @return the reminderDateTime
     */
    public Date getReminderDateTime() {
        return reminderDateTime;
    }

    /**
     * @param reminderDateTime the reminderDateTime to set
     */
    public void setReminderDateTime(Date reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    /**
     * @return the remindStatus
     */
    public String getRemindStatus() {
        return remindStatus;
    }

    /**
     * @param remindStatus the remindStatus to set
     */
    public void setRemindStatus(String remindStatus) {
        this.remindStatus = remindStatus;
    }

    /**
     * @return the viewStatus
     */
    public String getViewStatus() {
        return viewStatus;
    }

    /**
     * @param viewStatus the viewStatus to set
     */
    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }

    /**
     * @return the sourcetabelId
     */
    public String getSourcetabelId() {
        return sourcetabelId;
    }

    /**
     * @param sourcetabelId the sourcetabelId to set
     */
    public void setSourcetabelId(String sourcetabelId) {
        this.sourcetabelId = sourcetabelId;
    }

    /**
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * @return the creatdatetime
     */
    public String getCreatdatetime() {
        return creatdatetime;
    }

    /**
     * @param creatdatetime the creatdatetime to set
     */
    public void setCreatdatetime(String creatdatetime) {
        this.creatdatetime = creatdatetime;
    }

    /**
     * @return the lastupdatetime
     */
    public String getLastupdatetime() {
        return lastupdatetime;
    }

    /**
     * @param lastupdatetime the lastupdatetime to set
     */
    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    /**
     * @return the createuser
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * @param createuser the createuser to set
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

}

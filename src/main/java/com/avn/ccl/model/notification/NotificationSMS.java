/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.notification;

import java.sql.Timestamp;

/**
 * @Author : Roshen Dilshan
 * @Document : NotificationSMS
 * @Created on : Jan 21, 2016, 3:44:13 PM
 */
public class NotificationSMS {

    private String recipient;
    private int templateid;
    private String messagebody;
    private long componentid;
    private String component;
    private String cclsmsrout;
    private String cclgroupid;
    private int cclsmspriority;
    private Timestamp sendtime;
    private String createdUser;

    /**
     * @return the recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * @param recipient the recipient to set
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * @return the templateid
     */
    public int getTemplateid() {
        return templateid;
    }

    /**
     * @param templateid the templateid to set
     */
    public void setTemplateid(int templateid) {
        this.templateid = templateid;
    }

    /**
     * @return the messagebody
     */
    public String getMessagebody() {
        return messagebody;
    }

    /**
     * @param messagebody the messagebody to set
     */
    public void setMessagebody(String messagebody) {
        this.messagebody = messagebody;
    }

    /**
     * @return the componentid
     */
    public long getComponentid() {
        return componentid;
    }

    /**
     * @param componentid the componentid to set
     */
    public void setComponentid(long componentid) {
        this.componentid = componentid;
    }

    /**
     * @return the component
     */
    public String getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * @return the cclsmsrout
     */
    public String getCclsmsrout() {
        return cclsmsrout;
    }

    /**
     * @param cclsmsrout the cclsmsrout to set
     */
    public void setCclsmsrout(String cclsmsrout) {
        this.cclsmsrout = cclsmsrout;
    }

    /**
     * @return the cclgroupid
     */
    public String getCclgroupid() {
        return cclgroupid;
    }

    /**
     * @param cclgroupid the cclgroupid to set
     */
    public void setCclgroupid(String cclgroupid) {
        this.cclgroupid = cclgroupid;
    }

    /**
     * @return the cclsmspriority
     */
    public int getCclsmspriority() {
        return cclsmspriority;
    }

    /**
     * @param cclsmspriority the cclsmspriority to set
     */
    public void setCclsmspriority(int cclsmspriority) {
        this.cclsmspriority = cclsmspriority;
    }

    /**
     * @return the sendtime
     */
    public Timestamp getSendtime() {
        return sendtime;
    }

    /**
     * @param sendtime the sendtime to set
     */
    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * @return the createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * @param createdUser the createdUser to set
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

}

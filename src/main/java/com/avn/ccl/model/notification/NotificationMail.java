/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.notification;

import java.util.List;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;

/**
 * @Author : Roshen Dilshan
 * @Document : Notification
 * @Created on : Sep 21, 2015, 2:28:45 PM
 */
public class NotificationMail {

    private SimpleMailMessage simpleMailMessage;
    private List<FileSystemResource> resources;
    private int notificationTemplateID;
    private String createdUser;
    private String caseid;
    private String component;

    /**
     * @return the simpleMailMessage
     */
    public SimpleMailMessage getSimpleMailMessage() {
        return simpleMailMessage;
    }

    /**
     * @param simpleMailMessage the simpleMailMessage to set
     */
    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    /**
     * @return the resources
     */
    public List<FileSystemResource> getResources() {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public void setResources(List<FileSystemResource> resources) {
        this.resources = resources;
    }

    /**
     * @return the notificationTemplateID
     */
    public int getNotificationTemplateID() {
        return notificationTemplateID;
    }

    /**
     * @param notificationTemplateID the notificationTemplateID to set
     */
    public void setNotificationTemplateID(int notificationTemplateID) {
        this.notificationTemplateID = notificationTemplateID;
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

    /**
     * @return the caseid
     */
    public String getCaseid() {
        return caseid;
    }

    /**
     * @param caseid the caseid to set
     */
    public void setCaseid(String caseid) {
        this.caseid = caseid;
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.notificationtemplate;

/**
 *
 * @author Lahiru
 */
public class NotificationTemplate {
    
    private String notificationTempId;
    private String templateType;
    private String subject;
    private String reciepent;
    private String mailContent;
    private String concludingName;
    private String conclution;
    private String createdUser;

    /**
     * @return the notificationTempId
     */
    public String getNotificationTempId() {
        return notificationTempId;
    }

    /**
     * @param notificationTempId the notificationTempId to set
     */
    public void setNotificationTempId(String notificationTempId) {
        this.notificationTempId = notificationTempId;
    }

    /**
     * @return the templateType
     */
    public String getTemplateType() {
        return templateType;
    }

    /**
     * @param templateType the templateType to set
     */
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the reciepent
     */
    public String getReciepent() {
        return reciepent;
    }

    /**
     * @param reciepent the reciepent to set
     */
    public void setReciepent(String reciepent) {
        this.reciepent = reciepent;
    }

    /**
     * @return the mailContent
     */
    public String getMailContent() {
        return mailContent;
    }

    /**
     * @param mailContent the mailContent to set
     */
    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    /**
     * @return the concludingName
     */
    public String getConcludingName() {
        return concludingName;
    }

    /**
     * @param concludingName the concludingName to set
     */
    public void setConcludingName(String concludingName) {
        this.concludingName = concludingName;
    }

    /**
     * @return the conclution
     */
    public String getConclution() {
        return conclution;
    }

    /**
     * @param conclution the conclution to set
     */
    public void setConclution(String conclution) {
        this.conclution = conclution;
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

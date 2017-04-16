/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.audittrace;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Lahiru
 */
public class AuditTrace {
    
    private int auditTraceId;
    private String afectedId;
    private String responsibleUser;
    private String createdDateTime;
    private String afectedPage;
    private String description;
    private String task;   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date from_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date to_date;

    /**
     * @return the auditTraceId
     */
    public int getAuditTraceId() {
        return auditTraceId;
    }

    /**
     * @param auditTraceId the auditTraceId to set
     */
    public void setAuditTraceId(int auditTraceId) {
        this.auditTraceId = auditTraceId;
    }

    /**
     * @return the afectedId
     */
    public String getAfectedId() {
        return afectedId;
    }

    /**
     * @param afectedId the afectedId to set
     */
    public void setAfectedId(String afectedId) {
        this.afectedId = afectedId;
    }

    /**
     * @return the responsibleUser
     */
    public String getResponsibleUser() {
        return responsibleUser;
    }

    /**
     * @param responsibleUser the responsibleUser to set
     */
    public void setResponsibleUser(String responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    /**
     * @return the createdDateTime
     */
    public String getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * @param createdDateTime the createdDateTime to set
     */
    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * @return the from_date
     */
    public Date getFrom_date() {
        return from_date;
    }

    /**
     * @param from_date the from_date to set
     */
    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    /**
     * @return the to_date
     */
    public Date getTo_date() {
        return to_date;
    }

    /**
     * @param to_date the to_date to set
     */
    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    /**
     * @return the afectedPage
     */
    public String getAfectedPage() {
        return afectedPage;
    }

    /**
     * @param afectedPage the afectedPage to set
     */
    public void setAfectedPage(String afectedPage) {
        this.afectedPage = afectedPage;
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
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(String task) {
        this.task = task;
    }

    
 

}

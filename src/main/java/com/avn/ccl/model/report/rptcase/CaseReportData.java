/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.report.rptcase;

/**
 * @Author : Roshen Dilshan
 * @Document : CaseReportData
 * @Created on : Jul 29, 2015, 11:30:44 AM
 */

public class CaseReportData {
    
    private long case_id;
    private String case_type;
    private String case_date;
    private String priority;
    private String assignee_01;
    private String status;
    private String lastupdateddatetime;
    private String createddatetime;
    private String calllogid;
    private String createduser;

    /**
     * @return the case_id
     */
    public long getCase_id() {
        return case_id;
    }

    /**
     * @param case_id the case_id to set
     */
    public void setCase_id(long case_id) {
        this.case_id = case_id;
    }

    /**
     * @return the case_type
     */
    public String getCase_type() {
        return case_type;
    }

    /**
     * @param case_type the case_type to set
     */
    public void setCase_type(String case_type) {
        this.case_type = case_type;
    }

    /**
     * @return the case_date
     */
    public String getCase_date() {
        return case_date;
    }

    /**
     * @param case_date the case_date to set
     */
    public void setCase_date(String case_date) {
        this.case_date = case_date;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the assignee_01
     */
    public String getAssignee_01() {
        return assignee_01;
    }

    /**
     * @param assignee_01 the assignee_01 to set
     */
    public void setAssignee_01(String assignee_01) {
        this.assignee_01 = assignee_01;
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
     * @return the lastupdateddatetime
     */
    public String getLastupdateddatetime() {
        return lastupdateddatetime;
    }

    /**
     * @param lastupdateddatetime the lastupdateddatetime to set
     */
    public void setLastupdateddatetime(String lastupdateddatetime) {
        this.lastupdateddatetime = lastupdateddatetime;
    }

    /**
     * @return the createddatetime
     */
    public String getCreateddatetime() {
        return createddatetime;
    }

    /**
     * @param createddatetime the createddatetime to set
     */
    public void setCreateddatetime(String createddatetime) {
        this.createddatetime = createddatetime;
    }

    /**
     * @return the calllogid
     */
    public String getCalllogid() {
        return calllogid;
    }

    /**
     * @param calllogid the calllogid to set
     */
    public void setCalllogid(String calllogid) {
        this.calllogid = calllogid;
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

}

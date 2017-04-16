/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.report.rptcase;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : CaseReportParameters
 * @Created on : Jul 29, 2015, 11:25:44 AM
 */
public class CaseReportParameters {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date from_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date to_date;
    private String case_type;
    private String status;
    private String priority;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date case_date;
    private String department;
    private String product;
    private String subject;
    private String assignee;
    private String action;
    private String download_token_value_id;
    private String calllogid;
    
    /*checkbtnprivilage*/
    private boolean save_btn;
    private boolean edit_btn;
    private boolean search_btn;
    private boolean view_btn;
    private boolean download_btn;

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
     * @return the case_date
     */
    public Date getCase_date() {
        return case_date;
    }

    /**
     * @param case_date the case_date to set
     */
    public void setCase_date(Date case_date) {
        this.case_date = case_date;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.product = product;
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
     * @return the assignee
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * @param assignee the assignee to set
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the download_token_value_id
     */
    public String getDownload_token_value_id() {
        return download_token_value_id;
    }

    /**
     * @param download_token_value_id the download_token_value_id to set
     */
    public void setDownload_token_value_id(String download_token_value_id) {
        this.download_token_value_id = download_token_value_id;
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
     * @return the save_btn
     */
    public boolean isSave_btn() {
        return save_btn;
    }

    /**
     * @param save_btn the save_btn to set
     */
    public void setSave_btn(boolean save_btn) {
        this.save_btn = save_btn;
    }

    /**
     * @return the edit_btn
     */
    public boolean isEdit_btn() {
        return edit_btn;
    }

    /**
     * @param edit_btn the edit_btn to set
     */
    public void setEdit_btn(boolean edit_btn) {
        this.edit_btn = edit_btn;
    }

    /**
     * @return the search_btn
     */
    public boolean isSearch_btn() {
        return search_btn;
    }

    /**
     * @param search_btn the search_btn to set
     */
    public void setSearch_btn(boolean search_btn) {
        this.search_btn = search_btn;
    }

    /**
     * @return the view_btn
     */
    public boolean isView_btn() {
        return view_btn;
    }

    /**
     * @param view_btn the view_btn to set
     */
    public void setView_btn(boolean view_btn) {
        this.view_btn = view_btn;
    }

    /**
     * @return the download_btn
     */
    public boolean isDownload_btn() {
        return download_btn;
    }

    /**
     * @param download_btn the download_btn to set
     */
    public void setDownload_btn(boolean download_btn) {
        this.download_btn = download_btn;
    }

}

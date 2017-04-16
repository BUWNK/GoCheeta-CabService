/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.report.rptcall;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author : Roshen Dilshan
 * @Document : CaseReportParameters
 * @Created on : Jul 29, 2015, 11:25:44 AM
 */
public class CallReportParameters {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date from_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date to_date;
    private String preferred_language;
    private String call_direction;
    private String product;
    private String case_type;
    private String follow_up_action;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date callback_date;
    private String status;
    private String telephone;
    private String name;
    private String action;
    private String branch;
    private String download_token_value_id;
    private String user;

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
     * @return the preferred_language
     */
    public String getPreferred_language() {
        return preferred_language;
    }

    /**
     * @param preferred_language the preferred_language to set
     */
    public void setPreferred_language(String preferred_language) {
        this.preferred_language = preferred_language;
    }

    /**
     * @return the call_direction
     */
    public String getCall_direction() {
        return call_direction;
    }

    /**
     * @param call_direction the call_direction to set
     */
    public void setCall_direction(String call_direction) {
        this.call_direction = call_direction;
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
     * @return the follow_up_action
     */
    public String getFollow_up_action() {
        return follow_up_action;
    }

    /**
     * @param follow_up_action the follow_up_action to set
     */
    public void setFollow_up_action(String follow_up_action) {
        this.follow_up_action = follow_up_action;
    }

    /**
     * @return the callback_date
     */
    public Date getCallback_date() {
        return callback_date;
    }

    /**
     * @param callback_date the callback_date to set
     */
    public void setCallback_date(Date callback_date) {
        this.callback_date = callback_date;
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
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(String branch) {
        this.branch = branch;
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
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
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

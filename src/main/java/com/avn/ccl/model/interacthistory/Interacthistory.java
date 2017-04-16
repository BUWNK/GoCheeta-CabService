/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.interacthistory;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ISURU
 */
public class Interacthistory {

    private String id;
    private String module;
    private String discription;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date from_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date to_date;
    private String createdDateTime;
    private String lastUpdateDatetime;
    private String createduser;
    private String type;
    private String firstName;
    private String telephone;
    private String caseTypeDes;
    
    /**/
    private String title;
    private String gender;
    private String nationality;
    private String religion;
    private String marital_status;
    private String preferred_language;
    private String center;
    private String fd_type;
    private String savings_status;
    private String mf_cs;
    private String cr_cs;
    private String pw_cs;
    /**/

    private boolean save_btn;
    private boolean edit_btn;
    private boolean search_btn;
    private boolean view_btn;

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
     * @return the discription
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * @param discription the discription to set
     */
    public void setDiscription(String discription) {
        this.discription = discription;
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
     * @return the lastUpdateDatetime
     */
    public String getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    /**
     * @param lastUpdateDatetime the lastUpdateDatetime to set
     */
    public void setLastUpdateDatetime(String lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    /**
     * @return the module
     */
    public String getModule() {
        return module;
    }

    /**
     * @param module the module to set
     */
    public void setModule(String module) {
        this.module = module;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * @return the caseTypeDes
     */
    public String getCaseTypeDes() {
        return caseTypeDes;
    }

    /**
     * @param caseTypeDes the caseTypeDes to set
     */
    public void setCaseTypeDes(String caseTypeDes) {
        this.caseTypeDes = caseTypeDes;
    }

    /**
     * @return the center
     */
    public String getCenter() {
        return center;
    }

    /**
     * @param center the center to set
     */
    public void setCenter(String center) {
        this.center = center;
    }

    /**
     * @return the fd_type
     */
    public String getFd_type() {
        return fd_type;
    }

    /**
     * @param fd_type the fd_type to set
     */
    public void setFd_type(String fd_type) {
        this.fd_type = fd_type;
    }

    /**
     * @return the savings_status
     */
    public String getSavings_status() {
        return savings_status;
    }

    /**
     * @param savings_status the savings_status to set
     */
    public void setSavings_status(String savings_status) {
        this.savings_status = savings_status;
    }

    /**
     * @return the mf_cs
     */
    public String getMf_cs() {
        return mf_cs;
    }

    /**
     * @param mf_cs the mf_cs to set
     */
    public void setMf_cs(String mf_cs) {
        this.mf_cs = mf_cs;
    }

    /**
     * @return the cr_cs
     */
    public String getCr_cs() {
        return cr_cs;
    }

    /**
     * @param cr_cs the cr_cs to set
     */
    public void setCr_cs(String cr_cs) {
        this.cr_cs = cr_cs;
    }

    /**
     * @return the pw_cs
     */
    public String getPw_cs() {
        return pw_cs;
    }

    /**
     * @param pw_cs the pw_cs to set
     */
    public void setPw_cs(String pw_cs) {
        this.pw_cs = pw_cs;
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
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return the marital_status
     */
    public String getMarital_status() {
        return marital_status;
    }

    /**
     * @param marital_status the marital_status to set
     */
    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
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

}

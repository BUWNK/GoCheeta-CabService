/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.contact;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Rasika Madushanka
 */
public class Contact {

    private String title;
    private String nameInFull;
    private String jobtitle;
    private String mobile;
    private String employer;
    private String email;
    private int contactid;
    private String leadamount;
    private int leadid;
    private String contact_status;
    private int customer_account_id;
    private String productDescription;
    private String isDealer;
    private String forecastUntill;
    private String lead_closed_date;
    private String lead_lost_date;

    public String getForecastUntill() {
        return forecastUntill;
    }

    public void setForecastUntill(String forecastUntill) {
        this.forecastUntill = forecastUntill;
    }

    public String getIsDealer() {
        return isDealer;
    }

    public void setIsDealer(String isDealer) {
        this.isDealer = isDealer;
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
     * @return the jobtitle
     */
    public String getJobtitle() {
        return jobtitle;
    }

    /**
     * @param jobtitle the jobtitle to set
     */
    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the contactid
     */
    public int getContactid() {
        return contactid;
    }

    /**
     * @param contactid the contactid to set
     */
    public void setContactid(int contactid) {
        this.contactid = contactid;
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
     * @return the employer
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * @param employer the employer to set
     */
    public void setEmployer(String employer) {
        this.employer = employer;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contact_status
     */
    public String getContact_status() {
        return contact_status;
    }

    /**
     * @param contact_status the contact_status to set
     */
    public void setContact_status(String contact_status) {
        this.contact_status = contact_status;
    }

    /**
     * @return the customer_account_id
     */
    public int getCustomer_account_id() {
        return customer_account_id;
    }

    /**
     * @param customer_account_id the customer_account_id to set
     */
    public void setCustomer_account_id(int customer_account_id) {
        this.customer_account_id = customer_account_id;
    }

    /**
     * @return the nameInFull
     */
    public String getNameInFull() {
        return nameInFull;
    }

    /**
     * @param nameInFull the nameInFull to set
     */
    public void setNameInFull(String nameInFull) {
        this.nameInFull = nameInFull;
    }

    /**
     * @return the productDescription
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return the leadamount
     */
    public String getLeadamount() {
        return leadamount;
    }

    /**
     * @param leadamount the leadamount to set
     */
    public void setLeadamount(String leadamount) {
        this.leadamount = leadamount;
    }

    /**
     * @return the lead_closed_date
     */
    public String getLead_closed_date() {
        return lead_closed_date;
    }

    /**
     * @param lead_closed_date the lead_closed_date to set
     */
    public void setLead_closed_date(String lead_closed_date) {
        this.lead_closed_date = lead_closed_date;
    }

    /**
     * @return the lead_lost_date
     */
    public String getLead_lost_date() {
        return lead_lost_date;
    }

    /**
     * @param lead_lost_date the lead_lost_date to set
     */
    public void setLead_lost_date(String lead_lost_date) {
        this.lead_lost_date = lead_lost_date;
    }

    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.bean.webserviceclient;

import java.math.BigDecimal;
import org.json.JSONObject;

/**
 * @Author : Roshen Dilshan
 * @Document : StakeHolderSavingsBean
 * @Date : Jun 27, 2015 9:32:49 AM
 */
public class StakeHolderSavingsBean {

    private String savings_id_view;
    private String account_number;
    private String perform_location_code;
    private String servicing_location_code;
    private String servicing_officer;
    private String product_code;
    private String stake_holder_reference;
    private String account_type;
    private BigDecimal current_balance;
    private BigDecimal freeze_amount;
    private BigDecimal available_balance;
    private BigDecimal hold_amount;
    private String status;
    private String status_description;
    private String last_deposit_date;
    private String last_withdrawal_date;
    private String effective_start_date;
    private String effective_end_date;

    /**
     * @return the account_number
     */
    public String getAccount_number() {
        return account_number;
    }

    /**
     * @param account_number the account_number to set
     */
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    /**
     * @return the product_code
     */
    public String getProduct_code() {
        return product_code;
    }

    /**
     * @param product_code the product_code to set
     */
    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    /**
     * @return the stake_holder_reference
     */
    public String getStake_holder_reference() {
        return stake_holder_reference;
    }

    /**
     * @param stake_holder_reference the stake_holder_reference to set
     */
    public void setStake_holder_reference(String stake_holder_reference) {
        this.stake_holder_reference = stake_holder_reference;
    }

    /**
     * @return the current_balance
     */
    public BigDecimal getCurrent_balance() {
        return current_balance;
    }

    /**
     * @param current_balance the current_balance to set
     */
    public void setCurrent_balance(BigDecimal current_balance) {
        this.current_balance = current_balance;
    }

    /**
     * @return the freeze_amount
     */
    public BigDecimal getFreeze_amount() {
        return freeze_amount;
    }

    /**
     * @param freeze_amount the freeze_amount to set
     */
    public void setFreeze_amount(BigDecimal freeze_amount) {
        this.freeze_amount = freeze_amount;
    }

    /**
     * @return the available_balance
     */
    public BigDecimal getAvailable_balance() {
        return available_balance;
    }

    /**
     * @param available_balance the available_balance to set
     */
    public void setAvailable_balance(BigDecimal available_balance) {
        this.available_balance = available_balance;
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
     * @return the last_deposit_date
     */
    public String getLast_deposit_date() {
        return last_deposit_date;
    }

    /**
     * @param last_deposit_date the last_deposit_date to set
     */
    public void setLast_deposit_date(String last_deposit_date) {
        this.last_deposit_date = last_deposit_date;
    }

    /**
     * @return the last_withdrawal_date
     */
    public String getLast_withdrawal_date() {
        return last_withdrawal_date;
    }

    /**
     * @param last_withdrawal_date the last_withdrawal_date to set
     */
    public void setLast_withdrawal_date(String last_withdrawal_date) {
        this.last_withdrawal_date = last_withdrawal_date;
    }

    /**
     * @return the effective_start_date
     */
    public String getEffective_start_date() {
        return effective_start_date;
    }

    /**
     * @param effective_start_date the effective_start_date to set
     */
    public void setEffective_start_date(String effective_start_date) {
        this.effective_start_date = effective_start_date;
    }

    /**
     * @return the effective_end_date
     */
    public String getEffective_end_date() {
        return effective_end_date;
    }

    /**
     * @param effective_end_date the effective_end_date to set
     */
    public void setEffective_end_date(String effective_end_date) {
        this.effective_end_date = effective_end_date;
    }

    /**
     * @return the savings_id_view
     */
    public String getSavings_id_view() {
        return savings_id_view;
    }

    /**
     * @param savings_id_view the savings_id_view to set
     */
    public void setSavings_id_view(String savings_id_view) {
        this.savings_id_view = savings_id_view;
    }

    @Override
    public String toString() {
        String val = "{"
                + "savings_id_view:" + "\"" + savings_id_view + "\""
                + ", account_number:" + "\"" + account_number + "\""
                + ", product_code:" + "\"" + product_code + "\""
                + ", stake_holder_reference:" + "\"" + stake_holder_reference + "\""
                + ", current_balance:" + "\"" + current_balance + "\""
                + ", freeze_amount:" + "\"" + freeze_amount + "\""
                + ", available_balance:" + "\"" + available_balance + "\""
                + ", status:" + "\"" + status + "\""
                + ", last_deposit_date:" + "\"" + last_deposit_date + "\""
                + ", last_withdrawal_date:" + "\"" + last_withdrawal_date + "\""
                + ", effective_start_date:" + "\"" + effective_start_date + "\""
                + ", effective_end_date:" + "\"" + effective_end_date + "\""
                + ", perform_location_code: " + "\"" + perform_location_code + "\""
                + ", servicing_location_code: " + "\"" + servicing_location_code + "\""
                + ", servicing_officer: " + "\"" + servicing_officer + "\""
                + ", account_type: " + "\"" + account_type + "\""
                + ", hold_amount: " + "\"" + hold_amount + "\""
                + '}';
        val = new JSONObject(val).toString();
        return val;
    }

    /**
     * @return the status_description
     */
    public String getStatus_description() {
        return status_description;
    }

    /**
     * @param status_description the status_description to set
     */
    public void setStatus_description(String status_description) {
        this.status_description = status_description;
    }

    /**
     * @return the perform_location_code
     */
    public String getPerform_location_code() {
        return perform_location_code;
    }

    /**
     * @param perform_location_code the perform_location_code to set
     */
    public void setPerform_location_code(String perform_location_code) {
        this.perform_location_code = perform_location_code;
    }

    /**
     * @return the servicing_location_code
     */
    public String getServicing_location_code() {
        return servicing_location_code;
    }

    /**
     * @param servicing_location_code the servicing_location_code to set
     */
    public void setServicing_location_code(String servicing_location_code) {
        this.servicing_location_code = servicing_location_code;
    }

    /**
     * @return the servicing_officer
     */
    public String getServicing_officer() {
        return servicing_officer;
    }

    /**
     * @param servicing_officer the servicing_officer to set
     */
    public void setServicing_officer(String servicing_officer) {
        this.servicing_officer = servicing_officer;
    }

    /**
     * @return the account_type
     */
    public String getAccount_type() {
        return account_type;
    }

    /**
     * @param account_type the account_type to set
     */
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    /**
     * @return the hold_amount
     */
    public BigDecimal getHold_amount() {
        return hold_amount;
    }

    /**
     * @param hold_amount the hold_amount to set
     */
    public void setHold_amount(BigDecimal hold_amount) {
        this.hold_amount = hold_amount;
    }

}

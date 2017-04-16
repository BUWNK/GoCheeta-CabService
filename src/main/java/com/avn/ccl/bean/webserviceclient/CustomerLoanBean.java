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
 * @Document : CustomerLoanBean
 * @Date : Jun 27, 2015 9:32:16 AM
 */
public class CustomerLoanBean {
    
    private String loan_id_view;
    private String cs;
    private String module_code;
    private String customer_code;
    private String contract_no;
    private String alias_name;
    private String contract_date;
    private BigDecimal total_offered_amount;
    private String contract_status;
    private BigDecimal total_arrears_amount;
    private BigDecimal outstanding_amount;

    /**
     * @return the cs
     */
    public String getCs() {
        return cs;
    }

    /**
     * @param cs the cs to set
     */
    public void setCs(String cs) {
        this.cs = cs;
    }

    /**
     * @return the module_code
     */
    public String getModule_code() {
        return module_code;
    }

    /**
     * @param module_code the module_code to set
     */
    public void setModule_code(String module_code) {
        this.module_code = module_code;
    }

    /**
     * @return the customer_code
     */
    public String getCustomer_code() {
        return customer_code;
    }

    /**
     * @param customer_code the customer_code to set
     */
    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    /**
     * @return the contract_no
     */
    public String getContract_no() {
        return contract_no;
    }

    /**
     * @param contract_no the contract_no to set
     */
    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }

    /**
     * @return the alias_name
     */
    public String getAlias_name() {
        return alias_name;
    }

    /**
     * @param alias_name the alias_name to set
     */
    public void setAlias_name(String alias_name) {
        this.alias_name = alias_name;
    }

    /**
     * @return the contract_date
     */
    public String getContract_date() {
        return contract_date;
    }

    /**
     * @param contract_date the contract_date to set
     */
    public void setContract_date(String contract_date) {
        this.contract_date = contract_date;
    }

    /**
     * @return the total_offered_amount
     */
    public BigDecimal getTotal_offered_amount() {
        return total_offered_amount;
    }

    /**
     * @param total_offered_amount the total_offered_amount to set
     */
    public void setTotal_offered_amount(BigDecimal total_offered_amount) {
        this.total_offered_amount = total_offered_amount;
    }

    /**
     * @return the contract_status
     */
    public String getContract_status() {
        return contract_status;
    }

    /**
     * @param contract_status the contract_status to set
     */
    public void setContract_status(String contract_status) {
        this.contract_status = contract_status;
    }

    /**
     * @return the total_arrears_amount
     */
    public BigDecimal getTotal_arrears_amount() {
        return total_arrears_amount;
    }

    /**
     * @param total_arrears_amount the total_arrears_amount to set
     */
    public void setTotal_arrears_amount(BigDecimal total_arrears_amount) {
        this.total_arrears_amount = total_arrears_amount;
    }

    /**
     * @return the outstanding_amount
     */
    public BigDecimal getOutstanding_amount() {
        return outstanding_amount;
    }

    /**
     * @param outstanding_amount the outstanding_amount to set
     */
    public void setOutstanding_amount(BigDecimal outstanding_amount) {
        this.outstanding_amount = outstanding_amount;
    }

    /**
     * @return the loan_id_view
     */
    public String getLoan_id_view() {
        return loan_id_view;
    }

    /**
     * @param loan_id_view the loan_id_view to set
     */
    public void setLoan_id_view(String loan_id_view) {
        this.loan_id_view = loan_id_view;
    }

    @Override
    public String toString() {
        String val = "{" 
                + "loan_id_view:" + "\"" + loan_id_view + "\""
                + ", cs:" + "\"" + cs + "\""
                + ", module_code:" + "\"" + module_code + "\""
                + ", customer_code:" + "\"" + customer_code + "\""
                + ", contract_no:" + "\"" + contract_no + "\""
                + ", alias_name:" + "\"" + alias_name + "\""
                + ", contract_date:" + "\"" + contract_date + "\""
                + ", total_offered_amount:" + "\"" + total_offered_amount.toString() + "\""
                + ", contract_status:" + "\"" + contract_status + "\""
                + ", total_arrears_amount:" + "\"" + total_arrears_amount.toString() + "\""
                + ", outstanding_amount:" + "\"" + outstanding_amount .toString()+ "\""
                + '}';
        val = new JSONObject(val).toString();
        return val;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.customercodetype;

/**
 * @Author : Roshen Dilshan
 * @Document : CustomerCode
 * @Created on : Oct 15, 2015, 10:26:01 AM
 */
public class CustomerCodeType {

    private int codetypeid;
    private String description;

    /**
     * @return the codetypeid
     */
    public int getCodetypeid() {
        return codetypeid;
    }

    /**
     * @param codetypeid the codetypeid to set
     */
    public void setCodetypeid(int codetypeid) {
        this.codetypeid = codetypeid;
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

}

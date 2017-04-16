/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.addresstype;

import java.sql.Timestamp;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : AddressType
 * @Created on : Aug 6, 2015, 3:43:29 PM
 */
public class AddressType {

    private String addresstypeid;
    /*private String addresstypecode;*/
    private String description;
    private String sortid;
    private Timestamp createddatetime;
    private Timestamp lastupdateddatetime;

    /**
     * @return the addresstypeid
     */
    public String getAddresstypeid() {
        return addresstypeid;
    }

    /**
     * @param addresstypeid the addresstypeid to set
     */
    public void setAddresstypeid(String addresstypeid) {
        this.addresstypeid = addresstypeid;
    }

    /**
     * @return the addresstypecode
     */
//    public String getAddresstypecode() {
//        return addresstypecode;
//    }

    /**
     * @param addresstypecode the addresstypecode to set
     */
//    public void setAddresstypecode(String addresstypecode) {
//        this.addresstypecode = addresstypecode;
//    }

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
     * @return the sortid
     */
    public String getSortid() {
        return sortid;
    }

    /**
     * @param sortid the sortid to set
     */
    public void setSortid(String sortid) {
        this.sortid = sortid;
    }

    /**
     * @return the createddatetime
     */
    public Timestamp getCreateddatetime() {
        return createddatetime;
    }

    /**
     * @param createddatetime the createddatetime to set
     */
    public void setCreateddatetime(Timestamp createddatetime) {
        this.createddatetime = createddatetime;
    }

    /**
     * @return the lastupdateddatetime
     */
    public Timestamp getLastupdateddatetime() {
        return lastupdateddatetime;
    }

    /**
     * @param lastupdateddatetime the lastupdateddatetime to set
     */
    public void setLastupdateddatetime(Timestamp lastupdateddatetime) {
        this.lastupdateddatetime = lastupdateddatetime;
    }

}

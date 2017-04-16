/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.dependentrype;

import java.sql.Timestamp;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : DependentType
 * @Created on : Aug 7, 2015, 8:20:20 PM
 */
public class DependentType {

    private String id;
    private String description;
    private String sortid;
    private Timestamp createddatetime;
    private Timestamp lastupdateddatetime;

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

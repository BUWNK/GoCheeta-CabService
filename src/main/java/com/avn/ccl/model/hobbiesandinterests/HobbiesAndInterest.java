/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.hobbiesandinterests;

import java.sql.Timestamp;

/**
 * @Author : Roshen Dilshan
 * @Document : HobbiesAndInterest
 * @Created on : Jan 13, 2016, 2:32:50 PM
 */
public class HobbiesAndInterest {
    
    private int hobby_id;
    private String hobby_description;
    private int status;
    private int sortid;
    private Timestamp createddatetime;
    private Timestamp lastupdateddatetime;
    private String createduser;

    /**
     * @return the hobby_id
     */
    public int getHobby_id() {
        return hobby_id;
    }

    /**
     * @param hobby_id the hobby_id to set
     */
    public void setHobby_id(int hobby_id) {
        this.hobby_id = hobby_id;
    }

    /**
     * @return the hobby_description
     */
    public String getHobby_description() {
        return hobby_description;
    }

    /**
     * @param hobby_description the hobby_description to set
     */
    public void setHobby_description(String hobby_description) {
        this.hobby_description = hobby_description;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the sortid
     */
    public int getSortid() {
        return sortid;
    }

    /**
     * @param sortid the sortid to set
     */
    public void setSortid(int sortid) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.userrolesection;

/**
 *
 * @author Isuru
 */
public class UserRoleSection {

    private String multisection;
    private String userroleid;
    private String userroleDes;
    private String multisectionarray;
    private String sectionid;
    private String sectionDes;
    private String createddatetime;
    private String lastupdateddatetime;
    private String createduser;

    //------search------//
    private String searchoption;
    private String input;

    
    /*checkbtnprivilage*/
    private boolean save_btn;
    private boolean edit_btn;
    private boolean search_btn;
    private boolean view_btn;
    /**
     * @return the multisection
     */
    public String getMultisection() {
        return multisection;
    }

    /**
     * @param multisection the multisection to set
     */
    public void setMultisection(String multisection) {
        this.multisection = multisection;
    }

    /**
     * @return the userroleid
     */
    public String getUserroleid() {
        return userroleid;
    }

    /**
     * @param userroleid the userroleid to set
     */
    public void setUserroleid(String userroleid) {
        this.userroleid = userroleid;
    }

    /**
     * @return the multisectionarray
     */
    public String getMultisectionarray() {
        return multisectionarray;
    }

    /**
     * @param multisectionarray the multisectionarray to set
     */
    public void setMultisectionarray(String multisectionarray) {
        this.multisectionarray = multisectionarray;
    }

    /**
     * @return the sectionid
     */
    public String getSectionid() {
        return sectionid;
    }

    /**
     * @param sectionid the sectionid to set
     */
    public void setSectionid(String sectionid) {
        this.sectionid = sectionid;
    }

    /**
     * @return the sectionDes
     */
    public String getSectionDes() {
        return sectionDes;
    }

    /**
     * @param sectionDes the sectionDes to set
     */
    public void setSectionDes(String sectionDes) {
        this.sectionDes = sectionDes;
    }

    /**
     * @return the searchoption
     */
    public String getSearchoption() {
        return searchoption;
    }

    /**
     * @param searchoption the searchoption to set
     */
    public void setSearchoption(String searchoption) {
        this.searchoption = searchoption;
    }

    /**
     * @return the input
     */
    public String getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * @return the userroleDes
     */
    public String getUserroleDes() {
        return userroleDes;
    }

    /**
     * @param userroleDes the userroleDes to set
     */
    public void setUserroleDes(String userroleDes) {
        this.userroleDes = userroleDes;
    }

    /**
     * @return the createddatetime
     */
    public String getCreateddatetime() {
        return createddatetime;
    }

    /**
     * @param createddatetime the createddatetime to set
     */
    public void setCreateddatetime(String createddatetime) {
        this.createddatetime = createddatetime;
    }

    /**
     * @return the lastupdateddatetime
     */
    public String getLastupdateddatetime() {
        return lastupdateddatetime;
    }

    /**
     * @param lastupdateddatetime the lastupdateddatetime to set
     */
    public void setLastupdateddatetime(String lastupdateddatetime) {
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

}

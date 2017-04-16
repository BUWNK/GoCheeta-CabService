/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.wedget;

/**
 * @Author : ISURU
 * @Document : Wedget
 * @Created on : May 3, 2016, 2:09:32 PM
 */
public class Wedget {

    private String wedgetid;
    private String description;
    private String createddatetime;
    private String lastupdatedatetime;
    private String createduser;
    private String statusid;
    private String statusdes;
    private String searchoption;
    private String input;

    private boolean save_btn;
    private boolean edit_btn;
    private boolean search_btn;
    private boolean view_btn;

    /**
     * @return the wedgetid
     */
    public String getWedgetid() {
        return wedgetid;
    }

    /**
     * @param wedgetid the wedgetid to set
     */
    public void setWedgetid(String wedgetid) {
        this.wedgetid = wedgetid;
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
     * @return the lastupdatedatetime
     */
    public String getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    /**
     * @param lastupdatedatetime the lastupdatedatetime to set
     */
    public void setLastupdatedatetime(String lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
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
     * @return the statusid
     */
    public String getStatusid() {
        return statusid;
    }

    /**
     * @param statusid the statusid to set
     */
    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    /**
     * @return the statusdes
     */
    public String getStatusdes() {
        return statusdes;
    }

    /**
     * @param statusdes the statusdes to set
     */
    public void setStatusdes(String statusdes) {
        this.statusdes = statusdes;
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

}

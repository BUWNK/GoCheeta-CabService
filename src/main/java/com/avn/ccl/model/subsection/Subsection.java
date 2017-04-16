/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.subsection;

/**
 *
 * @author Isuru
 */
public class Subsection {

    private String subsectionId;
    private String subsectionDes;
    private String sectionid;
    private String sectionDes;
    private String statusid;
    private String statusDes;
    private String url;
    private String icon;
    private String sortid;
    private String clickable;
    private boolean clickableurl;
    //------search------//
    private String searchoption;
    private String input;
//*checkbtnprivilage*/
    private boolean save_btn;
    private boolean edit_btn;
    private boolean search_btn;
    private boolean view_btn;

    /**
     * /
     *
     **
     * @return the subsectionId
     */
    public String getSubsectionId() {
        return subsectionId;
    }

    /**
     * @param subsectionId the subsectionId to set
     */
    public void setSubsectionId(String subsectionId) {
        this.subsectionId = subsectionId;
    }

    /**
     * @return the subsectionDes
     */
    public String getSubsectionDes() {
        return subsectionDes;
    }

    /**
     * @param subsectionDes the subsectionDes to set
     */
    public void setSubsectionDes(String subsectionDes) {
        this.subsectionDes = subsectionDes;
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
     *
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
     * @return the statusDes
     */
    public String getStatusDes() {
        return statusDes;
    }

    /**
     * @param statusDes the statusDes to set
     */
    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
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
     * @return the clickable
     */
    public String getClickable() {
        return clickable;
    }

    /**
     * @param clickable the clickable to set
     */
    public void setClickable(String clickable) {
        this.clickable = clickable;
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
     * @return the clickableurl
     */
    public boolean isClickableurl() {
        return clickableurl;
    }

    /**
     * @param clickableurl the clickableurl to set
     */
    public void setClickableurl(boolean clickableurl) {
        this.clickableurl = clickableurl;
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

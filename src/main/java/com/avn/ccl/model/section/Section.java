/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.section;

import com.avn.ccl.model.subsection.Subsection;
import java.util.List;

/**
 *
 * @author Isuru
 */
public class Section {

    private String sectionid;
    private String sectionDes;
    private String statusid;
    private String statusDes;
    private String sectionlevel;
    private String sectionlevelDes;
    private String icon;
    private String parentsection;
    private String sortid;
    private String onlyparent;
    private String url;
    private String multisection;
    private String userroleid;
    private String multisectionarray;
    private boolean onlyparentsection;
    //------search------//
    private String searchoption;
    private String input;
    //-----------------//
    private List<Section> childsections;
    private List<Subsection> subsections;
//*checkbtnprivilage*/
     private boolean save_btn;
     private boolean edit_btn;
     private boolean search_btn;
     private boolean  view_btn;
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
     * @return the sectionlevel
     */
    public String getSectionlevel() {
        return sectionlevel;
    }

    /**
     * @param sectionlevel the sectionlevel to set
     */
    public void setSectionlevel(String sectionlevel) {
        this.sectionlevel = sectionlevel;
    }

    /**
     * @return the sectionlevelDes
     */
    public String getSectionlevelDes() {
        return sectionlevelDes;
    }

    /**
     * @param sectionlevelDes the sectionlevelDes to set
     */
    public void setSectionlevelDes(String sectionlevelDes) {
        this.sectionlevelDes = sectionlevelDes;
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
     * @return the parentsection
     */
    public String getParentsection() {
        return parentsection;
    }

    /**
     * @param parentsection the parentsection to set
     */
    public void setParentsection(String parentsection) {
        this.parentsection = parentsection;
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
     * @return the onlyparent
     */
    public String getOnlyparent() {
        return onlyparent;
    }

    /**
     * @param onlyparent the onlyparent to set
     */
    public void setOnlyparent(String onlyparent) {
        this.onlyparent = onlyparent;
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
     * @return the onlyparentsection
     */
    public boolean isOnlyparentsection() {
        return onlyparentsection;
    }

    /**
     * @param onlyparentsection the onlyparentsection to set
     */
    public void setOnlyparentsection(boolean onlyparentsection) {
        this.onlyparentsection = onlyparentsection;
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
     * @return the childsections
     */
    public List<Section> getChildsections() {
        return childsections;
    }

    /**
     * @param childsections the childsections to set
     */
    public void setChildsections(List<Section> childsections) {
        this.childsections = childsections;
    }

    /**
     * @return the subsections
     */
    public List<Subsection> getSubsections() {
        return subsections;
    }

    /**
     * @param subsections the subsections to set
     */
    public void setSubsections(List<Subsection> subsections) {
        this.subsections = subsections;
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

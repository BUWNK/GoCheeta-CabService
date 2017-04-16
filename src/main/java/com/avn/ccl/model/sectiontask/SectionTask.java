/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.sectiontask;

/**
 *
 * @author Isuru
 */
public class SectionTask {
    private String sectionid;
    private String sectionDes;
    private String taskid;
    private String taskDes;
    private String createddatetime;
    private String lastupdatedtime;
    private String createduser;
    private String multitask;
    private String multitaskarray;
    
     /*checkbtnprivilage*/
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
     * @return the taskid
     */
    public String getTaskid() {
        return taskid;
    }

    /**
     * @param taskid the taskid to set
     */
    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    /**
     * @return the taskDes
     */
    public String getTaskDes() {
        return taskDes;
    }

    /**
     * @param taskDes the taskDes to set
     */
    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
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
     * @return the lastupdatedtime
     */
    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * @param lastupdatedtime the lastupdatedtime to set
     */
    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
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
     * @return the multitaskarray
     */
    public String getMultitaskarray() {
        return multitaskarray;
    }

    /**
     * @param multitaskarray the multitaskarray to set
     */
    public void setMultitaskarray(String multitaskarray) {
        this.multitaskarray = multitaskarray;
    }

    /**
     * @return the multitask
     */
    public String getMultitask() {
        return multitask;
    }

    /**
     * @param multitask the multitask to set
     */
    public void setMultitask(String multitask) {
        this.multitask = multitask;
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

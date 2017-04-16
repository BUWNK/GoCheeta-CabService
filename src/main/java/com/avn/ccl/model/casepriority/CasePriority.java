/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.casepriority;

/**
 *
 * @author Hirantha
 */
public class CasePriority {
    private int priorityID;
    private String priorityDesc;
    private int sortID;

    public int getPriorityID() {
        return priorityID;
    }

    public void setPriorityID(int priorityID) {
        this.priorityID = priorityID;
    }

    public String getPriorityDesc() {
        return priorityDesc;
    }

    public void setPriorityDesc(String priorityDesc) {
        this.priorityDesc = priorityDesc;
    }

    public int getSortID() {
        return sortID;
    }

    public void setSortID(int sortID) {
        this.sortID = sortID;
    }
    
    
}

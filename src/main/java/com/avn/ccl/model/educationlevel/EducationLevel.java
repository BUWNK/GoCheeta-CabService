/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.educationlevel;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : EducationLevel
 * @Created on : Aug 4, 2015, 12:11:08 PM
 */

public class EducationLevel {
    
    private String educationlevelid;
    private String educationlevelcode;
    private String description;
    private String sortid;

    /**
     * @return the educationlevelid
     */
    public String getEducationlevelid() {
        return educationlevelid;
    }

    /**
     * @param educationlevelid the educationlevelid to set
     */
    public void setEducationlevelid(String educationlevelid) {
        this.educationlevelid = educationlevelid;
    }

    /**
     * @return the educationlevelcode
     */
    public String getEducationlevelcode() {
        return educationlevelcode;
    }

    /**
     * @param educationlevelcode the educationlevelcode to set
     */
    public void setEducationlevelcode(String educationlevelcode) {
        this.educationlevelcode = educationlevelcode;
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

}

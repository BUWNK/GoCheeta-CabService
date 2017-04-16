/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.education;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : Education
 * @Created on : Aug 4, 2015, 10:25:22 AM
 */

public class Education {

    private String education_id;
    private String account_id;
    private String education_level;
    private String education_level_description;
    private String institute;

    /**
     * @return the education_id
     */
    public String getEducation_id() {
        return education_id;
    }

    /**
     * @param education_id the education_id to set
     */
    public void setEducation_id(String education_id) {
        this.education_id = education_id;
    }

    /**
     * @return the account_id
     */
    public String getAccount_id() {
        return account_id;
    }

    /**
     * @param account_id the account_id to set
     */
    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    /**
     * @return the education_level
     */
    public String getEducation_level() {
        return education_level;
    }

    /**
     * @param education_level the education_level to set
     */
    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    /**
     * @return the education_level_description
     */
    public String getEducation_level_description() {
        return education_level_description;
    }

    /**
     * @param education_level_description the education_level_description to set
     */
    public void setEducation_level_description(String education_level_description) {
        this.education_level_description = education_level_description;
    }

    /**
     * @return the institute
     */
    public String getInstitute() {
        return institute;
    }

    /**
     * @param institute the institute to set
     */
    public void setInstitute(String institute) {
        this.institute = institute;
    }

}

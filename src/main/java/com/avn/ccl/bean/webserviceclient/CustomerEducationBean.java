/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.bean.webserviceclient;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : CustomerEducationBean
 * @Created on : Jul 14, 2015, 3:13:40 PM
 */

public class CustomerEducationBean {
    
    private String education_id_view;
    private String educationLevel;
    private String educationLevelDescription;
    private String institute;

    /**
     * @return the education_id_view
     */
    public String getEducation_id_view() {
        return education_id_view;
    }

    /**
     * @param education_id_view the education_id_view to set
     */
    public void setEducation_id_view(String education_id_view) {
        this.education_id_view = education_id_view;
    }

    /**
     * @return the educationLevel
     */
    public String getEducationLevel() {
        return educationLevel;
    }

    /**
     * @param educationLevel the educationLevel to set
     */
    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    /**
     * @return the educationLevelDescription
     */
    public String getEducationLevelDescription() {
        return educationLevelDescription;
    }

    /**
     * @param educationLevelDescription the educationLevelDescription to set
     */
    public void setEducationLevelDescription(String educationLevelDescription) {
        this.educationLevelDescription = educationLevelDescription;
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

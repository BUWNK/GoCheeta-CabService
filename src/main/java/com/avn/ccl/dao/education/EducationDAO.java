/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.education;

import com.avn.ccl.model.education.Education;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : EducationDAO
 * @Created on : Aug 4, 2015, 10:23:50 AM
 */
public interface EducationDAO {

    public long createEducation(Education education) throws Exception;

    public void deleteEducation(String educationid) throws Exception;
    
    public Education getEducationById(String educationid) throws Exception;

}

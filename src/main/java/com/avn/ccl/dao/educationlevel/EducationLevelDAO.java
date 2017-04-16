/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.educationlevel;

import com.avn.ccl.model.educationlevel.EducationLevel;
import java.util.Map;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : EducationLevelDAO
 * @Created on : Aug 4, 2015, 12:10:05 PM
 */
public interface EducationLevelDAO {

    public EducationLevel getEducationLevelById(String educationlevelid) throws Exception;
    
    public EducationLevel getEducationLevelByCode(String educationlevelcode) throws Exception;
    
    public Map<String, String> getEducationLevelDropdownList() throws Exception;

}

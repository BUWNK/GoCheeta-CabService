/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.opdesignation;

import com.avn.ccl.model.opdesignation.OPDesignation;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : DesignationDAO
 * @Date : Jul 23, 2015 12:51:19 PM
 */
public interface OPDesignationDAO {

    public Map<String, String> getDesignationDropdownList() throws Exception;

    public OPDesignation getDesignationById(String designationid) throws Exception;

    public OPDesignation getDesignationByCode(String designationcode) throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.casetype;

import com.avn.ccl.model.casetype.CaseType;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hirantha
 */
public interface CaseTypeDAO {
    public List<CaseType> getCaseTypeList();
    
    public Map<String, String> getCaseTypeDropdownList() throws Exception;
    
    public CaseType getCaseTypeById(String id) throws Exception;
    
}

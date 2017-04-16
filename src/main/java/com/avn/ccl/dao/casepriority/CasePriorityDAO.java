/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.casepriority;

import com.avn.ccl.model.casepriority.CasePriority;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hirantha
 */
public interface CasePriorityDAO {

    public List<CasePriority> getCasePriorityList();

    public Map<String, String> getCasePriorityDropdownList() throws Exception;

    public CasePriority getCasePriorityById(String id) throws Exception;
}

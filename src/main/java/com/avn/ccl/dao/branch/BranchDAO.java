/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.branch;

import com.avn.ccl.model.branch.Branch;
import java.util.Map;

/**
 *
 * @author Hirantha
 */
public interface BranchDAO {

    public Branch getBranch(int id) throws Exception ;

    public Map<String, String> getBranchIdDropdownList() throws Exception;

    public Map<String, String> getBranchIdMultiSelect() throws Exception;

    public Map<String, String> getBranchCodeDropdownList() throws Exception;

    public Map<String, String> getBranchOldDropdownList() throws Exception;
    
    public String getBranchCodeForUser(String username) throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.followupaction;

import com.avn.ccl.model.followupaction.FollowUpAction;
import java.util.Map;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : FollowUpActionDAO
 * @Created on : Aug 16, 2015, 10:43:13 AM
 */
public interface FollowUpActionDAO {
    
    public Map<String, String> getFollowUpActionDropdownList() throws Exception;
    
    public FollowUpAction getCallDirectionById(String id) throws Exception;

}

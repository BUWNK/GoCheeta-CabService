/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.activitytype;

import java.util.Map;

/**
 * @Author : ISURU
 * @Document : ActivityTypeDAO
 * @Created on : Jul 1, 2016, 10:16:04 AM
 */
public interface ActivityTypeDAO {
    
    public Map<String, String> getActivityTypeDropdownList() throws Exception;

}

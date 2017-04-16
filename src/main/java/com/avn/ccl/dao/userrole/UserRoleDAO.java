/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.userrole;

import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : UserRole
 * @Created on : Sep 17, 2015, 9:27:14 AM
 */
public interface UserRoleDAO {
    
    public Map<String, String> getUserRoleDropdownList() throws Exception;

}

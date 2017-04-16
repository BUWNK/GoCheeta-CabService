/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.fdtype;

import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : FdTypeDAO
 * @Created on : Jan 19, 2016, 5:39:25 PM
 */
public interface FdTypeDAO {
    
    public Map<String, String> getFdDropdownList() throws Exception;

}

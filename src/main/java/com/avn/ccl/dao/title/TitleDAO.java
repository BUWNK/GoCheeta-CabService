/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.title;

import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : TitleDAO
 * @Created on : Oct 15, 2015, 2:19:00 PM
 */
public interface TitleDAO {
    
    public Map<String, String> getTitleDropdownList() throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.religon;

import com.avn.ccl.model.religon.Religon;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : ReligonDAOImpl
 * @Date : Jul 4, 2015 12:17:21 PM
 */
public interface ReligonDAO {

    public Map<String, String> getReligonDropdownList() throws Exception;

    public Religon getReligonById(String id) throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.opprofession;

import com.avn.ccl.model.opprofession.OPProfession;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : OPProfessionDAO
 * @Date : Jul 23, 2015 2:45:12 PM
 */
public interface OPProfessionDAO {
    
    public Map<String, String> getProfessionDropdownList() throws Exception;
    
    public OPProfession getProfessionById(String professionid) throws Exception;
    
    public OPProfession getProfessionByCode(String professioncode) throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.opsector;

import com.avn.ccl.model.opsector.OPSector;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : OPSector
 * @Date : Jul 23, 2015 2:56:21 PM
 */
public interface OPSectorDAO {
    
    public Map<String, String> getSectorDropdownList() throws Exception;
    
    public OPSector getSectorById(String sectorid) throws Exception;

}

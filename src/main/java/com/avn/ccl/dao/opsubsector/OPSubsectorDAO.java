/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.opsubsector;

import com.avn.ccl.model.opsubsector.OPSubsector;
import java.util.List;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : OPSubsectorDAO
 * @Date : Jul 23, 2015 3:22:38 PM
 */
public interface OPSubsectorDAO {

    public List<OPSubsector> getSubsectorDropdownListBySector(String sectorid) throws Exception;
    
    public Map<String, String> getSubsectorDropdownListByIds(String[] subsectorids) throws Exception;

    public OPSubsector getSubsectorById(String subsectorid) throws Exception;

    public List<OPSubsector> getSubsectorByIds(String[] subsectorids) throws Exception;

}

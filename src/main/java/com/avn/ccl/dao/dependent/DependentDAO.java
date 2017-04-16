/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.dependent;

import com.avn.ccl.model.dependent.Dependent;
import java.util.List;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : DependentDAO
 * @Created on : Aug 6, 2015, 10:22:04 AM
 */
public interface DependentDAO {

    public long createDependent(Dependent dependent) throws Exception;
    
    public void deleteDependent(String dependentid) throws Exception;
    
    public List<Dependent> selectDelendentsByAccountId(String accountid) throws Exception;

}

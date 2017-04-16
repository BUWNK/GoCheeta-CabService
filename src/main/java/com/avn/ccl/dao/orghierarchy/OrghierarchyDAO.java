/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.orghierarchy;

import java.sql.SQLException;

/**
 * @Author : Roshen Dilshan
 * @Document : OrghierarchyDAO
 * @Created on : Jan 31, 2017, 9:06:43 AM
 */
public interface OrghierarchyDAO {

    public int getLevelByOrghierarchyId(int orghierarchyid) throws SQLException;

}

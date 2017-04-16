/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.service.hierarchytarget;

import com.avn.ccl.model.hierarchytarget.HierarchyTarget;
import java.sql.SQLException;
import java.util.List;
import org.json.JSONObject;

/**
 * @Author : Roshen Dilshan
 * @Document : HierarchyTargetService
 * @Created on : Feb 23, 2017, 9:40:10 AM
 */
public interface HierarchyTargetService {

    public int getTableDataCount() throws SQLException;

    public List<JSONObject> getTableData(int minCount, int start) throws SQLException;

    public void saveHierarchyTarget(HierarchyTarget hierarchyTarget, String username) throws SQLException;

}

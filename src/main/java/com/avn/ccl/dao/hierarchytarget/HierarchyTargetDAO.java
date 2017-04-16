/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.hierarchytarget;

import com.avn.ccl.model.hierarchytarget.HierarchyTarget;
import java.sql.SQLException;
import java.util.List;
import org.json.JSONObject;

/**
 * @Author : Roshen Dilshan
 * @Document : HierarchyTargetDAO
 * @Created on : Feb 23, 2017, 9:41:39 AM
 */
public interface HierarchyTargetDAO {

    public int getTableDataCount() throws SQLException;

    public List<JSONObject> getTableData(int minCount, int start) throws SQLException;

    public boolean isAlreadyExists(int hierarchyid) throws SQLException;

    public void insetHierarchyTarget(HierarchyTarget hierarchyTarget, String username) throws SQLException;

    public void updateHierarchyTarget(HierarchyTarget hierarchyTarget) throws SQLException;

}

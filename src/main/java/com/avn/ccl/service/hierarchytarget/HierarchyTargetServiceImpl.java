/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.service.hierarchytarget;

import com.avn.ccl.dao.hierarchytarget.HierarchyTargetDAO;
import com.avn.ccl.model.hierarchytarget.HierarchyTarget;
import java.sql.SQLException;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : Roshen Dilshan
 * @Document : HierarchyTargetServiceImpl
 * @Created on : Feb 23, 2017, 9:40:35 AM
 */
@Service("hierarchyTargetService")
public class HierarchyTargetServiceImpl implements HierarchyTargetService {

    @Autowired
    HierarchyTargetDAO hierarchyTargetDAO;

    @Override
    public int getTableDataCount() throws SQLException {
        return hierarchyTargetDAO.getTableDataCount();
    }

    @Override
    public List<JSONObject> getTableData(int minCount, int start) throws SQLException {
        return hierarchyTargetDAO.getTableData(minCount, start);
    }

    @Override
    public void saveHierarchyTarget(HierarchyTarget hierarchyTarget, String username) throws SQLException {
        if (hierarchyTargetDAO.isAlreadyExists(hierarchyTarget.getHierarchyid())) {
            hierarchyTargetDAO.updateHierarchyTarget(hierarchyTarget);
        } else {
            hierarchyTargetDAO.insetHierarchyTarget(hierarchyTarget, username);
        }
    }

}

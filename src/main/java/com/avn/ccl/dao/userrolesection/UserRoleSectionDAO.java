/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.userrolesection;

import com.avn.ccl.model.userrolesection.UserRoleSection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Isuru
 */
public interface UserRoleSectionDAO {

    public List<UserRoleSection> insertRoleSection(UserRoleSection userrolesection, String username) throws Exception;

    public int getTableDataCount(UserRoleSection parameters) throws Exception;

    public List<UserRoleSection> getTableData(UserRoleSection parameters, int minCount, int start) throws Exception;

    public UserRoleSection getUserRoleSection(String userroleid) throws Exception;

    public Map<String, String> getAssignedSections(String userrolesection) throws Exception;

    public Map<String, String> getNotAssignedSections(String userroleid) throws Exception;

    public void updateuserRoleSection(UserRoleSection userrolesection, String username) throws Exception;

}

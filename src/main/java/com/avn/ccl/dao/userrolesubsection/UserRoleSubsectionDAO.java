/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.userrolesubsection;

import com.avn.ccl.model.userrolesubsection.UserRoleSubSection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Isuru
 */
public interface UserRoleSubsectionDAO {

    public List<UserRoleSubSection> getSubSectionDropdownListBySectionID(String userrole, String sectionid) throws Exception;

    public List<UserRoleSubSection> insertUserRoleSubSection(UserRoleSubSection userrolesubsection, String username) throws Exception;

    public Map<String, String> getAssignedSubSections(String userroleid, String sectionid) throws Exception;

    public Map<String, String> getNotAssignedSubSections(String userroleid, String sectionid) throws Exception;

    public int getTableDataCount(UserRoleSubSection parameters) throws Exception;

    public List<UserRoleSubSection> getTableData(UserRoleSubSection parameters, int minCount, int start) throws Exception;

    public List<UserRoleSubSection> updateuserRoleSection(UserRoleSubSection userrolesubsection, String username) throws Exception;

}

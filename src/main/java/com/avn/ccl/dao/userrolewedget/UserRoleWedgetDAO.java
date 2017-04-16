/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.userrolewedget;

import com.avn.ccl.model.userrolewedget.UserRoleWedget;
import java.util.List;
import java.util.Map;

/**
 * @Author : ISURU
 * @Document : UserRoleWedgetDAO
 * @Created on : May 16, 2016, 11:40:47 AM
 */
public interface UserRoleWedgetDAO {

    public List<String> getInsertSortIdList(String userrole) throws Exception;

    public List<String> getInsertWedgetList(String userrole) throws Exception;

    public void insertUserRoleWedget(UserRoleWedget userrolewedget, String username) throws Exception;

    public int getTableDataCount(UserRoleWedget parameters) throws Exception;

    public List<UserRoleWedget> getTableData(UserRoleWedget parameters, int minCount, int start) throws Exception;

    public UserRoleWedget getWedgetByWedgetId(String wedgetid, String userroleid) throws Exception;

    public void updateWedget(UserRoleWedget wedget, String username) throws Exception;

    public void deleteWedget(String wedgetuid, String userroleid, String sortid) throws Exception;

    public Map<String, String> getWedgetDropdownList() throws Exception;

}

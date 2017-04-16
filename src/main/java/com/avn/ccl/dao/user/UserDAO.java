/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.user;

import com.avn.ccl.model.employee.Employee;
import com.avn.ccl.model.user.User;
import java.util.List;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : UserDAO
 * @Created on : Sep 15, 2015, 1:40:24 PM
 */
public interface UserDAO {

    public int getTableDataCount(User user) throws Exception;

    public List<User> getTableData(User user, int minCount, int start) throws Exception;

    public boolean isUserExists(User user) throws Exception;

    public void createUser(User user, Employee employee) throws Exception;
    
    public void createUser(User user) throws Exception;

    public void updateUser(User user) throws Exception;

    public User getUser(String username) throws Exception;

    public List<String> getUserAssignedEmployeeCategories(Long employeeid) throws Exception;

    public Map<String, String> getAssignedBranches(long employeeid, String employeecategory) throws Exception;

    public String getAssignedBranch(long employeeid, String employeecategory) throws Exception;

    public Map<String, String> getNotAssignedBranchList(long employeeid, String employeecategory) throws Exception;
    
    public boolean isUserCategoryExistsForBranch(long branchid, String employeecategory) throws Exception;
    
    public boolean isNotify(long employeeid, String employeecategory) throws Exception;
    
    public int getUserRoleByUsername(String username) throws Exception;
    
    public Map<String, String> getUserDropdownList() throws Exception;
    
    public void updateUserAttempte(String username) throws Exception;
    
    public int getUserAttempts(String username) throws Exception;
    
    public void reSetUserAttempts(String username) throws Exception;
    
}

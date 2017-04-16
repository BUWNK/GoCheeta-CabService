/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.employee;

import com.avn.ccl.model.employee.Employee;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @Author : Office Isuru
 * @Document : EmployeeDAO
 * @Date : Sep 23, 2015 11:18:52 AM
 */
public interface EmployeeDAO {

    public long createEmployee(Employee employee) throws Exception;

    public String getEmployeeFirstNameById(String branchid, String category) throws Exception;

    public String getEmployeeIdByBranchId(String branchid, String category) throws Exception;

    public String getEmployeeInitialsLastnameByid(long employeeid) throws Exception;

    public Map<String, String> getEmployeeDropdownList() throws Exception;

    public boolean isEmployeeExists(String epf) throws Exception;

    public long getEmployeeIdByEPF(String epf) throws Exception;

    public String getEmployeeEmailByUsername(String username) throws Exception;

    public String getEmployeeEmailByEmployeeId(long employeeid) throws Exception;

    public String getEmployeeEmailByEPF(String epf) throws Exception;

    public void updateEmployeInLogin(Employee employee, String username) throws Exception;

    public Employee getEmployeeDetailsByEmployeeId(long employeeid) throws Exception;

    public String getEmployeeNameInFullByEmployeeId(long employeeid) throws SQLException;

    public long getEmployeeIdByUsername(String username) throws Exception;

    public String getEmployeeOldBranchCodeByUsername(String username) throws SQLException;
    
    public int getUserHierarchyByUsername(String username) throws SQLException;
    
    public int getRMUserRegionIdByUsername(String username) throws SQLException;
    
    public Integer[] getEmployeeTerritorys(String username) throws SQLException;
    
    public Integer[] getUserRegionBranches(String username) throws SQLException;

}

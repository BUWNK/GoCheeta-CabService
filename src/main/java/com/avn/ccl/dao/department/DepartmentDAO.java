/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.department;

import com.avn.ccl.model.department.Department;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hirantha
 */
public interface DepartmentDAO {

    public List<Department> getDepartmentList() throws Exception;

    public Map<String, String> getDepartmentDropdownList() throws Exception;

    public Department getDepartmentById(String id) throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.sectiontask;

import com.avn.ccl.model.sectiontask.SectionTask;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Isuru
 */
public interface SectionTaskDAO {

    public List<SectionTask> insertSectionTask(SectionTask sectiontask, String username) throws Exception;

    public int getTableDataCount(SectionTask parameters) throws Exception;

    public List<SectionTask> getTableData(SectionTask parameters, int minCount, int start) throws Exception;

    public Map<String, String> getAssignedTask(String sectionid) throws Exception;

    public Map<String, String> getNotAssignedTask(String sectionid) throws Exception;

    public List<SectionTask> updatSectionTask(SectionTask sectiontask, String username) throws Exception;

}

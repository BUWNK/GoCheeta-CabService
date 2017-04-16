/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.section;

import com.avn.ccl.model.section.Section;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Isuru
 */
public interface SectionDAO {

    public long insertSection(Section section, String username) throws Exception;

    public List<Section> getParntSection(String sectionlevel) throws Exception;

    public Section getSectionBySectionId(String sectionId) throws Exception;

    public int getTableDataCount(Section parameters) throws Exception;

    public List<Section> getTableData(Section parameters, int minCount, int start) throws Exception;

    public void updateSection(Section section, String username) throws Exception;

    public Map<String, String> getSectionDropdownList() throws Exception;

    public Map<String, String> getMultiSectionDropdownList() throws Exception;

    public List<Section> getSectionDropdownListByRoleID(String roleid) throws Exception;
    
    public List<Section> getSectionByUserrole(int userrole) throws Exception;
    
    public List<Section> getSectionLevelZeroByUserrole(int userrole) throws Exception;
    
    public List<Section> getSectionLowLevelByUserrole(int userrole) throws Exception;

}

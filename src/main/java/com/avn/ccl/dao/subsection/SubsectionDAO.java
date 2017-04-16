/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.subsection;

import com.avn.ccl.model.subsection.Subsection;
import java.util.List;

/**
 *
 * @author Isuru
 */
public interface SubsectionDAO {

    public long insertSubSection(Subsection subsection, String username) throws Exception;

    public int getTableDataCount(Subsection parameters) throws Exception;

    public List<Subsection> getTableData(Subsection parameters, int minCount, int start) throws Exception;

    public Subsection getSubectionBySubSectionId(String subsectionId) throws Exception;

    public void updateSubSection(Subsection subsection, String username) throws Exception;

    public List<Subsection> getSubSectionsByUserroleAndSectionID(int userrole, String sectionid) throws Exception;

}

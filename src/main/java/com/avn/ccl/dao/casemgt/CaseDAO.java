/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.casemgt;

import com.avn.ccl.model.casemgt.Case;
import com.avn.ccl.model.casemgt.CaseHistory;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author maheshl
 */
public interface CaseDAO {

    public Case getCaseByCaseId(String caseId) throws Exception;

    public long insertCase(Case userCase, String user) throws Exception;

    public void insertToCaseHistory(CaseHistory caseHistory, String username) throws Exception;

    public int getTableDataCount(String user, long employeeId) throws Exception;

    public List<Case> getTableData(String username, long employeeid, int minCount, int start, String sort) throws Exception;

    public List<CaseHistory> getCaseHistory(String caseId, String username) throws Exception;

    public long getCaseAssignedEmployeeId(long caseid) throws Exception;

    public List<Case> getCaseDetail(String Uid) throws Exception;

    public String getCaseID(HttpSession session) throws Exception;

    public Case getUserRoleIdAndEmployeeId(String username) throws Exception;

    public int getTableDataCount(Case parameters) throws Exception;

    public List<Case> getTableData(Case parameters, int minCount, int start) throws Exception;

}

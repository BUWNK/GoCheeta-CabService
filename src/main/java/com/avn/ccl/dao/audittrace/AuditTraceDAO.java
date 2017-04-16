/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.audittrace;

import com.avn.ccl.model.audittrace.AuditTrace;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lahiru
 */
public interface AuditTraceDAO {

    public List<AuditTrace> getAuditTrace() throws Exception;

     public void insertAuditTrace(String page, String task, String description, String affectedId, String user) throws Exception;

    public List<AuditTrace> getTableData(AuditTrace parameters, int minCount, int start) throws Exception;

    public int getTableDataCount(AuditTrace parameters) throws Exception;
}

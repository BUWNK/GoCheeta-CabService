/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.report.rptcase;

import com.avn.ccl.model.report.rptcase.CaseReportData;
import com.avn.ccl.model.report.rptcase.CaseReportParameters;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : CaseReportDAO
 * @Created on : Jul 29, 2015, 11:29:29 AM
 */
public interface CaseReportDAO {

    public List<CaseReportData> getTableData(CaseReportParameters parameters, int minCount, int start) throws Exception;

    public int getTableDataCount(CaseReportParameters parameters) throws Exception;
    
    public Object generateExcelReport(CaseReportParameters parameters, ServletContext context, HashMap<String, Object> parameterMap, String reportBuildPath, String user, Date date) throws Exception;

}

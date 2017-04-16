/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.schedulertask;

import com.avn.ccl.model.schedulertask.CaseInfo;
import java.util.List;

/**
 *
 * @Author : Office Isuru
 * @Document : ShedultaskDAO
 * @Date : Sep 23, 2015 11:30:54 AM
 */
public interface ScheduleTaskDAO {

    public String getOrganizationlPrameters91() throws Exception;
    
    public String getOrganizationlPrameters101() throws Exception;
    
    public String getOrganizationlPrameters201() throws Exception;

    public List<String> getOpneCase(String Orgparameter) throws Exception;

    public List<String> getBMZMEmail(String caseid) throws Exception;
    
    public List<String> getRMEmail(String caseid) throws Exception;
    
    public List<String> getCCPersonmails(String caseid) throws Exception;
    
    public List<String> getInternamAuditMails(String caseid) throws Exception;
    
    public List<CaseInfo> getCaseByLevelId(int level) throws Exception;
    
    public List<List> caseCreattimeExceedtwoday() throws Exception;
    
    public List<List> caseCreattimeExceedtwodayMore() throws Exception;
    
    public boolean setNotificationlevel(String notificationlevel, String caseid) throws Exception;
    
    public List<String> getCreatedUserEmail(String caseid) throws Exception;
    
    public List<String> getCustomerDetails(String caseid) throws Exception;

}

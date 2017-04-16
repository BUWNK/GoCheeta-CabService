/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.lead;

import com.avn.ccl.model.lead.BranchPerformance;
import com.avn.ccl.model.lead.LeadChartQueryParameters;
import com.avn.ccl.model.lead.RegionPerformance;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @Author : Roshen Dilshan
 * @Document : LeadDAO
 * @Created on : May 12, 2016, 6:44:13 AM
 */
public interface LeadDAO {

    public BigDecimal getAchievedValue(LeadChartQueryParameters chartQueryParameters) throws SQLException;

    public BigDecimal getAchievedValueForUser(LeadChartQueryParameters chartQueryParameters) throws SQLException;

    public BigDecimal getForcastValue(LeadChartQueryParameters chartQueryParameters) throws SQLException;

    public long getLeadCountForStatusUserCreatedDateRange(String username, int status, Date[] range) throws SQLException;

    public long getLeadCountForStatusUserUpdatedDateRange(String username, int status, Date[] range) throws SQLException;

    public long getLeadContForUserCreatedDateRange(String username, Date[] range) throws SQLException;

    public long getLeadCountForUserStatusSalesColseDate(String username, int status, Date[] range) throws SQLException;

    public long getLeadCountForUserStatusSalesLostDate(String username, int status, Date[] range) throws SQLException;

    public long getLeadCountForUserStatusForecastDate(String username, int status, Date[] range) throws SQLException;

    public List<BranchPerformance> getBranchPerformanceByTargetID(long targetid) throws SQLException;

    public List<RegionPerformance> getRegionPerformanceByTargetID(long targetid) throws SQLException;

    public int getContactLeadCount(long contactid, long productid) throws SQLException;

    public long getFirstLeadIdForProductAndContact(long contactid, long productid) throws SQLException;

    public long getLeadCreatedEmployeeId(long leadid) throws SQLException;
    
    public int getRegionalPerformanceCount(int region) throws SQLException;
    
    public JSONArray getRegionalPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int region) throws SQLException;
    
    public int getBranchPerformanceCount(int region, int productcategory, Integer[] territories) throws SQLException;
    
    public JSONArray getBranchPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int region, int productcategory, Integer[] territories) throws SQLException;
    
    public int getIndividualPerformanceCount(int branch, int productcategory, Integer[] territories, String username) throws SQLException;
    
    public JSONArray getIndividualPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int branch, int productcategory, Integer[] territories, String username) throws SQLException;
    
    public JSONArray getForecastForDateRange(Date fromDate, Date toDate) throws SQLException;

}

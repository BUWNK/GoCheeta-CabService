/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.service.dashboard;

import com.avn.ccl.model.lead.BranchPerformance;
import com.avn.ccl.model.lead.RegionPerformance;
import com.avn.ccl.model.performancedashboard.Performancedashboard;
import com.avn.ccl.model.target.Target;
import com.avn.ccl.model.targetperiod.TargetPeriod;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @Author : Roshen Dilshan
 * @Document : DashboardService
 * @Created on : May 5, 2016, 9:57:23 AM
 */
public interface DashboardService {

    public JSONArray getAgentTargetGraph(long targetId) throws Exception;

    public JSONObject getBarTargetGarph(long targetId) throws Exception;

    public JSONObject getUserContactLeadAccountGraph(String username) throws Exception;

    public JSONObject getUserSuccessFailLeadRatioGraph(String username) throws Exception;

    public JSONObject getTargetRatioOrganizationGraph(long targetId) throws Exception;

    public JSONObject getTargetRatioUserGraph(long targetId, String username) throws Exception;

    public List<Target> getTargetDropDownList(String username) throws Exception;

    public List<TargetPeriod> getTargetPeriodDropDownList() throws Exception;

    public Map<String, String> getDropdownValueList(String username, Performancedashboard performancedashboard) throws Exception;

//    public JSONObject getActivityPerformanceByTargetId(long targetId) throws Exception;
    public JSONObject getActivitiesPerformanceByTargetPeriod(long targetId) throws Exception;

    public List<BranchPerformance> getBranchPerformanceByTargetID(long targetid) throws SQLException;

    public List<RegionPerformance> getRegionPerformanceByTargetID(long targetid) throws SQLException;

    public int getRegionalPerformanceCount(int region) throws SQLException;

    public JSONArray getRegionalPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int region) throws SQLException;

    public int getBranchPerformanceCount(int region, int productcategory, Integer[] territories) throws SQLException;

    public JSONArray getBranchPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int region, int productcategory, Integer[] territories) throws SQLException;

    public int getIndividualPerformanceCount(int branch, int productcategory, Integer[] territories, String username) throws SQLException;

    public JSONArray getIndividualPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int branch, int productcategory, Integer[] territories, String username) throws SQLException;

    public JSONObject getNext6MonthsForecast() throws SQLException;

}

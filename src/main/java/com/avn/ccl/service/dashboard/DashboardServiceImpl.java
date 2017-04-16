/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.service.dashboard;

import com.avn.ccl.dao.activity.ActivityDAO;
import com.avn.ccl.dao.activitytarget.ActivityTargetDAO;
import com.avn.ccl.dao.common.CommonDAO;
import com.avn.ccl.dao.contact.ContactDAO;
import com.avn.ccl.dao.dashboard.DashboardDAO;
import com.avn.ccl.dao.lead.LeadDAO;
import com.avn.ccl.dao.target.TargetDAO;
import com.avn.ccl.dao.targetperiod.TargetPeriodDAO;
import com.avn.ccl.model.activitytarget.ActivityTarget;
import com.avn.ccl.model.lead.BranchPerformance;
import com.avn.ccl.model.lead.LeadChartQueryParameters;
import com.avn.ccl.model.lead.RegionPerformance;
import com.avn.ccl.model.performancedashboard.Performancedashboard;
import com.avn.ccl.model.target.Target;
import com.avn.ccl.model.targetperiod.TargetPeriod;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : Roshen Dilshan
 * @Document : DashboardServiceImpl
 * @Created on : May 5, 2016, 9:58:50 AM
 */
@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    DashboardDAO dashboardDAO;
    @Autowired
    TargetPeriodDAO targetPeriodDAO;
    @Autowired
    TargetDAO targetDAO;
    @Autowired
    LeadDAO leadDAO;
    @Autowired
    CommonDAO commonDAO;
    @Autowired
    ContactDAO contactDAO;
    @Autowired
    ActivityDAO activityDAO;
    @Autowired
    ActivityTargetDAO activityTargetDAO;

    @Override
    @Transactional
    public JSONArray getAgentTargetGraph(long targetId) throws Exception {
        JSONArray graph = null;
        try {
            Target target = targetDAO.getTargetByID(targetId);

            graph = new JSONArray();
            JSONObject graphlines = new JSONObject();
            JSONArray graphpoint = new JSONArray();

            List<Date[]> periodList = this.getDatePeriods(target);
            if (periodList != null && periodList.size() > 0) {
                graphpoint.put(Common.getStartingTimeofDay(periodList.get(0)[0]).getTime());
                graphpoint.put(BigDecimal.ZERO.setScale(2, RoundingMode.CEILING));
                graphlines.put("label", "Organization Target");
                graphlines.append("data", graphpoint);

                graphpoint = new JSONArray();
                graphpoint.put(Common.getStartingTimeofDay(target.getTargetenddate()).getTime());
                graphpoint.put(target.getRevenue().setScale(2, RoundingMode.CEILING));
                graphlines.append("data", graphpoint);
                graph.put(graphlines);

                BigDecimal totalAmount = BigDecimal.ZERO;

                graphlines = new JSONObject();
                graphpoint = new JSONArray();

                graphpoint.put(Common.getStartingTimeofDay(periodList.get(0)[0]).getTime());
                graphpoint.put(BigDecimal.ZERO.setScale(2, RoundingMode.CEILING));
                graphlines.put("label", "Organization Achievement");
                graphlines.append("data", graphpoint);
                for (Date[] point : periodList) {
                    LeadChartQueryParameters queryParameters = new LeadChartQueryParameters();
                    queryParameters.setFromdate(point[0]);
                    queryParameters.setTodate(point[1]);
                    queryParameters.setStatusid(MasterDataVarList.CCL_CODE_STATUS_LEAD_CLOSED);
                    queryParameters.setProductid(target.getProductid());

                    graphpoint = new JSONArray();
                    graphpoint.put(point[1].getTime());
                    BigDecimal amount = leadDAO.getAchievedValue(queryParameters);
                    if (amount == null) {
                        amount = BigDecimal.ZERO;
                    }
                    totalAmount = totalAmount.add(amount);
                    graphpoint.put(totalAmount.setScale(2, RoundingMode.CEILING));
                    graphlines.append("data", graphpoint);
                }
            }
            graph.put(graphlines);
        } catch (SQLException sqle) {
            throw sqle;
        }
        return graph;
    }

    @Override
    @Transactional
    public JSONObject getBarTargetGarph(long targetId) throws Exception {
        JSONObject graph = null;
        try {
            JSONArray targetAmounts = new JSONArray();
            JSONArray forecastAmounts = new JSONArray();
            JSONArray achievedAmounts = new JSONArray();
            JSONArray tick = new JSONArray();
            JSONArray ticks = new JSONArray();
            Target target = targetDAO.getTargetByID(targetId);
            LeadChartQueryParameters queryParameters = new LeadChartQueryParameters();
            queryParameters.setProductid(target.getProductid());
            queryParameters.setStatusid(MasterDataVarList.CCL_CODE_STATUS_LEAD_LOST);
            queryParameters.setFromdate(target.getTargetstartdate());
            queryParameters.setTodate(target.getTargetenddate());
            graph = new JSONObject();
            BigDecimal targetAmount = target.getRevenue();
            if (target.getRevenue() == null) {
                targetAmount = BigDecimal.ZERO;
            }
            JSONArray array = new JSONArray();
            array.put(0, 0);
            array.put(1, targetAmount);
            targetAmounts.put(array);
            BigDecimal forecastAmount = leadDAO.getForcastValue(queryParameters);
            if (forecastAmount == null) {
                forecastAmount = BigDecimal.ZERO;
            }
            array = new JSONArray();
            array.put(0, 0);
            array.put(1, forecastAmount);
            forecastAmounts.put(array);
            queryParameters.setStatusid(MasterDataVarList.CCL_CODE_STATUS_LEAD_CLOSED);
            BigDecimal achievedAmount = leadDAO.getAchievedValue(queryParameters);
            if (achievedAmount == null) {
                achievedAmount = BigDecimal.ZERO;
            }
            array = new JSONArray();
            array.put(0, 0);
            array.put(1, achievedAmount);
            achievedAmounts.put(array);

            tick.put(0);
            tick.put("Organization");
            ticks.put(tick);
            graph.put("target", targetAmounts);
            graph.put("forecast", forecastAmounts);
            graph.put("achieved", achievedAmounts);
            graph.put("ticks", ticks);
        } catch (SQLException sqle) {
            throw sqle;
        }
        return graph;
    }

    @Override
    @Transactional
    public JSONObject getUserContactLeadAccountGraph(String username) throws Exception {
        JSONObject graph = new JSONObject();
        try {
            Date[] querter = commonDAO.getCurrentQuerter();

            JSONArray data = new JSONArray();
            JSONObject dataObject = new JSONObject();
            JSONArray point = new JSONArray();
            JSONArray points = new JSONArray();
            JSONArray tick = new JSONArray();
            JSONArray ticks = new JSONArray();

            dataObject.put("label", "Contact");
//            dataObject.put("color", "#E74C3C");
            point.put(contactDAO.getContactCountForUserDateReange(username, querter));
            point.put(0);
            points.put(point);
            dataObject.put("data", points);
            data.put(dataObject);

            tick.put(0);
            tick.put("Contact");
            ticks.put(tick);

            dataObject = new JSONObject();
            point = new JSONArray();
            points = new JSONArray();
            tick = new JSONArray();

            dataObject.put("label", "Lead");
//            dataObject.put("color", "#F1C40F");
            point.put(leadDAO.getLeadCountForStatusUserCreatedDateRange(username, MasterDataVarList.CCL_CODE_STATUS_LEAD_INITIAL, querter));
            point.put(1);
            points.put(point);
            dataObject.put("data", points);
            data.put(dataObject);

            tick.put(1);
            tick.put("Lead");
            ticks.put(tick);

            dataObject = new JSONObject();
            point = new JSONArray();
            points = new JSONArray();
            tick = new JSONArray();

            dataObject.put("label", "Account");
//            dataObject.put("color", "#27AE60");
            point.put(leadDAO.getLeadCountForStatusUserUpdatedDateRange(username, MasterDataVarList.CCL_CODE_STATUS_LEAD_CLOSED, querter));
            point.put(2);
            points.put(point);
            dataObject.put("data", points);
            data.put(dataObject);

            tick.put(2);
            tick.put("Account");
            ticks.put(tick);

            graph.put("data", data);
            graph.put("ticks", ticks);
        } catch (SQLException sqle) {
            throw sqle;
        }
        return graph;
    }

    @Override
    @Transactional
    public JSONObject getUserSuccessFailLeadRatioGraph(String username) throws Exception {
        JSONObject graph = new JSONObject();
        try {
            Date[] querter = commonDAO.getCurrentQuerter();

            JSONObject dataObject = new JSONObject();
            JSONArray dataObjects = new JSONArray();
            long pendingLeadCount = leadDAO.getLeadCountForStatusUserCreatedDateRange(username, MasterDataVarList.CCL_CODE_STATUS_LEAD_INITIAL, querter);
            long forcastLeadCount = leadDAO.getLeadCountForUserStatusForecastDate(username, MasterDataVarList.CCL_CODE_STATUS_LEAD_INITIAL, querter);
            long wonLeadCount = leadDAO.getLeadCountForUserStatusSalesColseDate(username, MasterDataVarList.CCL_CODE_STATUS_LEAD_CLOSED, querter);
            long lostLeadCount = leadDAO.getLeadCountForUserStatusSalesLostDate(username, MasterDataVarList.CCL_CODE_STATUS_LEAD_LOST, querter);
            long totalLeadCount = pendingLeadCount + forcastLeadCount + wonLeadCount + lostLeadCount;
            dataObject.put("label", "Leads Won");
            try {
                dataObject.put("data", Math.round(((wonLeadCount * 100.0) / totalLeadCount) * 100.0) / 100.0);
            } catch (ArithmeticException ae) {
                dataObject.put("data", 0);
            }
            dataObject.put("color", "#96AA27");
            dataObjects.put(dataObject);

            dataObject = new JSONObject();
            dataObject.put("label", "Leads Pending");
            try {
                dataObject.put("data", Math.round(((pendingLeadCount * 100.0) / totalLeadCount) * 100.0) / 100.0);
            } catch (ArithmeticException ae) {
                dataObject.put("data", 0);
            }
            dataObject.put("color", "#F9B433");
            dataObjects.put(dataObject);

            dataObject = new JSONObject();
            dataObject.put("label", "Leads Forecast");
            try {
                dataObject.put("data", Math.round(((forcastLeadCount * 100.0) / totalLeadCount) * 100.0) / 100.0);
            } catch (ArithmeticException ae) {
                dataObject.put("data", 0);
            }
            dataObject.put("color", "#00BFFF");
            dataObjects.put(dataObject);

            dataObject = new JSONObject();
            dataObject.put("label", "Leads Lost");
            try {
                dataObject.put("data", Math.round(((lostLeadCount * 100.0) / totalLeadCount) * 100.0) / 100.0);
            } catch (ArithmeticException ae) {
                dataObject.put("data", 0);
            }
            dataObject.put("color", "#DB5C9B");
            dataObjects.put(dataObject);

            graph.put("data", dataObjects);
        } catch (SQLException sqle) {
            throw sqle;
        }
        return graph;
    }

    @Override
    @Transactional
    public JSONObject getTargetRatioOrganizationGraph(long targetId) throws Exception {
        JSONObject graph = null;
        try {
            Target target = targetDAO.getTargetByID(targetId);
            LeadChartQueryParameters parameters = new LeadChartQueryParameters();
            parameters.setStatusid(MasterDataVarList.CCL_CODE_STATUS_LEAD_CLOSED);
            parameters.setProductid(target.getProductid());
            parameters.setFromdate(Common.getStartingTimeofDay(target.getTargetstartdate()));
            parameters.setTodate(Common.getStartingTimeofDay(target.getTargetenddate()));
            BigDecimal achivedAmount = leadDAO.getAchievedValue(parameters);
            if (achivedAmount == null) {
                achivedAmount = BigDecimal.ZERO;
            }
            BigDecimal ratio = BigDecimal.ZERO;
            try {
                BigDecimal prcent = new BigDecimal("100");
                BigDecimal multiply = achivedAmount.multiply(prcent);
                ratio = multiply.divide(target.getRevenue(), RoundingMode.HALF_UP);
            } catch (ArithmeticException ae) {
            }
            graph = new JSONObject();
            graph.put("data", ratio.toString());
        } catch (SQLException sqle) {
            throw sqle;
        }
        return graph;
    }

    @Override
    @Transactional
    public JSONObject getTargetRatioUserGraph(long targetId, String username) throws Exception {
        JSONObject graph = null;
        try {
            Target target = targetDAO.getTargetByID(targetId);
            LeadChartQueryParameters parameters = new LeadChartQueryParameters();
            parameters.setStatusid(MasterDataVarList.CCL_CODE_STATUS_LEAD_CLOSED);
            parameters.setProductid(target.getProductid());
            parameters.setFromdate(Common.getStartingTimeofDay(target.getTargetstartdate()));
            parameters.setTodate(Common.getStartingTimeofDay(target.getTargetenddate()));
            parameters.setUsername(username);
            BigDecimal achivedAmount = leadDAO.getAchievedValueForUser(parameters);
            if (achivedAmount == null) {
                achivedAmount = BigDecimal.ZERO;
            }
            BigDecimal ratio = BigDecimal.ZERO;
            try {
                BigDecimal prcent = new BigDecimal("100");
                BigDecimal multiply = achivedAmount.multiply(prcent);
                ratio = multiply.divide(target.getRevenue(), RoundingMode.HALF_UP);
            } catch (ArithmeticException ae) {
            }
            graph = new JSONObject();
            graph.put("data", ratio.toString());
        } catch (SQLException sqle) {
            throw sqle;
        }
        return graph;
    }

    @Override
    @Transactional
    public List<Target> getTargetDropDownList(String username) throws Exception {
        return targetDAO.getTargetDropDownList(username);
    }

    @Override
    @Transactional
    public List<TargetPeriod> getTargetPeriodDropDownList() throws Exception {
        return targetPeriodDAO.getTargetPeriodDropDownList();
    }

    @Override
    @Transactional
    public Map<String, String> getDropdownValueList(String username, Performancedashboard performancedashboard) throws Exception {
        return targetDAO.getDropdownValueList(username, performancedashboard);
    }

//    @Override
//    @Transactional
//    public JSONObject getActivityPerformanceByTargetId(long targetId) throws Exception {
//        JSONObject dataObject = new JSONObject();
//        Target target = targetDAO.getTargetByID(targetId);
//        dataObject.put("activitycount", activityDAO.getActivityCountForActivityId(target.getActivitytpe(),
//                target.getTargetstartdate(),
//                target.getTargetenddate()));
//        dataObject.put("targetcounts", target.getRevenue().longValue());
//        return dataObject;
//    }
    @Override
    @Transactional
    public JSONObject getActivitiesPerformanceByTargetPeriod(long targetId) throws Exception {
        JSONObject dataObject = new JSONObject();
//        JSONArray callGraph = new JSONArray();
//        JSONArray emailGraph = new JSONArray();
//        JSONArray vistMeetingGraph = new JSONArray();
//        JSONObject callGraphLines = new JSONObject();
//        JSONObject emailGraphLines = new JSONObject();
//        JSONObject visitMeetingGraphLines = new JSONObject();
//        JSONArray callGraphPoint = new JSONArray();
//        JSONArray emailGraphPoint = new JSONArray();
//        JSONArray visitMeetingGraphPoint = new JSONArray();
        Target target = targetDAO.getTargetByID(targetId);
        List<ActivityTarget> activityTargets = activityTargetDAO.getActivityTargetListByTargetId(targetId);
        List<Date[]> periodList = this.getDatePeriods(target);

//        callGraphPoint.put(Common.getStartingTimeofDay(periodList.get(0)[0]).getTime());
//        callGraphPoint.put(BigDecimal.ZERO.setScale(2, RoundingMode.CEILING));
//        callGraphLines.put("label", "Call Activity Target");
//        callGraphLines.append("data", callGraphPoint);
//
//        emailGraphPoint.put(Common.getStartingTimeofDay(periodList.get(0)[0]).getTime());
//        emailGraphPoint.put(BigDecimal.ZERO.setScale(2, RoundingMode.CEILING));
//        emailGraphLines.put("label", "E-mail Activity Target");
//        emailGraphLines.append("data", callGraphPoint);
//
//        callGraphPoint = new JSONArray();
//        emailGraphPoint = new JSONArray();
//        callGraphPoint.put(Common.getStartingTimeofDay(target.getTargetenddate()).getTime());
//        emailGraphPoint.put(Common.getStartingTimeofDay(target.getTargetenddate()).getTime());
//        long callActivityTargetCount = 0;
//        long emailActivityTargetCount = 0;
//        long vistMeetingActivityTargetCount = 0;
//        for (ActivityTarget activityTarget : activityTargets) {
//            if (activityTarget.getActivitytypeid() == MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_CALLS) {
//                callActivityTargetCount = activityTarget.getCount();
//            } else if (activityTarget.getActivitytypeid() == MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_PROPOSALS) {
//                emailActivityTargetCount = activityTarget.getCount();
//            } else if (activityTarget.getActivitytypeid() == MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_VISITS_MEETINGS) {
//                vistMeetingActivityTargetCount = activityTarget.getCount();
//            }
//        }
//        callGraphPoint.put(callActivityTargetCount);
//        callGraphLines.append("data", callGraphPoint);
//        callGraph.put(callGraphLines);
//
//        emailGraphPoint.put(emailActivityTargetCount);
//        emailGraphLines.append("data", emailGraphPoint);
//        emailGraph.put(emailGraphLines);
//
//        callGraphLines = new JSONObject();
//        callGraphPoint = new JSONArray();
//
//        emailGraphLines = new JSONObject();
//        emailGraphPoint = new JSONArray();
//
//        callGraphPoint.put(Common.getStartingTimeofDay(periodList.get(0)[0]).getTime());
//        callGraphPoint.put(BigDecimal.ZERO.setScale(2, RoundingMode.CEILING));
//        callGraphLines.put("label", "Call Activity Achievement");
//        callGraphLines.append("data", callGraphPoint);
//
//        emailGraphPoint.put(Common.getStartingTimeofDay(periodList.get(0)[0]).getTime());
//        emailGraphPoint.put(BigDecimal.ZERO.setScale(2, RoundingMode.CEILING));
//        emailGraphLines.put("label", "E-mail Activity Achievement");
//        emailGraphLines.append("data", emailGraphPoint);
//        long totalCallCount = 0;
//        long totalEmailCount = 0;
//        for (Date[] point : periodList) {
//            long callCount = activityDAO.getActivityCountByActivityTypeTargetDateRange(MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_CALLS, target, point);
//            callGraphPoint = new JSONArray();
//            callGraphPoint.put(point[1].getTime());
//            totalCallCount += callCount;
//            callGraphPoint.put(totalCallCount);
//            callGraphLines.append("data", callGraphPoint);
//            long emailCount = activityDAO.getActivityCountByActivityTypeTargetDateRange(MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_PROPOSALS, target, point);
//            emailGraphPoint = new JSONArray();
//            emailGraphPoint.put(point[1].getTime());
//            totalEmailCount += emailCount;
//            emailGraphPoint.put(totalEmailCount);
//            emailGraphLines.append("data", emailGraphPoint);
//            activityDAO.getActivityCountByActivityTypeTargetDateRange(MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_VISITS_MEETINGS, target, point);
//        }
//        callGraph.put(callGraphLines);
//        emailGraph.put(emailGraphLines);
        if (periodList != null && periodList.size() > 0) {
            dataObject.put("callactivitygraph", this.getActivityTargetPoints("Call", target, activityTargets, MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_CALLS, periodList));
            dataObject.put("proposalactivitygraph", this.getActivityTargetPoints("Proposals", target, activityTargets, MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_PROPOSALS, periodList));
            dataObject.put("visitmeetingactivitygraph", this.getActivityTargetPoints("Visit / Meeting", target, activityTargets, MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_VISITS_MEETINGS, periodList));
            dataObject.put("calls", activityDAO.getActivityCountByActivityTypeForTarget(MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_CALLS, target));
            dataObject.put("proposals", activityDAO.getActivityCountByActivityTypeForTarget(MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_PROPOSALS, target));
            dataObject.put("visits_meetings", activityDAO.getActivityCountByActivityTypeForTarget(MasterDataVarList.AFFINITI_CODE_ACTIVITY_TYPE_VISITS_MEETINGS, target));
        } else {
            JSONObject emptyobject = new JSONObject();
            JSONArray emptyarray = new JSONArray();
            emptyarray.put(emptyobject);
            dataObject.put("callactivitygraph", emptyarray);
            dataObject.put("proposalactivitygraph", emptyarray);
            dataObject.put("visitmeetingactivitygraph", emptyarray);
            dataObject.put("calls", 0);
            dataObject.put("proposals", 0);
            dataObject.put("visits_meetings", 0);
        }
        return dataObject;
    }

    private JSONArray getActivityTargetPoints(String lable, Target target, List<ActivityTarget> activityTargets, int activityType, List<Date[]> periodList) throws Exception {
        JSONArray graph = new JSONArray();
        JSONObject graphLines = new JSONObject();
        JSONArray graphPoint = new JSONArray();

        graphPoint.put(Common.getStartingTimeofDay(periodList.get(0)[0]).getTime());
        graphPoint.put(BigDecimal.ZERO.setScale(2, RoundingMode.CEILING));
        graphLines.put("label", lable + " Activity Target");
        graphLines.append("data", graphPoint);

        graphPoint = new JSONArray();
        graphPoint.put(Common.getStartingTimeofDay(target.getTargetenddate()).getTime());
        long activityTargetCount = 0;
        for (ActivityTarget activityTarget : activityTargets) {
            if (activityTarget.getActivitytypeid() == activityType) {
                activityTargetCount = activityTarget.getCount();
            }
        }
        graphPoint.put(activityTargetCount);
        graphLines.append("data", graphPoint);
        graph.put(graphLines);

        graphLines = new JSONObject();
        graphPoint = new JSONArray();

        graphPoint.put(Common.getStartingTimeofDay(periodList.get(0)[0]).getTime());
        graphPoint.put(BigDecimal.ZERO.setScale(2, RoundingMode.CEILING));
        graphLines.put("label", lable + " Activity Achievement");
        graphLines.append("data", graphPoint);

        long totalCount = 0;
        for (Date[] point : periodList) {
            long count = activityDAO.getActivityCountByActivityTypeTargetDateRange(activityType, target, point);
            graphPoint = new JSONArray();
            graphPoint.put(point[1].getTime());
            totalCount += count;
            graphPoint.put(totalCount);
            graphLines.append("data", graphPoint);
        }
        graph.put(graphLines);
        return graph;
    }

    private List<Date[]> getDatePeriods(Target target) throws Exception {
        List<Date[]> periodList = new ArrayList<>();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(Common.getStartingTimeofDay(target.getTargetstartdate()));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(Common.getEndingTimeofDay(target.getTargetenddate()));
        Date today = Common.getStartingTimeofDay(commonDAO.getCurentTimesStamp());
        if (target.getTargetperiodid() == MasterDataVarList.AFFINITI_TARGETPERIOD_MONTHLY) {
            int weekOfMonth = startCalendar.get(Calendar.WEEK_OF_MONTH);
            int numberOfDaysInMonth = startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int i = 1;
            while ((startCalendar.get(Calendar.WEEK_OF_YEAR) <= endCalendar.get(Calendar.WEEK_OF_YEAR)) && (i < numberOfDaysInMonth) && (startCalendar.getTimeInMillis() < today.getTime())) {
                Date[] datePoint = new Date[2];
                datePoint[0] = Common.getStartingTimeofDay(startCalendar.getTime());
                while ((weekOfMonth == startCalendar.get(Calendar.WEEK_OF_MONTH))
                        && (i < numberOfDaysInMonth)) {
                    startCalendar.add(Calendar.DATE, 1);
                    i++;
                    if (today.getTime() < startCalendar.getTimeInMillis()) {
                        break;
                    }
                }
                if (i == numberOfDaysInMonth) {
                    startCalendar.add(Calendar.DATE, 1);
                }
                weekOfMonth = startCalendar.get(Calendar.WEEK_OF_MONTH);
                datePoint[1] = Common.getStartingTimeofDay(startCalendar.getTime());
                periodList.add(datePoint);
            }
        } else if (target.getTargetperiodid() == MasterDataVarList.AFFINITI_TARGETPERIOD_QUARTELY
                || target.getTargetperiodid() == MasterDataVarList.AFFINITI_TARGETPERIOD_ANNUALLY) {
            while (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis() && today.getTime() > startCalendar.getTimeInMillis()) {
                Date[] datePoint = new Date[2];
                datePoint[0] = startCalendar.getTime();
                startCalendar.add(Calendar.MONTH, 1);
                if (today.getTime() >= startCalendar.getTimeInMillis()) {
                    datePoint[1] = startCalendar.getTime();
                } else {
                    while (startCalendar.getTimeInMillis() != today.getTime()) {
                        startCalendar.add(Calendar.DATE, -1);
                    }
                }
                datePoint[1] = startCalendar.getTime();
                periodList.add(datePoint);
            }
        }
        return periodList;
    }

    @Override
    @Transactional
    public List<BranchPerformance> getBranchPerformanceByTargetID(long targetid) throws SQLException {
        return leadDAO.getBranchPerformanceByTargetID(targetid);
    }

    @Override
    @Transactional
    public List<RegionPerformance> getRegionPerformanceByTargetID(long targetid) throws SQLException {
        return leadDAO.getRegionPerformanceByTargetID(targetid);
    }

    @Override
    public int getRegionalPerformanceCount(int region) throws SQLException {
        return leadDAO.getRegionalPerformanceCount(region);
    }

    @Override
    public JSONArray getRegionalPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int region) throws SQLException {
        return leadDAO.getRegionalPerformance(minCount, start, sort, fromDate, toDate, region);
    }

    @Override
    public int getBranchPerformanceCount(int region, int productcategory, Integer[] territories) throws SQLException {
        return leadDAO.getBranchPerformanceCount(region, productcategory, territories);
    }

    @Override
    public JSONArray getBranchPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int region, int productcategory, Integer[] territories) throws SQLException {
        return leadDAO.getBranchPerformance(minCount, start, sort, fromDate, toDate, region, productcategory, territories);
    }

    @Override
    public int getIndividualPerformanceCount(int branch, int productcategory, Integer[] territories, String username) throws SQLException {
        return leadDAO.getIndividualPerformanceCount(branch, productcategory, territories, username);
    }

    @Override
    public JSONArray getIndividualPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int branch, int productcategory, Integer[] territories, String username) throws SQLException {
        return leadDAO.getIndividualPerformance(minCount, start, sort, fromDate, toDate, branch, productcategory, territories, username);
    }

    @Override
    public JSONObject getNext6MonthsForecast() throws SQLException {
        JSONObject dataObject = new JSONObject();
        JSONArray dataArray = new JSONArray();
        Date current = commonDAO.getCurentTimesStamp();
        Date monthStart = Common.getStartingDateTimeOfMonth(current);
        for (int i = 0; i < 6; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(monthStart);
            calendar.add(Calendar.MONTH, 1);
            Date monthEnd = calendar.getTime();
            JSONArray array = leadDAO.getForecastForDateRange(monthStart, monthEnd);
            JSONObject dataElement = new JSONObject();
            dataElement.put("period", new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM).format(monthEnd));
            for (int ii = 0; array.length() > ii; ii++) {
                JSONObject data = (JSONObject) array.get(ii);
                if (data.getString("productcategory").equalsIgnoreCase("credit")) {
                    dataElement.put("credit", data.get("forecastamount"));
                } else if (data.getString("productcategory").equalsIgnoreCase("investment")) {
                    dataElement.put("investment", data.get("forecastamount"));
                }
            }
            monthStart = calendar.getTime();
            dataArray.put(dataElement);
        }
        dataObject.put("data", dataArray);
        JSONArray ykeys = new JSONArray();
        ykeys.put("credit");
        ykeys.put("investment");
        dataObject.put("ykeys", ykeys);
        JSONArray labels = new JSONArray();
        labels.put("Credit");
        labels.put("Investment");
        dataObject.put("labels", labels);
        dataObject.put("xkey", "period");
        return dataObject;
    }
}

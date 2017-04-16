/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.dashboard;

import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.dao.dashboard.DashboardDAOImpl;
import com.avn.ccl.dao.orghierarchy.OrghierarchyDAO;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.casemgt.CaseDAOImpl;
import com.avn.ccl.daoimpl.employee.EmployeeDAOImpl;
import com.avn.ccl.model.dashboard.DashBordChart;
import com.avn.ccl.model.lead.BranchPerformance;
import com.avn.ccl.model.lead.RegionPerformance;
import com.avn.ccl.model.performancedashboard.Performancedashboard;
import com.avn.ccl.model.target.Target;
import com.avn.ccl.service.dashboard.DashboardService;
import com.avn.ccl.service.target.TargetService;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author : Roshen Dilshan
 * @Document : PerformanceDashboardController
 * @Created on : Jun 21, 2016, 12:19:17 PM
 */
@Controller
public class PerformanceDashboardController {

    @Autowired
    CaseDAOImpl casedaoimple;
    @Autowired
    DashboardDAOImpl dashboarddaoimpl;
    @Autowired
    CommonDAOImpl commondaoimpl;
    @Autowired
    DashboardService dashboardService;
    @Autowired
    AuditTraceDAOImpl auditTraceDAOImpl;
    @Autowired
    TargetService targetService;
    @Autowired
    OrghierarchyDAO orghierarchyDAO;
    @Autowired
    EmployeeDAOImpl employeeDAOImpl;

    @RequestMapping(value = "/performancedashboard", method = RequestMethod.GET)
    public ModelAndView performanceDashboard(@ModelAttribute("dashboardform") Performancedashboard performancedashboard, ModelMap model, HttpSession session) {
        String userName = (String) session.getAttribute("username");
        try {
            auditTraceDAOImpl.insertAuditTrace(null, null, "Performance Dashboard", "", userName);
            try {
                model.addAttribute("targetPeriodList", dashboardService.getTargetPeriodDropDownList());
                model.addAttribute("targetList", dashboardService.getTargetDropDownList(userName));
                JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");

                model.addAttribute("TargetGraph1", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_BAR_CHART), assignchartlist));
                model.addAttribute("TargetGraph1ID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_BAR_CHART), assignchartlist));
                model.addAttribute("TargetGraph1CSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_BAR_CHART), assignchartlist));

                model.addAttribute("TargetGraph2", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_TARGET_GRAPH), assignchartlist));
                model.addAttribute("TargetGraph2ID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_TARGET_GRAPH), assignchartlist));
                model.addAttribute("TargetGraph2CSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_TARGET_GRAPH), assignchartlist));

                model.addAttribute("LeadContagGraph", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CONTACKLEAD), assignchartlist));
                model.addAttribute("LeadContagGraphID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CONTACKLEAD), assignchartlist));
                model.addAttribute("LeadContagGraphCSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CONTACKLEAD), assignchartlist));

                model.addAttribute("LeadRatioGraph", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist));
                model.addAttribute("LeadRationID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist));
                model.addAttribute("LeadRationCSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist));

            } catch (Exception e) {
            }
        } catch (Exception ex) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "HP");
        return new ModelAndView("dashboard/performancedashboard");
    }

    @RequestMapping(value = "/dashboard/targetdropdown", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> getTerritoriesByHierarichyBranch(
            @RequestParam("fromDate") String sfromDate,
            @RequestParam("toDate") String stoDate,
            @RequestParam("targetPeriod") String targetPeriod,
            HttpSession session) {
        Map<String, String> list = new LinkedHashMap<>();
        try {
            Performancedashboard performancedashboard = new Performancedashboard();
            Date fromDate = Common.getStartingTimeofDay(Common.getTimestampFromDateAndTime(CommonVarList.DATE_FORMAT_yyyy_MM_dd, sfromDate));
            Date toDate = Common.getEndingTimeofDay(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, stoDate));
            performancedashboard.setFromDate(fromDate);
            performancedashboard.setToDate(toDate);
            performancedashboard.setTargetPeriod(targetPeriod);
            String userName = (String) session.getAttribute("username");
            list = dashboardService.getDropdownValueList(userName, performancedashboard);
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @RequestMapping(value = "/dashboard/targetGraph", method = RequestMethod.POST)
    public @ResponseBody
    String loadTargetGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String data = "";
        long targetId = Long.valueOf(request.getParameter("target"));
        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
        try {
            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_TARGET_GRAPH), assignchartlist);
            if (dashbordchart.isTragetgraph()) {
                data = dashboardService.getAgentTargetGraph(targetId).toString();
            }
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return data;
    }

    @RequestMapping(value = "/dashboard/targetBarGraph", method = RequestMethod.POST)
    public @ResponseBody
    String loadTargetBarGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String data = "";
        long targetId = Long.valueOf(request.getParameter("target"));
        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
        try {
            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_BAR_CHART), assignchartlist);
            if (dashbordchart.isBarchart()) {
                data = dashboardService.getBarTargetGarph(targetId).toString();
            }
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return data;
    }

    @RequestMapping(value = "/dashboard/contactleadaccount", method = RequestMethod.POST)
    public @ResponseBody
    String loadContactLeadAccountGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String data = "";
        try {
            JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CONTACKLEAD), assignchartlist);
            if (dashbordchart.isContacklead()) {
                data = dashboardService.getUserContactLeadAccountGraph((String) session.getAttribute("username")).toString();
            }
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return data;
    }

    @RequestMapping(value = "/dashboard/leadratio", method = RequestMethod.POST)
    public @ResponseBody
    String loadRatioGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String data = "";
        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
        DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist);
        try {
            if (dashbordchart.isLeadratio()) {
                data = dashboardService.getUserSuccessFailLeadRatioGraph((String) session.getAttribute("username")).toString();
            }
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return data;
    }

    @RequestMapping(value = "/dashboard/organizationratio", method = RequestMethod.POST)
    public @ResponseBody
    String loadTargetRatioOrganizationGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String data = "";
        long targetId = Long.valueOf(request.getParameter("target"));
//        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
//        DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist);
        try {
//            if (dashbordchart.isLeadratio()) {
            data = dashboardService.getTargetRatioOrganizationGraph(targetId).toString();
//            }
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return data;
    }

    @RequestMapping(value = "/dashboard/userratio", method = RequestMethod.POST)
    public @ResponseBody
    String loadTargetRatioUserGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String data = "";
        String userName = (String) session.getAttribute("username");
        long targetId = Long.valueOf(request.getParameter("target"));
//        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
//        DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist);
        try {
//            if (dashbordchart.isLeadratio()) {
            data = dashboardService.getTargetRatioUserGraph(targetId, userName).toString();
//            }
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return data;
    }

//    @RequestMapping(value = "/dashboard/activitygraph", method = RequestMethod.POST)
//    public @ResponseBody
//    String loadActivityTargetGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
//        String data = "";
////        String userName = (String) session.getAttribute("username");
//        long targetId = Long.valueOf(request.getParameter("target"));
////        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
////        DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist);
//        try {
////            if (dashbordchart.isLeadratio()) {
//            data = dashboardService.getActivityPerformanceByTargetId(targetId).toString();
////            }
//        } catch (Exception e) {
//            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return data;
//    }
    @RequestMapping(value = "/dashboard/activitiesgraph", method = RequestMethod.POST)
    public @ResponseBody
    String loadActivitiesTargetGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String data = "";
//        String userName = (String) session.getAttribute("username");
        long targetId = Long.valueOf(request.getParameter("target"));
//        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
//        DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist);
        try {
//            if (dashbordchart.isLeadratio()) {
            data = dashboardService.getActivitiesPerformanceByTargetPeriod(targetId).toString();
//            }
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return data;
    }

    @RequestMapping(value = "/dashboard/gettarget", method = RequestMethod.POST)
    public @ResponseBody
    Target getTargetByID(@RequestParam("target") long targetid) {
        Target target = new Target();
        try {
            target = targetService.getTargetByID(targetid);
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return target;
    }

    @RequestMapping(value = "/dashboard/getbranchperformance", method = RequestMethod.POST)
    public @ResponseBody
    List<BranchPerformance> getBranchPerformancebyTargetID(@RequestParam("target") long targetid) {
        List<BranchPerformance> list = new ArrayList<>();
        try {
            list = dashboardService.getBranchPerformanceByTargetID(targetid);
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @RequestMapping(value = "/dashboard/getregionperformance", method = RequestMethod.POST)
    public @ResponseBody
    List<RegionPerformance> getRegionPerformancebyTargetID(@RequestParam("target") long targetid) {
        List<RegionPerformance> list = new ArrayList<>();
        try {
            list = dashboardService.getRegionPerformanceByTargetID(targetid);
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    private DashBordChart getAssignChart(String wedgetid, JSONArray objList) throws Exception {
        DashBordChart dashbordchart = new DashBordChart();
        dashbordchart.setCallvscase(false);
        dashbordchart.setAssigncase(false);
        dashbordchart.setBarchart(false);
        dashbordchart.setProductwiseinquiry(false);
        dashbordchart.setMycall(false);
        dashbordchart.setMycase(false);
        dashbordchart.setTragetgraph(false);

        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);

            if (yObject.getString("wedgetid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CALL_VS_CASECHART))) {
                if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                    dashbordchart.setCallvscase(true);
                }
            } else if (yObject.getString("wedgetid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_ASSIGN_CASE))) {
                if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                    dashbordchart.setAssigncase(true);
                }

            } else if (yObject.getString("wedgetid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_BAR_CHART))) {
                if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                    dashbordchart.setBarchart(true);
                }

            } else if (yObject.getString("wedgetid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_PRODUCT_WISE_INQUIRY))) {
                if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                    dashbordchart.setProductwiseinquiry(true);
                }

            } else if (yObject.getString("wedgetid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_MY_CALL))) {
                if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                    dashbordchart.setMycall(true);
                }

            } else if (yObject.getString("wedgetid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_MY_CASE))) {
                if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                    dashbordchart.setMycase(true);
                }

            } else if (yObject.getString("wedgetid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_TARGET_GRAPH))) {
                if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                    dashbordchart.setTragetgraph(true);
                }

            } else if (yObject.getString("wedgetid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CONTACKLEAD))) {
                if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                    dashbordchart.setContacklead(true);
                }

            } else if (yObject.getString("wedgetid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION))) {
                if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                    dashbordchart.setLeadratio(true);
                }

            }
        }

        return dashbordchart;
    }

    private String getWedgetID(String wedgetid, JSONArray objList) throws Exception {
        String ID = "";
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                ID = yObject.get("sortid").toString();
                break;
            }
        }

        return ID;
    }

    private String getWedgetCSS(String wedgetid, JSONArray objList) throws Exception {
        String css = "";
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                css = yObject.get("css").toString();
                break;
            }
        }

        return css;
    }

    @RequestMapping(value = "/regionalperformance", method = RequestMethod.GET)
    public @ResponseBody
    String getRetionalPerformanceTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONObject jsonResponse = new JSONObject();
        String user = (String) session.getAttribute("username");

        int userHierarchyid = employeeDAOImpl.getUserHierarchyByUsername(user);

        String columns[] = {"TS.TERRITORY", "TS.PRODUCTCATEGORY", "TS.TERRITORY", "TS.PRODUCTCATEGORY", "TS.EMPLOYEENAME", "TS.LEADFORECAST", "TS.LEADCONFIRMED"};

        Date fromDate = ((request.getParameter("perFromDate") == null) || (request.getParameter("perFromDate").trim().isEmpty()))
                ? null : Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("perFromDate"));
        Date toDate = ((request.getParameter("perToDate") == null) || (request.getParameter("perToDate").trim().isEmpty()))
                ? null : Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("perToDate"));

        if (fromDate != null) {
            fromDate = Common.getStartingTimeofDay(fromDate);
        }
        if (toDate != null) {
            toDate = Common.getEndingTimeofDay(toDate);
        }

        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            String sort = "ORDER BY ";
            sort += columns[param.iSortCol[0]] + " ";

            if (param.sSortDir[0].equalsIgnoreCase(CommonVarList.DATA_TABLE_SORTING_ASC)) {
                sort += CommonVarList.DATA_TABLE_SORTING_ASC + " ";
            } else {
                sort += CommonVarList.DATA_TABLE_SORTING_DESC + " ";
            }

            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }

            if (param.iDisplayLength < 5 || param.iDisplayLength > 100) {
                param.iDisplayLength = 5;
            }

            if (userHierarchyid > 0) {
                int accessHierarchyLevel = orghierarchyDAO.getLevelByOrghierarchyId(MasterDataVarList.AFFINITI_ORGHIERARCHY_REGIONAL_MGR);
                int userHierarchyLevel = orghierarchyDAO.getLevelByOrghierarchyId(userHierarchyid);

                if (accessHierarchyLevel > userHierarchyLevel) {
                    iTotalRecords = dashboardService.getRegionalPerformanceCount(0);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        rows = dashboardService.getRegionalPerformance(param.iDisplayLength, param.iDisplayStart, sort, fromDate, toDate, 0);
                    }
                } else if (accessHierarchyLevel == userHierarchyLevel) {
                    int rmregionid = employeeDAOImpl.getRMUserRegionIdByUsername(user);
                    iTotalRecords = dashboardService.getRegionalPerformanceCount(rmregionid);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        rows = dashboardService.getRegionalPerformance(param.iDisplayLength, param.iDisplayStart, sort, fromDate, toDate, rmregionid);
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);

        return jsonResponse.toString();
    }

    @RequestMapping(value = "/branchperformance", method = RequestMethod.GET)
    public @ResponseBody
    String getBranchPerformanceTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONObject jsonResponse = new JSONObject();
        String user = (String) session.getAttribute("username");

        int userHierarchyid = employeeDAOImpl.getUserHierarchyByUsername(user);

        String columns[] = {"TS.TERRITORY", "TS.PRODUCTCATEGORY", "TS.TERRITORY", "TS.PRODUCTCATEGORY", "TS.EMPLOYEENAME", "TS.LEADFORECAST", "TS.LEADCONFIRMED"};

        Date fromDate = ((request.getParameter("perFromDate") == null) || (request.getParameter("perFromDate").trim().isEmpty()))
                ? null : Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("perFromDate"));
        Date toDate = ((request.getParameter("perToDate") == null) || (request.getParameter("perToDate").trim().isEmpty()))
                ? null : Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("perToDate"));

        int regionid = ((request.getParameter("regionid") == null) || (request.getParameter("regionid").trim().isEmpty()))
                ? 0 : Integer.valueOf(request.getParameter("regionid"));

        int productcategoryid = ((request.getParameter("region_productcategory") == null) || (request.getParameter("region_productcategory").trim().isEmpty()))
                ? 0 : Integer.valueOf(request.getParameter("region_productcategory"));

        if (fromDate != null) {
            fromDate = Common.getStartingTimeofDay(fromDate);
        }
        if (toDate != null) {
            toDate = Common.getEndingTimeofDay(toDate);
        }

        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            String sort = "ORDER BY ";
            sort += columns[param.iSortCol[0]] + " ";

            if (param.sSortDir[0].equalsIgnoreCase(CommonVarList.DATA_TABLE_SORTING_ASC)) {
                sort += CommonVarList.DATA_TABLE_SORTING_ASC + " ";
            } else {
                sort += CommonVarList.DATA_TABLE_SORTING_DESC + " ";
            }

            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }

            if (param.iDisplayLength < 5 || param.iDisplayLength > 100) {
                param.iDisplayLength = 5;
            }

            if (userHierarchyid > 0) {
                int accessHierarchyLevel = orghierarchyDAO.getLevelByOrghierarchyId(MasterDataVarList.AFFINITI_ORGHIERARCHY_REGIONAL_MGR);
                int userHierarchyLevel = orghierarchyDAO.getLevelByOrghierarchyId(userHierarchyid);

                if (accessHierarchyLevel > userHierarchyLevel) {
                    iTotalRecords = dashboardService.getBranchPerformanceCount(regionid, productcategoryid, new Integer[0]);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        rows = dashboardService.getBranchPerformance(param.iDisplayLength,
                                param.iDisplayStart,
                                sort,
                                fromDate,
                                toDate,
                                regionid,
                                productcategoryid,
                                new Integer[0]);
                    }
                } else if (accessHierarchyLevel == userHierarchyLevel) {
                    int rmregionid = employeeDAOImpl.getRMUserRegionIdByUsername(user);
                    iTotalRecords = dashboardService.getBranchPerformanceCount(rmregionid, productcategoryid, new Integer[0]);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        rows = dashboardService.getBranchPerformance(param.iDisplayLength,
                                param.iDisplayStart,
                                sort,
                                fromDate,
                                toDate,
                                rmregionid,
                                productcategoryid,
                                new Integer[0]);
                    }
                } else if (orghierarchyDAO.getLevelByOrghierarchyId(MasterDataVarList.AFFINITI_ORGHIERARCHY_BRANCH_MGR) == userHierarchyLevel) {
                    Integer[] territories = employeeDAOImpl.getEmployeeTerritorys(user);
                    if (territories.length > 0) {
                        iTotalRecords = dashboardService.getBranchPerformanceCount(regionid, productcategoryid, territories);
                        iTotalDisplayRecords = iTotalRecords;
                        if (iTotalRecords > 0) {
                            rows = dashboardService.getBranchPerformance(param.iDisplayLength,
                                    param.iDisplayStart,
                                    sort,
                                    fromDate,
                                    toDate,
                                    regionid,
                                    productcategoryid,
                                    territories);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);

        return jsonResponse.toString();
    }

    @RequestMapping(value = "/individualperformance", method = RequestMethod.GET)
    public @ResponseBody
    String getIndividualPerformanceTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONObject jsonResponse = new JSONObject();
        String user = (String) session.getAttribute("username");

        int userHierarchyid = employeeDAOImpl.getUserHierarchyByUsername(user);

        String columns[] = {"TS.TERRITORY", "TS.PRODUCTCATEGORY", "TS.TERRITORY", "TS.PRODUCTCATEGORY", "TS.EMPLOYEENAME", "TS.LEADFORECAST", "TS.LEADCONFIRMED"};

        Date fromDate = ((request.getParameter("perFromDate") == null) || (request.getParameter("perFromDate").trim().isEmpty()))
                ? null : Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("perFromDate"));
        Date toDate = ((request.getParameter("perToDate") == null) || (request.getParameter("perToDate").trim().isEmpty()))
                ? null : Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("perToDate"));

        int branchid = ((request.getParameter("branchid") == null) || (request.getParameter("branchid").trim().isEmpty()))
                ? 0 : Integer.valueOf(request.getParameter("branchid"));

        int productcategoryid = ((request.getParameter("branch_productcategory") == null) || (request.getParameter("branch_productcategory").trim().isEmpty()))
                ? 0 : Integer.valueOf(request.getParameter("branch_productcategory"));

        if (fromDate != null) {
            fromDate = Common.getStartingTimeofDay(fromDate);
        }
        if (toDate != null) {
            toDate = Common.getEndingTimeofDay(toDate);
        }

        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            String sort = "ORDER BY ";
            sort += columns[param.iSortCol[0]] + " ";

            if (param.sSortDir[0].equalsIgnoreCase(CommonVarList.DATA_TABLE_SORTING_ASC)) {
                sort += CommonVarList.DATA_TABLE_SORTING_ASC + " ";
            } else {
                sort += CommonVarList.DATA_TABLE_SORTING_DESC + " ";
            }

            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }

            if (param.iDisplayLength < 5 || param.iDisplayLength > 100) {
                param.iDisplayLength = 5;
            }

            if (userHierarchyid > 0) {
                int accessHierarchyLevel = orghierarchyDAO.getLevelByOrghierarchyId(MasterDataVarList.AFFINITI_ORGHIERARCHY_REGIONAL_MGR);
                int userHierarchyLevel = orghierarchyDAO.getLevelByOrghierarchyId(userHierarchyid);

                if (accessHierarchyLevel > userHierarchyLevel) {
                    iTotalRecords = dashboardService.getIndividualPerformanceCount(branchid, productcategoryid, new Integer[0], null);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        rows = dashboardService.getIndividualPerformance(param.iDisplayLength,
                                param.iDisplayStart,
                                sort,
                                fromDate,
                                toDate,
                                branchid,
                                productcategoryid,
                                new Integer[0],
                                null);
                    }
                } else if (accessHierarchyLevel == userHierarchyLevel) {
                    Integer[] territories = employeeDAOImpl.getEmployeeTerritorys(user);
                    iTotalRecords = dashboardService.getIndividualPerformanceCount(branchid, productcategoryid, territories, null);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        rows = dashboardService.getIndividualPerformance(param.iDisplayLength,
                                param.iDisplayStart,
                                sort,
                                fromDate,
                                toDate,
                                branchid,
                                productcategoryid,
                                territories,
                                null);
                    }
                } else if (orghierarchyDAO.getLevelByOrghierarchyId(MasterDataVarList.AFFINITI_ORGHIERARCHY_BRANCH_MGR) == userHierarchyLevel) {
                    Integer[] territories = employeeDAOImpl.getEmployeeTerritorys(user);
                    if (territories.length > 0) {
                        iTotalRecords = dashboardService.getIndividualPerformanceCount(branchid, productcategoryid, territories, null);
                        iTotalDisplayRecords = iTotalRecords;
                        if (iTotalRecords > 0) {
                            rows = dashboardService.getIndividualPerformance(param.iDisplayLength,
                                    param.iDisplayStart,
                                    sort,
                                    fromDate,
                                    toDate,
                                    branchid,
                                    productcategoryid,
                                    territories,
                                    null);
                        }
                    }
                } else if (orghierarchyDAO.getLevelByOrghierarchyId(MasterDataVarList.AFFINITI_ORGHIERARCHY_MARKETING_EXT) == userHierarchyLevel) {
                    iTotalRecords = dashboardService.getIndividualPerformanceCount(branchid, productcategoryid, new Integer[0], user);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        rows = dashboardService.getIndividualPerformance(param.iDisplayLength,
                                param.iDisplayStart,
                                sort,
                                fromDate,
                                toDate,
                                branchid,
                                productcategoryid,
                                new Integer[0],
                                user);
                    }
                }
            }

        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);

        return jsonResponse.toString();
    }

    @RequestMapping(value = "/dashboard/next6thmonthsforecast", method = RequestMethod.POST)
    public @ResponseBody
    String loadNext6MonthsForecast(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String data = "";
        try {
            data = dashboardService.getNext6MonthsForecast().toString();
        } catch (Exception e) {
            Logger.getLogger(PerformanceDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        return data;
    }

}

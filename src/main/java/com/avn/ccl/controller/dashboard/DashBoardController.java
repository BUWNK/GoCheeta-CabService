/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.dashboard;

import com.avn.ccl.controller.login.LoginController;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.casemgt.CaseDAOImpl;
import com.avn.ccl.dao.dashboard.DashboardDAOImpl;
import com.avn.ccl.model.casemgt.Case;
import com.avn.ccl.model.dashboard.DashBordChart;
import com.avn.ccl.service.dashboard.DashboardService;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ISURU
 */
@Controller
public class DashBoardController {

    @Autowired
    CaseDAOImpl casedaoimple;
    @Autowired
    DashboardDAOImpl dashboarddaoimpl;
    @Autowired
    CommonDAOImpl commondaoimpl;
    @Autowired
    DashboardService dashboardService;

    @RequestMapping(value = "/loadAssigneCaseChart", method = RequestMethod.POST)
    public @ResponseBody
    String loadAssigneCaseChart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String name = (String) session.getAttribute("username");
        JSONArray objList = (JSONArray) session.getAttribute("assignchartlist");
        JSONArray dateWithXAssignee = new JSONArray();
        JSONArray dateWithYAssignee = new JSONArray();
        List<JSONObject> jSONArray = new ArrayList<>();
        Case casebeen = new Case();
        try {
            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_ASSIGN_CASE), objList);
//            if (dashbordchart.isAssigncase()) {
            casebeen = casedaoimple.getUserRoleIdAndEmployeeId(name);
            String employeeId = String.valueOf(casebeen.getEmployeeId());
            dateWithXAssignee = dashboarddaoimpl.getCaseCount(employeeId, "CS.ASSIGNEEID1");
            dateWithYAssignee = dashboarddaoimpl.getCallCount(name);

            for (int i = 0; i < dateWithXAssignee.length(); i++) {
                boolean isDataSet = false;
                JSONObject xObject = dateWithXAssignee.getJSONObject(i);
                for (int j = 0; j < dateWithYAssignee.length(); j++) {
                    JSONObject yObject = dateWithYAssignee.getJSONObject(j);
                    if (xObject.getString("m").equalsIgnoreCase(yObject.getString("m"))) {
                        JSONObject content = new JSONObject();
//                        content.put("z", yObject.getInt("z"));
                        content.put("x", xObject.getString("m"));
                        content.put("y", xObject.getInt("y"));
                        jSONArray.add(content);
                        isDataSet = true;
                        break;
                    }
                }
                if (!isDataSet) {
                    JSONObject content = new JSONObject();
//                    content.put("y", xObject.getString("y"));
                    content.put("x", xObject.getString("m"));
                    content.put("z", 0);
                    jSONArray.add(content);
                }
            }
//            model.put("objList", jSONArray);
//            }
        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        return jSONArray.toString();
    }

    @RequestMapping(value = "/loadCaseVsCaseChart", method = RequestMethod.POST)
    public @ResponseBody
    String loadCaseVsCaseChart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        //-----------------------------------CallVs Case Charat-----------------------------------------------------
        JSONArray dateWithX = new JSONArray();
        JSONArray dateWithY = new JSONArray();
        Case casebeen = new Case();
        List<JSONObject> jSONArray = new ArrayList<>();
        String name = (String) session.getAttribute("username");
        JSONArray objList = (JSONArray) session.getAttribute("assignchartlist");
        try {
            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CALL_VS_CASECHART), objList);
            if (dashbordchart.isCallvscase()) {
                casebeen = casedaoimple.getUserRoleIdAndEmployeeId(name);
                String employeeId = String.valueOf(casebeen.getEmployeeId());
                dateWithX = dashboarddaoimpl.getCaseCount(name, "CREATEDUSER");
                dateWithY = dashboarddaoimpl.getCallCount(name);
                for (int i = 0; i < dateWithX.length(); i++) {
                    boolean isDataSet = false;
                    JSONObject xObject = dateWithX.getJSONObject(i);
                    for (int j = 0; j < dateWithY.length(); j++) {
                        JSONObject yObject = dateWithY.getJSONObject(j);
                        if (xObject.getString("m").equalsIgnoreCase(yObject.getString("m"))) {
                            JSONObject content = new JSONObject();
                            content.put("z", yObject.getInt("z"));
                            content.put("x", xObject.getString("m"));
                            content.put("y", xObject.getInt("y"));
                            jSONArray.add(content);
                            isDataSet = true;
                            break;
                        }
                    }
                    if (!isDataSet) {
                        JSONObject content = new JSONObject();
                        content.put("y", xObject.getString("y"));
                        content.put("x", xObject.getString("m"));
                        content.put("z", 0);
                        jSONArray.add(content);
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        return jSONArray.toString();
    }

    @RequestMapping(value = "/loadUserWisePieChart", method = RequestMethod.POST)
    public @ResponseBody
    String loadUserWisePieChart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        List<JSONObject> jSONArray2 = new ArrayList<>();
        String name = (String) session.getAttribute("username");
        Case casebeen = new Case();
        JSONArray objList = new JSONArray();
        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
        try {
            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_PRODUCT_WISE_INQUIRY), assignchartlist);
            if (dashbordchart.isProductwiseinquiry()) {
                casebeen = casedaoimple.getUserRoleIdAndEmployeeId(name);
                String employeeId = String.valueOf(casebeen.getEmployeeId());
                objList = dashboarddaoimpl.getProduntCount(employeeId, "and C.ASSIGNEEID1", commondaoimpl.getCurentTimesStamp());

                int total = 0;
                for (int i = 0; i < objList.length(); i++) {
                    JSONObject xObject = objList.getJSONObject(i);
                    int count = xObject.getInt("value");
                    total += count;
                }
                for (int j = 0; j < objList.length(); j++) {
                    JSONObject yObject = objList.getJSONObject(j);
                    double pcount = yObject.getDouble("value");
                    double precentage = (pcount / total) * 100;
                    JSONObject content = new JSONObject();
                    content.put("data", Math.round(precentage * 100.0) / 100.0);
                    content.put("label", yObject.getString("label"));
                    jSONArray2.add(content);

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jSONArray2.toString();
    }

    @RequestMapping(value = "/loadOrganizationWisePieChart", method = RequestMethod.POST)
    public @ResponseBody
    String loadOrganizationWisePieChart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        List<JSONObject> jSONArray2 = new ArrayList<>();
        String name = (String) session.getAttribute("username");
        JSONArray objList = new JSONArray();
        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
        try {
            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_PRODUCT_WISE_INQUIRY), assignchartlist);
            if (dashbordchart.isProductwiseinquiry()) {
                objList = dashboarddaoimpl.getProduntCountOrganizationwise(commondaoimpl.getCurentTimesStamp());
                int total = 0;
                for (int i = 0; i < objList.length(); i++) {
                    JSONObject xObject = objList.getJSONObject(i);
                    int count = xObject.getInt("value");
                    total += count;
                }
                for (int j = 0; j < objList.length(); j++) {
                    JSONObject yObject = objList.getJSONObject(j);
                    double pcount = yObject.getDouble("value");
                    double precentage = (pcount / total) * 100;
                    JSONObject content = new JSONObject();
                    content.put("data", Math.round(precentage * 100.0) / 100.0);
                    content.put("label", yObject.getString("label"));
                    jSONArray2.add(content);

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONArray2.toString();
    }

    @RequestMapping(value = "/loadUserWiseBarchart", method = RequestMethod.POST)
    public @ResponseBody
    String loadUserWiseBarchart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String name = (String) session.getAttribute("username");
        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
        Case casebeen = new Case();
        JSONObject fulllist = new JSONObject();
        JSONArray Dcount = new JSONArray();
        JSONArray Departmentlist = new JSONArray();
        List<JSONObject> jSONArray4 = new ArrayList<>();;
        List<String> departments = new ArrayList<>();
        try {
            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_PRODUCT_WISE_INQUIRY), assignchartlist);
            if (dashbordchart.isProductwiseinquiry()) {
                casebeen = casedaoimple.getUserRoleIdAndEmployeeId(name);
                String employeeId = String.valueOf(casebeen.getEmployeeId());
                Dcount = dashboarddaoimpl.getDepartmentCount(employeeId, "and C.ASSIGNEEID1", commondaoimpl.getCurentTimesStamp());
                Departmentlist = dashboarddaoimpl.getDepartmentList();
                JSONObject details = new JSONObject();
                for (int i = 0; i < Departmentlist.length(); i++) {
                    JSONObject dlist = Departmentlist.getJSONObject(i);
                    if (i == 0) {
                        details.put("x", dlist.get("Year"));
                    }
                    boolean isdataset = false;
                    for (int j = 0; j < Dcount.length(); j++) {
                        JSONObject ddata = Dcount.getJSONObject(j);
                        if (dlist.getString("Departments").equalsIgnoreCase(ddata.getString("Des"))) {
                            if (Integer.parseInt(ddata.getString("Count")) != 0) {
                                details.put("" + dlist.getString("Departments") + "", "" + ddata.getString("Count") + "");
                                departments.add("" + dlist.getString("Departments") + "");
                                isdataset = true;
                            }
                        }
                    }
                }
                jSONArray4.add(details);
                fulllist.put("Dcontent", jSONArray4);
                fulllist.put("Department", departments);
            }
        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        return fulllist.toString();
    }

    @RequestMapping(value = "/loadOrganizationWiseBarchart", method = RequestMethod.POST)
    public @ResponseBody
    String loadOrganizationWiseBarchart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        String name = (String) session.getAttribute("username");
        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
        Case casebeen = new Case();
        JSONArray objList = new JSONArray();
        JSONArray Dpcount = new JSONArray();
        JSONArray Departmentlist = new JSONArray();
        JSONObject fulllist = new JSONObject();
        List<JSONObject> jSONArray5 = new ArrayList<>();
        List<String> departmentsO = new ArrayList<>();
        try {
            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_PRODUCT_WISE_INQUIRY), assignchartlist);
            if (dashbordchart.isProductwiseinquiry()) {
                Departmentlist = dashboarddaoimpl.getDepartmentList();
                Dpcount = dashboarddaoimpl.getDepartmentCountbyOrganizationwise(commondaoimpl.getCurentTimesStamp());

                JSONObject DOdetails = new JSONObject();
                for (int i = 0; i < Departmentlist.length(); i++) {
                    JSONObject dlistO = Departmentlist.getJSONObject(i);
                    if (i == 0) {
                        DOdetails.put("x", dlistO.get("Year"));
                    }
                    boolean isdataset = false;
                    for (int j = 0; j < Dpcount.length(); j++) {
                        JSONObject dodata = Dpcount.getJSONObject(j);
                        if (dlistO.getString("Departments").equalsIgnoreCase(dodata.getString("Des"))) {
                            if (Integer.parseInt(dodata.getString("Count")) != 0) {
                                DOdetails.put("" + dlistO.getString("Departments") + "", "" + dodata.getString("Count") + "");
                                departmentsO.add("" + dlistO.getString("Departments") + "");
                                isdataset = true;
                            }
                        }
                    }
                }
                jSONArray5.add(DOdetails);
                fulllist.put("DpcontentOrg", jSONArray5);
                fulllist.put("DepartmentOrg", departmentsO);
            }
        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        return fulllist.toString();
    }

//    @RequestMapping(value = "/dashboard/targetGraph", method = RequestMethod.POST)
//    public @ResponseBody
//    String loadTargetGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
//        String data = "";
//        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
//        try {
//            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_TARGET_GRAPH), assignchartlist);
//            if (dashbordchart.isTragetgraph()) {
//                data = dashboardService.getAgentTargetGraph(MasterDataVarList.AFFINITI_TARGETPERIOD_MONTHLY).toString();
//                dashboardService.getBarTargetGarph(1);
//            }
//        } catch (Exception e) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/dashboard/targetBarGraph", method = RequestMethod.POST)
//    public @ResponseBody
//    String loadTargetBarGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
//        String data = "";
//        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
//        try {
//            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_BAR_CHART), assignchartlist);
//            if (dashbordchart.isBarchart()) {
//                data = dashboardService.getBarTargetGarph(1).toString();
//            }
//        } catch (Exception e) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/dashboard/contactleadaccount", method = RequestMethod.POST)
//    public @ResponseBody
//    String loadContactLeadAccountGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
//        String data = "";
//        try {
//            JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
//            DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CONTACKLEAD), assignchartlist);
//            if (dashbordchart.isContacklead()) {
//                data = dashboardService.getUserContactLeadAccountGraph((String) session.getAttribute("username")).toString();
//            }
//
//        } catch (Exception e) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return data;
//    }
//
//    @RequestMapping(value = "/dashboard/leadratio", method = RequestMethod.POST)
//    public @ResponseBody
//    String loadRatioGraph(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
//        String data = "";
//        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
//        DashBordChart dashbordchart = getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist);
//        try {
//            if (dashbordchart.isLeadratio()) {
//                data = dashboardService.getUserSuccessFailLeadRatioGraph((String) session.getAttribute("username")).toString();
//            }
//        } catch (Exception e) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return data;
//    }
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.revenuetargetsetting;

import com.avn.ccl.controller.section.SectionController;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.activitytype.ActivityTypeDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
import com.avn.ccl.daoimpl.branchgroup.BranchGroupDAOImpl;
import com.avn.ccl.daoimpl.employee.EmployeeDAOImpl;
import com.avn.ccl.daoimpl.product.ProductDAOImpl;
import com.avn.ccl.daoimpl.region.RegionDAOImpl;
import com.avn.ccl.daoimpl.revenuetargetsetting.RevenueTargetSetiingDAOImpl;
import com.avn.ccl.daoimpl.targetgroup.TargetGroupDAOImpl;
import com.avn.ccl.daoimpl.targetperiod.TargetPeriodDAOImpl;
import com.avn.ccl.daoimpl.targetsetting.TargetSettingDAOImpl;
import com.avn.ccl.daoimpl.targettype.TargetTypeDAOImpl;
import com.avn.ccl.model.branch.Branch;
import com.avn.ccl.model.region.Region;
import com.avn.ccl.model.target.TargetSetting;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.map.ObjectMapper;
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

/**
 * @Author : ISURU
 * @Document : TargetSettingController
 * @Created on : Jul 1, 2016, 3:12:37 PM
 */
@Controller
public class RevenueTargetSettingController {

    @Autowired
    ProductDAOImpl productDAOImpl;
    @Autowired
    TargetGroupDAOImpl tragetgroupDAOImpl;
    @Autowired
    TargetPeriodDAOImpl targetperiodDAOImpl;
    @Autowired
    CommonDAOImpl commendaoimpl;
    @Autowired
    TargetSettingDAOImpl targetsettingDAOImpl;
    @Autowired
    AuditTraceDAOImpl audittarceDAOImpl;
    @Autowired
    RegionDAOImpl regiondaoimpl;
    @Autowired
    BranchDAOImpl branchdaoimpl;
    @Autowired
    ActivityTypeDAOImpl activitytypedaoimpl;
    @Autowired
    TargetTypeDAOImpl targettypedaoimpl;
    @Autowired
    RevenueTargetSetiingDAOImpl revenuetargetsetting;
    @Autowired
    BranchGroupDAOImpl branchgroupdaoimpl;
    @Autowired
    EmployeeDAOImpl employeedaoimpl;

    @RequestMapping(value = "/revenetarget/setting", method = RequestMethod.GET)
    public String revenueTarget(@ModelAttribute("userolesection") TargetSetting target, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            TargetSetting targetdata = checkPrivilage_btn(target, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_TARGETSETIING_WEDGET_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model);
            model.addAttribute("avn_create", targetdata.isSave_btn());
            model.addAttribute("activitytype", revenuetargetsetting.getActitityTypes());
            model.addAttribute("taget", revenuetargetsetting.getTargetDropdownList());
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "revenuetarget/revenuetarget";
    }

    @RequestMapping(value = "/target/savetabone", method = RequestMethod.POST)
    public @ResponseBody
    String saveTab1(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabone_info = request.getParameter("tab1_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting tabonedata = (TargetSetting) new ObjectMapper().readValue(tabone_info, TargetSetting.class);
            if (tabonedata.getTargetid() == null || tabonedata.getTargetid().isEmpty()) {
                jSONObject.put("TARGETID", revenuetargetsetting.insertTabone(tabonedata, user));
                jSONObject.put("CODE", "SUCCESS");
            } else {
                revenuetargetsetting.updateTabone(tabonedata, user);
                jSONObject.put("TARGETID", tabonedata.getTargetid());
                jSONObject.put("CODE", "SUCCESS");
            }
        } catch (Exception ex) {
            jSONObject.put("MESSAGE", "An error occurred during save");
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jSONObject.toString();
    }

    private TargetSetting checkPrivilage_btn(TargetSetting target, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        target = new TargetSetting();
        target.setSave_btn(true);
        target.setSearch_btn(true);
        target.setEdit_btn(true);
        target.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                target.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                target.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                target.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                target.setView_btn(false);
            }
        }
        return target;
    }

    @RequestMapping(value = "/target/regionlist", method = RequestMethod.POST)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
        if (targetview.isView_btn()) {
            ViewStatus = "disabled";
        } else {
            ViewStatus = "active";
        }
        if (targetedit.isEdit_btn()) {
            UpdateStatus = "disabled";
        } else {
            UpdateStatus = "active";
        }
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
//                chek allrat inste to regional target
                int count = revenuetargetsetting.getRegionalTargetCountByTargetid(parameters.getTargetid());
                if (count == 0) {
                    JSONArray regionlist = revenuetargetsetting.getRegionalArray();
                    TargetSetting targetsetting = new TargetSetting();
                    targetsetting.setTargetnotassignregion(regionlist.toString());
                    targetsetting.setTargetid(parameters.getTargetid());
                    revenuetargetsetting.insertRegionalTarget(targetsetting, user);
                }
//                inste to regiontarget

                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
//                if (Boolean.valueOf(request.getParameter("search"))) {

                iTotalRecords = revenuetargetsetting.getTableDataCount(parameters);
                iTotalDisplayRecords = iTotalRecords;
                if (iTotalRecords > 0) {
                    list = revenuetargetsetting.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                    for (TargetSetting data : list) {
                        JSONObject object = new JSONObject();
                        object.put("branchid", data.getBranchid());
                        object.put("branchdes", data.getBranchdes());
                        object.put("revenue", data.getRevenue());

                        rows.put(object);

                    }
                }
            }
//            } else {
//
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

//    private TargetSetting getParameters(HttpServletRequest request) throws Exception {
//        TargetSetting parameters = new TargetSetting();
//
//        if (request.getParameter("targetid") != null && !request.getParameter("targetid").isEmpty()) {
//            parameters.setTargetid(request.getParameter("targetid"));
//        }
////        if (request.getParameter("regionaltagetid") != null && !request.getParameter("regionaltagetid").isEmpty()) {
////            parameters.setRegionaltargetid(request.getParameter("regionaltagetid"));
////        }
//
//        return parameters;
//    }
    @RequestMapping(value = "/target/sum", method = RequestMethod.POST)
    public @ResponseBody
    String getRegionSUM(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabone_info = request.getParameter("tab1_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        int sum = 0;
        int totalamount = 0;
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabone_info, TargetSetting.class);
            sum = revenuetargetsetting.getRegionalTargetSUMtByTargetid(target.getTargetid());
            totalamount = revenuetargetsetting.getOrganizationTargetAmount(target.getTargetid());

        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        jSONObject.put("sum", sum);
        jSONObject.put("totalamount", totalamount);
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/regionlist", method = RequestMethod.POST)
    public @ResponseBody
    String getRegionList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtwo_info = request.getParameter("tab2_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtwo_info, TargetSetting.class);
            List<Region> list = revenuetargetsetting.getRegionDropDown(target.getTargetid());
            jSONObject.put("regionlist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/activitylist", method = RequestMethod.POST)
    public @ResponseBody
    String getActivityList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtwo_info = request.getParameter("tab2_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtwo_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getActivityTargetDropDown(target.getTargetid());
            jSONObject.put("activitylist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/regionactivitylist", method = RequestMethod.POST)
    public @ResponseBody
    String getRegionActivityList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabthree_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabthree_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getRegionActivityDropDown(target.getTargetid(), target.getRegionid());
            jSONObject.put("activitylist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/gettargetactivitycount", method = RequestMethod.POST)
    public @ResponseBody
    String getTragetActivityCount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtwo_info = request.getParameter("tab2_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtwo_info, TargetSetting.class);
            int count = revenuetargetsetting.getActivityTargetCount(target.getTargetactivityid());
            jSONObject.put("count", count);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/count", method = RequestMethod.POST)
    public @ResponseBody
    String getActivityTargetCount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtwo_info = request.getParameter("tab2_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtwo_info, TargetSetting.class);
            int count = revenuetargetsetting.getActivityCount(target.getActivitytypeid());
            jSONObject.put("count", count);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/datatable/save", method = RequestMethod.POST)
    public @ResponseBody
    String saveDatatable(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String datatable = request.getParameter("datatable_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(datatable, TargetSetting.class);
            jSONObject = revenuetargetsetting.updateRegionalTarget(target, user);
//            //System.out.println(status);
            if (jSONObject.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");

            } else {
                jSONObject.put("errorMsg", "Invalid amount");
            }
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/target/savetabtwo", method = RequestMethod.POST)
    public @ResponseBody
    String saveTabTwo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtwo_info = request.getParameter("tabtwo_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting tabonedata = (TargetSetting) new ObjectMapper().readValue(tabtwo_info, TargetSetting.class);
            revenuetargetsetting.insertRegionalActivity(tabonedata, user);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jSONObject.toString();
    }

    @RequestMapping(value = "load/regiondropdown", method = RequestMethod.POST)
    public @ResponseBody
    String getRegionDropDownList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabthree_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabthree_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getRegionDropDownForTabThree(target.getTargetid());
            jSONObject.put("regionlisttabthree", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/branchlist", method = RequestMethod.POST)
    public @ResponseBody
    String getBrnachlistTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
        if (targetview.isView_btn()) {
            ViewStatus = "disabled";
        } else {
            ViewStatus = "active";
        }
        if (targetedit.isEdit_btn()) {
            UpdateStatus = "disabled";
        } else {
            UpdateStatus = "active";
        }
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));
        parameters.setRegionaltargetid(request.getParameter("regionaltagetid"));
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
//                chek allrat inste to regional target
                int count = revenuetargetsetting.getBranchTargetCountByRegionalTargetid(parameters.getRegionaltargetid());
                if (count == 0) {
                    JSONArray branchlist = revenuetargetsetting.getBranchArray(parameters.getRegionaltargetid());
                    TargetSetting targetsetting = new TargetSetting();
                    targetsetting.setBrachtargetlist(branchlist.toString());
                    targetsetting.setTargetid(parameters.getTargetid());
                    targetsetting.setRegionaltargetid(parameters.getRegionaltargetid());
                    revenuetargetsetting.insertBrnachTarget(targetsetting, user);
                }
//                inste to regiontarget

                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
//                if (Boolean.valueOf(request.getParameter("search"))) {

                iTotalRecords = revenuetargetsetting.getBranchTargetCountByRegionalTargetid(parameters.getRegionaltargetid());
                iTotalDisplayRecords = iTotalRecords;
                if (iTotalRecords > 0) {
                    list = revenuetargetsetting.getBrnachTargetTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                    for (TargetSetting data : list) {
                        JSONObject object = new JSONObject();
                        object.put("branchid", data.getBranchid());
                        object.put("branchdes", data.getBranchdes());
                        object.put("revenue", data.getRevenue());

                        rows.put(object);

                    }
                }
            }
//            } else {
//
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/load/searchbranchlist", method = RequestMethod.POST)
    public @ResponseBody
    String getSearchBrnachlistTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
        if (targetview.isView_btn()) {
            ViewStatus = "disabled";
        } else {
            ViewStatus = "active";
        }
        if (targetedit.isEdit_btn()) {
            UpdateStatus = "disabled";
        } else {
            UpdateStatus = "active";
        }
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
//                chek allrat inste to regional target
                int count = revenuetargetsetting.getSearchBranchTargetCountByRegionalTargetid(parameters.getRegionaltargetid());
//                if (count == 0) {
//                    JSONArray branchlist = revenuetargetsetting.getBranchArray(parameters.getRegionaltargetid());
//                    TargetSetting targetsetting = new TargetSetting();
//                    targetsetting.setBrachtargetlist(branchlist.toString());
//                    targetsetting.setTargetid(parameters.getTargetid());
//                    targetsetting.setRegionaltargetid(parameters.getRegionaltargetid());
//                    revenuetargetsetting.insertBrnachTarget(targetsetting, user);
//                }
//                inste to regiontarget

                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
//                if (Boolean.valueOf(request.getParameter("search"))) {

                iTotalRecords = revenuetargetsetting.getSearchBranchTargetCountByRegionalTargetid(parameters.getTargetid());
                iTotalDisplayRecords = iTotalRecords;
                if (iTotalRecords > 0) {
                    list = revenuetargetsetting.getSearchBrnachTargetTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                    for (TargetSetting data : list) {
                        JSONObject object = new JSONObject();
                        object.put("branchid", data.getBranchid());
                        object.put("branchdes", data.getBranchdes());
                        object.put("revenue", data.getRevenue());

                        rows.put(object);

                    }
                }
            }
//            } else {
//
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/branchtarget/sum", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchSUM(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabthree_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        int sum = 0;
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabthree_info, TargetSetting.class);
            sum = revenuetargetsetting.getBranchTargetSUMtByTargetid(target.getRegionaltargetid());
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        jSONObject.put("sum", sum);
        return jSONObject.toString();
    }

    @RequestMapping(value = "/branchdatatable/save", method = RequestMethod.POST)
    public @ResponseBody
    String saveBranchDatatable(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String datatable = request.getParameter("datatable_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(datatable, TargetSetting.class);
            jSONObject = revenuetargetsetting.updateBrnachTarget(target, user);
            if (jSONObject.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
            } else {
                jSONObject.put("errorMsg", "Invalid amount");
            }
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "load/ractivitytargetlist", method = RequestMethod.POST)
    public @ResponseBody
    String getRegionDropDownBytargetidList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabthree_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabthree_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getRegionActivityTargetList(target.getTargetid());
            jSONObject.put("ractivitytargetlist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/loadbranch/count", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchActivityTargetCount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtwo_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtwo_info, TargetSetting.class);
            int count = revenuetargetsetting.getRegionActivityCount(target.getRegionalactivitytargetid());
            jSONObject.put("count", count);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "getbranchlist", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabthree_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabthree_info, TargetSetting.class);
            List<Branch> list = revenuetargetsetting.getBranchList(target.getRegionalactivitytargetid());
            jSONObject.put("branchlist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "target/savetabthree", method = RequestMethod.POST)
    public @ResponseBody
    String saveTabThree(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabthree_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting tabonedata = (TargetSetting) new ObjectMapper().readValue(tabthree_info, TargetSetting.class);
            revenuetargetsetting.insertTabone(tabonedata, user);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jSONObject.toString();
    }

    @RequestMapping(value = "/loadbranchregionlist", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchRegionList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabfour_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getRegionBranchList(target.getRegionaltargetid());
            jSONObject.put("regionbranchlist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/loadgrouplist", method = RequestMethod.POST)
    public @ResponseBody
    String getGroupList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabfour_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getGroupList(target.getTargetid());
            jSONObject.put("branchgrouplist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/branchtarget/amount", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchTargetAmount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            int amout = revenuetargetsetting.getBranchTargetAmout(target.getBrnachtargetid());
            jSONObject.put("amount", String.valueOf(amout));
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/savebarnchgrouprevenetdata", method = RequestMethod.POST)
    public @ResponseBody
    String inserBranchGroupRevenueTarget(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            JSONObject json = revenuetargetsetting.insertBranchGroupRevenue(target, user);
            if (json.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("remaning", json.getInt("remaning"));
                jSONObject.put("RMESSAGE", "Remaining amount to be shared among the Branches of current Region is");
            } else {
                jSONObject.put("MESSAGE", "Warning!  ");
                jSONObject.put("RMESSAGE", "Invalid amount");
            }

        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabeldatabranchgroupRevenuetarget", method = RequestMethod.POST)
    public @ResponseBody
    String getTabelDataBranchGroupRevenueTarget(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            revenuetargetsetting.insertBranchGroupRevenue(target, user);
//            jSONObject.put("amount", amout);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/branchgrouprevenue", method = RequestMethod.POST)
    public @ResponseBody
    String getBrnachGroupRevenueTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));
        parameters.setBrnachtargetid(request.getParameter("brnachtargetid"));
        parameters.setBranchgroupid(request.getParameter("branchgroupid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getBranchGroupRevenueTargetCount(parameters);
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getBranchGroupRevenueTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("brnachtargetid", data.getBrnachtargetid());
                    object.put("brnachtargetdes", data.getBrnachtargetdes());
                    object.put("branchgroupid", data.getBranchgroupid());
                    object.put("branchgroupdes", data.getBranchgroupdes());
                    DecimalFormat df = new DecimalFormat("#,###");
                    //System.out.println(df.format(new BigDecimal(data.getRevenue())));

                    object.put("revenue", String.valueOf(df.format(new BigDecimal(data.getRevenue()))));
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/load/searchbranchgrouprevenue", method = RequestMethod.POST)
    public @ResponseBody
    String getSearchBrnachGroupRevenueTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));
        parameters.setBrnachtargetid(request.getParameter("brnachtargetid"));
        parameters.setBranchgroupid(request.getParameter("branchgroupid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getSearchBranchGroupRevenueTargetCount(parameters);
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getSearchBranchGroupRevenueTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("brnachtargetid", data.getBrnachtargetid());
                    object.put("brnachtargetdes", data.getBrnachtargetdes());
                    object.put("branchgroupid", data.getBranchgroupid());
                    object.put("branchgroupdes", data.getBranchgroupdes());
                    DecimalFormat df = new DecimalFormat("#,###");
                    object.put("revenue", df.format(new BigDecimal(data.getRevenue())));
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/loadbranchregiongroup", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchRegionGroup(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabfour_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getRegionBranchGroupList(target.getTargetid());
            jSONObject.put("regionbranchgrouplist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/loadbranchgroupmemberregion", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchGroupMemberRegion(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabfour_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getBranchGroupMemberRegionList(target.getTargetid());
            jSONObject.put("loadbranchgroupmemberregion", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/loadbranchgroupmemberbranch", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchGroupMemberBranch(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabfour_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getBranchGroupMemberBranchList(target.getRegionaltargetid());
            jSONObject.put("loadbranchgroupmemberbranch", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/loadbranchgroupmembergroup", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchGroupMemberGroup(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabfour_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getBranchGroupMemberGroupList(target.getBrnachtargetid());
            jSONObject.put("regionbranchgrouplist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

//    @RequestMapping(value = "/branchgrouprevenuetarget", method = RequestMethod.POST)
//    public @ResponseBody
//    String getBranchGroupRevenyeTarget(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
//        String tabfour_info = request.getParameter("tab4_info");
//        JSONObject jSONObject = new JSONObject();
//        String user = (String) session.getAttribute("username");
//        try {
//            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabfour_info, TargetSetting.class);
//            List<TargetSetting> list = revenuetargetsetting.getRegionBranchGroupList(target.getTargetid());
//            jSONObject.put("regionbranchgrouplist", list);
//        } catch (Exception ex) {
//            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return jSONObject.toString();
//    }
    @RequestMapping(value = "/branchgrouprevenuamount", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchGroupRevenueAmount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            int amout = revenuetargetsetting.getBranchGroupRevenueTargetAmout(target.getBranchgrouprevenuetargetid());
            List<TargetSetting> list = revenuetargetsetting.getBranchGroupMemberid(target.getBranchgrouprevenuetargetid());
            jSONObject.put("amount", amout);
            jSONObject.put("emplist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/deletetablerow", method = RequestMethod.POST)
    public @ResponseBody
    String deletetablerow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            revenuetargetsetting.deleteRow(target);
            jSONObject.put("CODE", "SUCCESS");
        } catch (SQLIntegrityConstraintViolationException sqlicve) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Warning! Record cannot be deleted. ");
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, sqlicve);
        } catch (SQLException sqle) {
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/savebarnchgroupmemberdata", method = RequestMethod.POST)
    public @ResponseBody
    String inserBranchGroupMenberTarget(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            JSONObject json = revenuetargetsetting.insertBranchGroupMemberTarget(target, user);
            if (json.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("remaning", json.getInt("remaning"));
                jSONObject.put("RMESSAGE", "You have remaining amout is");
            } else {
                jSONObject.put("MESSAGE", "Warning!  ");
                jSONObject.put("RMESSAGE", "Invalid amount");
            }
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/branchgroupmemberdetail", method = RequestMethod.POST)
    public @ResponseBody
    String getBrnachGroupMemberTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setBranchgrouprevenuetargetid(request.getParameter("branchgrouprevenuetargetid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getBranchGroupMemberTargetCount(parameters.getBranchgrouprevenuetargetid());
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getBranchGroupMemberTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("branchgroupmembertargetid", data.getBranchgroupmembertargetid());
                    object.put("branchgrouprevenuetargetid", data.getBranchgrouprevenuetargetid());
                    object.put("branchgrouprevenuetargetdes", data.getBranchgrouprevenuetargetdes());
                    object.put("branchgroupmemberid", data.getBranchgroupmemberid());
                    object.put("empname", data.getEmpname());
                    DecimalFormat df = new DecimalFormat("#,###");
                    //System.out.println(df.format(new BigDecimal(data.getRevenue())));

                    object.put("revenue", String.valueOf(df.format(new BigDecimal(data.getRevenue()))));

                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/load/searchbranchgroupmemberdetail", method = RequestMethod.POST)
    public @ResponseBody
    String getSearchBrnachGroupMemberTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getSearchBranchGroupMemberTargetCount(parameters.getTargetid());
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getSearchBranchGroupMemberTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("branchgroupmembertargetid", data.getBranchgroupmembertargetid());
                    object.put("branchgroupmembertargetdes", data.getBranchgroupmembertargetdes());
                    object.put("branchgroupid", data.getBranchgroupid());
                    object.put("empname", data.getEmpname());
                    DecimalFormat df = new DecimalFormat("#,###");
                    object.put("revenue", df.format(new BigDecimal(data.getRevenue())));
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/deletetabelsixrow", method = RequestMethod.POST)
    public @ResponseBody
    String deletetabltwoerow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            revenuetargetsetting.deleteDatatabelsix(target);
            jSONObject.put("CODE", "SUCCESS");
        } catch (SQLIntegrityConstraintViolationException sqlicve) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Warning! Record cannot be deleted. The Branch Group amount you are trying to delete is already divided among related branch members");
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, sqlicve);
        } catch (SQLException sqle) {
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/regionbranchactivity", method = RequestMethod.POST)
    public @ResponseBody
    String getRegetBranchActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getRegionBranchActivity(target.getTargetid());
            List<TargetSetting> emplist = revenuetargetsetting.getBranchGroupMemberid(target.getTargetid());

            jSONObject.put("regionbrachactivity", list);
            jSONObject.put("emplist", emplist);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/branchactivitycount", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchActivityCount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            int count = revenuetargetsetting.getBranchActivityCount(target.getBranchactivitytargetid());
            jSONObject.put("count", count);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/savebrangroupactivity", method = RequestMethod.POST)
    public @ResponseBody
    String saveBranGroupaAtivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            revenuetargetsetting.insertBranchGroupActivityTarget(target, user);
//            jSONObject.put("amount", amout);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/branchgroupactivitydetail", method = RequestMethod.POST)
    public @ResponseBody
    String getBrnachGroupActivityTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setBranchactivitytargetid(request.getParameter("branchactivitytargetid"));
        parameters.setBranchgroupid(request.getParameter("branchgroupid"));
        parameters.setTargetid(request.getParameter("targetid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }

            int count = revenuetargetsetting.getTableDataCountInBranchGroupActivity(parameters.getBranchactivitytargetid());
            if (count == 0) {
                List<TargetSetting> list2 = revenuetargetsetting.getGroupList(parameters.getTargetid());
                revenuetargetsetting.insertBrnachGroupActivity(parameters, user, list2);
            }
            iTotalRecords = revenuetargetsetting.getBranchGroupActivityCount(parameters.getBranchactivitytargetid(), parameters.getBranchgroupid());
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getBranchGroupActivityTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("branchactivitytargetid", data.getBranchactivitytargetid());
                    object.put("branchgroupactivitytargetid", data.getBranchgroupactivitytargeid());
                    object.put("branchactivitytargetdes", data.getBranchactivitytargetdes());
                    object.put("branchgroupid", data.getBranchgroupid());
                    object.put("branchgroupdes", data.getBranchgroupdes());
                    DecimalFormat df = new DecimalFormat("#,###");
                    object.put("count", String.valueOf(df.format(new BigDecimal(data.getCount()))));
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/load/searchbranchgroupactivitydetail", method = RequestMethod.POST)
    public @ResponseBody
    String getSearchBrnachGroupActivityTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));
        parameters.setBranchgroupid(request.getParameter("branchgroupid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getSearchBranchGroupActivityCount(parameters.getTargetid(), parameters.getBranchgroupid());
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getSearchBranchGroupActivityTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("branchactivitytargetid", data.getBranchactivitytargetid());
                    object.put("branchactivitytargetdes", data.getBranchactivitytargetdes());
                    object.put("branchgroupid", data.getBranchgroupid());
                    object.put("branchgroupdes", data.getBranchgroupdes());
                    DecimalFormat df = new DecimalFormat("#,###");
                    object.put("count", df.format(new BigDecimal(data.getCount())));
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/deletetableeoghtrow", method = RequestMethod.POST)
    public @ResponseBody
    String deletetableeightrow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            revenuetargetsetting.dataTableeightdeleteRow(target);
            jSONObject.put("CODE", "SUCCESS");
        } catch (SQLIntegrityConstraintViolationException sqlicve) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Warning! Record cannot be deleted. The Brnach group activity count you are trying to delete is already divided among related branch group member activity");
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, sqlicve);
        } catch (SQLException sqle) {
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/regionbranchactivitygroup", method = RequestMethod.POST)
    public @ResponseBody
    String getRegetBranchActivityGroup(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getRegionBranchGroupActivity(target.getTargetid());
            jSONObject.put("regionbranchgroupactivity", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/getbrnachgroupmember", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchGroupMember(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getBranchGroupMemberid(target.getBranchgrouprevenuetargetid());
            int count = revenuetargetsetting.getBranchGroupActivityTargetCount(target.getBranchgroupactivitytargeid());
            jSONObject.put("branchgroupmember", list);
            jSONObject.put("count", count);

        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/getbrnachgroupmemberbybgmatid", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchGroupMemberByBatid(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getBranchGroupMemberidByBGMTID(target.getBranchgroupactivitytargeid());
            int count = revenuetargetsetting.getBranchGroupActivityTargetCount(target.getBranchgroupactivitytargeid());
            jSONObject.put("count", count);
            jSONObject.put("branchgroupmember", list);

        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/savebrangroupmemberactivity", method = RequestMethod.POST)
    public @ResponseBody
    String saveBranGroupaMemberAtivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            revenuetargetsetting.insertBranchGroupMemberActivityTarget(target, user);
//            jSONObject.put("amount", amout);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        model.addAttribute("productList", productDAOImpl.getProductDropdownList());
        model.addAttribute("sectionList", targetperiodDAOImpl.getTargetPeriodList());
        model.addAttribute("userRoleList", tragetgroupDAOImpl.getTargetGroupList());
        model.addAttribute("activitytype", activitytypedaoimpl.getActivityTypeDropdownList());
        model.addAttribute("targettype", targettypedaoimpl.getTargetTypeDropdownList());
//        model.addAttribute("branchgroup", branchgroupdaoimpl.getBranchGroupDropdownList());
        model.addAttribute("emoloyeelist", employeedaoimpl.getEmployeeDropdownList());
    }

    @RequestMapping(value = "/savedatatbeltwo", method = RequestMethod.POST)
    public @ResponseBody
    String saveRegionActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtwo_info = request.getParameter("tab2_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtwo_info, TargetSetting.class);
            revenuetargetsetting.insertRegionalActivity(target, user);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/regionactivity", method = RequestMethod.POST)
    public @ResponseBody
    String getRegionActivityTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setRegionid(request.getParameter("regionid"));
        parameters.setTargetid(request.getParameter("targetid"));
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getRegionActivityByRegionCounts(parameters.getRegionid(), parameters.getTargetid());
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getRegionalActivityByRegion(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
//                    object.put("regionid", data.getRegionid());
                    object.put("regiondes", data.getRegiondes());
                    object.put("callcount", data.getCallcount());
                    object.put("callactivityid", data.getCallactivityid());
                    object.put("proposalscount", data.getProposalscount());
                    object.put("proposalsactivityid", data.getProposalsactivityid());
                    object.put("visitspresentationscount", data.getVisitspresentationscount());
                    object.put("vpresentationsactivityid", data.getVpresentationsactivityid());
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/load/searchregionactivity", method = RequestMethod.POST)
    public @ResponseBody
    String getSearchRegionActivityTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setRegionid(request.getParameter("regionid"));
        parameters.setTargetid(request.getParameter("targetid"));
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getSearchRegionActivityCounts(parameters.getTargetid());
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getSearchRegionalActivityTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("regionid", data.getRegionid());
                    object.put("regiondes", data.getRegiondes());
                    object.put("targetactivityid", data.getTargetactivityid());
                    object.put("targetactivitydes", data.getTargetactivitydes());
                    DecimalFormat df = new DecimalFormat("#,###");
                    object.put("count", df.format(new BigDecimal(data.getCount())));
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/deletetablethreerow", method = RequestMethod.POST)
    public @ResponseBody
    String deletetablthreerow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabttwo_info = request.getParameter("tab2_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabttwo_info, TargetSetting.class);
            revenuetargetsetting.deletedatatablethreeRow(target);
            jSONObject.put("CODE", "SUCCESS");
        } catch (SQLIntegrityConstraintViolationException sqlicve) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Warning! Record cannot be deleted. The Region Activity count you are trying to delete is already divided among related branches.");
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, sqlicve);
        } catch (SQLException sqle) {
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/updatedatatablethereerow", method = RequestMethod.POST)
    public @ResponseBody
    String updatetablthreerow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabttwo_info = request.getParameter("tab2_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabttwo_info, TargetSetting.class);
            revenuetargetsetting.updatedatatablethreeRow(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/loadregiontargetamount", method = RequestMethod.POST)
    public @ResponseBody
    String getRegionTargetAmount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabthree_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabthree_info, TargetSetting.class);
            int amount = revenuetargetsetting.getRegionTargetAmount(target);
            jSONObject.put("amount", amount);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/savedatatblefive", method = RequestMethod.POST)
    public @ResponseBody
    String saveBranchActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabthree_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabthree_info, TargetSetting.class);
            revenuetargetsetting.insertBranchActivity(target, user);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/branchactivity", method = RequestMethod.POST)
    public @ResponseBody
    String getBranchActivityTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));
        parameters.setRegionid(request.getParameter("regionid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getBranchActivityCounts(parameters.getRegionid(), parameters.getTargetid());
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.geBranchActivityTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("branchdes", data.getBranchdes());
                    object.put("callcount", data.getCallcount());
                    object.put("callactivityid", data.getCallactivityid());
                    object.put("proposalscount", data.getProposalscount());
                    object.put("proposalsactivityid", data.getProposalsactivityid());
                    object.put("visitspresentationscount", data.getVisitspresentationscount());
                    object.put("vpresentationsactivityid", data.getVpresentationsactivityid());
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/load/searchbranchactivity", method = RequestMethod.POST)
    public @ResponseBody
    String getSearchBranchActivityTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getSearchBranchActivityCounts(parameters.getTargetid());
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.geSearchBranchActivityTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("regionalactivitytargetid", data.getRegionalactivitytargetid());
                    object.put("regionalactivitytargetdes", data.getRegionalactivitytargetdes());
                    object.put("branchid", data.getBranchid());
                    object.put("branchdes", data.getBranchdes());
                    DecimalFormat df = new DecimalFormat("#,###");
                    object.put("count", df.format(new BigDecimal(data.getCount())));
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/updatedatatabletfiverow", method = RequestMethod.POST)
    public @ResponseBody
    String updatetablfiverow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtthree_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtthree_info, TargetSetting.class);
            revenuetargetsetting.updatedatatablefiveRow(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/deletetablefiverow", method = RequestMethod.POST)
    public @ResponseBody
    String deletetablfiverow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtthree_info = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtthree_info, TargetSetting.class);
            revenuetargetsetting.deletedatatablefiveRow(target);
            jSONObject.put("CODE", "SUCCESS");
        } catch (SQLIntegrityConstraintViolationException sqlicve) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Warning! Record cannot be deleted. The Branch Activity count you are trying to delete is already divided among related team activity.");
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, sqlicve);
        } catch (SQLException sqle) {
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/updatebarnchgrouprevenetdata", method = RequestMethod.POST)
    public @ResponseBody
    String inserBranchGroupMenberTargetUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            JSONObject json = revenuetargetsetting.updateBranchGroupMemberTarget(target);
            if (json.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("remaning", json.getInt("remaning"));
                jSONObject.put("RMESSAGE", "Remaining amount to be shared among the Branches of current Region is");
            } else {
                jSONObject.put("MESSAGE", "Warning!  ");
                jSONObject.put("RMESSAGE", "Invalid amount");
            }
//
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/updatebarnchgroupmemberdata", method = RequestMethod.POST)
    public @ResponseBody
    String updateBranchGroupMenberTargetUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab4_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            JSONObject json = revenuetargetsetting.updatedatatableSevenRow(target);
            if (json.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("remaning", json.getInt("remaning"));
                jSONObject.put("RMESSAGE", "You have remaining amout is");
            } else {
                jSONObject.put("MESSAGE", "Warning!  ");
                jSONObject.put("RMESSAGE", "Invalid amount");
            }
//            jSONObject.put("amount", amout);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/updatebrangroupactivity", method = RequestMethod.POST)
    public @ResponseBody
    String updateBrangroupActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            revenuetargetsetting.updatedatatableEightRow(target);
//            jSONObject.put("amount", amout);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/branchgroupactivitymemberdetail", method = RequestMethod.POST)
    public @ResponseBody
    String getBrnachGroupActivityMemberData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setBranchgroumemberactivityid(request.getParameter("branchgroupactivitytargeid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//

        if (true) {
        }

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getBranchGroupMemberActivityCount(parameters.getBranchgroumemberactivityid());
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getBranchGroupMemberActivityTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("branchgroupactivitytarhetid", data.getBranchgroupactivitytargeid());
                    object.put("branchgroumemberactivityid", data.getBranchgroumemberactivityid());
                    object.put("branchgroupactivitydes", data.getBranchgroupactivitytargetdes());
                    object.put("branchgroupmemberid", data.getBranchgroupmemberid());
                    object.put("branchgroupmemberdes", data.getBranchgroupmemberdes());
                    DecimalFormat df = new DecimalFormat("#,###");
                    object.put("count", df.format(new BigDecimal(data.getCount())));
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/load/searchbranchgroupactivitymemberdetail", method = RequestMethod.POST)
    public @ResponseBody
    String getSearchBrnachGroupActivityMemberData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getSearchBranchGroupMemberActivityCount(parameters.getTargetid());
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getSearchBranchGroupMemberActivityTargeData(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("branchgroumemberactivityid", data.getBranchactivitytargetid());
                    object.put("branchgroumemberactivitydes", data.getBranchactivitytargetdes());
                    object.put("branchgroupmemberid", data.getBranchgroupmemberid());
                    object.put("branchgroupmemberdes", data.getBranchgroupmemberdes());
                    DecimalFormat df = new DecimalFormat("#,###");
                    object.put("count", df.format(new BigDecimal(data.getCount())));
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/deletetableninerow", method = RequestMethod.POST)
    public @ResponseBody
    String deletetableninerow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            revenuetargetsetting.dataTableninedeleteRow(target);
            jSONObject.put("CODE", "SUCCESS");
        } catch (SQLIntegrityConstraintViolationException sqlicve) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Warning! Record cannot be deleted. ");
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, sqlicve);
        } catch (SQLException sqle) {
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/updatebranchmemberactivity", method = RequestMethod.POST)
    public @ResponseBody
    String updatebranchmemberactivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            revenuetargetsetting.updateBranchMemberActivity(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/revenetarget/view", method = RequestMethod.GET)
    public String revenueTargetView(@ModelAttribute("userolesection") TargetSetting target, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            TargetSetting targetdata = checkPrivilage_btn(target, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_TARGETSETIING_WEDGET_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model);
            model.addAttribute("avn_create", targetdata.isSave_btn());
            model.addAttribute("activitytype", revenuetargetsetting.getActitityTypes());
            model.addAttribute("taget", revenuetargetsetting.getTargetDropdownList());
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "revenuetarget/revenuetargetview";
    }

    @RequestMapping(value = "/target/searched", method = RequestMethod.POST)
    public @ResponseBody
    String getTableDatasearch(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
        if (targetview.isView_btn()) {
            ViewStatus = "disabled";
        } else {
            ViewStatus = "active";
        }
        if (targetedit.isEdit_btn()) {
            UpdateStatus = "disabled";
        } else {
            UpdateStatus = "active";
        }
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
                if (Boolean.valueOf(request.getParameter("search"))) {

                    TargetSetting parameters = this.getParameters(request);

                    iTotalRecords = revenuetargetsetting.getSearchTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = revenuetargetsetting.getSearchTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (TargetSetting data : list) {
                            JSONObject object = new JSONObject();
                            object.put("targetid", data.getTargetid());
                            object.put("tragetdes", data.getTragetdes());
                            object.put("productdes", data.getProductdes());
                            DecimalFormat df = new DecimalFormat("#,###");
                            object.put("revenue", df.format(new BigDecimal(data.getRevenue())));
                            object.put("tragetgroupdes", data.getTragetgroupdes());
                            object.put("targettypedes", data.getTargettypedes());
                            object.put("activitytypedes", data.getActivitytypedes());
                            object.put("targetperioddes", data.getTargetperioddes());
                            object.put("targetstartdate", data.getTargetstartdate());
                            object.put("targetenddate", data.getTargetenddate());
                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/target/view?targetId=" + data.getTargetid() + "' class=" + ViewStatus + " ><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/target/update?targetId=" + data.getTargetid() + "'class=" + UpdateStatus + " ><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                    }
                }
            } else {

            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private TargetSetting getParameters(HttpServletRequest request) throws Exception {
        TargetSetting parameters = new TargetSetting();

        if (request.getParameter("searchoption") != null && !request.getParameter("searchoption").isEmpty()) {
            parameters.setSearchoption(request.getParameter("searchoption"));
        }
        if (request.getParameter("input") != null && !request.getParameter("input").isEmpty()) {
            parameters.setInput(request.getParameter("input"));
        }

        return parameters;
    }

    @RequestMapping(value = "/target/search", method = RequestMethod.GET)
    public String sectionSearchPageLoad(@ModelAttribute("targetsearch") TargetSetting target, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            TargetSetting targetdata = checkPrivilage_btn(target, String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_SECTION_SUBSECTION_ID), objList);
            model.addAttribute("avn_create", targetdata.isSave_btn());
        } catch (Exception e) {
        }
        return "revenuetarget/revenetargetsettingsearch";
    }

//    @RequestMapping(value = "/target/update", method = RequestMethod.GET)
//    public String sectionUpdatePageLoad(@ModelAttribute("userolesection") TargetSetting target, @RequestParam(value = "targetId", required = false) String targetId, ModelMap model, HttpSession session) {
//        try {
//            this.setCreateViewComponenets(model);
//            model.addAttribute("activitytype", revenuetargetsetting.getActitityType(targetId));
//            model.addAttribute("taget", revenuetargetsetting.getTargetDropdownList());
//            TargetSetting targetData = revenuetargetsetting.getTargetByTargetId(targetId);
//            model.addAttribute("userolesection", targetData);
//        } catch (Exception e) {
//        }
//        return "revenuetarget/revenuetargetsettingupdate";
//    }
    @RequestMapping(value = "/target/view", method = RequestMethod.GET)
    public String sectionViewPageLoad(@ModelAttribute("userolesection") TargetSetting target, @RequestParam(value = "targetId", required = false) String targetId, ModelMap model, HttpSession session) {
        try {
            this.setCreateViewComponenets(model);
            model.addAttribute("activitytype", revenuetargetsetting.getActitityType(targetId));
            model.addAttribute("taget", revenuetargetsetting.getTargetDropdownList());
            TargetSetting targetData = revenuetargetsetting.getTargetByTargetId(targetId);
            model.addAttribute("userolesection", targetData);
        } catch (Exception e) {
        }
        return "revenuetarget/newreveneutargetview";
    }

    @RequestMapping(value = "/target/update", method = RequestMethod.GET)
    public String updateTab1(@ModelAttribute("userolesection") TargetSetting target, @RequestParam(value = "targetId", required = false) String targetId, ModelMap model, HttpSession session) {
        try {
            this.setCreateViewComponenets(model);
            model.addAttribute("newactivitytype", revenuetargetsetting.getActitityType(targetId));

            model.addAttribute("taget", revenuetargetsetting.getTargetDropdownList());
            TargetSetting targetData = revenuetargetsetting.getTargetByTargetId(targetId);
            int minregionid = revenuetargetsetting.getMINRegionid(targetId);
            targetData.setRegionalid(String.valueOf(minregionid));
            model.addAttribute("minregionid", minregionid);
            model.addAttribute("userolesection", targetData);
        } catch (Exception e) {
        }
        return "revenuetarget/revenuetargetsettingupdate";
    }

    @RequestMapping(value = "/update/targetsetting", method = RequestMethod.GET)
    String updateTargetsetting(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            revenuetargetsetting.updateTarget(target, user);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/targetdropdown", method = RequestMethod.POST)
    public @ResponseBody
    String dropdownTarget(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            List<TargetSetting> targetsetting = revenuetargetsetting.getTarget();
            jSONObject.put("target", targetsetting);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabfourvalidarion", method = RequestMethod.POST)
    public @ResponseBody
    String tabfourvalidation(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        boolean status = true;
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> branchList = revenuetargetsetting.getRegionBranchListByRegionTragetid(target.getTargetid());
            JSONArray array = new JSONArray();
            for (int i = 0; i < branchList.size(); i++) {
                TargetSetting targetdata = branchList.get(i);
                int sum = revenuetargetsetting.getSumBranchGroupRevenue(targetdata.getBrnachtargetid());
                if (!targetdata.getRevenue().contentEquals(String.valueOf(sum))) {
                    array.put(targetdata.getBranchdes());
                    //System.out.println(targetdata.getBranchdes());
                    status = false;
                    jSONObject.put("MESSAGE", "Some Branch Group Targets you have set are not equal to the Total Target");
                    jSONObject.put("CODE", "ERROR");
                } else {
                    jSONObject.put("CODE", "SUCCESS");
                }
            }
            jSONObject.put("arrayList", array);
            jSONObject.put("ststus", status);

        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabfourvalidarionmembertarget", method = RequestMethod.POST)
    public @ResponseBody
    String tabfourvalidationMembertarget(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        boolean status = true;
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> branchList = revenuetargetsetting.getBranchGroupRevenueTargetList(target.getTargetid());
            JSONArray array = new JSONArray();
            for (int i = 0; i < branchList.size(); i++) {
                TargetSetting targetdata = branchList.get(i);
                int sum = revenuetargetsetting.getSumBranchGroup(targetdata.getBranchgrouprevenuetargetid());
                if (!targetdata.getRevenue().contentEquals(String.valueOf(sum))) {
                    array.put(targetdata.getBranchgrouprevenuetargetdes());
                    //System.out.println(targetdata.getBranchgrouprevenuetargetdes());
                    status = false;
                    jSONObject.put("MESSAGE", "Some Group Member targets are not equal to the total target amount of related Group");
                    jSONObject.put("CODE", "ERROR");
                } else {
                    jSONObject.put("CODE", "SUCCESS");
                }
            }
            jSONObject.put("arrayList", array);
            jSONObject.put("ststus", status);

        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabfiveregionlist", method = RequestMethod.POST)
    public @ResponseBody
    String getTabfiveRegionList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getTabfiveRegionList(target.getTargetid());
            jSONObject.put("regionlist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabfiveregionactivitylist", method = RequestMethod.POST)
    public @ResponseBody
    String getTabfiveRegionActivityList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getTabfiveRegionActivityList(target.getTargetid(), target.getRegionid());
            jSONObject.put("activitylist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabfiveregionactivitybranchlist", method = RequestMethod.POST)
    public @ResponseBody
    String getTabfiveRegionActivityBranchList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getTabfiveRegionActivityBranchList(target.getBranchactivitytargetid());
            jSONObject.put("barnchlist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabfivememberregionalist", method = RequestMethod.POST)
    public @ResponseBody
    String getTabfiveMemberRegionActivityBranchList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getTabfiveMemberActivityRegionList(target.getTargetid());
            jSONObject.put("regionlist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabfivememeberregionactivitylist", method = RequestMethod.POST)
    public @ResponseBody
    String getTabfiveMemberActivityList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getTabfiveMemberRegionActivityList(target.getTargetid(), target.getRegionid());
            jSONObject.put("activitylist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabfivememeberregionabranchlist", method = RequestMethod.POST)
    public @ResponseBody
    String getTabfiveMemberActivityBranchList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getTabfiveRegionBranchList(target.getRegionalactivitytargetid());
            jSONObject.put("barnchlist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabfivememeberregionabranchgrouplist", method = RequestMethod.POST)
    public @ResponseBody
    String getTabfiveMemberActivityBranchGroupList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getTabfiveRegionBranchGroupList(target.getBranchactivitytargetid());
            jSONObject.put("barnchgrouplist", list);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/tabthreevalidation", method = RequestMethod.POST)
    public @ResponseBody
    String getTabThreeValidation(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfive_info = request.getParameter("tab5_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        boolean status = true;
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfive_info, TargetSetting.class);
            List<TargetSetting> list = revenuetargetsetting.getRegionDropDownForTabThree(target.getTargetid());
            JSONArray array = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                TargetSetting targetdata = list.get(i);
                int sum = revenuetargetsetting.getSumBranchRevenue(targetdata.getRegionaltargetid());
                if (!targetdata.getRevenue().contentEquals(String.valueOf(sum))) {
//                    array.put(targetdata.getBranchdes());
//                    //System.out.println(targetdata.getBranchdes());
                    status = false;
                    jSONObject.put("MESSAGE", "Some Branch Group Targets you have set are not equal to the Total Target Amount of related branch");
                    jSONObject.put("CODE", "ERROR");
                } else {
                    jSONObject.put("CODE", "SUCCESS");
                }
            }
            jSONObject.put("arrayList", array);
            jSONObject.put("ststus", status);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/load/teamrevenue", method = RequestMethod.POST)
    public @ResponseBody
    String getBrnachGroupRevenue(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        String user = (String) session.getAttribute("username");
        TargetSetting targetdata = new TargetSetting();
        TargetSetting targetview = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID), objList);
        TargetSetting targetedit = checkPrivilage_btn(targetdata, String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID), objList);
        List<TargetSetting> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        TargetSetting parameters = new TargetSetting();
        parameters.setTargetid(request.getParameter("targetid"));
        parameters.setBrnachtargetid(request.getParameter("brnachtargetid"));
        parameters.setBranchgroupid(request.getParameter("branchgroupid"));
        List<TargetSetting> regionlist = revenuetargetsetting.getRegionDropDownForTabThree(parameters.getTargetid());
        List<TargetSetting> Grouplist = revenuetargetsetting.getGroupList(parameters.getTargetid());

        int count = revenuetargetsetting.getBranchGroupRevenueCountByTargetid(parameters.getTargetid());
        if (count == 0) {
            for (int i = 0; i < regionlist.size(); i++) {
                TargetSetting regiondata = regionlist.get(i);
                List<TargetSetting> branchlist = revenuetargetsetting.getRegionBranchList(regiondata.getRegionaltargetid());
                revenuetargetsetting.insertBranchGroupRevenueTarget(branchlist, Grouplist, user);
            }

        }

        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)
//
        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_TARGET_SETTING_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
//            if (privilage) {
            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }
            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }
            iTotalRecords = revenuetargetsetting.getBranchGroupRevenueCount(parameters);
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = revenuetargetsetting.getBranchGroupRevenue(parameters, param.iDisplayLength, param.iDisplayStart);
                for (TargetSetting data : list) {
                    JSONObject object = new JSONObject();
                    object.put("branchgrouptargetid", data.getBranchgrouptargetid());
                    object.put("brnachtargetid", data.getBrnachtargetid());
                    object.put("brnachtargetdes", data.getBrnachtargetdes());
                    object.put("branchgroupid", data.getBranchgroupid());
                    object.put("branchgroupdes", data.getBranchgroupdes());
                    DecimalFormat df = new DecimalFormat("#,###");
                    //System.out.println(df.format(new BigDecimal(data.getRevenue())));

                    object.put("revenue", String.valueOf(df.format(new BigDecimal(data.getRevenue()))));
                    String action = "<div class=\"row\"> "
                            + "<div class=\"col-xs-3\"><a href='#' id='" + data.getBranchgrouptargetid() + "'  class=" + "true" + " style=\"padding-left:15px;\" ><i class=\"fa fa-fw fa-money\" title=\"Add Member Target\"></i></a></div>"
                            + "</div>";
                    object.put("action", action);
                    rows.put(object);
                }
            }
//            }

        } catch (Exception e) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/datatbelthreecolums", method = RequestMethod.POST)
    public @ResponseBody
    String datatablethreecolum(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab2_info");
        JSONArray jSONArray = new JSONArray();
        String user = (String) session.getAttribute("username");
        JSONArray activity = revenuetargetsetting.getActitityTypes();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("title", "Region Name");
            jSONArray.put(jSONObject);
            for (int i = 0; i < activity.length(); i++) {
                JSONArray arrayitem = activity.optJSONArray(i);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("title", arrayitem.get(1));
                jSONArray.put(jSONObject2);
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("title", "Action");
            jSONArray.put(jSONObject3);

        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONArray.toString();
    }

    @RequestMapping(value = "/updatebranchgrouprevenue", method = RequestMethod.POST)
    public @ResponseBody
    String updateBranchGroupRevenue(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("datatable_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            JSONObject json = revenuetargetsetting.updateBrnachGroupTarget(target, user);
            if (json.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("remaning", json.getInt("remaning"));
                jSONObject.put("sum", json.getInt("sum"));
                jSONObject.put("RMESSAGE", "Remaining amount to be shared among the Branches of current Region is");
            } else {
                jSONObject.put("MESSAGE", "Warning!  ");
                jSONObject.put("RMESSAGE", "Invalid amount");
            }
//
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/updatebranchgroupmemberrevenue", method = RequestMethod.POST)
    public @ResponseBody
    String updateBranchGroupMemberRevenue(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("datatable_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            JSONObject json = revenuetargetsetting.updateBrnachGroupMemberTarget(target, user);
            if (json.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("remaning", json.getInt("remaning"));
                jSONObject.put("sum", json.getInt("sum"));
                jSONObject.put("RMESSAGE", " Remaining amount to be shared among the Members of current Group is");
            } else {
                jSONObject.put("MESSAGE", "Warning!  ");
                jSONObject.put("RMESSAGE", "Invalid amount");
            }
//
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/gettotalactivitycount", method = RequestMethod.POST)
    public @ResponseBody
    String getTotalActivityCount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab2_info");
        JSONArray jSONArray = new JSONArray();
        String user = (String) session.getAttribute("username");
        TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
        JSONArray activity = revenuetargetsetting.getActivityCountByTargetid(target.getTargetid());
        JSONArray ractivity = revenuetargetsetting.getReaminingActivityCountByTargetid(target.getTargetid());
        try {
            JSONObject jSONObject = new JSONObject();
            for (int i = 0; i < activity.length(); i++) {
                JSONObject jSONObject2 = new JSONObject();
                JSONArray arrayitem = activity.optJSONArray(i);
                for (int j = 0; j < ractivity.length(); j++) {
                    JSONArray remaningarrayitem = ractivity.optJSONArray(j);
                    if (arrayitem.getInt(0) == remaningarrayitem.getInt(0)) {
                        if (arrayitem.getInt(0) == 1) {
                            int remaning = arrayitem.getInt(1) - remaningarrayitem.getInt(1);
                            jSONObject2.put("callcount", remaning);
                            jSONObject2.put("Tcallcount", arrayitem.getInt(1));
                        } else if (arrayitem.getInt(0) == 2) {
                            int remaning = arrayitem.getInt(1) - remaningarrayitem.getInt(1);
                            jSONObject2.put("vp", remaning);
                            jSONObject2.put("Tvp", arrayitem.getInt(1));
                        } else {
                            int remaning = arrayitem.getInt(1) - remaningarrayitem.getInt(1);
                            jSONObject2.put("proposal", remaning);
                            jSONObject2.put("Tproposal", arrayitem.getInt(1));
                        }
                    }
                }

                jSONArray.put(jSONObject2);
            }

        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONArray.toString();
    }

    @RequestMapping(value = "/update/regionalactivity", method = RequestMethod.POST)
    public @ResponseBody
    String updateRegionalActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String datatable = request.getParameter("tab2_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(datatable, TargetSetting.class);
            jSONObject = revenuetargetsetting.updateRegionalActivity(target, user);
            if (jSONObject.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
            } else {
                jSONObject.put("MESSAGE", "Invalid amount");
            }
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/datatbelfivecolums", method = RequestMethod.POST)
    public @ResponseBody
    String datatablefiveecolum(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab2_info");
        JSONArray jSONArray = new JSONArray();
        String user = (String) session.getAttribute("username");
        JSONArray activity = revenuetargetsetting.getActitityTypes();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("title", "Branch Name");
            jSONArray.put(jSONObject);
            for (int i = 0; i < activity.length(); i++) {
                JSONArray arrayitem = activity.optJSONArray(i);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("title", arrayitem.get(1));
                jSONArray.put(jSONObject2);
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("title", "Action");
            jSONArray.put(jSONObject3);

        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONArray.toString();
    }

    @RequestMapping(value = "/branchactivitycountbyregion", method = RequestMethod.POST)
    public @ResponseBody
    String getTotalActivityCountByBranch(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("tab3_info");
        JSONArray jSONArray = new JSONArray();
        String user = (String) session.getAttribute("username");
        TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
        JSONArray Tactivity = revenuetargetsetting.getToatalActivityCountByRegionid(target.getTargetid(), target.getRegionid());
        JSONArray Ractivity = revenuetargetsetting.getReaminingActivityCountByRegionid(target.getTargetid(), target.getRegionid());
        try {
            JSONObject jSONObject = new JSONObject();
            for (int i = 0; i < Tactivity.length(); i++) {
                JSONObject jSONObject2 = new JSONObject();
                JSONArray arrayitem = Tactivity.optJSONArray(i);
                for (int j = 0; j < Ractivity.length(); j++) {
                    JSONArray remaningarrayitem = Ractivity.optJSONArray(j);
                    if (arrayitem.getInt(0) == remaningarrayitem.getInt(0)) {
                        if (arrayitem.getInt(0) == 1) {
                            int remaning = arrayitem.getInt(1) - remaningarrayitem.getInt(1);
                            jSONObject2.put("callcount", remaning);
                            jSONObject2.put("Tcallcount", arrayitem.getInt(1));
                        } else if (arrayitem.getInt(0) == 2) {
                            int remaning = arrayitem.getInt(1) - remaningarrayitem.getInt(1);
                            jSONObject2.put("vp", remaning);
                            jSONObject2.put("Tvp", arrayitem.getInt(1));
                        } else {
                            int remaning = arrayitem.getInt(1) - remaningarrayitem.getInt(1);
                            jSONObject2.put("proposal", remaning);
                            jSONObject2.put("Tproposal", arrayitem.getInt(1));
                        }
                    }
                }

                jSONArray.put(jSONObject2);
            }

        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONArray.toString();
    }

    @RequestMapping(value = "/update/branchactivity", method = RequestMethod.POST)
    public @ResponseBody
    String updateBranchActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String datatable = request.getParameter("tab3_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(datatable, TargetSetting.class);
            jSONObject = revenuetargetsetting.updateBranchActivity(target, user);
            if (jSONObject.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
            } else {
                jSONObject.put("errorMsg", "Invalid amount");
            }
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/updatebranchgroupactivity", method = RequestMethod.POST)
    public @ResponseBody
    String updateBranchGroupActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("datatable_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            JSONObject json = revenuetargetsetting.updateBrnachGroupActibity(target, user);
            if (json.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("remaning", json.getInt("remaning"));
                jSONObject.put("sum", json.getInt("sum"));
                jSONObject.put("RMESSAGE", "Remaining Activity count to be shared among the Branches group of current branch  is");
            } else {
                jSONObject.put("MESSAGE", "Warning!  ");
                jSONObject.put("RMESSAGE", "Invalid amount");
            }
//
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/updatebranchgroupmemberactivity", method = RequestMethod.POST)
    public @ResponseBody
    String updateBranchGroupMemberActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtfour_info = request.getParameter("datatable_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtfour_info, TargetSetting.class);
            JSONObject json = revenuetargetsetting.updatBranchMemberActivityCount(target);
            if (json.getBoolean("status")) {
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("remaning", json.getInt("remaning"));
                jSONObject.put("sum", json.getInt("sum"));
                jSONObject.put("RMESSAGE", "Remaining Activity count to be shared among the members of the current Group is ");
            } else {
                jSONObject.put("MESSAGE", "Warning!  ");
                jSONObject.put("RMESSAGE", "Invalid amount");
            }
//            //System.out.println("");
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/deletealltargetamount", method = RequestMethod.POST)
    public @ResponseBody
    String deleteAllTargetAmount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtone_info = request.getParameter("tab1_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtone_info, TargetSetting.class);
            jSONObject = revenuetargetsetting.deleteAllTargetAmount(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/deletealltargetaftersecondtab", method = RequestMethod.POST)
    public @ResponseBody
    String deleteAllTargetAfterSecondTab(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtone_info = request.getParameter("tab1_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtone_info, TargetSetting.class);
            jSONObject = revenuetargetsetting.deleteAllTargetAfterSecondTab(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/deletealltargetafterthirdtab", method = RequestMethod.POST)
    public @ResponseBody
    String deleteAllTargetAfterThirdTab(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtone_info = request.getParameter("tab1_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtone_info, TargetSetting.class);
            jSONObject = revenuetargetsetting.deleteAllTargetAfterThirdTab(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/deletealltargetafterfourrhtabfirstpart", method = RequestMethod.POST)
    public @ResponseBody
    String deleteAllTargetAfterFourrhTabFirstPart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtone_info = request.getParameter("tab1_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtone_info, TargetSetting.class);
            jSONObject = revenuetargetsetting.deleteAllTargetAfterFourrhTabFirstPart(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/deleteallactivitycount", method = RequestMethod.POST)
    public @ResponseBody
    String deleteAllActivityCount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtone_info = request.getParameter("tab1_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtone_info, TargetSetting.class);
            jSONObject = revenuetargetsetting.deleteAllActivityCount(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/deleteallactivitycountaftersecondtab", method = RequestMethod.POST)
    public @ResponseBody
    String deleteAllActivityCountAfterSecondTab(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtone_info = request.getParameter("tab1_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtone_info, TargetSetting.class);
            jSONObject = revenuetargetsetting.deleteAllActivityCountAfterSecondTab(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/deleteallactivitycountafterthirdtab", method = RequestMethod.POST)
    public @ResponseBody
    String deleteAllActivityCountAfterThirdTab(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtone_info = request.getParameter("tab1_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtone_info, TargetSetting.class);
            jSONObject = revenuetargetsetting.deleteAllActivityCountAfterThirdTab(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/deleteallactivitycountafterfourrhtabfirstpart", method = RequestMethod.POST)
    public @ResponseBody
    String deleteAllActivityCountAfterFourrhTabFirstPart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String tabtone_info = request.getParameter("tab1_info");
        JSONObject jSONObject = new JSONObject();
        String user = (String) session.getAttribute("username");
        try {
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtone_info, TargetSetting.class);
            jSONObject = revenuetargetsetting.deleteAllActivityCountAfterFourrhTabFirstPart(target);
        } catch (Exception ex) {
            Logger.getLogger(RevenueTargetSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/getTargetAmount", method = RequestMethod.POST)
    public @ResponseBody
    String getTargetAmount(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        JSONObject jSONObject = new JSONObject();
        try {
            String tabtone_info = request.getParameter("tab1_info");
            TargetSetting target = (TargetSetting) new ObjectMapper().readValue(tabtone_info, TargetSetting.class);
            TargetSetting targetData = revenuetargetsetting.getTargetByTargetId(target.getTargetid());
            jSONObject.put("ActivityCount", revenuetargetsetting.getActitityType(target.getTargetid()));
            jSONObject.put("Amonut", targetData.getRevenue());

        } catch (Exception e) {
        }
        return jSONObject.toString();
    }

}

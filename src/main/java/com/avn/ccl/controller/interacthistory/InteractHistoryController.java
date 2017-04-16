/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.interacthistory;

import com.avn.ccl.controller.audittrace.AuditTraceController;
import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.fdtype.FdTypeDAOImpl;
import com.avn.ccl.daoimpl.gender.GenderDAOImpl;
import com.avn.ccl.daoimpl.interacthistory.InteractHistoryDAOImpl;
import com.avn.ccl.daoimpl.language.LanguageDAOImpl;
import com.avn.ccl.daoimpl.maritalstatus.MaritalStatusDAOImpl;
import com.avn.ccl.daoimpl.religon.ReligonDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.title.TitleDAOImpl;
import com.avn.ccl.model.callcenter.CallCenter;
import com.avn.ccl.model.casemgt.Case;
import com.avn.ccl.model.customervisit.CustomerVisit;
import com.avn.ccl.model.interacthistory.Interacthistory;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ISURU
 */
@Controller
public class InteractHistoryController {

    @Autowired
    InteractHistoryDAOImpl interacthistorydaoimpl;
    @Autowired
    CommonDAOImpl commendaoimpl;
    @Autowired
    BranchDAOImpl branchDAOImpl;
    @Autowired
    FdTypeDAOImpl fdTypeDAOImpl;
    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    TitleDAOImpl titleDAOImpl;
    @Autowired
    GenderDAOImpl genderDAOImpl;
    @Autowired
    ReligonDAOImpl religonDAOImpl;
    @Autowired
    MaritalStatusDAOImpl maritalStatusDAOImpl;
    @Autowired
    LanguageDAOImpl languageDAOImpl;

    @RequestMapping(value = "/interacthistory", method = RequestMethod.GET)
    public String intercatHistorySearch(@ModelAttribute("interacthistory") Interacthistory data, Map<String, Object> model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            setPageViewComponenets(model);
            Interacthistory interacthistorydata = checkPrivilage_btn(data, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_INTERACT_HISTORY_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_CUSTOMER_INTERACT_HISTORY_SUBSECTION_ID), objList);
            model.put("avn_search", interacthistorydata.isSearch_btn());
        } catch (Exception e) {
        }

        return "interacthistory/interacthistory";
    }

    @RequestMapping(value = "/interacthistory/searched", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        CallCenter callcenter = new CallCenter();
        CallCenter callcenterview = checkPrivilage_btn(callcenter, String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CALL_CENTER_SUBSECTION_ID), objList);
        Case cases = new Case();
        Case viewcasae = checkPrivilage_btn(cases, String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CASE_SUBSECTION_ID), objList);
        CustomerVisit customervist = new CustomerVisit();
        CustomerVisit customervisitview = checkPrivilage_btn(customervist, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_VISIT_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CUSTOMERVISIT_SUBSECTION_ID), objList);
        Interacthistory interacthistory = new Interacthistory();
        Interacthistory interacthistorydata = checkPrivilage_btn(interacthistory, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_INTERACT_HISTORY_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_CUSTOMER_INTERACT_HISTORY_SUBSECTION_ID), objList);

        String CallViewStatus = "";
        String CaseViewStatus = "";
        String CustomerVistViewStatus = "";
        if (callcenterview.isView_btn()) {
            CallViewStatus = "disabled";
        } else {
            CallViewStatus = "active";
        }
        if (viewcasae.isView_btn()) {
            CaseViewStatus = "disabled";
        } else {
            CaseViewStatus = "active";
        }
        if (customervisitview.isView_btn()) {
            CustomerVistViewStatus = "disabled";
        } else {
            CustomerVistViewStatus = "active";
        }

        List<Interacthistory> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_INTERACT_HISTORY_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_CUSTOMER_INTERACT_HISTORY_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }

                if (Boolean.valueOf(request.getParameter("search"))) {
                    Interacthistory parameters = this.getParameters(request);

                    iTotalRecords = interacthistorydaoimpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    String action = null;
                    if (iTotalRecords > 0) {
                        list = interacthistorydaoimpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (Interacthistory data : list) {
                            JSONObject object = new JSONObject();
                            object.put("id", data.getId());
                            object.put("module", data.getModule());
                            object.put("caseTypeDes", data.getCaseTypeDes());
                            object.put("firstName", data.getFirstName());
                            object.put("telephone", data.getTelephone());
                            object.put("discription", data.getDiscription());
                            object.put("status", data.getStatus());
                            object.put("createdDateTime", data.getCreatedDateTime());
                            object.put("lastUpdateDatetime", data.getLastUpdateDatetime());
                            object.put("createduser", data.getCreateduser());
                            if (data.getType().equalsIgnoreCase("CL")) {
                                action = "<div class=\"row\">"
                                        + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/call/view?callid=" + data.getId() + "' class=" + CallViewStatus + " ><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div></div>";
                            } else if (data.getType().equalsIgnoreCase("CV")) {
                                action = "<div class=\"row\">"
                                        + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/customervisit/view?cusvisitID=" + data.getId() + "'class=" + CustomerVistViewStatus + "  ><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div></div>";
                            } else {
                                action = "<div class=\"row\">"
                                        + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/case/view?caseid=" + data.getId() + "'  class=" + CaseViewStatus + "><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div></div>";
                            }

                            object.put("action", action);
//                        object.put("audittraceid", data.getAuditTraceId());        
                            rows.put(object);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Logger.getLogger(AuditTraceController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private void setPageViewComponenets(Map<String, Object> model) throws Exception {
        Map<String, String> list = new LinkedHashMap<>();
        model.put("titleList", titleDAOImpl.getTitleDropdownList());
        model.put("genderList", genderDAOImpl.getGenderDropdownList());
        
        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("1", "Srilankan");
        model.put("nationalityList", list);
        model.put("religonList", religonDAOImpl.getReligonDropdownList());
        model.put("maritalStatusList", maritalStatusDAOImpl.getMaritalStatusCodeDropdownList());
        model.put("languageList", languageDAOImpl.getLanguagesList());
        
        model.put("branchOldCodeList", branchDAOImpl.getBranchOldDropdownList());
        model.put("fdTypeList", fdTypeDAOImpl.getFdDropdownList());
        model.put("savingsStatusList", statusDAOImpl.getStatusCodeDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_ACCOUNT_SAVINGS));
        model.put("contractStatusList", statusDAOImpl.getStatusCodeDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_ACCOUNT_LOAN));
    }

    private Interacthistory getParameters(HttpServletRequest request) throws Exception {
        Interacthistory parameters = new Interacthistory();
        if (request.getParameter("from_date") != null && !request.getParameter("from_date").isEmpty()) {
            parameters.setFrom_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("from_date")));
        }
        if (request.getParameter("to_date") != null && !request.getParameter("to_date").isEmpty()) {
            parameters.setTo_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("to_date")));
        }
        if (request.getParameter("firstName") != null && !request.getParameter("firstName").isEmpty()) {
            parameters.setFirstName(request.getParameter("firstName"));
        }
        if (request.getParameter("telephone") != null && !request.getParameter("telephone").isEmpty()) {
            parameters.setTelephone(request.getParameter("telephone"));
        }

        return parameters;
    }

    private Interacthistory checkPrivilage_btn(Interacthistory interacthistroy, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        interacthistroy = new Interacthistory();
        interacthistroy.setSave_btn(true);
        interacthistroy.setSearch_btn(true);
        interacthistroy.setEdit_btn(true);
        interacthistroy.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                interacthistroy.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                interacthistroy.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                interacthistroy.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                interacthistroy.setView_btn(false);
            }
        }
        return interacthistroy;
    }

    private CustomerVisit checkPrivilage_btn(CustomerVisit data, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        data = new CustomerVisit();
        data.setSave_btn(true);
        data.setSearch_btn(true);
        data.setEdit_btn(true);
        data.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                data.setView_btn(false);
            }
        }
        return data;
    }

    private CallCenter checkPrivilage_btn(CallCenter callcenter, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        callcenter = new CallCenter();
        callcenter.setSave_btn(true);
        callcenter.setSearch_btn(true);
        callcenter.setEdit_btn(true);
        callcenter.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                callcenter.setView_btn(false);
            }
        }
        return callcenter;
    }

    private Case checkPrivilage_btn(Case usercase, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        usercase = new Case();
        usercase.setSave_btn(true);
        usercase.setSearch_btn(true);
        usercase.setEdit_btn(true);
        usercase.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                usercase.setView_btn(false);
            }
        }

        return usercase;
    }

}

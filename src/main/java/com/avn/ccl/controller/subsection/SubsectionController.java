/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.subsection;

import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.section.SectionDAOImpl;

import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.subsection.SubsectionDAOImpl;
import com.avn.ccl.model.subsection.Subsection;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.MasterDataVarList;
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
 *
 * @author Isuru
 */
@Controller
public class SubsectionController {

    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    SubsectionDAOImpl subsectiondaoimpl;
    @Autowired
    AuditTraceDAOImpl audit;
    @Autowired
    SectionDAOImpl sectiondaoimpl;
    @Autowired
    CommonDAOImpl commendaoimpl;

    @RequestMapping(value = "/subsection/create", method = RequestMethod.GET)
    public String subSectionPageLoad(@ModelAttribute("subsections") Subsection subsection, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Subsection subsectiondata = checkPrivilage_btn(subsection, String.valueOf(MasterDataVarList.CCL_CODE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_SUBSECTION_SUBSECTION_ID), objList);
            model.addAttribute("avn_create", subsectiondata.isSave_btn());
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(SubsectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "subsection/subsection";
    }

    @RequestMapping(value = "/subsection/created", method = RequestMethod.POST)
    public @ResponseBody
    String saveSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        String user = (String) session.getAttribute("username");
        String subsectiondata = request.getParameter("subsection_info");
        Subsection subsection = (Subsection) new ObjectMapper().readValue(subsectiondata, Subsection.class);
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        JSONObject jSONObject = new JSONObject();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_SUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                Long subsectionID = subsectiondaoimpl.insertSubSection(subsection, user);
                jSONObject.put("CODE", "SUCCESS");
                audit.insertAuditTrace("Sub Section Create page ", " Create ", " Sub Section details added", String.valueOf(subsectionID), user);
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to Saving Section.");
            }
            response.reset();
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(SubsectionController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/subsection/search", method = RequestMethod.GET)
    public String sectionSearchPageLoad(@ModelAttribute("subsectionsearch") Subsection subsection, ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            Subsection subsectiondata = checkPrivilage_btn(subsection, String.valueOf(MasterDataVarList.CCL_CODE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_SUBSECTION_SUBSECTION_ID), objList);
            model.addAttribute("avn_search", subsectiondata.isSearch_btn());
        } catch (Exception e) {
        }
        return "subsection/subsectionsearch";
    }

    @RequestMapping(value = "/subsection/searched", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        Subsection subsectiondata = new Subsection();
        Subsection sectionview = checkPrivilage_btn(subsectiondata, String.valueOf(MasterDataVarList.CCL_CODE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_SUBSECTION_SUBSECTION_ID), objList);
        Subsection sectionedit = checkPrivilage_btn(subsectiondata, String.valueOf(MasterDataVarList.CCL_CODE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_SUBSECTION_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
        if (sectionview.isView_btn()) {
            ViewStatus = "disabled";
        } else {
            ViewStatus = "active";
        }
        if (sectionedit.isEdit_btn()) {
            UpdateStatus = "disabled";
        } else {
            UpdateStatus = "active";
        }
        List<Subsection> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_SUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
                if (Boolean.valueOf(request.getParameter("search"))) {

                    Subsection parameters = this.getParameters(request);

                    iTotalRecords = subsectiondaoimpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = subsectiondaoimpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (Subsection data : list) {
                            JSONObject object = new JSONObject();
                            object.put("subsectionId", data.getSubsectionId());
                            object.put("subsectionDes", data.getSubsectionDes());
                            object.put("sectionDes", data.getSectionDes());
                            object.put("statusDes", data.getStatusDes());
                            object.put("url", data.getUrl());
                            object.put("clickable", data.getClickable());
                            object.put("icon", data.getIcon());
                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/subsection/view?subsectionId=" + data.getSubsectionId() + "' class=" + ViewStatus + "><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/subsection/update?subsectionId=" + data.getSubsectionId() + "' class=" + UpdateStatus + "><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Logger.getLogger(SubsectionController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private Subsection getParameters(HttpServletRequest request) throws Exception {
        Subsection parameters = new Subsection();

        if (request.getParameter("searchoption") != null && !request.getParameter("searchoption").isEmpty()) {
            parameters.setSearchoption(request.getParameter("searchoption"));
        }
        if (request.getParameter("input") != null && !request.getParameter("input").isEmpty()) {
            parameters.setInput(request.getParameter("input"));
        }

        return parameters;
    }

    @RequestMapping(value = "/subsection/view", method = RequestMethod.GET)
    public String subsectionViewPageLoad(@ModelAttribute("subsectionsview") Subsection subsection, @RequestParam(value = "subsectionId", required = false) String subsectionId, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_SUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                Subsection subsectionData = subsectiondaoimpl.getSubectionBySubSectionId(subsectionId);
                model.addAttribute("subsectionsview", subsectionData);
            } else {
                model.addAttribute("errorMsg", "No privilage to view subsection");
            }

        } catch (Exception ex) {
            Logger.getLogger(SubsectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "subsection/subsectionview";
    }

    @RequestMapping(value = "/subsection/update", method = RequestMethod.GET)
    public String sectionUpdatePageLoad(@ModelAttribute("subsectionsupdate") Subsection subsection, @RequestParam(value = "subsectionId", required = false) String subsectionId, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Subsection subsectiondata = checkPrivilage_btn(subsection, String.valueOf(MasterDataVarList.CCL_CODE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_SUBSECTION_SUBSECTION_ID), objList);
            model.addAttribute("avn_update", subsectiondata.isEdit_btn());
            this.setCreateViewComponenets(model);
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_SUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                Subsection subsectionData = subsectiondaoimpl.getSubectionBySubSectionId(subsectionId);
                model.addAttribute("subsectionsupdate", subsectionData);
            } else {
                model.addAttribute("errorMsg", "No privilage to view subsection");
            }

        } catch (Exception ex) {
            Logger.getLogger(SubsectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "subsection/subsectionupdate";
    }

    @RequestMapping(value = "/subsection/updated", method = RequestMethod.POST)
    public @ResponseBody
    String updateSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        String user = (String) session.getAttribute("username");
        String subsectiondata = request.getParameter("subsection_info");
        Subsection subsection = (Subsection) new ObjectMapper().readValue(subsectiondata, Subsection.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_SUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            if (privilage) {
                subsectiondaoimpl.updateSubSection(subsection, user);
                jSONObject.put("CODE", "SUCCESS");
                audit.insertAuditTrace("Section Update page ", " Update ", " Section details update", subsection.getSectionid(), user);
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privalage to Saving Section.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(SubsectionController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        model.addAttribute("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_DEFAULT));
        model.addAttribute("sectionList", sectiondaoimpl.getSectionDropdownList());

    }

    private Subsection checkPrivilage_btn(Subsection subsection, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        subsection = new Subsection();
        subsection.setSave_btn(true);
        subsection.setSearch_btn(true);
        subsection.setEdit_btn(true);
        subsection.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                subsection.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                subsection.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                subsection.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                subsection.setView_btn(false);
            }
        }
        return subsection;
    }

}

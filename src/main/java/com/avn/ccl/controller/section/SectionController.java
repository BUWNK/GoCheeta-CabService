/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.section;

import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;

import com.avn.ccl.daoimpl.section.SectionDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.userrole.UserRoleDAOImpl;
import com.avn.ccl.model.callcenter.CallCenter;
import com.avn.ccl.model.section.Section;
import com.avn.ccl.model.userroletask.UserRoleTask;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
public class SectionController {

    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    AuditTraceDAOImpl audit;
    @Autowired
    SectionDAOImpl sectiondaoimpl;
    @Autowired
    UserRoleDAOImpl userroledaoimpl;
    @Autowired
    CommonDAOImpl commendaoimpl;

    @RequestMapping(value = "/section/create", method = RequestMethod.GET)
    public String sectionPageLoad(@ModelAttribute("sections") Section section, HttpSession session, ModelMap model) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Section sectiondata = checkPrivilage_btn(section, String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_SECTION_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model);
            Section sectionload = new Section();
            sectionload.setSectionlevel("0");
            model.addAttribute("avn_create", sectiondata.isSave_btn());
        } catch (Exception ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "section/section";
    }

    @RequestMapping(value = "/section/created", method = RequestMethod.POST)
    public @ResponseBody
    String saveSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        String user = (String) session.getAttribute("username");
        String sectiondata = request.getParameter("section_info");
        Section section = (Section) new ObjectMapper().readValue(sectiondata, Section.class);
        JSONObject jSONObject = new JSONObject();

        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_SECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                Long sectionID = sectiondaoimpl.insertSection(section, user);
                jSONObject.put("CODE", "SUCCESS");
                audit.insertAuditTrace("Section Create page ", " Create ", " Section details added", String.valueOf(sectionID), user);
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to create section");
            }
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/section/parentsection", method = RequestMethod.POST)
    public @ResponseBody
    List<Section> loadParentSection(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String values = request.getParameter("sectionlevel");
        return sectiondaoimpl.getParntSection(values);
    }

    @RequestMapping(value = "/section/search", method = RequestMethod.GET)
    public String sectionSearchPageLoad(@ModelAttribute("sectionsearch") Section section, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Section sectiondata = checkPrivilage_btn(section, String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_SECTION_SUBSECTION_ID), objList);
            model.addAttribute("avn_create", sectiondata.isSave_btn());
        } catch (Exception e) {
        }
        return "section/sectionsearch";
    }

    @RequestMapping(value = "/section/searched", method = RequestMethod.POST)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        Section sectiondata = new Section();
        Section sectionview = checkPrivilage_btn(sectiondata, String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_SECTION_SUBSECTION_ID), objList);
        Section sectionedit = checkPrivilage_btn(sectiondata, String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_SECTION_SUBSECTION_ID), objList);
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
        List<Section> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_SECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
                if (Boolean.valueOf(request.getParameter("search"))) {

                    Section parameters = this.getParameters(request);

                    iTotalRecords = sectiondaoimpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = sectiondaoimpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (Section data : list) {
                            JSONObject object = new JSONObject();
                            object.put("sectionid", data.getSectionid());
                            object.put("sectionDes", data.getSectionDes());
                            object.put("sectionlevel", data.getSectionlevel());
                            object.put("parentsection", data.getParentsection());
                            object.put("icon", data.getIcon());
                            object.put("statusDes", data.getStatusDes());
                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/section/view?sectionId=" + data.getSectionid() + "' class=" + ViewStatus + " ><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/section/update?sectionId=" + data.getSectionid() + "'class=" + UpdateStatus + " ><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                    }
                }
            } else {

            }

        } catch (Exception e) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private Section getParameters(HttpServletRequest request) throws Exception {
        Section parameters = new Section();

        if (request.getParameter("searchoption") != null && !request.getParameter("searchoption").isEmpty()) {
            parameters.setSearchoption(request.getParameter("searchoption"));
        }
        if (request.getParameter("input") != null && !request.getParameter("input").isEmpty()) {
            parameters.setInput(request.getParameter("input"));
        }

        return parameters;
    }

    @RequestMapping(value = "/section/view", method = RequestMethod.GET)
    public String sectionViewPageLoad(@ModelAttribute("sectionsview") Section section, @RequestParam(value = "sectionId", required = false) String sectionId, ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_SECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                this.setCreateViewComponenets(model);
                Section sectionData = sectiondaoimpl.getSectionBySectionId(sectionId);
                model.addAttribute("sectionsview", sectionData);
            } else {
                model.addAttribute("errorMsg", "No privilage to view section");
            }

        } catch (Exception ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "section/sectionview";
    }

    @RequestMapping(value = "/section/update", method = RequestMethod.GET)
    public String sectionUpdatePageLoad(@ModelAttribute("sectionsUpdate") Section section, @RequestParam(value = "sectionId", required = false) String sectionId, ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_SECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            Section sectionedit = checkPrivilage_btn(section, String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_SECTION_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model);
            model.addAttribute("avn_update", sectionedit.isEdit_btn());
            if (privilage) {
                Section sectionData = sectiondaoimpl.getSectionBySectionId(sectionId);
                model.addAttribute("sectionsUpdate", sectionData);
            } else {
                model.addAttribute("errorMsg", "No privilage to view case");
            }

        } catch (Exception ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "section/sectionupdate";
    }

    @RequestMapping(value = "/section/updated", method = RequestMethod.POST)
    public @ResponseBody
    String updateSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        String user = (String) session.getAttribute("username");
        String sectiondata = request.getParameter("section_info");
        Section section = (Section) new ObjectMapper().readValue(sectiondata, Section.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
//       
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_SECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            if (privilage) {
                sectiondaoimpl.updateSection(section, user);
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to update case");
            }
            jSONObject.put("CODE", "SUCCESS");
            audit.insertAuditTrace("Section Update page ", " Update ", " Section details update", String.valueOf(section.getSectionid()), user);
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        Map<String, String> list;
        model.addAttribute("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_DEFAULT));
        model.addAttribute("sectionList", sectiondaoimpl.getMultiSectionDropdownList());
        model.addAttribute("userRoleList", userroledaoimpl.getUserRoleDropdownList());

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("0", "0");
        list.put("1", "1");
        list.put("2", "2");
        model.put("SectionLevel", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        model.put("ParantSection", list);
    }

    private Section checkPrivilage_btn(Section section, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        section = new Section();
        section.setSave_btn(true);
        section.setSearch_btn(true);
        section.setEdit_btn(true);
        section.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                section.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                section.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                section.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                section.setView_btn(false);
            }
        }
        return section;
    }

}

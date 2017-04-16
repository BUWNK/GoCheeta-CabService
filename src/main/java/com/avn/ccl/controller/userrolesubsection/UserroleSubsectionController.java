/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.userrolesubsection;

import com.avn.ccl.controller.section.SectionController;
import com.avn.ccl.controller.userrolesection.UserRoleSectionController;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.section.SectionDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.userrole.UserRoleDAOImpl;
import com.avn.ccl.daoimpl.userrolesubsection.UserRoleSubsectionDAOImpl;
import com.avn.ccl.model.section.Section;
import com.avn.ccl.model.userrolesection.UserRoleSection;
import com.avn.ccl.model.userrolesubsection.UserRoleSubSection;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.util.ArrayList;
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
public class UserroleSubsectionController {

    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    AuditTraceDAOImpl audit;
    @Autowired
    UserRoleDAOImpl userroledaoimpl;
    @Autowired
    UserRoleSubsectionDAOImpl userrolesubsectiondaoimpl;
    @Autowired
    SectionDAOImpl sectiondaoimpl;
    @Autowired
    CommonDAOImpl commendaoimpl;

    @RequestMapping(value = "/userrolesubsection/create", method = RequestMethod.GET)
    public String userRoleSectionPageLoad(@ModelAttribute("userolesubsection") UserRoleSubSection rolesubsection, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            UserRoleSubSection userrolesubsetiondata = checkPrivilage_btn(rolesubsection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_USERROLESUBSECTION_SUBSECTION_ID), objList);
            model.addAttribute("avn_create", userrolesubsetiondata.isSave_btn());
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(UserroleSubsectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolesubsection/userrolesubsection";
    }

    @RequestMapping(value = "/rolesubsection/sections", method = RequestMethod.POST)
    public @ResponseBody
    List<Section> loadSectionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String values = request.getParameter("roleid");
        return sectiondaoimpl.getSectionDropdownListByRoleID(values);
    }

    @RequestMapping(value = "/rolesubsection/ajaxsubsectionsList", method = RequestMethod.POST)
    public @ResponseBody
    String loadAssignSubSectionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String subsectionData = request.getParameter("subsection");
        UserRoleSubSection rolesubsectionData = (UserRoleSubSection) new ObjectMapper().readValue(subsectionData, UserRoleSubSection.class);
        JSONObject subsectionList = new JSONObject();
        subsectionList.put("assignsubsections", userrolesubsectiondaoimpl.getAssignSubSectionDropdownListBySectionID(rolesubsectionData.getUserroleid(), rolesubsectionData.getSectionid()));
        subsectionList.put("notassignsubsections", userrolesubsectiondaoimpl.getSubSectionDropdownListBySectionID(rolesubsectionData.getUserroleid(), rolesubsectionData.getSectionid()));
        return subsectionList.toString();
    }

    @RequestMapping(value = "/rolesubsection/create", method = RequestMethod.POST)
    public @ResponseBody
    String saveSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        String user = (String) session.getAttribute("username");
        List<UserRoleSubSection> list;
        String rolesubsection = request.getParameter("rolesubsection_info");
        UserRoleSubSection rolesubsectionData = (UserRoleSubSection) new ObjectMapper().readValue(rolesubsection, UserRoleSubSection.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_USERROLESUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);

            List<String> arrylist = null;
            arrylist = new ArrayList<>();
            if (privilage) {
                list = userrolesubsectiondaoimpl.insertUserRoleSubSection(rolesubsectionData, user);
                for (UserRoleSubSection list1 : list) {
                    jSONObject.put("CODE", "SUCCESS");
                    audit.insertAuditTrace("User Role SubSection Add ", " Add ", " User Role Id " + rolesubsectionData.getUserroleid() + "Section Id's " + rolesubsectionData.getSectionid() + "Subsection Id's " + list1.getSubsectionid(), list1.getSubsectionid(), user);
                }
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to Saving Section.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(UserroleSubsectionController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/userrolesubsection/update", method = RequestMethod.GET)
    public String userRoleSectionUpdatePageLoad(@ModelAttribute("userolesubsectionupdate") UserRoleSubSection rolesubsection,
            @RequestParam(value = "userroldId", required = false) String userRoleId,
            @RequestParam(value = "sectionId", required = false) String sectionID,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            this.setCreateViewComponenets(model);
            UserRoleSubSection userrolesubsetiondata = checkPrivilage_btn(rolesubsection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USERROLESUBSECTION_SUBSECTION_ID), objList);
            model.addAttribute("avn_update", userrolesubsetiondata.isEdit_btn());
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USERROLESUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                rolesubsection.setUserroleid(userRoleId);
                rolesubsection.setSectionid(sectionID);
                model.addAttribute("userolesectionupdate", rolesubsection);
                model.addAttribute("notAssigneSubSection", userrolesubsectiondaoimpl.getNotAssignedSubSections(userRoleId, sectionID));
                model.addAttribute("assigneSubSection", userrolesubsectiondaoimpl.getAssignedSubSections(userRoleId, sectionID));
            } else {
                model.addAttribute("errorMsg", "No privilage to view user role subsection.");
            }

        } catch (Exception ex) {
            Logger.getLogger(UserroleSubsectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolesubsection/userrolesubsectionupdate";
    }

    @RequestMapping(value = "/userrolesubsection/updated", method = RequestMethod.POST)
    public @ResponseBody
    String updateSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        String user = (String) session.getAttribute("username");
        String roelsubsectionDate = request.getParameter("userrolesubsection_info");
        UserRoleSubSection rolesubsection = (UserRoleSubSection) new ObjectMapper().readValue(roelsubsectionDate, UserRoleSubSection.class);
        JSONObject jSONObject = new JSONObject();
        List<UserRoleSubSection> list;
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USERROLESUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            if (privilage) {
                list = userrolesubsectiondaoimpl.updateuserRoleSection(rolesubsection, user);
                jSONObject.put("CODE", "SUCCESS");
                for (UserRoleSubSection list1 : list) {
                    jSONObject.put("CODE", "SUCCESS");
                    audit.insertAuditTrace("User Role SubSection Add ", " Add ", " User Role Id " + rolesubsection.getUserroleid() + "Section Id's " + rolesubsection.getSectionid() + "Subsection Id's " + list1.getSubsectionid(), list1.getSubsectionid(), user);
                }
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to Saving Section.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(UserroleSubsectionController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/userrolesubsection/search", method = RequestMethod.GET)
    public String userRoleSubsectionSearchPageLoad(@ModelAttribute("userrolesubsectionsearch") UserRoleSubSection rolesubsection, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            UserRoleSubSection userrolesubsetiondata = checkPrivilage_btn(rolesubsection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USERROLESUBSECTION_SUBSECTION_ID), objList);
            model.addAttribute("avn_search", userrolesubsetiondata.isSearch_btn());
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(UserRoleSectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolesubsection/userrolesubsectionsearch";
    }

    @RequestMapping(value = "/rolesubsection/searched", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        UserRoleSubSection userolesubsection = new UserRoleSubSection();
        UserRoleSubSection userrolesubsectionview = checkPrivilage_btn(userolesubsection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USERROLESUBSECTION_SUBSECTION_ID), objList);
        UserRoleSubSection userrolesubsectionedit = checkPrivilage_btn(userolesubsection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USERROLESUBSECTION_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
        if (userrolesubsectionview.isView_btn()) {
            ViewStatus = "disabled";
        } else {
            ViewStatus = "active";
        }
        if (userrolesubsectionedit.isEdit_btn()) {
            UpdateStatus = "disabled";
        } else {
            UpdateStatus = "active";
        }
        List<UserRoleSubSection> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USERROLESUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
                if (Boolean.valueOf(request.getParameter("search"))) {

                    UserRoleSubSection parameters = this.getParameters(request);

                    iTotalRecords = userrolesubsectiondaoimpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = userrolesubsectiondaoimpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (UserRoleSubSection data : list) {
                            JSONObject object = new JSONObject();
                            object.put("sectionid", data.getSectionid());
                            object.put("sectionDes", data.getSectionDes());
                            object.put("userroleid", data.getUserroleid());
                            object.put("userroleDes", data.getUserroleDes());
                            object.put("subsectionid", data.getSubsectionid());
                            object.put("subsectionDes", data.getSubsectionDes());
                            object.put("lastupdateddatetime", data.getLastupdatetime());
                            object.put("createddatetime", data.getCreateddatetime());
                            object.put("createduser", data.getCreateduser());

                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/userrolesubsection/view?userroldId=" + data.getUserroleid() + "&sectionId=" + data.getSectionid() + "''class=" + ViewStatus + "><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/userrolesubsection/update?userroldId=" + data.getUserroleid() + "&sectionId=" + data.getSectionid() + "''class=" + UpdateStatus + "><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Logger.getLogger(UserroleSubsectionController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private UserRoleSubSection getParameters(HttpServletRequest request) throws Exception {
        UserRoleSubSection parameters = new UserRoleSubSection();

        if (!request.getParameter("userroleid").equalsIgnoreCase("--") && !request.getParameter("userroleid").isEmpty()) {
            parameters.setUserroleid(request.getParameter("userroleid"));
        }
        if (!request.getParameter("sectionid").equalsIgnoreCase("--") && !request.getParameter("sectionid").isEmpty()) {
            parameters.setSectionid(request.getParameter("sectionid"));
        }

        return parameters;
    }

    @RequestMapping(value = "/userrolesubsection/view", method = RequestMethod.GET)
    public String userRoleSectionViewPageLoad(@ModelAttribute("userolesubsectionview") UserRoleSubSection rolesubsection,
            @RequestParam(value = "userroldId", required = false) String userRoleId,
            @RequestParam(value = "sectionId", required = false) String sectionID,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            this.setCreateViewComponenets(model);
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USERROLESUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                rolesubsection.setUserroleid(userRoleId);
                rolesubsection.setSectionid(sectionID);
                model.addAttribute("userolesectionview", rolesubsection);
                model.addAttribute("notAssigneSubSection", userrolesubsectiondaoimpl.getNotAssignedSubSections(userRoleId, sectionID));
                model.addAttribute("assigneSubSection", userrolesubsectiondaoimpl.getAssignedSubSections(userRoleId, sectionID));
            } else {
                model.addAttribute("errorMsg", "No privilage to view user role subsection");
            }

        } catch (Exception ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolesubsection/userrolesubsectionview";
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        model.addAttribute("userRoleList", userroledaoimpl.getUserRoleDropdownList());
        model.addAttribute("sectionList", sectiondaoimpl.getSectionDropdownList());

    }

    private UserRoleSubSection checkPrivilage_btn(UserRoleSubSection userrolesubsection, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        userrolesubsection = new UserRoleSubSection();
        userrolesubsection.setSave_btn(true);
        userrolesubsection.setSearch_btn(true);
        userrolesubsection.setEdit_btn(true);
        userrolesubsection.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                userrolesubsection.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                userrolesubsection.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                userrolesubsection.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                userrolesubsection.setView_btn(false);
            }
        }
        return userrolesubsection;
    }

}

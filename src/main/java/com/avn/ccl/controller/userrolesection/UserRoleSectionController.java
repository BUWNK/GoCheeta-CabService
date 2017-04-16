/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.userrolesection;

import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.section.SectionDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.userrole.UserRoleDAOImpl;
import com.avn.ccl.daoimpl.userrolesection.UserRoleSectionDAOImpl;
import com.avn.ccl.model.userrolesection.UserRoleSection;
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
public class UserRoleSectionController {

    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    AuditTraceDAOImpl audit;
    @Autowired
    UserRoleSectionDAOImpl userrolesectiondaoimpl;
    @Autowired
    SectionDAOImpl sectiondaoimpl;
    @Autowired
    UserRoleDAOImpl userroledaoimpl;
    @Autowired
    CommonDAOImpl commendaoimpl;

    @RequestMapping(value = "/userrolesection/create", method = RequestMethod.GET)
    public String userRoleSectionPageLoad(@ModelAttribute("userolesection") UserRoleSection section, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            UserRoleSection userrolesetiondata = checkPrivilage_btn(section, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_CREATE_NEW_USERROLESECTION_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model);
            model.addAttribute("avn_create", userrolesetiondata.isSave_btn());
        } catch (Exception ex) {
            Logger.getLogger(UserRoleSectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolesection/userrolesection";
    }

    @RequestMapping(value = "/userrolesection/created", method = RequestMethod.POST)
    public @ResponseBody
    String insertRoleSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        List<UserRoleSection> list;
        
        String user = (String) session.getAttribute("username");
        String userrolrsectiondata = request.getParameter("section_info");
        UserRoleSection userrolesection = (UserRoleSection) new ObjectMapper().readValue(userrolrsectiondata, UserRoleSection.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_CREATE_NEW_USERROLESECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                list = userrolesectiondaoimpl.insertRoleSection(userrolesection, user);
                jSONObject.put("CODE", "SUCCESS");
                for (UserRoleSection data : list) {
                    audit.insertAuditTrace("User Role Section Create page ", " Create ", " User Role Id " + userrolesection.getUserroleid() + "Section Id's " + data.getSectionid(), data.getSectionid(), user);
                }
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "NO privilage to saving user role section.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(UserRoleSectionController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/userrolesection/search", method = RequestMethod.GET)
    public String userRolesectionSearchPageLoad(@ModelAttribute("userrolesectionsearch") UserRoleSection userrolesection, ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            UserRoleSection userrolesubsetiondata = checkPrivilage_btn(userrolesection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_SEARCH_USERROLESECTION_SUBSECTION_ID), objList);
            model.addAttribute("avn_search", userrolesubsetiondata.isSearch_btn());
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(UserRoleSectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolesection/userrolesectionsearch";
    }

    @RequestMapping(value = "/userrolesection/searched", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        UserRoleSection userolesection = new UserRoleSection();
        UserRoleSection sectionview = checkPrivilage_btn(userolesection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USERROLESECTION_SUBSECTION_ID), objList);
        UserRoleSection sectionedit = checkPrivilage_btn(userolesection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USERROLESECTION_SUBSECTION_ID), objList);
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
        List<UserRoleSection> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_SEARCH_USERROLESECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
                if (Boolean.valueOf(request.getParameter("search"))) {

                    UserRoleSection parameters = this.getParameters(request);

                    iTotalRecords = userrolesectiondaoimpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = userrolesectiondaoimpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (UserRoleSection data : list) {
                            JSONObject object = new JSONObject();
                            object.put("sectionid", data.getSectionid());
                            object.put("sectionDes", data.getSectionDes());
                            object.put("userroleid", data.getUserroleid());
                            object.put("userroleDes", data.getUserroleDes());
                            object.put("lastupdateddatetime", data.getLastupdateddatetime());
                            object.put("createddatetime", data.getCreateddatetime());
                            object.put("createduser", data.getCreateduser());

                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/userrolesection/view?userroldId=" + data.getUserroleid() + "&sectionId=" + data.getSectionid() + "'class=" + ViewStatus + "><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/userrolesection/update?userroldId=" + data.getUserroleid() + "&sectionId=" + data.getSectionid() + "'class=" + ViewStatus + "><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                    }
                }
            } else {

            }

        } catch (Exception e) {
            Logger.getLogger(UserRoleSectionController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private UserRoleSection getParameters(HttpServletRequest request) throws Exception {
        UserRoleSection parameters = new UserRoleSection();

        if (!request.getParameter("userroleid").equalsIgnoreCase("--") && !request.getParameter("userroleid").isEmpty()) {
            parameters.setUserroleid(request.getParameter("userroleid"));
        }
        if (!request.getParameter("sectionid").equalsIgnoreCase("--") && !request.getParameter("sectionid").isEmpty()) {
            parameters.setSectionid(request.getParameter("sectionid"));
        }

        return parameters;
    }

    @RequestMapping(value = "/userrolesection/view", method = RequestMethod.GET)
    public String userRoleSectionViewPageLoad(@ModelAttribute("userolesectionview") UserRoleSection userrolesection,
            @RequestParam(value = "userroldId", required = false) String userRoleId,
            @RequestParam(value = "sectionId", required = false) String sectionID,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            this.setCreateViewComponenets(model);
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USERROLESECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                userrolesection.setUserroleid(userRoleId);
                model.addAttribute("userolesectionview", userrolesection);
                model.addAttribute("notAssigneSection", userrolesectiondaoimpl.getNotAssignedSections(userRoleId));
                model.addAttribute("assigneSection", userrolesectiondaoimpl.getAssignedSections(userRoleId));
            } else {
                model.addAttribute("errorMsg", "No privilage to view user role section");
            }

        } catch (Exception ex) {
            Logger.getLogger(UserRoleSectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolesection/userrolesectionview";
    }

    @RequestMapping(value = "/userrolesection/update", method = RequestMethod.GET)
    public String userRoleSectionUpdatePageLoad(@ModelAttribute("userolesectionupdate") UserRoleSection userrolesection,
            @RequestParam(value = "userroldId", required = false) String userRoleId,
            @RequestParam(value = "sectionId", required = false) String sectionID,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USERROLESECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            UserRoleSection userrolesetiondata = checkPrivilage_btn(userrolesection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USERROLESECTION_SUBSECTION_ID), objList);
            model.addAttribute("avn_update", userrolesetiondata.isEdit_btn());
            this.setCreateViewComponenets(model);
            if (privilage) {
                userrolesection.setUserroleid(userRoleId);
                model.addAttribute("userolesectionupdate", userrolesection);
                model.addAttribute("notAssigneSection", userrolesectiondaoimpl.getNotAssignedSections(userRoleId));
                model.addAttribute("assigneSection", userrolesectiondaoimpl.getAssignedSections(userRoleId));
            } else {
                model.addAttribute("errorMsg", "No privilage to view user role section");
            }

        } catch (Exception ex) {
            Logger.getLogger(UserRoleSectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolesection/userrolesectionupdate";
    }

    @RequestMapping(value = "/userrolesection/updated", method = RequestMethod.POST)
    public @ResponseBody
    String updateSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        String user = (String) session.getAttribute("username");
        String roelsectionDate = request.getParameter("userrolesection_info");
        UserRoleSection rolesection = (UserRoleSection) new ObjectMapper().readValue(roelsectionDate, UserRoleSection.class);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_SUBSECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USERROLESUBSECTION_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            if (privilage) {
                userrolesectiondaoimpl.updateuserRoleSection(rolesection, user);
                jSONObject.put("CODE", "SUCCESS");
                audit.insertAuditTrace("Section Update page ", " Update ", " Section details update", rolesection.getSectionid(), user);
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage update user role section.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(UserRoleSectionController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/userrolesection/ajaxsectionList", method = RequestMethod.POST)
    public @ResponseBody
    String loadSectionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userroleid = request.getParameter("userroleid");
        JSONObject sectionList = new JSONObject();
        sectionList.put("assignsections", userrolesectiondaoimpl.getAssignSectionDropdownListByUserRoleID(userroleid));
        sectionList.put("notassignsections", userrolesectiondaoimpl.getNotAssignSectionDropdownListByUserRoleID(userroleid));
        return sectionList.toString();
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        model.addAttribute("sectionList", sectiondaoimpl.getSectionDropdownList());
        model.addAttribute("userRoleList", userroledaoimpl.getUserRoleDropdownList());
    }

    private UserRoleSection checkPrivilage_btn(UserRoleSection userrolesubsection, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        userrolesubsection = new UserRoleSection();
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

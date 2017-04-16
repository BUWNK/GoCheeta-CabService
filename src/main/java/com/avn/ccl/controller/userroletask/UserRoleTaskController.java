/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.userroletask;

import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.section.SectionDAOImpl;
import com.avn.ccl.daoimpl.sectiontask.SectionTaskDAOImpl;
import com.avn.ccl.daoimpl.task.TaskDAOImpl;
import com.avn.ccl.daoimpl.userrole.UserRoleDAOImpl;
import com.avn.ccl.daoimpl.userroletask.UserRoleTaskDAOImpl;
import com.avn.ccl.model.userrolesubsection.UserRoleSubSection;
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
public class UserRoleTaskController {

    @Autowired
    TaskDAOImpl taskdaoimple;
    @Autowired
    SectionTaskDAOImpl sectiontsakdaoimpl;
    @Autowired
    AuditTraceDAOImpl audit;
    @Autowired
    UserRoleDAOImpl userroledaoimpl;
    @Autowired
    UserRoleTaskDAOImpl userroletaskdaoimpl;
    @Autowired
    SectionDAOImpl sectiondaoimpl;
    @Autowired
    CommonDAOImpl commendaoimpl;

    @RequestMapping(value = "/userroletask/create", method = RequestMethod.GET)
    public String userRoleTaskPageLoad(@ModelAttribute("userroletask") UserRoleTask userroletask, ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            UserRoleTask userroletaskdata = checkPrivilage_btn(userroletask, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_TASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_USERROLETASK_SUBSECTION_ID), objList);
            model.addAttribute("avn_create", userroletaskdata.isSave_btn());
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(UserRoleTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userroletask/userroletask";
    }

    @RequestMapping(value = "/userroletask/userrolesubsection", method = RequestMethod.POST)
    public @ResponseBody
    String loadSubSectionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String Data = request.getParameter("data");
        UserRoleTask sectionData = (UserRoleTask) new ObjectMapper().readValue(Data, UserRoleTask.class);
        String userroleid = sectionData.getUserroleid();
        String sectionid = sectionData.getSectionid();
        JSONObject sectionListAndSubsection = new JSONObject();
        sectionListAndSubsection.put("subsectionList", userroletaskdaoimpl.getUserRoleSubsectionByUserRole(userroleid, sectionid));
        return sectionListAndSubsection.toString();
    }

    @RequestMapping(value = "/userroletask/section", method = RequestMethod.POST)
    public @ResponseBody
    String loadSectionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userroleid = request.getParameter("userroleid");
        JSONObject sectionListAndSubsection = new JSONObject();
        sectionListAndSubsection.put("sectionList", userroletaskdaoimpl.getUserRoleSectionByUserRole(userroleid));
        return sectionListAndSubsection.toString();
    }

    @RequestMapping(value = "/userroletask/tasklist", method = RequestMethod.POST)
    public @ResponseBody
    String loadTaskList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userroleid = request.getParameter("userroleid");
        UserRoleTask rolesubsectionData = (UserRoleTask) new ObjectMapper().readValue(userroleid, UserRoleTask.class);
        JSONObject taskList = new JSONObject();
        taskList.put("assignTask", userroletaskdaoimpl.getAssignedTaskList(rolesubsectionData));
        taskList.put("notAssignTask", userroletaskdaoimpl.getNotAssignedTaskList(rolesubsectionData));
        taskList.put("userrolesubsectionid", userroletaskdaoimpl.getUserrolesubsetionid(rolesubsectionData));
        return taskList.toString();
    }

    @RequestMapping(value = "/userroletask/created", method = RequestMethod.POST)
    public @ResponseBody
    String saveSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        String user = (String) session.getAttribute("username");
        List<UserRoleTask> list;
        String userrorlesectionData = request.getParameter("uerroletask_info");
        UserRoleTask rolesubsectionData = (UserRoleTask) new ObjectMapper().readValue(userrorlesectionData, UserRoleTask.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_TASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_USERROLETASK_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                list = userroletaskdaoimpl.insertUserRoleTask(rolesubsectionData, user);
                jSONObject.put("CODE", "SUCCESS");
                for (UserRoleTask data : list) {
                    audit.insertAuditTrace("User Role Task Add ", " Add ", " User Role subsection Id " + rolesubsectionData.getUserrolesubsectionid() + "Task Id's " + data.getTaskid(), data.getTaskid(), user);
                }
                response.reset();
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to Saving User Role Task.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving User Role Task.");
            Logger.getLogger(UserRoleTaskController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/userroletask/search", method = RequestMethod.GET)
    public String userRoleTaaskSearchPageLoad(@ModelAttribute("userroletasksearch") UserRoleTask userroletask, ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            UserRoleTask userroletaskdata = checkPrivilage_btn(userroletask, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_TASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_SEARCH_USERROLETASK_SUBSECTION_ID), objList);
            model.addAttribute("avn_search", userroletaskdata.isSearch_btn());
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(UserRoleTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userroletask/userroletasksearch";
    }

    @RequestMapping(value = "/userroletask/searched", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        UserRoleTask userolesubsection = new UserRoleTask();
        UserRoleTask userroletaskview = checkPrivilage_btn(userolesubsection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_TASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USERROLETASK_SUBSECTION_ID), objList);
        UserRoleTask userroletaskedite = checkPrivilage_btn(userolesubsection, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_TASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USERROLETASK_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
        if (userroletaskview.isView_btn()) {
            ViewStatus = "disabled";
        } else {
            ViewStatus = "active";
        }
        if (userroletaskedite.isEdit_btn()) {
            UpdateStatus = "disabled";
        } else {
            UpdateStatus = "active";
        }
        List<UserRoleTask> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_TASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_SEARCH_USERROLETASK_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
                if (Boolean.valueOf(request.getParameter("search"))) {

                    UserRoleTask parameters = this.getParameters(request);

                    iTotalRecords = userroletaskdaoimpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = userroletaskdaoimpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (UserRoleTask data : list) {
                            JSONObject object = new JSONObject();
                            object.put("sectionid", data.getSectionid());
                            object.put("sectionDes", data.getSectionDes());
                            object.put("subsectionid", data.getSubsectionid());
                            object.put("subsectionDes", data.getSubsectionDes());
                            object.put("userroleid", data.getUserroleid());
                            object.put("userroleDes", data.getUserroleDes());
                            object.put("taskid", data.getTaskid());
                            object.put("taskDes", data.getTaskDes());
                            object.put("lastupdateddatetime", data.getLastupdatedtime());
                            object.put("createddatetime", data.getCreateddatetime());
                            object.put("createduser", data.getCreateduser());

                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/userroletask/view?userroldId=" + data.getUserroleid() + "&sectionId=" + data.getSectionid() + "&subsectionId=" + data.getSubsectionid() + "''class=" + ViewStatus + "><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/userroletask/update?userroldId=" + data.getUserroleid() + "&sectionId=" + data.getSectionid() + "&subsectionId=" + data.getSubsectionid() + "''class=" + UpdateStatus + "><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                    }
                }

            }
        } catch (Exception e) {
            Logger.getLogger(UserRoleTaskController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private UserRoleTask getParameters(HttpServletRequest request) throws Exception {
        UserRoleTask parameters = new UserRoleTask();

        if (!request.getParameter("userroleid").equalsIgnoreCase("--") && !request.getParameter("userroleid").isEmpty()) {
            parameters.setUserroleid(request.getParameter("userroleid"));
        }

        return parameters;
    }

    @RequestMapping(value = "/userroletask/view", method = RequestMethod.GET)
    public String userRoleSectionViewPageLoad(@ModelAttribute("userroletaskview") UserRoleTask userroletask,
            @RequestParam(value = "userroldId", required = false) String userRoleId,
            @RequestParam(value = "sectionId", required = false) String sectioid,
            @RequestParam(value = "subsectionId", required = false) String subsectionid,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            this.setCreateViewComponenets(model);
//            userroletask.setUserroleid(userRoleId);
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_TASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USERROLETASK_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                userroletask.setSectionid(sectioid);
                userroletask.setSubsectionid(subsectionid);
                userroletask.setUserroleid(userRoleId);
                model.addAttribute("userroletaskview", userroletaskdaoimpl.getUserRoleTask(userRoleId));
                model.addAttribute("notAssigneTask", userroletaskdaoimpl.getNotAssignedTask(userroletask));
                model.addAttribute("assigneTask", userroletaskdaoimpl.getAssignedTask(userroletask));
                model.put("subsectionList", userroletaskdaoimpl.getSubSectionDropdownList(userRoleId, sectioid));
                model.addAttribute("userroletaskview", userroletask);
            } else {
                model.addAttribute("errorMsg", "No privilage to view user role task.");
            }

        } catch (Exception ex) {
            Logger.getLogger(UserRoleTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userroletask/userroletaskview";
    }

    @RequestMapping(value = "/userroletask/update", method = RequestMethod.GET)
    public String userRoleSectionUpdatePageLoad(@ModelAttribute("userroletaskupdate") UserRoleTask userroletask,
            @RequestParam(value = "userroldId", required = false) String userRoleId,
            @RequestParam(value = "sectionId", required = false) String sectioid,
            @RequestParam(value = "subsectionId", required = false) String subsectionid,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            UserRoleTask userroletaskdata = checkPrivilage_btn(userroletask, String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_TASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USERROLETASK_SUBSECTION_ID), objList);
            model.addAttribute("avn_update", userroletaskdata.isEdit_btn());
            userroletask.setSectionid(sectioid);
            userroletask.setSubsectionid(subsectionid);
            userroletask.setUserroleid(userRoleId);
            this.setCreateViewComponenets(model);
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_TASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USERROLETASK_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                model.addAttribute("userroletaskupdate", userroletaskdaoimpl.getUserRoleTask(userRoleId));
                model.addAttribute("notAssigneTask", userroletaskdaoimpl.getNotAssignedTask(userroletask));
                model.addAttribute("assigneTask", userroletaskdaoimpl.getAssignedTask(userroletask));
                model.addAttribute("userrolesubsectionid", userroletaskdaoimpl.getUserrolesubsetionid(userroletask));
                model.put("subsectionList", userroletaskdaoimpl.getSubSectionDropdownList(userRoleId, sectioid));
                model.addAttribute("userroletaskupdate", userroletask);
            } else {
                model.addAttribute("errorMsg", "No privilage to view user role task");
            }

        } catch (Exception ex) {
            Logger.getLogger(UserRoleTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userroletask/userroletaskupdate";
    }

    @RequestMapping(value = "/userroletask/updated", method = RequestMethod.POST)
    public @ResponseBody
    String updateSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        String user = (String) session.getAttribute("username");
        String userroletaskData = request.getParameter("userroletask_update");
        UserRoleTask userroletask = (UserRoleTask) new ObjectMapper().readValue(userroletaskData, UserRoleTask.class);
        JSONObject jSONObject = new JSONObject();
        List<UserRoleTask> list;
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USERROLE_TASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_SEARCH_USERROLETASK_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            if (privilage) {
                list = userroletaskdaoimpl.updateuserRoleTask(userroletask, user);
                jSONObject.put("CODE", "SUCCESS");
                for (UserRoleTask data : list) {
                    audit.insertAuditTrace("User Role Task Add ", " Add ", " User Role subsection Id " + userroletask.getUserrolesubsectionid() + "Task Id's " + data.getTaskid(), data.getTaskid(), user);
                }
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to  Saving Userrole Task.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(UserRoleTaskController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        Map<String, String> list;
        model.addAttribute("taskList", taskdaoimple.getTaskDropdownList());
        model.addAttribute("userRoleList", userroledaoimpl.getUserRoleDropdownList());
        model.addAttribute("sectionList", sectiondaoimpl.getSectionDropdownList());
        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        model.put("roleSectionList", list);
    }

    private UserRoleTask checkPrivilage_btn(UserRoleTask userroletask, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        userroletask = new UserRoleTask();
        userroletask.setSave_btn(true);
        userroletask.setSearch_btn(true);
        userroletask.setEdit_btn(true);
        userroletask.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                userroletask.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                userroletask.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                userroletask.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                userroletask.setView_btn(false);
            }
        }
        return userroletask;
    }

}

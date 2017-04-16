/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.sectiontask;

import com.avn.ccl.controller.section.SectionController;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.section.SectionDAOImpl;
import com.avn.ccl.daoimpl.sectiontask.SectionTaskDAOImpl;
import com.avn.ccl.daoimpl.task.TaskDAOImpl;
import com.avn.ccl.model.sectiontask.SectionTask;
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
public class SectionTaskController {

    @Autowired
    SectionDAOImpl sectiondaoimpl;
    @Autowired
    TaskDAOImpl taskdaoimple;
    @Autowired
    SectionTaskDAOImpl sectiontsakdaoimpl;
    @Autowired
    AuditTraceDAOImpl audit;
    @Autowired
    CommonDAOImpl commendaoimpl;

    @RequestMapping(value = "/sectiontask/create", method = RequestMethod.GET)
    public String subSectionPageLoad(@ModelAttribute("sectiontask") SectionTask sectiontask, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            SectionTask sectiontaskData = checkPrivilage_btn(sectiontask, String.valueOf(MasterDataVarList.CCL_CODE_SECTIONTASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_SECTIONTASK_SUBSECTION_ID), objList);
            model.addAttribute("avn_create", sectiontaskData.isSave_btn());
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(SectionTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sectiontask/sectiontask";
    }

    @RequestMapping(value = "/sectiontask/addtasked", method = RequestMethod.POST)
    public @ResponseBody
    String insertRoleSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        List<SectionTask> list;
        
        String user = (String) session.getAttribute("username");
        String userrolrsectiondata = request.getParameter("sectiontask_info");
        SectionTask sectiontask = (SectionTask) new ObjectMapper().readValue(userrolrsectiondata, SectionTask.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SECTIONTASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_SECTIONTASK_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                list = sectiontsakdaoimpl.insertSectionTask(sectiontask, user);
                jSONObject.put("CODE", "SUCCESS");
                for (SectionTask data : list) {
                    audit.insertAuditTrace("Section Task Assign page ", " Assign ", "Section Id's " + data.getSectionid() + " Task Id" + data.getTaskid(), data.getSectionid(), user);
                }
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to saving section task.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(SectionTaskController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/sectiontask/search", method = RequestMethod.GET)
    public String userRolesectionSearchPageLoad(@ModelAttribute("sectiontasksearch") SectionTask sectiontask, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            SectionTask sectiontaskData = checkPrivilage_btn(sectiontask, String.valueOf(MasterDataVarList.CCL_CODE_SECTIONTASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_SECTIONTASK_SUBSECTION_ID), objList);
            model.addAttribute("avn_search", sectiontaskData.isSearch_btn());
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(SectionTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sectiontask/sectiontasksearch";
    }

    @RequestMapping(value = "/sectiontask/searched", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        SectionTask sectiontask = new SectionTask();
        SectionTask userroletaskview = checkPrivilage_btn(sectiontask, String.valueOf(MasterDataVarList.CCL_CODE_SECTIONTASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_SECTIONTASK_SUBSECTION_ID), objList);
        SectionTask userroletaskedite = checkPrivilage_btn(sectiontask, String.valueOf(MasterDataVarList.CCL_CODE_SECTIONTASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_SECTIONTASK_SUBSECTION_ID), objList);
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
        List<SectionTask> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SECTIONTASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_SECTIONTASK_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
                if (Boolean.valueOf(request.getParameter("search"))) {

                    SectionTask parameters = this.getParameters(request);

                    iTotalRecords = sectiontsakdaoimpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = sectiontsakdaoimpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (SectionTask data : list) {
                            JSONObject object = new JSONObject();
                            object.put("sectionid", data.getSectionid());
                            object.put("sectionDes", data.getSectionDes());
                            object.put("taskid", data.getTaskid());
                            object.put("taskDes", data.getTaskDes());
                            object.put("lastupdateddatetime", data.getLastupdatedtime());
                            object.put("createddatetime", data.getCreateddatetime());
                            object.put("createduser", data.getCreateduser());

                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/sectiontask/view?sectionId=" + data.getSectionid() + "'class=" + ViewStatus + "><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/sectiontask/update?sectionId=" + data.getSectionid() + "'class=" + UpdateStatus + "><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Logger.getLogger(SectionTaskController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private SectionTask getParameters(HttpServletRequest request) throws Exception {
        SectionTask parameters = new SectionTask();

        if (!request.getParameter("sectionid").equalsIgnoreCase("--") && !request.getParameter("sectionid").isEmpty()) {
            parameters.setSectionid(request.getParameter("sectionid"));
        }

        return parameters;
    }

    @RequestMapping(value = "/sectiontask/view", method = RequestMethod.GET)
    public String userRoleSectionViewPageLoad(@ModelAttribute("sectiontaskview") SectionTask sectiontask,
            @RequestParam(value = "sectionId", required = false) String sectionID,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            this.setCreateViewComponenets(model);
            sectiontask.setSectionid(sectionID);
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SECTIONTASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_SECTIONTASK_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                model.addAttribute("sectiontaskview", sectiontask);
                model.addAttribute("notAssigneTask", sectiontsakdaoimpl.getNotAssignedTask(sectionID));
                model.addAttribute("assigneTask", sectiontsakdaoimpl.getAssignedTask(sectionID));
            } else {
                model.addAttribute("errorMsg", "No privilage to view section task.");
            }

        } catch (Exception ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sectiontask/sectiontaskview";
    }

    @RequestMapping(value = "/sectiontask/update", method = RequestMethod.GET)
    public String userRoleSectionUpdatePageLoad(@ModelAttribute("sectiontaskupdate") SectionTask sectiontask,
            @RequestParam(value = "sectionId", required = false) String sectionID,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            this.setCreateViewComponenets(model);
            sectiontask.setSectionid(sectionID);
            SectionTask sectiontaskData = checkPrivilage_btn(sectiontask, String.valueOf(MasterDataVarList.CCL_CODE_SECTIONTASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_SECTIONTASK_SUBSECTION_ID), objList);
            model.addAttribute("avn_update", sectiontaskData.isEdit_btn());
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SECTIONTASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_SECTIONTASK_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
           if(privilage){
             model.addAttribute("sectiontaskupdate", sectiontask);
            model.addAttribute("notAssigneTask", sectiontsakdaoimpl.getNotAssignedTask(sectionID));
            model.addAttribute("assigneTask", sectiontsakdaoimpl.getAssignedTask(sectionID));  
           }else{
             model.addAttribute("errorMsg", "No privilage to view section task");    
           }
            
        } catch (Exception ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sectiontask/sectiontaskupdate";
    }

    @RequestMapping(value = "/sectiontask/updated", method = RequestMethod.POST)
    public @ResponseBody
    String updateSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        String user = (String) session.getAttribute("username");
        String sectiontaskData = request.getParameter("sectiontaskupdate_info");
        SectionTask sectiontask = (SectionTask) new ObjectMapper().readValue(sectiontaskData, SectionTask.class);
        JSONObject jSONObject = new JSONObject();
        List<SectionTask> list;
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_SECTIONTASK_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_SECTIONTASK_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            if (privilage) {
                list = sectiontsakdaoimpl.insertSectionTask(sectiontask, user);
                jSONObject.put("CODE", "SUCCESS");
                for (SectionTask data : list) {
                    audit.insertAuditTrace("Section Task Update page ", " Update ", "Section Id's " + data.getSectionid() + " Task Id" + data.getTaskid(), data.getSectionid(), user);
                }
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No Privilage to Saving Section Task.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/sectiontask/ajaxtasklist", method = RequestMethod.POST)
    public @ResponseBody
    String loadTaskList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sectiontasid = request.getParameter("sectiontask");
        JSONObject notAssigntask = new JSONObject();
        notAssigntask.put("notAssigntask", sectiontsakdaoimpl.getNotAssignSectionDropdownListByUserRoleID(sectiontasid));
        notAssigntask.put("assigntask", sectiontsakdaoimpl.getAssignSectionDropdownListByUserRoleID(sectiontasid));
        return notAssigntask.toString();
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        model.addAttribute("sectionList", sectiondaoimpl.getSectionDropdownList());
//        model.addAttribute("taskList", taskdaoimple.getTaskDropdownList());
    }

    private SectionTask checkPrivilage_btn(SectionTask sectiontask, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        sectiontask = new SectionTask();
        sectiontask.setSave_btn(true);
        sectiontask.setSearch_btn(true);
        sectiontask.setEdit_btn(true);
        sectiontask.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                sectiontask.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                sectiontask.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                sectiontask.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                sectiontask.setView_btn(false);
            }
        }
        return sectiontask;
    }

}

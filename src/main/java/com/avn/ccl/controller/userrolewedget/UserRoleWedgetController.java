/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.userrolewedget;

import com.avn.ccl.controller.wedget.WedgetController;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.userrole.UserRoleDAOImpl;
import com.avn.ccl.daoimpl.userrolewedget.UserRoleWedgetDAOImpl;
import com.avn.ccl.daoimpl.wedget.WedgetDAOImpl;
import com.avn.ccl.daoimpl.wedgetwidth.WedgetWidthDAOImpl;
import com.avn.ccl.model.userrolewedget.UserRoleWedget;
import com.avn.ccl.model.wedget.Wedget;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
 * @Author : ISURU
 * @Document : UserRoleWedgetController
 * @Created on : May 4, 2016, 8:28:04 PM
 */
@Controller
public class UserRoleWedgetController {

    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    AuditTraceDAOImpl audit;
    @Autowired
    WedgetDAOImpl wedgetDAOImpl;
    @Autowired
    UserRoleDAOImpl userroledaoimpl;
    @Autowired
    CommonDAOImpl commendaoimpl;
    @Autowired
    WedgetWidthDAOImpl wedgetwidthdaoimpl;
    @Autowired
    UserRoleWedgetDAOImpl userrolewedgetdaoimpl;

    @RequestMapping(value = "/userrolewedget/add", method = RequestMethod.GET)
    public String sectionPageLoad(@ModelAttribute("userrolewedget") UserRoleWedget userrolewedget, HttpSession session, ModelMap model) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            UserRoleWedget userrolewedgetdata = checkPrivilage_btn(userrolewedget, String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_USER_ROLE_WEDGET_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model);
            model.addAttribute("avn_create", userrolewedgetdata.isSave_btn());
        } catch (Exception ex) {
            Logger.getLogger(WedgetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolewedget/userrolewedget";
    }

    @RequestMapping(value = "/userrolewedget/created", method = RequestMethod.POST)
    public @ResponseBody
    String saveSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        String user = (String) session.getAttribute("username");
        String userrolewedgetdata = request.getParameter("userrolewedget_info");
        UserRoleWedget userrolewedget = (UserRoleWedget) new ObjectMapper().readValue(userrolewedgetdata, UserRoleWedget.class);
        JSONObject jSONObject = new JSONObject();

        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_USER_ROLE_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                userrolewedgetdaoimpl.insertUserRoleWedget(userrolewedget, user);
                jSONObject.put("CODE", "SUCCESS");
                audit.insertAuditTrace("User Role Weget Create page ", " Create ", " User role details add", String.valueOf(userrolewedget.getWedgetid()), user);
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to create section");
            }
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Section.");
            Logger.getLogger(WedgetController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/userrolewedget/search", method = RequestMethod.GET)
    public String sectionSearchPageLoad(@ModelAttribute("wedgetsearch") UserRoleWedget wedget, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            UserRoleWedget userrolewedget = checkPrivilage_btn(wedget, String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_ROLE_WEDGET_SUBSECTION_ID), objList);
            model.addAttribute("avn_search", userrolewedget.isSearch_btn());
        } catch (Exception e) {
        }
        return "userrolewedget/userrolewedgetsearch";
    }

    @RequestMapping(value = "/userrolewedget/searched", method = RequestMethod.POST)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        UserRoleWedget userroledata = new UserRoleWedget();
        UserRoleWedget wedgetview = checkPrivilage_btn(userroledata, String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USER_ROLE_WEDGET_SUBSECTION_ID), objList);
        UserRoleWedget wedgetedit = checkPrivilage_btn(userroledata, String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USER_ROLE_WEDGET_SUBSECTION_ID), objList);
        UserRoleWedget wedgetdelete = checkPrivilage_btn(userroledata, String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_DELETE_USER_ROLE_WEDGET_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
        String DeleteStatus = "";
        if (wedgetview.isView_btn()) {
            ViewStatus = "disabled";
        } else {
            ViewStatus = "active";
        }
        if (wedgetedit.isEdit_btn()) {
            UpdateStatus = "disabled";
        } else {
            UpdateStatus = "active";
        }
        if (wedgetdelete.isDelete_btn()) {
            DeleteStatus = "disabled";
        } else {
            DeleteStatus = "active";
        }
        List<UserRoleWedget> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_USER_ROLE_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
                if (Boolean.valueOf(request.getParameter("search"))) {

                    UserRoleWedget parameters = this.getParameters(request);

                    iTotalRecords = userrolewedgetdaoimpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = userrolewedgetdaoimpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (UserRoleWedget data : list) {
                            JSONObject object = new JSONObject();
                            object.put("wedgetdescription", data.getWedgetdescription());
                            object.put("userroledescription", data.getUserroledescription());
                            object.put("sortid", data.getSortid());
                            object.put("createddatetime", data.getCreateddatetime());
                            object.put("lastupdatedatetime", data.getLastupdatedatetime());
                            object.put("createduser", data.getCreateduser());
                            object.put("statusdes", data.getStatusdes());
                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/userrolewedget/view?wedgetId=" + data.getWedgetid() + "&userroleId=" + data.getUserroleid() + "' class=" + ViewStatus + " ><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/userrolewedget/update?wedgetId=" + data.getWedgetid() + "&userroleId=" + data.getUserroleid() + "'class=" + UpdateStatus + " ><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/userrolewedget/delete?wedgetId=" + data.getWedgetid() + "&userroleId=" + data.getUserroleid() + "&sortId=" + data.getSortid() + "'class=" + DeleteStatus + " ><i class=\"fa fa-trash-o\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                    }
                }
            } else {

            }

        } catch (Exception e) {
            Logger.getLogger(WedgetController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/userrolewedget/view", method = RequestMethod.GET)
    public String sectionViewPageLoad(@ModelAttribute("wedgetView") Wedget wedget,
            @RequestParam(value = "wedgetId", required = false) String wedgetId,
            @RequestParam(value = "userroleId", required = false) String userroleId,
            ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USER_ROLE_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                this.setCreateViewComponenets(model);
                UserRoleWedget wedgetData = userrolewedgetdaoimpl.getWedgetByWedgetId(wedgetId, userroleId);
                model.addAttribute("wedgetView", wedgetData);
            } else {
                model.addAttribute("errorMsg", "No privilage to view wedget");
            }

        } catch (Exception ex) {
            Logger.getLogger(WedgetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolewedget/userrolewedgetview";
    }

    @RequestMapping(value = "/userrolewedget/update", method = RequestMethod.GET)
    public String sectionUpdatePageLoad(@ModelAttribute("wedgetUpdate") UserRoleWedget wedget,
            @RequestParam(value = "wedgetId", required = false) String wedgetId,
            @RequestParam(value = "userroleId", required = false) String userroleId,
            ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_USER_ROLE_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            UserRoleWedget wedgetedit = checkPrivilage_btn(wedget, String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USER_ROLE_WEDGET_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model);
            model.addAttribute("avn_update", wedgetedit.isEdit_btn());
            if (privilage) {
                Map<String, String> list2 = wedgetDAOImpl.getWedgetDropdownList();
                Map<String, String> list = new LinkedHashMap<>();
                list.put("", "-- Select --");
                for (int i = 0; i < list2.size(); i++) {
                    list.put("" + (i + 1) + "", "" + (i + 1) + "");
                }
                model.addAttribute("sortidList", list);
                UserRoleWedget wedgetData = userrolewedgetdaoimpl.getWedgetByWedgetId(wedgetId, userroleId);
                model.addAttribute("wedgetUpdate", wedgetData);
            } else {
                model.addAttribute("errorMsg", "No privilage to view wedget");
            }

        } catch (Exception ex) {
            Logger.getLogger(WedgetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolewedget/userrolewedgetupdate";
    }

    @RequestMapping(value = "/userrolewedget/updated", method = RequestMethod.POST)
    public @ResponseBody
    String updateSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        String user = (String) session.getAttribute("username");
        String userrolewedgetdata = request.getParameter("userrolewedget_info");
        UserRoleWedget wedget = (UserRoleWedget) new ObjectMapper().readValue(userrolewedgetdata, UserRoleWedget.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_USER_ROLE_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            if (privilage) {
                userrolewedgetdaoimpl.updateWedget(wedget, user);
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to update case");
            }
            jSONObject.put("CODE", "SUCCESS");
            audit.insertAuditTrace("Section Update page ", " Update ", " Wedget details update", String.valueOf(wedget.getWedgetid()), user);
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Wedget.");
            Logger.getLogger(WedgetController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    private UserRoleWedget getParameters(HttpServletRequest request) throws Exception {
        UserRoleWedget parameters = new UserRoleWedget();

        if (request.getParameter("searchoption") != null && !request.getParameter("searchoption").isEmpty()) {
            parameters.setSearchoption(request.getParameter("searchoption"));
        }
        if (request.getParameter("input") != null && !request.getParameter("input").isEmpty()) {
            parameters.setInput(request.getParameter("input"));
        }

        return parameters;
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        model.addAttribute("wedgetWidthList", wedgetwidthdaoimpl.getWedgetWidthDropdownList());
        model.addAttribute("userRoleList", userroledaoimpl.getUserRoleDropdownList());
        model.addAttribute("wedgetList", wedgetDAOImpl.getWedgetDropdownList());
        model.addAttribute("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_DEFAULT));

    }

    @RequestMapping(value = "/asd/asd", method = RequestMethod.POST)
    public @ResponseBody
    String loadSortId(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        System.out.println("he he its worke now");
        String wedgetData = request.getParameter("userroleid");
        UserRoleWedget rolesubsectionData = (UserRoleWedget) new ObjectMapper().readValue(wedgetData, UserRoleWedget.class);
        JSONObject sortidList = new JSONObject();
        Map<String, String> list2 = wedgetDAOImpl.getWedgetDropdownList();
        List<String> sortidlist = userrolewedgetdaoimpl.getInsertSortIdList(rolesubsectionData.getUserroleid());
        List<String> list = new LinkedList<>();
        for (int i = 0; i < list2.size(); i++) {
            list.add("" + (i + 1) + "");
        }

        if (sortidlist.size() > 0) {
            for (int i = 0; i < sortidlist.size(); i++) {
                list.remove(sortidlist.get(i));
            }
        }
        sortidList.put("sortIdList", list);

        return sortidList.toString();
    }

    @RequestMapping(value = "/wedget/list", method = RequestMethod.POST)
    public @ResponseBody
    String WedgetListId(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        System.out.println("he he its worke now");
        String wedgetData = request.getParameter("userroleid");
        UserRoleWedget rolesubsectionData = (UserRoleWedget) new ObjectMapper().readValue(wedgetData, UserRoleWedget.class);
        JSONObject wedgetList = new JSONObject();
        wedgetList.put("wedgetList", wedgetDAOImpl.getWedgetDropdownListNew(rolesubsectionData.getUserroleid()));

        return wedgetList.toString();
    }

    @RequestMapping(value = "/userrolewedget/delete", method = RequestMethod.GET)
    public String userroleWedgetDelete(@ModelAttribute("wedgetsearch") Wedget wedget,
            @RequestParam(value = "wedgetId", required = false) String wedgetId,
            @RequestParam(value = "userroleId", required = false) String userroleId,
            @RequestParam(value = "sortId", required = false) String sortId,
            ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_USER_ROLE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_DELETE_USER_ROLE_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_DELETE, objList);
            if (privilage) {
                userrolewedgetdaoimpl.deleteWedget(wedgetId, userroleId, sortId);
                model.addAttribute("successMsg", "Sucsessfuly deleted wedget");
            } else {
                model.addAttribute("errorMsg", "No privilage to view wedget");
            }

        } catch (Exception ex) {
            Logger.getLogger(WedgetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "userrolewedget/userrolewedgetsearch";
    }

    private UserRoleWedget checkPrivilage_btn(UserRoleWedget userrolewedget, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        userrolewedget = new UserRoleWedget();
        userrolewedget.setSave_btn(true);
        userrolewedget.setSearch_btn(true);
        userrolewedget.setEdit_btn(true);
        userrolewedget.setView_btn(true);
        userrolewedget.setDelete_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                userrolewedget.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                userrolewedget.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                userrolewedget.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                userrolewedget.setView_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DELETE))) {
                userrolewedget.setDelete_btn(false);
            }
        }
        return userrolewedget;
    }
}

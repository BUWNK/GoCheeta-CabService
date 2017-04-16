/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.wedget;

import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.userrole.UserRoleDAOImpl;
import com.avn.ccl.daoimpl.wedget.WedgetDAOImpl;
import com.avn.ccl.model.wedget.Wedget;
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
 * @Author : ISURU
 * @Document : WedgetController
 * @Created on : May 3, 2016, 2:15:06 PM
 */
@Controller
public class WedgetController {

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

    @RequestMapping(value = "/wedget/add", method = RequestMethod.GET)
    public String sectionPageLoad(@ModelAttribute("wedget") Wedget wedgets, HttpSession session, ModelMap model) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Wedget wedgetdata = checkPrivilage_btn(wedgets, String.valueOf(MasterDataVarList.CCL_CODE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_WEDGET_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model);
            model.addAttribute("avn_create", wedgetdata.isSave_btn());
        } catch (Exception ex) {
            Logger.getLogger(WedgetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "wedget/wedgetdetailsinsert";
    }

    @RequestMapping(value = "/wedget/created", method = RequestMethod.POST)
    public @ResponseBody
    String saveSections(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        String user = (String) session.getAttribute("username");
        String wedgetdata = request.getParameter("wedget_info");
        Wedget wedget = (Wedget) new ObjectMapper().readValue(wedgetdata, Wedget.class);
        JSONObject jSONObject = new JSONObject();

        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                Long wedgetID = wedgetDAOImpl.insertWedget(wedget, user);
                jSONObject.put("CODE", "SUCCESS");
                audit.insertAuditTrace("Section Create page ", " Create ", " Section details added", String.valueOf(wedgetID), user);
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

    @RequestMapping(value = "/wedget/search", method = RequestMethod.GET)
    public String sectionSearchPageLoad(@ModelAttribute("wedgetsearch") Wedget wedget, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Wedget sectiondata = checkPrivilage_btn(wedget, String.valueOf(MasterDataVarList.CCL_CODE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_WEDGET_SUBSECTION_ID), objList);
            model.addAttribute("avn_create", sectiondata.isSave_btn());
        } catch (Exception e) {
        }
        return "wedget/wedgetsearch";
    }

    @RequestMapping(value = "/wedget/searched", method = RequestMethod.POST)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        Wedget sectiondata = new Wedget();
        Wedget wedgetview = checkPrivilage_btn(sectiondata, String.valueOf(MasterDataVarList.CCL_CODE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_WEDGET_SUBSECTION_ID), objList);
        Wedget wedgetedit = checkPrivilage_btn(sectiondata, String.valueOf(MasterDataVarList.CCL_CODE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_WEDGET_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
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
        List<Wedget> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }
                if (Boolean.valueOf(request.getParameter("search"))) {

                    Wedget parameters = this.getParameters(request);

                    iTotalRecords = wedgetDAOImpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = wedgetDAOImpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (Wedget data : list) {
                            JSONObject object = new JSONObject();
                            object.put("wedgetid", data.getWedgetid());
                            object.put("description", data.getDescription());
                            object.put("createddatetime", data.getCreateddatetime());
                            object.put("lastupdatedatetime", data.getLastupdatedatetime());
                            object.put("createduser", data.getCreateduser());
                            object.put("statusdes", data.getStatusdes());
                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/wedget/view?wedgetId=" + data.getWedgetid() + "' class=" + ViewStatus + " ><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/wedget/update?wedgetId=" + data.getWedgetid() + "'class=" + UpdateStatus + " ><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
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

    @RequestMapping(value = "/wedget/view", method = RequestMethod.GET)
    public String wedgetViewPageLoad(@ModelAttribute("wedgetView") Wedget wedget, @RequestParam(value = "wedgetId", required = false) String wedgetId, ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                this.setCreateViewComponenets(model);
                Wedget wedgetData = wedgetDAOImpl.getWedgetByWedgetId(wedgetId);
                model.addAttribute("wedgetView", wedgetData);
            } else {
                model.addAttribute("errorMsg", "No privilage to view wedget");
            }

        } catch (Exception ex) {
            Logger.getLogger(WedgetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "wedget/wedgetview";
    }

    @RequestMapping(value = "/wedget/update", method = RequestMethod.GET)
    public String wedgetUpdatePageLoad(@ModelAttribute("wedgetUpdate") Wedget wedget, @RequestParam(value = "wedgetId", required = false) String wedgetId, ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_VIEW_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            Wedget wedgetedit = checkPrivilage_btn(wedget, String.valueOf(MasterDataVarList.CCL_CODE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_WEDGET_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model);
            model.addAttribute("avn_update", wedgetedit.isEdit_btn());
            if (privilage) {
                Wedget wedgetData = wedgetDAOImpl.getWedgetByWedgetId(wedgetId);
                model.addAttribute("wedgetUpdate", wedgetData);
            } else {
                model.addAttribute("errorMsg", "No privilage to view wedget");
            }

        } catch (Exception ex) {
            Logger.getLogger(WedgetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "wedget/wedgetupdate";
    }

    @RequestMapping(value = "/wedget/updated", method = RequestMethod.POST)
    public @ResponseBody
    String wedgetUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        String user = (String) session.getAttribute("username");
        String wedgetdata = request.getParameter("wedget_info");
        Wedget wedget = (Wedget) new ObjectMapper().readValue(wedgetdata, Wedget.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_WEDGET_ID), String.valueOf(MasterDataVarList.CCL_CODE_CCL_CODE_EDIT_WEDGET_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            if (privilage) {
                wedgetDAOImpl.updateWedget(wedget, user);
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

    private Wedget getParameters(HttpServletRequest request) throws Exception {
        Wedget parameters = new Wedget();

        if (request.getParameter("searchoption") != null && !request.getParameter("searchoption").isEmpty()) {
            parameters.setSearchoption(request.getParameter("searchoption"));
        }
        if (request.getParameter("input") != null && !request.getParameter("input").isEmpty()) {
            parameters.setInput(request.getParameter("input"));
        }

        return parameters;
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        model.addAttribute("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_DEFAULT));

    }

    private Wedget checkPrivilage_btn(Wedget wedgets, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        wedgets = new Wedget();
        wedgets.setSave_btn(true);
        wedgets.setSearch_btn(true);
        wedgets.setEdit_btn(true);
        wedgets.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                wedgets.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                wedgets.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                wedgets.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                wedgets.setView_btn(false);
            }
        }
        return wedgets;
    }

}

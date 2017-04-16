/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.hierarchytarget;

import com.avn.ccl.model.hierarchytarget.HierarchyTarget;
import com.avn.ccl.service.hierarchytarget.HierarchyTargetService;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author : Roshen Dilshan
 * @Document : HierarchyTargetController
 * @Created on : Feb 23, 2017, 9:25:00 AM
 */
@Controller
public class HierarchyTargetController {

    @Autowired
    HierarchyTargetService hierarchyTargetService;

    @RequestMapping(value = "/hierarchytarget/sethierarchytarget", method = RequestMethod.GET)
    public String pageView(Map<String, Object> model) {
        return "hierarchytarget/sethierarchytarget";
    }

    @RequestMapping(value = "/hierarchytarget/tabledata", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request) throws Exception {
        List<JSONObject> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {

            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }

            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }

            iTotalRecords = hierarchyTargetService.getTableDataCount();
            iTotalDisplayRecords = iTotalRecords;
            if (iTotalRecords > 0) {
                list = hierarchyTargetService.getTableData(param.iDisplayLength, param.iDisplayStart);
//                    for (JSONObject jSONObject : list) {
//                        jSONObject.put("action", String.format(jSONObject.getString("action"), request.getContextPath(), request.getContextPath()));
//                    }
                rows = new JSONArray(list);
            }
        } catch (Exception e) {
            Logger.getLogger(HierarchyTargetController.class.getName()).log(Level.SEVERE, null, e);
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/hierarchytarget/save", method = RequestMethod.POST)
    public @ResponseBody
    String hierarchyTargetSave(@RequestBody HierarchyTarget hierarchyTarget, HttpSession session) {
        JSONObject object = new JSONObject();
        long employeeid = 0;
        try {
            String username = String.valueOf(session.getAttribute("username"));
            hierarchyTargetService.saveHierarchyTarget(hierarchyTarget, username);
            object.put("code", "SUCCESS");
            object.put("message", "Employee created, Emplyee ID : <strong>" + employeeid + "</strong>");
        } catch (SQLException sqle) {
            object.put("code", "ERROR");
            object.put("message", "Error occurred while employee creating");
            Logger.getLogger(HierarchyTargetController.class.getName()).log(Level.SEVERE, null, sqle);
        }
        return object.toString();
    }

}

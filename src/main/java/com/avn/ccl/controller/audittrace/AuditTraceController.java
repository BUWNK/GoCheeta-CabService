/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.audittrace;

import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.model.audittrace.AuditTrace;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.CommonVarList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Lahiru
 */
@Controller
public class AuditTraceController {

    @Autowired
    private AuditTraceDAOImpl auditTraceDao;

    public void setAuditTraceDao(AuditTraceDAOImpl auditTraceDao) {
        this.auditTraceDao = auditTraceDao;
    }

//    @RequestMapping(value = "/audittrace", method = RequestMethod.GET)
//    public String viewAuditTracePage(@ModelAttribute("auditForm") AuditTrace data, ModelMap model) {
//        
//        return "settings/audittrace";
//
//    }
    @RequestMapping(value = "/audittrace", method = RequestMethod.GET)
    public String caseSearch(ModelMap model) {
        AuditTrace parameters = new AuditTrace();
        model.put("callReportForm", parameters);
//        try {
//            model.addAttribute("auditList", auditTraceDao.getAuditTrace());
//        } catch (Exception ex) {
//            Logger.getLogger(AuditTraceController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        model.put("MAP", "ATP");
        return "audittrace/audittrace";

    }

//    @RequestMapping(value = "/audittrace", method = RequestMethod.GET)
//    public String viewAuditTracePage(@ModelAttribute() AuditTrace data, ModelMap model) {
//        try {
//            model.addAttribute("auditList", auditTraceDao.getAuditTrace());
//        } catch (Exception e) {
//            Logger.getLogger(AuditTraceController.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return "settings/audittrace";
//
//    }
    @RequestMapping(value = "/audittrace/searched", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<AuditTrace> list;
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

            if (Boolean.valueOf(request.getParameter("search"))) {
                AuditTrace parameters = this.getParameters(request);

                iTotalRecords = auditTraceDao.getTableDataCount(parameters);
                iTotalDisplayRecords = iTotalRecords;
                if (iTotalRecords > 0) {
                    list = auditTraceDao.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                    for (AuditTrace data : list) {
                        JSONObject object = new JSONObject();
                        object.put("audittraceid", data.getAuditTraceId());
                        object.put("afectedid", data.getAfectedId());
                        object.put("afectedpage", data.getAfectedPage());
                        object.put("task", data.getTask());
                        object.put("description", data.getDescription());
                        object.put("responsibleuser", data.getResponsibleUser());
                        object.put("createddatetime", data.getCreatedDateTime());

                        rows.put(object);
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

    private AuditTrace getParameters(HttpServletRequest request) throws Exception {
        AuditTrace parameters = new AuditTrace();
        if (request.getParameter("from_date") != null && !request.getParameter("from_date").isEmpty()) {
            parameters.setFrom_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("from_date")));
        }
        if (request.getParameter("to_date") != null && !request.getParameter("to_date").isEmpty()) {
            parameters.setTo_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("to_date")));
        }

        return parameters;
    }
}

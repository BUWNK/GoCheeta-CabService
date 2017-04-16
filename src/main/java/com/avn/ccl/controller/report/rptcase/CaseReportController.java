/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.report.rptcase;

import com.avn.ccl.daoimpl.casepriority.CasePriorityDAOImpl;
import com.avn.ccl.daoimpl.casetype.CaseTypeDAOImpl;
import com.avn.ccl.daoimpl.department.DepartmentDAOImpl;
import com.avn.ccl.daoimpl.product.ProductDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.employee.EmployeeDAOImpl;
import com.avn.ccl.daoimpl.report.rptcase.CaseReportDAOImpl;
import com.avn.ccl.daoimpl.user.UserDAOImpl;
import com.avn.ccl.model.report.rptcase.CaseReportData;
import com.avn.ccl.model.report.rptcase.CaseReportParameters;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author : Roshen Dilshan
 * @Document : CaseReportController
 * @Created on : Jul 28, 2015, 2:39:49 PM
 */
@Controller
public class CaseReportController {

    @Autowired
    ServletContext context;

    @Autowired
    CaseTypeDAOImpl caseTypeDAOImpl;
    @Autowired
    CasePriorityDAOImpl casePriorityDAOImpl;
    @Autowired
    ProductDAOImpl productDAOImpl;
    @Autowired
    DepartmentDAOImpl departmentDAOImpl;
    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    UserDAOImpl userDAOImpl;
    @Autowired
    CaseReportDAOImpl caseReportDAOImpl;
    @Autowired
    CommonDAOImpl commonDAOImpl;
    @Autowired
    EmployeeDAOImpl employeeDAOImpl;

    @RequestMapping(value = "/report/case", method = RequestMethod.GET)
    public String pageView(Map<String, Object> model, HttpSession session) {
        CaseReportParameters parameters = new CaseReportParameters();
        model.put("caseReportForm", parameters);
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            CaseReportParameters parametersdata = checkPrivilage_btn(parameters, String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_CASE_REPORT_SUBSECTION_ID), objList);
            model.put("avn_search", parametersdata.isSearch_btn());
            model.put("avn_download", parametersdata.isDownload_btn());
            this.setPageViewComponenets(model);
        } catch (Exception e) {
            Logger.getLogger(CaseReportController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        model.put("MAP", "CSRP");
        return "reports/case/casereport";
    }

    private void setPageViewComponenets(Map<String, Object> model) throws Exception {
        model.put("caseTypeList", caseTypeDAOImpl.getCaseTypeDropdownList());
        model.put("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CASE));
        model.put("casePriorityList", casePriorityDAOImpl.getCasePriorityDropdownList());
        model.put("departmentList", departmentDAOImpl.getDepartmentDropdownList());
        model.put("productList", productDAOImpl.getProductDropdownList());
        model.put("userList", employeeDAOImpl.getEmployeeDropdownList());
    }

    @RequestMapping(value = "/report/case/tabledata", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        List<CaseReportData> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_CASE_REPORT_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }

                if (Boolean.valueOf(request.getParameter("search"))) {
                    CaseReportParameters parameters = this.getParameters(request);

                    iTotalRecords = caseReportDAOImpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = caseReportDAOImpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (CaseReportData data : list) {
                            JSONObject object = new JSONObject();
                            object.put("case_id", data.getCase_id());
                            object.put("case_type", data.getCase_type());
                            object.put("case_date", data.getCase_date());
                            object.put("priority", data.getPriority());
                            object.put("assignee_01", data.getAssignee_01());
                            object.put("status", data.getStatus());
                            object.put("lastupdated_date_time", data.getLastupdateddatetime());
                            object.put("created_date_time", data.getCreateddatetime());
                            object.put("createduser", data.getCreateduser());
                            rows.put(object);
                        }
                    }
                }
            } else {
            }

        } catch (Exception e) {
            Logger.getLogger(CaseReportController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/report/case/report", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<byte[]> getReport(@ModelAttribute("caseReportForm") CaseReportParameters parameters, Map<String, Object> model, HttpServletResponse response, HttpSession session) throws Exception {
        String user = (String) session.getAttribute("username");
        HashMap<String, Object> parameterMap;
        ResponseEntity<byte[]> outPutFile = null;
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_CASE_REPORT_SUBSECTION_ID), MasterDataVarList.CCL_CODE_DOWNLOAD, objList);
            this.setPageViewComponenets(model);
            if (privilage) {
                Date date = commonDAOImpl.getCurentTimesStamp();
                parameterMap = this.getDisplayParameterMap(parameters, user, date);

                HttpHeaders headers = new HttpHeaders();

                ByteArrayOutputStream outputStream;
                byte[] outputFile = null;
                String filename = "";

                if (parameters.getAction().equalsIgnoreCase("PDF")) {
                    outputFile = this.getPdfReport(user, parameterMap);
                    headers.setContentType(MediaType.parseMediaType("application/pdf"));
                    filename = "CASE_REPORT_" + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date) + ".pdf";
                } else if (parameters.getAction().equalsIgnoreCase("EXCEL")) {
                    Object object = this.getExcelReport(parameters, user, parameterMap, date);
                    if (object instanceof XSSFWorkbook) {
                        /*To download single excel file*/
                        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
                        filename = "CASE_REPORT_" + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date) + ".xlsx";

                        XSSFWorkbook workbook = (XSSFWorkbook) object;
                        outputStream = new ByteArrayOutputStream();
                        workbook.write(outputStream);

                        outputFile = outputStream.toByteArray();
                    } else if (object instanceof ByteArrayOutputStream) {
                        /*To download zip file*/
                        headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
                        filename = "CASE_REPORT_" + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date) + ".zip";

                        outputStream = (ByteArrayOutputStream) object;
                        outputFile = outputStream.toByteArray();
                    }
                }

                headers.setContentDispositionFormData(filename, filename);
                headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
                outPutFile = new ResponseEntity<>(outputFile, headers, HttpStatus.OK);
                response.addCookie(new Cookie("fileDownloadToken", parameters.getDownload_token_value_id()));
            }

        } catch (Exception e) {
            Logger.getLogger(CaseReportController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return outPutFile;
    }

    private byte[] getPdfReport(String user, HashMap<String, Object> parameterMap) throws Exception {
        byte[] outputFile;
        Connection connection = null;
        JRSwapFileVirtualizer virtualizer = null;
        try {
            String reportBuildPath = context.getInitParameter(CommonVarList.CONTEXT_PARAM_REPORT_BUILD_PATH);
            String reportLocation = context.getRealPath(CommonVarList.REPORT_LOCATION + CommonVarList.REPORT_NAME_CASE_RPT);

            reportBuildPath += File.separator + user + File.separator + "PDF";
            File file = new File(reportBuildPath);
            if (!file.exists()) {
                file.mkdirs();
            } else {
                for (File temp : file.listFiles()) {
                    temp.delete();
                }
            }

            int numberOfPdfPagesInMemory = Integer.valueOf(context.getInitParameter(CommonVarList.CONTEXT_PARAM_REPORT_NUMBER_OF_PAGES_IN_MEMORY));
            JRSwapFile swapFile = new JRSwapFile(reportBuildPath, 1024, 100);
            virtualizer = new JRSwapFileVirtualizer(numberOfPdfPagesInMemory, swapFile, Boolean.TRUE);
            parameterMap.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

            connection = caseReportDAOImpl.getDataSource().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportLocation, parameterMap, connection);

            outputFile = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (NumberFormatException | SQLException | JRException e) {
            throw e;
        } finally {
            if (virtualizer != null) {
                virtualizer.cleanup();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return outputFile;
    }

    private Object getExcelReport(CaseReportParameters parameters, String user, HashMap<String, Object> parameterMap, Date date) throws Exception {
        Object object = null;
        try {
            String reportBuildPath = context.getInitParameter(CommonVarList.CONTEXT_PARAM_REPORT_BUILD_PATH);

            reportBuildPath += File.separator + user + File.separator + "EXCEL";
            File file = new File(reportBuildPath);
            if (!file.exists()) {
                file.mkdirs();
            } else {
                for (File temp : file.listFiles()) {
                    temp.delete();
                }
            }

            object = caseReportDAOImpl.generateExcelReport(parameters, context, parameterMap, reportBuildPath, user, date);
        } catch (Exception e) {
            throw e;
        }
        return object;
    }

    private HashMap<String, Object> getDisplayParameterMap(CaseReportParameters parameters, String user, Date date) throws Exception {
        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("CCL_CRM_IMG_PATH", context.getRealPath(CommonVarList.REPORT_LOGO_PATH));
        parameterMap.put("CCL_CRM_RPT_TITLE", "CASE REPORT");
        parameterMap.put("CCL_CRM_RPT_USERNAME", user);
        parameterMap.put("CCL_CRM_RPT_DATE", new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd_hh_mm_a).format(date));
        parameterMap.put("CCL_CRM_PRM_FROM_DATE", parameters.getFrom_date());
        parameterMap.put("CCL_CRM_PRM_TO_DATE", parameters.getTo_date());
        parameterMap.put("CCL_CRM_PRM_CASE_TYPE", parameters.getCase_type());
        parameterMap.put("CCL_CRM_PRM_STATUS", parameters.getStatus());
        parameterMap.put("CCL_CRM_PRM_PRIORITY", parameters.getPriority());
        parameterMap.put("CCL_CRM_PRM_CASE_DATE", parameters.getCase_date());
        parameterMap.put("CCL_CRM_PRM_DEPARTMENT", parameters.getDepartment());
        parameterMap.put("CCL_CRM_PRM_PRODUCT", parameters.getProduct());
        parameterMap.put("CCL_CRM_PRM_SUBJECT", parameters.getSubject());
        parameterMap.put("CCL_CRM_PRM_ASSIGNEE", parameters.getAssignee());

        String from_date = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_NA;
        if (parameters.getFrom_date() != null) {
            from_date = Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd, parameters.getFrom_date());
        }
        parameterMap.put("CCL_CRM_DSP_FROM_DATE", from_date);

        String to_date = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_NA;

        if (parameters.getTo_date() != null) {
            to_date = Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd, parameters.getTo_date());
        }
        parameterMap.put("CCL_CRM_DSP_TO_DATE", to_date);

        String case_type = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getCase_type() != null && !parameters.getCase_type().isEmpty()) {
            case_type = caseTypeDAOImpl.getCaseTypeById(parameters.getCase_type()).getTypeDesc();
        }
        parameterMap.put("CCL_CRM_DSP_CASE_TYPE", case_type);

        String status = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getStatus() != null && !parameters.getStatus().isEmpty()) {
            status = statusDAOImpl.getStatusById(parameters.getStatus()).getStatusDesc();
        }
        parameterMap.put("CCL_CRM_DSP_STATUS", status);

        String priority = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getPriority() != null && !parameters.getPriority().isEmpty()) {
            priority = casePriorityDAOImpl.getCasePriorityById(parameters.getPriority()).getPriorityDesc();
        }
        parameterMap.put("CCL_CRM_DSP_PRIORITY", priority);

        String case_date = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_NA;
        if (parameters.getCase_date() != null) {
            case_date = Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd, parameters.getCase_date());
        }
        parameterMap.put("CCL_CRM_DSP_CASE_DATE", case_date);

        String department = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getDepartment() != null && !parameters.getDepartment().isEmpty()) {
            department = departmentDAOImpl.getDepartmentById(parameters.getDepartment()).getDepartmentDesc();
        }
        parameterMap.put("CCL_CRM_DSP_DEPARTMENT", department);

        String product = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getProduct() != null && !parameters.getProduct().isEmpty()) {
            product = productDAOImpl.getProductById(parameters.getProduct()).getProductDesc();
        }
        parameterMap.put("CCL_CRM_DSP_PRODUCT", product);

        String subject = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_NA;
        if (parameters.getSubject() != null && !parameters.getSubject().isEmpty()) {
            subject = parameters.getSubject();
        }
        parameterMap.put("CCL_CRM_DSP_SUBJECT", subject);

        String assignee = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getAssignee() != null && !parameters.getAssignee().isEmpty()) {
            assignee = employeeDAOImpl.getEmployeeInitialsLastnameByid(Long.valueOf(parameters.getAssignee()));
        }
        parameterMap.put("CCL_CRM_DSP_ASSIGNEE", assignee);

        return parameterMap;
    }

    private CaseReportParameters getParameters(HttpServletRequest request) throws Exception {
        CaseReportParameters parameters = new CaseReportParameters();
        if (request.getParameter("from_date") != null && !request.getParameter("from_date").isEmpty()) {
            parameters.setFrom_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("from_date")));
        }
        if (request.getParameter("to_date") != null && !request.getParameter("to_date").isEmpty()) {
            parameters.setTo_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("to_date")));
        }
        parameters.setCase_type(request.getParameter("case_type"));
        parameters.setStatus(request.getParameter("status"));
        parameters.setPriority(request.getParameter("priority"));
        if (request.getParameter("case_date") != null && !request.getParameter("case_date").isEmpty()) {
            parameters.setCase_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("case_date")));
        }
        parameters.setDepartment(request.getParameter("department"));
        parameters.setProduct(request.getParameter("product"));
        parameters.setSubject(request.getParameter("subject"));
        parameters.setAssignee(request.getParameter("assignee"));
//        parameters.setCalllogid(request.getParameter(""));
        return parameters;
    }

    private CaseReportParameters checkPrivilage_btn(CaseReportParameters parameters, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        parameters = new CaseReportParameters();
        parameters.setSave_btn(true);
        parameters.setSearch_btn(true);
        parameters.setEdit_btn(true);
        parameters.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                parameters.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                parameters.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                parameters.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                parameters.setView_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DOWNLOAD))) {
                parameters.setDownload_btn(false);
            }
        }

        return parameters;
    }

}

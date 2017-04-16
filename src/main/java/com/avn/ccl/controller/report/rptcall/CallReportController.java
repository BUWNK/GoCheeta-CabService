/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.report.rptcall;

import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
import com.avn.ccl.daoimpl.calldirection.CallDirectionDAOImpl;
import com.avn.ccl.daoimpl.casetype.CaseTypeDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.followupaction.FollowUpActionDAOImpl;
import com.avn.ccl.daoimpl.language.LanguageDAOImpl;
import com.avn.ccl.daoimpl.product.ProductDAOImpl;
import com.avn.ccl.daoimpl.report.rptcall.CallReportDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.user.UserDAOImpl;
import com.avn.ccl.model.report.rptcall.CallReportData;
import com.avn.ccl.model.report.rptcall.CallReportParameters;
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
 * @Document : CallReportController
 * @Date : Aug 13, 2015 12:17:56 PM
 */
@Controller
public class CallReportController {

    @Autowired
    ServletContext context;

    @Autowired
    CallReportDAOImpl callReportDAOImpl;
    @Autowired
    CallDirectionDAOImpl callDirectionDaoImpl;
    @Autowired
    FollowUpActionDAOImpl followUpActionDAOImpl;
    @Autowired
    CallReportDAOImpl callReportDAOImpl1;
    @Autowired
    CommonDAOImpl commonDAOImpl;
    @Autowired
    LanguageDAOImpl languagesDaoImpl;
    @Autowired
    CaseTypeDAOImpl caseTypeDAOImpl;
    @Autowired
    ProductDAOImpl productDAOImpl;
    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    BranchDAOImpl branchDAOImpl;
    @Autowired
    UserDAOImpl userDAOImpl;

    @RequestMapping(value = "/report/call", method = RequestMethod.GET)
    public String pageView(Map<String, Object> model, HttpSession session) {
        CallReportParameters parameters = new CallReportParameters();
        model.put("callReportForm", parameters);
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            CallReportParameters parametersdata = checkPrivilage_btn(parameters, String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CALL_REPORT_SUBSECTION_ID), objList);
            model.put("avn_search", parametersdata.isSearch_btn());
            model.put("avn_download", parametersdata.isDownload_btn());
            this.setPageViewComponenets(model);
        } catch (Exception e) {
            Logger.getLogger(CallReportController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        model.put("MAP", "CLRP");
        return "reports/call/callreport";
    }

    private void setPageViewComponenets(Map<String, Object> model) throws Exception {
        model.put("callDirectionsList", callDirectionDaoImpl.getCallDirectionDropdownList());
        model.put("followUpActionList", followUpActionDAOImpl.getFollowUpActionDropdownList());
        model.put("languageList", languagesDaoImpl.getLanguagesList());
        model.put("productList", productDAOImpl.getProductDropdownList());
        model.put("caseTypeList", caseTypeDAOImpl.getCaseTypeDropdownList());
        model.put("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CALLLOG));
        model.put("branchList", branchDAOImpl.getBranchIdDropdownList());
        model.put("userList", userDAOImpl.getUserDropdownList());
    }

    @RequestMapping(value = "/report/call/tabledata", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        List<CallReportData> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CALL_REPORT_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }

                if (Boolean.valueOf(request.getParameter("search"))) {
                    CallReportParameters parameters = this.getParameters(request);

                    iTotalRecords = callReportDAOImpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = callReportDAOImpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (CallReportData data : list) {
                            JSONObject object = new JSONObject();
                            object.put("calllogid", data.getCalllogid());
                            object.put("name", data.getName());
                            object.put("call_start_date", data.getCall_start_date());
                            object.put("call_direction", data.getCall_direction());
                            object.put("follow_up_action", data.getFollow_up_action());
                            object.put("call_back_date", data.getCall_back_date());
                            object.put("phone_number", data.getPhone_number());
                            object.put("lastupdated_time", data.getLastupdated_time());
                            object.put("created_time", data.getCreated_time());
                            object.put("user", data.getUser());

                            rows.put(object);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Logger.getLogger(CallReportController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/report/call/report", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<byte[]> getReport(@ModelAttribute("callReportForm") CallReportParameters parameters, Map<String, Object> model, HttpServletResponse response, HttpSession session) throws Exception {
        String user = (String) session.getAttribute("username");
        HashMap<String, Object> parameterMap;
        ResponseEntity<byte[]> outPutFile = null;
         JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CALL_REPORT_SUBSECTION_ID), MasterDataVarList.CCL_CODE_DOWNLOAD, objList);
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
                    filename = "CALL_REPORT_" + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date) + ".pdf";
                } else if (parameters.getAction().equalsIgnoreCase("EXCEL")) {
                    Object object = this.getExcelReport(parameters, user, parameterMap, date);
                    if (object instanceof XSSFWorkbook) {
                        /*To download single excel file*/
                        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
                        filename = "CALL_REPORT_" + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date) + ".xlsx";

                        XSSFWorkbook workbook = (XSSFWorkbook) object;
                        outputStream = new ByteArrayOutputStream();
                        workbook.write(outputStream);

                        outputFile = outputStream.toByteArray();
                    } else if (object instanceof ByteArrayOutputStream) {
                        /*To download zip file*/
                        headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
                        filename = "CALL_REPORT_" + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date) + ".zip";

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
            Logger.getLogger(CallReportController.class.getName()).log(Level.SEVERE, null, e);
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
            String reportLocation = context.getRealPath(CommonVarList.REPORT_LOCATION + CommonVarList.REPORT_NAME_CALL_RPT);

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

            connection = callReportDAOImpl.getDataSource().getConnection();

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

    private Object getExcelReport(CallReportParameters parameters, String user, HashMap<String, Object> parameterMap, Date date) throws Exception {
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

            object = callReportDAOImpl.generateExcelReport(parameters, context, parameterMap, reportBuildPath, user, date);

        } catch (Exception e) {
            throw e;
        }
        return object;
    }

    private HashMap<String, Object> getDisplayParameterMap(CallReportParameters parameters, String user, Date date) throws Exception {
        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("CCL_CRM_IMG_PATH", context.getRealPath(CommonVarList.REPORT_LOGO_PATH));
        parameterMap.put("CCL_CRM_RPT_TITLE", "CALL REPORT");
        parameterMap.put("CCL_CRM_RPT_USERNAME", user);
        parameterMap.put("CCL_CRM_RPT_DATE", new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd_hh_mm_a).format(date));
        parameterMap.put("CCL_CRM_PRM_FROM_DATE", parameters.getFrom_date());
        parameterMap.put("CCL_CRM_PRM_TO_DATE", parameters.getTo_date());
        parameterMap.put("CCL_CRM_PRM_LANGUAGE", parameters.getPreferred_language());
        parameterMap.put("CCL_CRM_PRM_MODULE", parameters.getProduct());
        parameterMap.put("CCL_CRM_PRM_CASE_TYPE", parameters.getCase_type());
        parameterMap.put("CCL_CRM_PRM_STATUS", parameters.getStatus());
        parameterMap.put("CCL_CRM_PRM_BRANCH", parameters.getBranch());
        parameterMap.put("CCL_CRM_PRM_CALL_DIRECTION", parameters.getCall_direction());
        parameterMap.put("CCL_CRM_PRM_FOLLOW_UP_ACTION", parameters.getFollow_up_action());
        parameterMap.put("CCL_CRM_PRM_CALLBACK_DATE", parameters.getCallback_date());
        parameterMap.put("CCL_CRM_PRM_TELEPHONE", parameters.getTelephone());
        parameterMap.put("CCL_CRM_PRM_NAME", parameters.getName());

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

        String language = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getPreferred_language() != null && !parameters.getPreferred_language().isEmpty()) {
            language = languagesDaoImpl.getLanguageById(parameters.getPreferred_language()).getLanguageDesc();
        }
        parameterMap.put("CCL_CRM_DSP_LANGUAGE", language);

        String call_direction = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getCall_direction() != null && !parameters.getCall_direction().isEmpty()) {
            call_direction = callDirectionDaoImpl.getCallDirectionById(parameters.getCall_direction()).getDescription();
        }
        parameterMap.put("CCL_CRM_DSP_CALL_DIRECTION", call_direction);

        String module = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getProduct() != null && !parameters.getProduct().isEmpty()) {
            module = productDAOImpl.getProductById(parameters.getProduct()).getProductDesc();
        }
        parameterMap.put("CCL_CRM_DSP_MODULE", module);

        String case_type = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getCase_type() != null && !parameters.getCase_type().isEmpty()) {
            case_type = caseTypeDAOImpl.getCaseTypeById(parameters.getCase_type()).getTypeDesc();
        }
        parameterMap.put("CCL_CRM_DSP_CASE_TYPE", case_type);

        String follow_up_action = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getFollow_up_action() != null && !parameters.getFollow_up_action().isEmpty()) {
            follow_up_action = followUpActionDAOImpl.getCallDirectionById(parameters.getFollow_up_action()).getDescription();
        }
        parameterMap.put("CCL_CRM_DSP_FOLLOW_UP_ACTION", follow_up_action);

        //This one is not using currently
        String callback_date = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_NA;
        if (parameters.getCallback_date() != null) {
            callback_date = Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd, parameters.getCallback_date());
        }
        parameterMap.put("CCL_CRM_DSP_CALLBACK_DATE", callback_date);

        String status = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getStatus() != null && !parameters.getStatus().isEmpty()) {
            status = statusDAOImpl.getStatusById(parameters.getStatus()).getStatusDesc();
        }
        parameterMap.put("CCL_CRM_DSP_STATUS", status);

        String name = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_NA;
        if (parameters.getName() != null && !parameters.getName().isEmpty()) {
            name = parameters.getName();
        }
        parameterMap.put("CCL_CRM_DSP_NAME", name);

        String telephone = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_NA;
        if (parameters.getTelephone() != null && !parameters.getTelephone().isEmpty()) {
            telephone = parameters.getTelephone();
        }
        parameterMap.put("CCL_CRM_DSP_TELEPHONE", telephone);

        String branch = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (parameters.getBranch() != null && !parameters.getBranch().isEmpty()) {
            branch = branchDAOImpl.getBranch(Integer.valueOf(parameters.getBranch())).getBranchDesc();
        }
        parameterMap.put("CCL_CRM_DSP_BRANCH", branch);

        return parameterMap;
    }

    private CallReportParameters getParameters(HttpServletRequest request) throws Exception {
        CallReportParameters parameters = new CallReportParameters();
        if (request.getParameter("from_date") != null && !request.getParameter("from_date").isEmpty()) {
            parameters.setFrom_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("from_date")));
        }
        if (request.getParameter("to_date") != null && !request.getParameter("to_date").isEmpty()) {
            parameters.setTo_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("to_date")));
        }
        parameters.setPreferred_language(request.getParameter("preferred_language"));
        parameters.setCall_direction(request.getParameter("call_direction"));
        parameters.setProduct(request.getParameter("product"));
        parameters.setCase_type(request.getParameter("case_type"));
        parameters.setFollow_up_action(request.getParameter("follow_up_action"));
        if (request.getParameter("callback_date") != null && !request.getParameter("callback_date").isEmpty()) {
            parameters.setCallback_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("callback_date")));
        }
        parameters.setStatus(request.getParameter("status"));
        parameters.setTelephone(request.getParameter("telephone"));
        parameters.setName(request.getParameter("name"));
        parameters.setBranch(request.getParameter("branch"));
        parameters.setUser(request.getParameter("user"));

        return parameters;
    }

    private CallReportParameters checkPrivilage_btn(CallReportParameters parameters, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        parameters = new CallReportParameters();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.report.rptcall;

import com.avn.ccl.dao.report.rptcall.CallReportDAO;
import com.avn.ccl.model.report.rptcall.CallReportData;
import com.avn.ccl.model.report.rptcall.CallReportParameters;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.ExcelCommon;
import com.avn.ccl.util.varlist.CommonVarList;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Author : Roshen Dilshan
 * @Document : CaseReportDAOImpl
 * @Created on : Jul 29, 2015, 12:14:59 PM
 */
public class CallReportDAOImpl implements CallReportDAO {
    
    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;
    
    private int detailRow;
    private int rowCount;
    private final int columnCount = 20;
    private final int headerRowCount = 9;
    private final int staticWidthColumn = 3;
    private final int commentColumnWith = 10000;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public DataSource getDataSource() {
        return this.dataSource;
    }
    
    @Override
    public List<CallReportData> getTableData(CallReportParameters parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CallReportData> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + "FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( "
                    + "        SELECT "
                    + "             CL.CALLLOGID                                                        AS CALLLOGID,  "
                    + "             NVL(CL.NAMEINFULL, '--')                                            AS NAME, "
                    + "             NVL(TO_CHAR(CL.CALLSTARTDATETIME, 'YYYY-MM-DD HH24:MI:SS'), '--')   AS CALLSTARTDATE, "
                    + "             NVL(CD.DESCRIPTION, '--')                                           AS CALLDIRECTION, "
                    + "             NVL(CF.DESCRIPTION, '--')                                           AS FOLLOWUPACTION, "
                    + "             NVL(TO_CHAR(CL.CALLBACKDATETIME, 'YYYY-MM-DD HH24:MI:SS'), '--')    AS CALLBACKDATETIME, "
                    + "             NVL(CL.PHONENUMBER, '--')                                           AS PHONENUMBER, "
                    + "             NVL(CL.COMMENTS, '--')                                              AS COMMENTS, "
                    + "             NVL(TO_CHAR(CL.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS'), '--') AS LASTUPDATEDDATETIME, "
                    + "             NVL(TO_CHAR(CL.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS'), '--')     AS CREATEDDATETIME, "
                    + "             NVL(CL.CREATEDUSER, '--')                                           AS CREATEDUSER"
                    + "        FROM "
                    + "             AVN_CALLLOG CL "
                    + "        LEFT OUTER JOIN "
                    + "             AVN_CALLDIRECTIONS CD  "
                    + "            ON "
                    + "             CL.CALLDIRECTIONID = CD.CALLDIRECTIONSID "
                    + "        LEFT OUTER JOIN "
                    + "             AVN_FOLLOWUPACTIONS CF  "
                    + "            ON "
                    + "             CL.FOLLOWUPACTIONID = CF.FOLLOUPACTIONID "
                    + "        WHERE "
                    + "             1 = 1 :where "
                    + "        ORDER BY "
                    + "             CL.CREATEDDATETIME DESC "
                    + "        ) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + "WHERE "
                    + "    ROWNUMER > ? ";
            sql = sql.replace(":where", this.getWhere(parameters));
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, start + minCount);
            statement.setInt(2, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            CallReportData reportData;
            while (resultSet.next()) {
                reportData = new CallReportData();
                reportData.setCalllogid(resultSet.getString("CALLLOGID"));
                reportData.setName(resultSet.getString("NAME"));
                reportData.setCall_start_date(resultSet.getString("CALLSTARTDATE"));
                reportData.setCall_direction(resultSet.getString("CALLDIRECTION"));
                reportData.setFollow_up_action(resultSet.getString("FOLLOWUPACTION"));
                reportData.setCall_back_date(resultSet.getString("CALLBACKDATETIME"));
                reportData.setPhone_number(resultSet.getString("PHONENUMBER"));
                reportData.setLastupdated_time(resultSet.getString("LASTUPDATEDDATETIME"));
                reportData.setCreated_time(resultSet.getString("CREATEDDATETIME"));
                reportData.setUser(resultSet.getString("CREATEDUSER"));
                list.add(reportData);
            }
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return list;
    }
    
    @Override
    public int getTableDataCount(CallReportParameters parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT "
                    + "    COUNT(*) CNT "
                    + "FROM "
                    + "    AVN_CALLLOG CL "
                    + "LEFT OUTER JOIN "
                    + "    AVN_CALLDIRECTIONS CD  "
                    + "   ON "
                    + "    CL.CALLDIRECTIONID = CD.CALLDIRECTIONSID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_FOLLOWUPACTIONS CF "
                    + "   ON "
                    + "    CL.FOLLOWUPACTIONID = CF.FOLLOUPACTIONID "
                    + "WHERE "
                    + "    1 = 1 :where "
                    + "ORDER BY "
                    + "    CL.CREATEDDATETIME DESC ";
            sql = sql.replace(":where", this.getWhere(parameters));
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("CNT");
            }
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return count;
    }
    
    @Override
    public Object generateExcelReport(CallReportParameters parameters, ServletContext context, HashMap<String, Object> parameterMap, String reportBuildPath, String user, Date date) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        XSSFWorkbook workbook;
        Object returnObject = null;
        try {
            int totalRecordCount = 0;
            String sql = "SELECT "
                    + "    COUNT(*) CNT "
                    + "FROM "
                    + "    AVN_CALLLOG CL "
                    + "LEFT OUTER JOIN "
                    + "     AVN_CALLDIRECTIONS CD "
                    + "    ON "
                    + "     CL.CALLDIRECTIONID = CD.CALLDIRECTIONSID "
                    + "LEFT OUTER JOIN "
                    + "     AVN_FOLLOWUPACTIONS CF "
                    + "    ON "
                    + "     CL.FOLLOWUPACTIONID = CF.FOLLOUPACTIONID "
                    + "LEFT OUTER JOIN "
                    + "     AVN_PREFLANGUAGE PF "
                    + "    ON "
                    + "     CL.LANGUAGEID = PF.LANGUAGEID "
                    + "LEFT OUTER JOIN "
                    + "     AVN_PRODUCT PR "
                    + "    ON "
                    + "     CL.PRODUCTID = PR.PRODUCTID "
                    + "LEFT OUTER JOIN "
                    + "     AVN_CASETYPE CT "
                    + "    ON "
                    + "     CL.CASETYPEID = CT.CASETYPEID "
                    + "LEFT OUTER JOIN "
                    + "     AVN_STATUS ST "
                    + "    ON "
                    + "     CL.STATUSID = ST.STATUSID "
                    + "LEFT OUTER JOIN "
                    + "     AVN_BRANCH BR "
                    + "    ON "
                    + "     CL.BRANCHID = BR.BRANCHID "
                    + "WHERE "
                    + "    1 = 1 :where "
                    + "ORDER BY "
                    + "    CL.CREATEDDATETIME DESC ";
            String where = this.getWhere(parameters);
            sql = sql.replace(":where", where);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                totalRecordCount = resultSet.getInt("CNT");
            }
            
            if (totalRecordCount > 0) {
                long maxRow = Long.parseLong(context.getInitParameter(CommonVarList.CONTEXT_PARAM_NUMBER_OF_ROWS_PER_EXCEL));
                workbook = this.createExcelTopSection(parameterMap, user, date);
                XSSFSheet sheet = workbook.getSheetAt(0);
                detailRow = headerRowCount;
                int fileCount = 0;
                this.createExcelTableHeandeSection(workbook);
                int selectRow = Integer.parseInt(context.getInitParameter(CommonVarList.CONTEXT_PARAM_NUMBER_OF_SELECT_ROWS));
                int numberOfTimes = totalRecordCount / selectRow;
                if ((totalRecordCount % selectRow) > 0) {
                    numberOfTimes += 1;
                }
                int from = 0;
                int to = selectRow;
                for (int i = 0; i < numberOfTimes; i++) {
                    sql = "SELECT "
                            + "    * "
                            + "FROM "
                            + "    ("
                            + "    SELECT "
                            + "        TB.*, "
                            + "         ROWNUM AS ROWNUMER  "
                            + "    FROM "
                            + "        ( "
                            + "        SELECT "
                            + "              CL.CALLLOGID                                AS CALLLOGID,  "
                            + "             NVL(CL.NAMEINFULL, '--')                                 AS NAME, "
                            + "             NVL(TO_CHAR(CL.CALLSTARTDATETIME, 'YYYY-MM-DD'), '--')   AS CALLSTARTDATE, "
                            + "             NVL(TO_CHAR(CL.CALLSTARTDATETIME, 'HH24:MI:SS'), '--')   AS CALLSTARTTIME, "
                            + "             NVL(CL.COMMENTS, '--')                                   AS COMMENTS, "
                            + "             NVL(PF.DESCRIPTION, '--')                                AS LANGUAGE, "
                            + "             NVL(CD.DESCRIPTION, '--')                                AS CALLDIRECTION, "
                            + "             NVL(PR.DESCRIPTION, '--')                                AS PRODUCT, "
                            + "             NVL(CT.DESCRIPTION, '--')                                AS CASETYPE, "
                            + "             NVL(ST.DESCRIPTION, '--')                                AS STATUS, "
                            + "             NVL(BR.ALIASNAME, '--')                                  AS BRANCH, "
                            + "             NVL(CF.DESCRIPTION, '--')                                AS FOLLOWUPACTION, "
                            + "             NVL(TO_CHAR(CL.CALLBACKDATETIME, 'YYYY-MM-DD'), '--')    AS CALLBACKDATE, "
                            + "             NVL(TO_CHAR(CL.CALLBACKDATETIME, 'HH24:MI:SS'), '--')    AS CALLBACKTIME, "
                            + "             NVL(CL.PHONENUMBER, '--')                                AS PHONENUMBER, "
                            + "             NVL(CL.CREATEDUSER, '--')                                AS CREATEDUSER, "
                            + "             NVL(TO_CHAR(CL.CREATEDDATETIME, 'YYYY-MM-DD'), '--')     AS CREATEDDATE, "
                            + "             NVL(TO_CHAR(CL.CREATEDDATETIME, 'HH24:MI:SS'), '--')     AS CREATEDTIME, "
                            + "             NVL(TO_CHAR(CL.LASTUPDATEDDATETIME, 'YYYY-MM-DD'), '--') AS LASTUPDATEDDATE, "
                            + "             NVL(TO_CHAR(CL.LASTUPDATEDDATETIME, 'HH24:MI:SS'), '--') AS LASTUPDATEDTIME "
                            + "        FROM "
                            + "             AVN_CALLLOG CL "
                            + "        LEFT OUTER JOIN "
                            + "             AVN_CALLDIRECTIONS CD "
                            + "            ON "
                            + "             CL.CALLDIRECTIONID = CD.CALLDIRECTIONSID "
                            + "        LEFT OUTER JOIN "
                            + "             AVN_FOLLOWUPACTIONS CF "
                            + "            ON "
                            + "             CL.FOLLOWUPACTIONID = CF.FOLLOUPACTIONID "
                            + "        LEFT OUTER JOIN "
                            + "             AVN_PREFLANGUAGE PF "
                            + "            ON "
                            + "             CL.LANGUAGEID = PF.LANGUAGEID "
                            + "        LEFT OUTER JOIN "
                            + "             AVN_PRODUCT PR "
                            + "            ON "
                            + "             CL.PRODUCTID = PR.PRODUCTID "
                            + "        LEFT OUTER JOIN "
                            + "             AVN_CASETYPE CT "
                            + "            ON "
                            + "             CL.CASETYPEID = CT.CASETYPEID "
                            + "        LEFT OUTER JOIN "
                            + "             AVN_STATUS ST "
                            + "            ON "
                            + "             CL.STATUSID = ST.STATUSID "
                            + "        LEFT OUTER JOIN "
                            + "             AVN_BRANCH BR "
                            + "            ON "
                            + "             CL.BRANCHID = BR.BRANCHID "
                            + "        WHERE "
                            + "             1 = 1 :where "
                            + "        ORDER BY "
                            + "             CL.CREATEDDATETIME DESC "
                            + "        ) TB  "
                            + "    WHERE "
                            + "        ROWNUM <= ?  "
                            + "    ) "
                            + "WHERE "
                            + "    ROWNUMER > ? ";
                    sql = sql.replace(":where", where);
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, to);
                    statement.setInt(2, from);
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        if (detailRow + 1 > maxRow) {
                            fileCount++;
                            this.writeTemporaryFile(workbook, fileCount, reportBuildPath);
                            workbook = this.createExcelTopSection(parameterMap, user, date);
                            sheet = workbook.getSheetAt(0);
                            detailRow = headerRowCount;
                            this.createExcelTableHeandeSection(workbook);
                        }
                        this.createExcelTableRow(workbook, resultSet);
                    }
                    from = to - 1;
                    to = to + selectRow;
                }
                
                if (fileCount > 0) {
                    fileCount++;
                    this.writeTemporaryFile(workbook, fileCount, reportBuildPath);
                    ByteArrayOutputStream outputStream = Common.zipFiles(new File(reportBuildPath).listFiles());
                    returnObject = outputStream;
                } else {
                    for (int i = 0; i < this.columnCount; i++) {
                        //to auto size all column in the sheet
                        if (this.staticWidthColumn != i) {
                            sheet.autoSizeColumn(i);
                        } else {
                            sheet.setColumnWidth(this.staticWidthColumn, this.commentColumnWith);
                        }
                    }
                    returnObject = workbook;
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        
        return returnObject;
    }
    
    private XSSFWorkbook createExcelTopSection(HashMap<String, Object> parameterMap, String user, Date date) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Call Report");
        
        XSSFCellStyle fontBoldedUnderlinedCell = ExcelCommon.getFontBoldedUnderlinedCell(workbook);
        
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("COMMERCIAL CREDIT");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        
        row = sheet.createRow(1);
        cell = row.createCell(columnCount - 2);
        cell.setCellValue("Created By");
        cell = row.createCell(columnCount - 1);
        cell.setCellValue(user);
        
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("CALL REPORT");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        
        cell = row.createCell(columnCount - 2);
        cell.setCellValue("Created On");
        cell = row.createCell(columnCount - 1);
        cell.setCellValue(new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd_hh_mm_a).format(date));
        
        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("From Date");
        cell = row.createCell(1);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_FROM_DATE")));
        cell.setCellStyle(ExcelCommon.getAligneCell(workbook, null, XSSFCellStyle.ALIGN_RIGHT));
        
        cell = row.createCell(3);
        cell.setCellValue("To Date");
        cell = row.createCell(4);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_TO_DATE")));
        cell.setCellStyle(ExcelCommon.getAligneCell(workbook, null, XSSFCellStyle.ALIGN_RIGHT));
        
        cell = row.createCell(6);
        cell.setCellValue("Language");
        cell = row.createCell(7);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_LANGUAGE")));
        
        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("Call Direction");
        cell = row.createCell(1);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_CALL_DIRECTION")));
        
        cell = row.createCell(3);
        cell.setCellValue("Module");
        cell = row.createCell(4);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_MODULE")));
        
        cell = row.createCell(6);
        cell.setCellValue("Case Type");
        cell = row.createCell(7);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_CASE_TYPE")));
        
        row = sheet.createRow(6);
        cell = row.createCell(0);
        cell.setCellValue("Follow Up Action");
        cell = row.createCell(1);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_FOLLOW_UP_ACTION")));
        
        cell = row.createCell(3);
        cell.setCellValue("Status");
        cell = row.createCell(4);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_STATUS")));
        
        cell = row.createCell(6);
        cell.setCellValue("Name");
        cell = row.createCell(7);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_NAME")));
        
        row = sheet.createRow(7);
        cell = row.createCell(0);
        cell.setCellValue("Contact #1");
        cell = row.createCell(1);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_TELEPHONE")));
        
        cell = row.createCell(3);
        cell.setCellValue("Branch");
        cell = row.createCell(4);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_BRANCH")));
        
        return workbook;
    }
    
    private void createExcelTableHeandeSection(XSSFWorkbook workbook) throws Exception {
        XSSFCellStyle columnHeaderCell = ExcelCommon.getColumnHeadeCell(workbook);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(this.detailRow++);
        int column = 0;
        
        Cell cell = row.createCell(column++);
        cell.setCellValue("CALLLOG ID");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("NAME");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("CALL START DATE");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("CALL START TIME");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("COMMENT");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("LANGUAGE");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("CALL DIRECTION");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("MODULE");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("CASE TYPE");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("STATUS");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("BRANCH");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("FOLLOW UP");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("CALL BACK DATE");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("CALL BACK TIME");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("PHONE NUMBER");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("AGENT");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("CREATED DATE");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("CREATED TIME");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("LAST UPDATED DATE");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(column++);
        cell.setCellValue("LAST UPDATED TIME");
        cell.setCellStyle(columnHeaderCell);
    }
    
    private void createExcelTableRow(XSSFWorkbook workbook, ResultSet resultSet) throws Exception {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        int column = 0;
        if (this.rowCount % 2 == 0) {
            rowColumnCell.setFillForegroundColor(new XSSFColor(new Color(235, 203, 202)));
        } else {
            rowColumnCell.setFillForegroundColor(new XSSFColor(new Color(172, 200, 227)));
        }
        
        rowColumnCell.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        Row row = sheet.createRow(this.detailRow++);
        
        Cell cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CALLLOGID"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("NAME"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CALLSTARTDATE"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CALLSTARTTIME"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("COMMENTS"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("LANGUAGE"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CALLDIRECTION"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("PRODUCT"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CASETYPE"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("STATUS"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("BRANCH"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("FOLLOWUPACTION"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CALLBACKDATE"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CALLBACKTIME"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("PHONENUMBER"));
        cell.setCellStyle(ExcelCommon.getAligneCell(workbook, rowColumnCell, XSSFCellStyle.ALIGN_RIGHT));
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CREATEDUSER"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CREATEDDATE"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CREATEDTIME"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("LASTUPDATEDDATE"));
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("LASTUPDATEDTIME"));
        cell.setCellStyle(rowColumnCell);
        
        this.rowCount++;
    }
    
    private void writeTemporaryFile(XSSFWorkbook workbook, int fileCount, String directory) throws Exception {
        File file;
        FileOutputStream outputStream = null;
        try {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < this.columnCount; i++) {
                //to auto size all column in the sheet
                if (this.staticWidthColumn != i) {
                    sheet.autoSizeColumn(i);
                } else {
                    sheet.setColumnWidth(this.staticWidthColumn, this.commentColumnWith);
                }
            }
            file = new File(directory);
            if (!file.exists()) {
                System.out.println("Directory created or not : " + file.mkdirs());
            }
            if (fileCount > 0) {
                file = new File(directory + File.separator + "Transaction Detail Report - Agent Wise_" + fileCount + ".xlsx");
            } else {
                file = new File(directory + File.separator + "Transaction Detail Report - Agent Wise.xlsx");
            }
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException e) {
            throw e;
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }
    
    private String getWhere(CallReportParameters parameters) {
        String where = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (parameters.getFrom_date() != null) {
            where += " AND CL.CALLSTARTDATETIME >= TO_DATE('" + dateFormat.format(Common.getStartingTimeofDay(parameters.getFrom_date())) + "', 'YYYY-MM-DD hh24:mi:ss')";
        }
        if (parameters.getTo_date() != null) {
            where += " AND CL.CALLSTARTDATETIME <= TO_DATE('" + dateFormat.format(Common.getEndingTimeofDay(parameters.getTo_date())) + "', 'YYYY-MM-DD hh24:mi:ss')";
        }
        if (parameters.getPreferred_language() != null && !parameters.getPreferred_language().isEmpty()) {
            where += " AND CL.LANGUAGEID = " + parameters.getPreferred_language();
        }
        if (parameters.getCall_direction() != null && !parameters.getCall_direction().isEmpty()) {
            where += " AND CL.CALLDIRECTIONID = " + parameters.getCall_direction();
        }
        if (parameters.getProduct() != null && !parameters.getProduct().isEmpty()) {
            where += " AND CL.PRODUCTID = " + parameters.getProduct();
        }
        if (parameters.getCase_type() != null && !parameters.getCase_type().isEmpty()) {
            where += " AND CL.CASETYPEID = " + parameters.getCase_type();
        }
        if (parameters.getFollow_up_action() != null && !parameters.getFollow_up_action().isEmpty()) {
            where += "  AND CL.FOLLOWUPACTIONID = " + parameters.getFollow_up_action();
        }
        if (parameters.getStatus() != null && !parameters.getStatus().isEmpty()) {
            where += " AND CL.STATUSID = " + parameters.getStatus();
        }
        if (parameters.getName() != null && !parameters.getName().isEmpty()) {
            where += " AND CL.NAMEINFULL LIKE '%" + parameters.getName() + "%'";
        }
//        if (parameters.getCallback_date() != null) {
//            where += " AND CL.CALLBACKDATETIME BETWEEN TO_DATE ('" + dateFormat.format(Common.getStartingTimeofDay(parameters.getCallback_date())) + "', 'YYYY-MM-DD hh24:mi:ss') AND TO_DATE ('" + dateFormat.format(Common.getEndingTimeofDay(parameters.getCallback_date())) + "', 'YYYY-MM-DD hh24:mi:ss')";
//        }
        if (parameters.getTelephone() != null && !parameters.getTelephone().isEmpty()) {
            where += " AND CL.PHONENUMBER LIKE '%" + parameters.getTelephone() + "%'";
        }
        if (parameters.getBranch() != null && !parameters.getBranch().isEmpty()) {
            where += " AND CL.BRANCHID = " + parameters.getBranch();
        }
        if (parameters.getUser() != null && !parameters.getUser().isEmpty()) {
            where += " AND CL.CREATEDUSER = '" + parameters.getUser() + "'";
        }
        return where;
    }
    
}

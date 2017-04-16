/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.report.rptcase;

import com.avn.ccl.dao.report.rptcase.CaseReportDAO;
import com.avn.ccl.model.report.rptcase.CaseReportData;
import com.avn.ccl.model.report.rptcase.CaseReportParameters;
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
public class CaseReportDAOImpl implements CaseReportDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    private int detailRow;
    private int rowCount;
    private final int columnCount = 20;
    private final int headerRowCount = 8;
    private final int staticWidthColumn = 7;
    private final int staticWidthColumn2 = 8;
    private final int commentColumnWith = 10000;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public List<CaseReportData> getTableData(CaseReportParameters parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CaseReportData> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + "FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "        ROWNUM AS ROWNUMER "
                    + "    FROM "
                    + "        ( "
                    + "        SELECT "
                    + "            CA.CASEID, "
                    + "            NVL(TO_CHAR(CA.CASEDATE, 'YYYY-MM-DD HH24:MI:SS'), '--')            AS CASEDATE, "
                    + "            NVL(CT.DESCRIPTION, '--')                                           AS CASETYPE, "
                    + "            NVL(CP.DESCRIPTION, '--')                                           AS PRIORITY, "
                    + "            NVL(DE.DESCRIPTION, '--')                                           AS DEPARTMENT, "
                    + "            NVL(PR.DESCRIPTION, '--')                                           AS PRODUCT, "
                    + "            NVL(AC.CCID, '--')                                                  AS ACCOUNT, "
                    + "            ST.DESCRIPTION                                                      AS STATUS, "
                    + "            NVL(CA.SUBJECT, '--')                                               AS SUBJECT, "
                    + "            NVL(CA.DESCRIPTION, '--')                                           AS DESCRIPTION, "
                    + "            NVL(TO_CHAR(CA.CALLLOGID), '--')                                    AS CALLLOGID, "
                    + "            NVL(EP.INITIALS, '') || ' ' || NVL(EP.LASTNAME, '')                 AS ASSIGNEE, "
                    + "            NVL(TO_CHAR(CA.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS'), '--') AS LASTUPDATEDDATETIME, "
                    + "            NVL(TO_CHAR(CA.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS'), '--')     AS CREATEDDATETIME, "
                    + "            CA.CREATEDUSER                                                      AS CREATEDUSER "
                    + "        FROM "
                    + "            AVN_CASE CA "
                    + "        INNER JOIN "
                    + "            AVN_CASETYPE CT "
                    + "            ON "
                    + "            CA.CASETYPEID = CT.CASETYPEID "
                    + "        INNER JOIN "
                    + "            AVN_CASEPRIORITY CP "
                    + "            ON "
                    + "            CA.PRIORITYID = CP.CASEPRIORITYID "
                    + "        LEFT OUTER JOIN "
                    + "            AVN_DEPARTMENT DE "
                    + "            ON "
                    + "            CA.DEPARTMENTID = DE.DEPARTMENTID "
                    + "        LEFT OUTER JOIN "
                    + "            AVN_PRODUCT PR "
                    + "            ON "
                    + "            CA.PRODUCTID = PR.PRODUCTID "
                    + "        LEFT OUTER JOIN "
                    + "            AVN_ACCOUNT AC "
                    + "            ON "
                    + "            CA.ACCOUNTID = AC.ACCOUNTID "
                    + "        LEFT OUTER JOIN "
                    + "            AVN_STATUS ST "
                    + "            ON "
                    + "            CA.STATUSID = ST.STATUSID "
                    + "        LEFT OUTER JOIN "
                    + "            AVN_EMPLOYEE EP "
                    + "            ON "
                    + "            CA.ASSIGNEEID1 = EP.EMPLOYEEID "
                    + "        LEFT OUTER JOIN "
                    + "            AVN_BRANCH BR "
                    + "            ON "
                    + "            CA.BRANCHID = BR.BRANCHID "
                    + "        WHERE "
                    + "            1 = 1 :where "
                    + "        ORDER BY "
                    + "            CA.CREATEDDATETIME DESC "
                    + "        ) "
                    + "        TB "
                    + "    WHERE "
                    + "        ROWNUM <= ? "
                    + "    ) "
                    + "WHERE "
                    + "    ROWNUMER > ?";
            sql = sql.replace(":where", this.getWhere(parameters));
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, start + minCount);
            statement.setInt(2, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            CaseReportData reportData;
            while (resultSet.next()) {
                reportData = new CaseReportData();
                reportData.setCase_id(resultSet.getLong("CASEID"));
                reportData.setCase_type(resultSet.getString("CASETYPE"));
                reportData.setCase_date(resultSet.getString("CASEDATE"));
                reportData.setPriority(resultSet.getString("PRIORITY"));
                reportData.setAssignee_01(resultSet.getString("ASSIGNEE"));
                reportData.setStatus(resultSet.getString("STATUS"));
                reportData.setLastupdateddatetime(resultSet.getString("LASTUPDATEDDATETIME"));
                reportData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                reportData.setCalllogid(resultSet.getString("CALLLOGID"));
                reportData.setCreateduser(resultSet.getString("CREATEDUSER"));
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
    public int getTableDataCount(CaseReportParameters parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT "
                    + "    COUNT(*) AS CNT "
                    + "FROM "
                    + "    AVN_CASE CA "
                    + "INNER JOIN "
                    + "    AVN_CASETYPE CT "
                    + "    ON "
                    + "    CA.CASETYPEID = CT.CASETYPEID "
                    + "INNER JOIN "
                    + "    AVN_CASEPRIORITY CP "
                    + "    ON "
                    + "    CA.PRIORITYID = CP.CASEPRIORITYID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_DEPARTMENT DE "
                    + "    ON "
                    + "    CA.DEPARTMENTID = DE.DEPARTMENTID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_PRODUCT PR "
                    + "    ON "
                    + "    CA.PRODUCTID = PR.PRODUCTID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_ACCOUNT AC "
                    + "    ON "
                    + "    CA.ACCOUNTID = AC.ACCOUNTID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_STATUS ST "
                    + "    ON "
                    + "    CA.STATUSID = ST.STATUSID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_EMPLOYEE EP "
                    + "    ON "
                    + "    CA.ASSIGNEEID1 = EP.EMPLOYEEID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_BRANCH BR "
                    + "    ON "
                    + "    CA.BRANCHID = BR.BRANCHID "
                    + "WHERE "
                    + "    1 = 1 "
                    + "    :where "
                    + "ORDER BY "
                    + "    CA.CREATEDDATETIME DESC";
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
    public Object generateExcelReport(CaseReportParameters parameters, ServletContext context, HashMap<String, Object> parameterMap, String reportBuildPath, String user, Date date) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        XSSFWorkbook workbook;
        Object returnObject = null;
        try {
            int totalRecordCount = 0;
            String sql = "SELECT "
                    + "    COUNT(*) AS CNT "
                    + "FROM "
                    + "    AVN_CASE CA "
                    + "INNER JOIN "
                    + "    AVN_CASETYPE CT "
                    + "    ON "
                    + "    CA.CASETYPEID = CT.CASETYPEID "
                    + "INNER JOIN "
                    + "    AVN_CASEPRIORITY CP "
                    + "    ON "
                    + "    CA.PRIORITYID = CP.CASEPRIORITYID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_DEPARTMENT DE "
                    + "    ON "
                    + "    CA.DEPARTMENTID = DE.DEPARTMENTID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_PRODUCT PR "
                    + "    ON "
                    + "    CA.PRODUCTID = PR.PRODUCTID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_ACCOUNT AC "
                    + "    ON "
                    + "    CA.ACCOUNTID = AC.ACCOUNTID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_STATUS ST "
                    + "    ON "
                    + "    CA.STATUSID = ST.STATUSID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_EMPLOYEE EP "
                    + "    ON "
                    + "    CA.ASSIGNEEID1 = EP.EMPLOYEEID "
                    + "LEFT OUTER JOIN "
                    + "    AVN_BRANCH BR "
                    + "    ON "
                    + "    CA.BRANCHID = BR.BRANCHID "
                    + "WHERE "
                    + "    1 = 1 "
                    + "    :where "
                    + "ORDER BY "
                    + "    CA.CREATEDDATETIME DESC";
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
                            + "        ROWNUM AS ROWNUMER "
                            + "    FROM "
                            + "        ( "
                            + "        SELECT "
                            + "            CA.CASEID                                                AS CASEID, "
                            + "            NVL(BR.ALIASNAME, '--')                                  AS BRANCH, "
                            + "            NVL(TO_CHAR(CA.CASEDATE, 'YYYY-MM-DD'), '--')            AS CASEDATE, "
                            + "            NVL(TO_CHAR(CA.CASEDATE, 'HH24:MI:SS'), '--')            AS CASETIME, "
                            + "            NVL(CT.DESCRIPTION, '--')                                AS CASETYPE, "
                            + "            NVL(CP.DESCRIPTION, '--')                                AS PRIORITY, "
                            + "            NVL(DE.DESCRIPTION, '--')                                AS DEPARTMENT, "
                            + "            NVL(PR.DESCRIPTION, '--')                                AS PRODUCT, "
                            + "            NVL(AC.CCID, '--')                                       AS ACCOUNT, "
                            + "            ST.DESCRIPTION                                           AS STATUS, "
                            + "            NVL(CA.SUBJECT, '--')                                    AS SUBJECT, "
                            + "            NVL(CA.DESCRIPTION, '--')                                AS DESCRIPTION, "
                            + " NVL("
                            + "  (SELECT RESOLUTIONDESCRIPTION "
                            + "  FROM AVN_CASEHISTORY "
                            + "  WHERE CASEHISTORYID = "
                            + "    (SELECT MAX(CASEHISTORYID) FROM AVN_CASEHISTORY WHERE CASEID = CA.CASEID "
                            + "    ) "
                            + "  ), '--') AS RESOLUTIONDESCRIPTION, "
                            + "            NVL(TO_CHAR(CA.CALLLOGID), '--')                         AS CALLLOGID, "
                            + "            NVL(EP.INITIALS, '') || ' ' || NVL(EP.LASTNAME, '')      AS ASSIGNEE, "
                            + "            NVL(CA.CREATEDUSER, '--')                                AS CREATEDUSER, "
                            + "            NVL(TO_CHAR(CA.LASTUPDATEDDATETIME, 'YYYY-MM-DD'), '--') AS LASTUPDATEDDATE, "
                            + "            NVL(TO_CHAR(CA.LASTUPDATEDDATETIME, 'HH24:MI:SS'), '--') AS LASTUPDATEDTIME, "
                            + "            NVL(TO_CHAR(CA.CREATEDDATETIME, 'YYYY-MM-DD'), '--')     AS CREATEDDATE, "
                            + "            NVL(TO_CHAR(CA.CREATEDDATETIME, 'HH24:MI:SS'), '--')     AS CREATEDTIME "
                            + "        FROM "
                            + "            AVN_CASE CA "
                            + "        INNER JOIN "
                            + "            AVN_CASETYPE CT "
                            + "            ON "
                            + "            CA.CASETYPEID = CT.CASETYPEID "
                            + "        INNER JOIN "
                            + "            AVN_CASEPRIORITY CP "
                            + "            ON "
                            + "            CA.PRIORITYID = CP.CASEPRIORITYID "
                            + "        LEFT OUTER JOIN "
                            + "            AVN_DEPARTMENT DE "
                            + "            ON "
                            + "            CA.DEPARTMENTID = DE.DEPARTMENTID "
                            + "        LEFT OUTER JOIN "
                            + "            AVN_PRODUCT PR "
                            + "            ON "
                            + "            CA.PRODUCTID = PR.PRODUCTID "
                            + "        LEFT OUTER JOIN "
                            + "            AVN_ACCOUNT AC "
                            + "            ON "
                            + "            CA.ACCOUNTID = AC.ACCOUNTID "
                            + "        LEFT OUTER JOIN "
                            + "            AVN_STATUS ST "
                            + "            ON "
                            + "            CA.STATUSID = ST.STATUSID "
                            + "        LEFT OUTER JOIN "
                            + "            AVN_EMPLOYEE EP "
                            + "            ON "
                            + "            CA.ASSIGNEEID1 = EP.EMPLOYEEID "
                            + "        LEFT OUTER JOIN "
                            + "            AVN_BRANCH BR "
                            + "            ON "
                            + "            CA.BRANCHID = BR.BRANCHID "
                            + "        WHERE "
                            + "            1 = 1 :where "
                            + "        ORDER BY "
                            + "            CA.CREATEDDATETIME DESC "
                            + "        ) "
                            + "        TB "
                            + "    WHERE "
                            + "        ROWNUM <= ? "
                            + "    ) "
                            + "WHERE "
                            + "    ROWNUMER > ?";
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
                    for (int i = 0; i < columnCount; i++) {
                        //to auto size all column in the sheet
                        if (this.staticWidthColumn == i) {
                            System.out.println(i);
                            sheet.setColumnWidth(i, this.commentColumnWith);
                        } else if (this.staticWidthColumn2 == i) {
                            sheet.setColumnWidth(i, this.commentColumnWith);
                        } else {
                            sheet.autoSizeColumn(i);
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
        XSSFSheet sheet = workbook.createSheet("Cass Report");

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
        cell.setCellValue("CASE REPORT");
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
        cell.setCellValue("Module");
        cell = row.createCell(7);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_DEPARTMENT")));

        cell = row.createCell(9);
        cell.setCellValue("Product");
        cell = row.createCell(10);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_PRODUCT")));

        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("Case Type");
        cell = row.createCell(1);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_CASE_TYPE")));

        cell = row.createCell(3);
        cell.setCellValue("Status");
        cell = row.createCell(4);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_STATUS")));

        cell = row.createCell(6);
        cell.setCellValue("Subject");
        cell = row.createCell(7);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_SUBJECT")));

        row = sheet.createRow(6);
        cell = row.createCell(0);
        cell.setCellValue("Priority");
        cell = row.createCell(1);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_PRIORITY")));

        cell = row.createCell(3);
        cell.setCellValue("Case Date");
        cell = row.createCell(4);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_CASE_DATE")));

        cell = row.createCell(6);
        cell.setCellValue("Assignee");
        cell = row.createCell(7);
        cell.setCellValue(String.valueOf(parameterMap.get("CCL_CRM_DSP_ASSIGNEE")));

        return workbook;
    }

    private void createExcelTableHeandeSection(XSSFWorkbook workbook) throws Exception {
        XSSFCellStyle columnHeaderCell = ExcelCommon.getColumnHeadeCell(workbook);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(detailRow++);
        int column = 0;
        Cell cell = row.createCell(column++);
        cell.setCellValue("CASE ID");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("BRANCH");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("MODULE");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("CASE TYPE");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("DEPARTMENT");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("ASSIGNEE");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("STATUS");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("DESCRIPTION");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("RESOLUTIONDESCRIPTION");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("CASE DATE");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("CASE TIME");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("PRIORITY");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("CCID");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("SUBJECT");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("CALLLOGID");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("AGENT");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("LASTUPDATED DATE");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("LASTUPDATED TIME");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("CREATED DATE");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(column++);
        cell.setCellValue("CREATED TIME");
        cell.setCellStyle(columnHeaderCell);
    }

    private void createExcelTableRow(XSSFWorkbook workbook, ResultSet resultSet) throws Exception {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        int column = 0;
        if (rowCount % 2 == 0) {
            rowColumnCell.setFillForegroundColor(new XSSFColor(new Color(235, 203, 202)));
        } else {
            rowColumnCell.setFillForegroundColor(new XSSFColor(new Color(172, 200, 227)));
        }

        rowColumnCell.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        Row row = sheet.createRow(detailRow++);
        Cell cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CASEID"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("BRANCH"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("PRODUCT"));//Module
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CASETYPE"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("DEPARTMENT"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("ASSIGNEE"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("STATUS"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("DESCRIPTION"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("RESOLUTIONDESCRIPTION"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CASEDATE"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CASETIME"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("PRIORITY"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("ACCOUNT"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("SUBJECT"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CALLLOGID"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CREATEDUSER"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("LASTUPDATEDDATE"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("LASTUPDATEDTIME"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CREATEDDATE"));
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(column++);
        cell.setCellValue(resultSet.getString("CREATEDTIME"));
        cell.setCellStyle(rowColumnCell);

        this.rowCount++;
    }

    private void writeTemporaryFile(XSSFWorkbook workbook, int fileCount, String directory) throws Exception {
        File file;
        FileOutputStream outputStream = null;
        try {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < columnCount; i++) {
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

    private String getWhere(CaseReportParameters parameters) {
        String where = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (parameters.getFrom_date() != null) {
            where += " AND CA.CREATEDDATETIME >= TO_DATE('" + dateFormat.format(Common.getStartingTimeofDay(parameters.getFrom_date())) + "', 'YYYY-MM-DD hh24:mi:ss')";
        }
        if (parameters.getTo_date() != null) {
            where += " AND CA.CREATEDDATETIME <= TO_DATE('" + dateFormat.format(Common.getEndingTimeofDay(parameters.getTo_date())) + "', 'YYYY-MM-DD hh24:mi:ss')";
        }
        if (parameters.getCase_type() != null && !parameters.getCase_type().isEmpty()) {
            where += " AND CA.CASETYPEID = " + parameters.getCase_type();
        }
        if (parameters.getStatus() != null && !parameters.getStatus().isEmpty()) {
            where += "  AND CA.STATUSID = " + parameters.getStatus();
        }
        if (parameters.getPriority() != null && !parameters.getPriority().isEmpty()) {
            where += " AND CA.PRIORITYID = " + parameters.getPriority();
        }
        if (parameters.getCase_date() != null) {
            where += " AND CA.CASEDATE BETWEEN TO_DATE ('" + dateFormat.format(Common.getStartingTimeofDay(parameters.getCase_date())) + "', 'YYYY-MM-DD hh24:mi:ss') AND TO_DATE ('" + dateFormat.format(Common.getEndingTimeofDay(parameters.getCase_date())) + "', 'YYYY-MM-DD hh24:mi:ss')";
        }
        if (parameters.getDepartment() != null && !parameters.getDepartment().isEmpty()) {
            where += " AND CA.DEPARTMENTID = " + parameters.getDepartment();
        }
        if (parameters.getProduct() != null && !parameters.getProduct().isEmpty()) {
            where += " AND CA.PRODUCTID = " + parameters.getProduct();
        }
        if (parameters.getSubject() != null && !parameters.getSubject().isEmpty()) {
            where += " AND CA.SUBJECT LIKE '%" + parameters.getSubject() + "%'";
        }
        if (parameters.getAssignee() != null && !parameters.getAssignee().isEmpty()) {
            where += " AND CA.ASSIGNEEID1 = " + parameters.getAssignee();
        }
        return where;
    }

}

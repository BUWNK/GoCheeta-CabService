/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.casemgt;

import com.avn.ccl.dao.casemgt.CaseDAO;
import com.avn.ccl.model.casemgt.Case;
import com.avn.ccl.model.casemgt.CaseHistory;
import com.avn.ccl.model.dependentrype.CaseUrl;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.DateTime;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author maheshl
 */
public class CaseDAOImpl implements CaseDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int getTableDataCount(Case parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_CASE C INNER JOIN AVN_STATUS ST ON C.STATUSID = ST.STATUSID   INNER JOIN  AVN_CASETYPE CT ON  C.CASETYPEID = CT.CASETYPEID INNER JOIN AVN_CASEPRIORITY CP ON C.PRIORITYID = CP.CASEPRIORITYID INNER JOIN AVN_EMPLOYEE E ON C.ASSIGNEEID1 = E.EMPLOYEEID INNER JOIN AVN_BRANCH B ON C.BRANCHID=B.BRANCHID INNER JOIN AVN_TITLE T ON C.TITLE=T.TITLECODE :where ";
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
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return count;
    }

    @Override
    public List<Case> getTableData(Case parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Case> list = null;
        try {
            String sql = "SELECT * "
                    + "FROM "
                    + "  (SELECT TB.*, "
                    + "    ROWNUM AS ROWNUMER "
                    + "  FROM "
                    + "    (SELECT NVL(E.INITIALS, '') "
                    + "      || ' ' "
                    + "      || NVL(E.LASTNAME, '') AS ASSIGNEE, "
                    + "      C.CASEID, "
                    + "      CT.DESCRIPTION AS CASETYPEDES, "
                    + "      CP.DESCRIPTION AS CASEPRIORITYDES, "
                    + "      ST.DESCRIPTION AS STATUSDES, "
                    + "      B. ALIASNAME, "
                    + "      P.DESCRIPTION AS PDES, "
                    + "      D.DESCRIPTION AS DDES, "
                    + "      C.PHONENUMBER, "
                    + "      NVL(C.CUSTOMERNAME, '--') AS CUSTOMERNAME, "
                    + "      C.NIC, "
                    + "      C.CREATEDUSER, "
                    + "      NVL(C.DESCRIPTION, '--') AS DESCRIPTION, "
                    + "      TO_CHAR(C.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')  AS CREATEDDATETIME "
                    + "    FROM AVN_CASE C "
                    + "    INNER JOIN AVN_STATUS ST "
                    + "    ON C.STATUSID = ST.STATUSID "
                    + "    INNER JOIN AVN_CASETYPE CT "
                    + "    ON C.CASETYPEID = CT.CASETYPEID "
                    + "    INNER JOIN AVN_CASEPRIORITY CP "
                    + "    ON C.PRIORITYID = CP.CASEPRIORITYID "
                    + "    INNER JOIN AVN_EMPLOYEE E "
                    + "    ON C.ASSIGNEEID1 = E.EMPLOYEEID "
                    + "    INNER JOIN AVN_BRANCH B "
                    + "    ON C.BRANCHID=B.BRANCHID "
                    + "    INNER JOIN AVN_TITLE T "
                    + "    ON C.TITLE=T.TITLECODE "
                    + "    INNER JOIN AVN_PRODUCT P "
                    + "    ON C.PRODUCTID=P.PRODUCTID "
                    + "    INNER JOIN AVN_DEPARTMENT D "
                    + "    ON C.DEPARTMENTID=D.DEPARTMENTID :where "
                    + "    ORDER BY C.CASEID DESC "
                    + "    ) TB "
                    + "  WHERE ROWNUM <= ? "
                    + "  ) "
                    + "WHERE ROWNUMER > ? ";

            sql = sql.replace(":where", this.getWhere(parameters));
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, start + minCount);
            statement.setInt(2, start);
            resultSet = statement.executeQuery();

            list = new ArrayList<>();
            Case reportData;
            while (resultSet.next()) {
                reportData = new Case();
                reportData.setCaseId(resultSet.getString("CASEID"));
                reportData.setCaseTypeDes(resultSet.getString("CASETYPEDES"));
                reportData.setBranchDesc(resultSet.getString("ALIASNAME"));
                reportData.setProductDes(resultSet.getString("PDES"));
                reportData.setPriorityDes(resultSet.getString("CASEPRIORITYDES"));
                reportData.setDepartmentDes(resultSet.getString("DDES"));
                reportData.setAssigneeId1(resultSet.getString("ASSIGNEE"));
                reportData.setStatusDes(resultSet.getString("STATUSDES"));
                reportData.setPhonenumber(resultSet.getString("PHONENUMBER"));
                reportData.setCustomername(resultSet.getString("CUSTOMERNAME"));
                reportData.setNic(resultSet.getString("NIC"));
                reportData.setCasecreateduser(resultSet.getString("CREATEDUSER"));
                reportData.setDescription(resultSet.getString("DESCRIPTION"));
                reportData.setCreatedDateTimeString(resultSet.getString("CREATEDDATETIME"));
                list.add(reportData);
            }

        } catch (Exception exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return list;
    }

    @Override
    public Case getCaseByCaseId(String caseId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Case caseBean = null;
        try {
            String sql = "SELECT CT.DESCRIPTION                              AS CASETYPEDES, "
                    + "  TO_CHAR( C.CASEDATE, 'YYYY-MM-DD HH12:mm:ss AM') AS CASEDATE , "
                    + "  CP.DESCRIPTION                                   AS CASEPRIORITYDES, "
                    + "  DP.DESCRIPTION                                   AS DEPARTMENTDES, "
                    + "  PD.DESCRIPTION                                   AS PRODUCTDES, "
                    + "  C.ACCOUNTID, "
                    + "  C.SUBJECT, "
                    + "  C.DESCRIPTION AS CASEDES, "
                    + "  E.FIRSTNAME, "
                    + "  E.EMPLOYEEID , "
                    + "  ST.DESCRIPTION AS STATUSDES, "
                    + "  C.STATUSID, "
                    + "  C.CALLLOGID, "
                    + "  C.CREATEDUSER, "
                    + "  C.CUSTOMERNAME, "
                    + "  C.PHONENUMBER, "
                    + "  NVL(C.NIC, '--')AS NIC, "
                    + "  B.ALIASNAME, "
                    + "  C.BRMF , "
                    + "  C.BRANCHID, "
                    + "  TI.DESCRIPTION, "
                    + "  C.CASEDOCNAME, "
                    + "  C.CASEDOCURL "
                    + "FROM AVN_CASE C, "
                    + "  AVN_EMPLOYEE E, "
                    + "  AVN_CASEPRIORITY CP, "
                    + "  AVN_CASETYPE CT, "
                    + "  AVN_STATUS ST, "
                    + "  AVN_DEPARTMENT DP, "
                    + "  AVN_PRODUCT PD, "
                    + "  AVN_BRANCH B, "
                    + "  AVN_TITLE TI "
                    + "WHERE C.PRODUCTID  = PD.PRODUCTID "
                    + "AND C.DEPARTMENTID = DP.DEPARTMENTID "
                    + "AND C.STATUSID     = ST.STATUSID "
                    + "AND C.CASETYPEID   = CT.CASETYPEID "
                    + "AND C.PRIORITYID   = CP.CASEPRIORITYID "
                    + "AND C.ASSIGNEEID1  = E.EMPLOYEEID "
                    + "AND B.BRANCHID     = C.BRANCHID "
                    + "AND C.TITLE        = TI.TITLECODE "
                    + "AND C.CASEID       = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(caseId));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                caseBean = new Case();
                caseBean.setCaseId(caseId);
                caseBean.setCaseTypeDes(resultSet.getString("CASETYPEDES"));
                caseBean.setCaseDate(resultSet.getString("CASEDATE"));
                caseBean.setPriorityDes(resultSet.getString("CASEPRIORITYDES"));
                caseBean.setDepartmentDes(resultSet.getString("DEPARTMENTDES"));
                caseBean.setProductDes(resultSet.getString("PRODUCTDES"));
                caseBean.setAccountId(resultSet.getString("ACCOUNTID"));
                caseBean.setSubject(resultSet.getString("SUBJECT"));
                caseBean.setDescription(resultSet.getString("CASEDES"));
                caseBean.setAssignee1Name(resultSet.getString("FIRSTNAME"));
                caseBean.setStatusDes(resultSet.getString("STATUSDES"));
                caseBean.setCaseCallLogId(resultSet.getString("CALLLOGID"));
                caseBean.setUser(resultSet.getString("CREATEDUSER"));
                caseBean.setNic(resultSet.getString("NIC"));
                caseBean.setCustomername(resultSet.getString("CUSTOMERNAME"));
                caseBean.setPhonenumber(resultSet.getString("PHONENUMBER"));
                caseBean.setBranchcode(resultSet.getString("BRANCHID"));
                caseBean.setCaseCategory(resultSet.getString("BRMF"));
                caseBean.setBranchDesc(resultSet.getString("ALIASNAME"));
                caseBean.setTitle(resultSet.getString("DESCRIPTION"));
                caseBean.setEmployeeID(resultSet.getString("EMPLOYEEID"));
                caseBean.setHcasedate(resultSet.getString("CASEDATE"));
                caseBean.setHsattudDes(resultSet.getString("STATUSDES"));
                caseBean.setHcomment(resultSet.getString("CASEDES"));
                caseBean.setHbrmf(resultSet.getString("BRMF"));
                caseBean.setHbranchid(resultSet.getString("BRANCHID"));
                caseBean.setHempname(resultSet.getString("FIRSTNAME"));
                caseBean.setHempid(resultSet.getString("EMPLOYEEID"));
                caseBean.setFilename(resultSet.getString("CASEDOCNAME"));
                caseBean.setFile_location(resultSet.getString("CASEDOCURL"));
                caseBean.setStatusid(resultSet.getString("STATUSID"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return caseBean;
    }

    @Override
    public List<CaseHistory> getCaseHistory(String caseId, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CaseHistory> caseHistoryList = new ArrayList<>();
        CaseHistory caseHistoryBean = null;
        try {
            String sql = "SELECT CH.CASEHISTORYID, "
                    + "          CH.CASEID, "
                    + "          TO_CHAR(CH.AFFECTEDDATE, 'YYYY-MM-DD HH12:mm:ss AM') as AFFECTEDDATE, "
                    + "          S.DESCRIPTION AS CASEDES, "
                    + "          CH.RESOLUTIONDESCRIPTION, "
                    + "          E.FIRSTNAME, "
                    + "          CASE CH.BRMF WHEN 'BR' THEN 'Branch' WHEN 'MF' THEN 'Micro Finance' ELSE CH.BRMF END AS BRMF, "
                    + "          B.ALIASNAME "
                    + "FROM AVN_CASEHISTORY CH, AVN_SYSTEMUSER SU,AVN_STATUS S, AVN_EMPLOYEE E, AVN_BRANCH B "
                    + "WHERE CH.STATUSID = S.STATUSID AND CH.ASSIGNEEID1=E.EMPLOYEEID AND CH.BRANCHID=B.BRANCHID AND CH.CASEID =? AND CH.STATUSID != 2 UNION SELECT CH.CASEHISTORYID,CH.CASEID,TO_CHAR(CH.AFFECTEDDATE, 'YYYY-MM-DD HH12:mm:ss AM'),S.DESCRIPTION AS CASEDES,CH.RESOLUTIONDESCRIPTION,E.FIRSTNAME, CH.BRMF, B.BRANCHDESCRIPTION FROM AVN_CASEHISTORY CH, AVN_SYSTEMUSER SU,AVN_STATUS S , AVN_EMPLOYEE E,AVN_BRANCH B WHERE CH.STATUSID = S.STATUSID AND CH.ASSIGNEEID1=E.EMPLOYEEID  AND CH.BRANCHID=B.BRANCHID AND CH.CASEID =? AND CH.STATUSID = 2  AND  CH.CREATEDUSER=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(caseId));
            statement.setInt(2, Integer.valueOf(caseId));
            statement.setString(3, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                caseHistoryBean = new CaseHistory();
                caseHistoryBean.setCaseHistoryId(resultSet.getInt("CASEHISTORYID"));
                caseHistoryBean.setCaseId(resultSet.getString("CASEID"));
                caseHistoryBean.setAffectedDate(resultSet.getString("AFFECTEDDATE"));
                caseHistoryBean.setStatusDes(resultSet.getString("CASEDES"));
                caseHistoryBean.setResolutionDescription(resultSet.getString("RESOLUTIONDESCRIPTION"));
                caseHistoryBean.setHbrmf(resultSet.getString("BRMF"));
                caseHistoryBean.setHempname(resultSet.getString("FIRSTNAME"));
                caseHistoryBean.setHbranchDes(resultSet.getString("ALIASNAME"));
                caseHistoryList.add(caseHistoryBean);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return caseHistoryList;
    }

    public CaseHistory getlastUpdateCaseHistory(String maxhitroyid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CaseHistory caseHistoryBean = null;
        try {
            String sql = "SELECT CH.CASEHISTORYID, "
                    + "  CH.CASEID, "
                    + "  TO_CHAR(CH.AFFECTEDDATE, 'YYYY-MM-DD HH12:mm:ss AM') AS AFFECTEDDATE, "
                    + "  CH.STATUSID, "
                    + "  S.DESCRIPTION, "
                    + "  CH.BRANCHID, "
                    + "  B.BRANCHDESCRIPTION, "
                    + "  CH.BRMF, "
                    + "  CH.ASSIGNEEID1, "
                    + "  E.FIRSTNAME, "
                    + "  CH.RESOLUTIONDESCRIPTION "
                    + "FROM AVN_CASEHISTORY CH "
                    + "INNER JOIN AVN_STATUS S ON CH.STATUSID   = S.STATUSID "
                    + "INNER JOIN AVN_BRANCH B ON CH.BRANCHID     = B.BRANCHID "
                    + "LEFT OUTER JOIN AVN_EMPLOYEE E ON CH.ASSIGNEEID1  = E.EMPLOYEEID "
                    + "WHERE CH.CASEHISTORYID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, maxhitroyid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                caseHistoryBean = new CaseHistory();
                caseHistoryBean.setHistoryid(resultSet.getString("CASEHISTORYID"));
                caseHistoryBean.setCaseId(resultSet.getString("CASEID"));
                caseHistoryBean.setHcasedate(resultSet.getString("AFFECTEDDATE"));
                caseHistoryBean.setHsattudDes(resultSet.getString("DESCRIPTION"));
                caseHistoryBean.setHcomment(resultSet.getString("RESOLUTIONDESCRIPTION"));
                caseHistoryBean.setHbranchid(resultSet.getString("BRANCHID"));
                caseHistoryBean.setHbranchDes(resultSet.getString("BRANCHDESCRIPTION"));
                caseHistoryBean.setHstatusid(resultSet.getString("STATUSID"));
                caseHistoryBean.setHbrmf(resultSet.getString("BRMF"));
                caseHistoryBean.setHempid(resultSet.getString("ASSIGNEEID1"));
                caseHistoryBean.setHempname(resultSet.getString("FIRSTNAME"));

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return caseHistoryBean;
    }

    @Override
    public long insertCase(Case userCase, String user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long caseid = 0;
        try {
            String sql = "INSERT INTO AVN_CASE ( CASEDATE, CASETYPEID, PRIORITYID, "
                    + "DEPARTMENTID, PRODUCTID, ACCOUNTID, SUBJECT, DESCRIPTION, ASSIGNEEID1, "
                    + "STATUSID, CREATEDDATETIME, LASTUPDATEDDATETIME,CREATEDUSER,NOTIFICATIONLEVEL,CUSTOMERNAME,NIC,PHONENUMBER,BRANCHID,BRMF,CALLLOGID,TITLE,CUSTOMERVISITID) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?,?)";
            connection = dataSource.getConnection();
            String[] generatedColumns = {"CASEID"};
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setTimestamp(1, DateTime.getTimestampFromDateTime(userCase.getCaseDate()));
            statement.setInt(2, Integer.valueOf(userCase.getCaseTypeId()));
            statement.setInt(3, Integer.valueOf(userCase.getPriorityId()));
            if (userCase.getDepartmentId() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, Integer.valueOf(userCase.getDepartmentId()));
            }
            statement.setInt(5, Integer.valueOf(userCase.getProductId()));
            if (userCase.getAccountId() != null && !userCase.getAccountId().isEmpty()) {
                statement.setInt(6, Integer.valueOf(userCase.getAccountId()));
            } else {
                statement.setNull(6, Types.INTEGER);
            }
            if (userCase.getSubject() == null) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, userCase.getSubject());
            }
            if (userCase.getDescription() == null) {
                statement.setNull(8, Types.VARCHAR);
            } else {
                statement.setString(8, userCase.getDescription());
            }
            if (userCase.getEmployeeId() == 0) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setLong(9, userCase.getEmployeeId());
            }
            if (userCase.getStatusid() != null && !userCase.getStatusid().isEmpty()) {
                statement.setInt(10, Integer.valueOf(userCase.getStatusid()));
            } else {
                statement.setInt(10, MasterDataVarList.CCL_CODE_STATUS_OPEN);
            }
            statement.setString(11, user);
            statement.setInt(12, MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_CASE);
            if (userCase.getCustomername() == null) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, userCase.getCustomername());
            }
            if (userCase.getNic() != null && !userCase.getNic().isEmpty()) {
                statement.setString(14, userCase.getNic());
            } else {
                statement.setNull(14, Types.VARCHAR);
            }
            statement.setString(15, userCase.getPhonenumber());
            statement.setInt(16, Integer.valueOf(userCase.getBranchcode()));
            if (userCase.getCaseCategory() == null) {
                statement.setNull(17, Types.VARCHAR);
            } else {
                statement.setString(17, userCase.getCaseCategory());
            }
            if (userCase.getCaseCallLogId() != null && !userCase.getCaseCallLogId().isEmpty()) {
                statement.setInt(18, Integer.valueOf(userCase.getCaseCallLogId()));
            } else {
                statement.setNull(18, Types.INTEGER);
            }
            if (userCase.getTitle() != null) {
                statement.setString(19, userCase.getTitle());
            } else {
                statement.setNull(19, Types.VARCHAR);
            }
            if (userCase.getCustomervisitId() != null && !userCase.getCustomervisitId().isEmpty()) {
                statement.setInt(20, Integer.valueOf(userCase.getCustomervisitId()));
            } else {
                statement.setNull(20, Types.INTEGER);
            }
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                caseid = resultSet.getLong(1);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return caseid;
    }

    @Override
    public long getCaseAssignedEmployeeId(long caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long employeeid = 0;
        try {
            String sql = "SELECT C.ASSIGNEEID1 FROM AVN_CASE C WHERE C.CASEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, caseid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeid = resultSet.getLong("ASSIGNEEID1");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return employeeid;
    }

    @Override
    public void insertToCaseHistory(CaseHistory caseHistory, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "INSERT INTO AVN_CASEHISTORY (CASEID, AFFECTEDDATE, STATUSID, RESOLUTIONDESCRIPTION, ASSIGNEEID1,CREATEDUSER, CREATEDDATETIME, LASTUPDATEDDATETIME,BRANCHID,BRMF,TRANSFERREASON) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,?,?)";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(caseHistory.getCaseId()));
            statement.setTimestamp(2, DateTime.getTimestampFromDateTime(caseHistory.getHcasedate()));
            statement.setInt(3, Integer.valueOf(caseHistory.getHstatusid()));
            statement.setString(4, caseHistory.getHcomment());
            statement.setInt(5, Integer.valueOf(caseHistory.getHempid()));
            statement.setString(6, username);
            statement.setInt(7, Integer.valueOf(caseHistory.getHbranchid()));
            statement.setString(8, caseHistory.getHbrmf());
            if (caseHistory.getReassignreason() != null && !caseHistory.getReassignreason().isEmpty()) {
                statement.setString(9, caseHistory.getReassignreason());
            } else {
                statement.setNull(9, Types.VARCHAR);
            }

            statement.executeUpdate();
            sql = "Update AVN_CASE SET STATUSID = ? WHERE CASEID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(caseHistory.getHstatusid()));
            statement.setInt(2, Integer.valueOf(caseHistory.getCaseId()));
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException | NumberFormatException | ParseException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public List<Case> getCaseDetail(String Uid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Case caseBean = null;
        List<Case> caseList = new ArrayList<>();
        try {
            String sql = "SELECT C.CASEID,C.SUBJECT,CT.DESCRIPTION AS CASETYPEDES,CP.DESCRIPTION AS CASEPRIORITYDES,ST.DESCRIPTION AS STATUSDES FROM AVN_CASE C,AVN_SYSTEMUSER S,AVN_EMPLOYEE E,AVN_CASEPRIORITY CP,AVN_CASETYPE CT,AVN_STATUS ST WHERE C.STATUSID = ST.STATUSID AND C.CASETYPEID = CT.CASETYPEID AND C.PRIORITYID = CP.CASEPRIORITYID AND C.ASSIGNEEID1 = S.EMPLOYEEID AND S.EMPLOYEEID = E.EMPLOYEEID AND S.USERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, Uid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                caseBean = new Case();
                caseBean.setCaseId(resultSet.getString("CASEID"));
                caseBean.setSubject(resultSet.getString("SUBJECT"));
                caseBean.setPriorityDes(resultSet.getString("CASEPRIORITYDES"));
                caseBean.setStatusDes(resultSet.getString("STATUSDES"));
                caseList.add(caseBean);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return caseList;
    }

    @Override
    public int getTableDataCount(String user, long employeeId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS CNT from AVN_CASE C  " +
                    "INNER JOIN AVN_CASETYPE CT ON C.CASETYPEID = CT.CASETYPEID  " +
                    "INNER JOIN AVN_CASEPRIORITY CP ON C.PRIORITYID = CP.CASEPRIORITYID  " +
                    "INNER JOIN AVN_STATUS ST ON C.STATUSID = ST.STATUSID  " +
                    "INNER JOIN AVN_EMPLOYEE E ON C.ASSIGNEEID1 = E.EMPLOYEEID  " +
                    "INNER JOIN AVN_PRODUCT PR ON C.PRODUCTID = PR.PRODUCTID "
                    + " WHERE CREATEDUSER =? OR ASSIGNEEID1=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            statement.setLong(2, employeeId);
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
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }

        return count;
    }

    @Override
    public List<Case> getTableData(String username, long employeeid, int minCount, int start, String sort) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Case> list = null;
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
                    + "            C.CASEID, "
                    + "            NVL(E.INITIALS, '') || ' ' || NVL(E.LASTNAME, '') AS ASSIGNEE, "
                    + "            NVL(C.NIC, '--') AS NIC, "
                    + "            NVL(C.PHONENUMBER, '--') AS PHONENUMBER, "
                    + "            CT.DESCRIPTION AS CASETYPE, "
                    + "            NVL(PR.DESCRIPTION, '--') AS MODULE, "
                    + "            CP.DESCRIPTION AS CASEPRIORITY, "
                    + "            ST.DESCRIPTION AS STATUS, "
                    + "            CASE WHEN C.CREATEDUSER = '" + username + "' THEN 'YES' ELSE 'NO' END AS CREATED, "
                    + "            CASE WHEN C.ASSIGNEEID1 = " + employeeid + " THEN 'YES' ELSE 'NO' END AS ASSIGNED "
                    + "        FROM "
                    + "            AVN_CASE C "
                    + "            INNER JOIN AVN_CASETYPE CT ON C.CASETYPEID = CT.CASETYPEID "
                    + "            INNER JOIN AVN_CASEPRIORITY CP ON C.PRIORITYID = CP.CASEPRIORITYID "
                    + "            INNER JOIN AVN_STATUS ST ON C.STATUSID = ST.STATUSID "
                    + "            INNER JOIN AVN_EMPLOYEE E ON C.ASSIGNEEID1 = E.EMPLOYEEID "
                    + "            INNER JOIN AVN_PRODUCT PR ON C.PRODUCTID = PR.PRODUCTID"
                    + "        WHERE 1 = 1 "
                    + "            AND C.CREATEDUSER = ? "
                    + "            OR C.ASSIGNEEID1 = ? "
                    + "        " + sort
                    + "        ) TB "
                    + "    WHERE "
                    + "        ROWNUM <= ? "
                    + "    ) "
                    + "WHERE "
                    + "    ROWNUMER > ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setLong(2, employeeid);
            statement.setInt(3, start + minCount);
            statement.setInt(4, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            Case reportData;
            while (resultSet.next()) {
                reportData = new Case();
                reportData.setCaseId(resultSet.getString("CASEID"));
                reportData.setAssigneeId1(resultSet.getString("ASSIGNEE"));
                reportData.setNic(resultSet.getString("NIC"));
                reportData.setPhonenumber(resultSet.getString("PHONENUMBER"));
                reportData.setCaseTypeDes(resultSet.getString("CASETYPE"));
                reportData.setProductDes(resultSet.getString("MODULE"));
                reportData.setPriorityDes(resultSet.getString("CASEPRIORITY"));
                reportData.setStatusDes(resultSet.getString("STATUS"));
                reportData.setCreated(resultSet.getString("CREATED"));
                reportData.setAssignees(resultSet.getString("ASSIGNED"));
                list.add(reportData);
            }
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return list;
    }

    @Override
    public String getCaseID(HttpSession session) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String caseID = null;

        String username = (String) session.getAttribute("username");
        try {
            String sql = "SELECT MAX(CASEID) AS CASEID FROM AVN_CASE WHERE CREATEDUSER=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                caseID = resultSet.getString("CaseID");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return caseID;
    }

    @Override
    public Case getUserRoleIdAndEmployeeId(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Case caseBeen = null;
        try {
            String sql = "SELECT USERROLE,EMPLOYEEID FROM AVN_SYSTEMUSER WHERE USERID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            caseBeen = new Case();
            while (resultSet.next()) {
                caseBeen.setUserRole(resultSet.getInt("USERROLE"));
                caseBeen.setEmployeeId(resultSet.getInt("EMPLOYEEID"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return caseBeen;
    }

    public List<String> getCaseHistoryCountByCaseid(String caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List maxidandcount = new ArrayList();
        try {
            String sql = "SELECT COUNT(*) AS CNT,max(CASEHISTORYID)AS MAXID FROM AVN_CASEHISTORY WHERE CASEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, caseid);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                maxidandcount.add(resultSet.getString("CNT"));
            }
            String sql2 = "SELECT max(CASEHISTORYID)AS MAXID FROM AVN_CASEHISTORY WHERE CASEID=?";
            statement = connection.prepareStatement(sql2);
            statement.setString(1, caseid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                maxidandcount.add(resultSet.getString("MAXID"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return maxidandcount;
    }

    public void insertCaseFilelocation(Case usercase) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_CASE SET CASEDOCURL=?,CASEDOCNAME=? WHERE CASEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, usercase.getFile_location());
            statement.setString(2, usercase.getFilename());
            statement.setString(3, usercase.getCaseId());
            statement.executeUpdate();
        } catch (SQLException | NumberFormatException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public Map<String, String> getAssigneList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> assigneeList = null;
        try {
            String sql = "SELECT E.EMPLOYEEID,  "
                    + "                    E.NAMEINFULL  "
                    + "                 || ' - '  "
                    + "                  || B.BRANCHCODEOLD  "
                    + "                  || ' - '  "
                    + "                   || IFNULL(E.EMPLOYEELEVEL,'') AS ASSIGNEE  "
                    + "                FROM employee E  "
                    + "                INNER JOIN territorymapemployee TE ON E.EMPLOYEEID=TE.EMPLOYEEID "
                    + "               INNER JOIN branch B  "
                    + "                 ON TE.TERRITORYMAPID = B.BRANCHID  "
                    + "              WHERE UPPER(E.EMPLOYEELEVEL) > 'LEVEL 2' ORDER BY E.FIRSTNAME";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            assigneeList = new LinkedHashMap<>();
            assigneeList.put("", "-- Select --");
            while (resultSet.next()) {
                assigneeList.put(resultSet.getString("EMPLOYEEID"), resultSet.getString("ASSIGNEE"));
            }
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception exception) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception exception) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception exception) {
                }
            }
        }
        return assigneeList;
    }

    private String getWhere(Case parameters) {
        String where = "where 1 = 1 ";
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("nic")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "AND C.NIC LIKE '%" + parameters.getInput().trim() + "%'";
        }
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("caseId")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "AND C.CASEID LIKE '%" + parameters.getInput().trim() + "%'";
        }
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("caseCallLogId")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "AND C.CALLLOGID LIKE '%" + parameters.getInput().trim() + "%'";
        }
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("customervisitId")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "AND C.CUSTOMERVISITID LIKE '%" + parameters.getInput().trim() + "%'";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (parameters.getFrom_date() != null) {
            where += "AND C.CREATEDDATETIME >= TO_CHAR('" + dateFormat.format(Common.getStartingTimeofDay(parameters.getFrom_date())) + "')";
        }
        if (parameters.getTo_date() != null) {
            where += "AND C.CREATEDDATETIME <= TO_CHAR('" + dateFormat.format(Common.getEndingTimeofDay(parameters.getTo_date())) + "')";
        }
        if (parameters.getFirstName() != null) {
            where += "AND UPPER(C.CUSTOMERNAME)  LIKE UPPER('%" + parameters.getFirstName().trim() + "%') ";
        }
        if (parameters.getTelephone() != null) {
            where += "AND C.PHONENUMBER LIKE '%" + parameters.getTelephone().trim() + "%' ";
        }

        return where;
    }

    public CaseUrl getCaseUrl(String fileID, String caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CaseUrl fileurl = null;
        try {
            String sql = "SELECT FILEPATH, FILENAME FROM AVN_CASEURL WHERE DOCID = ? AND CASEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, fileID);
            statement.setString(2, caseid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                fileurl = new CaseUrl();
                fileurl.setFilepath(resultSet.getString("FILEPATH"));
                fileurl.setFilename(resultSet.getString("FILENAME"));
            }
        } catch (SQLException | NumberFormatException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return fileurl;
    }

    public List<CaseUrl> getListUrl(String caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CaseUrl> fileurllist = new ArrayList<>();
        try {
            String sql = "SELECT DOCID, FILEPATH, FILENAME, CASEID FROM AVN_CASEURL WHERE CASEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, caseid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CaseUrl fileurl = new CaseUrl();
                fileurl.setDocid(resultSet.getLong("DOCID"));
                fileurl.setFilepath(resultSet.getString("FILEPATH"));
                fileurl.setFilename(resultSet.getString("FILENAME"));
                fileurl.setCaseid(resultSet.getLong("CASEID"));
                fileurllist.add(fileurl);
            }
        } catch (SQLException | NumberFormatException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return fileurllist;
    }

    public void insertCaseUrl(String filePath, String fileName, long caseid, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "INSERT INTO AVN_CASEURL (FILEPATH, FILENAME, CASEID, CREATEDUSER, CREATEDTIME, LASTUPDATEDTIME) VALUES( ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, filePath);
            statement.setString(2, fileName);
            statement.setLong(3, caseid);
            statement.setString(4, username);
            statement.executeUpdate();

        } catch (SQLException | NumberFormatException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }

    }

    public void updateAttachment(String fileid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_CASEURL WHERE DOCID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, fileid);
            statement.executeUpdate();
        } catch (SQLException | NumberFormatException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
    }

}

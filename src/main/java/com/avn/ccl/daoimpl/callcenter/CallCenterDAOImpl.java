/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.callcenter;

import com.avn.ccl.dao.callcenter.CallcenterDao;
import com.avn.ccl.model.callcenter.CallCenter;
import com.avn.ccl.util.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.avn.ccl.util.DateTime;
import static com.avn.ccl.util.varlist.CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lahiru
 */
public class CallCenterDAOImpl implements CallcenterDao {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    /**
     * @param dataSource the dataSource to set
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long insertCallLog(CallCenter callLogForm, HttpSession session) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String user = (String) session.getAttribute("username");
        long callLogId = 0;
        try {
            String sql = "INSERT "
                    + "INTO AVN_CALLLOG "
                    + "  ( "
                    + "    ACCOUNTID, "
                    + "    TITLE, "
                    + "    NAMEINFULL, "
                    + "    PREFERREDNAME, "
                    + "    LASTNAME, "
                    + "    LANGUAGEID, "
                    + "    CALLDIRECTIONID, "
                    + "    PRODUCTID, "
                    + "    CASETYPEID, "
                    + "    CALLSTARTDATETIME, "
                    + "    FOLLOWUPACTIONID, "
                    + "    CALLBACKDATETIME, "
                    + "    PHONENUMBER, "
                    + "    PHONENUMBER2, "
                    + "    COMMENTS, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER, "
                    + "    STATUSID, "
                    + "    BRANCHID, "
                    + "    COMPANY "
                    + "  ) "
                    + "  VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            String[] generatedColumns = {"CALLLOGID"};
            statement = connection.prepareStatement(sql, generatedColumns);
            if (callLogForm.getCallLogId() != null && !callLogForm.getCallLogId().isEmpty()) {
                statement.setInt(1, Integer.valueOf(callLogForm.getCallLogId()));
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.setString(2, callLogForm.getTitle());
            if (callLogForm.getName_in_full() != null) {
                statement.setString(3, callLogForm.getName_in_full());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            if (callLogForm.getPreferred_name() != null) {
                statement.setString(4, callLogForm.getPreferred_name());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }
            statement.setString(5, callLogForm.getLast_name());
            if (callLogForm.getPreferred_language() != null && !callLogForm.getPreferred_language().isEmpty()) {
                statement.setInt(6, Integer.valueOf(callLogForm.getPreferred_language()));
            } else {
                statement.setNull(6, Types.INTEGER);
            }
            statement.setInt(7, Integer.valueOf(callLogForm.getCallDirection()));
            statement.setInt(8, Integer.valueOf(callLogForm.getProductId()));
            statement.setInt(9, Integer.valueOf(callLogForm.getCaseTypeId()));
            statement.setTimestamp(10, DateTime.getTimestampFromDateAndTime(callLogForm.getStartDate(), callLogForm.getStartTime()));
            statement.setInt(11, Integer.valueOf(callLogForm.getFollowUpAction()));
            if (callLogForm.getCallbackDate() != null && callLogForm.getCallbackTime() != null) {
                statement.setTimestamp(12, DateTime.getTimestampFromDateAndTime(callLogForm.getCallbackDate(), callLogForm.getCallbackTime()));
            } else {
                statement.setNull(12, Types.TIMESTAMP);
            }
            statement.setString(13, callLogForm.getTelephone());
            if (callLogForm.getTelephone2() != null) {
                statement.setString(14, callLogForm.getTelephone2());
            } else {
                statement.setNull(14, Types.VARCHAR);
            }

            if (callLogForm.getComments() != null) {
                statement.setString(15, callLogForm.getComments());
            } else {
                statement.setNull(15, Types.VARCHAR);
            }
            statement.setString(16, user);
            statement.setInt(17, Integer.valueOf(callLogForm.getStatus()));
            statement.setString(18, callLogForm.getBranchcode());
            if (callLogForm.getCompanyname() != null && !callLogForm.getCompanyname().isEmpty()) {
                statement.setString(19, callLogForm.getCompanyname());
            } else {
                statement.setNull(19, Types.VARCHAR);
            }

            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                callLogId = resultSet.getLong(1);
            }
            if (callLogForm.getCallbackDate() != null && callLogForm.getCallbackTime() != null) {
                sql = "INSERT INTO AVN_REMINDERNOTIFICATION "
                        + " (DESCRIPTION,REMINDERUSER, REMINDERTIME,"
                        + " SOURCETABLEID,SECTIONID,CREATEDDATETIME,LASTUPDATEDDATETIME,"
                        + " CREATEDUSER,REMINDERSTATUS,VIEWEDSTATUS) "
                        + " VALUES"
                        + "(?,?,?,?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,?,?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, "Call Log Created With Call Back Time Call Log Id " + callLogId);
                statement.setString(2, user);
                statement.setTimestamp(3, DateTime.getTimestampFromDateAndTime(callLogForm.getCallbackDate(), callLogForm.getCallbackTime()));
                statement.setLong(4, callLogId);
                statement.setInt(5, MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID);
                statement.setString(6, "SYSTEM");
                statement.setInt(7, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_REMIND);
                statement.setInt(8, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_VIEWED);
                statement.execute();
            }

            connection.commit();
        } catch (SQLException ex) {
            if (connection != null) {
                connection.rollback();
            }
            throw ex;
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
        return callLogId;
    }

//    @Override
//    public List<CallCenter> getCallLog() throws Exception {
//        List<CallCenter> callLogList = new ArrayList<>();
//        try {
//            String sql = "SELECT  C.ACCOUNTID, C.PHONENUMBER, C.NAMEINFULL, CD.DESCRIPTION AS CALL_DIRECTION, FA.DESCRIPTION AS FOLLOW_UP_ACTION,C.COMMENTS FROM AVN_CALLLOG C, AVN_CALLDIRECTIONS CD, AVN_FOLLOWUPACTIONS FA WHERE C.CALLDIRECTIONID=CD.CALLDIRECTIONSID AND C.FOLLOWUPACTIONID=FA.FOLLOUPACTIONID";
//            connection = dataSource.getConnection();
//            statement = connection.prepareStatement(sql);
//            resultSet = statement.executeQuery();
//            CallCenter callCenter;
//            while (resultSet.next()) {
//                callCenter = new CallCenter();
//                callCenter.setAccountId(resultSet.getString("ACCOUNTID"));
//                callCenter.setTelephone(resultSet.getString("PHONENUMBER"));
//                callCenter.setNameInFull(resultSet.getString("NAMEINFULL"));
//                callCenter.setCallDirectionDes(resultSet.getString("CALL_DIRECTION"));
//                callCenter.setFollowUpActionDes(resultSet.getString("FOLLOW_UP_ACTION"));
//                callCenter.setComments(resultSet.getString("COMMENTS"));
//                callLogList.add(callCenter);
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (Exception exception) {
//                }
//            }
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (Exception exception) {
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (Exception exception) {
//                }
//            }
//        }
//        return callLogList;
//    }
    @Override
    public List<CallCenter> getCallDetail(String Uid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CallCenter> callList = new ArrayList<>();
        try {
            String sql = "SELECT C.CALLLOGID, C.PHONENUMBER, CD.DESCRIPTION AS CALL_DIRECTION, FA.DESCRIPTION AS FOLLOW_UP_ACTION FROM AVN_CALLLOG C, AVN_CALLDIRECTIONS CD, AVN_FOLLOWUPACTIONS FA, AVN_SYSTEMUSER S, AVN_EMPLOYEE E WHERE C.CALLDIRECTIONID=CD.CALLDIRECTIONSID AND C.FOLLOWUPACTIONID=FA.FOLLOUPACTIONID AND C.CREATEDUSER = S.USERID AND S.EMPLOYEEID = E.EMPLOYEEID AND S.USERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, Uid);
            resultSet = statement.executeQuery();
            CallCenter callCenterBean;
            while (resultSet.next()) {
                callCenterBean = new CallCenter();
                callCenterBean.setTelephone(resultSet.getString("PHONENUMBER"));
                callCenterBean.setCallDirectionDes(resultSet.getString("CALL_DIRECTION"));
                callCenterBean.setFollowUpActionDes(resultSet.getString("FOLLOW_UP_ACTION"));
                callList.add(callCenterBean);
            }
        } catch (SQLException e) {
            throw e;
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
        return callList;
    }

    @Override
    public int getTableDataCount(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS CNT FROM AVN_CALLLOG WHERE CREATEDUSER=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
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
        return count;
    }

    @Override
    public List<CallCenter> getTableData(String username, int minCount, int start, String sort) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CallCenter> list = null;
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
                    + "        C.CALLLOGID, NVL(C.NAMEINFULL, '--') AS NAMEINFULL, P.DESCRIPTION, C.PHONENUMBER, CD.DESCRIPTION AS CALL_DIRECTION, FA.DESCRIPTION AS FOLLOW_UP_ACTION"
                    + "        FROM "
                    + "           AVN_CALLLOG C, AVN_CALLDIRECTIONS CD, AVN_FOLLOWUPACTIONS FA, AVN_SYSTEMUSER S, AVN_EMPLOYEE E, AVN_PRODUCT P   "
                    + "        WHERE "
                    + "              C.CALLDIRECTIONID=CD.CALLDIRECTIONSID "
                    + "              AND C.FOLLOWUPACTIONID=FA.FOLLOUPACTIONID "
                    + "              AND C.CREATEDUSER = S.USERID "
                    + "              AND S.EMPLOYEEID = E.EMPLOYEEID "
                    + "              AND C.PRODUCTID = P.PRODUCTID "
                    + "              AND C.CREATEDUSER = ? "
                    + "       " + sort
                    + "        ) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + "WHERE "
                    + "    ROWNUMER > ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            CallCenter reportData;
            while (resultSet.next()) {
                reportData = new CallCenter();
                reportData.setCallLogId(resultSet.getString("CALLLOGID"));
                reportData.setTelephone(resultSet.getString("PHONENUMBER"));
                reportData.setCallDirectionDes(resultSet.getString("CALL_DIRECTION"));
                reportData.setFollowUpActionDes(resultSet.getString("FOLLOW_UP_ACTION"));
                reportData.setName_in_full(resultSet.getString("NAMEINFULL"));
                reportData.setProductIdDes(resultSet.getString("DESCRIPTION"));

                list.add(reportData);
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
        return list;
    }

    @Override
    public CallCenter getCallByCallLogId(int CallLogID) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        CallCenter callCenterBean = null;
        List<CallCenter> callList = new ArrayList<>();
        try {
            String sql = "SELECT NVL(C.PREFERREDNAME, '')AS PREFERREDNAME, "
                    + "  NVL(C.LASTNAME, '')          AS LASTNAME, "
                    + "  C.CALLLOGID, "
                    + "  NVL(C.TITLE, '') AS TITLE, "
                    + "  C.LANGUAGEID, "
                    + "  C.ACCOUNTID, "
                    + "  NVL(C.NAMEINFULL, '')AS NAMEINFULL, "
                    + "  C.CALLSTARTDATETIME, "
                    + "  C.PHONENUMBER, "
                    + "  NVL(C.PHONENUMBER2, '') AS PHONENUMBER2, "
                    + "  CD.DESCRIPTION            AS CALL_DIRECTION, "
                    + "  C.CALLDIRECTIONID, "
                    + "  FA.DESCRIPTION AS FOLLOW_UP_ACTION, "
                    + "  C.FOLLOWUPACTIONID, "
                    + "  C.CALLBACKDATETIME, "
                    + "  NVL(C.COMMENTS, '')    AS COMMENTS, "
                    + "  NVL(L.DESCRIPTION, '') AS LANGUAGE, "
                    + "  C.PRODUCTID, "
                    + "  C.CASETYPEID, "
                    + "  P.DESCRIPTION  AS DES1, "
                    + "  CT.DESCRIPTION AS DES2, "
                    + "  S.DESCRIPTION  AS STATUSDES , "
                    + "  C.STATUSID     AS STATUSID, "
                    + "  C.BRANCHID, "
                    + "  B.ALIASNAME, "
                    + "  NVL(C.COMPANY, '') AS COMPANY "
                    + "FROM AVN_CALLLOG C, "
                    + "  AVN_STATUS S, "
                    + "  AVN_CALLDIRECTIONS CD, "
                    + "  AVN_FOLLOWUPACTIONS FA, "
                    + "  AVN_PREFLANGUAGE L, "
                    + "  AVN_CASETYPE CT, "
                    + "  AVN_PRODUCT P, "
                    + "  AVN_BRANCH B "
                    + "WHERE C.CALLDIRECTIONID=CD.CALLDIRECTIONSID "
                    + "AND C.FOLLOWUPACTIONID =FA.FOLLOUPACTIONID "
                    + "AND C.LANGUAGEID       =L.LANGUAGEID "
                    + "AND C.PRODUCTID        =P.PRODUCTID "
                    + "AND C.CASETYPEID       =CT.CASETYPEID "
                    + "AND S.STATUSID         =C.STATUSID "
                    + "AND C.BRANCHID         =B.BRANCHID "
                    + "AND C.CALLLOGID        =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, CallLogID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                callCenterBean = new CallCenter();
                callCenterBean.setCallLogId(resultSet.getString("CALLLOGID"));
                callCenterBean.setAccountId(resultSet.getString("ACCOUNTID"));
                callCenterBean.setName_in_full(resultSet.getString("NAMEINFULL"));
                callCenterBean.setStartDate(resultSet.getTimestamp("CALLSTARTDATETIME"));
                callCenterBean.setStartTime(new Date(resultSet.getTimestamp("CALLSTARTDATETIME").getTime()));
                callCenterBean.setTelephone(resultSet.getString("PHONENUMBER"));
                callCenterBean.setCallDirection(resultSet.getString("CALLDIRECTIONID"));
                callCenterBean.setCallDirectionDes(resultSet.getString("CALL_DIRECTION"));
                callCenterBean.setFollowUpAction(resultSet.getString("FOLLOWUPACTIONID"));
                callCenterBean.setFollowUpActionDes(resultSet.getString("FOLLOW_UP_ACTION"));
                if (resultSet.getTimestamp("CALLBACKDATETIME") != null) {
                    callCenterBean.setCallbackDate(resultSet.getTimestamp("CALLBACKDATETIME"));
                    callCenterBean.setCallbackTime(new Date(resultSet.getTimestamp("CALLBACKDATETIME").getTime()));
                }
                callCenterBean.setComments(resultSet.getString("COMMENTS"));
                callCenterBean.setLanguageID(resultSet.getString("LANGUAGE"));
                callCenterBean.setPreferred_language(resultSet.getString("LANGUAGEID"));
                callCenterBean.setTitle(resultSet.getString("TITLE"));
                callCenterBean.setPreferred_name(resultSet.getString("PREFERREDNAME"));
                callCenterBean.setLast_name(resultSet.getString("LASTNAME"));
                callCenterBean.setProductId(resultSet.getString("PRODUCTID"));
                callCenterBean.setProductIdDes(resultSet.getString("DES1"));
                callCenterBean.setCaseTypeId(resultSet.getString("CASETYPEID"));
                callCenterBean.setCaseTypeDes(resultSet.getString("DES2"));
                callCenterBean.setTelephone2(resultSet.getString("PHONENUMBER2"));
                callCenterBean.setStatusDes(resultSet.getString("STATUSDES"));
                callCenterBean.setStatus(resultSet.getString("STATUSID"));
                callCenterBean.setBranchcode(resultSet.getString("BRANCHID"));
                callCenterBean.setBranchDes(resultSet.getString("ALIASNAME"));
                callCenterBean.setCompanyname(resultSet.getString("COMPANY"));
                callList.add(callCenterBean);
            }
        } catch (Exception e) {
            throw e;
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
        return callCenterBean;
    }

    @Override
    public List<CallCenter> getCall(String filter, String input) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CallCenter> callList = new ArrayList<>();
        String sql = null;
        if (filter.equals("telephone")) {
            sql = "SELECT  C.CALLLOGID, C.NAMEINFULL, C.PHONENUMBER, CD.DESCRIPTION AS CALL_DIRECTION, FA.DESCRIPTION AS FOLLOW_UP_ACTION FROM AVN_CALLLOG C, AVN_CALLDIRECTIONS CD, AVN_FOLLOWUPACTIONS FA WHERE C.CALLDIRECTIONID=CD.CALLDIRECTIONSID AND C.FOLLOWUPACTIONID=FA.FOLLOUPACTIONID AND  PHONENUMBER like ?";
        } else if (filter.equals("nameInFull")) {
            sql = "SELECT  C.CALLLOGID, C.NAMEINFULL, C.PHONENUMBER, CD.DESCRIPTION AS CALL_DIRECTION, FA.DESCRIPTION AS FOLLOW_UP_ACTION FROM AVN_CALLLOG C, AVN_CALLDIRECTIONS CD, AVN_FOLLOWUPACTIONS FA WHERE C.CALLDIRECTIONID=CD.CALLDIRECTIONSID AND C.FOLLOWUPACTIONID=FA.FOLLOUPACTIONID AND  NAMEINFULL like ?";
        }

        CallCenter callCenterBean = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, input + "%");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                callCenterBean = new CallCenter();
                callCenterBean.setCallLogId(resultSet.getString("CALLLOGID"));
                callCenterBean.setNameInFull(resultSet.getString("NAMEINFULL"));
                callCenterBean.setTelephone(resultSet.getString("PHONENUMBER"));
                callCenterBean.setCallDirectionDes(resultSet.getString("CALL_DIRECTION"));
                callCenterBean.setFollowUpActionDes(resultSet.getString("FOLLOW_UP_ACTION"));

                callList.add(callCenterBean);
            }
        } catch (Exception e) {
            throw e;

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
        return callList;
    }

    @Override
    public int getTableDataCount(CallCenter parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = " SELECT  COUNT(*) CNT FROM  AVN_CALLLOG C INNER JOIN  AVN_CALLDIRECTIONS CD   ON  C.CALLDIRECTIONID = CD.CALLDIRECTIONSID "
                    + " INNER JOIN   AVN_FOLLOWUPACTIONS FA ON C.FOLLOWUPACTIONID = FA.FOLLOUPACTIONID INNER JOIN AVN_PRODUCT P ON C.PRODUCTID=P.PRODUCTID "
                    + " INNER JOIN AVN_CASETYPE CT ON C.CASETYPEID=CT.CASETYPEID INNER JOIN AVN_FOLLOWUPACTIONS FA ON C.FOLLOWUPACTIONID=FA.FOLLOUPACTIONID "
                    + " INNER JOIN AVN_STATUS S ON C.STATUSID=S.STATUSID INNER JOIN AVN_BRANCH B ON C.BRANCHID=B.BRANCHID "
                    + " LEFT OUTER JOIN AVN_TITLE T ON C.TITLE=T.TITLECODE LEFT OUTER JOIN AVN_PREFLANGUAGE PL ON C.LANGUAGEID=PL.LANGUAGEID :where ";
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
        return count;
    }

    @Override
    public List<CallCenter> getTableData(CallCenter parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CallCenter> list = null;
        try {
            String sql = "";
            sql = "SELECT "
                    + "    * "
                    + "FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( SELECT C.CREATEDUSER, C.CALLLOGID,  NVL(C.NAMEINFULL, '--') AS NAMEINFULL, NVL(C.PHONENUMBER, '--') AS PHONENUMBER,  NVL(CD.DESCRIPTION, '--') AS CALL_DIRECTION,   NVL(FA.DESCRIPTION, '--') AS FOLLOW_UP_ACTION  FROM  AVN_CALLLOG C INNER JOIN  AVN_CALLDIRECTIONS CD "
                    + " ON  C.CALLDIRECTIONID = CD.CALLDIRECTIONSID INNER JOIN   AVN_FOLLOWUPACTIONS FA ON C.FOLLOWUPACTIONID = FA.FOLLOUPACTIONID INNER JOIN AVN_PRODUCT P ON C.PRODUCTID=P.PRODUCTID INNER JOIN AVN_CASETYPE CT ON C.CASETYPEID=CT.CASETYPEID INNER JOIN AVN_FOLLOWUPACTIONS FA ON C.FOLLOWUPACTIONID=FA.FOLLOUPACTIONID "
                    + " INNER JOIN AVN_STATUS S ON C.STATUSID=S.STATUSID INNER JOIN AVN_BRANCH B ON C.BRANCHID=B.BRANCHID LEFT OUTER JOIN AVN_TITLE T ON C.TITLE=T.TITLECODE LEFT OUTER JOIN AVN_PREFLANGUAGE PL ON C.LANGUAGEID=PL.LANGUAGEID :where "
                    + "        ORDER BY C.CREATEDDATETIME DESC"
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
            CallCenter reportData;
            while (resultSet.next()) {
                reportData = new CallCenter();
                reportData.setCallLogId(resultSet.getString("CALLLOGID"));
                reportData.setNameInFull(resultSet.getString("NAMEINFULL"));
                reportData.setTelephone(resultSet.getString("PHONENUMBER"));
                reportData.setCallDirectionDes(resultSet.getString("CALL_DIRECTION"));
                reportData.setFollowUpActionDes(resultSet.getString("FOLLOW_UP_ACTION"));
                reportData.setUsername(resultSet.getString("CREATEDUSER"));
                list.add(reportData);
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
        return list;
    }

    @Override
    public void Callupdate(CallCenter callLogUpdateForm, HttpSession session) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String user = (String) session.getAttribute("username");
        try {
            String sql = "UPDATE AVN_CALLLOG SET ACCOUNTID=?,NAMEINFULL=?,CALLSTARTDATETIME=?, "
                    + "CALLDIRECTIONID=?, FOLLOWUPACTIONID=?, CALLBACKDATETIME=? ,PHONENUMBER=?, "
                    + "COMMENTS=? ,LASTUPDATEDDATETIME=CURRENT_TIMESTAMP, CREATEDUSER=?,TITLE=?,LANGUAGEID=?,PREFERREDNAME=?,LASTNAME=?,STATUSID=?, BRANCHID=?,PHONENUMBER2=?,COMPANY=? WHERE CALLLOGID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            if (callLogUpdateForm.getAccountId() != null && !callLogUpdateForm.getAccountId().isEmpty()) {
                statement.setInt(1, Integer.valueOf(callLogUpdateForm.getAccountId()));
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            if (callLogUpdateForm.getName_in_full() != null && !callLogUpdateForm.getName_in_full().isEmpty()) {
                statement.setString(2, callLogUpdateForm.getName_in_full());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            statement.setTimestamp(3, DateTime.getTimestampFromDateAndTime(callLogUpdateForm.getStartDate(), callLogUpdateForm.getStartTime()));
            statement.setInt(4, Integer.valueOf(callLogUpdateForm.getCallDirection()));
            statement.setInt(5, Integer.valueOf(callLogUpdateForm.getFollowUpAction()));
            if (callLogUpdateForm.getCallbackDate() != null && callLogUpdateForm.getCallbackTime() != null) {
                statement.setTimestamp(6, DateTime.getTimestampFromDateAndTime(callLogUpdateForm.getCallbackDate(), callLogUpdateForm.getCallbackTime()));
            } else {
                statement.setNull(6, Types.TIMESTAMP);
            }
            statement.setString(7, callLogUpdateForm.getTelephone());
            if (callLogUpdateForm.getComments() != null && !callLogUpdateForm.getComments().isEmpty()) {
                statement.setString(8, callLogUpdateForm.getComments());
            } else {
                statement.setNull(8, Types.VARCHAR);
            }
            statement.setString(9, user);
            if (callLogUpdateForm.getTitle() != null && !callLogUpdateForm.getTitle().isEmpty() && !callLogUpdateForm.getTitle().equalsIgnoreCase(EMPTY_OR_NULL_REPLACE_VALUE)) {
                statement.setString(10, callLogUpdateForm.getTitle());
            } else {
                statement.setNull(10, Types.VARCHAR);
            }
            if (callLogUpdateForm.getPreferred_language() != null && !callLogUpdateForm.getPreferred_language().isEmpty()) {
                statement.setString(11, callLogUpdateForm.getPreferred_language());
            } else {
                statement.setNull(11, Types.VARCHAR);
            }
            if (callLogUpdateForm.getPreferred_name() != null && !callLogUpdateForm.getPreferred_name().isEmpty() && !callLogUpdateForm.getPreferred_name().equalsIgnoreCase(EMPTY_OR_NULL_REPLACE_VALUE)) {
                statement.setString(12, callLogUpdateForm.getPreferred_name());
            } else {
                statement.setNull(12, Types.VARCHAR);
            }
            if (callLogUpdateForm.getLast_name() != null && !callLogUpdateForm.getLast_name().isEmpty() && !callLogUpdateForm.getLast_name().equalsIgnoreCase(EMPTY_OR_NULL_REPLACE_VALUE)) {
                statement.setString(13, callLogUpdateForm.getLast_name());
            } else {
                statement.setNull(13, Types.VARCHAR);
            }
            statement.setString(14, callLogUpdateForm.getStatus());
            statement.setString(15, callLogUpdateForm.getBranchcode());
            if (callLogUpdateForm.getTelephone2() != null && !callLogUpdateForm.getTelephone2().isEmpty() && !callLogUpdateForm.getTelephone2().equalsIgnoreCase(EMPTY_OR_NULL_REPLACE_VALUE)) {
                statement.setString(16, callLogUpdateForm.getTelephone2());
            } else {
                statement.setNull(16, Types.VARCHAR);
            }
            if (callLogUpdateForm.getCompanyname() != null && !callLogUpdateForm.getCompanyname().isEmpty() && !callLogUpdateForm.getCompanyname().equalsIgnoreCase(EMPTY_OR_NULL_REPLACE_VALUE)) {
                statement.setString(17, callLogUpdateForm.getCompanyname());
            } else {
                statement.setNull(17, Types.VARCHAR);
            }
            statement.setInt(18, Integer.valueOf(callLogUpdateForm.getCallLogId()));

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
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

    }

    @Override
    public String getlanguage(String stakeholderreferenceno) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String p_language = null;
        try {
            String sql = "Select LANGUAGE from AVN_ACCOUNT where CCID= ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, stakeholderreferenceno);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                p_language = resultSet.getString("LANGUAGE");
            }
        } catch (Exception e) {
            throw e;
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
        return p_language;
    }

    @Override
    public String getCallID(HttpSession session) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String callLogId = null;

        String username = (String) session.getAttribute("username");
        try {
            String sql = "SELECT MAX(CALLLOGID) AS CALLLOGID FROM AVN_CALLLOG WHERE CREATEDUSER=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            resultSet.next();
//            System.out.println(resultSet.getString("CALLLOGID"));
            callLogId = resultSet.getString("CALLLOGID");
        } catch (SQLException e) {
            throw e;
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
        return callLogId;
    }

    private String getWhere(CallCenter parameters) {
        String where = "WHERE 1 = 1 ";
        if (parameters.getSearchoption() != null && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("callLogID")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "AND C.CALLLOGID LIKE " + parameters.getInput().trim();
        } else if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("telephone")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "AND C.PHONENUMBER LIKE '%" + parameters.getInput().trim() + "%'";
        } else if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("nameInFull")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "AND UPPER(C.NAMEINFULL) LIKE '%" + (parameters.getInput().trim()).toUpperCase() + "%'";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (parameters.getFrom_date() != null) {
            where += "AND C.CREATEDDATETIME >= TO_CHAR('" + dateFormat.format(Common.getStartingTimeofDay(parameters.getFrom_date())) + "')";
        }
        if (parameters.getTo_date() != null) {
            where += "AND C.CREATEDDATETIME <= TO_CHAR('" + dateFormat.format(Common.getEndingTimeofDay(parameters.getTo_date())) + "')";
        }
        if (parameters.getFirstName() != null) {
            where += "AND UPPER(C.PREFERREDNAME)  LIKE UPPER('%" + parameters.getFirstName().trim() + "%') ";
        }
        if (parameters.getTelephone() != null) {
            where += "AND C.PHONENUMBER LIKE '%" + parameters.getTelephone().trim() + "%' ";
        }

        return where;
    }

}

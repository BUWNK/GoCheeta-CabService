/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.customervisit;

import com.avn.ccl.dao.customervisit.CustomerVisitDAO;
import com.avn.ccl.model.customervisit.CustomerVisit;
import com.avn.ccl.util.DateTime;
import static com.avn.ccl.util.varlist.CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


/**
 * @Author : Isuru
 * @Document : CustomerVisitDAOImpl
 * @Created on : Jan 6, 2016, 9:35:40 AM
 */
public class CustomerVisitDAOImpl implements CustomerVisitDAO {

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
    public long insertCustomerVisit(CustomerVisit customervist, HttpSession session) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String user = (String) session.getAttribute("username");
        long customerVisitID = 0;
        try {
            String sql = "INSERT INTO AVN_CUSTOMERVISIT (TITLE,NAMEINFULL,PREFERREDNAME,LASTNAME,LANGUAGEID,PRODUCTID,CASETYPEID,CUSVISITDATETIME,FOLLOWUPACTIONID,CALLBACKDATETIME,PHONENUMBER, PHONENUMBER2, COMMENTS,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER,STATUSID,BRANCHID,COMPANY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?,?,?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            String[] generatedColumns = {"CUSTOMERVISITID"};
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setString(1, customervist.getTitle());
            if (customervist.getName_in_full() != null) {
                statement.setString(2, customervist.getName_in_full());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (customervist.getPreferred_name() != null) {
                statement.setString(3, customervist.getPreferred_name());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            statement.setString(4, customervist.getLast_name());
            if (customervist.getPreferred_language() != null && !customervist.getPreferred_language().isEmpty()) {
                statement.setInt(5, Integer.valueOf(customervist.getPreferred_language()));
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setInt(6, Integer.valueOf(customervist.getProductId()));
            statement.setInt(7, Integer.valueOf(customervist.getCaseTypeId()));
            statement.setTimestamp(8, DateTime.getTimestampFromDateAndTime(customervist.getStartDate(), customervist.getStartTime()));
            statement.setInt(9, Integer.valueOf(customervist.getFollowUpAction()));
            if (customervist.getCallbackDate() != null && customervist.getCallbackTime() != null) {
                statement.setTimestamp(10, DateTime.getTimestampFromDateAndTime(customervist.getCallbackDate(), customervist.getCallbackTime()));
            } else {
                statement.setNull(10, Types.TIMESTAMP);
            }
            statement.setString(11, customervist.getTelephone());
            if (customervist.getTelephone2() != null) {
                statement.setString(12, customervist.getTelephone2());
            } else {
                statement.setNull(12, Types.VARCHAR);
            }

            if (customervist.getComments() != null) {
                statement.setString(13, customervist.getComments());
            } else {
                statement.setNull(13, Types.VARCHAR);
            }
            statement.setString(14, user);
            statement.setInt(15, Integer.valueOf(customervist.getStatus()));
            statement.setString(16, customervist.getBranchcode());
            if (customervist.getCompanyname() != null && !customervist.getCompanyname().isEmpty()) {
                statement.setString(17, customervist.getCompanyname());
            } else {
                statement.setNull(17, Types.VARCHAR);
            }

            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                customerVisitID = resultSet.getLong(1);
            }
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
        return customerVisitID;
    }

    @Override
    public List<CustomerVisit> getCustomerVisitLog() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CustomerVisit> customerVisiList = new ArrayList<>();
        try {
            String sql = "SELECT   C.PHONENUMBER, C.NAMEINFULL, CD.DESCRIPTION AS CALL_DIRECTION, FA.DESCRIPTION AS FOLLOW_UP_ACTION,C.COMMENTS FROM AVN_CUSTOMERVISIT C, AVN_CALLDIRECTIONS CD, AVN_FOLLOWUPACTIONS FA WHERE C.CALLDIRECTIONID=CD.CALLDIRECTIONSID AND C.FOLLOWUPACTIONID=FA.FOLLOUPACTIONID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            CustomerVisit customervisit;
            while (resultSet.next()) {
                customervisit = new CustomerVisit();
                customervisit.setTelephone(resultSet.getString("PHONENUMBER"));
                customervisit.setNameInFull(resultSet.getString("NAMEINFULL"));
                customervisit.setFollowUpActionDes(resultSet.getString("FOLLOW_UP_ACTION"));
                customervisit.setComments(resultSet.getString("COMMENTS"));
                customerVisiList.add(customervisit);
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
        return customerVisiList;
    }

    @Override
    public CustomerVisit getCustomerVisitByID(int customervisitID) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CustomerVisit customerVisitBean = null;
        try {
            String sql = "SELECT C.CUSTOMERVISITID, NVL(C.PREFERREDNAME, '--')AS PREFERREDNAME,NVL(C.LASTNAME, '--') AS LASTNAME,C.CUSVISITDATETIME,NVL(C.TITLE, '--') AS TITLE,C.LANGUAGEID, NVL(C.NAMEINFULL, '--')AS NAMEINFULL, C.CUSVISITDATETIME, C.PHONENUMBER,NVL(C.PHONENUMBER2, '--') AS PHONENUMBER2, "
                    + " FA.DESCRIPTION AS FOLLOW_UP_ACTION, C.FOLLOWUPACTIONID, C.CALLBACKDATETIME,  NVL(C.COMMENTS, '--')AS COMMENTS, NVL(L.DESCRIPTION, '--') AS LANGUAGE,C.PRODUCTID, C.CASETYPEID,P.DESCRIPTION AS DES1,CT.DESCRIPTION AS DES2, "
                    + " S.DESCRIPTION AS STATUSDES , C.STATUSID AS STATUSID, C.BRANCHID, B.ALIASNAME,NVL(C.COMPANY, '--') AS COMPANY   FROM AVN_CUSTOMERVISIT C, AVN_STATUS S,  AVN_FOLLOWUPACTIONS FA,AVN_PREFLANGUAGE L,AVN_CASETYPE CT, AVN_PRODUCT P, AVN_BRANCH B  WHERE  "
                    + " C.FOLLOWUPACTIONID=FA.FOLLOUPACTIONID AND  C.LANGUAGEID =L.LANGUAGEID AND C.PRODUCTID=P.PRODUCTID AND C.CASETYPEID=CT.CASETYPEID AND S.STATUSID=C.STATUSID AND C.BRANCHID=B.BRANCHID AND C.CUSTOMERVISITID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customervisitID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customerVisitBean = new CustomerVisit();
                customerVisitBean.setCustomervisitId(resultSet.getString("CUSTOMERVISITID"));
                customerVisitBean.setName_in_full(resultSet.getString("NAMEINFULL"));
                customerVisitBean.setStartDate(resultSet.getTimestamp("CUSVISITDATETIME"));
                customerVisitBean.setStartTime(new Date(resultSet.getTimestamp("CUSVISITDATETIME").getTime()));
                customerVisitBean.setTelephone(resultSet.getString("PHONENUMBER"));
                customerVisitBean.setFollowUpAction(resultSet.getString("FOLLOWUPACTIONID"));
                customerVisitBean.setFollowUpActionDes(resultSet.getString("FOLLOW_UP_ACTION"));
                if (resultSet.getTimestamp("CALLBACKDATETIME") != null) {
                    customerVisitBean.setCallbackDate(resultSet.getTimestamp("CALLBACKDATETIME"));
                    customerVisitBean.setCallbackTime(new Date(resultSet.getTimestamp("CALLBACKDATETIME").getTime()));
                }
                customerVisitBean.setComments(resultSet.getString("COMMENTS"));
                customerVisitBean.setLanguageID(resultSet.getString("LANGUAGE"));
                customerVisitBean.setPreferred_language(resultSet.getString("LANGUAGEID"));
                customerVisitBean.setTitle(resultSet.getString("TITLE"));
                customerVisitBean.setPreferred_name(resultSet.getString("PREFERREDNAME"));
                customerVisitBean.setLast_name(resultSet.getString("LASTNAME"));
                customerVisitBean.setProductId(resultSet.getString("PRODUCTID"));
                customerVisitBean.setProductIdDes(resultSet.getString("DES1"));
                customerVisitBean.setCaseTypeId(resultSet.getString("CASETYPEID"));
                customerVisitBean.setCaseTypeDes(resultSet.getString("DES2"));
                customerVisitBean.setTelephone2(resultSet.getString("PHONENUMBER2"));
                customerVisitBean.setStatusDes(resultSet.getString("STATUSDES"));
                customerVisitBean.setStatus(resultSet.getString("STATUSID"));
                customerVisitBean.setBranchcode(resultSet.getString("BRANCHID"));
                customerVisitBean.setBranchDes(resultSet.getString("ALIASNAME"));
                customerVisitBean.setCompanyname(resultSet.getString("COMPANY"));
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
        return customerVisitBean;
    }

    @Override
    public int getTableDataCount(CustomerVisit parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = " SELECT  COUNT(*) CNT FROM  AVN_CUSTOMERVISIT C "
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
    public void customerVisitUpdate(CustomerVisit customervisitdata, HttpSession session) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String user = (String) session.getAttribute("username");
        try {
            String sql = "UPDATE AVN_CUSTOMERVISIT SET NAMEINFULL=?,CUSVISITDATETIME=?, "
                    + "  FOLLOWUPACTIONID=?, CALLBACKDATETIME=? ,PHONENUMBER=?, "
                    + "COMMENTS=? ,LASTUPDATEDDATETIME=CURRENT_TIMESTAMP, CREATEDUSER=?,TITLE=?,LANGUAGEID=?,PREFERREDNAME=?,LASTNAME=?,STATUSID=?, BRANCHID=?,PHONENUMBER2=?,COMPANY=? WHERE CUSTOMERVISITID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);

            if (customervisitdata.getName_in_full() != null && !customervisitdata.getName_in_full().isEmpty()) {
                statement.setString(1, customervisitdata.getName_in_full());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            statement.setTimestamp(2, DateTime.getTimestampFromDateAndTime(customervisitdata.getStartDate(), customervisitdata.getStartTime()));
            statement.setInt(3, Integer.valueOf(customervisitdata.getFollowUpAction()));
            if (customervisitdata.getCallbackDate() != null && customervisitdata.getCallbackTime() != null) {
                statement.setTimestamp(4, DateTime.getTimestampFromDateAndTime(customervisitdata.getCallbackDate(), customervisitdata.getCallbackTime()));
            } else {
                statement.setNull(4, Types.TIMESTAMP);
            }
            statement.setString(5, customervisitdata.getTelephone());
            if (customervisitdata.getComments() != null && !customervisitdata.getComments().isEmpty()) {
                statement.setString(6, customervisitdata.getComments());
            } else {
                statement.setNull(6, Types.VARCHAR);
            }
            statement.setString(7, user);
            if (customervisitdata.getTitle() != null && !customervisitdata.getTitle().isEmpty() && !customervisitdata.getTitle().equalsIgnoreCase(EMPTY_OR_NULL_REPLACE_VALUE)) {
                statement.setString(8, customervisitdata.getTitle());
            } else {
                statement.setNull(8, Types.VARCHAR);
            }
            if (customervisitdata.getPreferred_language() != null && !customervisitdata.getPreferred_language().isEmpty()) {
                statement.setString(9, customervisitdata.getPreferred_language());
            } else {
                statement.setNull(9, Types.VARCHAR);
            }
            if (customervisitdata.getPreferred_name() != null && !customervisitdata.getPreferred_name().isEmpty() && !customervisitdata.getPreferred_name().equalsIgnoreCase(EMPTY_OR_NULL_REPLACE_VALUE)) {
                statement.setString(10, customervisitdata.getPreferred_name());
            } else {
                statement.setNull(10, Types.VARCHAR);
            }
            if (customervisitdata.getLast_name() != null && !customervisitdata.getLast_name().isEmpty() && !customervisitdata.getLast_name().equalsIgnoreCase(EMPTY_OR_NULL_REPLACE_VALUE)) {
                statement.setString(11, customervisitdata.getLast_name());
            } else {
                statement.setNull(11, Types.VARCHAR);
            }
            statement.setString(12, customervisitdata.getStatus());
            statement.setString(13, customervisitdata.getBranchcode());
            if (customervisitdata.getTelephone2() != null && !customervisitdata.getTelephone2().isEmpty() && !customervisitdata.getTelephone2().equalsIgnoreCase(EMPTY_OR_NULL_REPLACE_VALUE)) {
                statement.setString(14, customervisitdata.getTelephone2());
            } else {
                statement.setNull(14, Types.VARCHAR);
            }
            if (customervisitdata.getCompanyname() != null && !customervisitdata.getCompanyname().isEmpty() && !customervisitdata.getCompanyname().equalsIgnoreCase(EMPTY_OR_NULL_REPLACE_VALUE)) {
                statement.setString(15, customervisitdata.getCompanyname());
            } else {
                statement.setNull(15, Types.VARCHAR);
            }
            statement.setInt(16, Integer.valueOf(customervisitdata.getCustomervisitId()));

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
    public List<CustomerVisit> getTableData(CustomerVisit parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CustomerVisit> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + "FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( SELECT C.CREATEDUSER, C.CUSTOMERVISITID,  NVL(C.NAMEINFULL, '--') AS NAMEINFULL, NVL(C.PHONENUMBER, '--') AS PHONENUMBER,     NVL(FA.DESCRIPTION, '--') AS FOLLOW_UP_ACTION  FROM  AVN_CUSTOMERVISIT C  "
                    + "  INNER JOIN   AVN_FOLLOWUPACTIONS FA ON C.FOLLOWUPACTIONID = FA.FOLLOUPACTIONID INNER JOIN AVN_PRODUCT P ON C.PRODUCTID=P.PRODUCTID INNER JOIN AVN_CASETYPE CT ON C.CASETYPEID=CT.CASETYPEID INNER JOIN AVN_FOLLOWUPACTIONS FA ON C.FOLLOWUPACTIONID=FA.FOLLOUPACTIONID "
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
            CustomerVisit reportData;
            while (resultSet.next()) {
                reportData = new CustomerVisit();
                reportData.setCustomervisitId(resultSet.getString("CUSTOMERVISITID"));
                reportData.setNameInFull(resultSet.getString("NAMEINFULL"));
                reportData.setTelephone(resultSet.getString("PHONENUMBER"));
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

    private String getWhere(CustomerVisit parameters) {
        String where = "";
        if (parameters.getSearchoption() != null && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("customervisitId")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "WHERE C.CUSTOMERVISITID LIKE " + parameters.getInput().trim();
        } else if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("telephone")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "WHERE C.PHONENUMBER LIKE '%" + parameters.getInput().trim() + "%'";
        } else if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("nameInFull")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "WHERE UPPER(C.NAMEINFULL) LIKE '%" + (parameters.getInput().trim()).toUpperCase() + "%'";
        }

        return where;
    }

}

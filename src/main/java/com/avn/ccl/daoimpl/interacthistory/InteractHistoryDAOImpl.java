/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.interacthistory;

import com.avn.ccl.dao.interacthistory.InteractHistoryDAO;
import com.avn.ccl.model.interacthistory.Interacthistory;
import com.avn.ccl.util.Common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author ISURU
 */
public class InteractHistoryDAOImpl implements InteractHistoryDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Interacthistory> getTableData(Interacthistory parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Interacthistory> list = null;
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
                    + "        SELECT TBL.MODULE, "
                    + "  TBL.ID, "
                    + "  TBL.SUMMERY, "
                    + "  TBL.STATUS, "
                    + "  TBL.CREATEDDATETIME, "
                    + "  TBL.LASTUPDATEDDATETIME, "
                    + "  TBL.CREATEDUSER, "
                    + "  TBL.ACTION, "
                    + "  TBL.CUSTOMERNAME, "
                    + "  TBL.TELEPHONE, "
                    + "  TBL.CASETYPE "
                    + " FROM "
                    + "  (SELECT 'CALL LOG'        AS MODULE, "
                    + "    CAL.CALLLOGID           AS ID, "
                    + "  NVL(CAL.COMMENTS, '--')   AS SUMMERY, "
                    + "    ST.DESCRIPTION          AS STATUS, "
                    + "    TO_CHAR(CAL.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')  AS CREATEDDATETIME, "
                    + "    TO_CHAR(CAL.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS') AS LASTUPDATEDDATETIME, "
                    + "    CAL.CREATEDUSER         AS CREATEDUSER, "
                    + "    NVL(CAL.NAMEINFULL, '--')  AS CUSTOMERNAME , "
                    + "    NVL(CAL.PHONENUMBER, '--')         AS TELEPHONE , "
                    + "    CT.DESCRIPTION         AS CASETYPE , "
                    + "   'CL' AS ACTION "
                    + "  FROM AVN_CALLLOG CAL "
                    + "  INNER JOIN AVN_STATUS ST "
                    + "  ON CAL.STATUSID = ST.STATUSID INNER JOIN AVN_CASETYPE CT ON CAL.CASETYPEID=CT.CASETYPEID"
                    + "  UNION ALL "
                    + "  SELECT 'CUSTOMER VISIT'   AS MODULE, "
                    + "    CUV.CUSTOMERVISITID     AS ID, "
                    + "    NVL(CUV.COMMENTS, '--')    AS SUMMERY, "
                    + "    ST.DESCRIPTION          AS STATUS, "
                    + "    TO_CHAR(CUV.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')     AS CREATEDDATETIME, "
                    + "    TO_CHAR(CUV.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS') AS LASTUPDATEDDATETIME, "
                    + "    CUV.CREATEDUSER         AS CREATEDUSER, "
                    + "    NVL(CUV.NAMEINFULL, '--')  AS CUSTOMERNAME, "
                    + "    NVL(CUV.PHONENUMBER, '--')         AS TELEPHONE, "
                    + "    CT.DESCRIPTION         AS CASETYPE , "
                    + "   'CV' AS ACTION "
                    + "  FROM AVN_CUSTOMERVISIT CUV "
                    + "  INNER JOIN AVN_STATUS ST "
                    + "  ON CUV.STATUSID = ST.STATUSID INNER JOIN AVN_CASETYPE CT ON CUV.CASETYPEID=CT.CASETYPEID"
                    + "  UNION ALL "
                    + "  SELECT 'CASE'             AS MODULE, "
                    + "    CAS.CASEID              AS ID, "
                    + "    CAS.DESCRIPTION         AS SUMMERY, "
                    + "    NVL(ST.DESCRIPTION, '--')          AS STATUS, "
                    + "    TO_CHAR(CAS.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')     AS CREATEDDATETIME, "
                    + "    TO_CHAR(CAS.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS') AS LASTUPDATEDDATETIME, "
                    + "    CAS.CREATEDUSER         AS CREATEDUSER, "
                    + "    NVL(CAS.CUSTOMERNAME, '--')         AS CUSTOMERNAME, "
                    + "    NVL(CAS.PHONENUMBER, '--')         AS TELEPHONE, "
                    + "    CT.DESCRIPTION         AS CASETYPE , "
                    + "   'CS' AS ACTION "
                    + "  FROM AVN_CASE CAS "
                    + "  INNER JOIN AVN_STATUS ST "
                    + "  ON CAS.STATUSID = ST.STATUSID INNER JOIN AVN_CASETYPE CT ON CAS.CASETYPEID=CT.CASETYPEID"
                    + "  ) TBL "
                    + " WHERE "
                    + "    1 = 1 :where "
                    + "ORDER BY TBL.CREATEDDATETIME DESC ) TB  "
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
            Interacthistory reportData;
            while (resultSet.next()) {
                reportData = new Interacthistory();
                reportData.setModule(resultSet.getString("MODULE"));
                reportData.setId(resultSet.getString("ID"));
                reportData.setFirstName(resultSet.getString("CUSTOMERNAME"));
                reportData.setTelephone(resultSet.getString("TELEPHONE"));
                reportData.setStatus(resultSet.getString("STATUS"));
                reportData.setDiscription(resultSet.getString("SUMMERY"));
                reportData.setCreatedDateTime(resultSet.getString("CREATEDDATETIME"));
                reportData.setLastUpdateDatetime(resultSet.getString("LASTUPDATEDDATETIME"));
                reportData.setCreateduser(resultSet.getString("CREATEDUSER"));
                reportData.setCaseTypeDes(resultSet.getString("CASETYPE"));
                reportData.setType(resultSet.getString("ACTION"));
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
    public int getTableDataCount(Interacthistory parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) as CNT "
                    + "FROM "
                    + "  (SELECT 'CALL LOG'        AS MODULE, "
                    + "    CAL.CALLLOGID           AS ID, "
                    + "    CAL.COMMENTS            AS SUMMERY, "
                    + "    ST.DESCRIPTION          AS STATUS, "
                    + "    TO_CHAR(CAL.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')  AS CREATEDDATETIME, "
                    + "    TO_CHAR(CAL.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS') AS LASTUPDATEDDATETIME, "
                    + "    CAL.CREATEDUSER         AS CREATEDUSER, "
                    + "    CAL.NAMEINFULL          AS CUSTOMERNAME , "
                    + "    CAL.PHONENUMBER         AS TELEPHONE "
                    + "  FROM AVN_CALLLOG CAL "
                    + "  INNER JOIN AVN_STATUS ST "
                    + "  ON CAL.STATUSID = ST.STATUSID "
                    + "  UNION ALL "
                    + "  SELECT 'CUSTOMER VISIT'   AS MODULE, "
                    + "    CUV.CUSTOMERVISITID     AS ID, "
                    + "    CUV.COMMENTS            AS SUMMERY, "
                    + "    ST.DESCRIPTION          AS STATUS, "
                    + "    TO_CHAR(CUV.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')     AS CREATEDDATETIME, "
                    + "    TO_CHAR(CUV.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS') AS LASTUPDATEDDATETIME, "
                    + "    CUV.CREATEDUSER         AS CREATEDUSER, "
                    + "    CUV.NAMEINFULL          AS CUSTOMERNAME, "
                    + "    CUV.PHONENUMBER         AS TELEPHONE "
                    + "  FROM AVN_CUSTOMERVISIT CUV "
                    + "  INNER JOIN AVN_STATUS ST "
                    + "  ON CUV.STATUSID = ST.STATUSID "
                    + "  UNION ALL "
                    + "  SELECT 'CASE'             AS MODULE, "
                    + "    CAS.CASEID              AS ID, "
                    + "    CAS.DESCRIPTION         AS SUMMERY, "
                    + "    ST.DESCRIPTION          AS STATUS, "
                    + "   TO_CHAR(CAS.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')     AS CREATEDDATETIME, "
                    + "    TO_CHAR(CAS.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS') AS LASTUPDATEDDATETIME, "
                    + "    CAS.CREATEDUSER         AS CREATEDUSER, "
                    + "    CAS.CUSTOMERNAME        AS CUSTOMERNAME, "
                    + "    CAS.PHONENUMBER         AS TELEPHONE "
                    + "  FROM AVN_CASE CAS "
                    + "  INNER JOIN AVN_STATUS ST "
                    + "  ON CAS.STATUSID = ST.STATUSID "
                    + "  ) TBL "
                    + " WHERE "
                    + "    1 = 1 :where";
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

    private String getWhere(Interacthistory parameters) {
        String where = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (parameters.getFrom_date() != null) {
            where += " AND CREATEDDATETIME >= TO_CHAR('" + dateFormat.format(Common.getStartingTimeofDay(parameters.getFrom_date())) + "')";
        }
        if (parameters.getTo_date() != null) {
            where += " AND CREATEDDATETIME <= TO_CHAR('" + dateFormat.format(Common.getEndingTimeofDay(parameters.getTo_date())) + "')";
        }
        if (parameters.getFirstName() != null) {
            where += " AND UPPER(CUSTOMERNAME)  LIKE UPPER('%" + parameters.getFirstName().trim() + "%') ";
        }
        if (parameters.getTelephone() != null) {
            where += " AND TELEPHONE LIKE '%" + parameters.getTelephone().trim() + "%' ";
        }

        return where;
    }

}

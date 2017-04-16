/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.audittrace;

import com.avn.ccl.dao.audittrace.AuditTraceDAO;
import com.avn.ccl.model.audittrace.AuditTrace;
import com.avn.ccl.util.Common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



/**
 *
 * @author Lahiru
 */
public class AuditTraceDAOImpl implements AuditTraceDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AuditTrace> getAuditTrace() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<AuditTrace> auditList = new ArrayList<>();
        try {
            String sql = "SELECT AUDITTRACEID,AFECTEDID,TASK,DESCRIPTION,AFFECTEDPAGE,RESPONSIBLEUSER,CREATEDATETIME  FROM AVN_AUDITTRACE ORDER BY CREATEDATETIME ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            AuditTrace audit;
            while (resultSet.next()) {
                audit = new AuditTrace();
                audit.setAuditTraceId(resultSet.getInt("AUDITTRACEID"));
                audit.setAfectedId(resultSet.getString("AFECTEDID"));
                audit.setTask(resultSet.getString("TASK"));
                audit.setDescription(resultSet.getString("DESCRIPTION"));
                audit.setAfectedPage(resultSet.getString("AFFECTEDPAGE"));
                audit.setResponsibleUser(resultSet.getString("RESPONSIBLEUSER"));
                audit.setCreatedDateTime(resultSet.getString("CREATEDATETIME"));
                auditList.add(audit);
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
        return auditList;
    }

    @Override
    public void insertAuditTrace(String page, String task, String description, String affectedId, String user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {        
            String sql = "INSERT INTO AVN_AUDITTRACE (AFFECTEDPAGE,TASK,DESCRIPTION,AFECTEDID,RESPONSIBLEUSER,CREATEDATETIME) VALUES(?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, page);
            statement.setString(2, task);
            statement.setString(3, description);
            statement.setString(4, affectedId);
            statement.setString(5, user);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw sqle;
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

    public List<AuditTrace> getTableData(AuditTrace parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<AuditTrace> list = null;
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
                    + "             AUDITTRACEID                                                AS AUDITTRACEID,"
                    + "             NVL(AFECTEDID, '--')                                        AS AFECTEDID, "
                    + "             NVL(RESPONSIBLEUSER, '--')                                  AS RESPONSIBLEUSER, "
                    + "             NVL(TO_CHAR(CREATEDATETIME, 'YYYY-MM-DD HH24:MI:SS'), '--') AS CREATEDATETIME, "
                    + "             NVL(AFFECTEDPAGE, '--')                                     AS AFFECTEDPAGE, "
                    + "             NVL(TASK, '--')                                             AS TASK, "
                    + "             NVL(DESCRIPTION, '--')                                      AS DESCRIPTION "
                    + "        FROM "
                    + "             AVN_AUDITTRACE  "
                    + "        WHERE "
                    + "             1 = 1 :where "
                    + "        ORDER BY "
                    + "             CREATEDATETIME DESC "
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
            AuditTrace reportData;
            while (resultSet.next()) {
                reportData = new AuditTrace();
                reportData.setAuditTraceId(resultSet.getInt("AUDITTRACEID"));
                reportData.setAfectedId(resultSet.getString("AFECTEDID"));
                reportData.setResponsibleUser(resultSet.getString("RESPONSIBLEUSER"));
                reportData.setCreatedDateTime(resultSet.getString("CREATEDATETIME"));
                reportData.setAfectedPage(resultSet.getString("AFFECTEDPAGE"));
                reportData.setTask(resultSet.getString("TASK"));
                reportData.setDescription(resultSet.getString("DESCRIPTION"));
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

    public int getTableDataCount(AuditTrace parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT "
                    + "    COUNT(*) CNT "
                    + "FROM "
                    + "    AVN_AUDITTRACE  "
                    + " WHERE "
                    + "    1 = 1 :where "
                    + "ORDER BY "
                    + "    CREATEDATETIME DESC ";
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

    private String getWhere(AuditTrace parameters) {
        String where = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (parameters.getFrom_date() != null) {
            where += " AND CREATEDATETIME >= TO_DATE('" + dateFormat.format(Common.getStartingTimeofDay(parameters.getFrom_date())) + "', 'YYYY-MM-DD hh24:mi:ss')";
        }
        if (parameters.getTo_date() != null) {
            where += " AND CREATEDATETIME <= TO_DATE('" + dateFormat.format(Common.getEndingTimeofDay(parameters.getTo_date())) + "', 'YYYY-MM-DD hh24:mi:ss')";
        }
        return where;
    }

}

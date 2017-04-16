/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.chart;

import com.avn.ccl.util.Common;
import static com.avn.ccl.util.Common.getStringFormatDate;
import static com.avn.ccl.util.Common.getStringFormatDateandTime;
import com.avn.ccl.util.varlist.CommonVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @Author : Office Isuru
 * @Document : ChartDAOImpl
 * @Date : Aug 23, 2015 12:16:49 PM
 */
public class ChartDAOImpl {

    private DataSource dataSource;
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JSONArray getCaseCount(String username, String column) throws Exception {
        String sql = "SELECT "
                + "   TRUNC(CURRENT_TIMESTAMP, 'iw') + ROWNUM -1 AS DY "
                + "FROM "
                + "   ALL_OBJECTS "
                + "WHERE "
                + "   ROWNUM <= TO_DATE(TRUNC(CURRENT_TIMESTAMP, 'iw') + 7 - 1/86400,'dd-mon-yyyy') - TO_DATE(TRUNC(CURRENT_TIMESTAMP, 'iw'),'dd-mon-yyyy') + 1 ";

        JSONArray jArray = new JSONArray();
        List<Date> dateList = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dateList.add(resultSet.getDate("DY"));

            }
            for (Date date : dateList) {
                String sql2 = "SELECT "
                        + "   COUNT(*) AS CSCOUNT, "
                        + "   CS.CREATEDDATETIME "
                        + "FROM "
                        + "   AVN_CASE CS "
                        + "WHERE "
                        + "  CS.CREATEDDATETIME BETWEEN ? and ? and :COLUMN= ? "
                        + " GROUP BY "
                        + "   CS.CREATEDDATETIME";
                String sql_repalce = sql2.replace(":COLUMN", column);
                statement = connection.prepareStatement(sql_repalce);
                statement.setString(1, getStringFormatDateandTime(CommonVarList.DATE_FORMAT_dd_MMM_yy_hhmmssSSa, new Timestamp(Common.getStartingTimeofDay(date).getTime())));
                statement.setString(2, getStringFormatDateandTime(CommonVarList.DATE_FORMAT_dd_MMM_yy_hhmmssSSa, new Timestamp(Common.getEndingTimeofDay(date).getTime())));
                statement.setString(3, username);
                boolean isDataSet = false;
                resultSet = statement.executeQuery();
                int cscount = 0;
                JSONObject obj = new JSONObject();
                while (resultSet.next()) {
                    obj.put("m", date.toString());
                    cscount += resultSet.getInt("CSCOUNT");
                    isDataSet = true;
                }
                if (isDataSet) {
                    obj.put("y", cscount);
                    jArray.put(obj);
                }
//                obj.put("y", cscount);
//                jArray.put(obj);
                if (!isDataSet) {
                    obj.put("m", date.toString());
                    obj.put("y", 0);
                    jArray.put(obj);
                }

            }
        } catch (SQLException | JSONException exception) {
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
        return jArray;
    }

    public JSONArray getCallCount(String username) throws Exception {
        JSONArray jArray = new JSONArray();
        List<Date> dateList = new ArrayList<>();
        try {
            String sql = "SELECT "
                    + "   TRUNC(CURRENT_TIMESTAMP, 'iw') + ROWNUM -1 AS DY "
                    + "FROM "
                    + "   ALL_OBJECTS "
                    + "WHERE "
                    + "   ROWNUM <= TO_DATE(TRUNC(CURRENT_TIMESTAMP, 'iw') + 7 - 1/86400,'dd-mon-yyyy') - TO_DATE(TRUNC(CURRENT_TIMESTAMP, 'iw'),'dd-mon-yyyy') + 1 ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dateList.add(resultSet.getDate("DY"));
            }
            for (Date date : dateList) {
                String sql2 = "SELECT "
                        + "   COUNT(*) AS CLCOUNT, "
                        + "   CL.CREATEDDATETIME "
                        + "FROM "
                        + "   AVN_CALLLOG CL "
                        + "WHERE "
                        + "  CL.CREATEDDATETIME BETWEEN ? and ? and   CREATEDUSER= ? "
                        + " GROUP BY "
                        + "   CL.CREATEDDATETIME";

                statement = connection.prepareStatement(sql2);
                statement.setString(1, getStringFormatDateandTime(CommonVarList.DATE_FORMAT_dd_MMM_yy_hhmmssSSa, new Timestamp(Common.getStartingTimeofDay(date).getTime())));
                statement.setString(2, getStringFormatDateandTime(CommonVarList.DATE_FORMAT_dd_MMM_yy_hhmmssSSa, new Timestamp(Common.getEndingTimeofDay(date).getTime())));
                statement.setString(3, username);
                boolean isDataSet = false;
                resultSet = statement.executeQuery();
                JSONObject obj = new JSONObject();
                int clcount = 0;
                while (resultSet.next()) {
                    obj.put("m", date.toString());
                    clcount += resultSet.getInt("CLCOUNT");
                    isDataSet = true;
                }
                if (isDataSet) {
                    obj.put("z", clcount);
                    jArray.put(obj);
                }

                if (!isDataSet) {
                    obj.put("m", date.toString());
                    obj.put("z", 0);
                    jArray.put(obj);
                }
            }
        } catch (SQLException | JSONException exception) {
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
        return jArray;
    }

    public JSONArray getProduntCount(String username, String column) throws Exception {
        JSONArray objList = new JSONArray();
        try {
            String sql = "select count(P.DESCRIPTION) as Pcount, P.DESCRIPTION as PDESCRIPTION, to_char(C.CREATEDDATETIME, 'yyyy-mm')as ENTRIES from AVN_CASE C, AVN_PRODUCT P where C.PRODUCTID=P.PRODUCTID and to_char(C.CREATEDDATETIME, 'yyyy-mm') =? :COLUMN= ? and C.STATUSID='1' Group by P.DESCRIPTION, to_char(C.CREATEDDATETIME, 'yyyy-mm')";
            sql = sql.replace(":COLUMN", column);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM, new java.sql.Date(System.currentTimeMillis())));
            statement.setString(2, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONObject obj = new JSONObject();
                obj.put("value", resultSet.getInt("Pcount"));
                obj.put("label", resultSet.getString("PDESCRIPTION"));
                objList.put(obj);
            }
        } catch (SQLException | JSONException exception) {
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
        return objList;
    }

    public JSONArray getDepartmentCount(String username, String column) throws Exception {
        JSONArray objList = new JSONArray();
        try {
            String sql = "select count(D.DEPARTMENTID) as Ccount, D.DESCRIPTION as DDESCRIPTION, to_char(C.CREATEDDATETIME, 'yyyy-MM')as ENTRIES from AVN_CASE C, AVN_DEPARTMENT D where C.DEPARTMENTID=D.DEPARTMENTID and to_char(C.CREATEDDATETIME, 'yyyy-MM') =? :COLUMN=? and C.STATUSID='1'  Group by D.DESCRIPTION, to_char(C.CREATEDDATETIME, 'yyyy-MM')";
            sql = sql.replace(":COLUMN", column);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM, new java.sql.Date(System.currentTimeMillis())));
            statement.setString(2, username);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                JSONObject obj = new JSONObject();
                obj.put("Count", resultSet.getString("Ccount"));
                obj.put("Des", resultSet.getString("DDESCRIPTION"));
                obj.put("year", resultSet.getString("ENTRIES"));
                objList.put(obj);

            }
        } catch (SQLException | JSONException exception) {
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
        return objList;
    }

    public JSONArray getProduntCountOrganizationwise() throws Exception {
        JSONArray objList = new JSONArray();
        try {
            String sql = "select count(P.DESCRIPTION) as Pcount, P.DESCRIPTION as PDESCRIPTION, to_char(C.CREATEDDATETIME, 'yyyy-mm')as ENTRIES from AVN_CASE C, AVN_PRODUCT P where C.PRODUCTID=P.PRODUCTID and to_char(C.CREATEDDATETIME, 'yyyy-mm') =? and C.STATUSID='1' Group by P.DESCRIPTION, to_char(C.CREATEDDATETIME, 'yyyy-mm')";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM, new java.sql.Date(System.currentTimeMillis())));
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                JSONObject obj = new JSONObject();
                obj.put("value", resultSet.getInt("Pcount"));
                obj.put("label", resultSet.getString("PDESCRIPTION"));
                objList.put(obj);
            }
        } catch (SQLException | JSONException exception) {
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
        return objList;
    }

    public JSONArray getDepartmentCountbyOrganizationwise() throws Exception {
        JSONArray objList = new JSONArray();
        try {
            String sql = "select count(D.DEPARTMENTID) as Ccount, D.DESCRIPTION as DDESCRIPTION, to_char(C.CREATEDDATETIME, 'yyyy-MM')as ENTRIES from AVN_CASE C, AVN_DEPARTMENT D where C.DEPARTMENTID=D.DEPARTMENTID and to_char(C.CREATEDDATETIME, 'yyyy-MM') =? and C.STATUSID='1'  Group by D.DESCRIPTION, to_char(C.CREATEDDATETIME, 'yyyy-MM')";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM, new java.sql.Date(System.currentTimeMillis())));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONObject obj = new JSONObject();
                obj.put("Count", resultSet.getString("Ccount"));
                obj.put("Des", resultSet.getString("DDESCRIPTION"));
                obj.put("year", resultSet.getString("ENTRIES"));
                objList.put(obj);
            }
        } catch (SQLException | JSONException exception) {
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
        return objList;
    }

    public JSONArray getDepartmentList() throws Exception {
        String sql2 = "Select DESCRIPTION from AVN_DEPARTMENT";
        JSONArray objList = new JSONArray();
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql2);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONObject obj = new JSONObject();
                obj.put("Departments", resultSet.getString("DESCRIPTION"));
                obj.put("Year", getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM, new java.sql.Date(System.currentTimeMillis())));
                objList.put(obj);
            }
        } catch (SQLException | JSONException exception) {
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
        return objList;
    }

}

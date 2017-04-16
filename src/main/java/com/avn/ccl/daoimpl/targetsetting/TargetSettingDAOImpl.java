/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.targetsetting;

import com.avn.ccl.dao.targetsetting.TargetSettingDAO;
import com.avn.ccl.model.region.Region;
import com.avn.ccl.model.sectiontask.SectionTask;
import com.avn.ccl.model.target.TargetSetting;
import com.avn.ccl.model.wedget.Wedget;
import com.avn.ccl.util.DateTime;
import static com.avn.ccl.util.DateTime.getTimestampFromDateAndTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @Author : ISURU
 * @Document : TargetSettingDAOImpl
 * @Created on : May 9, 2016, 10:50:05 AM
 */
public class TargetSettingDAOImpl implements TargetSettingDAO{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public long insertTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long targetID = 0;
        try {
            String sql = "INSERT INTO AVN_TARGET "
                    + " (DESCRIPTION,PRODUCTID,REVENUE,NUMBEROFCONTACT,TAGRETGROUPID,TARGETPERIODID, CREATEDDATETIME, LASTUPDATEDDATETIME,CREATEDUSER,TARGETSTARTDATE,TARGETENDDATE,TARGETTYPE,ACTIVITYTPE) "
                    + " VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,?,?,?,?)";
            String[] generatedColumns = {"TARGETID"};
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setString(1, target.getTragetdes());
            statement.setString(2, target.getProductid());
            statement.setString(3, target.getRevenue());
            statement.setString(4, target.getNuberofcontact());
            statement.setString(5, target.getTargergroupid());
            statement.setString(6, target.getTargetperiodid());
            statement.setString(7, username);
            statement.setTimestamp(8, getTimestampFromDateAndTime(target.getTargetstartdate(), "00:00"));
            statement.setTimestamp(9, getTimestampFromDateAndTime(target.getTargetenddate(), "00:00"));
            statement.setString(10, target.getTargettypeid());
            statement.setString(11, target.getActivitytypeid());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                targetID = resultSet.getLong(1);
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
        return targetID;
    }

    public int getTableDataCount(TargetSetting parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_TARGET TR  INNER JOIN AVN_TARGETPERIOD TP ON TR.TARGETPERIODID=TP.PERIODID INNER JOIN AVN_PRODUCT P ON TR.PRODUCTID=P.PRODUCTID :where ";
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

    private String getWhere(TargetSetting parameters) {
        String where = "";
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("targetid")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where TR.TARGETID = '" + parameters.getInput().trim() + "'";
        }
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("tragetdes")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where UPPER(TR.DESCRIPTION) LIKE UPPER( '%" + parameters.getInput().trim() + "%')";
        }
        return where;
    }

    public List<TargetSetting> getTableData(TargetSetting parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<TargetSetting> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + " FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( SELECT TR.TARGETID, TR.DESCRIPTION, TR.PRODUCTID, P.DESCRIPTION AS PDES, TR.REVENUE, TR.TAGRETGROUPID,  TR.TARGETPERIODID, TP.DESCRIPTION AS TPDES, TO_CHAR (TR.TARGETSTARTDATE,'YYYY-MM-DD') AS TARGETSTARTDATE, TO_CHAR(TR.TARGETENDDATE,'YYYY-MM-DD') AS  TARGETENDDATE FROM AVN_TARGET TR  INNER JOIN AVN_TARGETPERIOD TP ON TR.TARGETPERIODID=TP.PERIODID INNER JOIN AVN_PRODUCT P ON TR.PRODUCTID=P.PRODUCTID  :where ORDER BY TR.TARGETID DESC) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            sql = sql.replace(":where", this.getWhere(parameters));
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, start + minCount);
            statement.setInt(2, start);
            resultSet = statement.executeQuery();

            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
//                targetData = new TargetSetting();
//                targetData.setTargetid(resultSet.getString("TARGETID"));
//                targetData.setTragetdes(resultSet.getString("DESCRIPTION"));
//                targetData.setProductid(resultSet.getString("PRODUCTID"));
//                targetData.setProductdes(resultSet.getString("PDES"));
//                targetData.setRevenue(resultSet.getString("REVENUE"));
//                targetData.setTargetperiodid(resultSet.getString("TARGETPERIODID"));
//                targetData.setTargetperioddes(resultSet.getString("TPDES"));
//                targetData.setTargetstartdate(resultSet.getString("TARGETSTARTDATE"));
//                targetData.setTargetenddate(resultSet.getString("TARGETENDDATE"));
//                list.add(targetData);
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

    public TargetSetting getTargetByTargetId(String targetId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData = null;
        try {
            String sql = "SELECT TR.TARGETID, TR.DESCRIPTION, TR.PRODUCTID, P.DESCRIPTION AS PDES, TR.REVENUE,TR.NUMBEROFCONTACT, TR.TAGRETGROUPID, TG.DESCRIPTION AS TRGDES, TR.TARGETPERIODID, TP.DESCRIPTION AS TPDES, to_char(TR.TARGETSTARTDATE,'YYYY-MM-DD') AS TARGETSTARTDATE  ,  to_char(TR.TARGETENDDATE ,'YYYY-MM-DD') AS TARGETENDDATE, TR.TARGETTYPE, TR.ACTIVITYTPE FROM AVN_TARGET TR "
                    + " INNER JOIN AVN_TARGETGROUP TG ON TR.TAGRETGROUPID=TG.GROUPID "
                    + " INNER JOIN AVN_TARGETPERIOD TP ON TR.TARGETPERIODID=TP.PERIODID "
                    + " INNER JOIN AVN_PRODUCT P ON TR.PRODUCTID=P.PRODUCTID WHERE TR.TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(targetId));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setTargetid(resultSet.getString("TARGETID"));
                targetData.setTragetdes(resultSet.getString("DESCRIPTION"));
                targetData.setProductid(resultSet.getString("PRODUCTID"));
                targetData.setProductdes(resultSet.getString("PDES"));
                targetData.setTargetrevenue(resultSet.getString("REVENUE"));
                targetData.setNuberofcontact(resultSet.getString("NUMBEROFCONTACT"));
                targetData.setTargergroupid(resultSet.getString("TAGRETGROUPID"));
                targetData.setTragetgroupdes(resultSet.getString("TRGDES"));
                targetData.setTargetperiodid(resultSet.getString("TARGETPERIODID"));
                targetData.setTargetperioddes(resultSet.getString("TPDES"));
                targetData.setTargetstartdate(resultSet.getString("TARGETSTARTDATE"));
                targetData.setTargetenddate(resultSet.getString("TARGETENDDATE"));
                targetData.setTargettypeid(resultSet.getString("TARGETTYPE"));
                targetData.setActivitytypeid(resultSet.getString("ACTIVITYTPE"));
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
        return targetData;
    }

    public void updateTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_TARGET SET "
                    + " DESCRIPTION=?, "
                    + " PRODUCTID=?, "
                    + " REVENUE=?, "
                    + " NUMBEROFCONTACT=?, "
                    + " TAGRETGROUPID=?, "
                    + " TARGETPERIODID=?, "
                    + " LASTUPDATEDDATETIME=CURRENT_TIMESTAMP, "
                    + " CREATEDUSER=?, "
                    + " TARGETSTARTDATE=?, "
                    + " TARGETENDDATE=? "
                    + " WHERE TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTragetdes());
            statement.setString(2, target.getProductid());
            statement.setString(3, target.getRevenue());
            statement.setString(4, target.getNuberofcontact());
            statement.setString(5, target.getTargergroupid());
            statement.setString(6, target.getTargetperiodid());
            statement.setString(7, username);
            statement.setTimestamp(8, getTimestampFromDateAndTime(target.getTargetstartdate(), "00:00"));
            statement.setTimestamp(9, getTimestampFromDateAndTime(target.getTargetenddate(), "00:00"));
            statement.setString(10, target.getTargetid());
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

    public List<TargetSetting> getTargetSearchDroupDown(String targetdes) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT TARGETID, DESCRIPTION FROM AVN_TARGET WHERE DESCRIPTION LIKE '%" + targetdes.trim() + "%'";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setTargetid(resultSet.getString("TARGETID"));
                targetData.setTragetdes(resultSet.getString("DESCRIPTION"));
                list.add(targetData);
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
        return list;
    }

    public void insertNotAssignRegions(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            JSONArray array = new JSONArray(target.getTargetnotassignregion());
            for (int i = 0; i < array.length(); i++) {
                String sql = "INSERT INTO AVN_REGIONALTARGET  "
                        + " (TARGETID,REGIONID,TARGET,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER ) "
                        + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                connection = dataSource.getConnection();
                statement = connection.prepareStatement(sql);
                String regionid = array.getString(i);
                statement.setString(1, target.getTargetid());
                statement.setString(2, regionid);
                statement.setString(3, "0.0");
                statement.setString(4, username);

                statement.executeUpdate();
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

    }

    public JSONArray insertRegionTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long RegionaltargetID = 0;
        JSONArray regionarray = new JSONArray();
        try {
            JSONArray array = new JSONArray(target.getRegionaltargetlist());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String sql = "INSERT INTO AVN_REGIONALTARGET  "
                        + " (REGIONALTARGETID ,TARGETID,REGIONID,TARGET,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER ) "
                        + " VALUES (?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                connection = dataSource.getConnection();
                String[] generatedColumns = {"REGIONALTARGETID"};
                statement = connection.prepareStatement(sql, generatedColumns);
                statement.setString(1, null);
                statement.setString(2, target.getTargetid());
                statement.setString(3, obj.getString("ID"));
                statement.setString(4, obj.getString("TARGET"));
                statement.setString(5, username);
                statement.executeUpdate();
                resultSet = statement.getGeneratedKeys();
                while (resultSet.next()) {
                    JSONArray Jarray = new JSONArray();
                    RegionaltargetID = resultSet.getLong(1);
                    Jarray.put(String.valueOf(RegionaltargetID));
                    Jarray.put(obj.getString("ID"));
                    regionarray.put(Jarray);
                }
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
        return regionarray;
    }

    public long insertBranchTarget(TargetSetting target, String username, JSONArray json) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long RegionaltargetID = 0;
        try {
            JSONArray array = new JSONArray(target.getBrachtargetlist());
            for (int i = 0; i < json.length(); i++) {
                JSONArray jsonarray = json.getJSONArray(i);
                String regionaltargetid = jsonarray.getString(0);
                String RID = jsonarray.getString(1);
                for (int j = 0; j < array.length(); j++) {
                   
                    JSONObject obj = array.getJSONObject(j);
                   String  Objrid = obj.getString("RID");
                    if (RID.contentEquals(Objrid)) {
                        String sql = "INSERT INTO AVN_BRANCHTARGET "
                                + " (REGIONID,REGIONALTARGETID,BRANCHID,TARGET,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER ) "
                                + " VALUES (?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                        connection = dataSource.getConnection();
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, RID);
                        statement.setString(2, regionaltargetid);
                        statement.setString(3, obj.getString("ID"));
                        statement.setString(4, obj.getString("TARGET"));
                        statement.setString(5, username);
                        statement.executeUpdate();
                    }
                    
                    
                    

                }

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
        return RegionaltargetID;
    }

    public String getTargetIByDescription(String targetdes) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String targetID = null;
        try {
            String sql = "SELECT TARGETID FROM AVN_TARGET WHERE DESCRIPTION LIKE '%" + targetdes.trim() + "%'";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetID = resultSet.getString("TARGETID");
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
        return targetID;
    }

    public String getOrganizationTargetByID(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String target = null;
        try {
            String sql = "SELECT REVENUE FROM AVN_TARGET WHERE TARGETID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                target = resultSet.getString("REVENUE");
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
        return target;
    }

    public JSONArray getRegionaletails(String targetid) throws Exception {
        JSONArray regionlTargetDetails = new JSONArray();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long targtID = 0;
        try {
            String sql = "SELECT REGIONALTARGETID,TARGETID,TARGET FROM AVN_REGIONALTARGET WHERE TARGET=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray regiontarget = new JSONArray();
                regiontarget.put(resultSet.getString("REGIONALTARGETID"));
                regiontarget.put(resultSet.getString("TARGETID"));
                regiontarget.put(resultSet.getString("TARGET"));
                regionlTargetDetails.put(regiontarget);
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
        return regionlTargetDetails;
    }

    public Map<String, String> getTargetDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT TARGETID, DESCRIPTION FROM AVN_TARGET";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("TARGETID"), resultSet.getString("DESCRIPTION"));
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

    public List<TargetSetting> getTargetList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<TargetSetting> list = null;
        try {
            String sql = "SELECT TARGETID, DESCRIPTION FROM AVN_TARGET";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                TargetSetting target = new TargetSetting();
                target.setTargetid(resultSet.getString("TARGETID"));
                target.setTragetdes(resultSet.getString("DESCRIPTION"));
                list.add(target);
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

    public JSONArray getNotAssignBranch(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray list = null;
        try {
            String sql = "SELECT BRANCHID,REGIONID FROM AVN_BRANCH WHERE REGIONID in(Select REGIONID FROM AVN_REGIONALTARGET WHERE TARGETID=?  )  AND BRANCHID NOT IN (Select BRANCHID FROM AVN_BRANCHTARGET )";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            list = new JSONArray();
            while (resultSet.next()) {
                JSONArray array = new JSONArray();
                array.put(resultSet.getString("REGIONID"));
                array.put(resultSet.getString("BRANCHID"));
                list.put(array);
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

    public void insertNotAssignBranchTarget(TargetSetting target, String username, JSONArray json, JSONArray array) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long RegionaltargetID = 0;
        try {
            for (int i = 0; i < json.length(); i++) {
                JSONArray jsonarray = json.getJSONArray(i);
                String RID = jsonarray.getString(1);

                for (int j = 0; j < array.length(); j++) {
                    JSONArray arraylist = array.getJSONArray(j);
                    String regionid = arraylist.getString(0);
                    if (RID.contentEquals(regionid)) {
                        String sql = "INSERT INTO AVN_BRANCHTARGET "
                                + " (REGIONID,REGIONALTARGETID,BRANCHID,TARGET,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER ) "
                                + " VALUES (?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                        connection = dataSource.getConnection();
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, RID);
                        statement.setString(2, jsonarray.getString(0));
                        statement.setString(3, arraylist.getString(1));
                        statement.setString(4, "0.0");
                        statement.setString(5, username);

                        statement.executeUpdate();
                    }

                }

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

    }

    public JSONArray getRegionalTargetIdListByTargetId(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray array = null;
        try {
            String sql = "SELECT REGIONALTARGETID,REGIONID FROM AVN_REGIONALTARGET WHERE TARGETID= ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            array = new JSONArray();
            while (resultSet.next()) {
                JSONArray ridlist = new JSONArray();
                ridlist.put(resultSet.getString("REGIONALTARGETID"));
                ridlist.put(resultSet.getString("REGIONID"));
                array.put(ridlist);
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
        return array;
    }

    public JSONArray getRegionOragnizationTarget(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray list = new JSONArray();
        try {
            String sql = "SELECT R.REGIONNAME,RT.TARGET, RT.REGIONID FROM AVN_REGIONALTARGET RT INNER JOIN AVN_REGION R ON RT.REGIONID=R.REGIONID WHERE RT.TARGET>0 AND  RT.TARGETID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            list = new JSONArray();
            while (resultSet.next()) {
                JSONArray array = new JSONArray();
                array.put(resultSet.getString("REGIONNAME"));
                array.put(resultSet.getString("TARGET"));
                array.put(resultSet.getString("REGIONID"));
                list.put(array);
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
        return list;
    }

    public JSONArray getBrachTargetList(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray list = new JSONArray();
        try {
            String sql = "SELECT BT.REGIONID, B.ALIASNAME AS NAME, BT.BRANCHID, BT.TARGET FROM AVN_BRANCHTARGET BT INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID WHERE RT.TARGETID= ? AND BT.TARGET>0";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            list = new JSONArray();
            while (resultSet.next()) {
                JSONArray array = new JSONArray();
                array.put(resultSet.getString("REGIONID"));
                array.put(resultSet.getString("NAME"));
                array.put(resultSet.getString("BRANCHID"));
                array.put(resultSet.getString("TARGET"));
                list.put(array);
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
        return list;
    }

    public Map<String, String> getNotAssignRegionByTragetId(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT REGIONID, REGIONNAME FROM AVN_REGION  WHERE REGIONID  IN (SELECT REGIONID  FROM AVN_REGIONALTARGET  WHERE TARGETID= ? AND TARGET=0)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("REGIONID"), resultSet.getString("REGIONNAME"));
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
        return list;
    }

    public Map<String, String> getAssignRegionByTragetId(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT R.REGIONID, R.REGIONNAME FROM AVN_REGIONALTARGET RT INNER JOIN AVN_REGION R ON RT.REGIONID=R.REGIONID WHERE RT.TARGETID= ? AND RT.TARGET>0 ORDER BY REGIONID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("REGIONID"), resultSet.getString("REGIONNAME"));
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
        return list;
    }

    public Map<String, String> getNotAssignBranchByTargetId(String targetid, String regionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT BT.BRANCHID, B.ALIASNAME FROM AVN_BRANCHTARGET BT INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID WHERE RT.TARGETID= ? AND RT.REGIONID=? AND BT.TARGET=0";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            statement.setString(2, regionid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("BRANCHID"), resultSet.getString("ALIASNAME"));
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
        return list;
    }

    public Map<String, String> getAssignBranchByTargetId(String targetid, String regionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT BT.BRANCHID, B.ALIASNAME FROM AVN_BRANCHTARGET BT INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID WHERE RT.TARGETID= ? AND RT.REGIONID=? AND BT.TARGET>0";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            statement.setString(2, regionid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("BRANCHID"), resultSet.getString("ALIASNAME"));
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
        return list;
    }

    public int getMinRegionId(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int regionid = 0;
        try {
            String sql = "SELECT min(REGIONID) as REGIONID  FROM AVN_REGIONALTARGET WHERE TARGETID= ? AND TARGET>0";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                regionid = resultSet.getInt("REGIONID");
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
        return regionid;
    }

    public void updateTarget(TargetSetting target, JSONArray arrayregiontargetid, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
       JSONArray regiontargetidlist = new JSONArray();
        JSONArray notassignbranch = new JSONArray();
        try {

            String sql = "UPDATE AVN_TARGET SET "
                    + " DESCRIPTION=?, "
                    + " PRODUCTID=?, "
                    + " REVENUE=?, "
                    + " NUMBEROFCONTACT=?, "
                    + " TAGRETGROUPID=?, "
                    + " TARGETPERIODID=?, "
                    + " LASTUPDATEDDATETIME=CURRENT_TIMESTAMP, "
                    + " CREATEDUSER=?, "
                    + " TARGETSTARTDATE=?, "
                    + " TARGETENDDATE=? "
                    + " WHERE TARGETID=?";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTragetdes());
            statement.setString(2, target.getProductid());
            statement.setString(3, target.getRevenue());
            statement.setString(4, target.getNuberofcontact());
            statement.setString(5, target.getTargergroupid());
            statement.setString(6, target.getTargetperiodid());
            statement.setString(7, username);
            statement.setTimestamp(8, getTimestampFromDateAndTime(target.getTargetstartdate(), "00:00"));
            statement.setTimestamp(9, getTimestampFromDateAndTime(target.getTargetenddate(), "00:00"));
            statement.setString(10, target.getTargetid());
//            statement.executeUpdate();

            for (int i = 0; i < arrayregiontargetid.length(); i++) {
                JSONArray jarray = arrayregiontargetid.getJSONArray(i);
                sql = "DELETE FROM AVN_BRANCHTARGET WHERE REGIONALTARGETID=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, jarray.getString(0));
                statement.executeUpdate();
            }

            sql = "DELETE FROM AVN_REGIONALTARGET WHERE TARGETID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

//insertNotAssignRegions
            JSONArray notassignreigon = new JSONArray(target.getTargetnotassignregion());
            for (int i = 0; i < notassignreigon.length(); i++) {
                sql = "INSERT INTO AVN_REGIONALTARGET  "
                        + " (TARGETID,REGIONID,TARGET,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER ) "
                        + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                statement = connection.prepareStatement(sql);
                String regionid = notassignreigon.getString(i);
                statement.setString(1, target.getTargetid());
                statement.setString(2, regionid);
                statement.setString(3, "0.0");
                statement.setString(4, username);

                statement.executeUpdate();
            }

            //insertRegionTarget
            JSONArray regionarray = new JSONArray();
            JSONArray assignregion = new JSONArray(target.getRegionaltargetlist());
            for (int i = 0; i < assignregion.length(); i++) {
                JSONObject obj = assignregion.getJSONObject(i);
                sql = "INSERT INTO AVN_REGIONALTARGET  "
                        + " (TARGETID,REGIONID,TARGET,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER ) "
                        + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, target.getTargetid());
                statement.setString(2, obj.getString("ID"));
                statement.setString(3, obj.getString("TARGET"));
                statement.setString(4, username);
                statement.executeUpdate();
            }
            
             //          
//            regiontargetid get by targetid

             sql = "SELECT REGIONALTARGETID,REGIONID FROM AVN_REGIONALTARGET WHERE TARGETID= ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            resultSet = statement.executeQuery();
            regiontargetidlist = new JSONArray();
            while (resultSet.next()) {
                JSONArray regiontragetid = new JSONArray();
                regiontragetid.put(resultSet.getString("REGIONALTARGETID"));
                regiontragetid.put(resultSet.getString("REGIONID"));
                regiontargetidlist.put(regiontragetid);
            }
            
//            not assign branch
             sql = "SELECT BRANCHID,REGIONID FROM AVN_BRANCH WHERE REGIONID in(Select REGIONID FROM AVN_REGIONALTARGET WHERE TARGETID=?  )  AND BRANCHID NOT IN (Select BRANCHID FROM AVN_BRANCHTARGET )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            resultSet = statement.executeQuery();
            notassignbranch = new JSONArray();
            while (resultSet.next()) {
                JSONArray array = new JSONArray();
                array.put(resultSet.getString("REGIONID"));
                array.put(resultSet.getString("BRANCHID"));
                notassignbranch.put(array);
            }
        
//
//            //insertBranchTarget
            JSONArray assignbranch = new JSONArray(target.getBrachtargetlist());
            for (int i = 0; i < regiontargetidlist.length(); i++) {
                JSONArray jsonarray = regiontargetidlist.getJSONArray(i);
                String regionaltargetid = jsonarray.getString(0);
                String RID = jsonarray.getString(1);
                for (int j = 0; j < assignbranch.length(); j++) {
                    JSONObject obj = assignbranch.getJSONObject(j);
                    if (RID.contentEquals(obj.getString("RID"))) {
                        sql = "INSERT INTO AVN_BRANCHTARGET "
                                + " (REGIONID,REGIONALTARGETID,BRANCHID,TARGET,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER ) "
                                + " VALUES (?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, RID);
                        statement.setString(2, regionaltargetid);
                        statement.setString(3, obj.getString("ID"));
                        statement.setString(4, obj.getString("TARGET"));
                        statement.setString(5, username);
                        statement.executeUpdate();
                    }

                }

            }
 
//  //insertNotAssignBranchTarget          
            
            for (int i = 0; i < regiontargetidlist.length(); i++) {
                JSONArray jsonarray = regiontargetidlist.getJSONArray(i);
                String RID = jsonarray.getString(1);

                for (int j = 0; j < notassignbranch.length(); j++) {
                    JSONArray arraylist = notassignbranch.getJSONArray(j);
                    String regionid = arraylist.getString(0);
                    if (RID.contentEquals(regionid)) {
                         sql = "INSERT INTO AVN_BRANCHTARGET "
                                + " (REGIONID,REGIONALTARGETID,BRANCHID,TARGET,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER ) "
                                + " VALUES (?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                 
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, RID);
                        statement.setString(2, jsonarray.getString(0));
                        statement.setString(3, arraylist.getString(1));
                        statement.setString(4, "0.0");
                        statement.setString(5, username);

                        statement.executeUpdate();
                    }

                }

            }
           connection.commit();
        } catch (SQLException | NumberFormatException e) {
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

}

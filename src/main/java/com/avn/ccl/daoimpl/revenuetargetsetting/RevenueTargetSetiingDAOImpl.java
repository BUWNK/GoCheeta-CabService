/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.revenuetargetsetting;

import com.avn.ccl.model.branch.Branch;
import com.avn.ccl.model.region.Region;
import com.avn.ccl.model.target.TargetSetting;
import static com.avn.ccl.util.DateTime.getTimestampFromDateAndTime;
import com.avn.ccl.util.varlist.MasterDataVarList;
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
 * @Document : RevenueTargetSetiingDAOImpl
 * @Created on : Jul 2, 2016, 7:34:40 AM
 */
public class RevenueTargetSetiingDAOImpl {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JSONArray getActitityTypes() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray activitytype = new JSONArray();
        try {

            String sql = "SELECT ACTIVITYTYPEID, ACTIVITYTYPEDESCRIPTION FROM AVN_ACTIVITYTYPES ORDER BY ACTIVITYTYPEID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray Jarray = new JSONArray();
                Jarray.put(resultSet.getString("ACTIVITYTYPEID"));
                Jarray.put(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                activitytype.put(Jarray);

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
        return activitytype;
    }

    public long insertTabone(TargetSetting target, String username) throws Exception {
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
            connection.setAutoCommit(false);
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
            JSONArray activitylist = new JSONArray(target.getActivitytargettbl());

            for (int i = 0; i < activitylist.length(); i++) {
                JSONObject activityobj = activitylist.getJSONObject(i);
                String activityid = activityobj.getString("ID");
                String Count = activityobj.getString("COUNT");
                if (Count != null && !Count.isEmpty()) {
                    Count = activityobj.getString("COUNT");
                }
                sql = "INSERT INTO AVN_ACTIVITYTARGET (TARGETID,ACTIVITYTYPEID,COUNT,CREATEDDATETIME,LASTUPDATEDTIME,CREATEDUSER) "
                        + " VALUES(?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, String.valueOf(targetID));
                statement.setString(2, activityid);
                statement.setString(3, Count);
                statement.setString(4, username);
                statement.executeQuery();
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
        return targetID;
    }

    public void updateTabone(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_TARGET "
                    + "SET DESCRIPTION       = ?, "
                    + "  PRODUCTID           = ?, "
                    + "  REVENUE             = ?, "
                    + "  TARGETPERIODID      = ?, "
                    + "  TARGETSTARTDATE     = ?, "
                    + "  TARGETENDDATE       = ?, "
                    + "  LASTUPDATEDDATETIME = CURRENT_TIMESTAMP "
                    + "WHERE TARGETID        = ?";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTragetdes());
            statement.setString(2, target.getProductid());
            statement.setString(3, target.getRevenue());
            statement.setString(4, target.getTargetperiodid());
            statement.setTimestamp(5, getTimestampFromDateAndTime(target.getTargetstartdate(), "00:00"));
            statement.setTimestamp(6, getTimestampFromDateAndTime(target.getTargetenddate(), "00:00"));
            statement.setLong(7, Long.parseLong(target.getTargetid()));
            statement.executeUpdate();

            JSONArray activitylist = new JSONArray(target.getActivitytargettbl());
            for (int i = 0; i < activitylist.length(); i++) {
                JSONObject activityobj = activitylist.getJSONObject(i);
                String activityid = activityobj.getString("ID");
                String Count = activityobj.getString("COUNT");
                if (Count != null && !Count.isEmpty()) {
                    Count = activityobj.getString("COUNT");
                }
                sql = "UPDATE AVN_ACTIVITYTARGET "
                        + "SET COUNT          = ?, "
                        + "  LASTUPDATEDTIME  = CURRENT_TIMESTAMP "
                        + "WHERE TARGETID     = ? "
                        + "AND ACTIVITYTYPEID = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, Count);
                statement.setString(2, target.getTargetid());
                statement.setString(3, activityid);
                statement.executeQuery();
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

    public Map<String, String> getTargetDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT T.TARGETID, T.TARGETID || ' - ' || T.DESCRIPTION || ' - ' || P.DESCRIPTION AS DESCRIPTION  FROM AVN_TARGET T INNER JOIN AVN_PRODUCT P ON T.PRODUCTID=P.PRODUCTID  ORDER BY T.DESCRIPTION ASC";
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

    private String getWhere(TargetSetting parameters) {
        String where = "";
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("targetid")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where TR.TARGETID LIKE '%" + parameters.getInput().trim() + "%'";
        }
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("tragetdes")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where TR.DESCRIPTION LIKE '%" + parameters.getInput().trim() + "%'";
        }
        where += "WHERE RT.TARGETID='" + parameters.getTargetid() + "'";
        return where;
    }

    private String getWhereForSearch(TargetSetting parameters) {
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
                    + "        ( SELECT RT.REGIONID, B.ALIASNAME, RT.TARGET  FROM  AVN_REGIONALTARGET RT INNER JOIN AVN_BRANCH B ON RT.REGIONID=B.BRANCHID :where ORDER BY RT.REGIONID ASC) TB  "
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
                targetData = new TargetSetting();
                targetData.setBranchid(resultSet.getString("REGIONID"));
                targetData.setBranchdes(resultSet.getString("ALIASNAME"));
                targetData.setRevenue(resultSet.getString("TARGET"));
                list.add(targetData);
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

    public int getTableDataCount(TargetSetting parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM  AVN_REGIONALTARGET RT INNER JOIN AVN_BRANCH B ON RT.REGIONID=B.BRANCHID :where ";
//            String sql = "SELECT COUNT(*) CNT FROM  AVN_REGIONALTARGET RT INNER JOIN AVN_BRANCH RE ON RT.BRANCHID=RE.BRANCHID :where ";
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

    public void insertRegionalTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            JSONArray array = new JSONArray(target.getTargetnotassignregion());
            for (int i = 0; i < array.length(); i++) {
                String sql = "INSERT INTO AVN_REGIONALTARGET  "
                        + " (TARGETID,REGIONID,TARGET,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER ) "
                        + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
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

    public int getRegionalTargetCountByTargetid(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM  AVN_REGIONALTARGET WHERE TARGETID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
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

    public JSONArray getRegionalArray() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray regionarray = new JSONArray();
        try {
            String sql = "SELECT BRANCHID FROM AVN_BRANCH WHERE TERRITORYID=?";
            //  String sql = "SELECT BRANCHID FROM AVN_BRANCH WHERE BRANCHID IS NOT NULL";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, MasterDataVarList.AFFINITI_CODE_TERRITORY_REGIONS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                regionarray.put(resultSet.getString("BRANCHID"));
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
        return regionarray;
    }

    public int getRegionalTargetSUMtByTargetid(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int sum = 0;
        try {
            String sql = "SELECT SUM(TARGET)AS SUM FROM  AVN_REGIONALTARGET WHERE TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("SUM");
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
        return sum;
    }

    public int getOrganizationTargetAmount(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int amount = 0;
        try {
            String sql = "SELECT REVENUE FROM  AVN_TARGET WHERE TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("REVENUE");
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
        return amount;
    }

    public List<TargetSetting> getActivityTargetDropDown(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT AC.ACTIVITYTARGETID, AT.ACTIVITYTYPEDESCRIPTION FROM AVN_ACTIVITYTARGET AC INNER JOIN  AVN_ACTIVITYTYPES AT ON AC.ACTIVITYTYPEID=AT.ACTIVITYTYPEID WHERE AC.TARGETID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setTargetactivityid(resultSet.getString("ACTIVITYTARGETID"));
                targetData.setTargetactivitydes(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
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

    public List<TargetSetting> getRegionActivityDropDown(String targetid, String regionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT RAT.ID, ATY.ACTIVITYTYPEDESCRIPTION FROM AVN_REGIONALACTIVITYTARGET RAT LEFT OUTER JOIN AVN_REGIONALTARGET RT  ON RAT.BRANCHID=RT.BRANCHID LEFT OUTER JOIN AVN_ACTIVITYTARGET AT ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID INNER JOIN AVN_ACTIVITYTYPES ATY ON AT.ACTIVITYTYPEID=ATY.ACTIVITYTYPEID WHERE RAT.TARGETID=? AND RT.REGIONALTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            statement.setString(2, regionid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setTargetactivityid(resultSet.getString("ID"));
                targetData.setTargetactivitydes(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
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

    public int getActivityTargetCount(String activitytargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        int count = 0;
        try {
            String sql = "SELECT COUNT FROM AVN_ACTIVITYTARGET WHERE ACTIVITYTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, activitytargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("COUNT");
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
        return count;
    }

    public List<Region> getRegionDropDown(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Region targetData;
        List<Region> list = new ArrayList<>();
        try {
            String sql = "SELECT R.BRANCHID,R.ALIASNAME FROM AVN_REGIONALTARGET RT INNER JOIN AVN_BRANCH R ON RT.REGIONID=R.BRANCHID WHERE TARGETID=?  AND TARGET>0 ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new Region();
                targetData.setRegionid(resultSet.getString("BRANCHID"));
                targetData.setRegiondes(resultSet.getString("ALIASNAME"));
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

    public int getActivityCount(String activitytargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int COUNT = 0;
        try {
            String sql = "SELECT COUNT FROM AVN_ACTIVITYTARGET WHERE ACTIVITYTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, activitytargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                COUNT = resultSet.getInt("COUNT");
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
        return COUNT;
    }

    public JSONObject updateRegionalTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONObject json = new JSONObject();
        JSONArray activitytype = new JSONArray();
        boolean status = false;
        int COUNT = 1;
        try {
            JSONArray array = new JSONArray(target.getDatatabledata());
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String sql = "UPDATE AVN_REGIONALTARGET SET TARGET=? WHERE TARGETID=? AND REGIONID=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("COUNT"));
                statement.setString(2, target.getTargetid());
                statement.setString(3, obj.getString("ID"));
                statement.executeUpdate();

                if (obj.getInt("COUNT") > 0) {
                    sql = "SELECT COUNT(*)  AS COUNT FROM AVN_REGIONALACTIVITYTARGET WHERE TARGETID=? AND  REGIONID=?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, target.getTargetid());
                    statement.setString(2, obj.getString("ID"));
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        COUNT = resultSet.getInt("COUNT");
                    }

                    if (COUNT == 0) {
                        sql = "SELECT ACTIVITYTARGETID FROM  AVN_ACTIVITYTARGET WHERE TARGETID=?";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, target.getTargetid());
                        resultSet = statement.executeQuery();
                        while (resultSet.next()) {
                            activitytype.put(resultSet.getString("ACTIVITYTARGETID"));
                        }
                        for (int j = 0; j < activitytype.length(); j++) {
                            sql = "INSERT INTO AVN_REGIONALACTIVITYTARGET (TARGETID,REGIONID,RACTIVITYID,ACTIVITYCOUNY,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) "
                                    + " VALUES (?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                            statement = connection.prepareStatement(sql);
                            statement.setString(1, target.getTargetid());
                            statement.setString(2, obj.getString("ID"));
                            statement.setString(3, activitytype.getString(j));
                            statement.setString(4, "0");
                            statement.setString(5, username);
                            statement.executeUpdate();
                        }

                    }
                }
            }
            int amount = 0;
            String sql = "SELECT REVENUE FROM  AVN_TARGET WHERE TARGETID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("REVENUE");
            }

            sql = "SELECT SUM(TARGET)AS SUM FROM  AVN_REGIONALTARGET WHERE TARGETID=?";
            int sum = 0;
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("SUM");
            }
            if (sum <= amount || sum == 0) {
                connection.commit();
                status = true;
                json.put("total", sum);
                json.put("remaning", amount - sum);
                json.put("status", true);
            } else {
                connection.rollback();
            }

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
        return json;

    }

    public void insertRegionalActivity(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
//            JSONArray array = new JSONArray(target.getRegionactivity());
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject obj = array.getJSONObject(i);
//                String activityid = obj.getString("Activity Id");
//                String count = obj.getString("Count");
//                String regionid = obj.getString("Region Id");
            String sql = "INSERT INTO AVN_REGIONALACTIVITYTARGET (TARGETID,BRANCHID,RACTIVITYID, ACTIVITYCOUNY,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) "
                    + " VALUES (?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.setString(2, target.getRegionalid());
            statement.setString(3, target.getTargetactivityid());
            statement.setString(4, target.getCount());
            statement.setString(5, username);

            statement.executeUpdate();
//            }

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

    public List<TargetSetting> getRegionDropDownForTabThree(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT RT.REGIONALTARGETID, R.ALIASNAME, RT.TARGET FROM AVN_REGIONALTARGET RT INNER JOIN AVN_BRANCH R ON RT.REGIONID=R.BRANCHID WHERE RT.TARGETID=? AND RT.TARGET > '0'";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setRegionaltargetid(resultSet.getString("REGIONALTARGETID"));
                targetData.setRegiondes(resultSet.getString("ALIASNAME"));
                targetData.setRevenue(resultSet.getString("TARGET"));
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

    public int getBranchTargetCountByRegionalTargetid(String regionaltargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM  AVN_BRANCHTARGET WHERE REGIONALTARGETID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, regionaltargetid);
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

    public int getSearchBranchTargetCountByRegionalTargetid(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_BRANCHTARGET BT INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID WHERE RT.TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
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

    public JSONArray getBranchArray(String regionaltargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray brancharray = new JSONArray();
        try {
            String sql = "SELECT BRANCHID FROM AVN_BRANCH WHERE PARENTTERRITORYMAPID=(SELECT REGIONID FROM AVN_REGIONALTARGET WHERE REGIONALTARGETID=? ) AND ISHAVEPARENT=1";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, regionaltargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                brancharray.put(resultSet.getString("BRANCHID"));
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
        return brancharray;
    }

    public void insertBrnachTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            JSONArray array = new JSONArray(target.getBrachtargetlist());
            for (int i = 0; i < array.length(); i++) {
                String sql = "INSERT INTO AVN_BRANCHTARGET (REGIONALTARGETID,BRANCHID,TARGET,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER)  "
                        + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                statement = connection.prepareStatement(sql);
                String branchid = array.getString(i);
                statement.setString(1, target.getRegionaltargetid());
                statement.setString(2, branchid);
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

    public List<TargetSetting> getBrnachTargetTableData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        ( SELECT B.BRANCHID,B.ALIASNAME,BT.TARGET  FROM AVN_BRANCHTARGET BT INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID WHERE RT.REGIONALTARGETID=? ORDER BY B.BRANCHID ASC) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getRegionaltargetid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();

            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchid(resultSet.getString("BRANCHID"));
                targetData.setBranchdes(resultSet.getString("ALIASNAME"));
                targetData.setRevenue(resultSet.getString("TARGET"));
                list.add(targetData);
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

    public List<TargetSetting> getSearchBrnachTargetTableData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        ( SELECT B.BRANCHID,B.ALIASNAME,BT.TARGET  FROM AVN_BRANCHTARGET BT INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID WHERE RT.TARGETID=? ORDER BY B.BRANCHID ASC) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();

            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchid(resultSet.getString("BRANCHID"));
                targetData.setBranchdes(resultSet.getString("ALIASNAME"));
                targetData.setRevenue(resultSet.getString("TARGET"));
                list.add(targetData);
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

    public int getBranchTargetSUMtByTargetid(String regionaltargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int sum = 0;
        try {
            String sql = "SELECT SUM(TARGET) AS SUM FROM AVN_BRANCHTARGET WHERE  REGIONALTARGETID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, regionaltargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("SUM");
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
        return sum;
    }

    public JSONObject updateBrnachTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        JSONArray regionalactivityidlist = new JSONArray();
        int COUNT = 1;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            JSONArray array = new JSONArray(target.getDatatabledata());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String sql = "UPDATE AVN_BRANCHTARGET SET TARGET=? WHERE REGIONALTARGETID=? AND BRANCHID=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("COUNT"));
                statement.setString(2, target.getRegionaltargetid());
                statement.setString(3, obj.getString("ID"));
                statement.executeUpdate();

                if (obj.getInt("COUNT") > 0) {
                    sql = "SELECT COUNT(*) AS COUNT "
                            + "FROM AVN_BRANCHACTIVITYTARGET BAT "
                            + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                            + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                            + "INNER JOIN AVN_REGIONALTARGET RT "
                            + "ON RAT.TARGETID          =RT.TARGETID "
                            + "AND RAT.REGIONID         =RT.REGIONID "
                            + "WHERE RT.REGIONALTARGETID=? AND BAT.BRANCHID=?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, target.getRegionaltargetid());
                    statement.setString(2, obj.getString("ID"));
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        COUNT = resultSet.getInt("COUNT");
                    }

                    if (COUNT == 0) {
                        sql = "SELECT RAT.ID "
                                + "FROM AVN_REGIONALACTIVITYTARGET RAT "
                                + "INNER JOIN AVN_REGIONALTARGET RT "
                                + "ON RAT.TARGETID          =RT.TARGETID "
                                + "AND RAT.REGIONID         =RT.REGIONID "
                                + "WHERE RT.REGIONALTARGETID=?";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, target.getRegionaltargetid());
                        resultSet = statement.executeQuery();
                        while (resultSet.next()) {
                            regionalactivityidlist.put(resultSet.getString("ID"));
                        }
                        for (int j = 0; j < regionalactivityidlist.length(); j++) {
                            sql = "INSERT INTO AVN_BRANCHACTIVITYTARGET (REGIONACTIVITYTARGETID,BRANCHID,BCOUNT,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) "
                                    + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                            statement = connection.prepareStatement(sql);
                            statement.setString(1, regionalactivityidlist.getString(j));
                            statement.setString(2, obj.getString("ID"));
                            statement.setString(3, "0");
                            statement.setString(4, username);
                            statement.executeUpdate();
                        }

                    }

                }

            }
            int sum = 0;
            String sql = "SELECT SUM(TARGET) AS SUM FROM AVN_BRANCHTARGET WHERE  REGIONALTARGETID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getRegionaltargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("SUM");
            }
            int amount = 0;
            sql = "SELECT TARGET FROM AVN_REGIONALTARGET WHERE REGIONALTARGETID=? AND TARGETID=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getRegionaltargetid());
            statement.setString(2, target.getTargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("TARGET");
            }

            if (sum <= amount || sum == 0) {
                connection.commit();
                status = true;
                jason.put("status", status);
                jason.put("total", sum);
                jason.put("remaning", amount - sum);
            } else {
                connection.rollback();
            }

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
        return jason;
    }

    public List<TargetSetting> getRegionActivityTargetList(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT RAT.ID AS RID, R.ALIASNAME || ' - ' || ACT.ACTIVITYTYPEDESCRIPTION AS DES FROM  AVN_REGIONALACTIVITYTARGET RAT INNER JOIN AVN_ACTIVITYTARGET AT ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID INNER JOIN AVN_ACTIVITYTYPES ACT ON AT.ACTIVITYTYPEID=ACT.ACTIVITYTYPEID INNER JOIN AVN_BRANCH R ON RAT.REGIONID=R.BRANCHID WHERE RAT.TARGETID=?  ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setRegionalactivitytargetid(resultSet.getString("RID"));
                targetData.setRegionalactivitytargetdes(resultSet.getString("DES"));
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

    public int getRegionActivityCount(String activitytargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int COUNT = 0;
        try {
            String sql = "SELECT ACTIVITYCOUNY AS COUNT  FROM AVN_REGIONALACTIVITYTARGET WHERE ID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, activitytargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                COUNT = resultSet.getInt("COUNT");
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
        return COUNT;
    }

    public void insertBranchActivity(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
//            JSONArray array = new JSONArray(target.getBrachtargetlist());
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject obj = array.getJSONObject(i);
//                String activityid = obj.getString("Id");
//                String count = obj.getString("Count");
//                String regionid = obj.getString("Region Id");
            String sql = "INSERT INTO AVN_BRANCHACTIVITYTARGET (REGIONACTIVITYTARGETID,BRANCHID,BCOUNT,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER)"
                    + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getRegionalactivitytargetid());
            statement.setString(2, target.getBranchid());
            statement.setString(3, target.getCount());
            statement.setString(4, username);

            statement.executeUpdate();
//            }

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

    public List<Branch> getBranchList(String ratid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Branch targetData;
        List<Branch> list = new ArrayList<>();
        try {
            String sql = "SELECT B.BRANCHID, B.ALIASNAME FROM AVN_REGIONALTARGET RT INNER JOIN AVN_BRANCH B ON RT.REGIONID=B.BRANCHID WHERE RT.REGIONALTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, ratid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new Branch();
                targetData.setBranchId(resultSet.getInt("BRANCHID"));
                targetData.setBranchDesc(resultSet.getString("ALIASNAME"));
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

    public List<TargetSetting> getRegionBranchList(String regionaltargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BT.BRANCHTARGETID, B.ALIASNAME AS DES FROM AVN_BRANCHTARGET BT INNER JOIN AVN_BRANCH B ON BT.BRANCHID= B.BRANCHID INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH R ON RT.REGIONID=R.BRANCHID WHERE BT.REGIONALTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, regionaltargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBrnachtargetid(resultSet.getString("BRANCHTARGETID"));
                targetData.setBrnachtargetdes(resultSet.getString("DES"));
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

    public List<TargetSetting> getRegionBranchListByRegionTragetid(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BT.BRANCHTARGETID, "
                    + "  BT.TARGET, B.BRANCHDESCRIPTION "
                    + "FROM AVN_BRANCHTARGET BT "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BT.BRANCHID= B.BRANCHID "
                    + "INNER JOIN AVN_REGIONALTARGET RT "
                    + "ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RT.REGIONID=R.BRANCHID "
                    + "WHERE BT.REGIONALTARGETID IN "
                    + "  (SELECT RT.REGIONALTARGETID "
                    + "  FROM AVN_REGIONALTARGET RT "
                    + "  WHERE RT.TARGETID =? "
                    + "  ) "
                    + "ORDER BY BRANCHTARGETID ASC ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBrnachtargetid(resultSet.getString("BRANCHTARGETID"));
                targetData.setBranchdes(resultSet.getString("BRANCHDESCRIPTION"));
                targetData.setRevenue(resultSet.getString("TARGET"));
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

    public List<TargetSetting> getGroupList(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BG.BRANCHGROUPID, BG.DESCRIPTION FROM AVN_BRANCHGROUP BG WHERE BG.PRODUCTID=(SELECT T.PRODUCTID FROM AVN_TARGET T WHERE T.TARGETID=?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchgroupid(resultSet.getString("BRANCHGROUPID"));
                targetData.setBranchgroupdes(resultSet.getString("DESCRIPTION"));
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

    public int getBranchTargetAmout(String branchtargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int sum = 0;
        try {
            String sql = "SELECT TARGET FROM AVN_BRANCHTARGET WHERE BRANCHTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, branchtargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("TARGET");
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
        return sum;
    }

    public JSONObject insertBranchGroupRevenue(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        JSONObject json = new JSONObject();
        boolean status = false;
        try {
            String sql = "INSERT INTO AVN_BRANCHGROUPREVENUETARGET (BRANCHGROUPID,BRANCHTARGETID,REVENUE,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER)"
                    + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgroupid());
            statement.setString(2, target.getBrnachtargetid());
            statement.setString(3, target.getRevenue());
            statement.setString(4, username);
            statement.executeUpdate();

            int sum = 0;
            sql = "SELECT SUM(REVENUE) AS SUM FROM AVN_BRANCHGROUPREVENUETARGET WHERE  BRANCHTARGETID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBrnachtargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("SUM");
            }
            int amount = 0;
            sql = "SELECT TARGET FROM AVN_BRANCHTARGET WHERE BRANCHTARGETID=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBrnachtargetid());

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("TARGET");
            }

            if (sum <= amount || sum == 0) {
                connection.commit();
                status = true;
                json.put("status", status);
                json.put("remaning", (amount - sum));
            } else {
                json.put("status", status);

                connection.rollback();
            }

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
        return json;
    }

    public int getBranchGroupRevenueTargetCount(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHGROUPREVENUETARGET BGRT INNER JOIN AVN_BRANCHTARGET BT ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH R ON  RT.REGIONID=R.BRANCHID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID INNER JOIN AVN_BRANCHGROUP BG ON BGRT.BRANCHGROUPID = BG.BRANCHGROUPID WHERE RT.TARGETID=? AND BGRT.BRANCHTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.setString(2, target.getBrnachtargetid());

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

    public int getSearchBranchGroupRevenueTargetCount(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHGROUPREVENUETARGET BGRT INNER JOIN AVN_BRANCHTARGET BT ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH R ON  RT.REGIONID=R.BRANCHID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID INNER JOIN AVN_BRANCHGROUP BG ON BGRT.BRANCHGROUPID = BG.BRANCHGROUPID WHERE RT.TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
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

    public List<TargetSetting> getBranchGroupRevenueTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        ( SELECT BT.BRANCHTARGETID, R.ALIASNAME || ' - ' || B.ALIASNAME AS DES,BG.BRANCHGROUPID, BG.DESCRIPTION, BGRT.REVENUE FROM AVN_BRANCHGROUPREVENUETARGET BGRT INNER JOIN AVN_BRANCHTARGET BT ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH R ON  RT.REGIONID=R.BRANCHID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID INNER JOIN AVN_BRANCHGROUP BG ON BGRT.BRANCHGROUPID = BG.BRANCHGROUPID WHERE RT.TARGETID=? AND BGRT.BRANCHTARGETID=? ) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());
            statement.setString(2, parameters.getBrnachtargetid());
            statement.setInt(3, start + minCount);
            statement.setInt(4, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBrnachtargetid(resultSet.getString("BRANCHTARGETID"));
                targetData.setBrnachtargetdes(resultSet.getString("DES"));
                targetData.setBranchgroupid(resultSet.getString("BRANCHGROUPID"));
                targetData.setBranchgroupdes(resultSet.getString("DESCRIPTION"));
                targetData.setRevenue(resultSet.getString("REVENUE"));
                list.add(targetData);
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

    public List<TargetSetting> getSearchBranchGroupRevenueTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        ( SELECT BT.BRANCHTARGETID, R.ALIASNAME || ' - ' || B.ALIASNAME AS DES,BG.BRANCHGROUPID, BG.DESCRIPTION, BGRT.REVENUE FROM AVN_BRANCHGROUPREVENUETARGET BGRT INNER JOIN AVN_BRANCHTARGET BT ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH R ON  RT.REGIONID=R.BRANCHID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID INNER JOIN AVN_BRANCHGROUP BG ON BGRT.BRANCHGROUPID = BG.BRANCHGROUPID WHERE RT.TARGETID=? ) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());;
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBrnachtargetid(resultSet.getString("BRANCHTARGETID"));
                targetData.setBrnachtargetdes(resultSet.getString("DES"));
                targetData.setBranchgroupid(resultSet.getString("BRANCHGROUPID"));
                targetData.setBranchgroupdes(resultSet.getString("DESCRIPTION"));
                targetData.setRevenue(resultSet.getString("REVENUE"));
                list.add(targetData);
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

    public List<TargetSetting> getRegionBranchGroupList(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BGRT.BGTID, R.ALIASNAME || ' - ' || B.ALIASNAME || ' - ' || BG.DESCRIPTION AS DES  FROM AVN_BRANCHGROUPREVENUETARGET BGRT INNER JOIN AVN_BRANCHTARGET BT ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH R ON  RT.REGIONID=R.BRANCHID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID INNER JOIN AVN_BRANCHGROUP BG ON BGRT.BRANCHGROUPID = BG.BRANCHGROUPID WHERE RT.TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchgrouprevenuetargetid(resultSet.getString("BGTID"));
                targetData.setBranchgrouprevenuetargetdes(resultSet.getString("DES"));
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

    public List<TargetSetting> getBranchGroupMemberRegionList(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT RT.REGIONALTARGETID , R.ALIASNAME AS DES "
                    + "FROM AVN_BRANCHGROUPREVENUETARGET BGRT "
                    + "INNER JOIN AVN_BRANCHTARGET BT "
                    + "ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "INNER JOIN AVN_REGIONALTARGET RT "
                    + "ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RT.REGIONID=R.BRANCHID "
                    + "WHERE RT.TARGETID =? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setRegionid(resultSet.getString("REGIONALTARGETID"));
                targetData.setRegiondes(resultSet.getString("DES"));
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

    public List<TargetSetting> getBranchGroupMemberBranchList(String regiontargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT BT.BRANCHTARGETID, B.ALIASNAME AS DES "
                    + "FROM AVN_BRANCHGROUPREVENUETARGET BGRT "
                    + "INNER JOIN AVN_BRANCHTARGET BT "
                    + "ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "INNER JOIN AVN_REGIONALTARGET RT "
                    + "ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BT.BRANCHID=B.BRANCHID "
                    + "WHERE RT.REGIONALTARGETID =? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, regiontargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchid(resultSet.getString("BRANCHTARGETID"));
                targetData.setBranchdes(resultSet.getString("DES"));
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

    public List<TargetSetting> getBranchGroupMemberGroupList(String brachtargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BGRT.BGTID, BG.DESCRIPTION AS DES "
                    + "FROM AVN_BRANCHGROUPREVENUETARGET BGRT "
                    + "INNER JOIN AVN_BRANCHTARGET BT "
                    + "ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "INNER JOIN AVN_REGIONALTARGET RT "
                    + "ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_BRANCHGROUP BG "
                    + "ON BGRT.BRANCHGROUPID = BG.BRANCHGROUPID "
                    + "WHERE BT.BRANCHTARGETID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, brachtargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchgrouprevenuetargetid(resultSet.getString("BGTID"));
                targetData.setBranchgrouprevenuetargetdes(resultSet.getString("DES"));
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

    public int getBranchGroupRevenueTargetAmout(String bgrtid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int sum = 0;
        try {
            String sql = "SELECT REVENUE FROM AVN_BRANCHGROUPREVENUETARGET WHERE BGTID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, bgrtid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("REVENUE");
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
        return sum;
    }

    public int getBranchGroupActivityTargetCount(String bgatid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int sum = 0;
        try {
            String sql = "SELECT COUNT FROM AVN_BRANCHGROUPACTIVITYTARGET WHERE BGATID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, bgatid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("COUNT");
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
        return sum;
    }

    public void deleteRow(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = " DELETE FROM AVN_BRANCHGROUMEMBERTARGET WHERE  BRANCHGROUPREVENUETARGETID=? AND  BRANCHGROUPMEMBERID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgrouprevenuetargetid());
            statement.setString(2, target.getBranchgroupmemberid());
            statement.executeUpdate();
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

    }

    public JSONObject insertBranchGroupMemberTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONObject json = new JSONObject();
        boolean status = false;
        try {
            String sql = "INSERT INTO AVN_BRANCHGROUMEMBERTARGET (BRANCHGROUPREVENUETARGETID,BRANCHGROUPMEMBERID,REVENUE,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) "
                    + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgrouprevenuetargetid());
            statement.setString(2, target.getBranchgroupmembertargetid());
            statement.setString(3, target.getRevenue());
            statement.setString(4, username);
            statement.executeUpdate();

            int sum = 0;
            sql = "SELECT SUM(REVENUE) AS SUM FROM AVN_BRANCHGROUMEMBERTARGET WHERE  BRANCHGROUPREVENUETARGETID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgrouprevenuetargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("SUM");
            }
            int amount = 0;
            sql = "SELECT REVENUE FROM AVN_BRANCHGROUPREVENUETARGET WHERE BGTID=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgrouprevenuetargetid());

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("REVENUE");
            }

            if (sum <= amount || sum == 0) {
                connection.commit();
                status = true;
                json.put("status", status);
                json.put("remaning", amount - sum);
            } else {
                json.put("status", status);

                connection.rollback();
            }

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
        return json;
    }

    public List<TargetSetting> getBranchGroupMemberid(String branchgrouprevenuetargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BGM.BGMID, NVL(E.INITIALS, '') || ' ' || NVL(E.LASTNAME, '') AS NAME  FROM AVN_BRANCHGROUPMEMBER BGM INNER JOIN AVN_EMPLOYEE E ON BGM.EMPLOYEEID=E.EMPLOYEEID INNER JOIN AVN_BRANCHGROUPREVENUETARGET BGRT ON BGRT.BRANCHGROUPID=BGM.BRANCHGROUPID WHERE BGRT.BGTID=? ORDER BY E.INITIALS";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, branchgrouprevenuetargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setEmpid(resultSet.getString("BGMID"));
                targetData.setEmpname(resultSet.getString("NAME"));
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

    public List<TargetSetting> getBranchGroupMemberidByBGMTID(String barnchgorupactivitytargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BGM.BGMID, "
                    + "  NVL(E.INITIALS, '') "
                    + "  || ' ' "
                    + "  || NVL(E.LASTNAME, '') AS NAME "
                    + "FROM AVN_BRANCHGROUPMEMBER BGM "
                    + "INNER JOIN AVN_EMPLOYEE E "
                    + "ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + "INNER JOIN AVN_BRANCHGROUPACTIVITYTARGET BAT "
                    + "ON BGM.BRANCHGROUPID=BAT.BRANCHGORUPID "
                    + "WHERE BAT.BGATID =? "
                    + "ORDER BY E.INITIALS";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, barnchgorupactivitytargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setEmpid(resultSet.getString("BGMID"));
                targetData.setEmpname(resultSet.getString("NAME"));
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

    public int getBranchGroupMemberTargetCount(String BGRTID) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHGROUMEMBERTARGET WHERE BRANCHGROUPREVENUETARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, BGRTID);
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

    public int getSearchBranchGroupMemberTargetCount(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHGROUMEMBERTARGET BGMT "
                    + "INNER JOIN AVN_BRANCHGROUPMEMBER BGM "
                    + "ON BGMT.BRANCHGROUPMEMBERID=BGM.BGMID "
                    + "INNER JOIN AVN_EMPLOYEE E "
                    + "ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + "INNER JOIN AVN_BRANCHGROUPREVENUETARGET BGRT "
                    + "ON BGMT.BRANCHGROUPREVENUETARGETID=BGRT.BGTID "
                    + "INNER JOIN AVN_BRANCHTARGET BT "
                    + "ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "INNER JOIN AVN_REGIONALTARGET RT "
                    + "ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_BRANCHGROUP BG "
                    + "ON BGM.BRANCHGROUPID= BG.BRANCHGROUPID "
                    + "WHERE RT.TARGETID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
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

    public List<TargetSetting> getBranchGroupMemberTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        ( SELECT BGMT.BGMTID, BGMT.BRANCHGROUPREVENUETARGETID, R.ALIASNAME || ' - ' || B.ALIASNAME || ' - ' || BG.DESCRIPTION  AS DES,  BGM.BGMID, NVL(E.INITIALS, '') || ' ' || NVL(E.LASTNAME, '') AS NAME,BGMT.REVENUE FROM AVN_BRANCHGROUMEMBERTARGET BGMT "
                    + " INNER JOIN AVN_BRANCHGROUPMEMBER BGM ON BGMT.BRANCHGROUPMEMBERID=BGM.BGMID  "
                    + " INNER JOIN AVN_EMPLOYEE E ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + " INNER JOIN AVN_BRANCHGROUPREVENUETARGET BGRT ON BGMT.BRANCHGROUPREVENUETARGETID=BGRT.BGTID  "
                    + " INNER JOIN AVN_BRANCHTARGET BT ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + " INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + " INNER JOIN AVN_BRANCH R ON RT.REGIONID=R.BRANCHID "
                    + " INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID "
                    + " INNER JOIN AVN_BRANCHGROUP BG ON BGM.BRANCHGROUPID= BG.BRANCHGROUPID WHERE BGMT.BRANCHGROUPREVENUETARGETID=?) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getBranchgrouprevenuetargetid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchgroupmembertargetid(resultSet.getString("BGMTID"));
                targetData.setBranchgrouprevenuetargetid(resultSet.getString("BRANCHGROUPREVENUETARGETID"));
                targetData.setBranchgrouprevenuetargetdes(resultSet.getString("DES"));
                targetData.setBranchgroupmemberid(resultSet.getString("BGMID"));
                targetData.setEmpname(resultSet.getString("NAME"));
                targetData.setRevenue(resultSet.getString("REVENUE"));
                list.add(targetData);
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

    public List<TargetSetting> getSearchBranchGroupMemberTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        ( SELECT BGMT.BGMTID, R.ALIASNAME || ' - ' || B.ALIASNAME || ' - ' || BG.DESCRIPTION  AS DES,  BGM.BGMID, NVL(E.INITIALS, '') || ' ' || NVL(E.LASTNAME, '') AS NAME,BGMT.REVENUE FROM AVN_BRANCHGROUMEMBERTARGET BGMT "
                    + "                  INNER JOIN AVN_BRANCHGROUPMEMBER BGM ON BGMT.BRANCHGROUPMEMBERID=BGM.BGMID  "
                    + "                     INNER JOIN AVN_EMPLOYEE E ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + "                     INNER JOIN AVN_BRANCHGROUPREVENUETARGET BGRT ON BGMT.BRANCHGROUPREVENUETARGETID=BGRT.BGTID "
                    + "                     INNER JOIN AVN_BRANCHTARGET BT ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "                     INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "                     INNER JOIN AVN_BRANCH R ON RT.REGIONID=R.BRANCHID "
                    + "                     INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID "
                    + "                     INNER JOIN AVN_BRANCHGROUP BG ON BGM.BRANCHGROUPID= BG.BRANCHGROUPID "
                    + "                     WHERE RT.TARGETID=?) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchgroupmembertargetid(resultSet.getString("BGMTID"));
                targetData.setBranchgroupmembertargetdes(resultSet.getString("DES"));
                targetData.setBranchgroupid(resultSet.getString("BGMID"));
                targetData.setEmpname(resultSet.getString("NAME"));
                targetData.setRevenue(resultSet.getString("REVENUE"));
                list.add(targetData);
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

    public void deleteTabletwoRow(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_BRANCHGROUMEMBERTARGET WHERE BRANCHGROUPMEMBERID=? AND BRANCHGROUPMEMBERID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgroupid());
            statement.setString(2, target.getRegionalactivitytargetid());
            statement.executeUpdate();
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

    }

    public List<TargetSetting> getRegionBranchActivity(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BAT.BATID, R.ALIASNAME || ' - ' || B.ALIASNAME || ' - '  || ATY.ACTIVITYTYPEDESCRIPTION  AS DES FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + " INNER JOIN AVN_REGIONALACTIVITYTARGET RAT ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + " INNER JOIN AVN_BRANCH R ON RAT.REGIONID=R.BRANCHID "
                    + " INNER JOIN AVN_BRANCH B ON BAT.BRANCHID=B.BRANCHID "
                    + " INNER JOIN AVN_ACTIVITYTARGET AT ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + " INNER JOIN AVN_ACTIVITYTYPES ATY ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + " WHERE RAT.TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchactivitytargetid(resultSet.getString("BATID"));
                targetData.setBranchactivitytargetdes(resultSet.getString("DES"));
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

    public int getBranchActivityCount(String batid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int COUNT = 0;
        try {
            String sql = "SELECT BCOUNT FROM AVN_BRANCHACTIVITYTARGET WHERE BATID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, batid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                COUNT = resultSet.getInt("BCOUNT");
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
        return COUNT;
    }

    public void insertBranchGroupActivityTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "INSERT INTO AVN_BRANCHGROUPACTIVITYTARGET (BRANCHGORUPID,BRANCHACTIVITYTARGETID,COUNT,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER)"
                    + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgroupid());
            statement.setString(2, target.getBranchactivitytargetid());
            statement.setString(3, target.getCount());
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

    public int getBranchGroupActivityCount(String BGATID, String BGID) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHGROUPACTIVITYTARGET WHERE BRANCHACTIVITYTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, BGATID);
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

    public int getSearchBranchGroupActivityCount(String targetid, String BGID) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "INNER JOIN AVN_BRANCHGROUP BG "
                    + "ON BGAT.BRANCHGORUPID =BG.BRANCHGROUPID "
                    + "WHERE RAT.TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
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

    public List<TargetSetting> getBranchGroupActivityTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        (SELECT BAT.BATID ,BGAT.BGATID,  R.ALIASNAME ||' - ' || B.ALIASNAME || ' - ' || ATY.ACTIVITYTYPEDESCRIPTION AS DES, BG.BRANCHGROUPID, BG.DESCRIPTION,BGAT.COUNT FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + " INNER JOIN  AVN_BRANCHACTIVITYTARGET BAT ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + " INNER JOIN AVN_REGIONALACTIVITYTARGET RAT ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + " INNER JOIN AVN_BRANCH R ON RAT.REGIONID=R.BRANCHID "
                    + " INNER JOIN AVN_BRANCH B ON BAT.BRANCHID=B.BRANCHID "
                    + " INNER JOIN AVN_ACTIVITYTARGET AT ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + " INNER JOIN AVN_ACTIVITYTYPES ATY ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + " INNER JOIN AVN_BRANCHGROUP BG ON BGAT.BRANCHGORUPID=BG.BRANCHGROUPID "
                    + " WHERE BGAT.BRANCHACTIVITYTARGETID=?) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getBranchactivitytargetid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchactivitytargetid(resultSet.getString("BATID"));
                targetData.setBranchgroupactivitytargeid(resultSet.getString("BGATID"));
                targetData.setBranchactivitytargetdes(resultSet.getString("DES"));
                targetData.setBranchgroupid(resultSet.getString("BRANCHGROUPID"));
                targetData.setBranchgroupdes(resultSet.getString("DESCRIPTION"));
                targetData.setCount(resultSet.getString("COUNT"));
                list.add(targetData);
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

    public List<TargetSetting> getSearchBranchGroupActivityTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        (SELECT BAT.BATID , "
                    + "  R.ALIASNAME "
                    + "  ||' - ' "
                    + "  || B.ALIASNAME "
                    + "  || ' - ' "
                    + "  || ATY.ACTIVITYTYPEDESCRIPTION AS DES, "
                    + "  BG.BRANCHGROUPID, "
                    + "  BG.DESCRIPTION, "
                    + "  BGAT.COUNT "
                    + "FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "INNER JOIN AVN_BRANCHGROUP BG "
                    + "ON BGAT.BRANCHGORUPID =BG.BRANCHGROUPID "
                    + "WHERE RAT.TARGETID=?) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchactivitytargetid(resultSet.getString("BATID"));
                targetData.setBranchactivitytargetdes(resultSet.getString("DES"));
                targetData.setBranchgroupid(resultSet.getString("BRANCHGROUPID"));
                targetData.setBranchgroupdes(resultSet.getString("DESCRIPTION"));
                targetData.setCount(resultSet.getString("COUNT"));
                list.add(targetData);
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

    public void dataTableeightdeleteRow(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_BRANCHGROUPACTIVITYTARGET WHERE BRANCHACTIVITYTARGETID=? AND BRANCHGORUPID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchactivitytargetid());
            statement.setString(2, target.getBranchgroupid());
            statement.executeUpdate();
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

    }

    public List<TargetSetting> getRegionBranchGroupActivity(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BGAT.BGATID , "
                    + "  R.ALIASNAME "
                    + "  ||' - ' "
                    + "  || B.ALIASNAME "
                    + "  || ' - ' "
                    + "  || ATY.ACTIVITYTYPEDESCRIPTION "
                    + "  || ' - ' "
                    + "  || BG.DESCRIPTION AS DES "
                    + "FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "INNER JOIN AVN_BRANCHGROUP BG "
                    + "ON BGAT.BRANCHGORUPID=BG.BRANCHGROUPID "
                    + "WHERE AT.TARGETID    =? "
                    + "ORDER BY BGAT.BGATID ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchgroupactivitytargeid(resultSet.getString("BGATID"));
                targetData.setBranchgroupactivitytargetdes(resultSet.getString("DES"));
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

    public void insertBranchGroupMemberActivityTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "INSERT INTO AVN_BRANCHMEMBERACTIVITYTARGET (BRANCHGROUPACTIVITYTARGETID,BRANCHGROUPMEMBERID,COUNT,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER)"
                    + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgroupactivitytargeid());
            statement.setString(2, target.getBranchgroupmembertargetid());
            statement.setString(3, target.getCount());
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

    public int getRegionActivityCounts(String regionid, String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT  COUNT(*)As CNT FROM AVN_REGIONALACTIVITYTARGET WHERE  TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
//            statement.setString(1, regionid);
            statement.setString(1, targetid);
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

    public int getSearchRegionActivityCounts(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT  COUNT(*)As CNT FROM AVN_REGIONALACTIVITYTARGET WHERE  TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
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

    public List<TargetSetting> getRegionalActivityTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        (SELECT RAT.REGIONID, "
                    + "  R.ALIASNAME, "
                    + "  AT.ACTIVITYTARGETID, "
                    + "  AC.ACTIVITYTYPEDESCRIPTION, "
                    + "  RAT.ACTIVITYCOUNY "
                    + " FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + " INNER JOIN AVN_ACTIVITYTARGET AT "
                    + " ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + " INNER JOIN AVN_BRANCH R "
                    + " ON RAT.BRANCHID=R.BRANCHID "
                    + " INNER JOIN AVN_ACTIVITYTYPES AC "
                    + " ON AT.ACTIVITYTYPEID=AC.ACTIVITYTYPEID "
                    + " WHERE  RAT.TARGETID=?) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
//            statement.setString(1, parameters.getRegionid());
            statement.setString(1, parameters.getTargetid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setRegionid(resultSet.getString("REGIONID"));
                targetData.setRegiondes(resultSet.getString("ALIASNAME"));
                targetData.setTargetactivityid(resultSet.getString("ACTIVITYTARGETID"));
                targetData.setTargetactivitydes(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                targetData.setCount(resultSet.getString("ACTIVITYCOUNY"));
                list.add(targetData);
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

    public List<TargetSetting> getSearchRegionalActivityTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        (SELECT RAT.REGIONID, "
                    + "  R.ALIASNAME, "
                    + "  AT.ACTIVITYTARGETID, "
                    + "  AC.ACTIVITYTYPEDESCRIPTION, "
                    + "  RAT.ACTIVITYCOUNY "
                    + " FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + " INNER JOIN AVN_ACTIVITYTARGET AT "
                    + " ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + " INNER JOIN AVN_BRANCH R "
                    + " ON RAT.REGIONID=R.BRANCHID "
                    + " INNER JOIN AVN_ACTIVITYTYPES AC "
                    + " ON AT.ACTIVITYTYPEID=AC.ACTIVITYTYPEID "
                    + " WHERE  RAT.TARGETID=? ORDER BY RAT.REGIONID) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setRegionid(resultSet.getString("REGIONID"));
                targetData.setRegiondes(resultSet.getString("ALIASNAME"));
                targetData.setTargetactivityid(resultSet.getString("ACTIVITYTARGETID"));
                targetData.setTargetactivitydes(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                targetData.setCount(resultSet.getString("ACTIVITYCOUNY"));
                list.add(targetData);
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

    public void deletedatatablethreeRow(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_REGIONALACTIVITYTARGET WHERE REGIONID=? AND RACTIVITYID=? AND TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getRegionid());
            statement.setString(2, target.getTargetactivityid());
            statement.setString(3, target.getTargetid());
            statement.executeUpdate();
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

    }

    public void updatedatatablethreeRow(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_REGIONALACTIVITYTARGET SET ACTIVITYCOUNY=? WHERE REGIONID=? AND RACTIVITYID=?  AND TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getCount());
            statement.setString(2, target.getRegionid());
            statement.setString(3, target.getTargetactivityid());
            statement.setString(4, target.getTargetid());
            statement.executeUpdate();
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

    }

    public int getRegionTargetAmount(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int amount = 0;
        try {
            String sql = "SELECT TARGET FROM AVN_REGIONALTARGET WHERE REGIONALTARGETID=? AND TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getRegionaltargetid());
            statement.setString(2, target.getTargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("TARGET");
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
        return amount;
    }

    public int getBranchActivityCounts(String regionid, String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "  SELECT COUNT(DISTINCT BB.BRANCHID) AS CNT FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH BB "
                    + "ON BAT.BRANCHID =BB.BRANCHID "
                    + "WHERE RAT.REGIONID =? AND RAT.TARGETID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, regionid);
            statement.setString(2, targetid);
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

    public int getSearchBranchActivityCounts(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*)AS CNT "
                    + "FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ACT "
                    + "ON AT.ACTIVITYTYPEID=ACT.ACTIVITYTYPEID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID =B.BRANCHID "
                    + "WHERE RAT.TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
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

    public List<TargetSetting> geBranchActivityTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        (SELECT DISTINCT BB.BRANCHID , "
                    + "  BB.ALIASNAME, "
                    + "  NVL( "
                    + "  (SELECT BAT.BCOUNT "
                    + "  FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=1 "
                    + "  AND RAT.REGIONID     =? "
                    + "  AND BAT.BRANCHID     =BB.BRANCHID "
                    + "  ),0) AS CALLCOUNT, "
                    + "  NVL( "
                    + "  (SELECT BAT.BATID "
                    + "  FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=1 "
                    + "  AND RAT.REGIONID     =? "
                    + "  AND BAT.BRANCHID     =BB.BRANCHID "
                    + "  ),0) AS CALLACTIVITYID, "
                    + "  NVL( "
                    + "  (SELECT BAT.BCOUNT "
                    + "  FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=2 "
                    + "  AND RAT.REGIONID     =? "
                    + "  AND BAT.BRANCHID     =BB.BRANCHID "
                    + "  ),0) AS VPCOUNT, "
                    + "  NVL( "
                    + "  (SELECT BAT.BATID "
                    + "  FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=2 "
                    + "  AND RAT.REGIONID     =? "
                    + "  AND BAT.BRANCHID     =BB.BRANCHID "
                    + "  ),0) AS VPACTIVITYID, "
                    + "  NVL( "
                    + "  (SELECT BAT.BCOUNT "
                    + "  FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=3 "
                    + "  AND RAT.REGIONID     =? "
                    + "  AND BAT.BRANCHID     =BB.BRANCHID "
                    + "  ),0) AS PROPOSALCOUNT, "
                    + "  NVL( "
                    + "  (SELECT BAT.BATID "
                    + "  FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=3 "
                    + "  AND RAT.REGIONID     =? "
                    + "  AND BAT.BRANCHID     =BB.BRANCHID "
                    + "  ),0) AS PROPOSALACTIVITYID "
                    + "FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH BB "
                    + "ON BAT.BRANCHID    =BB.BRANCHID "
                    + "WHERE RAT.REGIONID =? "
                    + "AND RAT.TARGETID   =?) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());
            statement.setString(2, parameters.getRegionid());
            statement.setString(3, parameters.getTargetid());
            statement.setString(4, parameters.getRegionid());
            statement.setString(5, parameters.getTargetid());
            statement.setString(6, parameters.getRegionid());
            statement.setString(7, parameters.getTargetid());
            statement.setString(8, parameters.getRegionid());
            statement.setString(9, parameters.getTargetid());
            statement.setString(10, parameters.getRegionid());
            statement.setString(11, parameters.getTargetid());
            statement.setString(12, parameters.getRegionid());
            statement.setString(13, parameters.getRegionid());
            statement.setString(14, parameters.getTargetid());

            statement.setInt(15, start + minCount);
            statement.setInt(16, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData = new TargetSetting();
                targetData.setBranchid(resultSet.getString("BRANCHID"));
                targetData.setBranchdes(resultSet.getString("ALIASNAME"));
                targetData.setCallcount(resultSet.getString("CALLCOUNT"));
                targetData.setCallactivityid(resultSet.getString("CALLACTIVITYID"));
                targetData.setProposalscount(resultSet.getString("PROPOSALCOUNT"));
                targetData.setProposalsactivityid(resultSet.getString("PROPOSALACTIVITYID"));
                targetData.setVisitspresentationscount(resultSet.getString("VPCOUNT"));
                targetData.setVpresentationsactivityid(resultSet.getString("VPACTIVITYID"));
                list.add(targetData);
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

    public List<TargetSetting> geSearchBranchActivityTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        (SELECT BAT.REGIONACTIVITYTARGETID,"
                    + "  R.ALIASNAME "
                    + "  || ' - ' "
                    + "  || ACT.ACTIVITYTYPEDESCRIPTION AS DES, "
                    + "  BAT.BRANCHID, "
                    + "  B.ALIASNAME, "
                    + "  BAT.BCOUNT "
                    + "FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ACT "
                    + "ON AT.ACTIVITYTYPEID=ACT.ACTIVITYTYPEID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID =B.BRANCHID "
                    + "WHERE RAT.TARGETID=?) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setRegionalactivitytargetid(resultSet.getString("REGIONACTIVITYTARGETID"));
                targetData.setRegionalactivitytargetdes(resultSet.getString("DES"));
                targetData.setBranchid(resultSet.getString("BRANCHID"));
                targetData.setBranchdes(resultSet.getString("ALIASNAME"));
                targetData.setCount(resultSet.getString("BCOUNT"));
                list.add(targetData);
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

    public void updatedatatablefiveRow(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_BRANCHACTIVITYTARGET SET BCOUNT=? WHERE  REGIONACTIVITYTARGETID=? AND BRANCHID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getCount());
            statement.setString(2, target.getRegionalactivitytargetid());
            statement.setString(3, target.getBranchid());
            statement.executeUpdate();
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

    }

    public void deletedatatablefiveRow(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_BRANCHACTIVITYTARGET WHERE  REGIONACTIVITYTARGETID=? AND BRANCHID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getRegionalactivitytargetid());
            statement.setString(2, target.getBranchid());

            statement.executeUpdate();
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

    }

    public void deleteDatatabelsix(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_BRANCHGROUPREVENUETARGET WHERE BRANCHGROUPID=? AND BRANCHTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgroupid());
            statement.setString(2, target.getBrnachtargetid());
            statement.executeUpdate();
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

    }

    public JSONObject updateBranchGroupMemberTarget(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        JSONObject json = new JSONObject();
        boolean status = false;
        try {
            String sql = "UPDATE AVN_BRANCHGROUPREVENUETARGET SET REVENUE=? WHERE BRANCHGROUPID=? AND BRANCHTARGETID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getRevenue());
            statement.setString(2, target.getBranchgroupid());
            statement.setString(3, target.getBrnachtargetid());
            statement.executeUpdate();
            int sum = 0;
            sql = "SELECT SUM(REVENUE) AS SUM FROM AVN_BRANCHGROUPREVENUETARGET WHERE  BRANCHTARGETID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBrnachtargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("SUM");
            }
            int amount = 0;
            sql = "SELECT TARGET FROM AVN_BRANCHTARGET WHERE BRANCHTARGETID=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBrnachtargetid());

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("TARGET");
            }

            if (sum <= amount || sum == 0) {
                connection.commit();
                status = true;
                json.put("status", status);
                json.put("sum", sum);
                json.put("remaning", amount - sum);
            } else {
                json.put("status", status);

                connection.rollback();
            }

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
        return json;
    }

    public JSONObject updatedatatableSevenRow(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        ResultSet resultSet = null;
        JSONObject json = new JSONObject();
        boolean status = false;
        try {
            String sql = "UPDATE AVN_BRANCHGROUMEMBERTARGET SET REVENUE=? WHERE BRANCHGROUPREVENUETARGETID=? AND BRANCHGROUPMEMBERID=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getRevenue());
            statement.setString(2, target.getBranchgrouprevenuetargetid());
            statement.setString(3, target.getBranchgroupmembertargetid());
            statement.executeUpdate();

            int sum = 0;
            sql = "SELECT SUM(REVENUE) AS SUM FROM AVN_BRANCHGROUMEMBERTARGET WHERE  BRANCHGROUPREVENUETARGETID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgrouprevenuetargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("SUM");
            }
            int amount = 0;
            sql = "SELECT REVENUE FROM AVN_BRANCHGROUPREVENUETARGET WHERE BGTID=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgrouprevenuetargetid());

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("REVENUE");
            }

            if (sum <= amount || sum == 0) {
                connection.commit();
                status = true;
                json.put("status", status);
                json.put("remaning", amount - sum);
            } else {
                json.put("status", status);

                connection.rollback();
            }

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
        return json;
    }

    public void updatedatatableEightRow(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_BRANCHGROUPACTIVITYTARGET SET COUNT=? WHERE BRANCHACTIVITYTARGETID=? AND BRANCHGORUPID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getCount());
            statement.setString(2, target.getBranchgroupactivitytargeid());
            statement.setString(3, target.getBranchgroupid());
            statement.executeUpdate();
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

    }

    public int getBranchGroupMemberActivityCount(String BGATID) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHMEMBERACTIVITYTARGET WHERE BRANCHGROUPACTIVITYTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, BGATID);
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

    public int getSearchBranchGroupMemberActivityCount(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHMEMBERACTIVITYTARGET BGMAT "
                    + "INNER JOIN AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "ON BGMAT.BRANCHGROUPACTIVITYTARGETID=BGAT.BGATID "
                    + "INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "INNER JOIN AVN_BRANCHGROUP BG "
                    + "ON BGAT.BRANCHGORUPID=BG.BRANCHGROUPID "
                    + "INNER JOIN AVN_BRANCHGROUPMEMBER BGM "
                    + "ON BGMAT.BRANCHGROUPMEMBERID=BGM.BGMID "
                    + "INNER JOIN AVN_EMPLOYEE E "
                    + "ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + "WHERE RAT.TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
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

    public List<TargetSetting> getBranchGroupMemberActivityTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        (SELECT BGMAT.BRANCHGROUPACTIVITYTARGETID, BGMAT.BMATID, "
                    + "  R.ALIASNAME "
                    + "  || ' - ' "
                    + "  || B.ALIASNAME "
                    + "  || ' - ' "
                    + "  || ATY.ACTIVITYTYPEDESCRIPTION "
                    + "  || ' - ' "
                    + "  ||BG.DESCRIPTION AS DES, "
                    + "  BGMAT.BRANCHGROUPMEMBERID, "
                    + "  E.INITIALS "
                    + "  || ' ' "
                    + "  || E.LASTNAME AS NAME, "
                    + "  BGMAT.COUNT "
                    + "FROM AVN_BRANCHMEMBERACTIVITYTARGET BGMAT "
                    + "INNER JOIN AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "ON BGMAT.BRANCHGROUPACTIVITYTARGETID=BGAT.BGATID "
                    + "INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "INNER JOIN AVN_BRANCHGROUP BG "
                    + "ON BGAT.BRANCHGORUPID=BG.BRANCHGROUPID "
                    + "INNER JOIN AVN_BRANCHGROUPMEMBER BGM "
                    + "ON BGMAT.BRANCHGROUPMEMBERID=BGM.BGMID "
                    + "INNER JOIN AVN_EMPLOYEE E "
                    + "ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + "WHERE BGMAT.BRANCHGROUPACTIVITYTARGETID=?) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getBranchgroumemberactivityid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchgroupactivitytargeid(resultSet.getString("BRANCHGROUPACTIVITYTARGETID"));
                targetData.setBranchgroumemberactivityid(resultSet.getString("BMATID"));
                targetData.setBranchgroupactivitytargetdes(resultSet.getString("DES"));
                targetData.setBranchgroupmemberid(resultSet.getString("BRANCHGROUPMEMBERID"));
                targetData.setBranchgroupmemberdes(resultSet.getString("NAME"));
                targetData.setCount(resultSet.getString("COUNT"));
                list.add(targetData);
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

    public List<TargetSetting> getSearchBranchGroupMemberActivityTargeData(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        (SELECT BGMAT.BRANCHGROUPACTIVITYTARGETID, "
                    + "  R.ALIASNAME "
                    + "  || ' - ' "
                    + "  || B.ALIASNAME "
                    + "  || ' - ' "
                    + "  || ATY.ACTIVITYTYPEDESCRIPTION "
                    + "  || ' - ' "
                    + "  ||BG.DESCRIPTION AS DES, "
                    + "  BGMAT.BRANCHGROUPMEMBERID, "
                    + "  E.INITIALS "
                    + "  || ' ' "
                    + "  || E.LASTNAME AS NAME, "
                    + "  BGMAT.COUNT "
                    + "FROM AVN_BRANCHMEMBERACTIVITYTARGET BGMAT "
                    + "INNER JOIN AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "ON BGMAT.BRANCHGROUPACTIVITYTARGETID=BGAT.BGATID "
                    + "INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "INNER JOIN AVN_BRANCHGROUP BG "
                    + "ON BGAT.BRANCHGORUPID=BG.BRANCHGROUPID "
                    + "INNER JOIN AVN_BRANCHGROUPMEMBER BGM "
                    + "ON BGMAT.BRANCHGROUPMEMBERID=BGM.BGMID "
                    + "INNER JOIN AVN_EMPLOYEE E "
                    + "ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + "WHERE RAT.TARGETID=?) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());
            statement.setInt(2, start + minCount);
            statement.setInt(3, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchactivitytargetid(resultSet.getString("BRANCHGROUPACTIVITYTARGETID"));
                targetData.setBranchactivitytargetdes(resultSet.getString("DES"));
                targetData.setBranchgroupmemberid(resultSet.getString("BRANCHGROUPMEMBERID"));
                targetData.setBranchgroupmemberdes(resultSet.getString("NAME"));
                targetData.setCount(resultSet.getString("COUNT"));
                list.add(targetData);
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

    public void dataTableninedeleteRow(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_BRANCHMEMBERACTIVITYTARGET WHERE BRANCHGROUPACTIVITYTARGETID=? AND BRANCHGROUPMEMBERID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchgroumemberactivityid());
            statement.setString(2, target.getBranchgroupmemberid());
            statement.executeUpdate();
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

    }

    public void updateBranchMemberActivity(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE  AVN_BRANCHMEMBERACTIVITYTARGET SET COUNT=? WHERE BRANCHGROUPACTIVITYTARGETID=? AND BRANCHGROUPMEMBERID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getCount());
            statement.setString(2, target.getBranchgroupactivitytargeid());
            statement.setString(3, target.getBranchgroupmemberid());
            statement.executeUpdate();
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

    }

    public JSONArray getActitityType(String activityid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray activitytype = new JSONArray();
        try {

            String sql = "SELECT ATY.ACTIVITYTYPEID, ATY.ACTIVITYTYPEDESCRIPTION, AT.COUNT  FROM AVN_ACTIVITYTARGET AT INNER JOIN AVN_ACTIVITYTYPES ATY ON AT.ACTIVITYTYPEID=ATY.ACTIVITYTYPEID "
                    + "WHERE AT.TARGETID=?  ORDER BY ATY.ACTIVITYTYPEID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, activityid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray Jarray = new JSONArray();
                Jarray.put(resultSet.getString("ACTIVITYTYPEID"));
                Jarray.put(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                Jarray.put(resultSet.getString("COUNT"));
                activitytype.put(Jarray);

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
        return activitytype;
    }

    public TargetSetting getTargetByTargetId(String targetId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData = null;
        try {
            String sql = "SELECT TR.TARGETID, "
                    + "  TR.DESCRIPTION, "
                    + "  TR.PRODUCTID, "
                    + "  P.DESCRIPTION AS PDES, "
                    + "  TR.REVENUE, "
                    + "  TR.TARGETPERIODID, "
                    + "  TP.DESCRIPTION                           AS TPDES, "
                    + "  TO_CHAR(TR.TARGETSTARTDATE,'YYYY-MM-DD') AS TARGETSTARTDATE , "
                    + "  TO_CHAR(TR.TARGETENDDATE ,'YYYY-MM-DD')  AS TARGETENDDATE, "
                    + "  TR.TARGETTYPE "
                    + "FROM AVN_TARGET TR "
                    + "INNER JOIN AVN_TARGETPERIOD TP "
                    + "ON TR.TARGETPERIODID=TP.PERIODID "
                    + "INNER JOIN AVN_PRODUCT P "
                    + "ON TR.PRODUCTID  =P.PRODUCTID "
                    + "WHERE TR.TARGETID=?";
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
                targetData.setRevenue(resultSet.getString("REVENUE"));
                targetData.setTargetperiodid(resultSet.getString("TARGETPERIODID"));
                targetData.setTargetperioddes(resultSet.getString("TPDES"));
                targetData.setTargetstartdate(resultSet.getString("TARGETSTARTDATE"));
                targetData.setTargetenddate(resultSet.getString("TARGETENDDATE"));
                targetData.setTargettypeid(resultSet.getString("TARGETTYPE"));
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

    public int getMINRegionid(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT MIN(REGIONID) as MINREGIONID FROM AVN_REGIONALACTIVITYTARGET WHERE TARGETID=? AND ACTIVITYCOUNY >0";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("MINREGIONID");
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

    public List<TargetSetting> getTarget() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<TargetSetting> targetList = null;
        int amount = 0;
        try {
            String sql = "SELECT T.TARGETID, T.TARGETID || ' - ' || T.DESCRIPTION || ' - ' || P.DESCRIPTION AS  DESCRIPTION FROM AVN_TARGET T INNER JOIN AVN_PRODUCT P ON T.PRODUCTID=P.PRODUCTID  ORDER BY T.DESCRIPTION ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            targetList = new ArrayList<>();
            while (resultSet.next()) {
                TargetSetting targetsetting = new TargetSetting();
                targetsetting.setTargetid(resultSet.getString("TARGETID"));
                targetsetting.setTragetdes(resultSet.getString("DESCRIPTION"));
                targetList.add(targetsetting);
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
        return targetList;
    }

    public int getSumBranchGroupRevenue(String branchtargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int sum = 0;
        try {
            String sql = "SELECT SUM(REVENUE) AS REVENUE FROM AVN_BRANCHGROUPREVENUETARGET WHERE BRANCHTARGETID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, branchtargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("REVENUE");
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
        return sum;
    }

    public List<TargetSetting> getBranchGroupRevenueTargetList(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BGRT.BGTID,"
                    + "  B.BRANCHDESCRIPTION AS DES, BGRT.REVENUE "
                    + "FROM AVN_BRANCHGROUPREVENUETARGET BGRT "
                    + "INNER JOIN AVN_BRANCHTARGET BT "
                    + "ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "INNER JOIN AVN_REGIONALTARGET RT "
                    + "ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BT.BRANCHID =B.BRANCHID "
                    + "WHERE RT.REGIONALTARGETID IN "
                    + "  (SELECT RT.REGIONALTARGETID "
                    + "  FROM AVN_BRANCHGROUPREVENUETARGET BGRT "
                    + "  INNER JOIN AVN_BRANCHTARGET BT "
                    + "  ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "  INNER JOIN AVN_REGIONALTARGET RT "
                    + "  ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "  WHERE RT.TARGETID =?"
                    + "  )";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchgrouprevenuetargetid(resultSet.getString("BGTID"));
                targetData.setBranchgrouprevenuetargetdes(resultSet.getString("DES"));
                targetData.setRevenue(resultSet.getString("REVENUE"));
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

    public int getSumBranchGroup(String branchgrouprevenuetargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int sum = 0;
        try {
            String sql = "  SELECT SUM(REVENUE) AS REVENUE FROM AVN_BRANCHGROUMEMBERTARGET WHERE  BRANCHGROUPREVENUETARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, branchgrouprevenuetargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("REVENUE");
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
        return sum;
    }

    public List<TargetSetting> getTabfiveRegionList(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT RAT.REGIONID, R.ALIASNAME "
                    + "FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID    =R.BRANCHID "
                    + "WHERE RAT.TARGETID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setRegionid(resultSet.getString("REGIONID"));
                targetData.setRegiondes(resultSet.getString("ALIASNAME"));
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

    public List<TargetSetting> getTabfiveRegionActivityList(String targetid, String regionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT RAT.ID , "
                    + "  ATY.ACTIVITYTYPEDESCRIPTION "
                    + "FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "WHERE RAT.TARGETID   =? "
                    + "AND RAT.REGIONID     =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            statement.setString(2, regionid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setActivitytypeid(resultSet.getString("ID"));
                targetData.setActivitytypedes(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
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

    public List<TargetSetting> getTabfiveRegionActivityBranchList(String regionalactivitytargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BAT.BATID  , "
                    + "  B.ALIASNAME "
                    + "FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "WHERE  BAT.REGIONACTIVITYTARGETID= ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, regionalactivitytargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchactivitytargetid(resultSet.getString("BATID"));
                targetData.setBranchactivitytargetdes(resultSet.getString("ALIASNAME"));
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

    public List<TargetSetting> getTabfiveMemberActivityRegionList(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT R.BRANCHID, R.ALIASNAME "
                    + "FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "WHERE  RAT.TARGETID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setRegionid(resultSet.getString("BRANCHID"));
                targetData.setRegiondes(resultSet.getString("ALIASNAME"));
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

    public List<TargetSetting> getTabfiveMemberRegionActivityList(String targetid, String regionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT RAT.ID , "
                    + "  ATY.ACTIVITYTYPEDESCRIPTION "
                    + "FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "WHERE RAT.TARGETID =? "
                    + " AND RAT.REGIONID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            statement.setString(2, regionid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setActivitytypeid(resultSet.getString("ID"));
                targetData.setActivitytypedes(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
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

    public List<TargetSetting> getTabfiveRegionBranchList(String regionalactivitytargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BAT.BATID , "
                    + "  B.BRANCHDESCRIPTION "
                    + "FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "WHERE  BAT.REGIONACTIVITYTARGETID= ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, regionalactivitytargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchactivitytargetid(resultSet.getString("BATID"));
                targetData.setBranchactivitytargetdes(resultSet.getString("BRANCHDESCRIPTION"));
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

    public List<TargetSetting> getTabfiveRegionBranchGroupList(String brnachgroupactivityid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TargetSetting targetData;
        List<TargetSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT BGAT.BGATID , "
                    + "BG.DESCRIPTION "
                    + "FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_BRANCH R "
                    + "ON RAT.REGIONID=R.BRANCHID "
                    + "INNER JOIN AVN_BRANCH B "
                    + "ON BAT.BRANCHID=B.BRANCHID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES ATY "
                    + "ON ATY.ACTIVITYTYPEID=AT.ACTIVITYTYPEID "
                    + "INNER JOIN AVN_BRANCHGROUP BG ON BGAT.BRANCHGORUPID=BG.BRANCHGROUPID "
                    + "WHERE  BGAT.BRANCHACTIVITYTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, brnachgroupactivityid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchgroupid(resultSet.getString("BGATID"));
                targetData.setBranchgroupdes(resultSet.getString("DESCRIPTION"));
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

    public int getSumBranchRevenue(String regionaltargetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int sum = 0;
        try {
            String sql = "SELECT SUM(TARGET) AS REVENUE FROM  AVN_BRANCHTARGET WHERE REGIONALTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, regionaltargetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("REVENUE");
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
        return sum;
    }

    public int getBranchGroupRevenueCountByTargetid(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_BRANCHGROUPREVENUETARGET BGRT INNER JOIN AVN_BRANCHTARGET BT ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH R ON  RT.REGIONID=R.BRANCHID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID INNER JOIN AVN_BRANCHGROUP BG ON BGRT.BRANCHGROUPID = BG.BRANCHGROUPID WHERE RT.TARGETID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
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

    public void insertBranchGroupRevenueTarget(List<TargetSetting> brnachlist, List<TargetSetting> grouplist, String user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = dataSource.getConnection();
        try {

            for (int j = 0; j < brnachlist.size(); j++) {
                TargetSetting branchlistdata = brnachlist.get(j);
                for (int k = 0; k < grouplist.size(); k++) {
                    TargetSetting grouplistdata = grouplist.get(k);
                    String sql = "INSERT INTO AVN_BRANCHGROUPREVENUETARGET (BRANCHGROUPID,BRANCHTARGETID,REVENUE,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) "
                            + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";

                    statement = connection.prepareStatement(sql);
                    statement.setString(1, grouplistdata.getBranchgroupid());
                    statement.setString(2, branchlistdata.getBrnachtargetid());
                    statement.setString(3, "0");
                    statement.setString(4, user);
                    statement.executeUpdate();
                }

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

    }

    public List<TargetSetting> getBranchGroupRevenue(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        ( SELECT BGRT.BGTID, BT.BRANCHTARGETID, R.ALIASNAME || ' - ' || B.ALIASNAME AS DES,BG.BRANCHGROUPID, BG.DESCRIPTION, BGRT.REVENUE FROM AVN_BRANCHGROUPREVENUETARGET BGRT INNER JOIN AVN_BRANCHTARGET BT ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH R ON  RT.REGIONID=R.BRANCHID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID INNER JOIN AVN_BRANCHGROUP BG ON BGRT.BRANCHGROUPID = BG.BRANCHGROUPID WHERE RT.TARGETID=? AND BGRT.BRANCHTARGETID=? ) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());
            statement.setString(2, parameters.getBrnachtargetid());
            statement.setInt(3, start + minCount);
            statement.setInt(4, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setBranchgrouptargetid(resultSet.getString("BGTID"));
                targetData.setBrnachtargetid(resultSet.getString("BRANCHTARGETID"));
                targetData.setBrnachtargetdes(resultSet.getString("DES"));
                targetData.setBranchgroupid(resultSet.getString("BRANCHGROUPID"));
                targetData.setBranchgroupdes(resultSet.getString("DESCRIPTION"));
                targetData.setRevenue(resultSet.getString("REVENUE"));
                list.add(targetData);
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

    public int getBranchGroupRevenueCount(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHGROUPREVENUETARGET BGRT INNER JOIN AVN_BRANCHTARGET BT ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID INNER JOIN AVN_REGIONALTARGET RT ON BT.REGIONALTARGETID=RT.REGIONALTARGETID INNER JOIN AVN_BRANCH R ON  RT.REGIONID=R.BRANCHID INNER JOIN AVN_BRANCH B ON BT.BRANCHID=B.BRANCHID INNER JOIN AVN_BRANCHGROUP BG ON BGRT.BRANCHGROUPID = BG.BRANCHGROUPID WHERE RT.TARGETID=? AND BGRT.BRANCHTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.setString(2, target.getBrnachtargetid());
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

    public JSONObject updateBrnachGroupTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        JSONArray memberlist = new JSONArray();
        int count = 0;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            JSONArray array = new JSONArray(target.getDatatabledata());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String sql = "UPDATE AVN_BRANCHGROUPREVENUETARGET SET REVENUE=? WHERE BGTID=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("COUNT"));
                statement.setString(2, obj.getString("ID"));
                statement.executeUpdate();

                if (obj.getInt("COUNT") > 0) {
                    sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHGROUMEMBERTARGET WHERE BRANCHGROUPREVENUETARGETID=?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, obj.getString("ID"));
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        count = resultSet.getInt("CNT");
                    }

                    if (count == 0) {
                        sql = "SELECT BGMID FROM AVN_BRANCHGROUPMEMBER WHERE BRANCHGROUPID=?";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, obj.getString("BRANCHGROUPID"));
                        resultSet = statement.executeQuery();
                        while (resultSet.next()) {
                            memberlist.put(resultSet.getString("BGMID"));
                        }
                        for (int j = 0; j < memberlist.length(); j++) {
                            sql = "INSERT INTO AVN_BRANCHGROUMEMBERTARGET (BRANCHGROUPREVENUETARGETID,BRANCHGROUPMEMBERID,REVENUE,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) "
                                    + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                            statement = connection.prepareStatement(sql);
                            statement.setString(1, obj.getString("ID"));
                            statement.setString(2, memberlist.getString(j));
                            statement.setString(3, "0");
                            statement.setString(4, username);
                            statement.executeUpdate();
                        }

                    }

                }

            }

            int sum = 0;
            String sql = "SELECT SUM(REVENUE) AS SUM FROM AVN_BRANCHGROUPREVENUETARGET WHERE  BRANCHTARGETID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBrnachtargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("SUM");
            }
            int amount = 0;
            sql = "SELECT TARGET FROM AVN_BRANCHTARGET WHERE BRANCHTARGETID=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBrnachtargetid());

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("TARGET");
            }

            if (sum <= amount || sum == 0) {
                connection.commit();
                status = true;
                jason.put("remaning", (amount - sum));
                jason.put("status", status);
                jason.put("sum", sum);
            } else {
                connection.rollback();
            }

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
        return jason;
    }

    public JSONObject updateBrnachGroupMemberTarget(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        boolean commit = false;
        JSONObject jason = new JSONObject();
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            JSONArray array = new JSONArray(target.getDatatabledata());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String sql = "UPDATE AVN_BRANCHGROUMEMBERTARGET SET REVENUE=? WHERE BGMTID=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("COUNT"));
                statement.setString(2, obj.getString("ID"));
                statement.executeUpdate();
            }
            int amount = 0;
            int sum = 0;
            for (int j = 0; j < array.length(); j++) {
                JSONObject obj = array.getJSONObject(j);

                String sql = "SELECT SUM(REVENUE) AS SUM FROM AVN_BRANCHGROUMEMBERTARGET WHERE BRANCHGROUPREVENUETARGETID= ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("BGRTID"));
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    sum = resultSet.getInt("SUM");
                }

                sql = "SELECT BGRT.REVENUE FROM AVN_BRANCHGROUMEMBERTARGET BGMT INNER JOIN AVN_BRANCHGROUPREVENUETARGET BGRT ON BGMT.BRANCHGROUPREVENUETARGETID=BGRT.BGTID WHERE BGMT.BGMTID=?";

                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("ID"));
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    amount = resultSet.getInt("REVENUE");
                }

                if (sum <= amount || sum == 0) {
                   commit= true;
                } else {
                    commit= false;
                }
            }

            if (commit) {
                connection.commit();
                status = true;
                jason.put("status", status);
                jason.put("sum", sum);
                jason.put("remaning", (amount - sum));
            } else {
                connection.rollback();
            }

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
        return jason;
    }

    public List<TargetSetting> getRegionalActivityByRegion(TargetSetting parameters, int minCount, int start) throws Exception {
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
                    + "        (SELECT DISTINCT RAT.REGIONID,  "
                    + "  RR.ALIASNAME, "
                    + "    NVL((SELECT RAT.ACTIVITYCOUNY "
                    + "  FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=1 AND RAT.REGIONID=RR.BRANCHID "
                    + "  ),0) AS CALLCOUNT, "
                    + "   "
                    + " NVL((SELECT RAT.ID "
                    + "  FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=1 AND RAT.REGIONID=RR.BRANCHID "
                    + "  ),0) AS CALLACTIVITYID,  "
                    + "   "
                    + "   "
                    + "  NVL( "
                    + "  (SELECT RAT.ACTIVITYCOUNY "
                    + "  FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=3 AND RAT.REGIONID=RR.BRANCHID "
                    + "  ), 0) AS PROSPOSALCOUNT, "
                    + "   "
                    + "   NVL((SELECT RAT.ID "
                    + "  FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=3 AND RAT.REGIONID=RR.BRANCHID "
                    + "  ),0) AS  PROSPOSALACTIVITYID,  "
                    + "   "
                    + "   "
                    + "    NVL((SELECT RAT.ACTIVITYCOUNY "
                    + "  FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=2  AND RAT.REGIONID=RR.BRANCHID "
                    + "  ),0) AS VISITPRESENTATIONCOUNT, "
                    + "   "
                    + "    NVL((SELECT RAT.ID "
                    + "  FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  INNER JOIN AVN_BRANCH R "
                    + "  ON RAT.REGIONID=R.BRANCHID "
                    + "  INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "  ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "  WHERE RAT.TARGETID   =? "
                    + "  AND AT.ACTIVITYTYPEID=2 AND RAT.REGIONID=RR.BRANCHID "
                    + "  ),0) AS  VISITPRESENTATIONACTIVITYID "
                    + "   "
                    + "   "
                    + "   "
                    + "FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + "INNER JOIN AVN_BRANCH RR "
                    + "ON RAT.REGIONID   =RR.BRANCHID "
                    + "WHERE RAT.TARGETID=? ) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + " WHERE "
                    + "    ROWNUMER > ? ";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, parameters.getTargetid());
            statement.setString(2, parameters.getTargetid());
            statement.setString(3, parameters.getTargetid());
            statement.setString(4, parameters.getTargetid());
            statement.setString(5, parameters.getTargetid());
            statement.setString(6, parameters.getTargetid());
            statement.setString(7, parameters.getTargetid());
            statement.setInt(8, start + minCount);
            statement.setInt(9, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setRegionid(resultSet.getString("REGIONID"));
                targetData.setRegiondes(resultSet.getString("ALIASNAME"));
                targetData.setCallcount(resultSet.getString("CALLCOUNT"));
                targetData.setCallactivityid(resultSet.getString("CALLACTIVITYID"));
                targetData.setProposalscount(resultSet.getString("PROSPOSALCOUNT"));
                targetData.setProposalsactivityid(resultSet.getString("PROSPOSALACTIVITYID"));
                targetData.setVisitspresentationscount(resultSet.getString("VISITPRESENTATIONCOUNT"));
                targetData.setVpresentationsactivityid(resultSet.getString("VISITPRESENTATIONACTIVITYID"));
                list.add(targetData);
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

    public int getRegionActivityByRegionCounts(String regionid, String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT   COUNT( DISTINCT REGIONID)As CNT FROM AVN_REGIONALACTIVITYTARGET WHERE  TARGETID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
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

    public JSONArray getActivityCountByTargetid(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray array = new JSONArray();
        try {
            String sql = "SELECT ACTIVITYTYPEID,COUNT FROM AVN_ACTIVITYTARGET WHERE TARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray activitycount = new JSONArray();
                activitycount.put(resultSet.getString("ACTIVITYTYPEID"));
                activitycount.put(resultSet.getString("COUNT"));
                array.put(activitycount);
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
        return array;
    }

    public JSONArray getReaminingActivityCountByTargetid(String targetid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray array = new JSONArray();
        try {
            String sql = "SELECT SUM(RAT.ACTIVITYCOUNY) as SUM ,AC.ACTIVITYTYPEID "
                    + "FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "WHERE RAT.TARGETID   =? "
                    + "GROUP BY AC.ACTIVITYTYPEID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray activitycount = new JSONArray();
                activitycount.put(resultSet.getString("ACTIVITYTYPEID"));
                activitycount.put(resultSet.getString("SUM"));
                array.put(activitycount);
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
        return array;
    }

    public JSONObject updateRegionalActivity(TargetSetting target, String username) throws Exception {
        Connection connection = null;

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONObject json = new JSONObject();
        JSONArray Tarray = new JSONArray();
        JSONArray Rarray = new JSONArray();
        boolean ccount = false;
        boolean pcount = false;
        boolean vpcount = false;
        try {

            JSONArray array = new JSONArray(target.getDatatabledata());
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String sql = "UPDATE  AVN_REGIONALACTIVITYTARGET SET ACTIVITYCOUNY=? WHERE ID=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("VALUE"));
                statement.setString(2, obj.getString("ID"));
                statement.executeUpdate();
            }

            String sql = "SELECT ACTIVITYTYPEID,COUNT FROM AVN_ACTIVITYTARGET WHERE TARGETID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray activitycount = new JSONArray();
                activitycount.put(resultSet.getString("ACTIVITYTYPEID"));
                activitycount.put(resultSet.getString("COUNT"));
                Tarray.put(activitycount);
            }

            sql = "SELECT SUM(RAT.ACTIVITYCOUNY) as SUM ,AC.ACTIVITYTYPEID "
                    + "FROM AVN_REGIONALACTIVITYTARGET RAT "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "WHERE RAT.TARGETID   =? "
                    + "GROUP BY AC.ACTIVITYTYPEID";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray activitycount = new JSONArray();
                activitycount.put(resultSet.getString("ACTIVITYTYPEID"));
                activitycount.put(resultSet.getString("SUM"));
                Rarray.put(activitycount);
            }

            for (int i = 0; i < Tarray.length(); i++) {
                JSONArray arrayitem = Tarray.optJSONArray(i);
                for (int j = 0; j < Rarray.length(); j++) {
                    JSONArray remaningarrayitem = Rarray.optJSONArray(j);
                    if (arrayitem.getInt(0) == remaningarrayitem.getInt(0)) {
                        if (arrayitem.getInt(0) == 1) {
                            if (arrayitem.getInt(1) >= remaningarrayitem.getInt(1)) {
                                ccount = true;
                            }
                        } else if (arrayitem.getInt(0) == 2) {
                            if (arrayitem.getInt(1) >= remaningarrayitem.getInt(1)) {
                                vpcount = true;
                            }
                        } else {
                            if (arrayitem.getInt(1) >= remaningarrayitem.getInt(1)) {
                                pcount = true;
                            }
                        }
                    }
                }

            }
            if (ccount && vpcount && pcount) {
                connection.commit();
                json.put("status", true);
            } else {
                connection.rollback();
                json.put("status", false);
            }
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
        return json;

    }

    public JSONArray getToatalActivityCountByRegionid(String targetid, String regionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray array = new JSONArray();
        try {
            String sql = "SELECT SUM(RAT.ACTIVITYCOUNY) as SUM ,AC.ACTIVITYTYPEID  "
                    + "                    FROM AVN_REGIONALACTIVITYTARGET RAT  "
                    + "                    INNER JOIN AVN_ACTIVITYTARGET AT  "
                    + "                    ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID  "
                    + "                    INNER JOIN AVN_ACTIVITYTYPES AC  "
                    + "                    ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID  "
                    + "                    WHERE RAT.TARGETID   =? and  RAT.REGIONID   =? "
                    + "                    GROUP BY AC.ACTIVITYTYPEID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            statement.setString(2, regionid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray activitycount = new JSONArray();
                activitycount.put(resultSet.getString("ACTIVITYTYPEID"));
                activitycount.put(resultSet.getString("SUM"));
                array.put(activitycount);
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
        return array;
    }

    public JSONArray getReaminingActivityCountByRegionid(String targetid, String regionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray array = new JSONArray();
        try {
            String sql = "SELECT SUM(BAT.BCOUNT) AS SUM , "
                    + "  AC.ACTIVITYTYPEID "
                    + "FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "WHERE RAT.TARGETID   =? "
                    + "AND RAT.REGIONID     =? "
                    + "GROUP BY AC.ACTIVITYTYPEID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetid);
            statement.setString(2, regionid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray activitycount = new JSONArray();
                activitycount.put(resultSet.getString("ACTIVITYTYPEID"));
                activitycount.put(resultSet.getString("SUM"));
                array.put(activitycount);
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
        return array;
    }

    public JSONObject updateBranchActivity(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONObject json = new JSONObject();
        JSONArray Tarray = new JSONArray();
        JSONArray Rarray = new JSONArray();
        boolean ccount = false;
        boolean pcount = false;
        boolean vpcount = false;
        try {
            JSONArray array = new JSONArray(target.getDatatabledata());
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String sql = "UPDATE  AVN_BRANCHACTIVITYTARGET SET BCOUNT=? WHERE BATID=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("VALUE"));
                statement.setString(2, obj.getString("ID"));
                statement.executeUpdate();
            }

            String sql = "SELECT SUM(RAT.ACTIVITYCOUNY) as SUM ,AC.ACTIVITYTYPEID  "
                    + "                    FROM AVN_REGIONALACTIVITYTARGET RAT  "
                    + "                    INNER JOIN AVN_ACTIVITYTARGET AT  "
                    + "                    ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID  "
                    + "                    INNER JOIN AVN_ACTIVITYTYPES AC  "
                    + "                    ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID  "
                    + "                    WHERE RAT.TARGETID   =? and  RAT.REGIONID   =? "
                    + "                    GROUP BY AC.ACTIVITYTYPEID";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.setString(2, target.getRegionid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray activitycount = new JSONArray();
                activitycount.put(resultSet.getString("ACTIVITYTYPEID"));
                activitycount.put(resultSet.getString("SUM"));
                Tarray.put(activitycount);
            }

            sql = "SELECT SUM(BAT.BCOUNT) AS SUM , "
                    + "  AC.ACTIVITYTYPEID "
                    + "FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "INNER JOIN AVN_ACTIVITYTYPES AC "
                    + "ON AT.ACTIVITYTYPEID =AC.ACTIVITYTYPEID "
                    + "WHERE RAT.TARGETID   =? "
                    + "AND RAT.REGIONID     =? "
                    + "GROUP BY AC.ACTIVITYTYPEID";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.setString(2, target.getRegionid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONArray activitycount = new JSONArray();
                activitycount.put(resultSet.getString("ACTIVITYTYPEID"));
                activitycount.put(resultSet.getString("SUM"));
                Rarray.put(activitycount);
            }
            for (int i = 0; i < Tarray.length(); i++) {
                JSONArray arrayitem = Tarray.optJSONArray(i);
                for (int j = 0; j < Rarray.length(); j++) {
                    JSONArray remaningarrayitem = Rarray.optJSONArray(j);
                    if (arrayitem.getInt(0) == remaningarrayitem.getInt(0)) {
                        if (arrayitem.getInt(0) == 1) {
                            if (arrayitem.getInt(1) >= remaningarrayitem.getInt(1)) {
                                ccount = true;
                            }
                        } else if (arrayitem.getInt(0) == 2) {
                            if (arrayitem.getInt(1) >= remaningarrayitem.getInt(1)) {
                                vpcount = true;
                            }

                        } else {
                            if (arrayitem.getInt(1) >= remaningarrayitem.getInt(1)) {
                                pcount = true;
                            }
                        }
                    }
                }
            }

            if (ccount && vpcount && pcount) {
                connection.commit();
                json.put("status", true);
            } else {
                connection.rollback();
                json.put("status", false);
            }
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
        return json;

    }

    public List<TargetSetting> getSearchTableData(TargetSetting parameters, int minCount, int start) throws Exception {
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

            sql = sql.replace(":where", this.getWhereForSearch(parameters));
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, start + minCount);
            statement.setInt(2, start);
            resultSet = statement.executeQuery();

            list = new ArrayList<>();
            TargetSetting targetData;
            while (resultSet.next()) {
                targetData = new TargetSetting();
                targetData.setTargetid(resultSet.getString("TARGETID"));
                targetData.setTragetdes(resultSet.getString("DESCRIPTION"));
                targetData.setProductid(resultSet.getString("PRODUCTID"));
                targetData.setProductdes(resultSet.getString("PDES"));
                targetData.setRevenue(resultSet.getString("REVENUE"));
                targetData.setTargetperiodid(resultSet.getString("TARGETPERIODID"));
                targetData.setTargetperioddes(resultSet.getString("TPDES"));
                targetData.setTargetstartdate(resultSet.getString("TARGETSTARTDATE"));
                targetData.setTargetenddate(resultSet.getString("TARGETENDDATE"));
                list.add(targetData);
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

    public int getSearchTableDataCount(TargetSetting parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_TARGET TR  INNER JOIN AVN_TARGETPERIOD TP ON TR.TARGETPERIODID=TP.PERIODID INNER JOIN AVN_PRODUCT P ON TR.PRODUCTID=P.PRODUCTID :where ";
            sql = sql.replace(":where", this.getWhereForSearch(parameters));
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

    public int getTableDataCountInBranchGroupActivity(String branchactivityid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS CNT FROM AVN_BRANCHGROUPACTIVITYTARGET WHERE BRANCHACTIVITYTARGETID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, branchactivityid);
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

    public void insertBrnachGroupActivity(TargetSetting target, String username, List<TargetSetting> grouplist) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        connection = dataSource.getConnection();
        try {
            if (target.getBranchactivitytargetid() != "") {
                for (int k = 0; k < grouplist.size(); k++) {
                    TargetSetting grouplistdata = grouplist.get(k);
                    String sql = "INSERT INTO AVN_BRANCHGROUPACTIVITYTARGET (BRANCHGORUPID, BRANCHACTIVITYTARGETID,COUNT,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) "
                            + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, grouplistdata.getBranchgroupid());
                    statement.setString(2, target.getBranchactivitytargetid());
                    statement.setString(3, "0");
                    statement.setString(4, username);
                    statement.executeUpdate();
                }
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

    }

    public JSONObject updateBrnachGroupActibity(TargetSetting target, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        JSONArray memberlist = new JSONArray();
        int count = 0;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            JSONArray array = new JSONArray(target.getDatatabledata());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String sql = "UPDATE AVN_BRANCHGROUPACTIVITYTARGET SET COUNT=? WHERE BRANCHGORUPID=? AND BRANCHACTIVITYTARGETID=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("COUNT"));
                statement.setString(2, obj.getString("ID"));
                statement.setString(3, obj.getString("BATID"));
                statement.executeUpdate();

                if (obj.getInt("COUNT") > 0) {
                    sql = "SELECT COUNT(*) CNT  FROM AVN_BRANCHMEMBERACTIVITYTARGET WHERE BRANCHGROUPACTIVITYTARGETID=?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, obj.getString("BGATID"));
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        count = resultSet.getInt("CNT");
                    }

                    if (count == 0) {
                        sql = "SELECT BGMID FROM AVN_BRANCHGROUPMEMBER WHERE BRANCHGROUPID=?";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, obj.getString("ID"));
                        resultSet = statement.executeQuery();
                        while (resultSet.next()) {
                            memberlist.put(resultSet.getString("BGMID"));
                        }
                        for (int j = 0; j < memberlist.length(); j++) {
                            sql = "INSERT INTO AVN_BRANCHMEMBERACTIVITYTARGET (BRANCHGROUPACTIVITYTARGETID,BRANCHGROUPMEMBERID,COUNT,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) "
                                    + " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                            statement = connection.prepareStatement(sql);
                            statement.setString(1, obj.getString("BGATID"));
                            statement.setString(2, memberlist.getString(j));
                            statement.setString(3, "0");
                            statement.setString(4, username);
                            statement.executeUpdate();
                        }

                    }

                }

            }

            int sum = 0;
            String sql = "SELECT SUM(COUNT) AS BCOUNT FROM AVN_BRANCHGROUPACTIVITYTARGET WHERE BRANCHACTIVITYTARGETID=? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchactivitytargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("BCOUNT");
            }
            int amount = 0;
            sql = "SELECT BCOUNT  FROM AVN_BRANCHACTIVITYTARGET WHERE BATID=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getBranchactivitytargetid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("BCOUNT");
            }

            if (sum <= amount || sum == 0) {
                connection.commit();
                status = true;
                jason.put("status", status);
                jason.put("sum", sum);
                jason.put("remaning", (amount - sum));
            } else {
                connection.rollback();
            }

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
        return jason;
    }

    public JSONObject updatBranchMemberActivityCount(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        int count = 0;
        int amount = 0;
        int sum = 0;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            JSONArray array = new JSONArray(target.getDatatabledata());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String sql = "UPDATE  AVN_BRANCHMEMBERACTIVITYTARGET SET COUNT=? WHERE BRANCHGROUPACTIVITYTARGETID=? AND BRANCHGROUPMEMBERID=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("COUNT"));
                statement.setString(2, obj.getString("BGATID"));
                statement.setString(3, obj.getString("MEMBERID"));
                statement.executeUpdate();

                sql = "SELECT SUM(COUNT) AS BMCOUNT FROM AVN_BRANCHMEMBERACTIVITYTARGET WHERE BRANCHGROUPACTIVITYTARGETID=? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("BGATID"));
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    sum = resultSet.getInt("BMCOUNT");
                }

                sql = "SELECT COUNT FROM AVN_BRANCHGROUPACTIVITYTARGET WHERE BGATID=?";

                statement = connection.prepareStatement(sql);
                statement.setString(1, obj.getString("BGATID"));
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    amount = resultSet.getInt("COUNT");
                }

            }

            if (sum <= amount || sum == 0) {
                connection.commit();
                status = true;
                jason.put("status", status);
                jason.put("sum", sum);
                jason.put("remaning", (amount - sum));
            } else {
                connection.rollback();
            }

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
        return jason;

    }

    public JSONObject deleteAllTargetAmount(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE "
                    + "FROM AVN_BRANCHGROUMEMBERTARGET "
                    + "WHERE BGMTID IN "
                    + "  (SELECT BGMT.BGMTID "
                    + "  FROM AVN_BRANCHGROUMEMBERTARGET BGMT "
                    + "  INNER JOIN AVN_BRANCHGROUPMEMBER BGM "
                    + "  ON BGMT.BRANCHGROUPMEMBERID=BGM.BGMID "
                    + "  INNER JOIN AVN_EMPLOYEE E "
                    + "  ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + "  INNER JOIN AVN_BRANCHGROUPREVENUETARGET BGRT "
                    + "  ON BGMT.BRANCHGROUPREVENUETARGETID=BGRT.BGTID "
                    + "  INNER JOIN AVN_BRANCHTARGET BT "
                    + "  ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "  INNER JOIN AVN_REGIONALTARGET RT "
                    + "  ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "  WHERE RT.TARGETID     =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE  "
                    + "FROM AVN_BRANCHGROUPREVENUETARGET  "
                    + "WHERE BGTID IN  "
                    + "  (SELECT BGRT.BGTID  "
                    + "  FROM AVN_BRANCHGROUPREVENUETARGET BGRT  "
                    + "  INNER JOIN AVN_BRANCHTARGET BT  "
                    + "  ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID  "
                    + "  INNER JOIN AVN_REGIONALTARGET RT  "
                    + "  ON BT.REGIONALTARGETID=RT.REGIONALTARGETID  "
                    + "  WHERE RT.TARGETID     =?  "
                    + "  ) ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE "
                    + "FROM AVN_BRANCHTARGET "
                    + "WHERE BRANCHTARGETID IN "
                    + "  (SELECT BT.BRANCHTARGETID "
                    + "  FROM AVN_BRANCHTARGET BT "
                    + "  INNER JOIN AVN_REGIONALTARGET RT "
                    + "  ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "  INNER JOIN AVN_BRANCH B "
                    + "  ON BT.BRANCHID   =B.BRANCHID "
                    + "  WHERE RT.TARGETID=? "
                    + "  )";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE "
                    + "FROM AVN_REGIONALTARGET "
                    + "WHERE REGIONALTARGETID IN "
                    + "  (SELECT RT.REGIONALTARGETID "
                    + "  FROM AVN_REGIONALTARGET RT "
                    + "  INNER JOIN AVN_BRANCH RE "
                    + "  ON RT.REGIONID   =RE.BRANCHID "
                    + "  WHERE RT.TARGETID=? "
                    + "  )";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            connection.commit();
            status = true;
            jason.put("status", status);

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
        return jason;
    }

    public JSONObject deleteAllTargetAfterSecondTab(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE "
                    + "FROM AVN_BRANCHGROUMEMBERTARGET "
                    + "WHERE BGMTID IN "
                    + "  (SELECT BGMT.BGMTID "
                    + "  FROM AVN_BRANCHGROUMEMBERTARGET BGMT "
                    + "  INNER JOIN AVN_BRANCHGROUPMEMBER BGM "
                    + "  ON BGMT.BRANCHGROUPMEMBERID=BGM.BGMID "
                    + "  INNER JOIN AVN_EMPLOYEE E "
                    + "  ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + "  INNER JOIN AVN_BRANCHGROUPREVENUETARGET BGRT "
                    + "  ON BGMT.BRANCHGROUPREVENUETARGETID=BGRT.BGTID "
                    + "  INNER JOIN AVN_BRANCHTARGET BT "
                    + "  ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "  INNER JOIN AVN_REGIONALTARGET RT "
                    + "  ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "  WHERE RT.TARGETID     =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE  "
                    + "FROM AVN_BRANCHGROUPREVENUETARGET  "
                    + "WHERE BGTID IN  "
                    + "  (SELECT BGRT.BGTID  "
                    + "  FROM AVN_BRANCHGROUPREVENUETARGET BGRT  "
                    + "  INNER JOIN AVN_BRANCHTARGET BT  "
                    + "  ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID  "
                    + "  INNER JOIN AVN_REGIONALTARGET RT  "
                    + "  ON BT.REGIONALTARGETID=RT.REGIONALTARGETID  "
                    + "  WHERE RT.TARGETID     =?  "
                    + "  ) ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE "
                    + "FROM AVN_BRANCHTARGET "
                    + "WHERE BRANCHTARGETID IN "
                    + "  (SELECT BT.BRANCHTARGETID "
                    + "  FROM AVN_BRANCHTARGET BT "
                    + "  INNER JOIN AVN_REGIONALTARGET RT "
                    + "  ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "  INNER JOIN AVN_BRANCH B "
                    + "  ON BT.BRANCHID   =B.BRANCHID "
                    + "  WHERE RT.TARGETID=? "
                    + "  )";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            connection.commit();
            status = true;
            jason.put("status", status);

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
        return jason;
    }

    public JSONObject deleteAllTargetAfterThirdTab(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE "
                    + "FROM AVN_BRANCHGROUMEMBERTARGET "
                    + "WHERE BGMTID IN "
                    + "  (SELECT BGMT.BGMTID "
                    + "  FROM AVN_BRANCHGROUMEMBERTARGET BGMT "
                    + "  INNER JOIN AVN_BRANCHGROUPMEMBER BGM "
                    + "  ON BGMT.BRANCHGROUPMEMBERID=BGM.BGMID "
                    + "  INNER JOIN AVN_EMPLOYEE E "
                    + "  ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + "  INNER JOIN AVN_BRANCHGROUPREVENUETARGET BGRT "
                    + "  ON BGMT.BRANCHGROUPREVENUETARGETID=BGRT.BGTID "
                    + "  INNER JOIN AVN_BRANCHTARGET BT "
                    + "  ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "  INNER JOIN AVN_REGIONALTARGET RT "
                    + "  ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "  WHERE RT.TARGETID     =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE  "
                    + "FROM AVN_BRANCHGROUPREVENUETARGET  "
                    + "WHERE BGTID IN  "
                    + "  (SELECT BGRT.BGTID  "
                    + "  FROM AVN_BRANCHGROUPREVENUETARGET BGRT  "
                    + "  INNER JOIN AVN_BRANCHTARGET BT  "
                    + "  ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID  "
                    + "  INNER JOIN AVN_REGIONALTARGET RT  "
                    + "  ON BT.REGIONALTARGETID=RT.REGIONALTARGETID  "
                    + "  WHERE RT.TARGETID     =?  "
                    + "  ) ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();
            connection.commit();
            status = true;
            jason.put("status", status);

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
        return jason;
    }

    public JSONObject deleteAllTargetAfterFourrhTabFirstPart(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE "
                    + "FROM AVN_BRANCHGROUMEMBERTARGET "
                    + "WHERE BGMTID IN "
                    + "  (SELECT BGMT.BGMTID "
                    + "  FROM AVN_BRANCHGROUMEMBERTARGET BGMT "
                    + "  INNER JOIN AVN_BRANCHGROUPMEMBER BGM "
                    + "  ON BGMT.BRANCHGROUPMEMBERID=BGM.BGMID "
                    + "  INNER JOIN AVN_EMPLOYEE E "
                    + "  ON BGM.EMPLOYEEID=E.EMPLOYEEID "
                    + "  INNER JOIN AVN_BRANCHGROUPREVENUETARGET BGRT "
                    + "  ON BGMT.BRANCHGROUPREVENUETARGETID=BGRT.BGTID "
                    + "  INNER JOIN AVN_BRANCHTARGET BT "
                    + "  ON BGRT.BRANCHTARGETID=BT.BRANCHTARGETID "
                    + "  INNER JOIN AVN_REGIONALTARGET RT "
                    + "  ON BT.REGIONALTARGETID=RT.REGIONALTARGETID "
                    + "  WHERE RT.TARGETID     =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            status = true;
            jason.put("status", status);

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
        return jason;
    }

    public JSONObject deleteAllActivityCount(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE "
                    + "FROM AVN_BRANCHMEMBERACTIVITYTARGET "
                    + "WHERE BMATID IN "
                    + "  (SELECT BGMAT.BMATID "
                    + "  FROM AVN_BRANCHMEMBERACTIVITYTARGET BGMAT "
                    + "  INNER JOIN AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "  ON BGMAT.BRANCHGROUPACTIVITYTARGETID=BGAT.BGATID "
                    + "  INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "  ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  WHERE RAT.TARGETID           =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE "
                    + "FROM AVN_BRANCHGROUPACTIVITYTARGET "
                    + "WHERE BGATID IN "
                    + "  (SELECT BGAT.BGATID "
                    + "  FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "  INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "  ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  WHERE RAT.TARGETID           =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE "
                    + "FROM AVN_BRANCHACTIVITYTARGET "
                    + "WHERE BATID IN "
                    + "  (SELECT BAT.BATID "
                    + "  FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  WHERE RAT.TARGETID=? "
                    + "  )";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE "
                    + "FROM AVN_REGIONALACTIVITYTARGET "
                    + "WHERE ID IN "
                    + "  (SELECT ID FROM AVN_REGIONALACTIVITYTARGET RAT WHERE RAT.TARGETID=? "
                    + "  )";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            connection.commit();
            status = true;
            jason.put("status", status);

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
        return jason;
    }

    public JSONObject deleteAllActivityCountAfterSecondTab(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE "
                    + "FROM AVN_BRANCHMEMBERACTIVITYTARGET "
                    + "WHERE BMATID IN "
                    + "  (SELECT BGMAT.BMATID "
                    + "  FROM AVN_BRANCHMEMBERACTIVITYTARGET BGMAT "
                    + "  INNER JOIN AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "  ON BGMAT.BRANCHGROUPACTIVITYTARGETID=BGAT.BGATID "
                    + "  INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "  ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  WHERE RAT.TARGETID           =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE "
                    + "FROM AVN_BRANCHGROUPACTIVITYTARGET "
                    + "WHERE BGATID IN "
                    + "  (SELECT BGAT.BGATID "
                    + "  FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "  INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "  ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  WHERE RAT.TARGETID           =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE "
                    + "FROM AVN_BRANCHACTIVITYTARGET "
                    + "WHERE BATID IN "
                    + "  (SELECT BAT.BATID "
                    + "  FROM AVN_BRANCHACTIVITYTARGET BAT "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  INNER JOIN AVN_ACTIVITYTARGET AT "
                    + "  ON RAT.RACTIVITYID=AT.ACTIVITYTARGETID "
                    + "  WHERE RAT.TARGETID=? "
                    + "  )";

            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            connection.commit();
            status = true;
            jason.put("status", status);

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
        return jason;
    }

    public JSONObject deleteAllActivityCountAfterThirdTab(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE "
                    + "FROM AVN_BRANCHMEMBERACTIVITYTARGET "
                    + "WHERE BMATID IN "
                    + "  (SELECT BGMAT.BMATID "
                    + "  FROM AVN_BRANCHMEMBERACTIVITYTARGET BGMAT "
                    + "  INNER JOIN AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "  ON BGMAT.BRANCHGROUPACTIVITYTARGETID=BGAT.BGATID "
                    + "  INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "  ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  WHERE RAT.TARGETID           =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            sql = "DELETE "
                    + "FROM AVN_BRANCHGROUPACTIVITYTARGET "
                    + "WHERE BGATID IN "
                    + "  (SELECT BGAT.BGATID "
                    + "  FROM AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "  INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "  ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  WHERE RAT.TARGETID           =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            connection.commit();
            status = true;
            jason.put("status", status);

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
        return jason;
    }

    public JSONObject deleteAllActivityCountAfterFourrhTabFirstPart(TargetSetting target) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        JSONObject jason = new JSONObject();
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE "
                    + "FROM AVN_BRANCHMEMBERACTIVITYTARGET "
                    + "WHERE BMATID IN "
                    + "  (SELECT BGMAT.BMATID "
                    + "  FROM AVN_BRANCHMEMBERACTIVITYTARGET BGMAT "
                    + "  INNER JOIN AVN_BRANCHGROUPACTIVITYTARGET BGAT "
                    + "  ON BGMAT.BRANCHGROUPACTIVITYTARGETID=BGAT.BGATID "
                    + "  INNER JOIN AVN_BRANCHACTIVITYTARGET BAT "
                    + "  ON BGAT.BRANCHACTIVITYTARGETID=BAT.BATID "
                    + "  INNER JOIN AVN_REGIONALACTIVITYTARGET RAT "
                    + "  ON BAT.REGIONACTIVITYTARGETID=RAT.ID "
                    + "  WHERE RAT.TARGETID           =? "
                    + "  )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, target.getTargetid());
            statement.executeUpdate();

            connection.commit();
            status = true;
            jason.put("status", status);

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
        return jason;
    }

}

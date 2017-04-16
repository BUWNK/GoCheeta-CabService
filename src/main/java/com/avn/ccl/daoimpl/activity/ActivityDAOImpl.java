/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.activity;

import com.avn.ccl.dao.activity.ActivityDAO;
import com.avn.ccl.model.activity.Activity;
import com.avn.ccl.model.contact.Contact;
import com.avn.ccl.model.lead.Lead;
import com.avn.ccl.model.target.Target;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.DateTime;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SameeraPJ
 */
@Repository("activityDAO")
public class ActivityDAOImpl implements ActivityDAO {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Activity> getActivity(Activity activity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Activity> Activitylist = null;
        try {
            String sql = "SELECT AVN_ACTIVITY.ACTIVITYDATETIME,AVN_ACTIVITY.DESCRIPTION,"
                    + "AVN_ACTIVITY.LEADID ,AVN_ACTIVITY.CREATEDUSER,AVN_ACTIVITY.SPENTTIME,"
                    + "AVN_ACTIVITY.ACTIVITYID ,AVN_ACTIVITYTYPES.ACTIVITYTYPEDESCRIPTION,"
                    + "AVN_ACTIVITYTYPES.ACTIVITYTYPEID FROM AVN_ACTIVITY INNER JOIN "
                    + "AVN_ACTIVITYTYPES ON AVN_ACTIVITY.ACTIVITYTYPEID=AVN_ACTIVITYTYPES."
                    + "ACTIVITYTYPEID WHERE AVN_ACTIVITY.LEADID = ? AND AVN_ACTIVITY.ACTIVITYSTATUS = ? order by AVN_ACTIVITY.ACTIVITYDATETIME desc";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, activity.getLeadid());
            statement.setInt(2, CommonVarList.COMPLETED_ACTIVITIES);
            resultSet = statement.executeQuery();
            Activitylist = new ArrayList<>();
            while (resultSet.next()) {
                activity = new Activity();
                activity.setActivitytypedescription(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                activity.setActivityid(resultSet.getString("ACTIVITYID"));
                activity.setDescription(resultSet.getString("DESCRIPTION"));
                activity.setLeadid(resultSet.getInt("LEADID"));
                activity.setSpenttime(resultSet.getString("SPENTTIME"));
                activity.setActivitytime(DateTime.getDateTimeWithoutSeconds(resultSet.getTimestamp("ACTIVITYDATETIME")));
                activity.setCreateduser(resultSet.getString("CREATEDUSER"));
                activity.setActivitytypeid(resultSet.getString("ACTIVITYTYPEID"));
                Activitylist.add(activity);
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
        return Activitylist;
    }

    public Map<String, String> getLeadDropDownList(String createUser) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> leadList = null;
        try {

            String sql = "SELECT AVN_LEAD.LEADID,AVN_CONTACTS.NAMEINFULL "
                    + "FROM AVN_CONTACTS "
                    + "INNER JOIN AVN_LEAD "
                    + "ON AVN_LEAD.CONTACTID=AVN_CONTACTS.CONTACTID WHERE AVN_LEAD.CREATEDUSER=? ORDER BY AVN_LEAD.LEADID ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, createUser);
            resultSet = statement.executeQuery();
            leadList = new LinkedHashMap<>();
            leadList.put("0", "ALL");
            while (resultSet.next()) {
                leadList.put(resultSet.getString("LEADID"), resultSet.getString("NAMEINFULL"));
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
        return leadList;
    }

    //This for contact label drop down,but this remove so new requrement
    @Override
    public List<Lead> getLeadDropdown(String createUser) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Lead> Leadlistdropdown = null;
        try {
            String sql = "SELECT NVL(substr(AVN_CONTACTS.NAMEINFULL, 1, instr(AVN_CONTACTS.NAMEINFULL,' ')), AVN_CONTACTS.NAMEINFULL) AS NAMEINFULL,AVN_LEAD.LEADID,AVN_LEAD.LEADAMOUNT,AVN_PRODUCT.DESCRIPTION "
                    + "FROM AVN_CONTACTS "
                    + "INNER JOIN AVN_LEAD ON AVN_LEAD.CONTACTID=AVN_CONTACTS.CONTACTID "
                    + "INNER JOIN AVN_PRODUCT ON AVN_PRODUCT.PRODUCTID=AVN_LEAD.PRODUCTID "
                    + "WHERE AVN_LEAD.CREATEDUSER=? "
                    + "ORDER BY AVN_CONTACTS.NAMEINFULL ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, createUser);
            resultSet = statement.executeQuery();
            Leadlistdropdown = new ArrayList<>();
            Lead lead;

            while (resultSet.next()) {
                lead = new Lead();
                lead.setLeadid(resultSet.getInt("LEADID"));
                lead.setNameinFull(resultSet.getString("NAMEINFULL"));
                lead.setLeadamount(resultSet.getBigDecimal("LEADAMOUNT"));
                lead.setDescription(resultSet.getString("DESCRIPTION"));

                Leadlistdropdown.add(lead);
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
        return Leadlistdropdown;
    }

    //for contact dropdown
    @Override
    public List<Lead> getContactDropdown(String createUser) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Lead> Contactdropdown = null;
        try {
            String sql = "SELECT AVN_CONTACTS.NAMEINFULL,AVN_CONTACTS.CONTACTID "
                    + "FROM AVN_CONTACTS "
                    + "WHERE AVN_CONTACTS.CREATEDUSER=? "
                    + "ORDER BY AVN_CONTACTS.NAMEINFULL ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, createUser);
            resultSet = statement.executeQuery();
            Contactdropdown = new ArrayList<>();
            Lead lead;

            while (resultSet.next()) {
                lead = new Lead();
                lead.setContactid(resultSet.getInt("CONTACTID"));
                lead.setNameinFull(resultSet.getString("NAMEINFULL"));

                Contactdropdown.add(lead);
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
        return Contactdropdown;

    }

    public int getContactid(int leadid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int contactid = 0;
        try {
            String sql = "SELECT CONTACTID FROM AVN_LEAD WHERE LEADID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, leadid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contactid = resultSet.getInt("CONTACTID");
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

        return contactid;
    }

    @Override
    public List<Lead> getLead(int contactid) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Lead> leaddropList = null;
        try {
            String sql = "SELECT AVN_LEAD.LEADAMOUNT,AVN_LEAD.LEADID,AVN_PRODUCT.DESCRIPTION "
                    + "FROM AVN_LEAD "
                    + "INNER JOIN AVN_PRODUCT ON AVN_PRODUCT.PRODUCTID=AVN_LEAD.PRODUCTID "
                    + "WHERE AVN_LEAD.CONTACTID=?";
//                    + "ORDER BY AVN_CONTACTS.NAMEINFULL ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, contactid);
            resultSet = statement.executeQuery();
            leaddropList = new ArrayList<>();
            Lead lead;

            while (resultSet.next()) {
                lead = new Lead();
                lead.setLeadamount(resultSet.getBigDecimal("LEADAMOUNT"));
                lead.setDescription(resultSet.getString("DESCRIPTION"));
                lead.setLeadid(resultSet.getInt("LEADID"));

                leaddropList.add(lead);
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
        return leaddropList;
    }

    @Override
    public List<Lead> getLeadDropdownAjent(String createUser) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Lead> Leadlistdropdown = null;
        try {
            String sql = "SELECT NVL(substr(AVN_CONTACTS.NAMEINFULL, 1, instr(AVN_CONTACTS.NAMEINFULL,' ')), AVN_CONTACTS.NAMEINFULL) AS NAMEINFULL,AVN_LEAD.LEADID,AVN_LEAD.LEADAMOUNT,AVN_PRODUCT.DESCRIPTION "
                    + "FROM AVN_CONTACTS "
                    + "INNER JOIN AVN_LEAD ON AVN_LEAD.CONTACTID=AVN_CONTACTS.CONTACTID "
                    + "INNER JOIN AVN_PRODUCT ON AVN_PRODUCT.PRODUCTID=AVN_LEAD.PRODUCTID "
                    + "WHERE AVN_LEAD.CREATEDUSER=? "
                    + "ORDER BY AVN_CONTACTS.NAMEINFULL ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, createUser);
            resultSet = statement.executeQuery();
            Leadlistdropdown = new ArrayList<>();
            Lead lead;

            while (resultSet.next()) {
                lead = new Lead();
                lead.setLeadid(resultSet.getInt("LEADID"));
                lead.setNameinFull(resultSet.getString("NAMEINFULL"));
                lead.setLeadamount(resultSet.getBigDecimal("LEADAMOUNT"));
                lead.setDescription(resultSet.getString("DESCRIPTION"));

                Leadlistdropdown.add(lead);
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
        return Leadlistdropdown;
    }

    public String getcreateUser(int leadid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String createUser = "";
        try {
            String sql = "SELECT CREATEDUSER FROM AVN_LEAD WHERE LEADID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, leadid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                createUser = resultSet.getString("CREATEDUSER");
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

        return createUser;
    }

    @Override
    public List<Activity> getType() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Activity> Activitytype = null;
        try {
            String sql = "SELECT ACTIVITYTYPEDESCRIPTION,ACTIVITYTYPEID FROM AVN_ACTIVITYTYPES";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            Activitytype = new ArrayList<>();
            Activity activity2;
            while (resultSet.next()) {
                activity2 = new Activity();
                activity2.setActivitytype(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                activity2.setActivitytypeid(resultSet.getString("ACTIVITYTYPEID"));

                Activitytype.add(activity2);
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
        return Activitytype;
    }

    @Override
    public void insertActivity(Activity activity) throws SQLException {
        java.util.Date date = new java.util.Date();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO AVN_ACTIVITY (DESCRIPTION,LEADID,ACTIVITYTYPEID,ACTIVITYDATETIME,CREATEDUSER,CREATEDDATETIME,ACTIVITYSTATUS) VALUES (?,?,?,?,?,?,?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, activity.getActivitytypedescription());
            statement.setInt(2, activity.getLeadid());
            statement.setString(3, activity.getActivitytype());
            statement.setTimestamp(4, Common.getTimestampFromDateAndTime("yyyy-MM-dd hh:mm", activity.getActivitytime()));
            statement.setString(5, activity.getCreateduser());
            statement.setTimestamp(6, new Timestamp(date.getTime()));
            statement.setInt(7, CommonVarList.NOT_COMPLETED_ACTIVITIES);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw sqle;
        } catch (ParseException ex) {
            Logger.getLogger(ActivityDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

    public List<Activity> getActivityCreateUser(String ajent) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Activity> Activitylist = null;
        try {
            String sql = "SELECT AVN_ACTIVITY.ACTIVITYDATETIME,AVN_ACTIVITY.ACTIVITYTYPEID,AVN_ACTIVITY.DESCRIPTION,AVN_ACTIVITY.LEADID ,AVN_ACTIVITY.CREATEDUSER,AVN_ACTIVITY.SPENTTIME,AVN_ACTIVITY.ACTIVITYID ,AVN_ACTIVITYTYPES.ACTIVITYTYPEDESCRIPTION FROM AVN_ACTIVITY INNER JOIN AVN_ACTIVITYTYPES ON AVN_ACTIVITY.ACTIVITYTYPEID=AVN_ACTIVITYTYPES.ACTIVITYTYPEID WHERE AVN_ACTIVITY.CREATEDUSER = ? AND AVN_ACTIVITY.ACTIVITYSTATUS = ? order by AVN_ACTIVITY.ACTIVITYDATETIME desc";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, ajent);
            statement.setInt(2, CommonVarList.COMPLETED_ACTIVITIES);
            resultSet = statement.executeQuery();
            Activitylist = new ArrayList<>();
            Activity activity;
            while (resultSet.next()) {
                activity = new Activity();
                activity.setActivitytypedescription(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                activity.setActivityid(resultSet.getString("ACTIVITYID"));
                activity.setDescription(resultSet.getString("DESCRIPTION"));
                activity.setLeadid(resultSet.getInt("LEADID"));
                activity.setSpenttime(resultSet.getString("SPENTTIME"));
                activity.setActivitytime(resultSet.getString("ACTIVITYDATETIME"));
                activity.setCreateduser(resultSet.getString("CREATEDUSER"));
                activity.setActivitytypeid(resultSet.getString("ACTIVITYTYPEID"));

                Activitylist.add(activity);
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
        return Activitylist;
    }

    //when lead id=0 load data for activity for activity page according to contactid
    public List<Activity> getActivityContactid(int contactId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Activity> Activitylist = null;
        try {
            String sql = "SELECT AVN_ACTIVITY.ACTIVITYDATETIME,AVN_ACTIVITY.ACTIVITYTYPEID,AVN_ACTIVITY.DESCRIPTION,AVN_ACTIVITY.LEADID ,AVN_ACTIVITY.CREATEDUSER,AVN_ACTIVITY.SPENTTIME,AVN_ACTIVITY.ACTIVITYID ,AVN_ACTIVITYTYPES.ACTIVITYTYPEDESCRIPTION FROM AVN_ACTIVITY INNER JOIN AVN_ACTIVITYTYPES ON AVN_ACTIVITY.ACTIVITYTYPEID=AVN_ACTIVITYTYPES.ACTIVITYTYPEID INNER JOIN AVN_LEAD ON AVN_ACTIVITY.LEADID=AVN_LEAD.LEADID WHERE AVN_LEAD.CONTACTID = ? AND AVN_ACTIVITY.ACTIVITYSTATUS = ? order by AVN_ACTIVITY.ACTIVITYDATETIME desc";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, contactId);
            statement.setInt(2, CommonVarList.COMPLETED_ACTIVITIES);
            resultSet = statement.executeQuery();
            Activitylist = new ArrayList<>();
            Activity activity;
            while (resultSet.next()) {
                activity = new Activity();
                activity.setActivitytypedescription(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                activity.setActivityid(resultSet.getString("ACTIVITYID"));
                activity.setDescription(resultSet.getString("DESCRIPTION"));
                activity.setLeadid(resultSet.getInt("LEADID"));
                activity.setSpenttime(resultSet.getString("SPENTTIME"));
                activity.setActivitytime(resultSet.getString("ACTIVITYDATETIME"));
                activity.setCreateduser(resultSet.getString("CREATEDUSER"));
                activity.setActivitytypeid(resultSet.getString("ACTIVITYTYPEID"));

                Activitylist.add(activity);
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
        return Activitylist;
    }

    public List<Activity> getActivityUpdate(int activityId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Activity> Activitylist = null;
        try {
            String sql = "SELECT ACTIVITYDATETIME,DESCRIPTION ,ACTIVITYTYPEID, "
                    + "SPENTTIME,ACTIVITYID  FROM AVN_ACTIVITY "
                    + "WHERE AVN_ACTIVITY.ACTIVITYID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, activityId);
            resultSet = statement.executeQuery();
            Activitylist = new ArrayList<>();
            Activity activity;
            while (resultSet.next()) {
                activity = new Activity();
                activity.setActivitytime(resultSet.getString("ACTIVITYDATETIME"));
                activity.setDescription(resultSet.getString("DESCRIPTION"));
                activity.setActivitytypeid(resultSet.getString("ACTIVITYTYPEID"));
                if (resultSet.getString("SPENTTIME") == null) {
                    activity.setSpenttime("00/00");
                } else {
                    activity.setSpenttime(resultSet.getString("SPENTTIME"));
                }
                activity.setActivityid(resultSet.getString("ACTIVITYID"));
                Activitylist.add(activity);
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
        return Activitylist;
    }

    @Override
    public long getActivityCountByActivityTypeForTarget(int activityTypeId, Target target) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long count = 0;
        try {
            String query = "SELECT COUNT(*) AS CNT "
                    + "FROM AVN_ACTIVITY "
                    + "WHERE ACTIVITYTYPEID = ? "
                    + "AND LEADID IN (SELECT LEADID FROM AVN_LEAD WHERE PRODUCTID = ?) "
                    + "AND ACTIVITYSTATUS = ? "
                    + "AND ACTIVITYDATETIME BETWEEN ? AND ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, activityTypeId);
            statement.setInt(2, target.getProductid());
            statement.setInt(3, MasterDataVarList.AFFINITI_CODE_STATUS_ACTIVITY_COMPLEATED);
            statement.setTimestamp(4, new Timestamp(target.getTargetstartdate().getTime()));
            statement.setTimestamp(5, new Timestamp(target.getTargetenddate().getTime()));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getLong("CNT");
            }
        } catch (SQLException sqle) {
            throw sqle;
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
    public long getActivityCountByActivityTypeTargetDateRange(int activityTypeId, Target target, Date[] range) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long count = 0;
        try {
            String query = "SELECT COUNT(*) AS CNT "
                    + "FROM AVN_ACTIVITY "
                    + "WHERE ACTIVITYTYPEID = ? "
                    + "AND LEADID IN (SELECT LEADID FROM AVN_LEAD WHERE PRODUCTID = ?) "
                    + "AND ACTIVITYSTATUS = ? "
                    + "AND ACTIVITYDATETIME BETWEEN ? AND ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, activityTypeId);
            statement.setInt(2, target.getProductid());
            statement.setInt(3, MasterDataVarList.AFFINITI_CODE_STATUS_ACTIVITY_COMPLEATED);
            statement.setTimestamp(4, new Timestamp(range[0].getTime()));
            statement.setTimestamp(5, new Timestamp(range[1].getTime()));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getLong("CNT");
            }
        } catch (SQLException sqle) {
            throw sqle;
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
    public void setActivityUpdate(Activity activity) throws SQLException {
        java.util.Date date = new java.util.Date();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE AVN_ACTIVITY  SET DESCRIPTION= ? ,ACTIVITYTYPEID = ?, SPENTTIME = ?,"
                    + "ACTIVITYDATETIME = ?,CREATEDUSER = ?,CREATEDDATETIME = ?,ACTIVITYSTATUS = ?  WHERE   ACTIVITYID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, activity.getActivitytypedescription());
            statement.setString(2, activity.getActivitytypeid());
            statement.setString(3, activity.getSpenttime());
            String act_date = activity.getActivitytime();
            if (act_date == null || act_date.equals("")) {
                statement.setTimestamp(4, null);
            } else {
                statement.setTimestamp(4, Common.getTimestampFromDateAndTime("yyyy-MM-dd hh:mm", act_date));
            }
            statement.setString(5, activity.getCreateduser());
            statement.setTimestamp(6, new Timestamp(date.getTime()));
            statement.setInt(7, CommonVarList.COMPLETED_ACTIVITIES);
            statement.setString(8, activity.getActivityid());
            statement.executeUpdate();
        } catch (SQLException sqle) {
            throw sqle;
        } catch (ParseException ex) {
            Logger.getLogger(ActivityDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
    public List<Contact> getContact(int leadid) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Contact> Contactlist = null;
        try {
            String sql = "SELECT AVN_CONTACTS.NAMEINFULL,AVN_CONTACTS.JOBTITLE,AVN_TITLE.DESCRIPTION FROM AVN_CONTACTS INNER JOIN AVN_LEAD ON AVN_CONTACTS.CONTACTID=AVN_LEAD.CONTACTID INNER JOIN AVN_TITLE ON AVN_TITLE.TITLECODE=AVN_CONTACTS.TITLE WHERE AVN_LEAD.LEADID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, leadid);
            resultSet = statement.executeQuery();
            Contactlist = new ArrayList<>();
            Contact contactinfo;
            while (resultSet.next()) {
                contactinfo = new Contact();
                contactinfo.setNameInFull(resultSet.getString("NAMEINFULL"));
                contactinfo.setJobtitle(resultSet.getString("JOBTITLE"));
                contactinfo.setTitle(resultSet.getString("DESCRIPTION"));
                Contactlist.add(contactinfo);
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
        return Contactlist;
    }

    @Override
    public Activity getActivityById(long activityid) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Activity activity = null;
        try {
            String sql = "SELECT ACTIVITYID, "
                    + "DESCRIPTION, "
                    + "LEADID, "
                    + "ACTIVITYTYPEID, "
                    + "SPENTTIME, "
                    + "ACTIVITYDATETIME, "
                    + "ACTIVITYSTATUS "
                    + "FROM AVN_ACTIVITY "
                    + "WHERE ACTIVITYID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, activityid);
            resultSet = statement.executeQuery();
            activity = new Activity();
            while (resultSet.next()) {
                activity.setActivityid(resultSet.getString("ACTIVITYID"));
                activity.setDescription(resultSet.getString("DESCRIPTION"));
                activity.setLeadid(resultSet.getInt("LEADID"));
                activity.setActivitytypeid(resultSet.getString("ACTIVITYTYPEID"));
                activity.setSpenttime(resultSet.getString("SPENTTIME"));
                activity.setActivitydatetime(resultSet.getDate("ACTIVITYDATETIME"));
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
        return activity;
    }

}

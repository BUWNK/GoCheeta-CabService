/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.calender;

import com.avn.ccl.dao.calender.CalenderDAO;
import com.avn.ccl.model.activity.Activity;
import com.avn.ccl.model.event.Event;
import com.avn.ccl.model.event.eventDetail;
import com.avn.ccl.util.DateTime;
import com.avn.ccl.util.varlist.CommonVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author SameeraPJ
 */
public class CalenderDAOImpl implements CalenderDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Activity> getActivity(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Activity> Activitylist = null;
        try {
            String sql = "SELECT activity.ACTIVITYDATETIME,activity.DESCRIPTION ,activity.CREATEDUSER,activity.SPENTTIME,activity.ACTIVITYID ,activitytypes.ACTIVITYTYPEDESCRIPTION FROM activity INNER JOIN activitytypes ON activity.ACTIVITYTYPEID=activitytypes.ACTIVITYTYPEID WHERE activity.LEADID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Activitylist = new ArrayList<>();
            Activity activity;
            while (resultSet.next()) {
                activity = new Activity();
                activity.setActivitytypedescription(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                activity.setActivityid(resultSet.getString("ACTIVITYID"));
                activity.setDescription(resultSet.getString("DESCRIPTION"));
                activity.setSpenttime(resultSet.getString("SPENTTIME"));
                activity.setActivitytime(resultSet.getString("ACTIVITYDATETIME"));
                activity.setCreateduser(resultSet.getString("CREATEDUSER"));

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

    public List<eventDetail> getEventDetail(int activityId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<eventDetail> eventDetailList = null;
        try {
            String sql = "SELECT TIT.DESCRIPTION,CON.NAMEINFULL,CON.JOBTITLE,CON.MOBILE,LED.LEADAMOUNT,PRO.DESCRIPTION  "
                    + "AS PRODUCTDESCRIPTION,RAS.ACTIVITYTYPEDESCRIPTION, "
                    + "ACT.DESCRIPTION AS ACTDESCRIPTION,ACT.ACTIVITYDATETIME "
                    + "FROM activity ACT "
                    + "INNER JOIN activitytypes RAS  "
                    + "    ON RAS.ACTIVITYTYPEID=ACT.ACTIVITYTYPEID "
                    + "INNER JOIN lead LED  "
                    + "    ON ACT.LEADID=LED.LEADID "
                    + "INNER JOIN contacts CON "
                    + "    ON CON.CONTACTID=LED.CONTACTID  "
                    + "INNER JOIN product PRO "
                    + "    ON PRO.PRODUCTID = LED.PRODUCTID "
                    + "INNER JOIN title TIT "
                    + "    ON TIT.TITLECODE=CON.TITLE WHERE ACT.ACTIVITYID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, activityId);
            resultSet = statement.executeQuery();
            eventDetailList = new ArrayList<>();
            eventDetail eventDetail;
            while (resultSet.next()) {
                eventDetail = new eventDetail();
                eventDetail.setTitle(resultSet.getString("DESCRIPTION"));
                eventDetail.setNameInFull(resultSet.getString("NAMEINFULL"));
                eventDetail.setJobTitle(resultSet.getString("JOBTITLE"));
                eventDetail.setMobile(resultSet.getString("MOBILE"));
                eventDetail.setLeadAmount(resultSet.getBigDecimal("LEADAMOUNT"));
                eventDetail.setProductdescription(resultSet.getString("PRODUCTDESCRIPTION"));
                eventDetail.setActivityTypeDecription(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                eventDetail.setActivityDecription(resultSet.getString("ACTDESCRIPTION"));
                eventDetail.setActivityTime(DateTime.getDateTimeWithoutSeconds(resultSet.getTimestamp("ACTIVITYDATETIME")));
                eventDetail.setActivityId("" + activityId);

                eventDetailList.add(eventDetail);
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
        return eventDetailList;
    }

    @Override
    public List<Event> getEvent(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Event> eventList = null;
        try {
            String sql = "SELECT activity.ACTIVITYSTATUS,activity.ACTIVITYDATETIME,activitytypes.ACTIVITYTYPEDESCRIPTION,activity.ACTIVITYID FROM activity INNER JOIN activitytypes ON activity.ACTIVITYTYPEID=activitytypes.ACTIVITYTYPEID WHERE activity.LEADID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            eventList = new ArrayList<>();
            Event event;
            while (resultSet.next()) {
                event = new Event();
                event.setTitle(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                event.setStart(resultSet.getString("ACTIVITYDATETIME"));
                event.setId(resultSet.getString("ACTIVITYID"));
                if (resultSet.getInt("ACTIVITYSTATUS") == CommonVarList.COMPLETED_ACTIVITIES) {
                    event.setColor("green");
                } else if (resultSet.getInt("ACTIVITYSTATUS") == CommonVarList.NOT_COMPLETED_ACTIVITIES) {
                    if (new Date().after(resultSet.getDate("ACTIVITYDATETIME"))) {
                        event.setColor("red");
                    } else {
                        event.setColor("blue");
                    }
                }
//                event.setColor("#008000");
                eventList.add(event);
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
        return eventList;
    }

//    public List<Event> getFutureEventsByLeadId(int id) throws SQLException {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        List<Event> eventList = null;
//        try {
//            String sql = "SELECT AVN_ACTIVITYTYPES.ACTIVITYTYPEDESCRIPTION,AVN_ACTIVITY.ACTIVITYID FROM AVN_ACTIVITY INNER JOIN AVN_ACTIVITYTYPES ON AVN_ACTIVITY.NEXTACTIVITYTYPEID=AVN_ACTIVITYTYPES.ACTIVITYTYPEID WHERE AVN_ACTIVITY.LEADID = ?";
//            connection = dataSource.getConnection();
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, id);
//            resultSet = statement.executeQuery();
//            eventList = new ArrayList<>();
//            Event event;
//            while (resultSet.next()) {
//                event = new Event();
//                event.setTitle(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
//                event.setStart(resultSet.getString("NEXTACTIVITYDATETIME"));
//                event.setId(resultSet.getString("ACTIVITYID"));
//                eventList.add(event);
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (Exception e) {
//                }
//            }
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (Exception e) {
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (Exception e) {
//                }
//            }
//        }
//        return eventList;
//    }
    public List<Event> getEventcreateUser(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Event> eventList = null;
        try {
            String sql = "SELECT activity.ACTIVITYDATETIME, "
                    + "  activity.ACTIVITYSTATUS, "
                    + "  activitytypes.ACTIVITYTYPEDESCRIPTION, "
                    + "  activity.ACTIVITYID "
                    + "FROM activity "
                    + "INNER JOIN activitytypes "
                    + "ON activity.ACTIVITYTYPEID = activitytypes.ACTIVITYTYPEID "
                    + "WHERE activity.CREATEDUSER = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            eventList = new ArrayList<>();
            Event event;
            while (resultSet.next()) {
                event = new Event();
                event.setTitle(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                event.setStart(resultSet.getString("ACTIVITYDATETIME"));
                event.setId(resultSet.getString("ACTIVITYID"));
                if (resultSet.getInt("ACTIVITYSTATUS") == CommonVarList.COMPLETED_ACTIVITIES) {
                    event.setColor("green");
                } else if (resultSet.getInt("ACTIVITYSTATUS") == CommonVarList.NOT_COMPLETED_ACTIVITIES) {
                    if (new Date().after(resultSet.getDate("ACTIVITYDATETIME"))) {
                        event.setColor("red");
                    } else {
                        event.setColor("blue");
                    }
                }
                eventList.add(event);
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
        return eventList;
    }

    //for contact ajent function change get event
    public List<Event> getEventContactid(int contactid) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Event> eventList = null;
        try {
            String sql = "SELECT activity.ACTIVITYDATETIME, "
                    + "  activity.ACTIVITYSTATUS, "
                    + "  activitytypes.ACTIVITYTYPEDESCRIPTION, "
                    + "  activity.ACTIVITYID "
                    + "FROM activity "
                    + "INNER JOIN activitytypes "
                    + "ON activity.ACTIVITYTYPEID=activitytypes.ACTIVITYTYPEID "
                    + "INNER JOIN lead "
                    + "ON activity.LEADID   = lead.LEADID "
                    + "WHERE lead.CONTACTID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, contactid);
            resultSet = statement.executeQuery();
            eventList = new ArrayList<>();
            Event event;
            while (resultSet.next()) {
                event = new Event();
                event.setTitle(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                event.setStart(resultSet.getString("ACTIVITYDATETIME"));
                event.setId(resultSet.getString("ACTIVITYID"));
                if (resultSet.getInt("ACTIVITYSTATUS") == CommonVarList.COMPLETED_ACTIVITIES) {
                    event.setColor("green");
                } else if (resultSet.getInt("ACTIVITYSTATUS") == CommonVarList.NOT_COMPLETED_ACTIVITIES) {
                    if (new Date().after(resultSet.getDate("ACTIVITYDATETIME"))) {
                        event.setColor("orange");
                    } else {
                        event.setColor("blue");
                    }
                }
                eventList.add(event);
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
        return eventList;
    }

    public List<Event> getFutureEventsBycreatedUser(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Event> eventList = null;
        try {
            String sql = "SELECT activity.NEXTACTIVITYDATETIME,  "
                    + "  activitytypes.ACTIVITYTYPEDESCRIPTION,  "
                    + "  activity.ACTIVITYID  "
                    + "FROM activity  "
                    + "INNER JOIN activitytypes  "
                    + "ON activity.NEXTACTIVITYTYPEID    = activitytypes.ACTIVITYTYPEID  "
                    + "WHERE activity.CREATEDUSER        = ?  "
                    + "AND activity.NEXTACTIVITYDATETIME > = CURRENT_TIMESTAMP";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            eventList = new ArrayList<>();
            Event event;
            while (resultSet.next()) {
                event = new Event();
                event.setTitle(resultSet.getString("ACTIVITYTYPEDESCRIPTION"));
                event.setStart(resultSet.getString("NEXTACTIVITYDATETIME"));
                event.setId(resultSet.getString("ACTIVITYID"));
                eventList.add(event);
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
        return eventList;
    }

    @Override
    public List<Activity> getLeadid(String name) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Activity> agentLeadid = null;

        try {
            String sql = "SELECT NAMEINFULL,lead.LEADID FROM contacts INNER JOIN lead ON contacts.CONTACTID=lead.CONTACTID WHERE lead.CREATEDUSER = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            agentLeadid = new ArrayList<>();
            Activity activity;
            while (resultSet.next()) {
                activity = new Activity();
                activity.setNameinfull(resultSet.getString("NAMEINFULL"));
                activity.setLeadid(resultSet.getInt("LEADID"));

                agentLeadid.add(activity);
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
        System.out.println(agentLeadid);
        return agentLeadid;
    }
}

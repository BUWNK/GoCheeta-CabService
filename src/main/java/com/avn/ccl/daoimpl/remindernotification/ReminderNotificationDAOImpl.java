/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.remindernotification;

import com.avn.ccl.dao.remindernotification.ReminderNotificationDAO;
import com.avn.ccl.model.remindernotification.ReminderNotification;
import com.avn.ccl.util.Common;
import static com.avn.ccl.util.Common.getStringFormatDate;
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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author ISURU
 */
public class ReminderNotificationDAOImpl implements ReminderNotificationDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    public List<ReminderNotification> getReminderById(String name) throws Exception {
//        List<ReminderNotification> reminderList = null;
//
//        try {
//            String sql = "SELECT ID,SOURCETABLEID,DESCRIPTION,REMINDERSTATUS,TO_CHAR (REMINDERTIME, 'DD-MON-YYYY HH24:MI:SS')REMINDERTIME,SECTIONID,VIEWEDSTATUS  FROM AVN_REMINDERNOTIFICATION WHERE VIEWEDSTATUS=? AND REMINDERUSER=?";
//            connection = dataSource.getConnection();
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_VIEWED);
//            statement.setString(2, name);
//            resultSet = statement.executeQuery();
//            reminderList = new ArrayList<>();
//            ReminderNotification reminder;
//            while (resultSet.next()) {
//                reminder = new ReminderNotification();
//                reminder.setId(resultSet.getString("ID"));
//                reminder.setSourcetabelId(resultSet.getString("SOURCETABLEID"));
//                reminder.setDescription(resultSet.getString("DESCRIPTION"));
//                reminder.setRemindStatus(resultSet.getString("REMINDERSTATUS"));
//                reminder.setRemindTime(resultSet.getString("REMINDERTIME"));
//                reminder.setViewStatus(resultSet.getString("VIEWEDSTATUS"));
//                reminder.setSection(resultSet.getString("SECTIONID"));
//                reminderList.add(reminder);
//
//            }
//        } catch (SQLException | NumberFormatException exception) {
//            throw exception;
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
//        return reminderList;
//    }
    @Override
    public int getReminderCount(String name, Date d1, Date d2) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT "
                    + "FROM AVN_REMINDERNOTIFICATION "
                    + "WHERE (REMINDERSTATUS = ? "
                    + "  AND REMINDERUSER = ? "
                    + "  AND REMINDERTIME BETWEEN ? AND ?) "
                    + "  OR (REMINDERTIME < ? "
                    + "  AND REMINDERSTATUS = ? "
                    + "  AND VIEWEDSTATUS <> ? "
                    + "  AND REMINDERUSER = ?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_REMIND);
            statement.setString(2, name);
            statement.setTimestamp(3, new Timestamp(Common.getStartingTimeofDay(d1).getTime()));
            statement.setTimestamp(4, Common.getTimestampFromDateTime24(d2));
            statement.setTimestamp(5, Common.getTimestampFromDateTime24(d2));
            statement.setInt(6, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_REMIND);
            statement.setInt(7, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_VIEWED);
            statement.setString(8, name);
//            System.out.println(sql);
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
    public boolean setRemindStatus(String level, String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try {
            connection = dataSource.getConnection();
            String sql = "UPDATE AVN_REMINDERNOTIFICATION SET REMINDERSTATUS=? WHERE ID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, level);
            statement.setString(2, id);
            statement.executeUpdate();
            status = true;
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
        return status;
    }

    @Override
    public boolean setViewStatus(String level, String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try {
            connection = dataSource.getConnection();
            String sql = "UPDATE AVN_REMINDERNOTIFICATION SET VIEWEDSTATUS=? WHERE SOURCETABLEID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, level);
            statement.setString(2, id);
            statement.executeUpdate();
            status = true;
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
        return status;
    }

    @Override
    public boolean setDeleteStatus(String level, String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try {
            connection = dataSource.getConnection();
            String sql = "UPDATE AVN_REMINDERNOTIFICATION SET VIEWEDSTATUS=? WHERE SOURCETABLEID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, level);
            statement.setString(2, id);
            statement.executeUpdate();
            status = true;
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
        return status;
    }

    @Override
    public List<ReminderNotification> getTableData(int minCount, int start, String username, Date d1, Date d2) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ReminderNotification> reminderList = null;
        try {
            String sql = "SELECT * "
                    + "FROM "
                    + "  (SELECT TB.ID, "
                    + "    TB.SOURCETABLEID, "
                    + "    TB.DESCRIPTION, "
                    + "    TB.REMINDERSTATUS, "
                    + "    TO_CHAR (TB.REMINDERTIME, 'DD-MON-YYYY HH24:MI:SS') AS REMINDERTIME, "
                    + "    TB.SECTIONID, "
                    + "    TB.VIEWEDSTATUS, "
                    + "    ROWNUM AS ROWNUMER "
                    + "  FROM "
                    + "    (SELECT ID, "
                    + "      SOURCETABLEID, "
                    + "      DESCRIPTION, "
                    + "      REMINDERSTATUS, "
                    + "      REMINDERTIME, "
                    + "      SECTIONID, "
                    + "      VIEWEDSTATUS "
                    + "    FROM AVN_REMINDERNOTIFICATION "
                    + "    WHERE (REMINDERSTATUS = ? "
                    + "    AND REMINDERUSER      = ? "
                    + "    AND REMINDERTIME      < ?) "
                    + "    OR (REMINDERTIME      < ? "
                    + "    AND REMINDERUSER      = ? "
                    + "    AND VIEWEDSTATUS     <> ?) "
                    + "    ORDER BY REMINDERTIME DESC "
                    + "    ) TB "
                    + "  WHERE ROWNUM <= ? "
                    + "  ) "
                    + "WHERE ROWNUMER > ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_REMIND);
            statement.setString(2, username);
            statement.setTimestamp(3, Common.getTimestampFromDateTime24(d2));
            statement.setTimestamp(4, Common.getTimestampFromDateTime24(d2));
            statement.setString(5, username);
            statement.setInt(6, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_DELETE);
            statement.setInt(7, start + minCount);
            statement.setInt(8, start);
            resultSet = statement.executeQuery();

            reminderList = new ArrayList<>();
            while (resultSet.next()) {
                ReminderNotification reminder;
                reminder = new ReminderNotification();
                reminder.setId(resultSet.getString("ID"));
                reminder.setSourcetabelId(resultSet.getString("SOURCETABLEID"));
                reminder.setDescription(resultSet.getString("DESCRIPTION"));
                reminder.setRemindStatus(resultSet.getString("REMINDERSTATUS"));
                reminder.setRemindTime(resultSet.getString("REMINDERTIME"));
                reminder.setViewStatus(resultSet.getString("VIEWEDSTATUS"));
                reminder.setSection(resultSet.getString("SECTIONID"));
                reminderList.add(reminder);
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
        return reminderList;
    }

    @Override
    public int getAllCount(String name, Date d1, Date d2) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT "
                    + "FROM AVN_REMINDERNOTIFICATION "
                    + "WHERE (REMINDERSTATUS = ? "
                    + "  AND REMINDERUSER = ? "
                    + "  AND REMINDERTIME < ?) "
                    + "  OR (REMINDERTIME < ? "
                    + "  AND REMINDERUSER = ? "
                    + "  AND VIEWEDSTATUS <> ?) " /*+ "  OR (VIEWEDSTATUS <> ? "
                     + "  AND REMINDERUSER = ?)"*/;
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_DELETE);
            statement.setString(2, name);
//            statement.setTimestamp(3, new Timestamp(Common.getStartingTimeofDay(d1).getTime()));
            statement.setTimestamp(3, Common.getTimestampFromDateTime24(d2));
            statement.setTimestamp(4, Common.getTimestampFromDateTime24(d2));
            statement.setString(5, name);
            statement.setInt(6, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_DELETE);
//            statement.setInt(7, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_DELETE);
//            statement.setString(8, name);
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
    public String loadReminderNEW(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertReminderNotification(ReminderNotification notification) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT "
                + "INTO AVN_REMINDERNOTIFICATION "
                + "  ( "
                + "    DESCRIPTION, "
                + "    REMINDERUSER, "
                + "    REMINDERTIME, "
                + "    SOURCETABLEID, "
                + "    SECTIONID, "
                + "    CREATEDDATETIME, "
                + "    LASTUPDATEDDATETIME, "
                + "    CREATEDUSER, "
                + "    REMINDERSTATUS, "
                + "    VIEWEDSTATUS "
                + "  ) "
                + "  VALUES "
                + "  (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?)";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
//            statement.setString(1, "Call Log Created With Call Back Time Call Log Id " + callLogId);
//            statement.setString(2, user);
//            statement.setTimestamp(3, DateTime.getTimestampFromDateAndTime(callLogForm.getCallbackDate(), callLogForm.getCallbackTime()));
//            statement.setLong(4, callLogId);
//            statement.setInt(5, MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID);
//            statement.setString(6, "SYSTEM");
//            statement.setInt(7, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_REMIND);
//            statement.setInt(8, MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_VIEWED);

            statement.setString(1, notification.getDescription());
            statement.setString(2, notification.getReminderUser());
            statement.setTimestamp(3, new Timestamp(notification.getReminderDateTime().getTime()));
            statement.setLong(4, Long.valueOf(notification.getSourcetabelId()));
            statement.setInt(5, Integer.valueOf(notification.getSection()));
            statement.setString(6, notification.getCreateuser());
            statement.setInt(7, Integer.valueOf(notification.getRemindStatus()));
            statement.setInt(8, Integer.valueOf(notification.getViewStatus()));
            statement.execute();
        } catch (SQLException sqle) {
            throw sqle;
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

}

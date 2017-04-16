/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.userroletask;

import com.avn.ccl.model.userroletask.UserRoleTask;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.json.JSONArray;

/**
 *
 * @author Isuru
 */
public class UserRoleTaskDAOImpl {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<UserRoleTask> getUserRoleSubsectionByUserRole(String userroleid, String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleTask> roleSubsectionList = null;
        try {
            String sql = "SELECT SS.SUBSECTIONID, SS.DESCRIPTION AS SSDES  FROM AVN_USERROLESUBSECTION RS INNER JOIN AVN_SECTION S ON RS.SECTIONID=S.SECTIONID INNER JOIN AVN_SUBSECTION SS ON RS.SUBSECTIONID=SS.SUBSECTIONID   WHERE USERROLEID=? AND RS.SECTIONID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            statement.setString(2, sectionid);
            resultSet = statement.executeQuery();
            roleSubsectionList = new ArrayList<>();
            UserRoleTask userroltask;
            while (resultSet.next()) {
                userroltask = new UserRoleTask();
                userroltask.setSubsectionid(resultSet.getString("SUBSECTIONID"));
                userroltask.setSubsectionDes(resultSet.getString("SSDES"));
                roleSubsectionList.add(userroltask);
            }
        } catch (Exception ex) {
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
        return roleSubsectionList;
    }

    public List<UserRoleTask> getUserRoleSectionByUserRole(String userroleid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleTask> roleSubsectionList = null;
        try {
            String sql = "SELECT DISTINCT S.SECTIONID, S.DESCRIPTION AS SDES FROM AVN_USERROLESUBSECTION RS RIGHT OUTER JOIN AVN_SECTION S ON RS.SECTIONID=S.SECTIONID   WHERE USERROLEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            resultSet = statement.executeQuery();
            roleSubsectionList = new ArrayList<>();
            UserRoleTask userroltask;
            while (resultSet.next()) {
                userroltask = new UserRoleTask();
                userroltask.setSectionid(resultSet.getString("SECTIONID"));
                userroltask.setSectionDes(resultSet.getString("SDES"));
                roleSubsectionList.add(userroltask);
            }
        } catch (Exception ex) {
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
        return roleSubsectionList;
    }

    public List<UserRoleTask> getTaskByUserRoleid(String userroleid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleTask> taskList = null;
        try {
            String sql = "SELECT DISTINCT T.TASKID,T.DESCRIPTION FROM AVN_TASK T INNER JOIN AVN_SECTIONTASK ST ON T.TASKID=ST.TASKID INNER JOIN AVN_USERROLESUBSECTION URS ON ST.SECTIONID=URS.SECTIONID WHERE URS.USERROLEID=?  ORDER BY T.TASKID ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            resultSet = statement.executeQuery();
            taskList = new ArrayList<>();
            UserRoleTask userroltask;
            while (resultSet.next()) {
                userroltask = new UserRoleTask();
                userroltask.setTaskid(resultSet.getString("TASKID"));
                userroltask.setTaskDes(resultSet.getString("DESCRIPTION"));
                taskList.add(userroltask);
            }
        } catch (Exception ex) {
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
        return taskList;
    }

    public List<UserRoleTask> insertUserRoleTask(UserRoleTask userroletask, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleTask> list = null;
        try {
            String sql = "DELETE FROM AVN_USERROLETASK WHERE USERROLESUBSECTIONID=?";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
//            JSONArray Darray = new JSONArray(userroletask.getOldmultitaskarray());
//            for (int i = 0; i < Darray.length(); i++) {
//                String taskid = Darray.getString(i);
            statement.setString(1, userroletask.getUserrolesubsectionid());
//                statement.setString(2, taskid);
            statement.execute();
//            }

            sql = "INSERT INTO AVN_USERROLETASK (USERROLESUBSECTIONID,TASKID,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) VALUES (?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?)";
            statement = connection.prepareStatement(sql);
            if (userroletask.getMultitaskarray() != null && !userroletask.getMultitaskarray().isEmpty()) {
                JSONArray array = new JSONArray(userroletask.getMultitaskarray());
                UserRoleTask userroletaskDate;
                list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    userroletaskDate = new UserRoleTask();
                    String taskid = array.getString(i);
                    userroletaskDate.setTaskid(taskid);
                    statement.setInt(1, Integer.valueOf(userroletask.getUserrolesubsectionid()));
                    statement.setInt(2, Integer.valueOf(taskid));
                    statement.setString(3, username);
                    statement.execute();
                    list.add(userroletaskDate);
                }
            }
            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            if (resultSet != null) {
                try {

                    resultSet.close();
                } catch (Exception e) {
                    connection.rollback();;
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

    public int getTableDataCount(UserRoleTask parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_USERROLETASK RT INNER JOIN AVN_USERROLESUBSECTION URS ON RT.USERROLESUBSECTIONID=URS.USERROLESUBSECTIONID INNER JOIN AVN_USER_ROLE UR ON URS.USERROLEID=UR.USERROLEID INNER JOIN AVN_SUBSECTION SS ON URS.SUBSECTIONID=SS.SUBSECTIONID INNER JOIN AVN_SECTION S ON URS.SECTIONID=S.SECTIONID INNER JOIN AVN_TASK T ON RT.TASKID=T.TASKID :where ";
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

    private String getWhere(UserRoleTask parameters) {
        String where = "";
        if (parameters.getUserroleid() != null
                && !parameters.getUserroleid().equalsIgnoreCase("--")) {
            where += "AND URS.USERROLEID LIKE '%" + parameters.getUserroleid().trim() + "%'";
        }
        return where;
    }

    public List<UserRoleTask> getTableData(UserRoleTask parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleTask> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + " FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( SELECT S.SECTIONID, S.DESCRIPTION AS SDE,SS.SUBSECTIONID, SS.DESCRIPTION AS SSDES,UR.USERROLEID, UR.DESCRIPTION AS URDES,T.TASKID, T.DESCRIPTION AS TDES, TO_CHAR(RT.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')AS CREATEDDATETIME  ,TO_CHAR(RT.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')AS LASTUPDATEDDATETIME,RT.CREATEDUSER  FROM AVN_USERROLETASK RT INNER JOIN AVN_USERROLESUBSECTION URS ON RT.USERROLESUBSECTIONID=URS.USERROLESUBSECTIONID INNER JOIN AVN_USER_ROLE UR ON URS.USERROLEID=UR.USERROLEID INNER JOIN AVN_SUBSECTION SS ON URS.SUBSECTIONID=SS.SUBSECTIONID INNER JOIN AVN_SECTION S ON URS.SECTIONID=S.SECTIONID INNER JOIN AVN_TASK T ON RT.TASKID=T.TASKID :where ORDER BY URS.USERROLESUBSECTIONID ASC) TB  "
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
            UserRoleTask userroletaskData;
            while (resultSet.next()) {
                userroletaskData = new UserRoleTask();
                userroletaskData.setSectionid(resultSet.getString("SECTIONID"));
                userroletaskData.setSectionDes(resultSet.getString("SDE"));
                userroletaskData.setSubsectionid(resultSet.getString("SUBSECTIONID"));
                userroletaskData.setSubsectionDes(resultSet.getString("SSDES"));
                userroletaskData.setUserroleid(resultSet.getString("USERROLEID"));
                userroletaskData.setUserroleDes(resultSet.getString("URDES"));
                userroletaskData.setTaskid(resultSet.getString("TASKID"));
                userroletaskData.setTaskDes(resultSet.getString("TDES"));
                userroletaskData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                userroletaskData.setLastupdatedtime(resultSet.getString("LASTUPDATEDDATETIME"));
                userroletaskData.setCreateduser(resultSet.getString("CREATEDUSER"));
                list.add(userroletaskData);
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

    public Map<String, String> getAssignedTask(UserRoleTask userroletask) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT SECT.TASKID,TK.DESCRIPTION FROM AVN_SECTIONTASK SECT INNER JOIN AVN_TASK TK ON TK.TASKID=SECT.TASKID WHERE SECT.SECTIONID=? AND SECT.TASKID IN(SELECT USRT.TASKID FROM AVN_USERROLETASK USRT INNER JOIN AVN_USERROLESUBSECTION USRSUS ON USRSUS.USERROLESUBSECTIONID = USRT.USERROLESUBSECTIONID WHERE USRSUS.USERROLEID = ? AND USRSUS.SECTIONID = ? AND USRSUS.SUBSECTIONID = ?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroletask.getSectionid());
            statement.setString(2, userroletask.getUserroleid());
            statement.setString(3, userroletask.getSectionid());
            statement.setString(4, userroletask.getSubsectionid());
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("TASKID"), resultSet.getString("DESCRIPTION"));
            }
        } catch (Exception ex) {
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
        return list;
    }

    public Map<String, String> getNotAssignedTask(UserRoleTask userroletask) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT SECT.TASKID,TK.DESCRIPTION FROM AVN_SECTIONTASK SECT INNER JOIN AVN_TASK TK ON TK.TASKID=SECT.TASKID WHERE SECT.SECTIONID=? AND SECT.TASKID NOT IN(SELECT USRT.TASKID FROM AVN_USERROLETASK USRT INNER JOIN AVN_USERROLESUBSECTION USRSUS ON USRSUS.USERROLESUBSECTIONID = USRT.USERROLESUBSECTIONID WHERE USRSUS.USERROLEID = ? AND USRSUS.SECTIONID = ? AND USRSUS.SUBSECTIONID = ?) ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroletask.getSectionid());
            statement.setString(2, userroletask.getUserroleid());
            statement.setString(3, userroletask.getSectionid());
            statement.setString(4, userroletask.getSubsectionid());
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("TASKID"), resultSet.getString("DESCRIPTION"));
            }
        } catch (Exception ex) {
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
        return list;
    }

    public UserRoleTask getUserRoleTask(String userroleid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserRoleTask userroleTaskData = null;
        try {
            String sql = "SELECT S.SECTIONID, S.DESCRIPTION AS SDE,SS.SUBSECTIONID, SS.DESCRIPTION AS SSDES,UR.USERROLEID, UR.DESCRIPTION AS URDES,T.TASKID, T.DESCRIPTION AS TDES, TO_CHAR(RT.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')AS CREATEDDATETIME  ,TO_CHAR(RT.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')AS LASTUPDATEDDATETIME,RT.CREATEDUSER,URS.USERROLESUBSECTIONID  FROM AVN_USERROLETASK RT INNER JOIN AVN_USERROLESUBSECTION URS ON RT.USERROLESUBSECTIONID=URS.USERROLESUBSECTIONID INNER JOIN AVN_USER_ROLE UR ON URS.USERROLEID=UR.USERROLEID INNER JOIN AVN_SUBSECTION SS ON URS.SUBSECTIONID=SS.SUBSECTIONID INNER JOIN AVN_SECTION S ON URS.SECTIONID=S.SECTIONID INNER JOIN AVN_TASK T ON RT.TASKID=T.TASKID WHERE URS.USERROLEID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userroleTaskData = new UserRoleTask();
                userroleTaskData.setUserrolesubsectionid(resultSet.getString("USERROLESUBSECTIONID"));
                userroleTaskData.setSectionid(resultSet.getString("SECTIONID"));
                userroleTaskData.setSectionDes(resultSet.getString("SDE"));
                userroleTaskData.setSectionid(resultSet.getString("SUBSECTIONID"));
                userroleTaskData.setSubsectionDes(resultSet.getString("SSDES"));
                userroleTaskData.setUserroleid(resultSet.getString("USERROLEID"));
                userroleTaskData.setUserroleDes(resultSet.getString("URDES"));
                userroleTaskData.setTaskid(resultSet.getString("TASKID"));
                userroleTaskData.setTaskDes(resultSet.getString("TDES"));
                userroleTaskData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                userroleTaskData.setLastupdatedtime(resultSet.getString("LASTUPDATEDDATETIME"));
                userroleTaskData.setCreateduser(resultSet.getString("CREATEDUSER"));
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
        return userroleTaskData;
    }

    public List<UserRoleTask> updateuserRoleTask(UserRoleTask userroletask, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleTask> list = null;
        try {
            String sql = "DELETE FROM AVN_USERROLETASK WHERE USERROLESUBSECTIONID=?";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
//            JSONArray Darray = new JSONArray(userroletask.getOldmultitaskarray());
//            for (int i = 0; i < Darray.length(); i++) {
//                String taskid = Darray.getString(i);
            statement.setString(1, userroletask.getUserrolesubsectionid());
//                statement.setString(2, taskid);
            statement.execute();
//            }

            sql = "INSERT INTO AVN_USERROLETASK (USERROLESUBSECTIONID,TASKID,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) VALUES (?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?)";
            statement = connection.prepareStatement(sql);
            if (userroletask.getMultitaskarray() != null && !userroletask.getMultitaskarray().isEmpty()) {
                JSONArray array = new JSONArray(userroletask.getMultitaskarray());
                UserRoleTask userroletaskDate;
                list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    userroletaskDate = new UserRoleTask();
                    String taskid = array.getString(i);
                    userroletaskDate.setTaskid(taskid);
                    statement.setInt(1, Integer.valueOf(userroletask.getUserrolesubsectionid()));
                    statement.setInt(2, Integer.valueOf(taskid));
                    statement.setString(3, username);
                    statement.execute();
                    list.add(userroletaskDate);
                }
            }
            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            if (resultSet != null) {
                try {

                    resultSet.close();
                } catch (Exception e) {
                    connection.rollback();;
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

    public List<UserRoleTask> getAssignedTaskList(UserRoleTask userroletask) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleTask> UserRoleAssignTaskList = null;
        try {
            String sql = "SELECT SECT.TASKID,TK.DESCRIPTION FROM AVN_SECTIONTASK SECT INNER JOIN AVN_TASK TK ON TK.TASKID=SECT.TASKID WHERE SECT.SECTIONID=? AND SECT.TASKID IN(SELECT USRT.TASKID FROM AVN_USERROLETASK USRT INNER JOIN AVN_USERROLESUBSECTION USRSUS ON USRSUS.USERROLESUBSECTIONID = USRT.USERROLESUBSECTIONID WHERE USRSUS.USERROLEID = ? AND USRSUS.SECTIONID = ? AND USRSUS.SUBSECTIONID = ?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroletask.getSectionid());
            statement.setString(2, userroletask.getUserroleid());
            statement.setString(3, userroletask.getSectionid());
            statement.setString(4, userroletask.getSubsectionid());
            resultSet = statement.executeQuery();
            UserRoleAssignTaskList = new ArrayList<>();
            UserRoleTask userroltask;
            while (resultSet.next()) {
                userroltask = new UserRoleTask();
                userroltask.setTaskid(resultSet.getString("TASKID"));
                userroltask.setTaskDes(resultSet.getString("DESCRIPTION"));
                UserRoleAssignTaskList.add(userroltask);
            }
        } catch (Exception ex) {
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
        return UserRoleAssignTaskList;
    }

    public List<UserRoleTask> getNotAssignedTaskList(UserRoleTask userroletask) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleTask> UserRoleNotassignTaskList = null;
        try {
            String sql = "SELECT SECT.TASKID,TK.DESCRIPTION FROM AVN_SECTIONTASK SECT INNER JOIN AVN_TASK TK ON TK.TASKID=SECT.TASKID WHERE SECT.SECTIONID=? AND SECT.TASKID NOT IN(SELECT USRT.TASKID FROM AVN_USERROLETASK USRT INNER JOIN AVN_USERROLESUBSECTION USRSUS ON USRSUS.USERROLESUBSECTIONID = USRT.USERROLESUBSECTIONID WHERE USRSUS.USERROLEID = ? AND USRSUS.SECTIONID = ? AND USRSUS.SUBSECTIONID = ?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroletask.getSectionid());
            statement.setString(2, userroletask.getUserroleid());
            statement.setString(3, userroletask.getSectionid());
            statement.setString(4, userroletask.getSubsectionid());

            resultSet = statement.executeQuery();
            UserRoleNotassignTaskList = new ArrayList<>();
            UserRoleTask userroltask;
            while (resultSet.next()) {
                userroltask = new UserRoleTask();
                userroltask.setTaskid(resultSet.getString("TASKID"));
                userroltask.setTaskDes(resultSet.getString("DESCRIPTION"));
                UserRoleNotassignTaskList.add(userroltask);
            }
        } catch (Exception ex) {
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
        return UserRoleNotassignTaskList;
    }

    public String getUserrolesubsetionid(UserRoleTask userroletask) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String userrolesubsectionid = null;
        try {
            String sql = "SELECT USERROLESUBSECTIONID FROM AVN_USERROLESUBSECTION WHERE SECTIONID=? AND SUBSECTIONID=? AND USERROLEID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroletask.getSectionid());
            statement.setString(2, userroletask.getSubsectionid());
            statement.setString(3, userroletask.getUserroleid());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                userrolesubsectionid = resultSet.getString("USERROLESUBSECTIONID");

            }
        } catch (Exception ex) {
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
        return userrolesubsectionid;
    }

    public Map<String, String> getSubSectionDropdownList(String userroleid, String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT SS.SUBSECTIONID, SS.DESCRIPTION AS SSDES  FROM AVN_USERROLESUBSECTION RS INNER JOIN AVN_SECTION S ON RS.SECTIONID=S.SECTIONID INNER JOIN AVN_SUBSECTION SS ON RS.SUBSECTIONID=SS.SUBSECTIONID   WHERE USERROLEID=? AND RS.SECTIONID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            statement.setString(2, sectionid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("SUBSECTIONID"), resultSet.getString("SSDES"));
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
}

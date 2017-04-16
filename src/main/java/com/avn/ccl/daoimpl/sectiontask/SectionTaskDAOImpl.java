/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.sectiontask;

import com.avn.ccl.dao.sectiontask.SectionTaskDAO;
import com.avn.ccl.model.sectiontask.SectionTask;
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

/**
 *
 * @author Isuru
 */
public class SectionTaskDAOImpl implements SectionTaskDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<SectionTask> insertSectionTask(SectionTask sectiontask, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SectionTask> list = null;
        try {
            JSONArray array = new JSONArray(sectiontask.getMultitaskarray());
            String sql = "DELETE FROM AVN_USERROLETASK WHERE USERROLESUBSECTIONID  IN (SELECT  USERROLESUBSECTIONID FROM AVN_USERROLESUBSECTION where SECTIONID=?) AND TASKID NOT IN (" + array.toString().replaceAll("[\\[\\]\"]", "") + ")";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setString(1, sectiontask.getSectionid());
            System.out.println(array.toString().replaceAll("[\\[\\]\"]", ""));
            statement.execute();

            sql = "DELETE FROM AVN_SECTIONTASK WHERE SECTIONID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, sectiontask.getSectionid());
            statement.execute();

            sql = "INSERT INTO AVN_SECTIONTASK (SECTIONID,TASKID,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) VALUES (?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?)";
            statement = connection.prepareStatement(sql);
            if (sectiontask.getMultitaskarray() != null && !sectiontask.getMultitaskarray().isEmpty()) {
//                JSONArray array = new JSONArray(sectiontask.getMultitaskarray());
                list = new ArrayList<>();
                SectionTask sectiontaskData;
                for (int i = 0; i < array.length(); i++) {
                    sectiontaskData = new SectionTask();
                    String taskid = array.getString(i);
                    sectiontaskData.setTaskid(taskid);
                    statement.setInt(1, Integer.valueOf(sectiontask.getSectionid()));
                    statement.setInt(2, Integer.valueOf(taskid));
                    statement.setString(3, username);
                    statement.execute();
                    list.add(sectiontaskData);
                }
            }
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    connection.rollback();
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

    @Override
    public int getTableDataCount(SectionTask parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_SECTIONTASK ST INNER JOIN AVN_TASK TS ON ST.TASKID=TS.TASKID INNER JOIN AVN_SECTION S ON ST.SECTIONID=S.SECTIONID :where ";
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

    private String getWhere(SectionTask parameters) {
        String where = "";

        if (parameters.getSectionid() != null
                && !parameters.getSectionid().equalsIgnoreCase("--")) {
            where += "AND ST.SECTIONID LIKE '%" + parameters.getSectionid().trim() + "%'";
        }
        return where;
    }

    @Override
    public List<SectionTask> getTableData(SectionTask parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SectionTask> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + " FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( SELECT ST.SECTIONID, S.DESCRIPTION AS SDES, ST.TASKID, TS.DESCRIPTION AS TDES,ST.CREATEDDATETIME,ST.LASTUPDATEDDATETIME,ST.CREATEDUSER FROM AVN_SECTIONTASK ST INNER JOIN AVN_TASK TS ON ST.TASKID=TS.TASKID INNER JOIN AVN_SECTION S ON ST.SECTIONID=S.SECTIONID :where ORDER BY ST.SECTIONID ASC) TB  "
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
            SectionTask sectiontaskData;
            while (resultSet.next()) {
                sectiontaskData = new SectionTask();
                sectiontaskData.setSectionid(resultSet.getString("SECTIONID"));
                sectiontaskData.setSectionDes(resultSet.getString("SDES"));
                sectiontaskData.setTaskid(resultSet.getString("TASKID"));
                sectiontaskData.setTaskDes(resultSet.getString("TDES"));
                sectiontaskData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                sectiontaskData.setLastupdatedtime(resultSet.getString("LASTUPDATEDDATETIME"));
                sectiontaskData.setCreateduser(resultSet.getString("CREATEDUSER"));
                list.add(sectiontaskData);
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

    @Override
    public Map<String, String> getAssignedTask(String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT  ST.TASKID, TS.DESCRIPTION AS TDES FROM AVN_SECTIONTASK ST INNER JOIN AVN_TASK TS ON ST.TASKID=TS.TASKID WHERE ST.SECTIONID=? ORDER BY ST.SECTIONID ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, sectionid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("TASKID"), resultSet.getString("TDES"));
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

    @Override
    public Map<String, String> getNotAssignedTask(String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT T.TASKID, T.DESCRIPTION AS TDES FROM AVN_TASK T WHERE TASKID NOT IN (SELECT  ST.TASKID FROM AVN_SECTIONTASK ST INNER JOIN AVN_TASK TS ON ST.TASKID=TS.TASKID  WHERE ST.SECTIONID=?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, sectionid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("TASKID"), resultSet.getString("TDES"));
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

    @Override
    public List<SectionTask> updatSectionTask(SectionTask sectiontask, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SectionTask> list = null;
        try {
            JSONArray array = new JSONArray(sectiontask.getMultitaskarray());
            String sql = "DELETE FROM AVN_USERROLETASK WHERE USERROLESUBSECTIONID  IN (SELECT  USERROLESUBSECTIONID FROM AVN_USERROLESUBSECTION where SECTIONID=?) AND TASKID NOT IN (" + array.toString().replaceAll("[\\[\\]\"]", "") + ")";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setString(1, sectiontask.getSectionid());
            System.out.println(array.toString().replaceAll("[\\[\\]\"]", ""));
            statement.execute();

            sql = "DELETE FROM AVN_SECTIONTASK WHERE SECTIONID=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, sectiontask.getSectionid());
            statement.execute();

            sql = "INSERT INTO AVN_SECTIONTASK (SECTIONID,TASKID,CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) VALUES (?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?)";
            statement = connection.prepareStatement(sql);
            if (sectiontask.getMultitaskarray() != null && !sectiontask.getMultitaskarray().isEmpty()) {

                list = new ArrayList<>();
                SectionTask sectiontaskData;
                for (int i = 0; i < array.length(); i++) {
                    sectiontaskData = new SectionTask();
                    String taskid = array.getString(i);
                    sectiontaskData.setTaskid(taskid);
                    statement.setInt(1, Integer.valueOf(sectiontask.getSectionid()));
                    statement.setInt(2, Integer.valueOf(taskid));
                    statement.setString(3, username);
                    statement.execute();
                    list.add(sectiontaskData);
                }
            }
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    connection.rollback();
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

    public List<SectionTask> getAssignSectionDropdownListByUserRoleID(String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SectionTask> assignTaskList = null;
        try {
            String sql = "SELECT  ST.TASKID, TS.DESCRIPTION AS TDES FROM AVN_SECTIONTASK ST INNER JOIN AVN_TASK TS ON ST.TASKID=TS.TASKID WHERE ST.SECTIONID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, sectionid);
            resultSet = statement.executeQuery();
            assignTaskList = new ArrayList<>();
            SectionTask sectiontask;
            while (resultSet.next()) {
                sectiontask = new SectionTask();
                sectiontask.setTaskid(resultSet.getString("TASKID"));
                sectiontask.setTaskDes(resultSet.getString("TDES"));
                assignTaskList.add(sectiontask);
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
        return assignTaskList;
    }

    public List<SectionTask> getNotAssignSectionDropdownListByUserRoleID(String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SectionTask> NotAssignTaskList = null;
        try {
            String sql = "SELECT T.TASKID, T.DESCRIPTION AS TDES FROM AVN_TASK T WHERE TASKID NOT IN (SELECT  ST.TASKID FROM AVN_SECTIONTASK ST INNER JOIN AVN_TASK TS ON ST.TASKID=TS.TASKID  WHERE ST.SECTIONID=?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, sectionid);
            resultSet = statement.executeQuery();
            NotAssignTaskList = new ArrayList<>();
            SectionTask sectiontask;
            while (resultSet.next()) {
                sectiontask = new SectionTask();
                sectiontask.setTaskid(resultSet.getString("TASKID"));
                sectiontask.setTaskDes(resultSet.getString("TDES"));
                NotAssignTaskList.add(sectiontask);
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
        return NotAssignTaskList;
    }
}

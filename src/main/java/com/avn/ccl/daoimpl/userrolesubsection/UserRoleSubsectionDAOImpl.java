/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.userrolesubsection;

import com.avn.ccl.dao.userrolesubsection.UserRoleSubsectionDAO;
import com.avn.ccl.model.userrolesubsection.UserRoleSubSection;
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
public class UserRoleSubsectionDAOImpl implements UserRoleSubsectionDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<UserRoleSubSection> getSubSectionDropdownListBySectionID(String userrole, String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleSubSection> SubSectionList = null;
        try {
            String sql = "SELECT SUBSECTIONID, "
                    + " DESCRIPTION "
                    + "FROM AVN_SUBSECTION "
                    + "WHERE SECTIONID = ? AND SUBSECTIONID NOT IN "
                    + " (SELECT SUBSECTIONID "
                    + " FROM AVN_USERROLESUBSECTION  "
                    + " WHERE USERROLEID = ? "
                    + " AND SECTIONID   = ?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer.valueOf(sectionid)));
            statement.setInt(2, (Integer.valueOf(userrole)));
            statement.setInt(3, (Integer.valueOf(sectionid)));
            resultSet = statement.executeQuery();
            SubSectionList = new ArrayList<>();
            UserRoleSubSection subsection;
            while (resultSet.next()) {
                subsection = new UserRoleSubSection();
                subsection.setSubsectionid(resultSet.getString("SUBSECTIONID"));
                subsection.setSubsectionDes(resultSet.getString("DESCRIPTION"));
                SubSectionList.add(subsection);
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
        return SubSectionList;
    }

    public List<UserRoleSubSection> getAssignSubSectionDropdownListBySectionID(String userrole, String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleSubSection> SubSectionList = null;
        try {
            String sql = "SELECT RSS.SUBSECTIONID, SS.DESCRIPTION AS SDES FROM AVN_USERROLESUBSECTION RSS INNER JOIN AVN_SUBSECTION SS ON RSS.SUBSECTIONID=SS.SUBSECTIONID WHERE RSS.USERROLEID=? AND RSS.SECTIONID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);;
            statement.setInt(1, (Integer.valueOf(userrole)));
            statement.setInt(2, (Integer.valueOf(sectionid)));
            resultSet = statement.executeQuery();
            SubSectionList = new ArrayList<>();
            UserRoleSubSection subsection;
            while (resultSet.next()) {
                subsection = new UserRoleSubSection();
                subsection.setSubsectionid(resultSet.getString("SUBSECTIONID"));
                subsection.setSubsectionDes(resultSet.getString("SDES"));
                SubSectionList.add(subsection);
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
        return SubSectionList;
    }

    @Override
    public List<UserRoleSubSection> insertUserRoleSubSection(UserRoleSubSection userrolesubsection, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement4 = null;
        ResultSet resultSet = null;
        List<UserRoleSubSection> list = null;
        try {

            JSONArray Darray = new JSONArray(userrolesubsection.getOldmultisubsectionarray());
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            for (int i = 0; i < Darray.length(); i++) {
                String sql = "SELECT USERROLESUBSECTIONID FROM AVN_USERROLESUBSECTION WHERE SUBSECTIONID=?";

                List<String> subsectionid = new ArrayList<>();
                String Dsubsectionid = Darray.getString(i);
                statement = connection.prepareStatement(sql);
                statement.setString(1, Dsubsectionid);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    subsectionid.add(resultSet.getString("USERROLESUBSECTIONID"));
                }

                sql = "DELETE FROM AVN_USERROLETASK WHERE USERROLESUBSECTIONID=?";
                for (String userrolesubsectionid : subsectionid) {
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, userrolesubsectionid);
                    statement.addBatch();
                }
                statement.executeBatch();

                sql = "DELETE FROM AVN_USERROLESUBSECTION WHERE USERROLESUBSECTIONID=?";
                for (String userrolesubsectionid : subsectionid) {
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, userrolesubsectionid);
                    statement.addBatch();
                }
                statement.executeBatch();

            }
          String  sql = "INSERT INTO AVN_USERROLESUBSECTION (USERROLEID, SUBSECTIONID, CREATEDDATETIME,LASTUPDATEDDATETIME,SECTIONID,CREATEDUSER) VALUES (?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,?)";
            statement4 = connection.prepareStatement(sql);
            if (userrolesubsection.getMultisubsectionarray() != null && !userrolesubsection.getMultisubsectionarray().isEmpty()) {
                JSONArray array = new JSONArray(userrolesubsection.getMultisubsectionarray());
                UserRoleSubSection userrolesubsectionData;
                list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    userrolesubsectionData = new UserRoleSubSection();
                    String subsectionid = array.getString(i);
                    userrolesubsectionData.setSubsectionid(userrolesubsection.getSubsectionid());
                    statement4.setInt(1, Integer.valueOf(userrolesubsection.getUserroleid()));
                    statement4.setInt(2, Integer.valueOf(subsectionid));
                    statement4.setString(3, userrolesubsection.getSectionid());
                    statement4.setString(4, username);
                    statement4.execute();
                    list.add(userrolesubsectionData);
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
    public Map<String, String> getAssignedSubSections(String userroleid, String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT RSS.SUBSECTIONID, SS.DESCRIPTION AS SDES FROM AVN_USERROLESUBSECTION RSS INNER JOIN AVN_SUBSECTION SS ON RSS.SUBSECTIONID=SS.SUBSECTIONID WHERE RSS.USERROLEID=? AND RSS.SECTIONID=?  ORDER BY SS.DESCRIPTION DESC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            statement.setString(2, sectionid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("SUBSECTIONID"), resultSet.getString("SDES"));
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
    public Map<String, String> getNotAssignedSubSections(String userroleid, String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT SUBSECTIONID,DESCRIPTION FROM AVN_SUBSECTION WHERE SUBSECTIONID NOT IN (SELECT RSS.SUBSECTIONID FROM AVN_USERROLESUBSECTION RSS INNER JOIN AVN_SUBSECTION SS ON RSS.SUBSECTIONID=SS.SUBSECTIONID INNER JOIN AVN_USERROLESECTION URS ON RSS.SECTIONID=URS.SECTIONID WHERE URS.USERROLEID=? AND URS.SECTIONID=?) AND SECTIONID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            statement.setString(2, sectionid);
            statement.setString(3, sectionid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("SUBSECTIONID"), resultSet.getString("DESCRIPTION"));
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
    public int getTableDataCount(UserRoleSubSection parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_USERROLESUBSECTION RSS  INNER JOIN AVN_USER_ROLE UR ON RSS.USERROLEID=UR.USERROLEID INNER JOIN AVN_SECTION S ON RSS.SECTIONID=S.SECTIONID INNER JOIN AVN_SUBSECTION SS ON RSS.SUBSECTIONID=SS.SUBSECTIONID :where ";
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

    private String getWhere(UserRoleSubSection parameters) {
        String where = "";
        if (parameters.getUserroleid() != null
                && !parameters.getUserroleid().equalsIgnoreCase("--")) {
            where += "AND RSS.USERROLEID LIKE '%" + parameters.getUserroleid().trim() + "%'";
        }
        if (parameters.getSectionid() != null
                && !parameters.getSectionid().equalsIgnoreCase("--")) {
            where += "AND RSS.SECTIONID LIKE '%" + parameters.getSectionid().trim() + "%'";
        }
        return where;
    }

    @Override
    public List<UserRoleSubSection> getTableData(UserRoleSubSection parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleSubSection> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + " FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( SELECT RSS.USERROLEID,UR.DESCRIPTION AS URDES,  RSS.SUBSECTIONID, SS.DESCRIPTION AS SSDES, RSS.SECTIONID,RSS.CREATEDUSER,TO_CHAR(RSS.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')AS CREATEDDATETIME  ,TO_CHAR(RSS.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')AS LASTUPDATEDDATETIME  FROM AVN_USERROLESUBSECTION RSS  INNER JOIN AVN_USER_ROLE UR ON RSS.USERROLEID=UR.USERROLEID  INNER JOIN AVN_SUBSECTION SS ON RSS.SUBSECTIONID=SS.SUBSECTIONID :where ORDER BY RSS.CREATEDDATETIME ASC) TB  "
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
            UserRoleSubSection userrolesubsectionData;
            while (resultSet.next()) {
                userrolesubsectionData = new UserRoleSubSection();
                userrolesubsectionData.setUserroleid(resultSet.getString("USERROLEID"));
                userrolesubsectionData.setUserroleDes(resultSet.getString("URDES"));
                userrolesubsectionData.setSectionid(resultSet.getString("SECTIONID"));
//                userrolesubsectionData.setSectionDes(resultSet.getString("SDES"));
                userrolesubsectionData.setSubsectionid(resultSet.getString("SUBSECTIONID"));
                userrolesubsectionData.setSubsectionDes(resultSet.getString("SSDES"));
                userrolesubsectionData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                userrolesubsectionData.setLastupdatetime(resultSet.getString("LASTUPDATEDDATETIME"));
                userrolesubsectionData.setCreateduser(resultSet.getString("CREATEDUSER"));
                list.add(userrolesubsectionData);
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
    public List<UserRoleSubSection> updateuserRoleSection(UserRoleSubSection userrolesubsection, String username) throws Exception {
       Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement4 = null;
        ResultSet resultSet = null;
        List<UserRoleSubSection> list = null;
        try {

            JSONArray Darray = new JSONArray(userrolesubsection.getOldmultisubsectionarray());
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            for (int i = 0; i < Darray.length(); i++) {
                String sql = "SELECT USERROLESUBSECTIONID FROM AVN_USERROLESUBSECTION WHERE SUBSECTIONID=?";

                List<String> subsectionid = new ArrayList<>();
                String Dsubsectionid = Darray.getString(i);
                statement = connection.prepareStatement(sql);
                statement.setString(1, Dsubsectionid);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    subsectionid.add(resultSet.getString("USERROLESUBSECTIONID"));
                }

                sql = "DELETE FROM AVN_USERROLETASK WHERE USERROLESUBSECTIONID=?";
                for (String userrolesubsectionid : subsectionid) {
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, userrolesubsectionid);
                    statement.addBatch();
                }
                statement.executeBatch();

                sql = "DELETE FROM AVN_USERROLESUBSECTION WHERE USERROLESUBSECTIONID=?";
                for (String userrolesubsectionid : subsectionid) {
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, userrolesubsectionid);
                    statement.addBatch();
                }
                statement.executeBatch();

            }
          String  sql = "INSERT INTO AVN_USERROLESUBSECTION (USERROLEID, SUBSECTIONID, CREATEDDATETIME,LASTUPDATEDDATETIME,SECTIONID,CREATEDUSER) VALUES (?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,?)";
            statement4 = connection.prepareStatement(sql);
            if (userrolesubsection.getMultisubsectionarray() != null && !userrolesubsection.getMultisubsectionarray().isEmpty()) {
                JSONArray array = new JSONArray(userrolesubsection.getMultisubsectionarray());
                UserRoleSubSection userrolesubsectionData;
                list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    userrolesubsectionData = new UserRoleSubSection();
                    String subsectionid = array.getString(i);
                    userrolesubsectionData.setSubsectionid(userrolesubsection.getSubsectionid());
                    statement4.setInt(1, Integer.valueOf(userrolesubsection.getUserroleid()));
                    statement4.setInt(2, Integer.valueOf(subsectionid));
                    statement4.setString(3, userrolesubsection.getSectionid());
                    statement4.setString(4, username);
                    statement4.execute();
                    list.add(userrolesubsectionData);
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

}

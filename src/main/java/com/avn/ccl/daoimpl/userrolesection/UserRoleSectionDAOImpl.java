/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.userrolesection;

import com.avn.ccl.dao.userrolesection.UserRoleSectionDAO;
import com.avn.ccl.model.userrolesection.UserRoleSection;
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
public class UserRoleSectionDAOImpl implements UserRoleSectionDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<UserRoleSection> insertRoleSection(UserRoleSection userrolesection, String username) throws Exception {
        List<UserRoleSection> list = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            JSONArray array = new JSONArray(userrolesection.getMultisectionarray());
            String sql = "DELETE FROM AVN_USERROLETASK WHERE USERROLESUBSECTIONID  IN (SELECT  USERROLESUBSECTIONID FROM AVN_USERROLESUBSECTION where SECTIONID NOT IN (" + array.toString().replaceAll("[\\[\\]\"]", "") + ") AND USERROLEID=?)";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setString(1, userrolesection.getUserroleid());
            System.out.println(array.toString().replaceAll("[\\[\\]\"]", ""));
            statement.execute();

            sql = "DELETE FROM AVN_USERROLESUBSECTION WHERE SECTIONID NOT IN (" + array.toString().replaceAll("[\\[\\]\"]", "") + ") AND USERROLEID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userrolesection.getUserroleid());
            statement.execute();

            sql = "DELETE FROM AVN_USERROLESECTION WHERE USERROLEID=?";        
            statement = connection.prepareStatement(sql);
            statement.setString(1, userrolesection.getUserroleid());
            statement.execute();

            sql = "INSERT INTO AVN_USERROLESECTION (USERROLEID, SECTIONID, CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) VALUES (?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?)";
            statement = connection.prepareStatement(sql);
            if (userrolesection.getMultisectionarray() != null && !userrolesection.getMultisectionarray().isEmpty()) {
//                JSONArray array = new JSONArray(userrolesection.getMultisectionarray());
                UserRoleSection userrolesectionData;
                list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    userrolesectionData = new UserRoleSection();
                    String sectionid = array.getString(i);
                    userrolesectionData.setSectionid(sectionid);
                    statement.setInt(1, Integer.valueOf(userrolesection.getUserroleid()));
                    statement.setInt(2, Integer.valueOf(sectionid));
                    statement.setString(3, username);
                    statement.execute();
                    list.add(userrolesectionData);
                }
            }
            connection.commit();
        } catch (Exception e) {
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
    public int getTableDataCount(UserRoleSection parameters) throws Exception {
        int count = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_USERROLESECTION RS INNER JOIN AVN_SECTION S ON RS.SECTIONID=S.SECTIONID INNER JOIN AVN_USER_ROLE UR ON RS.USERROLEID=UR.USERROLEID :where ";
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

    private String getWhere(UserRoleSection parameters) {
        String where = "";
        if (parameters.getUserroleid() != null
                && !parameters.getUserroleid().equalsIgnoreCase("--")) {
            where += "AND RS.USERROLEID LIKE '%" + parameters.getUserroleid().trim() + "%'";
        }
        if (parameters.getSectionid() != null
                && !parameters.getSectionid().equalsIgnoreCase("--")) {
            where += "AND S.SECTIONID LIKE '%" + parameters.getSectionid().trim() + "%'";
        }
        return where;
    }

    @Override
    public List<UserRoleSection> getTableData(UserRoleSection parameters, int minCount, int start) throws Exception {
        List<UserRoleSection> list = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + " FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( SELECT RS.USERROLEID,UR.DESCRIPTION AS URDES,RS.SECTIONID, S.DESCRIPTION AS SDES,TO_CHAR(RS.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')AS LASTUPDATEDDATETIME, TO_CHAR(RS.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')AS CREATEDDATETIME, RS.CREATEDUSER FROM AVN_USERROLESECTION RS INNER JOIN AVN_SECTION S ON RS.SECTIONID=S.SECTIONID INNER JOIN AVN_USER_ROLE UR ON RS.USERROLEID=UR.USERROLEID :where ORDER BY RS.CREATEDDATETIME ASC) TB  "
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
            UserRoleSection userrolesectionData;
            while (resultSet.next()) {
                userrolesectionData = new UserRoleSection();
                userrolesectionData.setUserroleid(resultSet.getString("USERROLEID"));
                userrolesectionData.setUserroleDes(resultSet.getString("URDES"));
                userrolesectionData.setSectionid(resultSet.getString("SECTIONID"));
                userrolesectionData.setSectionDes(resultSet.getString("SDES"));
                userrolesectionData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                userrolesectionData.setLastupdateddatetime(resultSet.getString("LASTUPDATEDDATETIME"));
                userrolesectionData.setCreateduser(resultSet.getString("CREATEDUSER"));
                list.add(userrolesectionData);
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
    public UserRoleSection getUserRoleSection(String userroleid) throws Exception {
        UserRoleSection userrolesectionData = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT RS.USERROLEID,UR.DESCRIPTION AS URDES,RS.SECTIONID, S.DESCRIPTION AS SDES,TO_CHAR(RS.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')AS LASTUPDATEDDATETIME, TO_CHAR(RS.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS')AS CREATEDDATETIME, RS.CREATEDUSER FROM AVN_USERROLESECTION RS INNER JOIN AVN_SECTION S ON RS.SECTIONID=S.SECTIONID INNER JOIN AVN_USER_ROLE UR ON RS.USERROLEID=UR.USERROLEID WHERE RS.USERROLEID=? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userrolesectionData = new UserRoleSection();
                userrolesectionData.setUserroleid(resultSet.getString("USERROLEID"));
                userrolesectionData.setUserroleDes(resultSet.getString("URDES"));
                userrolesectionData.setSectionid(resultSet.getString("SECTIONID"));
                userrolesectionData.setSectionDes(resultSet.getString("SDES"));
                userrolesectionData.setCreateddatetime(resultSet.getString("CREATEDDATETIME"));
                userrolesectionData.setLastupdateddatetime(resultSet.getString("LASTUPDATEDDATETIME"));
                userrolesectionData.setCreateduser(resultSet.getString("CREATEDUSER"));
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
        return userrolesectionData;
    }

    @Override
    public Map<String, String> getAssignedSections(String userroleid) throws Exception {
        Map<String, String> list = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT RS.SECTIONID, S.DESCRIPTION AS SDES FROM AVN_USERROLESECTION RS INNER JOIN AVN_SECTION S ON RS.SECTIONID=S.SECTIONID WHERE RS.USERROLEID=? ORDER BY S.DESCRIPTION DESC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("SECTIONID"), resultSet.getString("SDES"));
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
    public Map<String, String> getNotAssignedSections(String userroleid) throws Exception {
        Map<String, String> list = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT SECTIONID, DESCRIPTION FROM AVN_SECTION WHERE SECTIONID NOT IN (SELECT RS.SECTIONID FROM AVN_USERROLESECTION RS INNER JOIN AVN_SECTION S ON RS.SECTIONID=S.SECTIONID WHERE RS.USERROLEID=?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("SECTIONID"), resultSet.getString("DESCRIPTION"));
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
    public void updateuserRoleSection(UserRoleSection userrolesection, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "DELETE FROM AVN_USERROLESECTION WHERE USERROLEID=?";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setString(1, userrolesection.getUserroleid());
            statement.execute();

            sql = "INSERT INTO AVN_USERROLESECTION (USERROLEID, SECTIONID, CREATEDDATETIME,LASTUPDATEDDATETIME,CREATEDUSER) VALUES (?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?)";
            statement = connection.prepareStatement(sql);
            if (userrolesection.getMultisectionarray() != null && !userrolesection.getMultisectionarray().isEmpty()) {
                JSONArray array = new JSONArray(userrolesection.getMultisectionarray());
                UserRoleSection userrolesectionData;
                for (int i = 0; i < array.length(); i++) {
                    userrolesectionData = new UserRoleSection();
                    String sectionid = array.getString(i);
                    userrolesectionData.setSectionid(sectionid);
                    statement.setInt(1, Integer.valueOf(userrolesection.getUserroleid()));
                    statement.setInt(2, Integer.valueOf(sectionid));
                    statement.setString(3, username);
                    statement.execute();
                }
            }
            connection.commit();
        } catch (Exception e) {
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
    }

    public List<UserRoleSection> getAssignSectionDropdownListByUserRoleID(String userroleid) throws Exception {
        List<UserRoleSection> SectionList = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT RS.SECTIONID, S.DESCRIPTION AS SDES FROM AVN_USERROLESECTION RS INNER JOIN AVN_SECTION S ON RS.SECTIONID=S.SECTIONID WHERE RS.USERROLEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userroleid);
            resultSet = statement.executeQuery();
            SectionList = new ArrayList<>();
            UserRoleSection section;
            while (resultSet.next()) {
                section = new UserRoleSection();
                section.setSectionid(resultSet.getString("SECTIONID"));
                section.setSectionDes(resultSet.getString("SDES"));
                SectionList.add(section);
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
        return SectionList;
    }

    public List<UserRoleSection> getNotAssignSectionDropdownListByUserRoleID(String userrole) throws Exception {
        List<UserRoleSection> NotAssignSectionList = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT SECTIONID, DESCRIPTION FROM AVN_SECTION WHERE SECTIONID NOT IN (SELECT RS.SECTIONID FROM AVN_USERROLESECTION RS INNER JOIN AVN_SECTION S ON RS.SECTIONID=S.SECTIONID WHERE RS.USERROLEID=?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);;
            statement.setInt(1, (Integer.valueOf(userrole)));
            resultSet = statement.executeQuery();
            NotAssignSectionList = new ArrayList<>();
            UserRoleSection section;
            while (resultSet.next()) {
                section = new UserRoleSection();
                section.setSectionid(resultSet.getString("SECTIONID"));
                section.setSectionDes(resultSet.getString("DESCRIPTION"));
                NotAssignSectionList.add(section);
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
        return NotAssignSectionList;
    }

}

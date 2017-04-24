/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.subsection;

import com.avn.ccl.dao.subsection.SubsectionDAO;
import com.avn.ccl.model.subsection.Subsection;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Isuru
 */
public class SubsectionDAOImpl implements SubsectionDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long insertSubSection(Subsection subsection, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long subsectionID = 0;
        try {
            String sql = "INSERT INTO AVN_SUBSECTION (DESCRIPTION,SECTIONID, CREATEDDATETIME, LASTUPDATEDDATETIME,CREATEDUSER,STATUS,ICON,SORTID,URL,CLICKABLE) "
                    + " VALUES (?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,?,?,?,?,?)";
            String[] generatedColumns = {"SUBSECTIONID"};
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setString(1, subsection.getSubsectionDes());
            statement.setString(2, subsection.getSectionid());
            statement.setString(3, username);
            statement.setInt(4, Integer.valueOf(subsection.getStatusid()));
            if (subsection.getIcon() != null && !subsection.getIcon().isEmpty()) {
                statement.setString(5, subsection.getIcon());
            } else {
                statement.setNull(5, Types.VARCHAR);
            }
            statement.setInt(6, Integer.valueOf(subsection.getSortid()));
            statement.setString(7, subsection.getUrl());
            if (subsection.isClickableurl()) {
                statement.setInt(8, MasterDataVarList.CCL_CODE_CLICKABLE_TRUE);
            } else {
                statement.setInt(8, MasterDataVarList.CCL_CODE_CLICKABLE_FALSE);
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                subsectionID = resultSet.getLong(1);
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
        return subsectionID;
    }

    @Override
    public int getTableDataCount(Subsection parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS CNT "
                    + "FROM AVN_SUBSECTION SS "
                    + "INNER JOIN AVN_STATUS ST "
                    + "ON SS.STATUS=ST.STATUSID "
                    + "INNER JOIN AVN_SECTION S "
                    + "ON SS.SECTIONID=S.SECTIONID :where ";
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

    private String getWhere(Subsection parameters) {
        String where = "";
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("subsectionId")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where SS.SUBSECTIONID LIKE '%" + parameters.getInput().trim() + "%'";
        }
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("subsectionDes")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where SS.DESCRIPTION LIKE '%" + parameters.getInput().trim() + "%'";
        }
        return where;
    }

    @Override
    public List<Subsection> getTableData(Subsection parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Subsection> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + " FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( SELECT SS.SUBSECTIONID,SS.DESCRIPTION AS SUBDES,SS.SECTIONID,SS.STATUS, ST.DESCRIPTION AS STDES, S.DESCRIPTION AS SDES,  NVL(SS.ICON,'--')AS ICON, SS.SORTID,SS.URL,SS.CLICKABLE FROM AVN_SUBSECTION SS INNER JOIN AVN_STATUS ST ON SS.STATUS=ST.STATUSID INNER JOIN AVN_SECTION S ON SS.SECTIONID=S.SECTIONID :where ORDER BY SS.SUBSECTIONID ASC) TB  "
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
            Subsection subsectionData;
            while (resultSet.next()) {
                subsectionData = new Subsection();
                subsectionData.setSubsectionId(resultSet.getString("SUBSECTIONID"));
                subsectionData.setSubsectionDes(resultSet.getString("SUBDES"));
                subsectionData.setSectionid(resultSet.getString("SECTIONID"));
                subsectionData.setSectionDes(resultSet.getString("SDES"));
                subsectionData.setStatusid(resultSet.getString("STATUS"));
                subsectionData.setStatusDes(resultSet.getString("STDES"));
                subsectionData.setUrl(resultSet.getString("URL"));
                subsectionData.setIcon(resultSet.getString("ICON"));
                subsectionData.setSortid(resultSet.getString("SORTID"));
                if (resultSet.getString("CLICKABLE").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CLICKABLE_TRUE))) {
                    subsectionData.setClickable("YES");
                } else {
                    subsectionData.setClickable("NO");
                }
                list.add(subsectionData);
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
    public Subsection getSubectionBySubSectionId(String subsectionId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Subsection subsectionData = null;
        try {
            String sql = " SELECT SS.SUBSECTIONID,SS.DESCRIPTION AS SUBDES,SS.SECTIONID,SS.STATUS, ST.DESCRIPTION AS STDES, S.DESCRIPTION AS SDES,"
                    + " SS.ICON,SS.SORTID,SS.URL, SS.CLICKABLE FROM AVN_SUBSECTION SS INNER JOIN AVN_STATUS ST ON SS.STATUS=ST.STATUSID INNER JOIN AVN_SECTION S ON "
                    + " SS.SECTIONID=S.SECTIONID WHERE SS.SUBSECTIONID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(subsectionId));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subsectionData = new Subsection();
                subsectionData.setSubsectionId(resultSet.getString("SUBSECTIONID"));
                subsectionData.setSubsectionDes(resultSet.getString("SUBDES"));
                subsectionData.setSectionid(resultSet.getString("SECTIONID"));
                subsectionData.setSectionDes(resultSet.getString("SDES"));
                subsectionData.setStatusid(resultSet.getString("STATUS"));
                subsectionData.setStatusDes(resultSet.getString("STDES"));
                subsectionData.setIcon(resultSet.getString("ICON"));
                subsectionData.setUrl(resultSet.getString("URL"));
                subsectionData.setSortid(resultSet.getString("SORTID"));
                if (resultSet.getString("CLICKABLE").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CLICKABLE_TRUE))) {
                    subsectionData.setClickableurl(true);
                } else {
                    subsectionData.setClickableurl(false);
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
        return subsectionData;
    }

    @Override
    public void updateSubSection(Subsection subsection, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_SUBSECTION SET DESCRIPTION=?,LASTUPDATEDDATETIME=CURRENT_TIMESTAMP,CREATEDUSER=?,STATUS=?,URL=?,ICON=?,SORTID=?,CLICKABLE=? WHERE SUBSECTIONID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, subsection.getSubsectionDes());
            statement.setString(2, username);
            statement.setInt(3, Integer.valueOf(subsection.getStatusid()));
            statement.setString(4, subsection.getUrl());
            statement.setString(5, subsection.getIcon());
            statement.setInt(6, Integer.valueOf(subsection.getSortid()));
               if (subsection.isClickableurl()) {
                statement.setInt(7, MasterDataVarList.CCL_CODE_CLICKABLE_TRUE);
            } else {
                statement.setInt(7, MasterDataVarList.CCL_CODE_CLICKABLE_FALSE);
            }
            statement.setString(8, subsection.getSubsectionId());
          
            statement.executeUpdate();
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

    @Override
    public List<Subsection> getSubSectionsByUserroleAndSectionID(int userrole, String sectionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Subsection> subsections;
        try {
            String sql = "SELECT "
                    + "IFNULL(SUS.DESCRIPTION, '') AS DESCRIPTION, "
                    + "IFNULL(SUS.ICON, '') AS ICON, "
                    + "IFNULL(SUS.URL, '') AS URL, "
                    + "CLICKABLE "
                    + "FROM subsection SUS "
                    + "INNER JOIN userrolesubsection URSUS ON URSUS.SUBSECTIONID = SUS.SUBSECTIONID "
                    + "WHERE URSUS.USERROLEID = ? AND URSUS.SECTIONID = ? "
                    + "ORDER BY SUS.SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userrole);
            statement.setInt(2, Integer.valueOf(sectionid));
            resultSet = statement.executeQuery();
            subsections = new ArrayList<>();
            Subsection subsection;
            while (resultSet.next()) {
                subsection = new Subsection();
                subsection.setSubsectionDes(resultSet.getString("DESCRIPTION"));
                subsection.setIcon(resultSet.getString("ICON"));
                subsection.setUrl(resultSet.getString("URL"));
                subsection.setClickable(resultSet.getString("CLICKABLE"));
                subsections.add(subsection);
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
        return subsections;
    }

}

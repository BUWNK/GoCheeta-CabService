/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.section;

import com.avn.ccl.dao.section.SectionDAO;
import com.avn.ccl.model.section.Section;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Isuru
 */
public class SectionDAOImpl implements SectionDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long insertSection(Section section, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long sectionID = 0;
        try {
            String sql = "INSERT INTO AVN_SECTION (DESCRIPTION, CREATEDDATETIME, LASTUPDATEDDATETIME,CREATEDUSER,STATUS,SECTIONLEVEL,PARENTSECTION, "
                    + " ICON,SORTID,ONLYPARENT,URL) VALUES (?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?)";
            String[] generatedColumns = {"SECTIONID"};
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setString(1, section.getSectionDes());
            statement.setString(2, username);
            statement.setInt(3, Integer.valueOf(section.getStatusid()));
            statement.setInt(4, Integer.valueOf(section.getSectionlevel()));
            if (section.getParentsection() != null && !section.getParentsection().isEmpty()) {
                statement.setInt(5, Integer.valueOf(section.getParentsection()));
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setString(6, section.getIcon());
            statement.setInt(7, Integer.valueOf(section.getSortid()));
            if (section.isOnlyparentsection()) {
                statement.setInt(8, MasterDataVarList.CCL_CODE_ONLY_PARENT_SECTION_TRUE);
            } else {
                statement.setInt(8, MasterDataVarList.CCL_CODE_ONLY_PARENT_SECTION_FALSE);
            }
            if (section.getUrl() != null && !section.getUrl().isEmpty()) {
                statement.setString(9, section.getUrl());
            } else {
                statement.setNull(9, Types.VARCHAR);
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                sectionID = resultSet.getLong(1);
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
        return sectionID;
    }

    @Override
    public List<Section> getParntSection(String sectionlevel) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Section> Parentlist = null;
        try {
            String sql = "SELECT SECTIONID,DESCRIPTION,SORTID FROM AVN_SECTION WHERE SECTIONLEVEL =? ORDER BY SORTID ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer.valueOf(sectionlevel)) - 1);
            resultSet = statement.executeQuery();
            Parentlist = new ArrayList<>();
            Section section;
            while (resultSet.next()) {
                section = new Section();
                section.setSectionid(resultSet.getString("SECTIONID"));
                section.setSectionDes(resultSet.getString("DESCRIPTION"));
                Parentlist.add(section);
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
        return Parentlist;
    }

    @Override
    public Section getSectionBySectionId(String sectionId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Section sectionData = null;
        try {
            String sql = " SELECT SE.SECTIONID,SE.DESCRIPTION AS SEDES, NVL(SE.PARENTSECTION, '0') AS PARENTSECTION, "
                    + " SE.SECTIONLEVEL,SE.CREATEDUSER,SE.STATUS,ST.DESCRIPTION AS STDES,SE.SORTID,SE.ICON,SE.ONLYPARENT, SE.URL FROM AVN_SECTION SE "
                    + " INNER JOIN AVN_STATUS ST ON SE.STATUS=ST.STATUSID WHERE SE.SECTIONID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sectionId));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sectionData = new Section();
                sectionData.setSectionid(resultSet.getString("SECTIONID"));
                sectionData.setSectionDes(resultSet.getString("SEDES"));
                sectionData.setSectionlevel(resultSet.getString("SECTIONLEVEL"));
                sectionData.setParentsection(resultSet.getString("PARENTSECTION"));
                sectionData.setIcon(resultSet.getString("ICON"));
                sectionData.setStatusid(resultSet.getString("STATUS"));
                sectionData.setStatusDes(resultSet.getString("STDES"));
                sectionData.setSortid(resultSet.getString("SORTID"));
                if (resultSet.getInt("ONLYPARENT") == MasterDataVarList.CCL_CODE_ONLY_PARENT_SECTION_TRUE) {
                    sectionData.setOnlyparentsection(true);
                } else {
                    sectionData.setOnlyparentsection(false);
                }
                if (sectionData.getUrl() != null && !sectionData.getUrl().isEmpty()) {
                    sectionData.setUrl(resultSet.getString("URL"));
                } else {

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
        return sectionData;
    }

    @Override
    public int getTableDataCount(Section parameters) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) CNT FROM AVN_SECTION SE INNER JOIN AVN_STATUS ST ON SE.STATUS=ST.STATUSID :where ";
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

    private String getWhere(Section parameters) {
        String where = "";
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("sectionid")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where SE.SECTIONID LIKE '%" + parameters.getInput().trim() + "%'";
        }
        if (parameters.getSearchoption() != null
                && !parameters.getSearchoption().isEmpty()
                && parameters.getSearchoption().equalsIgnoreCase("sectiondes")
                && parameters.getInput() != null
                && !parameters.getInput().isEmpty()) {
            where += "where SE.DESCRIPTION LIKE '%" + parameters.getInput().trim() + "%'";
        }
        return where;
    }

    @Override
    public List<Section> getTableData(Section parameters, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Section> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + " FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( SELECT SE.SECTIONID, SE.DESCRIPTION AS SEDES, SE.SECTIONLEVEL, NVL((SELECT DESCRIPTION FROM AVN_SECTION WHERE SECTIONID = SE.PARENTSECTION), '--') AS PARENTSECTION, "
                    + " SE.CREATEDUSER,SE.STATUS,ST.DESCRIPTION AS STDES,SE.SORTID, NVL(SE.ICON,'--')ICON FROM AVN_SECTION SE INNER JOIN AVN_STATUS ST ON "
                    + " SE.STATUS=ST.STATUSID :where ORDER BY SE.SECTIONID) TB  "
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
            Section sectionData;
            while (resultSet.next()) {
                sectionData = new Section();
                sectionData.setSectionid(resultSet.getString("SECTIONID"));
                sectionData.setSectionDes(resultSet.getString("SEDES"));
                sectionData.setSectionlevel(resultSet.getString("SECTIONLEVEL"));
                sectionData.setParentsection(resultSet.getString("PARENTSECTION"));
                sectionData.setIcon(resultSet.getString("ICON"));
                sectionData.setStatusid(resultSet.getString("STATUS"));
                sectionData.setStatusDes(resultSet.getString("STDES"));
                sectionData.setSortid(resultSet.getString("SORTID"));
                list.add(sectionData);
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
    public void updateSection(Section section, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_SECTION SET DESCRIPTION=?,LASTUPDATEDDATETIME=CURRENT_TIMESTAMP,CREATEDUSER=?,STATUS=?,SECTIONLEVEL=?,PARENTSECTION=?, "
                    + " ICON=?,SORTID=?,URL=?,ONLYPARENT=? WHERE SECTIONID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, section.getSectionDes());
            statement.setString(2, username);
            statement.setInt(3, Integer.valueOf(section.getStatusid()));
            statement.setInt(4, Integer.valueOf(section.getSectionlevel()));
            if (section.getParentsection() != null && !section.getParentsection().isEmpty()) {
                statement.setInt(5, Integer.valueOf(section.getParentsection()));
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setString(6, section.getIcon());
            statement.setInt(7, Integer.valueOf(section.getSortid()));
            if (section.getUrl() != null && !section.getUrl().isEmpty()) {
                statement.setString(8, section.getUrl());
            } else {
                statement.setNull(8, Types.VARCHAR);
            }
            if (section.isOnlyparentsection()) {
                statement.setInt(9, MasterDataVarList.CCL_CODE_ONLY_PARENT_SECTION_TRUE);
            } else {
                statement.setInt(9, MasterDataVarList.CCL_CODE_ONLY_PARENT_SECTION_FALSE);
            }
            statement.setString(10, section.getSectionid());
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

    @Override
    public Map<String, String> getSectionDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT SECTIONID,DESCRIPTION,SORTID FROM AVN_SECTION WHERE STATUS=3 ORDER BY SORTID ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("SECTIONID"), resultSet.getString("DESCRIPTION"));
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

    @Override
    public Map<String, String> getMultiSectionDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT SECTIONID,DESCRIPTION,SORTID FROM AVN_SECTION WHERE STATUS=3 ORDER BY SORTID ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("SECTIONID"), resultSet.getString("DESCRIPTION"));
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

    @Override
    public List<Section> getSectionDropdownListByRoleID(String roleid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Section> SectionList = null;
        try {
            String sql = "SELECT S.SECTIONID,S.DESCRIPTION, RS.USERROLEID, SORTID FROM AVN_SECTION S INNER JOIN AVN_USERROLESECTION RS ON S.SECTIONID=RS.SECTIONID WHERE STATUS=3 AND RS.USERROLEID=? ORDER BY SORTID ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer.valueOf(roleid)));
            resultSet = statement.executeQuery();
            SectionList = new ArrayList<>();
            Section section;
            while (resultSet.next()) {
                section = new Section();
                section.setSectionid(resultSet.getString("SECTIONID"));
                section.setSectionDes(resultSet.getString("DESCRIPTION"));
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

    @Override
    public List<Section> getSectionByUserrole(int userrole) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Section> list = null;
        try {
            String sql = "SELECT SEC.SECTIONID, SEC.DESCRIPTION, SEC.SECTIONLEVEL, SEC.PARENTSECTION, SEC.ICON "
                    + "FROM AVN_SECTION SEC "
                    + "INNER JOIN AVN_USERROLESECTION USEC ON USEC.SECTIONID = SEC.SECTIONID "
                    + "WHERE USEC.USERROLEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userrole);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            Section section;
            while (resultSet.next()) {
                section = new Section();
                section.setSectionid(resultSet.getString("SECTIONID"));
                section.setSectionDes(resultSet.getString("DESCRIPTION"));
                section.setSectionlevel(resultSet.getString("SECTIONLEVEL"));
                section.setParentsection(resultSet.getString("PARENTSECTION"));
                section.setIcon(resultSet.getString("ICON"));
                list.add(section);
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
        return list;
    }

    @Override
    public List<Section> getSectionLevelZeroByUserrole(int userrole) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Section> list = null;
        try {
            String sql = "SELECT SEC.SECTIONID, SEC.DESCRIPTION, SEC.SECTIONLEVEL, SEC.PARENTSECTION, SEC.ICON, SEC.ONLYPARENT, SEC.URL "
                    + "FROM AVN_SECTION SEC "
                    + "INNER JOIN AVN_USERROLESECTION USEC ON USEC.SECTIONID = SEC.SECTIONID "
                    + "WHERE USEC.USERROLEID = ? AND PARENTSECTION IS NULL "
                    + "ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userrole);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            Section section;
            while (resultSet.next()) {
                section = new Section();
                section.setSectionid(resultSet.getString("SECTIONID"));
                section.setSectionDes(resultSet.getString("DESCRIPTION"));
                section.setSectionlevel(resultSet.getString("SECTIONLEVEL"));
                section.setParentsection(resultSet.getString("PARENTSECTION"));
                section.setIcon(resultSet.getString("ICON"));
                section.setOnlyparent(resultSet.getString("ONLYPARENT"));
                section.setUrl(resultSet.getString("URL"));
                list.add(section);
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
        return list;
    }

    @Override
    public List<Section> getSectionLowLevelByUserrole(int userrole) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Section> list = null;
        try {
            String sql = "SELECT SEC.SECTIONID, SEC.DESCRIPTION, SEC.SECTIONLEVEL, SEC.PARENTSECTION, SEC.ICON "
                    + "FROM AVN_SECTION SEC "
                    + "INNER JOIN AVN_USERROLESECTION USEC ON USEC.SECTIONID = SEC.SECTIONID "
                    + "WHERE USEC.USERROLEID = ?  AND PARENTSECTION IS NOT NULL "
                    + "ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userrole);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            Section section;
            while (resultSet.next()) {
                section = new Section();
                section.setSectionid(resultSet.getString("SECTIONID"));
                section.setSectionDes(resultSet.getString("DESCRIPTION"));
                section.setSectionlevel(resultSet.getString("SECTIONLEVEL"));
                section.setParentsection(resultSet.getString("PARENTSECTION"));
                section.setIcon(resultSet.getString("ICON"));
                list.add(section);
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
        return list;
    }

}

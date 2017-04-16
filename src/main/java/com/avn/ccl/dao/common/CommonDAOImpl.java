/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.common;

import com.avn.ccl.dao.common.CommonDAO;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author : Office Isuru
 * @Document : DbdateandtimeDAOImpl
 * @Date : Sep 28, 2015 4:43:45 PM
 */
@Repository("commonDAO")
public class CommonDAOImpl implements CommonDAO {

    @Autowired
    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Date getCurentTimesStamp() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Date curentdatetime = null;
        try {
            String sql = "SELECT CURRENT_TIMESTAMP AS CURRENTDATETIME FROM DUAL";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                curentdatetime = new Date(resultSet.getTimestamp("CURRENTDATETIME").getTime());
            }
        } catch (SQLException sqle) {
            throw sqle;
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
        return curentdatetime;
    }

    @Override
    public JSONArray getAssignedTaskList(int userroleid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray objList = new JSONArray();
        try {
            String sql = "SELECT USRT.TASKID ,USRSUS.SECTIONID, USRSUS.SUBSECTIONID, USRSUS.USERROLEID FROM AVN_USERROLETASK USRT INNER JOIN AVN_USERROLESUBSECTION USRSUS ON USRSUS.USERROLESUBSECTIONID = USRT.USERROLESUBSECTIONID INNER JOIN AVN_USER_ROLE UR ON USRSUS.USERROLEID=UR.USERROLEID WHERE UR.USERROLEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userroleid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONObject obj = new JSONObject();
                obj.put("taskid", resultSet.getString("TASKID"));
                obj.put("sectionid", resultSet.getString("SECTIONID"));
                obj.put("subsectionid", resultSet.getString("SUBSECTIONID"));
                obj.put("userroleid", resultSet.getString("USERROLEID"));

                objList.put(obj);
            }
        } catch (SQLException | JSONException ex) {
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
        return objList;
    }

    @Override
    public boolean checkPrivilage(String sectionid, String subsectionid, int task, JSONArray objList) throws Exception {
        boolean privilage = false;
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(task))) {
                privilage = true;
                break;
            } else {
                privilage = false;
            }
        }
        return privilage;
    }

    @Override
    public int getMaxinvaidLoginCount() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int max_invalid_attempts = 999;
        try {
            String sql = "SELECT PARAMETERVALUE FROM AVN_ORGANIZATIONPARAMETERS WHERE PARAMETERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, MasterDataVarList.CCL_CODE_MAX_INVALID_LOGIN_COUNT);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                max_invalid_attempts = Integer.valueOf(resultSet.getString("PARAMETERVALUE"));
            }
        } catch (SQLException | NumberFormatException e) {
            throw e;
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
        return max_invalid_attempts;
    }

    @Override
    public String getOgrParaValueByRecordId(int id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String value = "";
        try {
            String sql = "SELECT PARAMETERVALUE FROM AVN_ORGANIZATIONPARAMETERS WHERE PARAMETERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                value = resultSet.getString("PARAMETERVALUE");
            }
        } catch (SQLException | NumberFormatException e) {
            throw e;
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
        return value;
    }

    @Override
    public Date[] getCurrentQuerter() throws SQLException {
        Date[] querter = new Date[2];
        String query = "SELECT "
                + "  D AS QUERTER_START, "
                + "  ADD_MONTHS(D, 3) - 1 AS QUERTER_END "
                + "FROM (SELECT TRUNC(CURRENT_TIMESTAMP, 'Q') AS D FROM DUAL)";
        List<Map<String, Object>> list = new JdbcTemplate(dataSource).queryForList(query);
        querter[0] = (Date) list.get(0).get("QUERTER_START");
        querter[1] = (Date) list.get(0).get("QUERTER_END");
        return querter;
    }

        public JSONArray getAssignedChartList(int userroleid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        JSONArray objList = new JSONArray();
        try {
            String sql = "SELECT URD.WIDGETID,URD.SORTID,URD.USERROLEID,URD.STATUS, WW.CSS FROM AVN_USERROLEDASHBOARDWIDGET URD INNER JOIN AVN_WEDGETWIDTH WW ON URD.WIDGETWIDTHID=WW.WIDGETWIDTHID WHERE USERROLEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userroleid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JSONObject obj = new JSONObject();
                obj.put("wedgetid", resultSet.getString("WIDGETID"));
                obj.put("sortid", resultSet.getString("SORTID"));
                obj.put("userroleid", resultSet.getString("USERROLEID"));
                obj.put("statusid", resultSet.getString("STATUS"));
                obj.put("css", resultSet.getString("CSS"));
                objList.put(obj);
            }
        } catch (SQLException | JSONException ex) {
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
        return objList;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.target;

import com.avn.ccl.model.performancedashboard.Performancedashboard;
import com.avn.ccl.model.target.Target;
import com.avn.ccl.model.target.TargetRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 * @Author : Roshen Dilshan
 * @Document : TargetDAOImpl
 * @Created on : May 10, 2016, 10:36:47 AM
 */
@Repository("targetDAO")
public class TargetDAOImpl implements TargetDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public Target getTargetByID(long targetid) throws SQLException {
        String query = "SELECT TARGETID, "
                + "  DESCRIPTION, "
                + "  PRODUCTID, "
                + "  REVENUE, "
                //                + "  NUMBEROFCONTACT, "
                + "  TAGRETGROUPID, "
                + "  TARGETPERIODID, "
                + "  TARGETSTARTDATE, "
                + "  TARGETENDDATE, "
                //                + "  TARGETTYPE, "
                //                + "  ACTIVITYTPE, "
                + "  CREATEDDATETIME, "
                + "  LASTUPDATEDDATETIME, "
                + "  CREATEDUSER "
                + "FROM AVN_TARGET "
                + "WHERE TARGETID = ?";
        return (Target) new JdbcTemplate(dataSource).queryForObject(query, new Object[]{targetid}, new TargetRowMapper());
    }

    @Override
    public List<Target> getTargetDropDownList(String username) throws SQLException {
        String query = "SELECT TAR.TARGETID, "
                + "  (TAR.DESCRIPTION "
                + "  || ' - ' "
                + "  || PRO.DESCRIPTION) AS DESCRIPTION "
                + "FROM AVN_TARGET TAR "
                + "INNER JOIN AVN_PRODUCT PRO "
                + "ON TAR.PRODUCTID     = PRO.PRODUCTID "
                + "WHERE TAR.PRODUCTID IN "
                + "  (SELECT PRODUCTID FROM AVN_EMPLOYEEPRODUCT WHERE EMPLOYEEID =(SELECT EMPLOYEEID FROM AVN_SYSTEMUSER WHERE USERID=?))";
        return new JdbcTemplate(dataSource).query(query, new Object[]{username}, new BeanPropertyRowMapper(Target.class));
    }

    @Override
    public Map<String, String> getDropdownValueList(String username, Performancedashboard performancedashboard) throws SQLException, DataAccessException {
        String query = "SELECT TAR.TARGETID AS ID, "
                + "  (TAR.DESCRIPTION "
                + "  || ' - ' "
                + "  || PRO.DESCRIPTION) AS DESCRIPTION "
                + "FROM AVN_TARGET TAR "
                + "INNER JOIN AVN_PRODUCT PRO "
                + "ON TAR.PRODUCTID     = PRO.PRODUCTID "
                + "WHERE TAR.PRODUCTID IN "
                + "  (SELECT PRODUCTID FROM AVN_EMPLOYEEPRODUCT WHERE EMPLOYEEID = (SELECT EMPLOYEEID FROM AVN_SYSTEMUSER WHERE USERID=?) "
                + "  ) "
                + "AND TAR.TARGETPERIODID  = ? "
                + "AND TAR.TARGETSTARTDATE BETWEEN ? AND ? "
                + "AND TAR.TARGETENDDATE  BETWEEN ? AND ?";
        return new JdbcTemplate(dataSource).query(query,
                new Object[]{username, performancedashboard.getTargetPeriod(),
                    performancedashboard.getFromDate(),
                    performancedashboard.getToDate(),
                    performancedashboard.getFromDate(),
                    performancedashboard.getToDate()},
                new ResultSetExtractor<Map>() {
                    @Override
                    public Map extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        Map<String, String> list = new LinkedHashMap<>();
                        while (resultSet.next()) {
                            list.put(resultSet.getString("ID"), resultSet.getString("DESCRIPTION"));
                        }
                        return list;
                    }
                });
    }

}

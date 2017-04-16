/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.targetperiod;

import com.avn.ccl.model.targetperiod.TargetPeriod;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author : Roshen Dilshan
 * @Document : TargetPeriodDAOImpl
 * @Created on : May 9, 2016, 3:25:07 PM
 */
@Repository("targetPeriodDAO")
public class TargetPeriodDAOImpl implements TargetPeriodDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public TargetPeriod getTargetPeriodById(int periodid) throws SQLException {
        String query = "SELECT PERIODID, "
                + "  DESCRIPTION, "
                + "  SORTID, "
                + "  PERIODINMONTHS, "
                + "  CREATEDUSER, "
                + "  CREATEDDATETIME, "
                + "  LASTUPDATEDDATETIME "
                + "FROM AVN_TARGETPERIOD "
                + "WHERE PERIODID = ?";
        return (TargetPeriod) new JdbcTemplate(dataSource).queryForObject(query, new Object[]{periodid}, new BeanPropertyRowMapper(TargetPeriod.class));
    }

    @Override
    public List<TargetPeriod> getTargetPeriodDropDownList() throws SQLException {
        String query = "SELECT PERIODID, DESCRIPTION FROM AVN_TARGETPERIOD";
        return new JdbcTemplate(dataSource).query(query, new BeanPropertyRowMapper(TargetPeriod.class));
    }

}

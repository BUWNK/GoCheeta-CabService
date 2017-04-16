/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.activitytarget;

import com.avn.ccl.model.activitytarget.ActivityTarget;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author : Roshen Dilshan
 * @Document : ActivityTargetDAOImpl
 * @Created on : Jul 10, 2016, 9:12:24 AM
 */
@Repository("activityTargetDAO")
public class ActivityTargetDAOImpl implements ActivityTargetDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<ActivityTarget> getActivityTargetListByTargetId(long targetId) throws SQLException {
        String query = "SELECT ACTIVITYTARGETID, "
                + "TARGETID, "
                + "ACTIVITYTYPEID, "
                + "COUNT "
                + "FROM AVN_ACTIVITYTARGET "
                + "WHERE TARGETID = ?";
        return new JdbcTemplate(dataSource).query(query, new Object[]{targetId}, new BeanPropertyRowMapper(ActivityTarget.class));
    }

}

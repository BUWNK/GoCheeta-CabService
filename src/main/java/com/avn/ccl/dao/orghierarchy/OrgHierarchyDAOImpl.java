/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.orghierarchy;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author : Roshen Dilshan
 * @Document : OrgHierarchyDAOImpl
 * @Created on : Jan 31, 2017, 9:07:00 AM
 */
@Repository("orgHierarchyDAO")
public class OrgHierarchyDAOImpl implements OrghierarchyDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public int getLevelByOrghierarchyId(int orghierarchyid) throws SQLException {
        String query = "SELECT HIERACHYLEVEL FROM AVN_ORGHIERACHY WHERE HIERACHYID = ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{orghierarchyid}, Integer.class);
    }

}

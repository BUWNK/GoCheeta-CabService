/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.target;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * @Author : Roshen Dilshan
 * @Document : TargetRowMapper
 * @Created on : May 11, 2016, 7:42:44 AM
 */
public class TargetRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Target target = new Target();
        target.setTargetid(rs.getLong("TARGETID"));
        target.setDescription(rs.getString("DESCRIPTION"));
        target.setProductid(rs.getInt("PRODUCTID"));
        target.setRevenue(rs.getBigDecimal("REVENUE"));
        target.setTargetgroupid(rs.getInt("TAGRETGROUPID"));
        target.setTargetperiodid(rs.getInt("TARGETPERIODID"));
        target.setTargetstartdate(rs.getTimestamp("TARGETSTARTDATE"));
        target.setTargetenddate(rs.getTimestamp("TARGETENDDATE"));
        target.setCreateddatetime(rs.getTimestamp("CREATEDDATETIME"));
        target.setLastupdateddatetime(rs.getTimestamp("LASTUPDATEDDATETIME"));
        target.setCreateduser(rs.getString("CREATEDUSER"));
        return target;
    }

}

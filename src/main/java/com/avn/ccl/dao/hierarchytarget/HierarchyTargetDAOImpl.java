/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.hierarchytarget;

import com.avn.ccl.model.hierarchytarget.HierarchyTarget;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import javax.sql.DataSource;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author : Roshen Dilshan
 * @Document : HierarchyTargetDAOImpl
 * @Created on : Feb 23, 2017, 9:42:10 AM
 */
@Repository("hierarchyTargetDAO")
public class HierarchyTargetDAOImpl implements HierarchyTargetDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public int getTableDataCount() throws SQLException {
        String query = "SELECT COUNT(*) AS CNT "
                + "FROM AVN_ORGHIERACHY ORGH "
                + "LEFT OUTER JOIN AVN_ORGHIERACHYTARGET ORGHT "
                + "ON ORGH.HIERACHYID = ORGHT.HIERARCHYID "
                + "ORDER BY ORGH.HIERACHYLEVEL DESC";
        return new JdbcTemplate(dataSource).queryForObject(query, Integer.class);
    }

    @Override
    public List<JSONObject> getTableData(int minCount, int start) throws SQLException {
        String query = "SELECT * "
                + "FROM "
                + "  (SELECT TB.*, "
                + "    ROWNUM AS ROWNUMER "
                + "  FROM "
                + "    (SELECT ORGH.HIERACHYID, "
                + "      ORGH.NAME, "
                + "      NVL(ORGHT.TARGETAMOUNT, '0') AS AMOUNT "
                + "    FROM AVN_ORGHIERACHY ORGH "
                + "    LEFT OUTER JOIN AVN_ORGHIERACHYTARGET ORGHT "
                + "    ON ORGH.HIERACHYID = ORGHT.HIERARCHYID "
                + "    ORDER BY ORGH.HIERACHYLEVEL DESC "
                + "    ) TB "
                + "  WHERE ROWNUM <= ? "
                + "  ) "
                + "WHERE ROWNUMER > ?";
        return new JdbcTemplate(dataSource).query(query, new Object[]{start + minCount, start}, new HierarchyTargetSetRowMpper());
    }

    @Override
    public boolean isAlreadyExists(int hierarchyid) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT FROM AVN_ORGHIERACHYTARGET WHERE HIERARCHYID = ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{hierarchyid}, Integer.class) != 0;
    }

    @Override
    public void insetHierarchyTarget(HierarchyTarget hierarchyTarget, String username) throws SQLException {
        String query = "INSERT "
                + "INTO AVN_ORGHIERACHYTARGET "
                + "  ( "
                + "    HIERARCHYID, "
                + "    TARGETAMOUNT, "
                + "    CREATEDUSER, "
                + "    LASTUPDATEDDATETIME, "
                + "    CREATEDDATETIME "
                + "  ) "
                + "  VALUES "
                + "  ( ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        new JdbcTemplate(dataSource).update(query, new Object[]{hierarchyTarget.getHierarchyid(), hierarchyTarget.getTargetamount(), username});
    }

    @Override
    public void updateHierarchyTarget(HierarchyTarget hierarchyTarget) throws SQLException {
        String query = "UPDATE AVN_ORGHIERACHYTARGET "
                + "SET TARGETAMOUNT      = ?, "
                + "  LASTUPDATEDDATETIME = CURRENT_TIMESTAMP "
                + "WHERE HIERARCHYID     = ?";
        new JdbcTemplate(dataSource).update(query, new Object[]{hierarchyTarget.getTargetamount(), hierarchyTarget.getHierarchyid()});
    }

    public class HierarchyTargetSetRowMpper implements RowMapper<JSONObject> {

        @Override
        public JSONObject mapRow(ResultSet rs, int rowNum) throws SQLException {
            JSONObject object = new JSONObject();
            object.put("hierarchyid", rs.getString("HIERACHYID"));
            object.put("hierarchy", rs.getString("NAME"));
            String amount = "<div class=\"row smart-form\">"
                    + " <div class=\"col-xs-12\">"
                    + "     <section style=\"margin-bottom: 0px;\">"
                    + "         <label class=\"input\">"
                    + "             <i class=\"icon-prepend fa fa-money\"></i>"
                    + "             <input type=\"text\" style=\"text-align:right;\" class=\"amount\" value=\"" + new DecimalFormat("#,###.00").format(rs.getBigDecimal("AMOUNT").setScale(2)).toString() + "\"/>"
                    + "         </label>"
                    + "     </section>"
                    + " </div>"
                    + "</div>";
            object.put("amount", amount);
            object.put("action", "<div class=\"row\">"
                    + " <div class=\"col-xs-offset-4 col-xs-4\">"
                    + "     <a class=\"btn btn-xs btn-success action\" href=\"javascript:void(0);\"><i class=\"fa fa-save\"></i>  Update</a>"
                    + " </div>"
                    + "</div>");
            return object;
        }
    }

}

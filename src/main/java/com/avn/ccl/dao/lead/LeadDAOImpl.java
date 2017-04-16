/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.lead;

import com.avn.ccl.model.lead.BranchPerformance;
import com.avn.ccl.model.lead.LeadChartQueryParameters;
import com.avn.ccl.model.lead.RegionPerformance;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author : Roshen Dilshan
 * @Document : LeadDAOImpl
 * @Created on : May 12, 2016, 6:45:47 AM
 */
@Repository("leadDAO")
public class LeadDAOImpl implements LeadDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public BigDecimal getAchievedValue(LeadChartQueryParameters chartQueryParameters) throws SQLException {
        String query = "SELECT SUM(CONFIRMEDAMOUNT) AS AMOUNT "
                + "FROM AVN_LEAD "
                + "WHERE STATUS = ? "
                + "AND PRODUCTID = ? "
                + "AND SALECLOSEDDATE BETWEEN ? AND ?";
        return new JdbcTemplate(dataSource).queryForObject(query,
                new Object[]{
                    chartQueryParameters.getStatusid(),
                    chartQueryParameters.getProductid(),
                    chartQueryParameters.getFromdate(),
                    chartQueryParameters.getTodate()
                }, BigDecimal.class);
    }

    @Override
    public BigDecimal getAchievedValueForUser(LeadChartQueryParameters chartQueryParameters) throws SQLException {
        String query = "SELECT SUM(CONFIRMEDAMOUNT) AS AMOUNT "
                + "FROM AVN_LEAD "
                + "WHERE STATUS = ? "
                + "AND PRODUCTID = ? "
                + "AND SALECLOSEDDATE BETWEEN ? AND ? "
                + "AND CREATEDUSER = ?";
        return new JdbcTemplate(dataSource).queryForObject(query,
                new Object[]{
                    chartQueryParameters.getStatusid(),
                    chartQueryParameters.getProductid(),
                    chartQueryParameters.getFromdate(),
                    chartQueryParameters.getTodate(),
                    chartQueryParameters.getUsername()
                }, BigDecimal.class);
    }

    @Override
    public BigDecimal getForcastValue(LeadChartQueryParameters chartQueryParameters) throws SQLException {
        String query = "SELECT SUM(LEADAMOUNT) AS AMOUNT  "
                + "FROM AVN_LEAD  "
                + "WHERE STATUS <> ?  "
                + "AND PRODUCTID = ?  "
                + "AND FORCASTUNTILDATE BETWEEN ? AND ?";
        return new JdbcTemplate(dataSource).queryForObject(query,
                new Object[]{
                    chartQueryParameters.getStatusid(),
                    chartQueryParameters.getProductid(),
                    chartQueryParameters.getFromdate(),
                    chartQueryParameters.getTodate()
                }, BigDecimal.class);
    }

    @Override
    public long getLeadCountForStatusUserCreatedDateRange(String username, int status, Date[] range) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT FROM AVN_LEAD WHERE STATUS = ? AND CREATEDUSER = ? AND CREATEDDATETIME BETWEEN ? AND ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{status, username, range[0], range[1]}, Long.class);
    }

    @Override
    public long getLeadCountForStatusUserUpdatedDateRange(String username, int status, Date[] range) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT FROM AVN_LEAD WHERE STATUS = ? AND CREATEDUSER = ? AND LASTUPDATEDATETIME BETWEEN ? AND ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{status, username, range[0], range[1]}, Long.class);
    }

    @Override
    public long getLeadContForUserCreatedDateRange(String username, Date[] range) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT FROM AVN_LEAD WHERE CREATEDUSER = ? AND LASTUPDATEDATETIME BETWEEN ? AND ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{username, range[0], range[1]}, Long.class);
    }

    @Override
    public long getLeadCountForUserStatusSalesColseDate(String username, int status, Date[] range) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT FROM AVN_LEAD WHERE STATUS = ? AND CREATEDUSER = ? AND SALECLOSEDDATE BETWEEN ? AND ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{status, username, range[0], range[1]}, Long.class);
    }

    @Override
    public long getLeadCountForUserStatusSalesLostDate(String username, int status, Date[] range) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT FROM AVN_LEAD WHERE STATUS = ? AND CREATEDUSER = ? AND SALELOSTDATE BETWEEN ? AND ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{status, username, range[0], range[1]}, Long.class);
    }

    @Override
    public long getLeadCountForUserStatusForecastDate(String username, int status, Date[] range) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT FROM AVN_LEAD WHERE STATUS = ? AND CREATEDUSER = ? AND FORCASTUNTILDATE BETWEEN ? AND ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{status, username, range[0], range[1]}, Long.class);
    }

    @Override
    public List<BranchPerformance> getBranchPerformanceByTargetID(long targetid) throws SQLException {
        String query = "SELECT BR.ALIASNAME AS BRANCH, "
                + "  BT.TARGET AS BRANCHTARGET, "
                + "  NVL( "
                + "  (SELECT SUM(LD.LEADAMOUNT) AS FOURECASTAMOUNT "
                + "  FROM AVN_EMPLOYEEPRODUCT EP "
                + "  INNER JOIN AVN_SYSTEMUSER SU "
                + "  ON EP.EMPLOYEEID = SU.EMPLOYEEID "
                + "  INNER JOIN AVN_EMPLOYEE EM "
                + "  ON SU.EMPLOYEEID = EM.EMPLOYEEID "
                + "  INNER JOIN AVN_BRANCH BRF "
                + "  ON EM.BRANCHID = BRF.BRANCHID "
                + "  INNER JOIN AVN_LEAD LD "
                + "  ON SU.USERID     = LD.CREATEDUSER "
                + "  WHERE LD.STATUS  = 37 "
                + "  AND EM.BRANCHID  = BR.BRANCHID "
                + "  AND LD.PRODUCTID = TR.PRODUCTID "
                + "  AND LD.FORCASTUNTILDATE BETWEEN TR.TARGETSTARTDATE AND TR.TARGETENDDATE "
                + "  GROUP BY BRF.BRANCHID "
                + "  ), 0) AS FOURECASTAMOUNT , "
                + "  NVL( "
                + "  (SELECT SUM(LD.CONFIRMEDAMOUNT) AS ACHIEVEDAMOUNT "
                + "  FROM AVN_EMPLOYEEPRODUCT EP "
                + "  INNER JOIN AVN_SYSTEMUSER SU "
                + "  ON EP.EMPLOYEEID = SU.EMPLOYEEID "
                + "  INNER JOIN AVN_EMPLOYEE EM "
                + "  ON SU.EMPLOYEEID = EM.EMPLOYEEID "
                + "  INNER JOIN AVN_BRANCH BRA "
                + "  ON EM.BRANCHID = BRA.BRANCHID "
                + "  INNER JOIN AVN_LEAD LD "
                + "  ON SU.USERID     = LD.CREATEDUSER "
                + "  WHERE LD.STATUS  = 38 "
                + "  AND EM.BRANCHID  = BR.BRANCHID "
                + "  AND LD.PRODUCTID = TR.PRODUCTID "
                + "  AND LD.SALECLOSEDDATE BETWEEN TR.TARGETSTARTDATE AND TR.TARGETENDDATE "
                + "  GROUP BY BRA.BRANCHID "
                + "  ), 0) AS ACHIEVEDAMOUNT "
                + "FROM AVN_TARGET TR "
                + "INNER JOIN AVN_REGIONALTARGET RT "
                + "ON TR.TARGETID = RT.TARGETID "
                + "INNER JOIN AVN_BRANCHTARGET BT "
                + "ON RT.REGIONALTARGETID = BT.REGIONALTARGETID "
                + "INNER JOIN AVN_BRANCH BR "
                + "ON BT.BRANCHID    = BR.BRANCHID "
                + "WHERE TR.TARGETID = ?";
        return new JdbcTemplate(dataSource).query(query, new Object[]{targetid}, new BeanPropertyRowMapper(BranchPerformance.class));
    }

    @Override
    public List<RegionPerformance> getRegionPerformanceByTargetID(long targetid) throws SQLException {
        String query = "SELECT RG.REGIONNAME AS REGION, "
                + "  RT.TARGET          AS REGIONTARGET, "
                + "  SUM(NVL( "
                + "  (SELECT SUM(LD.LEADAMOUNT) AS FOURECASTAMOUNT "
                + "  FROM AVN_EMPLOYEEPRODUCT EP "
                + "  INNER JOIN AVN_SYSTEMUSER SU "
                + "  ON EP.EMPLOYEEID = SU.EMPLOYEEID "
                + "  INNER JOIN AVN_EMPLOYEE EM "
                + "  ON SU.EMPLOYEEID = EM.EMPLOYEEID "
                + "  INNER JOIN AVN_BRANCH BRF "
                + "  ON EM.BRANCHID = BRF.BRANCHID "
                + "  INNER JOIN AVN_LEAD LD "
                + "  ON SU.USERID     = LD.CREATEDUSER "
                + "  WHERE LD.STATUS  = 37 "
                + "  AND EM.BRANCHID  = BR.BRANCHID "
                + "  AND LD.PRODUCTID = TR.PRODUCTID "
                + "  AND LD.FORCASTUNTILDATE BETWEEN TR.TARGETSTARTDATE AND TR.TARGETENDDATE "
                + "  GROUP BY BRF.BRANCHID "
                + "  ), 0)) AS FOURECASTAMOUNT , "
                + "  SUM(NVL( "
                + "  (SELECT SUM(LD.CONFIRMEDAMOUNT) AS ACHIEVEDAMOUNT "
                + "  FROM AVN_EMPLOYEEPRODUCT EP "
                + "  INNER JOIN AVN_SYSTEMUSER SU "
                + "  ON EP.EMPLOYEEID = SU.EMPLOYEEID "
                + "  INNER JOIN AVN_EMPLOYEE EM "
                + "  ON SU.EMPLOYEEID = EM.EMPLOYEEID "
                + "  INNER JOIN AVN_BRANCH BRA "
                + "  ON EM.BRANCHID = BRA.BRANCHID "
                + "  INNER JOIN AVN_LEAD LD "
                + "  ON SU.USERID     = LD.CREATEDUSER "
                + "  WHERE LD.STATUS  = 38 "
                + "  AND EM.BRANCHID  = BR.BRANCHID "
                + "  AND LD.PRODUCTID = TR.PRODUCTID "
                + "  AND LD.SALECLOSEDDATE BETWEEN TR.TARGETSTARTDATE AND TR.TARGETENDDATE "
                + "  GROUP BY BRA.BRANCHID "
                + "  ), 0)) AS ACHIEVEDAMOUNT "
                + "FROM AVN_TARGET TR "
                + "INNER JOIN AVN_REGIONALTARGET RT "
                + "ON TR.TARGETID = RT.TARGETID "
                + "INNER JOIN AVN_BRANCHTARGET BT "
                + "ON RT.REGIONALTARGETID = BT.REGIONALTARGETID "
                + "INNER JOIN AVN_BRANCH BR "
                + "ON BT.BRANCHID = BR.BRANCHID "
                + "INNER JOIN AVN_REGION RG "
                + "ON RT.REGIONID    = RG.REGIONID "
                + "WHERE TR.TARGETID = ? "
                + "GROUP BY RG.REGIONNAME, "
                + "  RT.TARGET";
        return new JdbcTemplate(dataSource).query(query, new Object[]{targetid}, new BeanPropertyRowMapper(RegionPerformance.class));
    }

    @Override
    public int getContactLeadCount(long contactid, long productid) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT "
                + "FROM AVN_LEAD LD "
                + "WHERE LD.CONTACTID IN "
                + "  (SELECT CT.CONTACTID "
                + "  FROM AVN_CONTACTS CT "
                + "  WHERE CT.MOBILE = "
                + "    (SELECT CTN.MOBILE FROM AVN_CONTACTS CTN WHERE CTN.CONTACTID = ?) "
                + "  ) "
                + "AND LD.PRODUCTID = ? "
                + "AND LD.STATUS    = ?";
        return new JdbcTemplate(dataSource).queryForObject(query,
                new Object[]{
                    contactid,
                    productid,
                    MasterDataVarList.CCL_CODE_STATUS_LEAD_INITIAL
                }, Integer.class);
    }

    @Override
    public long getFirstLeadIdForProductAndContact(long contactid, long productid) throws SQLException {
        String query = "SELECT MIN(LD.LEADID) AS LEADID "
                + "FROM AVN_LEAD LD "
                + "WHERE LD.CONTACTID IN "
                + "  (SELECT CT.CONTACTID "
                + "  FROM AVN_CONTACTS CT "
                + "  WHERE CT.MOBILE = "
                + "    (SELECT CTN.MOBILE FROM AVN_CONTACTS CTN WHERE CTN.CONTACTID = ?) "
                + "  ) "
                + "AND LD.PRODUCTID = ? "
                + "AND LD.STATUS    = ?";
        return new JdbcTemplate(dataSource).queryForObject(query,
                new Object[]{
                    contactid,
                    productid,
                    MasterDataVarList.CCL_CODE_STATUS_LEAD_INITIAL
                }, Long.class);
    }

    @Override
    public long getLeadCreatedEmployeeId(long leadid) throws SQLException {
        String query = "SELECT SU.EMPLOYEEID "
                + "FROM AVN_SYSTEMUSER SU "
                + "INNER JOIN AVN_LEAD LD "
                + "ON SU.USERID    = LD.CREATEDUSER "
                + "WHERE LD.LEADID = ?";
        return new JdbcTemplate(dataSource).queryForObject(query, new Object[]{leadid}, Long.class);
    }

    @Override
    public int getRegionalPerformanceCount(int region) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT "
                + "FROM AVN_PRODUCTCATEGORY PRDCT, "
                + "  AVN_TERRITORYMAPEMPLOYEE TME "
                + "INNER JOIN AVN_BRANCH BR "
                + "ON BR.BRANCHID = TME.TERRITORYMAPID "
                + "INNER JOIN AVN_EMPLOYEE EMP "
                + "ON TME.EMPLOYEEID     = EMP.EMPLOYEEID "
                + "WHERE TME.EMPLOYEEID IN "
                + "  (SELECT EMP.EMPLOYEEID FROM AVN_EMPLOYEE EMP WHERE EMP.HIERARCHYID = 2 "
                + "  ) "
                + "AND BR.TERRITORYID = 2 "
                + (region != 0 ? ("AND BR.BRANCHID = " + region + " ") : "");
        return new JdbcTemplate(dataSource).queryForObject(query, Integer.class);
    }

    @Override
    public JSONArray getRegionalPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int region) throws SQLException {
        String query = "SELECT * "
                + "FROM "
                + "  (SELECT TB.*, "
                + "    ROWNUM AS ROWNUMER "
                + "  FROM "
                + "    (SELECT * "
                + "    FROM "
                + "      (SELECT TME.TERRITORYMAPID                        AS ID, "
                + "        PRDCT.PRODUCTCATEGORYID                          AS PRODUCTCATEGORYID, "
                + "        PRDCT.DESCRIPTION                                AS PRODUCTCATEGORY, "
                + "        CONCAT(CONCAT(EMP.FIRSTNAME, ' '), EMP.LASTNAME) AS EMPLOYEENAME, "
                + "        BR.ALIASNAME                                     AS TERRITORY, "
                + "        NVL( "
                + "        (SELECT SUM(LD.LEADAMOUNT) "
                + "        FROM AVN_LEAD LD "
                + "        INNER JOIN AVN_PRODUCT PRD "
                + "        ON LD.PRODUCTID       = PRD.PRODUCTID "
                + "        WHERE LD.CREATEDUSER IN "
                + "          (SELECT SU.USERID "
                + "          FROM AVN_SYSTEMUSER SU "
                + "          WHERE SU.EMPLOYEEID IN "
                + "            (SELECT TMPE.EMPLOYEEID "
                + "            FROM AVN_TERRITORYMAPEMPLOYEE TMPE "
                + "            WHERE TMPE.TERRITORYMAPID IN "
                + "              (SELECT BRN.BRANCHID "
                + "              FROM AVN_BRANCH BRN "
                + "              WHERE BRN.PARENTTERRITORYMAPID = TME.TERRITORYMAPID "
                + "              ) "
                + "              AND TMPE.ISSITTING = 1 "
                + "            ) "
                + "          ) "
                + "        AND LD.STATUS           = 37 "
                + "        AND PRD.PRODUCTCATEGORY = PRDCT.PRODUCTCATEGORYID "
                + "        " + this.getLeadForecastWhere(fromDate, toDate)
                + "        ), '00.0') AS LEADFORECAST, "
                + "        NVL( "
                + "        (SELECT SUM(LD.CONFIRMEDAMOUNT) "
                + "        FROM AVN_LEAD LD "
                + "        INNER JOIN AVN_PRODUCT PRD "
                + "        ON LD.PRODUCTID       = PRD.PRODUCTID "
                + "        WHERE LD.CREATEDUSER IN "
                + "          (SELECT SU.USERID "
                + "          FROM AVN_SYSTEMUSER SU "
                + "          WHERE SU.EMPLOYEEID IN "
                + "            (SELECT TMPE.EMPLOYEEID "
                + "            FROM AVN_TERRITORYMAPEMPLOYEE TMPE "
                + "            WHERE TMPE.TERRITORYMAPID IN "
                + "              (SELECT BRN.BRANCHID "
                + "              FROM AVN_BRANCH BRN "
                + "              WHERE BRN.PARENTTERRITORYMAPID = TME.TERRITORYMAPID "
                + "              ) "
                + "              AND TMPE.ISSITTING = 1 "
                + "            ) "
                + "          ) "
                + "        AND LD.STATUS           = 38 "
                + "        AND PRD.PRODUCTCATEGORY = PRDCT.PRODUCTCATEGORYID "
                + "        " + this.getLeadConfirmedWhere(fromDate, toDate)
                + "        ), '00.0') AS LEADCONFIRMED "
                + "      FROM AVN_PRODUCTCATEGORY PRDCT, "
                + "        AVN_TERRITORYMAPEMPLOYEE TME "
                + "      INNER JOIN AVN_BRANCH BR "
                + "      ON BR.BRANCHID = TME.TERRITORYMAPID "
                + "      INNER JOIN AVN_EMPLOYEE EMP "
                + "      ON TME.EMPLOYEEID     = EMP.EMPLOYEEID "
                + "      WHERE TME.EMPLOYEEID IN "
                + "        (SELECT EMP.EMPLOYEEID FROM AVN_EMPLOYEE EMP WHERE EMP.HIERARCHYID = 2 "
                + "        ) "
                + "      AND BR.TERRITORYID = 2 "
                + "      " + (region != 0 ? ("AND BR.BRANCHID = " + region + " ") : "")
                + "      ) TS "
                + "    " + sort + " "
                + "    ) TB "
                + "  WHERE ROWNUM <= ? "
                + "  ) "
                + "WHERE ROWNUMER > ?";
        return new JSONArray(new JdbcTemplate(dataSource).query(query, new Object[]{start + minCount, start}, new RegionalPerformanceRowMapper()));
    }

    @Override
    public int getBranchPerformanceCount(int region, int productcategory, Integer[] territories) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT "
                + "FROM  AVN_PRODUCTCATEGORY PRDCT, "
                + "  AVN_BRANCH BR "
                + "LEFT OUTER JOIN "
                + "  (SELECT TME.EMPLOYEEID, "
                + "    TME.TERRITORYMAPID "
                + "  FROM AVN_TERRITORYMAPEMPLOYEE TME "
                + "  INNER JOIN AVN_EMPLOYEE EMP "
                + "  ON TME.EMPLOYEEID     = EMP.EMPLOYEEID "
                + "  WHERE EMP.HIERARCHYID = 3 "
                + "  ) TMET ON BR.BRANCHID = TMET.TERRITORYMAPID "
                + "WHERE BR.TERRITORYID    = 3 "
                + (region != 0 ? (" AND BR.PARENTTERRITORYMAPID = " + region + " ") : "")
                + (productcategory != 0 ? (" AND PRDCT.PRODUCTCATEGORYID = " + productcategory + " ") : "")
                + (territories.length > 0 ? " AND BR.BRANCHID IN (" + Arrays.toString(territories).replaceAll("\\[|\\]", "") + ") " : "")
                + "AND BR.BRANCHID        <> 999";
        return new JdbcTemplate(dataSource).queryForObject(query, Integer.class);
    }

    @Override
    public JSONArray getBranchPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int region, int productcategory, Integer[] territories) throws SQLException {
        String query = "SELECT * "
                + "FROM "
                + "  (SELECT TB.*, "
                + "    ROWNUM AS ROWNUMER "
                + "  FROM "
                + "    (SELECT * "
                + "    FROM "
                + "      (SELECT BR.BRANCHID      AS ID, "
                + "        PRDCT.PRODUCTCATEGORYID AS PRODUCTCATEGORYID, "
                + "        PRDCT.DESCRIPTION       AS PRODUCTCATEGORY, "
                + "        BR.ALIASNAME            AS TERRITORY, "
                + "        NVL( "
                + "        (SELECT CONCAT(CONCAT(EMP.FIRSTNAME, ' '), EMP.LASTNAME) "
                + "        FROM AVN_EMPLOYEE EMP "
                + "        WHERE EMP.EMPLOYEEID = TMET.EMPLOYEEID "
                + "        ), '--') AS EMPLOYEENAME, "
                + "        NVL( "
                + "        (SELECT SUM(LD.LEADAMOUNT) "
                + "        FROM AVN_LEAD LD "
                + "        INNER JOIN AVN_PRODUCT PRD "
                + "        ON LD.PRODUCTID       = PRD.PRODUCTID "
                + "        WHERE LD.CREATEDUSER IN "
                + "          (SELECT SU.USERID "
                + "          FROM AVN_SYSTEMUSER SU "
                + "          WHERE SU.EMPLOYEEID IN "
                + "            (SELECT TMPE.EMPLOYEEID "
                + "            FROM AVN_TERRITORYMAPEMPLOYEE TMPE "
                + "            WHERE TMPE.TERRITORYMAPID = BR.BRANCHID "
                + "            AND TMPE.ISSITTING        = 1 "
                + "            ) "
                + "          ) "
                + "        AND LD.STATUS           = 37 "
                + "        AND PRD.PRODUCTCATEGORY = PRDCT.PRODUCTCATEGORYID "
                + "        " + this.getLeadForecastWhere(fromDate, toDate)
                + "        ), '00.0') AS LEADFORECAST, "
                + "        NVL( "
                + "        (SELECT SUM(LD.CONFIRMEDAMOUNT) "
                + "        FROM AVN_LEAD LD "
                + "        INNER JOIN AVN_PRODUCT PRD "
                + "        ON LD.PRODUCTID       = PRD.PRODUCTID "
                + "        WHERE LD.CREATEDUSER IN "
                + "          (SELECT SU.USERID "
                + "          FROM AVN_SYSTEMUSER SU "
                + "          WHERE SU.EMPLOYEEID IN "
                + "            (SELECT TMPE.EMPLOYEEID "
                + "            FROM AVN_TERRITORYMAPEMPLOYEE TMPE "
                + "            WHERE TMPE.TERRITORYMAPID = BR.BRANCHID "
                + "            AND TMPE.ISSITTING        = 1 "
                + "            ) "
                + "          ) "
                + "        AND LD.STATUS           = 38 "
                + "        AND PRD.PRODUCTCATEGORY = PRDCT.PRODUCTCATEGORYID "
                + "        " + this.getLeadConfirmedWhere(fromDate, toDate)
                + "        ), '00.0') AS LEADCONFIRMED "
                + "      FROM AVN_PRODUCTCATEGORY PRDCT, "
                + "        AVN_BRANCH BR "
                + "      LEFT OUTER JOIN "
                + "        (SELECT TME.EMPLOYEEID, "
                + "          TME.TERRITORYMAPID "
                + "        FROM AVN_TERRITORYMAPEMPLOYEE TME "
                + "        INNER JOIN AVN_EMPLOYEE EMP "
                + "        ON TME.EMPLOYEEID     = EMP.EMPLOYEEID "
                + "        WHERE EMP.HIERARCHYID = 3 "
                + "        ) TMET ON BR.BRANCHID = TMET.TERRITORYMAPID "
                + "      WHERE BR.TERRITORYID    = 3 "
                + "      " + (region != 0 ? (" AND BR.PARENTTERRITORYMAPID = " + region + " ") : "")
                + "      " + (productcategory != 0 ? (" AND PRDCT.PRODUCTCATEGORYID = " + productcategory + " ") : "")
                + "      " + (territories.length > 0 ? " AND BR.BRANCHID IN (" + Arrays.toString(territories).replaceAll("\\[|\\]", "") + ") " : "")
                + "      AND BR.BRANCHID        <> 999 "
                + "      ) TS "
                + "    " + sort + " "
                + "    ) TB "
                + "  WHERE ROWNUM <= ? "
                + "  ) "
                + "WHERE ROWNUMER > ?";
        return new JSONArray(new JdbcTemplate(dataSource).query(query, new Object[]{start + minCount, start}, new RegionalPerformanceRowMapper()));
    }

    @Override
    public int getIndividualPerformanceCount(int branch, int productcategory, Integer[] territories, String username) throws SQLException {
        String query = "SELECT COUNT(*) AS CNT "
                + "FROM AVN_PRODUCTCATEGORY PRDCT, "
                + "  AVN_EMPLOYEE EMP "
                + "LEFT OUTER JOIN "
                + "  (SELECT TME.EMPLOYEEID, "
                + "    BR.ALIASNAME "
                + "  FROM AVN_TERRITORYMAPEMPLOYEE TME "
                + "  INNER JOIN AVN_BRANCH BR "
                + "  ON TME.TERRITORYMAPID    = BR.BRANCHID "
                + "  WHERE TME.ISSITTING      = 1 "
                + "  ) TMET ON EMP.EMPLOYEEID = TMET.EMPLOYEEID "
                + (branch != 0 ? (" INNER JOIN AVN_TERRITORYMAPEMPLOYEE TME "
                        + "ON EMP.EMPLOYEEID = TME.EMPLOYEEID "
                        + "AND TME.TERRITORYMAPID = " + branch + " ") : "")
                + (territories.length > 0 ? (" INNER JOIN AVN_TERRITORYMAPEMPLOYEE TME "
                        + "ON EMP.EMPLOYEEID = TME.EMPLOYEEID "
                        + "AND TME.TERRITORYMAPID IN (" + Arrays.toString(territories).replaceAll("\\[|\\]", "") + ") ") : "")
                + (username != null && !username.trim().isEmpty() ? " INNER JOIN AVN_SYSTEMUSER SUR "
                        + "ON EMP.EMPLOYEEID = SUR.EMPLOYEEID "
                        + "AND SUR.USERID = '" + username + "' " : "")
                + "WHERE 1      = 1 "
                + (productcategory != 0 ? (" AND PRDCT.PRODUCTCATEGORYID = " + productcategory + " ") : "");
        return new JdbcTemplate(dataSource).queryForObject(query, Integer.class);
    }

    @Override
    public JSONArray getIndividualPerformance(int minCount, int start, String sort, Date fromDate, Date toDate, int branch, int productcategory, Integer[] territories, String username) throws SQLException {
        String query = "SELECT * "
                + "FROM "
                + "  (SELECT TB.*, "
                + "    ROWNUM AS ROWNUMER "
                + "  FROM "
                + "    (SELECT * "
                + "    FROM "
                + "      (SELECT EMP.EMPLOYEEID                             AS ID, "
                + "        PRDCT.PRODUCTCATEGORYID                          AS PRODUCTCATEGORYID, "
                + "        PRDCT.DESCRIPTION                                AS PRODUCTCATEGORY, "
                + "        NVL(TMET.ALIASNAME, '--')                        AS TERRITORY, "
                + "        CONCAT(CONCAT(EMP.FIRSTNAME, ' '), EMP.LASTNAME) AS EMPLOYEENAME, "
                + "        NVL( "
                + "        (SELECT SUM(LD.LEADAMOUNT) "
                + "        FROM AVN_LEAD LD "
                + "        INNER JOIN AVN_PRODUCT PRD "
                + "        ON LD.PRODUCTID       = PRD.PRODUCTID "
                + "        WHERE LD.CREATEDUSER IN "
                + "          (SELECT SU.USERID "
                + "          FROM AVN_SYSTEMUSER SU "
                + "          WHERE SU.EMPLOYEEID = EMP.EMPLOYEEID "
                + "          ) "
                + "        AND LD.STATUS           = 37 "
                + "        AND PRD.PRODUCTCATEGORY = PRDCT.PRODUCTCATEGORYID "
                + "        " + this.getLeadForecastWhere(fromDate, toDate)
                + "        ), '00.0') AS LEADFORECAST, "
                + "        NVL( "
                + "        (SELECT SUM(LD.CONFIRMEDAMOUNT) "
                + "        FROM AVN_LEAD LD "
                + "        INNER JOIN AVN_PRODUCT PRD "
                + "        ON LD.PRODUCTID       = PRD.PRODUCTID "
                + "        WHERE LD.CREATEDUSER IN "
                + "          (SELECT SU.USERID "
                + "          FROM AVN_SYSTEMUSER SU "
                + "          WHERE SU.EMPLOYEEID = EMP.EMPLOYEEID "
                + "          ) "
                + "        AND LD.STATUS           = 38 "
                + "        AND PRD.PRODUCTCATEGORY = PRDCT.PRODUCTCATEGORYID "
                + "        " + this.getLeadConfirmedWhere(fromDate, toDate)
                + "        ), '00.0') AS LEADCONFIRMED "
                + "      FROM AVN_PRODUCTCATEGORY PRDCT, "
                + "        AVN_EMPLOYEE EMP "
                + "      LEFT OUTER JOIN "
                + "        (SELECT TME.EMPLOYEEID, "
                + "          BR.ALIASNAME "
                + "        FROM AVN_TERRITORYMAPEMPLOYEE TME "
                + "        INNER JOIN AVN_BRANCH BR "
                + "        ON TME.TERRITORYMAPID    = BR.BRANCHID "
                + "        WHERE TME.ISSITTING      = 1 "
                + "        ) TMET ON EMP.EMPLOYEEID = TMET.EMPLOYEEID "
                + (branch != 0 ? (" INNER JOIN AVN_TERRITORYMAPEMPLOYEE TME "
                        + "ON EMP.EMPLOYEEID = TME.EMPLOYEEID "
                        + "AND TME.TERRITORYMAPID = " + branch + " ") : "")
                + (territories.length > 0 ? (" INNER JOIN AVN_TERRITORYMAPEMPLOYEE TME "
                        + "ON EMP.EMPLOYEEID = TME.EMPLOYEEID "
                        + "AND TME.TERRITORYMAPID IN (" + Arrays.toString(territories).replaceAll("\\[|\\]", "") + ") ") : "")
                + (username != null && !username.trim().isEmpty() ? " INNER JOIN AVN_SYSTEMUSER SUR "
                        + "ON EMP.EMPLOYEEID = SUR.EMPLOYEEID "
                        + "AND SUR.USERID = '" + username + "' " : "")
                + "      WHERE 1      = 1 "
                + (productcategory != 0 ? (" AND PRDCT.PRODUCTCATEGORYID = " + productcategory + " ") : "")
                + "      ) TS "
                + "    " + sort + " "
                + "    ) TB "
                + "  WHERE ROWNUM <= ? "
                + "  ) "
                + "WHERE ROWNUMER > ?";
        return new JSONArray(new JdbcTemplate(dataSource).query(query, new Object[]{start + minCount, start}, new RegionalPerformanceRowMapper()));
    }

    private String getLeadForecastWhere(Date fromDate, Date toDate) {
        String where = "";
        if (fromDate != null && toDate != null) {
            where = " AND LD.FORCASTUNTILDATE BETWEEN TO_DATE('"
                    + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss, fromDate)
                    + "', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"
                    + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss, toDate)
                    + "', 'YYYY-MM-DD HH24:MI:SS')";
        } else if (fromDate != null) {
            where = " AND LD.FORCASTUNTILDATE > TO_DATE('"
                    + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss, fromDate)
                    + "', 'YYYY-MM-DD HH24:MI:SS')";
        } else if (toDate != null) {
            where = " AND LD.FORCASTUNTILDATE < TO_DATE('"
                    + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss, toDate)
                    + "', 'YYYY-MM-DD HH24:MI:SS')";
        }
        return where;
    }

    private String getLeadConfirmedWhere(Date fromDate, Date toDate) {
        String where = "";
        if (fromDate != null && toDate != null) {
            where = " AND LD.SALECLOSEDDATE BETWEEN TO_DATE('"
                    + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss, fromDate)
                    + "', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('"
                    + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss, toDate)
                    + "', 'YYYY-MM-DD HH24:MI:SS')";
        } else if (fromDate != null) {
            where = " AND LD.SALECLOSEDDATE > TO_DATE('"
                    + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss, fromDate)
                    + "', 'YYYY-MM-DD HH24:MI:SS')";
        } else if (toDate != null) {
            where = " AND LD.SALECLOSEDDATE < TO_DATE('"
                    + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss, toDate)
                    + "', 'YYYY-MM-DD HH24:MI:SS')";
        }
        return where;
    }

    @Override
    public JSONArray getForecastForDateRange(Date fromDate, Date toDate) throws SQLException {
        String query = "SELECT PDC.DESCRIPTION AS PRODUCTCATEGORY, "
                + "  NVL((SELECT SUM(LD.LEADAMOUNT) AS FORECASTAMOUNT "
                + "  FROM AVN_LEAD LD "
                + "  INNER JOIN AVN_PRODUCT PD "
                + "  ON LD.PRODUCTID = PD.PRODUCTID "
                + "  WHERE LD.FORCASTUNTILDATE BETWEEN ? AND ? " //TO_DATE('2017-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('2017-02-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
                + "  AND LD.STATUS          = 37 "
                + "  AND PD.PRODUCTCATEGORY = PDC.PRODUCTCATEGORYID "
                + "  ), 0) AS FORECASTAMOUNT "
                + "FROM AVN_PRODUCTCATEGORY PDC";
        return new JSONArray(new JdbcTemplate(dataSource).query(query, new Object[]{fromDate, toDate}, new ForecastForDateRangeRowMapper()));
    }

    public class RegionalPerformanceRowMapper implements RowMapper {

        @Override
        public JSONObject mapRow(ResultSet rs, int rowNum) throws SQLException {
            JSONObject object = new JSONObject();
            object.put("id", rs.getString("ID"));
            object.put("productcategoryid", rs.getString("PRODUCTCATEGORYID"));
            object.put("productcategory", rs.getString("PRODUCTCATEGORY"));
            object.put("employeename", rs.getString("EMPLOYEENAME"));
            object.put("territory", rs.getString("TERRITORY"));
            object.put("leadforecast", rs.getString("LEADFORECAST"));
            object.put("leadconfirmed", rs.getString("LEADCONFIRMED"));
            object.put("action",
                    "<div class=\"row\">"
                    + " <div class=\"col-xs-12\">"
                    + "     <div class=\"btn-group dropup\">"
                    + "         <button class=\"btn btn-primary btn-xs dropdown-toggle\" data-toggle=\"dropdown\">"
                    + "             <i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i>"
                    + "         </button>"
                    + "         <ul class=\"dropdown-menu dropdown-menu-right\">"
                    + "             <li>"
                    + "                 <a href=\"javascript:void(0);\" class=\"row_crdtorintr\">" + rs.getString("PRODUCTCATEGORY") + "</a>"
                    + "             </li>"
                    + "             <li>"
                    + "                 <a href=\"javascript:void(0);\" class=\"row_both\">Both</a>"
                    + "             </li>"
                    + "		</ul>"
                    + "     </div>"
                    + " </div>"
                    + "</div>");
            return object;
        }
    }

    public class ForecastForDateRangeRowMapper implements RowMapper {

        @Override
        public JSONObject mapRow(ResultSet rs, int rowNum) throws SQLException {
            DecimalFormat df = new DecimalFormat("#,###.00");
            JSONObject object = new JSONObject();
            object.put("productcategory", rs.getString("PRODUCTCATEGORY"));
            object.put("forecastamount", /*df.format(*/ rs.getBigDecimal("FORECASTAMOUNT")/*)*/);
            return object;
        }
    }
}

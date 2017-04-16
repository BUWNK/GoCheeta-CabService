/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.user;

import com.avn.ccl.dao.user.UserDAO;
import com.avn.ccl.model.employee.Employee;
import com.avn.ccl.model.user.User;
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
import org.json.JSONArray;

/**
 * @Author : Roshen Dilshan
 * @Document : UserDAOImpl
 * @Created on : Sep 15, 2015, 1:44:38 PM
 */
public class UserDAOImpl implements UserDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int getTableDataCount(User user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT "
                    + "    COUNT(*) AS CNT "
                    + "FROM "
                    + "    AVN_SYSTEMUSER SU "
                    + "INNER JOIN "
                    + "    AVN_USER_ROLE UR "
                    + "    ON "
                    + "    UR.USERROLEID = SU.USERROLE "
                    + "INNER JOIN "
                    + "    AVN_STATUS S "
                    + "    ON "
                    + "    S.STATUSID = SU.STATUS "
                    + "WHERE "
                    + "    1 = 1 "
                    + "    :where "
                    + "ORDER BY "
                    + "    SU.CREATEDDATETIME DESC ";
            String where = "";
            if (user.getUsername() != null && !user.getUsername().trim().isEmpty()) {
                where = "AND SU.USERID LIKE '%" + user.getUsername() + "%'";
            }
            sql = sql.replace(":where", where);
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
        return count;
    }

    @Override
    public List<User> getTableData(User user, int minCount, int start) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> list = null;
        try {
            String sql = "SELECT "
                    + "    * "
                    + "FROM "
                    + "    ("
                    + "    SELECT "
                    + "        TB.*, "
                    + "         ROWNUM AS ROWNUMER  "
                    + "    FROM "
                    + "        ( "
                    + "        SELECT "
                    + "             SU.USERID, "
                    + "             NVL(UR.DESCRIPTION, '--')                                           AS USERROLE, "
                    + "             NVL(SU.EMAIL, '--')                                                 AS EMAIL, "
                    + "             NVL(S.DESCRIPTION, '--')                                            AS STATUS, "
                    + "             NVL(SU.USERATTEMPTS, '0')                                           AS USERATTEMPTS, "
                    + "             NVL(TO_CHAR(SU.CREATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS'), '--')     AS CREATEDDATETIME, "
                    + "             NVL(TO_CHAR(SU.LASTUPDATEDDATETIME, 'YYYY-MM-DD HH24:MI:SS'), '--') AS LASTUPDATEDDATETIME "
                    + "        FROM "
                    + "             AVN_SYSTEMUSER SU "
                    + "        INNER JOIN "
                    + "             AVN_USER_ROLE UR "
                    + "            ON "
                    + "             UR.USERROLEID = SU.USERROLE "
                    + "        INNER JOIN "
                    + "             AVN_STATUS S "
                    + "            ON "
                    + "             S.STATUSID = SU.STATUS "
                    + "        WHERE "
                    + "             1 = 1 "
                    + "             :where "
                    + "        ORDER BY "
                    + "             SU.CREATEDDATETIME DESC"
                    + "        ) TB  "
                    + "    WHERE "
                    + "        ROWNUM <= ?  "
                    + "    ) "
                    + "WHERE "
                    + "    ROWNUMER > ? ";
            String where = "";
            if (user.getUsername() != null && !user.getUsername().trim().isEmpty()) {
                where = "AND SU.USERID LIKE '%" + user.getUsername() + "%'";
            }
            sql = sql.replace(":where", where);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, start + minCount);
            statement.setInt(2, start);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            User userDate;
            while (resultSet.next()) {
                userDate = new User();
                userDate.setUsername(resultSet.getString("USERID"));
                userDate.setUser_role(resultSet.getString("USERROLE"));
                userDate.setEmail(resultSet.getString("EMAIL"));
                userDate.setStatus(resultSet.getString("STATUS"));
                userDate.setInvalid_user_attepmts(resultSet.getInt("USERATTEMPTS"));
                userDate.setCreated_time(resultSet.getString("CREATEDDATETIME"));
                userDate.setLastupdated_time(resultSet.getString("LASTUPDATEDDATETIME"));
                list.add(userDate);
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
    public boolean isUserExists(User user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isExists = false;
        try {
            String sql = "SELECT "
                    + "    COUNT(*) AS CNT "
                    + "FROM "
                    + "    AVN_SYSTEMUSER SU "
                    + "WHERE "
                    + "    SU.USERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            resultSet = statement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt("CNT");
            }

            if (count > 0) {
                isExists = true;
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
        return isExists;
    }

    @Override
    public void createUser(User user, Employee employee) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "INSERT "
                    + "INTO "
                    + "    AVN_EMPLOYEE "
                    + "    ( "
                    + "        BRANCHID, "
                    + "        INITIALS, "
                    + "        FIRSTNAME, "
                    + "        LASTNAME, "
                    + "        EMAIL, "
                    + "        CONTACTMOBILE, "
                    + "        STATUS, "
                    + "        NIC, "
                    + "        EPF, "
                    + "        EMPLOYEELEVEL, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME"
                    + "    ) "
                    + "    VALUES "
                    + "    ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            String[] generatedColumns = {"EMPLOYEEID"};
            statement = connection.prepareStatement(sql, generatedColumns);
            if (employee.getBranchid() != null && !employee.getBranchid().isEmpty()) {
                statement.setInt(1, Integer.valueOf(employee.getBranchid()));
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.setString(2, employee.getInitials() == null ? "" : employee.getInitials());
            statement.setString(3, employee.getFirstname() == null ? "" : employee.getFirstname());
            statement.setString(4, employee.getLastname() == null ? "" : employee.getLastname());
            statement.setString(5, employee.getEmail() == null ? "" : employee.getEmail());
            statement.setString(6, employee.getContactmobile() == null ? "" : employee.getContactmobile());
            statement.setInt(7, Integer.valueOf(employee.getStatus()));
            statement.setString(8, employee.getNic() == null ? "" : employee.getNic());
            statement.setString(9, employee.getEpf() == null ? "" : employee.getEpf());
            statement.setString(10, employee.getEmployeelevel() == null ? "" : employee.getEmployeelevel());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            long employee_id = 0;
            while (resultSet.next()) {
                employee_id = resultSet.getLong(1);
            }

            sql = "INSERT "
                    + "INTO "
                    + "    AVN_EMPLOYEEBRANCHEMPLOYEECAT "
                    + "    ("
                    + "        EMPLOYEEID, "
                    + "        BRANCHID, "
                    + "        EMPLOYEECATEGORY, "
                    + "        ISNOTIFY, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME "
                    + "    ) "
                    + "    VALUES "
                    + "    (?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            statement = connection.prepareStatement(sql);
            if (user.getEmployeecategory() != null && !user.getEmployeecategory().isEmpty() && user.getBranchsb() != null && !user.getBranchsb().isEmpty()) {
                statement.setLong(1, employee_id);
                statement.setLong(2, Long.valueOf(user.getBranchsb()));
                statement.setString(3, user.getEmployeecategory());
                if (user.getEmployeecategory() != null
                        && (user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_CONTACT_CENTRE)
                        || user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_INTERNAL_AUDIT))) {
                    if (user.isNotify()) {
                        statement.setString(4, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
                    } else {
                        statement.setString(4, MasterDataVarList.CCL_CODE_NOTIFICATION_FALSE);
                    }
                } else {
                    statement.setString(4, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
                }
                statement.execute();
            }

            if (user.getEmployeecategory() != null && !user.getEmployeecategory().isEmpty() && user.getMultibranch() != null && !user.getMultibranch().isEmpty()) {
                JSONArray array = new JSONArray(user.getMultibranch());
                for (int i = 0; i < array.length(); i++) {
                    String branch = array.getString(i);
                    statement.setLong(1, employee_id);
                    statement.setLong(2, Long.valueOf(branch));
                    statement.setString(3, user.getEmployeecategory());
                    statement.setString(4, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
                    statement.execute();
                }
            }

            if (user.getEmployeecategory() != null && user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER)
                    && user.getBrorrm() != null && user.getBrorrm().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)) {
                JSONArray array = new JSONArray(user.getMultibranch());
                for (int i = 0; i < array.length(); i++) {
                    String branch = array.getString(i);
                    statement.setLong(1, employee_id);
                    statement.setLong(2, Long.valueOf(branch));
                    statement.setString(3, user.getEmployeecategory());
                    statement.setString(4, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
                    statement.execute();
                }
            }

            sql = "INSERT "
                    + "INTO "
                    + "    AVN_SYSTEMUSER "
                    + "    ("
                    + "        USERID, "
                    + "        USERROLE, "
                    + "        EMPLOYEEID, "
                    + "        STATUS, "
                    + "        EMAIL, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME "
                    + "    ) "
                    + "    VALUES "
                    + "    ( ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setInt(2, Integer.valueOf(user.getUser_role()));
            statement.setLong(3, employee_id);
            statement.setInt(4, Integer.valueOf(user.getStatus()));
            statement.setString(5, user.getEmail() == null ? "" : user.getEmail());
            statement.execute();

            connection.commit();
        } catch (SQLException sqle) {
            if (connection != null) {
                connection.rollback();
            }
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
    }

    @Override
    public void createUser(User user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "INSERT "
                    + "INTO "
                    + "    AVN_SYSTEMUSER "
                    + "    ("
                    + "        USERID, "
                    + "        USERROLE, "
                    + "        EMPLOYEEID, "
                    + "        STATUS, "
                    + "        EMAIL, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME "
                    + "    ) "
                    + "    VALUES "
                    + "    ( ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setInt(2, Integer.valueOf(user.getUser_role()));
            statement.setLong(3, user.getEmpid());
            statement.setInt(4, Integer.valueOf(user.getStatus()));
            statement.setString(5, user.getEmail() == null ? "" : user.getEmail());
            statement.execute();

            connection.commit();
        } catch (SQLException sqle) {
            if (connection != null) {
                connection.rollback();
            }
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
    }

    @Override
    public void updateUser(User user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE "
                    + "    AVN_EMPLOYEE "
                    + "    SET EMAIL           = ?, "
                    + "    LASTUPDATEDDATETIME = CURRENT_TIMESTAMP "
                    + "WHERE "
                    + "    EMPLOYEEID = "
                    + "    ( "
                    + "    SELECT "
                    + "        EMPLOYEEID "
                    + "    FROM "
                    + "        AVN_SYSTEMUSER "
                    + "    WHERE "
                    + "        USERID = ? "
                    + "    ) ";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.execute();

            sql = "DELETE FROM AVN_EMPLOYEEBRANCHEMPLOYEECAT WHERE EMPLOYEEID = ((SELECT EMPLOYEEID FROM AVN_SYSTEMUSER WHERE USERID = ?))";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.execute();

            sql = "INSERT "
                    + "INTO "
                    + "    AVN_EMPLOYEEBRANCHEMPLOYEECAT "
                    + "    ("
                    + "        EMPLOYEEID, "
                    + "        BRANCHID, "
                    + "        EMPLOYEECATEGORY, "
                    + "        ISNOTIFY, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME "
                    + "    ) "
                    + "    VALUES "
                    + "    ((SELECT EMPLOYEEID FROM AVN_SYSTEMUSER WHERE USERID = ?), ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            statement = connection.prepareStatement(sql);
            if (user.getEmployeecategory() != null && !user.getEmployeecategory().isEmpty() && user.getBranchsb() != null && !user.getBranchsb().isEmpty()) {
                statement.setString(1, user.getUsername());
                statement.setLong(2, Long.valueOf(user.getBranchsb()));
                statement.setString(3, user.getEmployeecategory());
                if (user.getEmployeecategory() != null
                        && (user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_CONTACT_CENTRE)
                        || user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_INTERNAL_AUDIT))) {
                    if (user.isNotify()) {
                        statement.setString(4, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
                    } else {
                        statement.setString(4, MasterDataVarList.CCL_CODE_NOTIFICATION_FALSE);
                    }
                } else {
                    statement.setString(4, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
                }
                statement.execute();
            }

            if (user.getEmployeecategory() != null && !user.getEmployeecategory().isEmpty() && user.getMultibranch() != null && !user.getMultibranch().isEmpty()) {
                JSONArray array = new JSONArray(user.getMultibranch());
                for (int i = 0; i < array.length(); i++) {
                    String branch = array.getString(i);
                    statement.setString(1, user.getUsername());
                    statement.setLong(2, Long.valueOf(branch));
                    statement.setString(3, user.getEmployeecategory());
                    statement.setString(4, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
                    statement.execute();
                }
            }

            if (user.getEmployeecategory() != null && user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER)
                    && user.getBrorrm() != null && user.getBrorrm().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)) {
                JSONArray array = new JSONArray(user.getMultibranch());
                for (int i = 0; i < array.length(); i++) {
                    String branch = array.getString(i);
                    statement.setString(1, user.getUsername());
                    statement.setLong(2, Long.valueOf(branch));
                    statement.setString(3, user.getEmployeecategory());
                    statement.setString(4, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
                    statement.execute();
                }
            }

            sql = "UPDATE "
                    + "    AVN_SYSTEMUSER "
                    + "    SET USERROLE        = ?, "
                    + "    STATUS              = ?, "
                    + "    EMAIL               = ?, "
                    + "    LASTUPDATEDDATETIME = CURRENT_TIMESTAMP "
                    + "WHERE "
                    + "    USERID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(user.getUser_role()));
            statement.setInt(2, Integer.valueOf(user.getStatus()));
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.execute();

            sql = "SELECT EMPLOYEEID FROM AVN_SYSTEMUSER WHERE USERID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setEmpid(resultSet.getLong("EMPLOYEEID"));
            }

            connection.commit();
        } catch (SQLException sqle) {
            if (connection != null) {
                connection.rollback();
            }
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
    }

    @Override
    public User getUser(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            String sql = "SELECT "
                    + "    USERID, "
                    + "    USERROLE,  "
                    + "    STATUS, "
                    + "    EMAIL,"
                    + "    EMPLOYEEID "
                    + "FROM "
                    + "    AVN_SYSTEMUSER "
                    + "WHERE "
                    + "    USERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            user = new User();
            while (resultSet.next()) {
                user.setUsername(resultSet.getString("USERID"));
                user.setUser_role(resultSet.getString("USERROLE") == null ? "" : resultSet.getString("USERROLE"));
                user.setStatus(resultSet.getString("STATUS") == null ? "" : resultSet.getString("STATUS"));
                user.setEmail(resultSet.getString("EMAIL") == null ? "" : resultSet.getString("EMAIL"));
                user.setEmpid(resultSet.getLong("EMPLOYEEID"));
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
        return user;
    }

    @Override
    public List<String> getUserAssignedEmployeeCategories(Long employeeid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> list = null;
        try {
            String sql = "SELECT DISTINCT "
                    + "    (EMPLOYEECATEGORY) AS EMPLOYEECATEGORY "
                    + "FROM "
                    + "    AVN_EMPLOYEEBRANCHEMPLOYEECAT "
                    + "WHERE "
                    + "    EMPLOYEEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, employeeid);
            resultSet = statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getString("EMPLOYEECATEGORY"));
            }
        } catch (Exception ex) {
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
        return list;
    }

    @Override
    public Map<String, String> getAssignedBranches(long employeeid, String employeecategory) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT "
                    + "    EBC.BRANCHID ,"
                    + "    BR.ALIASNAME "
                    + "FROM "
                    + "    AVN_EMPLOYEEBRANCHEMPLOYEECAT EBC "
                    + "INNER JOIN "
                    + "    AVN_BRANCH BR "
                    + "    ON "
                    + "    EBC.BRANCHID = BR.BRANCHID "
                    + "WHERE "
                    + "    EBC.EMPLOYEEID           = ? "
                    + "    AND EBC.EMPLOYEECATEGORY = ? "
                    + "ORDER BY "
                    + "    BR.ALIASNAME";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, employeeid);
            statement.setString(2, employeecategory);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("BRANCHID"), resultSet.getString("ALIASNAME"));
            }
        } catch (Exception ex) {
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
        return list;
    }

    @Override
    public String getAssignedBranch(long employeeid, String employeecategory) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String branch = null;
        try {
            String sql = "SELECT "
                    + "    EBC.BRANCHID "
                    + "FROM "
                    + "    AVN_EMPLOYEEBRANCHEMPLOYEECAT EBC "
                    + "WHERE "
                    + "    EBC.EMPLOYEEID           = ? "
                    + "    AND EBC.EMPLOYEECATEGORY = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, employeeid);
            statement.setString(2, employeecategory);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                branch = resultSet.getString("BRANCHID");
            }
        } catch (Exception ex) {
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
        return branch;
    }

    @Override
    public Map<String, String> getNotAssignedBranchList(long employeeid, String employeecategory) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT "
                    + "    BRANCHID, "
                    + "    ALIASNAME "
                    + "FROM "
                    + "    AVN_BRANCH "
                    + "WHERE "
                    + "    BRANCHID NOT IN "
                    + "    ("
                    + "    SELECT "
                    + "        EBC.BRANCHID "
                    + "    FROM "
                    + "        AVN_EMPLOYEEBRANCHEMPLOYEECAT EBC "
                    + "    WHERE "
                    + "        EBC.EMPLOYEEID           = ? "
                    + "        AND EBC.EMPLOYEECATEGORY = ? "
                    + "    ) "
                    + "ORDER BY "
                    + "    ALIASNAME";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, employeeid);
            statement.setString(2, employeecategory);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            while (resultSet.next()) {
                list.put(resultSet.getString("BRANCHID"), resultSet.getString("ALIASNAME"));
            }
        } catch (Exception ex) {
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
        return list;
    }

    @Override
    public boolean isUserCategoryExistsForBranch(long branchid, String employeecategory) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isExists = false;
        try {
            String sql = "SELECT "
                    + "    COUNT(*) AS CNT "
                    + "FROM "
                    + "    AVN_EMPLOYEEBRANCHEMPLOYEECAT "
                    + "WHERE "
                    + "    BRANCHID = ? "
                    + "    AND EMPLOYEECATEGORY = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, branchid);
            statement.setString(2, employeecategory);
            resultSet = statement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt("CNT");
            }

            if (count > 0) {
                isExists = true;
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
        return isExists;
    }

    @Override
    public boolean isNotify(long employeeid, String employeecategory) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isNotify = false;
        try {
            String sql = "SELECT "
                    + "    COUNT(*) AS CNT "
                    + "FROM "
                    + "    AVN_EMPLOYEEBRANCHEMPLOYEECAT "
                    + "WHERE "
                    + "    EMPLOYEEID = ? "
                    + "    AND EMPLOYEECATEGORY = ? "
                    + "    AND ISNOTIFY = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, employeeid);
            statement.setString(2, employeecategory);
            statement.setString(3, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
            resultSet = statement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt("CNT");
            }

            if (count > 0) {
                isNotify = true;
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
        return isNotify;
    }

    @Override
    public int getUserRoleByUsername(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int userRole = 0;
        try {
            String sql = "SELECT USERROLE FROM AVN_SYSTEMUSER WHERE USERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userRole = resultSet.getInt("USERROLE");
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
        return userRole;
    }

    @Override
    public Map<String, String> getUserDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT USERID FROM AVN_SYSTEMUSER ORDER BY USERID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("USERID"), resultSet.getString("USERID"));
            }
        } catch (Exception ex) {
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
        return list;
    }

    @Override
    public void updateUserAttempte(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_SYSTEMUSER SET USERATTEMPTS = USERATTEMPTS + 1 WHERE USERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.execute();
        } catch (Exception ex) {
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
    }

    @Override
    public int getUserAttempts(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int attempts =0;
        try {
            String sql = "SELECT USERATTEMPTS FROM AVN_SYSTEMUSER WHERE USERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                attempts = resultSet.getInt("USERATTEMPTS");
            }
        } catch (Exception ex) {
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
        return attempts;
    }

    @Override
    public void reSetUserAttempts(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_SYSTEMUSER SET USERATTEMPTS = 0 WHERE USERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.execute();
        } catch (Exception ex) {
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
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.employee;

import com.avn.ccl.dao.employee.EmployeeDAO;
import com.avn.ccl.model.employee.Employee;
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
 * @Author : Office Isuru
 * @Document : EmployeeDAOImpl
 * @Date : Sep 23, 2015 11:01:32 AM
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long createEmployee(Employee employee) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long employee_id = 0;
        try {
            String sql = "INSERT INTO  "
                    + "   AVN_EMPLOYEE ( BRANCHID, "
                    + "   FIRSTNAME, "
                    + "   LASTNAME, "
                    + "   CONTACTMOBILE,"
                    + "   NIC,  "
                    + "   CREATEDDATETIME,  "
                    + "   LASTUPDATEDDATETIME) "
                    + "   VALUES "
                    + "   ( ?, ?, ?, ?, ?, ? CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            connection = dataSource.getConnection();
            String[] generatedColumns = {"EMPLOYEEID"};
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setString(1, employee.getBranchid());
            statement.setString(2, employee.getFirstname());
            statement.setString(3, employee.getLastname());
            statement.setString(4, employee.getContactmobile());
            statement.setString(5, employee.getNic());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                employee_id = resultSet.getLong(1);
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
        return employee_id;
    }

    @Override
    public String getEmployeeFirstNameById(String branchid, String category) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String list = null;
        try {
            String sql = "SELECT FIRSTNAME FROM AVN_EMPLOYEE WHERE EMPLOYEEID = (SELECT EMPLOYEEID FROM AVN_EMPLOYEEBRANCHEMPLOYEECAT WHERE BRANCHID = ? AND EMPLOYEECATEGORY = (SELECT PARAMETERMAPVALUE FROM AVN_ORGANIZATIONPARAMETERS WHERE PARAMETERVALUE = ?))";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, branchid);
            statement.setString(2, category);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list = (resultSet.getString("FIRSTNAME"));
            }
        } catch (SQLException | NumberFormatException exception) {
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
    public String getEmployeeIdByBranchId(String branchid, String category) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String list = null;
        try {
            String sql = "SELECT * FROM AVN_EMPLOYEE WHERE EMPLOYEEID = (SELECT EMPLOYEEID FROM AVN_EMPLOYEEBRANCHEMPLOYEECAT WHERE BRANCHID = ? AND EMPLOYEECATEGORY = (SELECT PARAMETERMAPVALUE FROM AVN_ORGANIZATIONPARAMETERS WHERE PARAMETERVALUE = ?))";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, branchid);
            statement.setString(2, category);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list = (resultSet.getString("EMPLOYEEID"));
            }
        } catch (SQLException | NumberFormatException exception) {
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
    public String getEmployeeInitialsLastnameByid(long employeeid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String name = "";
        try {
            String sql = "SELECT NVL(INITIALS, '') || ' ' || NVL(LASTNAME, '') AS NAME FROM AVN_EMPLOYEE WHERE EMPLOYEEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, employeeid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("NAME");
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
        return name;
    }

    @Override
    public Map<String, String> getEmployeeDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> list = null;
        try {
            String sql = "SELECT EMPLOYEEID, NVL(INITIALS, '') || ' ' || NVL(LASTNAME, '') AS NAME FROM AVN_EMPLOYEE ORDER BY INITIALS";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            while (resultSet.next()) {
                list.put(resultSet.getString("EMPLOYEEID"), resultSet.getString("NAME"));
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
    public boolean isEmployeeExists(String epf) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isExists = false;
        try {
            String sql = "SELECT "
                    + "    COUNT(*) AS CNT "
                    + "FROM "
                    + "    AVN_EMPLOYEE "
                    + "WHERE "
                    + "    EPF = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, epf);
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
    public long getEmployeeIdByEPF(String epf) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long employeeId = 0;
        try {
            String sql = "SELECT "
                    + "    EMPLOYEEID "
                    + "FROM "
                    + "    AVN_EMPLOYEE "
                    + "WHERE "
                    + "    EPF = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, epf);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeId = resultSet.getInt("EMPLOYEEID");
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
        return employeeId;
    }

    @Override
    public String getEmployeeEmailByUsername(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String email = "";
        try {
            String sql = "SELECT E.EMAIL FROM AVN_EMPLOYEE E INNER JOIN AVN_SYSTEMUSER S ON E.EMPLOYEEID = S.EMPLOYEEID WHERE S.USERID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                email = resultSet.getString("EMAIL");
            }
        } catch (SQLException e) {
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
        return email;
    }

    @Override
    public String getEmployeeEmailByEmployeeId(long employeeid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String email = "";
        try {
            String sql = "SELECT EMAIL FROM AVN_EMPLOYEE WHERE EMPLOYEEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, employeeid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                email = resultSet.getString("EMAIL");
            }
        } catch (SQLException e) {
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
        return email;
    }

    @Override
    public String getEmployeeEmailByEPF(String epf) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String email = "";
        try {
            String sql = "SELECT EMAIL FROM AVN_EMPLOYEE WHERE EPF = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, epf);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                email = resultSet.getString("EMAIL");
            }
        } catch (SQLException e) {
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
        return email;
    }

    @Override
    public void updateEmployeInLogin(Employee employee, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE AVN_EMPLOYEE "
                    + "SET NIC               = ?, "
                    + "  CONTACTMOBILE       = ?, "
                    + "  BRANCHID            = ?, "
                    + "  INITIALS            = ?, "
                    + "  FIRSTNAME           = ?, "
                    + "  LASTNAME            = ?, "
                    + "  EMAIL               = ?, "
                    + "  EMPLOYEELEVEL       = ?, "
                    + "  LASTUPDATEDDATETIME = CURRENT_TIMESTAMP "
                    + "WHERE EPF             = ?";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.setString(1, employee.getNic() == null ? "" : employee.getNic());
            statement.setString(2, employee.getContactmobile() == null ? "" : employee.getContactmobile());
            if (employee.getBranchid() != null) {
                statement.setInt(3, Integer.valueOf(employee.getBranchid()));
            } else {
                statement.setInt(3, Types.INTEGER);
            }
            statement.setString(4, employee.getInitials() == null ? "" : employee.getInitials());
            statement.setString(5, employee.getFirstname() == null ? "" : employee.getFirstname());
            statement.setString(6, employee.getLastname() == null ? "" : employee.getLastname());
            statement.setString(7, employee.getEmail() == null ? "" : employee.getEmail());
            statement.setString(8, employee.getEmployeelevel() == null ? "" : employee.getEmployeelevel());
            statement.setString(9, employee.getEpf());
            statement.execute();

            sql = "UPDATE AVN_SYSTEMUSER SET EMAIL = ? WHERE USERID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, employee.getEmail());
            statement.setString(2, username);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
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
    public Employee getEmployeeDetailsByEmployeeId(long employeeid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Employee employeeDetails = null;
        try {
            String sql = "SELECT FIRSTNAME, EMAIL, CONTACTMOBILE FROM AVN_EMPLOYEE WHERE EMPLOYEEID =?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, employeeid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeDetails = new Employee();
                employeeDetails.setFirstname(resultSet.getString("FIRSTNAME"));
                employeeDetails.setEmail(resultSet.getString("EMAIL"));
                employeeDetails.setContactmobile(resultSet.getString("CONTACTMOBILE"));
            }
        } catch (SQLException e) {
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
        return employeeDetails;
    }

    @Override
    public String getEmployeeNameInFullByEmployeeId(long employeeid) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String nameInFull = "";
        try {
            String sql = "SELECT NVL(E.FIRSTNAME, '') "
                    + "  || ' ' "
                    + "  || NVL(E.LASTNAME,'') AS NAME "
                    + "FROM AVN_EMPLOYEE E, "
                    + "  AVN_SYSTEMUSER S "
                    + "WHERE E.EMPLOYEEID = S.EMPLOYEEID "
                    + "AND S.EMPLOYEEID       = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, employeeid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                nameInFull = resultSet.getString("NAME");
            }
        } catch (SQLException e) {
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
        return nameInFull;
    }

    @Override
    public long getEmployeeIdByUsername(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long employeeid = 0;
        try {
            String sql = "SELECT SU.EMPLOYEEID FROM AVN_SYSTEMUSER SU WHERE SU.USERID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeid = resultSet.getLong("EMPLOYEEID");
            }
        } catch (SQLException e) {
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
        return employeeid;
    }

    @Override
    public String getEmployeeOldBranchCodeByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String branchcode = "";
        try {
            String sql = "SELECT BR.BRANCHCODEOLD "
                    + "FROM AVN_SYSTEMUSER SU "
                    + "INNER JOIN AVN_EMPLOYEE EM "
                    + "ON SU.EMPLOYEEID = EM.EMPLOYEEID "
                    + "INNER JOIN AVN_BRANCH BR "
                    + "ON EM.BRANCHID = BR.BRANCHID "
                    + "WHERE SU.USERID  = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                branchcode = resultSet.getString("BRANCHCODEOLD");
            }
        } catch (SQLException e) {
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
        return branchcode;
    }

    @Override
    public int getUserHierarchyByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int userHierarchy = 0;
        try {
            String sql = "SELECT EMP.HIERARCHYID "
                    + "FROM AVN_EMPLOYEE EMP "
                    + "INNER JOIN AVN_SYSTEMUSER SU "
                    + "ON EMP.EMPLOYEEID = SU.EMPLOYEEID "
                    + "WHERE SU.USERID   = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userHierarchy = resultSet.getInt("HIERARCHYID");
            }
        } catch (SQLException e) {
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
        return userHierarchy;
    }

    @Override
    public int getRMUserRegionIdByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int territoryid = 0;
        try {
            String sql = "SELECT TME.TERRITORYMAPID "
                    + "FROM AVN_SYSTEMUSER SU "
                    + "INNER JOIN AVN_EMPLOYEE EMP "
                    + "ON SU.EMPLOYEEID = EMP.EMPLOYEEID "
                    + "INNER JOIN AVN_TERRITORYMAPEMPLOYEE TME "
                    + "ON EMP.EMPLOYEEID = TME.EMPLOYEEID "
                    + "INNER JOIN AVN_BRANCH BRN "
                    + "ON TME.TERRITORYMAPID = BRN.BRANCHID "
                    + "WHERE BRN.TERRITORYID = 2 "
                    + "AND SU.USERID         = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                territoryid = resultSet.getInt("TERRITORYMAPID");
            }
        } catch (SQLException e) {
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
        return territoryid;
    }

    @Override
    public Integer[] getEmployeeTerritorys(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer territories[] = new Integer[0];
        try {
            String sql = "SELECT TME.TERRITORYMAPID "
                    + "FROM AVN_SYSTEMUSER SU "
                    + "INNER JOIN AVN_EMPLOYEE EMP "
                    + "ON SU.EMPLOYEEID = EMP.EMPLOYEEID "
                    + "INNER JOIN AVN_TERRITORYMAPEMPLOYEE TME "
                    + "ON EMP.EMPLOYEEID = TME.EMPLOYEEID "
                    + "INNER JOIN AVN_BRANCH BRN "
                    + "ON TME.TERRITORYMAPID = BRN.BRANCHID "
                    + "WHERE BRN.TERRITORYID = 3 "
                    + "AND SU.USERID         = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            List<Integer> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getInt("TERRITORYMAPID"));
            }
            territories = list.toArray(new Integer[0]);
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
        return territories;
    }

    @Override
    public Integer[] getUserRegionBranches(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer territories[] = new Integer[0];
        try {
            String sql = "SELECT BR.BRANCHID "
                    + "FROM AVN_BRANCH BR "
                    + "WHERE BR.PARENTTERRITORYMAPID IN "
                    + "  (SELECT TME.TERRITORYMAPID "
                    + "  FROM AVN_TERRITORYMAPEMPLOYEE TME "
                    + "  INNER JOIN AVN_BRANCH BRN "
                    + "  ON TME.TERRITORYMAPID = BRN.BRANCHID "
                    + "  INNER JOIN AVN_SYSTEMUSER SU "
                    + "  ON TME.EMPLOYEEID     = SU.EMPLOYEEID "
                    + "  WHERE BRN.TERRITORYID = 2 "
                    + "  AND SU.USERID         = ? "
                    + "  )";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            List<Integer> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getInt("BRANCHID"));
            }
            territories = list.toArray(new Integer[0]);
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
        return territories;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.schedulertask;

import com.avn.ccl.dao.schedulertask.ScheduleTaskDAO;
import com.avn.ccl.model.schedulertask.CaseInfo;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @Author : Office Isuru
 * @Document : SchedulerTaskDAOImpl
 * @Date : Sep 21, 2015 4:35:19 PM
 */
public class SchedulerTaskDAOImpl implements ScheduleTaskDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getOrganizationlPrameters91() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String parametrvalue = null;
        try {
            String sql = "Select PARAMETERVALUE from AVN_ORGANIZATIONPARAMETERS where PARAMETERID='2'";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                parametrvalue = resultSet.getString("PARAMETERVALUE");
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
        return parametrvalue;
    }

    @Override
    public String getOrganizationlPrameters101() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String parametrvalue = null;
        try {
            String sql = "Select PARAMETERVALUE from AVN_ORGANIZATIONPARAMETERS where PARAMETERID='3'";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                parametrvalue = resultSet.getString("PARAMETERVALUE");
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
        return parametrvalue;
    }

    @Override
    public String getOrganizationlPrameters201() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String parametrvalue = null;
        try {
            String sql = "Select PARAMETERVALUE from AVN_ORGANIZATIONPARAMETERS where PARAMETERID='1'";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                parametrvalue = resultSet.getString("PARAMETERVALUE");
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
        return parametrvalue;
    }

    @Override
    public List<String> getOpneCase(String Orgparameter) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int parametrvalue = Integer.valueOf(Orgparameter);
        int totalmint = (parametrvalue * 60);
        List<String> cases = new ArrayList<>();
        try {
            String sql = "SELECT (CAST(CURRENT_TIMESTAMP AS DATE) - CAST(LASTUPDATEDDATETIME AS DATE)) * 24 *60  AS DIFF_MINUTES, CASEID FROM AVN_CASE WHERE STATUSID='1'";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (totalmint <= resultSet.getInt("DIFF_MINUTES")) {
                    cases.add(resultSet.getString("CASEID"));

                }
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
        return cases;
    }

    @Override
    public List<String> getBMZMEmail(String caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> depheaddetails = new ArrayList<>();
        try {
            String sql = "SELECT E.EMAIL,INITIALS,FIRSTNAME,LASTNAME FROM AVN_EMPLOYEE E,AVN_CASE C WHERE  C.ASSIGNEEID1= E.EMPLOYEEID and C.CASEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, caseid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                depheaddetails.add(resultSet.getString("EMAIL"));
                depheaddetails.add(resultSet.getString("INITIALS"));
                depheaddetails.add(resultSet.getString("FIRSTNAME"));
                depheaddetails.add(resultSet.getString("LASTNAME"));
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
        return depheaddetails;
    }

    @Override
    public List<String> getRMEmail(String caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> depheaddetails = new ArrayList<>();
        try {
            String sql = "SELECT E.EMAIL,FIRSTNAME,LASTNAME FROM AVN_EMPLOYEE E,AVN_CASE C, AVN_EMPLOYEEBRANCHEMPLOYEECAT EC WHERE C.BRANCHID=EC.BRANCHID and  EC.EMPLOYEECATEGORY=? and ISNOTIFY=? and EC.EMPLOYEEID=E.EMPLOYEEID and C.CASEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER);
            statement.setString(2, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
            statement.setString(3, caseid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                depheaddetails.add(resultSet.getString("EMAIL"));
                depheaddetails.add(resultSet.getString("FIRSTNAME"));
                depheaddetails.add(resultSet.getString("LASTNAME"));
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
        return depheaddetails;
    }

    @Override
    public List<String> getCCPersonmails(String caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> depheaddetails = new ArrayList<>();
        try {
            String sql = "SELECT E.EMAIL,FIRSTNAME,LASTNAME FROM AVN_EMPLOYEE E,AVN_CASE C, AVN_EMPLOYEEBRANCHEMPLOYEECAT EC WHERE C.BRANCHID=EC.BRANCHID and  EC.EMPLOYEECATEGORY=? and ISNOTIFY=? and EC.EMPLOYEEID=E.EMPLOYEEID and C.CASEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_CONTACT_CENTRE);
            statement.setString(2, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
            statement.setString(3, caseid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                depheaddetails.add(resultSet.getString("EMAIL"));
                depheaddetails.add(resultSet.getString("FIRSTNAME"));
                depheaddetails.add(resultSet.getString("LASTNAME"));
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
        return depheaddetails;
    }

    @Override
    public List<String> getInternamAuditMails(String caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> email = new LinkedList<>();
        try {
            String sql = "SELECT E.EMAIL,FIRSTNAME,LASTNAME FROM AVN_EMPLOYEE E,AVN_CASE C, AVN_EMPLOYEEBRANCHEMPLOYEECAT EC WHERE C.BRANCHID=EC.BRANCHID and  EC.EMPLOYEECATEGORY=? and ISNOTIFY=? and EC.EMPLOYEEID=E.EMPLOYEEID and C.CASEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_INTERNAL_AUDIT);
            statement.setString(2, MasterDataVarList.CCL_CODE_NOTIFICATION_TRUE);
            statement.setString(3, caseid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                email.add(resultSet.getString("EMAIL"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return email;
    }

    @Override
    public List<CaseInfo> getCaseByLevelId(int level) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CaseInfo> cases = new ArrayList<>();
        try {
            String sql = "SELECT CASEID, "
                    + "  TO_TIMESTAMP(TO_CHAR(CREATEDDATETIME, 'YYYY-MON-DD HH24:MI:SS'), 'YYYY-MON-DD HH24:MI:SS') AS CREATEDDATETIME, "
                    + "  BRMF, "
                    + "  CASETYPEID "
                    + "FROM AVN_CASE "
                    + "WHERE STATUSID       =" + MasterDataVarList.CCL_CODE_STATUS_OPEN + " "
                    + "AND NOTIFICATIONLEVEL=" + level + " ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            CaseInfo caseInfo;
            while (resultSet.next()) {
                caseInfo = new CaseInfo();
                caseInfo.setCaseid(resultSet.getString("CASEID"));
                caseInfo.setCreatedtime(new Date(resultSet.getTimestamp("CREATEDDATETIME").getTime()));
                caseInfo.setBrmf(resultSet.getString("BRMF"));
                caseInfo.setCasetypeid(resultSet.getInt("CASETYPEID"));
                cases.add(caseInfo);
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
        return cases;
    }

    @Override
    public List<List> caseCreattimeExceedtwoday() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<List> cases = new ArrayList<>();
        try {
            String sql = "select CASEID, TO_CHAR (CREATEDDATETIME, 'DD-MON-YYYY HH24:MI:SS')CREATEDDATETIME, BRMF, CASETYPEID from AVN_CASE where STATUSID='1' and NOTIFICATIONLEVEL='2'";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                List<Object> fullcase = new ArrayList<>();
                fullcase.add(resultSet.getString("CASEID"));
                fullcase.add(resultSet.getString("CREATEDDATETIME"));
                fullcase.add(resultSet.getString("BRMF"));
                fullcase.add(resultSet.getString("CASETYPEID"));
                cases.add(fullcase);
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
        return cases;
    }

    @Override
    public List<List> caseCreattimeExceedtwodayMore() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<List> cases = new ArrayList<>();
        try {
            String sql = "select CASEID, TO_CHAR (CREATEDDATETIME, 'DD-MON-YYYY HH24:MI:SS')CREATEDDATETIME,BRMF,CASETYPEID from AVN_CASE where STATUSID='1' and NOTIFICATIONLEVEL='3'";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                List<Object> fullcase = new ArrayList<>();
                fullcase.add(resultSet.getString("CASEID"));
                fullcase.add(resultSet.getString("CREATEDDATETIME"));
                fullcase.add(resultSet.getString("BRMF"));
                fullcase.add(resultSet.getString("CASETYPEID"));
                cases.add(fullcase);
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
        return cases;
    }

    @Override
    public boolean setNotificationlevel(String notificationlevel, String caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try {
            connection = dataSource.getConnection();
            String sql = "UPDATE AVN_CASE SET NOTIFICATIONLEVEL=? WHERE CASEID=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, notificationlevel);
            statement.setString(2, caseid);
            statement.executeUpdate();
            status = true;
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
        return status;
    }

    @Override
    public List<String> getCreatedUserEmail(String caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> depheademails = new ArrayList<>();
        try {
            String sql = "SELECT S.EMAIL FROM AVN_EMPLOYEE E , AVN_SYSTEMUSER S,AVN_CASE C WHERE S.EMPLOYEEID = E.EMPLOYEEID AND C.CREATEDUSER=S.USERID AND C.CASEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, caseid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                depheademails.add(resultSet.getString("EMAIL"));
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
        return depheademails;
    }

    @Override
    public List<String> getCustomerDetails(String caseid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> cutomerdetails = new ArrayList<>();
        try {
            String sql = "select CUSTOMERNAME,NIC,PHONENUMBER from  AVN_CASE Where CASEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, caseid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cutomerdetails.add(resultSet.getString("CUSTOMERNAME"));
                cutomerdetails.add(resultSet.getString("NIC"));
                cutomerdetails.add(resultSet.getString("PHONENUMBER"));
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
        return cutomerdetails;
    }

}

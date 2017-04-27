/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.branch;

import com.avn.ccl.dao.branch.BranchDAO;
import com.avn.ccl.model.branch.Branch;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Hirantha
 */
public class BranchDAOImpl implements BranchDAO {

    private DataSource dataSource;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Branch getBranch(int id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Branch branch = null;
        try {
            String sql = "SELECT * FROM AVN_BRANCH WHERE BRANCHID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                branch = new Branch();
                branch.setBranchId(Integer.parseInt(resultSet.getString("BRANCHID")));
                branch.setBranchDesc(resultSet.getString("BRANCHDESCRIPTION"));
                branch.setBranchCode(resultSet.getString("BRANCHCODE"));
                branch.setAliasName(resultSet.getString("ALIASNAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return branch;
    }

    @Override
    public Map<String, String> getBranchIdDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> branchList = null;
        try {
            String sql = "SELECT BRANCHID, ALIASNAME FROM branch WHERE TERRITORYID=? ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, MasterDataVarList.AFFINITI_CODE_TERRITORY_BRANCH);
            resultSet = statement.executeQuery();
            branchList = new LinkedHashMap<>();
            branchList.put("", "-- Select --");
            while (resultSet.next()) {
                branchList.put(resultSet.getString("BRANCHID"), resultSet.getString("ALIASNAME"));
            }
        } catch (SQLException exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return branchList;
    }

    @Override
    public Map<String, String> getBranchIdMultiSelect() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> branchList = null;
        try {
            String sql = "SELECT BRANCHID, ALIASNAME FROM AVN_BRANCH ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            branchList = new LinkedHashMap<>();
            while (resultSet.next()) {
                branchList.put(resultSet.getString("BRANCHID"), resultSet.getString("ALIASNAME"));
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
        return branchList;
    }

    @Override
    public Map<String, String> getBranchCodeDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> branchList = null;
        try {
            String sql = "SELECT BRANCHCODE, ALIASNAME FROM AVN_BRANCH ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            branchList = new LinkedHashMap<>();
            branchList.put("", "-- Select --");
            while (resultSet.next()) {
                branchList.put(resultSet.getString("BRANCHCODE"), resultSet.getString("ALIASNAME"));
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

        return branchList;
    }

    @Override
    public Map<String, String> getBranchOldDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> branchList = null;
        try {
            String sql = "SELECT BRANCHCODEOLD, ALIASNAME FROM AVN_BRANCH ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            branchList = new LinkedHashMap<>();
            branchList.put("", "-- Select --");
            while (resultSet.next()) {
                branchList.put(resultSet.getString("BRANCHCODEOLD"), resultSet.getString("ALIASNAME"));
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
        return branchList;
    }

    @Override
    public String getBranchCodeForUser(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String branchcode = "";
        try {
            String sql = "SELECT BRANCHCODE FROM AVN_BRANCH WHERE BRANCHID = (SELECT BRANCHID FROM AVN_EMPLOYEE WHERE EMPLOYEEID = (SELECT EMPLOYEEID FROM AVN_SYSTEMUSER WHERE USERID = ?))";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                branchcode = resultSet.getString("BRANCHCODE");
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
        return branchcode;
    }

    public List<Branch> getBranchIdListByRegionalId(String regionid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Branch> branchList = null;
        try {
            String sql = "SELECT BRANCHID, ALIASNAME FROM AVN_BRANCH WHERE REGIONID=? ORDER BY SORTID";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, regionid);
            resultSet = statement.executeQuery();
            branchList = new ArrayList<>();
            while (resultSet.next()) {
                Branch branch = new Branch();
                branch.setBranchId(Integer.valueOf(resultSet.getString("BRANCHID")));
                branch.setAliasName(resultSet.getString("ALIASNAME"));
                branchList.add(branch);
            }
        } catch (SQLException exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return branchList;
    }

}

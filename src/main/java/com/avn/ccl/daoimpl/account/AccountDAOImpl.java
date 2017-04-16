/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.account;

import com.avn.ccl.dao.account.AccountDAO;
import com.avn.ccl.model.account.Account;
import com.avn.ccl.model.occupation.Occupation;
import com.avn.ccl.util.Common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import javax.sql.DataSource;

/**
 * @Author : Roshen Dilshan
 * @Document : AccountDAOImpl
 * @Created on : Jul 31, 2015, 1:07:08 PM
 */
public class AccountDAOImpl implements AccountDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long createAccount(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long account_id = 0;
        try {
            String sql_delete_address = "DELETE AVN_ACCOUNTADDRESS WHERE ACCOUNT = ?";
            String sql_delete_education = "DELETE AVN_ACCOUNTEDUCATION WHERE ACCOUNTID = ?";
            String sql_delete_subsector = "DELETE AVN_ACCOUNTBUSSUBSECTOR WHERE ACCOUNT = ?";
            String sql_delete_dependent = "DELETE AVN_ACCOUNTDEPENDANTS WHERE ACCOUNTID = ?";
            String sql_delete_hobby = "DELETE FROM AVN_CUSTOMERHOBBIESINTEREST WHERE ACCOUNTID = ?";
            String sql_delete_account = "DELETE FROM AVN_ACCOUNT WHERE CUSTOMERCODE = ?";
            String sql_insert = "INSERT "
                    + "INTO "
                    + "    AVN_ACCOUNT "
                    + "    ( "
                    + "        ACCOUNTTCATEGORY,"
                    + "        ACCOUNTCATEGORYTYPE, "
                    + "        CUSTOMERCODETYPE, "
                    + "        CUSTOMERCODE, "
                    + "        BRANCHCODE, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME "
                    + "    ) "
                    + "    VALUES "
                    + "    ( ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            if (account.getAccount_id() != null && !account.getAccount_id().isEmpty() && Long.valueOf(account.getAccount_id()) != 0) {
                statement = connection.prepareStatement(sql_delete_address);
                statement.setLong(1, Long.valueOf(account.getAccount_id()));
                statement.execute();
                statement = connection.prepareStatement(sql_delete_education);
                statement.setLong(1, Long.valueOf(account.getAccount_id()));
                statement.execute();
                statement = connection.prepareStatement(sql_delete_subsector);
                statement.setLong(1, Long.valueOf(account.getAccount_id()));
                statement.execute();
                statement = connection.prepareStatement(sql_delete_dependent);
                statement.setLong(1, Long.valueOf(account.getAccount_id()));
                statement.execute();
                statement = connection.prepareStatement(sql_delete_hobby);
                statement.setLong(1, Long.valueOf(account.getAccount_id()));
                statement.execute();
            }
            statement = connection.prepareStatement(sql_delete_account);
            statement.setString(1, account.getCustomer_code());
            statement.execute();
            String[] generatedColumns = {"ACCOUNTID"};
            statement = connection.prepareStatement(sql_insert, generatedColumns);
            statement.setInt(1, Integer.valueOf(account.getCustomer_category()));
            statement.setInt(2, Integer.valueOf(account.getCustomer_category_type()));
            statement.setInt(3, Integer.valueOf(account.getCustomer_code_type()));
            statement.setString(4, account.getCustomer_code() == null ? "" : account.getCustomer_code().toUpperCase());
            statement.setInt(5, Integer.valueOf(account.getBranch_location()));
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                account_id = resultSet.getLong(1);
            }
            connection.commit();
        } catch (SQLException sqle) {
            connection.rollback();
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
        return account_id;
    }

    @Override
    public void updateAccountPersonalDetails(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE "
                    + "    AVN_ACCOUNT "
                    + "    SET "
                    + "    TITLE               = ?, "
                    + "    INITIALS            = ?, "
                    + "    PREFFEREDNAME       = ?, "
                    + "    LASTNAME            = ?, "
                    + "    NAMEINFULL          = ?, "
                    + "    DATEOFBIRTH         = ?, "
                    + "    MOTHERSMAIDENNAME   = ?, "
                    + "    NATIONALITYID       = ?, "
                    + "    RELIGONID           = ?, "
                    + "    GENDER              = ?, "
                    + "    MARITALSTATE        = ?, "
                    + "    LANGUAGE            = ?, "
                    + "    TELMOBILE01         = ?, "
                    + "    TELMOBILE02         = ?, "
                    + "    EMAIL               = ?, "
                    + "    LASTUPDATEDDATETIME = CURRENT_TIMESTAMP "
                    + "WHERE "
                    + "    ACCOUNTID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getTitle());
            statement.setString(2, account.getInitials());
            statement.setString(3, account.getPreferred_name());
            statement.setString(4, account.getLast_name());
            statement.setString(5, account.getName_in_full());
            statement.setTimestamp(6, new Timestamp(Common.getDateFromString("yyyy-MM-dd", account.getDate_of_birth()).getTime()));
            statement.setString(7, account.getMothers_maiden_name());
            if (account.getNationality() != null && !account.getNationality().isEmpty()) {
                statement.setInt(8, Integer.valueOf(account.getNationality()));
            } else {
                statement.setNull(8, Types.INTEGER);
            }
            if (account.getReligion() != null && !account.getReligion().isEmpty()) {
                statement.setInt(9, Integer.valueOf(account.getReligion()));
            } else {
                statement.setNull(9, Types.INTEGER);
            }
            statement.setString(10, account.getGender());
            statement.setString(11, account.getMarital_status());
            if (account.getPreferred_language() != null && !account.getPreferred_language().isEmpty()) {
                statement.setInt(12, Integer.valueOf(account.getPreferred_language()));
            } else {
                statement.setNull(12, Types.INTEGER);
            }
            statement.setString(13, account.getMobile_01());
            statement.setString(14, account.getMobile_02());
            statement.setString(15, account.getEmail());
            statement.setLong(16, Long.valueOf(account.getAccount_id()));
            statement.execute();
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
    }

    @Override
    public void updateAccountOccupation(Occupation occupation) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE "
                    + "    AVN_ACCOUNT "
                    + "    SET  "
                    + "    BUSDESIGNATION                = ?, "
                    + "    BUSLEVEL                      = ?, "
                    + "    BUSPROFESSION                 = ?, "
                    + "    BUSEMPLOYEE                   = ?, "
                    + "    BUSSECTOR                     = ?, "
                    + "    LASTUPDATEDDATETIME           = CURRENT_TIMESTAMP "
                    + "WHERE "
                    + "    ACCOUNTID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            if (occupation.getDesignation() != null && !occupation.getDesignation().isEmpty()) {
                statement.setLong(1, Long.valueOf(occupation.getDesignation()));
            } else {
                statement.setNull(1, Types.NUMERIC);
            }
            if (occupation.getLevel() != null && !occupation.getLevel().isEmpty()) {
                statement.setInt(2, Integer.valueOf(occupation.getLevel()));
            } else {
                statement.setNull(2, Types.NUMERIC);
            }
            if (occupation.getProfession() != null && !occupation.getProfession().isEmpty()) {
                statement.setLong(3, Long.valueOf(occupation.getProfession()));
            } else {
                statement.setNull(3, Types.NUMERIC);
            }
            statement.setString(4, occupation.getEmployer());
            if (occupation.getSector() != null && !occupation.getSector().isEmpty()) {
                statement.setLong(5, Long.valueOf(occupation.getSector()));
            } else {
                statement.setNull(5, Types.NUMERIC);
            }
            statement.setLong(6, Long.valueOf(occupation.getAccount_id()));
            statement.execute();
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
    }

    @Override
    public void updateAccountSGC(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE "
                    + "    AVN_ACCOUNT "
                    + "    SET  "
                    + "    SPOUSETITLE                   = ?, "
                    + "    SPPOUSINITIALS                = ?, "
                    + "    SPPOUSFIRSTNAME               = ?, "
                    + "    SPPOUSLASTNAME                = ?, "
                    + "    SPPOUSEFULLNAME               = ?, "
                    + "    SPOUSERESIDENTIALADDRESS      = ?, "
                    + "    SPOUSEOCCUPATION              = ?, "
                    + "    SPOUSEEMPLOYERBUSINESSNAME    = ?, "
                    + "    SPOUSEADDRESSWORK             = ?, "
                    + "    SPOUSETELEPHONEWORK           = ?, "
                    + "    SPYEARSOFEMPLOYEMENTSBUSINESS = ?, "
                    + "    LASTUPDATEDDATETIME           = CURRENT_TIMESTAMP "
                    + "WHERE "
                    + "    ACCOUNTID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getSgc_title());
            statement.setString(2, account.getSgc_initials());
            statement.setString(3, account.getSgc_firstname());
            statement.setString(4, account.getSgc_lastname());
            statement.setString(5, account.getSgc_name_in_full());
            statement.setString(6, account.getSgc_address_residence());
            statement.setString(7, account.getSgc_occupation());
            statement.setString(8, account.getSgc_employer_business_name());
            statement.setString(9, account.getSgc_employer_business_address());
            statement.setString(10, account.getSgc_employer_telephone());
            if (account.getSgc_years_of_employeement_business() != null && !account.getSgc_years_of_employeement_business().isEmpty()) {
                statement.setInt(11, Integer.valueOf(account.getSgc_years_of_employeement_business()));
            } else {
                statement.setNull(11, Types.INTEGER);
            }
            statement.setLong(12, Long.valueOf(account.getAccount_id()));
            statement.execute();
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
    }

    @Override
    public void updateAccountOther(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE "
                    + "    AVN_ACCOUNT "
                    + "    SET "
                    + "    ISTAXPAYEE               = ?, "
                    + "    TAXFILENUMBER            = ?, "
                    + "    SECRETQUESTIONID         = ?, "
                    + "    RESPONSETOSECRETQUESTION = ?, "
                    + "    LASTUPDATEDDATETIME      = CURRENT_TIMESTAMP "
                    + "WHERE "
                    + "    ACCOUNTID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            if (account.getIs_tax_payee() != null && !account.getIs_tax_payee().isEmpty()) {
                statement.setInt(1, Integer.valueOf(account.getIs_tax_payee()));
                statement.setString(2, account.getTax_no());
            } else {
                statement.setNull(1, Types.INTEGER);
                statement.setNull(2, Types.VARCHAR);
            }

            if (account.getSecret_question() != null && !account.getSecret_question().isEmpty()) {
                statement.setInt(3, Integer.valueOf(account.getSecret_question()));
                statement.setString(4, account.getSecret_response());
            } else {
                statement.setNull(3, Types.INTEGER);
                statement.setNull(4, Types.VARCHAR);
            }

            statement.setLong(5, Long.valueOf(account.getAccount_id()));
            statement.execute();
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
    }

    @Override
    public void updateAccountCCID(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE "
                    + "    AVN_ACCOUNT "
                    + "    SET "
                    + "    CCID                  = ?, "
                    + "    CUSTOMERCODE          = ?, "
                    + "    NICFILENAME           = ?, "
                    + "    NICFILELOCATION       = ?, "
                    + "    SIGNATUREFILENAME     = ?, "
                    + "    SIGNATUREFILELOCATION = ?, "
                    + "    LASTUPDATEDDATETIME   = CURRENT_TIMESTAMP "
                    + "WHERE "
                    + "    ACCOUNTID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getCcid());
            statement.setString(2, account.getCustomer_code());
            statement.setString(3, account.getNic_file_name() == null || account.getNic_file_name().trim().isEmpty() ? "" : account.getNic_file_name());
            statement.setString(4, account.getNic_file_location() == null || account.getNic_file_location().trim().isEmpty() ? "" : account.getNic_file_location());
            statement.setString(5, account.getSignature_file_name() == null || account.getSignature_file_name().trim().isEmpty() ? "" : account.getSignature_file_name());
            statement.setString(6, account.getSignature_file_location() == null || account.getSignature_file_location().trim().isEmpty() ? "" : account.getSignature_file_location());
            statement.setLong(7, Long.valueOf(account.getAccount_id()));
            statement.execute();
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
    }

    @Override
    public void selectAccountByCCID(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT "
                    + "    ACCOUNTID, "
                    + "    CCID, "
                    + "    ACCOUNTTCATEGORY, "
                    + "    ACCOUNTCATEGORYTYPE, "
                    + "    CUSTOMERCODETYPE, "
                    + "    CUSTOMERCODE, "
                    + "    BRANCHCODE, "
                    + "    PASSPORT, "
                    + "    TITLE, "
                    + "    INITIALS, "
                    + "    PREFFEREDNAME, "
                    + "    LASTNAME, "
                    + "    NAMEINFULL, "
                    + "    DATEOFBIRTH, "
                    + "    NATIONALITYID, "
                    + "    RELIGONID, "
                    + "    GENDER, "
                    + "    MOTHERSMAIDENNAME, "
                    + "    MARITALSTATE, "
                    + "    LANGUAGE, "
                    + "    COMMID, "
                    + "    TELMOBILE01, "
                    + "    TELMOBILE02, "
                    + "    EMAIL, "
                    + "    SYNCSTATUS, "
                    + "    BUSDESIGNATION, "
                    + "    BUSLEVEL, "
                    + "    BUSPROFESSION, "
                    + "    BUSEMPLOYEE, "
                    + "    BUSSECTOR, "
                    + "    YEARSOFEMPLOYEMENTBUSNESS, "
                    + "    SPOUSETITLE, "
                    + "    SPPOUSEFULLNAME, "
                    + "    SPPOUSINITIALS, "
                    + "    SPPOUSFIRSTNAME, "
                    + "    SPPOUSLASTNAME, "
                    + "    SPOUSERESIDENTIALADDRESS, "
                    + "    SPOUSEOCCUPATION, "
                    + "    SPOUSEEMPLOYERBUSINESSNAME, "
                    + "    SPOUSEADDRESSWORK, "
                    + "    SPOUSETELEPHONEWORK, "
                    + "    SPYEARSOFEMPLOYEMENTSBUSINESS, "
                    + "    ISTAXPAYEE, "
                    + "    TAXFILENUMBER, "
                    + "    SECRETQUESTIONID, "
                    + "    RESPONSETOSECRETQUESTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME "
                    + "FROM "
                    + "    AVN_ACCOUNT "
                    + "WHERE "
                    + "    CCID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getSearhDataBean().getStakeholder_reference_no());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account.setAccount_id(resultSet.getString("ACCOUNTID"));
                account.setNationality(resultSet.getString("NATIONALITYID") == null ? "" : resultSet.getString("NATIONALITYID"));
                account.setPreferred_language(resultSet.getString("LANGUAGE") == null ? "" : resultSet.getString("LANGUAGE"));
                account.setSgc_title(resultSet.getString("SPOUSETITLE") == null ? "" : resultSet.getString("SPOUSETITLE"));
                account.setSgc_name_in_full(resultSet.getString("SPPOUSEFULLNAME") == null ? "" : resultSet.getString("SPPOUSEFULLNAME"));
                account.setSgc_initials(resultSet.getString("SPPOUSINITIALS") == null ? "" : resultSet.getString("SPPOUSINITIALS"));
                account.setSgc_lastname(resultSet.getString("SPPOUSLASTNAME") == null ? "" : resultSet.getString("SPPOUSLASTNAME"));
                account.setSgc_firstname(resultSet.getString("SPPOUSFIRSTNAME") == null ? "" : resultSet.getString("SPPOUSFIRSTNAME"));
                account.setSgc_address_residence(resultSet.getString("SPOUSERESIDENTIALADDRESS") == null ? "" : resultSet.getString("SPOUSERESIDENTIALADDRESS"));
                account.setSgc_occupation(resultSet.getString("SPOUSEOCCUPATION") == null ? "" : resultSet.getString("SPOUSEOCCUPATION"));
                account.setSgc_employer_business_name(resultSet.getString("SPOUSEEMPLOYERBUSINESSNAME") == null ? "" : resultSet.getString("SPOUSEEMPLOYERBUSINESSNAME"));
                account.setSgc_employer_business_address(resultSet.getString("SPOUSEADDRESSWORK") == null ? "" : resultSet.getString("SPOUSEADDRESSWORK"));
                account.setSgc_employer_telephone(resultSet.getString("SPOUSETELEPHONEWORK") == null ? "" : resultSet.getString("SPOUSETELEPHONEWORK"));
                account.setSgc_years_of_employeement_business(resultSet.getString("SPYEARSOFEMPLOYEMENTSBUSINESS") == null ? "" : resultSet.getString("SPYEARSOFEMPLOYEMENTSBUSINESS"));

                account.setSecret_question(resultSet.getString("SECRETQUESTIONID") == null ? "" : resultSet.getString("SECRETQUESTIONID"));
                account.setSecret_response(resultSet.getString("RESPONSETOSECRETQUESTION") == null ? "" : resultSet.getString("RESPONSETOSECRETQUESTION"));
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
    }

    @Override
    public void updateAccountCorporate(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE "
                    + "    AVN_ACCOUNT "
                    + "    SET  "
                    + "    BUSEMPLOYEE                   = ?, "
                    + "    BUSSECTOR                     = ?, "
                    + "    LASTUPDATEDDATETIME           = CURRENT_TIMESTAMP "
                    + "WHERE "
                    + "    ACCOUNTID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getCopemployer());
            statement.setLong(2, Long.valueOf(account.getCopsector()));
            statement.setLong(3, Long.valueOf(account.getAccount_id()));
            statement.execute();
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
    }

    @Override
    public void updateAccountContactPerson(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE "
                    + "    AVN_ACCOUNT "
                    + "    SET CONTACTPERSONNIC       = ?, "
                    + "    CONTACTPERSONTITLE         = ?, "
                    + "    CONTACTPERSONNAMEINFULL    = ?, "
                    + "    CONTACTPERSONINITIALS      = ?, "
                    + "    CONTACTPERSONLASTNAME      = ?, "
                    + "    CONTACTPERSONPREFERREDNAME = ?, "
                    + "    CONTACTPERSONMOBILE        = ?, "
                    + "    CONTACTPERSONEMAIL         = ?, "
                    + "    LASTUPDATEDDATETIME        = CURRENT_TIMESTAMP "
                    + "WHERE "
                    + "    ACCOUNTID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getCp_nic() == null || account.getCp_nic().trim().isEmpty() ? "" : account.getCp_nic().trim());
            statement.setString(2, account.getCp_title() == null || account.getCp_title().trim().isEmpty() ? "" : account.getCp_title().trim());
            statement.setString(3, account.getCp_name_in_full() == null || account.getCp_name_in_full().trim().isEmpty() ? "" : account.getCp_name_in_full().trim());
            statement.setString(4, account.getCp_initials() == null || account.getCp_initials().trim().isEmpty() ? "" : account.getCp_initials().trim());
            statement.setString(5, account.getCp_last_name() == null || account.getCp_last_name().trim().isEmpty() ? "" : account.getCp_last_name().trim());
            statement.setString(6, account.getCp_preferred_name() == null || account.getCp_preferred_name().trim().isEmpty() ? "" : account.getCp_preferred_name().trim());
            statement.setString(7, account.getCp_mobile() == null || account.getCp_mobile().trim().isEmpty() ? "" : account.getCp_mobile().trim());
            statement.setString(8, account.getCp_email() == null || account.getCp_email().trim().isEmpty() ? "" : account.getCp_email());
            statement.setLong(9, Long.valueOf(account.getAccount_id()));
            statement.execute();
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
    }

    @Override
    public Account selectAccountByCustomerCode(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT "
                    + "    ACCOUNTID, "
                    + "    CCID, "
                    + "    ACCOUNTTCATEGORY, "
                    + "    ACCOUNTCATEGORYTYPE, "
                    + "    CUSTOMERCODETYPE, "
                    + "    CUSTOMERCODE, "
                    + "    BRANCHCODE, "
                    + "    PASSPORT, "
                    + "    TITLE, "
                    + "    INITIALS, "
                    + "    PREFFEREDNAME, "
                    + "    LASTNAME, "
                    + "    NAMEINFULL, "
                    + "    DATEOFBIRTH, "
                    + "    NATIONALITYID, "
                    + "    RELIGONID, "
                    + "    GENDER, "
                    + "    MOTHERSMAIDENNAME, "
                    + "    MARITALSTATE, "
                    + "    LANGUAGE, "
                    + "    COMMID, "
                    + "    TELMOBILE01, "
                    + "    TELMOBILE02, "
                    + "    EMAIL, "
                    + "    SYNCSTATUS, "
                    + "    BUSDESIGNATION, "
                    + "    BUSLEVEL, "
                    + "    BUSPROFESSION, "
                    + "    BUSEMPLOYEE, "
                    + "    BUSSECTOR, "
                    + "    YEARSOFEMPLOYEMENTBUSNESS, "
                    + "    SPOUSETITLE, "
                    + "    SPPOUSEFULLNAME, "
                    + "    SPPOUSINITIALS, "
                    + "    SPPOUSFIRSTNAME, "
                    + "    SPPOUSLASTNAME, "
                    + "    SPOUSERESIDENTIALADDRESS, "
                    + "    SPOUSEOCCUPATION, "
                    + "    SPOUSEEMPLOYERBUSINESSNAME, "
                    + "    SPOUSEADDRESSWORK, "
                    + "    SPOUSETELEPHONEWORK, "
                    + "    SPYEARSOFEMPLOYEMENTSBUSINESS, "
                    + "    ISTAXPAYEE, "
                    + "    TAXFILENUMBER, "
                    + "    SECRETQUESTIONID, "
                    + "    RESPONSETOSECRETQUESTION, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME "
                    + "FROM "
                    + "    AVN_ACCOUNT "
                    + "WHERE "
                    + "    CUSTOMERCODE = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getCustomer_code());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account.setAccount_id(resultSet.getString("ACCOUNTID"));
                account.setSgc_title(resultSet.getString("SPOUSETITLE") == null ? "" : resultSet.getString("SPOUSETITLE"));
                account.setSgc_name_in_full(resultSet.getString("SPPOUSEFULLNAME") == null ? "" : resultSet.getString("SPPOUSEFULLNAME"));
                account.setSgc_initials(resultSet.getString("SPPOUSINITIALS") == null ? "" : resultSet.getString("SPPOUSINITIALS"));
                account.setSgc_lastname(resultSet.getString("SPPOUSLASTNAME") == null ? "" : resultSet.getString("SPPOUSLASTNAME"));
                account.setSgc_firstname(resultSet.getString("SPPOUSFIRSTNAME") == null ? "" : resultSet.getString("SPPOUSFIRSTNAME"));
                account.setSgc_address_residence(resultSet.getString("SPOUSERESIDENTIALADDRESS") == null ? "" : resultSet.getString("SPOUSERESIDENTIALADDRESS"));
                account.setSgc_occupation(resultSet.getString("SPOUSEOCCUPATION") == null ? "" : resultSet.getString("SPOUSEOCCUPATION"));
                account.setSgc_employer_business_name(resultSet.getString("SPOUSEEMPLOYERBUSINESSNAME") == null ? "" : resultSet.getString("SPOUSEEMPLOYERBUSINESSNAME"));
                account.setSgc_employer_business_address(resultSet.getString("SPOUSEADDRESSWORK") == null ? "" : resultSet.getString("SPOUSEADDRESSWORK"));
                account.setSgc_employer_telephone(resultSet.getString("SPOUSETELEPHONEWORK") == null ? "" : resultSet.getString("SPOUSETELEPHONEWORK"));
                account.setSgc_years_of_employeement_business(resultSet.getString("SPYEARSOFEMPLOYEMENTSBUSINESS") == null ? "" : resultSet.getString("SPYEARSOFEMPLOYEMENTSBUSINESS"));

                account.setSecret_question(resultSet.getString("SECRETQUESTIONID") == null ? "" : resultSet.getString("SECRETQUESTIONID"));
                account.setSecret_response(resultSet.getString("RESPONSETOSECRETQUESTION") == null ? "" : resultSet.getString("RESPONSETOSECRETQUESTION"));
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
        return account;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.salesPipline;

import com.avn.ccl.dao.salesPipline.SalesPiplineDAO;
import com.avn.ccl.model.account.Account;
import com.avn.ccl.model.contact.Contact;
import com.avn.ccl.model.lead.Lead;
import com.avn.ccl.model.product.Product;
import com.avn.ccl.model.userrolehiarachy.UserRoleHiarachy;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.DateTime;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Rasika Madushanka
 */
public class SalesPiplineDAOImpl implements SalesPiplineDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Contact> getContact(String where) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Contact> Contactlist = null;
        try {
            String sql = "SELECT contacts.CONTACTID,title.DESCRIPTION,"
                    + "contacts.NAMEINFULL,contacts.JOBTITLE,contacts."
                    + "MOBILE FROM contacts INNER JOIN title ON contacts."
                    + "TITLE=title.TITLECODE "
                    + ":where ORDER BY contacts.CREATEDDATETIME DESC";
            sql = sql.replace(":where", where);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            Contactlist = new ArrayList<>();
            Contact contact;
            while (resultSet.next()) {
                contact = new Contact();
                contact.setContactid(resultSet.getInt("CONTACTID"));
                if (resultSet.getString("DESCRIPTION") != null) {
                    contact.setTitle(resultSet.getString("DESCRIPTION"));
                } else {
                    contact.setTitle("");
                }
                if (resultSet.getString("NAMEINFULL") != null) {
                    contact.setNameInFull(resultSet.getString("NAMEINFULL"));
                } else {
                    contact.setNameInFull("");
                }
                if (resultSet.getString("JOBTITLE") != null) {
                    contact.setJobtitle(resultSet.getString("JOBTITLE"));
                } else {
                    contact.setJobtitle("");
                }
                if (resultSet.getString("MOBILE") != null) {
                    contact.setMobile(resultSet.getString("MOBILE"));
                } else {
                    contact.setMobile("");
                }
                Contactlist.add(contact);
            }
        } catch (SQLException | NumberFormatException e) {
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
        return Contactlist;
    }

    public long insertLead(Lead lead, String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long leadid = 0;
        try {
            String sql = "INSERT "
                    + "INTO lead "
                    + "  ( "
                    + "    PRODUCTID, "
                    + "    CONTACTID, "
                    + "    LEADAMOUNT, "
                    + "    CREATEDUSER, "
                    + "    STATUS, "
                    + "    FORCASTUNTILDATE, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDATETIME, "
                    + "    CHANNELID, "
                    + "    CAMPAIGNID, "
                    + "    PROMOTIONCODE, "
                    + "    ACCOUNTCOUNT "
                    + "  ) "
                    + "  VALUES "
                    + "  (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
            connection = dataSource.getConnection();
            String[] generatedColumns = {"LEADID"};
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setInt(1, lead.getProductid());
            statement.setLong(2, lead.getContactid());
            if (lead.getLeadamount() == null || lead.getLeadamount().compareTo(BigDecimal.ZERO) == 0) {
                statement.setNull(3, Types.BIGINT);
            } else {
                statement.setBigDecimal(3, lead.getLeadamount());
            }
            statement.setString(4, username);
            statement.setString(5, "37");
            if (lead.getForcastuntildate() == null) {
                statement.setNull(6, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(6, new Timestamp(Common.getDateFromString("yyyy-MM-dd", lead.getForcastuntildate()).getTime()));
            }
            if (lead.getChannelId() == 0) {
                statement.setNull(7, Types.INTEGER);
            } else {
                statement.setInt(7, lead.getChannelId());
            }
            if (lead.getCampignId() == 0) {
                statement.setNull(8, Types.INTEGER);
            } else {
                statement.setInt(8, lead.getCampignId());
            }
            statement.setString(9, lead.getPromationCode());
            statement.setInt(10, lead.getAccouncount());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                leadid = resultSet.getLong(1);
            }
        } catch (SQLException ex) {
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
                } catch (Exception ex) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                }
            }
        }
        return leadid;
    }

    public List<Contact> getLead(String where, String leadType) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Contact> Contactlist = null;
        try {
            String sql = "SELECT title.DESCRIPTION, "
                    + "          contacts.NAMEINFULL, "
                    + "          contacts.JOBTITLE, "
                    + "          contacts.CONTACTID, "
                    + "          contacts.MOBILE, "
                    + "          lead.LEADAMOUNT, "
                    + "          lead.CONFIRMEDAMOUNT, "
                    + "          lead.LEADID, "
                    + "          lead.SALECLOSEDDATE, "
                    + "          lead.SALELOSTDATE, "
                    + "          contacts.CUSTOMERACCOUNTID, "
                    + "          contacts.STATUS "
                    + "FROM contacts INNER JOIN lead ON contacts.CONTACTID=lead.CONTACTID INNER JOIN title ON contacts.TITLE=title.TITLECODE :where "
                    + "ORDER BY LASTUPDATEDATETIME DESC ";
            sql = sql.replace(":where", where);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            Contactlist = new ArrayList<>();
            Contact contact;
            while (resultSet.next()) {
                contact = new Contact();
                if ("CLOSED".equals(leadType)) {
                    contact.setLeadamount(Common.sum(resultSet.getLong("CONFIRMEDAMOUNT")));
                } else {
                    contact.setLeadamount(Common.sum(resultSet.getLong("LEADAMOUNT")));
                }
                if (resultSet.getString("DESCRIPTION") != null) {
                    contact.setTitle(resultSet.getString("DESCRIPTION"));
                } else {
                    contact.setTitle("");
                }
                if (resultSet.getString("NAMEINFULL") != null) {
                    contact.setNameInFull(resultSet.getString("NAMEINFULL"));
                } else {
                    contact.setNameInFull("");
                }
                if (resultSet.getString("JOBTITLE") != null) {
                    contact.setJobtitle(resultSet.getString("JOBTITLE"));
                } else {
                    contact.setJobtitle("");
                }
                if (resultSet.getString("MOBILE") != null) {
                    contact.setMobile(resultSet.getString("MOBILE"));
                } else {
                    contact.setMobile("");
                }
                contact.setLeadid(resultSet.getInt("LEADID"));
                contact.setCustomer_account_id(resultSet.getInt("CUSTOMERACCOUNTID"));
                contact.setContact_status(resultSet.getString("STATUS"));
                contact.setContactid(resultSet.getInt("CONTACTID"));

                if (resultSet.getDate("SALECLOSEDDATE") != null) {
                    contact.setLead_closed_date(Common.getMonthAndDate(resultSet.getDate("SALECLOSEDDATE")));
                }
                if (resultSet.getDate("SALELOSTDATE") != null) {
                    contact.setLead_lost_date(Common.getMonthAndDate(resultSet.getDate("SALELOSTDATE")));
                }

                Contactlist.add(contact);
            }
        } catch (SQLException | NumberFormatException e) {
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
        return Contactlist;
    }

    public int getCountContactTable(String where) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int maxLeadId = 0;
        try {
            String sql = "SELECT COUNT(CONTACTID) AS COUNTCONTACTTABLE FROM  contacts :where";
            sql = sql.replace(":where", where);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                maxLeadId = resultSet.getInt("COUNTCONTACTTABLE");
            }
        } catch (Exception exception) {
            throw exception;
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

        return maxLeadId;
    }

    public int getCountLeadTable(String where) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "SELECT COUNT(LEADID) AS COUNTLEADTABLE FROM contacts INNER JOIN lead ON contacts.CONTACTID=lead.CONTACTID :where";
            sql = sql.replace(":where", where);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("COUNTLEADTABLE");
            }
        } catch (Exception exception) {
            throw exception;
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

        return count;
    }

    public String getHaveAccount(int leadId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String accountId = "";
        try {
            String sql = "SELECT contacts.CUSTOMERACCOUNTID FROM contacts INNER JOIN lead ON  "
                    + "contacts.CONTACTID=lead.CONTACTID WHERE lead.LEADID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, leadId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountId = resultSet.getString("CUSTOMERACCOUNTID");
            }
        } catch (Exception exception) {
            throw exception;
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

        return accountId;
    }

    public String getName(int leadId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String namefull = "";
        String name = "";
        String title = "";
        try {
            String sql = "SELECT contacts.NAMEINFULL,contacts.TITLE FROM contacts INNER JOIN lead ON  "
                    + "contacts.CONTACTID=lead.CONTACTID WHERE lead.LEADID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, leadId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("NAMEINFULL");
                title = resultSet.getString("TITLE");
            }
            namefull = title + "/" + name;
        } catch (Exception exception) {
            throw exception;
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

        return namefull;
    }

    public Account getAccountDetail(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Account account1 = null;
        try {
            String sql = "SELECT  ACCOUNTID,ACCOUNTTCATEGORY,TITLE,INITIALS,PREFFEREDNAME, "
                    + "LASTNAME,NAMEINFULL,DATEOFBIRTH,GENDER,MOTHERSMAIDENNAME FROM AVN_ACCOUNT WHERE ACCOUNTID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getAccount_id());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account1 = new Account();
                account1.setAccount_id(resultSet.getString("ACCOUNTID"));
                account1.setAccount_type(resultSet.getString("ACCOUNTTCATEGORY"));
                account1.setTitle(resultSet.getString("TITLE"));
                account1.setInitials(resultSet.getString("INITIALS"));
                account1.setPreferred_name(resultSet.getString("PREFFEREDNAME"));
                account1.setLast_name(resultSet.getString("LASTNAME"));
                account1.setName_in_full(resultSet.getString("NAMEINFULL"));
                account1.setDate_of_birth(resultSet.getString("DATEOFBIRTH"));
                account1.setGender(resultSet.getString("GENDER"));
                account1.setMothers_maiden_name(resultSet.getString("MOTHERSMAIDENNAME"));
            }
        } catch (Exception exception) {
            throw exception;
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

        return account1;
    }

    public long createAccountIndividual(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long account_id = 0;
        try {
            String sql_insert = "INSERT "
                    + "INTO "
                    + "    account "
                    + "    ( "
                    + "        ACCOUNTTCATEGORY,"
                    + "        TITLE, "
                    + "        INITIALS, "
                    + "        PREFFEREDNAME, "
                    + "        LASTNAME, "
                    + "        NAMEINFULL, "
                    + "        DATEOFBIRTH, "
                    + "        GENDER, "
                    + "        MOTHERSMAIDENNAME, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME, "
                    + "        ACCOUNTCATEGORYTYPE "
                    + "    ) "
                    + "    VALUES "
                    + "    ( ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
            connection = dataSource.getConnection();

            String[] generatedColumns = {"ACCOUNTID"};
            statement = connection.prepareStatement(sql_insert, generatedColumns);
            statement.setInt(1, Integer.valueOf(account.getAccount_type()));
            statement.setString(2, account.getTitle());
            statement.setString(3, account.getInitials());
            statement.setString(4, account.getPreferred_name());
            statement.setString(5, account.getLast_name());
            statement.setString(6, account.getName_in_full());
            statement.setTimestamp(7, new Timestamp(Common.getDateFromString("yyyy-MM-dd", account.getDate_of_birth()).getTime()));
            statement.setString(8, account.getGender());
            statement.setString(9, account.getMothers_maiden_name());
            statement.setString(10, account.getCustomer_category_type());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                account_id = resultSet.getLong(1);
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
        return account_id;
    }

    public long createAccountCorparate(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long account_id = 0;
        try {
            String sql_insert = "INSERT "
                    + "INTO "
                    + "    account "
                    + "    ( "
                    + "        ACCOUNTTCATEGORY,"
                    + "        BUSEMPLOYEE, "
                    + "        BUSSECTOR, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME, "
                    + "        ACCOUNTCATEGORYTYPE "
                    + "    ) "
                    + "    VALUES "
                    + "    ( ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
            connection = dataSource.getConnection();

            String[] generatedColumns = {"ACCOUNTID"};
            statement = connection.prepareStatement(sql_insert, generatedColumns);
            statement.setInt(1, Integer.valueOf(account.getAccount_type()));
            statement.setString(2, account.getCopemployer());
            statement.setString(3, account.getCopsector());
            statement.setString(4, account.getCustomer_category_type());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                account_id = resultSet.getLong(1);
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
        return account_id;
    }

    //set actual lead amount to lead table
    public void actualLeadAmount(int lead_id, BigDecimal actual_amount, int actual_account_count) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE lead SET CONFIRMEDAMOUNT = ?, ACTUALACCOUNTCOUNT = ? WHERE LEADID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1, actual_amount);
            statement.setInt(2, actual_account_count);
            statement.setInt(3, lead_id);
            statement.execute();
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
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

    public void updateLeadClosed(String status, int lead_id, String remark) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE lead  SET STATUS= ? , LASTUPDATEDATETIME = CURRENT_TIMESTAMP, SALECLOSEDDATE = CURRENT_TIMESTAMP, REMARK = ?  WHERE   LEADID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setString(2, remark);
            statement.setInt(3, lead_id);
            statement.execute();
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
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

    public int getcontactId(int lead_id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int contactId = 0;
        try {
            String sql = "SELECT CONTACTID FROM lead WHERE LEADID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, lead_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contactId = resultSet.getInt("CONTACTID");
            }
        } catch (Exception exception) {
            throw exception;
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

        return contactId;
    }

    public void updateContactClosed(long account_id, int contact_id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE "
                    + "    contacts "
                    + "    SET  "
                    + "    CUSTOMERACCOUNTID                   = ?, "
                    + "    STATUS                   = ?"
                    + "WHERE "
                    + "    CONTACTID = ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, account_id);
            statement.setString(2, "INIT");
            statement.setLong(3, contact_id);
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

    public BigDecimal getSumLeadTable(String where) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        BigDecimal sum = null;
        try {
            String sql = "SELECT SUM(LEADAMOUNT) AS SUMLEADTABLE FROM lead,contacts :where AND contacts.CONTACTID = lead.CONTACTID ";
            sql = sql.replace(":where", where);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getBigDecimal("SUMLEADTABLE");
            }
            if (sum == null) {
                sum = BigDecimal.ZERO;

            }

        } catch (Exception exception) {
            throw exception;
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
        return sum;
    }

    public Map<String, String> getOpportunityLostReasonsDropdownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> Opportunitylist = null;
        try {
            String sql = "SELECT OPPORTUNITYLOSTID, DESCRIPTION FROM opportunitylostreasons ORDER BY OPPORTUNITYLOSTID ASC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            Opportunitylist = new LinkedHashMap<>();
            Opportunitylist.put("", "-- Select --");
            while (resultSet.next()) {
                Opportunitylist.put(resultSet.getString("OPPORTUNITYLOSTID"), resultSet.getString("DESCRIPTION"));
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
        return Opportunitylist;
    }

    public void updateLeadOpportunityLost(int leadid, int opportunityid, String remark) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "UPDATE lead SET SALELOSTREASONCODE = ?, REMARK = ?, STATUS = ?, LASTUPDATEDATETIME = CURRENT_TIMESTAMP, SALELOSTDATE = CURRENT_TIMESTAMP WHERE LEADID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, opportunityid);
            statement.setString(2, remark);
            statement.setString(3, "39");
            statement.setInt(4, leadid);
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

    public long insertcontact(Contact contact, String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long contactid = 0;
        try {
            String sql = "INSERT INTO contacts (TITLE,NAMEINFULL,JOBTITLE,EMPLOYER,EMAIL,MOBILE,CREATEDDATETIME,CREATEDUSER,ISDEALER)VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP,?,?)";
            String[] generatedColumns = {"CONTACTID"};
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql, generatedColumns);
            statement.setString(1, contact.getTitle());
            statement.setString(2, contact.getNameInFull());
            statement.setString(3, contact.getJobtitle());
            statement.setString(4, contact.getEmployer());
            statement.setString(5, contact.getEmail());
            statement.setString(6, contact.getMobile());
            statement.setString(7, username);
            statement.setString(8, contact.getIsDealer());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                contactid = resultSet.getLong(1);
            }
//            loggingsrvice.getAccessLogger().info( user + " create a new section, " + "Section name is"+section.getDescription());
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
        return contactid;
    }

    public int getUserAccessValue(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int userAccessId = 0;
        try {
            String sql = "SELECT USERACCESSROLEID FROM AVN_SYSTEMUSER WHERE USERID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userAccessId = resultSet.getInt("USERACCESSROLEID");
            }
        } catch (Exception exception) {
            throw exception;
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

        return userAccessId;
    }

    public String getUserNameInFull(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String userNameInFull = "";
        try {
            String sql = "SELECT  employee.NAMEINFULL FROM systemuser INNER JOIN employee "
                    + "ON  employee.EMPLOYEEID=systemuser.EMPLOYEEID WHERE USERID= ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("NAMEINFULL");
                userNameInFull = firstName + " (ME)";
            }
        } catch (Exception exception) {
            throw exception;
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

        return userNameInFull;
    }

    public List<UserRoleHiarachy> getUserRoleHiarachy(int immediateSeniorId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRoleHiarachy> UserroleHiarachyList = null;
        try {
            String sql = "SELECT IMMEDIATESENIORID,USERACCESSROLEID FROM AVN_USERROLEHIARACHY WHERE IMMEDIATESENIORID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, immediateSeniorId);
            resultSet = statement.executeQuery();
            UserroleHiarachyList = new ArrayList<>();
            UserRoleHiarachy userRoleHiarachy;
            while (resultSet.next()) {
                userRoleHiarachy = new UserRoleHiarachy();
                userRoleHiarachy.setImmediateSeniorId(resultSet.getInt("IMMEDIATESENIORID"));
                userRoleHiarachy.setUserAccessRoleId(resultSet.getInt("USERACCESSROLEID"));
                UserroleHiarachyList.add(userRoleHiarachy);
            }
        } catch (SQLException | NumberFormatException e) {
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
        return UserroleHiarachyList;
    }

    public Map<String, String> getSubbordinateUserList(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> userDetailList = null;
        try {
            String sql = "SELECT DISTINCT (SU.USERID), OH.DESCRIPTION, EMP.NAMEINFULL FROM employeesupervisor ES INNER JOIN employee EMP ON ES.EMPLOYEEID = EMP.EMPLOYEEID INNER JOIN organizationhierarchy OH ON EMP.HIERARCHYID = OH.HIERACHYID INNER JOIN systemuser SU ON EMP.EMPLOYEEID = SU.EMPLOYEEID WHERE ES.SUPERVISOR = (SELECT EMPLOYEEID FROM systemuser WHERE USERID = ?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            userDetailList = new LinkedHashMap<>();
            while (resultSet.next()) {
                userDetailList.put(resultSet.getString("USERID"), resultSet.getString("NAMEINFULL") + "|" + resultSet.getString("DESCRIPTION"));
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
        return userDetailList;
    }

    public Map<String, String> getSubbordinateMaxAccessUserList(long empid) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> userDetailList = null;
        try {
            String sql = "SELECT DISTINCT (SU.USERID),  "
                    + "  OH.DESCRIPTION,  "
                    + "  EMP.NAMEINFULL  "
                    + "FROM employee EMP  "
                    + "INNER JOIN organizationhierarchy OH  "
                    + "ON EMP.HIERARCHYID = OH.HIERACHYID  "
                    + "INNER JOIN systemuser SU  "
                    + "ON EMP.EMPLOYEEID     = SU.EMPLOYEEID  "
                    + "WHERE EMP.HIERARCHYID >  "
                    + "  (SELECT SPA.MAXACCESSLEVEL FROM salespipelineaccess SPA WHERE SPA.EMPLOYEEID = ?)"
                    + "  AND EMP.EMPLOYEEID <> ? ";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, empid);
            statement.setLong(2, empid);
            resultSet = statement.executeQuery();
            userDetailList = new LinkedHashMap<>();
            while (resultSet.next()) {
                userDetailList.put(resultSet.getString("USERID"), resultSet.getString("") + "|" + resultSet.getString("DESCRIPTION"));
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
        return userDetailList;
    }

    public Map<String, String> getUserDetailDropDownListWithLoggedUser(int immediateSeniorId, String username, String fullName) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> userDetailList = null;
        try {
            List<UserRoleHiarachy> UserroleHiarachyList = new ArrayList<>();
            UserroleHiarachyList = this.getUserRoleHiarachy(immediateSeniorId);
            String whereclusers = "";
            int size = UserroleHiarachyList.size();
            String sql = "";
            if (size > 0) {
                for (int i = 0; i < UserroleHiarachyList.size(); i++) {
                    UserRoleHiarachy userRoleHiarachy = new UserRoleHiarachy();
                    userRoleHiarachy = UserroleHiarachyList.get(i);
                    whereclusers = whereclusers + "AVN_SYSTEMUSER.USERACCESSROLEID=" + userRoleHiarachy.getUserAccessRoleId();
                    if (i < (UserroleHiarachyList.size() - 1)) {
                        whereclusers = whereclusers + " OR ";
                    }
                }

                sql = "SELECT AVN_SYSTEMUSER.USERID,AVN_EMPLOYEE.FIRSTNAME,AVN_EMPLOYEE.LASTNAME FROM AVN_EMPLOYEE "
                        + "INNER JOIN AVN_SYSTEMUSER "
                        + "ON AVN_EMPLOYEE.EMPLOYEEID=AVN_SYSTEMUSER.EMPLOYEEID "
                        + "WHERE :where";
                sql = sql.replace(":where", whereclusers);
            } else {
                whereclusers = whereclusers + "AVN_SYSTEMUSER.USERID='" + username + "'";
                sql = "SELECT AVN_SYSTEMUSER.USERID,AVN_EMPLOYEE.FIRSTNAME,AVN_EMPLOYEE.LASTNAME FROM AVN_EMPLOYEE "
                        + "INNER JOIN AVN_SYSTEMUSER "
                        + "ON AVN_EMPLOYEE.EMPLOYEEID=AVN_SYSTEMUSER.EMPLOYEEID "
                        + "WHERE :where";
                sql = sql.replace(":where", whereclusers);
            }

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            userDetailList = new LinkedHashMap<>();
            userDetailList.put(username, fullName);
            while (resultSet.next()) {
                userDetailList.put(resultSet.getString("USERID"), resultSet.getString("FIRSTNAME") + " " + resultSet.getString("LASTNAME"));
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
        return userDetailList;
    }

    public Map<String, String> getContactDropDownList(String username) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> contactList = null;
        try {

            String sql = "SELECT  CONTACTID,NAMEINFULL FROM contacts WHERE CREATEDUSER=? ORDER BY contacts.CREATEDDATETIME DESC";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            contactList = new LinkedHashMap<>();
            while (resultSet.next()) {
                contactList.put(resultSet.getString("CONTACTID"), resultSet.getString("NAMEINFULL"));
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
        return contactList;
    }

    public List<Contact> getContactDetail(int contactId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Contact> contactList = new ArrayList<>();;
        Contact contact = null;
        try {
            String sql = "SELECT  CONTACTID,NAMEINFULL,JOBTITLE,EMPLOYER,EMAIL,MOBILE,ISDEALER FROM contacts WHERE CONTACTID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, contactId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contact = new Contact();
                contact.setNameInFull(resultSet.getString("NAMEINFULL"));
                contact.setJobtitle(resultSet.getString("JOBTITLE"));
                contact.setEmployer(resultSet.getString("EMPLOYER"));
                contact.setEmail(resultSet.getString("EMAIL"));
                contact.setMobile(resultSet.getString("MOBILE"));
                contact.setContactid(resultSet.getInt("CONTACTID"));
                contact.setIsDealer(resultSet.getString("ISDEALER"));
                contactList.add(contact);
            }
        } catch (Exception exception) {
            throw exception;
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

        return contactList;
    }

    public List<Lead> getLeadOne(int leadId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Lead> leadList = new ArrayList<>();;
        Lead lead = null;
        try {

            String sql = "SELECT lead.LEADAMOUNT, "
                    + "  product.PRODUCTID, "
                    + "  lead.FORCASTUNTILDATE, "
                    + "  lead. SALELOSTREASONCODE, "
                    + "  lead.SALELOSTDATE, "
                    + "  lead.SALECLOSEDDATE, "
                    + "  lead.CONFIRMEDAMOUNT, "
                    + "  lead.ACCOUNTCOUNT,"
                    + "  lead.ACTUALACCOUNTCOUNT "
                    + "FROM lead "
                    + "INNER JOIN product "
                    + "ON lead.PRODUCTID = product.PRODUCTID "
                    + "WHERE lead.LEADID = ?";

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, leadId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                lead = new Lead();
                lead.setLeadamount(resultSet.getBigDecimal("LEADAMOUNT"));
                lead.setProductid(resultSet.getInt("PRODUCTID"));
                lead.setForcastuntildate(DateTime.getDateString(resultSet.getTimestamp("FORCASTUNTILDATE")));
                if (resultSet.getTimestamp("SALECLOSEDDATE") != null) {
                    lead.setSalecloseddate(DateTime.getDateString(resultSet.getTimestamp("SALECLOSEDDATE")));
                }
                lead.setConfirmedamount(resultSet.getBigDecimal("CONFIRMEDAMOUNT"));
                lead.setLostresoncode(resultSet.getInt("SALELOSTREASONCODE"));
                if (resultSet.getTimestamp("SALELOSTDATE") != null) {
                    lead.setLostdate(DateTime.getDateString(resultSet.getTimestamp("SALELOSTDATE")));
                }
                lead.setAccouncount(resultSet.getInt("ACCOUNTCOUNT"));
                lead.setActualaccountcount(resultSet.getInt("ACTUALACCOUNTCOUNT"));
                leadList.add(lead);
            }
        } catch (Exception exception) {
            throw exception;
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

        return leadList;
    }

    public void updateContact(Contact contact) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE contacts "
                    + "SET NAMEINFULL=?,JOBTITLE=?,EMPLOYER=?,EMAIL=?,MOBILE=? "
                    + "WHERE CONTACTID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, contact.getNameInFull());
            statement.setString(2, contact.getJobtitle());
            statement.setString(3, contact.getEmployer());
            statement.setString(4, contact.getEmail());
            statement.setString(5, contact.getMobile());
            statement.setInt(6, contact.getContactid());
            statement.execute();
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
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

    public void updateLead(Lead lead) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE lead "
                    + "SET PRODUCTID = ?, "
                    + "LEADAMOUNT = ?, "
                    + "FORCASTUNTILDATE = ?, "
                    + "ACCOUNTCOUNT = ? "
                    + "WHERE LEADID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, lead.getProductid());
            if (lead.getLeadamount() == null || lead.getLeadamount().compareTo(BigDecimal.ZERO) == 0) {
                statement.setNull(2, Types.BIGINT);
            } else {
                statement.setBigDecimal(2, lead.getLeadamount());
            }
            statement.setTimestamp(3, new Timestamp(Common.getDateFromString("yyyy-MM-dd", lead.getForcastuntildate()).getTime()));
            statement.setInt(4, lead.getAccouncount());
            statement.setInt(5, lead.getLeadid());
            statement.execute();
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
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

    public void updateLeadSalesClosed(Lead lead) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE lead "
                    + "SET PRODUCTID=?,LEADAMOUNT=?,SALECLOSEDDATE=? ,CONFIRMEDAMOUNT=?  "
                    + "WHERE LEADID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, lead.getProductid());
            if (lead.getLeadamount() == null || lead.getLeadamount().compareTo(BigDecimal.ZERO) == 0) {
                statement.setNull(2, Types.BIGINT);
            } else {
                statement.setBigDecimal(2, lead.getLeadamount());
            }
            statement.setTimestamp(3, new Timestamp(Common.getDateFromString("yyyy-MM-dd", lead.getSalecloseddate()).getTime()));
            if (lead.getConfirmedamount() == null || lead.getConfirmedamount().compareTo(BigDecimal.ZERO) == 0) {
                statement.setNull(4, Types.BIGINT);
            } else {
                statement.setBigDecimal(4, lead.getConfirmedamount());
            }
            statement.setInt(5, lead.getLeadid());
            statement.execute();
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
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

    public void updateLeadLost(Lead lead) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE lead "
                    + "SET PRODUCTID=?,LEADAMOUNT=?,SALELOSTDATE=? , SALELOSTREASONCODE=? "
                    + "WHERE LEADID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, lead.getProductid());
            if (lead.getLeadamount() == null || lead.getLeadamount().compareTo(BigDecimal.ZERO) == 0) {
                statement.setNull(2, Types.BIGINT);
            } else {
                statement.setBigDecimal(2, lead.getLeadamount());
            }
            statement.setTimestamp(3, new Timestamp(Common.getDateFromString("yyyy-MM-dd", lead.getLostdate()).getTime()));
            statement.setInt(4, lead.getLostresoncode());
            statement.setInt(5, lead.getLeadid());
            statement.execute();
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
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

    public Map<String, String> getChanelDropDownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> channelList = null;
        try {

            String sql = "SELECT CHANNELID,DESCRIPTION FROM channel";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            channelList = new LinkedHashMap<>();
            while (resultSet.next()) {
                channelList.put(resultSet.getString("CHANNELID"), resultSet.getString("DESCRIPTION"));
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
        return channelList;
    }

    public Map<String, String> getChmpaignDropDownList() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, String> channelList = null;
        try {

            String sql = "SELECT CAMPAIGNID,DESCRIPTION FROM campaign WHERE CHANNELID=1";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            channelList = new LinkedHashMap<>();
            while (resultSet.next()) {
                channelList.put(resultSet.getString("CAMPAIGNID"), resultSet.getString("DESCRIPTION"));
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
        return channelList;
    }

    public boolean checkContactExistence(String mobile, String email) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean state = false;
        try {
            String sql = "SELECT contacts.MOBILE,contacts.EMAIL FROM contacts WHERE contacts.MOBILE=? AND contacts.EMAIL=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, mobile);
            statement.setString(2, email);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                state = true;
                break;
            }
            return state;
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
    }

    public List<Product> getProductDropdown(String createUser) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Product> productList = null;
        try {

            String sql = "SELECT product.PRODUCTID, product.DESCRIPTION FROM product "
                    + "INNER JOIN employeeproduct "
                    + "ON product.PRODUCTID=employeeproduct.PRODUCTID "
                    + "WHERE employeeproduct.EMPLOYEEID=(SELECT EMPLOYEEID FROM systemuser WHERE USERID=?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, createUser);
            resultSet = statement.executeQuery();
            productList = new ArrayList<>();
//            supervisorUsername.put("0", "ALL");
            Product product;
            while (resultSet.next()) {
                product = new Product();
                product.setProductID(0);
                product.setProductID(resultSet.getInt("PRODUCTID"));
                product.setProductDesc(resultSet.getString("DESCRIPTION"));
                productList.add(product);
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
        return productList;
    }

    public List<String> getSupervisorUsernameByEmployeeUseename(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> supervisorUsername = null;
        try {
            String sql = "SELECT SU.USERID AS USERNAME "
                    + "FROM systemuser SU "
                    + "INNER JOIN employee EMP "
                    + "ON SU.EMPLOYEEID      = EMP.EMPLOYEEID "
                    + "WHERE EMP.EMPLOYEEID IN "
                    + "  (SELECT ES.SUPERVISOR "
                    + "  FROM employeesupervisor ES "
                    + "  WHERE ES.EMPLOYEEID = "
                    + "    (SELECT SU.EMPLOYEEID FROM systemuser SU WHERE SU.USERID = ?) "
                    + "  )";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            supervisorUsername = new ArrayList<>();
            while (resultSet.next()) {
                supervisorUsername.add(resultSet.getString("USERNAME"));
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
        return supervisorUsername;
    }

    public boolean isSalesPipelineMaxAccessUser(long empid) throws SQLException {
        boolean isValidUser = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT COUNT(*) AS CNT FROM salespipelineaccess WHERE EMPLOYEEID = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, empid);
            resultSet = statement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt("CNT");
            }

            if (count > 0) {
                isValidUser = true;
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
        return isValidUser;
    }

}

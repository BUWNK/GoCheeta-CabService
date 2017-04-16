/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.notification;

import com.avn.ccl.dao.notification.NotificationDAO;
import com.avn.ccl.model.notification.NotificationMail;
import com.avn.ccl.model.notification.NotificationSMS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @Author : Roshen Dilshan
 * @Document : Notification
 * @Created on : Sep 21, 2015, 10:09:32 AM
 */
public class NotificationDAOImpl implements NotificationDAO {

    private DataSource dataSource;
    private DataSource mysqlDataSource;
//    private Connection oracleConnection;
//    private Connection mysqlConnection;
//    private PreparedStatement oracleStatement;
//    private PreparedStatement mysqlStatement;
//    private ResultSet resultSet;
    private JavaMailSender mailSender;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setMysqlDataSource(DataSource mysqlDataSource) {
        this.mysqlDataSource = mysqlDataSource;
    }

    @Override
    public void sendMail(NotificationMail notification) throws Exception {
        Connection oracleConnection = null;
        PreparedStatement oracleStatement = null;
        ResultSet resultSet = null;
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String sql = "INSERT "
                    + "INTO "
                    + "    AVN_NOTIFICATIONMAILLOG "
                    + "    ("
                    + "        RECIPIENTTO, "
                    + "        RECIPIENTCC, "
                    + "        RECIPIENTBCC, "
                    + "        RECIPIENT4, "
                    + "        RECIPIENT5, "
                    + "        SENTTIME, "
                    + "        CREATEDDATETIME, "
                    + "        LASTUPDATEDDATETIME, "
                    + "        CREATEDUSER, "
                    + "        NOTIFICATIONTEMPLATEID, "
                    + "        CASEID, "
                    + "        COMPONENT "
                    + "    )  VALUES  (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?,?,?)";
            oracleConnection = dataSource.getConnection();
            oracleConnection.setAutoCommit(false);
            oracleStatement = oracleConnection.prepareStatement(sql);

            String mail_to = "";
            if (notification.getSimpleMailMessage() != null && notification.getSimpleMailMessage().getTo() != null) {
                for (String mail_address : notification.getSimpleMailMessage().getTo()) {
                    mail_to += mail_address + ";";
                }
            }
            oracleStatement.setString(1, mail_to);

            String mail_cc = "";
            if (notification.getSimpleMailMessage() != null && notification.getSimpleMailMessage().getCc() != null) {
                for (String mail_address : notification.getSimpleMailMessage().getCc()) {
                    mail_cc += mail_address + ";";
                }
            }
            oracleStatement.setString(2, mail_cc);

            String mail_bcc = "";
            if (notification.getSimpleMailMessage() != null && notification.getSimpleMailMessage().getBcc() != null) {
                for (String mail_address : notification.getSimpleMailMessage().getBcc()) {
                    mail_bcc = mail_address + ";";
                }
            }
            oracleStatement.setString(3, mail_bcc);

            oracleStatement.setNull(4, Types.VARCHAR);
            oracleStatement.setNull(5, Types.VARCHAR);
            oracleStatement.setString(6, notification.getCreatedUser());
            oracleStatement.setInt(7, notification.getNotificationTemplateID());
            oracleStatement.setInt(8, Integer.valueOf(notification.getCaseid()));
            oracleStatement.setString(9, notification.getComponent());
            helper.setFrom(notification.getSimpleMailMessage().getFrom());
            if (notification.getSimpleMailMessage().getTo() != null && notification.getSimpleMailMessage().getTo().length > 0) {
                if (notification.getSimpleMailMessage().getTo()[0] != null) {
                    helper.setTo(notification.getSimpleMailMessage().getTo());
                }
            }
            if (notification.getSimpleMailMessage().getCc() != null && notification.getSimpleMailMessage().getCc().length > 0) {
                helper.setCc(notification.getSimpleMailMessage().getCc());
            }
            if (notification.getSimpleMailMessage().getBcc() != null && notification.getSimpleMailMessage().getBcc().length > 0) {
                helper.setBcc(notification.getSimpleMailMessage().getBcc());
            }
            helper.setSubject(notification.getSimpleMailMessage().getSubject());
            helper.setText(notification.getSimpleMailMessage().getText());

            if (notification.getResources() != null) {
                for (FileSystemResource resource : notification.getResources()) {
                    helper.addAttachment(resource.getFilename(), resource);
                }
            }
            mailSender.send(message);
            oracleStatement.execute();

            oracleConnection.commit();
        } catch (MessagingException | SQLException e) {
            if (oracleConnection != null) {
                oracleConnection.rollback();
            }
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception exception) {
                }
            }
            if (oracleStatement != null) {
                try {
                    oracleStatement.close();
                } catch (Exception exception) {
                }
            }
            if (oracleConnection != null) {
                try {
                    oracleConnection.close();
                } catch (Exception exception) {
                }
            }
        }
    }

    @Override
    public void sendSMS(NotificationSMS notificationSMS) throws Exception {
        Connection oracleConnection = null;
        Connection mysqlConnection = null;
        PreparedStatement oracleStatement = null;
        PreparedStatement mysqlStatement = null;
        ResultSet resultSet = null;
        try {
            String oracleSql = "INSERT "
                    + "INTO AVN_NOTIFICATIONSMSLOG "
                    + "  ( "
                    + "    RECIPIENT, "
                    + "    NOTIFICATIONTEMPLATEID, "
                    + "    COMPONENTID, "
                    + "    COMPONENT, "
                    + "    SENTTIME, "
                    + "    CREATEDDATETIME, "
                    + "    LASTUPDATEDDATETIME, "
                    + "    CREATEDUSER "
                    + "  ) "
                    + "  VALUES "
                    + "  (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
            oracleConnection = dataSource.getConnection();
            oracleConnection.setAutoCommit(false);
            oracleStatement = oracleConnection.prepareStatement(oracleSql);
            oracleStatement.setString(1, notificationSMS.getRecipient());
            if (notificationSMS.getTemplateid() > 0) {
                oracleStatement.setInt(2, notificationSMS.getTemplateid());
            } else {
                oracleStatement.setNull(2, Types.INTEGER);
            }
            if (notificationSMS.getComponentid() > 0) {
                oracleStatement.setLong(3, notificationSMS.getComponentid());
            } else {
                oracleStatement.setNull(3, Types.INTEGER);
            }
            if (notificationSMS.getComponent() != null && !notificationSMS.getComponent().isEmpty()) {
                oracleStatement.setString(4, notificationSMS.getComponent());
            } else {
                oracleStatement.setNull(4, Types.VARCHAR);
            }
            oracleStatement.setTimestamp(5, notificationSMS.getSendtime());
            oracleStatement.setString(6, notificationSMS.getCreatedUser());

            oracleStatement.execute();

            String mysqlSql = "INSERT INTO "
                    + "  trn_sms_outgoing( "
                    + "    Destination, "
                    + "    Route, "
                    + "    Message, "
                    + "    Priority, "
                    + "    DueDate, "
                    + "    RecordedDate, "
                    + "    GroupId, "
                    + "    OtherRef) "
                    + "VALUES "
                    + "  (?, ?, ?, ?, ?, NOW(), ?, ?)";
            mysqlConnection = mysqlDataSource.getConnection();
            mysqlConnection.setAutoCommit(false);
            mysqlStatement = mysqlConnection.prepareStatement(mysqlSql);
            mysqlStatement.setString(1, notificationSMS.getRecipient());
            mysqlStatement.setString(2, notificationSMS.getCclsmsrout());
            mysqlStatement.setString(3, notificationSMS.getMessagebody());
            mysqlStatement.setInt(4, notificationSMS.getCclsmspriority());
            mysqlStatement.setTimestamp(5, notificationSMS.getSendtime());
            mysqlStatement.setString(6, notificationSMS.getCclgroupid());
            mysqlStatement.setString(7, notificationSMS.getCreatedUser());

            mysqlStatement.execute();

            mysqlConnection.commit();
            oracleConnection.commit();
        } catch (SQLException e) {
            if (oracleConnection != null) {
                try {
                    oracleConnection.rollback();
                } catch (Exception exception) {
                }
            }
            if (mysqlConnection != null) {
                try {
                    mysqlConnection.rollback();
                } catch (Exception exception) {
                }
            }
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception exception) {
                }
            }
            if (oracleStatement != null) {
                try {
                    oracleStatement.close();
                } catch (Exception exception) {
                }
            }
            if (mysqlStatement != null) {
                try {
                    mysqlStatement.close();
                } catch (Exception exception) {
                }
            }
            if (oracleConnection != null) {
                try {
                    oracleConnection.close();
                } catch (Exception exception) {
                }
            }
            if (mysqlConnection != null) {
                try {
                    mysqlConnection.close();
                } catch (Exception exception) {
                }
            }
        }
    }

}

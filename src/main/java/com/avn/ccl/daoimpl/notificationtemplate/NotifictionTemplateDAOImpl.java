/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.daoimpl.notificationtemplate;

import com.avn.ccl.dao.notificationtemplate.NotificationTemplateDAO;
import com.avn.ccl.model.notificationtemplate.NotificationTemplate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Lahiru
 */
public class NotifictionTemplateDAOImpl implements NotificationTemplateDAO {

    private DataSource dataSource;
//    private Connection connection;
//    private PreparedStatement statement;
//    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public NotificationTemplate getNotificationTemplate(int Id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        NotificationTemplate notificationTemplate = null;
        try {
            String sql = "SELECT NOTIFICATIONTEMPLATEID,TEMPLATETYPE,SUBJECTS,RECIEPENT,MAILCONTENT,CONCLUSION,CONCLUDINGNAME FROM AVN_NOTIFICATIONTEMPLATES WHERE NOTIFICATIONTEMPLATEID=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                notificationTemplate = new NotificationTemplate();
                notificationTemplate.setNotificationTempId(resultSet.getString("NOTIFICATIONTEMPLATEID"));
                notificationTemplate.setTemplateType(resultSet.getString("TEMPLATETYPE"));
                notificationTemplate.setSubject(resultSet.getString("SUBJECTS"));
                notificationTemplate.setReciepent(resultSet.getString("RECIEPENT"));
                notificationTemplate.setMailContent(resultSet.getString("MAILCONTENT"));
                notificationTemplate.setConclution(resultSet.getString("CONCLUSION"));
                notificationTemplate.setConcludingName(resultSet.getString("CONCLUDINGNAME"));
            }
        } catch (SQLException e) {
            throw e;
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
        return notificationTemplate;
    }
}

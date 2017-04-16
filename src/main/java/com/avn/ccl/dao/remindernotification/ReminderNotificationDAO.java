/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.remindernotification;

import com.avn.ccl.model.remindernotification.ReminderNotification;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ISURU
 */
public interface ReminderNotificationDAO {

    public int getReminderCount(String name, Date d1, Date d2) throws Exception;

    public boolean setRemindStatus(String level, String id) throws Exception;

    public boolean setViewStatus(String level, String id) throws Exception;

    public boolean setDeleteStatus(String level, String id) throws Exception;

    public List<ReminderNotification> getTableData(int minCount, int start, String username, Date d1, Date d2) throws Exception;

    public int getAllCount(String name, Date d1, Date d2) throws Exception;

    String loadReminderNEW(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public void insertReminderNotification(ReminderNotification notification) throws SQLException;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.notification;

import com.avn.ccl.model.notification.NotificationMail;
import com.avn.ccl.model.notification.NotificationSMS;

/**
 * @Author : Roshen Dilshan
 * @Document : Notification
 * @Created on : Sep 21, 2015, 10:08:15 AM
 */
public interface NotificationDAO {

    public void sendMail(NotificationMail notification) throws Exception;
    
    public void sendSMS(NotificationSMS notificationSMS) throws Exception;

}

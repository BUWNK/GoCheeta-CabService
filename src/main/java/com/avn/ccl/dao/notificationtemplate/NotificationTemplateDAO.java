/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.notificationtemplate;

import com.avn.ccl.model.notificationtemplate.NotificationTemplate;

/**
 *
 * @author Lahiru
 */
public interface NotificationTemplateDAO {
    
    public NotificationTemplate getNotificationTemplate(int Id) throws Exception;
    
}

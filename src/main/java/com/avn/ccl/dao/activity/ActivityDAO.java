/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.activity;

import com.avn.ccl.model.activity.Activity;
import com.avn.ccl.model.contact.Contact;
import com.avn.ccl.model.lead.Lead;
import com.avn.ccl.model.target.Target;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SameeraPJ
 */
public interface ActivityDAO {

    public List<Activity> getActivity(Activity activity) throws SQLException;

    public List<Activity> getType() throws SQLException;

    public void insertActivity(Activity activity) throws SQLException;

    public long getActivityCountByActivityTypeForTarget(int activityTypeId, Target target) throws SQLException;

    public long getActivityCountByActivityTypeTargetDateRange(int activityTypeId, Target target, Date[] range) throws SQLException;

    public void setActivityUpdate(Activity activity) throws SQLException;

    public List<Contact> getContact(int leadid) throws SQLException;

    public List<Lead> getLeadDropdown(String createUser) throws SQLException;

    public List<Lead> getLeadDropdownAjent(String createUser) throws SQLException;

    public List<Lead> getContactDropdown(String createUser) throws SQLException;

    public List<Lead> getLead(int createUser) throws SQLException;

    public Activity getActivityById(long activityid) throws SQLException;
}

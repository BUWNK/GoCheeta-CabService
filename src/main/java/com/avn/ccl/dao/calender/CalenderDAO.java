/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.calender;

import com.avn.ccl.model.activity.Activity;
import com.avn.ccl.model.contact.Contact;
import com.avn.ccl.model.event.Event;
import com.avn.ccl.model.lead.Lead;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author SameeraPJ
 */
public interface CalenderDAO {
    public List<Activity> getActivity(int id) throws SQLException;
    public List<Event> getEvent(int id) throws SQLException;
    public List<Activity> getLeadid(String name) throws SQLException;
}

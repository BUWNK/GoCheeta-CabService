/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.calender;

import com.avn.ccl.daoimpl.activity.ActivityDAOImpl;
import com.avn.ccl.daoimpl.calender.CalenderDAOImpl;
import com.avn.ccl.daoimpl.salesPipline.SalesPiplineDAOImpl;
import com.avn.ccl.model.event.Event;
import com.avn.ccl.model.event.eventDetail;
import com.avn.ccl.model.lead.Lead;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author SameeraPJ
 */
@Controller
public class CalenderController {

    @Autowired
    CalenderDAOImpl calenderDAOimpl;
    @Autowired
    SalesPiplineDAOImpl salespiplinedaoimpl;
    @Autowired
    ActivityDAOImpl activityDAOImpl;

    @RequestMapping(value = "/calender/view/all", method = RequestMethod.GET)
    public String calendrviewall(ModelMap model, HttpSession session) throws SQLException, Exception {
        List<Event> eventinf = null;
        Map<String, String> userDetailList1 = null;
        Map<String, String> userDetailList2 = new LinkedHashMap<>();
        Map<String, String> userDetailList = new LinkedHashMap<>();
        List<Lead> leadDetailList;
        List<eventDetail> eventDetailList;
        List<Lead> contactList = null;
        try {
            String userName = (String) session.getAttribute("username");
            String isTrue = "";
            int userAccessId = 0;
            userDetailList1 = salespiplinedaoimpl.getSubbordinateUserList(userName);

            Iterator<String> coll = userDetailList1.keySet().iterator();
            while (coll.hasNext()) {
                String currentUserKey = coll.next();
                if (userName.equals(currentUserKey)) {
                    isTrue = "TRUE";
                } else {
                    isTrue = "FALSE";
                }
            }
            if (!"TRUE".equals(isTrue)) {
                userDetailList2.put(userName, salespiplinedaoimpl.getUserNameInFull(userName));
            }
            userDetailList.putAll(userDetailList2);
            userDetailList.putAll(userDetailList1);

            String myJson = "";
            JSONObject event = new JSONObject();
            contactList = activityDAOImpl.getContactDropdown(userName);
            eventinf = calenderDAOimpl.getEventcreateUser(userName);
            leadDetailList = activityDAOImpl.getLeadDropdown(userName);
            int activityId = 0;
            for (Event item : eventinf) {
                activityId = Integer.parseInt(item.getId());
                break;
            }
            eventDetailList = calenderDAOimpl.getEventDetail(activityId);

            model.put("userDetailList", salespiplinedaoimpl.getSubbordinateUserList(userName));
            model.put("typelist", activityDAOImpl.getType());
            model.put("eventDetailList", eventDetailList);
            event.put("events", eventinf);
            myJson = event.toString();
            model.put("leadDetailList", leadDetailList);
            model.put("events", myJson);
            model.put("userDetailList", userDetailList);
            model.put("username", isTrue);
            model.put("leadid", 0);
            model.put("createUser", userName);
            model.put("activityId", activityId);
            model.put("contactList", contactList);
        } catch (Exception exception) {
            Logger.getLogger(CalenderController.class.getName()).log(Level.SEVERE, null, exception);
        }
        return "calender/calenderview";
    }

    @RequestMapping(value = "/calender/view", method = RequestMethod.POST)
    public String calendrview(@ModelAttribute("calenderForm") Lead lead, ModelMap model, HttpSession session) throws SQLException, Exception {
        List<Event> eventinf = null;
        Map<String, String> userDetailList1 = null;
        Map<String, String> userDetailList2 = new LinkedHashMap<>();
        Map<String, String> userDetailList = new LinkedHashMap<>();
//        Map<String, String> leadDetailList;
        List<Lead> leadDetailList = null;
        List<eventDetail> eventDetailList;
        List<Event> fEventinf = null;
        List<Lead> contactList = null;
        List<Lead> leaddropList = null;
        try {
            String userName = (String) session.getAttribute("username");
            String isTrue = "";
            int userAccessId = 0;
//            userAccessId = salespiplinedaoimpl.getUserAccessValue(userName);
//            userDetailList1 = salespiplinedaoimpl.getUserDetailDropDownList(userAccessId, userName);
            userDetailList1 = salespiplinedaoimpl.getSubbordinateUserList(userName);

            //get contact dropdown
            contactList = activityDAOImpl.getContactDropdown(activityDAOImpl.getcreateUser(lead.getLeadid()));
            //get contact id for contact id selected value
            int contactid = activityDAOImpl.getContactid(lead.getLeadid());
            //get list for lead dropdown
            leaddropList = activityDAOImpl.getLead(contactid);

            Iterator<String> coll = userDetailList1.keySet().iterator();
            while (coll.hasNext()) {
                String currentUserKey = coll.next();
                if (userName.equals(currentUserKey)) {
                    isTrue = "TRUE";
                } else {
                    isTrue = "FALSE";
                }
            }
            if (!"TRUE".equals(isTrue)) {
                userDetailList2.put(userName, salespiplinedaoimpl.getUserNameInFull(userName));
            }
            userDetailList.putAll(userDetailList2);
            userDetailList.putAll(userDetailList1);
//            leadDetailList = activityDAOImpl.getLeadDropDownList(lead.getCreateUser());
            leadDetailList = activityDAOImpl.getLeadDropdown(lead.getCreateUser());

            String myJson = "";
            JSONObject event = new JSONObject();
            if (lead.getLeadid() == 0) {
                eventinf = calenderDAOimpl.getEventcreateUser(userName);
            } else {
                eventinf = calenderDAOimpl.getEvent(lead.getLeadid());
            }
            int activityId = 0;
            for (Event item : eventinf) {
                activityId = Integer.parseInt(item.getId());

            }
            eventDetailList = calenderDAOimpl.getEventDetail(activityId);

            //fEventinf = calenderDAOimpl.getFutureEventsByLeadId(lead.getLeadid());
//            eventinf.removeAll(fEventinf);
//            eventinf.addAll(fEventinf);
            model.put("typelist", activityDAOImpl.getType());
            event.put("events", eventinf);
            myJson = event.toString();
            model.put("eventDetailList", eventDetailList);
            model.put("leadDetailList", leadDetailList);
            model.put("events", myJson);
            model.put("userDetailList", userDetailList);
            model.put("username", isTrue);
            model.put("leadid", lead.getLeadid());
            model.put("createUser", lead.getCreateUser());
            model.put("activityId", activityId);
            model.put("contactList", contactList);
            model.put("contactid", contactid);
            model.put("leaddropList", leaddropList);
        } catch (Exception exception) {
            Logger.getLogger(CalenderController.class.getName()).log(Level.SEVERE, null, exception);
        }
        return "calender/calenderview";
    }

    @RequestMapping(value = "/calender/changecontact", method = RequestMethod.POST)
    public @ResponseBody
    String selectContact(@RequestParam String contactId, @RequestParam String username, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String myJson = "";
        JSONObject leadDropDown = new JSONObject();
        JSONObject events = new JSONObject();
        List<Lead> leadDetailList = null;
        List<Event> eventinf = null;
        List<eventDetail> eventDetailList;
        List<Lead> leadDetailListAjent;
        JSONObject eventsDetailList = new JSONObject();
        JSONObject eventsDetailListSize = new JSONObject();
        int contactid = Integer.parseInt(contactId);
        try {
            String userName = (String) session.getAttribute("username");
            if (userName.equalsIgnoreCase(username)) {
                if ("0".equals(contactId)) {
                    leadDetailList = activityDAOImpl.getLead(contactid);
                    eventinf = calenderDAOimpl.getEventcreateUser(userName);
                } else {
                    leadDetailList = activityDAOImpl.getLead(contactid);
                    eventinf = calenderDAOimpl.getEventContactid(contactid);
                }
            } else {
                leadDetailList = activityDAOImpl.getLead(contactid);
                eventinf = calenderDAOimpl.getEventcreateUser(username);
            }
            int activityId = 0;
            for (Event item : eventinf) {
                activityId = Integer.parseInt(item.getId());
                break;
            }
            eventDetailList = calenderDAOimpl.getEventDetail(activityId);
            eventsDetailList.put("eventDetailList", eventDetailList);
            leadDropDown.put("LeadDropDown", leadDetailList);
            events.put("eventsList", eventinf);
            eventsDetailListSize.put("eventsDetailListSize", eventDetailList.size());
            JSONArray listItems = new JSONArray();
            listItems.put(leadDropDown);
            listItems.put(events);
            listItems.put(eventsDetailList);
            listItems.put(eventsDetailListSize);
            myJson = listItems.toString();
        } catch (Exception exception) {
            Logger.getLogger(CalenderController.class.getName()).log(Level.SEVERE, null, exception);
        }
        return myJson;

    }

    //get calender when change lead dropdown
    @RequestMapping(value = "/calender/changeleaddrop", method = RequestMethod.POST)
    public @ResponseBody
    String selectLead(@RequestParam int leadId, @RequestParam int contactid, HttpServletRequest request, HttpServletResponse response) {
        String myJson = "";
        JSONObject events = new JSONObject();
        JSONObject eventsDetailList = new JSONObject();
        List<Event> eventinf;
        List<eventDetail> eventDetailList;
        JSONObject eventsDetailListSize = new JSONObject();
        List<Event> fEventinf = null;
        try {
            if (leadId == 0) {
                eventinf = calenderDAOimpl.getEventContactid(contactid);
            } else {
                eventinf = calenderDAOimpl.getEvent(leadId);
            }
            int activityId = 0;
            for (Event item : eventinf) {
                activityId = Integer.parseInt(item.getId());
                break;
            }
            eventDetailList = calenderDAOimpl.getEventDetail(activityId);

            //fEventinf = calenderDAOimpl.getFutureEventsByLeadId(leadId);
            //eventinf.removeAll(fEventinf);
            //eventinf.addAll(fEventinf);
            eventsDetailList.put("eventDetailList", eventDetailList);
            eventsDetailListSize.put("eventsDetailListSize", eventDetailList.size());
            events.put("eventsList", eventinf);
            JSONArray listItems = new JSONArray();
            listItems.put(events);
            listItems.put(eventsDetailList);
            listItems.put(eventsDetailListSize);
            myJson = listItems.toString();
        } catch (Exception exception) {
            Logger.getLogger(CalenderController.class.getName()).log(Level.SEVERE, null, exception);
        }
        return myJson;
    }

    @RequestMapping(value = "/calender/eventClick", method = RequestMethod.POST)
    public @ResponseBody
    String getEventDetail(@RequestParam int activityId, HttpServletRequest request, HttpServletResponse response) {
        String myJson = "";
        JSONObject events = new JSONObject();
        List<eventDetail> eventDetailList;
        try {
            eventDetailList = calenderDAOimpl.getEventDetail(activityId);
            events.put("eventsListDetail", eventDetailList);
            myJson = events.toString();
        } catch (Exception exception) {
            Logger.getLogger(CalenderController.class.getName()).log(Level.SEVERE, null, exception);
        }
        return myJson;
    }

}

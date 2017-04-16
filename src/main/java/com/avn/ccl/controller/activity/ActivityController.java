/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.activity;

import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.activity.ActivityDAOImpl;
import com.avn.ccl.daoimpl.calender.CalenderDAOImpl;
import com.avn.ccl.daoimpl.employee.EmployeeDAOImpl;
import com.avn.ccl.daoimpl.salesPipline.SalesPiplineDAOImpl;
import com.avn.ccl.model.activity.Activity;
import com.avn.ccl.model.contact.Contact;
import com.avn.ccl.model.event.Event;
import com.avn.ccl.model.event.eventDetail;
import com.avn.ccl.model.lead.Lead;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
public class ActivityController {

    @Autowired
    ActivityDAOImpl activityDAOImpl;
    @Autowired
    SalesPiplineDAOImpl salespiplinedaoimpl;
    @Autowired
    CalenderDAOImpl calenderDAOimpl;
    @Autowired
    EmployeeDAOImpl employeeDAOImpl;
    @Autowired
    CommonDAOImpl commonDAOImpl;

    @RequestMapping(value = "/lead", method = RequestMethod.POST)
    public String lead(@ModelAttribute("activityForm") Activity activity, @ModelAttribute("calenderForm") Lead lead, ModelMap model, HttpSession session) {

        List<Activity> activityList;
        List<Activity> typeList;
        List<Contact> LeadList;
        List<Lead> contactList;
        List<Lead> leadInfoList;
        List<Lead> leaddropList;
        int userAccessId = 0;
        Map<String, String> userDetailList1 = null;
        Map<String, String> userDetailList2 = new LinkedHashMap<>();
        Map<String, String> userDetailList = new LinkedHashMap<>();
        Map<String, String> leadDetailList;
        String isTrue = "";
        try {
            String userName = (String) session.getAttribute("username");
            activityList = activityDAOImpl.getActivity(activity);
            typeList = activityDAOImpl.getType();
//            userAccessId = salespiplinedaoimpl.getUserAccessValue(userName);
//            userDetailList1 = salespiplinedaoimpl.getUserDetailDropDownList(userAccessId, userName);
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

            int time1 = 0;
            int time2 = 0;
            int time3 = 0;
            String contactname = null;
            String jobtitle = null;
            String nametitle = null;
            for (Activity item : activityList) {
                if (activityList.size() != 0) {
                    String[] parts = item.getSpenttime().split("/");
                    String part1 = parts[0]; // 004
                    String part2 = parts[1];
                    int timehour = Integer.parseInt(part1);
                    int timeminite = Integer.parseInt(part2);
                    int time = timehour * 60 + timeminite;
                    if (item.getActivitytypeid().equals("1")) {
                        time1 = time1 + time;
                    }
                    if (item.getActivitytypeid().equals("2")) {
                        time2 = time2 + time;
                    }
                    if (item.getActivitytypeid().equals("3")) {
                        time3 = time3 + time;
                    }
                }
            }
            for (Activity item : activityList) {
                if (activityList.size() != 0) {
                    String[] parts = item.getSpenttime().split("/");
                    String part1 = parts[0]; // 004
                    String part2 = parts[1];
                    int timehour = Integer.parseInt(part1);
                    int timeminite = Integer.parseInt(part2);
                    int time = timehour * 60 + timeminite;
                    item.setSpenttime(spentTimeConvert(time));
                }
            }

            leadDetailList = activityDAOImpl.getLeadDropDownList(activityDAOImpl.getcreateUser(activity.getLeadid()));
            leadInfoList = activityDAOImpl.getLeadDropdown(activityDAOImpl.getcreateUser(activity.getLeadid()));
            //contact dropdown list
            contactList = activityDAOImpl.getContactDropdown(activityDAOImpl.getcreateUser(activity.getLeadid()));
            //for selected value for contact dropdown
            int contactid = activityDAOImpl.getContactid(activity.getLeadid());
            leaddropList = activityDAOImpl.getLead(contactid);
            LeadList = activityDAOImpl.getContact(activity.getLeadid());
            for (Contact item : LeadList) {
                contactname = item.getNameInFull();
                jobtitle = item.getJobtitle();
                nametitle = item.getTitle();
            }

            model.put("activitylist", activityList);
            model.put("typelist", typeList);
            model.put("time1", this.spentTimeConvert(time1));
            model.put("time2", this.spentTimeConvert(time2));
            model.put("time3", this.spentTimeConvert(time3));
            model.put("userDetailList", userDetailList);
            model.put("leadDetailList", leadDetailList);
            model.put("leadInfoList", leadInfoList);
            model.put("username", isTrue);
            model.put("leadid", activity.getLeadid());
            model.put("contactuser", contactname);
            model.put("jobtitle", jobtitle);
            model.put("nametitle", nametitle);
            model.put("contactDroplist", contactList);
            model.put("contactid", contactid);
            model.put("leaddropList", leaddropList);
            model.put("createUser", activityDAOImpl.getcreateUser(activity.getLeadid()));
        } catch (Exception exception) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, exception);
        }
        return "activity/activitytracker";
    }

    @RequestMapping(value = "/activity/addactivity", method = RequestMethod.POST)
    @ResponseBody
    String addActivity(@RequestParam String description, @RequestParam String atype, @RequestParam String leadid, @RequestParam String adate, HttpSession session) throws SQLException {
        String userName = (String) session.getAttribute("username");
        String myJson = "";
        JSONObject success = new JSONObject();
        JSONObject eventList = new JSONObject();
        JSONObject eventDetailListJson = new JSONObject();
        Activity activity = new Activity();
        List<Event> eventinf;
        List<eventDetail> eventDetailList;
        try {
            activity.setActivitytypedescription(description);
            activity.setLeadid(Integer.parseInt(leadid));
            activity.setActivitytype(atype);
            activity.setActivitytime(adate);
            activity.setCreateduser(userName);
            activityDAOImpl.insertActivity(activity);
            eventinf = calenderDAOimpl.getEvent(Integer.parseInt(leadid));
            int activityId = 0;
            for (Event item : eventinf) {
                activityId = Integer.parseInt(item.getId());
                break;
            }
            eventDetailList = calenderDAOimpl.getEventDetail(activityId);
            success.put("CODE", "SUCCESS");
            eventList.put("eventinf", eventinf);
            eventDetailListJson.put("eventDetailList", eventDetailList);
            JSONArray listItems = new JSONArray();
            listItems.put(success);
            listItems.put(eventList);
            listItems.put(eventDetailListJson);
            myJson = listItems.toString();
        } catch (Exception exception) {
            success.put("CODE", "ERROR");
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, exception);
        }
        return myJson;
    }

    @RequestMapping(value = "/activity/getLeadDropDown", method = RequestMethod.POST)
    public @ResponseBody
    String getcontactOne(@RequestParam String createUser, HttpServletRequest request, HttpServletResponse response) {
        String myJson = "";
        JSONObject leadDropDown = new JSONObject();
        JSONObject activityListjson = new JSONObject();
        JSONObject time01json = new JSONObject();
        JSONObject time02json = new JSONObject();
        JSONObject time03json = new JSONObject();
        Map<String, String> leadDetailList;
        try {
            leadDetailList = activityDAOImpl.getLeadDropDownList(createUser);
            Iterator<String> coll = leadDetailList.keySet().iterator();
            String leadId = "";
            while (coll.hasNext()) {
                leadId = coll.next();
                break;
            }

            List<Activity> activityList;
            if ("0".equals(leadId)) {
                activityList = activityDAOImpl.getActivityCreateUser(createUser);
            } else {
                Activity activity = new Activity();
                activity.setLeadid(Integer.parseInt(leadId));
                activityList = activityDAOImpl.getActivity(activity);
            }

            int time1 = 0;
            int time2 = 0;
            int time3 = 0;
            for (Activity item : activityList) {
                if (activityList.size() != 0) {
                    String[] parts = item.getSpenttime().split("/");
                    String part1 = parts[0]; // 004
                    String part2 = parts[1];
                    int timehour = Integer.parseInt(part1);
                    int timeminite = Integer.parseInt(part2);
                    int time = timehour * 60 + timeminite;
                    if (item.getActivitytypeid().equals("1")) {
                        time1 = time1 + time;
                    }
                    if (item.getActivitytypeid().equals("2")) {
                        time2 = time2 + time;
                    }
                    if (item.getActivitytypeid().equals("3")) {
                        time3 = time3 + time;
                    }
                }
            }
            for (Activity item : activityList) {
                if (activityList.size() != 0) {
                    String[] parts = item.getSpenttime().split("/");
                    String part1 = parts[0]; // 004
                    String part2 = parts[1];
                    int timehour = Integer.parseInt(part1);
                    int timeminite = Integer.parseInt(part2);
                    int time = timehour * 60 + timeminite;
                    item.setSpenttime(spentTimeConvert(time));
                }
            }
            leadDropDown.put("LeadDropDown", leadDetailList);
            activityListjson.put("activityList", activityList);
            time01json.put("time01", this.spentTimeConvert(time1));
            time02json.put("time02", this.spentTimeConvert(time2));
            time03json.put("time03", this.spentTimeConvert(time3));
            JSONArray listItems = new JSONArray();
            listItems.put(leadDropDown);
            listItems.put(activityListjson);
            listItems.put(time01json);
            listItems.put(time02json);
            listItems.put(time03json);
            myJson = listItems.toString();
        } catch (Exception exception) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/activity/getLeadIdDetail", method = RequestMethod.POST)
    public @ResponseBody
    String getActivityToLead(@RequestParam String leadId, @RequestParam int contactId, HttpServletRequest request, HttpServletResponse response) {
        String myJson = "";
        JSONObject activityListjson = new JSONObject();
        JSONObject time01json = new JSONObject();
        JSONObject time02json = new JSONObject();
        JSONObject time03json = new JSONObject();
        try {
            List<Activity> activityList = null;
            if ("0".equals(leadId)) {
                activityList = activityDAOImpl.getActivityContactid(contactId);
            } else {
                Activity activity = new Activity();
                activity.setLeadid(Integer.parseInt(leadId));
                activityList = activityDAOImpl.getActivity(activity);
            }

            int time1 = 0;
            int time2 = 0;
            int time3 = 0;
            for (Activity item : activityList) {
                if (activityList.size() != 0) {
                    String[] parts = item.getSpenttime().split("/");
                    String part1 = parts[0]; // 004
                    String part2 = parts[1];
                    int timehour = Integer.parseInt(part1);
                    int timeminite = Integer.parseInt(part2);
                    int time = timehour * 60 + timeminite;
                    if (item.getActivitytypeid().equals("1")) {
                        time1 = time1 + time;
                    }
                    if (item.getActivitytypeid().equals("2")) {
                        time2 = time2 + time;
                    }
                    if (item.getActivitytypeid().equals("3")) {
                        time3 = time3 + time;
                    }
                }
            }
            for (Activity item : activityList) {
                if (activityList.size() != 0) {
                    String[] parts = item.getSpenttime().split("/");
                    String part1 = parts[0]; // 004
                    String part2 = parts[1];
                    int timehour = Integer.parseInt(part1);
                    int timeminite = Integer.parseInt(part2);
                    int time = timehour * 60 + timeminite;
                    item.setSpenttime(spentTimeConvert(time));
                }
            }
            activityListjson.put("activityList", activityList);
            time01json.put("time01", this.spentTimeConvert(time1));
            time02json.put("time02", this.spentTimeConvert(time2));
            time03json.put("time03", this.spentTimeConvert(time3));
            JSONArray listItems = new JSONArray();
            listItems.put(activityListjson);
            listItems.put(time01json);
            listItems.put(time02json);
            listItems.put(time03json);
            myJson = listItems.toString();
        } catch (Exception exception) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/activity/getLeadDropdownList", method = RequestMethod.POST)
    public @ResponseBody
    String getLeadDropdown(@RequestParam String contactId, @RequestParam String ajent, HttpServletRequest request, HttpServletResponse response) {
        String myJson = "";
        JSONObject activityListjson = new JSONObject();
        JSONObject leadListjson = new JSONObject();
        JSONObject time01json = new JSONObject();
        JSONObject time02json = new JSONObject();
        JSONObject time03json = new JSONObject();
        int contactid = Integer.parseInt(contactId);
        try {
            List<Activity> activityList = null;
            List<Lead> leadList = null;
            //get activitylist using contactid because leadid=0
            activityList = activityDAOImpl.getActivityContactid(contactid);
            leadList = activityDAOImpl.getLead(contactid);

            int time1 = 0;
            int time2 = 0;
            int time3 = 0;
            for (Activity item : activityList) {
                if (activityList.size() != 0) {
                    String[] parts = item.getSpenttime().split("/");
                    String part1 = parts[0]; // 004
                    String part2 = parts[1];
                    int timehour = Integer.parseInt(part1);
                    int timeminite = Integer.parseInt(part2);
                    int time = timehour * 60 + timeminite;
                    if (item.getActivitytypeid().equals("1")) {
                        time1 = time1 + time;
                    }
                    if (item.getActivitytypeid().equals("2")) {
                        time2 = time2 + time;
                    }
                    if (item.getActivitytypeid().equals("3")) {
                        time3 = time3 + time;
                    }
                }
            }
            for (Activity item : activityList) {
                if (activityList.size() != 0) {
                    String[] parts = item.getSpenttime().split("/");
                    String part1 = parts[0]; // 004
                    String part2 = parts[1];
                    int timehour = Integer.parseInt(part1);
                    int timeminite = Integer.parseInt(part2);
                    int time = timehour * 60 + timeminite;
                    item.setSpenttime(spentTimeConvert(time));
                }
            }
            activityListjson.put("activityList", activityList);
            time01json.put("time01", this.spentTimeConvert(time1));
            time02json.put("time02", this.spentTimeConvert(time2));
            time03json.put("time03", this.spentTimeConvert(time3));
            leadListjson.put("leadList", leadList);
            JSONArray listItems = new JSONArray();
            listItems.put(activityListjson);
            listItems.put(time01json);
            listItems.put(time02json);
            listItems.put(time03json);
            listItems.put(leadListjson);

            myJson = listItems.toString();
        } catch (Exception exception) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/activity/getdataactivityupdate", method = RequestMethod.POST)
    public @ResponseBody
    String getupdateDataActivity(@RequestParam int activityId, HttpSession session) throws Exception {
        List<Activity> activityList = null;
        JSONObject activityListjson = new JSONObject();
        String myJson = "";
        try {
            Activity activity = activityDAOImpl.getActivityById(Long.valueOf(activityId));
            Calendar calendar = Calendar.getInstance();
            Date currentdatetime = commonDAOImpl.getCurentTimesStamp();
            calendar.setTime(currentdatetime);
            Calendar activityCalendar = Calendar.getInstance();
            activityCalendar.setTime(activity.getActivitydatetime());
            activityCalendar.add(Calendar.DATE, 1);
            boolean isCityOfficer = employeeDAOImpl.getEmployeeOldBranchCodeByUsername((String) session.getAttribute("username"))
                    .equalsIgnoreCase(MasterDataVarList.CCL_CODE_BRANCH_CITY_OFFIE);
            if (currentdatetime.getTime() > activity.getActivitydatetime().getTime()) {
                if (calendar.get(Calendar.WEEK_OF_YEAR) == activityCalendar.get(Calendar.WEEK_OF_YEAR)) {
                    if (activityCalendar.get(Calendar.DAY_OF_WEEK) < 7) {
                        if (TimeUnit.MINUTES.convert(
                                calendar.getTime().getTime() - activityCalendar.getTime().getTime(),
                                TimeUnit.MILLISECONDS) <= 1440) {
                            activityListjson.append("EXP", false);
                            activityList = activityDAOImpl.getActivityUpdate(activityId);
                        } else {
                            activityListjson.append("EXP", true);
                        }
                    } else if (isCityOfficer) {
                        activityListjson.append("EXP", false);
                        activityList = activityDAOImpl.getActivityUpdate(activityId);
                    } else if (TimeUnit.MINUTES.convert(
                            calendar.getTime().getTime() - activityCalendar.getTime().getTime(),
                            TimeUnit.MILLISECONDS) <= 1440) {
                        activityListjson.append("EXP", false);
                        activityList = activityDAOImpl.getActivityUpdate(activityId);
                    } else {
                        activityListjson.append("EXP", true);
                    }
                } else if ((calendar.get(Calendar.WEEK_OF_YEAR) == (activityCalendar.get(Calendar.WEEK_OF_YEAR) + 1))
                        && (activityCalendar.get(Calendar.DAY_OF_WEEK) == 7 || activityCalendar.get(Calendar.DAY_OF_WEEK) == 1)) {
                    if (isCityOfficer && activityCalendar.get(Calendar.DAY_OF_WEEK) == 7) {
                        activityCalendar.add(Calendar.DATE, 2);
                        if (TimeUnit.MINUTES.convert(
                                calendar.getTime().getTime() - activityCalendar.getTime().getTime(),
                                TimeUnit.MILLISECONDS) <= 1440) {
                            activityListjson.append("EXP", false);
                            activityList = activityDAOImpl.getActivityUpdate(activityId);
                        } else {
                            activityListjson.append("EXP", true);
                        }
                    } else {
                        activityCalendar.add(Calendar.DATE, 1);
                        if (TimeUnit.MINUTES.convert(
                                calendar.getTime().getTime() - activityCalendar.getTime().getTime(),
                                TimeUnit.MILLISECONDS) <= 1440) {
                            activityListjson.append("EXP", false);
                            activityList = activityDAOImpl.getActivityUpdate(activityId);
                        } else {
                            activityListjson.append("EXP", true);
                        }
                    }
                } else {
                    activityListjson.append("EXP", true);
                }
            } else {
                activityListjson.append("EXP", false);
                activityList = activityDAOImpl.getActivityUpdate(activityId);
            }
            activityListjson.put("activityList", activityList);
            myJson = activityListjson.toString();
        } catch (Exception exception) {
            activityListjson.put("CODE", "ERROR");
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/activity/updateactivity", method = RequestMethod.POST)
    @ResponseBody
    String updateactivity(@RequestParam String aid, @RequestParam String description, @RequestParam int leadId, @RequestParam int contactid, @RequestParam String atype, @RequestParam String spenthour, @RequestParam String spentminite, @RequestParam String adate, HttpSession session) throws SQLException {
        String userName = (String) session.getAttribute("username");
        String myJson = "";
        JSONObject success = new JSONObject();
        JSONObject eventList = new JSONObject();
        JSONObject eventDetailListJson = new JSONObject();
        Activity activity = new Activity();
        List<Event> eventinf = null;
        List<eventDetail> eventDetailList;
        try {
            String spenttime = spenthour + "/" + spentminite;
            activity.setActivityid(aid);
            activity.setActivitytypedescription(description);
            activity.setActivitytypeid(atype);
            activity.setSpenttime(spenttime);
            activity.setActivitytime(adate);
            activity.setCreateduser(userName);
            activityDAOImpl.setActivityUpdate(activity);
            if (leadId == 0 && contactid == 0) {
                eventinf = calenderDAOimpl.getEventcreateUser(userName);
            } else if (leadId == 0 && contactid != 0) {
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
            success.put("CODE", "SUCCESS");
            eventList.put("eventinf", eventinf);
            eventDetailListJson.put("eventDetailList", eventDetailList);
            JSONArray listItems = new JSONArray();
            listItems.put(success);
            listItems.put(eventList);
            listItems.put(eventDetailListJson);
            myJson = listItems.toString();
        } catch (Exception exception) {
            success.put("CODE", "ERROR");
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, exception);
        }
        return myJson;
    }

    public String spentTimeConvert(int time) {
        int timehour = time / 60;
        int timeminite = time % 60;
        String timestr = "";
        if (timehour != 0 && timeminite != 0) {
            timestr = Integer.toString(timehour) + "h " + Integer.toString(timeminite) + "min";
        } else if (timehour != 0 && timeminite == 0) {
            timestr = Integer.toString(timehour) + "h 00 min";
        } else if (timehour == 0 && timeminite != 0) {
            timestr = "00h " + Integer.toString(timeminite) + "min";
        } else {
            timestr = "00h 00min";
        }

        return timestr;
    }

}

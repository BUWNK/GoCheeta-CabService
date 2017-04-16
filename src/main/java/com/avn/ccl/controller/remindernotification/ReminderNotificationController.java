/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.remindernotification;

import com.avn.ccl.controller.login.LoginController;
import com.avn.ccl.controller.section.SectionController;
import com.avn.ccl.daoimpl.callcenter.CallCenterDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.remindernotification.ReminderNotificationDAOImpl;
import com.avn.ccl.model.callcenter.CallCenter;
import com.avn.ccl.model.remindernotification.ReminderNotification;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
 * @author ISURU
 */
@Controller
public class ReminderNotificationController {

    @Autowired
    ReminderNotificationDAOImpl remindernotificationdaoimpl;
    @Autowired
    CommonDAOImpl commonDAOImpl;
    @Autowired
    CallCenterDAOImpl callCenterDaoImpl;

    @RequestMapping(value = "Notification", method = RequestMethod.GET)
    public String caseView(@ModelAttribute("callview") CallCenter data,
            @RequestParam(value = "Id", required = false) String sourceid,
            @RequestParam(value = "section", required = false) String section,
            ModelMap model) {
        try {
            if (section.contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID))) {
                CallCenter objcallcenter = callCenterDaoImpl.getCallByCallLogId(Integer.valueOf(sourceid));
                model.addAttribute("objcallcenter", objcallcenter);
                remindernotificationdaoimpl.setViewStatus("35", sourceid);
            }

        } catch (Exception ex) {
            Logger.getLogger(ReminderNotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CCSP");
        return "callcenter/callview";
    }

    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    public @ResponseBody
    String getCount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONObject NotifiReminder = new JSONObject();
        String name = (String) session.getAttribute("username");
        try {
            Date d1 = commonDAOImpl.getCurentTimesStamp();
            Date d2 = commonDAOImpl.getCurentTimesStamp();
            d2.setTime(d2.getTime() + 10 * 60000);
//            System.out.println("Date" + d2);
//            System.out.println(getStringFormatDate(CommonVarList.DATE_FORMAT_dd_MMM_yy_hhmmssSSa, d1));
//            System.out.println(getStringFormatDate(CommonVarList.DATE_FORMAT_dd_MMM_yy_hhmmssSSa, d2));

            int count = remindernotificationdaoimpl.getReminderCount(name, d1, d2);

            NotifiReminder.put("count", count);
//            System.out.println("COUNT " + count);
        } catch (Exception ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NotifiReminder.toString();
    }

    @RequestMapping(value = "/updateNotifiState", method = RequestMethod.POST)
    public @ResponseBody
    String getupdateNotifiState(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String content = request.getParameter("content");
//        System.out.println("content " + content);
        JSONObject NotifiReminder = new JSONObject(content);
//        System.out.println(NotifiReminder.getJSONArray("array"));
        for (int i = 0; i < NotifiReminder.getJSONArray("array").length(); i++) {
            remindernotificationdaoimpl.setRemindStatus(String.valueOf(MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_REMIND), String.valueOf(NotifiReminder.getJSONArray("array").get(i)));
        }
        try {

        } catch (Exception ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucess";
    }

    @RequestMapping(value = "/Deletenotification", method = RequestMethod.GET)
    public @ResponseBody
    String deletenotification(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String content = request.getParameter("content");
        JSONObject data = new JSONObject(content);
//        System.out.println("content " + content);
        List<JSONObject> jSONArrayList = new ArrayList<>();
        try {
            remindernotificationdaoimpl.setDeleteStatus(String.valueOf(MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_DELETE), data.getString("id"));

            Date d1 = commonDAOImpl.getCurentTimesStamp();
            Date d2 = commonDAOImpl.getCurentTimesStamp();
            d2.setTime(d2.getTime() + 10 * 60000);
            int iDisplayStart;
            int iTotalRecords = 0;
            int iDisplayLength = 10;
            int minCount = 0;
            int maxPages = 0;

            String name = (String) session.getAttribute("username");
            iTotalRecords = remindernotificationdaoimpl.getAllCount(name, d1, d2);
            maxPages = (iTotalRecords / iDisplayLength);
            if (iTotalRecords % iDisplayLength > 0) {
                maxPages++;
            }
            if ((data.getInt("iDisplayStart") == 0)) {
                iDisplayStart = 0;
            } else {
                iDisplayStart = (10 * data.getInt("iDisplayStart"));
            }

            if (data.getInt("iDisplayLength") == 0) {
                iDisplayLength = 10;
            } else {
                iDisplayLength += (iDisplayLength * data.getInt("iDisplayLength"));
            }

            List< ReminderNotification> reminderList = remindernotificationdaoimpl.getTableData(iDisplayLength, iDisplayStart, name, d1, d2);

            JSONObject NotifiReminder = new JSONObject();

            if (reminderList.size() > 0) {
                for (ReminderNotification oneitemreminder : reminderList) {
                    JSONObject newNotifiReminder = new JSONObject();
                    newNotifiReminder.put("id", oneitemreminder.getId());
                    newNotifiReminder.put("description", oneitemreminder.getDescription());
                    newNotifiReminder.put("remindTime", oneitemreminder.getRemindTime());
                    newNotifiReminder.put("sourcetabelId", oneitemreminder.getSourcetabelId());
                    newNotifiReminder.put("section", oneitemreminder.getSection());
                    newNotifiReminder.put("contextpath", request.getContextPath());
                    newNotifiReminder.put("maxPages", maxPages);
                    newNotifiReminder.put("viewState", oneitemreminder.getViewStatus());
                    NotifiReminder.put("NewNotifiList", newNotifiReminder);

                    jSONArrayList.add(newNotifiReminder);

                }
            } else {

            }

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jSONArrayList.toString();
    }

    @RequestMapping(value = "/loadReminder2", method = RequestMethod.POST)
    public @ResponseBody
    String loadReminderNEW(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        Date d1 = commonDAOImpl.getCurentTimesStamp();
        Date d2 = commonDAOImpl.getCurentTimesStamp();
        d2.setTime(d2.getTime() + 10 * 60000);
        int iDisplayStart;
        int iTotalRecords = 0;
        int iDisplayLength = 10;
        int minCount = 0;
        int maxPages = 0;
        String content = request.getParameter("content");
        JSONObject data = new JSONObject(content);
        String name = (String) session.getAttribute("username");
        iTotalRecords = remindernotificationdaoimpl.getAllCount(name, d1, d2);
        maxPages = (iTotalRecords / iDisplayLength);
        if (iTotalRecords % iDisplayLength > 0) {
            maxPages++;
        }
        if ((data.getInt("iDisplayStart") == 0)) {
            iDisplayStart = 0;
        } else {
            iDisplayStart = (10 * data.getInt("iDisplayStart"));
        }

        if (data.getInt("iDisplayLength") == 0) {
            iDisplayLength = 10;
        } else {
            iDisplayLength += (iDisplayLength * data.getInt("iDisplayLength"));
        }

        List< ReminderNotification> reminderList = remindernotificationdaoimpl.getTableData(iDisplayLength, iDisplayStart, name, d1, d2);
        List<JSONObject> jSONArrayList = new ArrayList<>();
        JSONObject NotifiReminder = new JSONObject();
        try {
            if (reminderList.size() > 0) {
                for (ReminderNotification oneitemreminder : reminderList) {
                    JSONObject newNotifiReminder = new JSONObject();
                    newNotifiReminder.put("id", oneitemreminder.getId());
                    newNotifiReminder.put("description", oneitemreminder.getDescription());
                    newNotifiReminder.put("remindTime", oneitemreminder.getRemindTime());
                    newNotifiReminder.put("sourcetabelId", oneitemreminder.getSourcetabelId());
                    newNotifiReminder.put("section", oneitemreminder.getSection());
                    newNotifiReminder.put("contextpath", request.getContextPath());
                    newNotifiReminder.put("maxPages", maxPages);
                    newNotifiReminder.put("viewState", oneitemreminder.getViewStatus());
                    NotifiReminder.put("NewNotifiList", newNotifiReminder);

                    jSONArrayList.add(newNotifiReminder);

                }
            } else {

            }

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jSONArrayList.toString();
    }

    public void xmlCronTask2() {

//        System.out.println("this is test shedule");
    }
}

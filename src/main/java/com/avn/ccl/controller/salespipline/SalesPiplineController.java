/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.salespipline;

import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.dao.contact.ContactDAOImpl;
import com.avn.ccl.dao.lead.LeadDAOImpl;
import com.avn.ccl.daoimpl.accountcategory.AccountCategoryDAOImpl;
import com.avn.ccl.daoimpl.activity.ActivityDAOImpl;
import com.avn.ccl.daoimpl.employee.EmployeeDAOImpl;
import com.avn.ccl.daoimpl.gender.GenderDAOImpl;
import com.avn.ccl.daoimpl.notification.NotificationDAOImpl;
import com.avn.ccl.daoimpl.notificationtemplate.NotifictionTemplateDAOImpl;
import com.avn.ccl.daoimpl.opsector.OPSectorDAOImpl;
import com.avn.ccl.daoimpl.product.ProductDAOImpl;
import com.avn.ccl.daoimpl.remindernotification.ReminderNotificationDAOImpl;
import com.avn.ccl.daoimpl.salesPipline.SalesPiplineDAOImpl;
import com.avn.ccl.daoimpl.subsector.SubSectorDAOImpl;
import com.avn.ccl.daoimpl.title.TitleDAOImpl;
import com.avn.ccl.model.account.Account;
import com.avn.ccl.model.activity.Activity;
import com.avn.ccl.model.contact.Contact;
import com.avn.ccl.model.lead.Lead;
import com.avn.ccl.model.notification.NotificationMail;
import com.avn.ccl.model.notificationtemplate.NotificationTemplate;
import com.avn.ccl.model.occupation.Occupation;
import com.avn.ccl.model.remindernotification.ReminderNotification;
import com.avn.ccl.model.ticketmgt.Ticket;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Rasika Madushanka
 */
@Controller
public class SalesPiplineController {

    @Autowired
    SalesPiplineDAOImpl salespiplinedaoimpl;
    @Autowired
    ProductDAOImpl productdaoimpl;
    @Autowired
    AccountCategoryDAOImpl accountCategoryDAOImpl;
    @Autowired
    TitleDAOImpl titleDAOImpl;
    @Autowired
    GenderDAOImpl genderDAOImpl;
    @Autowired
    OPSectorDAOImpl oPSectorDAOImpl;
    @Autowired
    SubSectorDAOImpl subSectorDAOImpl;
    @Autowired
    ActivityDAOImpl activityDAOImpl;
    @Autowired
    LeadDAOImpl leadDAOImpl;
    @Autowired
    CommonDAOImpl commonDAOImpl;
    @Autowired
    ReminderNotificationDAOImpl reminderNotificationDAOImpl;
    @Autowired
    NotificationDAOImpl notificationDAOImpl;
    @Autowired
    NotifictionTemplateDAOImpl notifictionTemplateDAOImpl;
    @Autowired
    EmployeeDAOImpl employeeDAOImpl;
    @Autowired
    ContactDAOImpl contactDAOImpl;
    @Autowired
    ProductDAOImpl productDAOImpl;

    @RequestMapping(value = "/salespipline", method = RequestMethod.GET)
    public String viewSecctionData(ModelMap model, @ModelAttribute("accountForm") Account account, @ModelAttribute("activityForm") Activity activity, HttpSession session) {
        int userAccessId;
        Map<String, String> userDetailList1;
        Map<String, String> userDetailList2 = new LinkedHashMap<>();
        Map<String, String> userDetailList = new LinkedHashMap<>();
        Map<String, String> contactListDropDown;
        String isTrue = "";
        try {
            String userName = (String) session.getAttribute("username");
//            userAccessId = salespiplinedaoimpl.getUserAccessValue(userName);
//            userDetailList1 = salespiplinedaoimpl.getUserDetailDropDownList(userAccessId, userName);
            long empid = employeeDAOImpl.getEmployeeIdByUsername(userName);
            userDetailList1 = salespiplinedaoimpl.getSubbordinateUserList(userName);

            Iterator<String> coll = userDetailList1.keySet().iterator();
            String createUser = "";
            while (coll.hasNext()) {
                String currentUserKey = coll.next();
                if (userName.equals(currentUserKey)) {
                    isTrue = "TRUE";
                } else {
                    isTrue = "FALSE";
                }
            }
            if ("TRUE".equals(isTrue)) {
                contactListDropDown = salespiplinedaoimpl.getContactDropDownList(userName);
            } else {
                contactListDropDown = salespiplinedaoimpl.getContactDropDownList(createUser);
                userDetailList2.put(userName, salespiplinedaoimpl.getUserNameInFull(userName));
            }

            if (!salespiplinedaoimpl.isSalesPipelineMaxAccessUser(empid)) {
                userDetailList.putAll(userDetailList2);
                userDetailList.putAll(userDetailList1);
            } else {
                userDetailList.putAll(userDetailList2);
                userDetailList.putAll(salespiplinedaoimpl.getSubbordinateMaxAccessUserList(empid));
            }

            Iterator<String> colls = userDetailList.keySet().iterator();
            while (colls.hasNext()) {
                createUser = colls.next();
                break;
            }
            String whereContact = "WHERE contacts.CREATEDUSER= '" + createUser + "'";
            String whereLead = "WHERE lead.STATUS = '37' AND lead.CREATEDUSER= '" + createUser + "'";
            String whereClosed = "WHERE lead.STATUS = '38' AND lead.CREATEDUSER= '" + createUser + "'";
            String whereLost = "WHERE lead.STATUS = '39' AND lead.CREATEDUSER= '" + createUser + "'";
            model.put("contactlist", salespiplinedaoimpl.getContact(whereContact));
            model.put("Opportunitylist", salespiplinedaoimpl.getOpportunityLostReasonsDropdownList());
            model.put("productList", productdaoimpl.getProductDropdownListPipeline(createUser));

            model.put("leadList", salespiplinedaoimpl.getLead(whereLead, "LEADS"));
            model.put("leadClosedList", salespiplinedaoimpl.getLead(whereClosed, "CLOSED")); //sales closed
            model.put("leadLost", salespiplinedaoimpl.getLead(whereLost, "LOST")); // lead lost

            model.put("CONTACTCOUNT", salespiplinedaoimpl.getCountContactTable(whereContact));
            model.put("LEADCOUNT", salespiplinedaoimpl.getCountLeadTable(whereLead));
            model.put("CLOSEDCOUNT", salespiplinedaoimpl.getCountLeadTable(whereClosed));
            model.put("LOSTCOUNT", salespiplinedaoimpl.getCountLeadTable(whereLost));

            model.put("SUM", salespiplinedaoimpl.getSumLeadTable(whereLead));
            model.put("SUMCLOSED", salespiplinedaoimpl.getSumLeadTable(whereClosed));
            model.put("SUMLOST", salespiplinedaoimpl.getSumLeadTable(whereLost));

            model.put("customerCategoryList", accountCategoryDAOImpl.getAccountCategoryDropdownList());
            model.put("titleList", titleDAOImpl.getTitleDropdownList());
            model.put("genderList", genderDAOImpl.getGenderDropdownList());
            model.put("opsectorList", oPSectorDAOImpl.getSectorDropdownList());
            model.put("userDetailList", userDetailList);
//            model.put("CONTACTDRODOWNLIST", contactListDropDown);
            // this is added temporary. Need to clarify with Rasika to change the code. This is not calling a database call again. But have to discuss this
            //Added this as an immediate fix
            model.put("CONTACTDRODOWNLIST", salespiplinedaoimpl.getContactDropDownList(userName));
            model.put("username", isTrue);
            model.put("typelist", activityDAOImpl.getType());
            model.put("CHANELDRODOWNLIST", salespiplinedaoimpl.getChanelDropDownList());
            model.put("CAMPIGNDRODOWNLIST", salespiplinedaoimpl.getChmpaignDropDownList());
        } catch (Exception exception) {
            Logger.getLogger(SalesPiplineController.class.getName()).log(Level.SEVERE, null, exception);
        }

        return ("salespipline/salespiplinedashbord");

    }

    @RequestMapping(value = "/salespipline/getContactOne", method = RequestMethod.POST)
    public @ResponseBody
    String getcontactOne(@RequestParam int contactId, HttpServletRequest request, HttpServletResponse response) {
        List<Contact> contact;
        String myJson = "";
        JSONObject jSONObject = new JSONObject();
        try {
            contact = salespiplinedaoimpl.getContactDetail(contactId);
            jSONObject.put("CONTACT", contact);
            myJson = jSONObject.toString();
        } catch (Exception exception) {
            Logger.getLogger(SalesPiplineController.class.getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/getleadOne", method = RequestMethod.POST)
    public @ResponseBody
    String getleadOne(@RequestParam int leadId, HttpServletRequest request, HttpServletResponse response) {
        List<Lead> contact;
        String myJson = "";
        JSONObject jSONObject = new JSONObject();
        try {
            contact = salespiplinedaoimpl.getLeadOne(leadId);
            jSONObject.put("CONTACT", contact);
            myJson = jSONObject.toString();
        } catch (Exception exception) {
            Logger.getLogger(SalesPiplineController.class.getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/create/account", method = RequestMethod.POST)
    public @ResponseBody
    String createAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jsoncontent = request.getParameter("corporate");
        Account account = new Account();
        JSONObject rsp = new JSONObject(jsoncontent);
        String account_category = rsp.getString("account_category");
        int lead_id = rsp.getInt("lead_id");
        int actual_account_count = rsp.getInt("actualaccountcount");
        long account_id = 0;
        JSONObject success = new JSONObject();
        String myJson = "";
        try {
            if ("0".equals(account_category)) {
                account.setAccount_type(rsp.getString("account_category"));
                account.setCustomer_category_type(rsp.getString("customer_category_type"));
                account.setTitle(rsp.getString("title"));
                account.setInitials(rsp.getString("initials"));
                account.setPreferred_name(rsp.getString("preferred_name"));
                account.setLast_name(rsp.getString("last_name"));
                account.setName_in_full(rsp.getString("name_in_full"));
                account.setGender(rsp.getString("gender"));
                account.setMothers_maiden_name(rsp.getString("mothers_maiden_name"));
                account.setDate_of_birth(rsp.getString("date_of_birth"));
                account_id = salespiplinedaoimpl.createAccountIndividual(account);
            } else if ("1".equals(account_category) || account_category.equals("2")) {
                account.setAccount_type(rsp.getString("account_category"));
                account.setCustomer_category_type(rsp.getString("customer_category_type"));
                account.setCopemployer(rsp.getString("copemployer"));
                account.setCopsector(rsp.getString("copsector"));
                account_id = salespiplinedaoimpl.createAccountCorparate(account);
                account.setCopsub_sector_list(rsp.getJSONArray("copsub_sector_assign").toString());
                Occupation occupation = new Occupation();
                occupation.setAccount_id(String.valueOf(account_id));
                occupation.setSubsectors(account.getCopsub_sector());
                subSectorDAOImpl.createSubSectors(occupation);
            }

//            int actual_amount = rsp.getInt("actual_lead_amount");
            BigDecimal actual_amount = new BigDecimal(rsp.getString("actual_lead_amount"));
            salespiplinedaoimpl.actualLeadAmount(lead_id, actual_amount, actual_account_count);

            String remark = rsp.getString("lead_remark");
            if (remark == null) {
                remark = "";
            }
            salespiplinedaoimpl.updateLeadClosed("38", lead_id, remark);
            int contact_id = salespiplinedaoimpl.getcontactId(lead_id);
            salespiplinedaoimpl.updateContactClosed(account_id, contact_id);
            success.put("CODE", "SUCCESS");
            myJson = success.toString();
        } catch (Exception exception) {
            success.put("CODE", "ERROR");
            Logger.getLogger(SalesPiplineController.class.getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/lead/create", method = RequestMethod.POST)
    public @ResponseBody
    String insertLead(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        Lead lead = new Lead();
        Ticket ticket = new Ticket();
        JSONObject success = new JSONObject();
        String myJson = "";
        String userName = (String) session.getAttribute("username");
        String jsoncontent = request.getParameter("lead");
        JSONObject rsp = new JSONObject(jsoncontent);
        try {
            BigDecimal bigDecimal = BigDecimal.ZERO;
            String amnt = rsp.getString("amount");
            if (amnt != null) {
//                amnt = amnt.replace(",", "");
                bigDecimal = new BigDecimal(amnt);
            }
            lead.setProductid(rsp.getInt("productid"));
            lead.setLeadamount(bigDecimal);
            lead.setForcastuntildate(rsp.getString("date"));
            lead.setContactid(rsp.getInt("contact_id"));
            lead.setChannelId(rsp.getInt("channelId"));
            if (rsp.getInt("channelId") == 1) {
                lead.setCampignId(rsp.getInt("campignId"));
            } else {
                lead.setCampignId(0);
            }
            lead.setPromationCode(rsp.getString("promationCode"));

            ticket.setTicketTypeId(3);
            ticket.setPriorityId(2);
            ticket.setProductId(rsp.getInt("productid"));
            ticket.setAssigneeId(salespiplinedaoimpl.getAssigneeId(userName));
            ticket.setContactId(rsp.getInt("contact_id"));
            ticket.setStatus(1);
            ticket.setSubject("Lead Ticket");
            ticket.setDescription(rsp.getString("amount"));
            long leadid = salespiplinedaoimpl.insertLeadAndTicket(lead, userName, ticket);
            if (leadDAOImpl.getContactLeadCount(rsp.getInt("contact_id"), rsp.getInt("productid")) > 0) {
                List<String> supervisors = salespiplinedaoimpl.getSupervisorUsernameByEmployeeUseename(userName);
                ReminderNotification notification = new ReminderNotification();
                notification.setDescription("Same name card has been dragged by 2 sales executives to their Lead columns. Please check your mails for further details of the contact.");
                notification.setReminderDateTime(commonDAOImpl.getCurentTimesStamp());
                notification.setSourcetabelId(String.valueOf(leadid));
                notification.setSection(String.valueOf(MasterDataVarList.CCL_CODE_SALESPIPELINE));
                notification.setCreateuser(userName);
                notification.setRemindStatus(String.valueOf(MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_REMIND));
                notification.setViewStatus(String.valueOf(MasterDataVarList.CCL_CODE_REMINDER_NOTIFICATION_NOT_VIEWED));
                for (String supervisor : supervisors) {
                    notification.setReminderUser(supervisor);
                    reminderNotificationDAOImpl.insertReminderNotification(notification);
                    try {
                        NotificationTemplate notificationTemplate = notifictionTemplateDAOImpl.getNotificationTemplate(MasterDataVarList.CCL_CODE_NOTIFICATION_SP_1);
//                        String[] ccemaillist = {};
                        SimpleMailMessage sm = new SimpleMailMessage();
                        sm.setFrom(CommonVarList.NOTIFICATION_MAIL_FROM);
                        sm.setTo(employeeDAOImpl.getEmployeeEmailByUsername(supervisor));

                        String leadFirstCreatedUser = employeeDAOImpl.getEmployeeNameInFullByEmployeeId(leadDAOImpl.getLeadCreatedEmployeeId(leadDAOImpl.getFirstLeadIdForProductAndContact(rsp.getInt("contact_id"), rsp.getInt("productid"))));
                        String leadSecondCreatedUser = employeeDAOImpl.getEmployeeNameInFullByEmployeeId(employeeDAOImpl.getEmployeeIdByUsername(userName));

                        String subject = String.format(notificationTemplate.getSubject());
                        sm.setSubject(subject);

                        String mailContent = String.format(
                                notificationTemplate.getReciepent(),
                                employeeDAOImpl.getEmployeeNameInFullByEmployeeId(employeeDAOImpl.getEmployeeIdByUsername(supervisor))) + "\n\n\n"
                                + String.format(
                                        notificationTemplate.getMailContent(),
                                        leadSecondCreatedUser,
                                        leadFirstCreatedUser,
                                        contactDAOImpl.getContactNameInFullById(rsp.getLong("contact_id")),
                                        contactDAOImpl.getContactMobileById(rsp.getLong("contact_id")),
                                        productDAOImpl.getProductById(rsp.getString("productid")).getProductDesc())
                                + "\n\n\n"
                                + notificationTemplate.getConclution() + "\n\n"
                                + notificationTemplate.getConcludingName();
                        sm.setText(mailContent);
//                        sm.setCc(ccemaillist);
                        NotificationMail notificationMail = new NotificationMail();
                        notificationMail.setSimpleMailMessage(sm);
                        notificationMail.setResources(null);
                        notificationMail.setCaseid(String.valueOf(leadid));
                        notificationMail.setComponent("Sales Pipeline");
                        notificationMail.setCreatedUser(userName);
                        notificationMail.setNotificationTemplateID(MasterDataVarList.CCL_CODE_NOTIFICATION_SP_1);
                        notificationDAOImpl.sendMail(notificationMail);
                    } catch (Exception e) {
                        Logger.getLogger(SalesPiplineController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
            JSONArray listItems = new JSONArray();
            success.put("CODE", "SUCCESS");
            listItems.put(success);
            myJson = success.toString();

        } catch (Exception exception) {
            success.put("CODE", "ERROR");
            Logger.getLogger(SalesPiplineController.class.getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/lead/update/opportunitylost", method = RequestMethod.POST)
    public @ResponseBody
    String updateOpportunityLostToLead(@RequestParam int leadid, @RequestParam int opportunityid, @RequestParam String remark) throws Exception {
        String myJson = "";
        JSONObject success = new JSONObject();
        try {
            salespiplinedaoimpl.updateLeadOpportunityLost(leadid, opportunityid, remark);
            success.put("CODE", "SUCCESS");
            myJson = success.toString();
        } catch (Exception exception) {
            success.put("CODE", "ERROR");
            Logger
                    .getLogger(SalesPiplineController.class
                            .getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/addcontact", method = RequestMethod.POST)
    public @ResponseBody
    String AddContact(@RequestParam String title, @RequestParam String nameInFull, @RequestParam String jobtitle, @RequestParam String employer, @RequestParam String email, @RequestParam String mobile, @RequestParam String isDealer, HttpSession session) throws Exception {
        String userName = (String) session.getAttribute("username");
        Contact contact = new Contact();
        String myJson = "";
        JSONObject success = new JSONObject();
        try {
//            if (salespiplinedaoimpl.checkContactExistence(mobile, email)) {
//                success.put("CODE", "FAIL");
//                myJson = success.toString();
//            } else {
            contact.setTitle(title);
            contact.setNameInFull(nameInFull);
            contact.setJobtitle(jobtitle);
            contact.setEmployer(employer);
            contact.setEmail(email);
            contact.setMobile(mobile);
            contact.setIsDealer(isDealer);
            salespiplinedaoimpl.insertcontact(contact, userName);
            success.put("CODE", "SUCCESS");
            myJson = success.toString();
//            }
        } catch (Exception exception) {
            success.put("CODE", "ERROR");
            Logger
                    .getLogger(SalesPiplineController.class
                            .getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    private String setContactListWhere(JSONObject rsp) throws Exception {
        String createUser = rsp.getString("selectAgent");
//        String customerType = rsp.getString("customerType");
        String serachContact = rsp.getString("serachContact");
        String value = "";
        value = this.frountDeleteSpace(serachContact);
        String where = "WHERE ";
        if (!createUser.isEmpty()) {
            where = where + "  contacts.CREATEDUSER= '" + createUser + "'";
        }
//        if (!customerType.isEmpty()) {
//            if (customerType.equals("NEW")) {
//                where = where + " AND CUSTOMERACCOUNTID IS NULL";
//            } else {
//                where = where + " AND CUSTOMERACCOUNTID IS NOT NULL";
//            }
//        }
        if (!value.isEmpty()) {
            where = where + " AND (UPPER(JOBTITLE) LIKE UPPER('%" + value + "%') OR UPPER(NAMEINFULL) LIKE UPPER('%" + value + "%') OR UPPER(MOBILE) LIKE UPPER('%" + value + "%'))";
        }

        return where;
    }

    private String setLeadListWhere(JSONObject rsp) throws Exception {
        String createUser = rsp.getString("selectAgent");
//        String productId = rsp.getString("productidAll");
        String serachLead = rsp.getString("serachLead");
//        String forCastUntilDate = rsp.getString("forcastUntilSerach");

        String value = "";
        value = this.frountDeleteSpace(serachLead);
        String where = "WHERE lead.STATUS = '37'";
        if (!createUser.isEmpty()) {
            where = where + " AND lead.CREATEDUSER= '" + createUser + "'";
        }
//        if (!productId.isEmpty()) {
//            where = where + " AND AVN_LEAD.PRODUCTID= " + productId;
//        }
//        if (!forCastUntilDate.isEmpty()) {
//            where = where + " AND AVN_LEAD.FORCASTUNTILDATE >= to_date( '" + forCastUntilDate + "', 'yyyy-MM-dd' ) ";
//        }
        if (!value.isEmpty()) {
            where = where + " AND (UPPER(contacts.JOBTITLE) LIKE UPPER('%" + value + "%') OR UPPER(contacts.NAMEINFULL) LIKE UPPER('%" + value + "%') OR UPPER(contacts.MOBILE) LIKE UPPER('%" + value + "%') OR UPPER(lead.LEADAMOUNT) LIKE UPPER('%" + value + "%'))";
        }

        return where;
    }

    private String setLeadClosedListWhere(JSONObject rsp) throws Exception {

        String createUser = rsp.getString("selectAgent");
//        String productId = rsp.getString("productidAll");
        String serachLead = rsp.getString("serachClosed");
//        String forCastUntilDate = rsp.getString("forcastUntilSerach");
        String lostWonDuration = rsp.getString("durationSelect");

        String value = "";
        value = this.frountDeleteSpace(serachLead);
        String where = "WHERE lead.STATUS = '38'";
        if (!createUser.isEmpty()) {
            where = where + " AND lead.CREATEDUSER= '" + createUser + "'";
        }
//        if (!productId.isEmpty()) {
//            where = where + " AND AVN_LEAD.PRODUCTID= " + productId;
//        }
//        if (!forCastUntilDate.isEmpty()) {
//            where = where + " AND AVN_LEAD.FORCASTUNTILDATE >= to_date( '" + forCastUntilDate + "', 'yyyy-MM-dd' ) ";
//        }
        if (!value.isEmpty()) {
            where = where + " AND (UPPER(contacts.JOBTITLE) LIKE UPPER('%" + value + "%') OR UPPER(contacts.NAMEINFULL) LIKE UPPER('%" + value + "%') OR UPPER(contacts.MOBILE) LIKE UPPER('%" + value + "%') OR UPPER(lead.LEADAMOUNT) LIKE UPPER('%" + value + "%'))";
        }
        if (!lostWonDuration.isEmpty()) {

            Calendar cal_backdate = Calendar.getInstance();
            Date current_year = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            if (lostWonDuration.equals("YEARS")) {

                int previousYear = cal_backdate.get(Calendar.YEAR) - 1;
                cal_backdate.set(Calendar.YEAR, previousYear);
                Date previous_year = cal_backdate.getTime();

                where = where + " AND lead.FORCASTUNTILDATE >= STR_TO_DATE ('" + df.format(previous_year) + "', 'yyyy-MM-dd') AND lead.FORCASTUNTILDATE <= STR_TO_DATE ('" + df.format(current_year) + "', 'yyyy-MM-dd') ";

            }
            if (lostWonDuration.equals("QUARTER")) {

                int previousQuarter = cal_backdate.get(Calendar.MONTH) - 3;
                cal_backdate.set(Calendar.MONTH, previousQuarter);
                Date previous_quarter = cal_backdate.getTime();

                where = where + " AND lead.FORCASTUNTILDATE >= STR_TO_DATE ('" + df.format(previous_quarter) + "', 'yyyy-MM-dd') AND lead.FORCASTUNTILDATE <= STR_TO_DATE ('" + df.format(current_year) + "', 'yyyy-MM-dd') ";

            }
            if (lostWonDuration.equals("MONTH")) {

                int previousQuarter = cal_backdate.get(Calendar.MONTH) - 1;
                cal_backdate.set(Calendar.MONTH, previousQuarter);
                Date previous_month = cal_backdate.getTime();

                where = where + " AND lead.FORCASTUNTILDATE >= STR_TO_DATE ('" + df.format(previous_month) + "', 'yyyy-MM-dd') AND lead.FORCASTUNTILDATE <= STR_TO_DATE ('" + df.format(current_year) + "', 'yyyy-MM-dd') ";

            }

        }

        return where;
    }

    private String setLeadLostListWhere(JSONObject rsp) throws Exception {
        String createUser = rsp.getString("selectAgent");
//        String productId = rsp.getString("productidAll");
        String serachLead = rsp.getString("serachLost");
//        String forCastUntilDate = rsp.getString("forcastUntilSerach");
        String lostWonDuration = rsp.getString("durationSelect");

        String value = "";
        value = this.frountDeleteSpace(serachLead);
        String where = "WHERE lead.STATUS = '39'";
        if (!createUser.isEmpty()) {
            where = where + " AND lead.CREATEDUSER= '" + createUser + "'";
        }
//        if (!productId.isEmpty()) {
//            where = where + " AND AVN_LEAD.PRODUCTID= " + productId;
//        }
//        if (!forCastUntilDate.isEmpty()) {
//            where = where + " AND AVN_LEAD.FORCASTUNTILDATE >= to_date( '" + forCastUntilDate + "', 'yyyy-MM-dd' ) ";
//        }
        if (!value.isEmpty()) {
            where = where + " AND (UPPER(contacts.JOBTITLE) LIKE UPPER('%" + value + "%') OR UPPER(contacts.NAMEINFULL) LIKE UPPER('%" + value + "%') OR UPPER(contacts.MOBILE) LIKE UPPER('%" + value + "%') OR UPPER(lead.LEADAMOUNT) LIKE UPPER('%" + value + "%'))";
        }
        if (!lostWonDuration.isEmpty()) {

            Calendar cal_backdate = Calendar.getInstance();
            Date current_year = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            if (lostWonDuration.equals("YEARS")) {

                int previousYear = cal_backdate.get(Calendar.YEAR) - 1;
                cal_backdate.set(Calendar.YEAR, previousYear);
                Date previous_year = cal_backdate.getTime();

                where = where + " AND lead.FORCASTUNTILDATE >= STR_TO_DATE ('" + df.format(previous_year) + "', 'yyyy-MM-dd') AND lead.FORCASTUNTILDATE <= STR_TO_DATE ('" + df.format(current_year) + "', 'yyyy-MM-dd') ";

            }
            if (lostWonDuration.equals("QUARTER")) {

                int previousQuarter = cal_backdate.get(Calendar.MONTH) - 3;
                cal_backdate.set(Calendar.MONTH, previousQuarter);
                Date previous_quarter = cal_backdate.getTime();

                where = where + " AND lead.FORCASTUNTILDATE >= STR_TO_DATE ('" + df.format(previous_quarter) + "', 'yyyy-MM-dd') AND lead.FORCASTUNTILDATE <= STR_TO_DATE ('" + df.format(current_year) + "', 'yyyy-MM-dd') ";

            }
            if (lostWonDuration.equals("MONTH")) {

                int previousQuarter = cal_backdate.get(Calendar.MONTH) - 1;
                cal_backdate.set(Calendar.MONTH, previousQuarter);
                Date previous_month = cal_backdate.getTime();

                where = where + " AND lead.FORCASTUNTILDATE >= STR_TO_DATE ('" + df.format(previous_month) + "', 'yyyy-MM-dd') AND lead.FORCASTUNTILDATE <= STR_TO_DATE ('" + df.format(current_year) + "', 'yyyy-MM-dd') ";

            }
        }

        return where;
    }

    private String frountDeleteSpace(String space) throws Exception {
        String array[] = space.split("\\s+");
        String name = "";
        for (int i = 0; i < array.length; i++) {
            name = name + array[i];
            if (!name.isEmpty()) {
                if (i != (array.length - 1)) {
                    name = name + " ";
                }
            }
        }
        return name;
    }

    @RequestMapping(value = "/salespipline/contactserach", method = RequestMethod.POST)
    public @ResponseBody
    String getSerachContact(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsoncontent = request.getParameter("filterObject");
        JSONObject rsp = new JSONObject(jsoncontent);
        String myJson = "";
        try {
            String createUser = (String) session.getAttribute("username");
            String whereContact = "WHERE  contacts.CREATEDUSER= '" + createUser + "'";
            String whereLead = "WHERE lead.STATUS = '37' AND lead.CREATEDUSER= '" + createUser + "'";
            String whereClosed = "WHERE lead.STATUS = '38' AND lead.CREATEDUSER= '" + createUser + "'";
            String whereLost = "WHERE lead.STATUS = '39' AND lead.CREATEDUSER= '" + createUser + "'";
            JSONObject contactList = new JSONObject();
            contactList.put("CONTACTLIST", salespiplinedaoimpl.getContact(this.setContactListWhere(rsp))); //contacts
            JSONObject leadList = new JSONObject();
            leadList.put("LEADLIST", salespiplinedaoimpl.getLead(this.setLeadListWhere(rsp), "LEADS")); //leads
            JSONObject closedList = new JSONObject();
            closedList.put("CLOSEDLIST", salespiplinedaoimpl.getLead(this.setLeadClosedListWhere(rsp), "CLOSED")); //sales closed
            JSONObject lostList = new JSONObject();
            lostList.put("LOSTLIST", salespiplinedaoimpl.getLead(this.setLeadLostListWhere(rsp), "LOST")); // leads lost
            JSONObject contactCount = new JSONObject();
            contactCount.put("CONTACTCOUNT", salespiplinedaoimpl.getCountContactTable(this.setContactListWhere(rsp)));
            JSONObject leadCount = new JSONObject();
            leadCount.put("LEADCOUNT", salespiplinedaoimpl.getCountLeadTable(this.setLeadListWhere(rsp)));
            JSONObject closedCount = new JSONObject();
            closedCount.put("CLOSEDCOUNT", salespiplinedaoimpl.getCountLeadTable(this.setLeadClosedListWhere(rsp)));
            JSONObject lostCount = new JSONObject();
            lostCount.put("LOSTCOUNT", salespiplinedaoimpl.getCountLeadTable(this.setLeadLostListWhere(rsp)));
            JSONObject sumLead = new JSONObject();
            sumLead.put("SUM", salespiplinedaoimpl.getSumLeadTable(this.setLeadListWhere(rsp)));
            JSONObject sumClosed = new JSONObject();
            sumClosed.put("SUMCLOSED", salespiplinedaoimpl.getSumLeadTable(this.setLeadClosedListWhere(rsp)));
            JSONObject sumLost = new JSONObject();
            sumLost.put("SUMLOST", salespiplinedaoimpl.getSumLeadTable(this.setLeadLostListWhere(rsp)));
            JSONObject conatactTotalCount = new JSONObject();
            JSONObject leadTotalCount = new JSONObject();
            JSONObject closedTotalCount = new JSONObject();
            JSONObject lostTotalCount = new JSONObject();
            conatactTotalCount.put("CONTACTTOTALCOUNT", salespiplinedaoimpl.getCountContactTable(whereContact));
            leadTotalCount.put("LEADTOTALCOUNT", salespiplinedaoimpl.getCountLeadTable(whereLead));
            closedTotalCount.put("CLOSEDTOTALCOUNT", salespiplinedaoimpl.getCountLeadTable(whereClosed));
            lostTotalCount.put("LOSTTOTALCOUNT", salespiplinedaoimpl.getCountLeadTable(whereLost));
            JSONArray listItems = new JSONArray();
            listItems.put(contactList);
            listItems.put(leadList);
            listItems.put(closedList);
            listItems.put(lostList);
            listItems.put(contactCount);
            listItems.put(leadCount);
            listItems.put(closedCount);
            listItems.put(lostCount);
            listItems.put(sumLead);
            listItems.put(sumClosed);
            listItems.put(sumLost);
            listItems.put(conatactTotalCount);
            listItems.put(leadTotalCount);
            listItems.put(closedTotalCount);
            listItems.put(lostTotalCount);
            myJson = listItems.toString();

        } catch (Exception exception) {
            Logger.getLogger(SalesPiplineController.class
                    .getName()).log(Level.SEVERE, null, exception);
        }
        return myJson;
    }

    @RequestMapping(value = "/salespipline/contactserachajent", method = RequestMethod.POST)
    public @ResponseBody
    String getSerachContactnew(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jsoncontent = request.getParameter("filterObject");
        JSONObject rsp = new JSONObject(jsoncontent);
        String myJson = "";
        try {

            JSONObject agentProduct = new JSONObject();
            agentProduct.put("AGENTPRODUCT", salespiplinedaoimpl.getProductDropdown(rsp.getString("selectAgent")));
            JSONArray listItems = new JSONArray();

            listItems.put(agentProduct);
            myJson = listItems.toString();

        } catch (Exception exception) {
            Logger.getLogger(SalesPiplineController.class
                    .getName()).log(Level.SEVERE, null, exception);
        }
        return myJson;
    }

    @RequestMapping(value = "/salespipline/haveaccount", method = RequestMethod.POST)
    public @ResponseBody
    String contactHaveAccount(@RequestParam int leadid, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String myJson = "";
        String accountId = "";
        JSONObject haveAccount = new JSONObject();
        try {
            accountId = salespiplinedaoimpl.getHaveAccount(leadid);
            if (accountId == null) {
                haveAccount.put("HAVEACCOUNT", "NOT");
            } else {
                haveAccount.put("HAVEACCOUNT", "YES");
                salespiplinedaoimpl.updateLeadClosed("38", leadid, "");
            }
            myJson = haveAccount.toString();

        } catch (Exception exception) {
            Logger.getLogger(SalesPiplineController.class
                    .getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/getName", method = RequestMethod.POST)
    public @ResponseBody
    String getConatctName(@RequestParam int leadid, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String myJson = "";
        String namestr = "";
        JSONObject name = new JSONObject();
        try {

            namestr = salespiplinedaoimpl.getName(leadid);
            name.put("NAME", namestr);
            myJson = name.toString();

        } catch (Exception exception) {
            Logger.getLogger(SalesPiplineController.class
                    .getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/getLeadDetailsById", method = RequestMethod.POST)
    public @ResponseBody
    String getLeadDetailsById(@RequestParam int leadid, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        List<Lead> contact;
        String myJson = "";
        JSONObject jSONObject = new JSONObject();
        try {
            contact = salespiplinedaoimpl.getLeadOne(leadid);
            jSONObject.put("amount", contact.get(0).getLeadamount());
            jSONObject.put("accouncount", contact.get(0).getAccouncount());
            myJson = jSONObject.toString();

        } catch (Exception exception) {
            Logger.getLogger(SalesPiplineController.class
                    .getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/getContactCreateLead", method = RequestMethod.POST)
    public @ResponseBody
    String getcontactLead(@RequestParam String createUser, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> contactListDropDown;
        String myJson = "";
        JSONObject jSONObject = new JSONObject();
        try {
            contactListDropDown = salespiplinedaoimpl.getContactDropDownList(createUser);
            jSONObject.put("CONTACT", contactListDropDown);
            myJson = jSONObject.toString();

        } catch (Exception exception) {
            Logger.getLogger(SalesPiplineController.class
                    .getName()).log(Level.SEVERE, null, exception);
        }
        return myJson;
    }

    @RequestMapping(value = "/salespipline/updatecontact", method = RequestMethod.POST)
    public @ResponseBody
    String UpdateContact(@RequestParam String nameInFull, @RequestParam String jobtitle, @RequestParam String employer, @RequestParam String email, @RequestParam String mobile, @RequestParam int contactId) throws Exception {
        Contact contact = new Contact();
        String myJson = "";
        JSONObject success = new JSONObject();
        try {
            contact.setNameInFull(nameInFull);
            contact.setJobtitle(jobtitle);
            contact.setEmployer(employer);
            contact.setEmail(email);
            contact.setMobile(mobile);
            contact.setContactid(contactId);
            salespiplinedaoimpl.updateContact(contact);
            success.put("CODE", "SUCCESS");
            myJson = success.toString();
        } catch (Exception exception) {
            success.put("CODE", "ERROR");
            Logger
                    .getLogger(SalesPiplineController.class
                            .getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/lead/update", method = RequestMethod.POST)
    public @ResponseBody
    String updateLead(@RequestParam String forcastuntildate,
            @RequestParam BigDecimal amount,
            @RequestParam int productId,
            @RequestParam int leadId,
            @RequestParam int accountcount,
            HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        Lead lead = new Lead();
        JSONObject success = new JSONObject();
        String myJson = "";
        try {
            lead.setProductid(productId);
            lead.setLeadamount(amount);
            lead.setForcastuntildate(forcastuntildate);
            lead.setLeadid(leadId);
            lead.setAccouncount(accountcount);
            salespiplinedaoimpl.updateLead(lead);
            success.put("CODE", "SUCCESS");
            myJson = success.toString();

        } catch (Exception exception) {
            success.put("CODE", "ERROR");
            Logger
                    .getLogger(SalesPiplineController.class
                            .getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/lead/update/salesclosed", method = RequestMethod.POST)
    public @ResponseBody
    String updateLeadSalesClosed(@RequestParam String closeddate, @RequestParam BigDecimal amount, @RequestParam BigDecimal confirmamount, @RequestParam int productId, @RequestParam int leadId, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        Lead lead = new Lead();
        JSONObject success = new JSONObject();
        String myJson = "";
        try {
            lead.setProductid(productId);
            lead.setLeadamount(amount);
            lead.setConfirmedamount(confirmamount);
            lead.setSalecloseddate(closeddate);
            lead.setLeadid(leadId);
            salespiplinedaoimpl.updateLeadSalesClosed(lead);
            success.put("CODE", "SUCCESS");
            myJson = success.toString();

        } catch (Exception exception) {
            success.put("CODE", "ERROR");
            Logger
                    .getLogger(SalesPiplineController.class
                            .getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

    @RequestMapping(value = "/salespipline/lead/update/lost", method = RequestMethod.POST)
    public @ResponseBody
    String updateLeadLost(@RequestParam String lostdate, @RequestParam BigDecimal amount, @RequestParam int productId, @RequestParam int leadId, @RequestParam int resoncode, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        Lead lead = new Lead();
        JSONObject success = new JSONObject();
        String myJson = "";
        try {
            lead.setProductid(productId);
            lead.setLeadamount(amount);
            lead.setForcastuntildate(lostdate);
            lead.setLeadid(leadId);
            lead.setLostresoncode(resoncode);
            salespiplinedaoimpl.updateLead(lead);
            success.put("CODE", "SUCCESS");
            myJson = success.toString();

        } catch (Exception exception) {
            success.put("CODE", "ERROR");
            Logger
                    .getLogger(SalesPiplineController.class
                            .getName()).log(Level.SEVERE, null, exception);
        }

        return myJson;
    }

}

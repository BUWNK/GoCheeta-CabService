/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.schedulertask;

import com.avn.ccl.daoimpl.casemgt.CaseDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.employee.EmployeeDAOImpl;
import com.avn.ccl.daoimpl.organizationalparameters.OrganizationalParametersDAOImpl;
import com.avn.ccl.daoimpl.notification.NotificationDAOImpl;
import com.avn.ccl.daoimpl.notificationtemplate.NotifictionTemplateDAOImpl;
import com.avn.ccl.daoimpl.schedulertask.SchedulerTaskDAOImpl;
import com.avn.ccl.model.casemgt.Case;
import com.avn.ccl.model.notification.NotificationMail;
import com.avn.ccl.model.notificationtemplate.NotificationTemplate;
import com.avn.ccl.model.schedulertask.CaseInfo;
import com.avn.ccl.util.ApplicationUtil;
import com.avn.ccl.util.Common;
import static com.avn.ccl.util.Common.getDateFromString;
import static com.avn.ccl.util.Common.getFirstCharcterCapital;
import static com.avn.ccl.util.Common.getStringFormatDate;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @Author : Office Isuru
 * @Document : SchedulerTask
 * @Date : Sep 21, 2015 4:25:15 PM
 */
public class SchedulerTask {

    @Autowired
    SchedulerTaskDAOImpl shedulertaskdaoimpl;
    @Autowired
    NotificationDAOImpl notificationdaoimpl;
    @Autowired
    NotifictionTemplateDAOImpl emaildaoimpl;
    @Autowired
    CommonDAOImpl commonDAOImpl;
    @Autowired
    OrganizationalParametersDAOImpl defaultnotifipersons;
    @Autowired
    CaseDAOImpl casedaoimpl;
    @Autowired
    EmployeeDAOImpl employeedaoimple;

    private static final long MILLIS_OF_WEEK = TimeUnit.DAYS.toMillis(7);
    private static final long MILLIS_OF_WORKWEEK = TimeUnit.DAYS.toMillis(5);

    public void xmlCronTask() {
        try {
            System.out.println("Shedultask Start " + ApplicationUtil.SERVLET_CONTEXT.getInitParameter(CommonVarList.CONTEXT_PARAM_SOFTWARE_VERSION) + " " + commonDAOImpl.getCurentTimesStamp());
            if (Boolean.valueOf(ApplicationUtil.SERVLET_CONTEXT.getInitParameter(CommonVarList.CONTEXT_PARAM_IS_SHEDUER_TASK_RUN))) {
                String defaultccperson = defaultnotifipersons.getDefaultCCPerson();
                Date currentDateTime = commonDAOImpl.getCurentTimesStamp();

                // Level 2 Cases
                System.out.println("*******### Level 1 Started ###*******");
                for (CaseInfo caseInfo : shedulertaskdaoimpl.getCaseByLevelId(Integer.valueOf(commonDAOImpl.getOgrParaValueByRecordId(MasterDataVarList.CCL_CODE_NOTIFICATION_LEVEL_1)))) {
                    System.out.println("Case ID : " + caseInfo.getCaseid());
                    int time = getMinsBetween(caseInfo.getCreatedtime(), currentDateTime, true);
                    System.out.println("level 1 Time : " + time);
                    if (time >= ((Integer.valueOf(shedulertaskdaoimpl.getOrganizationlPrameters91())) * 60)) {
                        //TO Mail
                        List<String> bmemailsdetails = shedulertaskdaoimpl.getBMZMEmail(caseInfo.getCaseid());
                        Case casebean = casedaoimpl.getCaseByCaseId(caseInfo.getCaseid());
                        String useremails = employeedaoimple.getEmployeeEmailByUsername(casebean.getUser());
                        String[] emailtolist = new String[1];
                        if (bmemailsdetails != null && !bmemailsdetails.isEmpty()) {
                            emailtolist[0] = bmemailsdetails.get(0);
                        } else {
                            emailtolist[0] = null;
                        }
                        //TO Mail
                        //CC Mail
                        List<String> newemailcclist = new ArrayList<>();
                        String[] emailcclist = {useremails, defaultccperson};
                        for (String oneitememail : emailcclist) {
                            if (oneitememail != null && oneitememail.length() > 0) {
                                newemailcclist.add(oneitememail);
                            }
                        }
                        emailcclist = newemailcclist.toArray(new String[newemailcclist.size()]);
                        //CC Mail

                        NotificationTemplate notificationTemplate = emaildaoimpl.getNotificationTemplate(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_REMIND_1);
                        List<String> customerdetails = shedulertaskdaoimpl.getCustomerDetails(caseInfo.getCaseid());
                        String subject;
                        if (caseInfo.getCasetypeid() == MasterDataVarList.CCL_CODE_CASE_TYPE_LEADS) {
                            subject = " LEADS - "
                                    + String.format(notificationTemplate.getSubject(),
                                            casebean.getTitle(),
                                            getFirstCharcterCapital(customerdetails.get(0)), caseInfo.getCaseid());
                        } else {
                            subject = String.format(notificationTemplate.getSubject(),
                                    casebean.getTitle(), getFirstCharcterCapital(customerdetails.get(0)), caseInfo.getCaseid());
                        }

                        String reciepent = String.format(notificationTemplate.getReciepent(), getFirstCharcterCapital(bmemailsdetails.get(2)));

                        String mailContent = reciepent + "\n\n" + String.format(notificationTemplate.getMailContent(), "90", caseInfo.getCaseid()) + "\n\n\n"
                                + notificationTemplate.getConclution() + "\n\n"
                                + notificationTemplate.getConcludingName();

                        SimpleMailMessage sm = new SimpleMailMessage();
                        sm.setFrom(CommonVarList.NOTIFICATION_MAIL_FROM);
                        sm.setTo(emailtolist);
                        sm.setCc(emailcclist);
                        sm.setSubject(notificationTemplate.getSubject());
                        sm.setSubject(subject);
                        sm.setText(mailContent);
                        NotificationMail notification = new NotificationMail();
                        notification.setSimpleMailMessage(sm);
                        notification.setResources(null);
                        notification.setCaseid(caseInfo.getCaseid());
                        if (caseInfo.getCasetypeid() == MasterDataVarList.CCL_CODE_CASE_TYPE_LEADS) {
                            notification.setComponent(MasterDataVarList.CCL_CODE_NOTIFICATION_COMPONENT_LEADS);
                        } else {
                            notification.setComponent(MasterDataVarList.CCL_CODE_NOTIFICATION_COMPONENT_CASE);
                        }
                        notification.setNotificationTemplateID(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_REMIND_1);
                        notification.setCreatedUser("SYSTEM");
                        notificationdaoimpl.sendMail(notification);
                        System.out.println("Reminde 1 Send Success");
                        shedulertaskdaoimpl.setNotificationlevel("2", caseInfo.getCaseid());
                    }
                }
                System.out.println("*******### Level 1 Ended ###*******");

                // Level 3 Cases
                System.out.println("*******### Level 2 Started ###*******");
                for (CaseInfo caseInfo : shedulertaskdaoimpl.getCaseByLevelId(Integer.valueOf(commonDAOImpl.getOgrParaValueByRecordId(MasterDataVarList.CCL_CODE_NOTIFICATION_LEVEL_2)))) {
                    System.out.println("Case ID : " + caseInfo.getCaseid());
                    int time = getMinsBetween(caseInfo.getCreatedtime(), currentDateTime, true);
                    System.out.println("level 2 Time : " + time);
                    if (time >= ((Integer.valueOf(shedulertaskdaoimpl.getOrganizationlPrameters101())) * 60)) {
                        List<String> level3mails;
                        List<String> bmzmemailsdetails;
                        if (caseInfo.getBrmf().equalsIgnoreCase("BR")) {
                            level3mails = shedulertaskdaoimpl.getRMEmail(caseInfo.getCaseid());
                        } else {
                            level3mails = shedulertaskdaoimpl.getInternamAuditMails(caseInfo.getCaseid());
                        }
                        bmzmemailsdetails = shedulertaskdaoimpl.getBMZMEmail(caseInfo.getCaseid());

                        Case casebean = casedaoimpl.getCaseByCaseId(caseInfo.getCaseid());
                        String useremails = employeedaoimple.getEmployeeEmailByUsername(casebean.getUser());
                        String[] emaillist = new String[1];
                        if (level3mails != null && !level3mails.isEmpty()) {
                            emaillist[0] = level3mails.get(0);
                        } else {
                            emaillist[0] = null;
                        }
                        String[] emailcclist = {bmzmemailsdetails.get(0), useremails, defaultccperson};
                        List<String> newemailcclist = new ArrayList<>();
                        for (String oneitememail : emailcclist) {
                            if (oneitememail != null && oneitememail.length() > 0) {
                                newemailcclist.add(oneitememail);
                            }
                        }
                        emailcclist = newemailcclist.toArray(new String[newemailcclist.size()]);
                        NotificationTemplate notificationList = emaildaoimpl.getNotificationTemplate(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_REMIND_2);
                        List<String> customerdetails = shedulertaskdaoimpl.getCustomerDetails(caseInfo.getCaseid());
                        String subject;
                        if (caseInfo.getCasetypeid() == MasterDataVarList.CCL_CODE_CASE_TYPE_LEADS) {
                            subject = " LEADS - "
                                    + String.format(notificationList.getSubject(),
                                            casebean.getTitle(),
                                            getFirstCharcterCapital(customerdetails.get(0)), caseInfo.getCaseid());
                        } else {
                            subject = String.format(notificationList.getSubject(),
                                    casebean.getTitle(),
                                    getFirstCharcterCapital(customerdetails.get(0)), caseInfo.getCaseid());
                        }

                        String reciepent = String.format(notificationList.getReciepent(), getFirstCharcterCapital(bmzmemailsdetails.get(2)));

                        String mailContent = reciepent + "\n\n" + String.format(notificationList.getMailContent()) + "\n\n\n"
                                + notificationList.getConclution() + "\n\n"
                                + notificationList.getConcludingName();

                        SimpleMailMessage sm = new SimpleMailMessage();
                        sm.setFrom(CommonVarList.NOTIFICATION_MAIL_FROM);
                        sm.setTo(emaillist);
                        sm.setCc(emailcclist);
                        sm.setSubject(notificationList.getSubject());
                        sm.setSubject(subject);
                        sm.setText(mailContent);
                        NotificationMail notification = new NotificationMail();
                        notification.setSimpleMailMessage(sm);
                        notification.setResources(null);
                        notification.setCaseid(caseInfo.getCaseid());
                        if (caseInfo.getCasetypeid() == MasterDataVarList.CCL_CODE_CASE_TYPE_LEADS) {
                            notification.setComponent(MasterDataVarList.CCL_CODE_NOTIFICATION_COMPONENT_LEADS);
                        } else {
                            notification.setComponent(MasterDataVarList.CCL_CODE_NOTIFICATION_COMPONENT_CASE);
                        }
                        notification.setNotificationTemplateID(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_REMIND_2);
                        notification.setCreatedUser("SYSTEM");
                        notificationdaoimpl.sendMail(notification);
                        System.out.println("Reminde 2 Send Success");
                        shedulertaskdaoimpl.setNotificationlevel("3", caseInfo.getCaseid());
                    }
                }
                System.out.println("*******### Level 2 Ended ###*******");

                //Level 4 Cases
                System.out.println("*******### Level 3 Started ###*******");
                for (CaseInfo caseInfo : shedulertaskdaoimpl.getCaseByLevelId(Integer.valueOf(commonDAOImpl.getOgrParaValueByRecordId(MasterDataVarList.CCL_CODE_NOTIFICATION_LEVEL_3)))) {
                    System.out.println("Case ID : " + caseInfo.getCaseid());
                    int time = getMinsBetween(caseInfo.getCreatedtime(), currentDateTime, true);
                    System.out.println("level 3 Time : " + time);
                    if (time >= ((Integer.valueOf(shedulertaskdaoimpl.getOrganizationlPrameters201())) * 60)) {
                        List<String> level3mails;
                        if (caseInfo.getBrmf().equalsIgnoreCase("BR")) {
                            level3mails = shedulertaskdaoimpl.getRMEmail(caseInfo.getCaseid());
                        } else {
                            level3mails = shedulertaskdaoimpl.getInternamAuditMails(caseInfo.getCaseid());
                        }
                        String[] level3mailsList = new String[1];
                        if (level3mails != null && !level3mails.isEmpty()) {
                            level3mailsList[0] = level3mails.get(0);
                        } else {
                            level3mailsList[0] = null;
                        }
                        List<String> bmzmemailsdetails = shedulertaskdaoimpl.getBMZMEmail(caseInfo.getCaseid());
                        Case casebean = casedaoimpl.getCaseByCaseId(caseInfo.getCaseid());
                        String useremails = employeedaoimple.getEmployeeEmailByUsername(casebean.getUser());

                        List<String> contactcenter = shedulertaskdaoimpl.getCCPersonmails(caseInfo.getCaseid());
                        String[] emaillist = new String[1];
                        if (contactcenter != null && !contactcenter.isEmpty()) {
                            emaillist[0] = contactcenter.get(0);
                        } else {
                            emaillist[0] = null;
                        }
                        String[] emailcclist = {level3mailsList[0], bmzmemailsdetails.get(0), useremails, defaultccperson};
                        List<String> newemailcclist = new ArrayList<>();
                        for (String oneitememail : emailcclist) {
                            if (oneitememail != null && oneitememail.length() > 0) {
                                newemailcclist.add(oneitememail);
                            }
                        }
                        emailcclist = newemailcclist.toArray(new String[newemailcclist.size()]);

                        NotificationTemplate notificationList = emaildaoimpl.getNotificationTemplate(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_REMIND_3);
                        List<String> customerdetails = shedulertaskdaoimpl.getCustomerDetails(caseInfo.getCaseid());
                        String subject;
                        if (caseInfo.getCasetypeid() == MasterDataVarList.CCL_CODE_CASE_TYPE_LEADS) {
                            subject = " LEADS - " + String.format(notificationList.getSubject(), casebean.getTitle(), getFirstCharcterCapital(customerdetails.get(0)), caseInfo.getCaseid());
                        } else {
                            subject = String.format(notificationList.getSubject(), casebean.getTitle(), getFirstCharcterCapital(customerdetails.get(0)), caseInfo.getCaseid());
                        }

                        String reciepent = String.format(notificationList.getReciepent(), getFirstCharcterCapital(bmzmemailsdetails.get(2)));

                        String mailContent = reciepent + "\n\n" + String.format(notificationList.getMailContent()) + "\n\n\n"
                                + notificationList.getConclution() + "\n\n"
                                + notificationList.getConcludingName();

                        SimpleMailMessage sm = new SimpleMailMessage();
                        sm.setFrom(CommonVarList.NOTIFICATION_MAIL_FROM);
                        sm.setTo(emaillist);
                        sm.setCc(emailcclist);
                        sm.setSubject(notificationList.getSubject());
                        sm.setSubject(subject);
                        sm.setText(mailContent);
                        NotificationMail notification = new NotificationMail();
                        notification.setSimpleMailMessage(sm);
                        notification.setResources(null);
                        notification.setCaseid(caseInfo.getCaseid());
                        if (caseInfo.getCasetypeid() == MasterDataVarList.CCL_CODE_CASE_TYPE_LEADS) {
                            notification.setComponent(MasterDataVarList.CCL_CODE_NOTIFICATION_COMPONENT_LEADS);
                        } else {
                            notification.setComponent(MasterDataVarList.CCL_CODE_NOTIFICATION_COMPONENT_CASE);
                        }
                        notification.setNotificationTemplateID(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_REMIND_3);
                        notification.setCreatedUser("SYSTEM");
                        notificationdaoimpl.sendMail(notification);
                        System.out.println("Reminde 3 Send Success");
                        shedulertaskdaoimpl.setNotificationlevel("4", caseInfo.getCaseid());
                    }
                }
                System.out.println("*******### Level 3 Ended ###*******");
            }
            System.out.println("Shedultask End " + ApplicationUtil.SERVLET_CONTEXT.getInitParameter(CommonVarList.CONTEXT_PARAM_SOFTWARE_VERSION) + " " + commonDAOImpl.getCurentTimesStamp());
        } catch (Exception ex) {
            Logger.getLogger(SchedulerTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getMinsBetween(Date caseCreatedDateTime, Date currentDateTime, boolean onlyBusinessDays) {
        boolean holiday = false;
        int noofholidays = 0;
        Date caseCreatedDate = Common.getStartingTimeofDay(caseCreatedDateTime);
        Date currentDate = Common.getStartingTimeofDay(currentDateTime);
        if (holiday) {
            noofholidays = (int) (currentDate.getTime() - caseCreatedDate.getTime()) / (1000 * 60 * 60 * 24);
        }
        int noofdays = (int) (currentDate.getTime() - caseCreatedDate.getTime()) / (1000 * 60 * 60 * 24);
        long nofomilisecond = noofdays * (54000000);
        long fullduration = currentDateTime.getTime() - caseCreatedDateTime.getTime();
        long duration = fullduration - nofomilisecond;
        if (onlyBusinessDays) {
            Date fri = toFridayFive(caseCreatedDateTime);
            long timeBeforeWeekend = Math.max(fri.getTime() - caseCreatedDateTime.getTime(), 0);
            if (duration > timeBeforeWeekend) {
                Date mon = toMondayEight(currentDateTime);
                long timeAfterWeekend = Math.max(currentDateTime.getTime() - mon.getTime(), 0);
                long numberOfWeekends = Math.max((duration / MILLIS_OF_WEEK) - 1, 0);
                Date monday;
                int days = 0;
                try {
                    monday = getDateFromString(CommonVarList.DATE_FORMAT_dd_MMM_yyyy, getStringFormatDate(CommonVarList.DATE_FORMAT_dd_MMM_yyyy, mon));
                    days = (int) (currentDate.getTime() - monday.getTime()) / (1000 * 60 * 60 * 24);
                } catch (ParseException ex) {
                    Logger.getLogger(SchedulerTask.class.getName()).log(Level.SEVERE, null, ex);
                }

                long milisecond = days * (54000000);
                fullduration = numberOfWeekends * MILLIS_OF_WORKWEEK + timeBeforeWeekend + timeAfterWeekend;
                duration = fullduration - milisecond;
            }
        }
        duration = duration - noofholidays * (32400000);
        System.out.println(duration);
        int x = (int) TimeUnit.MILLISECONDS.toMinutes(duration);
        System.out.println(x);
        return (int) TimeUnit.MILLISECONDS.toMinutes(duration);
    }

    private static Date toMondayEight(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SATURDAY:
            case Calendar.SUNDAY:
                cal.add(Calendar.DAY_OF_MONTH, 7);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        toMmorning(cal);
        return cal.getTime();
    }

    private static Date toFridayFive(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        toFriFive(cal);
        return cal.getTime();
    }

    private static void toMmorning(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    private static void toFriFive(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 17);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

}

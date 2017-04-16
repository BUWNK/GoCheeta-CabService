/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.casemgt;

import com.avn.ccl.bean.serviceclient.fullprofile.CustomerData;
import com.avn.ccl.model.callcenter.CallCenter;
import com.avn.ccl.model.account.Account;
import com.avn.ccl.bean.webserviceclient.CustomerFullProfileSearhDataBean;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
import com.avn.ccl.daoimpl.casepriority.CasePriorityDAOImpl;
import com.avn.ccl.daoimpl.casetype.CaseTypeDAOImpl;
import com.avn.ccl.daoimpl.department.DepartmentDAOImpl;
import com.avn.ccl.daoimpl.product.ProductDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.callcenter.CallCenterDAOImpl;
import com.avn.ccl.daoimpl.casemgt.CaseDAOImpl;
import com.avn.ccl.daoimpl.organizationalparameters.OrganizationalParametersDAOImpl;
import com.avn.ccl.daoimpl.employee.EmployeeDAOImpl;
import com.avn.ccl.daoimpl.notificationtemplate.NotifictionTemplateDAOImpl;
import com.avn.ccl.daoimpl.notification.NotificationDAOImpl;
import com.avn.ccl.daoimpl.schedulertask.SchedulerTaskDAOImpl;
import com.avn.ccl.daoimpl.title.TitleDAOImpl;
import com.avn.ccl.daoimpl.user.UserDAOImpl;
import com.avn.ccl.model.branch.Branch;
import com.avn.ccl.model.casemgt.Case;
import com.avn.ccl.model.casemgt.CaseHistory;
import com.avn.ccl.model.dependentrype.CaseUrl;
import com.avn.ccl.model.employee.Employee;
import com.avn.ccl.model.notification.NotificationMail;
import com.avn.ccl.model.notification.NotificationSMS;
import com.avn.ccl.model.notificationtemplate.NotificationTemplate;
import com.avn.ccl.service.webservicecilent.CCLServiceClient;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import static com.avn.ccl.util.varlist.MasterDataVarList.CCL_CODE_CASE_PRIORITY_DEFAULT;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Administrator
 */
@Controller
public class CaseController {

    @Autowired
    SchedulerTaskDAOImpl shedulertaskdaoimpl;
    @Autowired
    ServletContext context;
    @Autowired
    CaseDAOImpl caseDaoImpl;
    @Autowired
    AuditTraceDAOImpl audit;
    @Autowired
    CaseTypeDAOImpl caseTypeDAOImpl;
    @Autowired
    CasePriorityDAOImpl casePriorityDAOImpl;
    @Autowired
    ProductDAOImpl productDAOImpl;
    @Autowired
    DepartmentDAOImpl departmentDAOImpl;
    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    UserDAOImpl userDAOImpl;
    @Autowired
    CallCenterDAOImpl callCenterDaoImpl;
    @Autowired
    NotificationDAOImpl notificationDAOImpl;
    @Autowired
    BranchDAOImpl branchdaoimpl;
    @Autowired
    NotifictionTemplateDAOImpl notifictionTemplateDAOImpl;
    @Autowired
    EmployeeDAOImpl employeeDAOImpl;
    @Autowired
    OrganizationalParametersDAOImpl organizationalParametersDAOImpl;
    @Autowired
    TitleDAOImpl titleDAOImpl;
    @Autowired
    CommonDAOImpl commonDAOImpl;

    private final int maxFileSizeInKb = 5000;

    @RequestMapping(value = "/case/search", method = RequestMethod.GET)
    public String callSearch(@ModelAttribute("casesearch") Case data, ModelMap model, HttpSession session) {
        model.put("MAP", "CSP");
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Case usercase = checkPrivilage_btn(data, String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CASAE_SEARCH_SUBSECTION_ID), objList);
            model.addAttribute("avn_search", usercase.isSearch_btn());
        } catch (Exception ex) {
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "case/casesearch";
    }

    @RequestMapping(value = "/case/create", method = RequestMethod.GET)
    public ModelAndView caseCreate(@ModelAttribute("userCase") Case userCase, ModelMap model, HttpSession session) {
        model.put("MAP", "CNCP");
        try {
            this.setCreateViewComponenets(model);
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            Case usercase = checkPrivilage_btn(userCase, String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CASE_SUBSECTION_ID), objList);
            model.addAttribute("avn_create", usercase.isSave_btn());
            userCase.setPriorityId(CCL_CODE_CASE_PRIORITY_DEFAULT);
        } catch (Exception ex) {
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView("case/casecreate", "command", model);
    }

    @RequestMapping(value = "/case/insert", method = RequestMethod.POST)
    public ModelAndView caseInsert(@ModelAttribute("userCase") Case userCase, ModelMap model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        try {
            boolean filesizok = true;
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CASE_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                List<MultipartFile> files = userCase.getFiles();
                for (MultipartFile multipartFile : files) {
                    if (!(multipartFile.getSize() / 1024 <= maxFileSizeInKb)) {
                        filesizok = false;
                        break;
                    }
                }
                if (filesizok) {
                    long caseid = caseDaoImpl.insertCase(userCase, username);
                    audit.insertAuditTrace("Ticket Create page", "Create", "Ticket Created ", String.valueOf(caseid), username);
                    model.addAttribute("successMsg", "Ticket created ID : " + caseid);

                    String errorMessage = "";
                    OutputStream outputStream = null;
                    try {
                        Date date = commonDAOImpl.getCurentTimesStamp();
                        String todayDirectory = Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd, date);
                        String time_format = Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date);
                        File file;
                        File case_attach_file = null;

//                        List<String> fileNames = new ArrayList<>();
                        Integer loopid = 0;
                        for (MultipartFile multipartFile : files) {
                            if (multipartFile.getSize() > 0) {
                                String uploadFileLocation = context.getInitParameter(CommonVarList.CONTEXT_PARAM_CASE_ATTACH_FILE_UPLOAD);
                                uploadFileLocation += File.separator + username + File.separator + todayDirectory + File.separator + caseid;
                                userCase.setFile_location(uploadFileLocation);
                                String fileName = multipartFile.getOriginalFilename();
//                                fileNames.add(fileName);
                                file = new File(uploadFileLocation);
                                if (!file.exists()) {
                                    file.mkdirs();
                                }
                                String case_attach_file_name = "CASE_" + caseid + time_format + loopid + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                                uploadFileLocation += File.separator + case_attach_file_name;
                                case_attach_file = new File(uploadFileLocation);
                                userCase.setFilename(case_attach_file_name);
                                userCase.setCaseId(String.valueOf(caseid));
                                caseDaoImpl.insertCaseUrl(uploadFileLocation, multipartFile.getOriginalFilename(), caseid, username);
                                //Handle file content - multipartFile.getInputStream()

                                //Handle file content - multipartFile.getInputStream()
                                outputStream = new FileOutputStream(case_attach_file);
                                outputStream.write(multipartFile.getBytes());

                                case_attach_file_name = null;
                                loopid++;
                            }

                        }
                    } catch (Exception e) {
                        errorMessage = "Failed to upload attachment";
                        Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, e);
                    } finally {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Exception e) {
                            }
                        }
                    }

                    long employeeid = caseDaoImpl.getCaseAssignedEmployeeId(caseid);
                    String customerName = Common.getFirstCharcterCapital(userCase.getCustomername());
                    try {
                        this.setCreateViewComponenets(model);
                        NotificationTemplate notificationTemplate = notifictionTemplateDAOImpl.getNotificationTemplate(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_CASE);
                        String defaultnotifiperson = organizationalParametersDAOImpl.getDefaultCCPerson();
                        String[] ccemaillist = {employeeDAOImpl.getEmployeeEmailByUsername(username), defaultnotifiperson};
                        SimpleMailMessage sm = new SimpleMailMessage();
                        sm.setFrom(CommonVarList.NOTIFICATION_MAIL_FROM);
                        sm.setTo(employeeDAOImpl.getEmployeeEmailByEmployeeId(employeeid));

                        String assigneeName = Common.getFirstCharcterCapital(userCase.getAssigneeId1());

                        String subject = String.format(notificationTemplate.getSubject(), Common.getFirstCharcterCapital(userCase.getTitle()), customerName, String.valueOf(caseid));
                        sm.setSubject(subject);

                        Branch branch = branchdaoimpl.getBranch(Integer.valueOf(userCase.getBranchcode()));
                        String mailContent = String.format(
                                notificationTemplate.getReciepent(),
                                assigneeName) + "\n\n\n"
                                + String.format(
                                        notificationTemplate.getMailContent(),
                                        userCase.getCaseDate(),
                                        customerName,
                                        userCase.getPhonenumber(),
                                        branch.getAliasName(),
                                        userCase.getNic(),
                                        userCase.getDescription(), "8") + "\n\n\n"
                                + notificationTemplate.getConclution() + "\n\n"
                                + notificationTemplate.getConcludingName();
                        sm.setText(mailContent);
                        sm.setCc(ccemaillist);
                        NotificationMail notification = new NotificationMail();
                        notification.setSimpleMailMessage(sm);
                        notification.setResources(null);
                        notification.setCaseid(String.valueOf(caseid));
                        notification.setComponent("Case");
                        notification.setCreatedUser(username);
                        notification.setNotificationTemplateID(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_CASE);
                        notificationDAOImpl.sendMail(notification);
                    } catch (Exception e) {
                        if (errorMessage.isEmpty()) {
                            errorMessage = "Their is an Error while sending mail please contact Administrator";
                        } else {
                            errorMessage += ("<br>" + "Their is an Error while sending mail please contact Administrator");
                        }
                        Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, e);
                    }

                    try {
                        NotificationTemplate notificationTemplateSMS = notifictionTemplateDAOImpl.getNotificationTemplate(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_SMS);
                        Employee employeeDetail = employeeDAOImpl.getEmployeeDetailsByEmployeeId(employeeid);
                        String phonenumber = "";
                        String number = "";
                        String smsContent = "";
                        if (employeeDetail.getContactmobile() != null && StringUtils.isNumeric(employeeDetail.getContactmobile())) {
                            if (employeeDetail.getContactmobile().length() < 10) {
                                number = employeeDetail.getContactmobile();
                                number = number.substring(0, 0) + "0" + number.substring(0, number.length());
                                phonenumber = number;
                            } else if (employeeDetail.getContactmobile().length() > 10) {
                                number = number.substring(0, 0) + "0" + number.substring(3, number.length());
                                phonenumber = number;
                            } else {
                                phonenumber = employeeDetail.getContactmobile();
                            }
                            smsContent = String.format(notificationTemplateSMS.getMailContent(), String.valueOf(caseid), customerName, phonenumber);
                            if (smsContent.length() > 160) {
                                int numdigits = (smsContent.length() - 160);
                                smsContent = String.format(notificationTemplateSMS.getMailContent(), String.valueOf(caseid), customerName.substring(0, numdigits), phonenumber);
                            }
                            NotificationSMS notificationsms = new NotificationSMS();
                            notificationsms.setRecipient(phonenumber);
                            notificationsms.setTemplateid(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_SMS);
                            notificationsms.setMessagebody(smsContent);
                            notificationsms.setComponentid(caseid);
                            notificationsms.setComponent("Case");
                            notificationsms.setCclsmsrout(MasterDataVarList.CCL_CODE_NOTIFICATION_COMPONENT_CCLSMSROUT);
                            notificationsms.setCclgroupid(MasterDataVarList.CCL_CODE_NOTIFICATION_COMPONENT_CCLGROUPID);
                            notificationsms.setCclsmspriority(1);
                            notificationsms.setSendtime(new Timestamp(commonDAOImpl.getCurentTimesStamp().getTime()));
                            notificationsms.setCreatedUser(username);
                            notificationDAOImpl.sendSMS(notificationsms);
                        }
                    } catch (Exception e) {
                        if (errorMessage.isEmpty()) {
                            errorMessage = "Their is an Error while sending sms please contact Administrator";
                        } else {
                            errorMessage += ("<br>" + "Their is an Error while sending sms please contact Administrator");
                        }
                        Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, e);
                    }

                    if (!errorMessage.isEmpty()) {
                        model.addAttribute("errorMsg", errorMessage);
                    }
                } else {
                    model.addAttribute("errorMsg", "Please upload maximum of 5mb file");
                }
            } else {
                model.addAttribute("errorMsg", "No privilage to create ticket");
            }
        } catch (Exception ex) {
            model.addAttribute("errorMsg", "Failed to create ticket");
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Case newcase = new Case();
        newcase.setPriorityId(CCL_CODE_CASE_PRIORITY_DEFAULT);
        model.addAttribute("userCase", newcase);
        model.put("MAP", "CNCP");
        return new ModelAndView("case/casecreate", "command", model);
    }

    @RequestMapping(value = "/case/downloadattachment", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<byte[]> getAttachment(@ModelAttribute("caseAttachment") Case usercase, HttpServletResponse response) throws Exception {
        ResponseEntity<byte[]> outPutFile = null;
        HttpHeaders headers = new HttpHeaders();
        byte[] outputFile = null;
        String absolutePath = new File("").getAbsolutePath();
        String fileLocation = absolutePath + File.separator + usercase.getFile_location();
        File file = new File(fileLocation);
        if (file.exists()) {
            outputFile = Files.readAllBytes(file.toPath());
            System.out.println(outputFile.length);
        }
        headers.setContentType(MediaType.valueOf(Files.probeContentType(file.toPath())));
        String filename = usercase.getFilename();
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        outPutFile = new ResponseEntity<>(outputFile, headers, HttpStatus.OK);
        response.addCookie(new Cookie("fileDownloadToken", usercase.getDownload_token_value_id()));
        return outPutFile;
    }

    @RequestMapping(value = "/case/removeattachment", method = RequestMethod.POST)
    public @ResponseBody
    String removeAttachment(@ModelAttribute("caseAttachment") Case usercases, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = "";
        JSONObject jSONObject = new JSONObject();
        String casedata = request.getParameter("case");
        Case usercase = (Case) new ObjectMapper().readValue(casedata, Case.class);
        String absolutePath = new File("").getAbsolutePath();
        String fileLocation = absolutePath + File.separator + usercase.getFile_location();
        File file = new File(fileLocation);
        try {
            if (file.isDirectory()) {
                //directory is empty, then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                } else {
                    //list all the directory contents
                    String files[] = file.list();
                    for (String temp : files) {
                        //construct the file structure
                        File fileDelete = new File(file, temp);
                        //recursive delete
//                        delete(fileDelete);
                        fileDelete.delete();
                    }
                    //check the directory again, if empty then delete it
                    if (file.list().length == 0) {
                        file.delete();
                        System.out.println("Directory is deleted : "
                                + file.getAbsolutePath());
                    }
                }
            } else {
                //if file, then delete it
                file.delete();
                System.out.println("File is deleted : " + file.getAbsolutePath());
                jSONObject.put("CODE", "SUCCESS");
            }
            Case caseDetails = new Case();
            caseDetails.setFilename(null);
            caseDetails.setFile_location(null);
            caseDetails.setCaseId(String.valueOf(usercase.getCaseId()));
            caseDaoImpl.insertCaseFilelocation(caseDetails);
        } catch (Exception e) {
            jSONObject.put("CODE", "ERROR");
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, e);
        }

        return jSONObject.toString();
    }

    @RequestMapping(value = "case/getaccounts", method = RequestMethod.POST)
    public @ResponseBody
    String getCustomerAccounts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String customerId = request.getParameter("cus_id");
        System.out.println("customer id" + customerId);
        Account account = null;
        CustomerFullProfileSearhDataBean dataBean = null;
        JSONObject jSONObject = new JSONObject();
        String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_SAVINGS_READ_PROXY_LK);
        try {
            if (customerId != null && !customerId.contentEquals("")) {
                account = new Account();
                account.setParameter_value(customerId);
                CustomerData customerData = new CCLServiceClient().getCustomerFullProfileSearch(account.getParameter_value(), endpoint);
                dataBean = new CustomerFullProfileSearhDataBean();
                jSONObject.put("STAKEHOLDER_ID_PK", customerData.getResponse().getSTAKEHOLDERIDPK());
                jSONObject.put("CUSTOMER_CODE", customerData.getResponse().getCUSTOMERCODE());
                jSONObject.put("TITLE", customerData.getResponse().getTITLE());
                jSONObject.put("INITIALS", customerData.getResponse().getINITIALS());
                jSONObject.put("NAME_IN_FULL", customerData.getResponse().getNAMEINFULL());
                jSONObject.put("DATE_OF_BIRTH", customerData.getResponse().getDATEOFBIRTH());
                dataBean.setGender(customerData.getResponse().getGENDER());
                jSONObject.put("GENDER", dataBean.getGender_description());
                dataBean.setMarital_status(customerData.getResponse().getMARITALSTATUS());
                jSONObject.put("MARITAL_STATUS", dataBean.getMarital_status_description());
            }
        } catch (Exception exception) {
            System.out.println("Error Message : " + exception.getMessage());
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, exception);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/case/update", method = RequestMethod.GET)
    public String caseUpdated(@ModelAttribute("caseupdated") CaseHistory data,
            @RequestParam(value = "caseid", required = false) String caseid, ModelMap model, HttpSession session) {
        String name = (String) session.getAttribute("username");
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        Map<String, String> caseCategoryList = new LinkedHashMap<>();
        caseCategoryList.put("", "-- Select --");
        caseCategoryList.put("BR", "Branch");
        caseCategoryList.put("MF", "Micro Finance");

        Map<String, String> statusList;
        Case casebean = new Case();
        try {
            this.setCreateViewComponenets(model);
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CASE_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            Case usercase = checkPrivilage_btn(casebean, String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_CASE_SUBSECTION_ID), objList);
            model.put("avn_update", usercase.isEdit_btn());
            if (privilage) {
                Case caseDetails = null;
                List<String> casecount = caseDaoImpl.getCaseHistoryCountByCaseid(caseid);
                if (casecount.get(0).equalsIgnoreCase("0")) {
                    caseDetails = caseDaoImpl.getCaseByCaseId(caseid);
                    CaseHistory lastupdatcasehitory = new CaseHistory();
                    lastupdatcasehitory.setHstatusid(caseDetails.getStatusid());
                    lastupdatcasehitory.setHbranchid(caseDetails.getBranchcode());
                    lastupdatcasehitory.setHbrmf(caseDetails.getCaseCategory());
                    lastupdatcasehitory.setHcomment(caseDetails.getDescription());
                    lastupdatcasehitory.setHempid(caseDetails.getEmployeeID());
                    lastupdatcasehitory.setHempname(caseDetails.getAssignee1Name());
                    model.addAttribute("caseupdated", lastupdatcasehitory);

                } else {
                    caseDetails = caseDaoImpl.getCaseByCaseId(caseid);
                    CaseHistory lastupdatcasehitory = caseDaoImpl.getlastUpdateCaseHistory(casecount.get(1));
                    model.addAttribute("caseupdated", lastupdatcasehitory);
                }

                if (caseDetails.getUser().equalsIgnoreCase(name)) {
                    statusList = statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CASE);
                } else {

                    List<Integer> notincategories = new ArrayList<>();
                    notincategories.add(MasterDataVarList.CCL_CODE_STATUS_CLOSE);
                    notincategories.add(MasterDataVarList.CCL_CODE_STATUS_REASSIGN);
                    statusList = statusDAOImpl.getStatusListWithIdsNotIn(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CASE, notincategories);
                }

                CallCenter objcallcenter = new CallCenter();
                if (caseDetails.getCaseCallLogId() != null) {
                    objcallcenter = callCenterDaoImpl.getCallByCallLogId(Integer.valueOf(caseDetails.getCaseCallLogId()));
                }

                model.put("caseCategoryList", caseCategoryList);
                model.put("caseDetails", caseDetails);
                model.put("statusList", statusList);
//                model.put("userList", userDAOImpl.getUserList());
                model.put("caseHistoryList", caseDaoImpl.getCaseHistory(caseid, name));
                model.put("objcallcenter", objcallcenter);

            } else {

            }
        } catch (Exception ex) {
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CSP");
        return "case/caseedit";
    }

    @RequestMapping(value = "/case/view", method = RequestMethod.GET)
    public String caseView(@ModelAttribute("caseAttachment") Case data,
            @RequestParam(value = "caseid", required = false) String caseid, Map<String, Object> model, HttpSession session) {
        String name = (String) session.getAttribute("username");
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CASE_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                Case caseDetails = null;
                List<String> casecount = caseDaoImpl.getCaseHistoryCountByCaseid(caseid);
                if (casecount.get(0).equalsIgnoreCase("0")) {
                    caseDetails = caseDaoImpl.getCaseByCaseId(caseid);
                } else {
                    caseDetails = caseDaoImpl.getCaseByCaseId(caseid);
                    CaseHistory lastupdatcasehitory = caseDaoImpl.getlastUpdateCaseHistory(casecount.get(1));
                    caseDetails.setHcasedate(lastupdatcasehitory.getHcasedate());
                    caseDetails.setHstatusid(lastupdatcasehitory.getHstatusid());
                    caseDetails.setHempname(lastupdatcasehitory.getHempname());
                    caseDetails.setHbranchid(lastupdatcasehitory.getHbranchid());
                    caseDetails.setHbrmf(lastupdatcasehitory.getHbrmf());
                    caseDetails.setHcomment(lastupdatcasehitory.getHcomment());
                }
                CallCenter objcallcenter = new CallCenter();
                if (caseDetails.getCaseCallLogId() != null) {
                    objcallcenter = callCenterDaoImpl.getCallByCallLogId(Integer.valueOf(caseDetails.getCaseCallLogId()));
                }

                model.put("caseDetails", caseDetails);
                model.put("statusList", statusDAOImpl.getStatusList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CASE));
//                model.put("userList", userDAOImpl.getUserList());
                model.put("caseHistoryList", caseDaoImpl.getCaseHistory(caseid, name));
                model.put("objcallcenter", objcallcenter);
                List<CaseUrl> urllist = caseDaoImpl.getListUrl(caseid);
                model.put("urllist", urllist);
            } else {
                model.put("errorMsg", "No privilage to view case.");
            }

        } catch (Exception ex) {
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CSP");
        return "case/caseeview";
    }

    @RequestMapping(value = "/case/updated", method = RequestMethod.POST)
    public String caseUpdated(@ModelAttribute("caseupdated") CaseHistory data, ModelMap model, HttpSession session) {
        String username = (String) session.getAttribute("username");

        Map<String, String> caseCategoryList = new LinkedHashMap<>();
        caseCategoryList.put("", "-- Select --");
        caseCategoryList.put("BR", "Branch");
        caseCategoryList.put("MF", "Micro Finance");
        model.put("caseCategoryList", caseCategoryList);
        try {
            this.setCreateViewComponenets(model);
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_CASE_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            Case caseDetails = caseDaoImpl.getCaseByCaseId(data.getCaseId());
            CallCenter objcallcenter = new CallCenter();
            if (caseDetails.getCaseCallLogId() != null) {
                objcallcenter = callCenterDaoImpl.getCallByCallLogId(Integer.valueOf(caseDetails.getCaseCallLogId()));
            }
            Map<String, String> statusList = null;
            if (caseDetails.getUser().equalsIgnoreCase(username)) {
                statusList = statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CASE);
            } else {
                List<Integer> notincategories = new ArrayList<>();
                notincategories.add(MasterDataVarList.CCL_CODE_STATUS_CLOSE);
                notincategories.add(MasterDataVarList.CCL_CODE_STATUS_REASSIGN);
                statusList = statusDAOImpl.getStatusListWithIdsNotIn(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CASE, notincategories);
            }
            model.put("caseDetails", caseDetails);
            model.put("objcallcenter", objcallcenter);
            model.put("statusList", statusList);
//            model.put("userList", userDAOImpl.getUserList());
            model.put("caseHistoryList", caseDaoImpl.getCaseHistory(data.getCaseId(), username));
            model.put("branchList", branchdaoimpl.getBranchIdDropdownList());
            if (privilage) {
                boolean filesizok = true;
                List<MultipartFile> files = data.getFiles();
                for (MultipartFile multipartFile : files) {
                    if (!(multipartFile.getSize() / 1024 <= maxFileSizeInKb)) {
                        filesizok = false;
                        break;
                    }
                }
                if (filesizok) {
                    caseDaoImpl.insertToCaseHistory(data, username);
                    model.put("caseHistoryList", caseDaoImpl.getCaseHistory(data.getCaseId(), username));
                    model.put("successMessage", "Ticket history added!");
                    audit.insertAuditTrace("Ticket Update page", "Update", "Case Update  ", data.getCaseId(), username);
                    model.put("caseDetails", caseDetails);

                    OutputStream outputStream = null;
                    String errorMessage = "";
                    try {
                        Date date = commonDAOImpl.getCurentTimesStamp();
                        String todayDirectory = Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd, date);
                        String time_format = Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date);
                        File file;
                        File case_attach_file = null;

//                            List<String> fileNames = new ArrayList<String>();
                        Integer loopid = 0;
                        for (MultipartFile multipartFile : files) {
                            if (multipartFile.getSize() > 0) {
                                String uploadFileLocation = context.getInitParameter(CommonVarList.CONTEXT_PARAM_CASE_ATTACH_FILE_UPLOAD);
                                uploadFileLocation += File.separator + username + File.separator + todayDirectory + File.separator + data.getCaseId();
                                Case casedata = new Case();
                                casedata.setFile_location(uploadFileLocation);
//                                String fileName = multipartFile.getOriginalFilename();
//                                    fileNames.add(fileName);
                                file = new File(uploadFileLocation);
                                if (!file.exists()) {
                                    file.mkdirs();
                                }
                                String case_attach_file_name = "CASE_" + data.getCaseId() + time_format + loopid + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                                uploadFileLocation += File.separator + case_attach_file_name;
                                System.out.println(uploadFileLocation);
                                case_attach_file = new File(uploadFileLocation);
                                casedata.setFilename(case_attach_file_name);
                                casedata.setCaseId(String.valueOf(data.getCaseId()));
                                caseDaoImpl.insertCaseUrl(uploadFileLocation, multipartFile.getOriginalFilename(), Long.valueOf(data.getCaseId()), username);
                                //Handle file content - multipartFile.getInputStream()
                                //Handle file content - multipartFile.getInputStream()
                                outputStream = new FileOutputStream(case_attach_file);
                                outputStream.write(multipartFile.getBytes());
                                case_attach_file_name = null;
                                System.out.println("case_attach_file_name" + case_attach_file_name);
                                loopid++;
                            }

                        }
                    } catch (Exception e) {
                        errorMessage = "Failed to upload attachment";
                        Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, e);
                    } finally {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Exception e) {
                            }
                        }
                    }

                    if (Integer.valueOf(data.getHstatusid()) == MasterDataVarList.CCL_CODE_STATUS_REASSIGN
                            && !caseDetails.getBranchcode().equalsIgnoreCase(data.getHbranchid())) {
                        long employeeid = caseDaoImpl.getCaseAssignedEmployeeId(Long.valueOf(data.getCaseId()));
                        String cusName = Common.getFirstCharcterCapital(caseDetails.getCustomername());
                        try {
                            NotificationTemplate notificationTemplate = notifictionTemplateDAOImpl.getNotificationTemplate(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_CASE_REASSIGNEE);
                            String defaultnotifiperson = organizationalParametersDAOImpl.getDefaultCCPerson();
                            String[] ccemaillist = {employeeDAOImpl.getEmployeeEmailByUsername(username), defaultnotifiperson};
                            SimpleMailMessage sm = new SimpleMailMessage();
                            sm.setFrom(CommonVarList.NOTIFICATION_MAIL_FROM);
                            sm.setTo(employeeDAOImpl.getEmployeeEmailByEmployeeId(employeeid));
                            String assigneeName = Common.getFirstCharcterCapital(data.getAssigneeId1());
                            String subject = String.format(notificationTemplate.getSubject(), Common.getFirstCharcterCapital(caseDetails.getTitle()), cusName, data.getCaseId());
                            sm.setSubject(subject);

                            Branch branch = branchdaoimpl.getBranch(Integer.valueOf(data.getBranchcode()));
                            String mailContent = String.format(
                                    notificationTemplate.getReciepent(),
                                    assigneeName) + "\n\n\n"
                                    + String.format(
                                            notificationTemplate.getMailContent(),
                                            cusName,
                                            caseDetails.getPhonenumber(),
                                            branch.getAliasName(),
                                            caseDetails.getNic(),
                                            data.getResolutionDescription()) + "\n\n\n"
                                    + notificationTemplate.getConclution() + "\n\n"
                                    + notificationTemplate.getConcludingName();
                            sm.setText(mailContent);
                            sm.setCc(ccemaillist);
                            NotificationMail notification = new NotificationMail();
                            notification.setSimpleMailMessage(sm);
                            notification.setResources(null);
                            notification.setCaseid(data.getCaseId());
                            notification.setComponent("Case");
                            notification.setCreatedUser(username);
                            notification.setNotificationTemplateID(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_CASE_REASSIGNEE);
                            notificationDAOImpl.sendMail(notification);
                        } catch (Exception e) {
                            if (errorMessage.isEmpty()) {
                                errorMessage = "Their is an Error while sending mail please contact Administrator";
                            } else {
                                errorMessage += ("<br>" + "Their is an Error while sending mail please contact Administrator");
                            }
                            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, e);
                        }

                        try {
                            NotificationTemplate notificationTemplateSMS = notifictionTemplateDAOImpl.getNotificationTemplate(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_SMS);
                            Employee employeeDetail = employeeDAOImpl.getEmployeeDetailsByEmployeeId(employeeid);
                            String phonenumber = "";
                            String number = "";
                            String smsContent = "";
                            if (employeeDetail.getContactmobile() != null && StringUtils.isNumeric(employeeDetail.getContactmobile())) {
                                if (employeeDetail.getContactmobile().length() < 10) {
                                    number = employeeDetail.getContactmobile();
                                    number = number.substring(0, 0) + "0" + number.substring(0, number.length());
                                    phonenumber = number;
                                } else if (employeeDetail.getContactmobile().length() > 10) {
                                    number = number.substring(0, 0) + "0" + number.substring(3, number.length());
                                    phonenumber = number;
                                } else {
                                    phonenumber = employeeDetail.getContactmobile();
                                }
                                smsContent = String.format(notificationTemplateSMS.getMailContent(), String.valueOf(data.getCaseId()), cusName, phonenumber);
                                if (smsContent.length() > 160) {
                                    int numdigits = (smsContent.length() - 160);
                                    smsContent = String.format(notificationTemplateSMS.getMailContent(), String.valueOf(data.getCaseId()), cusName.substring(0, numdigits), phonenumber);
                                }
                                NotificationSMS notificationsms = new NotificationSMS();
                                notificationsms.setRecipient(phonenumber);
                                notificationsms.setTemplateid(MasterDataVarList.CCL_CODE_NOTIFICATION_TEMPLATE_SMS);
                                notificationsms.setMessagebody(smsContent);
                                notificationsms.setComponentid(Long.valueOf(data.getCaseId()));
                                notificationsms.setComponent("Case");
                                notificationsms.setCclsmsrout(MasterDataVarList.CCL_CODE_NOTIFICATION_COMPONENT_CCLSMSROUT);
                                notificationsms.setCclgroupid(MasterDataVarList.CCL_CODE_NOTIFICATION_COMPONENT_CCLGROUPID);
                                notificationsms.setCclsmspriority(1);
                                notificationsms.setSendtime((Timestamp) commonDAOImpl.getCurentTimesStamp());
                                notificationsms.setCreatedUser(username);
                                notificationDAOImpl.sendSMS(notificationsms);
                            }
                        } catch (Exception e) {
                            if (errorMessage.isEmpty()) {
                                errorMessage = "Their is an Error while sending sms please contact Administrator";
                            } else {
                                errorMessage += ("<br>" + "Their is an Error while sending sms please contact Administrator");
                            }
                            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }

                    if (!errorMessage.isEmpty()) {
                        model.addAttribute("errorMessage", errorMessage);
                    }
                } else {
                    model.addAttribute("errorMessage", "Please upload maximum of 10mb Signature file");
                }
            } else {
                model.put("errorMessage", "No privilage to adding a case history");
            }
        } catch (Exception ex) {
            model.put("errorMessage", "An error occured while adding a case history");
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CSP");
        return "/case/caseedit";
    }

    @RequestMapping(value = "case/createcalllog", method = RequestMethod.GET)
    public ModelAndView caseCreatecallog(@ModelAttribute("userCase") Case callBean,
            @RequestParam(value = "callLogId", required = false) String callLogId, ModelMap model) {
        model.put("MAP", "CNCP");
        try {
            model.put("callLogId", callLogId);
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView("case/casecreate", "command", model);
    }

    @RequestMapping(value = "/case/assignee1", method = RequestMethod.GET)
    public @ResponseBody
    String getAssignee1List(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String values = request.getParameter("case");
        JSONObject jSONObject = new JSONObject(values);
        return employeeDAOImpl.getEmployeeFirstNameById(jSONObject.getString("branchcode"), jSONObject.getString("caseCategory"));
    }

    @RequestMapping(value = "/case/employeeid", method = RequestMethod.GET)
    public @ResponseBody
    String getEmployeeId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String values = request.getParameter("case");
        JSONObject jSONObject = new JSONObject(values);
        return employeeDAOImpl.getEmployeeIdByBranchId(jSONObject.getString("branchcode"), jSONObject.getString("caseCategory"));
    }

    @RequestMapping(value = "/case/searched", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        Case cases = new Case();
        Case viewcasae = checkPrivilage_btn(cases, String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CASE_SUBSECTION_ID), objList);
        Case editcasae = checkPrivilage_btn(cases, String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_CASE_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
        if (viewcasae.isView_btn()) {
            ViewStatus = "disabled";
        } else {
            ViewStatus = "active";
        }
        if (editcasae.isEdit_btn()) {
            UpdateStatus = "disabled";
        } else {
            UpdateStatus = "active";
        }

        List<Case> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CASE_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CASAE_SEARCH_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }

                if (Boolean.valueOf(request.getParameter("search"))) {
                    Case parameters = this.getParameters(request);

                    iTotalRecords = caseDaoImpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = caseDaoImpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (Case data : list) {
                            JSONObject object = new JSONObject();
                            object.put("caseId", data.getCaseId());
                            object.put("caseTypeDes", data.getCaseTypeDes());
                            object.put("priorityDes", data.getPriorityDes());
                            object.put("branchDesc", data.getBranchDesc());
                            object.put("productDes", data.getProductDes());
                            object.put("departmentDes", data.getDepartmentDes());
                            object.put("assigneeId1", data.getAssigneeId1());
                            object.put("casecreateduser", data.getCasecreateduser());
                            object.put("statusDes", data.getStatusDes());
                            object.put("customername", data.getCustomername());
                            object.put("phone", data.getPhonenumber());
                            object.put("description", data.getDescription());
                            object.put("createddatetime", data.getCreatedDateTimeString());
                            String action = "<div class=\"row\"> "
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/case/view?caseid=" + data.getCaseId() + "'class=" + ViewStatus + "  ><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/case/update?caseid=" + data.getCaseId() + "' class=" + UpdateStatus + "><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                        audit.insertAuditTrace("Case search page ", "Search ", " Case search ", parameters.getInput(), (String) session.getAttribute("username"));
                    }
                }
            } else {

            }

        } catch (Exception e) {
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, e);
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private Case getParameters(HttpServletRequest request) throws Exception {
        Case parameters = new Case();

        if (request.getParameter("searchoption") != null && !request.getParameter("searchoption").isEmpty()) {
            parameters.setSearchoption(request.getParameter("searchoption"));
        }
        if (request.getParameter("input") != null && !request.getParameter("input").isEmpty()) {
            parameters.setInput(request.getParameter("input"));
        }

        if (request.getParameter("from_date") != null && !request.getParameter("from_date").isEmpty()) {
            parameters.setFrom_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("from_date")));
        }
        if (request.getParameter("to_date") != null && !request.getParameter("to_date").isEmpty()) {
            parameters.setTo_date(Common.getDateFromString(CommonVarList.DATE_FORMAT_yyyy_MM_dd, request.getParameter("to_date")));
        }
        if (request.getParameter("firstName") != null && !request.getParameter("firstName").isEmpty()) {
            parameters.setFirstName(request.getParameter("firstName"));
        }
        if (request.getParameter("telephone") != null && !request.getParameter("telephone").isEmpty()) {
            parameters.setTelephone(request.getParameter("telephone"));
        }

        return parameters;
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        model.addAttribute("caseTypeList", caseTypeDAOImpl.getCaseTypeDropdownList());
        model.addAttribute("casePriorityList", casePriorityDAOImpl.getCasePriorityDropdownList());
        model.addAttribute("departmentList", departmentDAOImpl.getDepartmentDropdownList());
        model.addAttribute("productList", productDAOImpl.getProductDropdownList());
        model.addAttribute("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CASE));
        model.addAttribute("userList", userDAOImpl.getUserDropdownList());
        model.addAttribute("branchList", branchdaoimpl.getBranchIdDropdownList());
        model.addAttribute("titleList", titleDAOImpl.getTitleDropdownList());
        model.addAttribute("assigneeList", caseDaoImpl.getAssigneList());
        Map<String, String> caseCategoryList = new LinkedHashMap<>();
        caseCategoryList.put("", "-- Select --");
        caseCategoryList.put("BR", "Branch");
        caseCategoryList.put("MF", "Micro Finance");
        model.addAttribute("caseCategoryList", caseCategoryList);
    }

    @RequestMapping(value = "/case/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> caseDownload(@ModelAttribute("caseupdated") CaseHistory data,
            @RequestParam(value = "", required = false) String caseid,
            @RequestParam(value = "fileid", required = false) String fileid,
            @RequestParam(value = "downloadall", required = false) String downloadall,
            ModelMap model, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        String name = (String) session.getAttribute("username");
        ResponseEntity<byte[]> outPutFile = null;
        ByteArrayOutputStream outputStreamss = null;
        HttpHeaders headers = new HttpHeaders();
        byte[] outputFile = null;
        String filename = null;
        Date date = new Date();
        try {
            String absolutePath = new File("").getAbsolutePath();
            if (downloadall.contentEquals("true")) {
                List<CaseUrl> filelist = caseDaoImpl.getListUrl(caseid);
                filename = "CASE_" + caseid + ".zip";
                List<File> listFiles = new ArrayList<>();
                for (CaseUrl caseUrl : filelist) {
                    File sourceFile = new File(absolutePath + File.separator + caseUrl.getFilepath());
                    if (sourceFile.exists()) {
                        File destinationFile = new File(date.getTime() + File.separator + caseUrl.getFilename());
                        FileUtils.copyFile(sourceFile, destinationFile);
                        listFiles.add(destinationFile);
                    }
                }
                outputStreamss = Common.zipFiles(listFiles);
                outputFile = outputStreamss.toByteArray();
            } else {
                CaseUrl filelist = caseDaoImpl.getCaseUrl(fileid, caseid);
                File file = new File(absolutePath + File.separator + filelist.getFilepath());
                filename = filelist.getFilename();
                outputFile = Files.readAllBytes(file.toPath());
            }
            headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//            ResponseEntity<byte[]> respons = new ResponseEntity<>(outputStreamss.toByteArray(), headers, HttpStatus.OK);
//            response.addCookie(new Cookie("fileDownloadToken", daysummary.getDownload_token_value_id()));
//            System.out.println("Cooki" + daysummary.getDownload_token_value_id());
            outPutFile = new ResponseEntity<>(outputFile, headers, HttpStatus.OK);
            File file = new File(String.valueOf(date.getTime()));
            if (file.exists()) {
                for (File f : file.listFiles()) {
                    f.delete();
                }
                FileUtils.deleteDirectory(file);
            }
        } catch (Exception ex) {
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outPutFile;
    }

    @RequestMapping(value = "/case/removeattchment", method = RequestMethod.GET)
    public @ResponseBody
    String removeAttachment(
            @RequestParam(value = "caseid", required = false) String caseid,
            @RequestParam(value = "fileid", required = false) String fileid,
            ModelMap model, HttpSession session) {
        JSONObject jSONObject = new JSONObject();
        try {
            String absolutePath = new File("").getAbsolutePath();
            CaseUrl filelist = caseDaoImpl.getCaseUrl(fileid, caseid);
            File file = new File(absolutePath + File.separator + filelist.getFilepath());
            file.delete();
            caseDaoImpl.updateAttachment(fileid);
            jSONObject.put("CODE", "SUCCESS");
        } catch (Exception ex) {
            jSONObject.put("CODE", "ERROR");
            Logger.getLogger(CaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jSONObject.toString();
    }

    private Case checkPrivilage_btn(Case usercase, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        usercase = new Case();
        usercase.setSave_btn(true);
        usercase.setSearch_btn(true);
        usercase.setEdit_btn(true);
        usercase.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                usercase.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                usercase.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                usercase.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                usercase.setView_btn(false);
            }
        }
        return usercase;
    }
}

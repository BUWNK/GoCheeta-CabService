/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.login;

import com.avn.ccl.bean.serviceclient.userdetail.UserDetail;
import com.avn.ccl.model.callcenter.CallCenter;
import com.avn.ccl.daoimpl.activeusername.ActiveUserDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.callcenter.CallCenterDAOImpl;
import com.avn.ccl.daoimpl.casemgt.CaseDAOImpl;
import com.avn.ccl.dao.dashboard.DashboardDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.employee.EmployeeDAOImpl;
import com.avn.ccl.daoimpl.login.LogingDAOImpl;
import com.avn.ccl.daoimpl.logoutresons.LogoutReasonsDAOImpl;
import com.avn.ccl.daoimpl.section.SectionDAOImpl;
import com.avn.ccl.daoimpl.subsection.SubsectionDAOImpl;
import com.avn.ccl.daoimpl.user.UserDAOImpl;
import com.avn.ccl.model.casemgt.Case;
import com.avn.ccl.model.dashboard.DashBordChart;
import com.avn.ccl.model.employee.Employee;
import com.avn.ccl.model.section.Section;
import com.avn.ccl.model.user.User;
import com.avn.ccl.service.webservicecilent.CCLServiceClient;
import com.avn.ccl.util.ApplicationUtil;
import static com.avn.ccl.util.Common.getStringFormatDate;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.axis2.AxisFault;
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
import org.springframework.web.servlet.ModelAndView;
import org.wso2.carbon.authenticator.stub.AuthenticationAdminStub;
import org.wso2.carbon.authenticator.stub.LoginAuthenticationExceptionException;

/**
 *
 * @author Administrator
 */
@Controller
public class LoginController {

    @Autowired
    ServletContext context;
    @Autowired
    CaseDAOImpl caseDAOImpl;
    @Autowired
    AuditTraceDAOImpl auditTraceDAOImpl;
    @Autowired
    CallCenterDAOImpl callcenterdaoimpl;
    @Autowired
    DashboardDAOImpl chartdaoimple;
    @Autowired
    ActiveUserDAOImpl activeuser;
    @Autowired
    LogoutReasonsDAOImpl logoutresonnsdaoimpl;
    @Autowired
    LogingDAOImpl loginDAOImpl;
    @Autowired
    CommonDAOImpl commonDAOImpl;
    @Autowired
    EmployeeDAOImpl employeeDAOImpl;
    @Autowired
    UserDAOImpl userDAOImpl;
    @Autowired
    SectionDAOImpl sectionDAOImpl;
    @Autowired
    SubsectionDAOImpl subsectionDAOImpl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String logOut(
            @RequestParam(value = "auth", required = false) String auth, ModelMap model) {
        String version = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SOFTWARE_VERSION);
        model.addAttribute("version", version);
        try {
//            if (auth != null && auth.equalsIgnoreCase("fail")) {
//                String userName = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//                session.invalidate();
//                if (Integer.valueOf(userDAOImpl.getUser(userName).getStatus()) != MasterDataVarList.CCL_CODE_STATUS_INACTIVE) {
//                    if (userDAOImpl.getUserAttempts(userName) < commonDAOImpl.getMaxinvaidLoginCount()) {
//                        userDAOImpl.updateUserAttempte(userName);
//                        int remainattempt = commonDAOImpl.getMaxinvaidLoginCount() - userDAOImpl.getUserAttempts(userName);
//                        model.addAttribute("error", "Invalid username and password! Remaining Attempts " + remainattempt + "");
//                    } else {
//                        model.addAttribute("error", "Unable to sign in. Your account has been locked. Please contact admin!");
//                    }
//                } else {
//                    model.addAttribute("error", "Unable to sign in. Your account has been Inactive. Please contact admin!");
//                }
//            } else 

            if (auth != null && auth.equalsIgnoreCase("logout")) {
                model.addAttribute("msg", "You've been logged out successfully.");
            } else if (auth != null && auth.equalsIgnoreCase("sessiontimeout")) {
                model.addAttribute("info", "Session Timeout!");
            }
        } catch (Exception exception) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("loginForm") User loginuser, ModelMap model, HttpSession session) {
        String userName = loginuser.getUsername();
        ModelAndView modelAndView = null;
        String version = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SOFTWARE_VERSION);
        try {
            session.setAttribute("username", loginuser.getUsername());
            return new ModelAndView("redirect:/home");
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return modelAndView;
    }
    
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView login(@ModelAttribute("loginForm") User loginuser, ModelMap model, HttpSession session) {
//        String userName = loginuser.getUsername();
//        ModelAndView modelAndView = null;
//        String version = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SOFTWARE_VERSION);
//        try {
//            String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_ADMIN_MANAGEMENT_READ_DSLK);
//            UserDetail userDetails = new CCLServiceClient().getUserDetails(endpoint, userName);
//            if (userDetails.getResponse() != null) {
//                if (this.isLDAPLoginPass(userName, loginuser.getPassword())) {
//                    session.setAttribute("username", loginuser.getUsername());
//                    if (loginDAOImpl.checkUserAlreadyExists(userName) > 0) {
//                        if (Integer.valueOf(userDAOImpl.getUser(userName).getStatus()) != MasterDataVarList.CCL_CODE_STATUS_INACTIVE) {
//                            if (userDAOImpl.getUserAttempts(userName) < commonDAOImpl.getMaxinvaidLoginCount()) {
//                                userDAOImpl.reSetUserAttempts(userName);
//                                Employee employee = new Employee();
//                                employee.setNic(userDetails.getResponse().getNIC());
//                                employee.setContactmobile(userDetails.getResponse().getOFFICEMOBILENO());
//                                employee.setBranchid(userDetails.getResponse().getLOCATIONIDFK());
//                                employee.setInitials(userDetails.getResponse().getINITIALS());
//                                employee.setFirstname(userDetails.getResponse().getFIRSTNAME());
//                                employee.setLastname(userDetails.getResponse().getLASTNAME());
//                                employee.setEmail(userName + CommonVarList.EMAIL_DOAMIN);
//                                employee.setEpf(userDetails.getResponse().getEPFNO());
//                                employee.setEmployeelevel(userDetails.getResponse().getGRADE());
//                                employeeDAOImpl.updateEmployeInLogin(employee, userName);
//                            } else {
//                                model.addAttribute("error", "Unable to sign in. Your account has been locked. Please contact admin!");
//                                model.addAttribute("version", version);
//                                return new ModelAndView("index");
//                            }
//                        } else {
//                            model.addAttribute("error", "Unable to sign in. Your account has been Inactive. Please contact admin!");
//                            model.addAttribute("version", version);
//                            return new ModelAndView("index");
//                        }
//                    } else {
//                        User systemuser = new User();
//                        systemuser.setUsername(userName);
//                        systemuser.setEmail(userName + CommonVarList.EMAIL_DOAMIN);
//                        systemuser.setUser_role(String.valueOf(MasterDataVarList.CCL_CODE_ROLE_USER));
//                        systemuser.setStatus(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE));
//                        Employee employee = new Employee();
//                        employee.setBranchid(userDetails.getResponse().getLOCATIONIDFK());
//                        employee.setInitials(userDetails.getResponse().getINITIALS());
//                        employee.setFirstname(userDetails.getResponse().getFIRSTNAME());
//                        employee.setLastname(userDetails.getResponse().getLASTNAME());
//                        employee.setEmail(userName + CommonVarList.EMAIL_DOAMIN);
//                        employee.setContactmobile(userDetails.getResponse().getOFFICEMOBILENO());
//                        employee.setStatus(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE));
//                        employee.setNic(userDetails.getResponse().getNIC());
//                        employee.setEpf(userDetails.getResponse().getEPFNO());
//                        employee.setEmployeelevel(userDetails.getResponse().getGRADE());
//                        if (!employeeDAOImpl.isEmployeeExists(userDetails.getResponse().getEPFNO())) {
//                            userDAOImpl.createUser(systemuser, employee);
//                        } else {
//                            systemuser.setEmpid(employeeDAOImpl.getEmployeeIdByEPF(userDetails.getResponse().getEPFNO()));
//                            userDAOImpl.createUser(systemuser);
//                            employeeDAOImpl.updateEmployeInLogin(employee, userName);
//                        }
//                    }
//                    return new ModelAndView("redirect:/home");
//                } else {
//                    if (Integer.valueOf(userDAOImpl.getUser(userName).getStatus()) != MasterDataVarList.CCL_CODE_STATUS_INACTIVE) {
//                        if (userDAOImpl.getUserAttempts(userName) < commonDAOImpl.getMaxinvaidLoginCount()) {
//                            userDAOImpl.updateUserAttempte(userName);
//                            model.addAttribute("error", "Invalid username and password! Remaining Attempts " + (commonDAOImpl.getMaxinvaidLoginCount() - userDAOImpl.getUserAttempts(userName)) + "");
//                        } else {
//                            model.addAttribute("error", "Unable to sign in. Your account has been locked. Please contact admin!");
//                        }
//                    } else {
//                        model.addAttribute("error", "Unable to sign in. Your account has been Inactive. Please contact admin!");
//                    }
//                    model.addAttribute("version", version);
//                    modelAndView = new ModelAndView("index");
//                }
//            } else {
//                model.addAttribute("error", "User not found please try again!");
//                model.addAttribute("version", version);
//                return new ModelAndView("index");
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return modelAndView;
//    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(ModelMap model, HttpSession session) {
        String userName = (String) session.getAttribute("username");
        try {
            if (session.getAttribute("pagehierarchy") == null) {
                int userRole = userDAOImpl.getUserRoleByUsername(userName);
                List<Section> levelZeroSections = sectionDAOImpl.getSectionLevelZeroByUserrole(userRole);
                List<Section> lowLevelSections = sectionDAOImpl.getSectionLowLevelByUserrole(userRole);

                for (Section lowLevelSection : lowLevelSections) {
                    int parentSectionID = Integer.valueOf(lowLevelSection.getParentsection());
                    for (Section levelZeroSection : levelZeroSections) {
                        int sectionID = Integer.valueOf(levelZeroSection.getSectionid());
                        if (parentSectionID == sectionID) {
                            if (levelZeroSection.getChildsections() == null) {
                                List<Section> list = new ArrayList<>();
                                list.add(lowLevelSection);
                                levelZeroSection.setChildsections(list);
                            } else {
                                levelZeroSection.getChildsections().add(lowLevelSection);
                            }
                        }
                    }
                }

                for (Section section : levelZeroSections) {
                    this.setSubsetionsForChildSections(userRole, section.getChildsections());
                    section.setSubsections(subsectionDAOImpl.getSubSectionsByUserroleAndSectionID(userRole, section.getSectionid()));
                }

                session.setAttribute("pagehierarchy", levelZeroSections);
            }

            String lastlogin = null;
            try {
                if (session.getAttribute("lastlogin") == null) {
                    String logintime = getStringFormatDate(CommonVarList.DATE_FORMAT_dd_MMM_yy_hhmmssa, commonDAOImpl.getCurentTimesStamp());
                    System.out.println(logintime);
                    loginDAOImpl.updateLastLogin(userName, logintime);
                    lastlogin = getStringFormatDate(CommonVarList.DATE_FORMAT_dd_MMM_yy_hhmmssa, loginDAOImpl.getLastLoginDateTime(userName));
                    session.setAttribute("lastlogin", "Last Login " + lastlogin);
                    int userroleide = userDAOImpl.getUserRoleByUsername(userName);
                    session.setAttribute("userroleuid", userroleide);
                    session.setAttribute("userroletasklits", commonDAOImpl.getAssignedTaskList(userroleide));
                    session.setAttribute("assignchartlist", commonDAOImpl.getAssignedChartList(userroleide));
                    JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
                }
            } catch (NullPointerException e) {
                session.setAttribute("lastlogin", "First Time Login ");
            } catch (Exception e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            }

            auditTraceDAOImpl.insertAuditTrace(null, null, "Logged to the System", "", userName);
            try {
                JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
                model.addAttribute("CallVsCaseChart", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CALL_VS_CASECHART), assignchartlist));
                model.addAttribute("CallVsCaseChartID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CALL_VS_CASECHART), assignchartlist));
                model.addAttribute("CallVsCaseChartCSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CALL_VS_CASECHART), assignchartlist));

                model.addAttribute("MyCalls", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_MY_CALL), assignchartlist));
                model.addAttribute("MyCallsID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_MY_CALL), assignchartlist));
                model.addAttribute("MyCallsCSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_MY_CALL), assignchartlist));

                model.addAttribute("MyCase", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_MY_CASE), assignchartlist));
                model.addAttribute("MyCaseID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_MY_CASE), assignchartlist));
                model.addAttribute("MyCaseCSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_MY_CASE), assignchartlist));

                model.addAttribute("ProductWise", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_PRODUCT_WISE_INQUIRY), assignchartlist));
                model.addAttribute("ProductWiseID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_PRODUCT_WISE_INQUIRY), assignchartlist));
                model.addAttribute("ProductWiseCSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_PRODUCT_WISE_INQUIRY), assignchartlist));

                model.addAttribute("AssignCase", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_ASSIGN_CASE), assignchartlist));
                model.addAttribute("AssignCaseID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_ASSIGN_CASE), assignchartlist));
                model.addAttribute("AssignCaseCSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_ASSIGN_CASE), assignchartlist));

                model.addAttribute("TargetGraph1", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_BAR_CHART), assignchartlist));
                model.addAttribute("TargetGraph1ID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_BAR_CHART), assignchartlist));
                model.addAttribute("TargetGraph1CSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_BAR_CHART), assignchartlist));

                model.addAttribute("TargetGraph2", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_TARGET_GRAPH), assignchartlist));
                model.addAttribute("TargetGraph2ID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_TARGET_GRAPH), assignchartlist));
                model.addAttribute("TargetGraph2CSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_TARGET_GRAPH), assignchartlist));

                model.addAttribute("LeadContagGraph", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CONTACKLEAD), assignchartlist));
                model.addAttribute("LeadContagGraphID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CONTACKLEAD), assignchartlist));
                model.addAttribute("LeadContagGraphCSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_CONTACKLEAD), assignchartlist));

                model.addAttribute("LeadRationGraph", getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist));
                model.addAttribute("LeadRationID", getWedgetID(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist));
                model.addAttribute("LeadRationCSS", getWedgetCSS(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_LEADRATION), assignchartlist));

            } catch (Exception e) {
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "HP");
        return new ModelAndView("welcome");
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(
//            @RequestParam(value = "auth", required = false) String auth,
//            ModelMap model, HttpSession session) {
//        String version = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SOFTWARE_VERSION);
//        model.addAttribute("version", version);
//        try {
//            if (auth != null && auth.equalsIgnoreCase("fail")) {
//                String userName = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//                session.invalidate();
//                if (Integer.valueOf(userDAOImpl.getUser(userName).getStatus()) != MasterDataVarList.CCL_CODE_STATUS_INACTIVE) {
//                    if (userDAOImpl.getUserAttempts(userName) < commonDAOImpl.getMaxinvaidLoginCount()) {
//                        userDAOImpl.updateUserAttempte(userName);
//                        int remainattempt = commonDAOImpl.getMaxinvaidLoginCount() - userDAOImpl.getUserAttempts(userName);
//                        model.addAttribute("error", "Invalid username and password! Remaining Attempts " + remainattempt + "");
//                    } else {
//                        model.addAttribute("error", "Unable to sign in. Your account has been locked. Please contact admin!");
//                    }
//                } else {
//                    model.addAttribute("error", "Unable to sign in. Your account has been Inactive. Please contact admin!");
//                }
            /*} else*/
//            if (auth != null && auth.equalsIgnoreCase("logout")) {
//                model.addAttribute("msg", "You've been logged out successfully.");
//                session.invalidate();
//            } else if (auth != null && auth.equalsIgnoreCase("sessiontimeout")) {
//                model.addAttribute("info", "Session Timeout!");
//                session.invalidate();
//            }
//        } catch (Exception exception) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, exception);
//            exception.printStackTrace();
//        }
//        return "index";
//    }
    private boolean isLDAPLoginPass(String username, String password) {
        boolean isLDAPLoginPass = false;
        try {
            System.setProperty("javax.net.ssl.trustStore", getClass().getClassLoader().getResource("wso2carbon.jks").getFile());
            System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
            System.setProperty("javax.net.ssl.trustStoreType", "JKS");

            AuthenticationAdminStub stub = new AuthenticationAdminStub(ApplicationUtil.SERVLET_CONTEXT.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_LDAP_LOGIN));
            stub._getServiceClient().getOptions().setManageSession(true);
            isLDAPLoginPass = stub.login(username, password, ApplicationUtil.SERVLET_CONTEXT.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_LDAP_AUTHENTICATION_HOST));
            System.out.println("username : " + username);
            System.out.println("Login Status : " + isLDAPLoginPass);
        } catch (LoginAuthenticationExceptionException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AxisFault ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isLDAPLoginPass;
    }

    private void setSubsetionsForChildSections(int userRole, List<Section> childSections) throws Exception {
        if (childSections != null) {
            for (Section childSection : childSections) {
                childSection.setSubsections(subsectionDAOImpl.getSubSectionsByUserroleAndSectionID(userRole, childSection.getSectionid()));
                this.setSubsetionsForChildSections(userRole, childSection.getChildsections());
            }
        }
    }

    @RequestMapping(value = "/login/activuser", method = RequestMethod.GET)
    public @ResponseBody
    String getmodule(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        String name = (String) session.getAttribute("username");
        return activeuser.getUserFirstName(name);

    }

    @RequestMapping(value = "/login/getlogoutresons", method = RequestMethod.GET)
    public @ResponseBody
    List<String> loadLogoutResons(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            return logoutresonnsdaoimpl.getLogOutResons();
        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;

    }

    @RequestMapping(value = "/logout/logoutreson", method = RequestMethod.POST)
    public @ResponseBody
    boolean getlogoutReson(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String logoutreson = request.getParameter("logoutreson");
        try {
            auditTraceDAOImpl.insertAuditTrace(null, "Logout", logoutreson, null, (String) session.getAttribute("username"));
            return true;
        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    @RequestMapping(value = "/casetable", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");
        JSONObject jsonResponse = new JSONObject();
        if (getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_MY_CASE), assignchartlist)) {
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

            String columns[] = {"C.CASEID", "E.INITIALS", "CT.DESCRIPTION", "PR.DESCRIPTION", "C.NIC", "C.PHONENUMBER", "CP.DESCRIPTION", "ST.DESCRIPTION"};
            String user = (String) session.getAttribute("username");

            final DataTableRequestParam param = DataTableParamUtility.getParam(request);
            final String sEcho = param.sEcho;
            int iTotalRecords = 0; // total number of records (unfiltered)

            int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
            JSONArray rows = new JSONArray();
            try {
                String sort = "ORDER BY ";
                sort += columns[param.iSortCol[0]] + " ";

                if (param.sSortDir[0].equalsIgnoreCase(CommonVarList.DATA_TABLE_SORTING_ASC)) {
                    sort += CommonVarList.DATA_TABLE_SORTING_ASC + " ";
                } else {
                    sort += CommonVarList.DATA_TABLE_SORTING_DESC + " ";
                }

                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 5 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 5;
                }
                Case casebeen = caseDAOImpl.getUserRoleIdAndEmployeeId(user);
                iTotalRecords = caseDAOImpl.getTableDataCount(user, casebeen.getEmployeeId());
                List<Case> list = caseDAOImpl.getTableData(user, casebeen.getEmployeeId(), param.iDisplayLength, param.iDisplayStart, sort);
                iTotalDisplayRecords = iTotalRecords;
                if (iTotalRecords > 0) {
                    for (Case data : list) {
                        JSONObject object = new JSONObject();
                        object.put("caseid", data.getCaseId());
                        object.put("casetype", data.getCaseTypeDes());
                        object.put("priority", data.getPriorityDes());
                        object.put("assignee", data.getAssigneeId1());
                        object.put("module", data.getProductDes());
                        object.put("nic", data.getNic());
                        object.put("phone", data.getPhonenumber());
                        object.put("status", data.getStatusDes());
                        object.put("created", data.getCreated());
                        object.put("assigned", data.getAssignees());
                        String action = "<div class=\"row\">"
                                + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/case/view?caseid=" + data.getCaseId() + "'class=" + ViewStatus + " ><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/case/update?caseid=" + data.getCaseId() + "'class=" + UpdateStatus + " ><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                + "</div>";
                        object.put("action", action);

                        rows.put(object);
                    }

                }
            } catch (Exception e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
            }

            jsonResponse.put("sEcho", sEcho);
            jsonResponse.put("iTotalRecords", iTotalRecords);
            jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
            jsonResponse.put("aaData", rows);
        }
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/calltable", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData1(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        JSONArray assignchartlist = (JSONArray) session.getAttribute("assignchartlist");

        JSONObject jsonResponse = new JSONObject();
        if (getAssignChart(String.valueOf(MasterDataVarList.CCL_CODE_DASHBORD_MY_CALL), assignchartlist)) {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            CallCenter callcenter = new CallCenter();
            CallCenter callcenterprvive = checkPrivilage_btn(callcenter, String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CALL_CENTER_SUBSECTION_ID), objList);
            CallCenter callcenterprvedit = checkPrivilage_btn(callcenter, String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_CALL_CENTER_SUBSECTION_ID), objList);
            String ViewStatus = "";
            String UpdateStatus = "";
            if (callcenterprvive.isView_btn()) {
                ViewStatus = "disabled";
            } else {
                ViewStatus = "active";
            }
            if (callcenterprvedit.isEdit_btn()) {
                UpdateStatus = "disabled";
            } else {
                UpdateStatus = "active";
            }

            String columns[] = {"C.CALLLOGID", "C.PHONENUMBER", "CD.DESCRIPTION", "FA.DESCRIPTION"};
            String user = (String) session.getAttribute("username");

            List<CallCenter> list;
            final DataTableRequestParam param = DataTableParamUtility.getParam(request);
            final String sEcho = param.sEcho;
            int iTotalRecords = 0; // total number of records (unfiltered)

            int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
            JSONArray rows = new JSONArray();
            try {

                String sort = "ORDER BY ";
                sort += columns[param.iSortCol[0]] + " ";

                if (param.sSortDir[0].equalsIgnoreCase(CommonVarList.DATA_TABLE_SORTING_ASC)) {
                    sort += CommonVarList.DATA_TABLE_SORTING_ASC + " ";
                } else {
                    sort += CommonVarList.DATA_TABLE_SORTING_DESC + " ";
                }

                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 5 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 5;
                }
                iTotalRecords = callcenterdaoimpl.getTableDataCount(user);
                iTotalDisplayRecords = iTotalRecords;
                if (iTotalRecords > 0) {
                    list = callcenterdaoimpl.getTableData(user, param.iDisplayLength, param.iDisplayStart, sort);
                    for (CallCenter data : list) {
                        JSONObject object = new JSONObject();
                        object.put("callLogId", data.getCallLogId());
                        object.put("name_in_full", data.getName_in_full());
                        object.put("productIdDes", data.getProductIdDes());
                        object.put("telephone", data.getTelephone());
                        object.put("callDirectionDes", data.getCallDirectionDes());
                        object.put("followUpActionDes", data.getFollowUpActionDes());
                        String action = "<div class=\"row\">"
                                + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/call/view?callid=" + data.getCallLogId() + "'class=" + ViewStatus + "><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/calllogedit?callid=" + data.getCallLogId() + "'class=" + UpdateStatus + "><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                + "</div>";
                        object.put("action", action);
                        rows.put(object);
                    }

                }
            } catch (Exception e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
            }

            jsonResponse.put("sEcho", sEcho);
            jsonResponse.put("iTotalRecords", iTotalRecords);
            jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
            jsonResponse.put("aaData", rows);

        }
        return jsonResponse.toString();
    }

    public boolean checkPrivilage(String sectionid, int task, HttpSession session) throws Exception {
        String userName = (String) session.getAttribute("username");
        int userroleide = userDAOImpl.getUserRoleByUsername(userName);
        JSONArray objList = loginDAOImpl.getAssignedTaskList(userroleide);
        boolean privilage = false;
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("taskid").contentEquals(String.valueOf(task))) {
                privilage = true;
                break;
            } else {
                privilage = false;
            }
        }
        return privilage;
    }

    private CallCenter checkPrivilage_btn(CallCenter callcenter, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        callcenter = new CallCenter();
        callcenter.setSave_btn(true);
        callcenter.setSearch_btn(true);
        callcenter.setEdit_btn(true);
        callcenter.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                callcenter.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                callcenter.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                callcenter.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                callcenter.setView_btn(false);
            }
        }
        return callcenter;
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

    private boolean getAssignChart(String wedgetid, JSONArray objList) throws Exception {
        boolean privilage = false;
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                privilage = true;
                break;
            }
        }

        return privilage;
    }

    private String getWedgetID(String wedgetid, JSONArray objList) throws Exception {
        String ID = "";
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                ID = yObject.get("sortid").toString();
                break;
            }
        }

        return ID;
    }

    private String getWedgetCSS(String wedgetid, JSONArray objList) throws Exception {
        String css = "";
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                css = yObject.get("css").toString();
                break;
            }
        }

        return css;
    }

    private DashBordChart getAssignChartAvaiable(String wedgetid, JSONArray objList) throws Exception {
        DashBordChart dashbordchart = new DashBordChart();
        dashbordchart.setCallvscase(false);
        dashbordchart.setAssigncase(false);
        dashbordchart.setBarchart(false);
        dashbordchart.setProductwiseinquiry(false);
        dashbordchart.setMycall(false);
        dashbordchart.setMycase(false);
        dashbordchart.setTragetgraph(false);

        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                dashbordchart.setCallvscase(true);
            } else if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                dashbordchart.setAssigncase(true);
            } else if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                dashbordchart.setBarchart(true);
            } else if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                dashbordchart.setProductwiseinquiry(true);
            } else if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                dashbordchart.setMycall(true);
            } else if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                dashbordchart.setMycase(true);
            } else if (yObject.getString("wedgetid").contentEquals(wedgetid) && yObject.getString("statusid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_ACTIVE))) {
                dashbordchart.setTragetgraph(true);
            }
        }

        return dashbordchart;
    }
}

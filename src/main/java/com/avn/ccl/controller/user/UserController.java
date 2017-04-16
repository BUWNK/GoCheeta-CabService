/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.user;

import com.avn.ccl.bean.serviceclient.userdetail.UserDetail;
import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.employee.EmployeeDAOImpl;
import com.avn.ccl.daoimpl.employeecategory.EmployeeCategoryDAOImpl;
import com.avn.ccl.daoimpl.user.UserDAOImpl;
import com.avn.ccl.daoimpl.userrole.UserRoleDAOImpl;
import com.avn.ccl.model.employee.Employee;
import com.avn.ccl.model.user.User;
import com.avn.ccl.service.webservicecilent.CCLServiceClient;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author : Roshen Dilshan
 * @Document : UserController
 * @Created on : Sep 14, 2015, 3:18:40 PM
 */
@Controller
public class UserController {

    @Autowired
    ServletContext context;
    @Autowired
    UserDAOImpl userDAOImpl1;
    @Autowired
    UserRoleDAOImpl userRoleDAOImpl;
    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    AuditTraceDAOImpl audittraceDaoImpl;
    @Autowired
    BranchDAOImpl branchDAOImpl;
    @Autowired
    EmployeeDAOImpl employeeDAOImpl;
    @Autowired
    EmployeeCategoryDAOImpl employeeCategoryDAOImpl;
    @Autowired
    CommonDAOImpl commonDAOImpl;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String pageView(Map<String, Object> model) {
        User user = new User();
        model.put("userForm", user);
        try {
        } catch (Exception exception) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        model.put("MAP", "SUP");
        return "user/usersearch";
    }

    @RequestMapping(value = "/user/search/tabledata", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        try {

            if (param.iDisplayStart < 0) {
                param.iDisplayStart = 0;
            }

            if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                param.iDisplayLength = 10;
            }

            if (Boolean.valueOf(request.getParameter("search"))) {
                User user = this.getParameters(request);

                iTotalRecords = userDAOImpl1.getTableDataCount(user);
                iTotalDisplayRecords = iTotalRecords;
                if (iTotalRecords > 0) {
                    list = userDAOImpl1.getTableData(user, param.iDisplayLength, param.iDisplayStart);
                    int max_invalid_login_attempts = commonDAOImpl.getMaxinvaidLoginCount();
                    for (User data : list) {
                        JSONObject object = new JSONObject();
                        object.put("username", data.getUsername());
                        object.put("user_role", data.getUser_role());
                        object.put("email", data.getEmail());
                        object.put("status", data.getStatus());
                        object.put("created_time", data.getCreated_time());
                        object.put("lastupdated_time", data.getLastupdated_time());
                        object.put("invalis_user_attempts", data.getInvalid_user_attepmts());
                        String reset_action;
                        if (data.getInvalid_user_attepmts() >= max_invalid_login_attempts) {
                            reset_action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-6\"><a href=\"javascript:void(0);\" onclick=\"resetAttempts('" + data.getUsername() + "')\"><i class=\"fa fa-lg fa-fw fa-undo\" title=\"Re-Set\"></i> Re-Set</a></div>"
                                    + "</div>";
                        } else {
                            reset_action = "--";
                        }
                        object.put("reset_attempts", reset_action);
                        String action = "<div class=\"row\">"
                                + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/user/view?username=" + data.getUsername() + "'><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/user/update/view?username=" + data.getUsername() + "'><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Edit\"></i></a></div>"
                                + "</div>";
                        object.put("action", action);
                        rows.put(object);
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/user/create/view", method = RequestMethod.GET)
    public String createViewUser(@ModelAttribute("userForm") User user, Map<String, Object> model) {
        try {
            model.put("radiooptiondisplay", "none");
            model.put("multibranchdisplay", "none");
            model.put("isnotifydisplay", "none");
            this.setPageViewComponenets(model);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        model.put("MAP", "ANUP");
        return "user/usercreate";
    }

    @RequestMapping(value = "/user/isemployeeexists", method = RequestMethod.POST)
    public @ResponseBody
    String isEmployeeExists(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_ADMIN_MANAGEMENT_READ_DSLK);
        int isnotvalid = 3;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("CODE", 3);
        try {
            User newuser = new User();
            newuser.setUsername(username);
            if (!userDAOImpl1.isUserExists(newuser)) {
                UserDetail userDetails = new CCLServiceClient().getUserDetails(endpoint, username);
                if (userDetails.getResponse() != null) {
                    if (employeeDAOImpl.isEmployeeExists(userDetails.getResponse().getEPFNO())) {
                        isnotvalid = 1;
                        jSONObject.put("CODE", 1);
                        jSONObject.put("EMAIL", employeeDAOImpl.getEmployeeEmailByEPF(userDetails.getResponse().getEPFNO()));
                    } else {
                        isnotvalid = 0;
                        jSONObject.put("CODE", 0);
                        jSONObject.put("DOAMIN", CommonVarList.EMAIL_DOAMIN);
                    }
                }
            } else {
                isnotvalid = 4;
                jSONObject.put("CODE", 4);
            }

        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("userForm") User user, Map<String, Object> model, HttpSession session) {
        String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_ADMIN_MANAGEMENT_READ_DSLK);
        try {
            this.setPageViewComponenets(model);
            UserDetail userDetails = new CCLServiceClient().getUserDetails(endpoint, user.getUsername());
            if (userDetails.getResponse() != null) {
                if (!userDAOImpl1.isUserExists(user)) {
                    if (!employeeDAOImpl.isEmployeeExists(userDetails.getResponse().getEPFNO())) {
                        boolean isValid = true;
                        if (user.getEmployeecategory() != null
                                && user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER)) {
                            model.put("radiooptiondisplay", "");
                            isValid = !userDAOImpl1.isUserCategoryExistsForBranch(Long.valueOf(user.getBranchsb()), user.getEmployeecategory());
                            if (!isValid && user.getBrorrm() != null && user.getBrorrm().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)) {
                                model.put("multibranchdisplay", "");
                                JSONArray array = new JSONArray(user.getMultibranch());
                                for (int i = 0; i < array.length(); i++) {
                                    String branch = array.getString(i);
                                    isValid = !userDAOImpl1.isUserCategoryExistsForBranch(Long.valueOf(branch), MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER);
                                    if (!isValid) {
                                        break;
                                    }
                                }
                            }
                        } else if ((user.getEmployeecategory() != null && user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER))
                                || (user.getEmployeecategory() != null && user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_ZONE_MANAGER))) {
                            model.put("multibranchdisplay", "");
                            JSONArray array = new JSONArray(user.getMultibranch());
                            for (int i = 0; i < array.length(); i++) {
                                String branch = array.getString(i);
                                isValid = !userDAOImpl1.isUserCategoryExistsForBranch(Long.valueOf(branch), user.getEmployeecategory());
                                if (!isValid) {
                                    break;
                                }
                            }
                        } else {
                            if ((user.getEmployeecategory() != null && user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_CONTACT_CENTRE))
                                    || (user.getEmployeecategory() != null && user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_INTERNAL_AUDIT))) {
                                model.put("isnotifydisplay", "");
                            }
                            if (user.getEmployeecategory() != null) {
                                isValid = !userDAOImpl1.isUserCategoryExistsForBranch(Long.valueOf(user.getBranchsb()), user.getEmployeecategory());
                            }
                        }
                        if (isValid) {
                            Employee employee = new Employee();
                            employee.setBranchid(userDetails.getResponse().getLOCATIONIDFK());
                            employee.setInitials(userDetails.getResponse().getINITIALS());
                            employee.setFirstname(userDetails.getResponse().getFIRSTNAME());
                            employee.setLastname(userDetails.getResponse().getLASTNAME());
                            employee.setEmail(user.getEmail());
                            employee.setContactmobile(userDetails.getResponse().getOFFICEMOBILENO());
                            employee.setStatus(user.getStatus());
                            employee.setNic(userDetails.getResponse().getNIC());
                            employee.setEpf(userDetails.getResponse().getEPFNO());
                            userDAOImpl1.createUser(user, employee);
                            model.put("successMsg", "User Successfully created.");
                            model.put("userForm", new User());
                            audittraceDaoImpl.insertAuditTrace("User create page", "Create", "User Create ", user.getUsername(), (String) session.getAttribute("username"));
                            model.put("radiooptiondisplay", "none");
                            model.put("multibranchdisplay", "none");
                            model.put("isnotifydisplay", "none");
                        } else {
                            model.put("errorMsg", "Some branch(s) already assigned.");
                        }
                    } else {
                        user.setEmpid(employeeDAOImpl.getEmployeeIdByEPF(userDetails.getResponse().getEPFNO()));
                        userDAOImpl1.createUser(user);
                        model.put("successMsg", "User Successfully created.");
                        model.put("userForm", new User());
                        audittraceDaoImpl.insertAuditTrace("User create page", "Create", "User Create ", user.getUsername(), (String) session.getAttribute("username"));
                        model.put("radiooptiondisplay", "none");
                        model.put("multibranchdisplay", "none");
                        model.put("isnotifydisplay", "none");
                    }
                } else {
                    model.put("errorMsg", "Username already exists.");
                    this.setShowComponents(user, model);
                }
            } else {
                model.put("errorMsg", "Invalid User, Username not exists in Active Directory.");
                this.setShowComponents(user, model);
            }
        } catch (Exception e) {
            model.put("errorMsg", "Failed to create user.");
            this.setShowComponents(user, model);
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
        model.put("MAP", "ANUP");
        return "user/usercreate";
    }

    @RequestMapping(value = "/user/userreset", method = RequestMethod.POST)
    public @ResponseBody
    String reSetUserAttempts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        JSONObject jSONObject = new JSONObject();
        try {
            userDAOImpl1.reSetUserAttempts(username);
            jSONObject.put("CODE", "SUCCESS");
            jSONObject.put("MESSAGE", "User Attempts Re-Set Successfully.");
        } catch (Exception e) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Re-Set User Attempts Customer.");
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
        return jSONObject.toString();
    }

    private void setShowComponents(User user, Map<String, Object> model) {
        model.put("radiooptiondisplay", "none");
        model.put("multibranchdisplay", "none");
        model.put("isnotifydisplay", "none");
        if (user.getEmployeecategory() != null && user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER)) {
            model.put("radiooptiondisplay", "");
            if (user.getBrorrm() != null && user.getBrorrm().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)) {
                model.put("multibranchdisplay", "");
            }
        } else if (user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)
                || user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_ZONE_MANAGER)) {
            model.put("multibranchdisplay", "");
        } else if (user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_CONTACT_CENTRE)
                || user.getEmployeecategory().equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_INTERNAL_AUDIT)) {
            model.put("isnotifydisplay", "");
        }
    }

    @RequestMapping(value = "/user/view", method = RequestMethod.GET)
    public String viewUser(@RequestParam("username") String username, Map<String, Object> model) {
        try {
            model.put("radiooptiondisplay", "none");
            model.put("multibranchdisplay", "none");
            model.put("isnotifydisplay", "none");
            this.setPageViewComponenets(model);
            User user = userDAOImpl1.getUser(username);
            List<String> employeeCategories = userDAOImpl1.getUserAssignedEmployeeCategories(user.getEmpid());
            for (String employeeCategory : employeeCategories) {
                if (employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER)) {
                    user.setEmployeecategory(employeeCategory);
                    user.setBranchsb(userDAOImpl1.getAssignedBranch(user.getEmpid(), employeeCategory));
                    user.setBrorrm(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER);
                    user.setEmployeecategory(employeeCategory);
                    for (String temp : employeeCategories) {
                        if (temp.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)) {
                            model.put("radiooptiondisplay", "");
                            user.setBrorrm(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER);
                            model.put("multibranchdisplay", "");
                            model.put("multiBranchList", userDAOImpl1.getAssignedBranches(user.getEmpid(), MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER));
                            break;
                        }
                    }
                } else if (employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)
                        || employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_ZONE_MANAGER)) {
                    user.setEmployeecategory(employeeCategory);
                    boolean condition = true;
                    for (String temp : employeeCategories) {
                        if (temp.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER)) {
                            condition = false;
                            break;
                        }
                    }
                    if (condition) {
                        model.put("multibranchdisplay", "");
                        model.put("multiBranchList", userDAOImpl1.getAssignedBranches(user.getEmpid(), employeeCategory));
                    }
                } else if (employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_CONTACT_CENTRE)
                        || employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_INTERNAL_AUDIT)) {
                    user.setEmployeecategory(employeeCategory);
                    user.setBranchsb(userDAOImpl1.getAssignedBranch(user.getEmpid(), employeeCategory));
                    model.put("isnotifydisplay", "");
                    user.setNotify(userDAOImpl1.isNotify(user.getEmpid(), employeeCategory));
                } else {
                    user.setEmployeecategory(employeeCategory);
                    user.setBranchsb(userDAOImpl1.getAssignedBranch(user.getEmpid(), employeeCategory));
                }
            }
            model.put("userForm", user);
        } catch (Exception e) {
            model.put("radiooptiondisplay", "none");
            model.put("multibranchdisplay", "none");
            model.put("isnotifydisplay", "none");
            model.put("errorMsg", "Failed to view user.");
            model.put("userForm", new User());
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
        model.put("MAP", "SUP");
        return "user/userview";
    }

    @RequestMapping(value = "/user/update/view", method = RequestMethod.GET)
    public String viewUpdateUser(@RequestParam("username") String username, Map<String, Object> model) {
        try {
            model.put("radiooptiondisplay", "none");
            model.put("multibranchdisplay", "none");
            model.put("isnotifydisplay", "none");
            model.put("branchsbed", "true");
            this.setPageViewComponenets(model);
            User user = userDAOImpl1.getUser(username);
            List<String> employeeCategories = userDAOImpl1.getUserAssignedEmployeeCategories(user.getEmpid());
            for (String employeeCategory : employeeCategories) {
                if (employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER)) {
                    model.put("branchsbed", "false");
                    user.setEmployeecategory(employeeCategory);
                    user.setBranchsb(userDAOImpl1.getAssignedBranch(user.getEmpid(), employeeCategory));
                    user.setBrorrm(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER);
                    for (String temp : employeeCategories) {
                        if (temp.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)) {
                            model.put("radiooptiondisplay", "");
                            user.setBrorrm(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER);
                            model.put("multibranchdisplay", "");
                            model.put("branchMultiselect", userDAOImpl1.getNotAssignedBranchList(user.getEmpid(), MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER));
                            model.put("multiBranchList", userDAOImpl1.getAssignedBranches(user.getEmpid(), MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER));
                            break;
                        }
                    }
                } else if (employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)
                        || employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_ZONE_MANAGER)) {
                    boolean condition = true;
                    for (String temp : employeeCategories) {
                        if (temp.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER)) {
                            condition = false;
                            break;
                        }
                    }
                    if (condition) {
                        user.setEmployeecategory(employeeCategory);
                        model.put("multibranchdisplay", "");
                        model.put("branchMultiselect", userDAOImpl1.getNotAssignedBranchList(user.getEmpid(), employeeCategory));
                        model.put("multiBranchList", userDAOImpl1.getAssignedBranches(user.getEmpid(), employeeCategory));
                    }
                } else if (employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_CONTACT_CENTRE)
                        || employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_INTERNAL_AUDIT)) {
                    model.put("branchsbed", "false");
                    user.setEmployeecategory(employeeCategory);
                    user.setBranchsb(userDAOImpl1.getAssignedBranch(user.getEmpid(), employeeCategory));
                    model.put("isnotifydisplay", "");
                    user.setNotify(userDAOImpl1.isNotify(user.getEmpid(), employeeCategory));
                } else {
                    model.put("branchsbed", "false");
                    user.setEmployeecategory(employeeCategory);
                    user.setBranchsb(userDAOImpl1.getAssignedBranch(user.getEmpid(), employeeCategory));
                }
            }
            model.put("userForm", user);
        } catch (Exception e) {
            model.put("radiooptiondisplay", "none");
            model.put("multibranchdisplay", "none");
            model.put("isnotifydisplay", "none");
            model.put("errorMsg", "Failed to view user.");
            model.put("userForm", new User());
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
        model.put("MAP", "SUP");
        return "user/userupdate";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("userForm") User user, Map<String, Object> model, HttpSession session) {
        try {
            model.put("radiooptiondisplay", "none");
            model.put("multibranchdisplay", "none");
            model.put("isnotifydisplay", "none");
            model.put("branchsbed", "true");
            this.setPageViewComponenets(model);
            userDAOImpl1.updateUser(user);
            List<String> employeeCategories = userDAOImpl1.getUserAssignedEmployeeCategories(user.getEmpid());
            for (String employeeCategory : employeeCategories) {
                if (employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER)) {
                    model.put("branchsbed", "false");
                    user.setEmployeecategory(employeeCategory);
                    user.setBranchsb(userDAOImpl1.getAssignedBranch(user.getEmpid(), employeeCategory));
                    user.setBrorrm(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER);
                    for (String temp : employeeCategories) {
                        if (temp.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)) {
                            model.put("radiooptiondisplay", "");
                            user.setBrorrm(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER);
                            model.put("multibranchdisplay", "");
                            model.put("branchMultiselect", userDAOImpl1.getNotAssignedBranchList(user.getEmpid(), MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER));
                            model.put("multiBranchList", userDAOImpl1.getAssignedBranches(user.getEmpid(), MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER));
                            break;
                        }
                    }
                } else if (employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER)
                        || employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_ZONE_MANAGER)) {
                    boolean condition = true;
                    for (String temp : employeeCategories) {
                        if (temp.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER)) {
                            condition = false;
                            break;
                        }
                    }
                    if (condition) {
                        user.setEmployeecategory(employeeCategory);
                        model.put("multibranchdisplay", "");
                        model.put("branchMultiselect", userDAOImpl1.getNotAssignedBranchList(user.getEmpid(), employeeCategory));
                        model.put("multiBranchList", userDAOImpl1.getAssignedBranches(user.getEmpid(), employeeCategory));
                    }
                } else if (employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_CONTACT_CENTRE)
                        || employeeCategory.equalsIgnoreCase(MasterDataVarList.CCL_CODE_EMPLOYEE_CATEGORY_INTERNAL_AUDIT)) {
                    model.put("branchsbed", "false");
                    user.setEmployeecategory(employeeCategory);
                    user.setBranchsb(userDAOImpl1.getAssignedBranch(user.getEmpid(), employeeCategory));
                    model.put("isnotifydisplay", "");
                    user.setNotify(userDAOImpl1.isNotify(user.getEmpid(), employeeCategory));
                } else {
                    model.put("branchsbed", "false");
                    user.setEmployeecategory(employeeCategory);
                    user.setBranchsb(userDAOImpl1.getAssignedBranch(user.getEmpid(), employeeCategory));
                }
            }
            model.put("userForm", user);
            model.put("successMsg", "User Successfully updated.");
            audittraceDaoImpl.insertAuditTrace("User update page", "update", "User update ", user.getUsername(), (String) session.getAttribute("username"));
        } catch (Exception e) {
            model.put("radiooptiondisplay", "none");
            model.put("multibranchdisplay", "none");
            model.put("isnotifydisplay", "none");
            model.put("errorMsg", "Failed to update user.");
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
        model.put("MAP", "SUP");
        return "user/userupdate";
    }

    private void setPageViewComponenets(Map<String, Object> model) throws Exception {
        model.put("userRoleList", userRoleDAOImpl.getUserRoleDropdownList());
        model.put("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_DEFAULT));
        model.put("employeeCategoryList", employeeCategoryDAOImpl.getEmployeeCategoryDropdownList());
        model.put("branchList", branchDAOImpl.getBranchIdDropdownList());
        model.put("branchMultiselect", branchDAOImpl.getBranchIdMultiSelect());
    }

    private User getParameters(HttpServletRequest request) throws Exception {
        User user = new User();
        if (request.getParameter("username") != null && !request.getParameter("username").trim().isEmpty()) {
            user.setUsername(request.getParameter("username"));
        }

        return user;
    }

}

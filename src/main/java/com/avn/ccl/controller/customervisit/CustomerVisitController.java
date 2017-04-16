/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.customervisit;

import com.avn.ccl.bean.serviceclient.fullprofile.CustomerData;
import com.avn.ccl.bean.webserviceclient.CustomerEducationBean;
import com.avn.ccl.bean.webserviceclient.CustomerFullProfileSearhDataBean;
import com.avn.ccl.controller.account.AccountController;
import com.avn.ccl.daoimpl.account.AccountDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
import com.avn.ccl.daoimpl.calldirection.CallDirectionDAOImpl;
import com.avn.ccl.daoimpl.casemgt.CaseDAOImpl;
import com.avn.ccl.daoimpl.casepriority.CasePriorityDAOImpl;
import com.avn.ccl.daoimpl.casetype.CaseTypeDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.customervisit.CustomerVisitDAOImpl;
import com.avn.ccl.daoimpl.department.DepartmentDAOImpl;
import com.avn.ccl.daoimpl.dependent.DependentDAOImpl;
import com.avn.ccl.daoimpl.education.EducationDAOImpl;
import com.avn.ccl.daoimpl.educationlevel.EducationLevelDAOImpl;
import com.avn.ccl.daoimpl.followupaction.FollowUpActionDAOImpl;
import com.avn.ccl.daoimpl.language.LanguageDAOImpl;
import com.avn.ccl.daoimpl.opdesignation.OPDesignationDAOImpl;
import com.avn.ccl.daoimpl.oplevel.OPLevelDAOImpl;
import com.avn.ccl.daoimpl.opprofession.OPProfessionDAOImpl;
import com.avn.ccl.daoimpl.product.ProductDAOImpl;
import com.avn.ccl.daoimpl.religon.ReligonDAOImpl;
import com.avn.ccl.daoimpl.secretquestion.SecretQuestionDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.title.TitleDAOImpl;
import com.avn.ccl.daoimpl.user.UserDAOImpl;
import com.avn.ccl.model.account.Account;
import com.avn.ccl.model.account.AccountSearchData;
import com.avn.ccl.model.casemgt.Case;
import com.avn.ccl.model.customervisit.CustomerVisit;
import com.avn.ccl.service.webservicecilent.CCLServiceClient;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.map.ObjectMapper;
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
 * @Author : Isuru
 * @Document : CustomerVisitController
 * @Created on : Jan 6, 2016, 8:40:55 AM
 */
@Controller
public class CustomerVisitController {

    @Autowired
    CustomerVisitDAOImpl customervisitdaoimpl;
    @Autowired
    AuditTraceDAOImpl audit;
    @Autowired
    CallDirectionDAOImpl callDirectionDaoImpl;
    @Autowired
    FollowUpActionDAOImpl followUpActionDaoImpl;
    @Autowired
    LanguageDAOImpl languagesDaoImpl;
    @Autowired
    CaseTypeDAOImpl caseTypeDAOImpl;
    @Autowired
    ProductDAOImpl productDAOImpl;
    @Autowired
    UserDAOImpl userDAOImpl;
    @Autowired
    CasePriorityDAOImpl casePriorityDAOImpl;
    @Autowired
    DepartmentDAOImpl departmentDAOImpl;
    @Autowired
    BranchDAOImpl branchdaoimpl;
    @Autowired
    StatusDAOImpl statusDAOImpl;
    @Autowired
    TitleDAOImpl titleDAOImpl;
    @Autowired
    AccountDAOImpl accountDAOImpl;
    @Autowired
    EducationDAOImpl educationDAOImpl;
    @Autowired
    OPDesignationDAOImpl oPDesignationDAOImpl;
    @Autowired
    OPLevelDAOImpl oPLevelDAOImpl;
    @Autowired
    OPProfessionDAOImpl oPProfessionDAOImpl;
    @Autowired
    EducationLevelDAOImpl educationLevelDAOImpl;
    @Autowired
    DependentDAOImpl dependentDAOImpl;
    @Autowired
    BranchDAOImpl branchDAOImpl;
    @Autowired
    ReligonDAOImpl religonDAOImpl;
    @Autowired
    SecretQuestionDAOImpl secretQuestionDAOImpl;
    @Autowired
    ServletContext context;
    @Autowired
    CommonDAOImpl commendaoimpl;
    @Autowired
    CaseDAOImpl caseDaoImple;

    @RequestMapping(value = "/customervisit/create/view", method = RequestMethod.GET)
    public String createCustomreVisitView(@ModelAttribute("customerVistForm") CustomerVisit customervistBean, ModelMap model, HttpSession session) {
        CustomerVisit cutomervist = new CustomerVisit();
        cutomervist.setPreferred_language(MasterDataVarList.CCL_CODE_LANUAGE_CATEGORY_DEFAULT);
        cutomervist.setCallDirection(MasterDataVarList.CCL_CODE_CALLDIRECTION_CATEGORY_DEFAULT);
        cutomervist.setFollowUpAction(MasterDataVarList.CCL_CODE_FOLLOUPACTION_CATEGORY_DEFAULT);
        cutomervist.setTitle(MasterDataVarList.CCL_CODE_TITLE_DEFAULT);
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            CustomerVisit customervisit = checkPrivilage_btn(customervistBean, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_VISIT_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_CUSTOMER_VISIT_SUBSECTION_ID), objList);
            model.addAttribute("avn_create", customervisit.isSave_btn());
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(CustomerVisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CNCLP");
        model.addAttribute("customerVistForm", cutomervist);

        return "customervisit/customervisit";
    }

    @RequestMapping(value = "/customervisit/create", method = RequestMethod.POST)
    public @ResponseBody
    String createCustomerVisit(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsoncustomervisit = request.getParameter("customervisit");
        System.out.println("Content : " + jsoncustomervisit);
        CustomerVisit customervist = (CustomerVisit) new ObjectMapper().readValue(jsoncustomervisit, CustomerVisit.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_VISIT_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_CUSTOMER_VISIT_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                long accountId = customervisitdaoimpl.insertCustomerVisit(customervist, session);
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("MESSAGE", "Saving Customer Visit Informations. Customer Visit Id :" + accountId + "");
                jSONObject.put("TYPE", customervist.getCallcreattype());
                jSONObject.put("CUSVISITID", String.valueOf(accountId));
                audit.insertAuditTrace("Customer Visit Create page", "Create", "Customer Visit Created ", String.valueOf(accountId), (String) session.getAttribute("username"));
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to Create Customer Visit Informations.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Customer Informations.");
            Logger.getLogger(CustomerVisitController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/customervisit/created", method = RequestMethod.POST)
    public String createCase(@ModelAttribute("customerVistForm") CustomerVisit customervistBean, ModelMap model) {
        Case caseset = new Case();
        caseset.setTitle(customervistBean.getTitle());
        caseset.setPhonenumber(customervistBean.getTelephone());
        caseset.setCustomername(customervistBean.getName_in_full());
        caseset.setCaseTypeId(customervistBean.getCaseTypeId());
        caseset.setProductId(customervistBean.getProductId());
        caseset.setCustomervisitId(String.valueOf(customervistBean.getCustomervisitId()));
        caseset.setBranchcode(customervistBean.getBranchcode());
        caseset.setPriorityId(MasterDataVarList.CCL_CODE_CASE_PRIORITY_DEFAULT);
        model.addAttribute("userCase", caseset);
        try {
            model.addAttribute("successMsg", "Sucess create Customer Visit Log.");
            this.setCreateViewCaseComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(CustomerVisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CNCP");
        return "case/casecreate";
    }

    @RequestMapping(value = "/customervisit/view", method = RequestMethod.GET)
    public String caseView(@ModelAttribute("customervisitView") CustomerVisit cutomervisitBean,
            @RequestParam(value = "cusvisitID", required = false) String customervisitId,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_VISIT_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CUSTOMERVISIT_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                CustomerVisit objcustomervisit = customervisitdaoimpl.getCustomerVisitByID(Integer.valueOf(customervisitId));
                model.addAttribute("objcallcenter", objcustomervisit);
            } else {
                model.put("errorMsg", "No privilage to view customer vist.");
            }

        } catch (Exception ex) {
            Logger.getLogger(CustomerVisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CCSP");
        return "customervisit/customervisitview";
    }

    @RequestMapping(value = "/customervisit/search", method = RequestMethod.GET)
    public String callSearch(@ModelAttribute("customervisitsearch") CustomerVisit data, ModelMap model, HttpSession session) {
        try {
        } catch (Exception ex) {
            Logger.getLogger(CustomerVisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CCSP");
        return "customervisit/customervisitsearch";
    }

    @RequestMapping(value = "/customervisit/update", method = RequestMethod.GET)
    public String calllogedite(@ModelAttribute("customerVisitUpdate") CustomerVisit data,
            @RequestParam(value = "cusvisitID", required = false) String cusvisitID,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            CustomerVisit customervisit = checkPrivilage_btn(data, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_VISIT_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_CUSTOMER_VISIT_SUBSECTION_ID), objList);
            model.addAttribute("avn_update", customervisit.isSave_btn());
            this.setCreateViewComponenets(model);
            CustomerVisit customervisitdata = customervisitdaoimpl.getCustomerVisitByID(Integer.valueOf(cusvisitID));
            model.addAttribute("customerVisitUpdate", customervisitdata);
        } catch (Exception ex) {
            Logger.getLogger(CustomerVisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CCSP");
        return "customervisit/customervisitupdate";
    }

    @RequestMapping(value = "/customervisitupdate", method = RequestMethod.POST)
    public @ResponseBody
    String customerVisitUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String jsoncutomerVisitUpdate = request.getParameter("customervisitupdate");
        System.out.println("Content : " + jsoncutomerVisitUpdate);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            CustomerVisit customervist = (CustomerVisit) new ObjectMapper().readValue(jsoncutomerVisitUpdate, CustomerVisit.class);
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_VISIT_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_CUSTOMERVISIT_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            if (privilage) {
                customervisitdaoimpl.customerVisitUpdate(customervist, session);
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("MESSAGE", "Update Customer Informations.");
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to Saving Customer Informations.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Customer Informations.");
            Logger.getLogger(CustomerVisitController.class.getName()).log(Level.SEVERE, null, exception);
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/customervisit/searched", method = RequestMethod.POST)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        List<CustomerVisit> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        CustomerVisit customervisit = new CustomerVisit();
        CustomerVisit customervisitview = checkPrivilage_btn(customervisit, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_VISIT_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CUSTOMERVISIT_SUBSECTION_ID), objList);
        CustomerVisit customervisitedit = checkPrivilage_btn(customervisit, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_VISIT_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_CUSTOMERVISIT_SUBSECTION_ID), objList);
        String ViewStatus = "";
        String UpdateStatus = "";
        if (customervisitview.isView_btn()) {
            ViewStatus = "disabled";
        } else {
            ViewStatus = "active";
        }
        if (customervisitedit.isEdit_btn()) {
            UpdateStatus = "disabled";
        } else {
            UpdateStatus = "active";
        }
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_VISIT_ID), String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMERVISIT_SEARCH_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }

                if (Boolean.valueOf(request.getParameter("search"))) {
                    CustomerVisit parameters = this.getParameters(request);

                    iTotalRecords = customervisitdaoimpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = customervisitdaoimpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (CustomerVisit data : list) {
                            JSONObject object = new JSONObject();
                            object.put("customervisitId", data.getCustomervisitId());
                            object.put("nameInFull", data.getNameInFull());
                            object.put("telephone", data.getTelephone());
                            object.put("followUpActionDes", data.getFollowUpActionDes());
                            object.put("username", data.getUsername());
                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/customervisit/view?cusvisitID=" + data.getCustomervisitId() + "'  ><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/customervisit/update?cusvisitID=" + data.getCustomervisitId() + "'><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                        audit.insertAuditTrace("Customer Visit search page ", "Search ", " Customer Visit search ", parameters.getInput(), (String) session.getAttribute("username"));
                    }
                }
            } else {
            }

        } catch (Exception e) {
            Logger.getLogger(CustomerVisitController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private CustomerVisit getParameters(HttpServletRequest request) throws Exception {
        CustomerVisit parameters = new CustomerVisit();

        if (request.getParameter("searchoption") != null && !request.getParameter("searchoption").isEmpty()) {
            parameters.setSearchoption(request.getParameter("searchoption"));
        }
        if (request.getParameter("input") != null && !request.getParameter("input").isEmpty()) {
            parameters.setInput(request.getParameter("input"));
        }
        return parameters;
    }

    @RequestMapping(value = "customervisit/searchprofile", method = RequestMethod.GET)
    public String searchProfile(@ModelAttribute("accountForm") Account account,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Account accountdata = checkPrivilage_btn(account, String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CALL_LOG_SUBSECTION_ID), objList);
            model.addAttribute("avn_search", accountdata.isSearch_btn());
        } catch (Exception e) {
        }
        return "customervisit/fullprofilesearch";
    }

    @RequestMapping(value = "customervisit/fullprofile/search", method = RequestMethod.POST)
    public String searchCustomer(@ModelAttribute("accountForm") Account account,
            @RequestParam(value = "cli", required = false) String cli,
            @RequestParam(value = "datetime", required = false) String datetime,
            Map<String, Object> model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            this.setPageViewComponenets(model);
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (account.getParameter_value() != null && !account.getParameter_value().isEmpty()) {
                    String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_SAVINGS_READ_PROXY_LK);
                    CustomerData customerData = new CCLServiceClient().getCustomerFullProfileSearch(account.getParameter_value(), endpoint);
                    if (customerData.getResponse() != null) {
                        AccountSearchData searchData = new AccountSearchData();
                        searchData.setCcid(customerData.getResponse().getSTAKEHOLDERREFERENCENO());
                        byte isCorporateCustomer = customerData.getResponse().getISCOPERATEOPERATOR();
                        if (isCorporateCustomer == 0) {
                            searchData.setAccount_category("INDIVIDUAL");
                        } else if (isCorporateCustomer == 1) {
                            searchData.setAccount_category("CORPORATE");
                        } else if (isCorporateCustomer == 2) {
                            searchData.setAccount_category("MISCELLANEOUS");
                        }

                        byte categoryType = customerData.getResponse().getCATEGORYTYPE();
                        if (categoryType == 0) {
                            searchData.setAccount_category_type("NORMAL");
                        } else if (categoryType == 1) {
                            searchData.setAccount_category_type("MINOR");
                        } else if (categoryType == 2) {
                            searchData.setAccount_category_type("OPERATOR");
                        } else if (categoryType == 3) {
                            searchData.setAccount_category_type("NOMINEE");
                        } else if (categoryType == 4) {
                            searchData.setAccount_category_type("PARTNERSHIP");
                        } else if (categoryType == 5) {
                            searchData.setAccount_category_type("COMPANY");
                        } else if (categoryType == 6) {
                            searchData.setAccount_category_type("PROPRIETORSHIPS");
                        } else if (categoryType == 8) {
                            searchData.setAccount_category_type("ASSOCIATIONS");
                        } else if (categoryType == 9) {
                            searchData.setAccount_category_type("CLUBS & SOCIETIES");
                        } else if (categoryType == 10) {
                            searchData.setAccount_category_type("NGO");
                        }

                        byte customerCodeType = customerData.getResponse().getCUSTOMERCODETYPE();
                        if (customerCodeType == 1) {
                            searchData.setCustomer_code_type("NIC");
                        } else if (customerCodeType == 4) {
                            searchData.setCustomer_code_type("CCID");
                        } else if (customerCodeType == 6) {
                            searchData.setCustomer_code_type("BRN");
                        }

                        searchData.setCustomer_code(customerData.getResponse().getCUSTOMERCODE());
                        searchData.setBranch(customerData.getResponse().getLOCATION());

                        account.setSearchData(searchData);
                        model.put("accountSearchForm", account);

                        audit.insertAuditTrace("Account search page ", "Search ", " Account searched ", account.getParameter_value(), (String) session.getAttribute("username"));
                    }
                }
            } else {
                model.put("errorMsg", "No privilage to search full profile.");
            }

        } catch (Exception exception) {
            System.out.println("Error Message : " + exception.getMessage());
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return "customervisit/fullprofilesearch";
    }

    @RequestMapping(value = "/customervisit/customervisit", method = RequestMethod.POST)
    public String createCustomreVisiCase(@ModelAttribute("customerVistForm") CustomerVisit customervistBean, ModelMap model) {
        CustomerVisit cutomervisit = new CustomerVisit();
        cutomervisit.setPreferred_language(MasterDataVarList.CCL_CODE_LANUAGE_CATEGORY_DEFAULT);
        cutomervisit.setCallDirection(MasterDataVarList.CCL_CODE_CALLDIRECTION_CATEGORY_DEFAULT);
        cutomervisit.setFollowUpAction(MasterDataVarList.CCL_CODE_FOLLOUPACTION_CATEGORY_DEFAULT);
        cutomervisit.setTitle(MasterDataVarList.CCL_CODE_TITLE_DEFAULT);
        try {
            CustomerVisit customerVisit = (CustomerVisit) new ObjectMapper().readValue(customervistBean.getJasonobject(), CustomerVisit.class);
            cutomervisit.setName_in_full(customerVisit.getName_in_full());
            cutomervisit.setAccountId(customerVisit.getAccountId());
            cutomervisit.setTitle(customerVisit.getTitle());
            cutomervisit.setLast_name(customerVisit.getLast_name());
            this.setCreateViewComponenets(model);
        } catch (Exception ex) {
            Logger.getLogger(CustomerVisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CNCLP");
        model.addAttribute("customerVistForm", cutomervisit);

        return "customervisit/customervisit";
    }

    @RequestMapping(value = "/customervisit/fullprofileview", method = RequestMethod.POST)
    public String searchCustomerView(@ModelAttribute("accountForm") Account account, Map<String, Object> model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            this.setPageViewComponenets(model);
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                if (account.getParameter_value() != null && !account.getParameter_value().isEmpty()) {
                    String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_SAVINGS_READ_PROXY_LK);
                    CustomerData customerData = new CCLServiceClient().getCustomerFullProfileSearch(account.getParameter_value(), endpoint);
                    if (customerData.getResponse() != null) {
                        CustomerFullProfileSearhDataBean bean = new CustomerFullProfileSearhDataBean();
                        bean.setCustomerData(customerData);
                        for (CustomerEducationBean educationBean : bean.getCustomerEducationBeans()) {
                            educationBean.setEducationLevelDescription(educationLevelDAOImpl.getEducationLevelByCode(educationBean.getEducationLevel()).getDescription());
                        }
                        if (bean.getDesignation_code() != null && !bean.getDesignation_code().isEmpty()) {
                            bean.setDesignation_description(oPDesignationDAOImpl.getDesignationByCode(bean.getDesignation_code()).getDescription());
                        }
                        if (bean.getLevel_code() != null && !bean.getLevel_code().isEmpty()) {
                            bean.setLevel_description(oPLevelDAOImpl.getLevelByCode(bean.getLevel_code()).getDescription());
                        }
                        if (bean.getProfession_code() != null && !bean.getProfession_code().isEmpty()) {
                            bean.setProfession_description(oPProfessionDAOImpl.getProfessionByCode(bean.getProfession_code()).getDescription());
                        }
                        if (bean.getSubSectorList() != null) {
                            model.put("subSectorList", bean.getSubSectorList());
                        }
                        account.setSearhDataBean(bean);
                        accountDAOImpl.selectAccountByCCID(account);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("title", bean.getTitle());
                        jSONObject.put("initials", bean.getInitials());
                        jSONObject.put("last_name", bean.getLast_name());
                        jSONObject.put("name_in_full", bean.getName_in_full());
                        jSONObject.put("accountId", bean.getStakeholder_reference_no());
                        model.put("JASONOBJ", jSONObject.toString());
                        audit.insertAuditTrace("Account Search page ", "Search ", "Customer full profile view ", account.getParameter_value(), (String) session.getAttribute("username"));
                    }
                }
            } else {
                model.put("errorMsg", "No privilage to view fullprofile.");
            }

        } catch (Exception exception) {
            System.out.println("Error Message : " + exception.getMessage());
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }

        return "customervisit/fullprofileview";
    }

    private void setPageViewComponenets(Map<String, Object> model) throws Exception {
        Map<String, String> list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("0", "INDIVIDUAL");
        list.put("1", "CORPORATE");
        list.put("2", "MISCELLANEOUS");
        model.put("categoryList", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("0", "NORMAL");
        list.put("1", "MINOR");
        list.put("2", "OPERATOR");
        list.put("3", "NOMINEE");
        list.put("4", "PARTNERSHIP");
        list.put("5", "COMPANY");
        list.put("6", "PROPRIETORSHIPS");
        list.put("8", "ASSOCIATIONS");
        list.put("9", "CLUBS & SOCIETIES");
        list.put("10", "NGO");
        model.put("categoryTypeList", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("1", "NIC");
        list.put("4", "CCID");
        list.put("6", "BRN");
        model.put("customerCodeTypeList", list);

        model.put("branchList", branchDAOImpl.getBranchCodeDropdownList());

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("MR.", "MR.");
        list.put("MRS.", "MRS.");
        list.put("MS.", "MS.");
        list.put("MISS.", "MISS.");
        list.put("DR.", "DR.");
        list.put("REV.", "REV.");
        list.put("FR.", "FR.");
        model.put("titleList", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("M", "Male");
        list.put("F", "Female");
        model.put("genderList", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("1", "Sri Lanka");
        model.put("nationalityList", list);

        model.put("religonList", religonDAOImpl.getReligonDropdownList());

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("S", "Single");
        list.put("M", "Married");
        list.put("D", "Divorced");
        list.put("W", "Widowed");
        list.put("U", "Undisclosed");
        model.put("maritalStatusList", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("0", "SAME AS NIC");
        list.put("1", "VERIFIED");
        list.put("2", "NOT REQUIRED");
        model.put("billingProofList", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("A", "Active");
        list.put("C", "Closed");
        list.put("CP", "Closure Pending");
        list.put("D", "Dormant");
        list.put("F", "Freeze");
        list.put("I", "Inactive");
        list.put("N", "New");
        model.put("savingsStatusList", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("A", "Active");
        list.put("C", "Cancelled");
        list.put("L", "Legal");
        list.put("O", "Period Over");
        list.put("P", "Pending");
        list.put("R", "Repossessed");
        list.put("S", "Settled");
        list.put("T", "Terminate");
        list.put("W", "Write Off");
        model.put("contractStatusList", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("0", "NO");
        list.put("1", "YES");
        model.put("taxPayeeList", list);

        model.put("secretQuestionList", secretQuestionDAOImpl.getSecretQuestionDropdownList());
    }

    private void setCreateViewCaseComponenets(ModelMap model) throws Exception {
        model.addAttribute("caseTypeList", caseTypeDAOImpl.getCaseTypeDropdownList());
        model.addAttribute("casePriorityList", casePriorityDAOImpl.getCasePriorityDropdownList());
        model.addAttribute("departmentList", departmentDAOImpl.getDepartmentDropdownList());
        model.addAttribute("productList", productDAOImpl.getProductDropdownList());
        model.addAttribute("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CASE));
        model.addAttribute("userList", userDAOImpl.getUserDropdownList());
        model.addAttribute("branchList", branchdaoimpl.getBranchIdDropdownList());
        model.addAttribute("titleList", titleDAOImpl.getTitleDropdownList());
        model.addAttribute("assigneeList", caseDaoImple.getAssigneList());
    }

    private void setCreateViewComponenets(ModelMap model) throws Exception {
        model.addAttribute("caseTypeList", caseTypeDAOImpl.getCaseTypeDropdownList());
        model.addAttribute("productList", productDAOImpl.getProductDropdownList());
        model.addAttribute("callDirectoryList", callDirectionDaoImpl.getCallDirectionDropdownList());
        model.addAttribute("fuaList", followUpActionDaoImpl.getFollowUpActionDropdownList());
        model.addAttribute("p_language", languagesDaoImpl.getLanguagesList());
        model.addAttribute("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CALLLOG));
        model.put("titleList", titleDAOImpl.getTitleDropdownList());
        model.addAttribute("branchList", branchdaoimpl.getBranchIdDropdownList());
        model.addAttribute("assigneeList", caseDaoImple.getAssigneList());

    }

    private CustomerVisit checkPrivilage_btn(CustomerVisit data, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        data = new CustomerVisit();
        data.setSave_btn(true);
        data.setSearch_btn(true);
        data.setEdit_btn(true);
        data.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                data.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                data.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                data.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                data.setView_btn(false);
            }
        }
        return data;
    }

    private Account checkPrivilage_btn(Account account, String sectionid, String subsectionid, JSONArray objList) throws Exception {
        account = new Account();
        account.setSave_btn(true);
        account.setSearch_btn(true);
        account.setEdit_btn(true);
        account.setView_btn(true);
        for (int i = 0; i < objList.length(); i++) {
            JSONObject yObject = objList.getJSONObject(i);
            if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_CREATE))) {
                account.setSave_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_SEARCH))) {
                account.setSearch_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_UPDATE))) {
                account.setEdit_btn(false);
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_VIEW))) {
                account.setView_btn(false);
            }
        }
        return account;
    }
}

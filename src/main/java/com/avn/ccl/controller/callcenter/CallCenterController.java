/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.callcenter;

import com.avn.ccl.bean.serviceclient.basicprofile.CustomerBasicData;
import com.avn.ccl.bean.serviceclient.fullprofile.CustomerData;
import com.avn.ccl.bean.webserviceclient.CustomerBasicProfileSearchParameterBean;
import com.avn.ccl.bean.webserviceclient.CustomerEducationBean;
import com.avn.ccl.bean.webserviceclient.CustomerFullProfileSearhDataBean;
import com.avn.ccl.controller.account.AccountController;
import com.avn.ccl.daoimpl.account.AccountDAOImpl;
import com.avn.ccl.model.callcenter.CallCenter;
import com.avn.ccl.model.callcenter.CallSearchParms;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
import com.avn.ccl.daoimpl.callcenter.CallCenterDAOImpl;
import com.avn.ccl.daoimpl.calldirection.CallDirectionDAOImpl;
import com.avn.ccl.daoimpl.casemgt.CaseDAOImpl;
import com.avn.ccl.daoimpl.casepriority.CasePriorityDAOImpl;
import com.avn.ccl.daoimpl.casetype.CaseTypeDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
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
import com.avn.ccl.daoimpl.salesPipline.SalesPiplineDAOImpl;
import com.avn.ccl.daoimpl.secretquestion.SecretQuestionDAOImpl;
import com.avn.ccl.daoimpl.title.TitleDAOImpl;
import com.avn.ccl.model.casemgt.Case;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.user.UserDAOImpl;
import com.avn.ccl.model.account.Account;
import com.avn.ccl.model.account.AccountSearchData;
import com.avn.ccl.model.contact.Contact;
import com.avn.ccl.model.lead.Lead;
import com.avn.ccl.service.webservicecilent.CCLServiceClient;
import com.avn.ccl.util.Common;
import static com.avn.ccl.util.Common.getDateFromString;
import static com.avn.ccl.util.Common.getStringFormatDate;
import com.avn.ccl.util.DateTime;
import com.avn.ccl.util.datatable.DataTableParamUtility;
import com.avn.ccl.util.datatable.DataTableRequestParam;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CallCenterController {

    @Autowired
    CallCenterDAOImpl callCenterDaoImpl;
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
    CaseDAOImpl caseDaoImpl;
    @Autowired
    SalesPiplineDAOImpl salesPiplineDAOImpl;

    @RequestMapping(value = "/callcenter/recievingcall", method = RequestMethod.GET)
    public String setCallView(@ModelAttribute("callCenterForm") CallCenter callBean,
            @RequestParam(value = "cli", required = false) String cli,
            @RequestParam(value = "datetime", required = false) String datetime,
            @RequestParam(value = "isdataset", required = false) String isdataset,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "p_name", required = false) String p_name,
            @RequestParam(value = "l_name", required = false) String l_name,
            @RequestParam(value = "name_in_full", required = false) String name_in_full,
            @RequestParam(value = "accountId", required = false) String accountId,
            ModelMap model, HttpSession session) {
        CallCenter callbeans = new CallCenter();
        callbeans.setPreferred_language(MasterDataVarList.CCL_CODE_LANUAGE_CATEGORY_DEFAULT);
        callbeans.setCallDirection(MasterDataVarList.CCL_CODE_CALLDIRECTION_CATEGORY_DEFAULT);
        callbeans.setFollowUpAction(MasterDataVarList.CCL_CODE_FOLLOUPACTION_CATEGORY_DEFAULT);
        callbeans.setTitle(MasterDataVarList.CCL_CODE_TITLE_DEFAULT);
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            CallCenter callcenter = checkPrivilage_btn(callBean, String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CALL_LOG_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model);
            model.addAttribute("avn_create", callcenter.isSave_btn());
            if (datetime != null && cli != null && isdataset == null && !datetime.equalsIgnoreCase("")) {
                Date setDate = getDateFromString(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, datetime);
                String gettime = getStringFormatDate(CommonVarList.TIME_FORMAT_hh_mm_24, setDate);
                String getdate = getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd, setDate);
                model.put("Date", getdate);
                model.put("time", gettime);
                model.put("telephone", cli);
                model.put("clidatetime", datetime);
                CustomerBasicProfileSearchParameterBean databean = new CustomerBasicProfileSearchParameterBean();
                databean.setSearchType(1);
                databean.setSearchReferance(cli);
//                System.out.println("cli");
                CustomerBasicData basicData = null;

                CCLServiceClient cclserviceclient = new CCLServiceClient();
                String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_SAVINGS_READ_PROXY_LK);
                basicData = cclserviceclient.getCustpmerBasicProfileSearch(databean, endpoint);

                model.put("title", basicData.getResponse().getTITLE());
                model.put("p_name", basicData.getResponse().getFIRSTNAME());
                model.put("l_name", basicData.getResponse().getLASTNAME());
                model.put("name_in_full", basicData.getResponse().getNAMEINFULL());
                model.put("accountId", basicData.getResponse().getSTAKEHOLDERREFERENCENO());

            } else if (datetime != null && isdataset != null && !datetime.equalsIgnoreCase("")) {
                Date setDate = getDateFromString(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, datetime);
                String gettime = getStringFormatDate(CommonVarList.TIME_FORMAT_hh_mm_24, setDate);
                String getdate = getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd, setDate);
                model.put("Date", getdate);
                model.put("time", gettime);
                model.put("telephone", cli);
                model.put("clidatetime", datetime);
                model.put("title", title);
                model.put("p_name", p_name);
                model.put("l_name", l_name);
                model.put("name_in_full", name_in_full);
                model.put("accountId", accountId);
            } else {
                model.put("l_name", l_name);
                model.put("name_in_full", name_in_full);
                model.put("accountId", accountId);
                model.put("title", title);
                model.put("p_name", p_name);
            }

        } catch (Exception ex) {
            Logger.getLogger(CallCenterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CNCLP");
        model.addAttribute("callCenterForm", callbeans);

        return "callcenter/recievingcall";
    }

    @RequestMapping(value = "/call/view", method = RequestMethod.GET)
    public String caseView(@ModelAttribute("callview") CallCenter data,
            @RequestParam(value = "callid", required = false) String callid,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CALL_CENTER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                CallCenter objcallcenter = callCenterDaoImpl.getCallByCallLogId(Integer.valueOf(callid));
                model.addAttribute("objcallcenter", objcallcenter);
            } else {
                model.put("errorMsg", "No privilage to view call log.");
            }
        } catch (Exception ex) {
            Logger.getLogger(CallCenterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CCSP");
        return "callcenter/callview";
    }

    @RequestMapping(value = "callcenter/searchprofile", method = RequestMethod.GET)
    public String searchProfile(@ModelAttribute("accountForm") Account account,
            @RequestParam(value = "cli", required = false) String cli,
            @RequestParam(value = "datetime", required = false) String datetime,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Account accountdata = checkPrivilage_btn(account, String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CALL_SEARCH_SUBSECTION_ID), objList);
            model.addAttribute("avn_search", accountdata.isSearch_btn());
        } catch (Exception ex) {
            Logger.getLogger(CallCenterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("telephone", cli);
        model.put("clidatetime", datetime);
        return "callcenter/fullprofilesearch";
    }

    @RequestMapping(value = "/fullprofile/search", method = RequestMethod.POST)
    public String searchCustomer(@ModelAttribute("accountForm") Account account,
            @RequestParam(value = "cli", required = false) String cli,
            @RequestParam(value = "datetime", required = false) String datetime,
            Map<String, Object> model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            this.setPageViewComponenets(model);
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
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        model.put("telephone", cli);
        model.put("clidatetime", datetime);
        return "callcenter/fullprofilesearch";
    }

    @RequestMapping(value = "/callcenter/fullprofileview", method = RequestMethod.POST)
    public String searchCustomerView(@ModelAttribute("accountForm") Account account, Map<String, Object> model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            this.setPageViewComponenets(model);
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

                        model.put("title", bean.getTitle());
                        model.put("ini", bean.getInitials());
                        model.put("p_name", bean.getFirst_name());
                        model.put("l_name", bean.getLast_name());
                        model.put("name_in_full", bean.getName_in_full());
                        model.put("accountId", bean.getStakeholder_reference_no());
                        model.put("dependetList", dependentDAOImpl.selectDelendentsByAccountId(account.getAccount_id()));
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
        model.put("isdataset", true);
        model.put("telephone", account.getCli());
        model.put("clidatetime", account.getDatetime());

        return "callcenter/fullprofileview";
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

    @RequestMapping(value = "/callcenter/calllogview/insert", method = RequestMethod.POST)
    public ModelAndView callInsert(@ModelAttribute("callCenterForm") CallCenter callLogForm, ModelMap model, HttpSession session) {
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CALL_LOG_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            this.setCreateViewComponenets(model);
            if (privilage) {
                long calllogid = callCenterDaoImpl.insertCallLog(callLogForm, session);
                model.addAttribute("successMsg", "Successfully created a call log , Call ID :  " + String.valueOf(calllogid));
                String username = (String) session.getAttribute("username");
                audit.insertAuditTrace("Callog Create  page", "Create", "Create CallLog", String.valueOf(calllogid), username);
                CallCenter callbeans = new CallCenter();
                callbeans.setPreferred_language(MasterDataVarList.CCL_CODE_LANUAGE_CATEGORY_DEFAULT);
                callbeans.setCallDirection(MasterDataVarList.CCL_CODE_CALLDIRECTION_CATEGORY_DEFAULT);
                callbeans.setFollowUpAction(MasterDataVarList.CCL_CODE_FOLLOUPACTION_CATEGORY_DEFAULT);
                if (!callLogForm.isTopipeline()) {
                    if (callLogForm.getCallcreattype().contentEquals("CL")) {
                        model.addAttribute("callCenterForm", callbeans);
                        model.put("MAP", "CNCLP");
                        return new ModelAndView("callcenter/recievingcall", "command", model);
                    } else {
                        Case caseset = new Case();
                        caseset.setTitle(callLogForm.getTitle());
                        caseset.setPhonenumber(callLogForm.getTelephone());
                        caseset.setCustomername(callLogForm.getName_in_full());
                        caseset.setCaseTypeId(callLogForm.getCaseTypeId());
                        caseset.setProductId(callLogForm.getProductId());
                        caseset.setCaseCallLogId(String.valueOf(calllogid));
                        caseset.setBranchcode(callLogForm.getBranchcode());
                        caseset.setPriorityId(MasterDataVarList.CCL_CODE_CASE_PRIORITY_DEFAULT);
                        model.addAttribute("userCase", caseset);
                        this.setCreateViewCaseComponenets(model);
                        model.put("MAP", "CNCP");
                        return new ModelAndView("case/casecreate", "command", model);
                    }
                } else {
                    Case caseset = new Case();
                    caseset.setTitle(callLogForm.getTitle());
                    caseset.setPhonenumber(callLogForm.getTelephone());
                    caseset.setCustomername(callLogForm.getName_in_full());
                    caseset.setCaseTypeId(callLogForm.getCaseTypeId());
                    caseset.setProductId(callLogForm.getProductId());
                    caseset.setCaseCallLogId(String.valueOf(calllogid));
                    caseset.setBranchcode(callLogForm.getBranchcode());
                    caseset.setPriorityId(MasterDataVarList.CCL_CODE_CASE_PRIORITY_DEFAULT);
                    caseset.setStatusid(String.valueOf(MasterDataVarList.CCL_CODE_STATUS_TRANSFER_TO_SALES_PIPELINE));
                    caseset.setCaseDate(Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyy_MM_dd_hh_mm_ss_a, DateTime.getTimestampFromDateAndTime(callLogForm.getStartDate(), callLogForm.getStartTime())));
                    caseDaoImpl.insertCase(caseset, username);

                    Contact contact = new Contact();
                    contact.setTitle(callLogForm.getTitle());
                    contact.setMobile(callLogForm.getTelephone());
                    contact.setNameInFull(callLogForm.getName_in_full());
                    long contactid = salesPiplineDAOImpl.insertcontact(contact, username);

                    Lead lead = new Lead();
                    lead.setProductid(Integer.valueOf(callLogForm.getProductId()));
                    lead.setLeadamount(callLogForm.getAmount());
                    lead.setForcastuntildate(new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd).format(callLogForm.getForecastedDate()));
                    lead.setChannelId(Integer.valueOf(callLogForm.getChannel()));
                    if (callLogForm.getCamping() != null && !callLogForm.getCamping().trim().isEmpty()) {
                        lead.setCampignId(Integer.valueOf(callLogForm.getCamping()));
                    }
                    lead.setContactid(contactid);
                    salesPiplineDAOImpl.insertLead(lead, username);

                    model.addAttribute("callCenterForm", callbeans);
                    model.put("MAP", "CNCLP");
                    return new ModelAndView("callcenter/recievingcall", "command", model);
                }
            } else {
                model.addAttribute("errorMsg", "No privilage to create call log.");
            }

        } catch (Exception ex) {
            Logger.getLogger(CallCenterController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute("errorMsg", "Failed to create call log.");
        }
        model.addAttribute("callCenterForm", new CallCenter());
        return new ModelAndView("callcenter/recievingcall", "command", model);
    }

    @RequestMapping(value = "/calllogsearch", method = RequestMethod.GET)
    public String callSearch(@ModelAttribute("callsearch") CallCenter data, ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            CallCenter callcenter = checkPrivilage_btn(data, String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CALL_SEARCH_SUBSECTION_ID), objList);
            model.addAttribute("avn_search", callcenter.isSearch_btn());
        } catch (Exception ex) {
            Logger.getLogger(CallCenterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CCSP");
        return "callcenter/calllogsearch";
    }

    @RequestMapping(value = "/callcenter/searched", method = RequestMethod.POST)
    public String caseSearched(@ModelAttribute("callsearch") CallSearchParms data, ModelMap model, HttpSession session) {
        try {
            model.addAttribute("callList", callCenterDaoImpl.getCall(data.getSearchoption(), data.getInput()));
            audit.insertAuditTrace("Call Log search page ", "Search ", " Call Log search ", data.getInput(), (String) session.getAttribute("username"));
        } catch (Exception ex) {
            Logger.getLogger(CallCenterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "callcenter/calllogsearch";
    }

    @RequestMapping(value = "/calllogedit", method = RequestMethod.GET)
    public String callEdite(@ModelAttribute("callLogUpdateForm") CallCenter data,
            @RequestParam(value = "callid", required = false) String callid,
            ModelMap model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            this.setCreateViewComponenets(model);
            CallCenter callcenter = checkPrivilage_btn(data, String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_CALL_CENTER_SUBSECTION_ID), objList);
            model.addAttribute("avn_update", callcenter.isEdit_btn());
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CALL_CENTER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            if (privilage) {
                model.addAttribute("callLogUpdateForm", callCenterDaoImpl.getCallByCallLogId(Integer.valueOf(callid)));
            } else {
                model.put("errorMsg", "No privilage to view call log");
            }

        } catch (Exception ex) {
            Logger.getLogger(CallCenterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.put("MAP", "CCSP");
        return "callcenter/calllogedit";
    }

    @RequestMapping(value = "/calllogedit/updated", method = RequestMethod.POST)
    public ModelAndView callUpdate(@ModelAttribute("callLogUpdateForm") CallCenter callLogUpdateForm, ModelMap model, HttpSession session) {
        try {
            this.setCreateViewComponenets(model);
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_EDIT_CALL_CENTER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_UPDATE, objList);
            if (privilage) {
                callCenterDaoImpl.Callupdate(callLogUpdateForm, session);
                model.addAttribute("successMsg", "Successfully updated a call log.");
                audit.insertAuditTrace("Call Update page", "Update", "Updated CallLog", callLogUpdateForm.getCallLogId(), (String) session.getAttribute("username"));
            } else {
                model.addAttribute("errorMsg", "No privilage to update call log.");
            }

        } catch (Exception ex) {
            Logger.getLogger(CallCenterController.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute("errorMsg", "Failed to update a callLog");
        }

        model.put("MAP", "CCSP");
        return new ModelAndView("callcenter/calllogedit", "command", model);
    }

    @RequestMapping(value = "/calllog/searched", method = RequestMethod.GET)
    public @ResponseBody
    String getTableData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
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
        List<CallCenter> list;
        final DataTableRequestParam param = DataTableParamUtility.getParam(request);
        final String sEcho = param.sEcho;
        int iTotalRecords = 0; // total number of records (unfiltered)

        int iTotalDisplayRecords = 0;//value will be set when code filters data by keyword
        JSONArray rows = new JSONArray();

        try {
            boolean privilage = commendaoimpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CALLCENTER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CALL_SEARCH_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (param.iDisplayStart < 0) {
                    param.iDisplayStart = 0;
                }

                if (param.iDisplayLength < 10 || param.iDisplayLength > 100) {
                    param.iDisplayLength = 10;
                }

                if (Boolean.valueOf(request.getParameter("search"))) {
                    CallCenter parameters = this.getParameters(request);

                    iTotalRecords = callCenterDaoImpl.getTableDataCount(parameters);
                    iTotalDisplayRecords = iTotalRecords;
                    if (iTotalRecords > 0) {
                        list = callCenterDaoImpl.getTableData(parameters, param.iDisplayLength, param.iDisplayStart);
                        for (CallCenter data : list) {
                            JSONObject object = new JSONObject();
                            object.put("callLogId", data.getCallLogId());
                            object.put("nameInFull", data.getNameInFull());
                            object.put("telephone", data.getTelephone());
                            object.put("callDirectionDes", data.getCallDirectionDes());
                            object.put("followUpActionDes", data.getFollowUpActionDes());
                            object.put("username", data.getUsername());
                            String action = "<div class=\"row\">"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/call/view?callid=" + data.getCallLogId() + "' class=" + ViewStatus + " ><i class=\"fa fa-lg fa-fw fa-eye\" title=\"View\"></i></a></div>"
                                    + "<div class=\"col-xs-3\"><a href='" + request.getContextPath() + "/calllogedit?callid=" + data.getCallLogId() + "' class=" + UpdateStatus + " ><i class=\"fa fa-lg fa-fw fa-pencil\" title=\"Update\"></i></a></div>"
                                    + "</div>";
                            object.put("action", action);

                            rows.put(object);
                        }
                    }
                    audit.insertAuditTrace("Call Log page", "Search", "Call Log Seaarch", parameters.getInput(), (String) session.getAttribute("username"));
                }
            } else {

            }

        } catch (Exception e) {
            Logger.getLogger(CallCenterController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("sEcho", sEcho);
        jsonResponse.put("iTotalRecords", iTotalRecords);
        jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);
        jsonResponse.put("aaData", rows);
        return jsonResponse.toString();
    }

    private CallCenter getParameters(HttpServletRequest request) throws Exception {
        CallCenter parameters = new CallCenter();

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

    private void setCreateViewCaseComponenets(ModelMap model) throws Exception {
        model.addAttribute("caseTypeList", caseTypeDAOImpl.getCaseTypeDropdownList());
        model.addAttribute("casePriorityList", casePriorityDAOImpl.getCasePriorityDropdownList());
        model.addAttribute("departmentList", departmentDAOImpl.getDepartmentDropdownList());
        model.addAttribute("productList", productDAOImpl.getProductDropdownList());
        model.addAttribute("statusList", statusDAOImpl.getStatusDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_CASE));
        model.addAttribute("userList", userDAOImpl.getUserDropdownList());
        model.addAttribute("branchList", branchdaoimpl.getBranchIdDropdownList());
        model.addAttribute("titleList", titleDAOImpl.getTitleDropdownList());
        Map<String, String> caseCategoryList = new LinkedHashMap<>();
        caseCategoryList.put("", "-- Select --");
        caseCategoryList.put("BR", "Branch");
        caseCategoryList.put("MF", "Micro Finance");
        model.addAttribute("caseCategoryList", caseCategoryList);
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
        model.addAttribute("assigneeList", caseDaoImpl.getAssigneList());
        model.addAttribute("channelList", salesPiplineDAOImpl.getChanelDropDownList());
        model.addAttribute("campaingList", salesPiplineDAOImpl.getChmpaignDropDownList());
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

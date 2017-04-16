/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.report.rptaccount;

import com.avn.ccl.bean.serviceclient.fullprofile.CustomerData;
import com.avn.ccl.bean.webserviceclient.CustomerEducationBean;
import com.avn.ccl.model.account.Account;
import com.avn.ccl.bean.webserviceclient.CustomerFullProfileSearhDataBean;
import com.avn.ccl.dao.accountcategory.AccountCategoryDAO;
import com.avn.ccl.dao.accountcategorytype.AccountCategoryTypeDAO;
import com.avn.ccl.daoimpl.addresstype.AddressTypeDAOImpl;
import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
import com.avn.ccl.daoimpl.account.AccountDAOImpl;
import com.avn.ccl.daoimpl.address.AddressDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.city.CityDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.country.CountryDAOImpl;
import com.avn.ccl.daoimpl.dependent.DependentDAOImpl;
import com.avn.ccl.daoimpl.dependenttype.DependentTypeDAOImpl;
import com.avn.ccl.daoimpl.district.DistrictDAOImpl;
import com.avn.ccl.daoimpl.education.EducationDAOImpl;
import com.avn.ccl.daoimpl.educationlevel.EducationLevelDAOImpl;
import com.avn.ccl.daoimpl.opdesignation.OPDesignationDAOImpl;
import com.avn.ccl.daoimpl.oplevel.OPLevelDAOImpl;
import com.avn.ccl.daoimpl.opprofession.OPProfessionDAOImpl;
import com.avn.ccl.daoimpl.opsector.OPSectorDAOImpl;
import com.avn.ccl.daoimpl.opsubsector.OPSubsectorDAOImpl;
import com.avn.ccl.daoimpl.province.ProvinceDAOImpl;
import com.avn.ccl.daoimpl.religon.ReligonDAOImpl;
import com.avn.ccl.daoimpl.secretquestion.SecretQuestionDAOImpl;
import com.avn.ccl.daoimpl.subsector.SubSectorDAOImpl;
import com.avn.ccl.model.account.AccountSearchData;
import com.avn.ccl.service.webservicecilent.CCLServiceClient;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author : Roshen Dilshan
 * @Document : AccountController
 * @Date : Jun 26, 2015 10:40:00 AM
 */
@Controller
public class AccountReportController {

    @Autowired
    ServletContext context;

    @Autowired
    BranchDAOImpl branchDAOImpl;
    @Autowired
    ReligonDAOImpl religonDAOImpl;
    @Autowired
    AddressTypeDAOImpl addressTypeDAOImpl;
    @Autowired
    CountryDAOImpl countryDAOImpl;
    @Autowired
    ProvinceDAOImpl provinceDAOImpl;
    @Autowired
    DistrictDAOImpl districtDAOImpl;
    @Autowired
    CityDAOImpl cityDAOImpl;
    @Autowired
    OPDesignationDAOImpl oPDesignationDAOImpl;
    @Autowired
    OPLevelDAOImpl oPLevelDAOImpl;
    @Autowired
    OPProfessionDAOImpl oPProfessionDAOImpl;
    @Autowired
    OPSectorDAOImpl oPSectorDAOImpl;
    @Autowired
    OPSubsectorDAOImpl oPSubsectorDAOImpl;
    @Autowired
    AccountDAOImpl accountDAOImpl;
    @Autowired
    AddressDAOImpl addressDAOImpl;
    @Autowired
    EducationDAOImpl educationDAOImpl;
    @Autowired
    EducationLevelDAOImpl educationLevelDAOImpl;
    @Autowired
    SubSectorDAOImpl subSectorDAOImpl;
    @Autowired
    DependentTypeDAOImpl dependentTypeDAOImpl;
    @Autowired
    DependentDAOImpl dependentDAOImpl;
    @Autowired
    SecretQuestionDAOImpl secretQuestionDAOImpl;
    @Autowired
    AuditTraceDAOImpl audit;
    @Autowired
    CommonDAOImpl commonDAOImpl;
    @Autowired
    AccountCategoryDAO accountCategoryDAO;
    @Autowired
    AccountCategoryTypeDAO accountCategoryTypeDAO;

    @RequestMapping(value = "/report/account", method = RequestMethod.GET)
    public String pageView(Map<String, Object> model, HttpSession session) {
        Account account = new Account();
        model.put("accountForm", account);
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Account accountdata = checkPrivilage_btn(account, String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_REPORT_SUBSECTION_ID), objList);
            model.put("avn_search", accountdata.isSearch_btn());
            Map<String, String> list = new LinkedHashMap<>();
            list.put("", "-- Select --");
            list.put("1", "NIC");
            list.put("4", "CCID");
            list.put("6", "BRN");
            model.put("customerCodeTypeList", list);
        } catch (Exception exception) {
            Logger.getLogger(AccountReportController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        model.put("MAP", "ARP");
        return "reports/account/accountreportsearch";
    }

    @RequestMapping(value = "/report/account/search", method = RequestMethod.POST)
    public String searchCustomer(@ModelAttribute("accountForm") Account account, Map<String, Object> model, HttpSession session) {
        System.out.println("Search Parameter : " + account.getParameter_value());
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Account accountdata = checkPrivilage_btn(account, String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_REPORT_SUBSECTION_ID), objList);
            String ViewStatus = "";
            if (accountdata.isView_btn()) {
                ViewStatus = "disabled";
            } else {
                ViewStatus = "active";
            }
            model.put("avn_view", ViewStatus);
            this.setPageViewComponenets(model);
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_REPORT_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (account.getParameter_value() != null && !account.getParameter_value().isEmpty()) {
                    String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_SAVINGS_READ_PROXY_LK);
                    CustomerData customerData = new CCLServiceClient().getCustomerFullProfileSearch(account.getParameter_value(), endpoint);
                    if (customerData.getResponse() != null) {
                        AccountSearchData searchData = new AccountSearchData();
                        searchData.setCcid(customerData.getResponse().getSTAKEHOLDERREFERENCENO());
                        searchData.setAccount_category(this.getAccountCategory(customerData.getResponse().getISCOPERATEOPERATOR()));
                        searchData.setAccount_category_type(this.getAccountCategoryType(customerData.getResponse().getCATEGORYTYPE()));
                        searchData.setCustomer_code_type(this.getCustomerCodeType(customerData.getResponse().getCUSTOMERCODETYPE()));
                        searchData.setCustomer_code(customerData.getResponse().getCUSTOMERCODE());
                        searchData.setBranch(customerData.getResponse().getLOCATION());

                        account.setSearchData(searchData);
                        model.put("accountSearchForm", account);
//                    audit.insertAuditTrace("Customer Searched " + account.getParameter_value());
                        audit.insertAuditTrace("Customer Report Search page", "Search", "Customer Searched ", account.getParameter_value(), (String) session.getAttribute("username"));
                    }
                }
            }
        } catch (Exception exception) {
            System.out.println("Error Message : " + exception.getMessage());
            Logger.getLogger(AccountReportController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return "reports/account/accountreportsearch";
    }

    @RequestMapping(value = "/report/account/search/view", method = RequestMethod.POST)
    public String searchCustomerView(@ModelAttribute("accountForm") Account account, Map<String, Object> model, HttpSession session) {
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        System.out.println("Search Parameter : " + account.getParameter_value());
        try {
            this.setPageViewComponenets(model);
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_REPORT_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
            Account accountdata = checkPrivilage_btn(account, String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_REPORT_SUBSECTION_ID), objList);
            model.put("avn_download", accountdata.isDownload_btn());
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
                        model.put("dependetList", dependentDAOImpl.selectDelendentsByAccountId(account.getAccount_id()));
                        // audit.insertAuditTrace("Customer full profile view " + account.getParameter_value());
                        audit.insertAuditTrace("Customer Report Search view page", "Search", "Customer full profile view ", account.getParameter_value(), (String) session.getAttribute("username"));
                    }
                    account.setCustomer_code(account.getParameter_value());
                }
            } else {

            }

        } catch (Exception exception) {
            System.out.println("Error Message : " + exception.getMessage());
            Logger.getLogger(AccountReportController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return "reports/account/accountreportsearchview";
    }

    @RequestMapping(value = "/report/account/pdf", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<byte[]> getPdfReport(@ModelAttribute("accountForm") Account account, Map<String, Object> model, HttpServletResponse response, HttpSession session) throws Exception {
        String user = (String) session.getAttribute("username");
        HashMap<String, Object> parameterMap = new HashMap<>();
        ResponseEntity<byte[]> outPutFile = null;
//        Connection connection = null;
        JRSwapFileVirtualizer virtualizer = null;
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_REPORT_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_REPORT_SUBSECTION_ID), MasterDataVarList.CCL_CODE_DOWNLOAD, objList);
//            CallReportParameters parameters = this.getParameters(request);
            if (privilage) {
                String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_SAVINGS_READ_PROXY_LK);
                CustomerData customerData = new CCLServiceClient().getCustomerFullProfileSearch(account.getCustomer_code(), endpoint);
                CustomerFullProfileSearhDataBean bean = new CustomerFullProfileSearhDataBean();
                Date date = commonDAOImpl.getCurentTimesStamp();
                bean.setCustomerData(customerData);
                account.setSearhDataBean(bean);
                this.setPageViewComponenets(model);
                parameterMap.put("CCL_CRM_IMG_PATH", context.getRealPath(CommonVarList.REPORT_LOGO_PATH));
                parameterMap.put("CCL_CRM_RPT_TITLE", "CUSTOMER REPORT");
                parameterMap.put("CCL_CRM_RPT_USERNAME", user);
                parameterMap.put("CCL_CRM_RPT_DATE", new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd_hh_mm_a).format(date));
                parameterMap.put("CCL_CRM_DSP_CCID", account.getSearhDataBean().getStakeholder_reference_no());
                parameterMap.put("CCL_CRM_DSP_ACC_CAT", accountCategoryDAO.getAccountCategoryById(account.getSearhDataBean().getAccount_category()).getDescription());
                parameterMap.put("CCL_CRM_DSP_ACC_CAT_TYPE", accountCategoryTypeDAO.getAccountCategoryTypeById(account.getSearhDataBean().getAccount_category_type()).getDescription());
                parameterMap.put("CCL_CRM_DSP_CODE_TYPE", account.getSearhDataBean().getCustomer_code_type_name());
                parameterMap.put("CCL_CRM_DSP_CODE", account.getSearhDataBean().getCustomer_code());
                parameterMap.put("CCL_CRM_DSP_LOCATION", account.getSearhDataBean().getLocation());

                String reportBuildPath = context.getInitParameter(CommonVarList.CONTEXT_PARAM_REPORT_BUILD_PATH);
                String report;
                if (account.getSearhDataBean().getAccount_category().equalsIgnoreCase(MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_INDIVIDUAL)) {
                    parameterMap.put("CCL_CRM_DSP_TITLE", account.getSearhDataBean().getTitle());
                    parameterMap.put("CCL_CRM_DSP_NAME_IN_FULL", account.getSearhDataBean().getName_in_full());
                    parameterMap.put("CCL_CRM_DSP_INITAILS", account.getSearhDataBean().getInitials());
                    parameterMap.put("CCL_CRM_DSP_LAST_NAME", account.getSearhDataBean().getLast_name());
                    parameterMap.put("CCL_CRM_DSP_PREFERRED_NAME", account.getSearhDataBean().getFirst_name() == null || account.getSearhDataBean().getFirst_name().isEmpty() ? "" : account.getSearhDataBean().getFirst_name());
                    parameterMap.put("CCL_CRM_DSP_DATE_OF_BIRTH", account.getSearhDataBean().getDate_of_birth());
                    parameterMap.put("CCL_CRM_DSP_GENDER", account.getSearhDataBean().getGender().contentEquals("M") ? "Male" : "Female");
                    parameterMap.put("CCL_CRM_DSP_MOTHERS_MAIDEN_NAME", account.getSearhDataBean().getMothers_maiden_name() == null || account.getSearhDataBean().getMothers_maiden_name().isEmpty() ? "" : account.getSearhDataBean().getMothers_maiden_name());
                    parameterMap.put("CCL_CRM_DSP_NATIONALITY", "Sri Lanka");
                    parameterMap.put("CCL_CRM_DSP_RELIGON", account.getSearhDataBean().getReligion() == null || account.getSearhDataBean().getReligion().isEmpty() ? "" : religonDAOImpl.getReligonById(account.getSearhDataBean().getReligion()).getDescription());
                    parameterMap.put("CCL_CRM_DSP_MARITAL_STATUS", account.getSearhDataBean().getMarital_status() == null || account.getSearhDataBean().getMarital_status().isEmpty() ? "" : this.getMaritalStatusById(account.getSearhDataBean().getMarital_status()));
                    parameterMap.put("CCL_CRM_DSP_PREFERRED_LANGUAGE", "");
                    parameterMap.put("CCL_CRM_DSP_MOBILE_NO_01", account.getSearhDataBean().getMobile_no_1() == null || account.getSearhDataBean().getMobile_no_1().isEmpty() ? "" : account.getSearhDataBean().getMobile_no_1());
                    parameterMap.put("CCL_CRM_DSP_MOBILE_NO_02", account.getSearhDataBean().getMobile_no_2() == null || account.getSearhDataBean().getMobile_no_2().isEmpty() ? "" : account.getSearhDataBean().getMobile_no_2());
                    parameterMap.put("CCL_CRM_DSP_EMAIL", account.getSearhDataBean().getEmail() == null || account.getSearhDataBean().getEmail().isEmpty() ? "" : account.getSearhDataBean().getEmail());
                    report = CommonVarList.REPORT_LOCATION + CommonVarList.REPORT_NAME_ACCOUNT_INDIVIDUAL_RPT;
                } else {
                    report = CommonVarList.REPORT_LOCATION + CommonVarList.REPORT_NAME_ACCOUNT_CORPORATE_RPT;
                    parameterMap.put("CCL_CRM_DSP_COMPANY_NAME", account.getSearhDataBean().getCompany_name());
                    parameterMap.put("CCL_CRM_DSP_SECTOR", account.getSearhDataBean().getSector_description());
                    String subSectors = "";
                    for (String temp : account.getSearhDataBean().getSubSectorList().keySet()) {
                        subSectors += temp + "\n";
                    }
                    parameterMap.put("CCL_CRM_DSP_SUBSECTOR", subSectors);
                    parameterMap.put("CCL_CRM_DSP_CP_NIC", account.getSearhDataBean().getCp_nic());
                    parameterMap.put("CCL_CRM_DSP_CP_TITLE", account.getSearhDataBean().getCp_title());
                    parameterMap.put("CCL_CRM_DSP_CP_NAME_IN_FULL", account.getSearhDataBean().getCp_name_in_full());
                    parameterMap.put("CCL_CRM_DSP_CP_INITAILS", account.getSearhDataBean().getCp_initial());
                    parameterMap.put("CCL_CRM_DSP_CP_LAST_NAME", account.getSearhDataBean().getCp_last_name());
                    parameterMap.put("CCL_CRM_DSP_CP_PREFERRED_NAME", account.getSearhDataBean().getCp_preferred_name());
                    parameterMap.put("CCL_CRM_DSP_CP_MOBILE_NO", account.getSearhDataBean().getCp_mobile_no());
                    parameterMap.put("CCL_CRM_DSP_CP_EMAIL", account.getSearhDataBean().getCp_email());
                }
                String reportLocation = context.getRealPath(report);

                reportBuildPath += File.separator + user + File.separator + "PDF";
                File file = new File(reportBuildPath);
                if (!file.exists()) {
                    file.mkdirs();
                } else {
                    for (File temp : file.listFiles()) {
                        temp.delete();
                    }
                }

                int numberOfPdfPagesInMemory = Integer.valueOf(context.getInitParameter(CommonVarList.CONTEXT_PARAM_REPORT_NUMBER_OF_PAGES_IN_MEMORY));
                JRSwapFile swapFile = new JRSwapFile(reportBuildPath, 1024, 100);
                virtualizer = new JRSwapFileVirtualizer(numberOfPdfPagesInMemory, swapFile, Boolean.TRUE);
                parameterMap.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

                JasperPrint jasperPrint = JasperFillManager.fillReport(reportLocation, parameterMap);

                byte[] outputFile = JasperExportManager.exportReportToPdf(jasperPrint);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType("application/pdf"));
                String filename = "CUSTOMER_REPORT_" + Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date) + ".pdf";
                headers.setContentDispositionFormData(filename, filename);
                headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
                outPutFile = new ResponseEntity<>(outputFile, headers, HttpStatus.OK);
                response.addCookie(new Cookie("fileDownloadToken", account.getDownload_token_value_id()));
            }

        } catch (Exception e) {
            Logger.getLogger(AccountReportController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        } finally {
            if (virtualizer != null) {
                virtualizer.cleanup();
            }
        }
        return outPutFile;
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

    private String getAccountCategory(byte category) {
        String accountCategory = "";
        switch (String.valueOf(category)) {
            case MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_INDIVIDUAL:
                accountCategory = "INDIVIDUAL";
                break;
            case MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_CORPORATE:
                accountCategory = "CORPORATE";
                break;
            case MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_MISCELLANEOUS:
                accountCategory = "MISCELLANEOUS";
                break;
        }
        return accountCategory;
    }

    private String getAccountCategoryType(byte categoryType) {
        String accountCategoryType = "";
        if (categoryType == 0) {
            accountCategoryType = "NORMAL";
        } else if (categoryType == 1) {
            accountCategoryType = "MINOR";
        } else if (categoryType == 2) {
            accountCategoryType = "OPERATOR";
        } else if (categoryType == 3) {
            accountCategoryType = "NOMINEE";
        } else if (categoryType == 4) {
            accountCategoryType = "PARTNERSHIP";
        } else if (categoryType == 5) {
            accountCategoryType = "COMPANY";
        } else if (categoryType == 6) {
            accountCategoryType = "PROPRIETORSHIPS";
        } else if (categoryType == 8) {
            accountCategoryType = "ASSOCIATIONS";
        } else if (categoryType == 9) {
            accountCategoryType = "CLUBS & SOCIETIES";
        } else if (categoryType == 10) {
            accountCategoryType = "NGO";
        }
        return accountCategoryType;
    }

    private String getCustomerCodeType(byte codeType) {
        String customerCodeType = "";
        if (codeType == 1) {
            customerCodeType = "NIC";
        } else if (codeType == 4) {
            customerCodeType = "CCID";
        } else if (codeType == 6) {
            customerCodeType = "BRN";
        }
        return customerCodeType;
    }

    private String getMaritalStatusById(String id) {
        String maritalStatus = "";
        switch (id) {
            case "S":
                maritalStatus = "Single";
                break;
            case "M":
                maritalStatus = "Married";
                break;
            case "D":
                maritalStatus = "Divorced";
                break;
            case "W":
                maritalStatus = "Widowed";
                break;
            case "U":
                maritalStatus = "Undisclosed";
                break;
        }
        return maritalStatus;
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
            } else if (yObject.getString("sectionid").contentEquals(sectionid) && yObject.getString("subsectionid").contentEquals(subsectionid) && yObject.getString("taskid").contentEquals(String.valueOf(MasterDataVarList.CCL_CODE_DOWNLOAD))) {
                account.setDownload_btn(false);
            }
        }
        return account;
    }

}

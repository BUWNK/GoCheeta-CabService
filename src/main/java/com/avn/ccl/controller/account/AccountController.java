/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.account;

import com.avn.ccl.bean.serviceclient.fullprofile.CustomerData;
import com.avn.ccl.bean.webserviceclient.CustomerEducationBean;
import com.avn.ccl.model.account.Account;
import com.avn.ccl.bean.webserviceclient.CustomerFullProfileSearhDataBean;
import com.avn.ccl.bean.webserviceclient.CustomerLoanBean;
import com.avn.ccl.bean.webserviceclient.StakeHolderSavingsBean;
import com.avn.ccl.daoimpl.addresstype.AddressTypeDAOImpl;
import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
import com.avn.ccl.daoimpl.account.AccountDAOImpl;
import com.avn.ccl.daoimpl.accountcategoeytype.AccountCategoryTypeDAOImpl;
import com.avn.ccl.daoimpl.accountcategory.AccountCategoryDAOImpl;
import com.avn.ccl.daoimpl.address.AddressDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.city.CityDAOImpl;
import com.avn.ccl.dao.common.CommonDAOImpl;
import com.avn.ccl.daoimpl.country.CountryDAOImpl;
import com.avn.ccl.daoimpl.customercodetype.CustomerCodeTypeDAOImpl;
import com.avn.ccl.daoimpl.customerhobbiesinterest.CustomerHobbiesInterestDAOImpl;
import com.avn.ccl.daoimpl.dependent.DependentDAOImpl;
import com.avn.ccl.daoimpl.dependenttype.DependentTypeDAOImpl;
import com.avn.ccl.daoimpl.district.DistrictDAOImpl;
import com.avn.ccl.daoimpl.education.EducationDAOImpl;
import com.avn.ccl.daoimpl.educationlevel.EducationLevelDAOImpl;
import com.avn.ccl.daoimpl.fdtype.FdTypeDAOImpl;
import com.avn.ccl.daoimpl.gender.GenderDAOImpl;
import com.avn.ccl.daoimpl.hobbiesandinterests.HobbiesAndInterestsDAOImpl;
import com.avn.ccl.daoimpl.language.LanguageDAOImpl;
import com.avn.ccl.daoimpl.maritalstatus.MaritalStatusDAOImpl;
import com.avn.ccl.daoimpl.opdesignation.OPDesignationDAOImpl;
import com.avn.ccl.daoimpl.oplevel.OPLevelDAOImpl;
import com.avn.ccl.daoimpl.opprofession.OPProfessionDAOImpl;
import com.avn.ccl.daoimpl.opsector.OPSectorDAOImpl;
import com.avn.ccl.daoimpl.opsubsector.OPSubsectorDAOImpl;
import com.avn.ccl.daoimpl.province.ProvinceDAOImpl;
import com.avn.ccl.daoimpl.religon.ReligonDAOImpl;
import com.avn.ccl.daoimpl.secretquestion.SecretQuestionDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.subsector.SubSectorDAOImpl;
import com.avn.ccl.daoimpl.title.TitleDAOImpl;
import com.avn.ccl.model.city.City;
import com.avn.ccl.model.district.District;
import com.avn.ccl.model.province.Province;
import com.avn.ccl.model.account.AccountSearchData;
import com.avn.ccl.model.accountcategegorytype.AccountCategoryType;
import com.avn.ccl.model.address.Address;
import com.avn.ccl.model.casemgt.Case;
import com.avn.ccl.model.customercodetype.CustomerCodeType;
import com.avn.ccl.model.dependent.Dependent;
import com.avn.ccl.model.education.Education;
import com.avn.ccl.model.customerhobbiesinterests.CustomerHobbiesInterests;
import com.avn.ccl.model.occupation.Occupation;
import com.avn.ccl.model.opsubsector.OPSubsector;
import com.avn.ccl.service.webservicecilent.CCLServiceClient;
import com.avn.ccl.util.FTPAccess;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.varlist.CommonVarList;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @Author : Roshen Dilshan
 * @Document : AccountController
 * @Date : Jun 26, 2015 10:40:00 AM
 */
@Controller
public class AccountController {

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
    StatusDAOImpl statusDAOImpl;
    @Autowired
    AccountCategoryDAOImpl accountCategoryDAOImpl;
    @Autowired
    AccountCategoryTypeDAOImpl accountCategoryTypeDAOImpl;
    @Autowired
    CustomerCodeTypeDAOImpl customerCodeTypeDAOImpl;
    @Autowired
    TitleDAOImpl titleDAOImpl;
    @Autowired
    GenderDAOImpl genderDAOImpl;
    @Autowired
    MaritalStatusDAOImpl maritalStatusDAOImpl;
    @Autowired
    LanguageDAOImpl languageDAOImpl;
    @Autowired
    HobbiesAndInterestsDAOImpl hobbiesAndInterestsDAOImpl;
    @Autowired
    CustomerHobbiesInterestDAOImpl customerHobbiesInterestDAOImpl;
    @Autowired
    FdTypeDAOImpl fdTypeDAOImpl;

    private final int maxFileSizeInKb = 100;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String pageView(Map<String, Object> model, HttpSession session) {
        Account account = new Account();
        model.put("accountForm", account);
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Account accountdata = checkPrivilage_btn(account, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_CUSTOMER_SUBSECTION_ID), objList);
            model.put("customerCodeTypeList", customerCodeTypeDAOImpl.getCustomerCodeTypeDropdownList());
            model.put("avn_search", accountdata.isSearch_btn());
        } catch (Exception exception) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        model.put("MAP", "SAP");
        return "account/accountsearch";
    }

    @RequestMapping(value = "/account/search", method = RequestMethod.POST)
    public String searchCustomer(@ModelAttribute("accountForm") Account account, Map<String, Object> model, HttpSession session) {
        System.out.println("Search Parameter : " + account.getParameter_value());
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Account accountdata = checkPrivilage_btn(account, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CUSTOMER_SUBSECTION_ID), objList);
            String ViewStatus = "";
            if (accountdata.isView_btn()) {
                ViewStatus = "disabled";
            } else {
                ViewStatus = "active";
            }
            model.put("avn_view", ViewStatus);
            this.setPageViewComponenets(model);
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_SEARCH_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_SEARCH, objList);
            if (privilage) {
                if (account.getParameter_value() != null && !account.getParameter_value().isEmpty()) {
                    String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_SAVINGS_READ_PROXY_LK);
                    CustomerData customerData = new CCLServiceClient().getCustomerFullProfileSearch(account.getParameter_value(), endpoint);
                    if (customerData.getResponse() != null) {
                        AccountSearchData searchData = new AccountSearchData();
                        searchData.setCcid(customerData.getResponse().getSTAKEHOLDERREFERENCENO());

                        searchData.setAccount_category(accountCategoryDAOImpl.getAccountCategoryById(String.valueOf(customerData.getResponse().getISCOPERATEOPERATOR())).getDescription());
                        searchData.setAccount_category_type(accountCategoryTypeDAOImpl.getAccountCategoryTypeById(String.valueOf(customerData.getResponse().getCATEGORYTYPE())).getDescription());
                        searchData.setCustomer_code_type(customerCodeTypeDAOImpl.getCustomerCodeTypeById(String.valueOf(customerData.getResponse().getCUSTOMERCODETYPE())).getDescription());

                        searchData.setCustomer_code(customerData.getResponse().getCUSTOMERCODE());
                        searchData.setBranch(customerData.getResponse().getLOCATION());

                        account.setSearchData(searchData);
                        model.put("accountSearchForm", account);
                        // audit.insertAuditTrace("Customer Searched " + account.getParameter_value());
                        audit.insertAuditTrace("Customer search page ", "Search ", " Customer searched ", account.getParameter_value(), (String) session.getAttribute("username"));
                    }
                }
            } else {
                model.put("errorMsg", "No privilage to view customer.");
            }

        } catch (Exception exception) {
            System.out.println("Error Message : " + exception.getMessage());
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        model.put("MAP", "SAP");
        return "account/accountsearch";
    }

    @RequestMapping(value = "/account/search/view", method = RequestMethod.POST)
    public String searchCustomerView(@ModelAttribute("accountForm") Account account, Map<String, Object> model, HttpSession session) {
        System.out.println("Search Parameter : " + account.getParameter_value());
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_VIEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_VIEW, objList);
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

                        for (StakeHolderSavingsBean savingsBean : bean.getStakeHolderSavingsBeans()) {
                            savingsBean.setStatus_description(statusDAOImpl.getStatusByCategoryAndCode(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_ACCOUNT_SAVINGS, savingsBean.getStatus()).getStatusDesc());
                        }

                        for (CustomerLoanBean customerLoanBean : bean.getCustomerLoanBeans()) {
                            customerLoanBean.setContract_status(statusDAOImpl.getStatusByCategoryAndCode(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_ACCOUNT_LOAN, customerLoanBean.getCs()).getStatusDesc());
                        }

                        account.setSearhDataBean(bean);
                        accountDAOImpl.selectAccountByCCID(account);

                        model.put("dependetList", dependentDAOImpl.selectDelendentsByAccountId(account.getAccount_id()));
                        model.put("customerHobbiesInterestsList", customerHobbiesInterestDAOImpl.getCustomerHobbiesAndInterestByAccountId(account.getAccount_id()));
                        // audit.insertAuditTrace("Customer full profile view " + account.getParameter_value());
                        audit.insertAuditTrace("Customer Search page ", "Search ", "Customer full profile view ", account.getParameter_value(), (String) session.getAttribute("username"));
                    }
                }
            } else {
                model.put("errorMsg", "No privilage to view customer.");
            }

        } catch (Exception exception) {
            System.out.println("Error Message : " + exception.getMessage());
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        model.put("MAP", "SAP");
        return "account/accountsearchview";
    }

    @RequestMapping(value = "/account/create/view", method = RequestMethod.GET)
    public String createCustomerView(@ModelAttribute("accountForm") Account account, Map<String, Object> model, HttpSession session) {
        CustomerFullProfileSearhDataBean dataBean = new CustomerFullProfileSearhDataBean();
        account.setSearhDataBean(dataBean);
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Account accountdata = checkPrivilage_btn(account, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model, account);
            model.put("avn_create", accountdata.isSave_btn());
        } catch (Exception exception) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        model.put("MAP", "CNAP");
        return "account/accountcreate";
    }

    @RequestMapping(value = "/account/create/categorytypelist", method = RequestMethod.POST)
    public @ResponseBody
    List<AccountCategoryType> getAccountCategoryTypeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String category = request.getParameter("category");
        return accountCategoryTypeDAOImpl.getAccountCategoryTypesByCategory(category);
    }

    @RequestMapping(value = "/account/create/customercodetypelist", method = RequestMethod.POST)
    public @ResponseBody
    List<CustomerCodeType> getCustomerCodeTypeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String category = request.getParameter("category");
        List<CustomerCodeType> customerCodeTypes = new ArrayList<>();
        if (category.equalsIgnoreCase(MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_INDIVIDUAL)) {
            customerCodeTypes = customerCodeTypeDAOImpl.getCustomerCodeTypesIndividual();
        } else if (category.equalsIgnoreCase(MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_CORPORATE)
                || category.equalsIgnoreCase(MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_MISCELLANEOUS)) {
            customerCodeTypes = customerCodeTypeDAOImpl.getCustomerCodeTypesCorporate();
        }
        return customerCodeTypes;
    }

    @RequestMapping(value = "/account/create/provincelist", method = RequestMethod.POST)
    public @ResponseBody
    List<Province> getProvinceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String country = request.getParameter("countryid");
        return provinceDAOImpl.getProvinceListByCountry(country);
    }

    @RequestMapping(value = "/account/create/districtlist", method = RequestMethod.POST)
    public @ResponseBody
    List<District> getDistrictList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String province = request.getParameter("provinceid");
        return districtDAOImpl.getDistrictListByProvince(province);
    }

    @RequestMapping(value = "/account/create/citylist", method = RequestMethod.POST)
    public @ResponseBody
    List<City> getCityList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String district = request.getParameter("districtid");
        return cityDAOImpl.getCityListByDistrict(district);
    }

    @RequestMapping(value = "/account/create/subsectorlist", method = RequestMethod.POST)
    public @ResponseBody
    List<OPSubsector> getSubsectorList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sector = request.getParameter("sectorid");
        return oPSubsectorDAOImpl.getSubsectorDropdownListBySector(sector);
    }

    @RequestMapping(value = "/account/create", method = RequestMethod.POST)
    public String createCustomer(@ModelAttribute("accountForm") Account account, Map<String, Object> model, HttpSession session) {
        OutputStream outputStream = null;
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            model.put("MAP", "CNAP");

            if (privilage) {
                this.setCreateViewComponenets(model, account);
                if (account.getAddresses().length > 0) {
                    Date date = commonDAOImpl.getCurentTimesStamp();
                    String time_format = Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMddHHmmss, date);
                    File file;

                    boolean isValidNicFileFormat = true;
                    File nic_file = null;
                    String fileLocation = context.getInitParameter(CommonVarList.CONTEXT_PARAM_FTP_SERVER_FILE_LOCATION);
                    String todayDirectory = Common.getStringFormatDate(CommonVarList.DATE_FORMAT_yyyyMMdd, date);
                    String fileErrorMessage = "";
                    if (account.getNic_file() != null && account.getNic_file().getSize() > 0) {
                        if ((account.getNic_file().getSize() / 1024) <= maxFileSizeInKb) {
                            String extension = FilenameUtils.getExtension(account.getNic_file().getOriginalFilename());
                            if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("jpg")) {
                                String nicFileLocation = context.getInitParameter(CommonVarList.CONTEXT_PARAM_NIC_FILE_UPLOAD);
                                file = new File(nicFileLocation);
                                if (!file.exists()) {
                                    file.mkdirs();
                                }
                                String nic_file_name = "NIC" + (account.getCustomer_code() == null ? "" : account.getCustomer_code().toUpperCase()) + time_format + "." + FilenameUtils.getExtension(account.getNic_file().getOriginalFilename());
                                nicFileLocation += File.separator + nic_file_name;
                                nic_file = new File(nicFileLocation);
                                account.setNic_file_name(nic_file_name);
                                account.setNic_file_location(fileLocation + todayDirectory + "/" + nic_file_name);
                            } else {
                                isValidNicFileFormat = false;
                                fileErrorMessage = "Invaid NIC file format.<br>";
                            }
                        } else {
                            isValidNicFileFormat = false;
                            fileErrorMessage += "Please upload maximum of 100kb NIC file.<br>";
                        }
                    }

                    boolean isValidSignatureFileForamt = true;
                    File signature_file = null;
                    if (account.getSignature_file() != null && account.getSignature_file().getSize() > 0) {
                        if ((account.getSignature_file().getSize() / 1024) <= maxFileSizeInKb) {
                            String extension = FilenameUtils.getExtension(account.getSignature_file().getOriginalFilename());
                            if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("jpg")) {
                                String signatureFileLocation = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SIGNATURE_FILE_UPLOAD);
                                file = new File(signatureFileLocation);
                                if (!file.exists()) {
                                    file.mkdirs();
                                }
                                String signature_file_name = "SIGNATURE" + (account.getCustomer_code() == null ? "" : account.getCustomer_code().toUpperCase()) + time_format + "." + FilenameUtils.getExtension(account.getSignature_file().getOriginalFilename());
                                signatureFileLocation += File.separator + signature_file_name;
                                signature_file = new File(signatureFileLocation);
                                account.setSignature_file_name(signature_file_name);
                                account.setSignature_file_location(fileLocation + todayDirectory + "/" + signature_file_name);
                            } else {
                                isValidNicFileFormat = false;
                                fileErrorMessage += "Invaid Signature file format.<br>";
                            }
                        } else {
                            isValidNicFileFormat = false;
                            fileErrorMessage += "Please upload maximum of 100kb Signature file.<br>";
                        }
                    }

                    if (isValidNicFileFormat && isValidSignatureFileForamt) {
                        WebApplicationContext con = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
                        Map<String, DataSource> dataSources = con.getBeansOfType(DataSource.class);
                        DataSource dataSource = dataSources.get("dataSource");
                        String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_SAVINGS_PROXY_LK);
                        new CCLServiceClient().createCustomer(account, dataSource, endpoint, session);
                        account.setMessage_type("SUCCESS");
                        if (account.getMessage_type() != null && account.getMessage_type().contentEquals("SUCCESS")) {
                            accountDAOImpl.updateAccountCCID(account);
                            audit.insertAuditTrace("Customer Create Page ", "Create ", "Customer Created in CCL side ", "Customer Id " + account.getAccount_id() + " and CCID " + account.getCcid(), (String) session.getAttribute("username"));
                            model.put("successMsg", account.getMessage_description() + " CCID : " + account.getCcid() + ", Customer Code : " + account.getCustomer_code());
                            model.put("accountForm", new Account());

                            FTPAccess cCLFTPAccess = new FTPAccess();
                            String host = context.getInitParameter(CommonVarList.CONTEXT_PARAM_FTP_SERVER_LOCATION);
                            String username = context.getInitParameter(CommonVarList.CONTEXT_PARAM_FTP_SERVER_USERNAME);
                            String password = context.getInitParameter(CommonVarList.CONTEXT_PARAM_FTP_SERVER_PASSWORD);
                            String port = context.getInitParameter(CommonVarList.CONTEXT_PARAM_FTP_SERVER_PORT);

                            boolean isNicUploaded = true, isSignatureUploaded = true;
                            if (nic_file != null) {
                                outputStream = new FileOutputStream(nic_file);
                                outputStream.write(account.getNic_file().getBytes());
                                outputStream.close();

                                try {
                                    System.out.println("NIC File FTP Status : " + cCLFTPAccess.sendFile(nic_file, host, username, password, port, fileLocation, todayDirectory));
                                } catch (Exception e) {
                                    isNicUploaded = false;
                                    Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
                                }
                            }

                            if (signature_file != null) {
                                outputStream = new FileOutputStream(signature_file);
                                outputStream.write(account.getNic_file().getBytes());
                                outputStream.close();

                                try {
                                    System.out.println("Signature File FTP Status : " + cCLFTPAccess.sendFile(signature_file, host, username, password, port, fileLocation, todayDirectory));
                                } catch (Exception e) {
                                    isSignatureUploaded = false;
                                    Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
                                }
                            }

                            String uploadErrorMessage = "";
                            if (nic_file != null && !isNicUploaded) {
                                uploadErrorMessage = "NIC";
                            }

                            if (signature_file != null && !isSignatureUploaded) {
                                if (uploadErrorMessage.isEmpty()) {
                                    uploadErrorMessage = "Signature";
                                } else {
                                    uploadErrorMessage += ", Signature";
                                }
                            }

                            if (!uploadErrorMessage.isEmpty()) {
                                model.put("errorMsg", uploadErrorMessage + " file(s) are not uploaded correctly. Please contact administrator and upload file(s) manualy.");
                            }
                        } else if (account.getMessage_type() != null && account.getMessage_type().contentEquals("ERROR")) {
                            String errorCodes = "<p>Error Codes<p>";
                            for (String code : account.getErrorCodes()) {
                                errorCodes += "<p>" + code + "</p>";
                            }
                            model.put("errorMsg", errorCodes);
                            this.setCreateViewGridData(model, account);
                        }
                    }

                    if (!fileErrorMessage.isEmpty()) {
                        fileErrorMessage = fileErrorMessage.substring(0, fileErrorMessage.length() - 4);
                        model.put("errorMsg", fileErrorMessage);
                    }
                } else {
                    model.put("errorMsg", "Please enter at least one address to save customer.");
                    this.setCreateViewGridData(model, account);
                }
            } else {
                model.put("errorMsg", "No privilage to create customer.");
            }
        } catch (Exception exception) {
            model.put("errorMsg", "Failed to create a account.");
            try {
                this.setCreateViewGridData(model, account);
            } catch (Exception ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Error :: " + exception.getMessage());
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                }
            }
        }
        return "account/accountcreate";
    }

    @RequestMapping(value = "/account/create/createrecord", method = RequestMethod.POST)
    public @ResponseBody
    String createRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsonaccount = request.getParameter("account_info");
        Account account = (Account) new ObjectMapper().readValue(jsonaccount, Account.class);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_SAVINGS_READ_PROXY_LK);
            CustomerData data = new CCLServiceClient().getCustomerFullProfileSearch(account.getCustomer_code(), endpoint);
            if (privilage) {
                if (data.getResponse() == null) {
                    Account excistingAccount = new Account();
                    excistingAccount.setCustomer_code(account.getCustomer_code());
                    excistingAccount = accountDAOImpl.selectAccountByCustomerCode(excistingAccount);
                    account.setAccount_id(excistingAccount.getAccount_id());
                    long account_id = accountDAOImpl.createAccount(account);
                    jSONObject.put("CODE", "SUCCESS");
                    jSONObject.put("account_id", String.valueOf(account_id));
                    //audit.insertAuditTrace("Customer Created Customer Id " + account.getAccount_id());
                    audit.insertAuditTrace("Customer page ", "Created ", "Customer Created ", account.getAccount_id(), (String) session.getAttribute("username"));
                } else {
                    jSONObject.put("CODE", "EXIST");
                    jSONObject.put("MESSAGE", "Customer Alreaddy Exist.");
                }
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to create customer.");
            }
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Verifying Customer.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/savepointone", method = RequestMethod.POST)
    public @ResponseBody
    String saveCustomerPersonalDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsonaccount = request.getParameter("account");
        Account account = (Account) new ObjectMapper().readValue(jsonaccount, Account.class);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                accountDAOImpl.updateAccountPersonalDetails(account);
                jSONObject.put("CODE", "SUCCESS");
                //audit.insertAuditTrace("Customer Basic details Added Customer Id " + account.getAccount_id());
                audit.insertAuditTrace("Customer Create page ", " Create ", " Basic details added", account.getAccount_id(),(String) session.getAttribute("username"));
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to create customer.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Customer Informations.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/savepointtwo", method = RequestMethod.POST)
    public @ResponseBody
    String saveCustomerAddress(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsoncontent = request.getParameter("address");
        Address address = (Address) new ObjectMapper().readValue(jsoncontent, Address.class);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                long address_id = addressDAOImpl.createAddress(address);
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("address_id", String.valueOf(address_id));
                //audit.insertAuditTrace("Customer Address Added Address Id " + address_id + "Customer Id " + address.getAccount_id());
                audit.insertAuditTrace("Customer create page", " Create ", "Customer Address Added ", " AddressId " + address_id + " Customer Id " + address.getAccount_id(),(String) session.getAttribute("username"));
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to create customer.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Customer Informations.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/deleteaddress", method = RequestMethod.POST)
    public @ResponseBody
    String deleteCustomerAddress(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String address_id = request.getParameter("address_id");
        JSONObject jSONObject = new JSONObject();
        try {
            addressDAOImpl.deleteAddress(address_id);
            jSONObject.put("CODE", "SUCCESS");
            //  audit.insertAuditTrace("Customer Address Added " + address_id);
            audit.insertAuditTrace("Customer Create ", " Create ", " Customer Address Added ", address_id, (String) session.getAttribute("username"));
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Deletinging Address.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/addresscount", method = RequestMethod.POST)
    public @ResponseBody
    String getCustomerAddressCount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String account_id = request.getParameter("account_id");
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                int count = addressDAOImpl.countAddress(account_id);
                if (count > 0) {
                    jSONObject.put("CODE", "SUCCESS");
                } else {
                    jSONObject.put("CODE", "ERROR");
                    jSONObject.put("MESSAGE", "No privilage to create customer.");
                }
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "There Should be Atleast One Address.");
            }
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Deletinging Address.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/savepointthree", method = RequestMethod.POST)
    public @ResponseBody
    String saveCustomerEducation(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsoncontent = request.getParameter("education");
        Education education = (Education) new ObjectMapper().readValue(jsoncontent, Education.class);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                long education_id = educationDAOImpl.createEducation(education);
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("education_id", String.valueOf(education_id));
                //    audit.insertAuditTrace("Customer Education Added Education Id " + education_id + " Customer id " + education.getAccount_id());
                audit.insertAuditTrace("Customer Create page ", " Create ", " Customer Education Added ", " Education Id " + education_id + " Customer id " + education.getAccount_id(), (String) session.getAttribute("username"));
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to  create customer.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Customer Education.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/deleteeducation", method = RequestMethod.POST)
    public @ResponseBody
    String deleteCustomerEducation(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String education_id = request.getParameter("education_id");
        JSONObject jSONObject = new JSONObject();
        try {
            educationDAOImpl.deleteEducation(education_id);
            jSONObject.put("CODE", "SUCCESS");
            //   audit.insertAuditTrace("Customer Education Deleted EducationId " + education_id);
            audit.insertAuditTrace("Customer Create page ", "Delete ", "Customer Education Deleted ", education_id, (String) session.getAttribute("username"));
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Deletinging Address.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/savepointfive", method = RequestMethod.POST)
    public @ResponseBody
    String saveCustomerOccupation(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsoncontent = request.getParameter("occupation");
        Occupation occupation = new Occupation();
        JSONObject rsp = new JSONObject(jsoncontent);
        occupation.setAccount_id(rsp.getString("account_id"));
        occupation.setDesignation(rsp.getString("designation"));
//        occupation.setLevel(rsp.getString("level"));
        occupation.setProfession(rsp.getString("profession"));
        occupation.setEmployer(rsp.getString("employer"));
        occupation.setSector(rsp.getString("sector"));
        JSONArray array = rsp.getJSONArray("subsectors");
        String[] subSectors = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            subSectors[i] = array.getString(i);
        }
        occupation.setSubsectors(subSectors);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                accountDAOImpl.updateAccountOccupation(occupation);
                subSectorDAOImpl.createSubSectors(occupation);
                jSONObject.put("CODE", "SUCCESS");
//            audit.insertAuditTrace("Customer Ocupation Added Customer Id " + occupation.getAccount_id());
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to  create customer.");
            }
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Deletinging Address.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/savepointsix", method = RequestMethod.POST)
    public @ResponseBody
    String saveCustomerSGC(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsoncontent = request.getParameter("sgc");
        Account account = (Account) new ObjectMapper().readValue(jsoncontent, Account.class);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                accountDAOImpl.updateAccountSGC(account);
                jSONObject.put("CODE", "SUCCESS");
                //audit.insertAuditTrace("Customer SGC Added Customer Id " + account.getAccount_id());
                audit.insertAuditTrace("Customer Create page ", " Add ", "Customer SGC Added ", account.getAccount_id(), (String) session.getAttribute("username"));
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to  create customer.");
            }
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Customer Informations.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/savepointseven", method = RequestMethod.POST)
    public @ResponseBody
    String saveCustomerDependent(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsoncontent = request.getParameter("dependent");
        Dependent dependent = (Dependent) new ObjectMapper().readValue(jsoncontent, Dependent.class);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                long dependent_id = dependentDAOImpl.createDependent(dependent);
                jSONObject.put("CODE", "SUCCESS");
                jSONObject.put("dependent_id", String.valueOf(dependent_id));
                // audit.insertAuditTrace("Customer dependent Added Depended Id " + dependent_id + " Customer Id " + dependent.getAccount_id());
                audit.insertAuditTrace("Customer Create page ", "Add", "Customer dependent Added ", "Depended Id" + dependent_id + "Customer Id " + dependent.getAccount_id(), (String) session.getAttribute("username"));
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to  create customer.");
            }
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Customer Informations.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/deletedependent", method = RequestMethod.POST)
    public @ResponseBody
    String deleteCustomerDependent(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String dependent_id = request.getParameter("dependent_id");
        JSONObject jSONObject = new JSONObject();
        try {
            dependentDAOImpl.deleteDependent(dependent_id);
            jSONObject.put("CODE", "SUCCESS");
            // audit.insertAuditTrace("Customer dependent Deleted Depended Id " + dependent_id);
            audit.insertAuditTrace("Customer Create", "Delete", "Customer dependent Deleted ", dependent_id, (String) session.getAttribute("username"));
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Deletinging Address.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/savepointeight", method = RequestMethod.POST)
    public @ResponseBody
    String saveCustomerOther(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsoncontent = request.getParameter("other");
        Account account = (Account) new ObjectMapper().readValue(jsoncontent, Account.class);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                accountDAOImpl.updateAccountOther(account);
                jSONObject.put("CODE", "SUCCESS");
                //  audit.insertAuditTrace("Customer Others Added Customer Id " + account.getAccount_id());
                audit.insertAuditTrace("Customer Create page", "Add", "Customer other details added", account.getAccount_id(), (String) session.getAttribute("username"));
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to  create customer.");
            }
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Customer Informations.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/savepointnine", method = RequestMethod.POST)
    public @ResponseBody
    String saveCustomerCorporate(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsoncontent = request.getParameter("corporate");
        Account account = new Account();
        JSONObject rsp = new JSONObject(jsoncontent);
        account.setAccount_id(rsp.getString("account_id"));
        account.setCopemployer(rsp.getString("copemployer"));
        account.setCopsector(rsp.getString("copsector"));
        account.setCopsub_sector_list(rsp.getJSONArray("copsub_sector_assign").toString());
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                accountDAOImpl.updateAccountCorporate(account);
                Occupation occupation = new Occupation();
                occupation.setAccount_id(account.getAccount_id());
                occupation.setSubsectors(account.getCopsub_sector());
                subSectorDAOImpl.createSubSectors(occupation);
                jSONObject.put("CODE", "SUCCESS");
                //  audit.insertAuditTrace("Customer Corporate Details Added Customer Id " + account.getAccount_id());
                audit.insertAuditTrace("Customer Create", "Add", "Customer Corporate Details Added", account.getAccount_id(), (String) session.getAttribute("username"));
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to  create customer.");
            }
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Deletinging Address.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/savepointten", method = RequestMethod.POST)
    public @ResponseBody
    String saveCustomerContactPerson(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String jsoncontent = request.getParameter("contactperson");
        Account account = (Account) new ObjectMapper().readValue(jsoncontent, Account.class);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                accountDAOImpl.updateAccountContactPerson(account);
                jSONObject.put("CODE", "SUCCESS");
                // audit.insertAuditTrace("Customer Contact Person Details Added Customer Id " + account.getAccount_id());
                audit.insertAuditTrace("Customer Create", "Add", "Customer Contact Person Details Added ", account.getAccount_id(), (String) session.getAttribute("username"));
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to  create customer.");
            }
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Deletinging Address.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/savepointeleven", method = RequestMethod.POST)
    public @ResponseBody
    String saveCustomerHobbies(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        String jsoncontent = request.getParameter("hobbies");
        CustomerHobbiesInterests customerHobbiesInterests = (CustomerHobbiesInterests) new ObjectMapper().readValue(jsoncontent, CustomerHobbiesInterests.class);
        JSONObject jSONObject = new JSONObject();
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            boolean privilage = commonDAOImpl.checkPrivilage(String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), MasterDataVarList.CCL_CODE_CREATE, objList);
            if (privilage) {
                if (!customerHobbiesInterestDAOImpl.isCustomerHobbyAlreadyExist(customerHobbiesInterests.getAccount_id(), customerHobbiesInterests.getHobby_id())) {
                    long customer_hobby_id = customerHobbiesInterestDAOImpl.createCustomerHobbiesInterest(customerHobbiesInterests, username);
                    jSONObject.put("CODE", "SUCCESS");
                    jSONObject.put("customer_hobby_id", String.valueOf(customer_hobby_id));
                    audit.insertAuditTrace("Customer Create page ", "Add", "Customer hobby Added ", "Customer hobby Id" + customer_hobby_id + "Customer Id " + customerHobbiesInterests.getAccount_id(), username);
                } else {
                    jSONObject.put("CODE", "ERROR");
                    jSONObject.put("MESSAGE", "Hobby/Interest Already Exist.");
                }
            } else {
                jSONObject.put("CODE", "ERROR");
                jSONObject.put("MESSAGE", "No privilage to  create customer.");
            }

        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Saving Customer Informations.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/deletehobby", method = RequestMethod.POST)
    public @ResponseBody
    String deleteCustomerHobbies(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String hobby_id = request.getParameter("hobby_id");
        JSONObject jSONObject = new JSONObject();
        try {
            customerHobbiesInterestDAOImpl.deleteCustomerHobbiesInterest(hobby_id);
            jSONObject.put("CODE", "SUCCESS");
            audit.insertAuditTrace("Customer Create", "Delete", "Customer hobby/interest Deleted ", hobby_id, (String) session.getAttribute("username"));
        } catch (Exception exception) {
            jSONObject.put("CODE", "ERROR");
            jSONObject.put("MESSAGE", "Error Occurred While Deletinging hobby.");
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return jSONObject.toString();
    }

    @RequestMapping(value = "/account/create/testcall", method = RequestMethod.POST)
    public @ResponseBody
    String testCall(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String endpoint = context.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_ADMIN_MANAGEMENT_READ_DSLK);
        try {
            new CCLServiceClient().getUserDetails(endpoint, "malithm");
        } catch (Exception exception) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        return "";
    }

    private void setPageViewComponenets(Map<String, Object> model) throws Exception {
        Map<String, String> list = new LinkedHashMap<>();
        model.put("categoryList", accountCategoryDAOImpl.getAccountCategoryDropdownList());
        model.put("categoryTypeList", accountCategoryTypeDAOImpl.getAccountCategoryTypeDropdownList());
        model.put("customerCodeTypeList", customerCodeTypeDAOImpl.getCustomerCodeTypeDropdownList());
        model.put("branchList", branchDAOImpl.getBranchCodeDropdownList());
        model.put("titleList", titleDAOImpl.getTitleDropdownList());
        model.put("genderList", genderDAOImpl.getGenderDropdownList());
        model.put("religonList", religonDAOImpl.getReligonDropdownList());
        model.put("maritalStatusList", maritalStatusDAOImpl.getMaritalStatusCodeDropdownList());
        model.put("savingsStatusList", statusDAOImpl.getStatusCodeDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_ACCOUNT_SAVINGS));
        model.put("contractStatusList", statusDAOImpl.getStatusCodeDropdownList(MasterDataVarList.CCL_CODE_STATUS_CATEGORY_ACCOUNT_LOAN));
        model.put("secretQuestionList", secretQuestionDAOImpl.getSecretQuestionDropdownList());
        model.put("languageList", languageDAOImpl.getLanguagesList());
        model.put("hobbuesAndInterestList", hobbiesAndInterestsDAOImpl.getHobbyDropdownList(MasterDataVarList.CCL_CODE_STATUS_ACTIVE));
        model.put("fdTypeList", fdTypeDAOImpl.getFdDropdownList());
        model.put("branchOldCodeList", branchDAOImpl.getBranchOldDropdownList());

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("1", "Srilankan");
        model.put("nationalityList", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("0", "SAME AS NIC");
        list.put("1", "VERIFIED");
        list.put("2", "NOT REQUIRED");
        model.put("billingProofList", list);

        list = new LinkedHashMap<>();
        list.put("", "-- Select --");
        list.put("0", "NO");
        list.put("1", "YES");
        model.put("taxPayeeList", list);
    }

    private void setCreateViewComponenets(Map<String, Object> model, Account account) throws Exception {
        Map<String, String> valueList = new LinkedHashMap<>();
        Map<String, String> emptyList = new LinkedHashMap<>();
        emptyList.put("", "-- Select --");
        model.put("provinceList", emptyList);
        model.put("districtList", emptyList);
        model.put("cityList", emptyList);

        if (account.getCustomer_category() != null && !account.getCustomer_category().isEmpty()) {
            model.put("customerCategoryTypeList", accountCategoryTypeDAOImpl.getAccountCategoryTypeDropdownListByCategory(account.getCustomer_category()));
            if (account.getCustomer_category().equalsIgnoreCase(MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_INDIVIDUAL)) {
                model.put("customerCodeTypeList", customerCodeTypeDAOImpl.getCustomerCodeTypeDropdownListIndividual());
            } else if (account.getCustomer_category().equalsIgnoreCase(MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_CORPORATE)
                    || account.getCustomer_category().equalsIgnoreCase(MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_MISCELLANEOUS)) {
                model.put("customerCodeTypeList", customerCodeTypeDAOImpl.getCustomerCodeTypeDropdownListCorporate());
            }
        } else {
            model.put("customerCategoryTypeList", emptyList);
            model.put("customerCodeTypeList", emptyList);
        }

        model.put("customerCategoryList", accountCategoryDAOImpl.getAccountCategoryDropdownList());
        model.put("titleList", titleDAOImpl.getTitleDropdownList());
        model.put("genderList", genderDAOImpl.getGenderDropdownList());
        model.put("maritalStatusList", maritalStatusDAOImpl.getMaritalStatusCodeDropdownList());
        model.put("branchList", branchDAOImpl.getBranchCodeDropdownList());
        model.put("religonList", religonDAOImpl.getReligonDropdownList());
        model.put("addressTypeList", addressTypeDAOImpl.getAddressTypeDropdownList());
        model.put("countryList", countryDAOImpl.getCountryDropdownList());
        model.put("opdesignationList", oPDesignationDAOImpl.getDesignationDropdownList());
        model.put("oplevelList", oPLevelDAOImpl.getLevelDropdownList());
        model.put("opprofessionList", oPProfessionDAOImpl.getProfessionDropdownList());
        model.put("opsectorList", oPSectorDAOImpl.getSectorDropdownList());
        model.put("educationLevelList", educationLevelDAOImpl.getEducationLevelDropdownList());
        model.put("dependentRelationshipList", dependentTypeDAOImpl.getDependentTypeDropdownList());
        model.put("secretQuestionList", secretQuestionDAOImpl.getSecretQuestionDropdownList());
        model.put("languageList", languageDAOImpl.getLanguagesList());
        model.put("hobbuesAndInterestList", hobbiesAndInterestsDAOImpl.getHobbyDropdownList(MasterDataVarList.CCL_CODE_STATUS_ACTIVE));
        model.put("fdTypeList", fdTypeDAOImpl.getFdDropdownList());
        model.put("branchOldCodeList", branchDAOImpl.getBranchOldDropdownList());

        valueList.put("", "-- Select --");
        valueList.put("1", "Srilankan");
        model.put("nationalityList", valueList);

        valueList = new LinkedHashMap<>();
        valueList.put("", "-- Select --");
        valueList.put("0", "SAME AS NIC");
        valueList.put("1", "VERIFIED");
        valueList.put("2", "NOT REQUIRED");
        model.put("billingProofList", valueList);

        valueList = new LinkedHashMap<>();
        valueList.put("", "-- Select --");
        valueList.put("0", "NO");
        valueList.put("1", "YES");
        model.put("taxPayeeList", valueList);
    }

    private void setCreateViewGridData(Map<String, Object> model, Account account) throws Exception {
        List<Address> addressrecords = new ArrayList<>();
        for (Address address : account.getAddresses()) {
            address.setAddress_type_descriptin(addressTypeDAOImpl.getAddressTypeById(address.getAddress_type()).getDescription());
            addressrecords.add(address);
        }
        model.put("addressRowList", addressrecords);

        List<Education> educationrecords = new ArrayList<>();
        for (Education education : account.getEducations()) {
            education.setEducation_level_description(educationLevelDAOImpl.getEducationLevelById(education.getEducation_level()).getDescription());
            educationrecords.add(education);
        }
        model.put("educationRowList", educationrecords);

        List<Dependent> dependentrecords = new ArrayList<>();
        for (Dependent dependent : account.getDependents()) {
            dependent.setDependent_relationship_description(dependentTypeDAOImpl.getDependentTypeById(dependent.getDependent_relationship()).getDescription());
            dependentrecords.add(dependent);
        }
        model.put("dependentRowList", dependentrecords);

        List<CustomerHobbiesInterests> hobbiesrecords = new ArrayList<>();
        for (CustomerHobbiesInterests customerHobbiesInterest : account.getCustomerhobbiesinterest()) {
            customerHobbiesInterest.setHobby_description(hobbiesAndInterestsDAOImpl.getHobbiesAndInterestById(customerHobbiesInterest.getHobby_id()).getHobby_description());
            hobbiesrecords.add(customerHobbiesInterest);
        }
        model.put("hobbiesAndIntersetList", hobbiesrecords);

        if (account.getSub_sectors_not_assign().length > 0) {
            model.put("subsectorNotAssignList", oPSubsectorDAOImpl.getSubsectorDropdownListByIds(account.getSub_sectors_not_assign()));
        }

        if (account.getSub_sector().length > 0) {
            model.put("subsectorAssignList", oPSubsectorDAOImpl.getSubsectorDropdownListByIds(account.getSub_sector()));
        }
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

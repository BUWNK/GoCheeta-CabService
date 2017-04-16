/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.salespipline;

import com.avn.ccl.bean.webserviceclient.CustomerFullProfileSearhDataBean;
import com.avn.ccl.controller.account.AccountController;
import com.avn.ccl.daoimpl.account.AccountDAOImpl;
import com.avn.ccl.daoimpl.accountcategoeytype.AccountCategoryTypeDAOImpl;
import com.avn.ccl.daoimpl.accountcategory.AccountCategoryDAOImpl;
import com.avn.ccl.daoimpl.address.AddressDAOImpl;
import com.avn.ccl.daoimpl.addresstype.AddressTypeDAOImpl;
import com.avn.ccl.daoimpl.audittrace.AuditTraceDAOImpl;
import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
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
import com.avn.ccl.daoimpl.salesPipline.SalesPiplineDAOImpl;
import com.avn.ccl.daoimpl.secretquestion.SecretQuestionDAOImpl;
import com.avn.ccl.daoimpl.status.StatusDAOImpl;
import com.avn.ccl.daoimpl.subsector.SubSectorDAOImpl;
import com.avn.ccl.daoimpl.title.TitleDAOImpl;
import com.avn.ccl.model.account.Account;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author user
 */
@Controller
public class upadateCustomerController {

    @Autowired
    SalesPiplineDAOImpl salespiplinedaoimpl;
    @Autowired
    BranchDAOImpl branchDAOImpl;
    @Autowired
    ReligonDAOImpl religonDAOImpl;
    @Autowired
    AddressTypeDAOImpl addressTypeDAOImpl;
    @Autowired
    CountryDAOImpl countryDAOImpl;
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

    @RequestMapping(value = "/salespipline/customer/update", method = RequestMethod.POST)
    public String createCustomerView(@ModelAttribute("accountForm") Account account, Map<String, Object> model, HttpSession session) {
        CustomerFullProfileSearhDataBean dataBean = new CustomerFullProfileSearhDataBean();
        account.setSearhDataBean(dataBean);
        JSONArray objList = (JSONArray) session.getAttribute("userroletasklits");
        try {
            Account accountdata = checkPrivilage_btn(account, String.valueOf(MasterDataVarList.CCL_CODE_CUSTOMER_SECTION_ID), String.valueOf(MasterDataVarList.CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID), objList);
            this.setCreateViewComponenets(model, account);
            model.put("avn_create", accountdata.isSave_btn());
            model.put("AccountUpdateList",salespiplinedaoimpl.getAccountDetail(account));
        } catch (Exception exception) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, exception);
            exception.printStackTrace();
        }
        model.put("MAP", "CNAP");
        return "salespipline/customerUpdate";
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.bean.webserviceclient;

import com.avn.ccl.bean.serviceclient.fullprofile.BusinessesSubSectors;
import com.avn.ccl.bean.serviceclient.fullprofile.CustomerData;
import com.avn.ccl.bean.serviceclient.fullprofile.StakeholderFD;
import com.avn.ccl.bean.serviceclient.fullprofile.StakeholderSavings;
import com.avn.ccl.util.Common;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;

/**
 * @Author : Roshen Dilshan
 * @Document : CustomerFullProfileSearhDataBean
 * @Date : Jun 27, 2015 9:31:26 AM
 */
public class CustomerFullProfileSearhDataBean {

    private String account_category;
    private String account_category_name;
    private String account_category_type;
    private String account_category_type_name;
    private String customer_code_type_name;

    private BigInteger stakeholder_id_pk;
    private int mf_customer_id_pk;
    private String mf_customer_reference;
    private String stakeholder_reference_no;
    private String customer_code;
    private String title;
    private String initials;
    private String first_name;
    private String name_in_full;
    private String last_name;
    private String company_name;
    private String date_of_birth;
    private String marital_status;
    private String marital_status_description;
    private String gender;
    private String gender_description;
    private String mothers_maiden_name;
    private int is_black_listed;
    private String location_code;
    private String location;
    private String religion;
    private String sector_id_fk;
    private String customer_code_type;
    private int st_version;
    private int mf_version;
    private String black_list_date;
    private int is_in_crib;
    private int st_record_status;
    private int mf_record_status;
    private String mobile_no_1;
    private String mobile_no_2;
    private String email;
    private int csu_reference_code;
    private int group_code;
    private String csu_customer_code;
    private String pcsu_customer_code;
    private String spouse_nic_no;
    private String spouse_title;
    private String spouse_initials;
    private String spouse_firstname;
    private String spouse_lastname;
    private int relationship_type;
    private String product_type;
    private int is_leader;
    private String occupation;
    private String family_members;
    private String customer_type;
    private String csu_pcsu_name;
    private String csu_pcsu_code;
    private String csu_pcsu_customer_code;
    private String designation_code;
    private String designation_description;
    private String level_code;
    private String level_description;
    private String profession_code;
    private String profession_description;
    private String employeer;
    private String sector_code;
    private String sector_description;
    private Map<String, String> subSectorList;
    private String is_tax_payee;
    private String tax_number;
    private String cp_nic;
    private String cp_title;
    private String cp_name_in_full;
    private String cp_initial;
    private String cp_last_name;
    private String cp_preferred_name;
    private String cp_mobile_no;
    private String cp_email;
    private List<CustomerAddressBean> customerAddressBeans;
    private List<CustomerEducationBean> customerEducationBeans;
    private List<StakeHolderSavingsBean> stakeHolderSavingsBeans;
    private List<CustomerLoanBean> customerLoanBeans;
    private List<StakeHolderFDBean> stakeHolderFDBeans;

    private final String testObj = "{"
            + "    \"LOCATION_CODE\": \"019\","
            + "    \"FAMILY_MEMBERS\": \"\","
            + "    \"BLACK_LIST_DATE\": \"\","
            + "    \"STAKEHOLDER_ID_PK\": 303831,"
            + "    \"SPOUSE_TITLE\": \"MR.\","
            + "    \"FIRST_NAME\": \"\","
            + "    \"CSU_PCSU_CUSTOMER_CODE\": \"NY/MF/0041/0010\","
            + "    \"PCSU_CUSTOMER_CODE\": \"NY/MF/P0041/0010\","
            + "    \"MF_CUSTOMER_ID_PK\": 3618,"
            + "    \"MOBILE_NO_2\": \"\","
            + "    \"SPOUSE_LASTNAME\": \"N/A\","
            + "    \"MOBILE_NO_1\": \"\","
            + "    \"IS_LEADER\": 0,"
            + "    \"CUSTOMER_CODE_TYPE\": 1,"
            + "    \"OCCUPATION\": \"N/A\","
            + "    \"SECTOR_ID_FK\": \"\","
            + "    \"CSU_REFERENCE_CODE\": 1856,"
            + "    \"LAST_NAME\": \"AANANTHAJOTHY\","
            + "    \"PRODUCT_TYPE\": \"MF\","
            + "    \"SPOUSE_NIC_NO\": \"N/A\","
            + "    \"DATE_OF_BIRTH\": \"1975-02-15\","
            + "    \"customerAddressData\": {\"response\": {"
            + "        \"GS_DIVISION_ID_FK\": 1,"
            + "        \"IS_PREFFERED\": 1,"
            + "        \"PROVINCE_NAME\": \"\","
            + "        \"ADDRESS_LINE_3\": \"NELLIADY\","
            + "        \"ADDRESS_LINE_2\": \"KARAVEDDY SOUTH\","
            + "        \"CITY_ID_FK\": \"\","
            + "        \"ADDRESS_LINE_1\": \"UCHCHIL\","
            + "        \"PROVINCE_ID_FK\": \"\","
            + "        \"STAKEHOLDER_REFERENCE_NO\": \"STK303831\","
            + "        \"RECORD_STATUS\": 1,"
            + "        \"VERSION\": 1,"
            + "        \"GS_DIVISION_NAME\": \"SAMMANTHRANAPURA\","
            + "        \"COUNTRY_ID_FK\": 1,"
            + "        \"CITY_NAME\": \"\","
            + "        \"DISTRICT_ID_FK\": \"\","
            + "        \"ADDRESS_TYPE\": 1,"
            + "        \"DISTRICT_NAME\": \"\","
            + "        \"GPS\": \"\","
            + "        \"COUNTRY_NAME\": \"SRI LANKA\","
            + "        \"MF_CUSTOMER_REFERENCE\": \"\","
            + "        \"TYPE\": \"CO\","
            + "        \"FAX\": null,"
            + "        \"LAND_PHONE_NO\": \"\","
            + "        \"ADDRESS_ID_PK\": 703698,"
            + "        \"MF_CUSTOMER_CONTACTS_ID_PK\": \"\""
            + "    }},"
            + "    \"GROUP_CODE\": 13,"
            + "    \"SPOUSE_FIRSTNAME\": \"N/A\","
            + "    \"MARITAL_STATUS\": \"W\","
            + "    \"CSU_PCSU_NAME\": \"THIRUVALLUVAR\","
            + "    \"CSU_PCSU_CODE\": \"NY/MF/0041\","
            + "    \"MF_VERSION\": 0,"
            + "    \"CUSTOMER_TYPE\": \"CO\","
            + "    \"getCustomerLoan\": {\"response\": ["
            + "        {"
            + "            \"CUSTOMER_CODE\": \"755464694V\","
            + "            \"CONTRACT_DATE\": \"2013-03-27\","
            + "            \"OUTSTANDING_AMOUNT\": 0,"
            + "            \"ALIAS_NAME\": \"NELLYADI\","
            + "            \"CONTRACT_STATUS\": \"Settled\","
            + "            \"TOTAL_OFFERED_AMOUNT\": 102000,"
            + "            \"CS\": \"S\","
            + "            \"MODULE_CODE\": \"PW\","
            + "            \"TOTAL_ARREARS_AMOUNT\": 0,"
            + "            \"CONTRACT_NO\": \"NYPB03000502\""
            + "        },"
            + "        {"
            + "            \"CUSTOMER_CODE\": \"755464694V\","
            + "            \"CONTRACT_DATE\": \"2013-03-27\","
            + "            \"OUTSTANDING_AMOUNT\": 0,"
            + "            \"ALIAS_NAME\": \"NELLYADI\","
            + "            \"CONTRACT_STATUS\": \"Settled\","
            + "            \"TOTAL_OFFERED_AMOUNT\": 160000,"
            + "            \"CS\": \"S\","
            + "            \"MODULE_CODE\": \"PW\","
            + "            \"TOTAL_ARREARS_AMOUNT\": 0,"
            + "            \"CONTRACT_NO\": \"NYPB03000504\""
            + "        },"
            + "        {"
            + "            \"CUSTOMER_CODE\": \"755464694V\","
            + "            \"CONTRACT_DATE\": \"2013-04-01\","
            + "            \"OUTSTANDING_AMOUNT\": 18960,"
            + "            \"ALIAS_NAME\": \"NELLYADI\","
            + "            \"CONTRACT_STATUS\": \"Period Over\","
            + "            \"TOTAL_OFFERED_AMOUNT\": 12000,"
            + "            \"CS\": \"O\","
            + "            \"MODULE_CODE\": \"PW\","
            + "            \"TOTAL_ARREARS_AMOUNT\": 0,"
            + "            \"CONTRACT_NO\": \"NYPB03000545\""
            + "        },"
            + "        {"
            + "            \"CUSTOMER_CODE\": \"755464694V\","
            + "            \"CONTRACT_DATE\": \"2013-04-19\","
            + "            \"OUTSTANDING_AMOUNT\": 249991,"
            + "            \"ALIAS_NAME\": \"NELLYADI\","
            + "            \"CONTRACT_STATUS\": \"Period Over\","
            + "            \"TOTAL_OFFERED_AMOUNT\": 173000,"
            + "            \"CS\": \"O\","
            + "            \"MODULE_CODE\": \"PW\","
            + "            \"TOTAL_ARREARS_AMOUNT\": 0,"
            + "            \"CONTRACT_NO\": \"NYPB03000666\""
            + "        },"
            + "        {"
            + "            \"CUSTOMER_CODE\": \"755464694V\","
            + "            \"CONTRACT_DATE\": \"2013-10-09\","
            + "            \"OUTSTANDING_AMOUNT\": 3766,"
            + "            \"ALIAS_NAME\": \"NELLYADI\","
            + "            \"CONTRACT_STATUS\": \"Active\","
            + "            \"TOTAL_OFFERED_AMOUNT\": 50000,"
            + "            \"CS\": \"A\","
            + "            \"MODULE_CODE\": \"MF\","
            + "            \"TOTAL_ARREARS_AMOUNT\": 1098,"
            + "            \"CONTRACT_NO\": \"NYMF646296\""
            + "        },"
            + "        {"
            + "            \"CUSTOMER_CODE\": \"755464694V\","
            + "            \"CONTRACT_DATE\": \"2013-01-15\","
            + "            \"OUTSTANDING_AMOUNT\": 7337,"
            + "            \"ALIAS_NAME\": \"NELLYADI\","
            + "            \"CONTRACT_STATUS\": \"Settled\","
            + "            \"TOTAL_OFFERED_AMOUNT\": 25000,"
            + "            \"CS\": \"S\","
            + "            \"MODULE_CODE\": \"MF\","
            + "            \"TOTAL_ARREARS_AMOUNT\": 0,"
            + "            \"CONTRACT_NO\": \"NYMF563306\""
            + "        },"
            + "        {"
            + "            \"CUSTOMER_CODE\": \"755464694V\","
            + "            \"CONTRACT_DATE\": \"2011-07-11\","
            + "            \"OUTSTANDING_AMOUNT\": 7337,"
            + "            \"ALIAS_NAME\": \"NELLYADI\","
            + "            \"CONTRACT_STATUS\": \"Settled\","
            + "            \"TOTAL_OFFERED_AMOUNT\": 25000,"
            + "            \"CS\": \"S\","
            + "            \"MODULE_CODE\": \"MF\","
            + "            \"TOTAL_ARREARS_AMOUNT\": 0,"
            + "            \"CONTRACT_NO\": \"NYMF085943\""
            + "        },"
            + "        {"
            + "            \"CUSTOMER_CODE\": \"755464694V\","
            + "            \"CONTRACT_DATE\": \"2012-04-03\","
            + "            \"OUTSTANDING_AMOUNT\": 17342,"
            + "            \"ALIAS_NAME\": \"NELLYADI\","
            + "            \"CONTRACT_STATUS\": \"Settled\","
            + "            \"TOTAL_OFFERED_AMOUNT\": 50000,"
            + "            \"CS\": \"S\","
            + "            \"MODULE_CODE\": \"MF\","
            + "            \"TOTAL_ARREARS_AMOUNT\": 0,"
            + "            \"CONTRACT_NO\": \"NYMF366136\""
            + "        }"
            + "    ]},"
            + "    \"RELATIONSHIP_TYPE\": 1,"
            + "    \"STAKEHOLDER_REFERENCE_NO\": \"STK303831\","
            + "    \"stakeholderRelations\": \"\","
            + "    \"MF_RECORD_STATUS\": 1,"
            + "    \"CSU_CUSTOMER_CODE\": \"NY/MF/0041/0010\","
            + "    \"EMAIL\": \"\","
            + "    \"stakeholderSavingsList\": {\"response\": {"
            + "        \"LAST_DEPOSIT_DATE\": \"2014-08-06\","
            + "        \"FREEZE_AMOUNT\": 0,"
            + "        \"STAKE_HOLDER_REFERENCE\": \"STK303831\","
            + "        \"EFFECTIVE_START_DATE\": \"2011-07-07\","
            + "        \"LAST_WITHDRAWAL_DATE\": \"2012-12-18\","
            + "        \"ACCOUNT_NUMBER\": \"DS077509\","
            + "        \"EFFECTIVE_END_DATE\": \"2100-12-31\","
            + "        \"PRODUCT_CODE\": \"DS\","
            + "        \"AVAILABLE_BALANCE\": 10617.36,"
            + "        \"CURRENT_BALANCE\": 10617.36,"
            + "        \"STATUS\": \"A\""
            + "    }},"
            + "    \"ST_VERSION\": 1,"
            + "    \"IS_BLACK_LISTED\": 0,"
            + "    \"CUSTOMER_CODE\": \"755464694V\","
            + "    \"SPOUSE_INITIALS\": \"N/A\","
            + "    \"customerBusinessData\": \"\","
            + "    \"ST_RECORD_STATUS\": 1,"
            + "    \"INITIALS\": \"S.\","
            + "    \"LOCATION\": \"NELLYADI\","
            + "    \"GENDER\": \"F\","
            + "    \"NAME_IN_FULL\": \"SIVAPIRAKASAM AANANTHAJOTHY\","
            + "    \"RELIGION\": \"\","
            + "    \"IS_IN_CRIB\": 0,"
            + "    \"MF_CUSTOMER_REFERENCE\": \"MF3618\","
            + "    \"TITLE\": \"MRS.\""
            + "}";

    //added by hirantha for testing purposes
    public String getJsonString() {
        return testObj;
    }

    /**
     * @return the stakeholder_id_pk
     */
    public BigInteger getStakeholder_id_pk() {
        return stakeholder_id_pk;
    }

    /**
     * @param stakeholder_id_pk the stakeholder_id_pk to set
     */
    public void setStakeholder_id_pk(BigInteger stakeholder_id_pk) {
        this.stakeholder_id_pk = stakeholder_id_pk;
    }

    /**
     * @return the mf_customer_id_pk
     */
    public int getMf_customer_id_pk() {
        return mf_customer_id_pk;
    }

    /**
     * @param mf_customer_id_pk the mf_customer_id_pk to set
     */
    public void setMf_customer_id_pk(int mf_customer_id_pk) {
        this.mf_customer_id_pk = mf_customer_id_pk;
    }

    /**
     * @return the mf_customer_reference
     */
    public String getMf_customer_reference() {
        return mf_customer_reference;
    }

    /**
     * @param mf_customer_reference the mf_customer_reference to set
     */
    public void setMf_customer_reference(String mf_customer_reference) {
        this.mf_customer_reference = mf_customer_reference;
    }

    /**
     * @return the stakeholder_reference_no
     */
    public String getStakeholder_reference_no() {
        return stakeholder_reference_no;
    }

    /**
     * @param stakeholder_reference_no the stakeholder_reference_no to set
     */
    public void setStakeholder_reference_no(String stakeholder_reference_no) {
        this.stakeholder_reference_no = stakeholder_reference_no;
    }

    /**
     * @return the customer_code
     */
    public String getCustomer_code() {
        return customer_code;
    }

    /**
     * @param customer_code the customer_code to set
     */
    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the initials
     */
    public String getInitials() {
        return initials;
    }

    /**
     * @param initials the initials to set
     */
    public void setInitials(String initials) {
        this.initials = initials;
    }

    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the name_in_full
     */
    public String getName_in_full() {
        return name_in_full;
    }

    /**
     * @param name_in_full the name_in_full to set
     */
    public void setName_in_full(String name_in_full) {
        this.name_in_full = name_in_full;
    }

    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the date_of_birth
     */
    public String getDate_of_birth() {
        return date_of_birth;
    }

    /**
     * @param date_of_birth the date_of_birth to set
     */
    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    /**
     * @return the marital_status
     */
    public String getMarital_status() {
        return marital_status;
    }

    /**
     * @param marital_status the marital_status to set
     */
    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
        switch (marital_status) {
            case "M":
                marital_status_description = "Married";
                break;
            case "S":
                marital_status_description = "Single";
                break;
            case "D":
                marital_status_description = "Divorced";
                break;
            case "W":
                marital_status_description = "Widowed";
                break;
            case "U":
                marital_status_description = "Undisclosed";
                break;
        }
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
        if (gender.contentEquals("M")) {
            gender_description = "Male";
        } else if (gender.contentEquals("F")) {
            gender_description = "Female";
        }
    }

    /**
     * @return the is_black_listed
     */
    public int getIs_black_listed() {
        return is_black_listed;
    }

    /**
     * @param is_black_listed the is_black_listed to set
     */
    public void setIs_black_listed(int is_black_listed) {
        this.is_black_listed = is_black_listed;
    }

    /**
     * @return the location_code
     */
    public String getLocation_code() {
        return location_code;
    }

    /**
     * @param location_code the location_code to set
     */
    public void setLocation_code(String location_code) {
        this.location_code = location_code;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return the sector_id_fk
     */
    public String getSector_id_fk() {
        return sector_id_fk;
    }

    /**
     * @param sector_id_fk the sector_id_fk to set
     */
    public void setSector_id_fk(String sector_id_fk) {
        this.sector_id_fk = sector_id_fk;
    }

    /**
     * @return the customer_code_type
     */
    public String getCustomer_code_type() {
        return customer_code_type;
    }

    /**
     * @param customer_code_type the customer_code_type to set
     */
    public void setCustomer_code_type(String customer_code_type) {
        this.customer_code_type = customer_code_type;
    }

    /**
     * @return the st_version
     */
    public int getSt_version() {
        return st_version;
    }

    /**
     * @param st_version the st_version to set
     */
    public void setSt_version(int st_version) {
        this.st_version = st_version;
    }

    /**
     * @return the mf_version
     */
    public int getMf_version() {
        return mf_version;
    }

    /**
     * @param mf_version the mf_version to set
     */
    public void setMf_version(int mf_version) {
        this.mf_version = mf_version;
    }

    /**
     * @return the black_list_date
     */
    public String getBlack_list_date() {
        return black_list_date;
    }

    /**
     * @param black_list_date the black_list_date to set
     */
    public void setBlack_list_date(String black_list_date) {
        this.black_list_date = black_list_date;
    }

    /**
     * @return the is_in_crib
     */
    public int getIs_in_crib() {
        return is_in_crib;
    }

    /**
     * @param is_in_crib the is_in_crib to set
     */
    public void setIs_in_crib(int is_in_crib) {
        this.is_in_crib = is_in_crib;
    }

    /**
     * @return the st_record_status
     */
    public int getSt_record_status() {
        return st_record_status;
    }

    /**
     * @param st_record_status the st_record_status to set
     */
    public void setSt_record_status(int st_record_status) {
        this.st_record_status = st_record_status;
    }

    /**
     * @return the mf_record_status
     */
    public int getMf_record_status() {
        return mf_record_status;
    }

    /**
     * @param mf_record_status the mf_record_status to set
     */
    public void setMf_record_status(int mf_record_status) {
        this.mf_record_status = mf_record_status;
    }

    /**
     * @return the mobile_no_1
     */
    public String getMobile_no_1() {
        return mobile_no_1;
    }

    /**
     * @param mobile_no_1 the mobile_no_1 to set
     */
    public void setMobile_no_1(String mobile_no_1) {
        this.mobile_no_1 = mobile_no_1;
    }

    /**
     * @return the mobile_no_2
     */
    public String getMobile_no_2() {
        return mobile_no_2;
    }

    /**
     * @param mobile_no_2 the mobile_no_2 to set
     */
    public void setMobile_no_2(String mobile_no_2) {
        this.mobile_no_2 = mobile_no_2;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the csu_reference_code
     */
    public int getCsu_reference_code() {
        return csu_reference_code;
    }

    /**
     * @param csu_reference_code the csu_reference_code to set
     */
    public void setCsu_reference_code(int csu_reference_code) {
        this.csu_reference_code = csu_reference_code;
    }

    /**
     * @return the group_code
     */
    public int getGroup_code() {
        return group_code;
    }

    /**
     * @param group_code the group_code to set
     */
    public void setGroup_code(int group_code) {
        this.group_code = group_code;
    }

    /**
     * @return the csu_customer_code
     */
    public String getCsu_customer_code() {
        return csu_customer_code;
    }

    /**
     * @param csu_customer_code the csu_customer_code to set
     */
    public void setCsu_customer_code(String csu_customer_code) {
        this.csu_customer_code = csu_customer_code;
    }

    /**
     * @return the pcsu_customer_code
     */
    public String getPcsu_customer_code() {
        return pcsu_customer_code;
    }

    /**
     * @param pcsu_customer_code the pcsu_customer_code to set
     */
    public void setPcsu_customer_code(String pcsu_customer_code) {
        this.pcsu_customer_code = pcsu_customer_code;
    }

    /**
     * @return the spouse_nic_no
     */
    public String getSpouse_nic_no() {
        return spouse_nic_no;
    }

    /**
     * @param spouse_nic_no the spouse_nic_no to set
     */
    public void setSpouse_nic_no(String spouse_nic_no) {
        this.spouse_nic_no = spouse_nic_no;
    }

    /**
     * @return the spouse_title
     */
    public String getSpouse_title() {
        return spouse_title;
    }

    /**
     * @param spouse_title the spouse_title to set
     */
    public void setSpouse_title(String spouse_title) {
        this.spouse_title = spouse_title;
    }

    /**
     * @return the spouse_initials
     */
    public String getSpouse_initials() {
        return spouse_initials;
    }

    /**
     * @param spouse_initials the spouse_initials to set
     */
    public void setSpouse_initials(String spouse_initials) {
        this.spouse_initials = spouse_initials;
    }

    /**
     * @return the spouse_firstname
     */
    public String getSpouse_firstname() {
        return spouse_firstname;
    }

    /**
     * @param spouse_firstname the spouse_firstname to set
     */
    public void setSpouse_firstname(String spouse_firstname) {
        this.spouse_firstname = spouse_firstname;
    }

    /**
     * @return the spouse_lastname
     */
    public String getSpouse_lastname() {
        return spouse_lastname;
    }

    /**
     * @param spouse_lastname the spouse_lastname to set
     */
    public void setSpouse_lastname(String spouse_lastname) {
        this.spouse_lastname = spouse_lastname;
    }

    /**
     * @return the relationship_type
     */
    public int getRelationship_type() {
        return relationship_type;
    }

    /**
     * @param relationship_type the relationship_type to set
     */
    public void setRelationship_type(int relationship_type) {
        this.relationship_type = relationship_type;
    }

    /**
     * @return the product_type
     */
    public String getProduct_type() {
        return product_type;
    }

    /**
     * @param product_type the product_type to set
     */
    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    /**
     * @return the is_leader
     */
    public int getIs_leader() {
        return is_leader;
    }

    /**
     * @param is_leader the is_leader to set
     */
    public void setIs_leader(int is_leader) {
        this.is_leader = is_leader;
    }

    /**
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation the occupation to set
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * @return the family_members
     */
    public String getFamily_members() {
        return family_members;
    }

    /**
     * @param family_members the family_members to set
     */
    public void setFamily_members(String family_members) {
        this.family_members = family_members;
    }

    /**
     * @return the customer_type
     */
    public String getCustomer_type() {
        return customer_type;
    }

    /**
     * @param customer_type the customer_type to set
     */
    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    /**
     * @return the csu_pcsu_name
     */
    public String getCsu_pcsu_name() {
        return csu_pcsu_name;
    }

    /**
     * @param csu_pcsu_name the csu_pcsu_name to set
     */
    public void setCsu_pcsu_name(String csu_pcsu_name) {
        this.csu_pcsu_name = csu_pcsu_name;
    }

    /**
     * @return the csu_pcsu_code
     */
    public String getCsu_pcsu_code() {
        return csu_pcsu_code;
    }

    /**
     * @param csu_pcsu_code the csu_pcsu_code to set
     */
    public void setCsu_pcsu_code(String csu_pcsu_code) {
        this.csu_pcsu_code = csu_pcsu_code;
    }

    /**
     * @return the csu_pcsu_customer_code
     */
    public String getCsu_pcsu_customer_code() {
        return csu_pcsu_customer_code;
    }

    /**
     * @param csu_pcsu_customer_code the csu_pcsu_customer_code to set
     */
    public void setCsu_pcsu_customer_code(String csu_pcsu_customer_code) {
        this.csu_pcsu_customer_code = csu_pcsu_customer_code;
    }

    /**
     * @return the customerAddressBeans
     */
    public List<CustomerAddressBean> getCustomerAddressBeans() {
        if (customerAddressBeans == null) {
            customerAddressBeans = new ArrayList<CustomerAddressBean>();
        }
        return customerAddressBeans;
    }

    /**
     * @param customerAddressBeans the customerAddressBeans to set
     */
    public void setCustomerAddressBeans(List<CustomerAddressBean> customerAddressBeans) {
        this.customerAddressBeans = customerAddressBeans;
    }

    /**
     * @return the stakeHolderSavingsBeans
     */
    public List<StakeHolderSavingsBean> getStakeHolderSavingsBeans() {
        if (stakeHolderSavingsBeans == null) {
            stakeHolderSavingsBeans = new ArrayList<StakeHolderSavingsBean>();
        }
        return stakeHolderSavingsBeans;
    }

    /**
     * @param stakeHolderSavingsBeans the stakeHolderSavingsBeans to set
     */
    public void setStakeHolderSavingsBeans(List<StakeHolderSavingsBean> stakeHolderSavingsBeans) {
        this.stakeHolderSavingsBeans = stakeHolderSavingsBeans;
    }

    /**
     * @return the customerLoanBeans
     */
    public List<CustomerLoanBean> getCustomerLoanBeans() {
        if (customerLoanBeans == null) {
            customerLoanBeans = new ArrayList<CustomerLoanBean>();
        }
        return customerLoanBeans;
    }

    /**
     * @param customerLoanBeans the customerLoanBeans to set
     */
    public void setCustomerLoanBeans(List<CustomerLoanBean> customerLoanBeans) {
        this.customerLoanBeans = customerLoanBeans;
    }

//    public void setJsonDataToObject(JSONObject jsonObject) {
//        jsonObject = new JSONObject(this.testObj);
//        System.out.println("Full Object :: " + jsonObject.toString(4));
//        this.setStakeholder_id_pk(jsonObject.getLong("STAKEHOLDER_ID_PK"));
//        this.setMf_customer_id_pk(jsonObject.getInt("MF_CUSTOMER_ID_PK"));
//        this.setMf_customer_reference(jsonObject.getString("MF_CUSTOMER_REFERENCE"));
//        this.setStakeholder_reference_no(jsonObject.getString("STAKEHOLDER_REFERENCE_NO"));
//        this.setCustomer_code(jsonObject.getString("CUSTOMER_CODE"));
//        this.setTitle(jsonObject.getString("TITLE"));
//        this.setInitials(jsonObject.getString("INITIALS"));
//        this.setFirst_name(jsonObject.getString("FIRST_NAME"));
//        this.setName_in_full(jsonObject.getString("NAME_IN_FULL"));
//        this.setLast_name(jsonObject.getString("LAST_NAME"));
//        this.setDate_of_birth(jsonObject.getString("DATE_OF_BIRTH"));
//        this.setMarital_status(jsonObject.getString("MARITAL_STATUS"));
//        this.setGender(jsonObject.getString("GENDER"));
//        this.setIs_black_listed(jsonObject.getInt("IS_BLACK_LISTED"));
//        this.setLocation_code(jsonObject.getString("LOCATION_CODE"));
//        this.setLocation(jsonObject.getString("LOCATION"));
//        this.setReligion(String.valueOf(jsonObject.getInt("RELIGION")));
//        this.setSector_id_fk(jsonObject.getString("SECTOR_ID_FK"));
//        this.setCustomer_code_type(jsonObject.getInt("CUSTOMER_CODE_TYPE"));
//        this.setSt_version(jsonObject.getInt("ST_VERSION"));
//        this.setMf_version(jsonObject.getInt("MF_VERSION"));
//        this.setBlack_list_date(String.valueOf(jsonObject.getInt("BLACK_LIST_DATE")));
//        this.setIs_in_crib(jsonObject.getInt("IS_IN_CRIB"));
//        this.setSt_record_status(jsonObject.getInt("ST_RECORD_STATUS"));
//        this.setMf_record_status(jsonObject.getInt("MF_RECORD_STATUS"));
//        this.setMobile_no_1(jsonObject.getString("MOBILE_NO_1"));
//        this.setMobile_no_2(jsonObject.getString("MOBILE_NO_2"));
//        this.setEmail(jsonObject.getString("EMAIL"));
//        this.setCsu_reference_code(jsonObject.getInt("CSU_REFERENCE_CODE"));
//        this.setGroup_code(jsonObject.getInt("GROUP_CODE"));
//        this.setCsu_customer_code(jsonObject.getString("CSU_CUSTOMER_CODE"));
//        this.setPcsu_customer_code(jsonObject.getString("PCSU_CUSTOMER_CODE"));
//        this.setSpouse_nic_no(jsonObject.getString("SPOUSE_NIC_NO"));
//        this.setSpouse_title(jsonObject.getString("SPOUSE_TITLE"));
//        this.setSpouse_initials(jsonObject.getString("SPOUSE_INITIALS"));
//        this.setSpouse_firstname(jsonObject.getString("SPOUSE_FIRSTNAME"));
//        this.setSpouse_lastname(jsonObject.getString("SPOUSE_LASTNAME"));
//        this.setRelationship_type(jsonObject.getInt("RELATIONSHIP_TYPE"));
//        this.setProduct_type(jsonObject.getString("PRODUCT_TYPE"));
//        this.setIs_leader(jsonObject.getInt("IS_LEADER"));
//        this.setOccupation(jsonObject.getString("OCCUPATION"));
//        this.setFamily_members(jsonObject.getString("FAMILY_MEMBERS"));
//        this.setCustomer_type(jsonObject.getString("CUSTOMER_TYPE"));
//        this.setCsu_pcsu_name(jsonObject.getString("CSU_PCSU_NAME"));
//        this.setCsu_pcsu_code(jsonObject.getString("CSU_PCSU_CODE"));
//        this.setCsu_pcsu_customer_code(jsonObject.getString("CSU_PCSU_CUSTOMER_CODE"));
//        // Customer Address List
//        List<CustomerAddressBean> addressBeans = this.getCustomerAddressBeans();
//        CustomerAddressBean addressBean = null;
//
//        // Customer Savings List
//        List<StakeHolderSavingsBean> savingsBeans = this
//                .getStakeHolderSavingsBeans();
//        StakeHolderSavingsBean savingsBean = null;
//
//        // Customer Loan List
//        List<CustomerLoanBean> loanBeans = this.getCustomerLoanBeans();
//        CustomerLoanBean loanBean = null;
//
//        // To Generate Customer Address List
//        try {
//            JSONArray array = jsonObject.getJSONObject("customerAddressData").getJSONArray("response");
//            for (int i = 0; i < array.length(); i++) {
//                addressBean = new CustomerAddressBean();
//                addressBean.setAddress_id_view("ADDRESS" + i);
//                addressBean.setAddress_id_pk(array.getJSONObject(i).getLong("ADDRESS_ID_PK"));
//                addressBean.setMf_customer_reference(array.getJSONObject(i).getString("MF_CUSTOMER_REFERENCE"));
//                addressBean.setMf_customer_contacts_id_pk(array.getJSONObject(i).getString("MF_CUSTOMER_CONTACTS_ID_PK"));
//                addressBean.setStakeholder_reference_no(array.getJSONObject(i).getString("STAKEHOLDER_REFERENCE_NO"));
//                addressBean.setAddress_line_1(array.getJSONObject(i).getString("ADDRESS_LINE_1"));
//                addressBean.setAddress_line_2(array.getJSONObject(i).getString("ADDRESS_LINE_2"));
//                addressBean.setAddress_line_3(array.getJSONObject(i).getString("ADDRESS_LINE_3"));
//                addressBean.setAddress_type(array.getJSONObject(i).getInt("ADDRESS_TYPE"));
//                addressBean.setCity_id_fk(array.getJSONObject(i).getString("CITY_ID_FK"));
//                addressBean.setCity_id_fk(array.getJSONObject(i).getString("CITY_ID_FK"));
//                addressBean.setCity_name(array.getJSONObject(i).getString("CITY_NAME"));
//                addressBean.setGs_division_id_fk(array.getJSONObject(i).getInt("GS_DIVISION_ID_FK"));
//                addressBean.setGs_division_name(array.getJSONObject(i).getString("GS_DIVISION_NAME"));
//                addressBean.setDistrict_id_fk(array.getJSONObject(i).getString("DISTRICT_ID_FK"));
//                addressBean.setDistrict_name(array.getJSONObject(i).getString("DISTRICT_NAME"));
//                addressBean.setProvince_id_fk(array.getJSONObject(i).getString("PROVINCE_ID_FK"));
//                addressBean.setProvince_name(array.getJSONObject(i).getString("PROVINCE_NAME"));
//                addressBean.setCountry_id_fk(array.getJSONObject(i).getInt("COUNTRY_ID_FK"));
//                addressBean.setCountry_name(array.getJSONObject(i).getString("COUNTRY_NAME"));
//                addressBean.setGps(array.getJSONObject(i).getString("GPS"));
//                addressBean.setIs_preffered(array.getJSONObject(i).getInt("IS_PREFFERED"));
//                addressBean.setLand_phone_no(array.getJSONObject(i).getString("LAND_PHONE_NO"));
//                Object faxvalue = array.getJSONObject(i).get("FAX");
//                addressBean.setFax(faxvalue.toString());
//                addressBean.setVersion(array.getJSONObject(i).getInt("VERSION"));
//                addressBean.setType(array.getJSONObject(i).getString("TYPE"));
//                addressBean.setRecord_status(array.getJSONObject(i).getInt("RECORD_STATUS"));
//                addressBeans.add(addressBean);
//            }
//        } catch (Exception exception) {
//            System.out.println("(Array)Address List Convert Error :: " + exception.getMessage());
//        }
//
//        if (addressBeans.isEmpty()) {
//            try {
//                JSONObject object = jsonObject.getJSONObject("customerAddressData").getJSONObject("response");
//                if (object.length() > 0) {
//                    addressBean = new CustomerAddressBean();
//                    addressBean.setAddress_id_view("ADDRESS1");
//                    addressBean.setAddress_id_pk(object.getLong("ADDRESS_ID_PK"));
//                    addressBean.setMf_customer_reference(object.getString("MF_CUSTOMER_REFERENCE"));
//                    addressBean.setMf_customer_contacts_id_pk(object.getString("MF_CUSTOMER_CONTACTS_ID_PK"));
//                    addressBean.setStakeholder_reference_no(object.getString("STAKEHOLDER_REFERENCE_NO"));
//                    addressBean.setAddress_line_1(object.getString("ADDRESS_LINE_1"));
//                    addressBean.setAddress_line_2(object.getString("ADDRESS_LINE_2"));
//                    addressBean.setAddress_line_3(object.getString("ADDRESS_LINE_3"));
//                    addressBean.setAddress_type(object.getInt("ADDRESS_TYPE"));
//                    addressBean.setCity_id_fk(object.getString("CITY_ID_FK"));
//                    addressBean.setCity_id_fk(object.getString("CITY_ID_FK"));
//                    addressBean.setCity_name(object.getString("CITY_NAME"));
//                    addressBean.setGs_division_id_fk(object.getInt("GS_DIVISION_ID_FK"));
//                    addressBean.setGs_division_name(object.getString("GS_DIVISION_NAME"));
//                    addressBean.setDistrict_id_fk(object.getString("DISTRICT_ID_FK"));
//                    addressBean.setDistrict_name(object.getString("DISTRICT_NAME"));
//                    addressBean.setProvince_id_fk(object.getString("PROVINCE_ID_FK"));
//                    addressBean.setProvince_name(object.getString("PROVINCE_NAME"));
//                    addressBean.setCountry_id_fk(object.getInt("COUNTRY_ID_FK"));
//                    addressBean.setCountry_name(object.getString("COUNTRY_NAME"));
//                    addressBean.setGps(object.getString("GPS"));
//                    addressBean.setIs_preffered(object.getInt("IS_PREFFERED"));
//                    addressBean.setLand_phone_no(object.getString("LAND_PHONE_NO"));
//                    Object faxvalue = object.get("FAX");
//                    addressBean.setFax(faxvalue.toString());
//                    addressBean.setVersion(object.getInt("VERSION"));
//                    addressBean.setType(object.getString("TYPE"));
//                    addressBean.setRecord_status(object.getInt("RECORD_STATUS"));
//                    addressBeans.add(addressBean);
//                }
//            } catch (Exception exception) {
//                System.out.println("(Object)Address List Convert Error :: " + exception.getMessage());
//            }
//        }
//
//        // To Generate Customer Savings List
//        try {
//            JSONArray array = jsonObject.getJSONObject("stakeholderSavingsList").getJSONArray("response");
//            for (int i = 0; i < array.length(); i++) {
//                savingsBean = new StakeHolderSavingsBean();
//                savingsBean.setSavings_id_view("SAVING" + i);
//                savingsBean.setAccount_number(array.getJSONObject(i).getString("ACCOUNT_NUMBER"));
//                savingsBean.setProduct_code(array.getJSONObject(i).getString("PRODUCT_CODE"));
//                savingsBean.setStake_holder_reference(array.getJSONObject(i).getString("STAKE_HOLDER_REFERENCE"));
//                BigDecimal currentBalance = new BigDecimal(array.getJSONObject(i).getDouble("CURRENT_BALANCE"));
//                currentBalance = currentBalance.setScale(2, RoundingMode.CEILING);
//                savingsBean.setCurrent_balance(currentBalance);
//                savingsBean.setLast_deposit_date(array.getJSONObject(i).getString("LAST_DEPOSIT_DATE"));
//                BigDecimal freezeAmount = new BigDecimal(array.getJSONObject(i).getDouble("FREEZE_AMOUNT"));
//                freezeAmount = freezeAmount.setScale(2, RoundingMode.CEILING);
//                savingsBean.setFreeze_amount(freezeAmount);
//                BigDecimal availableBlance = new BigDecimal(array.getJSONObject(i).getDouble("AVAILABLE_BALANCE"));
//                availableBlance = availableBlance.setScale(2, RoundingMode.CEILING);
//                savingsBean.setAvailable_balance(availableBlance);
//                savingsBean.setStatus(array.getJSONObject(i).getString("STATUS"));
//                savingsBean.setLast_deposit_date(array.getJSONObject(i).getString("LAST_DEPOSIT_DATE"));
//                savingsBean.setLast_withdrawal_date(array.getJSONObject(i).getString("LAST_WITHDRAWAL_DATE"));
//                savingsBean.setEffective_start_date(array.getJSONObject(i).getString("EFFECTIVE_START_DATE"));
//                savingsBean.setEffective_end_date(array.getJSONObject(i).getString("EFFECTIVE_END_DATE"));
//                savingsBeans.add(savingsBean);
//            }
//        } catch (Exception exception) {
//            System.out.println("(Array)Savings List Convert Error :: " + exception.getMessage());
//        }
//
//        if (savingsBeans.isEmpty()) {
//            try {
//                JSONObject object = jsonObject.getJSONObject("stakeholderSavingsList").getJSONObject("response");
//                if (object.length() > 0) {
//                    savingsBean = new StakeHolderSavingsBean();
//                    savingsBean.setSavings_id_view("SAVING1");
//                    savingsBean.setAccount_number(object.getString("ACCOUNT_NUMBER"));
//                    savingsBean.setProduct_code(object.getString("PRODUCT_CODE"));
//                    savingsBean.setStake_holder_reference(object.getString("STAKE_HOLDER_REFERENCE"));
//                    BigDecimal currentBalance = new BigDecimal(object.getDouble("CURRENT_BALANCE"));
//                    currentBalance = currentBalance.setScale(2, RoundingMode.CEILING);
//                    savingsBean.setCurrent_balance(currentBalance);
//                    savingsBean.setLast_deposit_date(object.getString("LAST_DEPOSIT_DATE"));
//                    BigDecimal freezeAmount = new BigDecimal(object.getDouble("FREEZE_AMOUNT"));
//                    freezeAmount = freezeAmount.setScale(2, RoundingMode.CEILING);
//                    savingsBean.setFreeze_amount(freezeAmount);
//                    BigDecimal availableBlance = new BigDecimal(object.getDouble("AVAILABLE_BALANCE"));
//                    availableBlance = availableBlance.setScale(2, RoundingMode.CEILING);
//                    savingsBean.setAvailable_balance(availableBlance);
//                    savingsBean.setStatus(object.getString("STATUS"));
//                    savingsBean.setLast_deposit_date(object.getString("LAST_DEPOSIT_DATE"));
//                    savingsBean.setLast_withdrawal_date(object.getString("LAST_WITHDRAWAL_DATE"));
//                    savingsBean.setEffective_start_date(object.getString("EFFECTIVE_START_DATE"));
//                    savingsBean.setEffective_end_date(object.getString("EFFECTIVE_END_DATE"));
//                    savingsBeans.add(savingsBean);
//                }
//            } catch (Exception exception) {
//                System.out.println("(Object)Savings List Convert Error :: " + exception.getMessage());
//            }
//        }
//
//        // To Generate Customer Loan List
//        try {
//            JSONArray array = jsonObject.getJSONObject("getCustomerLoan").getJSONArray("response");
//            for (int i = 0; i < array.length(); i++) {
//                loanBean = new CustomerLoanBean();
//                loanBean.setLoan_id_view("LOAN" + i);
//                loanBean.setCs(array.getJSONObject(i).getString("CS"));
//                loanBean.setModule_code(array.getJSONObject(i).getString("MODULE_CODE"));
//                loanBean.setCustomer_code(array.getJSONObject(i).getString("CUSTOMER_CODE"));
//                loanBean.setContract_no(array.getJSONObject(i).getString("CONTRACT_NO"));
//                loanBean.setAlias_name(array.getJSONObject(i).getString("ALIAS_NAME"));
//                loanBean.setContract_date(array.getJSONObject(i).getString("CONTRACT_DATE"));
//                BigDecimal totalOfferedAmount = new BigDecimal(array.getJSONObject(i).getDouble("TOTAL_OFFERED_AMOUNT"));
//                totalOfferedAmount = totalOfferedAmount.setScale(2, RoundingMode.CEILING);
//                loanBean.setTotal_offered_amount(totalOfferedAmount);
//                loanBean.setContract_status(array.getJSONObject(i).getString("CONTRACT_STATUS"));
//                BigDecimal totalArrearsAmount = new BigDecimal(array.getJSONObject(i).getDouble("TOTAL_ARREARS_AMOUNT"));
//                totalArrearsAmount = totalArrearsAmount.setScale(2, RoundingMode.CEILING);
//                loanBean.setTotal_arrears_amount(totalArrearsAmount);
//                BigDecimal outtandingAmount = new BigDecimal(array.getJSONObject(i).getDouble("OUTSTANDING_AMOUNT"));
//                outtandingAmount = outtandingAmount.setScale(2, RoundingMode.CEILING);
//                loanBean.setOutstanding_amount(outtandingAmount);
//                loanBeans.add(loanBean);
//            }
//        } catch (Exception exception) {
//            System.out.println("(Array)Loan List Convert Error :: " + exception.getMessage());
//        }
//
//        if (loanBeans.isEmpty()) {
//            try {
//                JSONObject object = jsonObject.getJSONObject("getCustomerLoan").getJSONObject("response");
//                if (object.length() > 0) {
//                    loanBean = new CustomerLoanBean();
//                    loanBean.setLoan_id_view("LOAN1");
//                    loanBean.setCs(object.getString("CS"));
//                    loanBean.setModule_code(object.getString("MODULE_CODE"));
//                    loanBean.setCustomer_code(object.getString("CUSTOMER_CODE"));
//                    loanBean.setContract_no(object.getString("CONTRACT_NO"));
//                    loanBean.setAlias_name(object.getString("ALIAS_NAME"));
//                    loanBean.setContract_date(object.getString("CONTRACT_DATE"));
//                    BigDecimal totalOfferedAmount = new BigDecimal(object.getDouble("TOTAL_OFFERED_AMOUNT"));
//                    totalOfferedAmount = totalOfferedAmount.setScale(2, RoundingMode.CEILING);
//                    loanBean.setTotal_offered_amount(totalOfferedAmount);
//                    loanBean.setContract_status(object.getString("CONTRACT_STATUS"));
//                    BigDecimal totalArrearsAmount = new BigDecimal(object.getDouble("TOTAL_ARREARS_AMOUNT"));
//                    totalArrearsAmount = totalArrearsAmount.setScale(2, RoundingMode.CEILING);
//                    loanBean.setTotal_arrears_amount(totalArrearsAmount);
//                    BigDecimal outtandingAmount = new BigDecimal(object.getDouble("OUTSTANDING_AMOUNT"));
//                    outtandingAmount = outtandingAmount.setScale(2, RoundingMode.CEILING);
//                    loanBean.setOutstanding_amount(outtandingAmount);
//                    loanBeans.add(loanBean);
//                }
//            } catch (Exception exception) {
//                System.out.println("(Object)Loan List Convert Error :: " + exception.getMessage());
//            }
//        }
//    }
    /**
     * @return the gender_description
     */
    public String getGender_description() {
        return gender_description;
    }

    /**
     * @param gender_description the gender_description to set
     */
    public void setGender_description(String gender_description) {
        this.gender_description = gender_description;
    }

    /**
     * @return the marital_status_description
     */
    public String getMarital_status_description() {
        return marital_status_description;
    }

    /**
     * @param marital_status_description the marital_status_description to set
     */
    public void setMarital_status_description(String marital_status_description) {
        this.marital_status_description = marital_status_description;
    }

    public void setCustomerData(CustomerData customerData) {
        this.stakeholder_id_pk = customerData.getResponse().getSTAKEHOLDERIDPK();
        this.stakeholder_reference_no = customerData.getResponse().getSTAKEHOLDERREFERENCENO();

        byte category = customerData.getResponse().getISCOPERATEOPERATOR();
        this.account_category = String.valueOf(category);
//        switch (String.valueOf(category)) {
//            case MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_INDIVIDUAL:
//                this.account_category_name = "INDIVIDUAL";
//                break;
//            case MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_CORPORATE:
//                this.account_category_name = "CORPORATE";
//                break;
//            case MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_MISCELLANEOUS:
//                this.account_category_name = "MISCELLANEOUS";
//                break;
//        }

        byte categoryType = customerData.getResponse().getCATEGORYTYPE();
        this.account_category_type = String.valueOf(categoryType);
//        if (categoryType == 0) {
//            this.account_category_type_name = "NORMAL";
//        } else if (categoryType == 1) {
//            this.account_category_type_name = "MINOR";
//        } else if (categoryType == 2) {
//            this.account_category_type_name = "OPERATOR";
//        } else if (categoryType == 3) {
//            this.account_category_type_name = "NOMINEE";
//        } else if (categoryType == 4) {
//            this.account_category_type_name = "PARTNERSHIP";
//        } else if (categoryType == 5) {
//            this.account_category_type_name = "COMPANY";
//        } else if (categoryType == 6) {
//            this.account_category_type_name = "PROPRIETORSHIPS";
//        } else if (categoryType == 8) {
//            this.account_category_type_name = "ASSOCIATIONS";
//        } else if (categoryType == 9) {
//            this.account_category_type_name = "CLUBS & SOCIETIES";
//        } else if (categoryType == 10) {
//            this.account_category_type_name = "NGO";
//        }

        byte customerCodeType = customerData.getResponse().getCUSTOMERCODETYPE();
        this.customer_code_type = String.valueOf(customerCodeType);
//        if (customerCodeType == 1) {
//            this.customer_code_type_name = "NIC";
//        } else if (customerCodeType == 4) {
//            this.customer_code_type_name = "CCID";
//        } else if (customerCodeType == 6) {
//            this.customer_code_type_name = "BRN";
//        }

        this.customer_code = customerData.getResponse().getCUSTOMERCODE();
        this.location_code = customerData.getResponse().getLOCATIONCODE();
        this.location = customerData.getResponse().getLOCATION();

        if (String.valueOf(category).equalsIgnoreCase(MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_INDIVIDUAL)) {
            this.title = customerData.getResponse().getTITLE();
            this.name_in_full = customerData.getResponse().getNAMEINFULL();
            this.initials = customerData.getResponse().getINITIALS();
            this.first_name = customerData.getResponse().getFIRSTNAME();
            this.last_name = customerData.getResponse().getLASTNAME();
            this.date_of_birth = Common.getStringFormatDate("yyyy-MM-dd", customerData.getResponse().getDATEOFBIRTH().toGregorianCalendar().getTime());
            this.gender = customerData.getResponse().getGENDER();
            this.mothers_maiden_name = customerData.getResponse().getMOTHERSMAIDENNAME();
            this.religion = customerData.getResponse().getRELIGION() == null ? "" : customerData.getResponse().getRELIGION();
            this.marital_status = customerData.getResponse().getMARITALSTATUS() == null ? "" : customerData.getResponse().getMARITALSTATUS();
            this.mobile_no_1 = customerData.getResponse().getMOBILENO1() == null ? "" : customerData.getResponse().getMOBILENO1();
            this.mobile_no_2 = customerData.getResponse().getMOBILENO2() == null ? "" : customerData.getResponse().getMOBILENO2();
            this.email = customerData.getResponse().getEMAIL() == null ? "" : customerData.getResponse().getEMAIL();
        } else {
            this.company_name = customerData.getResponse().getLASTNAME();
        }

        List<CustomerData.Response.CustomerAddressData.AddressResponse> addressResponses = customerData.getResponse().getCustomerAddressData().getResponse();
        this.customerAddressBeans = new ArrayList<>();
        for (CustomerData.Response.CustomerAddressData.AddressResponse addressResponse : addressResponses) {
            CustomerAddressBean addressBean = new CustomerAddressBean();
            addressBean.setAddress_id_view(String.valueOf(addressResponse.getADDRESSIDPK()));
            addressBean.setAddress_type(String.valueOf(addressResponse.getADDRESSTYPE()));
            addressBean.setAddress_line_1(addressResponse.getADDRESSLINE1());
            addressBean.setAddress_line_2(addressResponse.getADDRESSLINE2());
            addressBean.setAddress_line_3(addressResponse.getADDRESSLINE3());
            addressBean.setCountry_id_fk(String.valueOf(addressResponse.getCOUNTRYIDFK()));
            addressBean.setCountry_name(addressResponse.getCOUNTRYNAME());
            addressBean.setProvince_id_fk(String.valueOf(addressResponse.getPROVINCEIDFK()));
            addressBean.setProvince_name(addressResponse.getPROVINCENAME());
            addressBean.setDistrict_id_fk(String.valueOf(addressResponse.getDISTRICTIDFK()));
            addressBean.setDistrict_name(addressResponse.getDISTRICTNAME());
            addressBean.setCity_id_fk(addressResponse.getCITYIDFK() == null ? "" : addressResponse.getCITYIDFK());
            addressBean.setCity_name(addressResponse.getCITYNAME() == null ? "" : addressResponse.getCITYNAME());
            addressBean.setGs_division_id_fk(addressResponse.getGSDIVISIONIDFK() == null ? "" : addressResponse.getGSDIVISIONIDFK());
            addressBean.setGs_division_name(addressResponse.getGSDIVISIONNAME() == null ? "" : addressResponse.getGSDIVISIONNAME());
            addressBean.setGps(addressResponse.getGPS() == null ? "" : addressResponse.getGPS());
            addressBean.setLand_phone_no(String.valueOf(addressResponse.getLANDPHONENO()));
            addressBean.setBilling_proof("");
            this.customerAddressBeans.add(addressBean);
        }

        List<CustomerData.Response.EducationDetails.EducationResponse> educationResponses = customerData.getResponse().getEducationDetails().getResponse();
        this.customerEducationBeans = new ArrayList<>();
        for (CustomerData.Response.EducationDetails.EducationResponse educationResponse : educationResponses) {
            CustomerEducationBean educationBean = new CustomerEducationBean();
            educationBean.setEducation_id_view(String.valueOf(educationResponse.getEDUCATIONDETAILIDPK()));
            educationBean.setEducationLevel(String.valueOf(educationResponse.getEDUCATIONLEVEL()));
            educationBean.setInstitute(educationResponse.getOTHERINSTITUTE() == null ? "" : educationResponse.getOTHERINSTITUTE());
            this.customerEducationBeans.add(educationBean);
        }

        if (customerData.getResponse().getCustomerBusinessData().getResponse() != null) {
            List businessRespnses = customerData.getResponse().getCustomerBusinessData().getResponse().getBUSINESSIDPKOrSTAKEHOLDERREFERENCENOOrBRN();
            for (Object object : businessRespnses) {
                if (object instanceof JAXBElement) {
                    JAXBElement element = (JAXBElement) object;
                    switch (element.getName().getLocalPart()) {
                        case "DESIGNATION_ID_FK":
                            this.designation_code = element.getValue() == null ? "" : element.getValue().toString();
                            break;
                        case "EMPLOYEE_LEVEL":
                            this.level_code = element.getValue() == null ? "" : element.getValue().toString();
                            break;
                        case "PROFFESION_ID_FK":
                            this.profession_code = element.getValue() == null ? "" : element.getValue().toString();
                            break;
                        case "OTHER_EMPLOYEMENT":
                            this.employeer = element.getValue() == null ? "" : element.getValue().toString();
                            break;
                        case "SECTOR_ID_FK":
                            this.sector_code = element.getValue() == null ? "" : element.getValue().toString();
                            break;
                        case "SECTOR_NAME":
                            this.sector_description = element.getValue() == null ? "" : element.getValue().toString();
                            break;
                    }
                } else if (object instanceof BusinessesSubSectors) {
                    BusinessesSubSectors subSectors = (BusinessesSubSectors) object;
                    this.subSectorList = new LinkedHashMap<>();
                    for (BusinessesSubSectors.Response response : subSectors.getResponse()) {
                        this.subSectorList.put(String.valueOf(response.getSUBSECTORIDFK()), response.getSUBSECTORNAME());
                    }
                }
            }
        }

        List<CustomerData.Response.GetCustomerLoan.LoanResponse> loanResponses = customerData.getResponse().getGetCustomerLoan().getResponse();
        this.customerLoanBeans = new ArrayList<>();
        int loanid = 0;
        for (CustomerData.Response.GetCustomerLoan.LoanResponse loanResponse : loanResponses) {
            CustomerLoanBean loanBean = new CustomerLoanBean();
            loanBean.setLoan_id_view(String.valueOf(loanid++));
            loanBean.setAlias_name(loanResponse.getALIASNAME());
            loanBean.setContract_date(loanResponse.getCONTRACTDATE() == null ? "" : Common.getStringFormatDate("yyyy-MM-dd", loanResponse.getCONTRACTDATE().toGregorianCalendar().getTime()));
            loanBean.setContract_no(loanResponse.getCONTRACTNO());
            loanBean.setContract_status(loanResponse.getCONTRACTSTATUS());
            loanBean.setCs(loanResponse.getCS());
            loanBean.setCustomer_code(loanResponse.getCUSTOMERCODE());
            loanBean.setModule_code(loanResponse.getMODULECODE());
            loanBean.setOutstanding_amount(loanResponse.getOUTSTANDINGAMOUNT().setScale(2, RoundingMode.CEILING));
            loanBean.setTotal_arrears_amount(loanResponse.getTOTALARREARSAMOUNT().setScale(2, RoundingMode.CEILING));
            loanBean.setTotal_offered_amount(loanResponse.getTOTALOFFEREDAMOUNT().setScale(2, RoundingMode.CEILING));
            this.customerLoanBeans.add(loanBean);
        }

        List<StakeholderSavings.Response> savingses = customerData.getResponse().getStakeholderSavings().getResponse();
        this.stakeHolderSavingsBeans = new ArrayList<>();
        int savingsid = 0;
        for (StakeholderSavings.Response saving : savingses) {
            StakeHolderSavingsBean savingsBean = new StakeHolderSavingsBean();
            savingsBean.setSavings_id_view(String.valueOf(savingsid++));
            savingsBean.setProduct_code(saving.getPRODUCTCODE());

            savingsBean.setAccount_number(saving.getACCOUNTNUMBER());
            savingsBean.setCurrent_balance(saving.getCURRENTBALANCE().setScale(2, RoundingMode.CEILING));
            savingsBean.setHold_amount(saving.getHOLDAMOUNT().setScale(2, RoundingMode.CEILING));
            savingsBean.setAvailable_balance(saving.getAVAILABLEBALANCE().setScale(2, RoundingMode.CEILING));
            savingsBean.setStatus(saving.getACCOUNTSTATUS());
            stakeHolderSavingsBeans.add(savingsBean);
        }

        this.is_tax_payee = String.valueOf(customerData.getResponse().getISTAXPAYEE());
        this.tax_number = customerData.getResponse().getTAXFILENO() == null ? "" : customerData.getResponse().getTAXFILENO();

        if (customerData.getResponse().getContactPersonDetails().getResponse() != null) {
            CustomerData.Response.ContactPersonDetails.ContactPersonResponse personResponse = customerData.getResponse().getContactPersonDetails().getResponse();
            this.cp_nic = personResponse.getCUSTOMERCODE() == null || personResponse.getCUSTOMERCODE().isEmpty() ? "" : personResponse.getCUSTOMERCODE();
            this.cp_title = personResponse.getTITLE() == null || personResponse.getTITLE().isEmpty() ? "" : personResponse.getTITLE();
            this.cp_name_in_full = personResponse.getNAMEINFULL() == null || personResponse.getNAMEINFULL().isEmpty() ? "" : personResponse.getNAMEINFULL();
            this.cp_initial = personResponse.getINITIALS() == null || personResponse.getINITIALS().isEmpty() ? "" : personResponse.getINITIALS();
            this.cp_last_name = personResponse.getLASTNAME() == null || personResponse.getLASTNAME().isEmpty() ? "" : personResponse.getLASTNAME();
            this.cp_preferred_name = personResponse.getFIRSTNAME() == null || personResponse.getFIRSTNAME().isEmpty() ? "" : personResponse.getFIRSTNAME();
            this.cp_mobile_no = personResponse.getMOBILENO() == 0 ? "" : String.valueOf(personResponse.getMOBILENO());
            this.cp_email = personResponse.getEMAIL() == null || personResponse.getEMAIL().isEmpty() ? "" : personResponse.getEMAIL();
        }

        List<StakeholderFD.Response> fds = customerData.getResponse().getStakeholderFD().getResponse();
        this.stakeHolderFDBeans = new ArrayList<>();
        int fdid = 0;
        for (StakeholderFD.Response fd : fds) {
            StakeHolderFDBean fdBean = new StakeHolderFDBean();
            fdBean.setFd_id_view(String.valueOf(fdid++));
            fdBean.setFd_no(fd.getFdNo());
            fdBean.setDepamt(fd.getDepamt().setScale(2, RoundingMode.CEILING));
            fdBean.setFd_date(fd.getFdDate() == null ? "" : Common.getStringFormatDate("yyyy-MM-dd", fd.getFdDate().toGregorianCalendar().getTime()));
            fdBean.setFd_prd(fd.getFdPrd());
            fdBean.setFd_inrate(String.valueOf(fd.getFdInrate()));
            fdBean.setCenter(fd.getCenter());
            fdBean.setFd_type(fd.getFdType());
            fdBean.setFd_monint(fd.getFdMonint().setScale(2, RoundingMode.CEILING));
            fdBean.setFd_next(fd.getFdNext() == null ? "" : Common.getStringFormatDate("yyyy-MM-dd", fd.getFdNext().toGregorianCalendar().getTime()));
            getStakeHolderFDBeans().add(fdBean);
        }
    }

    /**
     * @return the customer_code_type_name
     */
    public String getCustomer_code_type_name() {
        return customer_code_type_name;
    }

    /**
     * @param customer_code_type_name the customer_code_type_name to set
     */
    public void setCustomer_code_type_name(String customer_code_type_name) {
        this.customer_code_type_name = customer_code_type_name;
    }

    /**
     * @return the account_category
     */
    public String getAccount_category() {
        return account_category;
    }

    /**
     * @param account_category the account_category to set
     */
    public void setAccount_category(String account_category) {
        this.account_category = account_category;
    }

    /**
     * @return the account_category_name
     */
    public String getAccount_category_name() {
        return account_category_name;
    }

    /**
     * @param account_category_name the account_category_name to set
     */
    public void setAccount_category_name(String account_category_name) {
        this.account_category_name = account_category_name;
    }

    /**
     * @return the account_category_type
     */
    public String getAccount_category_type() {
        return account_category_type;
    }

    /**
     * @param account_category_type the account_category_type to set
     */
    public void setAccount_category_type(String account_category_type) {
        this.account_category_type = account_category_type;
    }

    /**
     * @return the account_category_type_name
     */
    public String getAccount_category_type_name() {
        return account_category_type_name;
    }

    /**
     * @param account_category_type_name the account_category_type_name to set
     */
    public void setAccount_category_type_name(String account_category_type_name) {
        this.account_category_type_name = account_category_type_name;
    }

    /**
     * @return the customerEducationBeans
     */
    public List<CustomerEducationBean> getCustomerEducationBeans() {
        return customerEducationBeans;
    }

    /**
     * @param customerEducationBeans the customerEducationBeans to set
     */
    public void setCustomerEducationBeans(List<CustomerEducationBean> customerEducationBeans) {
        this.customerEducationBeans = customerEducationBeans;
    }

    /**
     * @return the designation_code
     */
    public String getDesignation_code() {
        return designation_code;
    }

    /**
     * @param designation_code the designation_code to set
     */
    public void setDesignation_code(String designation_code) {
        this.designation_code = designation_code;
    }

    /**
     * @return the designation_description
     */
    public String getDesignation_description() {
        return designation_description;
    }

    /**
     * @param designation_description the designation_description to set
     */
    public void setDesignation_description(String designation_description) {
        this.designation_description = designation_description;
    }

    /**
     * @return the level_code
     */
    public String getLevel_code() {
        return level_code;
    }

    /**
     * @param level_code the level_code to set
     */
    public void setLevel_code(String level_code) {
        this.level_code = level_code;
    }

    /**
     * @return the level_description
     */
    public String getLevel_description() {
        return level_description;
    }

    /**
     * @param level_description the level_description to set
     */
    public void setLevel_description(String level_description) {
        this.level_description = level_description;
    }

    /**
     * @return the profession_code
     */
    public String getProfession_code() {
        return profession_code;
    }

    /**
     * @param profession_code the profession_code to set
     */
    public void setProfession_code(String profession_code) {
        this.profession_code = profession_code;
    }

    /**
     * @return the profession_description
     */
    public String getProfession_description() {
        return profession_description;
    }

    /**
     * @param profession_description the profession_description to set
     */
    public void setProfession_description(String profession_description) {
        this.profession_description = profession_description;
    }

    /**
     * @return the employeer
     */
    public String getEmployeer() {
        return employeer;
    }

    /**
     * @param employeer the employeer to set
     */
    public void setEmployeer(String employeer) {
        this.employeer = employeer;
    }

    /**
     * @return the sector_code
     */
    public String getSector_code() {
        return sector_code;
    }

    /**
     * @param sector_code the sector_code to set
     */
    public void setSector_code(String sector_code) {
        this.sector_code = sector_code;
    }

    /**
     * @return the sector_description
     */
    public String getSector_description() {
        return sector_description;
    }

    /**
     * @param sector_description the sector_description to set
     */
    public void setSector_description(String sector_description) {
        this.sector_description = sector_description;
    }

    /**
     * @return the subSectorList
     */
    public Map<String, String> getSubSectorList() {
        return subSectorList;
    }

    /**
     * @param subSectorList the subSectorList to set
     */
    public void setSubSectorList(Map<String, String> subSectorList) {
        this.subSectorList = subSectorList;
    }

    /**
     * @return the is_tax_payee
     */
    public String getIs_tax_payee() {
        return is_tax_payee;
    }

    /**
     * @param is_tax_payee the is_tax_apyee to set
     */
    public void setIs_tax_payee(String is_tax_payee) {
        this.is_tax_payee = is_tax_payee;
    }

    /**
     * @return the tax_number
     */
    public String getTax_number() {
        return tax_number;
    }

    /**
     * @param tax_number the tax_number to set
     */
    public void setTax_number(String tax_number) {
        this.tax_number = tax_number;
    }

    /**
     * @return the mothers_maiden_name
     */
    public String getMothers_maiden_name() {
        return mothers_maiden_name;
    }

    /**
     * @param mothers_maiden_name the mothers_maiden_name to set
     */
    public void setMothers_maiden_name(String mothers_maiden_name) {
        this.mothers_maiden_name = mothers_maiden_name;
    }

    /**
     * @return the company_name
     */
    public String getCompany_name() {
        return company_name;
    }

    /**
     * @param company_name the company_name to set
     */
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    /**
     * @return the cp_nic
     */
    public String getCp_nic() {
        return cp_nic;
    }

    /**
     * @param cp_nic the cp_nic to set
     */
    public void setCp_nic(String cp_nic) {
        this.cp_nic = cp_nic;
    }

    /**
     * @return the cp_title
     */
    public String getCp_title() {
        return cp_title;
    }

    /**
     * @param cp_title the cp_title to set
     */
    public void setCp_title(String cp_title) {
        this.cp_title = cp_title;
    }

    /**
     * @return the cp_name_in_full
     */
    public String getCp_name_in_full() {
        return cp_name_in_full;
    }

    /**
     * @param cp_name_in_full the cp_name_in_full to set
     */
    public void setCp_name_in_full(String cp_name_in_full) {
        this.cp_name_in_full = cp_name_in_full;
    }

    /**
     * @return the cp_initial
     */
    public String getCp_initial() {
        return cp_initial;
    }

    /**
     * @param cp_initial the cp_initial to set
     */
    public void setCp_initial(String cp_initial) {
        this.cp_initial = cp_initial;
    }

    /**
     * @return the cp_last_name
     */
    public String getCp_last_name() {
        return cp_last_name;
    }

    /**
     * @param cp_last_name the cp_last_name to set
     */
    public void setCp_last_name(String cp_last_name) {
        this.cp_last_name = cp_last_name;
    }

    /**
     * @return the cp_preferred_name
     */
    public String getCp_preferred_name() {
        return cp_preferred_name;
    }

    /**
     * @param cp_preferred_name the cp_preferred_name to set
     */
    public void setCp_preferred_name(String cp_preferred_name) {
        this.cp_preferred_name = cp_preferred_name;
    }

    /**
     * @return the cp_mobile_no
     */
    public String getCp_mobile_no() {
        return cp_mobile_no;
    }

    /**
     * @param cp_mobile_no the cp_mobile_no to set
     */
    public void setCp_mobile_no(String cp_mobile_no) {
        this.cp_mobile_no = cp_mobile_no;
    }

    /**
     * @return the cp_email
     */
    public String getCp_email() {
        return cp_email;
    }

    /**
     * @param cp_email the cp_email to set
     */
    public void setCp_email(String cp_email) {
        this.cp_email = cp_email;
    }

    /**
     * @return the stakeHolderFDBeans
     */
    public List<StakeHolderFDBean> getStakeHolderFDBeans() {
        return stakeHolderFDBeans;
    }

    /**
     * @param stakeHolderFDBeans the stakeHolderFDBeans to set
     */
    public void setStakeHolderFDBeans(List<StakeHolderFDBean> stakeHolderFDBeans) {
        this.stakeHolderFDBeans = stakeHolderFDBeans;
    }

}

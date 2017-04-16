/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.account;

import com.avn.ccl.bean.serviceclient.basicprofile.CustomerBasicData;
import com.avn.ccl.bean.serviceclient.fullprofile.CustomerData;
import com.avn.ccl.bean.webserviceclient.CustomerFullProfileSearhDataBean;
import com.avn.ccl.model.address.Address;
import com.avn.ccl.model.customerhobbiesinterests.CustomerHobbiesInterests;
import com.avn.ccl.model.dependent.Dependent;
import com.avn.ccl.model.education.Education;
import com.avn.ccl.model.hobbiesandinterests.HobbiesAndInterest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : Roshen Dilshan
 * @Document : AccountInputBean
 * @Date : Jun 26, 2015 6:39:42 PM
 */
public class Account {

    //To success or error handle
    private String message_type;
    private String message_description;
    private List<String> errorCodes;
    //Search parameters
    private String search_type;
    private String parameter_value;
    //Page parameters
    private String account_type;
    private String nic;
    private String confirm_nic;
    //General Details
    private String account_id;
    private String ccid;
    private String customer_category;
    private String customer_category_type;
    private String customer_code_type;
    private String customer_code;
    private String customer_code_confirm;
    private String passport;
    private String branch_location;
    private String version;
    //Personal Details
    private String title;
    private String initials;
    private String preferred_name;
    private String last_name;
    private String name_in_full;
    private String date_of_birth;
    private String mothers_maiden_name;
    private String gender;
    private String nationality;
    private String religion;
    private String marital_status;
    private String dependent;
    private String preferred_language;
    private String mobile_01;
    private String mobile_02;
    private String email;
    private String is_tax_payee;
    private String tax_no;
    //Corporate Customer Details
    private String copemployer;
    private String copsector;
    private String copsub_sector_not_assign;
    private String copsub_sector_assign;
    private String[] copsub_sector;
    private String copsub_sector_list;
    private String[] copsub_sectors_not_assign;
    private String copsub_sector_not_assign_list;
    //Contact Details
    private String address_type;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String country;
    private String province;
    private String district;
    private String city;
    private String gs;
    private String gps;
    private String land_phone_no;
    private String telephone;
    private String billing_proof;
    private String address_list;
    //Contact Person Details
    private String cp_nic;
    private String cp_title;
    private String cp_name_in_full;
    private String cp_initials;
    private String cp_last_name;
    private String cp_preferred_name;
    private String cp_mobile;
    private String cp_email;
    //Education Details
    private String education_level;
    private String institute;
    private String education_list;
    //Ocupation Details
    private String designation;
    private String level;
    private String profession;
    private String employer;
    private String sector;
    private String sub_sector_not_assign;
    private String sub_sector_assign;
    private String[] sub_sector;
    private String sub_sector_list;
    private String[] sub_sectors_not_assign;
    private String sub_sector_not_assign_list;
    //Spouse/Gardian/Care Taker Details
    private String sgc_title;
    private String sgc_initials;
    private String sgc_firstname;
    private String sgc_lastname;
    private String sgc_name_in_full;
    private String sgc_address_residence;
    private String sgc_occupation;
    private String sgc_employer_business_name;
    private String sgc_employer_business_address;
    private String sgc_employer_telephone;
    private String sgc_years_of_employeement_business;
    //Product Details
    private String savings_status;
    private String mf_cs;
    private String cr_cs;
    private String pw_cs;
    //Dependent Details
    private String dependent_relationship;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dependent_date_of_birth;
    private String dependent_name_in_full;
    private String dependent_list;
    //Other Details
    private String tax_file_number;
    private String secret_question;
    private String secret_response;
    private int Actual_lead_amount;
    //Customer Informations
    private CustomerFullProfileSearhDataBean searhDataBean;
    private AccountSearchData searchData;
    private CustomerData customerFullData;
    private CustomerBasicData customerBasicData;
    //Customer Address Array
    private Address[] addresses;
    //Customer Education Array
    private Education[] educations;
    //Customer Dependent Array
    private Dependent[] dependents;
    //Customer Attachments
    private MultipartFile nic_file;
    private MultipartFile signature_file;
    private String nic_file_name;
    private String signature_file_name;
    private String nic_file_location;
    private String signature_file_location;
    //CallCenter Attachments
    private String cli;
    private String datetime;

    private String download_token_value_id;

    private String jasonobject;
    
    /*checkbtnprivilage*/
     private boolean save_btn;
     private boolean edit_btn;
     private boolean search_btn;
     private boolean  view_btn;
     private boolean  download_btn;
     

    //Customer Hobbies
    private String hobby_id;
    private String hobby_comment;
    private String customer_hobby_list;
    //Customer Hobby, Interest Array
    private CustomerHobbiesInterests[] customerhobbiesinterest;
    //Customer FD
    private String fd_type;
    private String center;


    /**
     * @return the message_type
     */
    public String getMessage_type() {
        return message_type;
    }

    /**
     * @param message_type the message_type to set
     */
    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    /**
     * @return the message_description
     */
    public String getMessage_description() {
        return message_description;
    }

    /**
     * @param message_description the message_description to set
     */
    public void setMessage_description(String message_description) {
        this.message_description = message_description;
    }

    /**
     * @return the search_type
     */
    public String getSearch_type() {
        return search_type;
    }

    /**
     * @param search_type the search_type to set
     */
    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    /**
     * @return the parameter_value
     */
    public String getParameter_value() {
        return parameter_value;
    }

    /**
     * @param parameter_value the parameter_value to set
     */
    public void setParameter_value(String parameter_value) {
        this.parameter_value = parameter_value;
    }

    /**
     * @return the account_type
     */
    public String getAccount_type() {
        return account_type;
    }

    /**
     * @param account_type the account_type to set
     */
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the confirm_nic
     */
    public String getConfirm_nic() {
        return confirm_nic;
    }

    /**
     * @param confirm_nic the confirm_nic to set
     */
    public void setConfirm_nic(String confirm_nic) {
        this.confirm_nic = confirm_nic;
    }

    /**
     * @return the customer_category
     */
    public String getCustomer_category() {
        return customer_category;
    }

    /**
     * @param customer_category the customer_category to set
     */
    public void setCustomer_category(String customer_category) {
        this.customer_category = customer_category;
    }

    /**
     * @return the customer_category_type
     */
    public String getCustomer_category_type() {
        return customer_category_type;
    }

    /**
     * @param customer_category_type the customer_category_type to set
     */
    public void setCustomer_category_type(String customer_category_type) {
        this.customer_category_type = customer_category_type;
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
     * @return the customer_code_confirm
     */
    public String getCustomer_code_confirm() {
        return customer_code_confirm;
    }

    /**
     * @param customer_code_confirm the customer_code_confirm to set
     */
    public void setCustomer_code_confirm(String customer_code_confirm) {
        this.customer_code_confirm = customer_code_confirm;
    }

    /**
     * @return the passport
     */
    public String getPassport() {
        return passport;
    }

    /**
     * @param passport the passport to set
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * @return the branch_location
     */
    public String getBranch_location() {
        return branch_location;
    }

    /**
     * @param branch_location the branch_location to set
     */
    public void setBranch_location(String branch_location) {
        this.branch_location = branch_location;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
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
     * @return the preferred_name
     */
    public String getPreferred_name() {
        return preferred_name;
    }

    /**
     * @param preferred_name the preferred_name to set
     */
    public void setPreferred_name(String preferred_name) {
        this.preferred_name = preferred_name;
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
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
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
    }

    /**
     * @return the dependent
     */
    public String getDependent() {
        return dependent;
    }

    /**
     * @param dependent the dependent to set
     */
    public void setDependent(String dependent) {
        this.dependent = dependent;
    }

    /**
     * @return the preferred_language
     */
    public String getPreferred_language() {
        return preferred_language;
    }

    /**
     * @param preferred_language the preferred_language to set
     */
    public void setPreferred_language(String preferred_language) {
        this.preferred_language = preferred_language;
    }

    /**
     * @return the mobile_01
     */
    public String getMobile_01() {
        return mobile_01;
    }

    /**
     * @param mobile_01 the mobile_01 to set
     */
    public void setMobile_01(String mobile_01) {
        this.mobile_01 = mobile_01;
    }

    /**
     * @return the mobile_02
     */
    public String getMobile_02() {
        return mobile_02;
    }

    /**
     * @param mobile_02 the mobile_02 to set
     */
    public void setMobile_02(String mobile_02) {
        this.mobile_02 = mobile_02;
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
     * @return the is_tax_pyee
     */
    public String getIs_tax_payee() {
        return is_tax_payee;
    }

    /**
     * @param is_tax_payee the is_tax_pyee to set
     */
    public void setIs_tax_payee(String is_tax_payee) {
        this.is_tax_payee = is_tax_payee;
    }

    /**
     * @return the tax_no
     */
    public String getTax_no() {
        return tax_no;
    }

    /**
     * @param tax_no the tax_no to set
     */
    public void setTax_no(String tax_no) {
        this.tax_no = tax_no;
    }

    /**
     * @return the address_type
     */
    public String getAddress_type() {
        return address_type;
    }

    /**
     * @param address_type the address_type to set
     */
    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    /**
     * @return the address_line_01
     */
    public String getAddress_line_01() {
        return address_line_01;
    }

    /**
     * @param address_line_01 the address_line_01 to set
     */
    public void setAddress_line_01(String address_line_01) {
        this.address_line_01 = address_line_01;
    }

    /**
     * @return the address_line_02
     */
    public String getAddress_line_02() {
        return address_line_02;
    }

    /**
     * @param address_line_02 the address_line_02 to set
     */
    public void setAddress_line_02(String address_line_02) {
        this.address_line_02 = address_line_02;
    }

    /**
     * @return the address_line_03
     */
    public String getAddress_line_03() {
        return address_line_03;
    }

    /**
     * @param address_line_03 the address_line_03 to set
     */
    public void setAddress_line_03(String address_line_03) {
        this.address_line_03 = address_line_03;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the gs
     */
    public String getGs() {
        return gs;
    }

    /**
     * @param gs the gs to set
     */
    public void setGs(String gs) {
        this.gs = gs;
    }

    /**
     * @return the land_phone_no
     */
    public String getLand_phone_no() {
        return land_phone_no;
    }

    /**
     * @param land_phone_no the land_phone_no to set
     */
    public void setLand_phone_no(String land_phone_no) {
        this.land_phone_no = land_phone_no;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the billing_proof
     */
    public String getBilling_proof() {
        return billing_proof;
    }

    /**
     * @param billing_proof the billing_proof to set
     */
    public void setBilling_proof(String billing_proof) {
        this.billing_proof = billing_proof;
    }

    /**
     * @return the address_list
     */
    public String getAddress_list() {
        return address_list;
    }

    /**
     * @param address_list the address_list to set
     */
    public void setAddress_list(String address_list) {
        this.address_list = address_list;
    }

    /**
     * @return the education_level
     */
    public String getEducation_level() {
        return education_level;
    }

    /**
     * @param education_level the education_level to set
     */
    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    /**
     * @return the institute
     */
    public String getInstitute() {
        return institute;
    }

    /**
     * @param institute the institute to set
     */
    public void setInstitute(String institute) {
        this.institute = institute;
    }

    /**
     * @return the education_list
     */
    public String getEducation_list() {
        return education_list;
    }

    /**
     * @param education_list the education_list to set
     */
    public void setEducation_list(String education_list) {
        this.education_list = education_list;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession the profession to set
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * @return the employer
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * @param employer the employer to set
     */
    public void setEmployer(String employer) {
        this.employer = employer;
    }

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * @return the sgc_title
     */
    public String getSgc_title() {
        return sgc_title;
    }

    /**
     * @param sgc_title the sgc_title to set
     */
    public void setSgc_title(String sgc_title) {
        this.sgc_title = sgc_title;
    }

    /**
     * @return the sgc_initials
     */
    public String getSgc_initials() {
        return sgc_initials;
    }

    /**
     * @param sgc_initials the sgc_initials to set
     */
    public void setSgc_initials(String sgc_initials) {
        this.sgc_initials = sgc_initials;
    }

    /**
     * @return the sgc_firstname
     */
    public String getSgc_firstname() {
        return sgc_firstname;
    }

    /**
     * @param sgc_firstname the sgc_firstname to set
     */
    public void setSgc_firstname(String sgc_firstname) {
        this.sgc_firstname = sgc_firstname;
    }

    /**
     * @return the sgc_lastname
     */
    public String getSgc_lastname() {
        return sgc_lastname;
    }

    /**
     * @param sgc_lastname the sgc_lastname to set
     */
    public void setSgc_lastname(String sgc_lastname) {
        this.sgc_lastname = sgc_lastname;
    }

    /**
     * @return the sgc_address_residence
     */
    public String getSgc_address_residence() {
        return sgc_address_residence;
    }

    /**
     * @param sgc_address_residence the sgc_address_residence to set
     */
    public void setSgc_address_residence(String sgc_address_residence) {
        this.sgc_address_residence = sgc_address_residence;
    }

    /**
     * @return the sgc_occupation
     */
    public String getSgc_occupation() {
        return sgc_occupation;
    }

    /**
     * @param sgc_occupation the sgc_occupation to set
     */
    public void setSgc_occupation(String sgc_occupation) {
        this.sgc_occupation = sgc_occupation;
    }

    /**
     * @return the sgc_employer_business_name
     */
    public String getSgc_employer_business_name() {
        return sgc_employer_business_name;
    }

    /**
     * @param sgc_employer_business_name the sgc_employer_business_name to set
     */
    public void setSgc_employer_business_name(String sgc_employer_business_name) {
        this.sgc_employer_business_name = sgc_employer_business_name;
    }

    /**
     * @return the sgc_employer_business_address
     */
    public String getSgc_employer_business_address() {
        return sgc_employer_business_address;
    }

    /**
     * @param sgc_employer_business_address the sgc_employer_business_address to
     * set
     */
    public void setSgc_employer_business_address(String sgc_employer_business_address) {
        this.sgc_employer_business_address = sgc_employer_business_address;
    }

    /**
     * @return the sgc_employer_telephone
     */
    public String getSgc_employer_telephone() {
        return sgc_employer_telephone;
    }

    /**
     * @param sgc_employer_telephone the sgc_employer_telephone to set
     */
    public void setSgc_employer_telephone(String sgc_employer_telephone) {
        this.sgc_employer_telephone = sgc_employer_telephone;
    }

    /**
     * @return the sgc_years_of_employeement_business
     */
    public String getSgc_years_of_employeement_business() {
        return sgc_years_of_employeement_business;
    }

    /**
     * @param sgc_years_of_employeement_business the
     * sgc_years_of_employeement_business to set
     */
    public void setSgc_years_of_employeement_business(String sgc_years_of_employeement_business) {
        this.sgc_years_of_employeement_business = sgc_years_of_employeement_business;
    }

    /**
     * @return the tax_file_number
     */
    public String getTax_file_number() {
        return tax_file_number;
    }

    /**
     * @param tax_file_number the tex_file_number to set
     */
    public void setTax_file_number(String tax_file_number) {
        this.tax_file_number = tax_file_number;
    }

    /**
     * @return the secret_question
     */
    public String getSecret_question() {
        return secret_question;
    }

    /**
     * @param secret_question the secret_question to set
     */
    public void setSecret_question(String secret_question) {
        this.secret_question = secret_question;
    }

    /**
     * @return the secret_response
     */
    public String getSecret_response() {
        return secret_response;
    }

    /**
     * @param secret_response the secret_response to set
     */
    public void setSecret_response(String secret_response) {
        this.secret_response = secret_response;
    }

    /**
     * @return the searhDataBean
     */
    public CustomerFullProfileSearhDataBean getSearhDataBean() {
        return searhDataBean;
    }

    /**
     * @param searhDataBean the searhDataBean to set
     */
    public void setSearhDataBean(CustomerFullProfileSearhDataBean searhDataBean) {
        this.searhDataBean = searhDataBean;
    }

    /**
     * @return the customerAddressBeans
     * @throws java.io.IOException
     */
    public Address[] getAddresses() throws IOException {
        addresses = this.generateAddressArray();
        return addresses;
    }

    /**
     * @param addresses the customerAddressBeans to set
     */
    public void setAddresses(Address[] addresses) {
        this.addresses = addresses;
    }

    /**
     * @return the gps
     */
    public String getGps() {
        return gps;
    }

    /**
     * @param gps the gps to set
     */
    public void setGps(String gps) {
        this.gps = gps;
    }

    /**
     * @return the customerEducationBeans
     * @throws java.io.IOException
     */
    public Education[] getEducations() throws IOException {
        educations = this.generateEducationArray();
        return educations;
    }

    /**
     * @param educations
     */
    public void setEducations(Education[] educations) {
        this.educations = educations;
    }

    private Address[] generateAddressArray() throws IOException {
        JSONArray array = new JSONArray();
        if (this.address_list != null && !this.address_list.isEmpty()) {
            array = new JSONArray(this.address_list);
        }
//        System.out.println(array.toString(4));
        Address[] addressarray = new Address[array.length()];
        for (int i = 0; i < array.length(); i++) {
//            addressBean.setAddress_type(Integer.valueOf(array.getJSONObject(i).getString("address_type")));
//            addressBean.setAddress_line_1(array.getJSONObject(i).getString("address_line_01"));
//            addressBean.setAddress_line_2(array.getJSONObject(i).getString("address_line_02"));
//            addressBean.setAddress_line_3(array.getJSONObject(i).getString("address_line_03"));
//            addressBean.setCountry_id_fk(Integer.valueOf(array.getJSONObject(i).getString("country")));
//            addressBean.setProvince_id_fk(array.getJSONObject(i).getString("province"));
//            addressBean.setDistrict_id_fk(array.getJSONObject(i).getString("district"));
//            addressBean.setCity_id_fk(array.getJSONObject(i).getString("city"));
//            addressBean.setGs_division_name(array.getJSONObject(i).getString("gs"));
//            addressBean.setGps(array.getJSONObject(i).getString("gps"));
//            addressBean.setLand_phone_no(array.getJSONObject(i).getString("land_phone_no"));
//            addressBean.setBilling_proof(Integer.valueOf(array.getJSONObject(i).getString("billing_proof")));
//            cabs[i] = addressBean;
            addressarray[i] = (Address) new ObjectMapper().readValue(array.getJSONObject(i).toString(), Address.class);
        }
        return addressarray;
    }

    private Education[] generateEducationArray() throws IOException {
        JSONArray array = new JSONArray();
        if (this.education_list != null && !this.education_list.isEmpty()) {
            array = new JSONArray(this.education_list);
        }
//        System.out.println(array.toString(4));
//        CustomerEducationBean[] cebs = new CustomerEducationBean[array.length()];
        Education[] educationarray = new Education[array.length()];
//        CustomerEducationBean educationBean;
        for (int i = 0; i < array.length(); i++) {
//            educationBean = new CustomerEducationBean();
//            educationBean.setEducationLevel(array.getJSONObject(i).getString("education_level"));
//            educationBean.setInstitute(array.getJSONObject(i).getString("institute"));
//            cebs[i] = educationBean;
            educationarray[i] = (Education) new ObjectMapper().readValue(array.getJSONObject(i).toString(), Education.class);
        }
        return educationarray;
    }

    private Dependent[] generateDependentArray() throws IOException {
        JSONArray array = new JSONArray();
        if (this.dependent_list != null && !this.dependent_list.isEmpty()) {
            array = new JSONArray(this.dependent_list);
        }

        Dependent[] dependentarray = new Dependent[array.length()];
        for (int i = 0; i < array.length(); i++) {
            dependentarray[i] = (Dependent) new ObjectMapper().readValue(array.getJSONObject(i).toString(), Dependent.class);
        }
        return dependentarray;
    }

    private String[] generateSubsectorArray() {
        JSONArray array = new JSONArray();
        if (this.sub_sector_list != null && !this.sub_sector_list.isEmpty()) {
            array = new JSONArray(this.sub_sector_list);
        }
//        System.out.println(array.toString(4));
        String[] subsectorid = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            subsectorid[i] = String.valueOf(array.getInt(i));
        }
        return subsectorid;
    }

    private String[] generateSubsectorNotAssignArray() {
        JSONArray array = new JSONArray();
        if (this.sub_sector_not_assign_list != null && !this.sub_sector_not_assign_list.isEmpty()) {
            array = new JSONArray(this.sub_sector_not_assign_list);
        }
//        System.out.println(array.toString(4));
        String[] subsectorid = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            subsectorid[i] = String.valueOf(array.getInt(i));
        }
        return subsectorid;
    }

    private String[] generateCopSubsectorArray() {
        JSONArray array = new JSONArray();
        if (this.copsub_sector_list != null && !this.copsub_sector_list.isEmpty()) {
            array = new JSONArray(this.copsub_sector_list);
        }
//        System.out.println(array.toString(4));
        String[] subsectorid = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            subsectorid[i] = String.valueOf(array.getInt(i));
        }
        return subsectorid;
    }

    private String[] generateCopSubsectorNotAssignArray() {
        JSONArray array = new JSONArray();
        if (this.copsub_sector_not_assign_list != null && !this.copsub_sector_not_assign_list.isEmpty()) {
            array = new JSONArray(this.copsub_sector_not_assign_list);
        }
//        System.out.println(array.toString(4));
        String[] subsectorid = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            subsectorid[i] = String.valueOf(array.getInt(i));
        }
        return subsectorid;
    }

    private CustomerHobbiesInterests[] generateHobbyInterestArray() throws IOException {
        JSONArray array = new JSONArray();
        if (this.customer_hobby_list != null && !this.customer_hobby_list.isEmpty()) {
            array = new JSONArray(this.customer_hobby_list);
        }

        CustomerHobbiesInterests[] customerhobbyinterestarray = new CustomerHobbiesInterests[array.length()];
        for (int i = 0; i < array.length(); i++) {
            customerhobbyinterestarray[i] = (CustomerHobbiesInterests) new ObjectMapper().readValue(array.getJSONObject(i).toString(), CustomerHobbiesInterests.class);
        }
        return customerhobbyinterestarray;
    }

    /**
     * @return the savings_status
     */
    public String getSavings_status() {
        return savings_status;
    }

    /**
     * @param savings_status the savings_status to set
     */
    public void setSavings_status(String savings_status) {
        this.savings_status = savings_status;
    }

    /**
     * @return the mf_cs
     */
    public String getMf_cs() {
        return mf_cs;
    }

    /**
     * @param mf_cs the mf_cs to set
     */
    public void setMf_cs(String mf_cs) {
        this.mf_cs = mf_cs;
    }

    /**
     * @return the cr_cs
     */
    public String getCr_cs() {
        return cr_cs;
    }

    /**
     * @param cr_cs the cr_cs to set
     */
    public void setCr_cs(String cr_cs) {
        this.cr_cs = cr_cs;
    }

    /**
     * @return the pw_cs
     */
    public String getPw_cs() {
        return pw_cs;
    }

    /**
     * @param pw_cs the pw_cs to set
     */
    public void setPw_cs(String pw_cs) {
        this.pw_cs = pw_cs;
    }

    /**
     * @return the sub_sector
     */
    public String[] getSub_sector() {
        sub_sector = this.generateSubsectorArray();
        return sub_sector;
    }

    /**
     * @param sub_sector the sub_sector to set
     */
    public void setSub_sector(String[] sub_sector) {
        this.sub_sector = sub_sector;
    }

    /**
     * @return the sub_sector_list
     */
    public String getSub_sector_list() {
        return sub_sector_list;
    }

    /**
     * @param sub_sector_list the sub_sector_list to set
     */
    public void setSub_sector_list(String sub_sector_list) {
        this.sub_sector_list = sub_sector_list;
    }

    /**
     * @return the sub_sector_not_assign
     */
    public String getSub_sector_not_assign() {
        return sub_sector_not_assign;
    }

    /**
     * @param sub_sector_not_assign the sub_sector_not_assign to set
     */
    public void setSub_sector_not_assign(String sub_sector_not_assign) {
        this.sub_sector_not_assign = sub_sector_not_assign;
    }

    /**
     * @return the sub_sector_assign
     */
    public String getSub_sector_assign() {
        return sub_sector_assign;
    }

    /**
     * @param sub_sector_assign the sub_sector_assign to set
     */
    public void setSub_sector_assign(String sub_sector_assign) {
        this.sub_sector_assign = sub_sector_assign;
    }

    /**
     * @return the errorCodes
     */
    public List<String> getErrorCodes() {
        return errorCodes;
    }

    /**
     * @param errorCodes the errorCodes to set
     */
    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

    /**
     * @return the ccid
     */
    public String getCcid() {
        return ccid;
    }

    /**
     * @param ccid the ccid to set
     */
    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    /**
     * @return the account_id
     */
    public String getAccount_id() {
        return account_id;
    }

    /**
     * @param account_id the account_id to set
     */
    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    /**
     * @return the sgc_name_in_full
     */
    public String getSgc_name_in_full() {
        return sgc_name_in_full;
    }

    /**
     * @param sgc_name_in_full the sgc_name_in_full to set
     */
    public void setSgc_name_in_full(String sgc_name_in_full) {
        this.sgc_name_in_full = sgc_name_in_full;
    }

    /**
     * @return the dependent_relationship
     */
    public String getDependent_relationship() {
        return dependent_relationship;
    }

    /**
     * @param dependent_relationship the dependent_relationship to set
     */
    public void setDependent_relationship(String dependent_relationship) {
        this.dependent_relationship = dependent_relationship;
    }

    /**
     * @return the dependent_date_of_birth
     */
    public Date getDependent_date_of_birth() {
        return dependent_date_of_birth;
    }

    /**
     * @param dependent_date_of_birth the dependent_date_of_birth to set
     */
    public void setDependent_date_of_birth(Date dependent_date_of_birth) {
        this.dependent_date_of_birth = dependent_date_of_birth;
    }

    /**
     * @return the dependent_name_in_full
     */
    public String getDependent_name_in_full() {
        return dependent_name_in_full;
    }

    /**
     * @param dependent_name_in_full the dependent_name_in_full to set
     */
    public void setDependent_name_in_full(String dependent_name_in_full) {
        this.dependent_name_in_full = dependent_name_in_full;
    }

    /**
     * @return the dependents
     * @throws java.io.IOException
     */
    public Dependent[] getDependents() throws IOException {
        dependents = this.generateDependentArray();
        return dependents;
    }

    /**
     * @return the dependent_list
     */
    public String getDependent_list() {
        return dependent_list;
    }

    /**
     * @param dependent_list the dependent_list to set
     */
    public void setDependent_list(String dependent_list) {
        this.dependent_list = dependent_list;
    }

    /**
     * @return the sub_sectors_not_assign
     */
    public String[] getSub_sectors_not_assign() {
        this.sub_sectors_not_assign = this.generateSubsectorNotAssignArray();
        return sub_sectors_not_assign;
    }

    /**
     * @return the sub_sector_not_assign_list
     */
    public String getSub_sector_not_assign_list() {
        return sub_sector_not_assign_list;
    }

    /**
     * @param sub_sector_not_assign_list the sub_sector_not_assign_list to set
     */
    public void setSub_sector_not_assign_list(String sub_sector_not_assign_list) {
        this.sub_sector_not_assign_list = sub_sector_not_assign_list;
    }

    /**
     * @return the customerBasicData
     */
    public CustomerBasicData getCustomerBasicData() {
        return customerBasicData;
    }

    /**
     * @param customerBasicData the customerBasicData to set
     */
    public void setCustomerBasicData(CustomerBasicData customerBasicData) {
        this.customerBasicData = customerBasicData;
    }

    /**
     * @return the searchData
     */
    public AccountSearchData getSearchData() {
        return searchData;
    }

    /**
     * @param searchData the searchData to set
     */
    public void setSearchData(AccountSearchData searchData) {
        this.searchData = searchData;
    }

    /**
     * @return the customerFullData
     */
    public CustomerData getCustomerFullData() {
        return customerFullData;
    }

    /**
     * @param customerFullData the customerFullData to set
     */
    public void setCustomerFullData(CustomerData customerFullData) {
        this.customerFullData = customerFullData;
    }

    /**
     * @return the nic_file
     */
    public MultipartFile getNic_file() {
        return nic_file;
    }

    /**
     * @param nic_file the nic_file to set
     */
    public void setNic_file(MultipartFile nic_file) {
        this.nic_file = nic_file;
    }

    /**
     * @return the signature_file
     */
    public MultipartFile getSignature_file() {
        return signature_file;
    }

    /**
     * @param signature_file the signature_file to set
     */
    public void setSignature_file(MultipartFile signature_file) {
        this.signature_file = signature_file;
    }

    /**
     * @return the nic_file_location
     */
    public String getNic_file_location() {
        return nic_file_location;
    }

    /**
     * @param nic_file_location the nic_file_location to set
     */
    public void setNic_file_location(String nic_file_location) {
        this.nic_file_location = nic_file_location;
    }

    /**
     * @return the signature_file_location
     */
    public String getSignature_file_location() {
        return signature_file_location;
    }

    /**
     * @param signature_file_location the signature_file_location to set
     */
    public void setSignature_file_location(String signature_file_location) {
        this.signature_file_location = signature_file_location;
    }

    /**
     * @return the nic_file_name
     */
    public String getNic_file_name() {
        return nic_file_name;
    }

    /**
     * @param nic_file_name the nic_file_name to set
     */
    public void setNic_file_name(String nic_file_name) {
        this.nic_file_name = nic_file_name;
    }

    /**
     * @return the signature_file_name
     */
    public String getSignature_file_name() {
        return signature_file_name;
    }

    /**
     * @param signature_file_name the signature_file_name to set
     */
    public void setSignature_file_name(String signature_file_name) {
        this.signature_file_name = signature_file_name;
    }

    /**
     * @return the copemployer
     */
    public String getCopemployer() {
        return copemployer;
    }

    /**
     * @param copemployer the copemployer to set
     */
    public void setCopemployer(String copemployer) {
        this.copemployer = copemployer;
    }

    /**
     * @return the copsector
     */
    public String getCopsector() {
        return copsector;
    }

    /**
     * @param copsector the copsector to set
     */
    public void setCopsector(String copsector) {
        this.copsector = copsector;
    }

    /**
     * @return the copsub_sector_not_assign
     */
    public String getCopsub_sector_not_assign() {
        return copsub_sector_not_assign;
    }

    /**
     * @param copsub_sector_not_assign the copsub_sector_not_assign to set
     */
    public void setCopsub_sector_not_assign(String copsub_sector_not_assign) {
        this.copsub_sector_not_assign = copsub_sector_not_assign;
    }

    /**
     * @return the copsub_sector_assign
     */
    public String getCopsub_sector_assign() {
        return copsub_sector_assign;
    }

    /**
     * @param copsub_sector_assign the copsub_sector_assign to set
     */
    public void setCopsub_sector_assign(String copsub_sector_assign) {
        this.copsub_sector_assign = copsub_sector_assign;
    }

    /**
     * @return the copsub_sector
     */
    public String[] getCopsub_sector() {
        this.copsub_sector = this.generateCopSubsectorArray();
        return copsub_sector;
    }

    /**
     * @param copsub_sector the copsub_sector to set
     */
    public void setCopsub_sector(String[] copsub_sector) {
        this.copsub_sector = copsub_sector;
    }

    /**
     * @return the copsub_sector_list
     */
    public String getCopsub_sector_list() {
        return copsub_sector_list;
    }

    /**
     * @param copsub_sector_list the copsub_sector_list to set
     */
    public void setCopsub_sector_list(String copsub_sector_list) {
        this.copsub_sector_list = copsub_sector_list;
    }

    /**
     * @return the copsub_sectors_not_assign
     */
    public String[] getCopsub_sectors_not_assign() {
        this.copsub_sectors_not_assign = generateCopSubsectorNotAssignArray();
        return copsub_sectors_not_assign;
    }

    /**
     * @param copsub_sectors_not_assign the copsub_sectors_not_assign to set
     */
    public void setCopsub_sectors_not_assign(String[] copsub_sectors_not_assign) {
        this.copsub_sectors_not_assign = copsub_sectors_not_assign;
    }

    /**
     * @return the copsub_sector_not_assign_list
     */
    public String getCopsub_sector_not_assign_list() {
        return copsub_sector_not_assign_list;
    }

    /**
     * @param copsub_sector_not_assign_list the copsub_sector_not_assign_list to
     * set
     */
    public void setCopsub_sector_not_assign_list(String copsub_sector_not_assign_list) {
        this.copsub_sector_not_assign_list = copsub_sector_not_assign_list;
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
     * @return the cp_initials
     */
    public String getCp_initials() {
        return cp_initials;
    }

    /**
     * @param cp_initials the cp_initials to set
     */
    public void setCp_initials(String cp_initials) {
        this.cp_initials = cp_initials;
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
     * @return the cp_mobile
     */
    public String getCp_mobile() {
        return cp_mobile;
    }

    /**
     * @param cp_mobile the cp_mobile to set
     */
    public void setCp_mobile(String cp_mobile) {
        this.cp_mobile = cp_mobile;
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
     * @return the cli
     */
    public String getCli() {
        return cli;
    }

    /**
     * @param cli the cli to set
     */
    public void setCli(String cli) {
        this.cli = cli;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the download_token_value_id
     */
    public String getDownload_token_value_id() {
        return download_token_value_id;
    }

    /**
     * @param download_token_value_id the download_token_value_id to set
     */
    public void setDownload_token_value_id(String download_token_value_id) {
        this.download_token_value_id = download_token_value_id;
    }


    /**
     * @return the save_btn
     */
    public boolean isSave_btn() {
        return save_btn;
    }

    /**
     * @param save_btn the save_btn to set
     */
    public void setSave_btn(boolean save_btn) {
        this.save_btn = save_btn;
    }

    /**
     * @return the edit_btn
     */
    public boolean isEdit_btn() {
        return edit_btn;
    }

    /**
     * @param edit_btn the edit_btn to set
     */
    public void setEdit_btn(boolean edit_btn) {
        this.edit_btn = edit_btn;
    }

    /**
     * @return the search_btn
     */
    public boolean isSearch_btn() {
        return search_btn;
    }

    /**
     * @param search_btn the search_btn to set
     */
    public void setSearch_btn(boolean search_btn) {
        this.search_btn = search_btn;
    }

    /**
     * @return the view_btn
     */
    public boolean isView_btn() {
        return view_btn;
    }

    /**
     * @param view_btn the view_btn to set
     */
    public void setView_btn(boolean view_btn) {
        this.view_btn = view_btn;
    }

    /**
     * @return the jasonobject
     */
    public String getJasonobject() {
        return jasonobject;
    }

    /**
     * @param jasonobject the jasonobject to set
     */
    public void setJasonobject(String jasonobject) {
        this.jasonobject = jasonobject;
    }


    /**
     * @return the hobby_id
     */
    public String getHobby_id() {
        return hobby_id;
    }

    /**
     * @param hobby_id the hobby_id to set
     */
    public void setHobby_id(String hobby_id) {
        this.hobby_id = hobby_id;
    }

    /**
     * @return the customer_hobby_list
     */
    public String getCustomer_hobby_list() {
        return customer_hobby_list;
    }

    /**
     * @param customer_hobby_list the customer_hobby_list to set
     */
    public void setCustomer_hobby_list(String customer_hobby_list) {
        this.customer_hobby_list = customer_hobby_list;
    }

    /**
     * @return the customerhobbiesinterest
     */
    public CustomerHobbiesInterests[] getCustomerhobbiesinterest() throws IOException {
        customerhobbiesinterest = this.generateHobbyInterestArray();
        return customerhobbiesinterest;
    }

    /**
     * @param customerhobbiesinterest the customerhobbiesinterest to set
     */
    public void setCustomerhobbiesinterest(CustomerHobbiesInterests[] customerhobbiesinterest) {
        this.customerhobbiesinterest = customerhobbiesinterest;
    }

    /**
     * @return the hobby_comment
     */
    public String getHobby_comment() {
        return hobby_comment;
    }

    /**
     * @param hobby_comment the hobby_comment to set
     */
    public void setHobby_comment(String hobby_comment) {
        this.hobby_comment = hobby_comment;
    }

    /**
     * @return the fd_type
     */
    public String getFd_type() {
        return fd_type;
    }

    /**
     * @param fd_type the fd_type to set
     */
    public void setFd_type(String fd_type) {
        this.fd_type = fd_type;
    }

    /**
     * @return the center
     */
    public String getCenter() {
        return center;
    }

    /**
     * @param center the center to set
     */
    public void setCenter(String center) {
        this.center = center;
    }

    /**
     * @return the download_btn
     */
    public boolean isDownload_btn() {
        return download_btn;
    }

    /**
     * @param download_btn the download_btn to set
     */
    public void setDownload_btn(boolean download_btn) {
        this.download_btn = download_btn;
    }

    /**
     * @return the Actual_lead_amount
     */
    public int getActual_lead_amount() {
        return Actual_lead_amount;
    }

    /**
     * @param Actual_lead_amount the Actual_lead_amount to set
     */
    public void setActual_lead_amount(int Actual_lead_amount) {
        this.Actual_lead_amount = Actual_lead_amount;
    }

}

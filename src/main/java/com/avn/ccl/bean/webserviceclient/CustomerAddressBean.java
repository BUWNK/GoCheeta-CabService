/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.bean.webserviceclient;

import org.json.JSONObject;

/**
 * @Author : Roshen Dilshan
 * @Document : CustomerAddressBean
 * @Date : Jun 27, 2015 9:29:55 AM
 */
public class CustomerAddressBean {

    private String address_id_view;
    private long address_id_pk;
    private String mf_customer_reference;
    private String mf_customer_contacts_id_pk;
    private String stakeholder_reference_no;
    private String address_line_1;
    private String address_line_2;
    private String address_line_3;
    private String address_type;
    private String address_type_description;
    private String city_id_fk;
    private String city_name;
    private String gs_division_id_fk;
    private String gs_division_name;
    private String district_id_fk;
    private String district_name;
    private String province_id_fk;
    private String province_name;
    private String country_id_fk;
    private String country_name;
    private String gps;
    private int is_preffered;
    private String land_phone_no;
    private String fax;
    private int version;
    private String type;
    private int record_status;
    private String billing_proof;

    /**
     * @return the address_id_pk
     */
    public long getAddress_id_pk() {
        return address_id_pk;
    }

    /**
     * @param address_id_pk the address_id_pk to set
     */
    public void setAddress_id_pk(long address_id_pk) {
        this.address_id_pk = address_id_pk;
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
     * @return the mf_customer_contacts_id_pk
     */
    public String getMf_customer_contacts_id_pk() {
        return mf_customer_contacts_id_pk;
    }

    /**
     * @param mf_customer_contacts_id_pk the mf_customer_contacts_id_pk to set
     */
    public void setMf_customer_contacts_id_pk(String mf_customer_contacts_id_pk) {
        this.mf_customer_contacts_id_pk = mf_customer_contacts_id_pk;
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
     * @return the address_line_1
     */
    public String getAddress_line_1() {
        return address_line_1;
    }

    /**
     * @param address_line_1 the address_line_1 to set
     */
    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    /**
     * @return the address_line_2
     */
    public String getAddress_line_2() {
        return address_line_2;
    }

    /**
     * @param address_line_2 the address_line_2 to set
     */
    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    /**
     * @return the address_line_3
     */
    public String getAddress_line_3() {
        return address_line_3;
    }

    /**
     * @param address_line_3 the address_line_3 to set
     */
    public void setAddress_line_3(String address_line_3) {
        this.address_line_3 = address_line_3;
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
        if (address_type.contentEquals("1")){
            address_type_description = "PERMANENT";
        } else if (address_type.contentEquals("2")){
            address_type_description = "OFFICE";
        } else if (address_type.contentEquals("3")){
            address_type_description = "BUSINESS";
        }
    }

    /**
     * @return the city_id_fk
     */
    public String getCity_id_fk() {
        return city_id_fk;
    }

    /**
     * @param city_id_fk the city_id_fk to set
     */
    public void setCity_id_fk(String city_id_fk) {
        this.city_id_fk = city_id_fk;
    }

    /**
     * @return the city_name
     */
    public String getCity_name() {
        return city_name;
    }

    /**
     * @param city_name the city_name to set
     */
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    /**
     * @return the gs_division_id_fk
     */
    public String getGs_division_id_fk() {
        return gs_division_id_fk;
    }

    /**
     * @param gs_division_id_fk the gs_division_id_fk to set
     */
    public void setGs_division_id_fk(String gs_division_id_fk) {
        this.gs_division_id_fk = gs_division_id_fk;
    }

    /**
     * @return the gs_division_name
     */
    public String getGs_division_name() {
        return gs_division_name;
    }

    /**
     * @param gs_division_name the gs_division_name to set
     */
    public void setGs_division_name(String gs_division_name) {
        this.gs_division_name = gs_division_name;
    }

    /**
     * @return the district_id_fk
     */
    public String getDistrict_id_fk() {
        return district_id_fk;
    }

    /**
     * @param district_id_fk the district_id_fk to set
     */
    public void setDistrict_id_fk(String district_id_fk) {
        this.district_id_fk = district_id_fk;
    }

    /**
     * @return the district_name
     */
    public String getDistrict_name() {
        return district_name;
    }

    /**
     * @param district_name the district_name to set
     */
    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    /**
     * @return the province_id_fk
     */
    public String getProvince_id_fk() {
        return province_id_fk;
    }

    /**
     * @param province_id_fk the province_id_fk to set
     */
    public void setProvince_id_fk(String province_id_fk) {
        this.province_id_fk = province_id_fk;
    }

    /**
     * @return the province_name
     */
    public String getProvince_name() {
        return province_name;
    }

    /**
     * @param province_name the province_name to set
     */
    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    /**
     * @return the country_id_fk
     */
    public String getCountry_id_fk() {
        return country_id_fk;
    }

    /**
     * @param country_id_fk the country_id_fk to set
     */
    public void setCountry_id_fk(String country_id_fk) {
        this.country_id_fk = country_id_fk;
    }

    /**
     * @return the country_name
     */
    public String getCountry_name() {
        return country_name;
    }

    /**
     * @param country_name the country_name to set
     */
    public void setCountry_name(String country_name) {
        this.country_name = country_name;
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
     * @return the is_preffered
     */
    public int getIs_preffered() {
        return is_preffered;
    }

    /**
     * @param is_preffered the is_preffered to set
     */
    public void setIs_preffered(int is_preffered) {
        this.is_preffered = is_preffered;
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
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the record_status
     */
    public int getRecord_status() {
        return record_status;
    }

    /**
     * @param record_status the record_status to set
     */
    public void setRecord_status(int record_status) {
        this.record_status = record_status;
    }

    @Override
    public String toString() {
        String val = "{"
                + "address_id_pk:" + "\"" + address_id_pk + "\""
                + ", mf_customer_reference:" + "\"" + mf_customer_reference + "\""
                + ", mf_customer_contacts_id_pk:" + "\"" + mf_customer_contacts_id_pk + "\""
                + ", stakeholder_reference_no:" + "\"" + stakeholder_reference_no + "\""
                + ", address_line_1:" + "\"" + address_line_1 + "\""
                + ", address_line_2:" + "\"" + address_line_2 + "\""
                + ", address_line_3:" + "\"" + address_line_3 + "\""
                + ", address_type:" + "\"" + address_type + "\""
                + ", address_type_description: " + "\"" + address_type_description + "\""
                + ", city_id_fk:" + "\"" + city_id_fk + "\""
                + ", city_name:" + "\"" + city_name + "\""
                + ", gs_division_id_fk:" + "\"" + gs_division_id_fk + "\""
                + ", gs_division_name:" + "\"" + gs_division_name + "\""
                + ", district_id_fk:" + "\"" + district_id_fk + "\""
                + ", district_name:" + "\"" + district_name + "\""
                + ", province_id_fk:" + "\"" + province_id_fk + "\""
                + ", province_name:" + "\"" + province_name + "\""
                + ", country_id_fk:" + "\"" + country_id_fk + "\""
                + ", country_name:" + "\"" + country_name + "\""
                + ", gps:" + "\"" + gps + "\""
                + ", is_preffered:" + "\"" + is_preffered + "\""
                + ", land_phone_no:" + "\"" + land_phone_no + "\""
                + ", fax:" + "\"" + fax + "\""
                + ", version:" + "\"" + version + "\""
                + ", type:" + "\"" + type + "\""
                + ", record_status:" + "\"" + record_status + "\""
                + '}';
        val = new JSONObject(val).toString();
        return val;
    }

    /**
     * @return the address_type_description
     */
    public String getAddress_type_description() {
        return address_type_description;
    }

    /**
     * @param address_type_description the address_type_description to set
     */
    public void setAddress_type_description(String address_type_description) {
        this.address_type_description = address_type_description;
    }

    /**
     * @return the address_id_view
     */
    public String getAddress_id_view() {
        return address_id_view;
    }

    /**
     * @param address_id_view the address_id_view to set
     */
    public void setAddress_id_view(String address_id_view) {
        this.address_id_view = address_id_view;
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

}

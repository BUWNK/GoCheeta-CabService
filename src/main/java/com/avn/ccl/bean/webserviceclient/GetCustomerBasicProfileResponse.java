//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.05 at 08:39:16 PM IST 
//


package com.avn.ccl.bean.webserviceclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="STAKEHOLDER_REFERENCE_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CUSTOMER_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TITLE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="INITIALS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FIRST_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NAME_IN_FULL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LAST_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DATE_OF_BIRTH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GENDER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IS_BLACK_LISTED" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LOCATION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LOCATION_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CUSTOMER_CODE_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IS_IN_CRIB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MOBILE_NO_1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMAIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ADDRESS_LINE_1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ADDRESS_LINE_2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ADDRESS_LINE_3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CITY_ID_FK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CITY_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GS_DIVISION_ID_FK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DIVISION_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DISTRICT_ID_FK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DISTRICT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PROVINCE_ID_FK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PROVINCE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="COUNTRY_ID_FK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="COUNTRY_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RELATIONSHIP_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SPOUSE_NIC_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SPOUSE_TITLE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SPOUSE_INITIALS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SPOUSE_FIRSTNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SPOUSE_LASTNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IS_CO_CUSTOMER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FILE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stakeholderreferenceno",
    "customercode",
    "title",
    "initials",
    "firstname",
    "nameinfull",
    "lastname",
    "dateofbirth",
    "gender",
    "isblacklisted",
    "location",
    "locationcode",
    "customercodetype",
    "isincrib",
    "mobileno1",
    "email",
    "addressline1",
    "addressline2",
    "addressline3",
    "cityidfk",
    "cityname",
    "gsdivisionidfk",
    "divisionname",
    "districtidfk",
    "districtname",
    "provinceidfk",
    "provincename",
    "countryidfk",
    "countryname",
    "relationshiptype",
    "spousenicno",
    "spousetitle",
    "spouseinitials",
    "spousefirstname",
    "spouselastname",
    "iscocustomer",
    "filename"
})
@XmlRootElement(name = "getCustomerBasicProfileResponse")
public class GetCustomerBasicProfileResponse {

    @XmlElement(name = "STAKEHOLDER_REFERENCE_NO", required = true, nillable = true)
    protected String stakeholderreferenceno;
    @XmlElement(name = "CUSTOMER_CODE", required = true, nillable = true)
    protected String customercode;
    @XmlElement(name = "TITLE", required = true, nillable = true)
    protected String title;
    @XmlElement(name = "INITIALS", required = true, nillable = true)
    protected String initials;
    @XmlElement(name = "FIRST_NAME", required = true, nillable = true)
    protected String firstname;
    @XmlElement(name = "NAME_IN_FULL", required = true, nillable = true)
    protected String nameinfull;
    @XmlElement(name = "LAST_NAME", required = true, nillable = true)
    protected String lastname;
    @XmlElement(name = "DATE_OF_BIRTH", required = true, nillable = true)
    protected String dateofbirth;
    @XmlElement(name = "GENDER", required = true, nillable = true)
    protected String gender;
    @XmlElement(name = "IS_BLACK_LISTED", required = true, nillable = true)
    protected String isblacklisted;
    @XmlElement(name = "LOCATION", required = true, nillable = true)
    protected String location;
    @XmlElement(name = "LOCATION_CODE", required = true, nillable = true)
    protected String locationcode;
    @XmlElement(name = "CUSTOMER_CODE_TYPE", required = true, nillable = true)
    protected String customercodetype;
    @XmlElement(name = "IS_IN_CRIB", required = true, nillable = true)
    protected String isincrib;
    @XmlElement(name = "MOBILE_NO_1", required = true, nillable = true)
    protected String mobileno1;
    @XmlElement(name = "EMAIL", required = true, nillable = true)
    protected String email;
    @XmlElement(name = "ADDRESS_LINE_1", required = true, nillable = true)
    protected String addressline1;
    @XmlElement(name = "ADDRESS_LINE_2", required = true, nillable = true)
    protected String addressline2;
    @XmlElement(name = "ADDRESS_LINE_3", required = true, nillable = true)
    protected String addressline3;
    @XmlElement(name = "CITY_ID_FK", required = true, nillable = true)
    protected String cityidfk;
    @XmlElement(name = "CITY_NAME", required = true, nillable = true)
    protected String cityname;
    @XmlElement(name = "GS_DIVISION_ID_FK", required = true, nillable = true)
    protected String gsdivisionidfk;
    @XmlElement(name = "DIVISION_NAME", required = true, nillable = true)
    protected String divisionname;
    @XmlElement(name = "DISTRICT_ID_FK", required = true, nillable = true)
    protected String districtidfk;
    @XmlElement(name = "DISTRICT_NAME", required = true, nillable = true)
    protected String districtname;
    @XmlElement(name = "PROVINCE_ID_FK", required = true, nillable = true)
    protected String provinceidfk;
    @XmlElement(name = "PROVINCE_NAME", required = true, nillable = true)
    protected String provincename;
    @XmlElement(name = "COUNTRY_ID_FK", required = true, nillable = true)
    protected String countryidfk;
    @XmlElement(name = "COUNTRY_NAME", required = true, nillable = true)
    protected String countryname;
    @XmlElement(name = "RELATIONSHIP_TYPE", required = true, nillable = true)
    protected String relationshiptype;
    @XmlElement(name = "SPOUSE_NIC_NO", required = true, nillable = true)
    protected String spousenicno;
    @XmlElement(name = "SPOUSE_TITLE", required = true, nillable = true)
    protected String spousetitle;
    @XmlElement(name = "SPOUSE_INITIALS", required = true, nillable = true)
    protected String spouseinitials;
    @XmlElement(name = "SPOUSE_FIRSTNAME", required = true, nillable = true)
    protected String spousefirstname;
    @XmlElement(name = "SPOUSE_LASTNAME", required = true, nillable = true)
    protected String spouselastname;
    @XmlElement(name = "IS_CO_CUSTOMER", required = true, nillable = true)
    protected String iscocustomer;
    @XmlElement(name = "FILE_NAME", required = true, nillable = true)
    protected String filename;

    /**
     * Gets the value of the stakeholderreferenceno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTAKEHOLDERREFERENCENO() {
        return stakeholderreferenceno;
    }

    /**
     * Sets the value of the stakeholderreferenceno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTAKEHOLDERREFERENCENO(String value) {
        this.stakeholderreferenceno = value;
    }

    /**
     * Gets the value of the customercode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSTOMERCODE() {
        return customercode;
    }

    /**
     * Sets the value of the customercode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSTOMERCODE(String value) {
        this.customercode = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTITLE() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTITLE(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the initials property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINITIALS() {
        return initials;
    }

    /**
     * Sets the value of the initials property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINITIALS(String value) {
        this.initials = value;
    }

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFIRSTNAME() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFIRSTNAME(String value) {
        this.firstname = value;
    }

    /**
     * Gets the value of the nameinfull property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAMEINFULL() {
        return nameinfull;
    }

    /**
     * Sets the value of the nameinfull property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAMEINFULL(String value) {
        this.nameinfull = value;
    }

    /**
     * Gets the value of the lastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLASTNAME() {
        return lastname;
    }

    /**
     * Sets the value of the lastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLASTNAME(String value) {
        this.lastname = value;
    }

    /**
     * Gets the value of the dateofbirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATEOFBIRTH() {
        return dateofbirth;
    }

    /**
     * Sets the value of the dateofbirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATEOFBIRTH(String value) {
        this.dateofbirth = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGENDER() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGENDER(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the isblacklisted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISBLACKLISTED() {
        return isblacklisted;
    }

    /**
     * Sets the value of the isblacklisted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISBLACKLISTED(String value) {
        this.isblacklisted = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOCATION() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOCATION(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the locationcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOCATIONCODE() {
        return locationcode;
    }

    /**
     * Sets the value of the locationcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOCATIONCODE(String value) {
        this.locationcode = value;
    }

    /**
     * Gets the value of the customercodetype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSTOMERCODETYPE() {
        return customercodetype;
    }

    /**
     * Sets the value of the customercodetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSTOMERCODETYPE(String value) {
        this.customercodetype = value;
    }

    /**
     * Gets the value of the isincrib property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISINCRIB() {
        return isincrib;
    }

    /**
     * Sets the value of the isincrib property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISINCRIB(String value) {
        this.isincrib = value;
    }

    /**
     * Gets the value of the mobileno1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOBILENO1() {
        return mobileno1;
    }

    /**
     * Sets the value of the mobileno1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOBILENO1(String value) {
        this.mobileno1 = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMAIL() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMAIL(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the addressline1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDRESSLINE1() {
        return addressline1;
    }

    /**
     * Sets the value of the addressline1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDRESSLINE1(String value) {
        this.addressline1 = value;
    }

    /**
     * Gets the value of the addressline2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDRESSLINE2() {
        return addressline2;
    }

    /**
     * Sets the value of the addressline2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDRESSLINE2(String value) {
        this.addressline2 = value;
    }

    /**
     * Gets the value of the addressline3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDRESSLINE3() {
        return addressline3;
    }

    /**
     * Sets the value of the addressline3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDRESSLINE3(String value) {
        this.addressline3 = value;
    }

    /**
     * Gets the value of the cityidfk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCITYIDFK() {
        return cityidfk;
    }

    /**
     * Sets the value of the cityidfk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCITYIDFK(String value) {
        this.cityidfk = value;
    }

    /**
     * Gets the value of the cityname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCITYNAME() {
        return cityname;
    }

    /**
     * Sets the value of the cityname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCITYNAME(String value) {
        this.cityname = value;
    }

    /**
     * Gets the value of the gsdivisionidfk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGSDIVISIONIDFK() {
        return gsdivisionidfk;
    }

    /**
     * Sets the value of the gsdivisionidfk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGSDIVISIONIDFK(String value) {
        this.gsdivisionidfk = value;
    }

    /**
     * Gets the value of the divisionname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIVISIONNAME() {
        return divisionname;
    }

    /**
     * Sets the value of the divisionname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIVISIONNAME(String value) {
        this.divisionname = value;
    }

    /**
     * Gets the value of the districtidfk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDISTRICTIDFK() {
        return districtidfk;
    }

    /**
     * Sets the value of the districtidfk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDISTRICTIDFK(String value) {
        this.districtidfk = value;
    }

    /**
     * Gets the value of the districtname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDISTRICTNAME() {
        return districtname;
    }

    /**
     * Sets the value of the districtname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDISTRICTNAME(String value) {
        this.districtname = value;
    }

    /**
     * Gets the value of the provinceidfk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROVINCEIDFK() {
        return provinceidfk;
    }

    /**
     * Sets the value of the provinceidfk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROVINCEIDFK(String value) {
        this.provinceidfk = value;
    }

    /**
     * Gets the value of the provincename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROVINCENAME() {
        return provincename;
    }

    /**
     * Sets the value of the provincename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROVINCENAME(String value) {
        this.provincename = value;
    }

    /**
     * Gets the value of the countryidfk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOUNTRYIDFK() {
        return countryidfk;
    }

    /**
     * Sets the value of the countryidfk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOUNTRYIDFK(String value) {
        this.countryidfk = value;
    }

    /**
     * Gets the value of the countryname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOUNTRYNAME() {
        return countryname;
    }

    /**
     * Sets the value of the countryname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOUNTRYNAME(String value) {
        this.countryname = value;
    }

    /**
     * Gets the value of the relationshiptype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRELATIONSHIPTYPE() {
        return relationshiptype;
    }

    /**
     * Sets the value of the relationshiptype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRELATIONSHIPTYPE(String value) {
        this.relationshiptype = value;
    }

    /**
     * Gets the value of the spousenicno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPOUSENICNO() {
        return spousenicno;
    }

    /**
     * Sets the value of the spousenicno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPOUSENICNO(String value) {
        this.spousenicno = value;
    }

    /**
     * Gets the value of the spousetitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPOUSETITLE() {
        return spousetitle;
    }

    /**
     * Sets the value of the spousetitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPOUSETITLE(String value) {
        this.spousetitle = value;
    }

    /**
     * Gets the value of the spouseinitials property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPOUSEINITIALS() {
        return spouseinitials;
    }

    /**
     * Sets the value of the spouseinitials property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPOUSEINITIALS(String value) {
        this.spouseinitials = value;
    }

    /**
     * Gets the value of the spousefirstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPOUSEFIRSTNAME() {
        return spousefirstname;
    }

    /**
     * Sets the value of the spousefirstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPOUSEFIRSTNAME(String value) {
        this.spousefirstname = value;
    }

    /**
     * Gets the value of the spouselastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPOUSELASTNAME() {
        return spouselastname;
    }

    /**
     * Sets the value of the spouselastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPOUSELASTNAME(String value) {
        this.spouselastname = value;
    }

    /**
     * Gets the value of the iscocustomer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISCOCUSTOMER() {
        return iscocustomer;
    }

    /**
     * Sets the value of the iscocustomer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISCOCUSTOMER(String value) {
        this.iscocustomer = value;
    }

    /**
     * Gets the value of the filename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFILENAME() {
        return filename;
    }

    /**
     * Sets the value of the filename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFILENAME(String value) {
        this.filename = value;
    }

}

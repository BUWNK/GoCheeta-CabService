/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.service.webservicecilent;

import com.avn.ccl.bean.serviceclient.basicprofile.CustomerBasicData;
import com.avn.ccl.bean.serviceclient.fullprofile.CustomerData;
import com.avn.ccl.bean.serviceclient.userdetail.UserDetail;
import com.avn.ccl.model.account.Account;
import org.json.JSONObject;
import org.json.XML;

import com.avn.ccl.bean.webserviceclient.CustomerBasicProfileSearchParameterBean;
import com.avn.ccl.daoimpl.branch.BranchDAOImpl;
import com.avn.ccl.daoimpl.addresstype.AddressTypeDAOImpl;
import com.avn.ccl.daoimpl.educationlevel.EducationLevelDAOImpl;
import com.avn.ccl.daoimpl.opdesignation.OPDesignationDAOImpl;
import com.avn.ccl.daoimpl.opprofession.OPProfessionDAOImpl;
import com.avn.ccl.daoimpl.opsector.OPSectorDAOImpl;
import com.avn.ccl.daoimpl.opsubsector.OPSubsectorDAOImpl;
import com.avn.ccl.model.address.Address;
import com.avn.ccl.model.education.Education;
import com.avn.ccl.util.Validation;
import com.avn.ccl.util.varlist.MasterDataVarList;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMException;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.wsdl.WSDLConstants;
import org.json.JSONArray;


/**
 * @Author : Roshen Dilshan
 * @Document : CCLServiceClient
 * @Date : Jun 27, 2015 9:20:40 AM
 */
public class CCLServiceClient {

    private OMFactory factory;

    private EndpointReference SAVINGS_PROXY_LK = null;
    private EndpointReference SAVINGS_READ_PROXY_LK = null;
    private EndpointReference ADMIN_MGT_READ_DSLK = null;

    private final String SUCCESS_NAMESPASE_PREFIX = "success_msg";
    private final String FAIL_NAMESPASE_PREFIX = "fault_msg";

    public void createCustomer(Account account, DataSource dataSource, String endpoint, HttpSession session) throws Exception {
        try {
            SOAPEnvelope envelope = this.generateCustomerCreateEnvelope(account, dataSource,session);
            SAVINGS_PROXY_LK = new EndpointReference(endpoint);

            MessageContext messageContext = new MessageContext();
            messageContext.setEnvelope(envelope);

            ServiceClient serviceClient = new ServiceClient();
            Options options = new Options();
            options.setAction("urn:maintainSavingsCustomer");
            options.setTo(SAVINGS_PROXY_LK);
            serviceClient.setOptions(options);

            OperationClient operationClient = serviceClient.createClient(ServiceClient.ANON_OUT_IN_OP);
            operationClient.addMessageContext(messageContext);
            operationClient.execute(true);
            MessageContext responseMessageContext = operationClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            SOAPEnvelope responseEnvelope = responseMessageContext.getEnvelope();
            SOAPBody responseSoapBody = responseEnvelope.getBody();
            OMElement response = responseSoapBody.getFirstElement();
            if (response.findNamespaceURI(SUCCESS_NAMESPASE_PREFIX) != null) {
                JSONObject successObject = (XML.toJSONObject(response.toString())).getJSONObject(SUCCESS_NAMESPASE_PREFIX + ":maintainSavingsCustomerResponse");
                String successStatus = successObject.getString(SUCCESS_NAMESPASE_PREFIX + ":status");
                System.out.println(successStatus);
                JSONObject success = successObject.getJSONObject(SUCCESS_NAMESPASE_PREFIX + ":successFields").getJSONObject(SUCCESS_NAMESPASE_PREFIX + ":success");
                String successCode = success.getString("sucess_code");
                String successMessage = success.getString("content");
                String arguments = success.getString("args");
                System.out.println("Success Code : " + successCode);
                System.out.println("Success Message : " + successMessage);
                System.out.println("Arguments : " + arguments);

                String CCID = arguments.split("\\|")[0];
                String CUSTOMER_CODE = arguments.split("\\|")[1];

                System.out.println("CCID : " + CCID);
                System.out.println("CUSTOMER CODE : " + CUSTOMER_CODE);
                account.setCcid(CCID);
                account.setCustomer_code(CUSTOMER_CODE);
                account.setMessage_type("SUCCESS");
                account.setMessage_description(successMessage);
            } else if (response.findNamespaceURI(FAIL_NAMESPASE_PREFIX) != null) {
                JSONObject faultObject = (XML.toJSONObject(response.toString())).getJSONObject(FAIL_NAMESPASE_PREFIX + ":payload");
                String faultStatus = faultObject.getString(FAIL_NAMESPASE_PREFIX + ":status");
                System.out.println(faultStatus);

                List<String> errorCodes = new ArrayList<>();
                try {
                    JSONArray fault_array = faultObject.getJSONObject(FAIL_NAMESPASE_PREFIX + ":fault").getJSONArray(FAIL_NAMESPASE_PREFIX + ":faultFields");
                    System.out.println("Array");
                    for (int i = 0; i < fault_array.length(); i++) {
                        String errorCode = fault_array.getJSONObject(i).getJSONObject(FAIL_NAMESPASE_PREFIX + ":error").getString("error_code");
                        System.out.println(errorCode);
                        errorCodes.add(errorCode);
                    }
                } catch (Exception e) {
                }

                try {
                    String errorCode = faultObject.getJSONObject(FAIL_NAMESPASE_PREFIX + ":fault").getJSONObject(FAIL_NAMESPASE_PREFIX + ":faultFields").getJSONObject(FAIL_NAMESPASE_PREFIX + ":error").getString("error_code");
                    System.out.println("Object");
                    System.out.println(errorCode);
                    errorCodes.add(errorCode);
                } catch (Exception e) {
                }

                account.setErrorCodes(errorCodes);
                account.setMessage_type("ERROR");
                account.setMessage_description("");
            }
        } catch (AxisFault | OMException exception) {
            throw exception;
        }
    }

    private SOAPEnvelope generateCustomerCreateEnvelope(Account account, DataSource dataSource, HttpSession session) throws Exception {
        String user = (String) session.getAttribute("username");
        SOAPEnvelope envelope = OMAbstractFactory.getSOAP11Factory().getDefaultEnvelope();
        factory = envelope.getOMFactory();
        OMNamespace messageSavings = factory.createOMNamespace("http://cclk.lk/schemas/message/savings/v1", "v1");
        OMNamespace entitySavings = factory.createOMNamespace("http://cclk.lk/schemas/entity/savings/v1", "v11");
        OMNamespace commonSavings = factory.createOMNamespace("http://cclk.lk/schemas/entity/common/v1", "v12");
        envelope.declareNamespace(messageSavings);
        envelope.declareNamespace(entitySavings);
        envelope.declareNamespace(commonSavings);

        BranchDAOImpl branchDAOImpl = new BranchDAOImpl();
        branchDAOImpl.setDataSource(dataSource);

        OMElement rootElement = factory.createOMElement("maintainSavingsCustomer", messageSavings);
        rootElement.addChild(this.createElement("USER_ID", user, messageSavings));
        rootElement.addChild(this.createElement("USER_LOCATION_CODE", branchDAOImpl.getBranchCodeForUser(user), messageSavings));
        rootElement.addChild(this.createElement("TRANSACTION_TYPE", "1", messageSavings));

        OMElement savings = factory.createOMElement("T_CO_STAKEHOLDERS_SAVINGS", entitySavings);
        savings.addChild(this.createElement("CUS_CATEGORY", account.getCustomer_category(), entitySavings));
        savings.addChild(this.createElement("CUS_CATEGORY_TYPE", account.getCustomer_category_type(), entitySavings));
        savings.addChild(this.createElement("CUSTOMER_CODE_TYPE_ID_FK", account.getCustomer_code_type(), entitySavings));
        savings.addChild(this.createElement("CUSTOMER_CODE", account.getCustomer_code(), entitySavings));
        savings.addChild(this.createElement("LOCATION_CODE", account.getBranch_location(), entitySavings));
        if (account.getCustomer_category().equalsIgnoreCase(MasterDataVarList.CCL_CODE_ACCOUNT_CATEGORY_INDIVIDUAL)) {
            savings.addChild(this.createElement("TITLE", account.getTitle(), entitySavings));
            savings.addChild(this.createElement("INITIALS", account.getInitials(), entitySavings));
            savings.addChild(this.createElement("PREFERRED_NAME", account.getPreferred_name(), entitySavings));
            savings.addChild(this.createElement("LASTNAME", account.getLast_name(), entitySavings));
            savings.addChild(this.createElement("NAME_IN_FULL", account.getName_in_full(), entitySavings));
            savings.addChild(this.createElement("DATE_OF_BIRTH", account.getDate_of_birth(), entitySavings));
            savings.addChild(this.createElement("GENDER", account.getGender(), entitySavings));
            savings.addChild(this.createElement("MOTHERS_MAIDEN_NAME", account.getMothers_maiden_name(), entitySavings));
        } else {
            savings.addChild(this.createElement("LASTNAME", account.getCopemployer(), entitySavings));
            savings.addChild(this.createElement("DATE_OF_BIRTH", "1111-01-01", entitySavings));
        }

        if (Validation.isDropDownSelected(account.getNationality())) {
            savings.addChild(this.createElement("NATIONALITY_ID_FK", account.getNationality(), entitySavings));
        }

        if (Validation.isDropDownSelected(account.getReligion())) {
            savings.addChild(this.createElement("RELIGION", account.getReligion(), entitySavings));
        }

        if (Validation.isDropDownSelected(account.getMarital_status())) {
            savings.addChild(this.createElement("MARITAL_STATUS", account.getMarital_status(), entitySavings));
        }

        if (Validation.isTextEntered(account.getPreferred_language())) {
            savings.addChild(this.createElement("PREFERRED_LANGUAGE", account.getPreferred_language(), entitySavings));
        }

        if (Validation.isTextEntered(account.getDependent())) {
            savings.addChild(this.createElement("DEPENDENT", account.getDependent(), entitySavings));
        }

        if (Validation.isTextEntered(account.getMobile_01())) {
            savings.addChild(this.createElement("MOBILE_NO_1", account.getMobile_01(), entitySavings));
        }

        if (Validation.isTextEntered(account.getMobile_02())) {
            savings.addChild(this.createElement("MOBILE_NO_2", account.getMobile_02(), entitySavings));
        }

        if (Validation.isTextEntered(account.getEmail())) {
            savings.addChild(this.createElement("EMAIL", account.getEmail(), entitySavings));
        }

        if (Validation.isDropDownSelected(account.getIs_tax_payee())) {
            savings.addChild(this.createElement("IS_TAX_PAYEE", account.getIs_tax_payee(), entitySavings));
        }

        if (Validation.isTextEntered(account.getTax_no())) {
            savings.addChild(this.createElement("TAX_NO", account.getTax_no(), entitySavings));
        }

        AddressTypeDAOImpl addressDAO = new AddressTypeDAOImpl();
        addressDAO.setDataSource(dataSource);
        for (Address addressBean : account.getAddresses()) {
            OMElement address = factory.createOMElement("T_CO_STAKEHOLDER_ADDRESSESS_SAVINGS", entitySavings);
            address.addChild(this.createElement("ADDRESS_TYPE", /*addressDAO.getAddressTypeById(*/addressBean.getAddress_type()/*).getAddresstypecode()*/, entitySavings));
            address.addChild(this.createElement("ADDRESS_LINE_1", addressBean.getAddress_line_01(), entitySavings));
            address.addChild(this.createElement("ADDRESS_LINE_2", addressBean.getAddress_line_02(), entitySavings));

            if (Validation.isTextEntered(addressBean.getAddress_line_03())) {
                address.addChild(this.createElement("ADDRESS_LINE_3", addressBean.getAddress_line_03(), entitySavings));
            }

            address.addChild(this.createElement("COUNTRY_ID_FK", addressBean.getCountry(), entitySavings));
            address.addChild(this.createElement("PROVINCE_ID_FK", addressBean.getProvince(), entitySavings));
            address.addChild(this.createElement("DISTRICT_ID_FK", addressBean.getDistrict(), entitySavings));

            if (Validation.isDropDownSelected(addressBean.getCity())) {
                address.addChild(this.createElement("CITY_ID_FK", addressBean.getCity(), entitySavings));
            }

            address.addChild(this.createElement("RECORD_STATUS", "1", entitySavings));

            if (Validation.isTextEntered(addressBean.getGs())) {
                address.addChild(this.createElement("GSDIVISION", addressBean.getGs(), entitySavings));
            }

            if (Validation.isTextEntered(addressBean.getGps())) {
                address.addChild(this.createElement("GPS", addressBean.getGps(), entitySavings));
            }

            address.addChild(this.createElement("LAND_PHONE_NO", addressBean.getLand_phone_no(), entitySavings));
            address.addChild(this.createElement("BILLING_PROOF", addressBean.getBilling_proof(), entitySavings));
            savings.addChild(address);
        }

        EducationLevelDAOImpl educationLevelDAO = new EducationLevelDAOImpl();
        educationLevelDAO.setDataSource(dataSource);
        for (Education educationBean : account.getEducations()) {
            OMElement education = factory.createOMElement("T_CO_STKHOLDR_EDU_DETAILS", entitySavings);
            if (Validation.isDropDownSelected(educationBean.getEducation_level())) {
                education.addChild(this.createElement("EDUCATION_LEVEL", educationLevelDAO.getEducationLevelById(educationBean.getEducation_level()).getEducationlevelcode(), entitySavings));
            }
            if (Validation.isTextEntered(educationBean.getInstitute())) {
                education.addChild(this.createElement("OTHER_INSTITUTE", educationBean.getInstitute(), entitySavings));
            }
            education.addChild(this.createElement("RECORD_STATUS", "1", entitySavings));
            savings.addChild(education);
        }

        OMElement occupation = factory.createOMElement("T_CO_STAKEHOLDER_BUSINESSES", entitySavings);
        OPDesignationDAOImpl designationDAO = new OPDesignationDAOImpl();
        designationDAO.setDataSource(dataSource);
        boolean isOccupationEntered = false;
        if (Validation.isDropDownSelected(account.getDesignation())) {
            occupation.addChild(this.createElement("DESIGNATION_ID_FK", designationDAO.getDesignationById(account.getDesignation()).getCode(), entitySavings));
            isOccupationEntered = true;
        }

//        OPLevelDAOImpl levelDAO = new OPLevelDAOImpl();
//        levelDAO.setDataSource(dataSource);
//        if (Validation.isDropDownSelected(account.getLevel())) {
//            occupation.addChild(this.createElement("EMPLOYEE_LEVEL", levelDAO.getLevelById(account.getLevel()).getCode(), entitySavings));
//            isOccupationEntered = true;
//        }

        OPProfessionDAOImpl professionDAO = new OPProfessionDAOImpl();
        professionDAO.setDataSource(dataSource);
        if (Validation.isDropDownSelected(account.getProfession())) {
            occupation.addChild(this.createElement("PROFFESION_ID_FK", professionDAO.getProfessionById(account.getProfession()).getCode(), entitySavings));
            isOccupationEntered = true;
        }

        if (Validation.isTextEntered(account.getEmployer())) {
            occupation.addChild(this.createElement("OTHER_EMPLOYEMENT", account.getEmployer(), entitySavings));
            isOccupationEntered = true;
        }

        OPSectorDAOImpl sectorDAO = new OPSectorDAOImpl();
        sectorDAO.setDataSource(dataSource);
        if (Validation.isDropDownSelected(account.getSector())) {
            String sectorid = sectorDAO.getSectorById(account.getSector()).getCode();
            occupation.addChild(this.createElement("SECTOR_ID_FK", sectorid, entitySavings));

            OPSubsectorDAOImpl subsectorDAO = new OPSubsectorDAOImpl();
            subsectorDAO.setDataSource(dataSource);
            if (account.getSub_sector().length > 0) {
                for (String subSector : account.getSub_sector()) {
                    OMElement subsector = factory.createOMElement("T_CO_STAKEHOLDER_BNS_SUB_SECTS", entitySavings);
                    subsector.addChild(this.createElement("SECTOR_ID_FK", sectorid, entitySavings));
                    subsector.addChild(this.createElement("SUB_SECTOR_ID_FK", subsectorDAO.getSubsectorById(subSector).getCode(), entitySavings));
                    subsector.addChild(this.createElement("RECORD_STATUS", "1", entitySavings));
                    occupation.addChild(subsector);
                }
            }
            isOccupationEntered = true;
        }

        if (Validation.isDropDownSelected(account.getCopsector())) {
            String sectorid = sectorDAO.getSectorById(account.getCopsector()).getCode();
            occupation.addChild(this.createElement("SECTOR_ID_FK", sectorid, entitySavings));

            OPSubsectorDAOImpl subsectorDAO = new OPSubsectorDAOImpl();
            subsectorDAO.setDataSource(dataSource);
            if (account.getCopsub_sector().length > 0) {
                for (String subSector : account.getCopsub_sector()) {
                    OMElement subsector = factory.createOMElement("T_CO_STAKEHOLDER_BNS_SUB_SECTS", entitySavings);
                    subsector.addChild(this.createElement("SECTOR_ID_FK", sectorid, entitySavings));
                    subsector.addChild(this.createElement("SUB_SECTOR_ID_FK", subsectorDAO.getSubsectorById(subSector).getCode(), entitySavings));
                    subsector.addChild(this.createElement("RECORD_STATUS", "1", entitySavings));
                    occupation.addChild(subsector);
                }
            }
            isOccupationEntered = true;
        }

        if (isOccupationEntered) {
            occupation.addChild(this.createElement("RECORD_STATUS", "1", entitySavings));
        }

        savings.addChild(occupation);

        OMElement contactperson = factory.createOMElement("T_CO_STAKEHOLDER_CONTACT_PERSONS", entitySavings);
        if (Validation.isTextEntered(account.getCp_nic())) {
            contactperson.addChild(this.createElement("CUSTOMER_CODE_TYPE", "1", entitySavings));
            contactperson.addChild(this.createElement("CUSTOMER_CODE", account.getCp_nic(), entitySavings));
        }
        if (Validation.isDropDownSelected(account.getCp_title())) {
            contactperson.addChild(this.createElement("TITLE", account.getCp_title(), entitySavings));
        }
        if (Validation.isTextEntered(account.getCp_name_in_full())) {
            contactperson.addChild(this.createElement("NAME_IN_FULL", account.getCp_name_in_full(), entitySavings));
        }
        if (Validation.isTextEntered(account.getCp_initials())) {
            contactperson.addChild(this.createElement("INITIALS", account.getCp_initials(), entitySavings));
        }
        if (Validation.isTextEntered(account.getCp_last_name())) {
            contactperson.addChild(this.createElement("LASTNAME", account.getCp_last_name(), entitySavings));
        }
        if (Validation.isTextEntered(account.getCp_preferred_name())) {
            contactperson.addChild(this.createElement("FIRST_NAME", account.getCp_preferred_name(), entitySavings));
        }
        if (Validation.isTextEntered(account.getCp_mobile())) {
            contactperson.addChild(this.createElement("MOBILE_NO", account.getCp_mobile(), entitySavings));
        }
        if (Validation.isTextEntered(account.getCp_email())) {
            contactperson.addChild(this.createElement("EMAIL", account.getCp_email(), entitySavings));
        }
        savings.addChild(contactperson);

        if (account.getNic_file() != null) {
            OMElement attachment = factory.createOMElement("T_CO_STAKEHOLDER_ATTACHEMENTS", entitySavings);
            attachment.addChild(this.createElement("RECORD_STATUS", "1", entitySavings));
            attachment.addChild(this.createElement("VERSION", "0", entitySavings));
            attachment.addChild(this.createElement("FILE_TYPE", "NIC", entitySavings));
            attachment.addChild(this.createElement("FILE_NAME", account.getNic_file_name(), entitySavings));
            attachment.addChild(this.createElement("STORE_LOCATION", account.getNic_file_location(), entitySavings));
            savings.addChild(attachment);
        }

        if (account.getSignature_file() != null) {
            OMElement attachment = factory.createOMElement("T_CO_STAKEHOLDER_ATTACHEMENTS", entitySavings);
            attachment.addChild(this.createElement("RECORD_STATUS", "1", entitySavings));
            attachment.addChild(this.createElement("VERSION", "0", entitySavings));
            attachment.addChild(this.createElement("FILE_TYPE", "SIGNATURE", entitySavings));
            attachment.addChild(this.createElement("FILE_NAME", account.getSignature_file_name(), entitySavings));
            attachment.addChild(this.createElement("STORE_LOCATION", account.getSignature_file_location(), entitySavings));
            savings.addChild(attachment);
        }

        rootElement.addChild(savings);
        envelope.getBody().addChild(rootElement);
        return envelope;
    }

    private OMElement createElement(String elementName, String elementValue, OMNamespace elementNamespace) {
        OMElement element;
        element = factory.createOMElement(elementName, elementNamespace);
        element.addChild(factory.createOMText(element, elementValue));
        return element;
    }

    public CustomerBasicData getCustpmerBasicProfileSearch(CustomerBasicProfileSearchParameterBean dataBean, String endpoint) throws Exception {
        CustomerBasicData basicData = null;
        try {
            SOAPEnvelope envelope = OMAbstractFactory.getSOAP11Factory().getDefaultEnvelope();
            SAVINGS_READ_PROXY_LK = new EndpointReference(endpoint);
            factory = envelope.getOMFactory();
            OMNamespace messageSavings = factory.createOMNamespace("http://cclk.lk/schemas/message/savings/v1", "v1");
            envelope.declareNamespace(messageSavings);

            OMElement rootElement = factory.createOMElement("getCustomerBasicProfile", messageSavings);
            rootElement.addChild(this.createElement("PI_SEARCH_TYPE", String.valueOf(dataBean.getSearchType()), messageSavings));
            rootElement.addChild(this.createElement("PI_SEARCH_REFERENCE", dataBean.getSearchReferance(), messageSavings));
            rootElement.addChild(this.createElement("PI_ADDITIONAL_CRITERIA", "", messageSavings));
            envelope.getBody().addChild(rootElement);

            MessageContext messageContext = new MessageContext();
            messageContext.setEnvelope(envelope);

            ServiceClient serviceClient = new ServiceClient();
            Options options = new Options();
            options.setAction("urn:getCustomerBasicProfile");
            options.setTo(SAVINGS_READ_PROXY_LK);
            serviceClient.setOptions(options);

            OperationClient operationClient = serviceClient.createClient(ServiceClient.ANON_OUT_IN_OP);
            operationClient.addMessageContext(messageContext);
            operationClient.execute(true);
            MessageContext responseMessageContext = operationClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            SOAPEnvelope responseEnvelope = responseMessageContext.getEnvelope();

            XMLStreamReader reader = responseEnvelope.getXMLStreamReader();
            reader.nextTag();
            while (!reader.getLocalName().contentEquals("customerBasicData")) {
                reader.nextTag();
            }
            JAXBContext context = JAXBContext.newInstance(CustomerBasicData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            basicData = (CustomerBasicData) unmarshaller.unmarshal(reader);
        } catch (AxisFault | JAXBException | OMException | XMLStreamException axisFault) {
            throw axisFault;
        }
        return basicData;
    }

    public CustomerData getCustomerFullProfileSearch(String id, String endpoint) throws Exception {
        CustomerData customerData = null;
        try {
            SOAPEnvelope envelope = OMAbstractFactory.getSOAP11Factory().getDefaultEnvelope();
            SAVINGS_READ_PROXY_LK = new EndpointReference(endpoint);
            factory = envelope.getOMFactory();
            OMNamespace messageSavings = factory.createOMNamespace("http://cclk.lk/schemas/message/savings/v1", "v1");
            envelope.declareNamespace(messageSavings);

            OMElement rootElement = factory.createOMElement("getCustomerFullProfileView", messageSavings);
            rootElement.addChild(this.createElement("CUSTOMER_CRITERIA", "ST.CUSTOMER_CODE='" + id.toUpperCase() + "'", messageSavings));
            rootElement.addChild(this.createElement("EPF_NUMBER", "", messageSavings));
            envelope.getBody().addChild(rootElement);

            MessageContext messageContext = new MessageContext();
            messageContext.setEnvelope(envelope);

            ServiceClient serviceClient = new ServiceClient();
            Options options = new Options();
            options.setAction("urn:getCustomerFullProfileView");
            options.setTo(SAVINGS_READ_PROXY_LK);
            serviceClient.setOptions(options);

            OperationClient operationClient = serviceClient.createClient(ServiceClient.ANON_OUT_IN_OP);
            operationClient.addMessageContext(messageContext);
            operationClient.execute(true);
            MessageContext responseMessageContext = operationClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            SOAPEnvelope responseEnvelope = responseMessageContext.getEnvelope();

            XMLStreamReader reader = responseEnvelope.getXMLStreamReader();
            reader.nextTag();
            while (!reader.getLocalName().contentEquals("customerData")) {
                reader.nextTag();
            }
            JAXBContext context = JAXBContext.newInstance(CustomerData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            customerData = (CustomerData) unmarshaller.unmarshal(reader);
        } catch (AxisFault | JAXBException | OMException | XMLStreamException exception) {
            throw exception;
        }
        return customerData;
    }

    public UserDetail getUserDetails(String endpoint, String username) throws Exception {
        UserDetail userDetail = null;
        try {
            SOAPEnvelope envelope = OMAbstractFactory.getSOAP11Factory().getDefaultEnvelope();
            ADMIN_MGT_READ_DSLK = new EndpointReference(endpoint);
            factory = envelope.getOMFactory();
            OMNamespace messageAdmin = factory.createOMNamespace("http://cclk.lk/services/data/admin/v1", "v1");
            envelope.declareNamespace(messageAdmin);

            OMElement rootElement = factory.createOMElement("getEmployeeByUserId", messageAdmin);
            rootElement.addChild(this.createElement("USER_ID", username, messageAdmin));
            envelope.getBody().addChild(rootElement);

            MessageContext messageContext = new MessageContext();
            messageContext.setEnvelope(envelope);

            ServiceClient serviceClient = new ServiceClient();
            Options options = new Options();
            options.setAction("urn:getEmployeeByUserId");
            options.setTo(ADMIN_MGT_READ_DSLK);
            serviceClient.setOptions(options);

            OperationClient operationClient = serviceClient.createClient(ServiceClient.ANON_OUT_IN_OP);
            operationClient.addMessageContext(messageContext);
            operationClient.execute(true);
            MessageContext responseMessageContext = operationClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            SOAPEnvelope responseEnvelope = responseMessageContext.getEnvelope();

            XMLStreamReader reader = responseEnvelope.getXMLStreamReader();
            reader.nextTag();
            while (!reader.getLocalName().contentEquals("results")) {
                reader.nextTag();
            }
            JAXBContext context = JAXBContext.newInstance(UserDetail.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            userDetail = (UserDetail) unmarshaller.unmarshal(reader);
        } catch (AxisFault | JAXBException | OMException | XMLStreamException exception) {
            throw exception;
        }
        return userDetail;
    }
}

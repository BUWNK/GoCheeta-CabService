/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util.varlist;

import java.io.File;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : CommonVarList
 * @Created on : Jul 30, 2015, 10:18:36 AM
 */
public class CommonVarList {

    //------------------------ Report Param ------------------------//
    public static final String REPORT_LOCATION = File.separator + "WEB-INF" + File.separator + "views" + File.separator + "jsp" + File.separator + "reports";
    public static final String REPORT_LOGO_PATH = File.separator + "resources" + File.separator + "img" + File.separator + "CCL_LOGO_RPT.png";
    public static final String REPORT_NAME_CASE_RPT = File.separator + "case" + File.separator + "CCL_CRM_CASE_REPORT.jasper";
    public static final String REPORT_NAME_CALL_RPT = File.separator + "call" + File.separator + "CCL_CRM_CALL_REPORT.jasper";
    public static final String REPORT_NAME_ACCOUNT_INDIVIDUAL_RPT = File.separator + "account" + File.separator + "CCL_CRM_ACCOUNT_INDIVIDUAL_REPORT.jasper";
    public static final String REPORT_NAME_ACCOUNT_CORPORATE_RPT = File.separator + "account" + File.separator + "CCL_CRM_ACCOUNT_CORPORATE_REPORT.jasper";
    //--------------------------------------------------------------//
    //------------------------ Common Values ------------------------//
    public static final String EMPTY_OR_NULL_REPLACE_VALUE = "--";
    public static final String EMPTY_OR_NULL_REPLACE_VALUE_ALL = "-All-";
    public static final String EMPTY_OR_NULL_REPLACE_VALUE_NA = "-N/A-";
    
    public static final String EMAIL_DOAMIN = "@cclk.lk";
    //---------------------------------------------------------------//
    //------------------------ Context Param Names ------------------------//
    public static final String CONTEXT_PARAM_SERVICE_SAVINGS_PROXY_LK = "service_savings_proxy_lk";
    public static final String CONTEXT_PARAM_SERVICE_SAVINGS_READ_PROXY_LK = "service_savings_read_proxy_lk";
    public static final String CONTEXT_PARAM_SERVICE_ADMIN_MANAGEMENT_READ_DSLK = "service_admin_management_read_dslk";

    public static final String CONTEXT_PARAM_SERVICE_LDAP_LOGIN = "service_ldap_login";
    public static final String CONTEXT_PARAM_SERVICE_LDAP_AUTHENTICATION_HOST = "ldap_authentication_host";

    public static final String CONTEXT_PARAM_REPORT_BUILD_PATH = "temp_report_build_location";
    public static final String CONTEXT_PARAM_REPORT_NUMBER_OF_PAGES_IN_MEMORY = "number_of_pdf_pages";

    public static final String CONTEXT_PARAM_NIC_FILE_UPLOAD = "nic_file_location";
    public static final String CONTEXT_PARAM_SIGNATURE_FILE_UPLOAD = "signature_file_location";

    public static final String CONTEXT_PARAM_CASE_ATTACH_FILE_UPLOAD = "case_attach_file_location";
    
    public static final String CONTEXT_PARAM_NUMBER_OF_ROWS_PER_EXCEL = "number_of_rows_per_excel";
    public static final String CONTEXT_PARAM_NUMBER_OF_SELECT_ROWS = "number_of_select_rows";

    public static final String CONTEXT_PARAM_IS_SHEDUER_TASK_RUN = "is_sheduler_task_run";
//    public static final String CONTEXT_PARAM_IS_CC_PERSON = "cc_person";

    public static final String CONTEXT_PARAM_FTP_SERVER_LOCATION = "ftp_server_location";
    public static final String CONTEXT_PARAM_FTP_SERVER_FILE_LOCATION = "ftp_server_file_location";
    public static final String CONTEXT_PARAM_FTP_SERVER_PORT = "ftp_server_port";
    public static final String CONTEXT_PARAM_FTP_SERVER_USERNAME = "ftp_server_username";
    public static final String CONTEXT_PARAM_FTP_SERVER_PASSWORD = "ftp_server_password";
    public static final String CONTEXT_PARAM_SOFTWARE_VERSION = "software_version";
    //---------------------------------------------------------------------//
    //------------------------ Date Formats ------------------------//
    public static final String DATE_FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";
    public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String TIME_FORMAT_hh_mm_a = "hh:mm a";
    public static final String DATE_FORMAT_yyyy = "yyyy";
    public static final String DATE_FORMAT_yyyy_MM_dd_hh_mm_a = "yyyy-MM-dd hh:mm a";
    public static final String DATE_FORMAT_yyyy_MM_dd_hh_mm_ss_a = "yyyy-MM-dd hh:mm:ss a";
    public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_yyyy_MM = "yyyy-MM";
    public static final String DATE_FORMAT_dd_MMM_yy_hhmmssSSa = "dd-MMM-yy hh.mm.ss.SS a";
    public static final String DATE_FORMAT_dd_MMM_yy_hhmmssa = "dd-MMM-yy hh.mm.ss a";
    public static final String DATE_FORMAT_dd_MMM_yy_hhmmss = "dd-MMM-yy hh:mm:ss";
    public static final String DATE_FORMAT_dd_MMM_yy_HHmmss = "dd-MMM-yy HH:mm:ss";
    public static final String DATE_FORMAT_dd_MMM_yyyy = "dd-MMM-yyyy";
    public static final String TIME_FORMAT_hh_mm_24 = "HH:mm";
    //--------------------------------------------------------------//
    //------------------------ Sheduler task ------------------------//

    //---------------------------------------------------------------//
    //------------------------ Notification ------------------------//
    public static final String NOTIFICATION_MAIL_FROM = "noreplycclcrm@cclk.lk";
    //--------------------------------------------------------------//
    //------------------------ Datatable Sorting ------------------------//
    public static final String DATA_TABLE_SORTING_ASC = "ASC";
    public static final String DATA_TABLE_SORTING_DESC = "DESC";
    //-------------------------------------------------------------------//
    
    public static int NOT_COMPLETED_ACTIVITIES = 41;
    public static int COMPLETED_ACTIVITIES = 42;
}

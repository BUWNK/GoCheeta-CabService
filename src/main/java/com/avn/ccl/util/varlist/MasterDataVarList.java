/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util.varlist;

/**
 * @Author : Roshen Dilshan
 * @Document : MasterDataVarList
 * @Created on : Aug 19, 2015, 7:10:05 PM
 */
public class MasterDataVarList {

    //------------------------ Account Categories ------------------------//
    public static final String CCL_CODE_ACCOUNT_CATEGORY_INDIVIDUAL = "0";
    public static final String CCL_CODE_ACCOUNT_CATEGORY_CORPORATE = "1";
    public static final String CCL_CODE_ACCOUNT_CATEGORY_MISCELLANEOUS = "2";
    //------------------------ Account Categories ------------------------//
    //------------------------ Customer Code Types ------------------------//
    public static final String CCL_CODE_CUSTOMER_CODE_NIC = "1";
    public static final String CCL_CODE_CUSTOMER_CODE_CCID = "4";
    public static final String CCL_CODE_CUSTOMER_CODE_BRN = "6";
    //------------------------ Customer Code Types ------------------------//
    //------------------------ Status Categories ------------------------//
    public static final int CCL_CODE_STATUS_CATEGORY_CASE = 1;
    public static final int CCL_CODE_STATUS_CATEGORY_DEFAULT = 2;
    public static final int CCL_CODE_STATUS_CATEGORY_ACCOUNT_SAVINGS = 3;
    public static final int CCL_CODE_STATUS_CATEGORY_ACCOUNT_LOAN = 4;
    public static final int CCL_CODE_STATUS_CATEGORY_CALLLOG = 5;
    //------------------------ Status Categories ------------------------//
    //------------------------ Status ------------------------//
    public static final int CCL_CODE_STATUS_OPEN = 1;
    public static final int CCL_CODE_STATUS_CLOSE = 2;
    public static final int CCL_CODE_STATUS_ACTIVE = 3;
    public static final int CCL_CODE_STATUS_INACTIVE = 4;
    public static final int CCL_CODE_STATUS_REASSIGN = 6;
    public static final int CCL_CODE_STATUS_LEAD_INITIAL = 37;
    public static final int CCL_CODE_STATUS_LEAD_CLOSED = 38;
    public static final int CCL_CODE_STATUS_LEAD_LOST = 39;
    public static final int CCL_CODE_STATUS_TRANSFER_TO_SALES_PIPELINE = 40;
    public static final int AFFINITI_CODE_STATUS_ACTIVITY_COMPLEATED = 42;
    //------------------------ Status ------------------------//
    //------------------------ Notification Codes ------------------------//
    public static final int CCL_CODE_NOTIFICATION_TEMPLATE_CASE = 1;
    public static final int CCL_CODE_NOTIFICATION_TEMPLATE_REMIND_1 = 2;
    public static final int CCL_CODE_NOTIFICATION_TEMPLATE_REMIND_2 = 3;
    public static final int CCL_CODE_NOTIFICATION_TEMPLATE_REMIND_3 = 4;
    public static final int CCL_CODE_NOTIFICATION_TEMPLATE_CASE_REASSIGNEE = 5;
    public static final int CCL_CODE_NOTIFICATION_TEMPLATE_SMS = 6;
    public static final int CCL_CODE_NOTIFICATION_SP_1 = 7;
    //------------------------ Notification Codes ------------------------//
    //------------------------ Notification Codes ------------------------//
    public static final int CCL_CODE_ROLE_USER = 1;
    public static final int CCL_CODE_ROLE_ADMIN = 2;
    public static final int CCL_CODE_ROLE_SUPER_ADMIN = 3;
    public static final int CCL_CODE_ROLE_BRANCHMANAGER = 4;
    public static final int CCL_CODE_ROLE_REGENALMANAGER = 5;
    //------------------------ Notification Codes ------------------------//
    //------------------------ Employee Category Codes ------------------------//
    public static final String CCL_CODE_EMPLOYEE_CATEGORY_BRANCH_MANAGER = "BM";
    public static final String CCL_CODE_EMPLOYEE_CATEGORY_REGIONAL_MANAGER = "RM";
    public static final String CCL_CODE_EMPLOYEE_CATEGORY_ZONE_MANAGER = "ZM";
    public static final String CCL_CODE_EMPLOYEE_CATEGORY_INTERNAL_AUDIT = "IA";
    public static final String CCL_CODE_EMPLOYEE_CATEGORY_CONTACT_CENTRE = "CC";
    //------------------------ Employee Category Codes ------------------------//
    //--------------------Case Organization parameters Codes ------------------------//
    public static final int CCL_CODE_NOTIFICATION_BR = 8;
    public static final int CCL_CODE_NOTIFICATION_MF = 9;
    public static final int CCL_CODE_MAX_INVALID_LOGIN_COUNT = 11;
    public static final int CCL_CODE_NOTIFICATION_LEVEL_1 = 4;
    public static final int CCL_CODE_NOTIFICATION_LEVEL_2 = 5;
    public static final int CCL_CODE_NOTIFICATION_LEVEL_3 = 6;
    public static final int CCL_CODE_NOTIFICATION_LEVEL_4 = 7;
    //--------------------Case Organization parameters Codes ------------------------//
    //------------------------ Notification ------------------------//
    public static final String CCL_CODE_NOTIFICATION_TRUE = "1";
    public static final String CCL_CODE_NOTIFICATION_FALSE = "0";
    public static final String CCL_CODE_DEFAULT_CC_NOTIFICATION_PERSON = "10";
    public static final String CCL_CODE_NOTIFICATION_COMPONENT_CASE = "CASE";
    public static final String CCL_CODE_NOTIFICATION_COMPONENT_LEADS = "LEADS";
    public static final String CCL_CODE_NOTIFICATION_COMPONENT_CCLSMSROUT = "ccbulk";
    public static final String CCL_CODE_NOTIFICATION_COMPONENT_CCLGROUPID = "CRM";
    //------------------------ Notification ------------------------//

    public static final String CCL_CODE_LANUAGE_CATEGORY_DEFAULT = "1";
    public static final String CCL_CODE_CALLDIRECTION_CATEGORY_DEFAULT = "1";
    public static final String CCL_CODE_FOLLOUPACTION_CATEGORY_DEFAULT = "7";
    public static final String CCL_CODE_TITLE_DEFAULT = "MR.";
    public static final String CCL_CODE_CASE_PRIORITY_DEFAULT = "1";

    //------------------------ Pagemanagemet ------------------------//
    public static final int CCL_CODE_ONLY_PARENT_SECTION_TRUE = 1;
    public static final int CCL_CODE_ONLY_PARENT_SECTION_FALSE = 0;
    public static final int CCL_CODE_CLICKABLE_TRUE = 1;
    public static final int CCL_CODE_CLICKABLE_FALSE = 0;
    //------------------------ Pagemanagemet ------------------------//
    //------------------------ Pagemanagemet TaskList ------------------------//
    public static final int CCL_CODE_VIEW = 1;
    public static final int CCL_CODE_CREATE = 2;
    public static final int CCL_CODE_SEARCH = 3;
    public static final int CCL_CODE_UPDATE = 4;
    public static final int CCL_CODE_DELETE = 5;
    public static final int CCL_CODE_ASSIGN = 6;
    public static final int CCL_CODE_CASE_VS_CALL_GRAPH = 7;
    public static final int CCL_CODE_PRODUCT_WISE_GRAPH = 8;
    public static final int CCL_CODE_MY_CALLS = 9;
    public static final int CCL_CODE_MY_CASE = 10;
    public static final int CCL_CODE_ASSIGN_CASE_GRAPH = 11;
    public static final int CCL_CODE_DOWNLOAD = 12;

    //------------------------ Pagemanagemet TaskList ------------------------//
    //------------------------ Pagemanagemet Section_List ------------------------//
    public static final int CCL_CODE_CASE_SECTION_ID = 84;
    public static final int CCL_CODE_HOME_SECTION_ID = 82;
    public static final int CCL_CODE_CUSTOMER_SECTION_ID = 83;
    public static final int CCL_CODE_REPORT_SECTION_ID = 85;
    public static final int CCL_CODE_CALLCENTER_SECTION_ID = 86;
    public static final int CCL_CODE_SETTING_SECTION_ID = 87;
    public static final int CCL_CODE_USER_SECTION_ID = 88;
    public static final int CCL_CODE_CUSTOMER_VISIT_ID = 141;
    public static final int CCL_CODE_SECTION_ID = 121;
    public static final int CCL_CODE_SUBSECTION_ID = 122;
    public static final int CCL_CODE_SECTIONTASK_ID = 126;
    public static final int CCL_CODE_USERROLE_SUBSECTION_ID = 124;
    public static final int CCL_CODE_USERROLE_SECTION_ID = 123;
    public static final int CCL_CODE_USERROLE_TASK_ID = 125;
    public static final int CCL_CODE_CUSTOMER_INTERACT_HISTORY_ID = 161;
    public static final int CCL_CODE_WEDGET_ID = 22;
    public static final int CCL_CODE_USER_ROLE_WEDGET_ID = 24;
    public static final int CCL_CODE_USER_TARGET_SETTING_ID = 63;
    public static final int CCL_CODE_SALESPIPELINE = 81;
    //------------------------ Pagemanagemet Section_List ------------------------//
    //------------------------ Pagemanagemet Sub_Section_List ------------------------//
    public static final int CCL_CODE_CREATE_NEW_CUSTOMER_SUBSECTION_ID = 22;
    public static final int CCL_CODE_SEARCH_CUSTOMER_SUBSECTION_ID = 21;
    public static final int CCL_CODE_VIEW_CUSTOMER_SUBSECTION_ID = 74;

//CASE
    public static final int CCL_CODE_CASAE_SEARCH_SUBSECTION_ID = 23;
    public static final int CCL_CODE_CREATE_NEW_CASE_SUBSECTION_ID = 24;
    public static final int CCL_CODE_VIEW_CASE_SUBSECTION_ID = 55;
    public static final int CCL_CODE_EDIT_CASE_SUBSECTION_ID = 56;

    public static final int CCL_CODE_SEARCH_CASE_REPORT_SUBSECTION_ID = 25;
    public static final int CCL_CODE_CALL_REPORT_SUBSECTION_ID = 26;
    public static final int CCL_CODE_CUSTOMER_REPORT_SUBSECTION_ID = 27;

    public static final int CCL_CODE_CALL_SEARCH_SUBSECTION_ID = 28;
    public static final int CCL_CODE_CREATE_NEW_CALL_LOG_SUBSECTION_ID = 29;
    public static final int CCL_CODE_VIEW_CALL_CENTER_SUBSECTION_ID = 53;
    public static final int CCL_CODE_EDIT_CALL_CENTER_SUBSECTION_ID = 54;

    public static final int CCL_CODE_AUDIT_TRACE_SUBSECTION_ID = 30;

    public static final int CCL_CODE_SEARCH_USER_SUBSECTION_ID = 31;
    public static final int CCL_CODE_ADD_NEW_USER_SUBSECTION_ID = 32;
    public static final int CCL_CODE_CCL_CODE_EDIT_USER_SUBSECTION_ID = 72;//
    public static final int CCL_CODE_CCL_CODE_VIEW_USER_SUBSECTION_ID = 73;//

    public static final int CCL_CODE_CUSTOMERVISIT_SEARCH_SUBSECTION_ID = 57;
    public static final int CCL_CODE_CREATE_CUSTOMER_VISIT_SUBSECTION_ID = 58;
    public static final int CCL_CODE_VIEW_CUSTOMERVISIT_SUBSECTION_ID = 59;
    public static final int CCL_CODE_EDIT_CUSTOMERVISIT_SUBSECTION_ID = 60;

    public static final int CCL_CODE_CREATE_NEW_SECTION_SUBSECTION_ID = 42;
    public static final int CCL_CODE_SEARCH_SECTION_SUBSECTION_ID = 41;
    public static final int CCL_CODE_VIEW_SECTION_SUBSECTION_ID = 63;
    public static final int CCL_CODE_EDIT_SECTION_SUBSECTION_ID = 62;

    public static final int CCL_CODE_CREATE_NEW_SUBSECTION_SUBSECTION_ID = 44;
    public static final int CCL_CODE_VIEW_SUBSECTION_SUBSECTION_ID = 65;
    public static final int CCL_CODE_EDIT_SUBSECTION_SUBSECTION_ID = 64;
    public static final int CCL_CODE_SEARCH_SUBSECTION_SUBSECTION_ID = 43;

    public static final int CCL_CODE_CREATE_NEW_USERROLESUBSECTION_SUBSECTION_ID = 48;
    public static final int CCL_CODE_SEARCH_USERROLESUBSECTION_SUBSECTION_ID = 47;
    public static final int CCL_CODE_CCL_CODE_EDIT_USERROLESUBSECTION_SUBSECTION_ID = 68;
    public static final int CCL_CODE_CCL_CODE_VIEW_USERROLESUBSECTION_SUBSECTION_ID = 69;

    public static final int CCL_CODE_CCL_CODE_CREATE_NEW_USERROLESECTION_SUBSECTION_ID = 46;
    public static final int CCL_CODE_CCL_CODE_SEARCH_USERROLESECTION_SUBSECTION_ID = 45;
    public static final int CCL_CODE_CCL_CODE_EDIT_USERROLESECTION_SUBSECTION_ID = 66;
    public static final int CCL_CODE_CCL_CODE_VIEW_USERROLESECTION_SUBSECTION_ID = 67;

    public static final int CCL_CODE_CREATE_NEW_USERROLETASK_SUBSECTION_ID = 50;
    public static final int CCL_CODE_CCL_CODE_EDIT_USERROLETASK_SUBSECTION_ID = 70;
    public static final int CCL_CODE_CCL_CODE_VIEW_USERROLETASK_SUBSECTION_ID = 71;
    public static final int CCL_CODE_CCL_CODE_SEARCH_USERROLETASK_SUBSECTION_ID = 49;

    public static final int CCL_CODE_CREATE_NEW_SECTIONTASK_SUBSECTION_ID = 52;
    public static final int CCL_CODE_CCL_CODE_EDIT_SECTIONTASK_SUBSECTION_ID = 72;
    public static final int CCL_CODE_CCL_CODE_VIEW_SECTIONTASK_SUBSECTION_ID = 73;
    public static final int CCL_CODE_SEARCH_SECTIONTASK_SUBSECTION_ID = 51;

    public static final int CCL_CODE_SEARCH_CUSTOMER_INTERACT_HISTORY_SUBSECTION_ID = 81;

    public static final int CCL_CODE_CREATE_NEW_WEDGET_SUBSECTION_ID = 38;
    public static final int CCL_CODE_CCL_CODE_EDIT_WEDGET_SUBSECTION_ID = 39;
    public static final int CCL_CODE_CCL_CODE_VIEW_WEDGET_SUBSECTION_ID = 40;
    public static final int CCL_CODE_SEARCH_WEDGET_SUBSECTION_ID = 37;

    public static final int CCL_CODE_CREATE_NEW_USER_ROLE_WEDGET_SUBSECTION_ID = 83;
    public static final int CCL_CODE_CCL_CODE_EDIT_USER_ROLE_WEDGET_SUBSECTION_ID = 84;
    public static final int CCL_CODE_CCL_CODE_VIEW_USER_ROLE_WEDGET_SUBSECTION_ID = 85;
    public static final int CCL_CODE_CCL_CODE_DELETE_USER_ROLE_WEDGET_SUBSECTION_ID = 85;
    public static final int CCL_CODE_SEARCH_USER_ROLE_WEDGET_SUBSECTION_ID = 82;

    public static final int CCL_CODE_CREATE_NEW_TARGETSETIING_WEDGET_SUBSECTION_ID = 103;
    public static final int CCL_CODE_CCL_CODE_EDIT_TARGETSETTING_SUBSECTION_ID = 104;
    public static final int CCL_CODE_CCL_CODE_VIEW_TARGETSETTING_SUBSECTION_ID = 105;
    public static final int CCL_CODE_SEARCH_USER_TARGETSETTING_SUBSECTION_ID = 102;

//    public static final int CCL_CODE_EDITE_USERROLETASK_SUBSECTION_ID = 42;//CHANGE IDS
//    public static final int CCL_CODE_SEARCH_USERROLESUBSECTION_SUBSECTION_ID = 42;//CHANGE IDS
    //------------------------ Pagemanagemet Sub_Section_List ------------------------//
    //------------------------ Reminder Notification ------------------------//
    public static final int CCL_CODE_REMINDER_NOTIFICATION_NOT_REMIND = 32;
    public static final int CCL_CODE_REMINDER_NOTIFICATION_REMIND = 33;
    public static final int CCL_CODE_REMINDER_NOTIFICATION_NOT_VIEWED = 36;
    public static final int CCL_CODE_REMINDER_NOTIFICATION_VIEWED = 35;
    public static final int CCL_CODE_REMINDER_NOTIFICATION_DELETE = 34;
    //------------------------ Reminder Notification ------------------------//
    //------------------------ Case Types ------------------------//
    public static final int CCL_CODE_CASE_TYPE_BALANCE_INQUERY = 1;
    public static final int CCL_CODE_CASE_TYPE_CUSTOMER_MISHANDLED = 2;
    public static final int CCL_CODE_CASE_TYPE_DROP_CALL = 3;
    public static final int CCL_CODE_CASE_TYPE_INFORMATION = 4;
    public static final int CCL_CODE_CASE_TYPE_LOANS = 5;
    public static final int CCL_CODE_CASE_TYPE_REQUEST = 6;
    public static final int CCL_CODE_CASE_TYPE_SAVINGS = 7;
    public static final int CCL_CODE_CASE_TYPE_TRANSFER_CALLS = 8;
    public static final int CCL_CODE_CASE_TYPE_LEADS = 9;
    public static final int CCL_CODE_CASE_TYPE_OTHER = 10;
    public static final int CCL_CODE_CASE_TYPE_LETTERS = 11;
    //------------------------ Case Types ------------------------//
    //------------------------ Target Period ------------------------//
    public static final int AFFINITI_TARGETPERIOD_MONTHLY = 1;
    public static final int AFFINITI_TARGETPERIOD_QUARTELY = 2;
    public static final int AFFINITI_TARGETPERIOD_ANNUALLY = 3;
    //------------------------ Target Period ------------------------//
//------------------------ Dash Bord Chart ------------------------//
    public static final int CCL_CODE_DASHBORD_CALL_VS_CASECHART = 1;
    public static final int CCL_CODE_DASHBORD_MY_CASE = 7;
    public static final int CCL_CODE_DASHBORD_ASSIGN_CASE = 2;
    public static final int CCL_CODE_DASHBORD_PRODUCT_WISE_INQUIRY = 4;
    public static final int CCL_CODE_DASHBORD_MY_CALL = 3;
    public static final int CCL_CODE_DASHBORD_TARGET_GRAPH = 5;
    public static final int CCL_CODE_DASHBORD_BAR_CHART = 6;
    public static final int CCL_CODE_DASHBORD_CONTACKLEAD = 8;
    public static final int CCL_CODE_DASHBORD_LEADRATION = 9;
    //------------------------ Dash Bord Chart ------------------------//
    //------------------------ Activity Types ------------------------ //
    public static final int AFFINITI_CODE_ACTIVITY_TYPE_CALLS = 1;
    public static final int AFFINITI_CODE_ACTIVITY_TYPE_PROPOSALS = 2;
    public static final int AFFINITI_CODE_ACTIVITY_TYPE_VISITS_MEETINGS = 3;
    //------------------------ Activity Types ------------------------ //
    //------------------------ Territory Types ------------------------ //
    public static final int AFFINITI_CODE_TERRITORY_REGIONS = 2;
    public static final int AFFINITI_CODE_TERRITORY_BRANCH = 3;
    //------------------------ Territory Types ------------------------ //
    //------------------------ Branch Codes ------------------------ //
    public static final String CCL_CODE_BRANCH_CITY_OFFIE = "CO";
    //------------------------ Branch Codes ------------------------ //
    //------------------------ Organization Hierarchy ------------------------ //
    public static final int AFFINITI_ORGHIERARCHY_SENIOR_MGR = 1;
    public static final int AFFINITI_ORGHIERARCHY_REGIONAL_MGR = 2;
    public static final int AFFINITI_ORGHIERARCHY_OPERATIONAL_HD = 6;
    public static final int AFFINITI_ORGHIERARCHY_BRANCH_MGR = 3;
    public static final int AFFINITI_ORGHIERARCHY_TEAM_LD = 4;
    public static final int AFFINITI_ORGHIERARCHY_MARKETING_EXT = 5;
    //------------------------ Organization Hierarchy ------------------------ //
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.callcenter;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class CallCenter {

    //private int callLogId;
    private String callLogId;
    // private int accountId;
    private String accountId;
    private String nameInFull;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "hh:mm a")
    private Date startTime;
    private String callDirection;
    private String followUpAction;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date callbackDate;
    @DateTimeFormat(pattern = "hh:mm a")
    private Date callbackTime;
    private String comments;
    private int caseId;
    private Date createdDateTime;
    private Date lastUpdatedDateTime;
    private String callDirectionDes;
    private String followUpActionDes;
    private String telephone;
    private String telephone2;
    private String username;
    private String input;
    private String searchoption;
    private String caseTypeId;
    private String productId;
    private String caseTypeDes;
    private String productIdDes;
    private String status;
    private String statusDes;
    private String callcreattype;
    private String branchcode;
    private String branchDes;
    private String companyname;
    private boolean iddnum;
//    Reciving call moudle
    private String title;
    private String initials;
    private String preferred_name;
    private String last_name;
    private String name_in_full;
    private String preferred_language;
    private String languageID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date calldate;
    private String calltime;
    //------ Lead Info ------//
    private BigDecimal amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date forecastedDate;
    private String channel;
    private String camping;
    private String promotionCode;
    //------ Lead Info ------//
    //------bntprivilage---//
    private boolean save_btn;
    private boolean edit_btn;
    private boolean search_btn;
    private boolean view_btn;
    
    private Date from_date;
    private Date to_date;
    private String firstName;
    
    private boolean topipeline;

    /**
     * @return the callLogId
     */
    /**
     * @return the accountId
     */
    /**
     * @return the nameInFull
     */
    public String getNameInFull() {
        return nameInFull;
    }

    /**
     * @param nameInFull the nameInFull to set
     */
    public void setNameInFull(String nameInFull) {
        this.nameInFull = nameInFull;
    }

    /**
     * @return the startDate
     */
    /**
     * @param startTime the startTime to set
     */
    /**
     * @return the callbackDate
     */
    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the caseId
     */
    public int getCaseId() {
        return caseId;
    }

    /**
     * @param caseId the caseId to set
     */
    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    /**
     * @return the createdDateTime
     */
    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * @param createdDateTime the createdDateTime to set
     */
    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * @return the lastUpdatedDateTime
     */
    public Date getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    /**
     * @param lastUpdatedDateTime the lastUpdatedDateTime to set
     */
    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    /**
     * @return the callDirectionDes
     */
    public String getCallDirectionDes() {
        return callDirectionDes;
    }

    /**
     * @param callDirectionDes the callDirectionDes to set
     */
    public void setCallDirectionDes(String callDirectionDes) {
        this.callDirectionDes = callDirectionDes;
    }

    /**
     * @return the followUpActionDes
     */
    public String getFollowUpActionDes() {
        return followUpActionDes;
    }

    /**
     * @param followUpActionDes the followUpActionDes to set
     */
    public void setFollowUpActionDes(String followUpActionDes) {
        this.followUpActionDes = followUpActionDes;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the accountId
     */
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
     * @return the calldate
     */
    public Date getCalldate() {
        return calldate;
    }

    /**
     * @param calldate the calldate to set
     */
    public void setCalldate(Date calldate) {
        this.calldate = calldate;
    }

    /**
     * @return the calltime
     */
    public String getCalltime() {
        return calltime;
    }

    /**
     * @param calltime the calltime to set
     */
    public void setCalltime(String calltime) {
        this.calltime = calltime;
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
     * @return the callDirection
     */
    public String getCallDirection() {
        return callDirection;
    }

    /**
     * @param callDirection the callDirection to set
     */
    public void setCallDirection(String callDirection) {
        this.callDirection = callDirection;
    }

    /**
     * @return the followUpAction
     */
    public String getFollowUpAction() {
        return followUpAction;
    }

    /**
     * @param followUpAction the followUpAction to set
     */
    public void setFollowUpAction(String followUpAction) {
        this.followUpAction = followUpAction;
    }

    /**
     * @return the startDate
     */
//    public String getStartDate() {
//        return startDate;
//    }
//
//    /**
//     * @param startDate the startDate to set
//     */
//    public void setStartDate(String startDate) {
//        this.startDate = startDate;
//    }
    /**
     * @return the startTime
     */
//    public String getStartTime() {
//        return startTime;
//    }
//
//    /**
//     * @param startTime the startTime to set
//     */
//    public void setStartTime(String startTime) {
//        this.startTime = startTime;
//    }
    /**
     * @return the accounId
     */
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the callLogId
     */
    public String getCallLogId() {
        return callLogId;
    }

    /**
     * @param callLogId the callLogId to set
     */
    public void setCallLogId(String callLogId) {
        this.callLogId = callLogId;
    }

    /**
     * @return the input
     */
    public String getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * @return the searchoption
     */
    public String getSearchoption() {
        return searchoption;
    }

    /**
     * @param searchoption the searchoption to set
     */
    public void setSearchoption(String searchoption) {
        this.searchoption = searchoption;
    }

    /**
     * @return the caseTypeId
     */
    public String getCaseTypeId() {
        return caseTypeId;
    }

    /**
     * @param caseTypeId the caseTypeId to set
     */
    public void setCaseTypeId(String caseTypeId) {
        this.caseTypeId = caseTypeId;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the telephone2
     */
    public String getTelephone2() {
        return telephone2;
    }

    /**
     * @param telephone2 the telephone2 to set
     */
    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    /**
     * @return the caseTypeDes
     */
    public String getCaseTypeDes() {
        return caseTypeDes;
    }

    /**
     * @param caseTypeDes the caseTypeDes to set
     */
    public void setCaseTypeDes(String caseTypeDes) {
        this.caseTypeDes = caseTypeDes;
    }

    /**
     * @return the productIdDes
     */
    public String getProductIdDes() {
        return productIdDes;
    }

    /**
     * @param productIdDes the productIdDes to set
     */
    public void setProductIdDes(String productIdDes) {
        this.productIdDes = productIdDes;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the statusDes
     */
    public String getStatusDes() {
        return statusDes;
    }

    /**
     * @param statusDes the statusDes to set
     */
    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    /**
     * @return the callcreattype
     */
    public String getCallcreattype() {
        return callcreattype;
    }

    /**
     * @param callcreattype the callcreattype to set
     */
    public void setCallcreattype(String callcreattype) {
        this.callcreattype = callcreattype;
    }

    /**
     * @return the branchcode
     */
    public String getBranchcode() {
        return branchcode;
    }

    /**
     * @param branchcode the branchcode to set
     */
    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    /**
     * @return the branchDes
     */
    public String getBranchDes() {
        return branchDes;
    }

    /**
     * @param branchDes the branchDes to set
     */
    public void setBranchDes(String branchDes) {
        this.branchDes = branchDes;
    }

    /**
     * @return the languageID
     */
    public String getLanguageID() {
        return languageID;
    }

    /**
     * @param languageID the languageID to set
     */
    public void setLanguageID(String languageID) {
        this.languageID = languageID;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the companyname
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * @param companyname the companyname to set
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    /**
     * @return the callbackDate
     */
    public Date getCallbackDate() {
        return callbackDate;
    }

    /**
     * @param callbackDate the callbackDate to set
     */
    public void setCallbackDate(Date callbackDate) {
        this.callbackDate = callbackDate;
    }

    /**
     * @return the callbackTime
     */
    public Date getCallbackTime() {
        return callbackTime;
    }

    /**
     * @param callbackTime the callbackTime to set
     */
    public void setCallbackTime(Date callbackTime) {
        this.callbackTime = callbackTime;
    }

    /**
     * @return the iddnum
     */
    public boolean isIddnum() {
        return iddnum;
    }

    /**
     * @param iddnum the iddnum to set
     */
    public void setIddnum(boolean iddnum) {
        this.iddnum = iddnum;
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
     * @return the from_date
     */
    public Date getFrom_date() {
        return from_date;
    }

    /**
     * @param from_date the from_date to set
     */
    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    /**
     * @return the to_date
     */
    public Date getTo_date() {
        return to_date;
    }

    /**
     * @param to_date the to_date to set
     */
    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the topipeline
     */
    public boolean isTopipeline() {
        return topipeline;
    }

    /**
     * @param topipeline the topipeline to set
     */
    public void setTopipeline(boolean topipeline) {
        this.topipeline = topipeline;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the forecastedDate
     */
    public Date getForecastedDate() {
        return forecastedDate;
    }

    /**
     * @param forecastedDate the forecastedDate to set
     */
    public void setForecastedDate(Date forecastedDate) {
        this.forecastedDate = forecastedDate;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the camping
     */
    public String getCamping() {
        return camping;
    }

    /**
     * @param camping the camping to set
     */
    public void setCamping(String camping) {
        this.camping = camping;
    }

    /**
     * @return the promotionCode
     */
    public String getPromotionCode() {
        return promotionCode;
    }

    /**
     * @param promotionCode the promotionCode to set
     */
    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }


}

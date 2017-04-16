/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.casemgt;

import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author maheshl
 */
public class Case {

    private String caseId;
    private String caseCode;
    private String caseDate;
    private String caseTypeId;
    private String caseTypeDes;
    private String priorityId;
    private String priorityDes;
    private String departmentId;
    private String departmentDes;
    private String productId;
    private String productDes;
    private String accountId;
    private String subject;
    private String description;
    private String assigneeId1;
    private String assignee1Name;
    private String assigneeId2;
    private String assignee2Name;
    private int assignee;
    private String statusid;
    private String statusDes;
    private Date createdDateTime;
    private Date lastUpdatedDateTime;
    private String branchcode;
    private String caseCategory;
    private String customername;
    private String phonenumber;
    private String nic;
    private String searchoption;
    private String input;
    private String userEmail;
    private String branchDesc;
    private String created;
    private String assignees;
    private String casecreateduser;
    private String title;
    private String employeeID;
    private boolean iddnum;

    private String createdDateTimeString;

    /*callcenter*/
    private String caseCallLogId;
    /**
     * customervisit
     */
    private String customervisitId;

    private String email;
    private String user;
    private int userRole;
    private long employeeId;
    //*case Histroy update*/
    private String hcasedate;
    private String hstatusid;
    private String hsattudDes;
    private String hbranchid;
    private String hbranchDes;
    private String hbrmf;
    private String hempname;
    private String hcomment;
    private String historyid;
    private String hempid;
    private boolean autoassign;
    /*fileupload*/
    private List<MultipartFile> files;
    private MultipartFile upload_file;
    private String filename;
    private String file_location;
    private String download_token_value_id;
    private String fileid;

    /*checkbtnprivilage*/
    private boolean save_btn;
    private boolean edit_btn;
    private boolean search_btn;
    private boolean view_btn;

    /*Interact History Search Parameters*/
    private Date from_date;
    private Date to_date;
    private String firstName;
    private String telephone;

    /**
     * @return the caseId
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * @param caseId the caseId to set
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    /**
     * @return the caseCode
     */
    public String getCaseCode() {
        return caseCode;
    }

    /**
     * @param caseCode the caseCode to set
     */
    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    /**
     * @return the caseDate
     */
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
     * @return the priorityId
     */
    public String getPriorityId() {
        return priorityId;
    }

    /**
     * @param priorityId the priorityId to set
     */
    public void setPriorityId(String priorityId) {
        this.priorityId = priorityId;
    }

    /**
     * @return the priorityDes
     */
    public String getPriorityDes() {
        return priorityDes;
    }

    /**
     * @param priorityDes the priorityDes to set
     */
    public void setPriorityDes(String priorityDes) {
        this.priorityDes = priorityDes;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the departmentDes
     */
    public String getDepartmentDes() {
        return departmentDes;
    }

    /**
     * @param departmentDes the departmentDes to set
     */
    public void setDepartmentDes(String departmentDes) {
        this.departmentDes = departmentDes;
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
     * @return the productDes
     */
    public String getProductDes() {
        return productDes;
    }

    /**
     * @param productDes the productDes to set
     */
    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the assignee1Name
     */
    public String getAssignee1Name() {
        return assignee1Name;
    }

    /**
     * @param assignee1Name the assignee1Name to set
     */
    public void setAssignee1Name(String assignee1Name) {
        this.assignee1Name = assignee1Name;
    }

    /**
     * @return the assigneeId2
     */
    public String getAssigneeId2() {
        return assigneeId2;
    }

    /**
     * @param assigneeId2 the assigneeId2 to set
     */
    public void setAssigneeId2(String assigneeId2) {
        this.assigneeId2 = assigneeId2;
    }

    /**
     * @return the statusid
     */
    public String getStatusid() {
        return statusid;
    }

    /**
     * @param statusid the statusid to set
     */
    public void setStatusid(String statusid) {
        this.statusid = statusid;
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
     * @return the caseCallLogId
     */
    public String getCaseCallLogId() {
        return caseCallLogId;
    }

    /**
     * @param caseCallLogId the caseCallLogId to set
     */
    public void setCaseCallLogId(String caseCallLogId) {
        this.caseCallLogId = caseCallLogId;
    }

    /**
     * @return the assignee2Name
     */
    public String getAssignee2Name() {
        return assignee2Name;
    }

    /**
     * @param assignee2Name the assignee2Name to set
     */
    public void setAssignee2Name(String assignee2Name) {
        this.assignee2Name = assignee2Name;
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
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
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
     * @return the userRole
     */
    public int getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    /**
     * @return the employeeId
     */
    public long getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
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
     * @return the searchOperation
     */
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
     * @return the caseCategory
     */
    public String getCaseCategory() {
        return caseCategory;
    }

    /**
     * @param caseCategory the caseCategory to set
     */
    public void setCaseCategory(String caseCategory) {
        this.caseCategory = caseCategory;
    }

    /**
     * @return the assigneeId1
     */
    public String getAssigneeId1() {
        return assigneeId1;
    }

    /**
     * @param assigneeId1 the assigneeId1 to set
     */
    public void setAssigneeId1(String assigneeId1) {
        this.assigneeId1 = assigneeId1;
    }

    /**
     * @return the assignee
     */
    public int getAssignee() {
        return assignee;
    }

    /**
     * @param assignee the assignee to set
     */
    public void setAssignee(int assignee) {
        this.assignee = assignee;
    }

    /**
     * @return the customername
     */
    public String getCustomername() {
        return customername;
    }

    /**
     * @param customername the customername to set
     */
    public void setCustomername(String customername) {
        this.customername = customername;
    }

    /**
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return the branchDesc
     */
    public String getBranchDesc() {
        return branchDesc;
    }

    /**
     * @param branchDesc the branchDesc to set
     */
    public void setBranchDesc(String branchDesc) {
        this.branchDesc = branchDesc;
    }

    /**
     * @return the created
     */
    public String getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return the assignees
     */
    public String getAssignees() {
        return assignees;
    }

    /**
     * @param assignees the assignees to set
     */
    public void setAssignees(String assignees) {
        this.assignees = assignees;
    }

    /**
     * @return the casecreateduser
     */
    public String getCasecreateduser() {
        return casecreateduser;
    }

    /**
     * @param casecreateduser the casecreateduser to set
     */
    public void setCasecreateduser(String casecreateduser) {
        this.casecreateduser = casecreateduser;
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
     * @return the caseDate
     */
    public String getCaseDate() {
        return caseDate;
    }

    /**
     * @param caseDate the caseDate to set
     */
    public void setCaseDate(String caseDate) {
        this.caseDate = caseDate;
    }

    /**
     * @return the employeeID
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the hcasedate
     */
    public String getHcasedate() {
        return hcasedate;
    }

    /**
     * @param hcasedate the hcasedate to set
     */
    public void setHcasedate(String hcasedate) {
        this.hcasedate = hcasedate;
    }

    /**
     * @return the hstatusid
     */
    public String getHstatusid() {
        return hstatusid;
    }

    /**
     * @param hstatusid the hstatusid to set
     */
    public void setHstatusid(String hstatusid) {
        this.hstatusid = hstatusid;
    }

    /**
     * @return the hsattudDes
     */
    public String getHsattudDes() {
        return hsattudDes;
    }

    /**
     * @param hsattudDes the hsattudDes to set
     */
    public void setHsattudDes(String hsattudDes) {
        this.hsattudDes = hsattudDes;
    }

    /**
     * @return the hbranchid
     */
    public String getHbranchid() {
        return hbranchid;
    }

    /**
     * @param hbranchid the hbranchid to set
     */
    public void setHbranchid(String hbranchid) {
        this.hbranchid = hbranchid;
    }

    /**
     * @return the hbranchDes
     */
    public String getHbranchDes() {
        return hbranchDes;
    }

    /**
     * @param hbranchDes the hbranchDes to set
     */
    public void setHbranchDes(String hbranchDes) {
        this.hbranchDes = hbranchDes;
    }

    /**
     * @return the hbrmf
     */
    public String getHbrmf() {
        return hbrmf;
    }

    /**
     * @param hbrmf the hbrmf to set
     */
    public void setHbrmf(String hbrmf) {
        this.hbrmf = hbrmf;
    }

    /**
     * @return the hempname
     */
    public String getHempname() {
        return hempname;
    }

    /**
     * @param hempname the hempname to set
     */
    public void setHempname(String hempname) {
        this.hempname = hempname;
    }

    /**
     * @return the hcomment
     */
    public String getHcomment() {
        return hcomment;
    }

    /**
     * @param hcomment the hcomment to set
     */
    public void setHcomment(String hcomment) {
        this.hcomment = hcomment;
    }

    /**
     * @return the historyid
     */
    public String getHistoryid() {
        return historyid;
    }

    /**
     * @param historyid the historyid to set
     */
    public void setHistoryid(String historyid) {
        this.historyid = historyid;
    }

    /**
     * @return the hempid
     */
    public String getHempid() {
        return hempid;
    }

    /**
     * @param hempid the hempid to set
     */
    public void setHempid(String hempid) {
        this.hempid = hempid;
    }

    /**
     * @return the file_name
     */
    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the upload_file
     */
    public MultipartFile getUpload_file() {
        return upload_file;
    }

    /**
     * @param upload_file the upload_file to set
     */
    public void setUpload_file(MultipartFile upload_file) {
        this.upload_file = upload_file;
    }

    /**
     * @return the file_location
     */
    public String getFile_location() {
        return file_location;
    }

    /**
     * @param file_location the file_location to set
     */
    public void setFile_location(String file_location) {
        this.file_location = file_location;
    }

    /**
     * @return the autoassign
     */
    public boolean isAutoassign() {
        return autoassign;
    }

    /**
     * @param autoassign the autoassign to set
     */
    public void setAutoassign(boolean autoassign) {
        this.autoassign = autoassign;
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
     * @return the customervisitId
     */
    public String getCustomervisitId() {
        return customervisitId;
    }

    /**
     * @param customervisitId the customervisitId to set
     */
    public void setCustomervisitId(String customervisitId) {
        this.customervisitId = customervisitId;
    }

    /**
     * @return the createdDateTimeString
     */
    public String getCreatedDateTimeString() {
        return createdDateTimeString;
    }

    /**
     * @param createdDateTimeString the createdDateTimeString to set
     */
    public void setCreatedDateTimeString(String createdDateTimeString) {
        this.createdDateTimeString = createdDateTimeString;
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
     * @return the files
     */
    public List<MultipartFile> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    /**
     * @return the fileid
     */
    public String getFileid() {
        return fileid;
    }

    /**
     * @param fileid the fileid to set
     */
    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    /**
     * @return the caseDate
     *
     *
     * /
     **
     * @return the casedate
     */
}

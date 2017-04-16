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
 * @author Administrator
 */
public class CaseHistory {

    private int caseHistoryId;
    private String caseId;
    private String affectedDate;
    private String statusId;
    private String statusDes;
    private String resolutionDescription;
    private String assigneeId1;
    private String assigneeId2;
    private String assigneeName;
    private Date createdDateTime;
    private Date lastUpdaedDateTime;
    private String branchcode;
    private String createdUser;
    private int employeeId;
    /*case Histroy update*/
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
    private String checkbox;
    private String employeeID;
    private String oldemployeeID;
    private String reassignreason;
    private MultipartFile upload_file;
    private String filename;
    private String file_location;
    private String download_token_value_id;
    private List<MultipartFile> files;
    private String fileid;

    /**
     * @return the caseHistoryId
     */
    public int getCaseHistoryId() {
        return caseHistoryId;
    }

    /**
     * @param caseHistoryId the caseHistoryId to set
     */
    public void setCaseHistoryId(int caseHistoryId) {
        this.caseHistoryId = caseHistoryId;
    }

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
     * @return the affectedDate
     */
    public String getAffectedDate() {
        return affectedDate;
    }

    /**
     * @param affectedDate the affectedDate to set
     */
    public void setAffectedDate(String affectedDate) {
        this.affectedDate = affectedDate;
    }

    /**
     * @return the statusId
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
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
     * @return the resolutionDescription
     */
    public String getResolutionDescription() {
        return resolutionDescription;
    }

    /**
     * @param resolutionDescription the resolutionDescription to set
     */
    public void setResolutionDescription(String resolutionDescription) {
        this.resolutionDescription = resolutionDescription;
    }

    /**
     * @return the assigneeId
     */
    /**
     * @return the assigneeName
     */
    public String getAssigneeName() {
        return assigneeName;
    }

    /**
     * @param assigneeName the assigneeName to set
     */
    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
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
     * @return the lastUpdaedDateTime
     */
    public Date getLastUpdaedDateTime() {
        return lastUpdaedDateTime;
    }

    /**
     * @param lastUpdaedDateTime the lastUpdaedDateTime to set
     */
    public void setLastUpdaedDateTime(Date lastUpdaedDateTime) {
        this.lastUpdaedDateTime = lastUpdaedDateTime;
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
     * @return the createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * @param createdUser the createdUser to set
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the hcasedate
     */
    public String getHcasedate() {
        return hcasedate;
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
     * @param hcasedate the hcasedate to set
     */
    public void setHcasedate(String hcasedate) {
        this.hcasedate = hcasedate;
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
     * @return the checkbox
     */
    public String getCheckbox() {
        return checkbox;
    }

    /**
     * @param checkbox the checkbox to set
     */
    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
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
     * @return the oldemployeeID
     */
    public String getOldemployeeID() {
        return oldemployeeID;
    }

    /**
     * @param oldemployeeID the oldemployeeID to set
     */
    public void setOldemployeeID(String oldemployeeID) {
        this.oldemployeeID = oldemployeeID;
    }

    /**
     * @return the reassignreason
     */
    public String getReassignreason() {
        return reassignreason;
    }

    /**
     * @param reassignreason the reassignreason to set
     */
    public void setReassignreason(String reassignreason) {
        this.reassignreason = reassignreason;
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

}

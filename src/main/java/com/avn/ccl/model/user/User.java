/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.user;

/**
 * @Author : Roshen Dilshan
 * @Document : User
 * @Created on : Sep 15, 2015, 9:35:45 AM
 */
public class User {

    private String username;
    private String user_role;
    private String email;
    private String status;
    private String created_time;
    private String lastupdated_time;
    private String password;
    private long empid;
    private String branchid;
    private String firstname;
    private String lastname;
    private String contactmobile;
    private String nic;
    private String employeecategory;
    private String branchsb;
    private String branchmb_not_assign;
    private String branchmb_assign;
    private String multibranch;
    private String brorrm;
    private boolean notify;
    private int invalid_user_attepmts;

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
     * @return the user_role
     */
    public String getUser_role() {
        return user_role;
    }

    /**
     * @param user_role the user_role to set
     */
    public void setUser_role(String user_role) {
        this.user_role = user_role;
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
     * @return the created_time
     */
    public String getCreated_time() {
        return created_time;
    }

    /**
     * @param created_time the created_time to set
     */
    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    /**
     * @return the lastupdated_time
     */
    public String getLastupdated_time() {
        return lastupdated_time;
    }

    /**
     * @param lastupdated_time the lastupdated_time to set
     */
    public void setLastupdated_time(String lastupdated_time) {
        this.lastupdated_time = lastupdated_time;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the empid
     */
    public long getEmpid() {
        return empid;
    }

    /**
     * @param empid the empid to set
     */
    public void setEmpid(long empid) {
        this.empid = empid;
    }

    /**
     * @return the branchid
     */
    public String getBranchid() {
        return branchid;
    }

    /**
     * @param branchid the branchid to set
     */
    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the contactmobile
     */
    public String getContactmobile() {
        return contactmobile;
    }

    /**
     * @param contactmobile the contactmobile to set
     */
    public void setContactmobile(String contactmobile) {
        this.contactmobile = contactmobile;
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
     * @return the employeecategory
     */
    public String getEmployeecategory() {
        return employeecategory;
    }

    /**
     * @param employeecategory the employeecategory to set
     */
    public void setEmployeecategory(String employeecategory) {
        this.employeecategory = employeecategory;
    }

    /**
     * @return the branchsb
     */
    public String getBranchsb() {
        return branchsb;
    }

    /**
     * @param branchsb the branchsb to set
     */
    public void setBranchsb(String branchsb) {
        this.branchsb = branchsb;
    }

    /**
     * @return the branchmb_not_assign
     */
    public String getBranchmb_not_assign() {
        return branchmb_not_assign;
    }

    /**
     * @param branchmb_not_assign the branchmb_not_assign to set
     */
    public void setBranchmb_not_assign(String branchmb_not_assign) {
        this.branchmb_not_assign = branchmb_not_assign;
    }

    /**
     * @return the branchmb_assign
     */
    public String getBranchmb_assign() {
        return branchmb_assign;
    }

    /**
     * @param branchmb_assign the branchmb_assign to set
     */
    public void setBranchmb_assign(String branchmb_assign) {
        this.branchmb_assign = branchmb_assign;
    }

    /**
     * @return the multibranch
     */
    public String getMultibranch() {
        return multibranch;
    }

    /**
     * @param multibranch the multibranch to set
     */
    public void setMultibranch(String multibranch) {
        this.multibranch = multibranch;
    }

    /**
     * @return the brorrm
     */
    public String getBrorrm() {
        return brorrm;
    }

    /**
     * @param brorrm the brorrm to set
     */
    public void setBrorrm(String brorrm) {
        this.brorrm = brorrm;
    }

    /**
     * @return the notify
     */
    public boolean isNotify() {
        return notify;
    }

    /**
     * @param notify the notify to set
     */
    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    /**
     * @return the invalid_user_attepmts
     */
    public int getInvalid_user_attepmts() {
        return invalid_user_attepmts;
    }

    /**
     * @param invalid_user_attepmts the invalid_user_attepmts to set
     */
    public void setInvalid_user_attepmts(int invalid_user_attepmts) {
        this.invalid_user_attepmts = invalid_user_attepmts;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.bean.webserviceclient;

import java.math.BigDecimal;
import org.json.JSONObject;

/**
 * @Author : Roshen Dilshan
 * @Document : StakeHolderFDBean
 * @Created on : Jan 18, 2016, 3:25:17 PM
 */
public class StakeHolderFDBean {

    private String fd_id_view;
    private String fd_no;
    private BigDecimal depamt;
    private String fd_date;
    private String fd_prd;
    private String fd_inrate;
    private String center;
    private String fd_type;
    private BigDecimal fd_monint;
    private String fd_next;

    /**
     * @return the fd_id_view
     */
    public String getFd_id_view() {
        return fd_id_view;
    }

    /**
     * @param fd_id_view the fd_id_view to set
     */
    public void setFd_id_view(String fd_id_view) {
        this.fd_id_view = fd_id_view;
    }

    /**
     * @return the fd_no
     */
    public String getFd_no() {
        return fd_no;
    }

    /**
     * @param fd_no the fd_no to set
     */
    public void setFd_no(String fd_no) {
        this.fd_no = fd_no;
    }

    /**
     * @return the depamt
     */
    public BigDecimal getDepamt() {
        return depamt;
    }

    /**
     * @param depamt the depamt to set
     */
    public void setDepamt(BigDecimal depamt) {
        this.depamt = depamt;
    }

    /**
     * @return the fd_date
     */
    public String getFd_date() {
        return fd_date;
    }

    /**
     * @param fd_date the fd_date to set
     */
    public void setFd_date(String fd_date) {
        this.fd_date = fd_date;
    }

    /**
     * @return the fd_prd
     */
    public String getFd_prd() {
        return fd_prd;
    }

    /**
     * @param fd_prd the fd_prd to set
     */
    public void setFd_prd(String fd_prd) {
        this.fd_prd = fd_prd;
    }

    /**
     * @return the fd_inrate
     */
    public String getFd_inrate() {
        return fd_inrate;
    }

    /**
     * @param fd_inrate the fd_inrate to set
     */
    public void setFd_inrate(String fd_inrate) {
        this.fd_inrate = fd_inrate;
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
     * @return the fd_monint
     */
    public BigDecimal getFd_monint() {
        return fd_monint;
    }

    /**
     * @param fd_monint the fd_monint to set
     */
    public void setFd_monint(BigDecimal fd_monint) {
        this.fd_monint = fd_monint;
    }

    /**
     * @return the fd_next
     */
    public String getFd_next() {
        return fd_next;
    }

    /**
     * @param fd_next the fd_next to set
     */
    public void setFd_next(String fd_next) {
        this.fd_next = fd_next;
    }

    @Override
    public String toString() {
        String val = "{"
                + "fd_id_view:" + "\"" + fd_id_view + "\""
                + ", fd_no:" + "\"" + fd_no + "\""
                + ", depamt:" + "\"" + depamt + "\""
                + ", fd_date:" + "\"" + fd_date + "\""
                + ", fd_prd:" + "\"" + fd_prd + "\""
                + ", fd_inrate:" + "\"" + fd_inrate + "\""
                + ", center:" + "\"" + center + "\""
                + ", fd_type:" + "\"" + fd_type + "\""
                + ", fd_monint:" + "\"" + fd_monint + "\""
                + ", fd_next:" + "\"" + fd_next + "\""
                + '}';
        val = new JSONObject(val).toString();
        return val;
    }

}

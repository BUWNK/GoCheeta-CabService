/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.schedulertask;

import java.util.Date;

/**
 * @Author : Roshen Dilshan
 * @Document : CaseInfo
 * @Created on : Jan 27, 2016, 8:44:59 AM
 */
public class CaseInfo {
    
    private String caseid;
    private Date createdtime;
    private String brmf;
    private int casetypeid;

    /**
     * @return the caseid
     */
    public String getCaseid() {
        return caseid;
    }

    /**
     * @param caseid the caseid to set
     */
    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    /**
     * @return the createdtime
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * @param createdtime the createdtime to set
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * @return the brmf
     */
    public String getBrmf() {
        return brmf;
    }

    /**
     * @param brmf the brmf to set
     */
    public void setBrmf(String brmf) {
        this.brmf = brmf;
    }

    /**
     * @return the casetypeid
     */
    public int getCasetypeid() {
        return casetypeid;
    }

    /**
     * @param casetypeid the casetypeid to set
     */
    public void setCasetypeid(int casetypeid) {
        this.casetypeid = casetypeid;
    }

}

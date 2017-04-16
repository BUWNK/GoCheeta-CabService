/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.dependentrype;

/**
 * @Author : Roshen Dilshan
 * @Document : CaseUrl
 * @Created on : Aug 11, 2016, 8:34:08 AM
 */
public class CaseUrl {
    
    private long docid;
    private long caseid;
    private String filepath;
    private String filename;

    /**
     * @return the docid
     */
    public long getDocid() {
        return docid;
    }

    /**
     * @param docid the docid to set
     */
    public void setDocid(long docid) {
        this.docid = docid;
    }

    /**
     * @return the caseid
     */
    public long getCaseid() {
        return caseid;
    }

    /**
     * @param caseid the caseid to set
     */
    public void setCaseid(long caseid) {
        this.caseid = caseid;
    }

    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
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

}

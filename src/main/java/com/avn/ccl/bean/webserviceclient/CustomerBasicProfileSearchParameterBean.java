/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.bean.webserviceclient;

/**
 * @Author : Roshen Dilshan
 * @Document : CustomerBasicProfileSearchParameterBean
 * @Date : Jun 27, 2015 9:30:50 AM
 */
public class CustomerBasicProfileSearchParameterBean {
    
    private int searchType;
    private String searchReferance;
    private String additionalCriteria;

    /**
     * @return the searchType
     */
    public int getSearchType() {
        return searchType;
    }

    /**
     * @param searchType the searchType to set
     */
    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    /**
     * @return the searchReferance
     */
    public String getSearchReferance() {
        return searchReferance;
    }

    /**
     * @param searchReferance the searchReferance to set
     */
    public void setSearchReferance(String searchReferance) {
        this.searchReferance = searchReferance;
    }

    /**
     * @return the additionalCriteria
     */
    public String getAdditionalCriteria() {
        return additionalCriteria;
    }

    /**
     * @param additionalCriteria the additionalCriteria to set
     */
    public void setAdditionalCriteria(String additionalCriteria) {
        this.additionalCriteria = additionalCriteria;
    }

}

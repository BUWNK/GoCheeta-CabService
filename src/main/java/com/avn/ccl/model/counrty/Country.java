/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.counrty;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : Country
 * @Created on : Aug 19, 2015, 6:55:38 PM
 */

public class Country {
    
    private String counrtyid;
    private String countryname;

    /**
     * @return the counrtyid
     */
    public String getCounrtyid() {
        return counrtyid;
    }

    /**
     * @param counrtyid the counrtyid to set
     */
    public void setCounrtyid(String counrtyid) {
        this.counrtyid = counrtyid;
    }

    /**
     * @return the countryname
     */
    public String getCountryname() {
        return countryname;
    }

    /**
     * @param countryname the countryname to set
     */
    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

}

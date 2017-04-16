/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.callcenter;

/**
 *
 * @author Lahiru
 */
public class CallSearchParms {

    private String telephone;
    private String nameInFull;
    private String input;
    private String searchoption;

    public String getSearchoption() {
        return searchoption;
    }

    public void setSearchoption(String searchoption) {
        this.searchoption = searchoption;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
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

}

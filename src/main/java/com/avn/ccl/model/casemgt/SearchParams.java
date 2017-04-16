/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.casemgt;

/**
 *
 * @author maheshl
 */
public class SearchParams {
    private String nic;
    private int caseId;
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

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}

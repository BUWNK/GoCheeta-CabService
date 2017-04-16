/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.calldirection;

/**
 *
 * @author Lahiru
 */
public class CallDirection {
    
    private int callDirectionId;
    private String description;

    /**
     * @return the callDirectionId
     */
    public int getCallDirectionId() {
        return callDirectionId;
    }

    /**
     * @param callDirectionId the callDirectionId to set
     */
    public void setCallDirectionId(int callDirectionId) {
        this.callDirectionId = callDirectionId;
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
    
}

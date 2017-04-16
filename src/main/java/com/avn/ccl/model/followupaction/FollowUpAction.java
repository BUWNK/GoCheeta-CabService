/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.followupaction;

/**
 *
 * @author Lahiru
 */
public class FollowUpAction {
    
    private int followUpActionId;
    private String description;

    /**
     * @return the followUpActionId
     */
    public int getFollowUpActionId() {
        return followUpActionId;
    }

    /**
     * @param followUpActionId the followUpActionId to set
     */
    public void setFollowUpActionId(int followUpActionId) {
        this.followUpActionId = followUpActionId;
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

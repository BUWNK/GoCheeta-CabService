/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.userrolehiarachy;

/**
 *
 * @author user
 */
public class UserRoleHiarachy {
    private int userAccessRoleId;
    private int immediateSeniorId;

    /**
     * @return the userAccessRoleId
     */
    public int getUserAccessRoleId() {
        return userAccessRoleId;
    }

    /**
     * @param userAccessRoleId the userAccessRoleId to set
     */
    public void setUserAccessRoleId(int userAccessRoleId) {
        this.userAccessRoleId = userAccessRoleId;
    }

    /**
     * @return the immediateSeniorId
     */
    public int getImmediateSeniorId() {
        return immediateSeniorId;
    }

    /**
     * @param immediateSeniorId the immediateSeniorId to set
     */
    public void setImmediateSeniorId(int immediateSeniorId) {
        this.immediateSeniorId = immediateSeniorId;
    }
    
}

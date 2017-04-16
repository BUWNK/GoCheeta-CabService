/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.customerhobbiesinterests;

/**
 * @Author : Roshen Dilshan
 * @Document : HobbiesAndInterests
 * @Created on : Jan 12, 2016, 2:51:56 PM
 */
public class CustomerHobbiesInterests {
    
    private String hobby_id;
    private String account_id;
    private String hobby_description;
    private String hobby_comment;

    /**
     * @return the hobby_id
     */
    public String getHobby_id() {
        return hobby_id;
    }

    /**
     * @param hobby_id the hobby_id to set
     */
    public void setHobby_id(String hobby_id) {
        this.hobby_id = hobby_id;
    }

    /**
     * @return the account_id
     */
    public String getAccount_id() {
        return account_id;
    }

    /**
     * @param account_id the account_id to set
     */
    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    /**
     * @return the hobby_description
     */
    public String getHobby_description() {
        return hobby_description;
    }

    /**
     * @param hobby_description the hobby_description to set
     */
    public void setHobby_description(String hobby_description) {
        this.hobby_description = hobby_description;
    }

    /**
     * @return the hobby_comment
     */
    public String getHobby_comment() {
        return hobby_comment;
    }

    /**
     * @param hobby_comment the hobby_comment to set
     */
    public void setHobby_comment(String hobby_comment) {
        this.hobby_comment = hobby_comment;
    }
}

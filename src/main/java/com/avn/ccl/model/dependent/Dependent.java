/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.dependent;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : Dependent
 * @Created on : Aug 6, 2015, 10:19:45 AM
 */
public class Dependent {

    private String dependent_id;
    private String account_id;
    private String dependent_relationship;
    private String dependent_relationship_description;
    private String dependent_date_of_birth;
    private String dependent_name_in_full;

    /**
     * @return the dependent_id
     */
    public String getDependent_id() {
        return dependent_id;
    }

    /**
     * @param dependent_id the dependent_id to set
     */
    public void setDependent_id(String dependent_id) {
        this.dependent_id = dependent_id;
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
     * @return the dependent_relationship
     */
    public String getDependent_relationship() {
        return dependent_relationship;
    }

    /**
     * @param dependent_relationship the dependent_relationship to set
     */
    public void setDependent_relationship(String dependent_relationship) {
        this.dependent_relationship = dependent_relationship;
    }

    /**
     * @return the dependent_relationship_description
     */
    public String getDependent_relationship_description() {
        return dependent_relationship_description;
    }

    /**
     * @param dependent_relationship_description the dependent_relationship_description to set
     */
    public void setDependent_relationship_description(String dependent_relationship_description) {
        this.dependent_relationship_description = dependent_relationship_description;
    }

    /**
     * @return the dependent_date_of_birth
     */
    public String getDependent_date_of_birth() {
        return dependent_date_of_birth;
    }

    /**
     * @param dependent_date_of_birth the dependent_date_of_birth to set
     */
    public void setDependent_date_of_birth(String dependent_date_of_birth) {
        this.dependent_date_of_birth = dependent_date_of_birth;
    }

    /**
     * @return the dependent_name_in_full
     */
    public String getDependent_name_in_full() {
        return dependent_name_in_full;
    }

    /**
     * @param dependent_name_in_full the dependent_name_in_full to set
     */
    public void setDependent_name_in_full(String dependent_name_in_full) {
        this.dependent_name_in_full = dependent_name_in_full;
    }

}

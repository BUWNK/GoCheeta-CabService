/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.secretquestion;

import java.util.Map;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : SecretQuestion
 * @Created on : Aug 6, 2015, 12:31:27 PM
 */
public interface SecretQuestionDAO {

    public Map<String, String> getSecretQuestionDropdownList() throws Exception;
    
}

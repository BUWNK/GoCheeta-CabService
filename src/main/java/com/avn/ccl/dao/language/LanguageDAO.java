/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.language;

import com.avn.ccl.model.language.Language;
import java.util.Map;

/**
 *
 * @author Lahiru
 */
public interface LanguageDAO {
    
    public Map<String, String> getLanguagesList() throws Exception;
    
    public Language getLanguageById(String id) throws Exception;
    
}

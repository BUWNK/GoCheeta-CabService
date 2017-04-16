/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.dao.hobbiesandinterests;

import com.avn.ccl.model.hobbiesandinterests.HobbiesAndInterest;
import java.util.Map;

/**
 * @Author : Roshen Dilshan
 * @Document : HobbyDAO
 * @Created on : Jan 12, 2016, 2:41:21 PM
 */
public interface HobbiesAndInterestsDAO {
    
    public Map<String, String> getHobbyDropdownList(int status) throws Exception;
    
    public HobbiesAndInterest getHobbiesAndInterestById(String hobby_id) throws Exception;

}

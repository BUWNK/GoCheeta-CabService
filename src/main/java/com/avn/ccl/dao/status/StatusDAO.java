/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.status;

import com.avn.ccl.model.status.Status;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hirantha
 */
public interface StatusDAO {

    public List<Status> getStatusList(int statuscategory) throws Exception;

    public Map<String, String> getStatusDropdownList(int statuscategory) throws Exception;

    public Status getStatusById(String id) throws Exception;

    public Map<String, String> getStatusCodeDropdownList(int statuscategory) throws Exception;

    public Status getStatusByCategoryAndCode(int statusCategory, String statusCode) throws Exception;

    public List<Status> getStatusListByCategory(int statusCategory) throws Exception;

    public Map<String, String> getStatusDropdownListWithIdsNotIn(int statuscategory, List<Integer> notinids) throws Exception;
    
    public Map<String, String> getStatusListWithIdsNotIn(int statuscategory, List<Integer> notinids) throws Exception;
}

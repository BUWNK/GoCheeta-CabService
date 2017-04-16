/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.dashboard;

import java.util.Date;
import org.json.JSONArray;

/**
 *
 * @author ISURU
 */
public interface DashboardDAO {

    public JSONArray getCaseCount(String username, String column) throws Exception;

    public JSONArray getCallCount(String username) throws Exception;

    public JSONArray getDepartmentCount(String username, String column, Date date) throws Exception;

    public JSONArray getProduntCount(String username, String column, Date date) throws Exception;

    public JSONArray getProduntCountOrganizationwise(Date date) throws Exception;

    public JSONArray getDepartmentCountbyOrganizationwise(Date date) throws Exception;

//       public JSONArray getDepartmentCountbyOrganizationwise() throws Exception;
    public JSONArray getDepartmentList() throws Exception;

}

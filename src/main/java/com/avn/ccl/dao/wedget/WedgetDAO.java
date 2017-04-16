/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.wedget;

import com.avn.ccl.model.wedget.Wedget;
import java.util.List;
import java.util.Map;

/**
 * @Author : ISURU
 * @Document : WedgetDAO
 * @Created on : May 16, 2016, 11:16:00 AM
 */
public interface WedgetDAO {

    public long insertWedget(Wedget wedget, String username) throws Exception;

    public int getTableDataCount(Wedget parameters) throws Exception;

    public List<Wedget> getTableData(Wedget parameters, int minCount, int start) throws Exception;

    public Wedget getWedgetByWedgetId(String wedgetid) throws Exception;

    public void updateWedget(Wedget wedget, String username) throws Exception;

    public Map<String, String> getWedgetDropdownList() throws Exception;

    public List<Wedget> getWedgetDropdownListNew(String usereoleid) throws Exception;

}

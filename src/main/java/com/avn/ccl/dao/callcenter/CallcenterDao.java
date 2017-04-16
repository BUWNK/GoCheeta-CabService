/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.callcenter;

import com.avn.ccl.model.callcenter.CallCenter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lahiru
 */
public interface CallcenterDao {

    public long insertCallLog(CallCenter callLogForm, HttpSession session) throws Exception;

//    public List<CallCenter> getCallLog() throws Exception;
    public List<CallCenter> getCallDetail(String Uid) throws Exception;

    public int getTableDataCount(String username) throws Exception;

    public List<CallCenter> getTableData(String username, int minCount, int start, String sort) throws Exception;

    public CallCenter getCallByCallLogId(int CallLogID) throws Exception;

    public List<CallCenter> getCall(String filter, String input) throws Exception;

    public int getTableDataCount(CallCenter parameters) throws Exception;

    public List<CallCenter> getTableData(CallCenter parameters, int minCount, int start) throws Exception;

    public void Callupdate(CallCenter callLogUpdateForm, HttpSession session) throws Exception;

    public String getlanguage(String stakeholderreferenceno) throws SQLException;

    public String getCallID(HttpSession session) throws Exception;

}

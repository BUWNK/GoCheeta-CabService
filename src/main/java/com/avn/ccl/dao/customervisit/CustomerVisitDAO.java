/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.customervisit;

import com.avn.ccl.model.customervisit.CustomerVisit;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 * @Author : Isuru
 * @Document : CustomerVisitDao
 * @Created on : Jan 6, 2016, 8:42:41 AM
 */
public interface CustomerVisitDAO {

    public long insertCustomerVisit(CustomerVisit customervist, HttpSession session) throws Exception;

    public List<CustomerVisit> getCustomerVisitLog() throws Exception;

    public CustomerVisit getCustomerVisitByID(int customervisitID) throws Exception;

    public int getTableDataCount(CustomerVisit parameters) throws Exception;

     public void customerVisitUpdate(CustomerVisit customervisitdata, HttpSession session) throws Exception;

    public List<CustomerVisit> getTableData(CustomerVisit parameters, int minCount, int start) throws Exception;

}

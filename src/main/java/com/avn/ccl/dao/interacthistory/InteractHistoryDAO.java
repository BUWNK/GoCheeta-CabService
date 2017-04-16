/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.interacthistory;

import com.avn.ccl.model.interacthistory.Interacthistory;
import java.util.List;

/**
 *
 * @author ISURU
 */
public interface InteractHistoryDAO {
     public List<Interacthistory> getTableData(Interacthistory parameters, int minCount, int start) throws Exception;
      public int getTableDataCount(Interacthistory parameters) throws Exception ;
    
}

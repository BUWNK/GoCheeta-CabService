/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.service.target;

import com.avn.ccl.dao.target.TargetDAO;
import com.avn.ccl.model.lead.BranchPerformance;
import com.avn.ccl.model.target.Target;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : Roshen Dilshan
 * @Document : TargetServiceImpl
 * @Created on : Aug 19, 2016, 11:52:52 AM
 */
@Service("targetService")
public class TargetServiceImpl implements TargetService {
    
    @Autowired
    TargetDAO targetDAO;

    @Override
    @Transactional
    public Target getTargetByID(long targetid) throws SQLException {
        return targetDAO.getTargetByID(targetid);
    }
    
    
}

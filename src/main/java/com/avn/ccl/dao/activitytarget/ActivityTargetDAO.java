/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.activitytarget;

import com.avn.ccl.model.activitytarget.ActivityTarget;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author : Roshen Dilshan
 * @Document : ActivityTarget
 * @Created on : Jul 10, 2016, 9:12:03 AM
 */
public interface ActivityTargetDAO {

    public List<ActivityTarget> getActivityTargetListByTargetId(long targetId) throws SQLException;

}

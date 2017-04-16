/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.targetsetting;

import com.avn.ccl.model.target.TargetSetting;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/**
 * @Author : ISURU
 * @Document : TargetSettingDAO
 * @Created on : Jul 1, 2016, 9:09:56 AM
 */
public interface TargetSettingDAO {

    public long insertTarget(TargetSetting target, String username) throws Exception;

    public int getTableDataCount(TargetSetting parameters) throws Exception;

    public List<TargetSetting> getTableData(TargetSetting parameters, int minCount, int start) throws Exception;

    public TargetSetting getTargetByTargetId(String targetId) throws Exception;

    public void updateTarget(TargetSetting target, String username) throws Exception;

    public List<TargetSetting> getTargetSearchDroupDown(String targetdes) throws Exception;

    public void insertNotAssignRegions(TargetSetting target, String username) throws Exception;

    public JSONArray insertRegionTarget(TargetSetting target, String username) throws Exception;

    public long insertBranchTarget(TargetSetting target, String username, JSONArray json) throws Exception;

    public String getTargetIByDescription(String targetdes) throws Exception;

    public String getOrganizationTargetByID(String targetid) throws Exception;

    public JSONArray getRegionaletails(String targetid) throws Exception;

    public Map<String, String> getTargetDropdownList() throws Exception;

    public List<TargetSetting> getTargetList() throws Exception;

    public JSONArray getNotAssignBranch(String targetid) throws Exception;

    public void insertNotAssignBranchTarget(TargetSetting target, String username, JSONArray json, JSONArray array) throws Exception;

    public JSONArray getRegionalTargetIdListByTargetId(String targetid) throws Exception;

    public JSONArray getRegionOragnizationTarget(String targetid) throws Exception;

    public JSONArray getBrachTargetList(String targetid) throws Exception;

    public Map<String, String> getNotAssignRegionByTragetId(String targetid) throws Exception;

    public Map<String, String> getAssignRegionByTragetId(String targetid) throws Exception;

    public Map<String, String> getNotAssignBranchByTargetId(String targetid, String regionid) throws Exception;

    public Map<String, String> getAssignBranchByTargetId(String targetid, String regionid) throws Exception;

    public int getMinRegionId(String targetid) throws Exception;

    public void updateTarget(TargetSetting target, JSONArray arrayregiontargetid, String username) throws Exception;

}

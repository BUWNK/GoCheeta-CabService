/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.target;

import java.util.Map;

/**
 * @Author : ISURU
 * @Document : Target
 * @Created on : May 6, 2016, 4:11:31 PM
 */
public class TargetSetting {

    private String targetid;
    private String tragetdes;
    private String productid;
    private String productdes;
    private String revenue;
    private String nuberofcontact;
    private String targergroupid;
    private String tragetgroupdes;
    private String targetperiodid;
    private String targetperioddes;
    private String createdatetime;
    private String lastupdatedatetime;
    private String createduser;
    private String targetstartdate;
    private String targetenddate;
    private String regiontarget;
    private String activitytypeid;
    private String activitytypedes;
    private String targettypeid;
    private String targettypedes;
    private String targetrevenue;
//    -----------------------------
    private String activitytargettbl;

//    -----------------------------
    private String multiregionalarray;
    private String multiregional;
    private String regionalid;
    private String assignreagion;
    private String targetnotassignregion;

    private String multibrancharray;
    private String multibranch;
    private String branchid;
    private String branchdes;
    private String count;
    private String targetnotassignbranch;

    private String multiindividualarray;
    private String empid;
    private String empname;

    private String regionaltargetlist;
    private String brachtargetlist;
    private String totalvalue;
    private String oldtaget;

    private String tab2totalrevenue;
    private String tab2revenue;
    private String tab2totalcount;
    private String tab2count;

    private String tab3totalrevenue;
    private String tab3revenue;
    private String tab3totalcount;
    private String tab3count;

    private String tab4totalrevenue;
    private String tab4revenue;
    private String tab4totalcount;
    private String tab4count;

    private String tab5totalrevenue;
    private String tab5revenue;
    private String tab5totalcount;
    private String tab5count;

    private String searchoption;
    private String input;

    private boolean save_btn;
    private boolean edit_btn;
    private boolean search_btn;
    private boolean view_btn;

    private String targetactivityid;
    private String targetactivitydes;
    private String datatabledata;
    private String regionactivity;

    private String regionaltargetid;
    private String regionid;
    private String regiondes;
    private String regionalactivitytargetid;
    private String regionalactivitytargetdes;

    private String brnachtargetid;
    private String brnachtargetdes;
    private String branchgrouptargetid;
    private String branchgrouptargetdes;

    private String branchgroupid;
    private String branchgroupdes;

    private String branchgrouprevenuetargetid;
    private String branchgrouprevenuetargetdes;

    private String branchgroupmembertargetid;
    private String branchgroupmembertargetdes;

    private String branchactivitytargetid;
    private String branchactivitytargetdes;

    private String branchgroupactivitytargeid;
    private String branchgroupactivitytargetdes;

    private String branchgroumemberactivityid;
    private String branchgroumemberactivitydes;

    private String branchgroupmemberid;
    private String branchgroupmemberdes;
    private String memberbranchactivitytargetid;
    private String memberregionalactivitytargetid;

    private String callcount;  
    private String proposalscount;
    private String visitspresentationscount;
    private String vpresentationsactivityid;
    private String callactivityid;
    private String proposalsactivityid;

    /**
     * @return the targetid
     */
    public String getTargetid() {
        return targetid;
    }

    /**
     * @param targetid the targetid to set
     */
    public void setTargetid(String targetid) {
        this.targetid = targetid;
    }

    /**
     * @return the tragetdes
     */
    public String getTragetdes() {
        return tragetdes;
    }

    /**
     * @param tragetdes the tragetdes to set
     */
    public void setTragetdes(String tragetdes) {
        this.tragetdes = tragetdes;
    }

    /**
     * @return the productid
     */
    public String getProductid() {
        return productid;
    }

    /**
     * @param productid the productid to set
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * @return the revenue
     */
    public String getRevenue() {
        return revenue;
    }

    /**
     * @param revenue the revenue to set
     */
    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    /**
     * @return the nuberofcontact
     */
    public String getNuberofcontact() {
        return nuberofcontact;
    }

    /**
     * @param nuberofcontact the nuberofcontact to set
     */
    public void setNuberofcontact(String nuberofcontact) {
        this.nuberofcontact = nuberofcontact;
    }

    /**
     * @return the targergroupid
     */
    public String getTargergroupid() {
        return targergroupid;
    }

    /**
     * @param targergroupid the targergroupid to set
     */
    public void setTargergroupid(String targergroupid) {
        this.targergroupid = targergroupid;
    }

    /**
     * @return the tragetgroupdes
     */
    public String getTragetgroupdes() {
        return tragetgroupdes;
    }

    /**
     * @param tragetgroupdes the tragetgroupdes to set
     */
    public void setTragetgroupdes(String tragetgroupdes) {
        this.tragetgroupdes = tragetgroupdes;
    }

    /**
     * @return the targetperiodid
     */
    public String getTargetperiodid() {
        return targetperiodid;
    }

    /**
     * @param targetperiodid the targetperiodid to set
     */
    public void setTargetperiodid(String targetperiodid) {
        this.targetperiodid = targetperiodid;
    }

    /**
     * @return the createdatetime
     */
    public String getCreatedatetime() {
        return createdatetime;
    }

    /**
     * @param createdatetime the createdatetime to set
     */
    public void setCreatedatetime(String createdatetime) {
        this.createdatetime = createdatetime;
    }

    /**
     * @return the lastupdatedatetime
     */
    public String getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    /**
     * @param lastupdatedatetime the lastupdatedatetime to set
     */
    public void setLastupdatedatetime(String lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    /**
     * @return the createduser
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * @param createduser the createduser to set
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * @return the save_btn
     */
    public boolean isSave_btn() {
        return save_btn;
    }

    /**
     * @param save_btn the save_btn to set
     */
    public void setSave_btn(boolean save_btn) {
        this.save_btn = save_btn;
    }

    /**
     * @return the edit_btn
     */
    public boolean isEdit_btn() {
        return edit_btn;
    }

    /**
     * @param edit_btn the edit_btn to set
     */
    public void setEdit_btn(boolean edit_btn) {
        this.edit_btn = edit_btn;
    }

    /**
     * @return the search_btn
     */
    public boolean isSearch_btn() {
        return search_btn;
    }

    /**
     * @param search_btn the search_btn to set
     */
    public void setSearch_btn(boolean search_btn) {
        this.search_btn = search_btn;
    }

    /**
     * @return the view_btn
     */
    public boolean isView_btn() {
        return view_btn;
    }

    /**
     * @param view_btn the view_btn to set
     */
    public void setView_btn(boolean view_btn) {
        this.view_btn = view_btn;
    }

    /**
     * @return the searchoption
     */
    public String getSearchoption() {
        return searchoption;
    }

    /**
     * @param searchoption the searchoption to set
     */
    public void setSearchoption(String searchoption) {
        this.searchoption = searchoption;
    }

    /**
     * @return the input
     */
    public String getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * @return the productdes
     */
    public String getProductdes() {
        return productdes;
    }

    /**
     * @param productdes the productdes to set
     */
    public void setProductdes(String productdes) {
        this.productdes = productdes;
    }

    /**
     * @return the targetperioddes
     */
    public String getTargetperioddes() {
        return targetperioddes;
    }

    /**
     * @param targetperioddes the targetperioddes to set
     */
    public void setTargetperioddes(String targetperioddes) {
        this.targetperioddes = targetperioddes;
    }

    /**
     * @return the targetstartdate
     */
    public String getTargetstartdate() {
        return targetstartdate;
    }

    /**
     * @param targetstartdate the targetstartdate to set
     */
    public void setTargetstartdate(String targetstartdate) {
        this.targetstartdate = targetstartdate;
    }

    /**
     * @return the targetenddate
     */
    public String getTargetenddate() {
        return targetenddate;
    }

    /**
     * @param targetenddate the targetenddate to set
     */
    public void setTargetenddate(String targetenddate) {
        this.targetenddate = targetenddate;
    }

    /**
     * @return the multiregionalarray
     */
    public String getMultiregionalarray() {
        return multiregionalarray;
    }

    /**
     * @param multiregionalarray the multiregionalarray to set
     */
    public void setMultiregionalarray(String multiregionalarray) {
        this.multiregionalarray = multiregionalarray;
    }

    /**
     * @return the multiregional
     */
    public String getMultiregional() {
        return multiregional;
    }

    /**
     * @param multiregional the multiregional to set
     */
    public void setMultiregional(String multiregional) {
        this.multiregional = multiregional;
    }

    /**
     * @return the regionalid
     */
    public String getRegionalid() {
        return regionalid;
    }

    /**
     * @param regionalid the regionalid to set
     */
    public void setRegionalid(String regionalid) {
        this.regionalid = regionalid;
    }

    /**
     * @return the multibrancharray
     */
    public String getMultibrancharray() {
        return multibrancharray;
    }

    /**
     * @param multibrancharray the multibrancharray to set
     */
    public void setMultibrancharray(String multibrancharray) {
        this.multibrancharray = multibrancharray;
    }

    /**
     * @return the multibranch
     */
    public String getMultibranch() {
        return multibranch;
    }

    /**
     * @param multibranch the multibranch to set
     */
    public void setMultibranch(String multibranch) {
        this.multibranch = multibranch;
    }

    /**
     * @return the branchid
     */
    public String getBranchid() {
        return branchid;
    }

    /**
     * @param branchid the branchid to set
     */
    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    /**
     * @return the multiindividualarray
     */
    public String getMultiindividualarray() {
        return multiindividualarray;
    }

    /**
     * @param multiindividualarray the multiindividualarray to set
     */
    public void setMultiindividualarray(String multiindividualarray) {
        this.multiindividualarray = multiindividualarray;
    }

    /**
     * @return the empid
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * @param empid the empid to set
     */
    public void setEmpid(String empid) {
        this.empid = empid;
    }

    /**
     * @return the assignreagion
     */
    public String getAssignreagion() {
        return assignreagion;
    }

    /**
     * @param assignreagion the assignreagion to set
     */
    public void setAssignreagion(String assignreagion) {
        this.assignreagion = assignreagion;
    }

    /**
     * @return the regiontarget
     */
    public String getRegiontarget() {
        return regiontarget;
    }

    /**
     * @param regiontarget the regiontarget to set
     */
    public void setRegiontarget(String regiontarget) {
        this.regiontarget = regiontarget;
    }

    /**
     * @return the targetnotassignregion
     */
    public String getTargetnotassignregion() {
        return targetnotassignregion;
    }

    /**
     * @param targetnotassignregion the targetnotassignregion to set
     */
    public void setTargetnotassignregion(String targetnotassignregion) {
        this.targetnotassignregion = targetnotassignregion;
    }

    /**
     * @return the targetnotassignbranch
     */
    public String getTargetnotassignbranch() {
        return targetnotassignbranch;
    }

    /**
     * @param targetnotassignbranch the targetnotassignbranch to set
     */
    public void setTargetnotassignbranch(String targetnotassignbranch) {
        this.targetnotassignbranch = targetnotassignbranch;
    }

    /**
     * @return the regionaltargetlist
     */
    public String getRegionaltargetlist() {
        return regionaltargetlist;
    }

    /**
     * @param regionaltargetlist the regionaltargetlist to set
     */
    public void setRegionaltargetlist(String regionaltargetlist) {
        this.regionaltargetlist = regionaltargetlist;
    }

    /**
     * @return the brachtargetlist
     */
    public String getBrachtargetlist() {
        return brachtargetlist;
    }

    /**
     * @param brachtargetlist the brachtargetlist to set
     */
    public void setBrachtargetlist(String brachtargetlist) {
        this.brachtargetlist = brachtargetlist;
    }

    /**
     * @return the totalvalue
     */
    public String getTotalvalue() {
        return totalvalue;
    }

    /**
     * @param totalvalue the totalvalue to set
     */
    public void setTotalvalue(String totalvalue) {
        this.totalvalue = totalvalue;
    }

    /**
     * @return the oldtaget
     */
    public String getOldtaget() {
        return oldtaget;
    }

    /**
     * @param oldtaget the oldtaget to set
     */
    public void setOldtaget(String oldtaget) {
        this.oldtaget = oldtaget;
    }

    /**
     * @return the activitytypeid
     */
    public String getActivitytypeid() {
        return activitytypeid;
    }

    /**
     * @param activitytypeid the activitytypeid to set
     */
    public void setActivitytypeid(String activitytypeid) {
        this.activitytypeid = activitytypeid;
    }

    /**
     * @return the activitytypedes
     */
    public String getActivitytypedes() {
        return activitytypedes;
    }

    /**
     * @param activitytypedes the activitytypedes to set
     */
    public void setActivitytypedes(String activitytypedes) {
        this.activitytypedes = activitytypedes;
    }

    /**
     * @return the targettypeid
     */
    public String getTargettypeid() {
        return targettypeid;
    }

    /**
     * @param targettypeid the targettypeid to set
     */
    public void setTargettypeid(String targettypeid) {
        this.targettypeid = targettypeid;
    }

    /**
     * @return the targettypedes
     */
    public String getTargettypedes() {
        return targettypedes;
    }

    /**
     * @param targettypedes the targettypedes to set
     */
    public void setTargettypedes(String targettypedes) {
        this.targettypedes = targettypedes;
    }

    /**
     * @return the activitytargettbl
     */
    public String getActivitytargettbl() {
        return activitytargettbl;
    }

    /**
     * @param activitytargettbl the activitytargettbl to set
     */
    public void setActivitytargettbl(String activitytargettbl) {
        this.activitytargettbl = activitytargettbl;
    }

    /**
     * @return the branchdes
     */
    public String getBranchdes() {
        return branchdes;
    }

    /**
     * @param branchdes the branchdes to set
     */
    public void setBranchdes(String branchdes) {
        this.branchdes = branchdes;
    }

    /**
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @return the targetactivityid
     */
    public String getTargetactivityid() {
        return targetactivityid;
    }

    /**
     * @param targetactivityid the targetactivityid to set
     */
    public void setTargetactivityid(String targetactivityid) {
        this.targetactivityid = targetactivityid;
    }

    /**
     * @return the targetactivitydes
     */
    public String getTargetactivitydes() {
        return targetactivitydes;
    }

    /**
     * @param targetactivitydes the targetactivitydes to set
     */
    public void setTargetactivitydes(String targetactivitydes) {
        this.targetactivitydes = targetactivitydes;
    }

    /**
     * @return the datatabledata
     */
    public String getDatatabledata() {
        return datatabledata;
    }

    /**
     * @param datatabledata the datatabledata to set
     */
    public void setDatatabledata(String datatabledata) {
        this.datatabledata = datatabledata;
    }

    /**
     * @return the regionactivity
     */
    public String getRegionactivity() {
        return regionactivity;
    }

    /**
     * @param regionactivity the regionactivity to set
     */
    public void setRegionactivity(String regionactivity) {
        this.regionactivity = regionactivity;
    }

    /**
     * @return the regionaltargetid
     */
    public String getRegionaltargetid() {
        return regionaltargetid;
    }

    /**
     * @param regionaltargetid the regionaltargetid to set
     */
    public void setRegionaltargetid(String regionaltargetid) {
        this.regionaltargetid = regionaltargetid;
    }

    /**
     * @return the regionid
     */
    public String getRegionid() {
        return regionid;
    }

    /**
     * @param regionid the regionid to set
     */
    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    /**
     * @return the regiondes
     */
    public String getRegiondes() {
        return regiondes;
    }

    /**
     * @param regiondes the regiondes to set
     */
    public void setRegiondes(String regiondes) {
        this.regiondes = regiondes;
    }

    /**
     * @return the regionalactivitytargetid
     */
    public String getRegionalactivitytargetid() {
        return regionalactivitytargetid;
    }

    /**
     * @param regionalactivitytargetid the regionalactivitytargetid to set
     */
    public void setRegionalactivitytargetid(String regionalactivitytargetid) {
        this.regionalactivitytargetid = regionalactivitytargetid;
    }

    /**
     * @return the regionalactivitytargetdes
     */
    public String getRegionalactivitytargetdes() {
        return regionalactivitytargetdes;
    }

    /**
     * @param regionalactivitytargetdes the regionalactivitytargetdes to set
     */
    public void setRegionalactivitytargetdes(String regionalactivitytargetdes) {
        this.regionalactivitytargetdes = regionalactivitytargetdes;
    }

    /**
     * @return the brnachtargetid
     */
    public String getBrnachtargetid() {
        return brnachtargetid;
    }

    /**
     * @param brnachtargetid the brnachtargetid to set
     */
    public void setBrnachtargetid(String brnachtargetid) {
        this.brnachtargetid = brnachtargetid;
    }

    /**
     * @return the branchgrouptargetid
     */
    public String getBranchgrouptargetid() {
        return branchgrouptargetid;
    }

    /**
     * @param branchgrouptargetid the branchgrouptargetid to set
     */
    public void setBranchgrouptargetid(String branchgrouptargetid) {
        this.branchgrouptargetid = branchgrouptargetid;
    }

    /**
     * @return the brnachtargetdes
     */
    public String getBrnachtargetdes() {
        return brnachtargetdes;
    }

    /**
     * @param brnachtargetdes the brnachtargetdes to set
     */
    public void setBrnachtargetdes(String brnachtargetdes) {
        this.brnachtargetdes = brnachtargetdes;
    }

    /**
     * @return the branchgrouptargetdes
     */
    public String getBranchgrouptargetdes() {
        return branchgrouptargetdes;
    }

    /**
     * @param branchgrouptargetdes the branchgrouptargetdes to set
     */
    public void setBranchgrouptargetdes(String branchgrouptargetdes) {
        this.branchgrouptargetdes = branchgrouptargetdes;
    }

    /**
     * @return the branchgroupid
     */
    public String getBranchgroupid() {
        return branchgroupid;
    }

    /**
     * @param branchgroupid the branchgroupid to set
     */
    public void setBranchgroupid(String branchgroupid) {
        this.branchgroupid = branchgroupid;
    }

    /**
     * @return the branchgroupdes
     */
    public String getBranchgroupdes() {
        return branchgroupdes;
    }

    /**
     * @param branchgroupdes the branchgroupdes to set
     */
    public void setBranchgroupdes(String branchgroupdes) {
        this.branchgroupdes = branchgroupdes;
    }

    /**
     * @return the branchgrouprevenuetargetid
     */
    public String getBranchgrouprevenuetargetid() {
        return branchgrouprevenuetargetid;
    }

    /**
     * @param branchgrouprevenuetargetid the branchgrouprevenuetargetid to set
     */
    public void setBranchgrouprevenuetargetid(String branchgrouprevenuetargetid) {
        this.branchgrouprevenuetargetid = branchgrouprevenuetargetid;
    }

    /**
     * @return the branchgrouprevenuetargetdes
     */
    public String getBranchgrouprevenuetargetdes() {
        return branchgrouprevenuetargetdes;
    }

    /**
     * @param branchgrouprevenuetargetdes the branchgrouprevenuetargetdes to set
     */
    public void setBranchgrouprevenuetargetdes(String branchgrouprevenuetargetdes) {
        this.branchgrouprevenuetargetdes = branchgrouprevenuetargetdes;
    }

    /**
     * @return the branchgroupmembertargetid
     */
    public String getBranchgroupmembertargetid() {
        return branchgroupmembertargetid;
    }

    /**
     * @param branchgroupmembertargetid the branchgroupmembertargetid to set
     */
    public void setBranchgroupmembertargetid(String branchgroupmembertargetid) {
        this.branchgroupmembertargetid = branchgroupmembertargetid;
    }

    /**
     * @return the branchgroupmembertargetdes
     */
    public String getBranchgroupmembertargetdes() {
        return branchgroupmembertargetdes;
    }

    /**
     * @param branchgroupmembertargetdes the branchgroupmembertargetdes to set
     */
    public void setBranchgroupmembertargetdes(String branchgroupmembertargetdes) {
        this.branchgroupmembertargetdes = branchgroupmembertargetdes;
    }

    /**
     * @return the empname
     */
    public String getEmpname() {
        return empname;
    }

    /**
     * @param empname the empname to set
     */
    public void setEmpname(String empname) {
        this.empname = empname;
    }

    /**
     * @return the branchactivitytargetid
     */
    public String getBranchactivitytargetid() {
        return branchactivitytargetid;
    }

    /**
     * @param branchactivitytargetid the branchactivitytargetid to set
     */
    public void setBranchactivitytargetid(String branchactivitytargetid) {
        this.branchactivitytargetid = branchactivitytargetid;
    }

    /**
     * @return the branchactivitytargetdes
     */
    public String getBranchactivitytargetdes() {
        return branchactivitytargetdes;
    }

    /**
     * @param branchactivitytargetdes the branchactivitytargetdes to set
     */
    public void setBranchactivitytargetdes(String branchactivitytargetdes) {
        this.branchactivitytargetdes = branchactivitytargetdes;
    }

    /**
     * @return the branchgroupactivitytargeid
     */
    public String getBranchgroupactivitytargeid() {
        return branchgroupactivitytargeid;
    }

    /**
     * @param branchgroupactivitytargeid the branchgroupactivitytargeid to set
     */
    public void setBranchgroupactivitytargeid(String branchgroupactivitytargeid) {
        this.branchgroupactivitytargeid = branchgroupactivitytargeid;
    }

    /**
     * @return the branchgroupactivitytargetdes
     */
    public String getBranchgroupactivitytargetdes() {
        return branchgroupactivitytargetdes;
    }

    /**
     * @param branchgroupactivitytargetdes the branchgroupactivitytargetdes to
     * set
     */
    public void setBranchgroupactivitytargetdes(String branchgroupactivitytargetdes) {
        this.branchgroupactivitytargetdes = branchgroupactivitytargetdes;
    }

    /**
     * @return the branchgroumemberactivityid
     */
    public String getBranchgroumemberactivityid() {
        return branchgroumemberactivityid;
    }

    /**
     * @param branchgroumemberactivityid the branchgroumemberactivityid to set
     */
    public void setBranchgroumemberactivityid(String branchgroumemberactivityid) {
        this.branchgroumemberactivityid = branchgroumemberactivityid;
    }

    /**
     * @return the branchgroumemberactivitydes
     */
    public String getBranchgroumemberactivitydes() {
        return branchgroumemberactivitydes;
    }

    /**
     * @param branchgroumemberactivitydes the branchgroumemberactivitydes to set
     */
    public void setBranchgroumemberactivitydes(String branchgroumemberactivitydes) {
        this.branchgroumemberactivitydes = branchgroumemberactivitydes;
    }

    /**
     * @return the branchgroupmemberid
     */
    public String getBranchgroupmemberid() {
        return branchgroupmemberid;
    }

    /**
     * @param branchgroupmemberid the branchgroupmemberid to set
     */
    public void setBranchgroupmemberid(String branchgroupmemberid) {
        this.branchgroupmemberid = branchgroupmemberid;
    }

    /**
     * @return the branchgroupmemberdes
     */
    public String getBranchgroupmemberdes() {
        return branchgroupmemberdes;
    }

    /**
     * @param branchgroupmemberdes the branchgroupmemberdes to set
     */
    public void setBranchgroupmemberdes(String branchgroupmemberdes) {
        this.branchgroupmemberdes = branchgroupmemberdes;
    }

    /**
     * @return the targetrevenue
     */
    public String getTargetrevenue() {
        return targetrevenue;
    }

    /**
     * @param targetrevenue the targetrevenue to set
     */
    public void setTargetrevenue(String targetrevenue) {
        this.targetrevenue = targetrevenue;
    }

    /**
     * @return the tab2revenue
     */
    public String getTab2revenue() {
        return tab2revenue;
    }

    /**
     * @param tab2revenue the tab2revenue to set
     */
    public void setTab2revenue(String tab2revenue) {
        this.tab2revenue = tab2revenue;
    }

    /**
     * @return the tab2totalcount
     */
    public String getTab2totalcount() {
        return tab2totalcount;
    }

    /**
     * @param tab2totalcount the tab2totalcount to set
     */
    public void setTab2totalcount(String tab2totalcount) {
        this.tab2totalcount = tab2totalcount;
    }

    /**
     * @return the tab2count
     */
    public String getTab2count() {
        return tab2count;
    }

    /**
     * @param tab2count the tab2count to set
     */
    public void setTab2count(String tab2count) {
        this.tab2count = tab2count;
    }

    /**
     * @return the tab3totalrevenue
     */
    public String getTab3totalrevenue() {
        return tab3totalrevenue;
    }

    /**
     * @param tab3totalrevenue the tab3totalrevenue to set
     */
    public void setTab3totalrevenue(String tab3totalrevenue) {
        this.tab3totalrevenue = tab3totalrevenue;
    }

    /**
     * @return the tab3revenue
     */
    public String getTab3revenue() {
        return tab3revenue;
    }

    /**
     * @param tab3revenue the tab3revenue to set
     */
    public void setTab3revenue(String tab3revenue) {
        this.tab3revenue = tab3revenue;
    }

    /**
     * @return the tab3totalcount
     */
    public String getTab3totalcount() {
        return tab3totalcount;
    }

    /**
     * @param tab3totalcount the tab3totalcount to set
     */
    public void setTab3totalcount(String tab3totalcount) {
        this.tab3totalcount = tab3totalcount;
    }

    /**
     * @return the tab3count
     */
    public String getTab3count() {
        return tab3count;
    }

    /**
     * @param tab3count the tab3count to set
     */
    public void setTab3count(String tab3count) {
        this.tab3count = tab3count;
    }

    /**
     * @return the tab4totalrevenue
     */
    public String getTab4totalrevenue() {
        return tab4totalrevenue;
    }

    /**
     * @param tab4totalrevenue the tab4totalrevenue to set
     */
    public void setTab4totalrevenue(String tab4totalrevenue) {
        this.tab4totalrevenue = tab4totalrevenue;
    }

    /**
     * @return the tab4revenue
     */
    public String getTab4revenue() {
        return tab4revenue;
    }

    /**
     * @param tab4revenue the tab4revenue to set
     */
    public void setTab4revenue(String tab4revenue) {
        this.tab4revenue = tab4revenue;
    }

    /**
     * @return the tab4totalcount
     */
    public String getTab4totalcount() {
        return tab4totalcount;
    }

    /**
     * @param tab4totalcount the tab4totalcount to set
     */
    public void setTab4totalcount(String tab4totalcount) {
        this.tab4totalcount = tab4totalcount;
    }

    /**
     * @return the tab4count
     */
    public String getTab4count() {
        return tab4count;
    }

    /**
     * @param tab4count the tab4count to set
     */
    public void setTab4count(String tab4count) {
        this.tab4count = tab4count;
    }

    /**
     * @return the tab5totalrevenue
     */
    public String getTab5totalrevenue() {
        return tab5totalrevenue;
    }

    /**
     * @param tab5totalrevenue the tab5totalrevenue to set
     */
    public void setTab5totalrevenue(String tab5totalrevenue) {
        this.tab5totalrevenue = tab5totalrevenue;
    }

    /**
     * @return the tab5revenue
     */
    public String getTab5revenue() {
        return tab5revenue;
    }

    /**
     * @param tab5revenue the tab5revenue to set
     */
    public void setTab5revenue(String tab5revenue) {
        this.tab5revenue = tab5revenue;
    }

    /**
     * @return the tab5totalcount
     */
    public String getTab5totalcount() {
        return tab5totalcount;
    }

    /**
     * @param tab5totalcount the tab5totalcount to set
     */
    public void setTab5totalcount(String tab5totalcount) {
        this.tab5totalcount = tab5totalcount;
    }

    /**
     * @return the tab5count
     */
    public String getTab5count() {
        return tab5count;
    }

    /**
     * @param tab5count the tab5count to set
     */
    public void setTab5count(String tab5count) {
        this.tab5count = tab5count;
    }

    /**
     * @return the tab2totalrevenue
     */
    public String getTab2totalrevenue() {
        return tab2totalrevenue;
    }

    /**
     * @param tab2totalrevenue the tab2totalrevenue to set
     */
    public void setTab2totalrevenue(String tab2totalrevenue) {
        this.tab2totalrevenue = tab2totalrevenue;
    }

    /**
     * @return the memberbranchactivitytargetid
     */
    public String getMemberbranchactivitytargetid() {
        return memberbranchactivitytargetid;
    }

    /**
     * @param memberbranchactivitytargetid the memberbranchactivitytargetid to
     * set
     */
    public void setMemberbranchactivitytargetid(String memberbranchactivitytargetid) {
        this.memberbranchactivitytargetid = memberbranchactivitytargetid;
    }

    /**
     * @return the memberregionalactivitytargetid
     */
    public String getMemberregionalactivitytargetid() {
        return memberregionalactivitytargetid;
    }

    /**
     * @param memberregionalactivitytargetid the memberregionalactivitytargetid
     * to set
     */
    public void setMemberregionalactivitytargetid(String memberregionalactivitytargetid) {
        this.memberregionalactivitytargetid = memberregionalactivitytargetid;
    }

    /**
     * @return the callcount
     */
    public String getCallcount() {
        return callcount;
    }

    /**
     * @param callcount the callcount to set
     */
    public void setCallcount(String callcount) {
        this.callcount = callcount;
    }

    /**
     * @return the proposalscount
     */
    public String getProposalscount() {
        return proposalscount;
    }

    /**
     * @param proposalscount the proposalscount to set
     */
    public void setProposalscount(String proposalscount) {
        this.proposalscount = proposalscount;
    }

    /**
     * @return the visitspresentationscount
     */
    public String getVisitspresentationscount() {
        return visitspresentationscount;
    }

    /**
     * @param visitspresentationscount the visitspresentationscount to set
     */
    public void setVisitspresentationscount(String visitspresentationscount) {
        this.visitspresentationscount = visitspresentationscount;
    }

    /**
     * @return the vpresentationsactivityid
     */
    public String getVpresentationsactivityid() {
        return vpresentationsactivityid;
    }

    /**
     * @param vpresentationsactivityid the vpresentationsactivityid to set
     */
    public void setVpresentationsactivityid(String vpresentationsactivityid) {
        this.vpresentationsactivityid = vpresentationsactivityid;
    }

    /**
     * @return the callactivityid
     */
    public String getCallactivityid() {
        return callactivityid;
    }

    /**
     * @param callactivityid the callactivityid to set
     */
    public void setCallactivityid(String callactivityid) {
        this.callactivityid = callactivityid;
    }

    /**
     * @return the proposalsactivityid
     */
    public String getProposalsactivityid() {
        return proposalsactivityid;
    }

    /**
     * @param proposalsactivityid the proposalsactivityid to set
     */
    public void setProposalsactivityid(String proposalsactivityid) {
        this.proposalsactivityid = proposalsactivityid;
    }

}

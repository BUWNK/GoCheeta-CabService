/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.activitytarget;

/**
 * @Author : Roshen Dilshan
 * @Document : ActivityTarget
 * @Created on : Jul 10, 2016, 9:10:15 AM
 */
public class ActivityTarget {

    private long activitytargetid;
    private long targetid;
    private int activitytypeid;
    private long count;

    /**
     * @return the activitytargetid
     */
    public long getActivitytargetid() {
        return activitytargetid;
    }

    /**
     * @param activitytargetid the activitytargetid to set
     */
    public void setActivitytargetid(long activitytargetid) {
        this.activitytargetid = activitytargetid;
    }

    /**
     * @return the targetid
     */
    public long getTargetid() {
        return targetid;
    }

    /**
     * @param targetid the targetid to set
     */
    public void setTargetid(long targetid) {
        this.targetid = targetid;
    }

    /**
     * @return the activitytypeid
     */
    public int getActivitytypeid() {
        return activitytypeid;
    }

    /**
     * @param activitytypeid the activitytypeid to set
     */
    public void setActivitytypeid(int activitytypeid) {
        this.activitytypeid = activitytypeid;
    }

    /**
     * @return the count
     */
    public long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(long count) {
        this.count = count;
    }

}

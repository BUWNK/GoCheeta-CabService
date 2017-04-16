/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.model.hierarchytarget;

import java.math.BigDecimal;

/**
 * @Author : Roshen Dilshan
 * @Document : HierarchyTarget
 * @Created on : Mar 15, 2017, 9:20:06 AM
 */
public class HierarchyTarget {

    private int hierarchyid;
    private BigDecimal targetamount;

    /**
     * @return the hierarchyid
     */
    public int getHierarchyid() {
        return hierarchyid;
    }

    /**
     * @param hierarchyid the hierarchyid to set
     */
    public void setHierarchyid(int hierarchyid) {
        this.hierarchyid = hierarchyid;
    }

    /**
     * @return the targetamount
     */
    public BigDecimal getTargetamount() {
        return targetamount;
    }

    /**
     * @param targetamount the targetamount to set
     */
    public void setTargetamount(BigDecimal targetamount) {
        this.targetamount = targetamount;
    }

}

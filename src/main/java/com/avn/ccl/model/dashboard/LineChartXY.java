/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avn.ccl.model.dashboard;

import java.math.BigDecimal;

/**
 * @Author : Roshen Dilshan
 * @Document : LineChartXY
 * @Created on : May 12, 2016, 7:03:55 AM
 */
public class LineChartXY {
    
    private long pointx;
    private BigDecimal pointy;

    /**
     * @return the pointx
     */
    public long getPointx() {
        return pointx;
    }

    /**
     * @param pointx the pointx to set
     */
    public void setPointx(long pointx) {
        this.pointx = pointx;
    }

    /**
     * @return the pointy
     */
    public BigDecimal getPointy() {
        return pointy;
    }

    /**
     * @param pointy the pointy to set
     */
    public void setPointy(BigDecimal pointy) {
        this.pointy = pointy;
    }


}

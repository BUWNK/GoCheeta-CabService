/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util;

import java.sql.Date;

/**
 *
 * @author Hirantha
 */
public class FormatDate {
    
    public static Date stringToDate(String dateString){
        return java.sql.Date.valueOf(dateString);
    }
}

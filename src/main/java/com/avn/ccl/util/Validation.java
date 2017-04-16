/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util;

/**
 * @Author : Roshen Dilshan Dilshan
 * @Document : Validation
 * @Created on : Jul 20, 2015, 3:49:16 PM
 */
public class Validation {

    public static boolean isDropDownSelected(String value) {
        boolean condition = false;
        if (value != null && !value.isEmpty() && !value.contentEquals("")) {
            condition = true;
        }
        return condition;
    }

    public static boolean isTextEntered(String value) {
        boolean condition = false;
        if (value != null && !value.isEmpty()) {
            condition = true;
        }
        return condition;
    }

}

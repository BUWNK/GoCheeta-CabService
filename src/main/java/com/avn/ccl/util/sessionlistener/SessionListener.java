/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util.sessionlistener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author : Roshen Dilshan
 * @Document : SessionListener
 * @Created on : Feb 3, 2016, 3:48:14 PM
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        System.out.println("Session Destroyed");
        
    }

}

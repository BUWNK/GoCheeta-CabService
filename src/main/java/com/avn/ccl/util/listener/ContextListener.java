/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util.listener;

import com.avn.ccl.util.ApplicationUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author : Roshen Dilshan
 * @Document : ContextListener
 * @Created on : Sep 23, 2015, 10:57:51 AM
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationUtil.SERVLET_CONTEXT = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context Destoried");
    }

}

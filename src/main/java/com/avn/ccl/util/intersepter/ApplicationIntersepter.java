/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util.intersepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author : Roshen Dilshan
 * @Document : ApplicationIntersepter
 * @Created on : Feb 3, 2016, 2:37:46 PM
 */
public class ApplicationIntersepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
        boolean isContinueRequest = true;
        String requestUrl = hsr.getRequestURI();
        if (requestUrl.split("/").length > 2 
                && !requestUrl.split("/")[2].equalsIgnoreCase("resources") 
                && !requestUrl.split("/")[2].equalsIgnoreCase("login") 
                && !requestUrl.split("/")[2].equalsIgnoreCase("home")
                && (hsr.getQueryString() == null || !hsr.getQueryString().equalsIgnoreCase("auth=logout"))) {
            if (hsr.getSession(false) == null || hsr.getSession(false).getAttribute("pagehierarchy") == null) {
                hsr1.sendRedirect(hsr.getContextPath() + "/?auth=sessiontimeout");
                isContinueRequest = false;
            }
        } else if (requestUrl.split("/").length == 2 && (hsr.getQueryString() == null || !hsr.getQueryString().equalsIgnoreCase("auth=logout"))) {
            if (hsr.getSession(false) != null && hsr.getSession(false).getAttribute("pagehierarchy") != null) {
                hsr1.sendRedirect(hsr.getContextPath() + "/home");
            }
        }
        return isContinueRequest;
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
        String requestUrl = hsr.getRequestURI();
        boolean isLogOut = false;
        if (hsr.getQueryString() != null && hsr.getQueryString().equalsIgnoreCase("auth=logout")) {
            isLogOut = true;
            HttpSession session = hsr.getSession();
            session.removeAttribute("pagehierarchy");
            session.removeAttribute("username");
            session.invalidate();
        }
        if (!isLogOut && requestUrl.split("/").length > 2
                && !requestUrl.split("/")[2].equalsIgnoreCase("resources")
                && requestUrl.split("/")[2].equalsIgnoreCase("login")
                && requestUrl.split("/")[2].equalsIgnoreCase("home")) {
            if (hsr.getSession(false) == null || hsr.getSession(false).getAttribute("pagehierarchy") == null) {
                hsr1.sendRedirect(hsr.getContextPath() + "/?auth=sessiontimeout");
            }
        }
    }

}

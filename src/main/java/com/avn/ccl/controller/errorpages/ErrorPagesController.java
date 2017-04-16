/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.controller.errorpages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Lahiru
 */
@Controller
public class ErrorPagesController {
    
      @RequestMapping(value = "/errorpages/error404", method = RequestMethod.GET)
    public String error404(ModelMap model) {

        return "errorpages/error404";

    }
    
      @RequestMapping(value = "/errorpages/error500", method = RequestMethod.GET)
    public String error500(ModelMap model) {

        return "errorpages/error500";

    }
    
}

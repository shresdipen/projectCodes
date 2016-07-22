/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.controller;

import edu.mum.waa545.project.service.UserService;
import edu.mum.waa545.project.util.userRegistrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rajiv
 */
@Controller
public class LoginController {
    
    @Autowired
    UserService userService;
    
    
    @Autowired
    private userRegistrationUtil userregistrationutil;
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String getLoginPage(Model model) {
        System.out.println("From Login: "+userService.getAllusers());
        model.addAttribute("country",userregistrationutil.getAllCountries());
        model.addAttribute("nationality",userregistrationutil.getAllCountries());
        return "login";
    }
}
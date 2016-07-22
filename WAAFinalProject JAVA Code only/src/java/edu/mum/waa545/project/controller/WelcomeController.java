/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.controller;

import edu.mum.waa545.project.model.User;
import edu.mum.waa545.project.service.UserService;
import java.security.Principal;
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
public class WelcomeController {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/welcome", method=RequestMethod.GET )
    public String getIndexPage(Principal principal, Model model){
        String username = (String)principal.getName();
        User user = userService.findByUsername(username);
        
        model.addAttribute("user", user);
        
        return "welcome";
    }
}

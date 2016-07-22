/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rajiv
 */
@Controller
public class InitPageController {
    
    @RequestMapping(value="/initpage", method=RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }
}

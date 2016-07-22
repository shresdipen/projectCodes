/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dipen
 */

@Controller()
public class TestController {
    
    public String handleRequestInternal() throws Exception {
        
        System.out.println("Here.............");
        return ("uploadimages");
    }
    @RequestMapping("/uploadimages")
    public String sayHello(Model model){
    
        model.addAttribute("name", "Beeminet");
        
        return "uploadimages";
    }
}


package edu.mum.waa545.project.controller;

import edu.mum.waa545.project.model.EmployeeVO;
import edu.mum.waa545.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author tsegaab
 */
@Controller
@SessionAttributes("employee")
public class EmployeeController {
    @Autowired
    UserService manager;
     
    @RequestMapping(value = "/addEmployee",method = RequestMethod.GET)
    public String setupForm(Model model) 
    {
         EmployeeVO employeeVO = new EmployeeVO();
         model.addAttribute("employee", employeeVO);
         return "addEmployee";
    }
     
    @RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("employee") EmployeeVO employeeVO,
                            BindingResult result, SessionStatus status) 
    {
        //status.setComplete();
        return "redirect:addSuccess.spring";
    }
     
    @RequestMapping(value = "addSuccess.spring", method = RequestMethod.GET)
    public String success(Model model) 
    {
         return "addSuccess";
    }
}


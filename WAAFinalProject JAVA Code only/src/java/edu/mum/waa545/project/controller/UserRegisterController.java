package edu.mum.waa545.project.controller;

import edu.mum.waa545.project.db.UserRepository;
import edu.mum.waa545.project.model.Address;
import edu.mum.waa545.project.model.User;
import edu.mum.waa545.project.security.LoginAuthenticator;
import edu.mum.waa545.project.service.UserService;
import edu.mum.waa545.project.util.userRegistrationUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserRegisterController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private userRegistrationUtil userregistrationutil;
    
    @Autowired
    UserRepository userRepository;
   
    @Autowired
    LoginAuthenticator loginAuthenticator;
    
   @RequestMapping("/register")
   public ModelAndView user(){
       
       return new ModelAndView("user","command",new User());
   }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String sinup(Model model) {
        model.addAttribute("country",userregistrationutil.getAllCountries());
        model.addAttribute("nationality",userregistrationutil.getAllCountries());
        return "register";
    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public String saveNewUser(@RequestParam Map<String,String> allParams,Model model) {

        String username =allParams.get("username");
        boolean usernameDuplicate = userService.checkDuplicate(username);
        if(usernameDuplicate){
        username = "username is taken"; 
        model.addAttribute("username",username);
        model.addAttribute("country",userregistrationutil.getAllCountries());
        model.addAttribute("nationality",userregistrationutil.getAllCountries());
        return "register";
        }
              
        String password =allParams.get("password");
        String firstname =allParams.get("firstname");
        String lastname =allParams.get("lastname");
        int age =Integer.parseInt(allParams.get("age"));
        
        String dateofbirth =allParams.get("dateofbirth");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = new Date();
        try {
        parsedDate = format.parse(dateofbirth);            
        } catch (Exception e) {
        }
        
        String gender =allParams.get("gender");
        String country =allParams.get("country");
        String state =allParams.get("state");
        String highschool =allParams.get("highschool");
        String college =allParams.get("college");
        String nationality =allParams.get("nationality");
        
        User newuser =  new User(username, password, firstname, lastname, age, parsedDate , gender, 
        new Address(country, state, highschool, college, nationality));
        
        model.addAttribute("allusers", userService.getAllusers());
        model.addAttribute("newuser", newuser);
        userService.save(newuser);
        System.out.println("Userssss: "+userRepository.getAllusers());
        loginAuthenticator.setUserRepository(userRepository);
        return "welcome";
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    
}

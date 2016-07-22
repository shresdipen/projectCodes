/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.security;

import edu.mum.waa545.project.db.UserRepository;
import edu.mum.waa545.project.model.User;
import edu.mum.waa545.project.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rajiv
 */
public class LoginAuthenticator implements AuthenticationProvider {
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserRepository userRepository;
    
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Userssss2: "+userRepository.getAllusers());
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        
        User user = userService.findByUsername(username, userRepository);
        System.out.println(user+","+username+","+password);
        if(user == null)
            return null;
        
        if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            //grantedAuths.add(new SimpleGrantedAuthority("ROLE_SUPERVISOR"));

            Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getName(),
                            authentication.getCredentials(), grantedAuths);
            return auth;
        }
        
        return null;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    @RequestMapping(value="/loginasdasdasd", method=RequestMethod.GET)
    public String getLoginPage() {
        System.out.println("From Login: "+userService.getAllusers());
        return "login";
    }
    
}


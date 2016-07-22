/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.controller;

import edu.mum.waa545.project.model.RegisteredUser;
import edu.mum.waa545.project.model.User;
import edu.mum.waa545.project.service.FriendService;
import edu.mum.waa545.project.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author kb
 */
@Controller

public class FriendController extends AbstractController {

    @Autowired
    FriendService friends;

    @Autowired
    UserService users;

    public FriendController() {
    }

    @RequestMapping(value = "users.spring", method = RequestMethod.GET)
    public String getFriends(@RequestParam("name") String name, Model model) {

        model.addAttribute("friends", friends.getFriendsOnly(name));
        model.addAttribute("user", friends.getUser(name));
        //model.addAttribute("users",friends.searchUsers(name));
        //model.addAttribute("notFriend", friends.suggestFriends(name));
        model.addAttribute("suggested", friends.suggestFriends(name));
        System.out.println("From user get");
        return "users";
    }
    
    @RequestMapping(value = "/removeUser.spring", method = RequestMethod.POST)
    public ModelAndView removeFriend(HttpServletRequest request) {
        String oldFriend = request.getParameter("oldFriend");
        String userName = request.getParameter("delUser");
        RegisteredUser regUser = friends.getRegisteredUserByUserName(userName);
        User user = friends.getUserByUserName(oldFriend);
        regUser.removeFriend(user);
        request.setAttribute("friends", friends.getFriendsOnly(userName));
        request.setAttribute("suggested", friends.suggestFriends(userName));
        request.setAttribute("searched", friends.searchUsers(userName));
        request.setAttribute("name", regUser.getUser().getUsername());
        
        return new ModelAndView("redirect:/users.spring?name=" + regUser.getUser().getUsername());
    }

    @RequestMapping(value="myLogin", method = RequestMethod.GET)
    public String myLogin() {
        return "myLogin";
    }

    @RequestMapping(value = "users.spring", method = RequestMethod.POST)
    public String getAllFriends(@RequestParam("names") String name, @RequestParam("newFriend") String userName, Model model) {

        User user = friends.getUserByUserName(userName);
        model.addAttribute("friends", friends.getFriendsOnly(name));
        model.addAttribute("user", friends.getUser(name));
        model.addAttribute("suggested", friends.suggestFriends(name));

        return "users";
    }

    
    
    @RequestMapping(value = "/addFriend.spring", method = RequestMethod.POST)
    public ModelAndView addFriend(HttpServletRequest request) {
        String newFriend = request.getParameter("newFriend");
        String userName = request.getParameter("regUser");
        RegisteredUser regUser = friends.getRegisteredUserByUserName(userName);
        User user = friends.getUserByUserName(newFriend);
        System.out.println("Frin add friend");
        regUser.addFriend(user);
        request.setAttribute("friends", friends.getFriendsOnly(userName));
        request.setAttribute("suggested", friends.suggestFriends(userName));
        request.setAttribute("searched", friends.searchUsers(userName));
        request.setAttribute("name", regUser.getUser().getUsername());
        
        return new ModelAndView("redirect:/users.spring?name=" + regUser.getUser().getUsername());
    }
    
    @RequestMapping(value = "/searchFriend.spring", method = RequestMethod.POST)
    public ModelAndView searchFriend(HttpServletRequest request) {
       // String newFriend = request.getParameter("newFriend");
        String userName = request.getParameter("regUser");
        String searchUser = request.getParameter("firstName");
        RegisteredUser regUser = friends.getRegisteredUserByUserName(userName);
        //User user = friends.getUserByUserName(newFriend);
        //System.out.println("Frin add friend");
        //regUser.addFriend(user);
        request.setAttribute("friends", friends.getFriendsOnly(userName));
        request.setAttribute("suggested", friends.suggestFriends(userName));
        request.setAttribute("searched", friends.searchUsers(searchUser));
        request.setAttribute("name", regUser.getUser().getUsername());
        
        return new ModelAndView("redirect:/users.spring?name=" + regUser.getUser().getUsername());
    }
    
    @RequestMapping(value = "/addFriends.spring", method = RequestMethod.POST)
    public ModelAndView addFriendHome(HttpServletRequest request) {
        String newFriend = request.getParameter("newFriend");
        
        String userName = request.getParameter("regUser");
        userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        System.out.println("Friends: "+friends.getAllUsers());
        RegisteredUser regUser = friends.getRegisteredUserByUserName(userName);
        User user = friends.getUserByUserName(newFriend);
        System.out.println("Frin add friend: "+regUser);
        
        System.out.println("userName : "+userName);
        System.out.println("newFriend: "+newFriend);
        regUser.addFriend(user);
        request.setAttribute("friends", friends.getFriendsOnly(userName));
        request.setAttribute("suggested", friends.suggestFriends(userName));
        request.setAttribute("users", friends.searchUsers(userName));
        request.setAttribute("name", regUser.getUser().getUsername());
        
        return new ModelAndView("redirect:/home.spring?name=" + regUser.getUser().getUsername());
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}

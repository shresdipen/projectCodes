/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.service;

import edu.mum.waa545.project.model.RegisteredUser;
import edu.mum.waa545.project.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author kb
 */
@Service
public interface FriendService {

    //TO add a friend
    public void addFriend(RegisteredUser regUsers,User user);
    public void removeFriend(RegisteredUser regUsers,User user);

    //To get all of your friends only
    public List<User> getFriendsOnly(String name);

    //To get all the users in the system
    public List<User> getAllUsers();

    //get only one user with the given user name
    public User getUser(String userName);
    
    //searching user using first name
    public List<User> searchUsers(String firstName);
    
    //Suggest users
    public List<User> suggestFriends(String userName);
    
    //
    public User getUserByUserName(String userName);

    //getting registered users
    public RegisteredUser getRegisteredUserByUserName(String userName);
}

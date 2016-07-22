/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kb
 */
@Repository
public class RegisteredUser {

    @Autowired
    private User user;
    private List<User> friends = new ArrayList<>();

    public RegisteredUser() {
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addFriend(User user) {

        this.friends.add(user);
    }
    
    public void removeFriend(User user){
    
        this.friends.remove(user);
    }

}

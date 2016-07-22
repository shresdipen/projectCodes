/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.db;

import edu.mum.waa545.project.model.RegisteredUser;
import edu.mum.waa545.project.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author kb
 */
@Component
public class FriendDB {

    private List<RegisteredUser> friends = new ArrayList<>();

    public FriendDB() {

        RegisteredUser reg1 = new RegisteredUser();
        User u1 = new User();
        u1.setFirstname("Hagos");
        u1.setLastname("Bezabh");
        u1.setUsername("username1");
        reg1.setUser(u1);

        User u2 = new User();
        u2.setFirstname("Jhon");
        u2.setLastname("Sun");
        u2.setUsername("username2");

        User u3 = new User();
        u3.setFirstname("Jack");
        u3.setLastname("Apple");
        u3.setUsername("jack");

        User u4 = new User();
        u4.setFirstname("Victor");
        u4.setLastname("Vic");
        u4.setUsername("vic");

        List<User> friends1 = new ArrayList<>();
        friends1.add(u2);
        friends1.add(u3);
        friends1.add(u4);
        
        

        reg1.setFriends(friends1);

        RegisteredUser reg2 = new RegisteredUser();

        User u5 = new User();
        u5.setFirstname("Jennifer");
        u5.setLastname("Jen");
        u5.setUsername("jen");

        reg2.setUser(u5);

        User u6 = new User();
        u6.setFirstname("Beimnet");
        u6.setLastname("beimni");
        u6.setUsername("bei");

        User u7 = new User();
        u7.setFirstname("Dipen");
        u7.setLastname("dipen");
        u7.setUsername("username2");

        User u8 = new User();
        u8.setFirstname("Rajeev");
        u8.setLastname("Ragive");
        u8.setUsername("rag");

        User u9 = new User();
        u9.setFirstname("Najeeb");
        u9.setLastname("Najeeb");
        u9.setUsername("naj");

        User u10 = new User();
        u10.setFirstname("Levi");
        u10.setLastname("Lev");
        u10.setUsername("lev");

        List<User> friends2 = new ArrayList<>(); //= new ArrayList..asList(u6, u7, u8, u9, u10);

        friends2.add(u6);
        friends2.add(u7);
        friends2.add(u8);
        friends2.add(u9);
        friends2.add(u10);

        reg2.setFriends(friends2);

        friends.add(reg1);
        friends.add(reg2);
    }

    public List<RegisteredUser> getFriends() {
        return friends;
    }

    public void setFriends(List<RegisteredUser> friends) {
        this.friends = friends;
    }

    public RegisteredUser getRegisteredUserByUserName(String userName) {

        for (RegisteredUser regUser : friends) {
            System.out.println("regUser: "+regUser.getUser());
            System.out.println("username: "+userName);
            if (regUser.getUser().getUsername().equalsIgnoreCase(userName)) {
                return regUser;
            }
        }
        return null;
    }

    public void addFriend(RegisteredUser friend) {

        this.friends.add(friend);
    }
    public void removeFriend(RegisteredUser friend){
        this.friends.remove(friend);
    }
}

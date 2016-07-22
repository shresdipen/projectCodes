/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.db;

import edu.mum.waa545.project.model.User;
import java.util.List;

/**
 *
 * @author tsegaab
 */
public interface UserRepository {
    
    List<User> getAllusers();
   // void setOurusers(List<User> ourusers);
    void save(User user);
    User findUserByName(String username);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.service;

import edu.mum.waa545.project.model.Post;
import edu.mum.waa545.project.model.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dipen
 */
public interface PostService {
 
    public List<Post> getPosts();
    
    public List<Post> getUserPosts();
    
    public void addPost(List<String> images, Map<String, String[]> param);
    
    public void addChildrenPost(String parentPostId, String comment);
    
    public void removeChildrenPost(String parentPostId, String childPostId);
    
    public void removePost(String postId);
    
    public List<Post> getFriendsPosts(List<User> friends);
}

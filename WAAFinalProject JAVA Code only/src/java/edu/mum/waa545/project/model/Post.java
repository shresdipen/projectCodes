/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author dipen
 */
public class Post implements Comparable<Post>{
    public String userName;
    public List<String> imageName;
    public String comment;
    public String postId;
    public List<Post> childrenPost;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getImageName() {
        return imageName;
    }

    public void setImageName(List<String> imageName) {
        this.imageName = imageName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public List<Post> getChildrenPost() {
        return childrenPost;
    }

    public void setChildrenPost(List<Post> childrenPost) {
        this.childrenPost = childrenPost;
    }

    public Post(String userName, List<String> imageName, String comment, String postId, List<Post> childrenPost) {
        this.userName = userName;
        this.imageName = imageName;
        this.comment = comment;
        this.postId = postId;
        this.childrenPost = childrenPost;
    }

    public Post() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.postId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (!Objects.equals(this.postId, other.postId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Post{" + "userName=" + userName + ", imageName=" + imageName + ", comment=" + comment + ", postId=" + postId + ", childrenPost=" + childrenPost + '}';
    } 

    @Override
    public int compareTo(Post o) {
        int post1 = Integer.parseInt(this.getPostId());
        int post2 = Integer.parseInt(o.getPostId());
       if(post1<post2) return -1;
       else if(post1<post2) return 1;
       else return 0;
    }

        
    
    
}

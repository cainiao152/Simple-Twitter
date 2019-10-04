package com.simpletwitter.demo.Entity;

import org.apache.tomcat.jni.Time;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity

public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String username;
    private String password;
    private String passwordconfirm;
    private HashMap<Long,String> twittes;

    @OneToMany(targetEntity=User.class, fetch=FetchType.EAGER)
    private List<User> followered;

    public List<User> getFollowered() {
        return followered;
    }

    public void setFollowered(List<User> followered) {
        this.followered = followered;
    }

    public void addFollowered(User temp) {
        this.followered.add(temp);
    }

    public boolean checkfollowed(String name){
        for(int i=0;i<followered.size();i++){
           if(followered.get(i).getUsername().equals(name))
               return false;
        }

        return true;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String passwordconfirm) {
        this.username = username;
        this.password = password;
        this.passwordconfirm = passwordconfirm;
    }

    public User(String username, String password, HashMap<Long, String> twittes, List<User> followered) {
        this.username = username;
        this.password = password;
        this.twittes = twittes;
        this.followered = followered;
    }

    public User(String username, String password, HashMap<Long, String> twittes) {
        this.username = username;
        this.password = password;
        this.twittes = twittes;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordconfirm() {
        return passwordconfirm;
    }

    public HashMap<Long, String> getTwittes() {
        return twittes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordconfirm(String passwordconfirm) {
        this.passwordconfirm = passwordconfirm;
    }

    public void setTwittes(HashMap<Long, String> twittes) {
        this.twittes = twittes;
    }

    public void addTwittes(String twitte){this.twittes.put(System.currentTimeMillis(), twitte);}


}

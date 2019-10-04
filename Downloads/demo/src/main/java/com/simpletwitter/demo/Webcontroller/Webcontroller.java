package com.simpletwitter.demo.Webcontroller;

import com.simpletwitter.demo.Entity.User;
import com.simpletwitter.demo.Services.UserService;
import com.simpletwitter.demo.UserRepository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class Webcontroller {
    @Autowired
    private usersRepository  user;

    @Autowired
    private UserService  service;

    private User Logined;

    public Webcontroller(usersRepository usersRepository) {
        this.user = usersRepository;
    }


    @GetMapping("/")
    public String home(Model model){
        List<User> userinfos= (List<User>) user.findAll();
        model.addAttribute("all", userinfos );
        return "home";
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("twittes","from index ");
        return "index";
    }

    @PostMapping("/follow")
    public String followForm(@ModelAttribute("follow") User follow, Model model){

        List<User> userinfos= (List<User>) this.user.findAll();
        model.addAttribute("all", userinfos );


        User temp = this.user.findByUsername(follow.getUsername());
        if(temp!=null){
            if(Logined.checkfollowed(follow.getUsername())){
                Logined.addFollowered(temp);
                service.save(Logined);
            }
        }

        model.addAttribute("followed",Logined.getFollowered());
        model.addAttribute("user",Logined);
        model.addAttribute("twittes",new User());
        return "index";

    }

    @PostMapping("/index")
    public String indexForm(@ModelAttribute("twittes") User twittes,Model model){


           List<User> userinfos= (List<User>) this.user.findAll();
           model.addAttribute("all", userinfos );
           String temp=twittes.getUsername();
           Logined.addTwittes(temp);
           service.save(Logined);
           model.addAttribute("user",Logined);
           model.addAttribute("followed",Logined.getFollowered());
           model.addAttribute("follow",new User());

        return "index";
    }



    @GetMapping("/reg")
    public String reg(Model model){
        model.addAttribute("user",new User());
        return "reg";
    }


    @PostMapping("/reg")
    public String regForm(@ModelAttribute("user") User user, Model model){
        List<User> userinfo= (List<User>) this.user.findAll();
        model.addAttribute("all", userinfo );
        String username=user.getUsername();
        String password=user.getPassword();
        String passwordconfirm=user.getPasswordconfirm();
        model.addAttribute("message1","  ");
        model.addAttribute("message2","  ");
        model.addAttribute("message3","  ");
        if(username.equals("") || password.equals("") || passwordconfirm.equals("") ){
            model.addAttribute("message3","At least of the fields is empty, please don't leave it empty");
            return "reg";
        }
        User usertemp=this.user.findByUsername(username);
        if(usertemp==null){
                if(password.equals(passwordconfirm))
                {
                    user.setTwittes(new HashMap<Long,String>());
                    user.setFollowered(new ArrayList<>());
                    Logined=user;
                    service.save(user);
                    model.addAttribute("user",Logined);
                    model.addAttribute("twittes",new User());
                    model.addAttribute("follow",new User());
                    model.addAttribute("followed",Logined.getFollowered());
                    return "index";
                }
                else{
                    model.addAttribute("message3","Password not matched");
                    return "reg";
                }
            }
            else{
                model.addAttribute("message1","Username already been used");
            return "reg";
        }
    }



    @GetMapping("/login")
    public String LoginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String LoginSubmit(@ModelAttribute("user") User user, Model model){
        List<User> userinfo= (List<User>) this.user.findAll();
        model.addAttribute("all", userinfo );

        String username=user.getUsername();
        String password=user.getPassword();
        User usertemp=this.user.findByUsername(username);
        model.addAttribute("message","  ");
        if(usertemp!=null){
            String password1=usertemp.getPassword();
            if(password.equals(password1)){
                Logined=usertemp;
                model.addAttribute("user",Logined);
                model.addAttribute("twittes",new User());
                model.addAttribute("follow",new User());
                model.addAttribute("followed",Logined.getFollowered());
                return "index";
            }
            else
                model.addAttribute("message","Password incorrect");
        }
        else{
            model.addAttribute("message","User not found");
        }
        return "login";
    }

    @GetMapping("/all")
    public String get(Model model){
        List<User> userinfos= (List<User>) user.findAll();
        model.addAttribute("all", userinfos );

        List<String>twittes = new ArrayList<>();

        User usertemp=user.findByUsername("Changzhou Cai");
        if(usertemp!=null){
            HashMap<Long, String> temp=usertemp.getTwittes();
            String twittetemp="";
            if(temp==null)
                System.out.println("null twittes");
            else
            {

                            for(Long key:temp.keySet() ){
                twittetemp=temp.get(key);
                twittes.add(twittetemp);
            }
            }

            model.addAttribute("twittes", twittes);
        }
        return "all";
    }


}

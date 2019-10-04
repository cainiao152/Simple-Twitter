package com.simpletwitter.demo.Config;


import com.simpletwitter.demo.Entity.User;
import com.simpletwitter.demo.UserRepository.usersRepository;
import org.apache.tomcat.jni.Time;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class Dbconfig {

    @Bean
    CommandLineRunner commandLineRunner(usersRepository usersRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                HashMap<Long,String> temp= new HashMap<>();
                temp.put(System.currentTimeMillis(),"today is a good day");

                usersRepository.save(new User("Changzhou Cai","1234567",temp,new ArrayList<>()));
                temp.put(System.currentTimeMillis(),"today is a bad day");
                usersRepository.save(new User("Lingyang Cai","123456",temp,new ArrayList<>()));



            }


            };
        }

    }



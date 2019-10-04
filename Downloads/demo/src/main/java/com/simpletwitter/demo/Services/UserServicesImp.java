package com.simpletwitter.demo.Services;

import com.simpletwitter.demo.Entity.User;
import com.simpletwitter.demo.UserRepository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImp implements UserService {

    @Autowired
    usersRepository user;

    @Override
    public void save(User u) {
        user.save(u);
    }
}

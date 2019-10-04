package com.simpletwitter.demo.UserRepository;

import com.simpletwitter.demo.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface usersRepository extends CrudRepository <User,Long> {

    User findByUsername(String username);
    User findById (long id);

}

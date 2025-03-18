package com.restaurant.services;

import com.restaurant.entity.Users;
import com.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public Users saveUser(Users user){
        System.out.println("user is "+ user);
        return userRepository.save(user);
    }
}

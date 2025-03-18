package com.restaurant.controllers;

import com.restaurant.entity.Users;
import com.restaurant.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/create")
    public Users createUser(@RequestBody Users users) {
        return userServices.saveUser(users);
    }
}

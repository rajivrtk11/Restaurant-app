package com.restaurant.services;

import com.restaurant.entity.Users;
import com.restaurant.repositories.TaskRepository;
import com.restaurant.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public Users saveUser(Users user){
        System.out.println("user is "+ user);
//        Users save = userRepository.save(user);

        user.getFavourateList().forEach((task) -> {
            task.setUsers(user);
        });
        taskRepository.saveAll(user.getFavourateList());

        return user;
    }

}

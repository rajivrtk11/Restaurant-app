package com.restaurant.services;

import com.restaurant.entity.Users;
import com.restaurant.repositories.TaskRepository;
import com.restaurant.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public Users saveUser(Users user){
        System.out.println("user is "+ user);

        if(user.getTasksList() != null) {
            user.getTasksList().forEach((task) -> {
                task.setUsers(user);
            });
            taskRepository.saveAll(user.getTasksList());
        }
        else {
            Users save = userRepository.save(user);
        }

        return user;
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).get();
    }

}

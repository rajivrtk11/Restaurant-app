package com.restaurant.services;

import com.restaurant.entity.Task;
import com.restaurant.entity.Users;
import com.restaurant.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    // create task, update task, delete task

    @Transactional
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public void markfavourite(Long id) {
        Optional<Task> byId = taskRepository.findById(id);
        if(byId.isEmpty()) throw new IllegalArgumentException("Task with given id not exist");

        Task task = byId.get();
        task.setIsFavourite(true);
    }
}

package com.restaurant.controllers;

import com.restaurant.entity.Task;
import com.restaurant.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task task1 = taskService.createTask(task);

        return new ResponseEntity<>(task1, HttpStatus.CREATED);
    }

    // mark favourate task
    @GetMapping("/favourite/{id}")
    public ResponseEntity<?> markfavouriteTask(@PathVariable Long id) {
        taskService.markfavourite(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.restaurant.services;

import com.restaurant.entity.Task;
import com.restaurant.entity.Users;
import com.restaurant.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private Users users;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        users = new Users(1L, "John Doe", List.of(), new String[]{"ROLE_USER"});
        task = new Task(1L, "Task 1", "Description", true, users);
    }

    @Test
    void createTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task savedTask = taskService.createTask(task);

        assertEquals(1L, savedTask.getId());
    }

    @Test
    void markfavourite() {
        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(task));
        taskService.markfavourite(1L);

        assertEquals(true, task.getIsFavourite());

        taskService.markfavourite(3L);
    }

    @Test
    void markfavouriteIdNotFound() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> taskService.markfavourite(99L),
                "Task with given id not exist");
    }
}
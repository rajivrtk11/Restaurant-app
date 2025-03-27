package com.restaurant.entity;

import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private  Task task;
    private  Users users;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        users = new Users(1L, "John Doe", List.of(), new String[]{"ROLE_USER"});
        task = new Task(1L, "Task 1", "Description", true, users);
    }

    @Test
    void getId() {
        assertEquals(1L, task.getId());
    }

    @Test
    void getTitle() {
        assertEquals("Task 1", task.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("Description", task.getDescription());
    }

    @Test
    void getIsFavourite() {
        assertEquals(true, task.getIsFavourite());
    }

    @Test
    void getUsers() {
        assertEquals(users, task.getUsers());
    }

    @Test
    void setId() {
        task.setId(2L);
        assertEquals(2L, task.getId());
    }

    @Test
    void setTitle() {
        task.setTitle("Title 2");
        assertEquals("Title 2", task.getTitle());
    }

    @Test
    void setDescription() {
        task.setDescription("Description");
        assertEquals("Description", task.getDescription());
    }

    @Test
    void setIsFavourite() {
        task.setIsFavourite(true);
        assertEquals(true, task.getIsFavourite());
    }

    @Test
    void setUsers() {
        task.setUsers(users);
        assertEquals(users, task.getUsers());
    }

    @Test
    void testEquals() {

    }

    @Test
    void testHashCode() {
        Task task1 = new Task(1L, "Task 1", "Description", true, users);
        Task task2 = new Task(1L, "Task 1", "Description", true, users);
        Task task3 = new Task(2L, "Task 1", "Description", true, users);
        Task task4 = new Task(1L, "Task 1", "Description", true, users);
        Task task5 = new Task(1L, "Task 1", "Description", true, users);
        Object nonUserObject = "Some String"; // Completely different type

        // Self-equality
        assertEquals(task1, task1, "Object should be equal to itself");

        // Equality with same values
        assertEquals(task1, task2, "Objects with same field values should be equal");
        assertEquals(task1.hashCode(), task2.hashCode(), "Hash codes should match for equal objects");

        // Inequality with different field values
        assertNotEquals(task1, task3, "Objects with different ID and name should not be equal");
//        assertNotEquals(user1, user4, "Objects with same ID but different roles should not be equal");
//        assertNotEquals(user1, user5, "Objects with same ID but different task list should not be equal");

        // Null and different type checks
        assertNotEquals(task1, null, "Object should not be equal to null");
        assertNotEquals(task1, nonUserObject, "Object should not be equal to a different class");

        // Hashcode differences for distinct objects
        assertNotEquals(task1.hashCode(), task3.hashCode(), "Different objects should have different hash codes");

        // `canEqual` validation (ensures correct subclass handling)
        assertTrue(task1.canEqual(task2), "Users with same properties should pass canEqual check");
        assertFalse(task1.canEqual(nonUserObject), "Users should not be equal to a non-user object");
    }

    @Test
    void testToString() {
        String string = task.toString();
        assertEquals(string, task.toString());
    }
}
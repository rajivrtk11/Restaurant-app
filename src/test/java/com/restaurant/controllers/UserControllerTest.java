package com.restaurant.controllers;

import com.restaurant.entity.Users;
import com.restaurant.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Mock
    private UserServices userServices; // Mocking the service layer

    private UserController userController; // No @Spy annotation here

    private Users user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new Users(1L, "John Doe");

        // Manually create a spy on a fully initialized instance
        userController = spy(new UserController(userServices));
    }

    @Test
    void createUser() {
        when(userServices.saveUser(any(Users.class))).thenReturn(user); // Mock service call

        Users createdUser = userController.createUser(user); // Call real method

        // Verify that the real method in UserController was called
        verify(userController, times(1)).createUser(any(Users.class));

        // Verify that the service method was also invoked
        verify(userServices, times(1)).saveUser(any(Users.class));

        // Assertions
        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(user.getName(), createdUser.getName());
    }
}

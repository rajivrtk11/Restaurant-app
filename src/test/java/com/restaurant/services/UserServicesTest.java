package com.restaurant.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.restaurant.entity.Users;
import com.restaurant.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServicesTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServices userServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testSaveUser() {
        // Arrange
        Users user = new Users(1L, "John Doe"); // Adjust fields as per your model
        when(userRepository.save(any(Users.class))).thenReturn(user);

        // Act
        Users savedUser = userServices.saveUser(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getName());

        // Verify interactions
        verify(userRepository, times(1)).save(user);
    }
}

package com.restaurant.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.controllers.UserController;
import com.restaurant.entity.Users;
import com.restaurant.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class) // Enables Mockito annotations
class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;

    @Mock  // Mocking UserService
    private UserServices userServices;

    @InjectMocks  // Injects the mock into UserController
    private UserController userController;

    @BeforeEach
    void setUp() {
        // Initialize MockMvc with the controller and global exception handler
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Attach the exception handler
                .build();
    }

    @Test
    void shouldReturnValidationErrorsWhenInputIsInvalid() throws Exception {
        Users request = new Users();
        request.setName(null);  // Invalid input

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());  // 400 Bad Request
//                .andExpect(jsonPath("$.name").value("Name cannot be empty."));
    }

    @Test
    void shouldReturnSuccessWhenInputIsValid() throws Exception {
        Users request = new Users();
        request.setName("John Doe");

        when(userServices.saveUser(request)).thenReturn(request);

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());  // 200 OK
//                .andExpect(jsonPath("$").value("User created successfully!"));
    }
}

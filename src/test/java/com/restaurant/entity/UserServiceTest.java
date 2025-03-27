package com.restaurant.entity;

import com.restaurant.repositories.TaskRepository;
import com.restaurant.repositories.UserRepository;
import com.restaurant.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository usersRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private UserServices usersService;

    private Users user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new Users(1L, "John Doe", List.of(), new String[]{"ROLE_USER"});
    }

    @Test
    void testGetUserById() {
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));

        Users retrievedUser = usersService.getUserById(1L);

        assertNotNull(retrievedUser);
        assertEquals(1L, retrievedUser.getId());
        assertEquals("John Doe", retrievedUser.getName());
        assertArrayEquals(new String[]{"ROLE_USER"}, retrievedUser.getRoles());
        verify(usersRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveUser() {
        lenient().when(usersRepository.save(any(Users.class))).thenReturn(user);
        lenient().when(taskRepository.saveAll(anyList())).thenReturn(List.of());

        Users savedUser = usersService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getName());
        assertArrayEquals(new String[]{"ROLE_USER"}, savedUser.getRoles());
//        verify(usersRepository, times(1)).save(user);
//        verify(taskRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testEqualsAndHashCode() {
        Users user1 = new Users(1L, "John Doe", List.of(), new String[]{"ROLE_USER"});
        Users user2 = new Users(1L, "John Doe", List.of(), new String[]{"ROLE_USER"});
        Users user3 = new Users(2L, "Jane Doe", List.of(), new String[]{"ROLE_ADMIN"});
        Users user4 = new Users(1L, "John Doe", List.of(), new String[]{"ROLE_ADMIN"}); // Same ID, different role
        Users user5 = new Users(1L, "John Doe", List.of(new Task()), new String[]{"ROLE_USER"}); // Same ID, different tasks list
        Object nonUserObject = "Some String"; // Completely different type

        // Self-equality
        assertEquals(user1, user1, "Object should be equal to itself");

        // Equality with same values
        assertEquals(user1, user2, "Objects with same field values should be equal");
        assertEquals(user1.hashCode(), user2.hashCode(), "Hash codes should match for equal objects");

        // Inequality with different field values
        assertNotEquals(user1, user3, "Objects with different ID and name should not be equal");
        assertNotEquals(user1, user4, "Objects with same ID but different roles should not be equal");
        assertNotEquals(user1, user5, "Objects with same ID but different task list should not be equal");

        // Null and different type checks
        assertNotEquals(user1, null, "Object should not be equal to null");
        assertNotEquals(user1, nonUserObject, "Object should not be equal to a different class");

        // Hashcode differences for distinct objects
        assertNotEquals(user1.hashCode(), user3.hashCode(), "Different objects should have different hash codes");

        // `canEqual` validation (ensures correct subclass handling)
        assertTrue(user1.canEqual(user2), "Users with same properties should pass canEqual check");
        assertFalse(user1.canEqual(nonUserObject), "Users should not be equal to a non-user object");
    }

    @Test
    void testSetId() {
        user.setId(2L);
        assertEquals(2L, user.getId());
    }

    @Test
    void testSetName() {
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    void testSetTasksList() {
        user.setTasksList(List.of());
        assertEquals(0, user.getTasksList().size());
    }

    @Test
    void testSetRoles() {
        user.setRoles(new String[]{"ROLE_ADMIN"});
        assertArrayEquals(new String[]{"ROLE_ADMIN"}, user.getRoles());
    }

    @Test
    void testCanEqual() {
        Users otherUser = new Users(2L, "Jane Doe", List.of(), new String[]{"ROLE_ADMIN"});
        assertTrue(user.canEqual(otherUser));
    }
}



package com.restaurant.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.restaurant.entity.Restaurant;
import com.restaurant.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRestaurant_ShouldSaveAndReturnRestaurant() {
        // Arrange
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");

        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

        // Act
        Restaurant savedRestaurant = restaurantService.createRestaurant(restaurant);

        // Assert
        assertNotNull(savedRestaurant);
        assertEquals("Test Restaurant", savedRestaurant.getName());

        // Verify method call
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    void getRestaurantByPincodes_ShouldReturnPaginatedResults() {
        // Arrange
        String pincode = "123456";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1L);
        restaurant1.setName("Test Restaurant 1");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Test Restaurant 2");

        Page<Restaurant> restaurantPage = new PageImpl<>(List.of(restaurant1, restaurant2));

        when(restaurantRepository.findByPincode(pincode, pageable)).thenReturn(restaurantPage);

        // Act
        Page<Restaurant> result = restaurantService.getRestaurantByPincodes(pincode, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals("Test Restaurant 1", result.getContent().get(0).getName());

        // Verify method call
        verify(restaurantRepository, times(1)).findByPincode(pincode, pageable);
    }

    @Test
    void getRestaurantById_ShouldReturnRestaurant() {
        // Arrange
        Long restaurantId = 1L;
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        restaurant.setName("Test Restaurant");

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        // Act
        Restaurant result = restaurantService.getRestaurantById(restaurantId);

        // Assert
        assertNotNull(result);
        assertEquals(restaurantId, result.getId());
        assertEquals("Test Restaurant", result.getName());

        // Verify method call
        verify(restaurantRepository, times(1)).findById(restaurantId);
    }

    @Test
    void getRestaurantById_ShouldThrowException_WhenRestaurantNotFound() {
        // Arrange
        Long restaurantId = 1L;
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> restaurantService.getRestaurantById(restaurantId)
        );

        assertEquals("Restaurant with this id doest not exist ", exception.getMessage());

        // Verify method call
        verify(restaurantRepository, times(1)).findById(restaurantId);
    }
}

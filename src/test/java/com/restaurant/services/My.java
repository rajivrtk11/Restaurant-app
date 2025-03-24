package com.restaurant.services;

import com.restaurant.entity.Restaurant;
import com.restaurant.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class My {
   @Mock
   RestaurantRepository restaurantRepository;

   @InjectMocks
   RestaurantService restaurantService;

   @BeforeEach
   void setUp() {
        MockitoAnnotations.openMocks(this);
   }

   @Test
    void testCreateRestaurant(){
       Restaurant restaurant = new Restaurant(1L, "Restaurant 1", new String[]{"1"});
       when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

       Restaurant save = restaurantRepository.save(restaurant);
       assertEquals("Restaurant 1", restaurant.getName());
       assertNotNull(save);

       verify(restaurantRepository).save(restaurant);
   }

   @Test
   void testSum(){

   }

}
